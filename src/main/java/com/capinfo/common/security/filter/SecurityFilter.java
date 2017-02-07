package com.capinfo.common.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		// /login.shtm 登陆页
		if (httpServletRequest.getRequestURI().indexOf("/login.shtml") != -1) {
			chain.doFilter(request, response);
			return;
		}

		// String ip = request.getRemoteAddr();//返回发出请求的IP地址
		if (isAuthenticationed()) {
			chain.doFilter(request, response);
		} else {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login.shtml");
		}

		// chain.doFilter(request, response);

	}

	/**
	 * 判断用户是否授权
	 * 
	 * @return
	 */
	private boolean isAuthenticationed() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (null == securityContext) {
			return false;
		}
		if (null == securityContext.getAuthentication()) {
			return false;
		}
		Object authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken) {
			return false;
		}
		return true;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {

	}

}
