package com.ly.system.dao;

import com.ly.system.entity.Role;
import com.ly.util.Pagination;

import java.util.List;
import java.util.Map;

public interface RoleDao extends BaseDao<Role> {

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
	 * @param params 查询条件
	 * @param pageIndex 当前页数
	 * @param pageSize 每一页大小
	 * @return
	 */
	public Pagination<Role> getByParams(Map<String, Object> params, int pageIndex, int pageSize);
}
