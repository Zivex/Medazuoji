package com.capinfo.common.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capinfo.common.model.Resource;
import com.capinfo.common.model.Role;
import com.capinfo.common.web.parameter.ResourceParameter;
import com.capinfo.common.web.service.ResourceService;
import com.capinfo.framework.dao.SearchCriteria.OrderRow;
import com.capinfo.framework.dao.SearchCriteriaBuilder;
import com.capinfo.framework.dao.impl.restriction.RestrictionExpression;
import com.capinfo.framework.web.service.impl.CommonsDataOperationServiceImpl;

public class ResourceServiceImpl extends CommonsDataOperationServiceImpl<Resource, ResourceParameter> implements ResourceService {

	private static final Log LOGGER = LogFactory.getLog(ResourceServiceImpl.class);

	public SearchCriteriaBuilder<Resource> getSearchCriteriaBuilder(ResourceParameter parameter) {
		SearchCriteriaBuilder<Resource> searchCriteriaBuilder = super.getSearchCriteriaBuilder(parameter);
		Resource resource = (Resource) parameter.getEntity();
		searchCriteriaBuilder.addQueryCondition("name", RestrictionExpression.LIKE_OP, resource.getName());
		searchCriteriaBuilder.addOrderCondition("parentId", OrderRow.ORDER_ASC);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		searchCriteriaBuilder.addOrderCondition("id", OrderRow.ORDER_ASC);
		return searchCriteriaBuilder;
	}

	@Override
	public boolean deleteResource(Resource entity) {
		boolean suc = true;
		try {
			getGeneralService().delete(entity);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			suc = false;
		}
		return suc;

	}

	@Override
	public Resource getResourceById(Long id) {
		Resource resource = null;
		try {
			resource = getGeneralService().getObjectById(Resource.class, id);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			resource = null;
		}
		return resource;
	}

	// @Override
	// public boolean saveOrUpdateResource(ResourceParameter parameter) {
	// boolean suc = true;
	// try {
	// Resource resource=parameter.getEntity();
	// if(null!=resource.getId()){
	// Resource entity=getGeneralService().getObjectById(Resource.class,
	// resource.getId());
	// BeanUtils.copyProperties(resource, entity);
	// resource=entity;
	// }
	// getGeneralService().saveOrUpdate(resource);
	// } catch (Exception e) {
	// e.printStackTrace();
	// suc = false;
	// }
	// return suc;
	// }

	@Override
	public boolean saveOrUpdateResource(ResourceParameter parameter) {
		boolean suc = true;
		try {
			Resource resource = parameter.getEntity();
			if (null != resource.getId()) {
				getGeneralService().merge(resource);
			} else {
				getGeneralService().persist(resource);
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			suc = false;
		}
		return suc;
	}

	@Override
	public List<Resource> getAllResources() {
		List<Resource> resources = new ArrayList<Resource>();
		SearchCriteriaBuilder<Resource> searchCriteriaBuilder = new SearchCriteriaBuilder<Resource>(Resource.class);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, Boolean.TRUE);
		searchCriteriaBuilder.addOrderCondition("parentId", OrderRow.ORDER_ASC);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		searchCriteriaBuilder.addOrderCondition("id", OrderRow.ORDER_ASC);
		resources = getGeneralService().getObjects(searchCriteriaBuilder.build());
		return resources;
	}

	@Override
	public List<Resource> getResourcesByRoles(Set<Role> roles) {
		List<Resource> resources = new ArrayList<Resource>();
		SearchCriteriaBuilder<Resource> searchCriteriaBuilder = new SearchCriteriaBuilder<Resource>(Resource.class);
		String ids = "";
		for (Role role : roles) {
			Set<Resource> res = role.getResources();
			for (Resource resource : res) {
				ids = ids + resource.getId() + ",";
			}
		}
		if (ids.endsWith(",")) {
			ids = ids.substring(0, ids.length() - 1);
		}
		if (null != ids && !ids.trim().equals("")) {

			searchCriteriaBuilder.addQueryCondition("display", RestrictionExpression.EQUALS_OP, Boolean.TRUE);
			searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, Boolean.TRUE);
			searchCriteriaBuilder.addAdditionalRestrictionSql(" this_.id in(" + ids + ")");
			searchCriteriaBuilder.addOrderCondition("parentId", OrderRow.ORDER_ASC);
			searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
			resources = getGeneralService().getObjects(searchCriteriaBuilder.build());
		}
		return resources;
	}

}
