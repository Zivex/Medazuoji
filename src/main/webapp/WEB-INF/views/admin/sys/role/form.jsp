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
							<i class="fa fa-users fa-fw"></i>角色管理
							<small><a role="button" class="btn btn-default btn-sm" href='<c:url value="/admin/sys/role/initial.shtml"/>'>返回</a></small>
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div id="formDiv">
							<c:url var="saveForm" value="/admin/sys/role/save_or_update.shtml" />
							<form:form method="post" action='${saveForm}' class="form-horizontal" role="form">
								<form:hidden path="entity.id" />
								<form:hidden path="resourceIdString" id="resourceIds"/>
								
								<div class="form-group">
								   <label for="name" class="col-sm-2 control-label">角色名称: </label>
								   <div class="col-sm-4">
								      <form:input path="entity.name" class="form-control"  data-rule-required="true" data-rule-maxlength="80"  placeholder="角色名称"/>
								   </div>
								</div>
								<div class="form-group">
								   <label for="name" class="col-sm-2 control-label">授&ensp;权&ensp;码: </label>
								   <div class="col-sm-4">
								      <form:input path="entity.code" class="form-control" data-rule-required="true" data-rule-maxlength="40" placeholder="授权码"/>
								   </div>
								   <div class="col-sm-4 text-muted">
								   	&nbsp;授权码为字母如"ROLE_ADMIN"
								   </div>
								</div>
								<div class="form-group">
								   <label for="name" class="col-sm-2 control-label">角色描述: </label>
								   <div class="col-sm-4">
								      <form:input path="entity.description" class="form-control" data-rule-maxlength="160"  placeholder="角色描述"/>
								   </div>
								</div>
								<div class="form-group">
								   <label for="name" class="col-sm-2 control-label">可用资源: </label>
								   <div class="col-sm-4">
									   	<div id="treeDiv" class="dijitTree" style="width:300px; max-height:300px; ">
								  			<ul id="tree" class="ztree"></ul>
								  		</div>
								   </div>
								</div>
							</form:form>	
								<div class="form-group">
									 <label for="name" class="col-sm-2 control-label"></label>
									 <div class="col-sm-4">
									 	<button id="btn-submit"  class="btn btn-primary" onclick='submitForm()' >提 交</button>
									 	&emsp;
										<a role="button" class="btn btn-primary" href='<c:url value="/admin/sys/role/initial.shtml"/>'>取消</a>
									 </div>
								</div>
						</div>
						<div class="alert" style="display:none" id="msg-alert">
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


<SCRIPT type=text/javascript>

function submitForm(){
	var checkedItems =getCheckedItems();
	$("#resourceIds").val(checkedItems);
	$("#command").submit();
}

function getCheckedItems(){
	var inx=0;
	var checkedItems="";
	var treeObj = $.fn.zTree.getZTreeObj("tree");
	var nodes = treeObj.getCheckedNodes(true);
	nodes.forEach( function (item) {
		if(inx==0){
			checkedItems=checkedItems+item.id;
		}else{
			checkedItems=checkedItems+","+item.id;
		}
		inx++;
	});
	return checkedItems;
}

//pre-submit callback 
function showFormRequest(formData, jqForm, options) { 
	var suc = $("#command").valid();
	if (suc) {
		$("#displayDiv").mask("正在完成请求，请稍候！");
		return true;
	}
	return false;
} 
 
// post-submit callback 
function showFormResponse(responseText, statusText, xhr, $form)  { 
	$("#displayDiv").unmask();
	suc=responseText.success;
	type=responseText.type;
	$("#formDiv").hide();
	//
	$("#msg-alert").addClass('alert-' + type);
	$("#msg-alert").html(responseText.message);
	$("#msg-alert").show();
} 

//tree
var zNodes =${resourceTree};

var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
				pIdKey: "parentId"
			}
		}
	};

	$(document).ready(function() {
		initalizeSiderBar();
		selectMenu("m_adm_sys_role");
		initialFormValidate('command');
		initSaveForm();
		$.fn.zTree.init($("#tree"), setting, zNodes);
	});
</SCRIPT>



</body>
</html>