<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    
    <title>开森后台-查看管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./css/manager_index.css"/>
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
					<div class="index_titlename">查看管理员</div>
					<div class="middle_search">
						<jsp:include page="manager/search.jsp"></jsp:include>
					</div>
				</div>
				<div class="middle_main">
					<div style="width:1070px; background:#F5F5F5; margin:6px 5px; box-shadow:1px 2px 3px #CCCCCC">
						<table border="1px" width="100%" cellspacing="0px" bordercolor="#FFF">
							<tr height="38px" align="center" style="font-weight:bold">
								<td width="20%">管理员</td>
								<td width="20%">管理员权限</td>
								<td width="20%">管理员邮箱</td>
								<td width="40%">操作</td>							
							</tr>
							<c:forEach var="m" items="${requestScope.managerlist}">
								<tr align="center" height="30px">
									<td>${m.admin }</td>
									<td>${m.rank}</td>
									<td>${m.email}</td>
									<td align="center" width="25%">
										<a href="Manager.do?op=detail&admin=${m.admin }"><font color="#2894FF">详情</font></a>&nbsp;&nbsp;
										<a href="#"><font color="#2894FF"><del>修改</del></font></a>
									</td>
								</tr>
							</c:forEach>
							<tr align="center">
								<td colspan="6" height="36px;">
									<div style="position: absolute; margin-left:55%;">
										<form action="#" method="get" name="myform">
											<input type="text" value="1" class="middle_num"/><input type="submit" value="Go" class="middle_submit"/>
										</form>
									</div>
									<a href="">首页</a> &nbsp;&nbsp;&nbsp;
									<a href="">上页</a> &nbsp;&nbsp;&nbsp;
									<a href="">下页</a> &nbsp;&nbsp;&nbsp;
									<a href="">末页</a>
								</td>
							</tr>	
						</table>
					</div>
				</div>
			 </div>
		</div>
  	</body>
</html>
