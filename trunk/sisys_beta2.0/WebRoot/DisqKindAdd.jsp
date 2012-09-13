
<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String error = request.getParameter("result");
	if(error == "" || error == null) {
		error = "";
	} else if(error == "success") {
		error = "种类添加成功";
	} else if(error == "error"){
		error = "种类添加失败";
	}
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  	<script type="text/javascript"
	src="resources/scripts/addDisqKind.js"></script>
  	
    <base href="<%=basePath%>">
    
    <title>生产物流统计系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <p align="right"><a href="help.htm" target="_blank"><font size="7"></>使用说明</font></a></p>
    <form id="myForm" action="addDisqKind.action" method="post">
    	<label>
    		<%=error%>
    	</label>
    	
    	<table>
    		<tr>
    			<td>不合格种类名称</td>
    			<td><input type="text" name="disqKind.disDesc"></td>
    		</tr>
    		<tr>
    			<td><input type="radio" value="0" name="disqKind.kind" checked/>工废</td>
    			<td><input type="radio" value="1" name="disqKind.kind"/>料废</td>
    		</tr>
    		<tr>
    			<td><input class="button" type="submit" value="提交"></td>
				<td><input class="button" type="reset" value="重置"></td>
    	</table>
    </form>
  </body>
</html>

