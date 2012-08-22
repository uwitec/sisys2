<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sisys.bean.WFstandard"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	
	WFstandard wfsave = (WFstandard)request.getAttribute("wfsave");
	
%>

<%	
	String error = request.getParameter("result");
	if(error == null) {
		error = "";
	}  else if(error.equals("false")) {
		error = "修改失败！";
	} else if(error.equals("success")) {
		error = "修改成功！";
	}
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>生产物流统计系统</title>

<!--                       CSS                       -->

<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />

<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="resources/css/red.css" type="text/css" media="screen" />  
	 
		-->

<!-- Internet Explorer Fixes Stylesheet -->

<!--[if lte IE 7]>
			<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->

<!--                       Javascripts                       -->
<script type="text/javascript">
function check(){
	var batNo = document.getElementById("batNo").value;
	var staName1 = document.getElementById("staName1").value;
	var staName2 = document.getElementById("staName2").value;
	var staName3 = document.getElementById("staName3").value;
	var staName4 = document.getElementById("staName4").value;
	var staName5 = document.getElementById("staName5").value;
 	var proName = document.getElementById("proName").value;
 	var procName = document.getElementById("procName").value;
 	if(staName1=="该员工不存在" || staName2=="该员工不存在" || staName3=="该员工不存在" || 
 		staName4=="该员工不存在" || staName5=="该员工不存在" || proName=="该产品不存在" || 
 		procName=="该工序不存在") {
 		alert("输入信息有误，请重新输入！");
 	} else if(batNo == "" || (staName1 == "" && staName2 == "" && staName3 == "" && 
 			staName4 == "" && staName5 == "")|| proName == "" || procName == "") {
 		alert("输入信息不能为空！");
 	}
 	 else {
 		document.getElementById("myForm").submit();
		while(1){
		alert("正在处理…");
		}
 		
 	}
}
</script>

<script type="text/javascript"
	src="resources/scripts/addWorkForm.js"></script>

<!-- jQuery -->
<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<!-- jQuery Datepicker Plugin -->
<script type="text/javascript"
	src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
<!--[if IE]><script type="text/javascript" src="resources/scripts/jquery.bgiframe.js"></script><![endif]-->


