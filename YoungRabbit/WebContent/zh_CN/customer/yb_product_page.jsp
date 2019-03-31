<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
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
	<div class="marketplace ">
		<section class="mp-homebg">
			<%@include file="yb_base_nav.jsp"%>
			<div class="heading container">
				<h1 class="title text-red">产品列表</h1>
			</div>
			<h2 class="title m-b-30">
				优质产品 <b>放心产品</b>, <br>尽在YoungRabbit.
			</h2>
		</section>
		<div class="clearfix"></div>
		<section class="products-listing">
			<div class="container">
				<!-- products list -->
				<div id="test" class="clearfix">
					<script id="tpl" type="text/x-handlebars-template">
			  	{{#each this}}
				 <form id="form_{{GOOD_ID}}" method="post" action="<%=path%>/customer/buy?method=sales_link&GOOD_ID={{GOOD_ID}}">
					
					<div onclick="buy('{{GOOD_ID}}');" class="col-lg-6 col-md-6 col-sm-6 col-xs-12 small-verticle-product">
						<div class="card" >
							<div class="product-image-container product-bg-light-red">
								<img src="<%=path%>/image/default-placeholder.png" class="img-responsive placeholder">
								<a id="IMAGE_ID_{{IMAGE_ID}}">{{ help_image_id IMAGE_ID}}</a>
								<input type="hidden" id="H_IMAGE_ID_{{IMAGE_ID}}" name="H_IMAGE_ID_{{IMAGE_ID}}" value="" />
								<input type="hidden" id="H_PRODUCT_PRICE_{{GOOD_ID}}" name="H_PRODUCT_PRICE_{{GOOD_ID}}" value="{{PRODUCT_PRICE}}" />
								<input type="hidden" id="H_TITLE_{{GOOD_ID}}" name="H_TITLE_{{GOOD_ID}}" value="{{TITLE}}" />
								<input type="hidden" id="H_IMAGE_ID_{{GOOD_ID}}" name="H_IMAGE_ID_{{GOOD_ID}}" value="{{IMAGE_ID}}" />
							</div>
							<div class="card-footer clearfix">
							<a href="<%=path%>/customer/buy?method=sales_link&GOOD_ID={{GOOD_ID}}">
								<div class="pull-right">
									<span class="currency">$</span> <span class="amount">{{PRODUCT_PRICE}}</span>
								</div>
								<div class="product-title">
									<a href="#">{{TITLE}}</a>
									<p class="details">{{TITLE}}</p>
								</div>
								</a>
							</div>
						</div>
						
					</div>						
				</form>
				{{/each}}				
			 	 </script>				
				</div>							
			</div>			
		</section>
	</div>
		
	<div class="container">
		<hr class="featurette-divider">
		<!-- /END THE FEATURETTES -->
		
		<!-- FOOTER -->
		<%@include file="yb_base_footer.jsp"%>
	</div>
		
	<script>
		<%String product_list_json = (String) request.getAttribute("product_list");
			if (product_list_json == "") {
				product_list_json = "\"no_data\"";
			}%>

		var obj, rows =<%=product_list_json%>;

		function help_image_id_cb(data,msg){
			//if(msg.show_msg){
				//alert(msg.msg_content);
			//}
			if(data.length>0){
				//just one image
				var root='<%=path%>';
				var image_id=data[0].IMAGE_ID;
				var url=root+data[0].IMAGE_PATH;
				var image='<img src=\"'+url+'\" class=\"img-responsive preview\"  alt=\"产品图片\" >';
				var id="IMAGE_ID_"+image_id;
				var hide_image="H_IMAGE_ID_"+image_id;
				var oimg=document.getElementById(id);
				var ohidd=document.getElementById(hide_image);
				oimg.innerHTML=image;
				//$(hide_image).val(url);
				ohidd.value=url;
			}
			
		}
		
		Handlebars.registerHelper("help_image_id", function (IMAGE_ID) {
			
			var root='<%=path%>';
			var url = [ root, "/customer/product" ].join("");
			var params = [ "method=product_get_image_url&IMAGE_ID=", IMAGE_ID ].join("");
			ims_submit_ajax(url, params, help_image_id_cb);

		});

		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if (rows == "no_data") {
			test.innerHTML = "暂无数据！";
		} else {
			test.innerHTML = tplt(rows);
		}

		function buy(GOOD_ID) {
			var obj = document.getElementById('form_' + GOOD_ID);
			obj.submit();
		}
	</script>
	<%@include file="yb_base_foot_include.jsp"%>
</body>
</html>