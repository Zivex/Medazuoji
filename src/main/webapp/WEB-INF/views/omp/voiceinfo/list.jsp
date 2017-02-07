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
	<form:form id="listForm" name="listForm" method="post" action='${queryForm }'>
		<input id="item_entity_id" type="hidden" name="id" value="">
		<input id="currentPage" type="hidden" name="current" value="">
		<c:if test="${DataTotalCount>0}">
		
			<table class="table table-hover table-middle" role="grid">
				<thead>
					<tr class="active">
					    <th width="10%">序号</th>
						<th width="10%">语音名</th>
						<th width="10%">语音创建时间</th>
						<th width="10%">语音备注</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				
				
				<tbody>
				<c:forEach items="${entities}" var="vo"  varStatus="i">
					 <tr>
					    <td>${i.index+1}</td>
					 	<td>${vo.n}</td>
					 	<td>${vo.t}</td>
					 	<td>${vo.r}</td>
					 	<td>
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a onclick="toupd(${old.id})">发送</a></li>
										<c:if test="${old.logonName ne 'admin'}">
											<li><a href="###" onclick="deleteUser(${old.id},this);">删除</a></li>
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
	$(document).ready(function() {
		initListForm();
		<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
		initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
		</c:if>
	});
</SCRIPT>
