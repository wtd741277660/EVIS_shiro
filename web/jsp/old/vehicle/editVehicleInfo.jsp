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
.easyui-textbox,textarea{
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
	width:92%;
	height:100%;
	border:1px solid lightgray;
	margin:0 auto;
	border-radius:5px;
}
.editDiv table{
	margin:0 auto;
	border-collapse: separate;
	border-spacing: 5px;
	margin-top:5px;
	margin-bottom:5px;
}
</style>
<script src=<%= request.getContextPath() + "/js/vehicle/editVehicleInfo.js" %> type="text/javascript"></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/vendor/jquery.ui.widget.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/jquery.iframe-transport.js" %>></script>
<script src=<%= request.getContextPath() + "/plugin/fileUpload/js/jquery.fileupload.js" %>></script>
</head>
<body>
	<div align="center" style="margin-left:auto;margin-right:auto;width:100%;height:440px;overflow:auto;border-bottom:1px solid lightgray">
		<form id="addForm" style="height:100%">
			<table style="border-collapse: separate;border-spacing:10px;height:100%">
				<tr>
					<td align="right">
						<label for="vehicleNum">整机编号</label>
					</td>
					<td>
						<input id="vehicleNum" name="vehicleNum" class="easyui-textbox" readonly="readonly" value="${vehicle.vehicleNum}"/>
					</td>
					<td align="right">
						<label style="margin-left:50px" for="vehicleType">整机型号</label>
					</td>
					<td>
						<input id="vehicleType" name="vehicleType" class="easyui-textbox" value="${vehicle.vehicleType}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="customerName">客户名称</label>
					</td>
					<td>
						<input id="customerName" name="customerName" class="easyui-textbox" value="${vehicle.customerName}"/>
					</td>
					<td align="right">
						<label for="examineUser">检验员</label>
					</td>
					<td>
						<input id="examineUser" name="examineUser" class="easyui-textbox" value="${vehicle.examineUser}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="produceDate">生产日期</label>
					</td>
					<td>
						<input id="produceDate" name="produceDate" class="easyui-datebox" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${vehicle.produceDate}"/>'/>
					</td>
					<td align="right">
						<label for="leaveDate">出厂日期</label>
					</td>
					<td>
						<input id="leaveDate" name="leaveDate" class="easyui-datebox" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${vehicle.leaveDate}"/>'/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>整机系统</label>
					</td>
					<td colspan="3">
						<div style="display:inline">
							<div id="uploadDiv" class="upload clearfix">
								<div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload uploadBtn" id="uploadBtn">
						                <input type="file" name="files" data-url="saveFiles" value="" id="file7"
						                    onchange="CheckFile(this)" multiple/>重新上传 </a>
						        </div>
						        <div class="uploadbtnBox clearfix">
						            <a href="javascript:;" class="a-upload" onclick="cancleFile(this)"> 取消上传</a>
						        </div>
						        <div id="progresspercent" class="progresspercent"></div>
						        <div id="progress" class="progress" style="height: 4px;">
						            <div id="bar" class="bar" style="width: 0%;"></div>
						        </div>
						        <input class="savedFiles" id="vehicleSystem" name="vehicleSystem" type="hidden"  value="${vehicle.vehicleSystem}"/>
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="carframeSystem">车架系统</label>
					</td>
					<td>
						<input id="carframeSystem" name="carframeSystem" class="easyui-textbox" value="${vehicle.carframeSystem}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="frontSuspensionSystem">前悬系统</label>
					</td>
					<td>
						<input id="frontSuspensionSystem" name="frontSuspensionSystem" class="easyui-combobox" value="${vehicle.frontSuspensionSystem.id}"/>
					</td>
					<td align="right">
						<label for="frontSuspensionRemark">备注</label>
					</td>
					<td>
						<input id="frontSuspensionRemark" name="frontSuspensionRemark" value="${vehicle.frontSuspensionRemark}" class="easyui-textbox"/>
						<a class="editBtn">编辑</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label for="LFDamper">左前减震器</label>
									</td>
									<td>
										<input id="LFDamper" name="vehicleFrontSuspension.LFDamper" value="${vehicle.vehicleFrontSuspension.LFDamper}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="RFDamper">右前减震器</label>
									</td>
									<td>
										<input id="RFDamper" name="vehicleFrontSuspension.RFDamper" value="${vehicle.vehicleFrontSuspension.RFDamper}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="steeringEngine">转向机</label>
									</td>
									<td>
										<input id="steeringEngine" name="vehicleFrontSuspension.steeringEngine" value="${vehicle.vehicleFrontSuspension.steeringEngine}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="cardanJoint">万向节</label>
									</td>
									<td>
										<input id="cardanJoint" name="vehicleFrontSuspension.cardanJoint" value="${vehicle.vehicleFrontSuspension.cardanJoint}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="LAzibi">左A自臂</label>
									</td>
									<td>
										<input id="LAzibi" name="vehicleFrontSuspension.LAzibi" value="${vehicle.vehicleFrontSuspension.LAzibi}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="RAzibi">右A自臂</label>
									</td>
									<td>
										<input id="RAzibi" name="vehicleFrontSuspension.RAzibi" value="${vehicle.vehicleFrontSuspension.RAzibi}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="wheelHub">轮毂座</label>
									</td>
									<td>
										<input id="wheelHub" name="vehicleFrontSuspension.wheelHub" value="${vehicle.vehicleFrontSuspension.wheelHub}" class="easyui-textbox"/>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="rearSuspensionSystem">后悬系统</label>
					</td>
					<td>
						<input id="rearSuspensionSystem" name="rearSuspensionSystem" value="${vehicle.rearSuspensionSystem.id}" class="easyui-combobox"/>
					</td>
					<td align="right">
						<label for="rearSuspensionRemark">备注</label>
					</td>
					<td>
						<input id="rearSuspensionRemark" name="rearSuspensionRemark" value="${vehicle.rearSuspensionRemark}" class="easyui-textbox"/>
						<a class="editBtn">编辑</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label for="rearAxleNum">后桥编号</label>
									</td>
									<td>
										<input id="rearAxleNum" name="vehicleRearSuspension.rearAxleNum" value="${vehicle.vehicleRearSuspension.rearAxleNum}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="rearAxleType">后桥型号</label>
									</td>
									<td>
										<input id="rearAxleType" name="vehicleRearSuspension.rearAxleType" value="${vehicle.vehicleRearSuspension.rearAxleType}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="elecMachineNum">电机编号</label>
									</td>
									<td>
										<input id="elecMachineNum" name="vehicleRearSuspension.elecMachineNum" value="${vehicle.vehicleRearSuspension.elecMachineNum}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="elecMachineType">电机型号</label>
									</td>
									<td>
										<input id="elecMachineType" name="vehicleRearSuspension.elecMachineType" value="${vehicle.vehicleRearSuspension.elecMachineType}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="afterPlateSpring">后板簧</label>
									</td>
									<td>
										<input id="afterPlateSpring" name="vehicleRearSuspension.afterPlateSpring" value="${vehicle.vehicleRearSuspension.afterPlateSpring}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="afterDamping">后减震</label>
									</td>
									<td>
										<input id="afterDamping" name="vehicleRearSuspension.afterDamping" value="${vehicle.vehicleRearSuspension.afterDamping}" class="easyui-textbox"/>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="electronicControlSystem">电控系统</label>
					</td>
					<td>
						<input id="electronicControlSystem" name="electronicControlSystem" value="${vehicle.electronicControlSystem.id}" class="easyui-combobox"/>
					</td>
					<td align="right">
						<label for="elecControlRemark">备注</label>
					</td>
					<td>
						<input id="elecControlRemark" name="elecControlRemark" value="${vehicle.elecControlRemark}" class="easyui-textbox"/>
						<a class="editBtn">编辑</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label for="controllerNum">控制器编号</label>
									</td>
									<td>
										<input id="controllerNum" name="vehicleElecControl.controllerNum" value="${vehicle.vehicleElecControl.controllerNum}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="controllerType">控制器型号</label>
									</td>
									<td>
										<input id="controllerType" name="vehicleElecControl.controllerType" value="${vehicle.vehicleElecControl.controllerType}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="DCNum">DC编号</label>
									</td>
									<td>
										<input id="DCNum" name="vehicleElecControl.DCNum" value="${vehicle.vehicleElecControl.DCNum}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="DCType">DC型号</label>
									</td>
									<td>
										<input id="DCType" name="vehicleElecControl.DCType" value="${vehicle.vehicleElecControl.DCType}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="acceleratorNum">加速器编号</label>
									</td>
									<td>
										<input id="acceleratorNum" name="vehicleElecControl.acceleratorNum" value="${vehicle.vehicleElecControl.acceleratorNum}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="acceleratorType">加速器型号</label>
									</td>
									<td>
										<input id="acceleratorType" name="vehicleElecControl.acceleratorType" value="${vehicle.vehicleElecControl.acceleratorType}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="LFLight">左前灯</label>
									</td>
									<td>
										<input id="LFLight" name="vehicleElecControl.LFLight" value="${vehicle.vehicleElecControl.LFLight}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="RFLight">右前灯</label>
									</td>
									<td>
										<input id="RFLight" name="vehicleElecControl.RFLight" value="${vehicle.vehicleElecControl.RFLight}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="LRLight">左后灯</label>
									</td>
									<td>
										<input id="LRLight" name="vehicleElecControl.LRLight" value="${vehicle.vehicleElecControl.LRLight}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="RRLight">右后灯</label>
									</td>
									<td>
										<input id="RRLight" name="vehicleElecControl.RRLight" value="${vehicle.vehicleElecControl.RRLight}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="stopLightSwitch">刹车灯开关</label>
									</td>
									<td>
										<input id="stopLightSwitch" name="vehicleElecControl.stopLightSwitch" value="${vehicle.vehicleElecControl.stopLightSwitch}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="batteryStandard">电池型号规格</label>
									</td>
									<td>
										<input id="batteryStandard" name="vehicleElecControl.batteryStandard" value="${vehicle.vehicleElecControl.batteryStandard}" class="easyui-textbox"/>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="brakeSystem">刹车系统</label>
					</td>
					<td>
						<input id="brakeSystem" name="brakeSystem" value="${vehicle.brakeSystem.id}" class="easyui-combobox"/>
					</td>
					<td align="right">
						<label for="brakeSystemRemark">备注</label>
					</td>
					<td>
						<input id="brakeSystemRemark" name="brakeSystemRemark" value="${vehicle.brakeSystemRemark}" class="easyui-textbox"/>
						<a class="editBtn">编辑</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label for="brakeReturnSpring">刹车回位簧</label>
									</td>
									<td>
										<input id="brakeReturnSpring" name="vehicleBrake.brakeReturnSpring" value="${vehicle.vehicleBrake.brakeReturnSpring}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="accelerateReturnSpring">加速回位簧</label>
									</td>
									<td>
										<input id="accelerateReturnSpring" name="vehicleBrake.accelerateReturnSpring" value="${vehicle.vehicleBrake.accelerateReturnSpring}" class="easyui-textbox"/>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="windshieldSystem">风挡系统</label>
					</td>
					<td>
						<input id="windshieldSystem" name="windshieldSystem" value="${vehicle.windshieldSystem.id}" class="easyui-combobox"/>
					</td>
					<td align="right">
						<label for="windshieldRemark">备注</label>
					</td>
					<td>
						<input id="windshieldRemark" name="windshieldRemark" value="${vehicle.windshieldRemark}" class="easyui-textbox"/>
						<a class="editBtn">编辑</a>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<div  class="editDiv">
							<table align="center">
								<tr>
									<td align="right">
										<label for="windshield">挡风玻璃</label>
									</td>
									<td>
										<input id="windshield" name="vehicleWindshield.windshield" value="${vehicle.vehicleWindshield.windshield}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="rearviewMirror">后视镜</label>
									</td>
									<td>
										<input id="rearviewMirror" name="vehicleWindshield.rearviewMirror" value="${vehicle.vehicleWindshield.rearviewMirror}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="leftRightMirror">左右反光镜</label>
									</td>
									<td>
										<input id="leftRightMirror" name="vehicleWindshield.leftRightMirror" value="${vehicle.vehicleWindshield.leftRightMirror}" class="easyui-textbox"/>
									</td>
									<td align="right" style="margin-left:10px">
										<label for="scoreBoard">记分牌</label>
									</td>
									<td>
										<input id="scoreBoard" name="vehicleWindshield.scoreBoard" value="${vehicle.vehicleWindshield.scoreBoard}" class="easyui-textbox"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label for="windshieldHolder">挡风玻璃支架</label>
									</td>
									<td>
										<input id="windshieldHolder" name="vehicleWindshield.windshieldHolder" value="${vehicle.vehicleWindshield.windshieldHolder}" class="easyui-textbox"/>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label for="ceilingSystem">顶棚系统</label>
					</td>
					<td>
						<input id="ceilingSystem" name="ceilingSystem" value="${vehicle.ceilingSystem.id}" class="easyui-combobox"/>
					</td>
					<td align="right">
						<label for="accessory">选配件</label>
					</td>
					<td class="params">
						<input id="accessory" name="accessory" class="easyui-textbox" value="${vehicle.accessory}"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label>备注</label>
					</td>
					<td colspan="3">
						<textarea id="remarks" name="remarks" rows="4" cols="35">${vehicle.remark}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div align="center">
		<input id="saveBtn" class="orangeBtn" value="保存" type="button"/>
		<input id="closeBtn" class="orangeBtn" value="关闭" type="button"/>
	</div>
</body>
</html>