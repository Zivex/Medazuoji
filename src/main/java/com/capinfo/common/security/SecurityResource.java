package com.capinfo.common.security;

import org.springframework.security.core.GrantedAuthority;

public class SecurityResource implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7467917011769736362L;

	private final String resourceId;

	private final String resourceValue;

	private final String role;

	public SecurityResource(String resourceId, String resourceValue, String role) {
		super();
		this.resourceId = resourceId;
		this.resourceValue = resourceValue;
		this.role = role;
	}

	public String getAuthority() {
		return role;
	}

	public String getResourceId() {
		return resourceId;
	}

	public String getResourceValue() {
		return resourceValue;
	}

}
