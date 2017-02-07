<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="save" value="/admin/sys/dic/save_or_update_sort.shtml" />
				
<div class="modal-dialog">
	<div class="modal-content">
		<form:form method="post" id="formcommand" name="formcommand" action='${save}' class="form-horizontal" role="form">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="myModalLabel">字典分类管理</h4>
		</div>
		<div class="modal-body">
			<div id="formDiv">
				<form:hidden path="id" />
				<div class="form-group">
				   <label for="name" class="col-sm-2 control-label">名称: </label>
				   <div class="col-sm-10">
				      <form:input path="name" class="form-control" data-rule-required="true" data-rule-maxlength="40" placeholder="名称"/>
				   </div>
				</div>
				<div class="form-group">
				   <label for="name" class="col-sm-2 control-label">CODE: </label>
				   <div class="col-sm-10">
				      <form:input path="code" class="form-control"  data-rule-required="true" data-rule-maxlength="40" placeholder="CODE"/>
				   </div>
				</div>
				<div class="form-group">
				   <label for="description" class="col-sm-2 control-label">描述: </label>
				   <div class="col-sm-10">
				     <form:input path="description" class="form-control"   data-rule-maxlength="50" placeholder="描述"/>
				   </div>
				</div>
			</div>
			
			<div class="alert" style="display:none" id="dialog-alert">
				${messages.message}
			</div>
		</div>
		<div class="modal-footer">
			<button id="btn-dismiss" type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
			<input id="btn-submit" type="submit" class="btn btn-primary" value="提 交">
			<button id="btn-sure"  type="button" class="btn btn-success" onclick="loadParent();" style="display:none" >确定</button>
		</div>
		</form:form>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->

<!-- Script	-->
<SCRIPT type="text/javascript">
	
	function loadParent() {
		/* parent.queryItems();
		closeDynamicModal(); */
		location.href="javascript:history.go(0);";
	}

	function initForm() {
		var options = {
			// target:        'command',   // target element(s) to be updated with server response 
			beforeSubmit : showRequest, // pre-submit callback 
			success : showResponse, // post-submit callback 
			// other available options: 
			//url:       url         // override for form's 'action' attribute 
			type : 'post', // 'get' or 'post', override for form's 'method' attribute 
			dataType : 'json' // 'xml', 'script', or 'json' (expected server response type) 
			//clearForm: true        // clear all form fields after successful submit 
			//resetForm: true        // reset the form after successful submit 
			// $.ajax options can be used here too, for example: 
			//timeout:   3000 
		};

		// bind to the form's submit event 
		$('#formcommand').ajaxForm(options);
	}

	// pre-submit callback 
	function showRequest(formData, jqForm, options) {
		var suc = $("#formcommand").valid();
		if (suc) {
			$(".modal-dialog").mask("正在完成请求，请稍候！");
			return true;
		}
		return false;
	}

	// post-submit callback 
	function showResponse(responseText, statusText, xhr, $form) {
		$(".modal-dialog").unmask();
		var suc = responseText.success;
		var type = responseText.type;
		$("#formDiv").hide();
		//
		$("#dialog-alert").addClass('alert-' + type);
		$("#dialog-alert").html(responseText.message);
 		$("#dialog-alert").show();
 		//
 		$("#btn-submit").hide();
 		$("#btn-dismiss").hide();
 		$("#btn-sure").show();
	}

	
	$(document).ready(function() {
		initialFormValidate('formcommand');
		initForm();
	});
</SCRIPT>
