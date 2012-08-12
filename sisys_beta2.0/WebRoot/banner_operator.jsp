<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String cur = "";
	cur = request.getParameter("current");
 %>
    
<div id="sidebar">
	<div id="sidebar-wrapper">
		<!-- Sidebar with logo and menu -->

		<h1 id="sidebar-title">
			<a href="#">生产物流统计系统</a>
		</h1>

		<!-- Logo (221px wide) -->
		<a href="#"><img id="logo" src="resources/images/log0-l.png"
			alt="logo" /></a>

		<!-- Sidebar Profile links -->

		<ul id="main-nav">
			<!-- Accordion Menu -->

			<li><a href="NewIndex.action"
				class="nav-top-item no-submenu <%if(cur.equals("index")) out.println("current"); %>"> <!-- Add the class "current" to current menu item -->
					首页
			</a></li>
			<li>
				<a href="BatchAdd_operator.jsp?current=batchAdd" class="nav-top-item no-submenu <%if(cur.equals("batchAdd")) out.println("current"); %>">
					新建批次
				</a>       
			</li>

			<li><a href="#" class="nav-top-item <%if(cur.equals("workForm")) out.println("current"); %>"> 工单管理 </a>
				<ul>
					<li><a href="BeforeAdd.jsp?current=workForm">添加工单</a></li>
					<li><a href="reformAdd.jsp?current=workForm">添加返工工单</a></li>
					<li><a href="searchByBatch_operator.jsp?current=workForm">工单列表</a></li>
					<li><a href="searchReForm_operator.jsp?current=workForm">返工工单列表</a></li>
				</ul></li>

			<li><a href="#" class="nav-top-item <%if(cur.equals("sheet")) out.println("current"); %>"> 统计单管理 </a>
				<ul>
					<li><a href="SearchJD_operator.jsp?current=sheet">生产进度统计单</a></li>
					<li><a href="SearchHour_operator.jsp?current=sheet">工时统计单</a></li>
					<li><a href="SearchProduct_operator.jsp?current=sheet">不合格品统计单</a></li>
					<li><a href="SearchPeople_operator.jsp?current=sheet">员工工作统计单</a></li>
				</ul></li>

			<li><a href="AdminInfo_operator.jsp?current=pim"
				class="nav-top-item <%if(cur.equals("pim")) out.println("current"); %> no-submenu "> <!-- Add the class "no-submenu" to menu items with no sub menu -->
					个人信息管理
			</a></li>


		</ul>
		<!-- End #main-nav -->


	</div>
</div>
<!-- End #sidebar -->