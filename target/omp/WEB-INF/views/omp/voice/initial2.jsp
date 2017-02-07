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
							<i class="fa fa-user fa-fw"></i>语音指令批量管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/voice/voiceManage/list2.shtml" />
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
											<td>姓名：</td>
											<td><input type="text" value="${name }" id="name" name="name"/></td>
											<td>座机号：</td>
											<td><input type="text" value="${zjNumber }" id="zjNumber" name="zjNumber"/></td>
											<td>身份证号：</td>
											<td><input type="text" value="${idCard }" id="idCard" name="idCard"/></td>
											<td>指令发送状态</td>
											<td>
												<select id="send_flag" name="send_flag">
													<option value="${send_flag }">--请选择--</option>		
													<option value="1">已发送</option>
													<option value="0">未发送</option>
												</select>
											</td>
										</tr>
										<tr>
											<td>区域：</td>
											<td>
												<select id="county" name="county">
													<option value="${county }">--请选择--</option>		
												</select>
											</td>
											<td>街道：</td>
											<td>
												<select id="street" name="street">
													<option value="${street }">--请选择--</option>		
												</select>
											</td>
											<td>社区：</td>
											<td>
												<select id="community" name="community">
													<option value="${community }">--请选择--</option>		
												</select>
											</td>
											<td>指令执行状态</td>
											<td>
												<select id="execute_flag" name="execute_flag">
													<option value="${execute_flag }">--请选择--</option>		
													<option value="null">执行成功</option>
													<option value="1">执行失败</option>
												</select>
											</td>
										</tr>
										<tr>
											
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
							 <%@ include file="/WEB-INF/views/omp/voice/list2.jsp"%> 
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
						var idCard = $("#idCard").val();
						var zjNumber = $("#zjNumber").val();
						var county = $("#county").val();
						var street = $("#street").val();
						var community = $("#community").val();
						$.post("<%=request.getContextPath() %>/voice/voiceManage/list2.shtml",
								{
									name:name,
									idCard:idCard,
									zjNumber:zjNumber,
									county:county,
									street:street,
									community:community
								},	
								function(data){
									$("#resultDiv").html(data);
								});
						
						  

			}
		
		$(document).ready(function() {
			initalizeSiderBar();
			//selectMenu("o_order1");
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