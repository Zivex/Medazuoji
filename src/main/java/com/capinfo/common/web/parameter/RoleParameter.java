package com.capinfo.common.web.parameter;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.capinfo.common.model.Resource;
import com.capinfo.common.model.Role;
import com.capinfo.framework.web.parameter.DataListParameter;

public class RoleParameter extends DataListParameter<Role> {
	
	private Role entity = new Role();

	private Class<Role> entityClazz = Role.class;
	
	private String resourceIdString = "";
	
	private String resourceTree;
	
	public Role getEntity() {
		return entity;
	}

	public void setEntity(Role entity) {
		
		this.entity = entity;

		StringBuilder resourceIdBuffer = new StringBuilder();
		Set<Resource> resources = entity.getResources();
		for(Resource resource: resources) {
			
			resourceIdBuffer.append(resource.getId()).append(",");
		}
		
		resourceIdString = resourceIdBuffer.toString();
		
	}

	public Class<Role> getEntityClazz() {
		return entityClazz;
	}

	public void setEntityClazz(Class<Role> entityClazz) {
		this.entityClazz = entityClazz;
	}

	public String getResourceIdString() {
		return resourceIdString;
	}

	public void setResourceIdString(String resourceIdString) {
		
		this.resourceIdString = resourceIdString;
	}
	
	public void populateResource(){
		 Set<Resource> resources=new HashSet<Resource>();
		 if(StringUtils.isNotBlank(this.resourceIdString)){
			 String[] idArray=StringUtils.split(this.resourceIdString, ",");
			 for (String idStr : idArray) {
				Long id = Long.parseLong(idStr);
				resources.add(new Resource(id));
			}
		 }
		 this.entity.setResources(resources);
	}

	public String getResourceTree() {
		return resourceTree;
	}

	public void setResourceTree(String resourceTree) {
		this.resourceTree = resourceTree;
	}

}
