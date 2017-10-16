package com.ly.system.service;

import com.ly.business.entity.Permission;
import com.ly.util.TreeObj;

import java.util.List;

public interface PermissionService {

	/**
	 * 通过角色id获取对应的
	 * @param roleId
	 * @return
	 */
	public List<Permission> getByRoleId(String roleId);
	
	/**
	 * 获取权限树的数据
	 * @return
	 */
	public List<TreeObj> getPermissionTree();
}
