package com.capinfo.common.web.parameter;

import java.util.Set;

import javax.validation.Valid;

import com.capinfo.common.model.Role;
import com.capinfo.common.model.SystemUser;
import com.capinfo.framework.web.parameter.DataListParameter;

public class SystemUserParameter extends DataListParameter<SystemUser> {

	@Valid
	private SystemUser entity = new SystemUser();
	
	private Class<SystemUser> entityClazz = SystemUser.class;
	
	private Long[] roleIds;

	private String rolesIdStrs;
	
	private String oldPassword;
	
	
	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public SystemUser getEntity() {
		return entity;
	}

	public void setEntity(SystemUser entity) {
		this.entity = entity;
	}

	public Class<SystemUser> getEntityClazz() {
		return entityClazz;
	}

	public void setEntityClazz(Class<SystemUser> entityClazz) {
		this.entityClazz = entityClazz;
	}

	public String getRolesIdStrs() {
		rolesIdStrs="";
		Set<Role> roles=entity.getRoles();
		if(null!=roles&&roles.size()>0){
			for (Role role : roles) {
				rolesIdStrs=rolesIdStrs+role.getId()+",";
			}
			rolesIdStrs=rolesIdStrs.substring(0, rolesIdStrs.length()-1);
		}
		return rolesIdStrs;
	}

	public void setRolesIdStrs(String rolesIdStrs) {
		this.rolesIdStrs = rolesIdStrs;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}
