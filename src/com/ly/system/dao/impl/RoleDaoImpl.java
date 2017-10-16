package com.ly.system.dao.impl;

import com.ly.system.dao.RoleDao;
import com.ly.system.entity.Role;
import com.ly.util.Pagination;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public Role getByUserId(String userId) {
		String hql = "select role from Role role,User user " +
				" where user.role.id = role.id and user.id=:id";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", userId);
		Role role = super.get(hql, map);
		return role;
	}

	@Override
	public List<Role> all() {
		String hql = "from Role ";
		Query query = getSession().createQuery(hql);
		List<Role> list = query.list();
		return list;
	}

	@Override
	public Role getById(String roleId) {
		return super.getById(roleId);
	}

	@Override
	public Pagination<Role> getByParams(Map<String, Object> params,
                                        int pageIndex, int pageSize) {
		String hql = "from Role where 1=1 ";
		for(String key:params.keySet()){
			if(key.equals("roleName")){
				hql += " and roleName like:roleName ";
				params.put("roleName", "%" + params.get("roleName") + "%");
			}else{
				hql += " and " + key +"=:" + key;
			}
		}
		Pagination<Role> pagination = super.findPagination(hql,params,pageIndex, pageSize);
		return pagination;
	}

}
