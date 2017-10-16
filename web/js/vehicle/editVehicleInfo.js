$(document).ready(function(){
	//初始化参数下拉框
	$('#frontSuspensionSystem').combobox({    
	    url:'../params/getParamCombo?paramType=01',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    editable:false
	});
	$('#rearSuspensionSystem').combobox({    
	    url:'../params/getParamCombo?paramType=02',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    editable:false
	});  
	$('#electronicControlSystem').combobox({    
	    url:'../params/getParamCombo?paramType=03',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    editable:false
	});  
	$('#brakeSystem').combobox({    
	    url:'../params/getParamCombo?paramType=04',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    editable:false
	});  
	$('#windshieldSystem').combobox({    
	    url:'../params/getParamCombo?paramType=05',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    editable:false
	});  
	$('#ceilingSystem').combobox({    
	    url:'../params/getParamCombo?paramType=06',    
	    valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    editable:false
	});  
	
	$("#DCType,#rearviewMirror,#scoreBoard").combobox({
		valueField:'id',    
	    textField:'name',
	    panelHeight:'auto',
	    data:[{    
	        "id":1,    
	        "name":"有"   
	    },{    
	        "id":0,    
	        "name":"无"   
	    }],
	    editable:false
	});
	
	//初始化文件上传插件
	initFileUpload();
	
	//保存车辆信息
	$("#saveBtn").bind("click",function(){
		if(jQuery("form").form('validate')){
			var vehicleNum = $("#vehicleNum").val();
			if(vehicleNum == null || vehicleNum == "" || vehicleNum == "请先输入整机编号"){
				parent.$.messager.show({
           			title:'提示',
           			msg:'车辆编号不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
			}
        	var vehicleType = $("#vehicleType").val();
        	var customerName = $("#customerName").val();
        	var examineUser = $("#examineUser").val();
        	var produceDate = $("#produceDate").datebox('getValue');
        	var leaveDate = $("#leaveDate").datebox('getValue');
        	var vehicleSystem = $("#vehicleSystem").val();
        	var carframeSystem = $("#carframeSystem").val();
        	if(vehicleType == null || vehicleType == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'整机型号不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(customerName == null || customerName == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'客户姓名不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(examineUser == null || examineUser == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'检验员不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(produceDate == null || produceDate == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'生产日期不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(leaveDate == null || leaveDate == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'出厂日期不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(vehicleSystem == null || vehicleSystem == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'整机系统不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	if(carframeSystem == null || carframeSystem == ""){
        		parent.$.messager.show({
           			title:'提示',
           			msg:'车架系统不能为空！',
           			timeout:3000,
           			showType:'slide'
           		});
           		return false;
        	}
        	//判断各个系统的编辑域是否都填写了
        	var checked = checkCombobox();
        	if(checked == false){
        		return false;
        	}
        	checked = checkEditDiv();
        	if(checked == false){
        		return false;
        	}
    		//如果验证成功，就通过ajax方式将数据传到后台并保存
    		var data = $.serializeObject($('#addForm'));
    		data.type = "edit";
    		$.ajax({
    		     type: 'POST',
    		     url: "/EVIS/vehicle/updateVehicle" ,
    		     data: {"data":JSON.stringify(data)},
    		     dataType: "json",
    		     success: function(result){
    		    	 var success = result.success;
    		    	 if(success == "true"){
						parent.$.messager.show({
	               			title:'提示',
	               			msg:'车辆信息更新成功！',
	               			timeout:3000,
	               			showType:'slide'
	               		});
						getOpener().doSearch();
						closeDialog();
    		    	 }else{
    		    		 parent.$.messager.show({
                 			title:'提示',
                 			msg:result.errorMsg,
                 			timeout:3000,
                 			showType:'slide'
                 		});
    		    	 }
    		     }
    		});
		}else{
			parent.$.messager.show({
    			title:'提示',
    			msg:'更新失败，请将页面中的必填项输入完整！',
    			timeout:3000,
    			showType:'slide'
    		});
			return false;
		}
	});
	$("#closeBtn").bind("click",function(){
		var savedFiles = $("#vehicleSystem").val();
		var vehicleNum = $("#vehicleNum").val();
		if(savedFiles != ""){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"/EVIS/vehicle/deleteFiles",
				data:{
					"vehicleNum":vehicleNum,
					"type":"edit"
				},
				async:false,
				success:function(data){
				}
			});
		}
		closeDialog();
	});
	
	//将编辑信息区域隐藏
	$(".editDiv").each(function(){
		$(this).hide();
	});
	
	//编辑信息按钮的点击事件
	$(".editBtn").bind("click",function(){
		editBtnClick($(this));
	});
	
	//给上传按钮加一个点击事件
	$("#file7").bind("click",function(){
		var vehicleNum = $("#vehicleNum").val();
		var vehicleSystem = $("#vehicleSystem").val();
		//如果是重新加载，就先取消上传再加载
		if(vehicleSystem != ""){
			$.ajax({
				type:"post",
				dataType:"json",
				url:"/EVIS/vehicle/deleteFiles",
				data:{
					"vehicleNum":vehicleNum,
					"type":"edit"
				},
				async:false,
				success:function(data){
					uploadDiv.find(".progress").hide();
		            uploadDiv.find(".bar").css("width", 0);
		            uploadDiv.find(".progresspercent").hide().text(0 + "%");
				}
			});
			//重新加载，删除后台的照片后，将前台缓存信息也清空
			$("#vehicleSystem").val("");
			return true;
		}else{
			return true;
		}
	});
	
	var vehicleSystem = $("#vehicleSystem").val();
	if(vehicleSystem != null && vehicleSystem != ""){
		var uploadDiv = $("#uploadDiv");
		uploadDiv.find(".progress").show();
	    uploadDiv.find(".bar").css("width", 160);
	    uploadDiv.find(".progresspercent").show().text("100%");
	}
});
//初始化文件上传插件
function initFileUpload(){
	$("input[type=file]").fileupload({
	//	dataType:"json",
		autoUpload: true,
		type:"post",
		url:"/EVIS/vehicle/saveFiles",
		add:function(e,data){
			var vehicleNum = $("#vehicleNum").val();
			var url = "/EVIS/vehicle/saveFiles?vehicleNum=" + vehicleNum + "&type=edit";
			$(this).fileupload('option', 'url', url);
			data.submit();
		},
		done:function(e,data){
			var result = data.result;
			var fileNames = $("#vehicleSystem").val();
			if(fileNames != null && fileNames != ""){
				$("#vehicleSystem").val(fileNames + "," + result.successNames);
			}else{
				$("#vehicleSystem").val(result.successNames);
			}
			//修改按钮文本
			changeToReLoad();
		},
		fail:function(e,result){
			$.messager.alert('消息提示','上传失败！','warning');
		},
		progressall:function(e,data){
			var maxWidth = 160;
	        var percent = (data.loaded / data.total * 100).toFixed(2);
	        var progress = parseInt(data.loaded / data.total * maxWidth, 10);
	        var uploadDiv = $(e.target).parent().parent().parent();
	        uploadDiv.find(".progress").show();
	        uploadDiv.find(".bar").css("width", progress);
	        uploadDiv.find(".progresspercent").show().text(percent + "%");
		}
	});
}
function checkCombobox(){
	var nullFlag = false;
	$(".easyui-combobox").each(function(){
		var value = $(this).combobox("getValue");
		if(value == null || value == ""){
			var label = $(this).parent().prev().find("label");
			var labelName = $(label[0]).html();
			parent.$.messager.show({
       			title:'提示',
       			msg:labelName + '不能为空！',
       			timeout:3000,
       			showType:'slide'
       		});
			nullFlag = true;
			return false;
		}
	});
	if(nullFlag){
		return false;
	}
	return true;
}
//判断各个系统的编辑域是否都填写了
function checkEditDiv(){
	$(".editDiv").each(function(){
		var editDiv = $(this);
		var label = $(editDiv).parent().parent().prev().find("label")[0];
		var title = $(label).html();
		var a = $(editDiv).parent().parent().prev().find("a")[0];
		$(this).find("input").each(function(){
			var value = $(this).val().trim();
			if(value == ""){
				parent.$.messager.show({
					title:'消息',
					msg:title + "的内容需要填写完整！",
					timeout:3000,
					showType:'slide'
				});
				if($(a).html == "编辑"){
					editBtnClick($(a));
				}
				return false;
			}
		});
	});
}
function editBtnClick(obj){
	//按钮所在的tr
	var tr = obj.parent().parent();
	//编辑信息所在的div
	var div = $(tr).next().find("div");
	if($(div).is(":hidden")){
		$(div).show();
		obj.html("收起");
	}else{
		$(div).hide();
		obj.html("编辑");
	}
}
function closeDialog(isSave){
	var api = frameElement.api;
	api.close();
}
function getOpener(){
	var tab = parent.$('#maintabs').tabs('getSelected');
	var index = parent.$('#maintabs').tabs('getTabIndex',tab);
	var opener = parent.$("iframe[name!=lhgDialog]")[index].contentWindow;
	return opener;
}
function CheckFile(obj) {
	var array = new Array('gif', 'jpeg', 'png', 'jpg');  //可以上传的文件类型  
    if (obj.value == '') {
        $.messager.alert('消息提示','上传照片不能为空！','warning');
        return false;
    }else {
        var fileContentType = obj.value.match(/^(.*)(\.)(.{1,8})$/)[3]; //这个文件类型正则很有用：）  
        var isExists = false;
        for (var i in array) {
            if (fileContentType.toLowerCase() == array[i].toLowerCase()) {
                isExists = true;
                return true;
            }
        }
        if (isExists == false) {
            obj.value = null;
            $.messager.alert('消息提示','上传的文件类型只能是jpg、png、gif格式！','warning');
            return false;
        }
        return false;
    }
}
function cancleFile(obj){
	uploadDiv = $(obj).parent().parent();
//	var savedFiles = uploadDiv.find(".savedFiles").val();
	var vehicleNum = $("#vehicleNum").val();
	var savedFiles = $("#vehicleSystem").val();
	if(savedFiles == null || savedFiles == ""){
		parent.$.messager.show({
			title:'消息',
			msg:'尚未上传照片，无法取消上传！',
			timeout:3000,
			showType:'slide'
		});
		return;
	}else{
		$.ajax({
			type:"post",
			dataType:"json",
			url:"/EVIS/vehicle/deleteFiles",
			data:{
				"vehicleNum":vehicleNum,
				"type":"edit"
			},
			success:function(data){
				console.info(data)
				var success = data.success;
				if(success != null && success == "false"){
					parent.$.messager.show({
						title:'消息',
						msg:data.message,
						timeout:3000,
						showType:'slide'
					});
				}else {
					parent.$.messager.show({
						title:'消息',
						msg:'取消上传成功！',
						timeout:3000,
						showType:'slide'
					});
					uploadDiv.find(".progress").hide();
		            uploadDiv.find(".bar").css("width", 0);
		            uploadDiv.find(".progresspercent").hide().text(0 + "%");
		            //修改上传按钮的文字
		            changeToLoad(uploadDiv);
				}
				//修改上传的文件
				$("#vehicleSystem").val("");
			}
		});
	}
}
//将上传照片修改为重新上传
function changeToLoad(){
	var uploadBtnHtml = $("#uploadBtn").html();
	if(uploadBtnHtml.indexOf("重新上传") != -1){
		uploadBtnHtml = uploadBtnHtml.replace("重新上传","上传照片");
		$("#uploadBtn").html(uploadBtnHtml);
		initFileUpload();
	}
}
//将重新上传改为上传照片
function changeToReLoad(){
	var uploadBtnHtml = $("#uploadBtn").html();
	if(uploadBtnHtml.indexOf("上传照片") != -1){
		uploadBtnHtml = uploadBtnHtml.replace("上传照片","重新上传");
		$("#uploadBtn").html(uploadBtnHtml);
		initFileUpload();
	}
}