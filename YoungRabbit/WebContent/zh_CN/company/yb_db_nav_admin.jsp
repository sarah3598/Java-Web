
<%@ page language="java" import="java.util.*,com.csh.servlet.PublicMessageServlet,com.csh.servlet.LoginControlPublicServlet" pageEncoding="utf-8"%>
<%
	String base_nav_path = request.getContextPath();
	String base_nav_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + base_nav_path + "/";
	int msg_num = 0;
	msg_num = PublicMessageServlet.getMessageCount(request);
	Object USER_ID = LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
	Object USERNAME = LoginControlPublicServlet.getSessionKeyValue(request, response, "USERNAME");
%>

<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<%=base_nav_path%>/company/user_update"><%=USERNAME%></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
			<!--<li><a href="<%=base_nav_path%>/company/user_update">当前用户：<%=USERNAME%> <span class="sr-only"></span></a></li>-->
				<li><a href="<%=base_nav_path%>/company/slide_manage">网站管理</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">销售员管理<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="<%=base_nav_path%>/company/basic_rule_manage">规则管理</a></li>
						<li><a href="<%=base_nav_path%>/company/staff_salary_manage">薪资管理</a></li>
						<li><a href="<%=base_nav_path%>/company/sales_manage">用户管理</a></li>
						<li><a href="<%=base_nav_path%>/company/team_manage?method=team_list">团队管理</a></li>
						<li><a href="<%=base_nav_path%>/company/team_manage?method=train_team_list">培训管理</a></li>
						<li><a href="<%=base_nav_path%>/company/apply_admin">申请管理</a></li>
					</ul></li> 
				<li><a href="<%=base_nav_path%>/company/product_manage?method=product_list">产品管理</a></li>
				<li><a href="<%=base_nav_path%>/company/product_manage?method=product_stock_avai_list">库存管理</a></li>
				<li><a href="<%=base_nav_path%>/company/orders_manage?method=order_list">订单管理</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li role="presentation"><a href="<%=base_nav_path%>/company/notice">消息通知 <span class="badge"><%=msg_num%></span></a></li>

				<li><a href="<%=base_nav_path%>/company/user_update">个人信息</a></li>
				<li><a href="<%=base_nav_path%>/logout?OP=1">退出</a></li>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>