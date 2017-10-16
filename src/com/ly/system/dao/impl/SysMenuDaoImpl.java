package com.ly.system.dao.impl;

import com.ly.system.dao.SysMenuDao;
import com.ly.system.entity.Menu;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SysMenuDaoImpl extends BaseDaoImpl<Menu> implements SysMenuDao {

	@Override
	public List<Menu> getMenusByLevel(int menuLevel) {
		String hql = "from SysMenus where menuLevel =:menuLevel";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menuLevel", menuLevel);
		List<Menu> list = super.findList(hql, map);
		return list;
	}

	@Override
	public List<Menu> getMenusByUser() {
		String sql = "select * from sys_menu ";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Menu.class);
		List<Menu> list = query.list();
		return list;
	}

	@Override
	public List<Menu> getByParentId(String parentId) {
		String sql = "select * from sys_menu where parent_id ='" + parentId + "' order by MENU_ORDER asc";
		SQLQuery query = getSession().createSQLQuery(sql).addEntity(Menu.class);
		List<Menu> menus = query.list();
		for(Menu menu : menus){
			menu.setSubMenu(this.getByParentId(menu.getMENU_ID()));
			menu.setTarget("treeFrame");
		}
		return menus;
	}

}
