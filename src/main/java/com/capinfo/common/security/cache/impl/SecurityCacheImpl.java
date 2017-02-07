package com.capinfo.common.security.cache.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

import com.capinfo.common.security.access.InvocationSecurityMetadataSourceServiceImpl;
import com.capinfo.common.security.cache.SecurityCache;
import com.capinfo.framework.model.system.SecureRole;
import com.capinfo.framework.model.system.SecureUser;

public class SecurityCacheImpl implements SecurityCache {
	
	@Autowired
	private UserCache userCache;
	
	@Autowired
	private InvocationSecurityMetadataSourceServiceImpl securityMetadataSource;
	
	@Override
	public UserDetails getUserFromCache(String name){
		if(null!=userCache){
			return userCache.getUserFromCache(name);
		}
		return null;
	}
	
	@Override
	public void modifyUserInCache(SecureUser sysUser, String userName) {
		removeUserFromCache(userName);
		List<GrantedAuthority> authorities = role2authorities(sysUser.getRoles());
		User user=new User(sysUser.getLogonName(),sysUser.getPassword(),authorities);
		userDetailsInCache(user);
	}
	
	@Override
	public void removeUserFromCache(String userName) {
		if(null!=userCache){
			UserDetails userDetails = userCache.getUserFromCache(userName);
			if (userDetails != null){
				userCache.removeUserFromCache(userName);
			}
		}
	}
	
	@Override
	public void refreshResource(){
		securityMetadataSource.updateResourceDefine();
	}
	
	
	private void userDetailsInCache(UserDetails userDetails) {
		if(null!=userCache){
			userCache.putUserInCache(userDetails);
		}
	}
	
	/**
	 * 由role转为GrantedAuthority
	 */
	private List<GrantedAuthority> role2authorities(Collection roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if(null!=roles){
			for (Iterator iter = roles.iterator(); iter.hasNext();) {
				SecureRole role = (SecureRole) iter.next();
				authorities.add(new SimpleGrantedAuthority(role.getCode()));
			}
		}
		return authorities;
	}
}
