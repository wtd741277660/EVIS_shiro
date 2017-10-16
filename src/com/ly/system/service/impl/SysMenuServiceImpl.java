package com.ly.system.service.impl;

import com.ly.system.dao.SysMenuDao;
import com.ly.system.entity.Menu;
import com.ly.system.service.SysMenuService;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	
	@Autowired
	private SysMenuDao sysMenuDao;

	@Override
	public List<Menu> getMenusByLevel(int menuLevel) {
		return sysMenuDao.getMenusByLevel(menuLevel);
	}

	@Override
	public List<Menu> getMenusByUser() {
		return sysMenuDao.getMenusByUser();
	}

	@Override
	public List<Menu> getByParent(String parentId) {
		return sysMenuDao.getByParentId(parentId);
	}

}
