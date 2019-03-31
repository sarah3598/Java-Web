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
<title>Young Rabbit 主页</title>
</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<%@include file="yb_base_nav.jsp"%>
	<!-- Carousel ================================================== -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner" role="listbox" id="test">
			<script id="tpl" type="text/x-handlebars-template">
		{{#each this}}
			<div class="item {{help_active SLIDE_ID}}">
			  <img class="{{help_class SLIDE_ID}}-slide" src="{{help_url URL}}" alt="{{help_class SLIDE_ID}} slide">
			  <div class="container">
				<div class="carousel-caption">
				  <h1>{{HEAD}}</h1>
				  <p>{{DESCR}}</p>
				  <p><a class="btn btn-lg btn-primary" href="{{HREF}}" role="button">Sign up today</a></p>
				</div>
			  </div>
			</div>
		{{/each}}
		</script>
		</div>
		<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span>
		</a>
	</div>

	<!-- Marketing messaging and featurettes ================================================== -->
	<div class="container marketing">
		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-lg-4">
				<img class="img-circle" src="<%=path%>/image/home_page/s1.jpg" alt="Generic placeholder image" width="140" height="140">
				<h4>「年輕、朝氣、活力」</h4>
				<p align="left">我們是充滿熱情及生命力的團隊，對能變美的事物總有著獨特的見解。在全球化的時代下，緊貼著網路時代的脈搏、跟上時代的節奏，積極的創新技術、研發產品，我們始終抱著超越100分的專注與熱情，藉由不斷的學習、精進自己，全心全意，無保留的投入事業，證明存在的價值，更致力於將變美的秘密，無私地分享給全球女性。</p>
				<!-- <p>
					<a class="btn btn-default" href="#" role="button">View details
						&raquo;</a>
				</p>-->
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle" src="<%=path%>/image/home_page/s2.jpg" alt="Generic placeholder image" width="140" height="140">
				<h4>「美麗與健康缺一不可」</h4>
				<p align="left">為了滿足您對美麗的所有要求，我們按市場最高標準來鞭策自己，產品的安全品質層層把關，一刻不鬆懈，確保每樣產品都能做出真實的正向口碑及影響力。品牌以誠信為原則，全方位的品質控管，嚴格篩選製作的原料及監控產品的生產過程，保障每位女性們的權益。</p>
				<!--  <p>
					<a class="btn btn-default" href="#" role="button">View details
						&raquo;</a>
				</p>-->
			</div>
			<!-- /.col-lg-4 -->
			<div class="col-lg-4">
				<img class="img-circle" src="<%=path%>/image/home_page/s3.jpg" alt="Generic placeholder image" width="140" height="140">
				<h4>「開拓新視野」</h4>
				<p align="left">Young Rabbit以零售及批發業務起頭，在規劃新品牌同時，也致力於創新產品研發，期望創造出更具價值的產品。除此之外，我們也正積極拓展全球據點，目前範圍已涵蓋馬來西亞，新加坡，澳洲，中國，台灣及香港等地，旗下擁有數千名優質代理，在各大社交網站例如Facebook、Instagram、Line及微信都非常活躍，未來，市場將涵蓋整個亞洲，並朝向全球市場邁進。</p>
				<!-- <p>
					<a class="btn btn-default" href="#" role="button">View details
						&raquo;</a>
				</p>-->
			</div>
			<!-- /.col-lg-4 -->
			
		</div>
		<!-- /.row -->

		<hr class="featurette-divider">
		
		<!-- /END THE FEATURETTES -->
		<!-- FOOTER -->
		<%@include file="yb_base_footer.jsp"%>

	</div>
	</div>
	<!-- /.container -->

	<%@include file="yb_base_foot_include.jsp"%>
	<script>
	<!-- 后台数据库有待完善，目前通过手动添加首页相关图片 -->
	
		var  rows =[];
		
		Handlebars.registerHelper("help_url", function (URL) {
			return ["<%=path%>" , URL].join("");					
		});
 
		Handlebars.registerHelper("help_class", function(id) {
			if (id == 1)
				return "second";
			else if (id == 2)
				return "third";
			else
				return "first";
		});

		Handlebars.registerHelper("help_active",function(id){
			var flag = 0;
			for(var i = 0; i < rows.length; i ++){
				if(rows[i].SLIDE_ID == parseInt(id))
					flag = i;
			}
			
			if(flag == 0)
				return "active";
			else
				return;
        });

		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		test.innerHTML = tplt(rows);
		
		function slide_cb(data,msg){
			rows =data;
			test.innerHTML = tplt(rows);
		}
		
		/***************public***************/
		window.onload = function() {
			var params ="";
			ims_submit_ajax("<%=path%>/homepage", params, slide_cb);
		}
	</script>
</body>
</html>