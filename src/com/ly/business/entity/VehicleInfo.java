package com.ly.business.entity;

import com.ly.system.entity.User;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="vehicle_info")
public class VehicleInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2762694723332561823L;

	@Id
	@Column(name="vehicle_num",length=100,nullable=false)
	private String vehicleNum;//整机编号
	
	@Column(name="vehicle_type",length=255)
	private String vehicleType;//整机型号
	
	@Column(name="customer_name",length=255)
	private String customerName;//客户姓名
	
	@Column(name="create_date",nullable=false)
	private Date createDate;//创建日期
	
	@Column(name="update_date")
	private Date updateDate;//更新日期
	
	@Column(name="produce_date",nullable=false)
	private Date produceDate;//生产日期
	
	@Column(name="leave_date")
	private Date leaveDate;//出厂日期
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name="record_user")
	private User recordUser;//记录人员
	
	@Column(name="examine_user",length=20)
	private String examineUser;//检验员
	
	@Column(name="vehicle_system",length=500)
	private String vehicleSystem;//整机系统
	
	@Column(name="carframe_system",length=200)
	private String carframeSystem;//车架系统
	
	@ManyToOne
	@JoinColumn(name="brake_system")
	private BrakeSystem brakeSystem;//刹车系统
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_brake")
	private VehicleBrake vehicleBrake;//刹车系统详细信息
	
	@Column(name="brake_system_remark",length=300)
	private String brakeSystemRemark;//刹车系统备注
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="ceiling_system")
	private CeilingSystem ceilingSystem;//顶棚系统
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="elec_control_system")
	private ElectronicControlSystem electronicControlSystem;//电控系统
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_elec_control")
	private VehicleElecControl vehicleElecControl;//电控系统详细信息
	
	@Column(name="elec_control_remark",length=300)
	private String elecControlRemark;//电控系统备注
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="front_suspension_system")
	private FrontSuspensionSystems frontSuspensionSystem;//前悬系统
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_front_suspension")
	private VehicleFrontSuspension vehicleFrontSuspension;//前悬系统详细信息
	
	@Column(name="front_suspension_remark",length=300)
	private String frontSuspensionRemark;//前悬系统备注
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="rear_suspension_system")
	private RearSuspensionSystems rearSuspensionSystem;//后悬系统
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_rear_suspension")
	private VehicleRearSuspension vehicleRearSuspension;//后悬系统详细信息
	
	@Column(name="rear_suspension_remark",length=300)
	private String rearSuspensionRemark;//后悬系统备注
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="windshield_system")
	private WindshieldSystem windshieldSystem;//风挡系统
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="vehicle_windshield")
	private VehicleWindshield vehicleWindshield;//风挡系统详细信息
	
	@Column(name="windshield_remark",length=300)
	private String windshieldRemark;//风挡系统备注
	
	@Column(name="accessory",length=200)
	private String accessory;//选配件
	
	@Column(name="remarks",length=1000)	
	private String remark;//备注

	public String getVehicleNum() {
		return vehicleNum;
	}

	public void setVehicleNum(String vehicleNum) {
		this.vehicleNum = vehicleNum;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public User getRecordUser() {
		return recordUser;
	}

	public void setRecordUser(User recordUser) {
		this.recordUser = recordUser;
	}

	public String getExamineUser() {
		return examineUser;
	}

	public void setExamineUser(String examineUser) {
		this.examineUser = examineUser;
	}

	public String getVehicleSystem() {
		return vehicleSystem;
	}

	public void setVehicleSystem(String vehicleSystem) {
		this.vehicleSystem = vehicleSystem;
	}

	public String getCarframeSystem() {
		return carframeSystem;
	}

	public void setCarframeSystem(String carframeSystem) {
		this.carframeSystem = carframeSystem;
	}

	public BrakeSystem getBrakeSystem() {
		return brakeSystem;
	}

	public void setBrakeSystem(BrakeSystem brakeSystem) {
		this.brakeSystem = brakeSystem;
	}

	public VehicleBrake getVehicleBrake() {
		return vehicleBrake;
	}

	public void setVehicleBrake(VehicleBrake vehicleBrake) {
		this.vehicleBrake = vehicleBrake;
	}

	public String getBrakeSystemRemark() {
		return brakeSystemRemark;
	}

	public void setBrakeSystemRemark(String brakeSystemRemark) {
		this.brakeSystemRemark = brakeSystemRemark;
	}

	public CeilingSystem getCeilingSystem() {
		return ceilingSystem;
	}

	public void setCeilingSystem(CeilingSystem ceilingSystem) {
		this.ceilingSystem = ceilingSystem;
	}

	public ElectronicControlSystem getElectronicControlSystem() {
		return electronicControlSystem;
	}

	public void setElectronicControlSystem(
			ElectronicControlSystem electronicControlSystem) {
		this.electronicControlSystem = electronicControlSystem;
	}

	public VehicleElecControl getVehicleElecControl() {
		return vehicleElecControl;
	}

	public void setVehicleElecControl(VehicleElecControl vehicleElecControl) {
		this.vehicleElecControl = vehicleElecControl;
	}

	public String getElecControlRemark() {
		return elecControlRemark;
	}

	public void setElecControlRemark(String elecControlRemark) {
		this.elecControlRemark = elecControlRemark;
	}

	public FrontSuspensionSystems getFrontSuspensionSystem() {
		return frontSuspensionSystem;
	}

	public void setFrontSuspensionSystem(
			FrontSuspensionSystems frontSuspensionSystem) {
		this.frontSuspensionSystem = frontSuspensionSystem;
	}

	public VehicleFrontSuspension getVehicleFrontSuspension() {
		return vehicleFrontSuspension;
	}

	public void setVehicleFrontSuspension(
			VehicleFrontSuspension vehicleFrontSuspension) {
		this.vehicleFrontSuspension = vehicleFrontSuspension;
	}

	public String getFrontSuspensionRemark() {
		return frontSuspensionRemark;
	}

	public void setFrontSuspensionRemark(String frontSuspensionRemark) {
		this.frontSuspensionRemark = frontSuspensionRemark;
	}

	public RearSuspensionSystems getRearSuspensionSystem() {
		return rearSuspensionSystem;
	}

	public void setRearSuspensionSystem(RearSuspensionSystems rearSuspensionSystem) {
		this.rearSuspensionSystem = rearSuspensionSystem;
	}

	public VehicleRearSuspension getVehicleRearSuspension() {
		return vehicleRearSuspension;
	}

	public void setVehicleRearSuspension(VehicleRearSuspension vehicleRearSuspension) {
		this.vehicleRearSuspension = vehicleRearSuspension;
	}

	public String getRearSuspensionRemark() {
		return rearSuspensionRemark;
	}

	public void setRearSuspensionRemark(String rearSuspensionRemark) {
		this.rearSuspensionRemark = rearSuspensionRemark;
	}

	public WindshieldSystem getWindshieldSystem() {
		return windshieldSystem;
	}

	public void setWindshieldSystem(WindshieldSystem windshieldSystem) {
		this.windshieldSystem = windshieldSystem;
	}

	public VehicleWindshield getVehicleWindshield() {
		return vehicleWindshield;
	}

	public void setVehicleWindshield(VehicleWindshield vehicleWindshield) {
		this.vehicleWindshield = vehicleWindshield;
	}

	public String getWindshieldRemark() {
		return windshieldRemark;
	}

	public void setWindshieldRemark(String windshieldRemark) {
		this.windshieldRemark = windshieldRemark;
	}

	public String getAccessory() {
		return accessory;
	}

	public void setAccessory(String accessory) {
		this.accessory = accessory;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
