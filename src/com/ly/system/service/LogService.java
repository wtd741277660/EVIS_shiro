package com.ly.system.service;

import com.ly.system.entity.Log;
import com.ly.util.Pagination;

import java.util.Map;

public interface LogService {

	/**
	 * 记录日志信息
	 * @param content 日志信息
	 * @param logLeval 日志类型。 1:车辆信息，2：用户信息，3：系统级别
	 */
	public void saveLog(String content, String logLeval);

	/**
	 * 根据查询条件对日志信息进行分页显示
	 * @param params 查询参数
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return
	 */
	public Pagination<Log> queryList(Map<String, Object> params, int pageIndex, int pageSize);
}
