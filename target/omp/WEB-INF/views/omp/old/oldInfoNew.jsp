<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript">
function back(){
	//返回到首页列表
	window.location.href="${pageContext.request.contextPath}/order/orderManage/initial.shtml?name=&idCard=&zjNumber=&county=&street=&community=&execute_flag=&send_flag=";
}
</script>
<div class="panel panel-default">
	<form:form id="listForm" name="listForm">
		<input type="hidden" id="id" name="id" value="${detaMap.id }"/>
		<a style="font-size:20px;" id="close" href="#" onclick="back()" title="关闭按钮">X</a>
		<table class="table table-hover table-middle"> 
			<tr>
				<td  width="25%">姓名：</td><td>${detaMap.NAME }</td>
			</tr>
			<tr>
				<td>区县：</td><td>${detaMap.q }</td>
			</tr>
			<tr>
				<td>街道：</td><td>${detaMap.j }</td>
			</tr>
			<tr>
				<td>社区：</td><td>${detaMap.s }</td>
			</tr>
			<tr>
				<td>座机号：</td><td>${detaMap.ZJNUMBER }</td>
			</tr>
			<tr>
				<td>话机类型：</td>
				<td><select id="teltype" disabled="disabled" name="teltype">
					<option value="居家型"<c:if test="${detaMap.TELTYPE=='居家型' }">selected="selected"</c:if>>居家型</option>
					<option value="农行型"<c:if test="${detaMap.TELTYPE=='农行型' }">selected="selected"</c:if>>农行型</option>
					<option value="失能型"<c:if test="${detaMap.TELTYPE=='失能型' }">selected="selected"</c:if>>失能型</option>
				</select></td>
			</tr>
			
			<tr>
				<td>推送状态：</td>
				<c:if test="${detaMap.execute_flag==1 }">
				    <td style="color: green">是</td>
				</c:if>
				<c:if test="${detaMap.execute_flag!=1 }">
				    <td style="color: red">否</td>
				</c:if>
				
			</tr>
			<tr>
				<td>失败原因：</td><td>${detaMap.errorMessage }</td>
			</tr>
			
		</table>
		
		<%-- <table class="table table-hover table-middle" role="grid">
				<thead>
					<tr class="active">
						<th width="25%">话机键位</th>
						<th >号码</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="old" items="${arrayList}" varStatus="sta">
						<tr>
							<td>${old.key}</td>
							<td>${old.value}</td>
							
						</tr>
					</c:forEach>
				</tbody>
				
			</table> --%>
	</form:form>
</div>
