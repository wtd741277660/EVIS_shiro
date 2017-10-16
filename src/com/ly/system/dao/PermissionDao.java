package com.ly.system.dao;

import com.ly.business.entity.Permission;

import java.util.List;

public interface PermissionDao extends BaseDao<Permission> {

	/**
	 * 通过角色id获取对应的
	 * @param roleId
	 * @return
	 */
	public List<Permission> getByRoleId(String roleId);
	
	/**
	 * 获取所有的权限数据
	 * @return
	 */
	public List<Permission> getAll();
}
