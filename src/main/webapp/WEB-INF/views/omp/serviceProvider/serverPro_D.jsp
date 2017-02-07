<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style type="text/css">
#box {
	position: absolute;
	left: 65%;
	top: 255px;
	width: 120px;
	height: 120px;
	margin: -90px 0 0 -200px;
	border: 0px solid;
	filter: alpha(opacity = 100);
}

#shadow {
	position: absolute;
	left: 0;
	top: 0;
	width: 100%;
	height: 400%;
	background-color: #0060C0;
	filter: alpha(opacity = 60);
	opacity: .6;
}
</style>
<!-- <script language="JavaScript" src="../../jscript/move.js"></script> -->
<!-- <script type="text/javascript" -->
<!-- 	src="../../script/My97DatePicker/WdatePicker.js"></script> -->
<!-- <script type="text/javascript" src="../../script/actApExampleUnit.js"></script> -->
<!-- <script type="text/javascript" src="../jscript/annex_fws.js"></script> -->
<!-- <script src="../jscript/jquery-1.8.2.js"></script> -->
<!-- <script src="../jscript/jannex_fws.js"></script> -->
<script type="text/javascript">
	function openItem(url) {
		var width = 950;
		var height = 400;
		var left = (window.screen.availWidth - width) / 2;
		var top = (window.screen.availHeight - height) / 2;
		var config = "top=" + top + ",left=" + left + ",toolbar=no,width="
				+ width + ",height=" + height
				+ ",directories=no,status=no,scrollbars=1,resize=no,menubar=no";
		window.open(url, "win", config);
	}
</script>
</head>

