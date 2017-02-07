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
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"
				role="main">
				<div>
					<div class="page-header">
						<h1>
							<i class="fa fa-user fa-fw"></i>区划管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm"
								value="/admin/omp/region/list.shtml" />
							<c:url var="queryForm" value="/admin/sys/user/list.shtml" />
							<form:form id="command" role="form" class="form-inline" action="${queryForm}" method="post">
								<input id="pageNo" name="currentPieceNum" type="hidden" value="1">
								&nbsp;
									<a role="button" class="btn btn-primary" onclick="getList()">区划</a>
									<a role="button" class="btn btn-primary" onclick="ExportCommunityOrder()">导出社区指令</a>
								&nbsp;
<!-- 								<label for="entity.logonName" class="control-label">标准编码:</label> -->
<!-- 								<div class="form-group"> -->
<!-- 									<input id="entity.logonName" name="entity.logonName" placeholder="输入标准编码" class="form-control" type="text" value=""> -->
<!-- 								</div> -->
								&nbsp;
<!-- 								<label for="entity.name" class="control-label">区域名称:</label> -->
<!-- 								<div class="form-group"> -->
<!-- 									<input id="entity.name" name="entity.name" placeholder="输入区域名称" class="form-control" type="text" value=""> -->
<!-- 								</div> -->
<!-- 								&nbsp; -->
<!-- 								<input type="submit" class="btn btn-primary" value="查 询 "> -->
<!-- 								&nbsp; -->
<%-- 								<a role="button" class="btn btn-primary" href='<c:url value="/admin/sys/user/form.shtml"/>'>添加区域</a> --%>
							</form:form>
						</div>
						<hr>
						<div id="borad" style="display: none">
							<div id="message" class="alert"></div>
						</div>
						<div id="resultDiv">
<%-- 						<%@ include file="/WEB-INF/views/omp/region/list.jsp"%> --%>
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
			selectMenu("ompRegion");
			initQueryForm();
		});
		function getList(){
			$.post("${pageContext.request.contextPath}/admin/omp/ompRegion/list.shtml",
					function(data){
					$("#resultDiv").html(data);
			});
		}
	</script>


</body>
</html>