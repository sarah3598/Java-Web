<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String nav_path = request.getContextPath();
	String nav_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + nav_path + "/";
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
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_blog.jsp" title="Message">博客</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_join_us.jsp" title="Join Us">加入我们</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="#">语言选择</a></li>
				<li class="seperator hidden-xs"></li>
				<li><a href="<%=nav_path%>/zh_CN/customer/yb_user_login.jsp" class="loginlink"> <input value="登录   / 注册" class="btn btn-login" type="submit"></a></li>			
				<li><form id="search_form" class="navbar-form navbar-right">
						<input style="height: 35px;" type="text" value="" placeholder="输入搜索内容" class="form-control" />
					</form></li>
			</ul>

		</div>

	</div>
</div>