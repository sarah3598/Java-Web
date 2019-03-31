<%@ page language="java" import="java.util.*,com.csh.servlet.LoginControlPublicServlet,com.csh.servlet.PublicMessageServlet" pageEncoding="utf-8"%>
<%@include file="yb_session_null.jsp"%>
<%
	String nav_path = request.getContextPath();
	String nav_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + nav_path + "/";
	Object USERNAME=LoginControlPublicServlet.getSessionKeyValue(request, response, "USERNAME");
    int msg_num=0;
    //msg_num=PublicMessageServlet.getMessageCount(request);
%>

<div id="main-nav" class="navbar navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#site-nav">
				<span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
			</button>
			<a href="<%=nav_path%>/zh_CN/customer/yb_home_page.jsp" title="Redbeard"> <span class="logo-small"><img src="<%=nav_path%>/image/Logo.png" alt=""></span>
			</a>
		</div>

		<div id="site-nav" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">				
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_home_page.jsp" title="Home">主页</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_about_us.jsp" title="About Us">关于我们</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="<%=nav_path%>/customer/product?method=product_list" title="Products">产品</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="#" title="Agent List">销售商</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="#">语言选择</a></li>
				<li class="seperator hidden-xs"></li>
				<li role="presentation"><a href="<%=nav_path%>/customer/notice">消息通知 <span class="badge"><%=msg_num %></span></a></li>
				<li><a href="<%=nav_path%>/customer/orders_manage?method=order_list">我的订单</a></li>
				<li><a href="<%=nav_path%>/customer/user_update">个人信息(<%=USERNAME %>)</a></li>
				<li><a href="<%=nav_path %>/logout?OP=2">退出系统</a></li>
			</ul>

		</div>

	</div>
</div>