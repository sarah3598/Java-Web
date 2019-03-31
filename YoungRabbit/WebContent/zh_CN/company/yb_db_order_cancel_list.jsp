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
					<li><a href="<%=path%>/company/orders_manage?method=order_received_list"> 已收货订单 </a></li>
					<li><a href="<%=path%>/company/orders_manage?method=order_failed_pay_list"> 支付失败订单 </a></li>
					<li class="active"><a href="<%=path%>/company/orders_manage?method=order_cancel_list"> 普通已取消订单 </a></li>
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
								<th>产品价格(元)</th>
								<th>购买数量</th>
								<th>运费(元)</th>
								<th>订单总价(元)</th>
								<th>订单状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="test">
							<script id="tpl" type="text/x-handlebars-template">
			  {{#each this}}
                <tr>
                  <td>{{RN}}</td>
 				  <td>{{ORDER_ID}}</td>
                  <td>{{TITLE}}</td>
                  <td>{{PRODUCT_PRICE}}</td>
				  <td>{{BUY_NUM}}</td>
 				  <td>{{SHIPPING_PRICE}}</td>
 				  <td>{{TOTAL_CHARGE}}</td>
 				  <td>{{help_pay PAY_STATUS}}</td>
                  <td>
					<button type="button" class="btn btn-sm btn-success" onclick="upd_order('{{ORDER_ID}}','{{TITLE}}','{{PRODUCT_PRICE}}','{{BUY_NUM}}','{{SHIPPING_PRICE}}','{{DISCOUNT_MONEY}}','{{TOTAL_CHARGE}}','{{ORDER_STATUS}}','{{HAPPEN_DATE}}','{{HAPPEN_TIME}}','{{USER_ID}}');">修改</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_order('{{ORDER_ID}}');">删除</button>

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
							<li><span aria-hidden="true"> 当前是第：<%=paging.getCurrentPage()%>页|共<%=paging.getTotalPage()%>页
							</span></li>
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

						<div id="addproducts" >
							<form id="form" method="post">
								<input type="hidden" name="OP" id="OP" />
								<div class="form-horizontal">
									<div class="form-group">
										<div class="col-md-12">
											<label>订单编号</label> <input class="form-control largcontrol" id="ORDER_ID" name="ORDER_ID" type="text" disabled>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<label>产品名称</label><input class="form-control largcontrol" id="TITLE" name="TITLE" type="text" disabled>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											<label>产品价格</label><input type="text" name="PRODUCT_PRICE" id="PRODUCT_PRICE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的产品价格。</span>
										</div>
									</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>购买数量</label><input type="text" name="BUY_NUM" id="BUY_NUM" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的产品数量。</span>
											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>运费</label><input type="text" name="SHIPPING_PRICE" id="SHIPPING_PRICE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的运费。</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>折扣</label><input type="text" name="DISCOUNT_MONEY" id="DISCOUNT_MONEY" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入折扣。</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>订单总价</label><input type="text" name="TOTAL_CHARGE" id="TOTAL_CHARGE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入订单总价。</span>
											</div>
										</div>
										<div class="form-group">
											<div class="col-md-12">
												<label>订单状态</label><input type="text" name="ORDER_STATUS" id="ORDER_STATUS" class="form-control largcontrol" disabled>

											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>订单生成时间</label> <input type="text" name="HAPPEN_DATE" id="HAPPEN_DATE" class="form-control largcontrol" disabled> <input type="text" name="HAPPEN_TIME" id="HAPPEN_TIME" class="form-control largcontrol" disabled>

											</div>
										</div>
										<div class="form-group">
											<div class="col-md-12">
												<label>买家</label> <input type="text" name="USER_ID" id="USER_ID" class="form-control largcontrol" disabled>

											</div>
										</div>


									<div class="form-group text-center">
										<input value="更新" class="btn btn-qubico" type="button" onclick="update();">
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
		<%String order_list_json = (String) request.getAttribute("order_cancel_list");
			if (order_list_json == "") {
				order_list_json = "[]";
			}%>

		var obj, rows =<%=order_list_json%>;

		function update(){
			obj.submit();
		}
		
		function back(){
			order_update.style.display = "none";
			order_list.style.display = "";
		}
		
		function cb1(arr){			
			test.innerHTML = tplt(arr);
			
		}

		function cb(arr){			
			test.innerHTML = tplt(arr);
			order_update.style.display = "none";
			order_list.style.display = "";
		}

		function cb(arr,msg){	
			
			test.innerHTML = tplt(arr);
			order_update.style.display = "none";
			order_list.style.display = "";
		}

		function d_cb(arr,msg){	
			
			if(arr.length>0){
				test.innerHTML = tplt(arr);
			}else{
				test.innerHTML="暂无数据！";
			}
		}

		function del_order(ORDER_ID){
			if(confirm("确定要删除该订单吗？")){
				var page_size=PAGE_SIZE.value;
				var current_page=<%=paging.getCurrentPage()%>;
				var params = ["method=delete_order&OP=3&ORDER_ID=", ORDER_ID].join("")+["&page_size=", page_size].join("")+["&current_page=", current_page].join("");
				ims_submit_ajax("<%=path%>/company/orders_manage", params, d_cb);	
			}
		}

		function upd_order(ORDER_ID,TITLE,PRODUCT_PRICE,BUY_NUM,SHIPPING_PRICE,DISCOUNT_MONEY,TOTAL_CHARGE,ORDER_STATUS,HAPPEN_DATE,HAPPEN_TIME,USER_ID){
			var row = {"OP":"1","ORDER_ID":ORDER_ID,"TITLE":TITLE,"PRODUCT_PRICE":PRODUCT_PRICE,"BUY_NUM":BUY_NUM,"SHIPPING_PRICE":SHIPPING_PRICE,"DISCOUNT_MONEY":DISCOUNT_MONEY,"TOTAL_CHARGE":TOTAL_CHARGE,"ORDER_STATUS":ORDER_STATUS,"HAPPEN_DATE":HAPPEN_DATE,"HAPPEN_TIME":HAPPEN_TIME,"USER_ID":USER_ID};
			obj.init_object(row);
			order_list.style.display = "none";
			order_update.style.display = "";
		}

		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/orders_manage?method=order_no_pay_list&current_page="+current_page+"&page_size="+page_size;
		}


		function ims_show_warn() {
			//this.nextElementSibling.style.display = "";
		}
		
		function verify_is_percent() {
			if(this.value){
				var o = validator.is_percent(validator.trim(this.value),this.minLength, this.maxLength);
				if(this.nextElementSibling){
					o ? this.nextElementSibling.style.display = "none": this.nextElementSibling.style.display = "";
				}
			}
		}
		
		function verify_is_price() {
			if(this.value){
				var o = validator.is_price(validator.trim(this.value),this.minLength, this.maxLength);
					if(this.nextElementSibling){
					o ? this.nextElementSibling.style.display = "none": this.nextElementSibling.style.display = "";
				}
			}
		}
		
		Handlebars.registerHelper("help_pay", function (PAY_STATUS) {
			if (PAY_STATUS == 0)
				return "未支付";
			else if (PAY_STATUS == 1)
				return "已支付";
			else if (PAY_STATUS == -1)
				return "支付失败";
			else
				return "状态出错！";
		});
		
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/orders_manage?method=order_list&page_size="+page_size;
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows);
		}
		
		function check_form(){
			return true;
		}
		
		function update_order_cb(data, msg){
			if(msg.show_msg){
				alert(msg.msg_content);
			}
			test.innerHTML = tplt(data);
			order_update.style.display = "none";
			order_list.style.display = "";
			
			
		}
		
		var cols = [{
			id : "form",
			submit:{
				integrate:check_form,
				postform:submitWindow,
				url:'<%=path%>/company/orders_manage?method=update_order',
				refresh:update_order_cb,
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
		{   id:"GOOD_ID", 
			init : {}
		},  
		{   id:"TITLE", 
			init : {}
		}, 
		{   id:"PRODUCT_PRICE", 
			init : {}
		}, 
		{
			id : "BUY_NUM",
			tips : {
				action : ims_show_warn,
				text : "购买数量"
			},
			init : {
				minLength : 1,
				maxLength : 10,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify_is_price
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_price
			}
		},{
			id : "SHIPPING_PRICE",
			tips : {
				action : ims_show_warn,
				text : "运费"
			},
			init : {
				minLength : 1,
				maxLength : 10,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify_is_price
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_price
			}
		},
		{
			id : "DISCOUNT_MONEY",
			tips : {
				action : ims_show_warn,
				text : "折扣"
			},
			init : {
				minLength : 1,
				maxLength : 10,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify_is_price
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_price
			}
		},  
		{
			id : "TOTAL_CHARGE",
			tips : {
				action : ims_show_warn,
				text : "订单总价"
			},
			init : {
				minLength : 1,
				maxLength : 10,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify_is_price
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_price
			}
		}, 
		{
			id : "ORDER_STATUS",
			init : {}
		}, 
		{
			id : "HAPPEN_DATE",
			init : {}
		},
		{
			id : "HAPPEN_TIME",
			init : {}
		},
		{
			id : "USER_ID",
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