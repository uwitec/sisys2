<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	String cur = "";
	cur = request.getParameter("current");
 %>

<div id="sidebar"><div id="sidebar-wrapper"> <!-- Sidebar with logo and menu -->
			
	<h1 id="sidebar-title"><a href="#">生产物流统计系统</a></h1>
  
	<!-- Logo (221px wide) -->
	<a href="#"><img id="logo" src="resources/images/log0-l.png" alt="logo" /></a>
  
	<!-- Sidebar Profile links -->

	<ul id="main-nav">  <!-- Accordion Menu -->
		
		<li>
			<a href="NewIndex.action" class="nav-top-item no-submenu <%if(cur.equals("index")) out.println("current"); %>"> <!-- Add the class "current" to current menu item -->
				首页
			</a>       
		</li>
		
		<li>
			<a href="BatchAdd_admin.jsp?current=batchAdd" class="nav-top-item no-submenu <%if(cur.equals("batchAdd")) out.println("current"); %>">
				新建批次
			</a>       
		</li>
		
		<li> 
			<a href="#" class="nav-top-item <%if(cur.equals("workForm")) out.println("current"); %>">
			工单管理
			</a>
			<ul>
				<li><a href="searchByBatch_admin.jsp?current=workForm">工单列表</a></li>
				<li><a href="searchReForm_admin.jsp?current=workForm">返工工单列表</a></li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="nav-top-item <%if(cur.equals("sheet")) out.println("current"); %>">
				统计单管理
			</a>
			<ul>
				<li><a href="SearchJD_admin.jsp?current=sheet">生产进度统计单</a></li>
				<li><a href="SearchHour_admin.jsp?current=sheet">工时统计单</a></li>
				<li><a href="SearchProduct_admin.jsp?current=sheet">不合格品统计单</a></li>
				<li><a href="SearchPeople_admin.jsp?current=sheet">员工工作统计单</a></li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="nav-top-item <%if(cur.equals("outOfDue")) out.println("current"); %>"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
				超期批次管理
			</a>
			<ul>
				<li><a href="OutOfDue.jsp?current=outOfDue">超期批次处理</a></li>
				<li><a href="OutofDueSearch.jsp?current=outOfDue">超期批次查询</a></li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="nav-top-item <%if(cur.equals("database")) out.println("current"); %>">
				数据库管理
			</a>
			<ul>
				<li><a href="DataSave.jsp?current=database">数据备份</a></li>
				<li><a href="DataLogin.jsp?current=database">数据导入</a></li>
			</ul>
		</li>
		
		<li>
			<a href="#" class="nav-top-item <%if(cur.equals("basedata")) out.println("current"); %>">
				基本信息录入
			</a>
			<ul>
				<li><a href="StaffImport.jsp?current=basedata">员工表</a></li>
				<li><a href="ProLineImport.jsp?current=basedata">生产线编码表</a></li>
				<li><a href="ProImport.jsp?current=basedata">产品成本表</a></li>
			</ul>
		</li>
		
		<li>
			<a href="UserAdd.jsp?current=userAdd" class="nav-top-item no-submenu <%if(cur.equals("userAdd")) out.println("current"); %>"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
				添加用户
			</a>
		</li>
		
		<li>
			<a href="AdminInfo_admin.jsp?current=pim" class="nav-top-item no-submenu <%if(cur.equals("pim")) out.println("current"); %>"> <!-- Add the class "no-submenu" to menu items with no sub menu -->
				个人信息管理
			</a>
		</li>
		     
		
	</ul> <!-- End #main-nav -->
	
	
</div></div> <!-- End #sidebar -->