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
		
		
		<li><a href="<%=left_nav_path%>/company/sale_report">销售报表</a></li>
		<li><a href="<%=left_nav_path%>/customer/product?method=product_list">销售链接</a></li>
	</ul>
	
</div>