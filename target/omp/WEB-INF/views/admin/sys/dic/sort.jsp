<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="/WEB-INF/views/layout/adm_top.jsp"%>

<style type="text/css">
</style>

</head>
<body>
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
							<i class="fa fa-book fa-fw"></i>字典管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/admin/sys/dic/sort_list.shtml" />
							<form:form id="command" role="form" class="form-inline" action="${queryForm}" method="post">
							
							<input type="hidden" name="type" value="1">
							<form:hidden path="currentPieceNum" id="pageNo" value="1"/>
								<label for="name" class="control-label">名称:</label>
								<div class="form-group">
									 <form:input class="form-control" path="name" placeholder="输入名称"  />
								</div>
								  &emsp;
								<input type="submit" class="btn btn-primary" value="查 询 ">
								  &emsp;
								  <button type="button" class="btn btn-primary" onclick='showSortModal("");'>添加字典分类</button>
							</form:form>
						</div>
						<hr>
						<div id="resultDiv">
							<%@ include file="/WEB-INF/views/admin/sys/dic/inc_sort_list.jsp"%>
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
		
	function showSortModal(idstr){
		var id=idstr;
		if (typeof(id)=="undefined"||null==id) { 
			id="";
		}
		var url='<c:url value="/admin/sys/dic/form_sort.shtml"/>?id='+id;
		showDynamicModal(url);
	}
	 function  refresh(){
		location.href="javascript:history.go(0);";
	};
	function showSortModaldel(idstr){
		if(confirm("确定删除！！")){
			/* $.ajax({ 
				type:"POST", 
				url: "<c:url value='/admin/sys/dic/form_sort_del.shtml'/>", 
				async:false, 
				// data:sendstr, //也可以用注释的这方式来进行传值操作 
				data:{id:idstr}, //也可以用 $("#text1").val()的方式读取text1中的元素值 
				datatype:json,
				success:function(data){ 
					alert("成功");		
				},
			});  */
			 $.ajax({
				type:"POST",
				url: "<c:url value='/admin/sys/dic/form_sort_del.shtml'/>", 
				data:{id:idstr}, 		
				dataType:"json"
			}).done(function(data){
				$("#dialog-alert").html(data.message);
				$("#dialog-alert").show();
				$("#dialog-alert").delay(600).hide(0);
				setTimeout("refresh()",1000);

			}); 
			
		}
	} 
		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("m_adm_sys_dic");
			initQueryForm();
		});
	</script>


</body>
</html>