package com.ly.util;

import java.util.ArrayList;
import java.util.List;

public enum ParamTypeEnum {

//	FrontSuspension("01","FRONT_SUSPENSION_SYSTEM","前悬系统"),
//	RearSuspension("02","REAR_SUSPENSION_SYSTEM","后悬系统"),
//	ElecControl("03","ELECTRONIC_CONTROL_SYSTEM","电控系统"),
//	Brake("04","BRAKE_SYSTEM","刹车系统"),
//	Windshield("05","WINDSHIELD_SYSTEM","风挡系统"),
//	Ceiling("06","CEILING_SYSTEM","顶棚系统");
	
	FrontSuspension("01","front_suspension_system","前悬系统"),
	RearSuspension("02","rear_suspension_system","后悬系统"),
	ElecControl("03","electronic_control_system","电控系统"),
	Brake("04","brake_system","刹车系统"),
	Windshield("05","windshield_system","风挡系统"),
	Ceiling("06","ceiling_system","顶棚系统");
	
	private String code;
	
	private String tableName;
	
	private String name;

	private ParamTypeEnum(String code, String tableName,String name) {
		this.code = code;
		this.tableName = tableName;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getTableNameByCode(String code){
		if (code == null || code.isEmpty()) {
			return null;
		}
		for(ParamTypeEnum type:ParamTypeEnum.values()){
			if (type.getCode().equals(code)) {
				return type.getTableName();
			}
		}
		return null;
	}
	
	public static List<String> getAllCodes(){
		List<String> codes = new ArrayList<String>();
		for(ParamTypeEnum type:ParamTypeEnum.values()){
			codes.add(type.getCode());
		}
		return codes;
	}
	
	public static String getNameByCode(String code){
		if (code == null || code.isEmpty()) {
			return null;
		}
		for(ParamTypeEnum type:ParamTypeEnum.values()){
			if (type.getCode().equals(code)) {
				return type.getName();
			}
		}
		return null;
	}
}
