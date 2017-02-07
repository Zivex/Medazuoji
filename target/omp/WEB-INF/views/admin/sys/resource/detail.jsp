<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div>
	<table class="table table-bordered table-middle" width="100%">
		<tbody>
			<tr>
				<td width="20%">资源名称：</td>
				<td width="45%">${command.entity.name}</td>
				<td width="15%">显示位置：</td>
				<td width="20%">${command.entity.position}</td>
			</tr>
			<tr>
				<td>资&ensp;源&ensp;值：</td>
				<td>${command.entity.value}</td>
				<td>是否显示：</td>
				<td><c:choose>
						<c:when test="${command.entity.display eq true}">是</c:when>
						<c:otherwise>否</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td>图&emsp;&emsp;标：</td>
				<td>${command.entity.classId}</td>
				<td>菜单标识：</td>
				<td>${command.entity.menuId}</td>
			</tr>
			<tr>
				<td>资源类型：</td>
				<td>${command.entity.type}</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>允许访问角色：</td>
				<td colspan="3">
					<UL class="list-unstyled">
						<c:forEach items="${command.entity.roles}" var="role">
							<LI>${role.name}</LI>
						</c:forEach>
					</UL>
				</td>
			</tr>
		</tbody>
	</table>
</div>




