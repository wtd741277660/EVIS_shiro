package com.ly.system.service.impl;

import com.ly.business.entity.Permission;
import com.ly.system.dao.PermissionDao;
import com.ly.system.service.PermissionService;
import com.ly.util.TreeObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionDao permissionDao;

	@Override
	public List<Permission> getByRoleId(String roleId) {
		if (roleId == null || roleId.isEmpty()) {
			return null;
		}
		List<Permission> permissions = permissionDao.getByRoleId(roleId);
		return permissions;
	}

	@Override
	public List<TreeObj> getPermissionTree() {
		List<Permission> permissions = permissionDao.getAll();
		List<TreeObj> tree = new ArrayList<TreeObj>();
		for(Permission permi:permissions){
			TreeObj obj = new TreeObj();
			obj.setId(permi.getId());
			obj.setName(permi.getPermissionName());
			if (permi.getParentId() == null) {
				obj.setpId("0");
			}else{
				obj.setpId(permi.getParentId());
			}
//			List<TreeObj> children = new ArrayList<TreeObj>();
//			for(Permission p:permissions){
//				if (p.getParentId() != null && p.getParentId().equals(permi.getId())) {
//					TreeObj o = new TreeObj();
//					o.setId(p.getId());
//					o.setName(p.getPermissionName());
//					o.setpId(p.getParentId());
//					children.add(o);
//				}
//			}
//			obj.setChildren(children);
			tree.add(obj);
		}
//		TreeObj root = new TreeObj();
//		root.setId("0");
//		root.setName("权限列表");
//		root.setpId("-1");
//		tree.add(root);
		return tree;
	}

}
