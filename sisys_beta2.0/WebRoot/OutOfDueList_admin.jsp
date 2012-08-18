<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.sisys.bean.OutDueBatchCopy" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	List<OutDueBatchCopy> outOfDueList = (List<OutDueBatchCopy>)request.getAttribute("outOfDueList");
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
<script type="text/javascript" src="resources/scripts/My97DatePicker/WdatePicker.js"></script>
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
			<h2>超期批次查询</h2>
			<p id="page-intro">Extended batch inquiries</p>



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
						
						<table>
							
							<thead>
								<tr>								   
								  <tr>
									<tr>
									<th width=10%>产品名称</th>
									<th width=10%>批次号</th>
									<th width=10%>是否完成</th>
									<th width=10%>是否处理</th>
									<th width=30%>备注</th>
									<th width="3%">操作</th>
								</tr>
								</tr>
								</tr>
								
							</thead>
						 
							<tfoot>
								<tr>
									<td colspan="7"><br /></td>
								</tr>
							</tfoot>
						 
							<tbody>
								<c:forEach items="${outOfDueList }" var="entity">
									<tr>
										<td>${entity.proName}</td>
										<td>${entity.batchNo}<br /></td>
										<td>
										<c:if test="${entity.isOver eq 1}">是</c:if>
										<c:if test="${entity.isOver eq 0}">否</c:if>
										</td>
										<td>
									    <c:if test="${entity.isHandle eq 1}">是</c:if>
									    <c:if test="${entity.isHandle eq 0}">否</c:if>
									    </td>
										<td>${entity.note}</td>
										<td>
										<!-- Icons -->
										
											<a href="OutOfDue.jsp?current=OutOfDue" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
											
										</td>
									</tr>							
								</c:forEach>
							</tbody>
							
						</table>
					</div>
					<!-- End #login-content -->
				</div>

			<!-- End #main-content -->
			<div id="footer">
				<small> <!-- Remove this notice or replace it with whatever you want -->
					&#169; Copyright 2012 Your Company | Powered by 顺江实验室 | <a href="#">Top</a>
				</small>
			</div>
			<!-- End #footer -->
		</div>
	</div>
</body>


<!-- Download From www.exet.tk-->
</html>
