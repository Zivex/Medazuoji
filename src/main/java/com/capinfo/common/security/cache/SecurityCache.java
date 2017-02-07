package com.capinfo.common.security.cache;

import org.springframework.security.core.userdetails.UserDetails;

import com.capinfo.framework.model.system.SecureUser;

public interface SecurityCache {

	public UserDetails getUserFromCache(String name);

	public void modifyUserInCache(SecureUser sysUser, String userName);

	public void removeUserFromCache(String userName);

	public void refreshResource();

}
