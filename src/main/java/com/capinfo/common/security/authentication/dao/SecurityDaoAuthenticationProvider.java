/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.capinfo.common.security.authentication.dao;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details
 * from a {@link UserDetailsService}.
 * 
 * @author Ben Alex
 * @author Rob Winch
 */
public class SecurityDaoAuthenticationProvider extends DaoAuthenticationProvider {

	/**
	 * 重写org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider.authenticate
	 * 
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
				messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only UsernamePasswordAuthenticationToken is supported"));

		// Determine username  credentials
		String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

		boolean cacheWasUsed = true;
		UserDetails user = getUserCache().getUserFromCache(username);
		// Ehcache中取出的UserDetails中password为null.增加username和password的非空判断
		// boolean userOutCache=user == null;
		boolean userOutCache = user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword()) ;
		if (userOutCache) {
			cacheWasUsed = false;

			try {
				user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
				
				if (!authentication.getCredentials().toString().equals(user.getPassword())) {
					throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
				}
			} catch (UsernameNotFoundException notFound) {
				logger.debug("User '" + username + "' not found");

				if (hideUserNotFoundExceptions) {
					throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
				} else {
					throw notFound;
				}
			}

			Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");
		}

		try {
			getPreAuthenticationChecks().check(user);
		} catch (AuthenticationException exception) {
			if (cacheWasUsed) {
				// There was a problem, so try again after checking
				// we're using latest data (i.e. not from the cache)
				cacheWasUsed = false;
				user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
				getPreAuthenticationChecks().check(user);
			} else {
				throw exception;
			}
		}

		getPostAuthenticationChecks().check(user);

		if (!cacheWasUsed) {
			getUserCache().putUserInCache(user);
			UserDetails user2 = getUserCache().getUserFromCache(username);
		}

		Object principalToReturn = user;

		if (isForcePrincipalAsString()) {
			principalToReturn = user.getUsername();
		}

		return createSuccessAuthentication(principalToReturn, authentication, user);
	}

}
