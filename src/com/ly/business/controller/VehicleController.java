package com.ly.business.controller;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.system.entity.User;
import com.ly.system.service.LogService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ly.business.entity.VehicleInfo;
import com.ly.business.service.VehicleService;
import com.ly.util.AppTools;
import com.ly.util.LogTypeEnum;
import com.ly.util.OperateFiles;
import com.ly.util.Pagination;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	private Logger logger = Logger.getLogger(VehicleController.class);
	@Resource
	private VehicleService vehicleService;
	@Resource
	private LogService logService;
	
	@RequestMapping("/vehicleList")
	public String vehicleList(Model model){
		User currentUser = AppTools.getCurrentUser();
		String role = currentUser.getRole().getROLE_NAME();
		model.addAttribute("role",role);
		return "/vehicle/vehicleList";
	}
	
	@RequestMapping("/queryList")
	@ResponseBody
	public JSONObject queryList(HttpServletRequest request){
		JSONObject result = new JSONObject();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String pageIndexStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("rows");
		int pageIndex = 1;
		int pageSize = 20;
		if (pageIndexStr != null && !pageIndexStr.isEmpty()) {
			pageIndex = Integer.parseInt(pageIndexStr);
		}
		if (pageSizeStr != null && !pageSizeStr.isEmpty()) {
			pageSize = Integer.parseInt(pageSizeStr);;
		}
		
		Pagination<VehicleInfo> pagination = vehicleService.queryList(request, pageIndex, pageSize);
		JSONArray rows = new JSONArray();
		if (pagination.getItems() != null) {
			for(VehicleInfo vehicle:pagination.getItems()){
				JSONObject json = new JSONObject();
				json.put("vehicleNum", vehicle.getVehicleNum());
				json.put("vehicleType", vehicle.getVehicleType());
				json.put("customerName", vehicle.getCustomerName());
				if (vehicle.getProduceDate() != null) {
					json.put("produceDate", sdf.format(vehicle.getProduceDate()));
				}else{
					json.put("produceDate", "");
				}
				if (vehicle.getLeaveDate() != null) {
					json.put("leaveData", sdf.format(vehicle.getLeaveDate()));
				}else{
					json.put("leaveData", "");
				}
//				json.put("carFrameSystem", vehicle.getCarFrameSystem());
//				json.put("brakeSystem", vehicle.getBrakeSystem());
//				json.put("frontSuspensionSystem", vehicle.getFrontSuspensionSystem());
//				json.put("rearSuspensionSystem", vehicle.getRearSuspensionSystem());
//				json.put("windshieldSystem", vehicle.getWindshieldSystem());
//				json.put("electronicControlSystem", vehicle.getElectronicControlSystem());
//				json.put("ceilingSystem", vehicle.getCeilingSystem());
				json.put("remarks", vehicle.getRemark());
				json.put("recordUser", vehicle.getRecordUser() == null?"":vehicle.getRecordUser().getUSERNAME());
				json.put("examineUser", vehicle.getExamineUser());
				rows.add(json);
			}
		}
		result.put("rows", rows);
		result.put("total", pagination.getRowsCount());
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "/vehicle/addVehicleInfo";
		
	}
	
	@RequestMapping("/saveVehicle")
	@ResponseBody
	public JSONObject saveVehicle(@ModelAttribute VehicleInfo vehicleInfo,HttpServletRequest request){
		JSONObject result = new JSONObject();
		result.put("success", "true");
		try {
			vehicleInfo.setCreateDate(new Date());
			vehicleInfo = vehicleService.saveInfo(vehicleInfo,request);
			logService.saveLog("新建车辆信息[" + vehicleInfo.getVehicleNum() + "]",LogTypeEnum.vehicle.getType());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "false");
			result.put("errorMsg", "保存车辆信息失败：" + e.getMessage());
			logService.saveLog("新建车辆信息[" + vehicleInfo.getVehicleNum() + "]失败:" + e.getMessage(),LogTypeEnum.vehicle.getType());
			logger.error("创建车辆信息失败",e);
		}
		return result;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(HttpServletRequest request,Model model){
		String vehicleNum = request.getParameter("vehicleNum");
		if (vehicleNum == null || vehicleNum.isEmpty()) {
			model.addAttribute("errorMsg", "车辆编号为空！");
		}
		VehicleInfo vehicleInfo = vehicleService.getByNum(vehicleNum);
		if (vehicleInfo == null) {
			model.addAttribute("errorMsg", "车辆信息不存在！");
		}else{
			model.addAttribute("vehicle", vehicleInfo);
		}
		return "/vehicle/editVehicleInfo";
	}
	
	@RequestMapping("/updateVehicle")
	@ResponseBody
	public JSONObject updateVehicle(VehicleInfo vehicleInfo,HttpServletRequest request){
		JSONObject result = new JSONObject();
		vehicleInfo.setUpdateDate(new Date());
		result.put("success", "true");
		try {
			vehicleInfo = vehicleService.updateInfo(request);
			logService.saveLog("编辑车辆信息[" + vehicleInfo.getVehicleNum() + "]",LogTypeEnum.vehicle.getType());
		} catch (Exception e) {
			result.put("success", "false");
			result.put("errorMsg", "编辑车辆信息失败：" + e.getMessage());
			logService.saveLog("编辑车辆信息[" + vehicleInfo.getVehicleNum() + "]失败:" + e.getMessage(),LogTypeEnum.vehicle.getType());
			logger.error("更新车辆信息失败",e);
		}
		return result;
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(HttpServletRequest request,Model model){
		String vehicleNum = request.getParameter("vehicleNum");
		if (vehicleNum == null || vehicleNum.isEmpty()) {
			model.addAttribute("errorMsg", "车辆编号为空！");
		}
		VehicleInfo vehicleInfo = vehicleService.getByNum(vehicleNum);
		if (vehicleInfo == null) {
			model.addAttribute("errorMsg", "车辆信息不存在！");
		}else{
			model.addAttribute("vehicle", vehicleInfo);
		}
		return "/vehicle/viewVehicleInfo";
	}
	
	@RequestMapping(value="/saveFiles",method=RequestMethod.POST)
	@ResponseBody
	public JSONObject saveFiles(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String vehicleNum = request.getParameter("vehicleNum");
		String type = request.getParameter("type");
		List<String> successFiels = new ArrayList<String>();
		List<String> failedFiles = new ArrayList<String>();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (!item.isFormField()) {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						// item.getName()返回上传文件在客户端的完整路径名称
						System.out.println("上传文件的名称:" + item.getName());
						
						//保存文件
						String saveResult = OperateFiles.saveFiles(item,vehicleNum,type);
						if (saveResult.equals("FALSE")) {
							failedFiles.add(item.getName());
						}else{
							successFiels.add(saveResult);
						}
					}
				}
			}
		}catch(FileUploadException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果全部都上传错误，就标记失败
		//如果部分成功，就保存失败的文件名，用来前台显示，成功的文件名将存到对应的input中用来入库
		String failedNames = null;
		String successNames = null;
		if (failedFiles != null && failedFiles.size() > 0) {
			failedNames = StringUtils.join(failedFiles,",");
			json.put("failedNames", failedNames);
		}
		if (successFiels != null && successFiels.size() > 0) {
			successNames = StringUtils.join(successFiels,",");
			json.put("success", "true");
			json.put("successNames", successNames);
		}else{
			json.put("success", "false");
		}
		return json;
	}
	
	@RequestMapping("/deleteFiles")
	@ResponseBody
	public JSONObject deleteFiles(HttpServletRequest request){
		JSONObject result = new JSONObject();
		String vehicleNum = request.getParameter("vehicleNum");
		String type = request.getParameter("type");
		if (vehicleNum == null || vehicleNum.isEmpty()) {
			result.put("success", "false");
			result.put("message", "删除失败！");
		}else{
			result = OperateFiles.deleteFile(vehicleNum,type);
		}
		return result;
	}
	
	@RequestMapping("/checkRepeat")
	@ResponseBody
	public JSONObject checkRepeat(HttpServletRequest request){
		JSONObject json = new JSONObject();
		String vehicleNum = request.getParameter("vehicleNum");
		VehicleInfo vehicleInfo = vehicleService.getByNum(vehicleNum);
		if (vehicleInfo == null) {
			json.put("success", "false");
		}else{
			json.put("success", "true");
		}
		return json;
	}
	
