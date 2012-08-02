<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="data.bean.DisqKind"%>
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
					<th rowspan=2 width=20%>姓名</th>
					<th rowspan=2 width=20%>工号</th>
					<th rowspan=2 width=20%>总量</th>
					<th colspan=<%=gType.size()%>  width=20%>工废</th>
					<th colspan=<%=lType.size()%> width=20%>料废</th>
				</tr>
				<tr>
					<%for(DisqKind g:gType){%>
						<th><%=g.getDisDesc() %></th>
					<%} for(DisqKind l:lType){%>
						<th><%=l.getDisDesc() %></th>
					<%} %>
				</tr>
				<%for(int i=0;i<pd3List.size();i++){
					Map<Integer,Object> dMap = (Map<Integer,Object>)pd3List.get(i).get("dMap");
				%>
				<tr align="center">
					<td><%=pd3List.get(i).get("staName") %></td>
					<td><a target="_blank" href=SearchPpByDisq?staNo=<%=pd3List.get(i).get("staNo")%>><%=pd3List.get(i).get("staNo")%></a></td>
					<td><%=pd3List.get(i).get("disqNum") %></td>
					<%for(DisqKind g:gType){ %>
						<%if(dMap.get(g.getId()) == null){ %>
							<td>&nbsp;</td>
						<%}else{ %>
							<td><%=dMap.get(g.getId()) %></td>
					<%}} %>
					<%for(DisqKind l:lType){ %>
						<%if(dMap.get(l.getId()) == null){ %>
							<td>&nbsp;</td>
						<%}else{ %>
							<td><%=dMap.get(l.getId()) %></td>
					<%}} %>
				</tr>
				<%} %>
				
				

			</table>
	</div>
  </body>
</html>
