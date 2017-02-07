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
							<i class="fa fa-book fa-fw"></i>字典项管理 <small> ${dictionarySort.name}</small> <a href='<c:url value="/admin/sys/dic/sort.shtml"/>' role="button" class="btn btn-sm btn-default">返回</a>
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="query" value="/admin/sys/dic/item_list.shtml" />
							<form:form class="form-inline" role="form" method="post" action='${query}'>
							<input type="hidden" name="type" value="2">
							<form:hidden path="sortId"/>
							<form:hidden path="currentPieceNum" id="pageNo"/>
								<label for="name" class="control-label">名称:</label>
								<div class="form-group">
									 <form:input class="form-control" path="name" placeholder="输入名称"  />
								</div>
								  &emsp;
								<input type="submit" class="btn btn-primary" value="查 询 ">
								  &emsp;
								<button type="button" class="btn btn-primary" onclick='showItemModal("");'>添加字典项</button>
							</form:form>
						</div>
						<hr>
						<div id="resultDiv">
							<%@ include file="/WEB-INF/views/admin/sys/dic/inc_item_list.jsp"%>
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
		
		function showItemModal(idstr){
			var id=idstr;
			if (typeof(id)=="undefined"||null==id) { 
				id="";
			}
			var url='<c:url value="/admin/sys/dic/form_item.shtml"/>?sortId=${command.sortId}&id='+id;
			showDynamicModal(url);
		}
		
	
		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("m_adm_sys_dic");
			initQueryForm();
		});
	</script>


</body>
</html>