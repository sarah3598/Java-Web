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
<title>Inventory</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>库存管理</h2>
		<ul class="nav nav-tabs">
			<li><a href="<%=path%>/company/product_manage?method=product_stock_avai_list"> 当前库存</a></li>
			<li class="active"><a href="<%=path%>/company/product_manage?method=product_stock_incoming_list"> 进货管理</a></li>
		</ul>
		<div id="emsg_id"></div>
		<div id="stock_list" class="table-responsive" style="border: 1px solid #eee;">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>序号</th>
						<th>产品ID</th>
						<th>产品名称</th>
						<th>进货数量</th>
						<th>供货商</th>
						<th>进货日期</th>
						<th>预计到货时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="test">
					<script id="tpl" type="text/x-handlebars-template">
						{{#each this}}
							<tr>
								<td>{{RN}}</td>
								<td>{{GOOD_ID}}</td>
								<td>{{TITLE}}</td>
								
								<td>{{PLAN_INCOMING_QUANTITY}}</td>
								<td>{{SUPPLIER}}</td>
								<td>{{INCOMING_TIMESTAMP}}</td>
								<td>{{INCOMING_ESTIM_ARRI_TIMESTAMP}}</td>
								<td>
									<button type="button" class="btn btn-sm btn-success" onclick="receive_incoming('{{INCOMING_ID}}','{{GOOD_ID}}','{{TITLE}}','{{PLAN_INCOMING_QUANTITY}}','{{SUPPLIER}}','{{INCOMING_TIMESTAMP}}','{{INCOMING_ESTIM_ARRI_TIMESTAMP}}');">确认收货</button>
									<button type="button" class="btn btn-sm btn-success" onclick="reject_incoming('{{INCOMING_ID}}','{{GOOD_ID}}','{{TITLE}}','{{PLAN_INCOMING_QUANTITY}}','{{SUPPLIER}}','{{INCOMING_TIMESTAMP}}','{{INCOMING_ESTIM_ARRI_TIMESTAMP}}');">拒绝收货</button>
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
					<li><span aria-hidden="true"> 当前：第<%=paging.getCurrentPage()%>页|共<%=paging.getTotalPage()%>页
					</span></li>
					<li><a style="cursor: pointer" onclick="pagination(1,<%=paging.getPageSize()%>);return false;">首页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getFirstPage()%>,<%=paging.getPageSize()%>);return false;">上一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getDownPage()%>,<%=paging.getPageSize()%>);return false;">下一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getTotalPage()%>,<%=paging.getPageSize()%>);return false;">尾页</a></li>
				</ul>
			</nav>
			<!-- 分页结束 -->
		</div>
		<!-- 收货开始 -->
		<div id="add_product_stock" style="display:none" class="tab-pane fade in active" >
			<div class="tab-content">
				<form id="form" method="post">
					<input type="hidden" name="OP" id="OP" value="OP" />
					<input type="hidden" name="GOOD_ID" id="GOOD_ID" value="GOOD_ID" />
					<input type="hidden" name="INCOMING_ID" id="INCOMING_ID" value="INCOMING_ID" />
					<div class="form-horizontal">
						<section id="plan_incoming_section" >
						<div class="form-group">
							<div class="col-md-12">
								<label>产品名称</label><input class="form-control largcontrol" id="TITLE" name="TITLE" type="text" disabled>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label>计划进货数量</label><input type="text" name="PLAN_INCOMING_QUANTITY" id="PLAN_INCOMING_QUANTITY" class="form-control largcontrol" disabled> 
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label>供货商</label><select name="SUPPLIER" id="SUPPLIER" class="form-control largcontrol" disabled></select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label>预计到货时间</label><input type="text" name="INCOMING_ESTIM_ARRI_DATE" id="INCOMING_ESTIM_ARRI_DATE" class="form-control largcontrol" disabled> 
							</div>
						</div>
						</section>
						<section id="receive_incoming_section" style="display:none">
						<div class="form-group">
							<div class="col-md-12">
								<label>实际到货数量</label><input type="text" name="REAL_INCOMING_QUANTITY" id="REAL_INCOMING_QUANTITY" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入实际到货数量。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label>实际到货时间</label><input type="text" name="REAL_INCOMING_ARRI_TIMESTAMP" id="REAL_INCOMING_ARRI_TIMESTAMP" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入实际到货时间。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label>收货单据上传</label><input type="text" name="INCOMING_RECEIPT_IMAGE_ID" id="INCOMING_RECEIPT_IMAGE_ID" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请上传收货单据图片。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label>收货人</label><input type="text" name="LOGIN_ACCOUNT" id="LOGIN_ACCOUNT" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请填写收货人。</span>
							</div>
						</div>								
						</section>
						<section id="reject_incoming_section" style="display:none">
						<div class="form-group">
							<div class="col-md-12">
								<label>拒收货理由</label><input type="text" name="REJECT_REASON" id="REJECT_REASON" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请填拒收货理由。</span>
							</div>
						</div>	
						</section>
						<div class="form-group text-center">
							<button class="btn btn-qubico" type="button" onclick="add();">提交</button>
							<button type="button" class="btn btn-qubico" onclick="back();">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- 收货结束 -->
	</div>
	<script>
		<%String stock_list_json = (String) request.getAttribute("stock_list");
			if (stock_list_json == "") {
				stock_list_json = "[]";
			}%>
		
		var obj, rows =<%=stock_list_json%>;
		
		function show(flag){
			if(flag==1){//show receive
				stock_list.style.display = "none";
				add_product_stock.style.display = "";			
				receive_incoming_section.style.display = "";
			}
			else if(flag==2){//back
				stock_list.style.display = "";
				add_product_stock.style.display = "none";			
				receive_incoming_section.style.display = "none";
			}
			else if(flag==3){//show reject
				stock_list.style.display = "none";
				add_product_stock.style.display = "";			
				reject_incoming_section.style.display = "";
			}
			else if(flag==4){//back
				stock_list.style.display = "";
				add_product_stock.style.display = "none";			
				reject_incoming_section.style.display = "none";
			}
		}
		
	
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		
		function add(){
			obj.submit();
		}
			
		function back(){
			show(2);
			show(4);
		}
		
		
		function receive_incoming(INCOMING_ID,GOOD_ID,TITLE,PLAN_INCOMING_QUANTITY,SUPPLIER,INCOMING_TIMESTAMP,INCOMING_ESTIM_ARRI_TIMESTAMP){
			
			var row = {"OP":1,"INCOMING_ID":INCOMING_ID,"GOOD_ID":GOOD_ID,"TITLE":TITLE,"PLAN_INCOMING_QUANTITY":PLAN_INCOMING_QUANTITY ,"SUPPLIER":SUPPLIER,"INCOMING_TIMESTAMP":INCOMING_TIMESTAMP,"INCOMING_ESTIM_ARRI_TIMESTAMP":INCOMING_ESTIM_ARRI_TIMESTAMP};
			obj.init_object(row);			
			show(1);					
		}
		
		function reject_incoming(INCOMING_ID,GOOD_ID,TITLE,PLAN_INCOMING_QUANTITY,SUPPLIER,INCOMING_TIMESTAMP,INCOMING_ESTIM_ARRI_TIMESTAMP){
			
			var row = {"OP":2,"INCOMING_ID":INCOMING_ID,"GOOD_ID":GOOD_ID,"TITLE":TITLE,"PLAN_INCOMING_QUANTITY":PLAN_INCOMING_QUANTITY ,"SUPPLIER":SUPPLIER,"INCOMING_TIMESTAMP":INCOMING_TIMESTAMP,"INCOMING_ESTIM_ARRI_TIMESTAMP":INCOMING_ESTIM_ARRI_TIMESTAMP};
			obj.init_object(row);
			show(3);
								
		}

		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/product_manage?method=product_stock_avai_list&current_page="+current_page+"&page_size="+page_size;
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
		
		function verify_is_name(){
			if(this.value){
				var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
					if(this.nextElementSibling){
					o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
				}
			}
		}
		function verify_is_changed()
		{
			var v=this.value;
			var o=document.getElementById("SUPPLIER");
			v=="9999"?o.nextElementSibling.style.display = "":o.nextElementSibling.style.display = "none";
		}
		
		function check_form()
		{
			
			var o=document.getElementById("SUPPLIER");
			var v=o.value;
			if(v=="9999"){
				o.nextElementSibling.style.display = "";
				return false;
			}
			return true;
		}
		
		
		
		
		
		
		function cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
						
		}
		
		
		
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/product_manage?method=product_stock_incoming_list&page_size="+page_size;
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows);
		}
	
		var arr0 = [["3","每页3条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		var arr1 = [["9999","请选择供货商"],["0","无"],["1","供货商A"],["2","供货商B"],["3","供货商C"]];
	
		var cols = [{
			id : "form",
			submit:{
				integrate:false,
				postform:submitWindow,
				url:'<%=path%>/company/product_manage?method=product_stock_incoming_receive',
				refresh:cb,
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
		},{
			id : "INCOMING_ID",
			init : {}
		},{
			id : "GOOD_ID",
			init : {}
		},
		{
			id : "TITLE",
			init : {}
		},{
			id : "INCOMING_TIMESTAMP",
			init : {}
		},
		{
			id : "PLAN_INCOMING_QUANTITY",
			init : {}
		},  {
			id : "SUPPLIER",
			tips : {
				action : ims_show_warn,
				text : "请选择供货商"
			},
			init : {
				multiple : false,
				vdefault : 9999,
				value : 0,
				text : 1,
				ds : arr1
			}
		}, 
		{
			id : "INCOMING_ESTIM_ARRI_TIMESTAMP",			
			init : {}
		}, {
			id : "INCOMING_RECEIPT_IMAGE_ID",			
			init : {}
		},{
			id : "REAL_INCOMING_QUANTITY",
			tips : {
				action : ims_show_warn,
				text : "实际到货数量"
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
		}, {
			id : "REAL_INCOMING_ARRI_TIMESTAMP",			
			init : {}
		},{
			id : "REJECT_REASON",
			tips : {
				action : ims_show_warn,
				text : "拒绝收货原因"
			},
			init : {
				minLength : 1,
				maxLength : 200,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_name
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, ];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
    </script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>