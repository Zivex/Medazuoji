<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:if test="${not empty messages }">
	<div class="alert alert-warning alert-dismissable">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		${messages.message}
	</div>
</c:if>
<div class="panel panel-default">
	<form:form id="listForm" name="listForm" method="post"
		action='${queryForm }'>
		<%-- 		<form:hidden path="id" id="item_entity_id" /> --%>
		<%-- 		<form:hidden path="current" id="current" /> --%>
		<input id="item_entity_id" type="hidden" name="id" value="">
		<input id="currentPage" type="hidden" name="current" value="">
		<%-- 		<c:if test="${DataTotalCount>0}"> --%>
		<!-- tools -->
		<!-- 		<div class="panel-heading"></div> -->
		<table class="table table-hover table-middle" role="grid">
			<thead>
				<tr class="active">
					<th width="5%"><input type="checkbox" onclick="check()" /></th>
					<th width="20%">话机键位</th>
					<th width="25%">话机类型</th>
					<th width="25%">话机服务</th>
					<th width="25%">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="old" items="${dataList}" varStatus="sta">
					<tr>
						<td><input type="checkbox" class="ids" value="${old.id}" /></td>
						<%-- <td><a id="viewItem" href="###"
							onclick="showDetails(${old.id});">${old.k}</a></td> --%>
					    <td>${old.k}</td>
						<td>${old.phoneType}</td>
						<td>${old.serviceName}</td>
						<td>
							<div class="btn-group">
								<button type="button"
									class="btn btn-default btn-sm dropdown-toggle"
									data-toggle="dropdown">
									操作 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a data-toggle="modal" data-target="#myModal" onclick="toupd(${old.id})">修改话机服务名称</a></li>
								</ul>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<%-- 		</c:if> --%>
	</form:form>
	<div class="panel-footer">
		<table class="table table-pagination">
			<thead>
				<tr>
					<td align="left">共<span class="text-danger"><strong>${DataTotalCount}</strong></span>条记录（每页<span
						class="text-info"><strong>${PerPieceSize}</strong></span>条记录）&emsp;
					</td>
					<td align="right" height="28"><div id="result_page"></div></td>
				</tr>
			</thead>
		</table>
	</div>


	<div>

		<!-- 按钮触发模态框 -->
		<!-- <button class="btn btn-primary btn-lg" data-toggle="modal"
			data-target="#myModal">修改话机服务名称</button> -->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">修改话机服务名称</h4>
					</div>
					<div class="modal-body">
						<form>
							<input type="hidden" name="kid" id="kid" value="" />
								话机服务：<select name="serverName" id="serverName">
								<option>--请选择--</option>
								<c:forEach items="${phoneType }" var="type">
<%-- 									<c:if test=""> --%>
										<option>${type.serviceName }</option>
<%-- 									</c:if> --%>
								</c:forEach>								
							</select>
							<br>
							<br>
							添加新的类型：<input type="text" name="newServerName" id="newServerName" value="" />
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭
						</button>
						<button type="button" onclick="upd()" class="btn btn-primary">提交更改</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal -->
		</div>

	</div>


</div>


<!-- Script	-->
<SCRIPT type="text/javascript">
	$(document).ready(function() {
// 		initListForm();
// 		<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
// 		initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
// 		</c:if>

		$("#newServerName").change(function(){
			$("#serverName").val("1");
			
		});

	});
	
	function upd(){
		var kid = $("#kid").val();
		var serverName = $("#serverName").val();
		var newServerName = $("#newServerName").val();
		$.post("<%=request.getContextPath()%>/wordbook/wordbookmanage/updServerName.shtml",
				{kid:kid,serverName:serverName,newServerName:newServerName},
				function(data){
			alert(data);
		});
	}

	function toupd(id){
		$("#kid").val(id);
	}
	
	function check(){
		$(".ids").map(function() {
			$(this).attr("checked", !$(this).attr("checked"));
		});
	}
	
	
</SCRIPT>
