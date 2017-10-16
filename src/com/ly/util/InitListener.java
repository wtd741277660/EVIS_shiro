package com.ly.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ly.system.service.ParamService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ParamService paramService = (ParamService) webApplicationContext.getBean("paramService");
		
		//初始化参数，放到缓存中
		paramService.initParams(null);
	}

}
