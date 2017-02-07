<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div id="optDiv">
	<div class="alert" style="display:none" id="msg-alert">
	</div>
	<div id="formDiv">
		<c:url var="saveForm" value="/admin/sys/resource/save_or_update.shtml" />
		<form:form method="post" action='${saveForm}'>
			<form:hidden path="entity.id" />
			<form:hidden path="entity.parentId" />
			<table class="table table-bordered table-middle">
				<tr>
					<td>资源名称：</td>
					<td>
						<form:input path="entity.name" data-rule-required="true" data-rule-maxlength="80" size="30" class="form-control" />
					</td>
					<td>显示位置：</td>
					<td>
						<form:input path="entity.position" size="10" data-rule-digits="true" class="form-control" />
					</td>
				</tr>
				<tr>
					<td>资&ensp;源&ensp;值：</td>
					<td>
						<form:input path="entity.value" size="30" data-rule-required="true" data-rule-maxlength="120" class="form-control" />
					</td>
					<td>是否显示：</td>
					<td>
						<form:select path="entity.display" class="form-control">
							<form:option value="true">是</form:option>
							<form:option value="false">否</form:option>
						</form:select>
					</td>
				</tr>
				<tr>
					<td>资源类型：</td>
					<td>
						<form:select path="entity.type" class="form-control">
							<form:option value="URL">URL</form:option>
							<form:option value="MENU">MENU</form:option>
						</form:select>
					</td>
					<td>菜单标识：</td>
					<td>
						<form:input path="entity.menuId" size="30" class="form-control" />
					</td>
				</tr>
				<tr>
				<td>图&emsp;&emsp;标：</td>
					<td>
						<form:input path="entity.classId" size="30" data-rule-maxlength="80" class="form-control" />
					</td>
					<td>图标提示：</td>
					<td>
						fa fa-cubes fa-lg fa-fw<br>
						fa fa-caret-right fa-fw
					</td>
				</tr>
				<tr>
					<td>允许访问<br>角色：
					</td>
					<td colspan="3">
						<form:select path="entity.roles" multiple="true" data-rule-required="true" class="form-control">
							<form:options items="${roleList}" itemLabel="name" itemValue="id" />
						</form:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<div class="center-block">
							<button type="submit" class="btn btn-primary">提 交</button>
						</div>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</div>

<SCRIPT type=text/javascript>
	// pre-submit callback 
	function showFormRequest(formData, jqForm, options) {
		var suc = $("#command").valid();
		if (suc) {
			$("#formDiv").mask("正在完成请求，请稍候！");
			return true;
		}
		return false;
	}

	// post-submit callback 
	function showFormResponse(responseText, statusText, xhr, $form) {
		$("#optDiv").unmask();
		suc = responseText.success;
		type = responseText.type;
		$("#formDiv").hide();
		$("#msg-alert").addClass('alert-' + type);
		$("#msg-alert").html(responseText.message);
 		$("#msg-alert").show();
		updateTree();
	}

	$(document).ready(function() {
		initialFormValidate('command');
		initSaveForm();
	});
</SCRIPT>


