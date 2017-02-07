<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="panel panel-default">
	<form:form id="listForm" name="listForm"  >
		
		<table class="table table-hover table-middle">
			<tr>
				<td  width="25%">店名：</td><td>${item.SERVER_NAME}</td>
			</tr>
			<tr>
				<td>服务地区：</td><td>${item.SCOPE_DELIVERY}</td>
			</tr>
			<tr>
				<td>服务项目</td>
				<td>${item.SERVER_TYPE}</td>
			</tr>
			<tr>
				<td>服务电话：</td>
				<td>${item.SERVER_TEL}</td>
			</tr>
			
		</table>
		
		
	</form:form>
</div>
