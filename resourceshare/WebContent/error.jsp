<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>中小学课后自学网-页面出错误了</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
  	<div style="margin:160px 550px;">
  		<img src="./images/util/error.jpg" width="260px"/>
  		<input type="button" value="返回" onclick="javascript:history.go(-1)" style="width:100px; height:32px; border:0px; background:#C00; color:#FFF; margin:30px 80px;"> 
  	</div>
  </body>
</html>
