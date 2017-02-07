package com.capinfo.common.security.cache;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import com.capinfo.framework.model.system.SecureResource;
import com.capinfo.framework.model.system.SecureRole;

public class ResourceCache implements Serializable {

	private static ConcurrentMap<String, Collection<ConfigAttribute>> resourceMap = null;

	private ResourceCache() {

	}

	/**
	 * @return the resourceMap
	 */
	public static Map<String, Collection<ConfigAttribute>> getResourceMap() {
		return resourceMap;
	}

	/**
	 * 
	 * 加载权限资源列表
	 * 
	 * @param rList
	 */
	public static void loadResources(List<SecureResource> rList) {
		if (resourceMap == null) {
			resourceMap = new ConcurrentHashMap<String, Collection<ConfigAttribute>>();
		} else {
			resourceMap.clear();
		}
		for (SecureResource resource : rList) {
			String url = resource.getValue();
			Set<SecureRole> roles = resource.getRoles();
			if (null != roles) {
				for (SecureRole role : roles) {
					ConfigAttribute ca = new SecurityConfig(String.valueOf(role.getCode()));
					if (resourceMap.containsKey(url)) {
						Collection<ConfigAttribute> value = resourceMap.get(url);
						value.add(ca);
						resourceMap.put(url, value);
					} else {
						Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
						atts.add(ca);
						resourceMap.put(url, atts);
					}
				}
			}

		}
	}

}
