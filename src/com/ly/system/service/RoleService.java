package com.ly.system.service;

import com.ly.system.entity.Role;
import com.ly.util.Pagination;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleService {

	/**
	 * 获取用户对应的角色对象
	 * @param userId 用户id
	 * @return
	 */
	public Role getByUserId(String userId);
	
	/**
	 * 获取所有角色数据
	 * @return
	 */
	public List<Role> all();
	
	/**
	 * 根据id获取角色对象
	 * @param roleId
	 * @return
	 */
	public Role getById(String roleId);
	
	
	/**
	 * 根据查询条件获取角色信息
	 * @param reqeust
	 * @return
	 */
	public Pagination<Role> getByParams(HttpServletRequest reqeust);
	
	/**
	 * 保存角色对应的权限
	 * @param roleId
	 * @param permissionIds
	 */
	public void savePermissions(String roleId, String permissionIds);
}
