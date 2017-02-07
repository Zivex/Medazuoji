<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/easyUIjs/themes/default/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/easyUIjs/themes/icon.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyUIjs/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyUIjs/jquery.easyui.min.js" rel="stylesheet"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyUIjs/locale/easyui-lang-zh_CN.js" rel="stylesheet"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/easyUIjs/sys.js" rel="stylesheet"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#tree').tree({
			onDblClick : function(node) {
				var id = node.id;
				$.post("${pageContext.request.contextPath}/admin/omp/ompRegion/get.shtml",
					{id:id},
					function(data){
					for(var i = 0;i<data.length;i++){
						$("#table").append("<tr><td><input class='ids' type='checkbox' value='"+data[i].id+"'/></td><td>"+data[i].text+"</td></tr>");
					}
		
				});
			}
		});
		
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
		
		$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/getServiceType.shtml",function(data){
			$("#select").empty();
			$("#select").append("<option value=''>请选择</option>")
			for(var i = 0;i<data.length;i++){
				$("#select").append("<option value='"+data[i].serviceName+"'>"+data[i].serviceName+"</option>")
			}
		});
		
		
	});
</script>

<div style="float: left;">
	<h6>请双击所选择的街道</h6>
</div>
<div style="float: left; margin-left: 350px;">
<!-- 	<div style="float: left;"> -->
<!-- 		<label for="entity.logonName" class="control-label" style=" line-height:35px;">商户名称:</label> -->
<!-- 	</div> -->
<!-- 	<div style="float: left;margin-left: 5px;"> -->
<!-- 		<input id="showName" name="showName" placeholder="输入商户名称" -->
<!-- 			class="form-control" type="text" value=""> -->
<!-- 	</div> -->
<!-- 	<div style="float: left;margin-left: 15px;"> -->
<!-- 		<input type="button" onclick="query()" class="btn btn-primary" value="查询"> -->
<!-- 	</div> -->
</div>

<div style="float: left;height: 300px;width: 165px;overflow:auto;border:1px solid black;clear:both;" >
<ul id="tree" class="easyui-tree" data-options="lines:true">
	<c:forEach items="${dataList }" var="item" >
			<li id="${item.ID}" state="closed"><span>${item.TEXT }</span>
				<ul id="${item.TEXT}">
					<c:forEach items="${street }" var="street" >
						<c:if test="${item.id==street.pid }">
							<li id="${street.ID}" state="closed"><span>${street.TEXT }</span>
						</c:if>
					</c:forEach>
				</ul>
			</li>
	</c:forEach>
</ul>
<ul id = "ss"></ul>
</div>
<div style="float: left;height: 300px;width: 55px;padding-left: 55px;padding-top: 145px;"><input type="button" onclick="del()" value="删除"/></div>
<div style="float: left;height: 300px;width: 165px;overflow:auto;border:1px solid black;margin-left: 100px" >
<div align="center"><font size="4px">候选区</font></div>
<table id="table" class="table">
<tr align="center"><td><input type="button" value="全/反选" onclick="check()"/></td><td>社区名称</td></tr>
</table>
</div>
<div style="float: left;height: 300px;width: 55px;padding-left: 135px;padding-top: 145px;"><input type="button" onclick="mapping()" value="进入待分配区/删"/></div>
<!-- <div style="float: left;height: 300px;width: 55px;padding-left: 55px;padding-top: 145px;"><input type="button" onclick="del()" value="删除"/></div> -->
<div style="float: right;height: 300px;width: 200px;overflow:auto;border:1px solid black;margin-right: 100px">
	<table id="table2" class="table">
	</table>
</div>
<div id="div3" style="float: left;clear:both; widows: 100%;height: 500px;">
	<hr width="1094px">
	<br>
	<div style="width: 48%; float: left;">
		<div style="float: left;margin-top: 5px;">
			老人话机类型：<input class="phoneType" type="radio" name="phoneType"
				value="1" />居家型<input class="phoneType" type="radio"
				name="phoneType" value="2" />失能型<input class="phoneType"
				type="radio" name="phoneType" value="3" />农商型
		</div>
		<div style="float: left;margin-left: 20px;">
			<button class="btn btn-primary" onclick="save()">保存</button>
		</div>
		<br>
		<div>
			<form action="<%=request.getContextPath()%>">
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
					<option value="" >请选择</option>
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

<script type="text/javascript">

	function del() {
		$(".ids:checkbox:checked").map(function() {
			$(this).parent().parent().remove();
		});
	}

	function addprovider() {
		var id = $(".spId:checked").val();
		var name = $(".spId:checked").parent().next().text();
		var number = $(".spId:checked").parent().next().next().next().text();
		$(".addprov:checked").val(id);
		$(".addprov:checked").parent().prev().prev().text(name);
		$(".addprov:checked").parent().prev().find("input").first().val(number);
	}

	function check() {
		$(".ids").map(function() {
			$(this).attr("checked", !$(this).attr("checked"));
		});

	}

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
	
	function save(){
		var communityids = $(".tb2").map(function(){
			return $(this).val();
		}).get().join();
		var ptype = $(".phoneType:checked").val();
		var trnumber = $("#table3 tr").length;
		$("#table3").find("tr").eq(1);
		for(var i=1;i<trnumber;i++){
			var key = $("#table3").find("tr").eq(i).find("td").eq(0).text();
			var kid = $("#table3").find("tr").eq(i).find("td").eq(2).attr("value");
			var showname = $("#table3").find("tr").eq(i).find("td").eq(3).text();
			var showid = $("#table3").find("tr").eq(i).find("td").eq(5).find("input").val();
			var shownumber = $("#"+key).val();
			$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/saveSqOrder.shtml",
					{kid:kid,communityids:communityids,ptype:ptype,showid:showid,shownumber:shownumber},
					function(data){
				
			});
		}
			alert("保存成功");
	}
	
	
	function ExportCommunityOrder(){
		var i = 0;
		var id = $(".ids:checkbox:checked").map(function() {
			i++;
			return $(this).val();
		}).get().join();
		
		if(i>1){
			alert("您好，一次只能导出一个社区指令");
		}else{
			$.post("<%=request.getContextPath()%>/admin/omp/ompRegion/exportExcel.shtml",{id:id});
		}
		
	}
	
</script>
<style>
td {
	text-align: center;
	valign: center;
}
</style>