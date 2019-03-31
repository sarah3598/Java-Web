<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="yb_base_head_include.jsp"%>
<title>About us</title>

</head>

<!-- NAVBAR ================================================== -->
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<%@include file="yb_base_nav.jsp"%>
	<!-- Marketing messaging and featurettes ================================================== -->
	<div class="container marketing">
		<!-- START THE FEATURETTES -->
		<hr class="featurette-divider">
		<div class="row featurette">
			<div class="col-md-7">
				<h2 class="featurette-heading">Young Rabbit</h2>
				<p class="lead">Young Rabbit 為了『成就女人的美麗』而存在，提供愛美女性擁有品質保證的美容保健食品，並以健康為變美的前提，讓美麗不僅僅是膚淺的表面，而是由內而外散發的光彩與自信。</p>
			</div>
			<div class="col-md-5">
				<img class="featurette-image img-responsive center-block" width="80%" src="<%=path%>/image/about_us/about_1.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="row featurette">
			<div class="col-md-7 col-md-push-5">
				<h2 class="featurette-heading">美容生物科技公司</h2>
				<p class="lead">Young Rabbit 美容生物科技公司成立於 _____年，為馬來西亞極具代表性的美容保健食品產業，主要產品為服用型美容保健食品，外敷型美容產品及化妝美膚系列。憑藉著對網路市場的專業度和美容流行趨勢的敏銳度，在短短的期間裡，一躍成為馬來西亞首屈一指的社交電商貿易領導者，更創下媲美上市公司的優秀成績，卓越的表現在業界有目共睹，讓人傾慕。</p>
			</div>
			<div class="col-md-5 col-md-pull-7">
				<img class="featurette-image img-responsive center-block" width="80%" src="<%=path%>/image/about_us/about_2.jpg" alt="Generic placeholder image">
			</div>
		</div>

		<hr class="featurette-divider">

		<div class="container">
			<div class="eventheader">
				<h1 class="section-title">新時代女性 Yvonne</h1>
				<p>Young Rabbit創辦人Yvonne，天生的領導者，築成夢想的美好典範，一位最美的人生贏家。</p>
			</div>
			<div class="eventlist-container">
				<div class="eventlist event-bg">
					<div class="eventlist-block">
						<div class="eventmark event-bg"></div>
						<div class="event">
							<div class="heading">一切的夢想，源自於一份熱情</div>
							<div class="content trix-content">
								年輕的她，沒有富裕的家庭背景、沒有專業的零售知識，卻彷彿擁有與生俱來的商業嗅覺，和對市場流行趨勢的極高敏銳度，因對事業的熱愛，以及敢追、敢夢的冒險家的精神，仍成功闖出屬於自己的一片天，引領團隊再創巔峰、屢造佳績。<br>
								<!--	<img src="/images/1111.jpg" style="max-width: 100%;">-->
							</div>
							<time class="date">Dream</time>

						</div>
					</div>
					<div class="eventlist-block">
						<div class="eventmark event-bg"></div>
						<div class="event">
							<div class="heading">從哪裡跌倒，就從哪裡站起來</div>
							<div class="content trix-content">
								「我並不因為一時的失敗，而放棄了曾經的初衷與夢想」，曾歷經感情的風雪與事業的起落，嘗盡人生滋味卻不曾削減一絲熱誠，Yvonne對決定的每件事，都全心全意無保留的投入，在遇到人生挫敗之時，相信必定會有另外一扇明窗為她開啟。<br> 創健Young Rabbit起因是一個意志的轉念下，她發現網路銷售平台的龐大市場，並轉身進入組織行銷業，成為第一個於網路作陌生成交的行銷代理商，憑著一股不屈不撓的意志，及永不放棄的堅定毅力，更曾創下一天馬幣10萬的陌生成交記錄，令人嘖嘖稱奇。<br>
								<!--	<img src="/images/2222.jpg" style="max-width: 100%;">-->
							</div>
							<time class="date">Difficulty</time>

						</div>
					</div>
					<div class="eventlist-block">
						<div class="eventmark event-bg"></div>
						<div class="event">
							<div class="heading">因為熱愛，所以堅持</div>
							<div class="content trix-content">
								Yvonne把工作的熱情投入在有興趣的事物上，而這一次大膽的自行引入國際品牌，一躍成為第一流產品的亞洲總代理。一路走來，Yvonne秉持著「以信任為本，以顧客為尊」的理念，重視代理商的培訓，更仔細聆聽客戶的需求，打造口耳相傳的真實口碑，因抱持著這樣的初衷不變，藉由網路無遠佛界的擴散力量，迅速的在馬來西亞及新加波等地建立良好的口碑及信譽，重返榮耀，再創事業新高峰。<br> Yvonne回顧過往的經歷顯得一派輕鬆，始終抱持著正向思維的她，更不吝嗇與她人分享經歷的點點滴滴。如今，在團隊的努力下，Young Rabbit事業版圖日益茁壯，更積極拓展海外視野，未來的成就肯定非同凡響。<br>
								<!--	<img src="/images/3333.jpg" style="max-width: 100%;">-->
							</div>
							<time class="date">Devotion</time>
						</div>
					</div>
				</div>
			</div>
		</div>

		<hr class="featurette-divider">

		<!-- /END THE FEATURETTES -->



		<!-- FOOTER -->
		<%@include file="yb_base_footer.jsp"%>

	</div>
	<!-- /.container -->

	<%@include file="yb_base_foot_include.jsp"%>
	<script>
		
	</script>
	<!-- Bootstrap core JavaScript ================================================== -->
	<script src="<%=path%>/assets/js/vendor/jquery.min.js"></script>
	<script src="<%=path%>/dist/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=path%>/assets/js/vendor/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/assets/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>