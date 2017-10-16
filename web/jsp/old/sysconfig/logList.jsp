<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src=<%= request.getContextPath() + "/js/sysconfig/logList.js" %> type="text/javascript"></script>
</head>
<body class="easyui-layout">   
    <div id="center" data-options="region:'center'" style="background:#eee;">
		<!-- search -->
		<div>
			<form id="searchForm">
				<table style="border-collapse: separate;border-spacing:10px;">
					<tr>
						<td>
							<label clsss="span" for="logLevel">日志类型</label>
						</td>
						<td>
							<input id="logLevel" name="logLevel" class="easyui-combobox inputArea"/>
						</td>
						<td>
							<label clsss="span">开始时间</label>
						</td>
						<td>
							<input id="startTime" name="startTime" class="easyui-datetimebox inputArea"/>
						</td>
						<td>
							<label clsss="span">结束时间</label>
						</td>
						<td>
							<input id="endTime" name="endTime" class="easyui-datetimebox inputArea"/>
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
			<table id="logList"></table>
		</div>
    </div>   
</body> 
</html>