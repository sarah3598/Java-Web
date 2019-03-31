<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/common.css" rel="stylesheet">
	<link href="./css/self.css" rel="stylesheet">
</head>
<body>
<div class="col-sm-3 pull-left">
				<p class="self-center">后台管理</p>
				<ul class="list-group self-lists" id="selfList">
					<li class="active" id="nav-self"><a href="User.do?op=findAll">用户列表</a></li>
					<li id="nav-letter"><a href="letter.html">我的私信</a></li>
					<li id="nav-albums"><a href="albums.html">我的相册</a></li>
					<li id="nav-dynamic"><a href="selfDynamic.html">我的动态</a></li>
					<li id="nav-setting"><a href="setting.html">账号设置</a></li>
				</ul>
</div>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/birthday.js"></script>
<script src="js/self.js"></script>
</body>
</html>