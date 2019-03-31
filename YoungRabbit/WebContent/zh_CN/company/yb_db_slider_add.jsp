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
<%@include file="yb_file_input_include.jsp"%>
<title>网站管理</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>网站管理</h2>
		<div id="myTabContent1" class="tab-content">
			<!-- 规则列表 -->
			<div id="emsg_id"></div>
			<div id="rule_list" class="table-responsive">
				<table class="table table-striped" style="border: 1px solid #eee;">
					<thead>
						<tr>
							<th>序号</th>
							<th>标题</th>
							<th>描述</th>
							<th>链接地址</th>
							<th>操作
								<button type="button" class="btn btn-xs btn-primary" onclick="add_slide();">添加图片</button>
							</th>
						</tr>
					</thead>
					<tbody id="test">
						<script id="tpl" type="text/x-handlebars-template">
						{{#each this}}
						<tr>
							<td>{{addOne @index}}</td>
							<td>{{HEAD}}</td>
							<td>{{DESCR}}</td>
							<td style="max-width:600px;word-wrap:break-word;">{{HREF}}</td>
							<td>
							<button type="button" class="btn btn-sm btn-success" onclick="upd_photo('{{SLIDE_ID}}','{{HEAD}}','{{DESCR}}','{{HREF}}','{{URL}}');">修改</button>
							<button type="button" class="btn btn-sm btn-danger" onclick="del_photo('{{SLIDE_ID}}');">删除</button>
							</td>
						</tr>
						{{/each}}
						</script>
					</tbody>
				</table>
			</div>
			<div class="container-fluid" id="change" style="display:none" >
				<div class="row-fluid">														
					<div id="addslide" style="margin-right: 20%; margin-top: 10px; float: left; width: 350px;">	
						<form id="form" method="post">
							<input type="hidden" name="OP" id="OP" /> 
							<input type="hidden" name="method" id="method" value="slide_add" /> 
							<input type="hidden" name="SLIDE_ID" id="SLIDE_ID" />
							<div class="form-horizontal">
								<div class="form-group">
									<div class="col-md-12">
										<label>名称</label>
										<input class="form-control largcontrol" id="HEAD" name="HEAD" type="text"> 
										<span id="head" class="text-danger" style="display: none;">请输入产品名称</span>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-12">
										<label>描述</label>
										<input class="form-control largcontrol" id="DESCR" name="DESCR" type="text"> 
										<span id="descr" class="text-danger" style="display: none;">请输入产品描述</span>
									</div>
								</div>											
								<div class="form-group">
									<div class="col-md-12">
										<label>图片跳转地址</label>
										<input class="form-control largcontrol" id="HREF" name="HREF" type="text"> 
										<span class="text-danger" id="href" style="display: none;">请输入产品链接地址</span>
									</div>
								</div>
								<div class="form-group text-center">
									<div class="form-group text-center">
										<input id="ADD" value="添加" class="btn btn-qubico" type="button" onclick="add_slide_img();">
										<input id="UPDATE" value="修改" class="btn btn-qubico" type="button" onclick="update();">	
										<input value="返回" class="btn btn-qubico" type="button" onclick="back();">
									</div>
								</div>
							</div>
						</form>
					</div>
					<div id="add_img" style="margin-left: 10px; margin-right: 120px; margin-top: 10px; float: right; width: 400px;">
						<form id="fileform" name="fileform"
							enctype="multipart/form-data">
							<!-- image-preview-filename input [CUT FROM HERE]-->
							<label class="control-label">请先上传首页图片</label>
							<span id="UPLOAD_IMAGE_WARN" class="text-danger" style="display: none;">请先上传首页图片。</span> 
							<input id="input-44" name="input44[]" type="file" multiple class="file">
							<div class="form-group text-center">
								<input value="保存图片" class="btn btn-qubico" type="submit">
							</div>
							<div id="errorBlock" class="help-block"></div>
						</form>
					</div>
					<div id="upd_img" style="margin-left: 10px; margin-right: 120px; margin-top: 10px; float: right; width: 400px;">
						<form id="fileform1" name="fileform1" enctype="multipart/form-data">
							<!-- image-preview-filename input [CUT FROM HERE]-->
							<label class="control-label">修改首页图片</label>
							<span id="UPLOAD_IMAGE_WARN_1" class="text-danger" style="display: none;">修改首页图片。</span> 
							<input id="input-44-1" name="input44[]" type="file" multiple class="file">
							<div class="form-group text-center">
								<input value="修改图片" class="btn btn-qubico" type="submit">
							</div>
							<div id="errorBlock" class="help-block"></div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--通用页面结束-->	
	</div>
	<script>
		var obj, rows = [];
	  
	  <%String slide_json = (String) request.getAttribute("slide_list");
		if (slide_json == "") {
			slide_json = "\"no_data\"";
		}%>
		
	   var  rows2 =<%=slide_json%>;
	   var slide_path="<%=path%>/files/upload/product_image/cat1.jpg";
		function back(){
			window.location.reload();
		}
		function cb(arr,msg){	
			if(msg.show_msg){
				
				show_msg_warn("emsg_id",msg.msg_content);
			}
			window.location.reload();
		}
		function add_slide_img(){
			var r = document.getElementById('SLIDE_ID').value;
			if(r==""){
				var msg="请先保存图片！";
				show_msg_warn("emsg_id",msg);
			}				
							
			obj.submit();
			
		}
		function update(){
			//alert("here");
			obj.submit();
			
		}
		function add_slide(){
			if(rows2.length >= 3){
				var msg="首页只能上传三张滚动图片";
				show_msg_warn("emsg_id",msg);
			}

			else{
				var row = {"OP":1};
				obj.init_object(row);
				upd_img.style.display = "none";
				UPDATE.style.display = "none";
				change.style.display = "";
				rule_list.style.display = "none";
			}
		}
		
		function upd_photo(SLIDE_ID,HEAD,DESCR,HREF,URL){
			var rows = {"OP":2, "SLIDE_ID":SLIDE_ID, "HEAD":HEAD, "DESCR":DESCR, "HREF":HREF};
			obj.init_object(rows);
			var slide_path="<%=path%>"+URL;
			file_input_init(slide_path);
			add_img.style.display = "none";
			ADD.style.display = "none";
			rule_list.style.display = "none";
			change.style.display = "";
		}
		
		function del_photo(SLIDE_ID){
			if(confirm("确定要该图片吗？")){
				var params = ["OP=3&SLIDE_ID=", SLIDE_ID].join("");
				ims_submit_ajax("<%=path%>/company/slide_manage", params, cb);		
			}
		}
		
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		test.innerHTML = tplt(rows2);
		
	
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify_is_name(){
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		
		
		
		function add_slide_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			if(data.success){
				return true;
			}else{
				return false;
			}	
		}

		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		
		var cols = [
			{id : "form",
				submit:{
					postform:submitWindow,
					url:'<%=path%>/company/slide_manage',
					refresh:cb,
				}},
			{id:"OP", 	init:{}},		//OP是标志位 1:添加  2:修改	
			{id:"method", init:{}},  
			{id:"SLIDE_ID", init:{}},  
			
			{id:"HEAD", tips:{action:ims_show_warn, text:"名称"},
						init : {minLength : 1,maxLength : 20,size : 32,	allowNull : false},
						event : {id : "onkeyup",fn : verify_is_name},
						submit : {formatter : validator.trim,check : validator.is_name}},  
			{id:"DESCR",tips:{action:ims_show_warn, text:"描述"},
						init : {minLength : 0,maxLength : 50,size : 32,	allowNull : false},
						event : {id : "onkeyup",fn : verify_is_name},
						submit : {formatter : validator.trim,check : validator.is_name}},			
			{id:"HREF",	tips:{action:ims_show_warn, text:"链接地址"},
						init : {minLength : 1,maxLength : 200,size : 32,allowNull : false},
						event : {id : "onkeyup",fn : verify_is_name},
						submit : {formatter : validator.trim,check : validator.is_site}},  
		];
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
    </script>
	<!-- 输入有效性检验用 -->
	<script>					
		function upload_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			var slide_id=data.SLIDE_ID;
			if(slide_id>0){
				$('#SLIDE_ID').val(slide_id);
				//var o=document.getElementById("GOOD_ID");
				//o.value = good_id;	
			}
			return false;
		}
		
		
		//upload slide image
		var form = document.forms.namedItem("fileform");
		form.addEventListener('submit', function(ev) {
		  var oData = new FormData(form);
		  var req = new XMLHttpRequest();
		  var done = false, robj;
		  req.open("POST", "<%=path%>/upload_manage?method=upload_slide_image", false);
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
			uploadUrl : "<%=path%>/upload_manage?method=upload_slide_image",
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
			}]
		});
					
		
		
		
		function upload_cb1(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			var slide_id=data.SLIDE_ID;
			if(slide_id>0){
				$('#SLIDE_ID').val(slide_id);
				//var o=document.getElementById("GOOD_ID");
				//o.value = good_id;	
			}
			return false;
		}
		
		//update slide image
		var form1 = document.forms.namedItem("fileform1");
		form1.addEventListener('submit', function(ev) {
		  var ofhimg=document.getElementById("SLIDE_ID");
		  var slide_id=ofhimg.value;
		  var oData1 = new FormData(form1);
		  var req1 = new XMLHttpRequest();
		  var done1 = false, robj1;
		  req1.open("POST", "<%=path%>/upload_manage?method=update_slide_image&SLIDE_ID="+slide_id, false);
		  req1.send(oData1);
		  if ((req1.readyState == 4) && (req1.status == 200)
					&& (req1.responseText.length > 0)) {
				robj1 = eval('(' + req1.responseText + ')');
				if (typeof (robj1) == "object")
					done1 = true;
			}

			if (!done1){
				alert("网络错误！");
				return false;
			}
				
			if (robj1.result) {
				if (robj1.msg) {
					upload_cb1(robj1.data, robj1.msg);
				} else {
					alert("网络错误！");
				}
			}
		  ev.preventDefault();
		}, false);
		
		$("#input-44-1").fileinput({
			uploadUrl : "<%=path%>/upload_manage?method=update_slide_image",
			maxFilePreviewSize : 10240,
			showUpload : false,
			showCaption : true,
			browseClass : "btn btn-primary btn-lg",
			fileType : "any",
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			overwriteInitial : true,
			initialPreviewAsData : true,
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
			initialPreview : [ slide_path, ],
				initialPreviewConfig : [ {
				caption : "上传图片",
				size : 329892,
				width : "100px",
				url : slide_path,
				key : 1
			} ]
		});

		//刷新图片
			function file_input_init(path){
				var el = document.getElementsByClassName('file-preview-image kv-preview-data');
				
				for(var i=0;i<el.length;i++)
					$(el[i]).attr("src", path);
				
			}
		
	</script>
	<%@include file="yb_db_foot_include.jsp"%>

</body>
</html>