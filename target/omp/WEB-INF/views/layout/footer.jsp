<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
#footer {
	height: 40px;
	background-color: #f5f5f5;
}

#footer>.container {
	padding-left: 15px;
	padding-right: 15px;
}

.container .credit {
	margin: 10px 0;
}

ul.footer-links:before {
	border-left: 35px solid #ffa900;
	border-right: 34px solid #00a753;
	margin: 0 30px 0 0;
}

ul.footer-links:after,ul.footer-links:before {
	content: "";
	height: 4px;
	position: absolute;
	right: 0;
	top: -5px;
}

ul.footer-links:after {
	border-left: 34px solid #0089fa;
	border-right: 35px solid #ff002b;
	margin: 0 99px 0 0;
}

ul.footer-links {
	margin: 10px 0;
	padding-left: 0;
}

ul.footer-links li {
	display: inline;
	padding: 0 2px;
}

ul.footer-links li:first-child {
	padding-left: 0;
}

.footer-fixed-bottom {
	position: fixed;
	right: 0;
	left: 0;
	top: auto;
	bottom: 0;
	border-width: 0 0 1px;
}
</style>

<div id="footer" class="footer-fixed-bottom">
	<div class="container">
<%--  <p class="text-muted credit">Designed by sonic</p> --%>
		<ul class="footer-links" style="text-align: center;">
			<!-- <li>北京市沙河镇|老人机管理平台</li>
			<li class="muted">-</li>
			<li>1.0版本</li> -->
			<li></li>
			<li class="muted"></li>
			<li></li>
			<li></li>
		</ul> 
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/resources/js/bootstrap/bootstrap.min.js"/>"></script>
<script type="text/javascript">
	
</script>
