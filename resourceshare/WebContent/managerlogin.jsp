<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="com.util.*,com.dao.DBHelper,java.net.*"%>

<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	String userName = "";
	String userPwd = "";
	Cookie[] cookie = request.getCookies();
	if(cookie != null && cookie.length > 0){
		for(Cookie c: cookie){
			if(c.getName().equals("managerName")){
				userName = URLDecoder.decode(c.getValue(),"utf-8");
			}
			if(c.getName().equals("managerPwd")){
				userPwd = URLDecoder.decode(c.getValue(),"utf-8");
			}	
		}						
	}
%>
<!doctype html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>中小学课后自学网</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/sign.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<main class="col-sm-4 col-sm-offset-4">
	<form class="form-horizontal" role="form" action="ManagerLogin.do" method="post" name="form1">
	<div>管理员登录</div>
	  <div style="height:80px;">
			<div style="height:15px;"></div>
			<div id="msg" class="errmsg">${requestScope.errMsg}</div>
	 </div>
	  <div class="form-group">
	    <label for="userName" class="col-sm-3 control-label">用户名</label>
	    <div class="col-sm-8">
	      <input type="text" class="form-control" id="managerName" name="managerName" placeholder="请输入用户名..." value="<%=userName %>">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="password" class="col-sm-3 control-label">密码</label>
	    <div class="col-sm-8">
	      <input type="password" class="form-control" id="managerPwd" name="managerPwd" placeholder="请输入密码..." value="<%=userPwd %>">
	    </div>
	  </div>
	  <div class="checkbox col-sm-offset-3">
	    <label>
	      <input type="checkbox" id="checkboxID" name="checkboxID" checked="checked"/>下次自动登录
	    </label>
	    <a href="up_pwd_username.jsp" target="_blank">忘记密码？</a>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-3 col-sm-4 col-xs-5">
	      <button type="submit" class="btn sign">登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
	    </div>
	  </div>
	</form>        
</main>
<footer class="col-sm-6 col-sm-offset-3">
	<ul class="list-inline">
	  <li><a href="index.jsp">回到首页</a></li>
	  <li><a href="index.jsp">联系我们</a></li>
	  <li><a href="index.jsp">帮助</a></li>
	</ul>
</footer>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
window.onload = function(){
	var errmsgText = document.getElementById("msg").innerText;
	var errmsg = document.getElementById("msg");

	if(errmsgText != "" && errmsgText != null){
		errmsg.style.display = 'block'
	}
}
	$(function(){
		$("#navBar li").removeClass("active");
	})
</script>
</body>
</html>