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
	<c:if test="${DataTotalCount>0}">
<!-- 		<div class="panel-heading"></div> -->
		<!-- tools -->
		<table class="table table-hover table-middle " width="100%">
			<thead>
				<tr class="active">
					<th width="10%">ID</th>
					<th width="20%">名称</th>
					<th width="20%">描述</th>
					<th width="20%">CODE</th>
					<th width="10%">位置</th>
					<th width="10%">是否可用</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="item" items="${dataList}" varStatus="sta">
				<tr>
					<td>${item.id}</td>
					<td>${item.name}</td>
					<td><!--
					<c:if test="${item.sortId == '2' }">
					  如果是部门级别字典，描述项被占用
					 <c:if test="${item.description == '1'}">区</c:if>
					 <c:if test="${item.description == '2'}">街道</c:if>
					 <c:if test="${item.description == '3'}">社区</c:if>
					</c:if>
					<c:if test="${item.sortId != '2' }">
					 	${item.description}
					</c:if>
					 -->
					 ${item.description}
					</td>
					<td>${item.code}</td>
					<td>${item.position}</td>
					<td>${item.enabled==true?"是":"否"}</td>
					<td>
					<button type="button" onclick="showItemModal(${item.id});" class="btn btn-sm btn-default">修改</button>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
		</c:if>			        
	</form:form>
	<div class="panel-footer">
		<table class="table" >
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
	<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
	initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
	</c:if>
});

</SCRIPT>
