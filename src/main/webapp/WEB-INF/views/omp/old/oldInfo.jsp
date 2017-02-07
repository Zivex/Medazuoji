<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="panel panel-default">
	<form:form id="listForm" name="listForm" >
		<input type="hidden" id="id" name="id" value="${detaMap.id }"/>
		<table class="table table-hover table-middle">
			<tr>
				<td  width="25%">姓名：</td><td>${detaMap.name }</td>
			</tr>
			<tr>
				<td>区县：</td><td><select disabled="disabled" name="county" id="county">
					<c:forEach items="${Region.county }" var="county">
						<option value="${county.id }"<c:if test="${county.id==detaMap.household_county_id }">selected="selected"</c:if>>${county.name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>街道：</td><td><select disabled="disabled" name="street" id="street">
						<c:forEach items="${Region.street }" var="street">
							<option value="${street.id }"<c:if test="${street.id==detaMap.household_street_id }">selected="selected"</c:if>>${street.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>社区：</td><td><select disabled="disabled" name="community" id="community">
						<c:forEach items="${Region.community }" var="community">
							<option value="${community.id }"<c:if test="${community.id==detaMap.household_community_id }">selected="selected"</c:if>>${community.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>座机号：</td><td>${detaMap.zjnumber }</td>
			</tr>
			<tr>
				<td>手机号：</td><td>${detaMap.phone }</td>
			</tr>
			<tr>
				<td>居住地址：</td><td>${detaMap.address }</td>
			</tr>
			<tr>
				<td>紧急联系人：</td><td>${detaMap.emergencycontact }</td>
			</tr>
			<tr>
				<td>紧急联系人电话：</td><td>${detaMap.emergencycontacttle }</td>
			</tr>
			<tr>
				<td>话机类型：</td>
				<td><select id="teltype" disabled="disabled" name="teltype">
					<option value="居家型"<c:if test="${detaMap.teltype=='居家型' }">selected="selected"</c:if>>居家型</option>
					<option value="农行型"<c:if test="${detaMap.teltype=='农行型' }">selected="selected"</c:if>>农行型</option>
					<option value="失能型"<c:if test="${detaMap.teltype=='失能型' }">selected="selected"</c:if>>失能型</option>
				</select></td>
			</tr>
		</table>
		
		<table class="table table-hover table-middle" role="grid">
				<thead>
					<tr class="active">
						<th width="25%">话机键位</th>
						<th >号码</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach var="old" items="${arrayList}" varStatus="sta">
						<tr>
							<td>
							   <c:if test="${old.key=='M1'}">养老驿站</c:if>
							   <c:if test="${old.key=='M2'}">咨询投诉</c:if>
							   <c:if test="${old.key=='M3'}">老人用具</c:if>
							   <c:if test="${old.key=='M4'}">居家护理</c:if>
							   <c:if test="${old.key=='M5'}">家电服务</c:if>
							   <c:if test="${old.key=='M6'}">家政服务</c:if>
							   <c:if test="${old.key=='M7'}">幸福彩虹</c:if>
							   <c:if test="${old.key=='M8'}">老人餐桌</c:if>
							   <c:if test="${old.key=='M9'}">卫生站</c:if>
							   <c:if test="${old.key=='M10'}">居委会</c:if>
							   <c:if test="${old.key=='M11'}">社区广播</c:if>
							   <c:if test="${old.key=='M12'}">急救</c:if>
							   <c:if test="${old.key=='M13'}">中心号码</c:if>
							   <c:if test="${old.key=='M14'}">----</c:if>
							   <c:if test="${old.key=='M15'}">----</c:if>
							   <c:if test="${old.key=='M16'}">----</c:if>							
							</td>
							<td>${old.value}</td>
							
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
	</form:form>
</div>
