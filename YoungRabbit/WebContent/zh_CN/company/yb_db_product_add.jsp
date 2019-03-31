<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="yb_db_head_include.jsp"%>
<%@include file="yb_file_input_include.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/bootstrap-select.css">
<title>product</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
	<div class="container">
		<h2>产品管理</h2>
		<ul class="nav nav-tabs">
			<li><a href="<%=path%>/company/product_manage?method=product_list"> 产品列表 </a></li>
			<li  class="active"><a href="<%=path%>/zh_CN/company/yb_db_product_add.jsp"> 添加产品 </a></li>
			<!-- <li><a href="#"> 产品捆绑 </a></li> -->
		</ul>
		<div id="emsg_id"></div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div style="margin-right: 50px; margin-top: 10px; float: right; width: 350px;">
					<form id="fileform" name="fileform" enctype="multipart/form-data">
						<!-- image-preview-filename input [CUT FROM HERE]-->
						<label class="control-label">请先上传产品图片</label>
						<span id="UPLOAD_IMAGE_WARN" class="text-danger" style="display: none;">请先上传产品图片。</span> 
						<input id="input-44" name="input44[]" type="file" multiple class="file">
						<div class="form-group text-center">
							<input value="保存图片" class="btn btn-qubico" type="submit">
						</div>
						
						<div id="errorBlock" class="help-block"></div>
					</form>
				</div>
				<div id="addproducts" style="margin-left: 10px; margin-right: 120px; margin-top: 10px; float: right; width: 400px;">
					<form id="form" method="post">
						<input type="hidden" name="GOOD_ID" id="GOOD_ID" value="-1" />
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
									</select><span id="BANNED_COUNTRIES_WARN" class="text-danger" style="display: none;">请选择该产品禁售的国家或地区。</span>
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
								<div class="form-group text-center">
								<input value="保存" class="btn btn-qubico" type="button" onclick="add_product();">
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		var obj, rows = [];
		function ims_show_warn() {
			this.nextElementSibling.style.display = "";
		}
		
		function verify_is_percent() {
			var o = validator.is_integer(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify_is_price() {
			var o = validator.is_price(validator.trim(this.value),
					this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none"
					: this.nextElementSibling.style.display = "";
		}
		
		function verify_is_name(){
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		
		function verify_is_changed1()
		{
			var v=this.value;
			var o=document.getElementById("BANNED_COUNTRIES_WARN");
			v=="99999"?o.style.display = "":o.style.display = "none";
		}
		
		function verify_is_changed2()
		{
			var v=this.value;
			var o=document.getElementById("CURRENCY_WARN");
			v=="0"?o.style.display = "":o.style.display = "none";
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
				alert("请上传产品图片！");
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
			if(CURRENCY.value=="0"){
				var o=document.getElementById("CURRENCY_WARN");
				o.style.display = "";
				return false;
			}
			return true;
		}
		
		function add_product(){
			obj.submit();
		}
		
		function add_product_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
		}
		
		var arr1 = [["99999","请选择产品禁售国家或地区"],["0","无"],["1","中国"],["2","新加坡"],["3","马来西亚"]];
		var arr2 = [["0","请选择产品价格单位"],["1","美元"],["2","人民币"],["3","林吉特"]];
	
		var cols = [{
			id : "form",
			submit:{
				integrate:check_form,
				postform:submitWindow,
				url:'<%=path%>/company/product_manage?method=product_add_single',
				refresh:add_product_cb,
			}
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
			tips : {
				action : ims_show_warn,
				text : "产品禁售国家或地区"
			},
			init : {
				multiple : false,
				vdefault : 99999,
				value : 0,
				text : 1,
				ds : arr1
			},
			event : {
				id : "onchange",
				fn : verify_is_changed1
			}
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
		}, ];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
		
		function upload_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			var good_id=data.GOOD_ID;
			if(good_id>0){
				$('#GOOD_ID').val(good_id);
				//var o=document.getElementById("GOOD_ID");
				//o.value = good_id;
				
			}
			return false;
		}
		
		
		//upload file form
		var form = document.forms.namedItem("fileform");
		form.addEventListener('submit', function(ev) {
		  var oData = new FormData(form);
		  var req = new XMLHttpRequest();
		  var done = false, robj;
		  req.open("POST", "<%=path%>/upload_manage?method=upload_product_image", false);
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
			else{
				ims_show_warn(emsg,"");
			}
		  ev.preventDefault();
		}, false);

		$("#input-44").fileinput({
			uploadUrl : "<%=path%>/upload_manage?method=upload_product_image",
			maxFilePreviewSize : 10240,
			showUpload : false,
			showCaption : true,
			browseClass : "btn btn-primary btn-lg",
			fileType : "any",
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			overwriteInitial : true,
			initialPreviewAsData : true,
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
			initialPreview : [ "<%=path%>/files/upload/product_image/cat1.jpg", ],
			initialPreviewConfig : [ {
				caption : "上传图片",
				size : 329892,
				width : "100px",
				url : "{$url}",
				key : 1
			} ]
		});
		
	</script>
	<script src="<%=header_path%>/js/jquery.min.js"></script>
	<%@include file="yb_db_foot_include.jsp"%>
	<script src="<%=path%>/js/bootstrap-select.js"></script>
</body>
</html>