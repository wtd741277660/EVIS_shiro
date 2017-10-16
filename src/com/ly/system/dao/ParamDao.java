package com.ly.system.dao;

import com.ly.system.entity.Params;
import com.ly.util.Pagination;

import java.util.List;
import java.util.Map;

public interface ParamDao extends BaseDao<Params> {


	/**
	 * 根据查询条件对参数信息进行分页显示
	 * @param params 查询参数
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @param paramType 参数类型
	 * @return
	 */
	public Pagination<Map<String, Object>> queryList(Map<String, Object> params, int pageIndex, int pageSize, String paramType);

	/**
	 * 记录参数信息
	 * @param param 参数信息
	 */
	public void saveParam(Params param);

	/**
	 * 更新参数信息
	 * @param param 参数信息
	 */
	public void updateParam(Params param);

	/**
	 * 删除参数信息
	 * @param id 参数信息id
	 * @param paramType 参数类型
	 */
	public void deleteParam(String id, String paramType);

	/**
	 * 根据id获取相应的参数数据
	 * @param id 参数id
	 * @param paramType 参数类型
	 * @return
	 */
	public Params getByIdAndType(String id, String paramType);
	
	/**
	 * 根据参数类型获取参数集合
	 * @param paramType 参数类型
	 * @return 对应的参数集合
	 */
	public List<Params> getByType(String paramType);
	
}
