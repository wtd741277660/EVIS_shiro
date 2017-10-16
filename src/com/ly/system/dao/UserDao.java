package com.ly.system.dao;

import com.ly.system.entity.User;
import com.ly.util.Pagination;

import java.util.List;
import java.util.Map;

public interface UserDao extends BaseDao<User> {

	/**
	 * 根据用户名获取User对象
	 * @param account 用户名
	 * @return
	 */
	public User getByAccount(String account);

	/**
	 * 根据查询参数获取分页后的用户列表
	 * @param params 查询参数
	 * @param pageIndex 页数
	 * @param pageSize 每页大小
	 * @return
	 */
	public Pagination<User> queryList(Map<String, Object> params, int pageIndex, int pageSize);

	/**
	 * 获取所有用户集合
	 * @return
	 */
	public List<User> getAllUsers();

	/**
	 * 重置密码
	 * @param account 账号
	 * @param password 新密码
	 */
	public void resetPassword(String account, String password);
}
