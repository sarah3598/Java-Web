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

<%@include file="yb_base_head_include.jsp"%>
<title>我的订单</title>
</head>

<body>

	<%@include file="yb_base_nav.jsp"%>

	<div class="container-fluid">

		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">我的订单</h1>
				<div id="emsg_id"></div>
				<!--订单列表  -->
				<div id="order_list" class="table-responsive" style="border: 1px solid #eee;">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>序号</th>
								<th>订单编号</th>
								<th>下单时间</th>
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
 				  <td>{{ORDER_NO}}</td>
				  <td>{{help_datetime HAPPEN_DATE HAPPEN_TIME}}</td>
                  <td>{{TITLE}}</td>
                  <td>{{PRODUCT_PRICE}}</td>
				  <td>{{BUY_NUM}}</td>
 				  <td>{{SHIPPING_PRICE}}</td>
 				  <td>{{ORDER_TOTAL_MONEY}}</td>
 				  <td>{{help_status ORDER_STATUS}}</td>
                  <td>
					<button type="button" class="btn btn-sm btn-success" style="display:{{help_btn_style ORDER_STATUS}}" onclick="{{help_fname ORDER_STATUS}}('{{ORDER_ID}}','{{ORDER_STATUS}}');">{{help_opname ORDER_STATUS}}</button>
					<button type="button" class="btn btn-sm btn-success" onclick="upd_order('{{ORDER_ID}}','{{ORDER_NO}}','{{TITLE}}','{{PRODUCT_PRICE}}','{{BUY_NUM}}','{{SHIPPING_PRICE}}','{{DISCOUNT_MONEY}}','{{TOTAL_CHARGE}}','{{ORDER_STATUS}}','{{HAPPEN_DATE}}','{{HAPPEN_TIME}}','{{USER_ID}}','{{CANCEL_REMARK}}');">{{help_opname2 ORDER_STATUS}}</button>
					<!--<button type="button" class="btn btn-sm btn-danger" onclick="del_order('{{ORDER_ID}}');">拒绝收货</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_order('{{ORDER_ID}}');">删除</button>-->
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
								<input type="hidden" name="OP" id="OP" value="2"/>
								<input type="hidden" name="ORDER_ID" id="ORDER_ID" />
								<div class="form-horizontal">
									<div class="form-group">
										<div class="col-md-12">
											<label>订单编号</label> <input class="form-control largcontrol" id="ORDER_NO" name="ORDER_NO" type="text" disabled>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<label>产品名称</label><input class="form-control largcontrol" id="TITLE" name="TITLE" type="text" disabled>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											<label>产品价格</label><input type="text" name="PRODUCT_PRICE" id="PRODUCT_PRICE" class="form-control largcontrol" disabled> <span class="text-danger" style="display: none;">请输入正确的产品价格。</span>
										</div>
									</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>购买数量</label><input type="text" name="BUY_NUM" id="BUY_NUM" class="form-control largcontrol" disabled> <span class="text-danger" style="display: none;">请输入正确的产品数量。</span>
											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>运费</label><input type="text" name="SHIPPING_PRICE" id="SHIPPING_PRICE" class="form-control largcontrol" disabled> <span class="text-danger" style="display: none;">请输入正确的运费。</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>折扣</label><input type="text" name="DISCOUNT_MONEY" id="DISCOUNT_MONEY" class="form-control largcontrol" disabled> <span class="text-danger" style="display: none;">请输入折扣。</span>

											</div>
										</div>

										<div class="form-group">
											<div class="col-md-12">
												<label>订单总价</label><input type="text" name="TOTAL_CHARGE" id="TOTAL_CHARGE" class="form-control largcontrol" disabled> <span class="text-danger" style="display: none;">请输入订单总价。</span>
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
										
										<div id="CANCEL_AREA" class="form-group" style="display:none">
											<div class="col-md-12">
												<label>取消原因</label> 
												<textarea name="CANCEL_REASON" id="CANCEL_REASON" class="form-control" rows="3"></textarea>
												<!-- <input type="text" name="CANCEL_REASON" id="CANCEL_REASON" class="form-control largcontrol" > -->
											</div>
										</div>


									<div class="form-group text-center">
										<input id="BTN_SUBMIT" value="确定" class="btn btn-qubico" style="display:none;" type="button" onclick="update();">
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
		<%String order_list_json = (String) request.getAttribute("order_list");
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
			if(msg.show_msg){
				alert(msg.msg_content);
			}
			test.innerHTML = tplt(arr);
			order_update.style.display = "none";
			order_list.style.display = "";
		}

		function d_cb(arr,msg){	
			if(msg.show_msg){
				alert(msg.msg_content);
			}
			if(arr.length>0){
				test.innerHTML = tplt(arr);
			}else{
				test.innerHTML="暂无数据！";
			}
		}
		
		function r_cb(arr,msg){	
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				if(arr.length>0){
					test.innerHTML = tplt(arr);
				}else{
					test.innerHTML="暂无数据！";
				}
			}, __warn_period);
			
		}
		
		function receive_order(ORDER_ID,ORDER_STATUS){
			if (ORDER_STATUS == 4){
				if(confirm("确定要收货吗？")){
					var page_size=PAGE_SIZE.value;
					var current_page=<%=paging.getCurrentPage()%>;
					var params = ["method=update_order&OP=1&ORDER_ID=", ORDER_ID].join("")+["&page_size=", page_size].join("")+["&current_page=", current_page].join("");
					ims_submit_ajax("<%=path%>/customer/orders_manage", params, r_cb);
				}
				
			}else{
				show_msg_warn("emsg_id","当前订单状态，无法确认收货！");
			}
		}

		function del_order(ORDER_ID){
			if(confirm("确定要删除该订单吗？")){
				var page_size=PAGE_SIZE.value;
				var current_page=<%=paging.getCurrentPage()%>;
				var params = ["method=delete_order&ORDER_ID=", ORDER_ID].join("")+["&page_size=", page_size].join("")+["&current_page=", current_page].join("");
				ims_submit_ajax("<%=path%>/company/orders_manage", params, d_cb);	
			}
		}

		function upd_order(ORDER_ID,ORDER_NO,TITLE,PRODUCT_PRICE,BUY_NUM,SHIPPING_PRICE,DISCOUNT_MONEY,TOTAL_CHARGE,ORDER_STATUS,HAPPEN_DATE,HAPPEN_TIME,USER_ID,CANCEL_REMARK){
			if (ORDER_STATUS == 0){
				ORDER_STATUS="已取消";
				var o = document.getElementById('BTN_SUBMIT');
				o.style.display="none";
				var o2 = document.getElementById('CANCEL_AREA');
				o2.style.display="";
			}
			else if (ORDER_STATUS == 1){
				ORDER_STATUS="待付款";
				var o = document.getElementById('BTN_SUBMIT');
				o.style.display="";
				var o2 = document.getElementById('CANCEL_AREA');
				o2.style.display="";
				
			}
			else if (ORDER_STATUS == 2){
				ORDER_STATUS="已付款";
				var o = document.getElementById('BTN_SUBMIT');
				o.style.display="";
				var o2 = document.getElementById('CANCEL_AREA');
				o2.style.display="";
			}			
			else if (ORDER_STATUS == 3){
				ORDER_STATUS="打包中";
				var o = document.getElementById('BTN_SUBMIT');
				o.style.display="none";
				var o2 = document.getElementById('CANCEL_AREA');
				o2.style.display="none";
			}
			else if (ORDER_STATUS == 4){
				ORDER_STATUS="配送中";
				var o = document.getElementById('BTN_SUBMIT');
				o.style.display="none";
				var o2 = document.getElementById('CANCEL_AREA');
				o2.style.display="none";
			}
			else if (ORDER_STATUS == 5){
				ORDER_STATUS="已完成";
				var o = document.getElementById('BTN_SUBMIT');
				o.style.display="none";
				var o2 = document.getElementById('CANCEL_AREA');
				o2.style.display="none";
			}
				
			
			
			var row = {"OP":"2","ORDER_ID":ORDER_ID,"ORDER_NO":ORDER_NO,"TITLE":TITLE,"PRODUCT_PRICE":PRODUCT_PRICE,"BUY_NUM":BUY_NUM,"SHIPPING_PRICE":SHIPPING_PRICE,"DISCOUNT_MONEY":DISCOUNT_MONEY,"TOTAL_CHARGE":TOTAL_CHARGE,"ORDER_STATUS":ORDER_STATUS,"HAPPEN_DATE":HAPPEN_DATE,"HAPPEN_TIME":HAPPEN_TIME,"USER_ID":USER_ID,"CANCEL_REASON":CANCEL_REMARK};
			obj.init_object(row);
			order_list.style.display = "none";
			order_update.style.display = "";
		}

		function pagination(current_page,page_size){
			window.location.href="<%=path%>/customer/orders_manage?method=order_list&current_page="+current_page+"&page_size="+page_size;
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
		
		
		
		Handlebars.registerHelper("help_status", function (ORDER_STATUS) {
			if (ORDER_STATUS == 0)
				return "已取消";
			else if (ORDER_STATUS == 1)
				return "待付款";
			else if (ORDER_STATUS == 2)
				return "已付款";
			else if (ORDER_STATUS == 3)
				return "打包中";
			else if (ORDER_STATUS == 4)
				return "配送中";
			else if (ORDER_STATUS == 5)
				return "已完成";
			else
				return "状态出错！";
		});
		
		Handlebars.registerHelper("help_datetime", function (DATE,TIME) {
			var date_str = DATE.replace(/(\d{4})(\d{2})(\d{2})/g,'$1-$2-$3');
			return date_str;
		});
		
		Handlebars.registerHelper("help_fname", function (ORDER_STATUS) {
			if (ORDER_STATUS == 1)
				return "go_pay";
			else if (ORDER_STATUS == 4)
				return "receive_order";
			
		});
		
		Handlebars.registerHelper("help_opname", function (ORDER_STATUS) {
			if (ORDER_STATUS == 1)
				return "继续支付";
			else if (ORDER_STATUS == 4)
				return "确认收货";
			
		});
		
		Handlebars.registerHelper("help_opname2", function (ORDER_STATUS) {
			if (ORDER_STATUS == 1||ORDER_STATUS == 2)
				return "取消订单";
			else 
				return "订单详情";
			
		});
		
		Handlebars.registerHelper("help_btn_style", function (ORDER_STATUS) {
			if (ORDER_STATUS == 1||ORDER_STATUS == 4)
				return "";
			else 
				return "none";
			
		});
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/customer/orders_manage?method=order_list&page_size="+page_size;
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
			form.OP=2;
			return true;
		}
		
		function update_order_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				if(data.length>0){
					test.innerHTML = tplt(data);
				}else{
					test.innerHTML="暂无数据！";
				}
			}, __warn_period);	
			order_update.style.display = "none";
			order_list.style.display = "";
			
			
		}
		
		var cols = [{
			id : "form",
			submit:{
				integrate:check_form,
				postform:submitWindow,
				url:'<%=path%>/customer/orders_manage?method=update_order',
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
		{
			id : "ORDER_NO",
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
		},{
			id : "CANCEL_REASON",
			tips : {
				text : "取消原因"
			},
			init : {}
		},];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
		
		
		
	</script>

	<%@include file="yb_base_foot_include.jsp"%>
</body>
</html>