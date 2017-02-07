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
							<i class="fa fa-user fa-fw"></i>用户管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/admin/sys/user/list.shtml" />
							<form:form id="command" role="form" class="form-inline" action="${queryForm}" method="post">
								<input id="pageNo" name="currentPieceNum" type="hidden" value="1">
								<label for="entity.logonName" class="control-label">登录名:</label>
								<div class="form-group">
									<input id="entity.logonName" name="entity.logonName" placeholder="输入登录名" class="form-control" type="text" value="">
								</div>
								&nbsp;
								<label for="entity.name" class="control-label">姓名:</label>
								<div class="form-group">
									<input id="entity.name" name="entity.name" placeholder="输入姓名" class="form-control" type="text" value="">
								</div>
								&nbsp;
								<input type="submit" class="btn btn-primary" value="查 询 ">
								&nbsp;
								<a role="button" class="btn btn-primary" href='<c:url value="/admin/sys/user/form.shtml"/>'>添加用户</a>
							</form:form>
						</div>
						<hr>
						<div id="borad" style="display: none">
							<div id="message" class="alert"></div>
						</div>
						<div id="resultDiv">
							<%@ include file="/WEB-INF/views/admin/sys/user/list.jsp"%>
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
		/**
		 * 显示FORM对话框
		 **/
		function showFormModal(idstr) {
			var id = idstr;
			if (typeof (id) == "undefined" || null == id) {
				id = "";
			}
			var url = '<c:url value="/admin/sys/user/form.shtml"/>';
			if (id != "") {
				url = '<c:url value="/admin/sys/user/edit.shtml"/>?entity.id='
						+ id;
			}
			showDynamicModal(url);
		}

		/**
		 *显示用户详细信息
		 */
		function showDetails(id) {
			var url = '<c:url value="/admin/sys/user/detail.shtml"/>?entity.id='
					+ id;
			showDynamicModal(url);
		}

		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("m_adm_sys_user");
			initQueryForm();
		});
	</script>


</body>
</html>