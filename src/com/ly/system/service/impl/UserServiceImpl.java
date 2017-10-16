package com.ly.system.service.impl;

import com.ly.system.dao.UserDao;
import com.ly.system.entity.User;
import com.ly.system.service.UserService;
import com.ly.util.Page;
import com.ly.util.PageData;
import com.ly.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User getByAccount(String account) {
		User user = userDao.getByAccount(account);
		return user;
	}

	@Override
	public Pagination<User> queryList(Page page) {
		String hql = "from User where 1=1 ";
		PageData pd = page.getPd();
		for(Object key:pd.keySet()){
			if(key.equals("account")){
				hql += " and account like:account ";
			}else if(key.equals("name")){
				hql += " and name like:name ";
			}else{
				hql += " and " + key +"=:" + key;
			}
		}
		Pagination<User> pagination = userDao.findPagination(hql,pd.getMap(),page.getCurrentPage(),page.getShowCount());
		return pagination;
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public void resetPassword(String account, String password) {
		userDao.resetPassword(account, password);
	}
	
}
