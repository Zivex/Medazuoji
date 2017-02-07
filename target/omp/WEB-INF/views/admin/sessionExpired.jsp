<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<%@ include file="/WEB-INF/views/layout/adm_top.jsp"%>
<style type="text/css">
.wrapper {
font-family:"Segoe UI",微软雅黑,Tahoma,Geneva,Verdana;font-size:10pt;line-height:150%;
	margin: 0px auto;
	width: 560px;
}

.logo {
	float: left;
	height: 58px;
	padding: 40px 0px 20px 20px;
	width: 52px;
}

.content {
	margin-left: 72px;
	padding: 20px;
}

h3.warn {
	color: #C60;
}

h3 {
	display: block;
	font-size: 1.17em;
	font-weight: bold;
	margin: 1em 0px;
}

p.code {
	color: #999;
}

p {
	display: block;
	margin: 1em 0px;
}

.message {
	min-height: 48px;
	padding-bottom: 20px;
}

.actions {
	margin-bottom: 10px;
	min-height: 32px;
}
</style>
</head>
<body>
	<!-- head -->
		<%@ include file="/WEB-INF/views/layout/adm_head.jsp"%>
	<!-- /head -->
	<!-- main -->
	<div class="main clearfix">
		<div class="wrapper">
			<div class="logo"><img alt="" src="<c:url value="/resources/image/login/denied.png"/>"/></div>
			<div class="content">
				<h3 class="warn">该用户已登录</h3>
				<p class="code">HTTP 错误 401</p>
				<div class="message">
					<p>
						服务器拒绝了您的浏览请求. <br> <br>服务器只允许单个用户登录，请先退出该用户.
					</p>
				</div>
				<div class="actions"></div>
			</div>
		</div>
	</div>
	<!-- /main-->
	
	<!--footer-->
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!--/footer-->

	<!-- Script	-->
	<SCRIPT type="text/javascript">
		$(document).ready(function() {
		});
	</SCRIPT>
</body>
</html>
