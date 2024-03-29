<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.sisys.bean.WFstandard"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	String name = (String)request.getAttribute("name");
	WFstandard wfsave = (WFstandard)request.getAttribute("wfsave");
%>

<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
<title>生产物流统计系统</title>
		
		<!--                       CSS                       -->
	  
		<!-- Reset Stylesheet -->
		<link rel="stylesheet" href="resources/css/reset.css" type="text/css" media="screen" />
	  
		<!-- Main Stylesheet -->
		<link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen" />
		
		<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
		<link rel="stylesheet" href="resources/css/invalid.css" type="text/css" media="screen" />	
		
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
  
		<!-- jQuery -->
		<script type="text/javascript" src="resources/scripts/jquery-1.3.2.min.js"></script>
		
		<!-- jQuery Configuration -->
		<script type="text/javascript" src="resources/scripts/simpla.jquery.configuration.js"></script>
		
		<!-- Facebox jQuery Plugin -->
		<script type="text/javascript" src="resources/scripts/facebox.js"></script>
		
		<!-- jQuery WYSIWYG Plugin -->
		<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>
		
		<!-- jQuery Datepicker Plugin -->
		<script type="text/javascript" src="resources/scripts/jquery.datePicker.js"></script>
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
	<div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
	
			
		<jsp:include flush="true" page="banner_operator.jsp"></jsp:include>
		
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			<!-- Page Head -->
			<h2>Welcome!</h2>
			<p id="page-intro">What would you like to do?</p>
			
			<div class="clear"></div> <!-- End .clear -->
			
			<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3>工单详细</h3>

					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<table class="formAdd" id="table">
								<tr>
								
									<th>产品编号</th><th>${wfsave.proNo}</th>
									<th>产品名称</th><th colspan=3>${wfsave.proName}</th>
									<th>工序编号</th><th>${wfsave.procNo}</th>
									<th>工序名称</th><th colspan=3>${wfsave.procName}</th>
									<th>批次编号</th><th>${wfsave.batchNo}</th>
							
								</tr>
								<tr>
									<th rowspan=2>员工工号</th>
									<th rowspan=2>员工姓名</th>
									<th rowspan=2>合格品数量</th>
									<th colspan=11 style="text-align:center">不合格品种类及数量</th>
								</tr>
								<tr>
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
									<td>${wfsave.staNo1}</td>
									<td>${wfsave.staName1}</td>
									<td>${wfsave.quaNum1}</td>
									<td>${wfsave.disqNum1[0]}</td>
									<td>${wfsave.disqNum1[1]}</td>
									<td>${wfsave.disqNum1[2]}</td>
									<td>${wfsave.disqNum1[3]}</td>
									<td>${wfsave.disqNum1[4]}</td>
									<td>${wfsave.disqNum1[5]}</td>
									<td>${wfsave.disqNum1[6]}</td>
									<td>${wfsave.disqNum1[7]}</td>
									<td>${wfsave.disqNum1[8]}</td>
									<td>${wfsave.disqNum1[9]}</td>
									<td>${wfsave.disqNum1[10]}</td>
								</tr>
								<tr class="formContent">
									<td>${wfsave.staNo2}</td>
									<td>${wfsave.staName2}</td>
									<td>${wfsave.quaNum2}</td>
									<td>${wfsave.disqNum2[0]}</td>
									<td>${wfsave.disqNum2[1]}</td>
									<td>${wfsave.disqNum2[2]}</td>
									<td>${wfsave.disqNum2[3]}</td>
									<td>${wfsave.disqNum2[4]}</td>
									<td>${wfsave.disqNum2[5]}</td>
									<td>${wfsave.disqNum2[6]}</td>
									<td>${wfsave.disqNum2[7]}</td>
									<td>${wfsave.disqNum2[8]}</td>
									<td>${wfsave.disqNum2[9]}</td>
									<td>${wfsave.disqNum2[10]}</td>
								</tr>
								<tr class="formContent">
									<td>${wfsave.staNo3}</td>
									<td>${wfsave.staName3}</td>
									<td>${wfsave.quaNum3}</td>
									<td>${wfsave.disqNum3[0]}</td>
									<td>${wfsave.disqNum3[1]}</td>
									<td>${wfsave.disqNum3[2]}</td>
									<td>${wfsave.disqNum3[3]}</td>
									<td>${wfsave.disqNum3[4]}</td>
									<td>${wfsave.disqNum3[5]}</td>
									<td>${wfsave.disqNum3[6]}</td>
									<td>${wfsave.disqNum3[7]}</td>
									<td>${wfsave.disqNum3[8]}</td>
									<td>${wfsave.disqNum3[9]}</td>
									<td>${wfsave.disqNum3[10]}</td>
								</tr>
								<tr class="formContent">
									<td>${wfsave.staNo4}</td>
									<td>${wfsave.staName4}</td>
									<td>${wfsave.quaNum4}</td>
									<td>${wfsave.disqNum4[0]}</td>
									<td>${wfsave.disqNum4[1]}</td>
									<td>${wfsave.disqNum4[2]}</td>
									<td>${wfsave.disqNum4[3]}</td>
									<td>${wfsave.disqNum4[4]}</td>
									<td>${wfsave.disqNum4[5]}</td>
									<td>${wfsave.disqNum4[6]}</td>
									<td>${wfsave.disqNum4[7]}</td>
									<td>${wfsave.disqNum4[8]}</td>
									<td>${wfsave.disqNum4[9]}</td>
									<td>${wfsave.disqNum4[10]}</td>
								</tr>
								<tr class="formContent">
									<td>${wfsave.staNo5}</td>
									<td>${wfsave.staName5}</td>
									<td>${wfsave.quaNum5}</td>
									<td>${wfsave.disqNum5[0]}</td>
									<td>${wfsave.disqNum5[1]}</td>
									<td>${wfsave.disqNum5[2]}</td>
									<td>${wfsave.disqNum5[3]}</td>
									<td>${wfsave.disqNum5[4]}</td>
									<td>${wfsave.disqNum5[5]}</td>
									<td>${wfsave.disqNum5[6]}</td>
									<td>${wfsave.disqNum5[7]}</td>
									<td>${wfsave.disqNum5[8]}</td>
									<td>${wfsave.disqNum5[9]}</td>
									<td>${wfsave.disqNum5[10]}</td>
								</tr>
								<tr>
									<td><span>是否删除</span></td>
									<td>${wfsave.status}</td>
									<td><span>删除时间</span></td>
									<td>${wfsave.deletetime}</td>
								</tr>
								<tr>
									<td><span>统计员姓名</span></td>
									<td>${name}</td>
								</tr>
						</table>
						
						
						
					</div> <!-- End #tab1 -->
					
  
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->

			<div class="clear"></div>
						
			<jsp:include flush="true" page="footer.jsp"></jsp:include>
	
		</div> <!-- End #main-content -->
		
	</div></body>
  

<!-- Download From www.exet.tk-->
</html>
