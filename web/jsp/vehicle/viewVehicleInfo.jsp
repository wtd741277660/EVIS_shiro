<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.textarea{
	border:1px solid lightgray;
}
body{
	padding:10px;
}
.editBtn{
	cursor: pointer;
	font-size:10px;
	color:blue;
	text-decoration:underline;
}
.editDiv{
	width:90%;
	height:100%;
	border:1px solid lightgray;
	margin:0 auto;
	border-radius:5px;
}
.editDiv table{
	margin:0 auto;
	border-collapse: separate;
	border-spacing: 3px;
	margin-top:5px;
	margin-bottom:5px;
}
.viewArea{
	width:150px;
	display:inline-block;
	overflow:hidden;
	/* white-space:nowrap; */
	border-bottom:1px solid lightgray;
}
#dowebok {
	list-style: none;
}
#dowebok li {
	display:inline;
	margin-left:10px;
}
</style>
<link rel="stylesheet" href=<%= request.getContextPath() + "/plugin/viewer/css/viewer.min.css" %> type="text/css">
<script src=<%= request.getContextPath() + "/plugin/viewer/js/viewer.min.js" %> type="text/javascript"></script>
<script src=<%= request.getContextPath() + "/js/vehicle/viewVehicleInfo.js" %> type="text/javascript"></script>
</head>
<body>
	<div align="center" style="margin-left:auto;margin-right:auto;width:100%;height:440px;overflow:auto;border-bottom:1px solid lightgray">
		<form id="addForm" style="height:100%">
			<table style="border-collapse: separate;border-spacing:10px;height:100%">
				<tr>
					<td align="right">
						<label>整机编号</label>
					</td>
					<td>
						<label id="vehicleNum" class="viewArea" title="${vehicle.vehicleNum}">${vehicle.vehicleNum}</label>
					</td>
					<td align="right">
						<label style="margin-left:50px">整机型号</label>
					</td>
					<td>
						<label id="vehicleType" class="viewArea" title="${vehicle.vehicleType}">${vehicle.vehicleType}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>客户名称</label>
					</td>
					<td>
						<label id="customerName" class="viewArea" title="${vehicle.customerName}">${vehicle.customerName}</label>
					</td>
					<td align="right">
						<label>检验员</label>
					</td>
					<td>
						<label id="examineUser" class="viewArea" title="${vehicle.examineUser}">${vehicle.examineUser}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>生产日期</label>
					</td>
					<td>
						<label id="produceDate" class="viewArea"><fmt:formatDate value="${vehicle.produceDate}" type="date" pattern="yyyy-MM-dd"/></label>
					</td>
					<td align="right">
						<label>出厂日期</label>
					</td>
					<td>
						<label id="leaveDate" class="viewArea"><fmt:formatDate value="${vehicle.leaveDate}" type="date" pattern="yyyy-MM-dd"/></label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>整机系统</label>
					</td>
					<td colspan="3">
						<input id="vehicleSystem" value="${vehicle.vehicleSystem}" type="hidden" />
						<ul id="dowebok">
						    
						</ul>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>车架系统</label>
					</td>
					<td>
						<label id="carframeSystem" class="viewArea" title="${vehicle.carframeSystem}">${vehicle.carframeSystem}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>前悬系统</label>
					</td>
					<td>
						<label id="frontSuspensionSystem" class="viewArea" title="${vehicle.frontSuspensionSystem.name}">${vehicle.frontSuspensionSystem.name}</label>
					</td>
					<td align="right">
						<label>备注</label>
					</td>
					<td>
						<label id="frontSuspensionRemark" class="viewArea" title="${vehicle.frontSuspensionRemark}">${vehicle.frontSuspensionRemark}</label>
						<a class="editBtn">展开</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label>左前减震器</label>
									</td>
									<td>
										<label id="LFDamper" class="viewArea" title="${vehicle.vehicleFrontSuspension.LFDamper}">${vehicle.vehicleFrontSuspension.LFDamper}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>右前减震器</label>
									</td>
									<td>
										<label id="RFDamper" class="viewArea" title="${vehicle.vehicleFrontSuspension.RFDamper}">${vehicle.vehicleFrontSuspension.RFDamper}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>转向机</label>
									</td>
									<td>
										<label id="steeringEngine" class="viewArea" title="${vehicle.vehicleFrontSuspension.steeringEngine}">${vehicle.vehicleFrontSuspension.steeringEngine}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>万向节</label>
									</td>
									<td>
										<label id="cardanJoint" class="viewArea" title="${vehicle.vehicleFrontSuspension.cardanJoint}">${vehicle.vehicleFrontSuspension.cardanJoint}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>左A自臂</label>
									</td>
									<td>
										<label id="LAzibi" class="viewArea" title="${vehicle.vehicleFrontSuspension.LAzibi}">${vehicle.vehicleFrontSuspension.LAzibi}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>右A自臂</label>
									</td>
									<td>
										<label id="RAzibi" class="viewArea" title="${vehicle.vehicleFrontSuspension.RAzibi}">${vehicle.vehicleFrontSuspension.RAzibi}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>轮毂座</label>
									</td>
									<td>
										<label id="wheelHub" class="viewArea" title="${vehicle.vehicleFrontSuspension.wheelHub}">${vehicle.vehicleFrontSuspension.wheelHub}</label>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>后悬系统</label>
					</td>
					<td>
						<label id="rearSuspensionSystem" class="viewArea" title="${vehicle.rearSuspensionSystem.name}">${vehicle.rearSuspensionSystem.name}</label>
					</td>
					<td align="right">
						<label>备注</label>
					</td>
					<td>
						<label id="rearSuspensionRemark" class="viewArea" title="${vehicle.rearSuspensionRemark}">${vehicle.rearSuspensionRemark}</label>
						<a class="editBtn">展开</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label>后桥编号</label>
									</td>
									<td>
										<label id="rearAxleNum" class="viewArea" title="${vehicle.vehicleRearSuspension.rearAxleNum}">${vehicle.vehicleRearSuspension.rearAxleNum}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>后桥型号</label>
									</td>
									<td>
										<label id="rearAxleType" class="viewArea" title="${vehicle.vehicleRearSuspension.rearAxleType}">${vehicle.vehicleRearSuspension.rearAxleType}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>电机编号</label>
									</td>
									<td>
										<label id="elecMachineNum" class="viewArea" title="${vehicle.vehicleRearSuspension.elecMachineNum}">${vehicle.vehicleRearSuspension.elecMachineNum}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>电机型号</label>
									</td>
									<td>
										<label id="elecMachineType" class="viewArea" title="${vehicle.vehicleRearSuspension.elecMachineType}">${vehicle.vehicleRearSuspension.elecMachineType}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>后板簧</label>
									</td>
									<td>
										<label id="afterPlateSpring" class="viewArea" title="${vehicle.vehicleRearSuspension.afterPlateSpring}">${vehicle.vehicleRearSuspension.afterPlateSpring}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>后减震</label>
									</td>
									<td>
										<label id="afterDamping" class="viewArea" title="${vehicle.vehicleRearSuspension.afterDamping}">${vehicle.vehicleRearSuspension.afterDamping}</label>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>电控系统</label>
					</td>
					<td>
						<label id="electronicControlSystem" class="viewArea" title="${vehicle.electronicControlSystem.name}">${vehicle.electronicControlSystem.name}</label>
					</td>
					<td align="right">
						<label>备注</label>
					</td>
					<td>
						<label id="elecControlRemark" name="" class="viewArea" title="${vehicle.elecControlRemark}">${vehicle.elecControlRemark}</label>
						<a class="editBtn">展开</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label>控制器编号</label>
									</td>
									<td>
										<label id="controllerNum" class="viewArea" title="${vehicle.vehicleElecControl.controllerNum}">${vehicle.vehicleElecControl.controllerNum}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>控制器型号</label>
									</td>
									<td>
										<label id="controllerType" class="viewArea" title="${vehicle.vehicleElecControl.controllerType}">${vehicle.vehicleElecControl.controllerType}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>DC编号</label>
									</td>
									<td>
										<label id="DCNum" class="viewArea" title="${vehicle.vehicleElecControl.DCNum}">${vehicle.vehicleElecControl.DCNum}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>DC型号</label>
									</td>
									<td>
										<label id="DCType" class="viewArea">${vehicle.vehicleElecControl.DCType}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>加速器编号</label>
									</td>
									<td>
										<label id="acceleratorNum" class="viewArea" title="${vehicle.vehicleElecControl.acceleratorNum}">${vehicle.vehicleElecControl.acceleratorNum}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>加速器型号</label>
									</td>
									<td>
										<label id="acceleratorType" class="viewArea" title="${vehicle.vehicleElecControl.acceleratorType}">${vehicle.vehicleElecControl.acceleratorType}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>左前灯</label>
									</td>
									<td>
										<label id="LFLight" class="viewArea" title="${vehicle.vehicleElecControl.LFLight}">${vehicle.vehicleElecControl.LFLight}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>右前灯</label>
									</td>
									<td>
										<label id="RFLight" class="viewArea" title="${vehicle.vehicleElecControl.RFLight}">${vehicle.vehicleElecControl.RFLight}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>左后灯</label>
									</td>
									<td>
										<label id="LRLight" class="viewArea" title="${vehicle.vehicleElecControl.LRLight}">${vehicle.vehicleElecControl.LRLight}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>右后灯</label>
									</td>
									<td>
										<label id="RRLight" class="viewArea" title="${vehicle.vehicleElecControl.RRLight}">${vehicle.vehicleElecControl.RRLight}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>刹车灯开关</label>
									</td>
									<td>
										<label id="stopLightSwitch" class="viewArea" title="${vehicle.vehicleElecControl.stopLightSwitch}">${vehicle.vehicleElecControl.stopLightSwitch}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>电池型号规格</label>
									</td>
									<td>
										<label id="batteryStandard" class="viewArea" title="${vehicle.vehicleElecControl.batteryStandard}">${vehicle.vehicleElecControl.batteryStandard}</label>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>刹车系统</label>
					</td>
					<td>
						<label id="brakeSystem" class="viewArea" title="${vehicle.brakeSystem.name}">${vehicle.brakeSystem.name}</label>
					</td>
					<td align="right">
						<label>备注</label>
					</td>
					<td>
						<label id="brakeSystemRemark" class="viewArea" title="${vehicle.brakeSystemRemark}">${vehicle.brakeSystemRemark}</label>
						<a class="editBtn">展开</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label>刹车回位簧</label>
									</td>
									<td>
										<label id="brakeReturnSpring" class="viewArea" title="${vehicle.vehicleBrake.brakeReturnSpring}">${vehicle.vehicleBrake.brakeReturnSpring}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>加速回位簧</label>
									</td>
									<td>
										<label id="accelerateReturnSpring" class="viewArea" title="${vehicle.vehicleBrake.accelerateReturnSpring}">${vehicle.vehicleBrake.accelerateReturnSpring}</label>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>风挡系统</label>
					</td>
					<td>
						<label id="windshieldSystem" class="viewArea" title="${vehicle.windshieldSystem.name}">${vehicle.windshieldSystem.name}</label>
					</td>
					<td align="right">
						<label>备注</label>
					</td>
					<td>
						<label id="windshieldRemark" class="viewArea" title="${vehicle.windshieldRemark}">${vehicle.windshieldRemark}</label>
						<a class="editBtn">展开</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label>挡风玻璃</label>
									</td>
									<td>
										<label id="windshield" class="viewArea" title="${vehicle.vehicleWindshield.windshield}">${vehicle.vehicleWindshield.windshield}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>后视镜</label>
									</td>
									<td>
										<label id="rearviewMirror" class="viewArea">${vehicle.vehicleWindshield.rearviewMirror}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>左右反光镜</label>
									</td>
									<td>
										<label id="leftRightMirror" class="viewArea" title="${vehicle.vehicleWindshield.leftRightMirror}">${vehicle.vehicleWindshield.leftRightMirror}</label>
									</td>
									<td align="right" style="margin-left:10px">
										<label>记分牌</label>
									</td>
									<td>
										<label id="scoreBoard" class="viewArea">${vehicle.vehicleWindshield.scoreBoard}</label>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>挡风玻璃支架</label>
									</td>
									<td>
										<label id="windshieldHolder" class="viewArea" title="${vehicle.vehicleWindshield.windshieldHolder}">${vehicle.vehicleWindshield.windshieldHolder}</label>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>顶棚系统</label>
					</td>
					<td>
						<label id="ceilingSystem" class="viewArea" title="${vehicle.ceilingSystem.name}">${vehicle.ceilingSystem.name}</label>
					</td>
					<td align="right">
						<label>选配件</label>
					</td>
					<td>
						<label id="accessory" class="viewArea" title="${vehicle.accessory}">${vehicle.accessory}</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>备注</label>
					</td>
					<td colspan="3">
						<textarea id="remarks" name="remarks" rows="4" cols="35" readonly="readonly">${vehicle.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center">
		<input id="closeBtn" class="orangeBtn" value="关闭" type="button"/>
	</div>
</body>
</html>