package com.ly.business.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="vehicle_elec_control")
public class VehicleElecControl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1630200168862751356L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="VEHICLE_NUM")
//	private VehicleInfo vehicleInfo;//车辆信息编码
	
	@Column(name="CONTROLLER_NUM")
	private String controllerNum;//控制器编号
	
	@Column(name="CONTROLLER_TYPE")
	private String controllerType;//控制器型号
	
	@Column(name="DC_NUM")
	private String DCNum;//DC编号
	
	@Column(name="DC_TYPE")
	private String DCType;//DC型号，0：无，1：有
	
	@Column(name="ACCELERATOR_NUM")
	private String acceleratorNum;//加速器编号
	
	@Column(name="ACCELERATOR_TYPE")
	private String acceleratorType;//加速器型号
	
	@Column(name="STOP_LIGHT_SWITCH")
	private String stopLightSwitch;//刹车灯开关
	
	@Column(name="LF_LIGHT")
	private String LFLight;//左前灯
	
	@Column(name="RF_LIGHT")
	private String RFLight;//右前灯
	
	@Column(name="LR_LIGHT")
	private String LRLight;//左后灯
	
	@Column(name="RR_LIGHT")
	private String RRLight;//右后灯
	
	@Column(name="BATTERY_STANDARD")
	private String batteryStandard;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="vehicleElecControl")
	private VehicleInfo vehicle;
	
	public VehicleInfo getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleInfo vehicle) {
		this.vehicle = vehicle;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public VehicleInfo getVehicleInfo() {
//		return vehicleInfo;
//	}
//
//	public void setVehicleInfo(VehicleInfo vehicleInfo) {
//		this.vehicleInfo = vehicleInfo;
//	}

	public String getControllerNum() {
		return controllerNum;
	}

	public void setControllerNum(String controllerNum) {
		this.controllerNum = controllerNum;
	}

	public String getControllerType() {
		return controllerType;
	}

	public void setControllerType(String controllerType) {
		this.controllerType = controllerType;
	}

	public String getDCNum() {
		return DCNum;
	}

	public void setDCNum(String dCNum) {
		DCNum = dCNum;
	}

	public String getDCType() {
		return DCType;
	}

	public void setDCType(String dCType) {
		DCType = dCType;
	}

	public String getAcceleratorNum() {
		return acceleratorNum;
	}

	public void setAcceleratorNum(String acceleratorNum) {
		this.acceleratorNum = acceleratorNum;
	}

	public String getAcceleratorType() {
		return acceleratorType;
	}

	public void setAcceleratorType(String acceleratorType) {
		this.acceleratorType = acceleratorType;
	}

	public String getStopLightSwitch() {
		return stopLightSwitch;
	}

	public void setStopLightSwitch(String stopLightSwitch) {
		this.stopLightSwitch = stopLightSwitch;
	}

	public String getLFLight() {
		return LFLight;
	}

	public void setLFLight(String lFLight) {
		LFLight = lFLight;
	}

	public String getRFLight() {
		return RFLight;
	}

	public void setRFLight(String rFLight) {
		RFLight = rFLight;
	}

	public String getLRLight() {
		return LRLight;
	}

	public void setLRLight(String lRLight) {
		LRLight = lRLight;
	}

	public String getRRLight() {
		return RRLight;
	}

	public void setRRLight(String rRLight) {
		RRLight = rRLight;
	}

	public String getBatteryStandard() {
		return batteryStandard;
	}

	public void setBatteryStandard(String batteryStandard) {
		this.batteryStandard = batteryStandard;
	}

}
