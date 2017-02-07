<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<div id="bs-sidebar" class="col-sm-3 col-md-2 bs-sidebar">
	<div class="sidebar-menu">
		<ul class="nav bs-sidenav">
			<li id="m_home"><a href='<c:url value="/admin/index.shtml" />'> <i class="fa fa-home fa-lg fa-fw"></i> 首页
			</a></li>
			

			<c:forEach var="menu" items="${sessionScope.menus}" varStatus="status">
				<c:choose>
					<c:when test="${menu.value.type eq 'URL'}">
						<li id="${menu.value.menuId}">
							<a href='<c:url value="${menu.value.value}"/>'> <i class="${menu.value.classId}"></i> ${menu.value.name}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="#M_${menu.value.id}" class="collapsed" data-toggle="collapse"> 
								<i class="${menu.value.classId}"></i><i class="pull-right arrow fa fa-angle-right"></i> ${menu.value.name}
							</a>
							<ul id="M_${menu.value.id}" class="nav nav-list collapse">
								<c:if test="${not empty menu.value.items}">
									<c:forEach var="item" items="${menu.value.items}">
										<li id="${item.menuId}">
											<a href='<c:url value="${item.value}"/>'><i class="fa fa-caret-right fa-fw"></i>${item.name}</a>
										</li>
									</c:forEach>
								</c:if>
							</ul></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			
			
			
			<%-- <li><a href="#adm_sys" class="collapsed" data-toggle="collapse"> <i class="fa fa-cogs fa-lg fa-fw"></i><i class="pull-right arrow fa fa-angle-right"></i> 系统管理
			</a>
				<ul id="adm_sys" class="nav nav-list collapse">
				
					<li id="m_adm_sys_user"><a href='<c:url value="/admin/sys/user/initial.shtml"/>'><i class="fa fa-caret-right fa-fw"></i>用户管理</a></li>
					<li id="m_adm_sys_role"><a href='<c:url value="/admin/sys/role/initial.shtml"/>'><i class="fa fa-caret-right  fa-fw"></i>角色管理</a></li>
					<li id="m_adm_sys_res"><a href='<c:url value="/admin/sys/resource/initial.shtml"/>'><i class="fa fa-caret-right  fa-fw"></i>资源管理</a></li>
					<li id="m_adm_sys_dic"><a href='<c:url value="/admin/sys/dic/sort.shtml"/>'><i class="fa fa-caret-right  fa-fw"></i>字典管理</a></li>
					<li id="o_wordbook"><a href='<c:url value="/wordbook/wordbookmanage/initial.shtml"/>'><i class="fa fa-caret-right  fa-fw"></i>字典管理</a></li>
					<li id="o_log"><a href='<c:url value="/syslog/Systemlogs/list.shtml"/>'><i class="fa fa-caret-right  fa-fw"></i>日志管理</a></li>
					<li id="o_wordbooks"><a href='<c:url value="/syslog/ReportFrom/initial.shtml?county=&street=&community=&otype=&stime=&etime="/>'><i class="fa fa-caret-right  fa-fw"></i>报表管理</a></li>
				</ul></li> --%>
				
				
			<%-- <li><a href="#adm_xbs" class="collapsed" data-toggle="collapse"> <i class="glyphicon glyphicon-th-large">&nbsp;</i><i class="pull-right arrow fa fa-angle-right"></i> 小帮手
			</a>
				<ul id="adm_xbs" class="nav nav-list collapse">
					<li id="o_old"><a href='<c:url value="/old/oldMatch/list.shtml?name=&idCard=&zjNumber=&county=&street=&community=&isGenerationOrder=&isindividuation="/>'><i class="fa fa-caret-right fa-fw"></i>老人管理</a></li>
					<li id="o_provider"><a href='<c:url value="/admin/omp/ServiceProvider/initial.shtml?QSql="/>'><i class="fa fa-caret-right  fa-fw"></i>服务商管理</a></li>
					<li id="ompRegion"><a href='<c:url value="/admin/omp/ompRegion/listNew.shtml?county=&street=&community=&isSend=creationTime="/>'><i class="fa fa-caret-right  fa-fw"></i>区划管理</a></li>
					<li id="o_order"><a href='<c:url value="/order/orderManage/initial.shtml?name=&idCard=&zjNumber=&county=&street=&community=&execute_flag=&send_flag="/>'><i class="fa fa-caret-right  fa-fw"></i>指令键管理</a></li>
					<li id="o_voice"><a href='<c:url value="/voice/voiceManage/initial.shtml?name=&idCard=&zjNumber=&county=&street=&community="/>'><i class="fa fa-caret-right  fa-fw"></i>语音管理</a></li>
					<li id="o_voice1">
						<a href='<c:url value="/voice/voiceManage/voiceQuery.shtml?name="/>'><i class="fa fa-caret-right  fa-fw"></i>语音管理</a>
						<a id="yyid" href='javascript:;'><i class="fa fa-caret-right fa-fw"></i>语音管理</a>
						<ul id="yy2" style="display:none;">
							<li id="o_voice2"><a href='<c:url value="/voice/voiceManage/voiceQuery.shtml?name="/>'><i class="fa fa-caret-right  fa-fw"></i>语音文件管理</a></li>
							<li id="o_voice3"><a href='<c:url value="/voice/voiceManage/initial2.shtml?name=&idCard=&zjNumber=&county=&street=&community=&execute_flag=&send_flag="/>'><i class="fa fa-caret-right  fa-fw"></i>语音指令管理</a></li>
						</ul>
					</li>
				</ul></li> --%>
		</ul>
	</div>
</div>
<script>
	$("#yyid").click(function(){
		$("#adm_xbs li").removeClass("active");
		$("#o_voice1").addClass("active");
		$("#yy2").toggle(400);
	})
</script>
