package com.ly.system.service.impl;

import com.ly.system.dao.RoleDao;
import com.ly.system.entity.Role;
import com.ly.system.service.RoleService;
import com.ly.util.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role getByUserId(String userId){
		Role role = roleDao.getByUserId(userId);
		return role;
	}

	@Override
	public List<Role> all() {
		return roleDao.all();
	}

	@Override
	public Role getById(String roleId) {
		return roleDao.getById(roleId);
	}

	@Override
	public Pagination<Role> getByParams(HttpServletRequest request) {
		String pageIndexStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		int pageIndex = 1;
		int pageSize = 20;
		if (pageIndexStr != null && !pageIndexStr.isEmpty()) {
			pageIndex = Integer.parseInt(pageIndexStr);
		}
		if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
			pageSize = Integer.parseInt(pageSizeStr);;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String roleName = request.getParameter("roleName");
		if (roleName != null && !roleName.isEmpty()) {
			params.put("roleName", roleName);
		}
		return roleDao.getByParams(params, pageIndex, pageSize);
	}

	@Override
	public void savePermissions(String roleId, String permissionIds) {
		String[] ids = permissionIds.split(",");
		//保存新的权限之前需要删掉旧的权限
//		rolePermissionDao.deleteByRoleId(roleId);
//		for(int i = 0;i < ids.length;i++){
//			RolePermission rp = new RolePermission();
//			rp.setPermissionId(ids[i]);
//			rp.setRoleId(roleId);
//			rolePermissionDao.save(rp);
//		}
		
	}
}
