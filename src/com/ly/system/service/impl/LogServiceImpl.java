package com.ly.system.service.impl;

import com.ly.system.dao.LogDao;
import com.ly.system.entity.Log;
import com.ly.system.entity.User;
import com.ly.system.service.LogService;
import com.ly.util.AppTools;
import com.ly.util.Pagination;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public void saveLog(String content,String logLevel) {
		Log log = new Log();
		Subject subject = SecurityUtils.getSubject();//获取当前用户
		User currentUser = (User) subject.getSession().getAttribute("currentUser");
		log.setContent(content);
		log.setLogLevel(logLevel);
		log.setOperateDate(new Date());
		String remoteIP = AppTools.getIp();
		if (remoteIP != null) {
			log.setOperateIP(remoteIP.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":remoteIP);
		}
		log.setOperateUser(currentUser.getUSERNAME());
		logDao.saveLog(log);
	}

	@Override
	public Pagination<Log> queryList(Map<String, Object> params, int pageIndex, int pageSize) {
		return logDao.queryList(params, pageIndex, pageSize);
	}
}
