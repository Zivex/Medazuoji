/**
 * @Copyright(c) 2014 CAPINFO All Rights Reserved
 * @createTime: 2014-7-31
 */

package com.capinfo.common.security.userdetails;

import java.util.List;

import com.capinfo.common.security.SecurityResource;

/**
 * @description:
 *
 * @version: 1.0
 * @author: lp
 * 
 */

public interface AuthorityDetailsService {
	
	/**
	 *
	 *获取系统资源列表
	 *
	 * @return 资源列表
	 */
	public List<SecurityResource> loadRoleAuthorities();
	
	
	/**
	 * 
	 * 根据角色获取资源列表
	 *
	 * @param role
	 * @return
	 */
	public List<SecurityResource> loadRoleAuthoritiesByRole(String role);

}
