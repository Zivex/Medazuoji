package com.capinfo.common.security.access;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.capinfo.common.security.SecurityResource;
import com.capinfo.common.security.userdetails.AuthorityDetailsService;

/**
 * <p>
 * 提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * </p>
 * <p>
 * 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * </p>
 * 
 */
public class InvocationSecurityMetadataSourceServiceImpl implements FilterInvocationSecurityMetadataSource {

	private ConcurrentHashMap<String, Collection<ConfigAttribute>> requestMap;

	private AuthorityDetailsService authorityDetailsService;

	private static final Log LOGGER = LogFactory.getLog(InvocationSecurityMetadataSourceServiceImpl.class);

	public InvocationSecurityMetadataSourceServiceImpl(AuthorityDetailsService authorityDetailsService) {
		this.authorityDetailsService = authorityDetailsService;
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		List<SecurityResource> rList = authorityDetailsService.loadRoleAuthorities();
		loadResources(rList);

	}

	public void updateResourceDefine() {
		List<SecurityResource> rList = authorityDetailsService.loadRoleAuthorities();
		loadResources(rList);
	}

	/**
	 * 
	 * 加载权限资源列表
	 * 
	 * @param rList
	 */
	public void loadResources(List<SecurityResource> rList) {
		if (requestMap == null) {
			requestMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
		} else {
			requestMap.clear();
		}
		for (SecurityResource resource : rList) {
			String url = resource.getResourceValue();
			String role = resource.getAuthority();
			if (StringUtils.isNotEmpty(url) && StringUtils.isNotEmpty(role)) {
				ConfigAttribute ca = new SecurityConfig(String.valueOf(role));
				if (requestMap.containsKey(url)) {
					Collection<ConfigAttribute> value = requestMap.get(url);
					value.add(ca);
					requestMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					requestMap.put(url, atts);
				}
			}
		}
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// Set allAttributes = new HashSet();
		// java.util.Map.Entry entry;
		// Map<String, Collection<ConfigAttribute>> requestMap =
		// ResourceCache.getrequestMap();
		// Iterator it = ResourceCache.getrequestMap().entrySet().iterator();
		// while (it.hasNext()) {
		// entry = (java.util.Map.Entry) it.next();
		// allAttributes.addAll((Collection) entry.getValue());
		// }
		// return allAttributes;
		return null;
	}

	// 根据URL，找到相关的权限配置。
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		// object 是一个URL，被用户请求的url。
		FilterInvocation filterInvocation = (FilterInvocation) object;
		HttpServletRequest request = filterInvocation.getHttpRequest();
		Iterator<String> ite = getRequestMap().keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
			if (requestMatcher.matches(request)) {
				LOGGER.info("request:" + request.getRequestURI());
				LOGGER.info("match:" + resURL);
				return getRequestMap().get(resURL);
			}
		}
		return null;
	}

	public AuthorityDetailsService getAuthorityDetailsService() {
		return authorityDetailsService;
	}

	public void setAuthorityDetailsService(AuthorityDetailsService authorityDetailsService) {
		this.authorityDetailsService = authorityDetailsService;
	}

	public ConcurrentHashMap<String, Collection<ConfigAttribute>> getRequestMap() {
		return requestMap;
	}

	@Override
	public boolean supports(Class<?> arg0) {

		return true;
	}

}
