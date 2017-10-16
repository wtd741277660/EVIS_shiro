package com.ly.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.system.entity.Log;
import com.ly.system.service.LogService;
import com.ly.util.LogTypeEnum;
import com.ly.util.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/log")
public class LogController {

	@Resource
	private LogService logService;
	
	@RequestMapping("/list")
	public String list(){
		return "/sysconfig/logList";
	}
	
	@RequestMapping("/getList")
	@ResponseBody
	public JSONObject getList(HttpServletRequest request){
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
		Map<String, Object> params = new HashMap<String, Object>();
		String logLevel = request.getParameter("logLevel");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(logLevel != null && !logLevel.isEmpty()){
			params.put("logLevel", logLevel);
		}
		if(startTime != null && !startTime.isEmpty()){
			params.put("startTime", startTime);
		}
		if(endTime != null && !endTime.isEmpty()){
			params.put("endTime", endTime);
		}
		
		Pagination<Log> pagination = logService.queryList(params, pageIndex, pageSize);
		JSONArray array = new JSONArray();
		if (pagination.getItems() != null) {
			for(Log log:pagination.getItems()){
				JSONObject obj = new JSONObject();
				obj.put("operateUser", log.getOperateUser());
				if (log.getOperateDate() != null) {
					obj.put("operateDate", sdf.format(log.getOperateDate()));
				}else{
					obj.put("operateDate", "");
				}
				obj.put("operateIP", log.getOperateIP());
				obj.put("content", log.getContent());
				obj.put("logLevel", log.getLogLevel());
				array.add(obj);
			}
		}
		result.put("total", pagination.getRowsCount());
		result.put("rows", array);
		return result;
	}
	
	@RequestMapping("/getLogTypes")
	@ResponseBody
	public JSONArray getLogTypes(){
		JSONArray array = new JSONArray();
		LogTypeEnum[] types = LogTypeEnum.values();
		for(LogTypeEnum type:types){
			JSONObject obj = new JSONObject();
			obj.put("id", type.getType());
			obj.put("name", type.getTypeName());
			array.add(obj);
		}
		return array;
	}
}
