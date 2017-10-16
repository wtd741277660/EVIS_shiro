package com.ly.system.controller;

import com.ly.business.entity.Permission;
import com.ly.system.entity.Role;
import com.ly.system.entity.User;
import com.ly.system.service.PermissionService;
import com.ly.system.service.RoleService;
import com.ly.system.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ResourceBundle;

public class ShiroRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	private static long sessionTimeOut = 1800000;//设置回话超时时间
	
	static {
		ResourceBundle resource = ResourceBundle.getBundle("configSystem");
		String timeStr = resource.getString("sessionTimeOut");
		try {
			sessionTimeOut = Long.valueOf(timeStr);
		} catch (Exception e) {
			System.out.println("获取回话超时时间失败！");
		}
		System.out.println("会话超时时间是：" + sessionTimeOut);
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		System.out.println("111");
		//获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String)super.getAvailablePrincipal(principals);
		
		User user = userService.getByAccount(currentUsername);
		if(user == null){
			throw new AuthenticationException("msg:用户不存在。");
		}
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		
		Role role = roleService.getByUserId(user.getUSER_ID());
		
		List<Permission> permList = null;
		
		if (role != null) {
			simpleAuthorInfo.addRole(role.getROLE_ID());
			//permList = permissionService.getByRoleId(role.getROLE_ID());
		}
		if(permList != null && permList.size() > 0){
			for(Permission perm : permList){
				if(perm.getPermissionCode() != null){
					simpleAuthorInfo.addStringPermission(perm.getPermissionCode());
				}
			}
		}
		return simpleAuthorInfo;
	}

	
	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		System.out.println("123");
		//获取基于用户名和密码的令牌
		//实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		
		Session session = getSession();
		//判断验证码
//		String code = (String)session.getAttribute(Constants.VALIDATE_CODE);
//		if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
//			throw new AuthenticationException("msg:验证码错误, 请重试.");
//		}
		User user = userService.getByAccount(token.getUsername());
		System.out.println("用户：" + user.getUSERNAME());
		if(user != null){
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getUSERNAME(), user.getPASSWORD(), this.getName());
			this.setSession("currentUser", user);
			return authcInfo;
		}
		return null;
	}
	
	/**
	 * 保存登录名
	 */
	private void setSession(Object key, Object value){
		Session session = getSession();
		if(null != session){
			session.setAttribute(key, value);
		}
	}
	
	private Session getSession(){
		try{
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null){
				session = subject.getSession();
			}
			if (session != null){
				session.setTimeout(sessionTimeOut);
				return session;
			}
		}catch (InvalidSessionException e){
			
		}
		return null;
	}


}