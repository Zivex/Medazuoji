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
							<i class="fa fa-user fa-fw"></i>用户管理&nbsp;<a href='<c:url value="/admin/sys/user/initial.shtml"/>' role="button" class="btn btn-sm btn-default">返回</a>
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div id="formDiv">
							<c:url var="save" value="/admin/sys/user/update.shtml" />
							<form:form method="post" action='${save}' class="form-horizontal" role="form">
								<form:hidden path="entity.id" />
								<div class="form-group">
									<label for="entity.name" class="col-md-2 control-label">姓&emsp;&emsp;名: </label>
									<div class="col-md-4">
										<form:input path="entity.name" class="form-control" data-rule-required="true" data-rule-maxlength="100" placeholder="姓名" />
									</div>
								</div>
								<div class="form-group">
									<label for="entity.logonName" class="col-md-2 control-label">登&ensp;录&ensp;名: </label>
									<div class="col-md-4">
										<p class="form-control-static">${command.entity.logonName}</p>
									</div>
								</div>
								<div class="form-group">
									<label for="entity.roles" class="col-md-2 control-label">分配角色: </label>
									<div class="col-md-4">
										<form:select path="entity.roles" multiple="true" class="col-md-4 form-control">
											<form:options items="${roleList}" itemLabel="name" itemValue="id" />
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<label for="entity.userType" class="col-md-2 control-label">用户类型: </label>
									<div class="col-md-4"></div>
								</div>
							</form:form>
							<div class="form-group">
								<label for="name" class="col-md-2 control-label"></label>
								<div class="col-md-4">
									<button id="btn-submit" class="btn btn-primary" onclick='submitForm()'>提 交</button>
									&emsp; <a role="button" class="btn btn-default" href='<c:url value="/admin/sys/user/initial.shtml"/>'>取消</a>
								</div>
							</div>
						</div>
						<div class="alert" style="display: none" id="msg-alert"></div>
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
		function submitForm() {
			$("#command").submit();
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
		function showFormResponse(responseText, statusText, xhr, $form) {
			$("#displayDiv").unmask();
			suc = responseText.success;
			type = responseText.type;
			$("#formDiv").hide();
			//
			$("#msg-alert").addClass('alert-' + type);
			$("#msg-alert").html(responseText.message);
			$("#msg-alert").show();
		}

		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("m_adm_sys_user");
			initialFormValidate('command');
			initSaveForm();
		});
	</script>


</body>
</html>