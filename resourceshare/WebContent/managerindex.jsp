<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	if(session.getAttribute("manager") == null){
		response.sendRedirect("managerlogin.jsp");
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  	<base href="<%=basePath%>">
   
  	<title>后台管理</title>
   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="./css/manager_index.css"/>
	<script src="./js/marager_index.js"></script>
	<script src="./js/manager_util.js"></script>
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
</head>
  
<body>
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
	  </div><!-- /container -->
	</nav><!--导航结束-->
    <div class="middle">
	    <div class="middle_menu">
	    	<jsp:include page="manager/left.jsp"></jsp:include>    		
	    </div>
	    <div class="middle_right">
	    	<jsp:include page="manager/right.jsp"></jsp:include>  
	    	<div class="bottom">
				Copyright© 中小学课后自学网2017-2018，All Rights Reserved
 			</div>
	    </div>
    </div>

</body>
</html>