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
<script type="text/javascript" src="<c:url value="/resources/js/echarts.js"/>"></script>


<div class="panel panel-default">
	<form:form id="listForm" name="listForm" method="post"
		action='${queryForm }'>
		<%-- 		<form:hidden path="id" id="item_entity_id" /> --%>
		<%-- 		<form:hidden path="current" id="current" /> --%>
		<input id="item_entity_id" type="hidden" name="id" value="">
		<input id="currentPage" type="hidden" name="current" value="">
		<%-- 		<c:if test="${DataTotalCount>0}"> --%>
		<!-- tools -->
		<!-- 		<div class="panel-heading"></div> -->
		<table class="table table-hover table-middle" role="grid" style="display:none;">
		<div style="font-weight:bold;font-size: 30px; text-align:center;">按键统计次数报表</div>
			<thead>
				<tr class="active">
					<th width="20%">话机键位</th>
					<th width="25%">话机统计次数</th>
				</tr>
			</thead>
			<tbody >
				<c:forEach var="old" items="${showcout}" varStatus="sta">
					<tr>
						<td class="pz">${old.key}</td>
					    <td class="pzg">${old.nbr}</td>
					</tr>
					
				</c:forEach>
			</tbody>
		</table>
		<%-- 		</c:if> --%>
	</form:form>
	
	<div id="consumption5" style="width:80%; height:600px; margin:0 auto;"></div>

</div>


<!-- Script	-->
<SCRIPT type="text/javascript">
/* 	$(document).ready(function() { */
	console.log($(".pz").text());
// 		initListForm();
// 		<c:if test="${DataTotalCount!=null&&DataTotalCount>0}">
// 		initPagination(<c:out value="${DataTotalCount}"/>,<c:out value="${PerPieceSize}"/>,<c:out value="${CurrentPieceNum}"/>);
// 		</c:if>

		$("#newServerName").change(function(){
			$("#serverName").val("1");
			
		});
	
	var pzg1=$(".pzg").eq(0).text();
	var pzg2=$(".pzg").eq(1).text();
	var pzg3=$(".pzg").eq(2).text();
	var pzg4=$(".pzg").eq(3).text();
	var pzg5=$(".pzg").eq(4).text();
	var pzg6=$(".pzg").eq(5).text();
	var pzg7=$(".pzg").eq(6).text();
	var pzg8=$(".pzg").eq(7).text();
	var pzg9=$(".pzg").eq(8).text();
	var pzg10=$(".pzg").eq(9).text();
	var pzg11=$(".pzg").eq(10).text();
	var pzg12=$(".pzg").eq(11).text();
	function upd(){
		var kid = $("#kid").val();
		var serverName = $("#serverName").val();
		var newServerName = $("#newServerName").val();
		$.post("<%=request.getContextPath()%>/wordbook/wordbookmanage/updServerName.shtml",
				{kid:kid,serverName:serverName,newServerName:newServerName},
				function(data){
			alert(data);
		});
	}

	function toupd(id){
		$("#kid").val(id);
	}
	
	function check(){
		$(".ids").map(function() {
			$(this).attr("checked", !$(this).attr("checked"));
		});
	}
	
	

/* 	} */
</SCRIPT>
<script>
		require.config({
			paths: {
			    echarts: '<c:url value="/resources/js"/>'
		    }
		});
		require(
	    	[
		    'echarts',
		    'echarts/chart/bar'
			],
			function (ec) {

			    var consumption5 = ec.init(document.getElementById('consumption5'));

			   var option = {
				   	color: ['#3398DB'],
			   		title: {
		                text: ''
		            },
		            tooltip: {},
		            xAxis: {
		                data: ['居委会', '卫生站', '老年餐桌', '幸福彩虹', '家政服务', '家电服务', '养老驿站', '康复产品', '咨询投诉', '居家护理', '社区广播', '急救']
		            },
		            yAxis: {},
		            series: [{
		                name: '按键次数',
		                type: 'bar',
		                data: [pzg1, pzg2 , pzg3 , pzg4 , pzg5 , pzg6 , pzg7 , pzg8 , pzg9 , pzg10 , pzg11 , pzg12]
		            }]
				};
			    consumption5.setOption(option); 
			}
		);
</script>
