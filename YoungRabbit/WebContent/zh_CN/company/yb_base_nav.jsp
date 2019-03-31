<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String nav_path = request.getContextPath();
	String nav_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ nav_path + "/";
%>
<div id="main-nav" class="navbar navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#site-nav">
				<span class="icon-bar"></span><span class="icon-bar"></span><span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="http://#" title="Redbeard"> <span
				class="logo-small"><img src="<%=nav_path%>/image/Logo.png"
					alt=""></span>
			</a>
		</div>
		<div id="site-nav" class="navbar-collapse collapse">
			<%--<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_home_page.jsp"
					title="主页">主页</a></li>
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_product_page.jsp"
					title="产品">产品</a></li>
				<li><a href="http://#" title="公司概况">公司概况</a></li>
				<li><a href="<%=nav_path%>/company/register" title="加入我们">加入我们</a>
				</li>
				<li><a href="#" title="Agent List">销售商</a></li>
				<li><a href="#" title="Message">博客</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="<%=nav_path%>/zh_CN/company/yb_user_login.jsp"
					class="loginlink"> <input value="登录" class="btn btn-login"
						type="submit">
				</a></li>
			</ul> --%>
		</div>
	</div>
</div>