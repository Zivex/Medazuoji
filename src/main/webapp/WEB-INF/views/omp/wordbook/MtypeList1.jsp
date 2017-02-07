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
						<th width="10%" style="text-align: center;">服务名称</th>
						<th width="10%" style="text-align: center;">是否有效</th>
						<th width="10%" style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="quhua" items="${dataList}" varStatus="sta">
						<tr>
							<td><input type="checkbox" class="ids" value="${quhua.id}"/></td>
							<td style="text-align: center;">${quhua.serviceName}</td>
							<td style="text-align: center;">
							 <c:if test="${quhua.status==1}">
							      <span style="color: green">在用</span>
							 </c:if>
							 <c:if test="${quhua.status==0}">
							      <span style="color: red">失效</span>
							 </c:if>    
							</td>
							<td style="text-align: center;">
								<div class="btn-group">
									<button type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown">
										操作 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<%-- <li><a onclick="addZhiling(${quhua.id})">新增指令</a></li> --%>
										<c:if test="${old.logonName ne 'admin'}">
											<li><a href="###" onclick="edit(${quhua.id},this);">修改</a></li>
										</c:if>
										<c:if test="${old.logonName ne 'admin'}">
											<li><a href="###" onclick="look(${quhua.id},this);">查看</a></li>
										</c:if>
										<li><a onclick="del(${quhua.id})">删除</a></li>
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
		
	    var shequId=document.getElementById("shequId").value; 
		$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/exportExcelNew.shtml",
				{shequId:shequId},
				function(data){
		});
		    document.getElementById('div3').style.display='none';
		    document.getElementById('div2').style.display='none';
		    document.getElementById('content').style.display='block';
		    document.getElementById('imp').style.display='none';
			alert("已成功导出到D盘下");
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
