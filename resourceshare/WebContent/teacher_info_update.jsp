<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	request.setCharacterEncoding("utf-8");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>中小学课后自学网</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
	<link href="css/teacherCenter.css" rel="stylesheet">
	<link href="css/self.css" rel="stylesheet">
	<base href="<%=basePath%>">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<header>
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

<% if(session.getAttribute("user") == null){%>
			<jsp:include page="include/top.jsp"></jsp:include>
		<%}else{ %>
			<jsp:include page="include/top2.jsp"></jsp:include>
		<%} %>
	  </div><!-- /container -->
	</nav><!--导航结束-->
	<div class="banner-box container hidden-xs">
		<!-- <div class="container"> -->
			<div class="row">
				<div class="friends-search col-lg-8 col-md-12 col-sm-12">
				<ul>
			    	<li>资源选择：
			    		<p class="s_list" id="sex-sec-p"><span>学校</span><em class="glyphicon glyphicon-menu-down"></em></p>
						<div class="se_ce hidden" id="sex-p" >
							<ol class="list-group">
								<li><a name="sexsec" value="2">01学校</a></li>
								<li><a name="sexsec" value="1">02学校</a></li>
							</ol>
						</div>
			    	</li>
			    	<li>年级：
			    		<p class="s_list" id="age-start-p"><span>初中</span><em class="glyphicon glyphicon-menu-down"></em></p>
						<div class="se_ce hidden" id="class">
								<ol class="list-group">
                                     <li><a name="sexsec" value="2">小学</a></li>
								     <li><a name="sexsec" value="1">高中</a></li>
    		                    </ol> 
			            </div>
			    	</li>
			    	<li>学科：
			    		<p class="s_list" id="s-sec-area"><span>数学</span><em class="glyphicon glyphicon-menu-down"></em></p>
						<div class="se_ce hidden" id="sec-area" >
							<ol class="list-group">
								<li><a name="sexsec" value="2">英语</a></li>
								<li><a name="sexsec" value="1">语文</a></li>
							</ol>
						</div>
			    	</li>
			    	<li><a class="s_button" href="javascript:void(0)">快速搜索</a></li>
			    </ul>
				</div>
				<div class="col-lg-4 hidden-sm hidden-md">
					<div class="maqBox">
						<i class="glyphicon glyphicon-volume-up"></i>
					    <ul class="list-group">
					        <li><a href="#">1.欢迎光临中小学课后自学网！</a></li>
					        <li><a href="#">2.名师讲堂上线了！</a></li>
					        <li><a href="#">3.最新名校试题上线了！</a></li>
					    </ul>
					</div>
				</div>
			</div>
	</div><!-- 通栏 -->
</header>
<main>
	<div class="container">
		<div class="row">
		<div style="font-size:12px; width:620px; height:760px; margin-left:50px;">
		               	<form class="form-horizontal" action="Teacher.do?op=teacherUpdate&phone=${sessionScope.teacher.mobile}" method="post">
		               	<div id="msg" class="errmsg">${requestScope.errMsg}</div>
		               	<div class="form-group clearfix">
				    <label for="image" class="col-sm-3 control-label">头像</label>
				  	<div class="col-sm-8 avator">
					  	<img src="${teacher.picurl}" alt="">
				  		<p>保存头像</p>
					  	<input type="file" name="picurl" onchange="loadfile(this);">
				  	</div>
				  </div>
	                        <table border="0px">
	                            <tr height="38px">
	                               <td width="120px;">&nbsp;姓名：</td>
	                               <td>&nbsp;<input type="text" name="relname" value="${teacher.relname}"/></td>
	                          	</tr>
	                          	<tr height="38px">
						            <td>&nbsp;身份证号：</td>
						            <td>&nbsp;<input type="text" name="idno" value="${teacher.idno}" readonly></td>
						        </tr>			
						        <tr height="38px">
						            <td>&nbsp;邮箱：</td>
						            <td>&nbsp;<input type="text" name="email" value="${teacher.email}"/></td>
						        </tr>    
						        <tr height="38px">
						            <td>&nbsp;用户移动电话：</td>
						            <td>&nbsp;<input type="text" name="phone" value="${teacher.mobile}" readonly/></td>
						        </tr>
						        <tr height="38px">
						            <td>&nbsp;职称：</td>
						            <td>&nbsp;<input type="text" name="jobtitle" value="${teacher.jobtitle}"/></td>
						        </tr>
						        <tr height="38px">
						            <td>&nbsp;任教学科：</td>
						            <td>&nbsp;<input type="text" name="subject" value="${teacher.subject}"/></td>
						        </tr>
						        <tr height="38px">
						            <td>&nbsp;用户性别：</td>
						            <td>
						            	<c:if test="${teacher.sex=='男'}">
											&nbsp;<input type="radio" name="sex" checked="checked" value="男"/><span>男</span>
											&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"/><span>女</span>
											&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="保密"/><span>保密</span>
										</c:if>
										<c:if test="${teacher.sex=='女'}">
											&nbsp;<input type="radio" name="sex" value="男"/><span>男</span>
											&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" checked="checked" value="女"/><span>女</span>
											&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="保密"/><span>保密</span>
										</c:if>
										<c:if test="${teacher.sex=='保密'}">
											&nbsp;<input type="radio" name="sex" value="男"/><span>男</span>
											&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" value="女"/><span>女</span>
											&nbsp;&nbsp;&nbsp;<input type="radio" name="sex" checked="checked" value="保密"/><span>保密</span>
										</c:if>
		                        	</td>
						        </tr>
						        <tr height="38px">
						            <td>&nbsp;年龄：</td>
						            <td>&nbsp;<input type="text" name="age" value="${teacher.age}"></td>
						        </tr>			
						        <tr height="38px">
						            <td>&nbsp;学历：</td>
						            <td>&nbsp;<input type="text" name="education" value="${teacher.education}"/></td>
						        </tr>
						        <tr height="38px">
						            <td>&nbsp;地址：</td>
						            <td>&nbsp;<textarea rows="3" cols="42" name="address" style="padding-left:5px;">${teacher.address}</textarea></td>
						        </tr>
						        
						        <tr height="38px">
						            <td>&nbsp;教龄：</td>
						            <td>&nbsp;<input type="text" name="teachage" value="${teacher.teachage}"></td>
						        </tr>						     						        
						        <tr height="52px" align="center">
						            <td colspan="2"><input type="submit" value="确认" class="submit" id="submit2"/></td>
						        </tr>
		                   </table>
		            	</form>
                    </div>
		</div><!--row结束-->
	</div><!--container结束-->
</main>
<footer>
	<div class="container">
		
    <p class="jy_link">
		<a href="">武汉中学</a>
        <a href="">华师一附中</a>
       <a href="">武汉外国语学校</a>
	</p>
	<p>Copyright (C) 2017 All Rights Reserved. 项目六组 版权所有</p>
	</div>
</footer>
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/common.js"></script>
<script src="js/self.js"></script>
<script type="text/javascript">
	$(function(){
		$("#navBar li").removeClass("active");
	})
</script>
</body>
</html>