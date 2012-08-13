<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    <h2 align="center" id="tableTitle"><s:property value="proName"/>(<s:property value="proNo"/>)废品统计表(时间:<s:property value="sTime" /></th>-<th><s:property value="eTime" /></th>)</h2>
	<form name="tableExport" method="POST" action="tableExport.action">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
		<div align="center"><input  onclick=tableExportor('MainTable') class="button" type="button" value="导出"></div>
	</form>
	<br></br>
	<div align="center">
		<table cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" id="MainTable">
				<tr align="center">
					<th width=25%>批次号</th>
					<th width=25%>总数量</th>
					<th width=25%>不合格品数量</th>
					<th width=25%>不合格品率</th>
				</tr>
				
				<s:iterator value="Pdsheet">
				<tr align="center">				
					<td><s:property value="batchNo"/></td>	
					<td><s:property value="totalNum" /></td>	
					<td><s:property value="disqNum"/></td>	
					<td><s:property value="disqPercent"/></td>
				</tr>	
				</s:iterator>
				
				<tr align="center">
					<td>合计</td>
					<td><s:property value="TcompleteNum"/></td>
					<td><s:property value="TdisqNum"/></td>
					<td><s:property value="TdisqPercent"/></td>
				</tr>

			</table>
	</div>
  </body>
</html>
