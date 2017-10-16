<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/jsp/main/resource.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href=<%= request.getContextPath() + "/plugin/zTree/css/zTreeStyle.css" %> type="text/css">
<style>
.easyui-textbox{
	border:1px solid lightgray;
}
body{padding:10px}
</style>
<script src=<%= request.getContextPath() + "/plugin/zTree/javascript/jquery.ztree.core-3.5.min.js" %> type="text/javascript"></script>
<script src=<%= request.getContextPath() + "/plugin/zTree/javascript/jquery.ztree.excheck-3.5.min.js" %> type="text/javascript"></script>
<script src=<%= request.getContextPath() + "/plugin/zTree/javascript/jquery.ztree.exhide-3.5.min.js" %> type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() + "/js/user/configPermissions.js" %>" ></script>
</head>
<body>
	<input id="roleId" value="${roleId}" />
	<div align="center" style="margin-left:auto;margin-right:auto;width:100%">
		<div style="height:330px;border:1px solid;width:90%;overflow-y:scroll">
			<ul id="premissions" class="ztree"></ul>
		</div>
	</div>
	<div align="center">
		<input id="saveBtn" class="orangeBtn" value="保存" type="button"/>
		<input id="closeBtn" class="orangeBtn" value="关闭" type="button"/>
	</div>
	<input id="checked" type="hidden" value="${permissionIds}"/>
</body>
</html>