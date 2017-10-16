package com.ly.system.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 
* 类名称：角色
* 类描述： 
* @author FH QQ 313596790[青苔]
* 作者单位： 
* 联系方式：
* 创建时间：2014年3月10日
* @version 1.0
 */
@Entity
@Table(name="sys_role")
public class Role {
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ROLE_ID",nullable=false,length=32)
	private String ROLE_ID;
	@Column
	private String ROLE_NAME;
	@Column
	private String RIGHTS;
	@Column
	private String PARENT_ID;
	@Column
	private String ADD_QX;
	@Column
	private String DEL_QX;
	@Column
	private String EDIT_QX;
	@Column
	private String CHA_QX;
	
	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getROLE_NAME() {
		return ROLE_NAME;
	}
	public void setROLE_NAME(String rOLE_NAME) {
		ROLE_NAME = rOLE_NAME;
	}
	public String getRIGHTS() {
		return RIGHTS;
	}
	public void setRIGHTS(String rIGHTS) {
		RIGHTS = rIGHTS;
	}
	public String getPARENT_ID() {
		return PARENT_ID;
	}
	public void setPARENT_ID(String pARENT_ID) {
		PARENT_ID = pARENT_ID;
	}
	public String getADD_QX() {
		return ADD_QX;
	}
	public void setADD_QX(String aDD_QX) {
		ADD_QX = aDD_QX;
	}
	public String getDEL_QX() {
		return DEL_QX;
	}
	public void setDEL_QX(String dEL_QX) {
		DEL_QX = dEL_QX;
	}
	public String getEDIT_QX() {
		return EDIT_QX;
	}
	public void setEDIT_QX(String eDIT_QX) {
		EDIT_QX = eDIT_QX;
	}
	public String getCHA_QX() {
		return CHA_QX;
	}
	public void setCHA_QX(String cHA_QX) {
		CHA_QX = cHA_QX;
	}
	
	
}
