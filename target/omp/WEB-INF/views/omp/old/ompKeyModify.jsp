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
						<th width="25%">话机键位</th>
						<th >号码</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="old" items="${detaMap}" varStatus="sta">
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
							<td><input type="text" id="${old.key}" name="nametext" value="${old.value}"/></td>
							
						</tr>
					</c:forEach>
				</tbody>
				
				<tr>
				<input type="hidden" id="useridInput"  value="${hxUserID}"/>
				<td colspan="2"><input type="button" onclick="hxsubmit()" value="修改"/></td>
			</tr>
			</table>
		
	</form:form>
		
</div>
<div>
	
	<SCRIPT type="text/javascript">
	function hxsubmit(){
		var jsonObj = $("input[name='nametext']");
		var isOK = 1;
		var json = "[{";
		for(var i=0;i<jsonObj.length;i++){
			var myvalue = jsonObj.eq(i).attr("value");
			var myid = jsonObj.eq(i).attr("id");
			json = json + "\"" + myid + "\"" ;
			json = json + ":\"" + myvalue + "\"" ;
			
			if (myvalue == ""  || myvalue==undefined){
				isOK = 0;
				break;
			}
			if (i+1 < jsonObj.length){
				json = json + ",";
			}
		};
		json = json + "}]";
		if (isOK == 0){
			alert("请填写完整数据后提交");
			return;
		}
		var id = $("#useridInput").val();
		$.post("${pageContext.request.contextPath }/old/oldMatch/uploadOldIndividuation.shtml",
				{id:id,json:json},
			function(data){
				alert(data);	
		});
	}
</SCRIPT>
</div>

	
