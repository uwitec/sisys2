<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme() + "://"  
    + request.getServerName() + ":" + request.getServerPort()  
    + path + "/";  
%>  
  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
<head>  
   <base href="<%=basePath%>">  
</head>  
<%  
   out.clear();  
   out = pageContext.pushBody();  
   response.setContentType("application/pdf");  
  
   try {  
    String strPdfPath = new String("e:/gd.pdf");  
    //判断该路径下的文件是否存在  
    File file = new File(strPdfPath);  
    if (file.exists()) {  
     DataOutputStream temps = new DataOutputStream(response  
       .getOutputStream());  
     DataInputStream in = new DataInputStream(  
       new FileInputStream(strPdfPath));  
  
     byte[] b = new byte[2048];  
     while ((in.read(b)) != -1) {  
      temps.write(b);  
      temps.flush();  
     }  
  
     in.close();  
     temps.close();  
    } else {  
     out.print(strPdfPath + " 文件不存在!");  
    }  
  
   } catch (Exception e) {  
    out.println(e.getMessage());  
   }  
%>  

<body>  
   <br>  
</body>  
</html>
