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
							<i class="fa fa-user fa-fw"></i>区划管理
						</h1>
					</div>
					<div class="header-underline"></div>
					<div id="displayDiv">
						<div class="operatorDiv">
							<c:url var="queryForm" value="/admin/omp/ompRegion/list1.shtml" />
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
											
											<td>是否生成指令</td>
											<td>
												<select id="isSend" name="isSend">
													<option value="${isSend }">--请选择--</option>		
													<option value="0">未生成指令</option>
													<option value="1">已生成指令</option>
												</select>
											</td>
											<td>区县：</td>
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
										</tr>
										<tr>
											<td><button onclick="quety()">查询</button></td>
											<td><input type="reset"/></td>
											
<!-- 											<td><button>导出</button></td> -->
											<td><button onclick="createOrder()">批量生成指令</button></td>
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
							<%@ include file="/WEB-INF/views/omp/region/list1.jsp"%>
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
		* 导入execl信息
		**/
		function importInformation(){
			var url = '<c:url value="/old/Import/toImport.shtml"/>';
			showDynamicModal(url);
		}
		
		/**
		 *社区指令信息查询
		 */
		 function quety(){
			var isSend = $("#isSend").val();
			var name = $("#name").val();
			var creationTime = $("#creationTime").val();
			var county = $("#county").val();
			var street = $("#street").val();
			$.post("<%=request.getContextPath() %>/omp/region/listNew",
			     {
						creationTime:creationTime,
						name:name,
						county:county,
						street:street,
						isSend:isSend
					});
			
		}
		
		/**
		 *未审核信息展示
		 */
		 function DeAuditInformation(){
			var current=1;
			$.post("${pageContext.request.contextPath}/old/oldMatch/listForDeGenerate.shtml",
					{current:current},
					function(data){
				$("#resultDiv").html(data);
			})
		}
		
		function toupd(id){
			$.post("${pageContext.request.contextPath}/old/oldMatch/upd.shtml",
					{id:id},
					function(data){
				$("#resultDiv").html(data);
			})
		}
		

		//跳转批量生成指令页面
		function createOrder(){
			window.location.href="<%=request.getContextPath() %>/admin/omp/ompRegion/initial.shtml";
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