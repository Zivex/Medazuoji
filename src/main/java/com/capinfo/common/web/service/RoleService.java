package com.capinfo.common.web.service;

import java.util.List;

import com.capinfo.common.model.Role;
import com.capinfo.common.web.parameter.RoleParameter;
import com.capinfo.framework.web.service.CommonsDataOperationService;

/**
 * <p>
 * Title: RoleService
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 */
public interface RoleService extends CommonsDataOperationService<Role, RoleParameter> {
	
	/**
	 * 所有角色
	 * 
	 * @return
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 角色
	 * 
	 * @param parameter
	 * @return
	 */
	public Role getRoleById(Long id);
	
	/**
	 * 添加、编辑角色
	 * @param parameter
	 * @return
	 */
	public boolean saveOrUpdateRole(RoleParameter parameter);
	
	/**
	 * 删除指定角色
	 * @param entity
	 * @return
	 */
	public boolean deleteRole(Role entity);
}
