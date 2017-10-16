package com.ly.system.entity;

import com.ly.business.entity.VehicleInfo;
import com.ly.util.Page;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 
* 类名称：用户
* 类描述： 
* @author FH QQ 313596790[青苔]
* 作者单位： 
* 联系方式：
* 创建时间：2014年6月28日
* @version 1.0
 */
@Entity
@Table(name="sys_user")
public class User {

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="USER_ID",nullable=false,length=32)
	private String USER_ID;		//用户id

	@Column
	private String USERNAME;	//用户名
	@Column
	private String PASSWORD; 	//密码
	@Column
	private String NAME;		//姓名
	@Column
	private String RIGHTS;		//权限
	@Column
	private String ROLE_ID;		//角色id
	@Column
	private String LAST_LOGIN;	//最后登录时间
	@Column
	private String IP;			//用户登录ip地址
	@Column
	private String STATUS;		//状态

	@ManyToOne(targetEntity = Role.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role role;			//角色对象

	@Transient
	private Page page;			//分页对象
	@Column
	private String SKIN;		//皮肤

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "recordUser",targetEntity = VehicleInfo.class)
	private List<VehicleInfo> vehicleInfos;
	
	public String getSKIN() {
		return SKIN;
	}
	public void setSKIN(String sKIN) {
		SKIN = sKIN;
	}
	
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getRIGHTS() {
		return RIGHTS;
	}
	public void setRIGHTS(String rIGHTS) {
		RIGHTS = rIGHTS;
	}
	public String getROLE_ID() {
		return ROLE_ID;
	}
	public void setROLE_ID(String rOLE_ID) {
		ROLE_ID = rOLE_ID;
	}
	public String getLAST_LOGIN() {
		return LAST_LOGIN;
	}
	public void setLAST_LOGIN(String lAST_LOGIN) {
		LAST_LOGIN = lAST_LOGIN;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}

	public List<VehicleInfo> getVehicleInfos() {
		return vehicleInfos;
	}

	public void setVehicleInfos(List<VehicleInfo> vehicleInfos) {
		this.vehicleInfos = vehicleInfos;
	}
}
