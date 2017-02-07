package com.capinfo.common.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.capinfo.common.security.Constants;

public class LogoutSuccessHandlerImpl extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 登出后删除session
		request.getSession().removeAttribute(Constants.ADMIN_ATTRIBUTE_KEY);
		// super
		super.onLogoutSuccess(request, response, authentication);
	}
}
