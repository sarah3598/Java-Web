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
<link rel="stylesheet" href="<%=path%>/css/bootstrap-select.css">
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>订单管理</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a href="<%=path%>/company/orders_manage?method=order_list"> 订单列表 </a></li>
			<%-- <li><a href="<%=path%>/company/orders_manage?method=order_already_pay_list"> 已支付订单 </a></li>
			<li><a href="<%=path%>/company/orders_manage?method=order_wait_delivery_list"> 待发货订单 </a></li>
			<li><a href="<%=path%>/company/orders_manage?method=order_on_delivery_list"> 配送中订单 </a></li>
			<li><a href="<%=path%>/company/orders_manage?method=order_received_list"> 已收货订单 </a></li>
			<li><a href="<%=path%>/company/orders_manage?method=order_failed_pay_list"> 支付失败订单 </a></li>
			<li><a href="<%=path%>/company/orders_manage?method=order_cancel_list"> 普通已取消订单 </a></li>
			<li><a href="<%=path%>/company/orders_manage?method=order_need_check_cancel_list"> 待处理已取消订单 </a></li>
			 --%>
		</ul>
		<!--订单列表  -->
		<div id="emsg_id"></div>
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
						<th>
							<select id="done" class="selectpicker" multiple title="订单状态" data-done-button="true" onchange="query_orders()">
									<option value="0">已取消</option>
									<option value="1">未支付</option>
									<option value="2">已支付</option>
									<option value="3">打包中</option>
									<option value="4">配送中</option>
									<option value="5">已收货</option>
							</select>
						</th>
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
							<td>{{PRODUCT_PRICE}}</td>
							<td>{{BUY_NUM}}</td>
							<td>{{SHIPPING_PRICE}}</td>
							<td>{{ORDER_TOTAL_MONEY}}</td>
							<td>{{help_order ORDER_STATUS}}</td>
							<td>
							<button type="button" class="btn btn-sm btn-success" onclick="upd_order('{{ORDER_REMARKS}}','{{INVOICE_CLIENT}}','{{ADDRESS}}','{{ORDER_ID}}','{{ORDER_NO}}','{{TITLE}}','{{PRODUCT_PRICE}}','{{BUY_NUM}}','{{SHIPPING_PRICE}}','{{DISCOUNT_MONEY}}','{{ORDER_TOTAL_MONEY}}','{{ORDER_STATUS}}','{{HAPPEN_DATE}}','{{HAPPEN_TIME}}','{{USER_ID}}');">详情</button>
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
						<input type="hidden" name="ORDER_ID" id="ORDER_ID" />
						<div class="form-horizontal">
							<div class="form-group">
								<div class="col-md-12">
									订单编号<input class="form-control largcontrol" id="ORDER_NO" name="ORDER_NO" type="text" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品名称<input class="form-control largcontrol" id="TITLE" name="TITLE" type="text" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品价格<input type="text" name="PRODUCT_PRICE" id="PRODUCT_PRICE" class="form-control largcontrol" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									购买数量<input type="text" name="BUY_NUM" id="BUY_NUM" class="form-control largcontrol" > 
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-12">
									运费<input type="text" name="SHIPPING_PRICE" id="SHIPPING_PRICE" class="form-control largcontrol" > 
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									折扣<input type="text" name="DISCOUNT_MONEY" id="DISCOUNT_MONEY" class="form-control largcontrol" > 
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<label>订单总价</label><input type="text" name="ORDER_TOTAL_MONEY" id="ORDER_TOTAL_MONEY" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入订单总价。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									订单状态<input type="text" name="ORDER_STATUS" id="ORDER_STATUS" class="form-control largcontrol" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									订单生成时间 <input type="text" name="HAPPEN_DATE" id="HAPPEN_DATE" class="form-control largcontrol" > <input type="text" name="HAPPEN_TIME" id="HAPPEN_TIME" class="form-control largcontrol" >
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									买家<input type="text" name="USER_ID" id="USER_ID" class="form-control largcontrol" >
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-12">
									<label>收货地址</label><input type="text" name="ADDRESS" id="ADDRESS" class="form-control largcontrol" >
								</div>
							</div>
									
							<div class="form-group">
								<div class="col-md-12">
									<label>发票抬头</label><input type="text" name="INVOICE_CLIENT" id="INVOICE_CLIENT" class="form-control largcontrol" >
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-12">
									<label>备注</label><textarea class="form-control" id="ORDER_REMARKS" name="ORDER_REMARKS" type="text" rows="10"></textarea>
							<span class="text-danger" style="display:none;">1～500个文字</span>
								</div>
							</div>
									
							<div class="form-group text-center">
								<button id="btn_upd" type="button" class="btn btn-qubico" type="button" onclick="update();">更新</button>
								<button type="button" class="btn btn-qubico" onclick="back();">返回</button>
							</div>
							
							
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 修改订单结束 -->
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
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
		}

		function del_order(ORDER_ID){
			if(confirm("确定要删除该订单吗？")){
				var params = ["method=delete_order&ORDER_ID=",ORDER_ID].join("");
				ims_submit_ajax("<%=path%>/company/orders_manage", params, d_cb);	
			}
		}

		function upd_order(ORDER_REMARKS,INVOICE_CLIENT,ADDRESS,ORDER_ID,ORDER_NO,TITLE,PRODUCT_PRICE,BUY_NUM,SHIPPING_PRICE,DISCOUNT_MONEY,ORDER_TOTAL_MONEY,ORDER_STATUS,HAPPEN_DATE,HAPPEN_TIME,USER_ID){
			var opt="0";
			
			if (ORDER_STATUS == 0)//已取消订单，只能查看
				opt="0";
			else if (ORDER_STATUS == 1)//未支付订单，可以修改 总价和地址
				opt="1";
			else if (ORDER_STATUS == 2)//已支付订单，不能修改，只能添加备注
				opt="2";
			else if (ORDER_STATUS == 3)//打包中订单，不能修改  只能点击发货，并添加运单号
				opt="3";
			else if (ORDER_STATUS == 4)//配送中订单，只能查看
				opt="0";
			else if (ORDER_STATUS == 5)//已收货订单，只能查看
				opt="0";
			
			
			
			var row = {"OP":opt,"ORDER_REMARKS":ORDER_REMARKS,"INVOICE_CLIENT":INVOICE_CLIENT,"ADDRESS":ADDRESS,"ORDER_ID":ORDER_ID,"ORDER_NO":ORDER_NO,"TITLE":TITLE,"PRODUCT_PRICE":PRODUCT_PRICE,"BUY_NUM":BUY_NUM,"SHIPPING_PRICE":SHIPPING_PRICE,"DISCOUNT_MONEY":DISCOUNT_MONEY,"ORDER_TOTAL_MONEY":ORDER_TOTAL_MONEY,"ORDER_STATUS":ORDER_STATUS,"HAPPEN_DATE":HAPPEN_DATE,"HAPPEN_TIME":HAPPEN_TIME,"USER_ID":USER_ID};
			obj.init_object(row);
			order_list.style.display = "none";
			order_update.style.display = "";
			
			
			
			
			//只让对应可修改的框的属性可修改
			if (ORDER_STATUS == 0||ORDER_STATUS == 4||ORDER_STATUS == 5){//已取消订单，只能查看
				document.getElementById("ORDER_REMARKS").readOnly=true;
				document.getElementById("ORDER_ID").readOnly=true;
				document.getElementById("ORDER_NO").readOnly=true;
				document.getElementById("TITLE").readOnly=true;
				document.getElementById("PRODUCT_PRICE").readOnly=true;
				document.getElementById("BUY_NUM").readOnly=true;
				document.getElementById("SHIPPING_PRICE").readOnly=true;
				document.getElementById("DISCOUNT_MONEY").readOnly=true;
				document.getElementById("ORDER_TOTAL_MONEY").readOnly=true;
				document.getElementById("ORDER_STATUS").readOnly=true;
				document.getElementById("HAPPEN_DATE").readOnly=true;
				document.getElementById("HAPPEN_TIME").readOnly=true;
				document.getElementById("USER_ID").readOnly=true;
				document.getElementById("INVOICE_CLIENT").readOnly=true;
				document.getElementById("ADDRESS").readOnly=true;
				
				document.getElementById("btn_upd").style.display="none";
			}
			else if (ORDER_STATUS == 1)//未支付订单，可以修改 总价
			{
				document.getElementById("ORDER_REMARKS").readOnly=true;
				document.getElementById("ORDER_ID").readOnly=true;
				document.getElementById("ORDER_NO").readOnly=true;
				document.getElementById("TITLE").readOnly=true;
				document.getElementById("PRODUCT_PRICE").readOnly=true;
				document.getElementById("BUY_NUM").readOnly=true;
				document.getElementById("SHIPPING_PRICE").readOnly=true;
				document.getElementById("DISCOUNT_MONEY").readOnly=true;
				document.getElementById("ORDER_TOTAL_MONEY").readOnly=false;
				document.getElementById("ORDER_STATUS").readOnly=true;
				document.getElementById("HAPPEN_DATE").readOnly=true;
				document.getElementById("HAPPEN_TIME").readOnly=true;
				document.getElementById("USER_ID").readOnly=true;
				document.getElementById("INVOICE_CLIENT").readOnly=true;
				document.getElementById("ADDRESS").readOnly=true;
				
				document.getElementById("btn_upd").style.display="";
			}
			else if (ORDER_STATUS == 2)//已支付订单，不能修改，只能添加备注
			{
				document.getElementById("ORDER_REMARKS").readOnly=false;
				document.getElementById("ORDER_ID").readOnly=true;
				document.getElementById("ORDER_NO").readOnly=true;
				document.getElementById("TITLE").readOnly=true;
				document.getElementById("PRODUCT_PRICE").readOnly=true;
				document.getElementById("BUY_NUM").readOnly=true;
				document.getElementById("SHIPPING_PRICE").readOnly=true;
				document.getElementById("DISCOUNT_MONEY").readOnly=true;
				document.getElementById("ORDER_TOTAL_MONEY").readOnly=false;
				document.getElementById("ORDER_STATUS").readOnly=true;
				document.getElementById("HAPPEN_DATE").readOnly=true;
				document.getElementById("HAPPEN_TIME").readOnly=true;
				document.getElementById("USER_ID").readOnly=true;
				document.getElementById("INVOICE_CLIENT").readOnly=true;
				document.getElementById("ADDRESS").readOnly=true;
				
				document.getElementById("btn_upd").style.display="";
			}
			else if (ORDER_STATUS == 3)//打包中订单，不能修改  只能点击发货
			{
				document.getElementById("ORDER_REMARKS").readOnly=true;
				document.getElementById("ORDER_ID").readOnly=true;
				document.getElementById("ORDER_NO").readOnly=true;
				document.getElementById("TITLE").readOnly=true;
				document.getElementById("PRODUCT_PRICE").readOnly=true;
				document.getElementById("BUY_NUM").readOnly=true;
				document.getElementById("SHIPPING_PRICE").readOnly=true;
				document.getElementById("DISCOUNT_MONEY").readOnly=true;
				document.getElementById("ORDER_TOTAL_MONEY").readOnly=false;
				document.getElementById("ORDER_STATUS").readOnly=true;
				document.getElementById("HAPPEN_DATE").readOnly=true;
				document.getElementById("HAPPEN_TIME").readOnly=true;
				document.getElementById("USER_ID").readOnly=true;
				document.getElementById("INVOICE_CLIENT").readOnly=true;
				document.getElementById("ADDRESS").readOnly=true;
				
				document.getElementById("btn_upd").style.display="";
				document.getElementById("btn_upd").innerHTML="发货";
			}
			
		}

		function query_orders(){
			var s=document.getElementById('done').selectedOptions;
			var ORDER_STATUS="";
			for(var i=0;i<s.length;i++){	
				if(i!=s.length-1)
					ORDER_STATUS+="ORDER_STATUS="+s[i].value+" OR ";
				else
					ORDER_STATUS+="ORDER_STATUS="+s[i].value;
			}
			var page_size=document.getElementById('PAGE_SIZE').value;
			var params = ["method=998&current_page=1&page_size=",page_size,"&ORDER_STATUS=", ORDER_STATUS].join("");
			window.location.href="<%=path%>/company/orders_manage?"+params;
		}
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/orders_manage?method=999&current_page="+current_page+"&page_size="+page_size;
		}


		function ims_show_warn() {
			//this.nextElementSibling.style.display = "";
		}
		
		
		
		function verify_is_price() {
			if(this.value){
				var o = validator.is_price(validator.trim(this.value),this.minLength, this.maxLength);
					if(this.nextElementSibling){
					o ? this.nextElementSibling.style.display = "none": this.nextElementSibling.style.display = "";
				}
			}
		}
		
		Handlebars.registerHelper("help_order", function (ORDER_STATUS) {
			if (ORDER_STATUS == 0)
				return "已取消";
			else if (ORDER_STATUS == 1)
				return "未支付";
			else if (ORDER_STATUS == 2)
				return "已支付";
			else if (ORDER_STATUS == 3)
				return "打包中";
			else if (ORDER_STATUS == 4)
				return "配送中";
			else if (ORDER_STATUS == 5)
				return "已收货";
			else
				return "状态出错！";
		});
		
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/orders_manage?method=999&page_size="+page_size;
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
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
			
			
			
		}
		
		var cols = [{
			id : "form",
			submit:{
				integrate:check_form,
				postform:submitWindow,
				url:'<%=path%>/company/orders_manage?method=996',
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
			init : {}
		},{
			id : "SHIPPING_PRICE",
			init : {}
		},
		{
			id : "DISCOUNT_MONEY",
			init : {}
		},  
		{
			id : "ORDER_TOTAL_MONEY",
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
		},
		{
			id : "ADDRESS",
			init : {}
		},
		{
			id : "INVOICE_CLIENT",
			init : {}
		},
		{
			id : "ORDER_REMARKS",
			init : {}
		},];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
			
			var params='<%=paging.getPageParams()%>';
			if(params!=null){
				var arr=params.split(',');
					$('#done').selectpicker('val', arr);
					//document.getElementById('done').selectpicker('val', params);
				
			}
		}
	</script>
	<%@include file="yb_db_foot_include.jsp"%>
	<script src="<%=path%>/js/bootstrap-select.js"></script>
</body>
</html>