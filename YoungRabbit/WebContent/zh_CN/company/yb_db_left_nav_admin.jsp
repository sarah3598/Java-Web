<%@ page language="java" import="java.util.*,com.csh.servlet.LoginControlPublicServlet" pageEncoding="utf-8"%>
<%@include file="yb_db_session_null.jsp"%>
<%
	String left_nav_path = request.getContextPath();
	String nav_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ left_nav_path + "/";
	Object USER_ID=LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
	Object USERNAME=LoginControlPublicServlet.getSessionKeyValue(request, response, "USERNAME");
%>

<div id="nav" class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="<%=left_nav_path%>/company/user_update">当前用户：<%=USERNAME %> <span class="sr-only"></span></a></li>
		<li><a href="<%=left_nav_path%>/company/notification_manage">通知</a></li>
		<li><a href="<%=left_nav_path%>/company/slide_manage">网站管理</a></li>
		<li><a href="<%=left_nav_path%>/company/grade_rule_manage">规则管理</a></li>
		<li><a href="<%=left_nav_path%>/company/staff_salary_manage">薪资管理</a></li>
		<li><a href="<%=left_nav_path%>/company/sales_manage">用户管理</a></li>
		<li><a href="<%=left_nav_path%>/company/team_manage?method=vp_team_list">团队管理</a></li>
		<li><a href="<%=left_nav_path%>/company/apply_admin">申请管理</a></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="<%=left_nav_path%>/company/product_manage?method=product_list">产品管理</a></li>
		<li><a href="<%=left_nav_path%>/company/product_manage?method=product_stock_avai_list">库存管理</a></li>
		<li><a href="<%=left_nav_path%>/company/orders_manage?method=order_list">订单管理</a></li>
		<!--  <li><a href="<%=left_nav_path%>/zh_CN/company/yb_db_accounts.jsp">统计</a></li>
		<li><a href="<%=left_nav_path%>/zh_CN/company/yb_db_tools.jsp">工具</a></li>
		<li><a href="#">设置</a></li>-->
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="<%=left_nav_path%>/customer/product?method=product_list">销售链接</a></li>
	</ul>
	<ul class="nav nav-sidebar">
		<li><a href="#">其它</a></li>
	</ul>
</div>