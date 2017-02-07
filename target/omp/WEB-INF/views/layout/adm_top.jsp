<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>大座机</title>
<meta name="copyright" content="Capinfo" />
<meta name="description" content="Capinfo System" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"> 
<link rel=stylesheet type=text/css href="<c:url value="/resources/css/bootstrap.css"/>" media="screen">
<%-- <link rel=stylesheet type=text/css href="<c:url value="/resources/css/bootstrap-theme.css"/>" media="screen"> --%>

<link rel=stylesheet type=text/css href="<c:url value="/resources/css/font-awesome.min.css"/>" media="screen">
<link rel=stylesheet type=text/css href="<c:url value="/resources/css/datepicker3.css"/>" media="screen">
<%-- <link rel=stylesheet type=text/css href="<c:url value="/resources/css/style.css"/>" media="screen"> --%>
<link rel=stylesheet type=text/css href="<c:url value="/resources/css/main.css"/>" media="screen">

<!--[if lt IE 9]>
     <script type="text/javascript"	src="<c:url value="/resources/js/bootstrap/html5shiv.js"/>"></script>
     <script type="text/javascript"	src="<c:url value="/resources/js/bootstrap/respond.min.js"/>"></script>
<![endif]-->

<script type="text/javascript"	src="<c:url value="/resources/js/jquery/jquery-1.11.1.min.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/resources/js/jquery/jquery-migrate-1.2.1.min.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/resources/js/jquery/jquery.form.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/resources/js/jquery/pagination.min.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/resources/js/jquery/loadmask.min.js"/>"></script>

<script type="text/javascript"	src="<c:url value="/resources/js/base/func.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.zh-CN.js"/>"></script>
<script type="text/javascript"  src="<c:url value="/resources/js/jquery/validation/jquery.validate.min.js"/>"></script> 
<script type="text/javascript"  src="<c:url value="/resources/js/jquery/validation/additional-methods.js"/>"></script> 
<script type="text/javascript"  src="<c:url value="/resources/js/jquery/validation/messages_zh.js"/>"></script> 
<script type="text/javascript"  src="<c:url value="/resources/My97DatePicker/WdatePicker.js"/>"></script> 

<!-- Script	-->
<script type="text/javascript">
window.UEDITOR_HOME_URL='<c:url value="/ueditor/"/>';
window.HOME_URL='<c:url value="/"/>';
jQuery.ajaxSetup({ 
		cache: false
});
jQuery.validator.setDefaults({
	errorClass : "text-danger"
});

$(document).bind('touchmove', function (event) {
    event.preventDefault();
});

var myScroll;
//菜单初始化
function initalizeSiderBar() {
	//myScroll = new IScroll('#bs-sidebar', { keyBindings: true , mouseWheel: true});
	$(".nav-list").on('show.bs.collapse', function (event) {
		$(this).prev('a').addClass("opend");
		$(this).prev('a').children(".arrow").removeClass("fa-angle-right").addClass("fa-angle-down");
	});
	$(".nav-list").on('shown.bs.collapse', function (event) {
		//myScroll.refresh();
	});
	$(".nav-list").on('hide.bs.collapse', function (event) {
		$(this).prev('a').removeClass("opend");
		$(this).prev('a').children(".arrow").removeClass("fa-angle-down").addClass("fa-angle-right");
	});
	$(".nav-list").on('hidden.bs.collapse', function (event) {
		//myScroll.refresh();
	});
// 	siderBarHeight();
// 	$(window).resize(function (){ 
// 		siderBarHeight();
// 	});
  
}

function siderBarHeight() {
	var clientHeight=document.documentElement.clientHeight;//可见区域高度
	var scrollHeight=document.documentElement.scrollHeight;//网页正文全文高
	var h=clientHeight>scrollHeight?clientHeight:scrollHeight;
	h=h-30;
	$("#bs-sidebar").css("min-height",h+"px");
}

function selectMenu(el){
		if($("#"+el).length>0){
			$("#"+el).addClass("active");
			$("#"+el).parent("ul").addClass("in");
			$("#"+el).parent("ul").parent("li").addClass("active");
			$("#"+el).parent("ul").parent("li").find("i.arrow").removeClass("fa-angle-right").addClass("fa-angle-down");
		}
	}

</script>