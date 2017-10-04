<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>中小学课后辅导平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
	<link href="css/self.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="./css/manager_index.css"/>
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<header>
	<nav class="navbar head-default" role="navigation">
	  <div class="container">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand disabled" href="#">中小学课后自学网</a>
	    </div>

	    
	    <% if(session.getAttribute("manager") == null){%>
			<jsp:include page="include/top.jsp"></jsp:include>
		<%}else{ %>
			<jsp:include page="include/topm.jsp"></jsp:include>
		<%} %>
	    <!-- /.navbar-collapse -->
	  </div><!-- /container -->
	</nav><!--导航结束-->
</header>
<main>
	<div class="middle">
	
	<div class="middle_menu">
	    	<jsp:include page="manager/left.jsp"></jsp:include>    		
	    </div>
		<div class="row self-box">
			<div class="col-sm-9 pull-right">
					<table border="1px" width="100%" cellspacing="0px" bordercolor="#FFF">
							<tr height="38px" align="center" style="font-weight:bold">
								<td width="20%">用户名</td>
								<td width="10%">性别</td>
								<td width="20%">手机</td>
								<td width="20%">注册时间</td>
								<td width="30%">操作</td>							
							</tr>
							<c:forEach var="u" items="${requestScope.userlist}">
								<tr align="center" height="30px">
									<td>${u.username }</td>
									<td>${u.sex}</td>
									<td>${u.mobile}</td>
									<td>${u.adddate}</td>
									<td align="center" width="25%">
										<a href="User.do?op=detail&username=${u.username }"><font color="#2894FF">详情</font></a>&nbsp;&nbsp;
										<a href="#"><font color="#2894FF"><del>修改</del></font></a>
								</tr>
							</c:forEach>
							<tr align="center">
								<td colspan="6" height="36px;">
									<div style="position: absolute; margin-left:55%;">
										<form action="#" method="get" name="myform">
											<input type="text" value="1" class="middle_num"/><input type="submit" value="Go" class="middle_submit"/>
										</form>
									</div>
									<a href="">首页</a> &nbsp;&nbsp;&nbsp;
									<a href="">上页</a> &nbsp;&nbsp;&nbsp;
									<a href="">下页</a> &nbsp;&nbsp;&nbsp;
									<a href="">末页</a>
								</td>
							</tr>	
						</table>     
			</div>	<!-- 用户列表 -->		
			
		</div>
	</div>
</main>
<footer>
	<div class="container">
		
    <p class="jy_link">
		<a href="">武汉中学</a>
        <a href="">华师一附中</a>
       <a href="">武汉外国语学校</a>
	</p>
	<p>Copyright (C) 2017 All Rights Reserved. 项目六组 版权所有</p>
	</div>
</footer>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/birthday.js"></script>
<script src="js/self.js"></script>
<script>  
$(function () {
	$("#navBar li").removeClass("active");
	$.ms_DatePicker({
            YearSelector: ".sel_year",
            MonthSelector: ".sel_month",
            DaySelector: ".sel_day"
    });
	$.ms_DatePicker();
}); 

</script> 
</body>
</html>