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
	    <% if(session.getAttribute("user") == null){%>
			<jsp:include page="include/top.jsp"></jsp:include>
		<%}else{ %>
			<jsp:include page="include/top2.jsp"></jsp:include>
		<%} %>
	    <!-- /.navbar-collapse -->
	  </div><!-- /container -->
	</nav><!--导航结束-->
</header>
<main>
	<div class="container">
		<div class="row self-box">
			<div class="col-sm-9 pull-right">
				<form class="form-horizontal" action="UserUserServlet.do?op=userUpdate&username=${sessionScope.user.username }" method="post" name="form1">
				  <div class="form-group clearfix">
				    <label for="image" class="col-sm-3 control-label">头像</label>
				  	<div class="col-sm-8 avator">
					  	<img src="${user.picurl}" alt="">
				  		<p>保存头像</p>
					  	<input type="file" name="picurl" onchange="loadfile(this);">
				  	</div>
				  </div>
				  <div class="form-group">
				    <label for="userName" class="col-sm-3 control-label">用户名</label>
				    <div class="col-sm-8">
				      <input name="userName" type="text" class="form-control" id="userName" value="${sessionScope.user.username}" readonly>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="nickName" class="col-sm-3 control-label">昵称</label>
				    <div class="col-sm-8">
				      <input type="text" name="nickName" class="form-control" id="nickName" value="${sessionScope.user.nickname}">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="relName" class="col-sm-3 control-label">真实姓名</label>
				    <div class="col-sm-8">
				      <input type="text" name="relName" class="form-control" id="relName" value="${sessionScope.user.relname}">
				    </div>
				  </div>
				    <div class="form-group">
				    <label for="sex" class="col-sm-3 control-label">性别</label>
				    <div class="col-sm-8">
				    	<input type="radio"  name="sex" value="女"   checked="${sessionScope.user.sex=='女'?"'checked'":''}"/>女
				    	<input type="radio"  name="sex" value="男"   checked="${sessionScope.user.sex=='男'?"'checked'":''}"/>男
				    	<input type="radio"  name="sex" value="保密"   checked="${sessionScope.user.sex=='保密'?"'checked'":''}"/>保密
				    </div>
				  </div>
				
				 <div class="form-group">
				    <label for="userEmail" class="col-sm-3 control-label">邮箱</label>
				    <div class="col-sm-8">
				      <input type="text" name="userEmail" class="form-control" id="userEmail" value="${sessionScope.user.useremail}">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="mobile" class="col-sm-3 control-label">手机号</label>
				    <div class="col-sm-8 inputPhone">
				      <input type="text" name="mobile" class="form-control" id="mobile" value="${sessionScope.user.mobile}">
				    </div>
				  </div>
				  
				  <div class="form-group">
				    <label for="city" class="col-sm-3 control-label">所在城市</label>
				    <div class="col-sm-8">
				      <input type="text" name="city" class="form-control" id="city" value="${sessionScope.user.city}">
				    </div>
				  </div>
				  
				  <div class="form-group">
				   <label for="education" class="col-sm-3 control-label">生日</label>
				   <div class="col-sm-8">
			        <select class="sel_year" rel="2000"></select>年
			        <select class="sel_month" rel="2"></select>月
			        <select class="sel_day" rel="14"></select>日
				    </div>
				  </div>
				  
				   <div class="form-group">
				    <label for="score" class="col-sm-3 control-label">用户积分</label>
				    <div class="col-sm-8">
				      <input type="text" name="city" class="form-control" id="score" value="${sessionScope.user.score}" readonly>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-3 col-sm-4 col-xs-5">
				      <button type="submit" class="btn">保&nbsp;&nbsp;&nbsp;&nbsp;存</button>
				    </div>
				  </div>
				</form>        
			</div>			
			<div class="col-sm-3 pull-left">
				<p class="self-center">个人中心</p>
				<ul class="list-group self-lists" id="selfList">
					<li class="active" id="nav-self"><a href="self.html">个人资料</a></li>
					<li id="nav-letter"><a href="letter.html">我的私信</a></li>
					<li id="nav-albums"><a href="albums.html">我的相册</a></li>
					<li id="nav-dynamic"><a href="selfDynamic.html">我的动态</a></li>
					<li id="nav-setting"><a href="setting.html">账号设置</a></li>
				</ul>
			</div>
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