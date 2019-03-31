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
<link rel="stylesheet" href="<%=path%>/css/bootstrap-select.css">
<title>Product List</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>产品管理</h2>
		<ul class="nav nav-tabs">
			<li class="active"><a href="<%=path%>/company/product_manage?method=product_list"> 产品列表 </a></li>
			<li><a href="<%=path%>/zh_CN/company/yb_db_product_add.jsp"> 添加产品 </a></li>
			<!-- <li><a href="#"> 产品捆绑 </a></li> -->
		</ul>
		<div id="emsg_id"></div>
		<!--产品列表  -->
		<div id="product_list" class="table-responsive">
			<table class="table table-striped" style="border: 1px solid #eee;">
				<thead>
					<tr>
						<th>序号</th>
						<th>产品编号</th>
						<th>产品缩略图</th>
						<th>产品名称</th>
						<th>产品价格(元)</th>
						<th>售卖状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="test">
					<script id="tpl" type="text/x-handlebars-template">
						{{#each this}}
						<tr>
						  <td>{{RN}}</td>
						  <td>{{GOOD_ID}}</td>
						  <td>
								<input type="hidden" id="H_IMAGE_ID_{{IMAGE_ID}}" value="" />
								<div id="IMAGE_ID_{{IMAGE_ID}}">{{ help_image_id IMAGE_ID}}</div>
						  </td>
						  <td>{{TITLE}}</td>
						  <td>{{PRODUCT_PRICE}}</td>
						  <td>{{help_hide_mark HIDE_MARK}}</td>
						  <td>
							<button type="button" class="btn btn-sm btn-success" onclick="upd_product('{{GOOD_ID}}','{{IMAGE_ID}}','{{HIDE_MARK}}','{{TITLE}}','{{BANNED_COUNTRIES}}','{{PRODUCT_PRICE}}','{{CURRENCY}}','{{COMMISSION_MODEL}}','{{SALE_COMMISSION}}','{{SS_COMMISSION}}','{{GL_COMMISSION}}','{{MA_COMMISSION}}','{{VP_COMMISSION}}','{{GL_OVERRIDING}}','{{MA_OVERRIDING}}','{{VP_OVERRIDING}}','{{OWNERSHIP_COMMISSION}}','{{WHOLESALE_MODEL}}','{{WHOLESALE_PRICE}}','{{AGENT_PRICE}}','{{PRODUCT_WEIGHT}}','{{SHIPPING_PRICE}}');">详情</button>
							<button type="button" class="btn btn-sm btn-danger" onclick="del_product('{{GOOD_ID}}');">下架</button>

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

		<!-- 修改产品开始 -->
		<div id="product_add" style="display: none" class="container-fluid">
			<div class="row-fluid">
				<div style="margin-right: 50px; margin-top: 10px; float: right; width: 350px;">
					<form id="fileform" name="fileform" enctype="multipart/form-data">
						<!-- image-preview-filename input [CUT FROM HERE]-->
						<label class="control-label">请先上传产品图片</label><span id="UPLOAD_IMAGE_WARN" class="text-danger" style="display: none;">请先上传产品图片。</span> <input id="input-44" name="input44[]" type="file" multiple class="file-loading">
						<div class="form-group text-center">
							<input value="更新图片" class="btn btn-qubico" type="submit">
						</div>
						
						<div id="errorBlock" class="help-block"></div>
					</form>
				</div>
				<div id="addproducts" style="margin-left: 10px; margin-right: 120px; margin-top: 10px; float: right; width: 400px;">
					<form id="form" method="post">
						<input type="hidden" name="OP" id="OP" /> <input type="hidden" name="IMAGE_ID" id="IMAGE_ID" value="-1" /> <input type="hidden" name="GOOD_ID" id="GOOD_ID" value="-1" />
						<div class="form-horizontal">
							<div class="form-group">
								<div class="col-md-12">
									<input id="HIDE_MARK" name="HIDE_MARK" type="checkbox" value="0">隐藏该产品
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品名称<input class="form-control largcontrol" id="TITLE" name="TITLE" type="text"> <span class="text-danger" style="display: none;">请输入产品名称</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品重量(kg)<input class="form-control largcontrol" id="PRODUCT_WEIGHT" name="PRODUCT_WEIGHT" type="text"> <span class="text-danger" style="display: none;">请输入产品重量</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品禁售的国家或地区<input type="hidden" name="BANNED_COUNTRIES" id="BANNED_COUNTRIES" />
									<select id="done"  class="selectpicker" multiple title="禁售的国家或地区" data-done-button="true" >
										<option value="1">马来西亚</option>
										<option value="2">新加坡</option>
										<option value="3">中国</option>
									</select>
									<span id="BANNED_COUNTRIES_WARN" class="text-danger" style="display: none;">请选择该产品禁售的国家或地区。</span>
									
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品价格<input type="text" name="PRODUCT_PRICE" id="PRODUCT_PRICE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的产品价格。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									运费<input type="text" name="SHIPPING_PRICE" id="SHIPPING_PRICE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的运费。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									产品售价单位<select id="CURRENCY" name="CURRENCY" class="form-control largcontrol">
									</select><span id="CURRENCY_WARN" class="text-danger" style="display: none;">请选择该产品售价单位。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<input name="COMMISSION_MODEL" id="COMMISSION_MODEL" type="checkbox"> 佣金模式
								</div>
							</div>
							<section id="commission_model_section" style="display: none;">
								<div class="form-group">
									<div class="col-md-12">销售代理的销售佣金(RM)
										<input type="text" name="SALE_COMMISSION" id="SALE_COMMISSION" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">独家代理的销售佣金(RM)
										<input type="text" name="SS_COMMISSION" id="SS_COMMISSION" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">组长的销售佣金(RM)
										<input type="text" name="GL_COMMISSION" id="GL_COMMISSION" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">经理的销售佣金(RM)
										<input type="text" name="MA_COMMISSION" id="MA_COMMISSION" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">副总裁的销售佣金(RM)
										<input type="text" name="VP_COMMISSION" id="VP_COMMISSION" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">组长的覆盖佣金(RM)
										<input type="text" name="GL_OVERRIDING" id="GL_OVERRIDING" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">经理的覆盖佣金(RM)
										<input type="text" name="MA_OVERRIDING" id="MA_OVERRIDING" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">副总裁的覆盖佣金(RM)
										<input type="text" name="VP_OVERRIDING" id="VP_OVERRIDING" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">产品所有者的佣金(RM)
										<input type="text" name="OWNERSHIP_COMMISSION" id="OWNERSHIP_COMMISSION" class="form-control largcontrol"> 
										<span class="text-danger" style="display: none;">请输入提取佣金金额。</span>
									</div>
								</div>
							</section>
							<div class="form-group">
								<div class="col-md-12">
									<input name="WHOLESALE_MODEL" id="WHOLESALE_MODEL" type="checkbox"> 批发模式 <br>
								</div>
							</div>
							<section id="wholesale_model_section" style="display: none;">
								<div class="form-group">
									<div class="col-md-12">
										批发价格<input type="text" name="WHOLESALE_PRICE" id="WHOLESALE_PRICE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的批发价格(0~999999)。</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										代理价格<input type="text" name="AGENT_PRICE" id="AGENT_PRICE" class="form-control largcontrol"> <span class="text-danger" style="display: none;">请输入正确的代理价格(0~999999)。</span>
									</div>
								</div>
							</section>
							<div class="form-group text-center">
								<input value="更新" class="btn btn-qubico" type="button" onclick="update();">
								<button type="button" class="btn btn-qubico" onclick="back();">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- 修改产品结束 -->
	</div>
	<script>
		<%String product_list_json = (String) request.getAttribute("product_list");
			if (product_list_json == "") {
				product_list_json = "[]";
			}%>

		var obj, rows =<%=product_list_json%>;
		var file_path="<%=path%>/files/upload/product_image/cat1.jpg";
		function update(){
			obj.submit();
		}
		
		function back(){
			product_add.style.display = "none";
			product_list.style.display = "";
		}
		
		function cb1(arr){			
			test.innerHTML = tplt(arr);
			
		}

		function cb(arr){			
			test.innerHTML = tplt(arr);
			product_add.style.display = "none";
			product_list.style.display = "";
		}

		function cb(arr,msg){	
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			test.innerHTML = tplt(arr);
			product_add.style.display = "none";
			product_list.style.display = "";
		}

		function d_cb(arr,msg){	
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			if(arr.length>0){
				test.innerHTML = tplt(arr);
			}else{
				test.innerHTML="暂无数据！";
			}
		}

		function del_product(GOOD_ID){
			if(confirm("确定要下架该产品吗？")){
				var page_size=PAGE_SIZE.value;
				var current_page=<%=paging.getCurrentPage()%>;
				var params = ["method=product_delete&GOOD_ID=", GOOD_ID].join("")+["&page_size=", page_size].join("")+["&current_page=", current_page].join("");
				ims_submit_ajax("<%=path%>/company/product_manage", params, d_cb);	
			}
		}

																												
		function upd_product(GOOD_ID,IMAGE_ID,HIDE_MARK,TITLE,BANNED_COUNTRIES,PRODUCT_PRICE,CURRENCY,COMMISSION_MODEL,SALE_COMMISSION,SS_COMMISSION,GL_COMMISSION,MA_COMMISSION,VP_COMMISSION,GL_OVERRIDING,MA_OVERRIDING,VP_OVERRIDING,OWNERSHIP_COMMISSION,WHOLESALE_MODEL,WHOLESALE_PRICE,AGENT_PRICE,PRODUCT_WEIGHT,SHIPPING_PRICE){
			var row = {"OP":"product_update","GOOD_ID":GOOD_ID,"IMAGE_ID":IMAGE_ID,"HIDE_MARK":HIDE_MARK,"TITLE":TITLE,"BANNED_COUNTRIES":BANNED_COUNTRIES,"PRODUCT_PRICE":PRODUCT_PRICE,"CURRENCY":CURRENCY,"COMMISSION_MODEL":COMMISSION_MODEL,
						"SALE_COMMISSION":SALE_COMMISSION,"SS_COMMISSION":SS_COMMISSION,"GL_COMMISSION":GL_COMMISSION,"MA_COMMISSION":MA_COMMISSION,"VP_COMMISSION":VP_COMMISSION,"GL_OVERRIDING":GL_OVERRIDING,"MA_OVERRIDING":MA_OVERRIDING,"VP_OVERRIDING":VP_OVERRIDING,"OWNERSHIP_COMMISSION":OWNERSHIP_COMMISSION,"WHOLESALE_MODEL":WHOLESALE_MODEL,"WHOLESALE_PRICE":WHOLESALE_PRICE,"AGENT_PRICE":AGENT_PRICE,"PRODUCT_WEIGHT":PRODUCT_WEIGHT,"SHIPPING_PRICE":SHIPPING_PRICE};
			obj.init_object(row);
			product_list.style.display = "none";
			product_add.style.display = "";
			
			var arr=BANNED_COUNTRIES.split(',');
			$('#done').selectpicker('val', arr);
			
			var o1=document.getElementById("HIDE_MARK");
			var o2=document.getElementById("COMMISSION_MODEL");
			var o3=document.getElementById("WHOLESALE_MODEL");
			if(HIDE_MARK=="1"){
				
				o1.value="1";
				o1.checked=true;
			}else{
				o1.value="0";
				o1.checked=false;
			}
			if(COMMISSION_MODEL=="1"){
				
				o2.value="1";
				o2.checked=true;
				commission_model_section.style.display = "";
			}else{
				o2.value="0";
				o2.checked=false;
				commission_model_section.style.display = "none";
			}
			if(WHOLESALE_MODEL=="1"){
				
				o3.value="1";
				o3.checked=true;
				wholesale_model_section.style.display = "";
			}else{
				o3.value="0";
				o3.checked=false;
				wholesale_model_section.style.display = "none";
			}
			var h_image_path_id="H_IMAGE_ID_"+IMAGE_ID;
			var ohimg=document.getElementById(h_image_path_id);			
			var image_path=ohimg.value;
			file_path=image_path;
			file_input_init(image_path);
			
			var ofhimg=document.getElementById("IMAGE_ID");
			ofhimg.value=IMAGE_ID;
			
			
			
			
		}

		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/product_manage?method=product_list&current_page="+current_page+"&page_size="+page_size;
		}


		function ims_show_warn() {
			this.nextElementSibling.style.display = "";
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
		
		function verify_is_name(){
			if(this.value){
				var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
					if(this.nextElementSibling){
					o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
				}
			}
		}
		function verify_is_changed1()
		{
			var v=this.value;
			var o=document.getElementById("BANNED_COUNTRIES_WARN");
			v=="9999"?o.style.display = "":o.style.display = "none";
		}
		
		function verify_is_changed2()
		{
			var v=this.value;
			var o=document.getElementById("CURRENCY_WARN");
			v=="9999"?o.style.display = "":o.style.display = "none";
		}
		
		function checked_verify0(){
			
			var o = HIDE_MARK.checked;
			o ? HIDE_MARK.value = "1" : HIDE_MARK.value = "0";
		}
		
		function checked_verify1(){
			
			var o = COMMISSION_MODEL.checked;
			o ? COMMISSION_MODEL.value = "1" : COMMISSION_MODEL.value = "0";
			o ? commission_model_section.style.display = "" : commission_model_section.style.display = "none";
		}
		
		function checked_verify2(){
			
			var o = WHOLESALE_MODEL.checked;
			o ? WHOLESALE_MODEL.value = "1" : WHOLESALE_MODEL.value = "0";
			o ? wholesale_model_section.style.display = "" : wholesale_model_section.style.display = "none";
		}
		
		function check_form(){
			if(GOOD_ID.value == "-1"){
				var o=document.getElementById("UPLOAD_IMAGE_WARN");
				o.style.display = "";
				setTimeout(function () { 
					o.style.display = "none";
				}, 3000);
				return false;
			}		
			var s=document.getElementById('done').selectedOptions;
			var val="";
			for(var i=0;i<s.length;i++){	
				if(i!=s.length-1)
					val+=s[i].value+",";
				else
					val+=s[i].value;
			}
			BANNED_COUNTRIES.value=val;
				
			if(CURRENCY.value=="9999"){
				var o=document.getElementById("CURRENCY_WARN");
				o.style.display = "";
				return false;
			}
			
			return true;
		}
		
		function add_product(){
			obj.submit();
		}
		
		function update_product_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			test.innerHTML = tplt(data);
			product_add.style.display = "none";
			product_list.style.display = "";
			
			
		}
		
		Handlebars.registerHelper("help_hide_mark", function (HIDE_MARK) {
			if (HIDE_MARK == 0)
				return "已售卖";
			else if (HIDE_MARK == 1)
				return "未开售";
			else
				return "状态出错！";
		});
		
		function help_image_id_cb(data,msg){
			//if(msg.show_msg){
			//	alert(msg.msg_content);
			//}
			if(data.length>0){
				//just one image
				var root='<%=path%>';
				var image_id=data[0].IMAGE_ID;
				var url=root+data[0].IMAGE_PATH;
				var image='<img src=\"'+url+'\" class=\"img-thumbnail\" alt=\"产品图片\" width=\"100\" height=\"100\">';
				var id="IMAGE_ID_"+image_id;
				var hid="H_IMAGE_ID_"+image_id;
				var oimg=document.getElementById(id);
				var ohidd=document.getElementById(hid);
				oimg.innerHTML=image;
				//$(hide_image).val(url);
				ohidd.value=url;
			}
			
		}
		
		Handlebars.registerHelper("help_image_id", function (IMAGE_ID) {
			
			var params = ["method=product_get_image_url&IMAGE_ID=", IMAGE_ID].join("");
			ims_submit_ajax("<%=path%>/company/product_manage", params, image=help_image_id_cb);
			
		});
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/product_manage?method=product_list&page_size="+page_size;
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows);
		}
	
		
		var arr1 = [["9999","请选择产品禁售国家或地区"],["0","无"],["1","中国"],["2","新加坡"],["3","马来西亚"]];
		var arr2 = [["9999","请选择产品价格单位"],["1","美元"],["2","人民币"],["3","林吉特"]];
	
		var cols = [{
			id : "form",
			submit:{
				integrate:check_form,
				postform:submitWindow,
				url:'<%=path%>/company/product_manage?method=product_update_single',
				refresh:update_product_cb,
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
			id : "IMAGE_ID",
			init : {}
		},{id:"GOOD_ID", 
			init:{
				vdefault : -1,
				value : -1
			}
		},  
		{
			id : "HIDE_MARK",
			init : {
				multiple : false,
				vdefault : 0,
				value : 0
			},
			event : {
				id : "onclick",
				fn : checked_verify0
			}
		},
		{
			id : "TITLE",
			tips : {
				action : ims_show_warn,
				text : "产品名称"
			},
			init : {
				minLength : 1,
				maxLength : 10,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify_is_name
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		},{
			id : "PRODUCT_PRICE",
			tips : {
				action : ims_show_warn,
				text : "产品销售价格"
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
			id : "PRODUCT_WEIGHT",
			tips : {
				action : ims_show_warn,
				text : "产品重量(kg)"
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
		}, {
			id : "BANNED_COUNTRIES",
			init : {}
		}, 
		{
			id : "CURRENCY",
			tips : {
				action : ims_show_warn,
				text : "产品价格单位"
			},
			init : {
				multiple : false,
				vdefault : 0,
				value : 0,
				text : 1,
				ds : arr2
			},
			event : {
				id : "onchange",
				fn : verify_is_changed2
			}
		}, {
			id : "COMMISSION_MODEL",
			init : {
				multiple : false,
				vdefault : 0,
				value : 0
			},
			event : {
				id : "onclick",
				fn : checked_verify1
			}
		},{
			id : "SALE_COMMISSION",
			tips : {
				action : ims_show_warn,
				text : "销售佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, 
		{
			id : "SS_COMMISSION",
			tips : {
				action : ims_show_warn,
				text : "销售佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		},{
			id : "GL_COMMISSION",
			tips : {
				action : ims_show_warn,
				text : "销售佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "MA_COMMISSION",
			tips : {
				action : ims_show_warn,
				text : "销售佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "VP_COMMISSION",
			tips : {
				action : ims_show_warn,
				text : "销售佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "OWNERSHIP_COMMISSION",
			tips : {
				action : ims_show_warn,
				text : "佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : { 
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "GL_OVERRIDING",
			tips : {
				action : ims_show_warn,
				text : "覆盖佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		},{
			id : "MA_OVERRIDING",
			tips : {
				action : ims_show_warn,
				text : "覆盖佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 8,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "VP_OVERRIDING",
			tips : {
				action : ims_show_warn,
				text : "覆盖佣金金额"
			},
			init : {
				minLength : 1,
				maxLength : 3,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_percent
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "WHOLESALE_MODEL",
			init : {
				multiple : false,
				vdefault : 0,
				value : 0
			},
			event : {
				id : "onclick",
				fn : checked_verify2
			}
		},{
			id : "WHOLESALE_PRICE",
			tips : {
				action : ims_show_warn,
				text : "批发价格"
			},
			init : {
				minLength : 1,
				maxLength : 6,
				size : 32,
				allowNull : true
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
			id : "AGENT_PRICE",
			tips : {
				action : ims_show_warn,
				text : "代理价格"
			},
			init : {
				minLength : 1,
				maxLength : 6,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_price
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_price
			}
		},];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
		
		function upload_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			return false;
		}
		
		
		//upload file form
		var form = document.forms.namedItem("fileform");
		form.addEventListener('submit', function(ev) {
		  var ofhimg=document.getElementById("IMAGE_ID");
		  var image_id=ofhimg.value;
		  var oData = new FormData(form);
		  var req = new XMLHttpRequest();
		  var done = false, robj;
		  var submit_url="<%=path%>/upload_manage?method=update_product_image&IMAGE_ID="+image_id;
		  req.open("POST", submit_url, false);
		  req.send(oData);
		  if ((req.readyState == 4) && (req.status == 200)
					&& (req.responseText.length > 0)) {
				robj = eval('(' + req.responseText + ')');
				if (typeof (robj) == "object")
					done = true;
			}

			if (!done){
				alert("网络错误！");
				return false;
			}
				
			if (robj.result) {
				if (robj.msg) {
					upload_cb(robj.data, robj.msg);
				} else {
					alert("网络错误！");
				}
			}
		  ev.preventDefault();
		}, false);

		
			
			$("#input-44").fileinput({
				uploadUrl : "<%=path%>/upload_manage?method=update_product_image",
				maxFilePreviewSize : 10240,
				showUpload : false,
				showCaption : true,
				browseClass : "btn btn-primary btn-lg",
				fileType : "any",
				previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
				overwriteInitial : true,
				initialPreviewAsData : true,
				allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
				initialPreview : [file_path , ],
				initialPreviewConfig : [ {
					caption : "上传图片",
					size : 329892,
					width : "100px",
					url : file_path,
					key : 1
					}]
				});
			
			//刷新图片
			function file_input_init(path){
				var el = document.getElementsByClassName('file-preview-image kv-preview-data');
				
				for(var i=0;i<el.length;i++)
					$(el[i]).attr("src", path);
				
			}
	</script>

	<%@include file="yb_db_foot_include.jsp"%>
	<script src="<%=path%>/js/bootstrap-select.js"></script>
</body>
</html>