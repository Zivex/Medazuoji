package com.capinfo.common.web.service;

import java.util.List;

import com.capinfo.common.model.Resource;
import com.capinfo.common.model.SystemUser;
import com.capinfo.common.web.parameter.SystemUserParameter;
import com.capinfo.framework.web.service.CommonsDataOperationService;

public interface SystemUserService extends CommonsDataOperationService<SystemUser, SystemUserParameter> {

	/**
	 * 更新用户信息
	 * 
	 * @param parameter
	 * @return
	 */
	public boolean updateUserInfo(SystemUserParameter parameter);

	/**
	 * 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean saveUser(SystemUser user);

	/**
	 * 用户
	 * 
	 * @param parameter
	 * @return
	 */
	public SystemUser getUserById(Long id);

	/**
	 * 判断是否存在用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean isExistUser(String userName);

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public SystemUser loadUserByUsername(String userName);

	/**
	 * 获取全部授权资源
	 * 
	 * @return
	 */
	public List<Resource> getResources();

	public boolean resetPassword(SystemUserParameter parameter);

	public boolean changePassword(SystemUserParameter parameter);

	public boolean isUserPassword(SystemUserParameter parameter);

	public boolean deleteUser(SystemUserParameter parameter);

}