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
	<form:form id="listForm" name="listForm" method="post" action=''>
		<form:hidden path="entity.id" id="item_entity_id" />
		<c:if test="${DataTotalCount>0}">
			<!-- tools -->
			<!-- 		<div class="panel-heading"></div> -->
			<table class="table table-hover table-middle" role="grid">
				<thead>
					<tr class="active">
						<th width="20%">登录名</th>
						<th width="20%">姓名</th>
						<th width="20%">用户类型</th>
						<th width="20%">所属区域</th>
						<th width="20%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${dataList}" varStatus="sta">
						<tr>
							<td><a id="viewItem" href="###" onclick="showDetails(${item.id});">${item.logonName}</a></td>
							<td>${item.name}</td>
							<td><c:if test="${item.userType==0}">管理员</c:if> <c:if test="${item.userType==1}">普通人员</c:if></td>
							<td></td>
							<td>
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a href='<c:url value="/admin/sys/user/edit.shtml?entity.id=${item.id}"/>'>修改</a></li>
										<c:if test="${item.logonName ne 'admin'}">
											<li><a href="###" onclick="deleteUser(${item.id});">删除</a></li>
											<li><a href="###" onclick="restePass(${item.id});">重置密码</a></li>
										</c:if>
									</ul>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form:form>
	<div class="panel-footer">
		<table class="table table-pagination">
			<thead>
				<tr>
					<td align="left">共<span class="text-danger"><strong>${DataTotalCount}</strong></span>条记录（每页<span class="text-info"><strong>${PerPieceSize}</strong></span>条记录）&emsp;
					</td>
					<td align="right" height="28"><div id="result_page"></div></td>
				</tr>
			</thead>
		</table>
	</div>
</div>



<!-- Script	-->
<SCRIPT type="text/javascript">


/**
* 删除用户
*/
function deleteUser(uid){
	var sure=confirm("删除操作是不可逆的，确认删除该用户吗？");
	if(sure){
		$("#item_entity_id").val(uid);
		$("#listForm").attr("action", '<c:url value="/admin/sys/user/delete.shtml"/>');
		$("#listForm").submit();
	}
}

/**
* 重置密码
*/
function restePass(uid){
	var sure=confirm("确认要重置密码吗？");
	if(sure){
		$("#item_entity_id").val(uid);
		$("#listForm").attr("action", '<c:url value="/admin/sys/user/reset_pass.shtml"/>');
		$("#listForm").submit();
	}
}


$(document).ready(function() {
	initListForm();
	<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
	initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
	</c:if>
});

</SCRIPT>
