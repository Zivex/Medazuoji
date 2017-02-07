<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
						<th width="10%" style="text-align: center;">是否添加指令</th>
						<!-- <th width="10%" style="text-align: center;">创建时间</th> -->
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
							 <c:if test="${quhua.jjType!=null||quhua.snType!=null||quhua.nsType!=null}">
							      <span style="color: green">是</span>
							 </c:if>
							 <c:if test="${quhua.jjType==null&&quhua.snType==null&&quhua.nsType==null}">
							      <span style="color: red">否</span>
							 </c:if>    
							</td>
							<%-- <td style="text-align: center;">${quhua.time}</td> --%>
							<td style="text-align: center;">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<%-- <li><a onclick="addZhiling(${quhua.id})">新增指令</a></li> --%>
										<c:if test="${old.logonName ne 'admin'}">
											<li><a href="###" onclick="edit(${quhua.id},this);">修改指令</a></li>
										</c:if>
										<c:if test="${old.logonName ne 'admin'}">
											<li><a href="###" onclick="look(${quhua.id},this);">查看指令</a></li>
										</c:if>
										
										<li><a onclick="daochu(${quhua.id})">导出指令</a></li>
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
<div id="div2" style="float: left;clear:both; widows: 100%;height: 500px; display: none" >
	<hr width="1094px">
	<br>
	<div style="width: 48%; float: left;" id="div4">
		<div style="float: left;margin-top: 5px;">
			老人话机类型：
			<input class="phoneType" type="radio" name="phoneType" value="1" />居家型
			<input class="phoneType" type="radio" name="phoneType" value="2" />失能型
			<input class="phoneType" type="radio" name="phoneType" value="3" />农商型
		</div>
		<div style="float: left;margin-left: 20px;">
			<button class="btn btn-primary" id="save" onclick="save()">保存</button>
			<button class="btn btn-primary" id="imp" onclick="imp()">导出</button>
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
	
	<div style="width: 48%;float: right;" id="div3">
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
	
    //获取服务商信息
	$(".phoneType").change(function(){
		var ptype = $(this).val();
		var shequId=document.getElementById("shequId").value; 
		$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/getOrder.shtml",{ptype:ptype,shequId:shequId},function(data){
				$("#table3").empty();
				$("#table3").append("<tr><td>键位</td><td>话机类型</td><td>服务类型</td><td>服务商名称</td><td>服务商电话</td><td><input type='button' onclick='addprovider()' value='添加'/></td></tr>");
			for(var i=0;i<data.length;i++){
				if(data[i].sname==null){
					data[i].sname = " ";
				}
				$("#table3").append("<tr><td>"+data[i].KEY+"</td><td>"+data[i].ptype+"</td><td value='"+data[i].kid+"'>"+data[i].sname+"</td><td></td><td><input type='text'  value='"+data[i].LinkNbr+"' style='border:0px;' id='"+data[i].KEY+"'/></td><td><input type='radio' class='addprov' name='addprov'/></td></tr>");
			}
		});
	});
	
	$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/getServiceType.shtml",function(data){
		$("#select").empty();
		$("#select").append("<option value=''>请选择</option>")
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
	
	//新增修改指令
	function addZhiling(Id){
		document.getElementById("shequId").value=Id;
		document.getElementById('div2').style.display='block';
		document.getElementById('content').style.display='none';
		document.getElementById('imp').style.display='none';
	}
	
	//修改指令
	function edit(Id){
		
		document.getElementById("shequId").value=Id;
		document.getElementById('div2').style.display='block';
		document.getElementById('content').style.display='none';
		document.getElementById('imp').style.display='none';
		
	}
	//查看指令
	function look(Id){

		document.getElementById("shequId").value=Id;
		document.getElementById('div2').style.display='block';
		document.getElementById('div3').style.display='none';
		document.getElementById('content').style.display='none';
		document.getElementById('save').style.display='none';
		document.getElementById('imp').style.display='none';
		document.getElementById('div4').style.width='1300px';
	}
    
	//导出指令
	function daochu(Id){
        
		document.getElementById("shequId").value=Id;
		document.getElementById('div2').style.display='block';
		document.getElementById('div3').style.display='none';
		document.getElementById('content').style.display='none';
		document.getElementById('save').style.display='none';
		document.getElementById('div4').style.width='1300px';
		
	}

	
	//新增服务商
	function addprovider() {
		var id = $(".spId:checked").val();
		var name = $(".spId:checked").parent().next().text();
		var number = $(".spId:checked").parent().next().next().next().text();
		$(".addprov:checked").val(id);
		$(".addprov:checked").parent().prev().prev().text(name);
		$(".addprov:checked").parent().prev().find("input").first().val(number);
	}
	
	//保存社区指令
	function save(){
		
	    var shequId=document.getElementById("shequId").value;    
		var communityids = $(".tb2").map(function(){
			return $(this).val();
		}).get().join();
		
		var ptype = $(".phoneType:checked").val();
		var trnumber = $("#table3 tr").length;
		$("#table3").find("tr").eq(1);
		var json = "[{";
		for(var i=1;i<trnumber;i++){
			var key = $("#table3").find("tr").eq(i).find("td").eq(0).text();
			
			var kid = $("#table3").find("tr").eq(i).find("td").eq(2).attr("value");
			//服务商名称
			var showname = $("#table3").find("tr").eq(i).find("td").eq(3).text();
			var showid = $("#table3").find("tr").eq(i).find("td").eq(5).find("input").val();
			//联系方式
			var shownumber = $("#"+key).val();
			//去除前后空格
			shownumber=shownumber.replace(/(^\s*)|(\s*$)/g, "");
			//正则表达式
			var reg = new RegExp("^[0-9]*$");
			if(shownumber==""){
				shownumber="96003";
			}
			 if(!reg.test(shownumber)){  
				 alert("电话号码不可含有特殊字符。"); 
				 return false;
			 }  
			json = json + "\"" + key + "\"" ;
			json = json + ":\"" + shownumber + "\"" ;
			if (i+1 < 17){//
				json = json + ",";
			}
			
		};
		json = json + "}]";
		$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/saveSqOrder.shtml",
				{shequId:shequId,kid:kid,communityids:communityids,ptype:ptype,json:json,showid:showid,shownumber:shownumber},
				function(data){
			           
		});  
		    document.getElementById('div2').style.display='none';
		    document.getElementById('div3').style.display='none';
		    document.getElementById('content').style.display='block';
			alert("保存成功");
	}
	
	//保存社区指令
	function imp(){
			/*  $.messager.confirm('确认', '确认把该搜索结果导出Excel表格 ？', function(r) {
				if (r) {
					var shequId=document.getElementById("shequId").value;
					$.messager.progress({
						title : '处理中',
						msg : '请稍后',
					});
					$.messager.progress('close');
					location.href="${ctx}/admin/omp/ompRegion/exportExcelNew.shtml?shequId="+shequId;
				}
			 } */
			 var shequId=document.getElementById("shequId").value;
			 var url= "${ctx}/admin/omp/ompRegion/exportExcelNew.shtml?shequId="+shequId;
			 window.open(url);
		<%-- $.post("<%=request.getContextPath()%>/admin/omp/ompRegion/exportExcelNew.shtml",
				{shequId:shequId},
				function(data){
		}); --%>
		    document.getElementById('div3').style.display='none';
		    document.getElementById('div2').style.display='none';
		    document.getElementById('content').style.display='block';
		    document.getElementById('imp').style.display='none';
			//alert("已成功导出到D盘下");
	}
	
	
	/**
	* 删除用户
	*/
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
	//生成指令
	function createOrder(){
		var ids = $(".ids:checkbox:checked").map(function(){
			return $(this).val();
		}).get().join();
		$.post("<%=request.getContextPath() %>/old/oldMatch/createOrder.shtml",{ids:ids},function(data){
			alert(data);
		});
	}
	
	
</SCRIPT>
