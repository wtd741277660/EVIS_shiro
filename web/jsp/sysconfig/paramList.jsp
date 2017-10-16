<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src=<%= request.getContextPath() + "/js/sysconfig/paramList.js" %> type="text/javascript"></script>
</head>
<body class="easyui-layout">   
    <div id="center" data-options="region:'center'" style="background:#eee;">
		<!-- search -->
		<div style="margin-left:50px">
			<form id="searchForm">
				<input id="paramType" name="paramType" type="hidden" value="${paramType}" />
				<table style="border-collapse: separate;border-spacing:10px;">
					<tr>
						<td>
							<label clsss="span" for="logLevel">名称</label>
						</td>
						<td>
							<input id="name" name="name" class="inputArea"/>
						</td>
						<td>
							<input type="button" class="greenBtn" id="search" onclick="doSearch()" value="查询" />
							<input type="button" class="greenBtn" id="reset" onclick="reset1()" value="重置" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- dataGrid -->
		<div>
			<table id="paramList"></table>
		</div>
		<!-- button -->
		<div align="center">
			<c:if test="${paramType=='01'}">
				<c:set var="permissionAdd" value="system:frontSuspension:add"></c:set>
				<c:set var="permissionEdit" value="system:frontSuspension:edit"></c:set>
				<c:set var="permissionDelete" value="system:frontSuspension:delete"></c:set>
			</c:if>
			<c:if test="${paramType=='02'}">
				<c:set var="permissionAdd" value="system:rearSuspension:add"></c:set>
				<c:set var="permissionEdit" value="system:rearSuspension:edit"></c:set>
				<c:set var="permissionDelete" value="system:rearSuspension:delete"></c:set>
			</c:if>
			<c:if test="${paramType=='03'}">
				<c:set var="permissionAdd" value="system:elecControl:add"></c:set>
				<c:set var="permissionEdit" value="system:elecControl:edit"></c:set>
				<c:set var="permissionDelete" value="system:elecControl:delete"></c:set>
			</c:if>
			<c:if test="${paramType=='04'}">
				<c:set var="permissionAdd" value="system:brake:add"></c:set>
				<c:set var="permissionEdit" value="system:brake:edit"></c:set>
				<c:set var="permissionDelete" value="system:brake:delete"></c:set>
			</c:if>
			<c:if test="${paramType=='05'}">
				<c:set var="permissionAdd" value="system:windshield:add"></c:set>
				<c:set var="permissionEdit" value="system:windshield:edit"></c:set>
				<c:set var="permissionDelete" value="system:windshield:delete"></c:set>
			</c:if>
			<c:if test="${paramType=='06'}">
				<c:set var="permissionAdd" value="system:ceiling:add"></c:set>
				<c:set var="permissionEdit" value="system:ceiling:edit"></c:set>
				<c:set var="permissionDelete" value="system:ceiling:delete"></c:set>
			</c:if>
			<shiro:hasPermission name="${permissionAdd}">
				<input type="button" style="margin-left:30px" class="orangeBtn" id="add_button" value="新增" />
			</shiro:hasPermission>
			<shiro:hasPermission name="${permissionEdit}">
				<input type="button" style="margin-left:30px" class="orangeBtn" id="edit_button" value="编辑" />
			</shiro:hasPermission>
			<shiro:hasPermission name="${permissionDelete}">
				<input type="button" style="margin-left:30px" class="orangeBtn" id="delete_button" value="删除" />
			</shiro:hasPermission>
		</div>
    </div>   
</body> 
</html>