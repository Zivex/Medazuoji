<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta http-equiv="refresh"
	content="30;url=${pageContext.request.contextPath}/old/oldMatch/list.shtml?name=&idCard=&zjNumber=&county=&street=&community=&isGenerationOrder=&creationTime=">
<div class="panel-footer">

	<div align="center">
			<c:if test="${failure.size()>0 }">
			<img alt=""
				src="${pageContext.request.contextPath}/resources/image/drsb.png">
				<p align="center">失败提示：</p><br>
				<c:forEach items="${failure }" var="l">
					重复身份证号码：<span>${l.certificatesNumber }</span>
					重复大座机号码：<span>${l.zjNumber }</span><br>
				</c:forEach>
			</c:if>
			<c:if test="${failure.size()==0 }">
			<img alt=""
				src="${pageContext.request.contextPath}/resources/image/drcg.png">
			</c:if>
		<div align="center">
			<input id="autoBack" type="button"
				onclick="location='${pageContext.request.contextPath}/old/oldMatch/list.shtml?name=&idCard=&zjNumber=&county=&street=&community=&isGenerationOrder=&creationTime='"
				value="30秒后自动返回列表/点击返回" />
		</div>
	</div>

</div>



<!-- Script	-->
<SCRIPT type="text/javascript">
	
</SCRIPT>
