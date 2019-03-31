<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>

<%@include file="yb_db_head_include.jsp"%>
<title>accounts</title>
</head>
<body>

	<%@include file="yb_db_nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">账户</h1>
				<div class="container">
					<!--一级菜单-->
					<ul id="WEBSITE_CONTROL" class="nav nav-tabs">
						<li class="active"><a href="#ORDER" data-toggle="tab">
								订单履行 </a></li>
						<li><a href="#FIGURE" data-toggle="tab"> 销售数据 </a></li>
						<li><a href="#INVENTORY" data-toggle="tab"> 库存 </a></li>
					</ul>
					<!--一级菜单结束-->
					<div id="myTabContent" class="tab-content">
						<!--订单-->
						<div class="tab-pane fade in active" id="ORDER">
							<br /> <br />
							<div class="col-md-7"></div>
							<div class="form-inline col-md-5">
								<div class="btn-group">
									<button type="button" class="btn btn-default dropdown-toggle"
										data-toggle="dropdown">
										条件过滤 <span class="caret"></span>
									</button>
									<ul class="dropdown-menu" role="menu">
										<li><a href="#">最新订单</a></li>
										<li><a href="#">待支付订单</a></li>
									</ul>
								</div>
								<input type="text" class="form-control " placeholder="请输入搜索内容"></input>
								<button class="btn btn-default">搜索</button>
							</div>
							<br /> <br /> <br /> <br />
							<table class="table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>日期</th>
										<th>ID</th>
										<th>联系方式</th>
										<!-- 对应数据表中无此字段 -->
										<th>商品</th>
										<th>数量</th>
										<!-- 对应数据表中无此字段 -->
										<th>地址</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody id="order">
									<script id="tpl_order" type="text/x-handlebars-template">
			    					{{#each this}}
               						 <tr>
                  					 <td>{{addOne @index}}</td>
                 					 <td>{{HAPPEN_DATE}} </td>
                  					 <td>{{ORDER_ID}}</td>
                  					 <td>{{PHONE}}</td>
                  					 <td>{{GOOD_ID}}</td>
				  					 <td>{{QUANTITY}}</td>
				  					 <td>{{SOLD_TO}}</td>
			      					 <td>{{STATUS}}</td>
                					 </tr>
									 {{/each}}
			  					</script>
								</tbody>
							</table>
						</div>
						<!--订单结束-->
						<!--数据-->
						<div class="tab-pane fade" id="FIGURE">
							<br /> <br />
							<p>This page shows sales in monetary terms and quantity. The
								figures can be filtered to show only specific products, show
								revenue and/or profit, by month or year, and date range. The
								data will also be plotted into a graph automatically</p>
						</div>
						<!--数据-->
						<!--库存-->
						<div class="tab-pane fade" id="INVENTORY">
							<br /> <br />
							<div>
								<button class="btn btn-default ">添加库存</button>
							</div>
							<h2>
								<span class="label label-info">已入库</span>
							</h2>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>商品</th>
										<!-- 对应数据表中无此字段 -->
										<th>ID</th>
										<th>数量</th>
									</tr>
								</thead>
								<tbody id="stock_available">
									<script id="tpl_available" type="text/x-handlebars-template">
			    					{{#each this}}
               						 <tr>
                  					 <td>{{addOne @index}}</td>
									 <td>{{TITLE}}</td>
                  					 <td>{{GOOD_ID}}</td>
				  					 <td>{{QUANTITY}}</td>
                					 </tr>
									 {{/each}}
			  					</script>
								</tbody>
							</table>
							<br />
							<h2>
								<span class="label label-info">未入库</span>
							</h2>
							<table class="table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>商品</th>
										<!-- 对应数据表中无此字段 -->
										<th>ID</th>
										<th>数量</th>
										<th>状态</th>
										<!-- 对应数据表中无此字段 -->
									</tr>
								</thead>
								<tbody id="stock_incoming">
									<script id="tpl_incoming" type="text/x-handlebars-template">
			    						{{#each this}}
               						 <tr>
                  					 <td>{{addOne @index}}</td>
									 <td>{{TITLE}}</td><!-- 对应数据表中无此字段 -->
                  					 <td>{{GOOD_ID}}</td>
				  					 <td>{{QUANTITY}}</td>
			      					 <td>{{STATUS}}</td>
                					 </tr>
									 {{/each}}
			  						</script>
								</tbody>
							</table>
						</div>
						<!--库存结束-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var rows = [{"HAPPEN_DATE":"2017/04/25","ORDER_ID":"4532","PHONE":"13295487536","GOOD_ID":"12563","QUANTITY":"56","SOLD_TO":"China","STATUS":"1"},
					{"HAPPEN_DATE":"2017/04/26","ORDER_ID":"4569","PHONE":"15369874563","GOOD_ID":"1354","QUANTITY":"32","SOLD_TO":"Korea","STATUS":"1"}];

		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		var tpl_ordero = document.getElementById('tpl_order');
		var tpl_ordert = Handlebars.compile(tpl_ordero.innerHTML);
		var order = document.getElementById('order');
		order.innerHTML = tpl_ordert(rows);
    </script>
	<script>
    	var rows = [{"TITLE":"SHIRT","GOOD_ID":"4532","QUANTITY":"56"},
					{"TITLE":"SKIRT","GOOD_ID":"4569","QUANTITY":"32"}];

		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		var tpl_availableo = document.getElementById('tpl_available');
		var tpl_availablet = Handlebars.compile(tpl_availableo.innerHTML);
		var stock_available = document.getElementById('stock_available');
		stock_available.innerHTML = tpl_availablet(rows);
    </script>
	<script>
    	var rows = [{"TITLE":"SHIRT","GOOD_ID":"4532","QUANTITY":"56","STATUS":"1"},
					{"TITLE":"SHIRT","GOOD_ID":"4532","QUANTITY":"56","STATUS":"1"}];

		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		var tpl_incomingo = document.getElementById('tpl_incoming');
		var tpl_incomingt = Handlebars.compile(tpl_incomingo.innerHTML);
		var stock_incoming = document.getElementById('stock_incoming');
		stock_incoming.innerHTML = tpl_incomingt(rows);
    </script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="<%=path %>/js/jquery.min.js"><\/script>')
	</script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=path%>/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>