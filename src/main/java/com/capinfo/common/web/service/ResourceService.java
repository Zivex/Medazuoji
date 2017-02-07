package com.capinfo.common.web.service;

import java.util.List;
import java.util.Set;

import com.capinfo.common.model.Resource;
import com.capinfo.common.model.Role;
import com.capinfo.common.web.parameter.ResourceParameter;
import com.capinfo.framework.web.service.CommonsDataOperationService;

public interface ResourceService extends CommonsDataOperationService<Resource, ResourceParameter> {

	/**
	 * 删除指定资源
	 * @param entity
	 * @return
	 */
	public boolean deleteResource(Resource entity);
	
	/**
	 * 根据ID获取资源
	 * @param id
	 * @return
	 */
	public Resource getResourceById(Long id);
	
	/**
	 * 添加、编辑资源
	 * @param parameter
	 * @return
	 */
	public boolean saveOrUpdateResource(ResourceParameter parameter);
	
	/**
	 * 所有资源
	 * 
	 * @param parameter
	 * @return
	 */
	public List<Resource> getAllResources();

	/**
	 * @author: lp
	 * 根据角色获取资源
	 * @param roles
	 * @return
	 */
	List<Resource> getResourcesByRoles(Set<Role> roles);
}
