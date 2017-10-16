package com.ly.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.business.entity.*;
import com.ly.system.entity.*;
import com.ly.system.service.LogService;
import com.ly.system.service.ParamService;
import com.ly.util.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/params")
public class ParamsController {
	
	private Logger logger = Logger.getLogger(ParamsController.class);
	
	@Resource
	private ParamService paramService;
	@Resource
	private LogService logService;

	@RequestMapping("/paramList")
	public String paramList(HttpServletRequest request,Model model){
		String paramType = request.getParameter("paramType");
		model.addAttribute("paramType", paramType);
		return "sysconfig/paramList";
	}
	
	@RequestMapping("/queryParamList")
	@ResponseBody
	public JSONObject queryParamList(HttpServletRequest request){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject result = new JSONObject();
		String pageIndexStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		int pageIndex = 1;
		int pageSize = 20;
		if (pageIndexStr != null && !pageIndexStr.isEmpty()) {
			try {
				pageIndex = Integer.parseInt(pageIndexStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
			try {
				pageSize = Integer.parseInt(pageSizeStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Pagination<Map<String, Object>> pagination = paramService.queryList(request, pageIndex, pageSize);
		JSONArray array = new JSONArray();
		if (pagination.getItems() != null) {
			for(Map<String, Object> map:pagination.getItems()){
				JSONObject obj = new JSONObject();
				obj.put("id", map.get("ID"));
				obj.put("name", map.get("NAME"));
				if (map.get("CREATE_TIME") != null) {
					obj.put("createTime", sdf.format(map.get("CREATE_TIME")));
				}
				if (map.get("UPDATE_TIME") != null) {
					obj.put("updateTime", sdf.format(map.get("UPDATE_TIME")));
				}
				array.add(obj);
			}
		}
		result.put("rows", array);
		result.put("total", pagination.getRowsCount());
		return result;
	}
	
	@RequestMapping("/addParam")
	public ModelAndView add(HttpServletRequest request,Model model){
		String paramType = request.getParameter("paramType");
		model.addAttribute("paramType", paramType);
		Params param = new Params();
		model.addAttribute("param", param);
		return new ModelAndView("sysconfig/addParam");
	}
	
	@RequestMapping("/saveParam")
	@ResponseBody
	public JSONObject saveParam(@ModelAttribute("param") Params param, HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		User user = AppTools.getCurrentUser();
		try {
			paramService.saveParam(param);
			logService.saveLog(user.getUSERNAME() + "新建参数[" + ParamTypeEnum.getNameByCode(param.getParamType()) + "],名称为[" + param.getName() + "]成功",
					LogTypeEnum.system.getType());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "创建参数失败，原因时：" + e.getMessage());
			logService.saveLog(user.getUSERNAME() + "新建参数[" + ParamTypeEnum.getNameByCode(param.getParamType()) + "],名称为[" + param.getName() + "]失败，原因是：" + e.getMessage(),
					LogTypeEnum.system.getType());
			logger.error("创建参数" + param.getName() + "失败", e);
		}
		return result;
	}
	
	@RequestMapping("/editParam")
	public String edit(HttpServletRequest request,Model model){
		String paramType = request.getParameter("paramType");
		model.addAttribute("paramType", paramType);
		String id = request.getParameter("id");
		Params param = paramService.getByIdAndType(id, paramType);
		if (param != null) {
			model.addAttribute("name",param.getName());
			model.addAttribute("id", param.getId());
		}
		return "sysconfig/editParam";
	}
	
	@RequestMapping("/updateParam")
	@ResponseBody
	public JSONObject updateParam(Params param, HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		User user = AppTools.getCurrentUser();
		String oldName = request.getParameter("oldName");
		try {
			paramService.updateParam(param);
			logService.saveLog(user.getUSERNAME() + "更新参数[" + ParamTypeEnum.getNameByCode(param.getParamType()) + "],名称由[" + oldName + "]修改为[" + param.getName() + "]成功",
					LogTypeEnum.system.getType());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "更新参数失败，原因时：" + e.getMessage());
			logService.saveLog(user.getUSERNAME() + "更新参数[" + ParamTypeEnum.getNameByCode(param.getParamType()) + "],名称由[" + oldName + "]修改为[" + param.getName() + "]失败，原因是：" + e.getMessage(),
					LogTypeEnum.system.getType());
			logger.error("更新参数" + param.getName() + "失败", e);
		}
		return result;
	}
	
	@RequestMapping("/deleteParam")
	@ResponseBody
	public JSONObject deleteParam(HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		User user = AppTools.getCurrentUser();
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String paramType = request.getParameter("paramType");
		try {
			paramService.deleteParam(id, paramType);
			logService.saveLog(user.getUSERNAME() + "删除参数[" + ParamTypeEnum.getNameByCode(paramType) + "],名称为[" + name + "]成功",
					LogTypeEnum.system.getType());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "删除参数失败，原因时：" + e.getMessage());
			logService.saveLog(user.getUSERNAME() + "删除参数[" + ParamTypeEnum.getNameByCode(paramType) + "],名称为[" + name + "]失败，原因是：" + e.getMessage(),
					LogTypeEnum.system.getType());
			logger.error("创建参数" + name + "失败", e);
		}
		return result;
	}
	
	@RequestMapping("/getParamCombo")
	@ResponseBody
	public JSONArray getParamCombo(HttpServletRequest request){
		JSONArray data = new JSONArray();
		String paramType = request.getParameter("paramType");
		if (paramType == null || paramType.isEmpty()) {
			return data;
		}
		Map<String, Object> map = ResourceUtil.params.get(paramType);
		for(String key:map.keySet()){
			JSONObject obj = new JSONObject();
			obj.put("id", key);
			String name = "";
			if (paramType.equals("01")) {
				FrontSuspensionSystems object = (FrontSuspensionSystems) map.get(key);
				name = object.getName();
			}else if(paramType.equals("02")){
				RearSuspensionSystems object = (RearSuspensionSystems) map.get(key);
				name = object.getName();
			}else if(paramType.equals("03")){
				ElectronicControlSystem object = (ElectronicControlSystem) map.get(key);
				name = object.getName();
			}else if(paramType.equals("04")){
				BrakeSystem object = (BrakeSystem) map.get(key);
				name = object.getName();
			}else if(paramType.equals("05")){
				WindshieldSystem object = (WindshieldSystem) map.get(key);
				name = object.getName();
			}else if(paramType.equals("06")){
				CeilingSystem object = (CeilingSystem) map.get(key);
				name = object.getName();
			}
			obj.put("name",name);
			data.add(obj);
		}
		return data;
	}
}
