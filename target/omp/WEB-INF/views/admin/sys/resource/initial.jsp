<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/layout/adm_top.jsp"%>
<link rel=stylesheet type=text/css href="<c:url value="/resources/zTree/css/zTreeStyle/zTreeStyle.css"/>"  media="screen">
<script type="text/javascript"  src="<c:url value="/resources/zTree/js/jquery.ztree.all-3.5.min.js"/>"></script> 
<style type="text/css">
</style>

</head>
<body class="claro">
	<!-- header -->
	<%@ include file="/WEB-INF/views/layout/adm_head.jsp"%>
	<!-- /header -->

	<!-- container -->
	<div class="container-fluid">
		<div class="row">
			<!-- menu -->
			<%@ include file="/WEB-INF/views/menu/menu_adm.jsp"%>
			<!-- ./menu -->
			<!-- main -->
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main" role="main">
				<div>
					<div class="page-header">
						<h1>
							<i class="fa fa-database fa-fw"></i>资源管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="row">
						  <div class="col-md-3">
						  	<div class="panel panel-default">
							  <div class="panel-heading">资源列表</div>
							  <div id="treeDiv" class="panel-body dijitTree">
							  	<ul id="tree" class="ztree"></ul>
							  </div>
							</div>
						  </div>
						  <div class="col-md-9">
						  	<div class="panel panel-default">
							  <div class="panel-heading">操作面板</div>
							  <div class="panel-body" id="resourceOperateDiv">
							   	 &emsp;
							  </div>
							</div>
						  </div>
						</div>
					</div>
				</div>
			</div>
			<!-- ./main -->
		</div>
	</div>
	<!-- /container -->

	<!--footer-->
	<%@ include file="/WEB-INF/views/layout/adm_foot.jsp"%>
	<!--/footer-->
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addResNode();">添加资源</li>
		<li id="m_update" onclick="updateResNode();">编辑资源</li>
		<li id="m_del" onclick="removeResNode(true);">删除资源</li>
	</ul>
