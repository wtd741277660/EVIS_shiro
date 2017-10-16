package com.ly.system.dao.impl;

import com.ly.business.entity.Permission;
import com.ly.system.dao.PermissionDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao {

	@Override
	public List<Permission> getByRoleId(String roleId) {
		String hql = "select perm from Permission perm, RolePermission rp,Role role" +
				" where rp.permissionId = perm.id and role.id = rp.roleId " +
				" and role.id=:roleId";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		List<Permission> permissions = super.findListByMap(hql, params);
		return permissions;
	}

	@Override
	public List<Permission> getAll() {
		String hql = "from Permission";
		List<Permission> permissions = super.findListByMap(hql, new HashMap<String, Object>());
		return permissions;
	}

}
