<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
<%@ page import="com.sisys.bean.Batch"%>
<%@ page import="com.sisys.bean.Flowpath"%>
<%@ page import="com.sisys.bean.Product"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
				+ request.getServerName() + ":" + request.getServerPort()
				+ path + "/";
	
%>

<%
	
	String result = request.getParameter("result");
	String proNo = (String)request.getAttribute("pro");
	String batNo = (String)request.getAttribute("bat");
	String code = (String) request.getAttribute("code");
	String error = "";
	if(result == null) {
		error = "";
	} else if(result.equals("success")) {
		error = "添加成功！" + code;
	}  else if(result.equals("false")) {
		error = "添加失败！";
	}  else if(result.equals("repetition")) {
		error = "该批次已存在！";
	}  else if(result.equals("isnull")) {
		error = "输入不能为空！";
	}  else if(result.equals("pnone")) {
		error = "该产品不存在！";
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

<script type="text/javascript"
	src="resources/scripts/addBatch.js"></script>
	
<script type="text/javascript">
	function ale()
	{//这个基本没有什么说的，就是弹出一个提醒的对话框
		var batchNo = document.getElementById("batchNo").value;
		var flowpath = document.getElementById("flowpath").value;
		if(batchNo == "undefine" || batchNo == "该产品批次已满！") {
			alert(batchNo);
		} else if(flowpath == "未找到相应流程！请重新输入产品编号") {
			alert("未找到相应流程！请重新输入产品编号或检查该产品是否已导入");
		} else {
			document.getElementById("myForm").submit();
			
			while(1){
				alert("正在处理…");
			}
		}
		

	}
</script>

<script type="text/javascript">
	window.onload = function send() {
		var result = "<%=result%>";
		var pro = "<%=proNo%>";
		var bat = "<%=batNo%>";
		if(result=="success")
			window.open("PDFs/" + pro + "/" + bat + ".pdf");
	}
</script>

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
<p align="right"><a href="help.htm" target="_blank"><font size="7"></>使用说明</font></a></p>
<div id="div1" style="position:absolute;top:0px;left:0px;width:1200px;height:60000px;display:none;"></div>


	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<jsp:include flush="true" page="banner_operator.jsp"></jsp:include>

		<div id="main-content">
			<!-- Main Content Section with everything -->

			<!-- Page Head -->
			<h2>新建批次</h2>
			<p id="page-intro">Add Batch</p>



			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>Search Box</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div id="login-content">
						
						<label>
							<%=error%>
						</label>
						
						<form id="myForm" action="addBatch.action" method="post">

						<table class="">
								<tr>
									<td><span>产品编号</span></td>
									<td><input type="text" width="50px"  id="proNo" name="product.proNo"  onblur='display()'></td>
									<td><span>批次号</span></td>
									<td><input type="text" width="50px"  id="batchNo" class="readonly" name="batch.batchNo"></td>
									
								</tr>
								<tr>
									<td><span>目标数量</span></td>
									<td><input type="text" width="50px" name="batch.totalNum"></td>
									
								</tr>
								<tr>
									<td><span>选择流程</span></td>
								</tr>
								<tr>									
									<td colspan=4>
										<div id="flowpath">
											
										</div>
									</td>
								</tr>
								
								<tr>
									<td><input class="button" type="button" value="提交" onclick="ale()"/></td>
									<td><input class="button" type="reset" value="重置"/></td>
								</tr>
							</table>
							
					</div>
					<!-- End #login-content -->
				</div>

			</div>
			<!-- End #main-content -->
			<jsp:include flush="true" page="footer.jsp"></jsp:include>
		</div>
	</div>
</body>


<!-- Download From www.exet.tk-->
</html>
