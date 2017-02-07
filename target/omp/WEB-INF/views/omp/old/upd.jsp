<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="panel panel-default">
	<form:form id="listForm" name="listForm" method="post" action="${pageContext.request.contextPath }/old/oldMatch/updete.shtml">
		<input type="hidden" id="id" name="id" value="${detaMap.id }"/>
		<table>
			<tr>
				<td>姓名：</td><td><input type="text" id="name" name="name" value="${detaMap.name }"/></td>
			</tr>
			<tr>
				<td>区县：</td><td><select name="county" id="county">
					<c:forEach items="${Region.county }" var="county">
						<option value="${county.id }"<c:if test="${county.id==detaMap.household_county_id }">selected="selected"</c:if>>${county.name}</option>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>街道：</td><td><select name="street" id="street">
						<c:forEach items="${Region.street }" var="street">
							<option value="${street.id }"<c:if test="${street.id==detaMap.household_street_id }">selected="selected"</c:if>>${street.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>社区：</td><td><select name="community" id="community">
						<c:forEach items="${Region.community }" var="community">
							<option value="${community.id }"<c:if test="${community.id==detaMap.household_community_id }">selected="selected"</c:if>>${community.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>座机号：</td><td><input type="text" id="zjnumber" name="zjnumber" value="${detaMap.zjnumber }"/></td>
			</tr>
			<tr>
				<td>手机号：</td><td><input type="text" id="phone" name="phone" value="${detaMap.phone }"/></td>
			</tr>
			<tr>
				<td>居住地址：</td><td><input type="text" id="address" name="address" value="${detaMap.address }"/></td>
			</tr>
			<tr>
				<td>紧急联系人：</td><td><input type="text" id="emergencycontact" name="emergencycontact" value="${detaMap.emergencycontact }"/></td>
			</tr>
			<tr>
				<td>紧急联系人电话：</td><td><input type="text" id="emergencycontacttle" name="emergencycontacttle" value="${detaMap.emergencycontacttle }"/></td>
			</tr>
			<tr>
				<td>话机类型：</td>
				<td><select id="teltype" name="teltype">
					<option value="居家型"<c:if test="${detaMap.teltype=='居家型' }">selected="selected"</c:if>>居家型</option>
					<option value="农行型"<c:if test="${detaMap.teltype=='农行型' }">selected="selected"</c:if>>农行型</option>
					<option value="失能型"<c:if test="${detaMap.teltype=='失能型' }">selected="selected"</c:if>>失能型</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" onclick="submit()" value="修改"/></td>
			</tr>
		</table>
	</form:form>
</div>
<!-- Script	-->
<SCRIPT type="text/javascript">

	$(document).ready(function(){
		$("#county").change(function(){
			$("#community").empty();
			$("#community").append("<option>请选择</option>")
			var id = $("#county").val();
			$.post("${pageContext.request.contextPath }/old/oldMatch/getRegionById.shtml",
					{id:id},
					function(data){
						$("#street").empty();
						$("#street").append("<option>请选择</option>")
						for(var i = 0;i<data.length;i++){
							$("#street").append("<option value='"+data[i].ID+"'>"+data[i].NAME+"</option>")
						}
			
			});
		});
		$("#street").change(function(){
			
			var id = $("#county").val();
			$.post("${pageContext.request.contextPath }/old/oldMatch/getRegionById.shtml",
					{id:id},
					function(data){
						$("#community").empty();
						$("#community").append("<option>请选择</option>")
						for(var i = 0;i<data.length;i++){
							$("#community").append("<option value='"+data[i].ID+"'>"+data[i].NAME+"</option>")
						}
			});
		});
	});

	function submit(){
		$("#listForm").submit(function(){
			alert("asa");
			var street = $("#street").val();
			var community = $("#community").val();
			if(street=="" || street==undefined){
				return;
			}
			if(community=="" || community==undefined){
				return;
			}
		});
	}
</SCRIPT>