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
<title>Blog</title>

</head>

<!-- NAVBAR ================================================== -->
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<%@include file="yb_base_nav.jsp"%>
	<div class="container">
		<br /><br /><br />
		<hr>
		<div class="blog-header">
			<h1 class="blog-title">博客</h1>
			<p class="lead blog-description">写下文字，记录点滴</p>
		</div>

		<div class="row">
			<div class="col-sm-8 blog-main">
				<div class="blog-post">
					<h2 class="blog-post-title">如何拍摄美丽的星空</h2>
					<p class="blog-post-meta">
						5月15日, 2017年 by <a href="#">Mark</a>
					</p>

					<p>很多人对星空充满了好奇与憧憬。美丽的星空图展现了宇宙的浩瀚与神秘，让无数人为之陶醉。然而，由于星星距离我们很远，且光线微弱，很多人并不了解如何拍摄出满意的星空照片。经常在后台收到这样的私信：如何拍星星？需要什么样的工具？去哪里拍呢？</p>
					<hr>
					<p>星空美好，苍穹震撼，大家都渴望体验被繁星笼罩的感觉，但又不是人人都能做到，所以我一直酝酿着出份教程，告诉大家应该怎样将这样美的星空带回家，并且分享给更多的人。这趟旅程也让我机缘巧合之下积累素材，得以给大家讲一讲拍摄星空的技巧。</p>
					<blockquote>
						<p>
							<strong>拍摄准备</strong></br> 星星发出的光线极其微弱，只有在纯黑的夜幕下才能拍摄出最好的效果，因而，在拍摄地点和拍摄日期选择上，都有一定的要求。同时，拍摄星空需要比较专业的设备，才能达到最好的效果。
						</p>
					</blockquote>
					<h3>（1）拍摄地点的选择：</h3>
					<p>拍摄星空首先需要选择一个晴朗、大气通透的地区，肉眼看见繁星较多，同时要避开城市的光害污染。因此，我经常会为了拍摄出一组好的星空的照片而驱车长途奔袭几百公里，只为寻找远离城市，光污染小的地方。</p>
					<h3>（2）拍摄日期的选择：</h3>
					<p>月光对星星的影响很大，满月的月光会掩盖掉很多我们肉眼不可见的暗星，得到的画面星点稀少，缺少感觉。我们的拍摄选在了12月27-30日，农历的廿九、三十、初一、初二几天。农历的月末和月初，几乎可以忽略月光的影响。</p>
					<pre style="background-color: #f5f5f5;">
						<code>Example</code>
					</pre>
					<p>…………</p>

				</div>
				<!-- /.blog-post -->

				<div class="blog-post">
					<h2 class="blog-post-title">春风沉醉的夜晚</h2>
					<p class="blog-post-meta">
						3月23日, 2017年 by <a href="#">Jacob</a>
					</p>

					<p>小街弄堂里的空气中会飘出饭菜的香味，是呀这里是要过日子的地方。一起飘来的，还有一些断断续续的钢琴声和小提琴声，那是晚饭前父母要求孩子练琴的声音。有时候是巴赫，有时候是贝多芬，伴随着红烧带鱼的气息就陪在我的窗前，晚风把它们都带了进来，化成一股绵软的温馨盘在心间。每一栋米色外墙的小楼里都亮了柔柔的黄色小灯，让人恍惚觉得这个城市变得很小，小得就像一个放置心灵的盒子。</p>
					<blockquote>
						<p>Example</p>
					</blockquote>
					<p>春天，是个多么美妙的季节。我清晨踏着晨曦下的海棠花出门，轻柔的纯 棉针织衫和皮肤摩擦着，唤醒了蛰伏一个冬天的敏感神经。朋友们、学员们陆陆续续来做我的客人，在小小的空间，一批又一批。有时候是微型的火锅，有时候是清香的下午茶。那么小的空间里，还是要忙不迭换上美好的窗帘，忙不迭每天给小露台上的蔷薇和木香浇水，忙不迭用小烤箱烘一点香香的面包，忙不迭扫地擦地打扫卫生。我总记得陶渊明在《归去来兮辞》中说到“田园将芜胡不归”，意思是田园要荒芜了，何必还留恋名利不回家呢？家乡的田地会荒芜，同样的，精神的田地不耕耘，也会有荒芜的一天。于是只是简单地扫扫地浇浇花，都让我觉得自己的心更踏实了。</p>
				</div>
				<!-- /.blog-post -->

				<nav>
					<ul class="pager">
						<li><a href="#">Previous</a></li>
						<li><a href="#">Next</a></li>
					</ul>
				</nav>

			</div>
			<!-- /.blog-main -->

			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="sidebar-module sidebar-module-inset">
					<h4>随笔</h4>
					<p>早晨太阳好的时候，阳光会透过树叶斑驳地落到地上，整个长长的小街，就像蒙上了一层柔和透明的蕾丝头纱。午后日头转烈，深深浅浅的绿色树木就在头顶相互掩映着。</p>
				</div>
				<div class="sidebar-module">
					<h4>Archives</h4>
					<ol class="list-unstyled">
						<li><a href="#">March 2014</a></li>
						<li><a href="#">February 2014</a></li>
						<li><a href="#">January 2014</a></li>
						<li><a href="#">December 2013</a></li>
						<li><a href="#">November 2013</a></li>
						<li><a href="#">October 2013</a></li>
						<li><a href="#">September 2013</a></li>
						<li><a href="#">August 2013</a></li>
						<li><a href="#">July 2013</a></li>
						<li><a href="#">June 2013</a></li>
						<li><a href="#">May 2013</a></li>
						<li><a href="#">April 2013</a></li>
					</ol>
				</div>
				<div class="sidebar-module">
					<h4>Elsewhere</h4>
					<ol class="list-unstyled">
						<li><a href="#">GitHub</a></li>
						<li><a href="#">Twitter</a></li>
						<li><a href="https://m.facebook.com/youngrabbitmy/">Facebook</a></li>
					</ol>
				</div>
			</div>
			<!-- /.blog-sidebar -->

		</div>
		<!-- /.row -->

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
