package com.capinfo.common.web.parameter;

import javax.validation.Valid;

import com.capinfo.common.model.Resource;
import com.capinfo.framework.web.parameter.DataListParameter;

public class ResourceParameter extends DataListParameter<Resource> {

	@Valid 
	private Resource entity = new Resource();

	private Class<Resource> entityClazz = Resource.class;

	public Resource getEntity() {
		return entity;
	}

	public void setEntity(Resource entity) {
		this.entity = entity;
	}

	public Class<Resource> getEntityClazz() {
		return entityClazz;
	}


}
