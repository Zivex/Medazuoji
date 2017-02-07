<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<div class="alert" style="display:none" id="dialog-alert">
		${message}
	</div>

<div class="panel panel-default">
	<form:form id="listForm" name="listForm" method="post" action=''>
	<c:if test="${DataTotalCount>0}">
<!-- 		<div class="panel-heading"></div> -->
		<!-- tools -->
		<table class="table table-hover table-middle " width="100%">
			<thead>
				<tr class="active">
					<th width="10%">ID</th>
					<th width="20%">名称</th>
					<th width="20%">CODE</th>
					<th width="30%">描述</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${dataList}" varStatus="sta">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td>${item.code}</td>
					<td>${item.description}</td>
					<td>
					<div class="btn-group">
					  <button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
					   	 操作 <span class="caret"></span>
					  </button>
					  <ul class="dropdown-menu" role="menu">
					    <li><a href='<c:url value="/admin/sys/dic/item.shtml?sortId=${item.id}"/>'>管理字典项</a></li>
					    <li><a href="###" onclick="showSortModal(${item.id});">修改</a></li>
					    <li><a href="###" onclick="showSortModaldel(${item.id});">删除</a></li>
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
		<table class="table">
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


$(document).ready(function() {
	initListForm();
	<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
	initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
	</c:if>
});

</SCRIPT>
