$(document).ready(function(){
	var height = $("#center").height() - 120;
	
	$('#roleList').datagrid({
		height:height,
	   	width:'auto',
	    url:'../role/getList',  
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
			field : 'roleName', 
			align : 'left',
			title : '角色名称',
			width: 70
		},{
			field : 'createTime', 
			align : 'left',
			title : '创建时间',
			width: 70
		},{
			field : 'updateTime', 
			align : 'left',
			title : '更新时间',
			width: 70
		}]]
	});
	
	$("#edit_button").bind("click",function(){
		var selects = $('#roleList').datagrid("getSelections");
		if(selects == null || selects.length == 0){
			$.messager.show({
    			title:'提示',
    			msg:'请先选择一个角色！',
    			timeout:3000,
    			showType:'slide'
    		});
			return false;
		}else if(selects.length > 1){
			$.messager.show({
    			title:'提示',
    			msg:'只能选择一个角色！',
    			timeout:3000,
    			showType:'slide'
    		});
			return false;
		}else{
			var roleId = selects[0]["roleId"];
			createWithoutCancle("配置权限","/EVIS/role/configPermi?roleId=" + roleId,400,400);
		}
	});
});
function doSearch(){
	var t = $.serializeObject($('#searchForm'));
	$('#roleList').datagrid('load', t); 
}
function reset1(){
	$("#roleName").val("");
	doSearch();
}