//	@RequestMapping("/cancleSave")
//	@ResponseBody
//	public JSONObject cancleSave(HttpServletRequest request){
//		JSONObject result = new JSONObject();
//		result.put("success", "true");
//		String vehicleNum = request.getParameter("vehicleNum");
//		String type = request.getParameter("type");
//		if (vehicleNum == null || vehicleNum.isEmpty()) {
////			result.put("success", "false");
////			result.put("errorMsg", "车辆编号为空，删除已上传文件失败！");
//			return result;
//		}
//		boolean delete = OperateFiles.deleteFileByVehicleNum(vehicleNum,type);
//		if (!delete) {
//			result.put("success", "false");
//			result.put("errorMsg", "已上传照片删除失败！");
//			return result;
//		}
//		return result;
//	}
	
	@RequestMapping("/getVehicleSystem")
	public void getVehicleSystem(HttpServletRequest request,HttpServletResponse response){
		String vehicleNum = request.getParameter("vehicleNum");
		String photoUrl = request.getParameter("photoUrl");
		 try {
			//读取本地图片输入流
			FileInputStream inputStream = new FileInputStream(photoUrl);
			int i = inputStream.available();
			//byte数组用于存放图片字节数据
			byte[] buff = new byte[i];
			inputStream.read(buff);
			//记得关闭输入流
			inputStream.close();
			//设置发送到客户端的响应内容类型
			response.setContentType("image/*");
			OutputStream out = response.getOutputStream();
			out.write(buff);
			//关闭响应输出流
			out.close();
		} catch (Exception e) {
//			e.printStackTrace();
		    System.out.println(photoUrl + " 不存在");
		}
	}
	
}
