package com.ly.system.service.impl;

import com.ly.business.entity.*;
import com.ly.system.dao.ParamDao;
import com.ly.system.entity.*;
import com.ly.system.service.ParamService;
import com.ly.util.Pagination;
import com.ly.util.ParamTypeEnum;
import com.ly.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("paramService")
public class ParamServiceImpl implements ParamService {
	
	@Autowired
	private ParamDao paramDao;

	@Override
	public Pagination<Map<String, Object>> queryList(HttpServletRequest request,int pageIndex, int pageSize) {
		String name = request.getParameter("name");
		String paramType = request.getParameter("paramType");
		Map<String, Object> params = new HashMap<String, Object>();
		if (name != null && !name.isEmpty()) {
			params.put("name", "%" + name + "%");
		}
		Pagination<Map<String, Object>> pagination = paramDao.queryList(params, pageIndex, pageSize, paramType);
		return pagination;
	}

	@Override
	public void saveParam(Params param) {
		paramDao.saveParam(param);
		initParams(param.getParamType());
	}

	@Override
	public void updateParam(Params param) {
		paramDao.updateParam(param);
		initParams(param.getParamType());
	}

	@Override
	public void deleteParam(String id, String paramType) {
		paramDao.deleteParam(id, paramType);
		initParams(paramType);
	}

	@Override
	public Params getByIdAndType(String id, String paramType) {
		return paramDao.getByIdAndType(id, paramType);
	}

	@Override
	public List<Params> getByType(String paramType) {
		return paramDao.getByType(paramType);
	}

	@Override
	public void initParams(String paramType) {
		List<String> types = new ArrayList<String>();
		//如果指定了参数类型，就刷新具体的参数类型，否则就刷新所有参数缓存
		if (paramType != null) {
			types.add(paramType);
		}else{
			types = ParamTypeEnum.getAllCodes();
		}
		for(String type:types){
			List<Params> list = getByType(type);
			Map<String, Object> map = new HashMap<String, Object>();
			for(Params param:list){
				if (type.equals("01")) {
					FrontSuspensionSystems object = new FrontSuspensionSystems();
					object.setCreateTime(param.getCreateTime());
					object.setId(param.getId());
					object.setName(param.getName());
					object.setUpdateTime(param.getUpdateTime());
					map.put(object.getId(), object);
				}else if(type.equals("02")){
					RearSuspensionSystems object = new RearSuspensionSystems();
					object.setCreateTime(param.getCreateTime());
					object.setId(param.getId());
					object.setName(param.getName());
					object.setUpdateTime(param.getUpdateTime());
					map.put(object.getId(), object);
				}else if(type.equals("03")){
					ElectronicControlSystem object = new ElectronicControlSystem();
					object.setCreateTime(param.getCreateTime());
					object.setId(param.getId());
					object.setName(param.getName());
					object.setUpdateTime(param.getUpdateTime());
					map.put(object.getId(), object);
				}else if(type.equals("04")){
					BrakeSystem object = new BrakeSystem();
					object.setCreateTime(param.getCreateTime());
					object.setId(param.getId());
					object.setName(param.getName());
					object.setUpdateTime(param.getUpdateTime());
					map.put(object.getId(), object);
				}else if(type.equals("05")){
					WindshieldSystem object = new WindshieldSystem();
					object.setCreateTime(param.getCreateTime());
					object.setId(param.getId());
					object.setName(param.getName());
					object.setUpdateTime(param.getUpdateTime());
					map.put(object.getId(), object);
				}else if(type.equals("06")){
					CeilingSystem object = new CeilingSystem();
					object.setCreateTime(param.getCreateTime());
					object.setId(param.getId());
					object.setName(param.getName());
					object.setUpdateTime(param.getUpdateTime());
					map.put(object.getId(), object);
				}
			}
			ResourceUtil.params.put(type, map);
		}
	}

}
