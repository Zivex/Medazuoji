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
						<th width="10%"><input type="checkbox" onclick="check()"/></th>
						<th width="8%">姓名</th>
						<th width="8%">区域</th>
						<th width="8%">街道</th>
						<th width="18%">社区</th>
						<th width="10%">座机号</th>
						<th width="10%">推送状态</th>
						<th width="10%">执行状态</th>
						<th width="10%">操作</th>
<!-- 					<th width="10%">居住地址</th> -->
<!-- 					<th width="10%">录入员</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="old" items="${dataList}" varStatus="sta">
						<tr>
							<td><input type="checkbox" class="ids pzl" value="${old.id}"/></td>

							<td>
							    <a id="viewItem" href="###"  onclick="xiangqing(${old.id}),this">${old.name}</a>

							</td>


							<td>${old.county}</td>
							<td>${old.street}</td>
							<td>${old.community}</td>
							<td>${old.zjnumber}</td>
							<td>
								<c:if test="${old.send_flag==1}"><font color="green">已发送</font></c:if>
								<c:if test="${old.send_flag!=1}"><font color="red">待发送</font></c:if>
							</td>
							<td>
							<c:if test="${old.execute_flag==1}"><font color="green">成功</font></c:if>
								<c:if test="${old.execute_flag==0}"><font color="red">失败</font></c:if>
								<c:if test="${old.execute_flag==3}"><font color="blue">未执行</font></c:if>
							</td>
<%-- 							<td>${old.address}</td> --%>
<%-- 							<td>${old.workername}</td> --%>
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
	<style>
		*{margin: 0; padding: 0;}
		table{border-collapse: collapse;}
		th,td{ width: 200px; height: 40px; text-align: center; vertical-align: middle;}
		#box{width: 600px; height: 0px; position: absolute; left: 50%; top: 20%; margin-left: -300px; display: none; transition: all 2s; background:#f2f2f2; border-radius:4px; z-index:999; padding:20px;}
	</style>
	<!-- 弹出框  -->
	<div id="box" >
		<a style="font-size:20px;" id="close" href="javascript:;" title="关闭按钮"><button>关闭</button></a>
		<table cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
			<thead>
				<tr>
					<th width="8%">姓名</th>
					<th width="8%">区县</th>
					<th width="10%">街道</th>
					<th width="10%">社区</th>
					<th width="10%">座机号</th>
					<th width="10%">话机类型</th>
					<th width="10%">失败原因</th>
					<th width="15%">执行失败原因</th>
				</tr>
			</thead>
			
			<tbody>
			<c:forEach items="${olist}" var="olist">
				<tr>
					<td>${olist.NAME}</td>
					<td>${olist.q}</td>
					<td>${olist.j}</td>
					<td>${olist.s}</td>
					<td>${olist.ZJNUMBER}</td>
					<td>${olist.TELTYPE}</td>
					<td>${olist.execute_flag}</td>
					<td>${olist.errorMessage}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div> 	
	<script>
	    
		$("#close").click(function(){
			$("#box").hide();
		})
	</script>
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
	
	function check(){
		$(".ids").map(function() {
			$(this).attr("checked", !$(this).attr("checked"));
		});
	}
	
	function deleteUser(uid,tr){
		var sure=confirm("删除操作是不可逆的，确认删除该指令吗？");
		if(sure){
			$.post("<%=request.getContextPath() %>/order/orderManage/delect.shtml",{uid:uid},function(data){
				alert(data);
				location.reload();
				window.location.href="${pageContext.request.contextPath}/order/orderManage/initial.shtml?name=&idCard=&zjNumber=&county=&street=&community=&execute_flag=&send_flag=";
			});
	// 		$("#item_entity_id").val(uid);
	// 		$("#listForm").attr("action", '<c:url value="/old/oldMatch/delete.shtml"/>');
	// 		$("#listForm").submit();
		}
	}
	
	$(function () { 
		$("[data-toggle='popover']").popover();
	});
	
	function toupd(ids){
		var sure=confirm("确认要发送指令吗？");
		if(sure){
			$.post("<%=request.getContextPath() %>/order/orderManage/sendOrder.shtml",{ids:ids},function(data){
				alert(data);
				location.reload();
			});
		}
	}
</SCRIPT>
