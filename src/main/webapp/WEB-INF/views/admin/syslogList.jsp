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
<%-- 		<form:hidden path="id" id="item_entity_id" /> --%>
<%-- 		<form:hidden path="current" id="current" /> --%>
		<input id="item_entity_id" type="hidden" name="id" value="">
		<input id="currentPage" type="hidden" name="current" value="">

			<!-- tools -->
			<!-- 		<div class="panel-heading"></div> -->
			<table class="table table-hover table-middle" role="grid">
				<thead>
					<tr class="active">
						<th width="8%">类型</th>
						<th  style="text-align: center;">内容</th>
						<th width="10%" style="text-align: center;">操作人</th>
						<th width="10%" style="text-align: center;">时间</th>
						<th width="10%" style="text-align: center;">操作状态</th>

					</tr>
				</thead>
					<%-- 	<c:if test="${DataTotalCount>0}"> --%>
				<tbody>
					<c:forEach var="old" items="${showlog}" varStatus="sta">
						<tr>
							<td style="text-align: center;">${old.TYPE}</td>
							<td style="text-align: center;">${old.CONTENT}</td>
							<td style="text-align: center;">${old.CREATEDATE}</td>
							
							<td style="text-align: center;">${old.CREATER}</td>
							<td style="text-align: center;color: green;"><c:if test="${old.STATE==1}">成功</c:if></td>
							<td style="text-align: center;color: red;"><c:if test="${old.STATE==0}">失败</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
				<%-- </c:if> --%>
			</table>
		
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
<div>
	<table class="table" id="table1">
	</table>
</div>

	
	
<!-- Script	-->
<SCRIPT type="text/javascript">
	
	/**
	* 删除用户
	*/
	function deleteUser(uid,tr){
		var sure=confirm("删除操作是不可逆的，确认删除该信息吗？");
		if(sure){
			location.href = "delete.shtml?id="+uid;
			alert("删除成功！");
			window.location.href="${pageContext.request.contextPath}/old/oldMatch/list.shtml?name=&idCard=&zjNumber=&county=&street=&community=&isGenerationOrder=&isindividuation=";
	// 		$("#item_entity_id").val(uid);
	// 		$("#listForm").attr("action", '<c:url value="/old/oldMatch/delete.shtml"/>');
	// 		$("#listForm").submit();
		}
	}
	
	$(document).ready(function() {
		initListForm();
		<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
		initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
		</c:if>
	});
	
	function check(){
		$(".ids").map(function() {
			$(this).attr("checked", !$(this).attr("checked"));
		});
	}
	
	function createOrder(){
		var ids = $(".ids:checkbox:checked").map(function(){
			return $(this).val();
		}).get().join();
		$.post("<%=request.getContextPath() %>/old/oldMatch/createOrder.shtml",{ids:ids},function(data){
			alert(data);
		});
	}

</SCRIPT>
