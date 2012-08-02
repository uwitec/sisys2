<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>生产物流统计系统</title>
	
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    
    <!--<link rel="stylesheet" type="text/css" href="./styles.css">-->

  </head>
  
  <body>
    <h2 align="center">员工工作统计表(<s:property value="sTime"/>到<s:property value="eTime"/>)</h2>
	<div align="center"><a class="button" href="#">导出</a></div>
	<br></br>
	<div align="center">
		<table border="1" cellspacing="1" cellpadding="5">
		
		

				<tr align="center">
		
					<th >姓名</th>
					<th >工号</th>
					<th >&nbsp;</th>
					<s:iterator value="Whsheet">
					<th><s:property value="Time" /></th>
					</s:iterator>
				</tr>
				<tr align="center">
				
					<td rowspan='2'><a href="SheetPeople1.html"><s:property value="staName"/></a></td>							
					<td rowspan='2'><s:property value="staNo"/></td>
					<td>工时(小时)</td>
					<s:iterator value="Whsheet">				
					<td><s:property value="WorkHours" /></td>	
					</s:iterator>
				</tr>
				<tr align="center">
					<td>报酬(元)</td>
					<s:iterator value="Whsheet">
					<td><s:property value="Salary" /></td>
					</s:iterator>
				</tr>
				
				
		</table>
		
	</div>
  </body>
</html>
