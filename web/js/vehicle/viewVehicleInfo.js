var viewer;
$(document).ready(function(){
	$("#closeBtn").bind("click",function(){
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
	
	//处理图片预览
	var vehicleSystem = $("#vehicleSystem").val();
	var vehicleNum = $("#vehicleNum").html();
	if(vehicleSystem != ""){
		var lis = "";
		var photos = vehicleSystem.split(",");
		var url = "/EVIS/vehicle/getVehicleSystem?vehicleNum=" + vehicleNum;
		if(photos != null && photos.length > 0){
			for(var i = 0;i < photos.length;i++){
				url += "&photoUrl=" + photos[i];
				lis += "<li><img style='width:40px;height:20px' data-original='" + url + "' " +
						"src='" + url + "' alt='整机系统'></li>";
				url = "/EVIS/vehicle/getVehicleSystem?vehicleNum=" + vehicleNum;
			}
		}
		$("#dowebok").html(lis);
	}
	
	parent.viewer = new Viewer(document.getElementById('dowebok'), {
		url: 'data-original',
		zIndex:9999
	});
	
	$(".viewArea").each(function(){
		var html = $(this).html();
		if(html == null || html == ""){
			$(this).html("&nbsp");
		}
	});
	
	$("#rearviewMirror,#scoreBoard,#DCType").each(function(){
		var value = $(this).html();
		if(value == "1"){
			$(this).html("有");
		}else{
			$(this).html("无");
		}
	});
});
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
		obj.html("展开");
	}
}
function closeDialog(){
	var api = frameElement.api;
	api.close();
}
function getOpener(){
	var tab = parent.$('#maintabs').tabs('getSelected');
	var index = parent.$('#maintabs').tabs('getTabIndex',tab);
	var opener = parent.$("iframe[name!=lhgDialog]")[index].contentWindow;
	return opener;
}