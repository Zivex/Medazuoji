package com.capinfo.omp.utils;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public class SysInfo {
	
	Authentication authentication;
	
	public String holdUserSession() {
		Object principal = authentication.getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		}else {
			username = principal.toString();
		}
		return username;
		
	}
}
