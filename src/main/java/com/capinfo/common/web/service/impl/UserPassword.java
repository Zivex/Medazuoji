package com.capinfo.common.web.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class UserPassword {

	public static void main(String[] args) {
		PasswordEncoder encoder=new StandardPasswordEncoder();
		String pass=encoder.encode("123456");
		System.out.println(pass);
		
	}

}
