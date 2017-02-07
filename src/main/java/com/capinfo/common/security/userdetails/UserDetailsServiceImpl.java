package com.capinfo.common.security.userdetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import com.capinfo.common.security.SecurityUser;

/**
 * 提供一个经过用户认证后的UserDetails。 该UserDetails包括用户名、密码、是否可用、是否过期等信息。
 * 用户如果没有任何角色也无法登陆系统，可复写JdbcDaoImpl中的<code>loadUserByUsername</code>方法
 */
public class UserDetailsServiceImpl extends JdbcDaoImpl {

	@Override
	protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery, List<GrantedAuthority> combinedAuthorities) {
		String returnUsername = userFromUserQuery.getUsername();
		if (!isUsernameBasedPrimaryKey()) {
			returnUsername = username;
		}
		SecurityUser user = (SecurityUser) userFromUserQuery;
		return new SecurityUser(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(), true, true, true, combinedAuthorities,
				user.getUserId(), user.getAlias());
	}

	@Override
	protected List<UserDetails> loadUsersByUsername(String username) {
		return getJdbcTemplate().query(getUsersByUsernameQuery(), new String[] { username }, new RowMapper<UserDetails>() {
			public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
				String username = rs.getString(1);
				String password = rs.getString(2);
				boolean enabled = rs.getBoolean(3);
				Long userId = rs.getLong(4);
				String alias = rs.getString(5);
				return new SecurityUser(username, password, enabled, true, true, true, AuthorityUtils.NO_AUTHORITIES, userId, alias);
			}
		});

	}

}
