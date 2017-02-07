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
							<i class="fa fa-user fa-fw"></i>语音指令管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/voice/voiceManage/voiceQuery.shtml" />
							<form:form id="command" role="form" class="form-inline" action="${queryForm}" method="post">
							<input id="pageNo" name="current" type="hidden" value="1">
									<table class="table" >
										<tr>
											<td>姓名：</td>
											<td><input type="text" value="${v.name}" id="name" name="name"/></td>
										</tr>
										
										<tr>
											<td>预约时间：</td>
											<td><button onclick="quety()">查询</button></td>
											<td><input type="reset" value="重置"/></td>
										</tr>
									</table>
							</form:form>
						</div>
						<hr>
						<div id="borad" style="display: none">
							<div id="message" class="alert"></div>
						</div>
						<div id="resultDiv">
							<%@ include file="/WEB-INF/views/omp/voiceinfo/list.jsp"%>
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
		 *指令查询
		 */
		 function quety(){
					var name = $("#name").val();
					$.post("<%=request.getContextPath() %>/voice/voiceManage/voiceQuery.shtml",
							{
								name:name
							});
					
		}
		
		function openUpload(){
			var sure=confirm("确认发送指令吗？");
			if(sure){
				var ids = $(".ids:checkbox:checked").map(function(){
					return $(this).val();
				}).get().join();
				$("#userid").val(ids);
				$("#formf").submit();
			}
			
		}
		
		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("o_voice1");
			initQueryForm();
			$.post("<%=request.getContextPath() %>/old/oldMatch/getRegionById.shtml",function(data){
				for(var i = 0;i<data.length;i++){
					$("#county").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
				}				
			});
			$("#county").change(function(){
				var id = $("#county").val();
				$.post("<%=request.getContextPath() %>/old/oldMatch/getRegionById.shtml",{id:id},function(data){
					for(var i = 0;i<data.length;i++){
						$("#street").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
					}				
				});
			});
			
			$("#street").change(function(){
				var id = $("#street").val();
				$.post("<%=request.getContextPath() %>/old/oldMatch/getRegionById.shtml",{id:id},function(data){
					for(var i = 0;i<data.length;i++){
						$("#community").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
					}				
				});
			});
		});
		
	</script>


</body>
</html>