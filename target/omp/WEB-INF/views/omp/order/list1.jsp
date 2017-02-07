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

<div class="panel panel-default" id="content">
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
						<th width="10%" style="text-align: center;">区县</th>
						<th width="10%" style="text-align: center;">街道</th>
						<th width="10%" style="text-align: center;">社区</th>
						<th width="10%" style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="quhua" items="${dataList}" varStatus="sta">
						<tr>
							<td><input type="checkbox" class="ids" value="${quhua.id}"/></td>
							<td style="text-align: center;">${quhua.city}</td>
							<td style="text-align: center;">${quhua.street}</td>
							<td style="text-align: center;">${quhua.shequ}</td>
							<td style="text-align: center;">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a onclick="addZhiling(${quhua.id})">发送指令</a>
										
										</li>
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

<!--一开始时隐藏 -->
<div id="div3" style="float: left;clear:both; widows: 100%;height: 500px; display: none" >
	<hr width="1094px">
	<br>
	<div style="width: 48%; float: left;">
		<div style="float: left;margin-top: 5px;">
			老人话机类型：
			<input class="phoneType" type="radio" name="phoneType" value="1" />居家型
			<input class="phoneType" type="radio" name="phoneType" value="2" />失能型
			<input class="phoneType" type="radio" name="phoneType" value="3" />农商型
		</div>
		<div style="float: left;margin-left: 20px;">
			<button class="btn btn-primary" onclick="save()">保存</button>
		</div>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>">
			    <input type="hidden" name="shequId" id="shequId" value=""/>
				<table class="table table-bordered" id="table3">
				</table>
				<br>
			</form>
		</div>
	</div>
	<div style="width: 48%;float: right;">
		<div>
			<div style="float: left;">
				<label for="entity.logonName" class="control-label" style=" line-height:35px;">商&nbsp;&nbsp;户&nbsp;名&nbsp;&nbsp;称:&nbsp;&nbsp;</label>
			</div>
			<div style="float: left;margin-left: 5px;">
				<input id="showName" name="showName" placeholder="输入商户名称"
				class="form-control" type="text" value="">
 			</div>
			<div style="float: left;margin-left: 5px;clear:both;">
				<label for="entity.logonName" class="control-label" style=" line-height:35px;">服务商类型:</label>
			</div>
			<div style="float: left;margin-top: 5px;margin-left: 15px;">
				<select id="select" class="form-control">
					<option value="">请选择</option>
				</select>
			</div>
 			<div style="float: left;margin-left: 15px;">
				<input type="button" onclick="query()" class="btn btn-primary" value="查询">
			</div>
		</div><br>
		<div>
			<form action="">
				<table class="table table-bordered" id="table4">
					
				</table>
			</form>
		</div>
	</div>
	
</div>

<div>
	<table class="table" id="table1">
	</table>
</div>

	
	
<!-- Script	-->
<SCRIPT type="text/javascript">


	function mapping() {
		$("#table2").empty();
		$(".ids:checkbox:checked").map(function(){
					var id = $(this).val();
					var name = $(this).parent().next().text();
					$("#table2").append(
							"<tr><td><input class='tb2' type='hidden' value='"+id+"'/></td><td>"
									+ name + "</td></tr>");
					$(this).parent().parent().remove();
				});
	}
	
	function query() {
		var showName = $("#showName").val();
		var serverType = $("#select").val();
		$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/allocation.shtml",
				{showName:showName,serverType:serverType},
				function(data){
				$("#table4").empty();
				$("#table4").append("<tr><td><input type='radio'/></td><td>服务商名称</td><td>服务类型</td><td>服务商电话</td></tr>");
			for(var i = 0;i<data.length;i++){
				$("#table4").append("<tr><td><input class='spId' name='serverProvider' type='radio' value='"+data[i].ID+"'/></td><td>"+data[i].SERVER_NAME+"</td><td>"+data[i].SERVER_TYPE+"</td><td>"+data[i].SERVER_TEL+"</td></tr>");
			}
		});
	}

	$(".phoneType").change(function(){
		var ptype = $(this).val();
		$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/getOrder.shtml",{ptype:ptype},function(data){
				$("#table3").empty();
				$("#table3").append("<tr><td>键位</td><td>话机类型</td><td>服务类型</td><td>服务商名称</td><td>服务商电话</td><td><input type='button' onclick='addprovider()' value='添加'/></td></tr>");
			for(var i=0;i<data.length;i++){
				if(data[i].sname==null){
					data[i].sname = " ";
				}

				$("#table3").append("<tr><td>"+data[i].KEY+"</td><td>"+data[i].ptype+"</td><td value='"+data[i].kid+"'>"+data[i].sname+"</td><td></td><td><input type='text' style='border:0px;' id='"+data[i].KEY+"'/></td><td><input type='radio' class='addprov' name='addprov'/></td></tr>");
			}
		});
	});
	//取得服务商类型
	$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/getServiceType.shtml",function(data){
		$("#select").empty();
		$("#select").append("<option value=''>请选择111</option>")
		for(var i = 0;i<data.length;i++){
			$("#select").append("<option value='"+data[i].serviceName+"'>"+data[i].serviceName+"</option>")
		}
	});

	function showDetails(oid){
		debugger;
		$.post("<%=request.getContextPath() %>/old/oldMatch/getOldKeyPointMessage.shtml",{oid:oid},function(data){
			console.info(data);
			for(var i =0;i<data.length;i++){
				$("#table1").append("<tr><td>"+data[i].key+"</td><td><input name='name' readonly='readonly' value='"+data[i].number+"'/></td></tr>");
			}
		});
	}
	


	
	
	function deleteUser(uid,tr){
		var sure=confirm("删除操作是不可逆的，确认删除该信息吗？");
		if(sure){
			location.href = "delete.shtml?id="+uid;
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
	
	
	//发送指令
	function createOrder(){
		var ids = $(".ids:checkbox:checked").map(function(){
			return $(this).val();
		}).get().join();
		$.post("<%=request.getContextPath() %>/old/oldMatch/createOrder.shtml",{ids:ids},function(data){
			alert(data);
		});
	}
	
	
	
	
</SCRIPT>
