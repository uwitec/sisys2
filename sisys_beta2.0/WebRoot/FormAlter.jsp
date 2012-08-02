<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

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

</head>

<body>
	<div id="body-wrapper">
		<!-- Wrapper for the radial gradient background -->

		<jsp:include flush="true" page="banner_admin.jsp"></jsp:include>

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

					<h3>工单修改</h3>


					<div class="clear"></div>

				</div>
				<!-- End .content-box-header -->

				<div class="content-box-content">

					<div class="tab-content default-tab" id="tab1">
						<!-- This is the target div. id must match the href of this div's tab -->

						<form id="" method="" action="">
							<table class="">
								<tr>
									<td><span>姓名</span></td>
									<td><input type="text" width="50px"></td>
									<td><span>工号</span></td>
									<td><input type="text" width="50px"></td>
									<td><span>所属生产线</span></td>
									<td><input type="text" width="50px"></td>
								</tr>
								<tr>
									<td><span>产品名称</span></td>
									<td><input type="text" width="50px"></td>
									<td><span>批次号</span></td>
									<td><input type="text" width="50px"></td>
									<td><span>工序名称</span></td>
									<td><input type="text" width="50px"></td>
								</tr>
								<tr>
									<td><span>产品总数</span></td>
									<td><input type="text" width="50px"></td>
									<td><span>合格品数量</span></td>
									<td><input type="text" width="50px"></td>
								</tr>
								<tr>
									<td><span>不合格品数量</span></td>
									<td><input type="text" width="50px"></td>
									<td><span>不合格品种类</span></td>
									<td><select name="" class="" style="width: 155px">
											<option value="option1">种类一</option>
											<option value="option2">种类二</option>
											<option value="option3">种类三</option>
											<option value="option4">添加种类</option>
									</select></td>
								</tr>
								<tr>
									<td><input class="button" type="submit" value="提交"></td>
									<td><input class="button" type="reset" value="重置"></td>
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

		</div>
		<!-- End #main-content -->

	</div>
</body>


<!-- Download From www.exet.tk-->
</html>
