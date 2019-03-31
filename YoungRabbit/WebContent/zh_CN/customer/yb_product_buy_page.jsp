<%@ page language="java" import="java.util.*,java.net.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String GOOD_ID = request.getParameter("GOOD_ID");
	String SALES_PERSON_ID =request.getParameter("SALES_PERSON_ID");
	String SALES_PERSON_ROLE =request.getParameter("ROLE");
	SALES_PERSON_ID =SALES_PERSON_ID ==null?"":SALES_PERSON_ID;
	SALES_PERSON_ROLE =SALES_PERSON_ROLE ==null?"":SALES_PERSON_ROLE;
	
%>

<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="yb_base_head_include.jsp"%>
<!-- product private -->
<link rel="stylesheet" href="<%=path%>/css/marketplace.css">
<!-- public -->
<link rel="stylesheet" href="<%=path%>/css/bootstrap.css">
<link rel="stylesheet" href="<%=path%>/css/qubico.css">
<link rel="stylesheet" href="<%=path%>/css/css_002.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/css.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/modal.css">
<title>Young Rabbit Product</title>
</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<div id="test" class="marketplace ">
	<script id="tpl" type="text/x-handlebars-template">
		{{#each this}}
		<section class="mp-homebg">
			<%@include file="yb_base_nav.jsp"%>
			<div class="heading container">
				<h1 class="title text-red" id="ptitle1">{{TITLE}}</h1>
			</div>
			<!--  <h2 class="title m-b-30" id="ptitle2">
				Buy premium quality iOS Apps with <b>full source code</b>, <br>developed exclusively by the Redbeard team.
			</h2>-->
		</section>
		<div class="clearfix"></div>
		<div class="container">
			<!-- products list -->
			<div class="clearfix">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 small-verticle-product">
					<div class="card" data-navigate-url="/code-store/rbnb/">
						<div class="product-image-container product-bg-light-red">
							<img src="<%=path%>/image/default-placeholder.png" class="img-responsive placeholder"><span id="IMAGE_ID_{{IMAGE_ID}}">
														{{help_image_id IMAGE_ID}}
													</span>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 small-verticle-product">
					
						
							<div class="card-footer clearfix">
								<div class="product-title">
									<a href="#">{{TITLE}}</a>
									<p class="details">{{DESCRIPTION}}</p>
								</div>
							</div>

							<div class="card-footer clearfix">
								<div class="product-title">
								<a href="#">{{help_quantity NOW_STOCK_QUANTITY}}</a>
								</div>
							</div>

							<div class="card-footer clearfix">

								<div>
									<span class="currency">惊爆价{{help_currency CURRENCY}}</span> <span class="amount">{{PRODUCT_PRICE}}</span>
								</div>
							</div>
							<br /> <br /> <br /> <br />
							<div class="form-group text-center">
								<div class="form-group text-center">
								<input type="hidden" name="GOOD_ID" id="GOOD_ID" value="{{GOOD_ID}}" /> <input type="hidden" name="SALES_PERSON_ID" id="SALES_PERSON_ID" value="<%=SALES_PERSON_ID%>" /> <input type="hidden" name="ROLE" id="ROLE" value="<%=SALES_PERSON_ROLE%>" />

								<!-- <input value="加入购物车" class="btn btn-qubico" type="button" onclick="add_cart();"> -->
								<input value="立即购买" class="btn btn-qubico" type="button" onclick="buy_now();">
								</div>
							</div>						
					
				</div>
			</div>
		</div>
	{{/each}}
	</script>
	</div>


	<div class="container">
		<hr class="featurette-divider">
		<!-- /END THE FEATURETTES -->

		<!-- FOOTER -->
		<%@include file="yb_base_footer.jsp"%>
	</div>

	<script type="text/javascript">
	var  rows =[];
	Handlebars.registerHelper("help_quantity", function (NOW_STOCK_QUANTITY) {
		if(NOW_STOCK_QUANTITY==0)
			return "无货";
		else if(NOW_STOCK_QUANTITY<=10)
			return ["仅剩",NOW_STOCK_QUANTITY,"件"].join("");	
		else 
			return ["库存",NOW_STOCK_QUANTITY,"件"].join("");	
	});
	
	//var arr2 = [["0","请选择产品价格单位"],["1","美元"],["2","人民币"],["3","林吉特"]];
	Handlebars.registerHelper("help_currency", function (CURRENCY) {
		if(CURRENCY==1)
			return ["$",].join("");
		else if(CURRENCY==2)
			return ["¥"].join("");	
		else if(CURRENCY==3)
			return ["RM"].join("");	
	});
	
	function help_image_id_cb(data,msg){
		
		if(data.length>0){
			//just one image
			var root='<%=path%>';
			var image_id=data[0].IMAGE_ID;
			var url=root+data[0].IMAGE_PATH;
			var image='<img src=\"'+url+'\" class=\"img-responsive preview\" alt=\"产品图片\">';
			var id="IMAGE_ID_"+image_id;
			var oimg=document.getElementById(id);
			oimg.innerHTML=image;				
		}else{
			if(msg.show_msg){
				//alert(msg.msg_content);
			}
		}
							
	}
	
	Handlebars.registerHelper("help_image_id", function (IMAGE_ID) {
		var params = ["method=product_get_image_url&IMAGE_ID=", IMAGE_ID].join("");
		ims_submit_ajax("<%=path%>/customer/product", params,help_image_id_cb);

	});

	var tplo = document.getElementById('tpl');
	var tplt = Handlebars.compile(tplo.innerHTML);
	var test = document.getElementById('test');
	test.innerHTML = tplt(rows);	
	
		function buy_now() {
			//先判断该用户是否登录，若已登录做如下跳转；否则，跳转到登录
			var GOOD_ID=document.getElementById('GOOD_ID').value;
			var SALES_PERSON_ID=document.getElementById('SALES_PERSON_ID').value;
			var ROLE=document.getElementById('ROLE').value;
			var url=["<%=path%>/customer/buy?method=buy_detail&GOOD_ID=",GOOD_ID,"&SALES_PERSON_ID=",SALES_PERSON_ID,"&ROLE=",ROLE].join("");
			window.location.href=url;
		}

		function p_cb(data, msg) {
			rows = data;
			test.innerHTML = tplt(rows);
		}

		/***************public***************/
		window.onload = function() {
			var root="<%=path%>";
			var url = [ root, "/customer/product" ].join("");
			var GOOD_ID =<%=GOOD_ID%>;
			var params = [ "method=product_detail&GOOD_ID=", GOOD_ID ].join("");
			ims_submit_ajax(url, params, p_cb);
		}
	</script>
	<script src="<%=path%>/js/jquery"></script>
	<script src="<%=path%>/js/jqueryval"></script>
	<script src="<%=path%>/js/bootstrap.js"></script>
	<script src="<%=path%>/js/jquery_003.js"></script>
	<script src="<%=path%>/js/jquery_006.js"></script>
	<script src="<%=path%>/js/waypoints.js"></script>
</body>
</html>