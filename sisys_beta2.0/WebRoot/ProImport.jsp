<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%
	List<String> errorPro=(List<String>)request.getAttribute("errorPro");
	String result = request.getParameter("result");
	if(result == null) {
		result = "";
	}else if(result.equals("error")){
		
		result = "数据导入出错！";
	}else if(result.equals("dataError")) {
		result = "文件内容不匹配！请重新导入！";
	}else if(result.equals("noproLine")) {
		result = "未找到相应生产线！请先导入生产线编码表！";
	}else if(result.equals("success")) {
		result = "数据导入成功！";
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
		<script type="text/javascript">
	function ale()
	{//这个基本没有什么说的，就是弹出一个提醒的对
			document.getElementById("myForm").submit();
			while(1){
				alert("正在处理…");
			}
	}
</script>
		
		<script type="text/javascript">
			function checkType(){
				var forum = document.forms["fileForm"]; 
				var upload = document.getElementsByName("myFile")[0].value;
				if(upload.length == 0){
					alert("请选择上传文件！");
					document.getElementsByName("myFile")[0].focus();
					return false;
				}
				var type = upload.substring(upload.lastIndexOf(".")+1,upload.length);
				if(type == "hfd"){
					forum.submit();
				}else{
					alert("文件类型错误！只允许导入hfd文件！");
					document.getElementsByName("myFile")[0].focus();
					return false;
				}
			}
		</script>
		
	</head>
  
	<body>
	<p align="right"><a href="help.htm" target="_blank"><font size="7"></>使用说明</font></a></p>
	<div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		<jsp:include flush="true" page="banner_admin.jsp"></jsp:include>
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			<!-- Page Head -->
			<h2>产品成本表导入</h2>
			<p id="page-intro">Data Import</p>
			
			
			
			<div class="clear"></div> <!-- End .clear -->
			
			<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
				
					<h3>Select Box</h3>

					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
				<div align="center">
					<label>
						<%if(result.equals("errormore")){
						%><p>导入失败有：</p><%
						for(int i=0;i<errorPro.size();i++){
						%><p><%=errorPro.get(i)%></p><%;}
						}else%><%=result%><%;%>
					</lable>
				</div>
		
				<div id="login-content">
				
				<form method="post" action="proImport.action" name="fileForm" enctype="multipart/form-data" >
					<p>单文件导入</p>
					<p>
						<label>路径</label>
						<input class="text-input" type="file" name="myFile"/>
					</p>
					
					<div class="clear"></div>
					<p>
						<input onclick=checkType() class="button" type="button" value="确定" />
					</p>
					
				</form>
				<form method="post" action="moreproImport.action" id="myForm">
					<p>批量导入</p>
					<p>
						<label>路径</label>
						<input class="text-input" type="text" name="path"/>
					</p>
					
					<div class="clear"></div>
					<p>
						<input onclick=ale() class="button" type="button" value="确定" />
					</p>
					
				</form>
					
			</div> <!-- End #login-content -->
						</div>
						
						
			
			
			
		</div> <!-- End #main-content -->
		<jsp:include flush="true" page="footer.jsp"></jsp:include>
			</div>
	</div></body>
  

<!-- Download From www.exet.tk-->
</html>
