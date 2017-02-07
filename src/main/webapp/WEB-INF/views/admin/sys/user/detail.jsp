<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			<h4 class="modal-title" id="detailModalLabel">用户信息</h4>
		</div>
		<div class="modal-body">
			<div id="detailDiv">
				<table class="table table-bordered table-middle" width="100%">
					<tbody>
						<tr>
							<td width="20%">姓&emsp;&emsp;名：</td>
							<td>${user.name}</td>
						</tr>
						<tr>
							<td>登&ensp;录&ensp;名：</td>
							<td>${user.logonName}</td>
						</tr>
						<tr>
							<td>分配角色：</td>
							<td>
								<ul class="list-unstyled">
									<c:forEach items="${user.roles}" var="role">
										<LI>${role.name}</LI>
									</c:forEach>
								</ul>
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

<!-- Script	-->
<SCRIPT type="text/javascript">
	
</SCRIPT>
