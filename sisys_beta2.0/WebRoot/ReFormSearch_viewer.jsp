<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.*"%>
<%@ page import="com.sisys.bean.BWFstandard"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%	
	Map resultMap = (Map)request.getAttribute("resultMap");
	String error = "";
	List<BWFstandard> list = null;
	if(resultMap.get("result").equals("error")){
		error = resultMap.get("message").toString();
	}else{		
		list = (List<BWFstandard>)resultMap.get("list");
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
		
	</head>
  
	<body>
	<p align="right"><a href="help.htm" target="_blank"><font size="7"></>使用说明</font></a></p>
	<div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
	
		
		<jsp:include flush="true" page="banner_viewer.jsp"></jsp:include>
		
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
						
						<label>
							<%=error %>
						</label>
						
						<table>
							
							<thead>							   
								  <tr>
									<th width=2%>Id</th>
									<th width=5%>产品</th>
									<th width=5%>批次</th>
									<th width=5%>工序号</th>
									<th width=5%>工序</th>
									<th width=5%>员工姓名</th>
									<th width=5%>工时</th>
									<th width=5%>责任人姓名</th>
									<th width=5%>审批人姓名</th>
									<th width=5%>操作</th>
								</tr>
								
							</thead>
						 
							<tfoot>
								<tr>
									<td colspan="7"><br /></td>
								</tr>
							</tfoot>
						 
							<%for(int i = 0;i < list.size();i++){
									BWFstandard bwf = list.get(i);	
								 %>
									<tr>
										<td><%=bwf.getId() %></td>
										<td><%=bwf.getProName() %></td>										
										<td><%=bwf.getBatchNo() %></td>										
										<td><%=bwf.getProcNo() %></td>										
										<td><%=bwf.getProcName() %></td>
										<td><%=bwf.getStaName() %></td>
										<td><%=bwf.getWorkHours() %></td>
										<td><%=bwf.getRespName() %></td>
										<td><%=bwf.getCheckName() %></td>
										<td>
										<!-- Icons -->
										
										     <a href="showBackForm.action?id=<%=bwf.getId() %>" title="Edit Meta"><img src="resources/images/icons/hammer_screwdriver.png" alt="查看详情" /></a>
											
										</td>
									</tr>
								<%} %>	
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
