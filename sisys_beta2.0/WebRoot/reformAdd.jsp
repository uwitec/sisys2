<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sisys.bean.BackKind"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<%	
	String error = request.getAttribute("result")==null?null:request.getAttribute("result").toString();
	String indexResult = request.getParameter("indexResult");
	int flag = 0;
	if(error == null) {
		error = "";
	}else if(error.equals("error")) {
		error = "添加失败！";
	}else if(error.equals("batchError")) {
		error = "添加失败！批次号不存在！";
	}else if(error.equals("success")){
		error = "添加成功！";
	}
	if(indexResult != null && indexResult.equals("kindError")){
		error = "请先在数据库中添加返工类别！";
		flag = 1;
	}
	
	List<BackKind> kindList = (List<BackKind>)request.getAttribute("kindList");
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>生产物流统计系统</title>

<!--                       CSS                       -->

<!-- Reset Stylesheet -->
<link rel="stylesheet" href="resources/css/reset.css" type="text/css"
	media="screen" />

<!-- Main Stylesheet -->
<link rel="stylesheet" href="resources/css/style.css" type="text/css"
	media="screen" />

<!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
<link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
	media="screen" />

<!-- Colour Schemes
	  
		Default colour scheme is green. Uncomment prefered stylesheet to use it.
		
		<link rel="stylesheet" href="resources/css/blue.css" type="text/css" media="screen" />
		
		<link rel="stylesheet" href="resources/css/red.css" type="text/css" media="screen" />  
	 
		-->

<!-- Internet Explorer Fixes Stylesheet -->

<!--[if lte IE 7]>
			<link rel="stylesheet" href="resources/css/ie.css" type="text/css" media="screen" />
		<![endif]-->

<!--                       Javascripts                       -->

<script type="text/javascript">
function check(){
	var batNo = document.getElementById("batNo").value;
	var staName = document.getElementById("staName").value;
 	var proName = document.getElementById("proName").value;
 	var respName = document.getElementById("respName").value;
 	var checkName = document.getElementById("checkName").value;
 	var procName = document.getElementById("procName").value;
 	var kind = document.getElementById("kind").value;
 	if(staName=="该员工不存在" || proName=="该产品不存在" || procName=="该工序不存在" || respName=="该员工不存在" || checkName=="该员工不存在") {
 		alert("输入信息有误，请重新输入！");
 	} else if(batNo == "" || staName == "" || proName == "" || procName == "" || respName=="" || checkName=="" || kind == "") {
 		alert("输入信息不能为空！");
 	}
 	 else {
 		document.getElementById("myForm").submit();
 	}
}
</script>


<script type="text/javascript"
	src="resources/scripts/addWorkForm.js"></script>

<script type="text/javascript"
	src="resources/scripts/addDisqKind.js"></script>

<!-- jQuery -->
<script type="text/javascript"
	src="resources/scripts/jquery-1.3.2.min.js"></script>

<!-- jQuery Configuration -->
<script type="text/javascript"
	src="resources/scripts/simpla.jquery.configuration.js"></script>

<!-- Facebox jQuery Plugin -->
<script type="text/javascript" src="resources/scripts/facebox.js"></script>

<!-- jQuery WYSIWYG Plugin -->
<script type="text/javascript" src="resources/scripts/jquery.wysiwyg.js"></script>

<!-- jQuery Datepicker Plugin -->
<script type="text/javascript"
	src="resources/scripts/jquery.datePicker.js"></script>
<script type="text/javascript" src="resources/scripts/jquery.date.js"></script>
<!--[if IE]><script type="text/javascript" src="resources/scripts/jquery.bgiframe.js"></script><![endif]-->


<!-- Internet Explorer .png-fix -->

<!--[if IE 6]>
			<script type="text/javascript" src="resources/scripts/DD_belatedPNG_0.0.7a.js"></script>
			<script type="text/javascript">
				DD_belatedPNG.fix('.png_bg, img, li');
			</script>
		<![endif]-->
		
<base target="_self" />

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->
	
	
		<jsp:include flush="true" page="banner_operator.jsp"></jsp:include>
	
		<div id="main-content">
			<!-- Main Content Section with everything -->

			<!-- Page Head -->
			<h2>Welcome!</h2>
			<p id="page-intro">What would you like to do?</p>

			<div class="clear"></div>
			<!-- End .clear -->

			<div class="content-box">
				<!-- Start Content Box -->

				<div class="content-box-header">

					<h3>工单添加</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->


						<label>
							<%=error%>
						</label>
						
						<form id="myForm" action="addBackForm.action" method="post">
							<table class="" id="table">
								<tr>
									<td><span>产品编号</span></td>
									<td><input type="text" width="50px" id="proNo" name="proNo" onblur="displayProNo()"/></td>
				
									<td><span>产品名称</span></td>
									<td><input readOnly="true" type="text" width="50px"  id="proName" name="proName"/></td>
								</tr>
								<tr>
									<td><span>批次号</span></td>
									<td><input type="text" width="50px" id="batNo" name="batchNo" /></td>
								</tr>
								<tr>
									<td><span>工序号</span></td>
									<td><input type="text" width="50px" id="procNo" name="procNo" onblur="displayProcNo()"/></td>
				
									<td><span>工序名称</span></td>
									<td><input readOnly="true" type="text" width="50px"  id="procName" name="procName"/></td>
								</tr>
								<tr>
									<td><span>员工工号</span></td>
									<td><input type="text" width="50px" id="staNo" name="staNo" onblur="displayStaNo()"/></td>
				
									<td><span>员工姓名</span></td>
									<td><input readOnly="true" type="text" width="50px"  id="staName" name="staName"/></td>
								</tr>
								<tr>
									<td><span>返工类别</span></td>
									<td>
										<select name="kind" id="kind">
									<option value="">----请选择--</option>
									<%if(flag == 0) {%>
									<%for(int i = 0;i < kindList.size();i++){%>
									<option value="<%=kindList.get(i).getName() %>"><%=kindList.get(i).getName() %></option>
									<%}} %>
								</select>
									</td>
									
									<td><span>返工工时</span></td>
									<td><input type="text" width="50px" id="workHours" name="workHours"/></td>
								</tr>
								<tr>
									<td><span>责任人工号</span></td>
									<td><input type="text" width="50px" id="respNo" name="respNo" onblur="displayRespNo()"/></td>
								
									<td><span>责任人姓名</span></td>
									<td><input readOnly="true" type="text" width="50px" id="respName" name="respName"/></td>
								</tr>
								<tr>
									<td><span>审批人工号</span></td>
									<td><input type="text" width="50px" id="checkNo" name="checkNo" onblur="displayCheckNo()"/></td>
								
									<td><span>审批人姓名</span></td>
									<td><input readOnly="true" type="text" width="50px" id="checkName" name="checkName"/></td>
								</tr>
								
								
								<tr>
									<td><input class="button" type="button" <%if(flag==0){ %>onclick="check()"<%}else{ %> onclick="<%=error %>" <%} %> value="提交"/></td>
									<td><input class="button" type="reset" value="重置"/></td>
								</tr>
							</table>


						</form>

					</div>
					<!-- End #tab1 -->



				</div>
				<!-- End .content-box-content -->

			</div>
			<!-- End .content-box -->

			<div class="clear"></div>

						<jsp:include flush="true" page="footer.jsp"></jsp:include>
						
			<!-- End #footer -->

		</div>
		<!-- End #main-content -->

	</div>
</body>


<!-- Download From www.exet.tk-->
</html>
