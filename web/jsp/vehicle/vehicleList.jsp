<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.inputArea{
		margin-right:60px;
		border:1px solid lightgray;
	}
	.span{
		margin-left:60px;
	}
	#reset{
		margin-left:30px;
	}
</style>
<link rel="stylesheet" href=<%= request.getContextPath() + "/plugin/viewer/css/viewer.min.css" %> type="text/css">
<script src=<%= request.getContextPath() + "/plugin/viewer/js/viewer.min.js" %> type="text/javascript"></script>
<script src=<%= request.getContextPath() + "/js/vehicle/vehicleInfoList.js" %> type="text/javascript"></script>
</head>
<body class="easyui-layout">   
	<!-- north -->
    <%-- <div data-options="region:'north',href:'<%= request.getContextPath() + "/jsp/main/north.jsp" %>'" 
    	style="height:120px;width:100%"></div>    --%>
    <!-- <div data-options="region:'west',title:'West',split:true" style="width:100px;"></div>   --> 
    
    <!-- center -->
    <div id="center" data-options="region:'center'" style="background:#eee;">
		<!-- search -->
		<div>
			<form id="searchForm">
				<table style="border-collapse: separate;border-spacing:10px;">
					<tr>
						<td>
							<label clsss="span" for="vehicleNum">整机编号</label>
						</td>
						<td>
							<input id="vehicleNum" name="vehicleNum" class="inputArea"/>
						</td>
						<td>
							<label clsss="span" for="vehicleType">整机型号</label>
						</td>
						<td>
							<input class="inputArea" id="vehicleType" name="vehicleType" />
						</td>
						<td>
							<label clsss="span" for="customerName">客户姓名</label>
						</td>
						<td>
							<input class="inputArea" id="customerName" name="customerName" />
						</td>
						<td>
							<label clsss="span" for="customerName">检验员</label>
						</td>
						<td>
							<input class="inputArea" id="examineUser" name="examineUser" />
						</td>
					</tr>
					<tr>
						<td>
							<label clsss="span">生产日期</label>
						</td>
						<td>
							<input id="produceDate" name="produceDate" class="easyui-datebox inputArea"/>
						</td>
						<td>
							<label clsss="span">出厂日期</label>
						</td>
						<td>
							<input id="leaveDate" name="leaveDate" class="easyui-datebox inputArea"/>
						</td>
						<td>
							<label clsss="span" for="customerName">信息录入员</label>
						</td>
						<td>
							<input class="inputArea" id="recordUser" name="recordUser" />
						</td>
						<td>
						</td>
						<td>
							<input type="button" class="greenBtn" id="search" onclick="doSearch()" value="查询" />
							<input type="button" class="greenBtn" id="reset" onclick="reSet1()" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- dataGrid -->
		<div>
			<table id="vehicleList"></table>
		</div>
		<input type="hidden" value="${role}" id="role" />
		<div align="center" id="buttons">
			<shiro:hasPermission name="vehicle:vehicleInfo:record">
				<input type="button" style="margin-left:30px" class="orangeBtn" id="add_button" value="录入" />
			</shiro:hasPermission>
			<shiro:hasPermission name="vehicle:vehicleInfo:edit">
				<input type="button" style="margin-left:30px" class="orangeBtn" id="edit_button" value="编辑" />
			</shiro:hasPermission>
		</div>
    </div>   
</body> 
</html>