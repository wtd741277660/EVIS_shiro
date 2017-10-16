package com.ly.business.service;

import javax.servlet.http.HttpServletRequest;

import com.ly.business.entity.VehicleInfo;
import com.ly.util.Pagination;

public interface VehicleService {

	/**
	 * 根据查询条件分页查询车辆信息
	 * @param params 查询参数
	 * @param pageIndex 第几页
	 * @param pageSize 每页大小
	 * @return 分页后的数据集合
	 */
	public Pagination<VehicleInfo> queryList(HttpServletRequest request,int pageIndex,int pageSize);
	
	/**
	 * 根据车辆编号获取对象
	 * @param vehicleNum
	 * @return
	 */
	public VehicleInfo getByNum(String vehicleNum);
	
	/**
	 * 保存车辆信息
	 * @param vehicleInfo
	 */
	public VehicleInfo saveInfo(VehicleInfo vehicleInfo,HttpServletRequest request);
	
	/**
	 * 更新车辆信息
	 * @param vehicleInfo
	 */
	public VehicleInfo updateInfo(HttpServletRequest request);
	
}
