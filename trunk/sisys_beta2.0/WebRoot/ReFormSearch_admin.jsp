<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ page import="com.sisys.bean.Page"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%	
	/*String error = (String)request.getAttribute("result");
	if(error == null) {
		error = "";
	}  else if(error.equals("outoflineAlter")) {
		error = "修改后后工序产品数大于前工序产品数，请检查后重试！";
	} else if(error.equals("isdeleteAlter")) {
		error = "记录已删除，不能修改！";
	}  else if(error.equals("errorAlter")) {
		error = "修改失败！";
	} else if(error.equals("successAlter")){
		error = "修改成功！";
	} else if(error.equals("error")){
		error = "逻辑错误！";
	}*/
	
%>

<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
<title>Simpla Admin</title>
		
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
  
	<body><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
	
		
		<jsp:include flush="true" page="banner_admin.jsp"></jsp:include>
		
		<div id="main-content"> <!-- Main Content Section with everything -->
				
			<!-- Page Head -->
			<h2>返工工单列表</h2>
			<p id="page-intro">Rework List</p>
			
			<div class="clear"></div> <!-- End .clear -->
			
			<div align="right">
				
			</form>
			</div>
			<div class="clear"></div>
			
			<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3>Content box</h3>

					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->						
						
						<label><!--
							${error}
						--></label>
						
						<table>
							
							<thead>
								<tr>								   
								  <tr>
									<th width=2%>Id</th>
									<th width=5%>员工姓名</th>
									<th width=5%>审批人姓名</th>
									<th width=5%>责任人姓名</th>
									<th width=5%>工时</th>
									<th width=5%>操作</th>
								</tr>
								</tr>
								
							</thead>
						 
							<tfoot>
								<tr>
									<td colspan="7"><br /></td>
								</tr>
							</tfoot>
						 
							<tbody>
									<tr>
										<td>1</td>
										<td>aa</td>
										<td>bb</td>
										<td>cc</td>
										<td>1</td>
										<td>
										<!-- Icons -->
										
										
											<a href="ReFormAlter_admin.jsp?current=workForm" title="Edit"><img src="resources/images/icons/pencil.png" alt="Edit" /></a>
										    <a href="#" title="Delete" onclick="return confirm('确实要删除吗？');"><img src="resources/images/icons/cross.png" alt="Delete" /></a>
										     <a href="ReFormDetail_admin.jsp?current=workForm" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png" alt="Edit Meta" /></a>
											
										</td>
									</tr>
							</tbody>
							
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
