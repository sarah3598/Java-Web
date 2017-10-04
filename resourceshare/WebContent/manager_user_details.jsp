<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
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
    
    <title>自学网后台-用户详情</title>
    
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
	<style>
		.middle_adduser{
			width:540px; 
			margin:20px auto; 
			background:#F5F5F5; 
			box-shadow:1px 2px 3px #CCCCCC
		}
		.submit{
			width:100px;
			height:32px;
			padding:0px;
			border:1px solid skyblue;
			background:skyblue;
			color:#FFF;
			font-weight:bold;
		}
	</style>

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
			<div class="middle_menu" style="height:800px;">
				<jsp:include page="manager/left.jsp"></jsp:include>    		
			</div>
			<div class="middle_right">
				<div class="middle_index">
					<div class="index_titlename">用户详情</div>
					<div class="middle_search">
						<jsp:include page="manager/search.jsp"></jsp:include>
					</div>
				</div>
				<div class="middle_main">
					<div class="middle_adduser">
					    <table border="1px" cellspacing="0px" bordercolor="#FFF">
					        <tr height="50px">
					            <td width="160px">&nbsp;用户编号：</td>
					            <td width="380px">&nbsp;${user.userid}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;用户名：</td>
					            <td>&nbsp;${user.username}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;昵称：</td>
					            <td>&nbsp;${user.nickname}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;用户名邮箱：</td>
					            <td>&nbsp;${user.useremail}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;用户移动电话：</td>
					            <td>&nbsp;${user.mobile}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;姓名：</td>
					            <td>&nbsp;${user.relname}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;用户性别：</td>
					            <td>&nbsp;${user.sex}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;所在城市：</td>
					            <td>&nbsp;${user.city}</td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;所在地址：</td>
					            <td>&nbsp;${user.address}</td>
					        </tr>
					        			        						        						        						        						       						        						        
					        
					        <tr height="50px">
					            <td>&nbsp;用户积分：</td>
					            <td>&nbsp;${user.score}</td>
					        </tr>						     						        
					        <tr height="52px" align="center">
					            <td colspan="2"><input type="button" value="返回" class="submit" id="submit"/></td>
					        </tr>
					    </table>
					</div>
				</div>
			 </div>
		</div>
		<script src="./js/manager_user_add.js"></script>
	</body>
</html>
