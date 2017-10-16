package com.ly.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 参数实体类模板
 * @author Administrator
 *
 */
public class Params implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4663449972303359583L;

	private String id;
	
	private String name;
	
	private Date createTime;
	
	private Date updateTime;
	
	private String paramType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	
}
