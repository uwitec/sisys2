<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sisys.bean.Department"%>
<%@page import="com.sisys.bean.Product"%>
<%@page import="com.sisys.bean.Batch"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	Map resultMap = (Map)request.getAttribute("mapNI");
	List<Department> department=(List)resultMap.get("department");
	List<Product> product_ing=(List)resultMap.get("product_ing");
	List<Product> product_od=(List)resultMap.get("product_od");
	List<String> tmp_ing=new ArrayList<String>();
	List<String> tmp_ing_No=new ArrayList<String>();
	List<String> tmp_od_No=new ArrayList<String>();
	List<String> tmp_od=new ArrayList<String>();
	List<Product> product_ing_bu=product_ing;
	List<Product> product_od_bu=product_od;
	
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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
			<h2>Welcome!</h2>
			<p id="page-intro">What would you like to do?</p>
			
			<ul class="shortcut-buttons-set">
				
				<li><a class="shortcut-button" href="searchByBatch_viewer.jsp?current=workForm"><span>
					<img src="resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
					工单列表
				</span></a></li>
				
				<li><a class="shortcut-button" href="searchReForm_viewer.jsp?current=workForm"><span>
					<img src="resources/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
					返工工单列表
				</span></a></li>
				
				
			</ul><!-- End .shortcut-buttons-set -->
			
			<div class="clear"></div> <!-- End .clear -->
			
			<div class="content-box"><!-- Start Content Box -->
				
		 		<div class="content-box-header">
					
					<h3>超期产品</h3>

					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<table>						 
							<tbody>
							<%if(resultMap.get("result").equals("success")){
								if(resultMap.get("error_od").equals("success")){
								int flag=0;
								while(product_od.size()!=0){
									for(int i=0;i<department.size();i++){
										flag=0;
											for(int j=0;j<product_od.size();j++){ 
												if(product_od.get(j).getDeptId()==department.get(i).getId()){
												tmp_od.add(product_od.get(j).getProName());
												tmp_od_No.add(product_od.get(j).getProNo());														
													flag=1;
													product_od.remove(j);
												}
											}
											if(flag==0){
												tmp_od.add("&nbsp;");
												tmp_od_No.add("&nbsp;");
											}
											
										}
									tmp_od.add(null);
									tmp_od_No.add(null);
								}
										%>
									
									<tr>
										<%for(int i=0;i<department.size();i++){ %>
										<td><h3><%=department.get(i).getDeptName()%></h3></td>
										<%}%>
									</tr>
								<tr>
								<%for(int i=0;i<tmp_od_No.size();i++){%>
								<%if(tmp_od.get(i)==null){%>
								</tr><tr><%}else{
								String par=tmp_od_No.get(i);
								System.out.println(par);%>
								<td><a style="color:red" target="_blank" href=SearchIndexJd?proNo=<%=par%>><%=tmp_od.get(i)%></a></td>
								<%}}
								}else
									out.println(resultMap.get("message_od"));
								}else{
									out.println(resultMap.get("message"));}%>
								
							</tbody>
							
						</table>
						
					</div> <!-- End #tab1 -->
					
  
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->
			

			<div class="content-box"><!-- Start Content Box -->
				
				<div class="content-box-header">
					
					<h3>正在生产产品</h3>

					
					<div class="clear"></div>
					
				</div> <!-- End .content-box-header -->
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<table>
						
							<tbody>
								<%if(resultMap.get("result").equals("success")){
								if(resultMap.get("error_ing").equals("success")){
								int flag=0;
								
								while(product_ing.size()!=0){
									for(int i=0;i<department.size();i++){
										flag=0;
											for(int j=0;j<product_ing.size();j++){ 
												if(product_ing.get(j).getDeptId()==department.get(i).getId()){
												tmp_ing.add(product_ing.get(j).getProName());
												tmp_ing_No.add(product_ing.get(j).getProNo());
													
													flag=1;
													product_ing.remove(j);
												}
											}
											if(flag==0){
												tmp_ing.add("&nbsp;");
												tmp_ing_No.add("&nbsp;");
											}
												
										}
									tmp_ing.add(null);
									tmp_ing_No.add(null);
								}
										%>
									
									<tr>
										<%for(int i=0;i<department.size();i++){ %>
										<td><h3><%=department.get(i).getDeptName()%></h3></td>
										<%}%>
									</tr>
								<tr>
								<%for(int i=0;i<tmp_ing.size();i++){%>
								<%if(tmp_ing.get(i)==null){%>
								</tr><tr><%}else{
								String par=tmp_ing_No.get(i);%>
								<td><a target="_blank" href=SearchIndexJd?proNo=<%=par%>><%=tmp_ing.get(i)%></a></td><%}}%>
							
							</tr>
							<%
								}else
									out.println(resultMap.get("message_ing"));
								}else{
									out.println(resultMap.get("message"));}%>										
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
