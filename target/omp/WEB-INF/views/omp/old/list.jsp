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
		<c:if test="${DataTotalCount>0}">
			<!-- tools -->
			<!-- 		<div class="panel-heading"></div> -->
			<table class="table table-hover table-middle" role="grid">
				<thead>
					<tr class="active">
						<th width="5%"><input type="checkbox" onclick="check()"/></th>
						<th width="6%">姓名</th>
						<th width="10%" style="text-align: center;">区域</th>
						<th width="10%" style="text-align: center;">街道</th>
						<th width="12%" style="text-align: center;">社区</th>
						<th width="10%" style="text-align: center;">座机号</th>
						<th width="10%" style="text-align: center;">身份证号</th>
						<th width="10%" style="text-align: center;">联系电话</th>
						<th width="10%" style="text-align: center;">话机类型</th>
						<th width="8%" style="text-align: center;">个性类型</th>
						<th width="10%" style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="old" items="${dataList}" varStatus="sta">
						<tr>
							<td><input type="checkbox" class="ids" value="${old.id}"/></td>
							<td><a id="viewItem"  onclick="hxtoOldInfo(${old.id});">${old.name}</a></td>
							<td style="text-align: center;">${old.q}</td>
							<td style="text-align: center;">${old.j}</td>
							<td style="text-align: center;">${old.s}</td>
							<td style="text-align: center;">${old.zjnumber}</td>
							<td style="text-align: center;">${old.CERTIFICATES_NUMBER}</td>
							<td style="text-align: center;">${old.phone}</td>
							<td style="text-align: center;">${old.teltype}</td>
							<td style="text-align: center;">
								<c:if test="${old.isindividuation==1}">
								  <span style="color: green">是</span>
								</c:if>
								<c:if test="${old.isindividuation==0}">
								  <span style="color: red">否</span>
								</c:if>
							</td>
							
<%-- 							<td>${old.workername}</td> --%>
							<td style="text-align: center;">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a onclick="toupd(${old.id})">修改</a></li>
										<c:if test="${old.logonName ne 'admin'}">
											<li><a href="###" onclick="deleteUser(${old.id},this);">删除</a></li>
										</c:if>
										<li><a onclick="ompKeyModify(${old.id},${old.typeid} )">指令个性化</a></li>
										<%-- <li><a onclick="tocreOrder(${old.id})">生成指令</a></li> --%>
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
