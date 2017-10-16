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
@Table(name="vehicle_front_suspension")
public class VehicleFrontSuspension implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6385772961959879928L;
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="VEHICLE_NUM")
//	private VehicleInfo vehicleInfo;//车辆信息编码
	
	@Column(name="LF_DAMPER",length=200)
	private String LFDamper;//左前减震器
	
	@Column(name="RF_DAMPER",length=200)
	private String RFDamper;//右前减震器
	
	@Column(name="STEERING_ENGINE",length=200)
	private String steeringEngine;//转向机
	
	@Column(name="CARDAN_JOINT",length=200)
	private String cardanJoint;//万向节
	
	@Column(name="LA_ZIBI",length=200)
	private String LAzibi;//左A自臂
	
	@Column(name="RA_ZIBI",length=200)
	private String RAzibi;//右A自臂
	
	@Column(name="WHEEL_HUB",length=200)
	private String wheelHub;//轮毂座
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="vehicleFrontSuspension")
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

	public String getLFDamper() {
		return LFDamper;
	}

	public void setLFDamper(String lFDamper) {
		LFDamper = lFDamper;
	}

	public String getRFDamper() {
		return RFDamper;
	}

	public void setRFDamper(String rFDamper) {
		RFDamper = rFDamper;
	}

	public String getSteeringEngine() {
		return steeringEngine;
	}

	public void setSteeringEngine(String steeringEngine) {
		this.steeringEngine = steeringEngine;
	}

	public String getCardanJoint() {
		return cardanJoint;
	}

	public void setCardanJoint(String cardanJoint) {
		this.cardanJoint = cardanJoint;
	}

	public String getLAzibi() {
		return LAzibi;
	}

	public void setLAzibi(String lAzibi) {
		LAzibi = lAzibi;
	}

	public String getRAzibi() {
		return RAzibi;
	}

	public void setRAzibi(String rAzibi) {
		RAzibi = rAzibi;
	}

	public String getWheelHub() {
		return wheelHub;
	}

	public void setWheelHub(String wheelHub) {
		this.wheelHub = wheelHub;
	}

}
