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
<main class="col-sm-6 col-sm-offset-3">
	<form class="form-horizontal" action="Teacher.do?op=addteacher" method="post" name="form1" onSubmit="return form1All()">
	  				<div>
	  				<div><input id="phone" name="phone" type="hidden" value="${sessionScope.teacher1.phone }" />  
	  				<input id="pwd" name="pwd" type="hidden" value="${sessionScope.teacher1.pwd2 }" /> 
	  				
	  				</div>
	  					<div>
							<span>真实姓名：</span>
							
							<input type="text"  id="relname" name="relname" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkPhone()" onfocus="getsFocus()"/>
							<span class="main_left_msg" id="phonemsg">${requestScope.errmsg }</span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>职&nbsp;&nbsp;&nbsp;&nbsp;称：</span>
							<input type="text" id="jobtitle" name="jobtitle" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkPwd()" onfocus="getsFocus()"/>
							
						</div>
						
						<div style="height:25px;"></div>
						<div>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="submit" value="同意并注册"/ id="submit" style="color:#FFF; background:#09F; height:40px; width:260px; border:1px solid #09F; font-size:18px; font-weight:500;" onclick="">
						</div>
	    <div class="col-sm-5 col-xs-7 exist">
	    	已有账号，前去<a href="login.jsp">登录</a>
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

</body>
</html>