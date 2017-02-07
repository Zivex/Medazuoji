<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/layout/adm_top.jsp"%>
<link rel=stylesheet type=text/css href="<c:url value="/resources/dojo/dijit/themes/claro/claro.css"/>"  media="screen">
<link rel=stylesheet type=text/css href="<c:url value="/resources/dojo/cbtree/themes/claro/claro.css"/>" media="screen">
<script type="text/javascript"  src="<c:url value="/resources/dojo/dojo/dojo.js"/>"></script> 
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
						  <div class="col-md-4">
						  	<div class="panel panel-default">
							  <div class="panel-heading">资源列表</div>
							  <div class="panel-body">
							    <div id="tree" style="overridde: hidden" ></div>
							  </div>
							</div>
						  </div>
						  
<script>
	require([
    "dojo/ready", "dojo/_base/window", "dojo/store/Memory","dojo/store/Observable",
    "dijit/tree/ObjectStoreModel", "dijit/Tree"
], function(ready, win, Memory,Observable, ObjectStoreModel, Tree){

    // Create test store, adding the getChildren() method required by ObjectStoreModel
    myStore = new Memory({
        data:${jsonTree},
        getChildren: function(object){
            return this.query({parentId: object.id});
        }
    });
	
   // give store Observable interface so Tree can track updates
	myStore = new Observable(myStore);
 
    // Create the model
    myModel = new ObjectStoreModel({
        store: myStore,
        mayHaveChildren: function(object){
			// Normally the object would have some attribute (like a type) that would tell us if
			// it might have children.   But since our data is local we'll just check if there
			// actually are children or not.
			return this.store.getChildren(object).length > 0;
		},
        query: {id: 1}
    });

    // Create the Tree.   Note that all widget creation should be inside a dojo.ready().
    ready(function(){
         myTree = new Tree({
            model: myModel,
            getIconClass: function(/*dojo.store.Item*/ item, /*Boolean*/ opened){
                return (!item || this.model.mayHaveChildren(item)) ? (opened ? "dijitFolderOpened" : "dijitFolderClosed") : "dijitLeaf"
            }
        });
        myTree.on("click", function(object){
			loadDetail(object.id);
		}, true);
        myTree.placeAt("tree");
        myTree.startup();
    });
});
	require(["dijit/registry", "dijit/Menu", "dijit/MenuItem","dojo/aspect", "dojo/query!css2"], function(registry, Menu, MenuItem,aspect){
	    var menu = new Menu({
	        targetNodeIds: ["tree"],
	        selector: ".dijitTreeNode"
	    });
	    var item_add=new MenuItem({
	    	 label: "添加资源",
		     onClick: function(evt){
		            var node = this.getParent().currentTarget;
		            var tn = dijit.byNode(node);
		            loadSave(tn.item.id);
		            console.log("menu clicked for node id", tn.item.id);
		        }
		    });
	    var item_edit=new MenuItem({
	    	 label: "编辑资源",
		     onClick: function(evt){
		            var node = this.getParent().currentTarget;
		            var tn = dijit.byNode(node);
		            loadUpdate(tn.item.id);
		            console.log("menu clicked for node id", tn.item.id);
		        }
		    });
	    var item_del=new MenuItem({
	    	 label: "删除资源",
		     onClick: function(evt){
		            var node = this.getParent().currentTarget;
		            var treeNode = dijit.byNode(node);
		           // alert(treeNode);
		            //alert(treeNode.item);
		            //alert(myModel.mayHaveChildren(tn.item));
		            deleteResource(treeNode);
		            console.log("menu clicked for node id", treeNode.item.id);
		        }
		    });
	    menu.addChild(item_add);
	    menu.addChild(item_edit);
	    menu.addChild(item_del);
	   //	registry.byId("menu_item_del").set("disabled", true);
	});
	</script>
						  
						  <div class="col-md-8">
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
	function deleteResource(treeNode){
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
	
	function updateTree(){
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
	
		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("m_adm_sys_res");
			initQueryForm();
		});
	</script>


</body>
</html>