</div>
	<!-- Script	-->
	<script type="text/javascript">
	function loadDetail(id){
		var url='<c:url value="/admin/sys/resource/detail.shtml?entity.id="/>'+id;
		$("#resourceOperateDiv").mask("正在完成请求，请稍候！");
		$("#resourceOperateDiv").load(url, function(response, status, xhr) {
			  if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    alert(msg);
			    $("#resourceOperateDiv").unmask();
			  }
			});
	}	
	
	function loadSave(pid){
		var url='<c:url value="/admin/sys/resource/form.shtml?entity.parentId="/>'+pid;
		$("#resourceOperateDiv").mask("正在完成请求，请稍候！");
		$("#resourceOperateDiv").load(url, function(response, status, xhr) {
			  if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    alert(msg);
			    $("#resourceOperateDiv").unmask();
			  }
			});
	}
	
	function loadUpdate(id){
		var url='<c:url value="/admin/sys/resource/form.shtml?entity.id="/>'+id;
		$("#resourceOperateDiv").mask("正在完成请求，请稍候！");
		$("#resourceOperateDiv").load(url, function(response, status, xhr) {
			  if (status == "error") {
			    var msg = "Sorry but there was an error: ";
			    alert(msg);
			    $("#resourceOperateDiv").unmask();
			  }
			});
	}
	
	/**
	*
	*
	*item --item 
	*
	*/
	function deleteResourceX(treeNode){
		var itemId=treeNode.item.id;
		if(itemId==0){
				alert("根节点不能删除！");
				return;
			}else if(myModel.mayHaveChildren(treeNode.item)){
				alert("该节点包含子节点，暂不能删除！");
				return;
			} else{
				var sure = confirm("删除操作是不可逆的，确认删除该资源吗？");
				if (sure) {
					$.ajax({
						  type: "POST",
						  url: '<c:url value="/admin/sys/resource/delete.shtml"/>',
						  data: {"entity.id": itemId},
						  dataType: "json"
						}).done(function( responseText ) {
						  var suc=responseText.success;
						  var type=responseText.type;
						  var msg=responseText.message;
						  var html='<div><div class="alert alert-'+type+'">'+msg+'</div></div>';
						  $("#resourceOperateDiv").html(html);
						  if(suc){
							  myStore.remove(itemId);
						  }
					});
				}
			}
	}
	
	function deleteResource(treeNode){
		var itemId=treeNode.id;
		if(itemId==1){
				alert("根节点不能删除！");
				return;
			}else if(treeNode.isParent){
				alert("该节点包含子节点，暂不能删除！");
				return;
			} else{
				var sure = confirm("删除操作是不可逆的，确认删除该资源吗？");
				if (sure) {
					$.ajax({
						  type: "POST",
						  url: '<c:url value="/admin/sys/resource/delete.shtml"/>',
						  data: {"entity.id": itemId},
						  dataType: "json"
						}).done(function( responseText ) {
						  var suc=responseText.success;
						  var type=responseText.type;
						  var msg=responseText.message;
						  var html='<div><div class="alert alert-'+type+'">'+msg+'</div></div>';
						  $("#resourceOperateDiv").html(html);
						  if(suc){
							  zTree.removeNode(treeNode);
						  }
					});
				}
			}
	}
	
	function updateTree(){
		var date=new Date();
		var timestamp=date.getTime();
		var url='<c:url value="/admin/sys/resource/getResourceTree.shtml"/>';
		url=url+"?timestamp="+timestamp;
		$("#treeDiv").mask("正在刷新，请稍候！");
		$.getJSON(url,function(data,status){
			$("#treeDiv").unmask();
			$.fn.zTree.init($("#tree"), setting, data);
			expandRoot();
		});
	}
	
	function updateTreeXX(){
		var date=new Date();
		var timestamp=date.getTime();
		var url='<c:url value="/admin/sys/resource/tree.shtml"/>';
		url=url+"?timestamp="+timestamp;
		$("#tree").mask("正在刷新，请稍候！");
		$.get(url,function(data,status){
			$("#tree").html(data);
			$("#tree").unmask();
		});
	}
	
	function updateTreeX(){
		$("#tree").mask("正在刷新，请稍候！");
		var results = myStore.query({});
		//先删除全部数据--在添加时删除才会刷新
		//$.each(results,function(index,item){
			//myStore.remove(item.id);
		//});
		//然后加载最新数据
		var date=new Date();
		var timestamp=date.getTime();
		var url='<c:url value="/admin/sys/resource/getResourceTree.shtml"/>';
		url=url+"?timestamp="+timestamp;
		//get data
		$.getJSON(url, function(json) {
			$.each(json,function(index,item){
				if(item.id!=0){
					myStore.remove(item.id);
					myStore.add(item);
				}
			});
		});
		$("#tree").unmask();
	}
	//tree
	var zNodes =${jsonTree};
	
	var setting = {
			data: {
				simpleData: {
					enable: true,
					pIdKey: "parentId"
				}
			},
			callback: {
				onClick: treeNodeClick,
				onRightClick: menuClick
			}
		};
	
	function treeNodeClick(event, treeId, treeNode, clickFlag) {
		loadDetail(treeNode.id);
	}	

	function menuClick(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
	}

	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_update").hide();
			$("#m_del").hide();
		} else {
			$("#m_add").show();
			$("#m_update").show();
			$("#m_del").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

		$("body").bind("mousedown", onBodyMouseDown);
	}
	
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
	
	function addResNode() {
		hideRMenu();
		var node=zTree.getSelectedNodes()[0];
		loadSave(node.id);
	}
	
	function updateResNode() {
		hideRMenu();
		var node=zTree.getSelectedNodes()[0];
		loadUpdate(node.id);
	}
	
	function removeResNode() {
		hideRMenu();
		var node=zTree.getSelectedNodes()[0];
		deleteResource(node);
	}
	
	function expandRoot(){
		var root = zTree.getNodeByParam("id", 1, null);
		zTree.expandNode(root, true, false, false);
	}
	
	var zTree, rMenu;
	$(document).ready(function() {
		initalizeSiderBar();
		selectMenu("m_adm_sys_res");
		initQueryForm();
		$.fn.zTree.init($("#tree"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("tree");
		rMenu = $("#rMenu");
		expandRoot();
	});
	</script>


</body>
</html>