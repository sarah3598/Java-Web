<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Pagination paging = (Pagination) request.getAttribute("page");
%>



<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>

<%@include file="yb_db_head_include.jsp"%>
<%@include file="yb_file_input_include.jsp"%>
<title>Product List</title>
</head>

<body>

	<%@include file="yb_db_nav.jsp"%>

	<div class="container-fluid">

		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">订单管理</h1>
				<!--产品管理二级菜单导航  开始-->
				<ul class="nav nav-tabs">
					<li><a href="<%=path%>/company/orders_manage?method=order_no_pay_list"> 未支付订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_already_pay_list"> 已支付订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_wait_delivery_list"> 待发货订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_on_delivery_list"> 配送中订单 </a></li>
					<li class="active"><a href="<%=path%>/company/orders_manage?method=order_received_list"> 已收货订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_failed_pay_list"> 支付失败订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_cancel_list"> 普通已取消订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_need_check_cancel_list"> 待处理已取消订单 </a></li>
				</ul>
				<!--产品管理二级菜单导航  结束-->
				<!--订单列表  -->
				<div id="order_list" class="table-responsive" style="border: 1px solid #eee;">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>订单编号</th>
								<th>产品名称</th>								
								<th>发货数量</th>
								<th>货物总重量(kg)</th>
								<th>运费(元)</th>
								<th>发票抬头</th>
								<th>收货地址</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="test">
							<script id="tpl" type="text/x-handlebars-template">
			  {{#each this}}
                <tr>
                  <td>{{RN}}</td>
 				  <td>{{ORDER_NO}}</td>
                  <td>{{TITLE}}</td>
                  <td>{{BUY_NUM}}</td>
				  <td>{{help_weight BUY_NUM PRODUCT_WEIGHT}}</td>
 				  <td>{{SHIPPING_PRICE}}</td>
				  <td>{{help_invoice IS_INVOICE INVOICE_CLIENT}}</td>
				  <td>{{USER_NAME}} {{AREA_ID1}} {{AREA_ID2}}{{USER_PHONE}}</td>
 				
                  <td>
					
					<button type="button" class="btn btn-sm btn-success" onclick="detail('{{ORDER_ID}}','{{GOOD_ID}}','{{ORDER_NO}}','{{HAPPEN_DATE}}','{{HAPPEN_TIME}}','{{TITLE}}','{{BUY_NUM}}','{{PRODUCT_WEIGHT}}','{{SHIPPING_PRICE}}','{{ADDRESS_ID}}','{{IS_INVOICE}}','{{INVOICE_CLIENT}}','{{USER_NAME}}','{{AREA_ID1}}','{{AREA_ID2}}','{{AREA_ID3}}','{{ADDRESS}}','{{USER_PHONE}}','{{USER_TEL}}');">详情</button>
					
				  </td>
                </tr>
				{{/each}}
			  </script>
						</tbody>
					</table>
					<!-- 分页开始 -->
					<nav aria-label="Page navigation">
						<ul class="pagination">
							<li><select style="width: 150px;" class="form-control largcontrol" id="PAGE_SIZE" name="PAGE_SIZE"></select></li>
							<li><span aria-hidden="true"> 当前是第：<%=paging.getCurrentPage()%>页|共<%=paging.getTotalPage()%>页	</span></li>
							<li><a style="cursor: pointer" onclick="pagination(1,<%=paging.getPageSize()%>);return false;">首页</a></li>
							<li><a style="cursor: pointer" onclick="pagination(<%=paging.getFirstPage()%>,<%=paging.getPageSize()%>);return false;">上一页</a></li>
							<li><a style="cursor: pointer" onclick="pagination(<%=paging.getDownPage()%>,<%=paging.getPageSize()%>);return false;">下一页</a></li>
							<li><a style="cursor: pointer" onclick="pagination(<%=paging.getTotalPage()%>,<%=paging.getPageSize()%>);return false;">尾页</a></li>
						</ul>
					</nav>
					<!-- 分页结束 -->

				</div>

				<!-- 修改订单开始 -->
				<div id="order_update" style="display: none" class="container-fluid">
					<div class="row-fluid">

						<div id="addproducts">
							<form id="form" method="post">
								<input type="hidden" name="OP" id="OP" />
								<input type="hidden" name="ORDER_ID" id="ORDER_ID" />
								<input type="hidden" name="GOOD_ID" id="GOOD_ID" />
								<div class="form-horizontal">
									<div class="form-group">
										<div class="col-md-12">
											<label>订单编号</label> <input class="form-control largcontrol" id="ORDER_NO" name="ORDER_NO" type="text" disabled>
										</div>
									</div>
									
									<div class="form-group">
											<div class="col-md-12">
												<label>订单生成时间</label> <input type="text" name="CREATE_TIME" id="CREATE_TIME" class="form-control largcontrol" disabled> 

											</div>
									</div>
									
									<div class="form-group">
										<div class="col-md-12">
											<label>产品名称</label><input class="form-control largcontrol" id="TITLE" name="TITLE" type="text" disabled>
										</div>
									</div>
				

									<div class="form-group">
										<div class="col-md-12">
											<label>发货数量</label><input type="text" name="BUY_NUM" id="BUY_NUM" class="form-control largcontrol" disabled>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											<label>货物总重量(kg)</label><input type="text" name="TOTAL_WEIGHT" id="TOTAL_WEIGHT" class="form-control largcontrol" disabled>

										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											<label>运费</label><input type="text" name="SHIPPING_PRICE" id="SHIPPING_PRICE" class="form-control largcontrol" disabled>

										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											<label>收货地址</label><input type="text" name="ADDRESS" id="ADDRESS" class="form-control largcontrol" disabled>
										</div>
									</div>
									
									<div class="form-group">
										<div class="col-md-12">
											<label>发票抬头</label><input type="text" name="INVOICE_CLIENT" id="INVOICE_CLIENT" class="form-control largcontrol" disabled>
										</div>
									</div>
									
								

									<div class="form-group text-center">
										
										<button type="button" class="btn btn-qubico" onclick="back();">返回</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- 修改订单结束 -->
			</div>
		</div>
	</div>
	<script>
		<%String order_list_json = (String) request.getAttribute("order_received_list");
			if (order_list_json == "") {
				order_list_json = "[]";
			}%>

		var obj, rows =<%=order_list_json%>;
		
		function back(){
			
			order_list.style.display = "";
			order_update.style.display = "none";
		}
		
		function detail(ORDER_ID,GOOD_ID,ORDER_NO,HAPPEN_DATE,HAPPEN_TIME,TITLE,BUY_NUM,PRODUCT_WEIGHT,SHIPPING_PRICE,ADDRESS_ID,IS_INVOICE,INVOICE_CLIENT,USER_NAME,AREA_ID1,AREA_ID2,AREA_ID3,ADDRESS,USER_PHONE,USER_TEL){
			
			var create_time=HAPPEN_DATE+" "+HAPPEN_TIME;
			var tweight=BUY_NUM*PRODUCT_WEIGHT;			
			var address=[,USER_NAME," ",AREA_ID1," ",AREA_ID2," ",AREA_ID3," ",ADDRESS," ",USER_PHONE," ",USER_TEL].join("");
			var INVOICE_CLIENT=IS_INVOICE==1?INVOICE_CLIENT:"不开发票";
			
			var row = {"OP":"1","ORDER_ID":ORDER_ID,"ORDER_NO":ORDER_NO,"ORDER_ID":ORDER_ID,"CREATE_TIME":create_time,"TITLE":TITLE,"BUY_NUM":BUY_NUM,"TOTAL_WEIGHT":tweight,"SHIPPING_PRICE":SHIPPING_PRICE,"ADDRESS":address,"INVOICE_CLIENT":INVOICE_CLIENT};
			
			
			obj.init_object(row);
			order_list.style.display = "none";
			order_update.style.display = "";
		}
	


		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/orders_manage?method=order_received_list&current_page="+current_page+"&page_size="+page_size;
		}
		
		Handlebars.registerHelper("help_weight", function (BUY_NUM,PRODUCT_WEIGHT) {
			var tweight=BUY_NUM*PRODUCT_WEIGHT;
			return tweight;
		});
		
		Handlebars.registerHelper("help_invoice", function (IS_INVOICE,INVOICE_CLIENT) {
			if(IS_INVOICE==1)
				return INVOICE_CLIENT;
			else
				return "不开发票";
			
		});
		
		Handlebars.registerHelper("help_address", function (ADDRESS_ID) {
			
			return "获取地址";
			
		});

		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/orders_manage?method=order_received_list&page_size="+page_size;
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows);
		}
		
		var cols = [{
			id : "form",
			submit:{
	
			}
		},{
			id : "PAGE_SIZE",
			init : {
				multiple : false,
				vdefault : <%=paging.getPageSize()%>,
				value : 0,
				text : 1,
				ds : arr0
			}
		},{
			id : "OP",
			init : {}
		}, //OP是标志位 1:添加  2:修改
		{
			id : "ORDER_ID",
			init : {}
		},
		{
			id : "GOOD_ID",
			init : {}
		},
		{
			id : "ORDER_NO",
			init : {}
		},
		{
			id : "CREATE_TIME",
			init : {}
		}, 
		{   id:"TITLE", 
			init : {}
		}, 
		{
			id : "BUY_NUM",
			init : {}
		},
		{
			id : "TOTAL_WEIGHT",
			init : {}
		}, 
		{
			id : "SHIPPING_PRICE",
			init : {}
		},
		{
			id : "ADDRESS",
			init : {}
		},
		{
			id : "INVOICE_CLIENT",
			init : {}
		},];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
		
		
		
	</script>

	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>