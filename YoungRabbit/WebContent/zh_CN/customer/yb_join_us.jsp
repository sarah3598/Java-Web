<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html lang="zh-CN">
<head>
<%@include file="yb_base_head_include.jsp"%>
<link rel="stylesheet" href="<%=path %>/css/site.css">
<title>About us</title>
</head>
<!-- NAVBAR ================================================== -->
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<%@include file="yb_base_nav.jsp"%>

	<hr>
	<div class="header" style="background-image: url(http://static.bootcss.com/expo/img/d/dd/2de797545de56274f03a5920eb3a1.jpg)">
		<div class="logoimg">
			<a href="<%=path %>/zh_CN/company/yb_register_step_1.jsp" title="点击加入我们"><img src="<%=path %>/image/site.png" alt="点击加入我们" width="78"></a>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="logotxt">
						<h1>
							<a href="<%=path %>/zh_CN/company/yb_register_step_1.jsp">YoungRabbit</a>
						</h1>
					</div>
					<h2 class="site-name text-center">
						<a href="<%=path %>/zh_CN/company/yb_register_step_1.jsp">加入我们</a>
					</h2>
				</div>
			</div>
		</div>
	</div>
	<!-- Marketing messaging and featurettes ================================================== -->
	<div class="container marketing">
		<!-- START THE FEATURETTES -->
		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading" style="margin-top: 30px;">销售总裁致辞</h2>
				<p class="lead">首先我要感谢大家有意加入我们的公司，Young Rabbit。我也在此要赞扬大家在我们举办的训练中展示的恒心和意志。在此我想大家暂停一下，用一点时间回想当初努力地原因。</p>
				<p class="lead">我相信对您们很多人来说，加入Young Rabbit是你们向往的目标。可是我要告诉大家，加入Young Rabbit才是你们路程的起点。这个路程会有Young Rabbit上上下下人员一起来走。而且我在此保证你们会在这个路程上找到意想不到的奖励。</p>
				<p class="lead">我们公司新推出的销售系统保证大家共同成长，公平和公正的回报。按照个人可以付出的时间和努力，大家都可以在这打拼出一番天地。</p>
				<p class="lead">最后，我要欢迎大家加入我们的Young Rabbit的大家庭。</p>
				<p class="lead" style="float: right;">--林丽婉（销售总裁）</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block" style="width:500;height:500;" src="<%=path%>/image/01.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading" style="margin-top: 30px;">招聘</h2>
				<p class="lead">Young Rabbit会定期举办培训课程来招聘新成员。每个课程将会由被选的销售经理级别的人员和销售成绩出色的销售员来带领。顺利完成培训的申请人将会被公司聘请，而他们会加入带领他们的领导的销售群。</p>
				<h2 class="featurette-heading" style="margin-top: 30px;">报酬 & 假期</h2>
				<p class="lead">Young Rabbit提供丰富的、并具有挑战性的薪资报酬，具体包括底薪、多种佣金、多种津贴、多种奖金和奖赏等。</p>
				<p class="lead">Young Rabbit为员工提供人性化的年假、病假、住院医疗假、产假等，保证在工作之余，充分的享受高质量的生活。</p>
				<h2 class="featurette-heading" style="margin-top: 30px;">晋升</h2>
				<p class="lead">Young Rabbit为员工提供公平、公开、公正的晋升渠道，为员工打造终身职业规划。</p>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block" style="width:500;height:500;" src="<%=path%>/image/02.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading" style="margin-top: 30px;">产品总代</h2>
				<p class="lead">优秀的销售人员可以成为Young Rabbit的产品总代。作为总代，每当公司售卖一份他们代理的产品而且那产品的月销售又达到一个设定的最低销量时，他们将可以得到总代佣金。</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block" style="width:500;height:500;" src="<%=path%>/image/03.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading" style="margin-top: 30px;">联系方式</h2>
				<p class="lead">电话：010-88889999</p>
				<p class="lead">微信：aaaaaaaaa</p>
				<p class="lead">Facebook：aaaaaaaaa</p>
				<p class="lead">Blog：aaaaaaaaa</p>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block" style="width:500;height:500;" src="<%=path%>/image/04.jpg" alt="Generic placeholder image">
			</div>
		</div>
		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->


		<!-- FOOTER -->
		<%@include file="yb_base_footer.jsp"%>

	</div>
	<!-- /.container -->
	<script>
		
	</script>
	<!-- Bootstrap core JavaScript ================================================== -->
	<script src="../assets/js/vendor/jquery.min.js"></script>
	<script src="../dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="../assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="../assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
