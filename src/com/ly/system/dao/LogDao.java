package com.ly.system.dao;

import com.ly.system.entity.Log;
import com.ly.util.Pagination;

import java.util.Map;

public interface LogDao extends BaseDao<Log> {

	/**
	 * 记录日志信息
	 * @param log 日志信息
	 */
	public void saveLog(Log log);

	/**
	 * 根据查询条件对日志信息进行分页显示
	 * @param params 查询参数
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return
	 */
	public Pagination<Log> queryList(Map<String, Object> params, int pageIndex, int pageSize);
}
