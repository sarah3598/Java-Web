<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册-个人用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/user_register.css">
	<script src="js/register.js"></script>
	<script type="text/javascript">
		window.onload = function(){
			createCode();
			var phoneVal = document.getElementById("phonemsg").innerText;
			var emailVal = document.getElementById("emailmsg").innerText;
			
			if(phoneVal == "账号已存在"){
				document.form1.phone.style.borderColor = "red";
				document.getElementById("phonemsg").style.background = "#FFE1E1";
				document.getElementById("phonemsg").style.height = "38px";
				document.getElementById("phonemsg").style.border = "1px solid";
				document.getElementById("phonemsg").style.borderColor = "pink";	
				document.getElementById("phonemsg").innerHTML = '<br/>&nbsp;账号已存在';
			} 

			if(emailVal == "账号已存在"){
				document.form2.email.style.borderColor = "red";
				document.getElementById("emailmsg").style.background = "#FFE1E1";
				document.getElementById("emailmsg").style.height = "38px";
				document.getElementById("emailmsg").style.border = "1px solid";
				document.getElementById("emailmsg").style.borderColor = "pink";	
				document.getElementById("emailmsg").innerHTML = '<br/>&nbsp;账号已存在';
			}
		}
	</script>

  </head>
  
  <body>
   	
	<div style="width:1100px; margin:auto;">
		<div class="header">
			<!--logo-->
			<div class="header_right">您好，欢迎光临中小学课后自学网！&nbsp;<a href="UserIndexServlet.do">首页</a>|<a href="login.jsp">登录</a>|欢迎注册</div>
		</div>
        <br />
		<div class="main">
			<div class="main_left">
				<div class="main_left_phone" id="phoneregister"><br /><a href="javascript:void(0)" onclick="checkLogin1()" id="aphoneregister">手机注册</a></div>
				<div class="main_left_email" id="emailregister"><br /><a href="javascript:void(0)" onclick="checkLogin2()" id="aemailregister">邮箱注册</a></div>
				<!--手机注册-->
				<div class="main_left_register" id="register1">
					<form action="UserIndexServlet.do?op=phone" method="post" name="form1" onSubmit="return form1All()">
						<div style="height:30px;"></div>
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
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</span>
							<input type="checkbox" checked="checked" id="protocol" onclick="checkbox()"/><font size="-1">我已阅读并同意<a href="javascript:void(0);" target="_blank">《平台使用协议》</a></font>
							&nbsp;&nbsp;<span class="main_left_msg" id="protocolmsg"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="submit" value="同意协议并注册"/ id="submit" style="color:#FFF; background:#09F; height:40px; width:260px; border:1px solid #09F; font-size:18px; font-weight:500;" onclick="form1All2()">
						</div>
					</form>
				</div>
				<!--邮箱注册-->
				<div class="main_left_register" id="register2" style="display:none;">
					<form action="UserIndexServlet.do?op=email" method="post" name="form2" onSubmit="return form2All()">
						<div style="height:30px;"></div>
						<div>
							<span>电子邮箱：</span>
							<input type="text"  id="email" name="email" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkEMail()"/>
							<span class="main_left_msg" id="emailmsg">${requestScope.errmsg }</span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
							<input type="password" id="pwd" name="pwd" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkEMailPwd()"/>
							<span class="main_left_msg" id="pwdmsg2"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>确认密码：</span>
							<input type="password" id="pwd2" name="pwd2" style="width:250px; height:32px; border:1px solid; padding-left:5px;" onblur="checkEMailpwd2()"/>
							<span class="main_left_msg" id="pwd2msg2"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>验&nbsp;证&nbsp;码：</span>
							<input type="text" id="yzm2" name="yzm2" style="width:125px; height:32px; border:1px solid; padding-left:5px;" onblur="ckeckEMailCode()"/>&nbsp;<input type="button" id="checkCode2" class="code" style="width:60px" onClick="createCode2()"/>&nbsp;<a href="javascript:viod(0)" class="a_color" onClick="createCode2()">换一张</a>
							<span class="main_left_msg" id="yzmmsg2"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;</span>
							<input type="checkbox" checked="checked" id="protoco2" onclick="checkboxEMail()"/><font size="-1">我已阅读并同意<a href="javascript:viod(0);" target="_blank">《平台使用协议》</a></font>
							&nbsp;&nbsp;<span class="main_left_msg" id="protocolmsg2"></span>
						</div>
						<div style="height:25px;"></div>
						<div>
							<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input type="submit" value="同意协议并注册"/ id="submit2" style="color:#FFF; background:#09F; height:40px; width:260px; border:1px solid #09F; font-size:18px; font-weight:500;" onclick="form2All2()">
						</div>
					</form>
				</div>
			</div>
			
		</div>
		<!--版权-->
		<div class="footer">
			<br />
			<br />
			<br />
			&nbsp;<a href="#" target="_blank">关于我们</a>&nbsp;|&nbsp;<a href="#" target="_blank">联系我们</a>&nbsp;|&nbsp;<a href="#" target="_blank">商家入驻</a>&nbsp;|&nbsp;<a href="#" target="_blank">广告中心</a>&nbsp;|&nbsp;<a href="#" target="_blank">友情链接</a>&nbsp;|&nbsp;<a href="#" target="_blank">社区问答</a>&nbsp;<br />
			Copyright© 中小学课后自学网 2017-2018，All Rights Reserved
		</div>
	</div>
  </body>
</html>