<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->
		
				<jsp:include flush="true" page="banner_admin.jsp"></jsp:include>

		
		<div id="main-content">
			<!-- Main Content Section with everything -->

			<!-- Page Head -->
			<h2>Welcome!</h2>
			<p id="page-intro">What would you like to do?</p>

			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>工单修改</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->


						<label>
							<%=error%>
						<label>
						
						<form id="myForm" method="post" action="alterWorkForm.action">
						    <input type="hidden" name="wfId" value="${wfsave.wfId}" />
							<div class="formHeader">
								<span>产品编号</span><input type="text" name="proNo" value="${wfsave.proNo}" id="proNo" onblur='displayProNo()'>
								<span>产品名称</span><input type="text" name="proName" value="${wfsave.proName}" id="proName" class="readonly">
								<span>工序编号</span><input type="text" name="procNo" value="${wfsave.procNo}" id="procNo" onblur='displayProcNo()'>
								<span>工序名称</span><input type="text" name="procName" value="${wfsave.procName}" id="procName" class="readonly">
								<span>批次编号</span><input type="text" name="batNo" value="${wfsave.batchNo}" id="batNo">
							</div>
							<table class="formAdd" id="table">
								<tr>
									<th></th>
									<th></th>
									<th></th>
									<th colspan=11 style="text-align:center">不合格品种类及数量</th>
								</tr>
								<tr>
									<th>员工工号</th>
									<th>员工姓名</th>
									<th>合格品数量</th>
									
									<th>调试不合格</th>
									<th>气孔砂眼</th>
									<th>裂纹</th>
									<th>缺料起层</th>
									<th>碰、划、拉伤</th>
									<th>尺寸不合格</th>
									<th>加工缺陷</th>
									<th>物理性能不合格</th>
									<th>化学成分不合格</th>
									<th>试验检验件</th>
									<th>其它</th>
									
								</tr>
								
								<tr class="formContent">
									<td><input type="text" name="staNo1" value="${wfsave.staNo1}" id="staNo1" onblur='displayStaNo1()'></td>
									<td><input type="text" name="staName1" value="${wfsave.staName1}" id="staName1" class="readonly"></td>
									<td><input type="text" name="quaNum1" value="${wfsave.quaNum1}"></td>
									<td><input type="text" name="disqNum1-1" value="${wfsave.disqNum1[0]}"></td>
									<td><input type="text" name="disqNum1-2" value="${wfsave.disqNum1[1]}"></td>
									<td><input type="text" name="disqNum1-3" value="${wfsave.disqNum1[2]}"></td>
									<td><input type="text" name="disqNum1-4" value="${wfsave.disqNum1[3]}"></td>
									<td><input type="text" name="disqNum1-5" value="${wfsave.disqNum1[4]}"></td>
									<td><input type="text" name="disqNum1-6" value="${wfsave.disqNum1[5]}"></td>
									<td><input type="text" name="disqNum1-7" value="${wfsave.disqNum1[6]}"></td>
									<td><input type="text" name="disqNum1-8" value="${wfsave.disqNum1[7]}"></td>
									<td><input type="text" name="disqNum1-9" value="${wfsave.disqNum1[8]}"></td>
									<td><input type="text" name="disqNum1-10" value="${wfsave.disqNum1[9]}"></td>
									<td><input type="text" name="disqNum1-11" value="${wfsave.disqNum1[10]}"></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo2" value="${wfsave.staNo2}" id="staNo2" onblur='displayStaNo2()'></td>
									<td><input type="text" name="staName2" value="${wfsave.staName2}" id="staName2" class="readonly"></td>
									<td><input type="text" name="quaNum2" value="${wfsave.quaNum2}"></td>
									<td><input type="text" name="disqNum2-1" value="${wfsave.disqNum2[0]}"></td>
									<td><input type="text" name="disqNum2-2" value="${wfsave.disqNum2[1]}"></td>
									<td><input type="text" name="disqNum2-3" value="${wfsave.disqNum2[2]}"></td>
									<td><input type="text" name="disqNum2-4" value="${wfsave.disqNum2[3]}"></td>
									<td><input type="text" name="disqNum2-5" value="${wfsave.disqNum2[4]}"></td>
									<td><input type="text" name="disqNum2-6" value="${wfsave.disqNum2[5]}"></td>
									<td><input type="text" name="disqNum2-7" value="${wfsave.disqNum2[6]}"></td>
									<td><input type="text" name="disqNum2-8" value="${wfsave.disqNum2[7]}"></td>
									<td><input type="text" name="disqNum2-9" value="${wfsave.disqNum2[8]}"></td>
									<td><input type="text" name="disqNum2-10" value="${wfsave.disqNum2[9]}"></td>
									<td><input type="text" name="disqNum2-11" value="${wfsave.disqNum2[10]}"></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo3" value="${wfsave.staNo3}" id="staNo3" onblur='displayStaNo3()'></td>
									<td><input type="text" name="staName3" value="${wfsave.staName3}" id="staName3" class="readonly"></td>
									<td><input type="text" name="quaNum3" value="${wfsave.quaNum3}"></td>
									<td><input type="text" name="disqNum3-1" value="${wfsave.disqNum3[0]}"></td>
									<td><input type="text" name="disqNum3-2" value="${wfsave.disqNum3[1]}"></td>
									<td><input type="text" name="disqNum3-3" value="${wfsave.disqNum3[2]}"></td>
									<td><input type="text" name="disqNum3-4" value="${wfsave.disqNum3[3]}"></td>
									<td><input type="text" name="disqNum3-5" value="${wfsave.disqNum3[4]}"></td>
									<td><input type="text" name="disqNum3-6" value="${wfsave.disqNum3[5]}"></td>
									<td><input type="text" name="disqNum3-7" value="${wfsave.disqNum3[6]}"></td>
									<td><input type="text" name="disqNum3-8" value="${wfsave.disqNum3[7]}"></td>
									<td><input type="text" name="disqNum3-9" value="${wfsave.disqNum3[8]}"></td>
									<td><input type="text" name="disqNum3-10" value="${wfsave.disqNum3[9]}"></td>
									<td><input type="text" name="disqNum3-11" value="${wfsave.disqNum3[10]}"></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo4" value="${wfsave.staNo4}" id="staNo4" onblur='displayStaNo4()'></td>
									<td><input type="text" name="staName4" value="${wfsave.staName4}" id="staName4" class="readonly"></td>
									<td><input type="text" name="quaNum4" value="${wfsave.quaNum4}"></td>
									<td><input type="text" name="disqNum4-1" value="${wfsave.disqNum4[0]}"></td>
									<td><input type="text" name="disqNum4-2" value="${wfsave.disqNum4[1]}"></td>
									<td><input type="text" name="disqNum4-3" value="${wfsave.disqNum4[2]}"></td>
									<td><input type="text" name="disqNum4-4" value="${wfsave.disqNum4[3]}"></td>
									<td><input type="text" name="disqNum4-5" value="${wfsave.disqNum4[4]}"></td>
									<td><input type="text" name="disqNum4-6" value="${wfsave.disqNum4[5]}"></td>
									<td><input type="text" name="disqNum4-7" value="${wfsave.disqNum4[6]}"></td>
									<td><input type="text" name="disqNum4-8" value="${wfsave.disqNum4[7]}"></td>
									<td><input type="text" name="disqNum4-9" value="${wfsave.disqNum4[8]}"></td>
									<td><input type="text" name="disqNum4-10" value="${wfsave.disqNum4[9]}"></td>
									<td><input type="text" name="disqNum4-11" value="${wfsave.disqNum4[10]}"></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo5" value="${wfsave.staNo5}" id="staNo5" onblur='displayStaNo5()'></td>
									<td><input type="text" name="staName5" value="${wfsave.staName5}" id="staName5" class="readonly"></td>
									<td><input type="text" name="quaNum5" value="${wfsave.quaNum5}"></td>
									<td><input type="text" name="disqNum5-1" value="${wfsave.disqNum5[0]}"></td>
									<td><input type="text" name="disqNum5-2" value="${wfsave.disqNum5[1]}"></td>
									<td><input type="text" name="disqNum5-3" value="${wfsave.disqNum5[2]}"></td>
									<td><input type="text" name="disqNum5-4" value="${wfsave.disqNum5[3]}"></td>
									<td><input type="text" name="disqNum5-5" value="${wfsave.disqNum5[4]}"></td>
									<td><input type="text" name="disqNum5-6" value="${wfsave.disqNum5[5]}"></td>
									<td><input type="text" name="disqNum5-7" value="${wfsave.disqNum5[6]}"></td>
									<td><input type="text" name="disqNum5-8" value="${wfsave.disqNum5[7]}"></td>
									<td><input type="text" name="disqNum5-9" value="${wfsave.disqNum5[8]}"></td>
									<td><input type="text" name="disqNum5-10" value="${wfsave.disqNum5[9]}"></td>
									<td><input type="text" name="disqNum5-11" value="${wfsave.disqNum5[10]}"></td>
								</tr>
									
								<tr>
									<td class="but"><input class="button" type="button" onclick="check()" value="提交"/></td>
									<td class="but"><input class="button" type="reset" value="重置"/></td>
								</tr>
							</table>


						</form>

					</div>
					<!-- End #tab1 -->



				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->

			<div class="clear"></div>

			<jsp:include flush="true" page="footer.jsp"></jsp:include>
			
			<!-- End #footer -->

		</div>
		<!-- End #main-content -->

	</div>
</body>


<!-- Download From www.exet.tk-->
</html>