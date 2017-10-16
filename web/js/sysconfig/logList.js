$(document).ready(function(){
	
	$('#logLevel').combobox({   
		url:'/EVIS/log/getLogTypes',
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	});  
	
	var height = $("#center").height() - 120;
	$('#logList').datagrid({
		height:height,
	   	width:'auto',
	    url:'../log/getList',  
	    rownumbers:true,	//显示行数
		fitColumns:true,	//自适应列的大小，防止出现水平滚动条
		loadMsg:"正在加载数据，请稍等...",	//载入等待时信息
		nowrap:true,	//数据长度超过列宽时自动截取，默认为true，否则换行
		pagination:true,	//显示分页工具栏
		pageNumber:1,	//设置分页属性时，初始化页码，默认为1
		pageSize:20,	//设置分页属性时，初始化每页记录数，默认为10
		pageList:[10,20,30,40,50],		//设置分页属性时，初始化每页记录数列表，默认为[10,20,30,40,50]
		striped:true,	//设置斑马线
		singleSelect : true,	//只允许选择一行
		columns : [[{
			field:'operateUser',
			align : 'left',
			title:'操作人',
			width: 100
		},{
			field : 'operateDate', 
			align : 'left',
			title : '操作时间',
			width: 100
		},{
			field : 'content', 
			align : 'left',
			title : '日志记录',
			width: 70,
			formatter: function(value,row,index){
				if(value != ""){
					return "<label title='" + value + "'>" + value + "</label>";
				}else{
					return "";
				}
			}
		},{
			field : 'operateIP', 
			align : 'left',
			title : '操作IP',
			width: 70
		},{
			field : 'logLevel', 
			align : 'left',
			title : '日志类型',
			width: 70,
			formatter: function(value,row,index){
				if (value == "1"){
					return "车辆信息";
				} else if(value == "2"){
					return "用户信息";
				} else if (value == "3"){
					return "系统信息";
				} else {
					return value;
				}
			}
		}]]
	});
})
function doSearch(){
	var t = $.serializeObject($('#searchForm'));
	$('#logList').datagrid('load', t); 
}
function reset1(){
	$("#logLevel").combobox("reset");
	$("#startTime").datetimebox("reset");
	$("#endTime").datetimebox("reset");
	doSearch();
}