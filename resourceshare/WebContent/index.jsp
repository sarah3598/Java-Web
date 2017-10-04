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
	<link href="css/index.css" rel="stylesheet">
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

<% if(session.getAttribute("user") == null&&session.getAttribute("teacher")==null){%>
			<jsp:include page="include/top.jsp"></jsp:include>
		<%}else if(session.getAttribute("user")!=null){ %>
			<jsp:include page="include/top2.jsp"></jsp:include>
		<%}else if(session.getAttribute("teacher")!=null){ %>
		    <jsp:include page="include/top3.jsp"></jsp:include>	
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
			<div>
				<!-- 广告轮播 -->
				<div id="ad-carousel" class="carousel slide" data-ride="carousel">
				    <ol class="carousel-indicators">
				        <li data-target="#ad-carousel" data-slide-to="0" class="active"></li>
				        <li data-target="#ad-carousel" data-slide-to="1"></li>
				        <li data-target="#ad-carousel" data-slide-to="2"></li>
				        <li data-target="#ad-carousel" data-slide-to="3"></li>
				    </ol>
				    <div class="carousel-inner">
				        <div class="item active">
				            <img src="images/04.jpg" alt="1 slide">
				        </div>
				        <div class="item">
				            <img src="images/03.jpg" alt="2 slide">
				        </div>
				        <div class="item">
				            <img src="images/02.jpg" alt="3 slide">
				        </div>
				        <div class="item">
				            <img src="images/01.jpg" alt="4 slide">
				        </div>
				    </div>
				    <a class="left carousel-control" href="#ad-carousel" data-slide="prev"><span
				            class="glyphicon glyphicon-chevron-left"></span></a>
				    <a class="right carousel-control" href="#ad-carousel" data-slide="next"><span
				            class="glyphicon glyphicon-chevron-right"></span></a>
				</div><!--轮播结束-->
				<div class="frame recommended">
					<h4>名师风采</h4>
					<ul class="media-list">
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
						  </div>
						  <div class="media-body">
							<ul class="list-group">
								<li><strong>彭和平</strong></li>
								<li>高级教师</li>
							</ul>
							<button class="btn care" type="button">关注</button>						  
						  </div>
					  </li>
				</ul>
				</div><!--名师推荐结束-->
                	<div class="frame video">
					<h4>精彩课程</h4>
					<ul class="media-list">
					   <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>初一数学|一元一次方程专题</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>高一数学|二次函数专题</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					   <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>中考语文|文言文翻译</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					   <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>小学语文|人物写作</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>初三物理|电路专题</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					   <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>高一数学|图像平移专题（一）</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					  <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>初二数学|二元一次方程组</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
					   <li class="media">
						  <div class="media-left">
						    <a href="friendsCenter.html">
						      <img class="media-object" src="./images/Penguins.jpg" alt="头像">
						    </a>
                            <ul class="list-group">
								<li><strong>高一化学|氧化反应</strong></li>
							</ul>				  
						  </div>
						  
					  </li>
				</ul>
				</div>
				<div class="warning">
                    <i class="glyphicon glyphicon-alert"></i>
					<ul class="list-group">
						<li><span>1</span>名校毕业，专业资深</li>
						<li><span>2</span>尽心负责，耐心辅导</li>
						<li><span>3</span>省时便捷，随时可学</li>
					</ul>
				</div>
			</div><!--col-lg-8结束-->
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
<script src="js/index.js"></script>
</body>
</html>