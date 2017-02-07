package com.capinfo.common.security.userdetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.capinfo.common.security.SecurityResource;

/**
 * 提供一个经过用户认证后的UserDetails。 该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 * 用户如果没有任何角色也无法登陆系统，可复写JdbcDaoImpl中的<code>loadUserByUsername</code>方法
 */
public class AuthorityDetailsServiceImpl extends JdbcDaoSupport implements AuthorityDetailsService {
		

	private String roleAuthoritiesNameQuery;

	private String roleAuthoritiesByRoleNameQuery;

	public String getRoleAuthoritiesNameQuery() {
		return roleAuthoritiesNameQuery;
	}

	public void setRoleAuthoritiesNameQuery(String roleAuthoritiesNameQuery) {
		this.roleAuthoritiesNameQuery = roleAuthoritiesNameQuery;
	}

	public String getRoleAuthoritiesByRoleNameQuery() {
		return roleAuthoritiesByRoleNameQuery;
	}

	public void setRoleAuthoritiesByRoleNameQuery(
			String roleAuthoritiesByRoleNameQuery) {
		this.roleAuthoritiesByRoleNameQuery = roleAuthoritiesByRoleNameQuery;
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capinfo.framework.security.userdetails.AuthorityDetailsService#
	 * loadRoleAuthorities()
	 */
	@Override
	public List<SecurityResource> loadRoleAuthorities() {
		return getJdbcTemplate().query(getRoleAuthoritiesNameQuery(),
				new RowMapper<SecurityResource>() {
					public SecurityResource mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						String resourceId = rs.getString(1);
						String resourceValue = rs.getString(2);
						String roleCode = rs.getString(3);
						return new SecurityResource(resourceId, resourceValue,
								roleCode);
					}
				});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capinfo.framework.security.userdetails.AuthorityDetailsService#
	 * loadRoleAuthoritiesByRole(java.lang.String)
	 */
	@Override
	public List<SecurityResource> loadRoleAuthoritiesByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

}
