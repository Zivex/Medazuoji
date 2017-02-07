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
							<i class="fa fa-users fa-fw"></i>角色管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/admin/sys/role/list.shtml" />
							<form:form id="command" role="form" class="form-inline" action="${queryForm}" method="post">
								<input id="pageNo" name="currentPieceNum" type="hidden" value="1">
								<label for="entity.name" class="control-label">角色名称:</label>
								<div class="form-group">
									<form:input class="form-control" path="entity.name" placeholder="输入角色名称"  />
								</div>
								  &emsp;
								<input type="submit" class="btn btn-primary" value="查 询 ">
								  &emsp;
								<a role="button" class="btn btn-primary" href='<c:url value="/admin/sys/role/form.shtml"/>'>添加角色</a>
							</form:form>
						</div>
						<hr>
						<div id="borad" style="display: none">
							<div id="message" class="alert"></div>
						</div>
						<div id="resultDiv">
							<%@ include file="/WEB-INF/views/admin/sys/role/list.jsp"%>
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
		
		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("m_adm_sys_role");
			initQueryForm();
		});
	</script>


</body>
</html>