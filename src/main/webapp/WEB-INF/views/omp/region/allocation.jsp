<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="panel panel-default">
	<form:form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/old/oldMatch/updete.shtml">
		<input type="hidden" id="id" name="id" value="${detaMap.id }"/>
		<table class="table table-hover table-middle">
			<thead>
				<tr class="active">
					<th width="10%"></th>
					<th width="10%">ID</th>
					<th width="20%">姓名</th>
					<th width="20%">配送范围</th>
					<th width="20%">服务项目</th>
					<!-- <th width="20%">操作</th> -->
				</tr>
			</thead>
			<c:forEach items="${dataList }" var="item">
				<tr>
						<td><input type="checkbox" value="${item.id}" class="id"/></td>
						<td>${item.SERVER_ID}</td>
						<td>${item.SERVER_NAME}</td>
						<td>${item.SCOPE_DELIVERY}</td>
						<td>${item.createdate}</td>
					</tr>
			</c:forEach>
			<tr>
				<td colspan="5"  align="center" style="width:120px; height: 21px;" valign="middle"><input type="button" onclick="allocation()" value="分配"/></td>
			</tr>
		</table>
	</form:form>
</div>
<!-- Script	-->
<SCRIPT type="text/javascript">

	$(document).ready(function(){
		
	});
	
	function allocation(){
		var ids = $("input[class='id']:checkbox:checked").map(function(){
			return $(this).val();
		}).get().join();
		alert(ids);
	}
		
</SCRIPT>