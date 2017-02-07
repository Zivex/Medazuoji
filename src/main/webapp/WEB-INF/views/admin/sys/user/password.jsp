<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url var="save" value="/admin/sys/user/change_password.shtml" />
<style>
.modal-content{
width: 100% !important;
}
.modal-dialog{
width: 600px !important;
margin-left:30% !important;
}
</style>				
<div class="modal-dialog">
	<div class="modal-content">
		<form:form method="post" id="passcommand"  name="passcommand" action='${save}' class="form-horizontal" role="form">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="passModalLabel">修改密码</h4>
		</div>
		<div class="modal-body">
			<c:choose>
				<c:when test="${not empty messages}">
					<div class="alert alert-warning">
						${messages.message}
					</div>
				</c:when>
				<c:otherwise>
					<div id="formDiv">
						<form:hidden path="entity.id"/>
						<form:hidden path="entity.logonName"/>
						<div class="form-group">
						   <label for="entity.name" class="col-sm-2 control-label">原&ensp;密&ensp;码: </label>
						   <div class="col-sm-8">
						      <form:password path="oldPassword" class="form-control" data-rule-required="true"  data-rule-maxlength="20" placeholder="原密码"/>
						   </div>
						</div>
						<div class="form-group">
						   <label for="entity.password" class="col-sm-2 control-label">新&ensp;密&ensp;码: </label>
						   <div class="col-sm-8">
						      <form:password path="entity.password" id="password" class="form-control" data-rule-required="true" data-rule-minlength="6" data-rule-maxlength="20" placeholder="新密码"/>
						   </div>
						</div>
						<div class="form-group">
						  <label for="description" class="col-sm-2 control-label">确认密码: </label>
						   <div class="col-sm-8">
						     <input type="password" id="repassword" class="form-control" data-rule-required="true" data-rule-equalTo="#password" placeholder="确认新密码"/>
						   </div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			
			<div class="alert" style="display:none" id="dialog-alert">
				${messages.message}
			</div>
			
			
		</div>
		<div class="modal-footer">
			<button id="btn-dismiss" type="button" class="btn btn-default" data-dismiss="modal">返回</button>
			<c:if test="${false eq posted}">
				<input id="btn-submit" type="submit" class="btn btn-primary" value="提 交">
			</c:if>
			<button id="btn-sure"  type="button" class="btn btn-success" onclick="closeDynamicModal();" style="display:none" >确定</button>
		</div>
		</form:form>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->

<!-- Script	-->
<SCRIPT type="text/javascript">
	$(function(){
		$("#btn-dismiss").click(function(){
			var url ='<c:url value="/admin/sys/user/change_userinfo_form.shtml"/>';	
			showDynamicModal(url);
		});
	});

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
		$('#passcommand').ajaxForm(options);
	}


	// pre-submit callback 
	function showRequest(formData, jqForm, options) {
		var suc = $("#passcommand").valid();
		if (suc) {
			$(".modal-dialog").mask("正在完成请求，请稍候！");
			return true;
		}
		return true;
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
 		/* $("#btn-dismiss").hide(); */
 		$("#btn-sure").show();
	}

	
	$(document).ready(function() {
		jQuery.validator.setDefaults({
			errorClass : "text-danger"
		});
		$('#passcommand').validate({
			highlight : function(element) {  
                $(element).closest('.form-group').addClass('has-error');  
            },  
            success : function(label) {  
                label.closest('.form-group').removeClass('has-error');  
                label.remove();  
            },  
            errorPlacement : function(error, element) {
				error.addClass("text-danger control-label");
				if (element.is(":radio")) {
					error.appendTo(element.parent().next().next());
				} else if (element.is(":checkbox")) {
					error.appendTo(element.next());
				} else {
					error.appendTo(element.parent());
				}
			}
		});
		initForm();
		
	});
</SCRIPT>
