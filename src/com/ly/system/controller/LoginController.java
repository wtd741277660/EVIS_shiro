package com.ly.system.controller;

import com.ly.system.entity.Menu;
import com.ly.system.entity.User;
import com.ly.system.service.LogService;
import com.ly.system.service.SysMenuService;
import com.ly.system.service.UserService;
import com.ly.util.AppTools;
import com.ly.util.LogTypeEnum;
import com.ly.util.MD5;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class LoginController {
	
	private Logger logger = Logger.getLogger(LoginController.class);
	@Resource
	private SysMenuService sysMenuService;
	@Resource
	private LogService logService;
	@Resource
	private UserService userService;

	@RequestMapping("/login")
	public String login(Model model){
		return "login";
	}
	
	@RequestMapping("/doLogin")
	public String doLogin(Model model,HttpServletRequest request){
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = MD5.getMD5(password.getBytes());
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		Subject currentUser = SecurityUtils.getSubject();
		
//		model.addAttribute("userName",username);
//		model.addAttribute("password",password);
		if(username == null || username.isEmpty()){
			model.addAttribute("errorMsg", "账号不能为空！");
			return "login";
		}
		if(password == null || password.isEmpty()){
			model.addAttribute("errorMsg", "密码不能为空！");
			return "login";
		}
		try {
			currentUser.login(token);
		} catch (UnknownAccountException ue) {
			ue.printStackTrace();
			model.addAttribute("errorMsg", "账号不存在！");
			logger.error("登陆失败",ue);
			return "login";
		} catch (AuthenticationException ae) {
			ae.printStackTrace();
			model.addAttribute("errorMsg", "账号或密码不正确！");
			logger.error("登陆失败",ae);
			return "login";
		}
		logService.saveLog("[" + username + "]登陆系统成功", LogTypeEnum.login.getType());
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		//保存登陆人员信息
		User currentUser = AppTools.getCurrentUser();
		model.addAttribute("user",currentUser);
		if (currentUser == null) {
			model.addAttribute("errorMsg", "当前登录人员为空！");
		}
		//加载菜单信息
		List<Menu> menus = sysMenuService.getByParent("0");
		model.addAttribute("menuList",menus);
		return "/main/index";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute(session.getId());
//		ClientManager.getInstance().removeClinet(session.getId());
		User user = AppTools.getCurrentUser();
		logService.saveLog("[" + user.getUSERNAME() + "]退出系统成功", LogTypeEnum.login.getType());
		session.invalidate();
		return "redirect:login";
	}

	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value="/tab")
	public String tab(){
		return "main/tab";
	}
}
