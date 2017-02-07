package com.capinfo.common.security.authentication;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.capinfo.common.model.Resource;
import com.capinfo.common.model.SystemUser;
import com.capinfo.common.security.Constants;
import com.capinfo.common.web.service.ResourceService;
import com.capinfo.common.web.service.SystemUserService;

public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler {

	static final String ANONYMOUS = "anonymousUser";

	private static final Log LOGGER = LogFactory.getLog(AuthenticationSuccessHandlerImpl.class);

	@Autowired
	private SystemUserService systemUserService;
	
	@Autowired
	private ResourceService resourceService;

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException,
			IOException {
		// 登录成功后记录Session
		holdUserSession(request, authentication);
		// super
		super.onAuthenticationSuccess(request, response, authentication);
	}

	/**
	 * 
	 * 刷新已登录用户Session
	 * 
	 * @param request
	 * @param securityContext
	 */
	private void holdUserSession(HttpServletRequest request, Authentication authentication) {
		Object principal = authentication.getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		if (!username.equalsIgnoreCase(ANONYMOUS)) {
			try {
				SystemUser user = systemUserService.loadUserByUsername(username);
				LOGGER.info("login user:" + user.getId());
				request.getSession().setAttribute(Constants.ADMIN_ATTRIBUTE_KEY, user);
				// 添加菜单Session
				LinkedHashMap<Long, Resource> menus = new LinkedHashMap<Long, Resource>();
				List<Resource> menuList = resourceService.getResourcesByRoles(user.getRoles());
				for (Resource resource : menuList) {
					if (resource.getId() > Resource.ROOT_PARENT) {
						if (resource.getParentId() == Resource.ROOT_PARENT) {
							if (null == menus.get(resource.getId())) {
								menus.put(resource.getId(), resource);
							}
						} else {
							Resource parent = menus.get(resource.getParentId());
							if (null != parent) {
								parent.getItems().add(resource);
								menus.put(parent.getId(), parent);
							} else {
								parent = resource.getParent();
								parent.getItems().add(resource);
								menus.put(parent.getId(), parent);
							}
						}
					}
				}
				request.getSession().setAttribute("menus", menus);
			} catch (NullPointerException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage(), e);
				System.out.println("No Such User");
			}
		}
	}
}
