<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta http-equiv="refresh" content="3;url=login.jsp"/>
	<style type="text/css">
		body{
			color:green;
			text-align:center;
			margin:220px auto;
		}
		a{
			color:green;
		}
	</style>
	<script>
		function jump(){
			var num = parseInt(document.getElementById("time").innerHTML);
			num--;
			if(num > 0){
				document.getElementById("time").innerHTML = num;
			}
			window.setTimeout("jump()",1000);
		}
	</script>

  </head>
  
  <body onload="jump()">
     <h1>恭喜您注册成功！</h1>
     <h2><a href="login.jsp">系统将在<span id="time">4</span>秒后自动跳转到登录页面。如未跳转，请点击本链接跳转。</a></h2>
  </body>
</html>
