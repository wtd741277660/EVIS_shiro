package com.ly.system.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name="logs")
@Entity
public class Log implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810341254433173135L;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
	@Column(name="operate_user",length=40)
	private String operateUser;//操作人ID
	
	@Column(name="operate_date")
	private Date operateDate;//日志记录时间
	
	@Column(name="operate_ip")
	private String operateIP;//操作IP
	
	@Column(name="content",length=2000)
	private String content;//日志记录内容
	
	@Column(name="log_level",length=5)
	private String logLevel;//日志记录类型,1:车辆信息，2：用户信息，3：系统日志

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperateUser() {
		return operateUser;
	}

	public void setOperateUser(String operateUser) {
		this.operateUser = operateUser;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getOperateIP() {
		return operateIP;
	}

	public void setOperateIP(String operateIP) {
		this.operateIP = operateIP;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}
	
}
