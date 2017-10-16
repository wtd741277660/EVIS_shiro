package com.ly.util;


public enum LogTypeEnum {

	login("1","登陆"),
	logout("2","退出"),
	vehicle("3","车辆信息日志"),
	user("4","用户信息日志"),
	system("5","系统信息日志"),
	others("6","其他");
	
	
	private String type;
	private String typeName;

	private LogTypeEnum(String type, String typeName) {
		this.type = type;
		this.typeName = typeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
