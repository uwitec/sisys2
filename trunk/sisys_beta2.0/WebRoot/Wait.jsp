<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Wait.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->		
<style type="text/css">
.outer {
  #position: relative;
  width: 100px;
  height: 100px;
}
.outer div {
  width: 100%;
  text-align: center;
  #position: absolute;
  #top: 50%;
  *left: 0;
}
.outer div div {
  display: block;
  text-align: center;
  #position: relative;
  #top: -50%;
}
.outer div div img {
  vertical-align: middle;
}
.outer .inlinEl {
  display: table-cell;
  vertical-align: middle;
  width: 100px;
  height: 100px;
}
</style>

  </head>
  
  <body>
  <div id="div1"  class="outer" style="position:absolute;top:0px;left:0px;width:1200px;height:60000px;">
  <div>
    <div align="center">
      <span class="inlinEl">
        <img src="resources/images/shortcut-button-bg.gif"/>
      </span>
    </div>
  </div>
 </div>
  </body>
</html>
