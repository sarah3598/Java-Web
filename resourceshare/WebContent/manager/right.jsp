<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'right.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="./css/manager_index.css"/>
	<style type="text/css">
		.succeed{
			width:380px; 
			height:80px;
			font-size:30px;
			color:red;
			font-weight:bold;
			background:#DFDFDF;
			line-height:80px;
			text-align:center;
			border-radius:10px;
			position:absolute;
			z-index:20;
			margin:-100px 380px;
		}
	</style>
  </head>
  
	<body>
		<div class="middle_index">
			<div class="index_titlename">首页</div>
		    
		</div>
		<div>
			<div style="margin:120px 460px;">
				<img src="./images/managerindex/managerindex.jpg" width="280px"/>
			</div>
			<div class="succeed" id="succeed">${requestScope.errmsg}</div>
		</div>
	</body>
</html>
