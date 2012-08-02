<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String error = (String)request.getParameter("result");
	if(error == null) {
		error = "";
	} else if(error.equals("success")) {
		error = "添加用户成功！";
	} else if(error.equals("nameError")){
		error = "用户名重复，请重试！";
	} else if(error.equals("false")){
		error = "添加用户失败！";
	} else if(error.equals("empty")) {
		error = "输入不能为空！";
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
			<h2>添加用户</h2>
			<p id="page-intro">Add User</p>



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
							<%=error %>
						</label>

						<form action="addUser.action" method="post">
							<p>
								<label>用户类型</label>
								<select name="user.level">
									<option value="0">----请选择--</option>
									<option value="1">普通用户</option>
									<option value="2">统计员</option>
									<option value="3">管理员</option>
								</select>
							</p>

							<p>
								<label>初始用户名</label> <input class="text-input" type="text" name="user.username"/>
							</p>
							<div class="clear"></div>
							<p>
								<label>初始用户密码</label> <input class="text-input" type="text" name="user.password"/>
							</p>
							<div class="clear"></div>

							<p>
								<input type="submit" class="button" value="确定" />
							</p>
							<div class="clear"></div>
						</form>
					</div>
					<!-- End #login-content -->
				</div>





			</div>
			<!-- End #main-content -->
			
			<jsp:include flush="true" page="footer.jsp"></jsp:include>
			<!-- End #footer -->
		</div>
	</div>
</body>


<!-- Download From www.exet.tk-->
</html>
