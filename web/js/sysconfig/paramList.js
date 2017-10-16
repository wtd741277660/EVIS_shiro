$(document).ready(function(){
	var height = $("#center").height() - 120;
	$('#paramList').datagrid({
		height:height,
	   	width:'auto',
	    url:'../params/queryParamList',  
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
		queryParams:$.serializeObject($('#searchForm')),
		columns : [[{
			field:'name',
			align : 'left',
			title:'名称',
			width: 100
		},{
			field : 'createTime', 
			align : 'left',
			title : '创建时间',
			width: 100
		},{
			field : 'updateTime', 
			align : 'left',
			title : '更新时间',
			width: 70
		}]]
	});
	var paramName = "";
	var paramType = $("#paramType").val();
	if(paramType == "01"){
		paramName = "前悬系统";
	}else if(paramType == "01"){
		paramName = "后悬系统";
	}else if(paramType == "01"){
		paramName = "电控系统";
	}else if(paramType == "01"){
		paramName = "刹车系统";
	}else if(paramType == "01"){
		paramName = "风挡系统";
	}else {
		paramName = "顶棚系统";
	}
	
	$("#add_button").bind("click",function(){
		createWithoutCancle("新增" + paramName + "参数","/EVIS/params/addParam?paramType=" + paramType,300,100);
	});
	$("#edit_button").bind("click",function(){
		var selects = $('#paramList').datagrid("getSelected");
		if(selects == null || selects.length == 0){
			$.messager.show({
       			title:'提示',
       			msg:'请先选择一条数据！',
       			timeout:3000,
       			showType:'slide'
       		});
			return false;
		}
		var id = selects["id"];
		createWithoutCancle("编辑" + paramName + "参数","/EVIS/params/editParam?id=" + id + "&paramType=" + paramType,330,150);
	});
	$("#delete_button").bind("click",function(){
		var selects = $('#paramList').datagrid("getSelected");
		if(selects == null || selects.length == 0){
			$.messager.show({
       			title:'提示',
       			msg:'请先选择一条数据！',
       			timeout:3000,
       			showType:'slide'
       		});
			return false;
		}
		var id = selects["id"];
		var name = selects["name"];
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/EVIS/params/deleteParam",
			data:{
				"id":id,
				"paramType":paramType,
				"name":name
			},
			success:function(data){
				var success = data.success;
				if(success == "true"){
					$.messager.show({
		       			title:'提示',
		       			msg:'删除参数成功！',
		       			timeout:3000,
		       			showType:'slide'
		       		});
					doSearch();
				}else{
					$.messager.show({
		       			title:'提示',
		       			msg:data.errorMsg,
		       			timeout:3000,
		       			showType:'slide'
		       		});
				}
			}
		})
	});
})
function doSearch(){
	var t = $.serializeObject($('#searchForm'));
	$('#paramList').datagrid('load', t); 
}
function reset1(){
	$("#name").val("");
	doSearch();
}