<body style="background-color: transparent">
	<div class="panel panel-default">
		<form id="queryForm" name="queryForm"
			action="../fwstj/bizBaSprvInfoEdit.do?method=update" method="post"
			ENCTYPE="multipart/form-data">
			<input type="hidden" name="isRevocation" id="isRevocation"
				value="6675"> <input type="hidden" name="id" id="id"
				value="32916"> <input type="hidden" name="addServiceIds"
				id="addServiceIds"> <input type="hidden" name="area"
				id="area" value="">
			<table width="100%" border="1" cellspacing="0" cellpadding="0"
				height="100%">
				<tr>
					<td width="11" valign="top" class="td-bg"></td>
					<td align="center" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="31" align="left" valign="middle"
								> 当前位置 &gt; 服务商管理</td>
							</tr>
							<tr>
								<td bgcolor="#FFFFFF" style="font-size: 12px;"></td>
							</tr>
						</table>
						<table width="98%" border="1" cellspacing="0" cellpadding="0">
							<tr height="25">
								<td height="22" width="100%" align="left" bgcolor="#519bec"
									class="word-14e">&nbsp;详细信息</td>

							</tr>
						</table>
						<table width="98%" border="1" align="center" cellpadding="0"
							cellspacing="0" class="bor-2">
							<tr>
								<td><br>
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="10"></td>
											<td align="center" class="word-14c">
												<div class="aj-z-1">
													<div>
														<div>区域信息</div>
													</div>
												</div>
											</td>
										</tr>
									</table> <input type="hidden" name="addServiceIds" id="addServiceIds">
									<table border="1" width="98%" cellpadding="0" cellspacing="1"
										bgcolor="#a6c6da" align="center">
										<tr height="22">
											<td width="15%" height="35" align="right" bgcolor="#D4E5F4">所属区县：</td>
											<td width="35%" bgcolor="#FFFFFF">&nbsp;${map.country }</td>
											<td width="15%" height="35" align="right" bgcolor="#D4E5F4">所属街道：</td>
											<td width="35%" bgcolor="#FFFFFF">&nbsp;${map.street }</td>
										</tr>
										<tr height="22">
											<td width="15%" height="35" align="right" bgcolor="#D4E5F4">所属社区：</td>
											<td width="35%" bgcolor="#FFFFFF">&nbsp;${map.community }</td>
											<td width="15%" height="35" align="right" bgcolor="#D4E5F4">&nbsp;</td>
											<td width="35%" bgcolor="#FFFFFF">&nbsp;</td>
										</tr>
									</table> <br>
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="10"></td>
											<td align="center" class="word-14c">
												<div class="aj-z-1" id="newDivMain"
													style="width: 84px; height: 27px; cursor: hand">
													<div>
														<div>基本信息</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#a6c6da">
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">服务单位名称：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;${map.shop_name }</td>
											<td width="16%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">上级服务商名称：</td>
											<td width="34%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;${map.shop_parentid }</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">服务类型：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;${map.mian_sercices }</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">&nbsp;</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">联&nbsp;系&nbsp;人：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;${map.shop_manager }</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">联系电话：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;${map.shop_telephone }</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">商户编号：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">收券：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">渠道发展来源：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">服务商编号：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">仓储能力：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">是否愿意成为订单服务单位：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">总负责人：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">总负责人联系电话：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">开户联系人：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">开户联系人电话：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">售后对接人：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">售后电话：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">服务电话：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;${map.service_tel }</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">营业面积：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">核实信息：</td>
											<td width="85%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5" colspan="3">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">地&nbsp;&nbsp;&nbsp;&nbsp;址：</td>
											<td width="85%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5" colspan="3">
												&nbsp;</td>
										</tr>
									</table> <br />
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="10"></td>
											<td align="center" class="word-14c">
												<div class="aj-z-1" id="newDivMain"
													style="width: 84px; height: 27px; cursor: hand"
													onclick="changeDiv('newDiv','oldDiv')">
													<div>
														<div>社区协会维护项</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
									<table width="98%" cellpadding="0" cellspacing="1" border="1"
										bgcolor="#a6c6da" align="center">
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">商户类型：</td>
											<td width="85%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5" colspan="3">
												<table border='0'>
													<tr>
														<td>${map.MIAN_SERCICES }</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr height="22">
											<td width="15%" height="35" align="right" bgcolor="#D4E5F4">折扣信息：</td>
											<td width="85%" bgcolor="#FFFFFF" colspan="3">
												&nbsp;持卡享受促销价/会员价/3月银发节享9折，免费一公里送货上门</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">签约时间：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">到期时间：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">店面类型：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">未审核：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">服务状态：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">&nbsp;</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
									</table> <br />
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="10"></td>
											<td align="center" class="word-14c">
												<div class="aj-z-1" id="newDivMain"
													style="width: 84px; height: 27px; cursor: hand"
													onclick="changeDiv('newDiv','oldDiv')">
													<div>
														<div>宣传信息</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#a6c6da">
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">电子券服务单位标识牌：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">特供服务单位公示牌：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">折扣服务单位公示牌：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">专用通道悬挂牌：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">折扣服务单位灯箱：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">特供服务单位灯箱：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">偏远地区特供服务单位灯箱：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">偏远地区特供服务单位公示牌：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">X展架：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">彩旗：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">臂贴：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">POS塑封纸：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">通知：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">海报：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">&nbsp;其他：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5" colspan="3" height="50">
												&nbsp;</td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">&nbsp;备注：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5" colspan="3" height="50">
												&nbsp;</td>
										</tr>
									</table> <br />

									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="10"></td>
											<td align="center" class="word-14c">
												<div class="aj-z-1" id="newDivMain"
													style="width: 84px; height: 27px; cursor: hand">
													<div>
														<div>开户信息</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="1" bgcolor="#a6c6da">
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">是否受理开户：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5"></td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">受理开户时间：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5"></td>
										</tr>
										<tr>
											<td width="15%" align="right" valign="middle"
												bgcolor="#D4E5F4">是否提交装机申请：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5"></td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">提交装机申请时间：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5"></td>
										</tr>
										<tr>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">未受理开户原因：</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5"></td>
											<td width="15%" height="35" align="right" valign="middle"
												bgcolor="#D4E5F4">&nbsp;</td>
											<td width="35%" align="left" valign="middle"
												bgcolor="#FFFFFF" class="word-5">&nbsp;</td>
										</tr>
									</table> <br />
									<table width="98%" border="1" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="10"></td>
											<td align="center" class="word-14c">
												<div class="aj-z-1" id="newDivMain"
													style="width: 84px; height: 27px; cursor: hand">
													<div>
														<div>POS机信息</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
									<table width="98%" cellpadding="0" cellspacing="1" border="1"
										bgcolor="#a6c6da" align="center" name="serviceItem"
										id="serviceItem">
										<tr>
											<td width="3%" height=35 align="center" valign="middle"
												bgcolor="#D4E5F4">序号</td>
											<td width="10%" align="center" bgcolor="#D4E5F4">店面名称</td>
											<td width="9%" align="center" bgcolor="#D4E5F4">商户编号</td>
											<td width="9%" align="center" bgcolor="#D4E5F4">POS机终端编号</td>
											<td width="9%" align="center" bgcolor="#D4E5F4">终端序列号</td>
											<td width="9%" align="center" bgcolor="#D4E5F4">小帮手终端编号</td>
											<td width="8%" align="center" bgcolor="#D4E5F4">通讯方式</td>
											<td width="8%" align="center" bgcolor="#D4E5F4">SIM卡</td>
											<td width="8%" align="center" bgcolor="#D4E5F4">工作状态</td>
											<td width="8%" align="center" bgcolor="#D4E5F4">收单方式</td>
											<td width="9%" align="center" bgcolor="#D4E5F4">操作</td>
										</tr>
									</table> <br /></td>
							</tr>
						</table> <br />
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<tr>
								<td height="28" align=center><input type="button"
									value="返回"
									onClick="javascript:location.href='/omp/admin/omp/ServiceProvider/list.shtml'"
									class="input_btn01"></td>
							</tr>
						</table>
						</div>
</body>