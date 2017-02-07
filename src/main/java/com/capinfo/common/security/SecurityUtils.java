package com.capinfo.common.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

	private SecurityUtils() {
	}

	/**
	 * 全局获取UserDetails
	 * 
	 * @return
	 */
	public static SecurityUser getSecurityUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SecurityUser securityUser = null;
		if (principal instanceof UserDetails) {
			securityUser = (SecurityUser) principal;
		}
		return securityUser;
	}

}
