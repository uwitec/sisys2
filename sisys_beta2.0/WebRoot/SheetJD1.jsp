<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sisys.bean.ScheduleTab"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	Map resultMap = (Map)request.getAttribute("resultMap");
	List<String> dateNo = (List<String>)resultMap.get("dateNo");
	List<Map<String,Object>> listMap = (List<Map<String,Object>>)resultMap.get("listMap");
	ScheduleTab sc;
 %>
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
	<title>生产物流统计系统</title>
	<link rel="stylesheet" href="resources/css/tablestyle.css" type="text/css"
	media="screen" />
	<SCRIPT type=text/javascript>
		function tableExportor(id){ 
			var forum = document.forms["tableExport"]; 
			forum.title.value = document.getElementById('tableTitle').innerHTML;
			forum.content.value=eval(id+".innerHTML"); 
			forum.submit(); 
		} 
	</SCRIPT>
		
	</head>
  
	<body><div id="body-wrapper"> <!-- Wrapper for the radial gradient background -->
		
		
		
		<div id="main-content"> <!-- Main Content Section with everything -->
			
			
			
			<div class="content-box"><!-- Start Content Box -->
				
				
				
				<div class="content-box-content">
					
					<div class="tab-content default-tab" id="tab1"> <!-- This is the target div. id must match the href of this div's tab -->
						
						<h2 style="text-align:center" id="tableTitle">${resultMap['proName'] }(${resultMap['proNo'] })生产进度汇总表(时间:${resultMap['starttime'] }到${resultMap['endTime'] })</h2>
						<form name="tableExport" method="POST" action="tableExport.action">
							<input type="hidden" name="title">
							<input type="hidden" name="content">
							<div align="center"><input  onclick=tableExportor('MainTable') class="button" type="button" value="导出"></div>
						</form>
						<table cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" id="MainTable">
							<tbody>
								<tr>
									<th >日期</th>
									<%for(String t : dateNo){ %>
									<th rowspan=2 width="30px"><%=t %></th>
									<%} %>
								</tr>
								<tr>
									<th>批次</th>
								</tr>
								<%for(int i = 0;i < listMap.size();i++){ 
									Map jdMap = (Map)(listMap.get(i).get("jdMap"));
									int size = 1;
									if(jdMap.size() != 0){
										size = jdMap.size();
									}
								%>																					
								<tr>
									<td rowspan=<%=size %>>
									<%if(listMap.get(i).get("status").toString().equals("2")) {%>
										<font color="#ff0000">
									<%} %>
										<%=listMap.get(i).get("batchNo") %>
									<%if(listMap.get(i).get("status").toString().equals("2")) {%>
										</font>
									<%} %>
									</td>
									<%for(int j=1;j<=size;j++){%> 
										<%for(String t : dateNo){
											if(jdMap.size() == 0){ %>
												<td>&nbsp</td>
											<%}else {
												sc =(ScheduleTab)((Map)jdMap.get(j)).get(t);%>
												<%if(sc != null){ %>
												<td style="background-color:<%=sc.getColorNo() %>">
												<%=sc.getNum() %>
												</td>
												<%}else{%>
												<td>&nbsp</td>
											<%}}} %>
										</tr>
									<%} %>
									
								
								<%} %>
							</tbody>
						</table>
						
						
						
						
					</div> <!-- End #tab1 -->
					
  
					
				</div> <!-- End .content-box-content -->
				
			</div> <!-- End .content-box -->

			
			
		</div> <!-- End #main-content -->
		
	</div></body>
  

<!-- Download From www.exet.tk-->
</html>
