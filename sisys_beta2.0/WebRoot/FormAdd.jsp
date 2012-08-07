<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sisys.bean.DisqKind"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	//String name = (String)request.getAttribute("name");
%>

<%	
	String error = request.getParameter("result");
	if(error == null) {
		error = "";
	} else if(error.equals("outofline")) {
		error = "后工序产品数量大于前工序产品数量或产品已完成，添加失败！";
	} else if(error.equals("error")) {
		error = "添加失败！";
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
	var quaNum = document.getElementById("quaNum").value;
	var staName = document.getElementById("staName").value;
 	var proName = document.getElementById("proName").value;
 	var procName = document.getElementById("procName").value;
 	if(staName=="该员工不存在" || proName=="该产品不存在" || procName=="该工序不存在") {
 		alert("输入信息有误，请重新输入！");
 	} else if(batNo == "" || quaNum == "" || staName == "" || proName == "" || procName == "") {
 		alert("输入信息不能为空！");
 	}
 	 else {
 		document.getElementById("myForm").submit();
 	}
}
</script>


<!--<script type="text/javascript"
	src="resources/scripts/addWorkForm.js"></script>

<script type="text/javascript"
	src="resources/scripts/addDisqKind.js"></script>

--><!-- jQuery -->
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
		
<base target="_self" />

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->
	
	
		<jsp:include flush="true" page="banner_operator.jsp"></jsp:include>
	
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

					<h3>工单添加</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->


						<label>
							<%=error%>
						</label>
						
						<form id="myForm" action="addWorkForm.action" method="post">
							<div class="formHeader">
								<span>产品编号</span><input type="text" name="proNo">
								<span>产品名称</span><input type="text" name="proName" readOnly="true">
								<span>工序编号</span><input type="text" name="procNo">
								<span>工序名称</span><input type="text" name="procName" readOnly="true">
								<span>批次编号</span><input type="text" name="batNo">
							</div>
							<table class="formAdd" id="table">
								<tr>
									<th></th>
									<th></th>
									<th></th>
									<th colspan=11 style="text-align:center">不合格品种类及数量</th>
									<th></th>
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
									<td><input type="text" name="staNo1" ></td>
									<td><input type="text" name="" ></td>
									<td><input type="text" name="quaNum1" ></td>
									<td><input type="text" name="disqNum1-1" ></td>
									<td><input type="text" name="disqNum1-2" ></td>
									<td><input type="text" name="disqNum1-3" ></td>
									<td><input type="text" name="disqNum1-4" ></td>
									<td><input type="text" name="disqNum1-5" ></td>
									<td><input type="text" name="disqNum1-6" ></td>
									<td><input type="text" name="disqNum1-7" ></td>
									<td><input type="text" name="disqNum1-8" ></td>
									<td><input type="text" name="disqNum1-9" ></td>
									<td><input type="text" name="disqNum1-10" ></td>
									<td><input type="text" name="disqNum1-11" ></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo2" ></td>
									<td><input type="text" name="" ></td>
									<td><input type="text" name="quaNum2" ></td>
									<td><input type="text" name="disqNum2-1" ></td>
									<td><input type="text" name="disqNum2-2" ></td>
									<td><input type="text" name="disqNum2-3" ></td>
									<td><input type="text" name="disqNum2-4" ></td>
									<td><input type="text" name="disqNum2-5" ></td>
									<td><input type="text" name="disqNum2-6" ></td>
									<td><input type="text" name="disqNum2-7" ></td>
									<td><input type="text" name="disqNum2-8" ></td>
									<td><input type="text" name="disqNum2-9" ></td>
									<td><input type="text" name="disqNum2-10" ></td>
									<td><input type="text" name="disqNum2-11" ></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo3" ></td>
									<td><input type="text" name="" ></td>
									<td><input type="text" name="quaNum3" ></td>
									<td><input type="text" name="disqNum3-1" ></td>
									<td><input type="text" name="disqNum3-2" ></td>
									<td><input type="text" name="disqNum3-3" ></td>
									<td><input type="text" name="disqNum3-4" ></td>
									<td><input type="text" name="disqNum3-5" ></td>
									<td><input type="text" name="disqNum3-6" ></td>
									<td><input type="text" name="disqNum3-7" ></td>
									<td><input type="text" name="disqNum3-8" ></td>
									<td><input type="text" name="disqNum3-9" ></td>
									<td><input type="text" name="disqNum3-10" ></td>
									<td><input type="text" name="disqNum3-11" ></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo4" ></td>
									<td><input type="text" name="" ></td>
									<td><input type="text" name="quaNum4" ></td>
									<td><input type="text" name="disqNum4-1" ></td>
									<td><input type="text" name="disqNum4-2" ></td>
									<td><input type="text" name="disqNum4-3" ></td>
									<td><input type="text" name="disqNum4-4" ></td>
									<td><input type="text" name="disqNum4-5" ></td>
									<td><input type="text" name="disqNum4-6" ></td>
									<td><input type="text" name="disqNum4-7" ></td>
									<td><input type="text" name="disqNum4-8" ></td>
									<td><input type="text" name="disqNum4-9" ></td>
									<td><input type="text" name="disqNum4-10" ></td>
									<td><input type="text" name="disqNum4-11" ></td>
								</tr>
								<tr class="formContent">
									<td><input type="text" name="staNo5" ></td>
									<td><input type="text" name="" ></td>
									<td><input type="text" name="quaNum5" ></td>
									<td><input type="text" name="disqNum5-1" ></td>
									<td><input type="text" name="disqNum5-2" ></td>
									<td><input type="text" name="disqNum5-3" ></td>
									<td><input type="text" name="disqNum5-4" ></td>
									<td><input type="text" name="disqNum5-5" ></td>
									<td><input type="text" name="disqNum5-6" ></td>
									<td><input type="text" name="disqNum5-7" ></td>
									<td><input type="text" name="disqNum5-8" ></td>
									<td><input type="text" name="disqNum5-9" ></td>
									<td><input type="text" name="disqNum5-10" ></td>
									<td><input type="text" name="disqNum5-11" ></td>
								</tr>
									
								<tr>
									<td class="but"><input class="button" type="submit" onclick="check()" value="提交"/></td>
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
