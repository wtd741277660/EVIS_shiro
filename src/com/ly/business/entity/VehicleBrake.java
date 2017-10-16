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
@Table(name="vehicle_brake")
public class VehicleBrake implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1837170002106033781L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="VEHICLE_NUM")
//	private VehicleInfo vehicleInfo;//车辆信息编码
	
	@Column(name="BRAKE_RETURN_SPRING")
	private String brakeReturnSpring;//刹车回位簧
	
	@Column(name="ACCELERATE_RETURN_SPRING")
	private String accelerateReturnSpring;//加速回位簧
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="vehicleBrake")
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

	public String getBrakeReturnSpring() {
		return brakeReturnSpring;
	}

	public void setBrakeReturnSpring(String brakeReturnSpring) {
		this.brakeReturnSpring = brakeReturnSpring;
	}

	public String getAccelerateReturnSpring() {
		return accelerateReturnSpring;
	}

	public void setAccelerateReturnSpring(String accelerateReturnSpring) {
		this.accelerateReturnSpring = accelerateReturnSpring;
	}

}
