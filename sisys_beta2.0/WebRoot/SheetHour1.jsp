<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="data.bean.WorkHoursTab"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%
	Map resultMap = (Map)request.getAttribute("resultMap");
	List<String> dateNo = (List<String>)resultMap.get("dateNo");
	List<Map<String,Object>> listMap = (List<Map<String,Object>>)resultMap.get("listMap");
	WorkHoursTab wh;
 %>
<html>
  <head>
    <title>生产物流统计系统</title>
	<link rel="stylesheet" href="resources/css/tablestyle.css" type="text/css"
	media="screen" />
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->
    <SCRIPT type=text/javascript>
		function tableExportor(id){ 
			var forum = document.forms["tableExport"]; 
			forum.title.value = document.getElementById('tableTitle').innerHTML;
			forum.content.value=eval(id+".innerHTML"); 
			forum.submit(); 
		} 
	</SCRIPT>

  </head>
  
  <body>
    <h2 align="center" id="tableTitle">${resultMap['deptName'] }员工工作统计表(${resultMap['starttime'] }到${resultMap['endTime'] })</h2>
	<form name="tableExport" method="POST" action="tableExport.action">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
		<div align="center"><input  onclick=tableExportor('MainTable') class="button" type="button" value="导出"></div>
	</form>
	<br></br>
	<div align="center">
		<table cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" id="MainTable">
		
		

				<tr align="center">
		
					<th colspan=3>日期</th>
					<%for(String t : dateNo){ %>
						<th rowspan=2 width="30px"><%=t %></th>
					<%} %>
					<th rowspan=2 width="30px">合计</th>
				</tr>
				<tr>
					<th>姓名</th>
					<th>工号</th>
					<th>&nbsp</th>
				</tr>
				<%for(int i = 0;i < listMap.size();i++){
					Map whMap = (Map)(listMap.get(i).get("whMap"));
					Map totalMap = (Map)(listMap.get(i).get("totalMap"));
				 %>
				<tr align="center">
				
					<td rowspan='2' style="white-space: nowrap;"><%=listMap.get(i).get("staName") %></td>							
					<td rowspan='2'><%=listMap.get(i).get("staNo") %></td>
					<td style="white-space: nowrap;">工时(小时)</td>
					<%for(String t : dateNo){
						if(whMap.size() == 0){ %>
							<td>&nbsp</td>
						<%}else {
							wh =(WorkHoursTab)whMap.get(t);%>
							<%if(wh != null){ %>
								<td><%=wh.getWorkHours() %></td>
							<%}else{%>
							<td>&nbsp</td>
					<%}}} %>
					<td><%=totalMap.get("workHours") %></td>
					</tr>
					<tr align="center">
						<td>报酬(元)</td>
					<%for(String t : dateNo){
						if(whMap.size() == 0){ %>
							<td>&nbsp</td>
						<%}else {
							wh =(WorkHoursTab)whMap.get(t);%>
							<%if(wh != null){ %>
								<td><%=wh.getSalary() %></td>
							<%}else{%>
							<td>&nbsp</td>
					<%}}} %>
					<td><%=totalMap.get("salary") %></td>
					</tr>
					
				<%} %>
				
		</table>
		
	</div>
  </body>
</html>
