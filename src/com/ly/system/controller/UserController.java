package com.ly.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.system.entity.Role;
import com.ly.system.entity.User;
import com.ly.system.service.LogService;
import com.ly.system.service.RoleService;
import com.ly.system.service.UserService;
import com.ly.util.LogTypeEnum;
import com.ly.util.Page;
import com.ly.util.PageData;
import com.ly.util.Pagination;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

	private Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;
	@Resource
	private LogService logService;
	@Resource
	private RoleService roleService;
	
	@RequestMapping("/userList")
	public ModelAndView userList(Page page){
		ModelAndView mv = new ModelAndView("/user/userList");
		PageData pd = this.getPageData();
		String account = pd.getString("account");
		String name = pd.getString("name");
		if (account != null && !account.isEmpty()) {
			pd.put("account", account);
		}
		if (name != null && !name.isEmpty()) {
			pd.put("name", name);
		}
		page.setPd(pd);
		Pagination<User> pagination = userService.queryList(page);
		mv.addObject("userList", pagination.getItems());
//		mv.addObject("roleList", roleList);
		mv.addObject("pd", pd);
		return mv;
	}
	
	@RequestMapping("addUser")
	public String addUser(Model model){
		return "/user/addUser";
	}
	
	@RequestMapping("/saveUser")
	@ResponseBody
	public JSONObject saveUser(User user, HttpServletRequest request){
		JSONObject result = new JSONObject();
		String roleId = request.getParameter("roleId");
		if(roleId != null && !roleId.isEmpty()){
			Role role = roleService.getById(roleId);
			user.setRole(role);
		}
		Subject subject = SecurityUtils.getSubject();//获取当前用户
		User currentUser = (User) subject.getSession().getAttribute("currentUser");
//		user.setCreateDate(new Date());
//		user.setCreateUser(currentUser.getUSERNAME());
		result.put("success", "true");
		try {
			userService.saveUser(user);
			logService.saveLog("新建用户[" + user.getUSERNAME() + "]",LogTypeEnum.user.getType());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "新建用户失败：" + e.getMessage());
			logService.saveLog("新建用户[" + user.getUSERNAME() + "]失败:" + e.getMessage(),LogTypeEnum.user.getType());
			logger.error("创建用户失败",e);
		}
		
		return result;
	}
	
	@RequestMapping("editUser")
	public String editUser(HttpServletRequest request,Model model){
		String account = request.getParameter("account");
		if (account != null && !account.isEmpty()) {
			User user = userService.getByAccount(account);
			model.addAttribute("user",user);
		}
		return "/user/editUser";
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public JSONObject updateUser(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String roleId = request.getParameter("roleId");
		if (account == null || account.isEmpty()) {
			result.put("success", "false");
			result.put("errorMsg", "账号为空，编辑用户失败！");
		}
		User user = userService.getByAccount(account);
		if (user == null) {
			result.put("success", "false");
			result.put("errorMsg", "用户为空，编辑用户失败！");
		}
//		user.setUpdateDate(new Date());
		user.setNAME(name);
		if(roleId != null && !roleId.isEmpty() && !roleId.equals(user.getRole().getROLE_ID())){
			Role role = roleService.getById(roleId);
			user.setRole(role);
		}
		try {
			userService.updateUser(user);
			logService.saveLog("编辑用户[" + user.getUSERNAME() + "]",LogTypeEnum.user.getType());
		} catch (Exception e) {
			result.put("success", "false");
			result.put("errorMsg", "编辑用户失败：" + e.getMessage());
			logService.saveLog("编辑用户[" + user.getUSERNAME() + "]失败:" + e.getMessage(),LogTypeEnum.user.getType());
			logger.error("更新用户失败",e);
		}
		return result;
	}
	
	@RequestMapping("/checkRepeat")
	@ResponseBody
	public JSONObject checkRepeat(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("repeat", "false");
		String account = request.getParameter("account");
		if (account == null || account.isEmpty()) {
			return result;
		}
		User user = userService.getByAccount(account);
		if (user != null) {
			result.put("repeat", "true");
		}
		return result;
	}
	
	@RequestMapping("/getUserCombo")
	@ResponseBody
	public JSONArray getUserCombo(){
		JSONArray users = new JSONArray();
		List<User> list = userService.getAllUsers();
		if (list != null) {
			for(User user:list){
				JSONObject obj = new JSONObject();
				obj.put("id", user.getUSER_ID());
				obj.put("name",user.getUSERNAME());
				users.add(obj);
			}
		}
		return users;
	}
	
	@RequestMapping("/resetPass")
	public String resetPass(HttpServletRequest request,Model model){
		String account = request.getParameter("account");
		model.addAttribute("account", account);
		return "/user/resetPass";
	}
	
	@RequestMapping("/resetPassword")
	@ResponseBody
	public JSONObject resetPassword(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		
		String account = request.getParameter("account");
		String newPassword = request.getParameter("password");
		try {
			userService.resetPassword(account, newPassword);
			logService.saveLog("重置用户[" + account + "]的密码",LogTypeEnum.user.getType());
		} catch (Exception e) {
			logService.saveLog("重置用户[" + account + "]密码失败:" + e.getMessage(),LogTypeEnum.user.getType());
			logger.error("更新用户失败",e);
		}
		return result;
	}
}
