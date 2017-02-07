<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/admin/index.shtml"/>"><img src='<c:url value="/resources/image/capinfo.png"/>' height="24"></a>
		</div>
		<div class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
			<ul class="nav navbar-nav">
			</ul>
			<ul class="nav navbar-nav navbar-right">
		      <li class="dropdown">
		        <a href="###" class="dropdown-toggle" data-toggle="dropdown">
		        	<span class="text-primary">
		        		<sec:authentication property="principal.alias"/>
		        	</span><b class="caret"></b></a>
		        <ul class="dropdown-menu">
		          <li><a href="<c:url value="/logout"/>">退出</a></li>
		          <li><a href="###" onclick='showPassModal();'>修改密码</a></li>
		        </ul>
		      </li>
		    </ul>
		</div>
	</div>
</div>

<!-- modal -->
<div class="modal fade modal-primary" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true"></div>
<!-- /.modal -->

<!-- Script	-->
<script type="text/javascript">
	function showPassModal() {
		var url = '<c:url value="/admin/sys/user/change_password_form.shtml"/>';
		showDynamicModal(url);
	}
	
	
</script>

