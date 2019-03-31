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
    
    <title>自学网后台-添加教师</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
	<link href="css/self.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="./css/manager_index.css"/>
	<script src="./js/manager_util.js"></script>
	
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
		.msg{
			width:160px; 
			height:26px; 
			margin:-26px 220px; 
			position:absolute;
			color:red;
			font-size:2px;
		}
		.succeed{
			width:320px; 
			height:80px;
			font-size:30px;
			color:#093;
			font-weight:bold;
			background:#DFDFDF;
			line-height:80px;
			text-align:center;
			border-radius:10px;
			position:absolute;
			z-index:20;
			margin:30px 380px;
		}
		input{
			width:180px;
			height:30px;
			padding-left:5px;
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
			<div class="middle_menu">
				<jsp:include page="manager/left.jsp"></jsp:include>    		
			</div>
			<div class="middle_right">
				<div class="middle_index">
					<div class="index_titlename">添加用户</div>
					<div class="middle_search">
						<jsp:include page="manager/search.jsp"></jsp:include>
					</div>
				</div>
				<div class="middle_main">
					<div class="middle_adduser">
						 <form action="User.do?op=adduser" method="post" onSubmit="return All()">
						    <table border="1px" cellspacing="0px" bordercolor="#FFF">
						        <tr height="50px">
						            <td width="160px">&nbsp;请输入账号：</td>
						            <td width="380px">&nbsp;
						                <input type="text" size="18" name="username" id="username" onBlur="userName()" placeholder="用户名"/>
						                <div class="msg" id="usernamemsg">${requestScope.errmsg}</div>
						            </td>
						        </tr>
						        <tr height="50px">
						            <td>&nbsp;请输入密码：</td>
						            <td>&nbsp;
						                <input type="password" size="18" name="pwd" id="pwd" onBlur="Pwd()" placeholder="密码"/>
						                <div class="msg" id="pwdmsg"></div>
						            </td>
						        </tr>
						        <tr height="50px">
						            <td>&nbsp;请输入确认密码：</td>
						            <td>&nbsp;
						                <input type="password" size="18" name="rpwd" id="rpwd" onBlur="rPwd()" placeholder="确认密码"/>
						                <div class="msg" id="rpwdmsg"></div>
						            </td>
						        </tr>
						        <tr height="50px">
						            <td>&nbsp;请输入用户类型:</td>
						            <td>&nbsp;
						                <select name="type">
						                    <option>--VIP--</option>
						                    <option>是</option>
						                    <option>否</option>
						                </select>
						            </td>
						        </tr>
						        <tr height="52px" align="center">
						            <td colspan="2"><input type="submit" value="确认" class="submit"/></td>
						        </tr>
						    </table>
						</form>
					</div>
					<div class="succeed" id="succeed">${requestScope.succeedmsg}</div>
				</div>
			 </div>
		</div>
		<script src="./js/manager_user_add.js"></script>
	</body>
</html>
