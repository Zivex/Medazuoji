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
							<i class="fa fa-user fa-fw"></i>话机服务类型管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/wordbook/wordbookmanage/MtypeList1.shtml" />
							<form:form id="command" role="form" class="form-inline" action="${queryForm}" method="post">
							<input id="pageNo" name="current" type="hidden" value="1">
<!-- 								&nbsp; -->
<!-- 									<a role="button" class="btn btn-primary" onclick="DeAuditInformation()">未生成指令</a> -->
<!-- 								&nbsp; -->
<!-- 								&nbsp; -->
<!-- 									<a role="button" class="btn btn-primary" onclick="DeAuditInformation()">*已生成指令*</a> -->
<!-- 								&nbsp; -->
<!-- 								&nbsp; -->
<!-- 									<a role="button" class="btn btn-primary" onclick="importInformation()">导入信息</a> -->
<!-- 								&nbsp; -->
<!-- 								&nbsp; -->
<!-- 									<a role="button" class="btn btn-primary" onclick="oldInformationDisplay()">老人信息展示</a> -->
<!-- 								&nbsp; -->
									<table class="table" >
										<tr>
											<%-- <td>社区名称：</td>
											<td><input type="text" value="${name }" id="name" name="name"/></td>
											 --%>
											
										</tr>
										<tr>
											<!-- <td>申请时间：</td>
											<td><input type="text" id="creationTime" name="creationTime" value="" /></td> -->
											
											<td>是否有效</td>
											<td>
												<select id="status" name="status">
													<option value="${status}">--请选择--</option>		
													<option value="0">失效</option>
													<option value="1">在用</option>
												</select>
											</td>
											<td>服务名称：</td>
											<td><input type="text" value="${serviceName }" id="serviceName" name="serviceName"/></td>
										</tr>
										<tr>
											<td><button onclick="quety()">查询</button></td>
											<td><input type="reset"/></td>
										</tr>
									</table>
							</form:form>
						</div>
						<hr>
						<div id="borad" style="display: none">
							<div id="message" class="alert"></div>
						</div>
						<!-- 展示列表 -->
						<div id="resultDiv">
							<%@ include file="/WEB-INF/views/omp/wordbook/MtypeList1.jsp"%>
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
		 *社区指令信息查询
		 */
		 function quety(){
			var status = $("#status").val();
			var serviceName = $("#serviceName").val();
			$.post("<%=request.getContextPath() %>/wordbook/wordbookmanage/MtypeList",
			     {
				        status:status,
				        serviceName:serviceName
					});
			
		}
		
		
		$(document).ready(function() {
			initalizeSiderBar();
			selectMenu("ompRegion");
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