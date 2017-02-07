<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html lang="zh-CN">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
<title>系统登录</title>
<link href='<c:url value="/resources/css/login.css"/>' rel="stylesheet"
	type="text/css" />
<link href='<c:url value="/resources/login/css/style.css"/>'
	rel="stylesheet" type="text/css" />
<link href='<c:url value="/resources/login/css/skin_/login.css"/>'
	rel="stylesheet" type="text/css" />
<head>
<%@ include file="/WEB-INF/views/layout/adm_top.jsp"%>
<style type="text/css">
body {
	/* 	padding-top: 60px; */
	padding-top: 0px;
	padding-bottom: 0px;
	/*   background-color: #eee; */
}

.header-bar {
	height: 83px;
}

.header-bar .header-singin .logo {
	float: none;
	margin: 25px auto 20px;
	display: block;
}

.header-singin .logo {
	margin: 17px 0 0;
	float: left;
	height: 38px;
	width: 211px;
}

.div-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
	-moz-border-radius: 2px;
	-webkit-border-radius: 2px;
	border-radius: 2px;
	-webkit-box-shadow: 0 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0 2px 2px rgba(0, 0, 0, 0.3);
	background-color: #f7f7f7;
}

.form-signin {
	background-color: #f7f7f7;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.margin-bottom-sm {
	margin-bottom: 5px !important;
}
/**
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
**/
.alert-danger {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

#footer {
	height: 60px;
	background-color: #f5f5f5;
}

#footer>.container {
	padding-left: 15px;
	padding-right: 15px;
}

.container .credit {
	margin: 20px 0;
}
</style>
</head>
<body style="position:relative;background:url(<%=request.getContextPath() %>/resources/login/img/skin_/loginlight.png);background-color:#0170a4;">
	<div id="container">
		<div id="bd">
			<div id="main">
				<div class="login-box">

					<div style="padding-top: 170px; padding-left: 0px">
						<div class="container div-signin "
							style="background-color: transparent; -webkit-box-shadow: 0 2px 2px rgba(0, 0, 0, 0); box-shadow: 0 0 0 rgba(0, 0, 0, 0); border: 0px solid #e0e0e0; border-radius: 3px;">
							<form class="form-signin" style="background-color: transparent;"
								id="command" name="command" onsubmit="return checkForm();"
								action='<c:url value="/login"/>' method="post">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />


								<div class="input-group  margin-bottom-sm">
									<span class="input-group-addon"><i
										class="fa fa-user fa-fw"></i></span> <input type="text"
										id="j_username" name="username" class="form-control"
										placeholder="用户名" autofocus>
								</div>
								<div class="input-group margin-bottom-sm">
									<span class="input-group-addon"><i
										class="fa fa-key fa-fw"></i></span> <input type="password"
										id="j_password" name="password" class="form-control"
										placeholder="密码">
								</div>
								<div class="input-group margin-bottom-sm">
									<span class="input-group-addon"><i
										class="fa fa-barcode fa-fw"></i></span> 
										<input type="text" maxlength="4"
										id="j_captcha" name="j_captcha" class="form-control"
										placeholder="验证码" style="width: 150px"> <img
										height="42" width="73" id="captchaImg"
										style="margin-left: 5px" src="<c:url value="/jcaptcha"/>" />
									<a href="###" onclick="refreshCaptcha();"
										style="margin-left: 5px" title="看不清换一张"><i
										class="fa fa-refresh fa-lg"></i></a>
								</div>
								<div class="input-group margin-bottom-sm" style="float: right">
								</div>

								<div class="input-group margin-bottom-sm" style="width: 500px">
									<button class="buttonSub" style="margin-left: 30px;color: green">登 录</button>
									<button class="buttonReset" type="reset">重 置</button>
								</div>
							</form>
							<c:if test="${not empty param.login_error}">
								<div class="alert alert-block alert-danger fade in">
									<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">&times;</button>
									<h4>登录失败!</h4>
									<p>${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</p>
								</div>
							</c:if>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	<!-- /container -->

<script type="text/javascript">
		function checkForm() {
			var user = $.trim($("#j_username").val());
			var pass = $("#j_password").val();
			if (user == "" || pass == "") {
				alert("请输入用户名密码！");
				return false;
			} else {
				return true;
			}
		}

		function refreshCaptcha() {
			$('#captchaImg').attr(
					'src',
					'<c:url value="/jcaptcha"/>' + '?'
							+ Math.floor(Math.random() * 100)).fadeIn();
		}
	</script>
	<%@ include file="/WEB-INF/views/layout/footer.jsp"%>
	<!--/footer-->

</body>
</html>