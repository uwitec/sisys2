<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sisys.bean.DisqKind"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
    <h2 align="center" id="tableTitle">员工废品统计表(时间:<s:property value="sTime"/>-<s:property value="eTime"/>)</h2>
	<form name="tableExport" method="POST" action="tableExport.action">
		<input type="hidden" name="title">
		<input type="hidden" name="content">
		<div align="center"><input  onclick=tableExportor('MainTable') class="button" type="button" value="导出"></div>
	</form>
	<div align="center">
		<table cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series" id="MainTable">
				<tr align="center">
					<th>姓名</th>
					<th>工号</th>					
					<s:iterator value="disqkind">
					<th><s:property value="disDesc"/></th>
					</s:iterator>	
					<th>总量</th>			
				</tr>
				<s:iterator value="disqStaff" id="pd3">
				<tr align="center">				
					<td><s:property value="#pd3.staName"/></td>
					<td><a target="_blank" href=SearchPpByDisq?staNo=<s:property value="staNo"/>><s:property value="staNo"/></a></td>		
					<s:set name="list1" value="#pd3.disqTypeNum"></s:set>
					<s:iterator value="#list1" id="type">
					<td><s:property/></td>
					</s:iterator>
				</tr>	
				</s:iterator>
				<tr align="center">
				<td colspan=2>合计</td>
				<s:iterator value="total">			
				<td><s:property/></td>	
				</s:iterator>
				</tr>

			</table>
	</div>
  </body>
</html>
