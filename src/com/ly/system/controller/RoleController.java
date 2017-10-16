package com.ly.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.business.entity.Permission;
import com.ly.system.entity.Role;
import com.ly.system.service.PermissionService;
import com.ly.system.service.RoleService;
import com.ly.util.Pagination;
import com.ly.util.TreeObj;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource
	private RoleService roleService;
	@Resource
	private PermissionService permissionService;
	
	@RequestMapping("/getRoles")
	@ResponseBody
	public JSONArray getRoleCombobox(){
		List<Role> roles = roleService.all();
		JSONArray array = new JSONArray();
		for(Role role:roles){
			JSONObject obj = new JSONObject();
			obj.put("id", role.getROLE_ID());
			obj.put("name", role.getROLE_NAME());
			array.add(obj);
		}
		return array;
	}
	
	@RequestMapping("/roleList")
	public String roleList(){
		return "user/roleList";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public JSONObject getList(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject data = new JSONObject();
		Pagination<Role> pagination = roleService.getByParams(request);
		List<Role> list = pagination.getItems();
		JSONArray array = new JSONArray();
		if (list != null && list.size() > 0) {
			for(Role role:list){
				JSONObject obj = new JSONObject();
				obj.put("roleName", role.getROLE_NAME());
				obj.put("roleId", role.getROLE_ID());
//				if (role.getCreateDate() != null) {
//					obj.put("createTime", sdf.format(role.getCreateDate()));
//				}
//				if (role.getUpdateDate() != null) {
//					obj.put("updateTime", sdf.format(role.getUpdateDate()));
//				}
				array.add(obj);
			}
		}
		data.put("rows", array);
		data.put("total", pagination.getRowsCount());
		return data;
	}
	
	@RequestMapping("/configPermi")
	public String configPermi(HttpServletRequest request,Model model){
		String roleId = request.getParameter("roleId");
		List<Permission> permissions = permissionService.getByRoleId(roleId);
		String permissionIds = "";
		for(int i = 0;i < permissions.size();i++){
			if (i < permissions.size() - 1) {
				permissionIds += permissions.get(i).getId() + ",";
			}else{
				permissionIds += permissions.get(i).getId();
			}
		}
		model.addAttribute("permissionIds", permissionIds);
		model.addAttribute("roleId", roleId);
		return "user/configPermissions";
	}
	
	@RequestMapping("/configPermissions")
	@ResponseBody
	public JSONObject configPermissions(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		String checked = request.getParameter("checked");
		String roleId = request.getParameter("roleId");
		if (checked == null || checked.isEmpty()) {
			result.put("success", "false");
			result.put("errorMsg", "权限选择为空！");
			return result;
		}
		if (roleId == null || roleId.isEmpty()) {
			result.put("success", "false");
			result.put("errorMsg", "角色为空！");
			return result;
		}
		try {
			roleService.savePermissions(roleId, checked);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "配置权限失败");
		}
		return result;
	}
	
	@RequestMapping("/getPermissionTree")
	@ResponseBody
	public List<TreeObj> getPermissionTree(){
		List<TreeObj> tree = permissionService.getPermissionTree();
		return tree;
	}
}
