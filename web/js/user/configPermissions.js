$(document).ready(function(){
	
	//构建树
	var setting = {
			view: {
				showLine:true,
				expandSpeed:"fast",
				selectedMulti:false
			},
			data: {
				simpleData: {
					enable: true,
					idKey:"id",
					pIdKey:"pId",
					rootPid:"0"
				}
			},
			check: {
				enable: true,
				checkStyle:'checkbox',
				chkboxType: { "Y": "p", "N": "s" }
			},
			callback: {
				onCheck: zTreeOnCheck
			}
		};
 
	 var nodes = null;
	 $.ajax({   
		  async : false,   //设置为false。请求为同步请求
		  cache:false,   	//不设置缓存
		  type: 'post',   
		  dataType : "json",   
		  url: "/EVIS/role/getPermissionTree",//后台处理程序  
		  error: function () {//请求失败处理函数   
			  var errorMessage = "树结构数据请求失败";
			  parent.$.messager.show({
					title:'提示',
					msg:errorMessage,
					timeout:3000, 
					showType:'slide'
			  });
			  return false;
		  },   
		  success:function(data){ //请求成功后处理函数。 
			  nodes = data; //把后台封装好的简单Json格式赋给treeNodes 
		  }   
	 })
	//初始化新建和编辑通告界面的部门树
    var zTreeNew = $.fn.zTree.init($("#premissions"), setting, nodes);
    
     //如果有选中项，就将选中项在页面中选中
	 var checked = $("#checked").val();
	 if(checked != null && checked != ""){
  		  var arr = checked.split(",");
  		  var treeObj = $.fn.zTree.getZTreeObj("premissions");
   		  for(var i=0;i<arr.length;i++){
 		    var node =  treeObj.getNodeByParam('id',arr[i]);
 		    if(node != null){
 		    	treeObj.checkNode(node,true,true);
 		    	var parentNode = node.getParentNode();
 		    	if(parentNode != null && !parentNode.open){
 		    		treeObj.expandNode(parentNode);
 		    	}
 		    }
 	     }
	 }
	 
	 $("#saveBtn").bind("click",function(){
		 var checked = $("#checked").val();
		 if(checked == null || checked == ""){
			 parent.$.messager.show({
				title:'提示',
				msg:"权限选择不能为空！",
				timeout:3000, 
				showType:'slide'
			 });
			 return false;
		 }
		 $.ajax({
			type:"post",
			dataType:"json",
			url:"/EVIS/role/configPermissions",
			data:{
				"checked":checked,
				"roleId":$("#roleId").val()
			},
			success:function(data){
				var success = data.success;
				if(success == "true"){
					 parent.$.messager.show({
						title:'提示',
						msg:"配置权限成功",
						timeout:3000, 
						showType:'slide'
					 });
					 closeDialog();
				}else{
					 parent.$.messager.show({
						title:'提示',
						msg:data.errorMsg,
						timeout:3000, 
						showType:'slide'
					 });
				}
			}
		 });
	 });
	 
	 $("#closeBtn").bind("click",function(){
		 closeDialog();
	 });
});
function zTreeOnCheck(){
	var treeObj = $.fn.zTree.getZTreeObj("premissions");
	//获取所有选中节点
	var nodes = treeObj.getCheckedNodes(true);
	var ids = new Array();
	if(nodes != null && nodes.length > 0){
		for(var i = 0;i < nodes.length;i++){
			ids[i] = nodes[i].id;
		}
	}
	$("#checked").val(ids);
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