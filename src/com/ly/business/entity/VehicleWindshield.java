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
@Table(name="vehicle_windshield")
public class VehicleWindshield implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1695709013589005724L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	private String id;
	
//	@OneToOne
//	@JoinColumn(name="VEHICLE_NUM")
//	private VehicleInfo vehicleInfo;//车辆信息编码
	
	@Column(name="WINDSHIELD")
	private String windshield;//挡风玻璃
	
	@Column(name="REARVIEW_MIRROR")
	private String rearviewMirror;//后视镜，0：无，1：有
	
	@Column(name="LEFT_RIGHT_MIRROR")
	private String leftRightMirror;//左右反光镜
	
	@Column(name="SCORE_BOARD")
	private String scoreBoard;//记分牌，0：无，1：有
	
	@Column(name="WINDSHIELD_HOLDER")
	private String windshieldHolder;//挡风玻璃支架
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="vehicleWindshield")
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

	public String getWindshield() {
		return windshield;
	}

	public void setWindshield(String windshield) {
		this.windshield = windshield;
	}

	public String getRearviewMirror() {
		return rearviewMirror;
	}

	public void setRearviewMirror(String rearviewMirror) {
		this.rearviewMirror = rearviewMirror;
	}

	public String getLeftRightMirror() {
		return leftRightMirror;
	}

	public void setLeftRightMirror(String leftRightMirror) {
		this.leftRightMirror = leftRightMirror;
	}

	public String getScoreBoard() {
		return scoreBoard;
	}

	public void setScoreBoard(String scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public String getWindshieldHolder() {
		return windshieldHolder;
	}

	public void setWindshieldHolder(String windshieldHolder) {
		this.windshieldHolder = windshieldHolder;
	}
	
}
