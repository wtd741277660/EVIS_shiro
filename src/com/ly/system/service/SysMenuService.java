package com.ly.system.service;

import com.ly.system.entity.Menu;

import java.util.List;

public interface SysMenuService {

	/**
	 * 根据菜单等级获取对应的菜单集合
	 * @param menuLevel 菜单等级 1：一级菜单，2：二级菜单
	 * @return
	 */
	public List<Menu> getMenusByLevel(int menuLevel);
	
	
	/**
	 * 获取当前登录用户对应的角色的菜单
	 * @return
	 */
	public List<Menu> getMenusByUser();

	public List<Menu> getByParent(String parentId);
}
