<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.easyui-textbox{
	border:1px solid lightgray;
}
body{padding:10px}
</style>
<script src=<%= request.getContextPath() + "/js/sysconfig/addParam.js" %> type="text/javascript"></script>
</head>
<body>
	<div align="center" style="margin-left:auto;margin-right:auto;width:100%">
		<sf:form id="addForm" modelAttribute="param" commandName="param" action="saveParam">
			<sf:input path="paramType" type="hidden" value="${paramType}" />
			<table style="border-collapse: separate;border-spacing:10px;">
				<tr>
					<td align="right">
						<label for="name">参数名称</label>
					</td>
					<td>
						<sf:input path="name" class="easyui-textbox"/>
					</td>
				</tr>
			</table>
		</sf:form>
	</div>
	<div align="center">
		<input id="saveBtn" class="orangeBtn" value="保存" type="button"/>
	</div>
</body>
</html>