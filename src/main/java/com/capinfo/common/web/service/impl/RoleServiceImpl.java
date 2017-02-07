package com.capinfo.common.web.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capinfo.common.model.Role;
import com.capinfo.common.web.parameter.RoleParameter;
import com.capinfo.common.web.service.RoleService;
import com.capinfo.framework.dao.SearchCriteria.OrderRow;
import com.capinfo.framework.dao.SearchCriteriaBuilder;
import com.capinfo.framework.dao.impl.restriction.RestrictionExpression;
import com.capinfo.framework.web.service.impl.CommonsDataOperationServiceImpl;

public class RoleServiceImpl extends CommonsDataOperationServiceImpl<Role, RoleParameter> implements RoleService {

	private static final Log LOGGER = LogFactory.getLog(RoleServiceImpl.class);

	public SearchCriteriaBuilder<Role> getSearchCriteriaBuilder(RoleParameter parameter) {
		SearchCriteriaBuilder<Role> searchCriteriaBuilder = super.getSearchCriteriaBuilder(parameter);
		Role role = (Role) parameter.getEntity();
		searchCriteriaBuilder.addQueryCondition("name", RestrictionExpression.LIKE_OP, role.getName());
		searchCriteriaBuilder.addOrderCondition("id", OrderRow.ORDER_DESC);
		return searchCriteriaBuilder;
	}

	@Override
	public List<Role> getAllRoles() {
		try {
			return getGeneralService().getAllObjects(Role.class);
		} catch (RuntimeException e) {
			LOGGER.debug(e.getMessage(), e);
			return null;
		}
	}

	@Override
	public Role getRoleById(Long id) {
		Role role = null;
		try {
			role = getGeneralService().getObjectById(Role.class, id);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			role = null;
		}
		return role;
	}

	@Override
	public boolean saveOrUpdateRole(RoleParameter parameter) {
		boolean suc = true;
		try {
			// 为了保证修改角色时，不会清理到实际已经与角色关联的用户。
			parameter.setIgnoreProperties(new String[] { "users", "itemTypes" });
			// 装配资源
			parameter.populateResource();
			super.saveOrUpdate(parameter);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			suc = false;
		}
		return suc;
	}

	@Override
	public boolean deleteRole(Role role) {
		boolean suc = true;
		try {
			Role entity = getGeneralService().getObjectById(Role.class, role.getId());
			getGeneralService().delete(entity);
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
			suc = false;
		}
		return suc;
	}

}
