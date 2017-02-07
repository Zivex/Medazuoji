<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="detailModalLabel">角色信息</h4>
		</div>
		<div class="modal-body">
			<div id="detailDiv">
				<table class="table table-bordered table-middle" width="100%">
					<tbody>
						<tr>
							<td>角色名称：</td><td class="t_lt">${command.entity.name}</td>
						</tr>
						<tr>
							<td>授&ensp;权&ensp;码：</td><td class="t_lt">${command.entity.code}</td>
						</tr>
						<tr>
							<td>角色描述：</td><td class="t_lt">${command.entity.description}</td>
						</tr>
						<tr>
							<td width="100px">可用资源：</td>
							<td class="t_lt" width="250px">
								<div id="rTree" class="claro" style="min-height:120px;">
									<ul id="tree" class="ztree"></ul>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="modal-footer">
			<button id="btn-dismiss" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		</div>
	</div>
	<!-- /.modal-content -->
</div>
<!-- /.modal-dialog -->

<script>
//tree
var zNodes = ${command.resourceTree};

var setting = {
	data : {
		simpleData : {
			enable : true,
			pIdKey : "parentId"
		}
	}
};

$(document).ready(function() {
	$.fn.zTree.init($("#tree"), setting, zNodes);
});
	
</script>


