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
			<div class="col-sm-8 user">
				<div class="base clearfix">
					<div class="userImg">
					      <img src="./images/1.jpg" alt="...">
					</div>
					<div class="userBase">
						<h3>${teacher.relname}</h3><a href="teacher_info_update.jsp">修改资料</a>
						<ul class="list-inline">
							<li><span>性别：</span>${teacher.sex}</li>
							<li><span>职称：</span>${teacher.jobtitle}</li>
							<li><span>学历：</span>${teacher.education}</li>
							<li><span>学科：</span>${teacher.subject}</li>
						</ul>
						<!--userBase
						<div class="sendLetter clearfix">
							<textarea name="dynamic" id="dynamic" rows="5"></textarea>
							<button class="btn pull-left" type="button">清空</button>
							<button class="btn pull-right publish-btn" type="button">发送私信</button>
						</div>-->
					</div><!--userBase-->
				</div>
				<div class="details">
					<p class="title"><span>详细资料</span></p>
					<ul class="list-inline">
						<li><span>手机号：</span>${teacher.mobile}</li>
						<li><span>身份证号：</span>${teacher.idno}</li>
						<li><span>邮箱：</span>${teacher.email}</li>
						<li><span>教龄：</span>${teacher.teachage}</li>
						<li><span>所在地：</span>${teacher.subject}</li>
						<li><span>毕业院校：</span>华中师范大学</li>
						<li><span>工作单位：</span>青山中学</li>
					</ul>
				</div>
				<div class="userAlbums">
					<p class="title2" style="font-size:16px;"><span>我的视频|<a href="teacher_video_add.jsp">上传视频</a></span></p>
					
					<div class="col-xs-6 col-sm-4">
					    <div class="thumbnail">
					      <img src="./images/1.jpg" alt="...">
					      <div class="caption">
					        <p><a href="detailPhotos.html">吃喝玩乐(23)</a></p>
					      </div>
					    </div><!--thumbnail-->
				  	</div>
					<div class="col-xs-6 col-sm-4">
					    <div class="thumbnail">
					      <img src="./images/1.jpg" alt="...">
					      <div class="caption">
					        <p><a href="detailPhotos.html">吃喝玩乐(23)</a></p>
					      </div>
					    </div><!--thumbnail-->
				  	</div>
					<div class="col-xs-6 col-sm-4">
					    <div class="thumbnail">
					      <img src="./images/1.jpg" alt="...">
					      <div class="caption">
					        <p><a href="detailPhotos.html">吃喝玩乐(23)</a></p>
					      </div>
					    </div><!--thumbnail-->
				  	</div>
					<div class="col-xs-6 col-sm-4">
					    <div class="thumbnail">
					      <img src="./images/1.jpg" alt="...">
					      <div class="caption">
					        <p><a href="detailPhotos.html">吃喝玩乐(23)</a></p>
					      </div>
					    </div><!--thumbnail-->
				  	</div>
					<div class="col-xs-6 col-sm-4">
					    <div class="thumbnail">
					      <img src="./images/1.jpg" alt="...">
					      <div class="caption">
					        <p><a href="detailPhotos.html">吃喝玩乐(23)</a></p>
					      </div>
					    </div><!--thumbnail-->
				  	</div>
				</div><!--userAlbums-->
			</div><!--col-sm-8结束-->
			<div class="col-sm-4 maybe">
				<p class="title">你可能喜欢的人</p>
				<span class="pull-right"><i class="glyphicon glyphicon-refresh"></i>换一换</span>
				<div class="col-xs-6">
				    <a href="friendsCenter.html" class="thumbnail">
				      <img src="./images/2.jpg" alt="...">
				      <p>彭和平</p>
				    </a>
				</div>
				<div class="col-xs-6">
				    <a href="friendsCenter.html" class="thumbnail">
				      <img src="./images/2.jpg" alt="...">
				      <p>彭和平</p>
				    </a>
				</div>
				<div class="col-xs-6">
				    <a href="friendsCenter.html" class="thumbnail">
				      <img src="./images/2.jpg" alt="...">
				      <p>彭和平</p>
				    </a>
				</div>
				<div class="col-xs-6">
				    <a href="friendsCenter.html" class="thumbnail">
				      <img src="./images/2.jpg" alt="...">
				      <p>彭和平</p>
				    </a>
				</div>
			</div><!--col-sm-4结束-->
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
<script type="text/javascript">
	$(function(){
		$("#navBar li").removeClass("active");
	})
</script>
</body>
</html>