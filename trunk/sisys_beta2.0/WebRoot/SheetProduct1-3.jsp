<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.sisys.bean.DisqKind"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	Map resultMap = (Map)request.getAttribute("resultMap");
	List<DisqKind> gType = (List<DisqKind>)resultMap.get("gWasteType");
	List<DisqKind> lType = (List<DisqKind>)resultMap.get("lWasteType");
	List<Map<String,Object>> pd3List = (List<Map<String,Object>>)resultMap.get("pd3List");
	
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
    <h2 align="center" id="tableTitle">员工废品统计表(时间:2012/06/02-2012/07/02)</h2>
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
					<th>总量</th>
					<s:iterator value="disqkind" id="dk">
					<th><s:property value="disDesc"/></th></s:iterator>				
				</tr>
				<s:iterator value="Pd3sheet" id="pd3">
				<tr align="center">				
					<td><s:property value="staName"/></td>
					<td><s:property value="staNo"/></td>	
					<td><s:property value="disqNum"/></td>	
					<s:set name="list1" value="%{pd3.disqTypeNum}"></s:set>
					<s:iterator value="%{list1}" id="type">
					</s:iterator>
				</tr>	
				</s:iterator>
				
				

			</table>
	</div>
  </body>
</html>
