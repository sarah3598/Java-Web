<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>自学网-教师注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/sign.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/user_register.css">
	<script src="js/register.js"></script>
	
    <script type="text/javascript">
	    window.onload=function(){
	    	createCode();
	    	var phoneVal = document.getElementById("phonemsg").innerText;
	    	if(phoneVal== "账号已存在"){
	    		document.form1.phone.style.borderColor = "red";
				document.getElementById("phonemsg").style.background = "#FFE1E1";
				document.getElementById("phonemsg").style.height = "38px";
				document.getElementById("phonemsg").style.border = "1px solid";
				document.getElementById("phonemsg").style.borderColor = "pink";	
				document.getElementById("phonemsg").innerHTML = '<br/>&nbsp;账号已存在';
	    	}
	    }
    </script>    
    
</head>
<body>
<jsp:useBean id="teacher" class="com.model.teacher.Teacher" scope="page"></jsp:useBean>
<main class="col-sm-6 col-sm-offset-3">
	<form class="form-horizontal" action="<%=path %>teacherregister2.jsp" method="post" name="form1" onSubmit="return form1All()">
	  				<div>
	  					<div>
							<span>手&nbsp;机&nbsp;号：</span>
							<input type="text"  id="phone" name="phone" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkPhone()" onfocus="getsFocus()"/>
							<span class="main_left_msg" id="phonemsg">${requestScope.errmsg }</span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
							<input type="password" id="pwd" name="pwd" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkPwd()" onfocus="getsFocus()"/>
							<span class="main_left_msg" id="pwdmsg"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>确认密码：</span>
							<input type="password" id="pwd2" name="pwd2" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkpwd2()" onfocus="getsFocus()"/>
							<span class="main_left_msg" id="pwd2msg"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>验&nbsp;证&nbsp;码：</span>
							<input type="text" id="yzm" name="yzm" style="width:125px; height:32px; border:1px solid; padding-left:5px;" onblur="validateCode()" onfocus="getsFocus()"/>&nbsp;<input type="button" id="checkCode" class="code" style="width:60px" onClick="twoMethod()"/>&nbsp;<a class="a_color" onClick="twoMethod()">换一张</a>
							<span class="main_left_msg" id="yzmmsg"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="submit" value="下一步"/ id="submit" style="color:#FFF; background:#09F; height:40px; width:260px; border:1px solid #09F; font-size:18px; font-weight:500;" onclick="form1All2()">
						</div>
	    <div class="col-sm-5 col-xs-7 exist">
	    	已有账号，前去<a href="login.html">登录</a>
	    </div>
	  </div>
	</form>        
</main>
<footer class="col-sm-6 col-sm-offset-3">
	<ul class="list-inline">
	  <li><a href="index.html">回到首页</a></li>
	  <li><a href="index.html">联系我们</a></li>
	  <li><a href="index.html">帮助</a></li>
	</ul>
</footer>

<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$("#navBar li").removeClass("active");
	})
</script>
<% 
	teacher.setMobile(request.getParameter("phone"));
	teacher.setPwd(request.getParameter("pwd2"));
   
	//放入 session,跳转到下一个页面  
    session.setAttribute("teacher1",teacher); 
   // response.sendRedirect("teacherregister2.jsp");  

%>  
</body>
</html>