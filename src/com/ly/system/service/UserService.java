package com.ly.system.service;

import com.ly.system.entity.User;
import com.ly.util.Page;
import com.ly.util.Pagination;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	/**
	 * 根据用户名获取User对象
	 * @param account 用户名
	 * @return
	 */
	public User getByAccount(String account);
	
	/**
	 * 根据查询参数获取分页后的用户列表
	 * @param page 分页参数和查询参数
	 * @return
	 */
	public Pagination<User> queryList(Page page);

	/**
	 * 保存用户信息
	 * @param user
	 */
	public void saveUser(User user);

	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);

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
