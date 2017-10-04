<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>开森后台-修改密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

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
		input{
			width:180px;
			height:30px;
			padding-left:5px;
		}
	</style>
  </head>
  
  <body>
	<div class="top_index">
		<jsp:include page="manager/top.jsp"></jsp:include>
	</div>
	<div class="middle">
		<div class="middle_menu">
			<jsp:include page="manager/left.jsp"></jsp:include>    		
		</div>
		<div class="middle_right">
			<div class="middle_index">
				<div class="index_titlename">修改密码</div>
				<div class="middle_search">
					<jsp:include page="manager/search.jsp"></jsp:include>
				</div>
			</div>
			<div class="middle_main">
				<div class="middle_adduser">
					 <form action="Manager.do?op=upPwd" method="post" onSubmit="return All3()">
					    <table border="1px" cellspacing="0px" bordercolor="#FFF">
					        <tr height="50px">
					            <td width="160px">&nbsp;请输入原密码：</td>
					            <td width="380px">&nbsp;
					                <input type="password" size="18" name="oldpwd" id="oldpwd" onBlur="oldPwd()" placeholder="原密码"/>
					                <div class="msg" id="oldpwdmsg">${requestScope.errmsg}</div>
					            </td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;请输入新密码：</td>
					            <td>&nbsp;
					                <input type="password" size="18" name="pwd" id="pwd" onBlur="Pwd()" placeholder="新密码"/>
					                <div class="msg" id="pwdmsg"></div>
					            </td>
					        </tr>
					        <tr height="50px">
					            <td>&nbsp;请输入确认密码：</td>
					            <td>&nbsp;
					                <input type="password" size="18" name="rpwd" id="rpwd" onBlur="rPwd()" placeholder="确认新密码"/>
					                <div class="msg" id="rpwdmsg"></div>
					            </td>
					        </tr>				     
					        <tr height="52px" align="center">
					            <td colspan="2"><input type="submit" value="确认" class="submit"/></td>
					        </tr>
					    </table>
					</form>
				</div>
				<div class="succeed" id="succeed">${requestScope.err2msg}</div>
			</div>
		 </div>
	</div>
	<script src="./js/manager_user_add.js"></script>
  </body>
</html>
