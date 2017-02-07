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
	
		<table class="table table-hover table-middle " width="100%">
			<thead>
				<tr class="active">
					<th width="30%">角色名称</th>
					<th width="20%">授权码</th>
					<th width="40%">描述</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${dataList}" varStatus="sta">
				<tr>
					<td>
						<a href="###" onclick="showDetails(${item.id});" >${item.name}</a>
					</td>
					<td>${item.code}</td>
					<td>${item.description}</td>
					<td>
						<div class="btn-group">
						  <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
						   	 操作 <span class="caret"></span>
						  </button>
						  <ul class="dropdown-menu" role="menu">
						    <li><a href='<c:url value="/admin/sys/role/form.shtml?menuId=${command.menuId}&entity.id=${item.id}"/>'>修改</a></li>
						    <li><a href="###" onclick="deleteRole(${item.id});">删除</a></li>
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
		<table class="table table-pagination" >
			<thead>
				<tr>
					<td align="left">共<span class="text-danger"><strong>${DataTotalCount}</strong></span>条记录（每页<span class="text-info"><strong>${PerPieceSize}</strong></span>条记录）&emsp;</td>
					<td align="right" height="28"><div id="result_page"></div></td>
				</tr>
			</thead>	
		</table>
	</div>
</div>

<span class="blank3"></span>


<!-- Script	-->
<SCRIPT type="text/javascript">


/**
* 删除角色
*/
function deleteRole(id){
	var sure=confirm("删除操作是不可逆的，确定要删除该角色吗？");
	if(sure){
		$("#item_entity_id").val(id);
		$("#listForm").attr("action", '<c:url value="/admin/sys/role/delete.shtml" />');
		$("#listForm").submit();
	}
}

/**
*显示详细信息
*/
function showDetails(id){
	var url='<c:url value="/admin/sys/role/detail.shtml"/>?entity.id='+id;
	showDynamicModal(url);
}

$(document).ready(function() {
	initListForm();
	<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
	initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
	</c:if>
});

</SCRIPT>
