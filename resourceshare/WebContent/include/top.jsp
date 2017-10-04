<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
		
  </head>
  
  <body>
		<div class="top" id="top">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav" id="navBar">
	        <li class="active" id="nav-index"><a href="index.html">首页</a></li>
	        <li id="nav-friends"><a href="index.html">教学视频</a></li>
	        <li id="nav-people"><a href="index.html">教案课件</a></li>
	        <li id="nav-photos"><a href="index.html">学生论坛</a></li>
	      </ul>
	      <ul class="nav navbar-nav navbar-right">
	          <li><a href="loginselect.jsp">登录</a></li>   
	          <li><a href="register.jsp">注册</a></li>
              <li><a href="managerlogin.jsp">后台管理</a></li>
	      </ul>
	    </div>
		</div>
  </body>
</html>
