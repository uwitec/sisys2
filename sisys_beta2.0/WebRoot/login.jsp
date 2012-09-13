<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	String error = request.getParameter("result");
	if(error == null) {
		error = "";
	} else if(error.equals("false")){
		error = "用户名或密码错误，请重新输入！";
	} else if(error.equals("empty")) {
		error = "输入值不能为空，请重试！";
	}
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
		
		<!-- Internet Explorer .png-fix -->
		
		<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->
		
	</head>
  
	<body id="login">
		<p align="right"><a href="help.htm" target="_blank"><font size="7"></>使用说明</font></a></p>
  <div id="login-wrapper" class="png_bg">
			<div id="login-top">
			
				<h1>物流信息统计系统</h1>
				
				
				<!-- Logo (221px width) -->
				<img id="logo" src="resources/images/logo1.png" alt="Simpla Admin logo" />
			</div> <!-- End #logn-top -->
			
			<div align="center">
					<label>
						<%=error%>
					</lable>
			</div>
			
			<div id="login-content">
				
				<form action="login.action" method="post">
					
									
					<div class="notification information png_bg">
					
						<div>
							首次使用，用户名密码都是admin
						</div>
					</div>
					
					<p>
						<select name="user.level" style="width: 300px">
						
								<option value="0">请选择登录权限</option>
								<option value="1">普通用户</option>
								<option value="2">统计员</option>
								<option value="3">管理员</option>
						</select>
					</p>
					
					<div><br></br></div>
					
					<p>
						<label>用户名</label>
						<input class="text-input" type="text" name="user.username"/>
					</p>
					<div class="clear"></div>
					<p>
						<label>密码</label>
						<input class="text-input" type="password" name="user.password"/>
					</p>
					
					<div class="clear"></div>
					
					
					<div class="clear"></div>
					<p><input type="submit" class="button" value="登录"></p>
						
					
				</form>
				
			</div> <!-- End #login-content -->
			
		</div> <!-- End #login-wrapper -->
		
  </body>
  </html>
