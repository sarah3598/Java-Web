<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";	
%>
<html>
<head>
<title>edit product</title>
<link href="<%=basePath%>js/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>js/bootstrap_fileinput/css/fileinput.css" media="all"
	rel="stylesheet" type="text/css" />
<link href="<%=basePath%>js/bootstrap_fileinput/themes/explorer/theme.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="<%=basePath%>js/ajax/jquery.min.js"></script>
<script src="<%=basePath%>js/ajax/jquery.form.js"></script>
<script src="<%=basePath%>js/bootstrap_fileinput/js/plugins/sortable.js"
	type="text/javascript"></script>
<script src="<%=basePath%>js/bootstrap_fileinput/js/fileinput.js"
	type="text/javascript"></script>
<script src="<%=basePath%>js/bootstrap_fileinput/js/locales/fr.js"
	type="text/javascript"></script>
<script src="<%=basePath%>js/bootstrap_fileinput/js/locales/es.js"
	type="text/javascript"></script>
<script src="<%=basePath%>js/bootstrap_fileinput/themes/explorer/theme.js"
	type="text/javascript"></script>
<script src="<%=basePath%>js/bootstrap3/js/bootstrap.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$('#imageform').ajaxForm({
			success : function(data) {
				//convert josn to object
				alert("Upload product image success!");
				var dataObj = eval("(" + data + ")");
				$.each(dataObj.imageid,function(idx, item) {
						$('#imageid').val(item.image_id);
				});
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				alert("Upload product image failed!");
				alert(errorThrown);
			}
		});
	});
	$(function() {
		$('#prodinfoform').ajaxForm({
			success : function(data) {
				//convert josn to object
				alert("add product success!");
				alert(data);
				var dataObj = eval("(" + data + ")");
				$.each(dataObj.product,function(idx, item) {
						$('#product_title').val(item.product_title);
				});
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				alert("add product failed!");
				alert(errorThrown);
			}
		});
	});
	$(function() {
		$('#saleinfoform').ajaxForm({
			success : function(data) {
				var dataObj = eval("(" + data + ")");
				$.each(dataObj.msg,function(idx, item) {
					alert(item.success);
				});
			},
			error : function(XMLHttpRequest, textStatus,errorThrown) {
				alert("add product failed!");
				alert(errorThrown);
			}
		});
	});
	//change hide mark value
	$(function() {
		$('#hide_mark').click(function(){
			//alert("checkbox click");
			if ($('#hide_mark').is(':checked')) {
				//alert("hide checked!");
				$('#hide_mark').val('1');
				//$('#hide_mark').attr('value',1);			
			}
			else{
				$('#hide_mark').val('0');
			}
		})
	});
	//change hide mark value
	$(function() {
		$('#commission_model').click(function(){
			//alert("checkbox click");
			if ($('#commission_model').is(':checked')) {
				//alert("hide checked!");
				$('#commission_model').val('1');
				//$('#hide_mark').attr('value',1);			
			}else{
				$('#commission_model').val('0');
			}
		})
	}); 
	//change hide mark value
 	$(function() {
		$('#wholesale_model').click(function(){
			//alert("checkbox click");
			if ($('#wholesale_model').is(':checked')) {
				//alert("hide checked!");
				$('#wholesale_model').val('1');
				//$('#hide_mark').attr('value',1);			
			}else{
				$('#wholesale_model').val('0');
			}
		})
	});	
</script>
</head>
<body>
	<div class="wapper cbody" style="margin-left: 100px">
		<div class="Ccontent left margin-top">

			<div class="margin-top STYLE1" id="list2">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<ul class="breadcrumb">
								<li><a href="<%=basePath%>products/list?method=listedit">Products Edit List</a>
									<span class="divider">/Edit Product</span></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="container-fluid" style="margin-top: 30px">
					<div>
						<div style="float: left; width: 420px; height: auto">
							<div>
								<div class="container kv-main"
									style="width: 400px; height: auto">
									<form id="imageform" method="post"
										action="<%=basePath%>products/upload?method=updateProductImage"
										enctype="multipart/form-data">
										<input id="file-3" type="file" name="file" /> <input
											id="upload_image" type="submit" value="Update Picture"
											class="btn btn-primary" />
									</form>
								</div>
							</div>
							<div style="margin-left: 30px">
								<form id="prodinfoform" method="post"
									action="<%=basePath%>products?method=updateProductInfo">
									
									
									<p>
										<input id="hide_mark" name="hide_mark"  value="${product.HIDE_MARK}" type="checkbox"> <label>hide</label>
									</p>
									
									
									
									
									
									<p >
										<input style="width: 200px;" type="text" name="product_title" value="${product.TITLE}"
											value="product ID" class="btn btn-default">
									</p>
									<p>
										<select style="width: 200px;" id="bannedCountries"
											name="banned_countries" class="btn btn-default">
											<option value="Singapore">Singapore</option>
											<option value="Malaysia">Malaysia</option>
											<option value="China">China</option>
										</select>
									</p>
									<p>
										<input style="width: 200px;" type="submit"
											value="Update Product" class="btn btn-primary" />
									</p>
									
									<p id="hidden_imageid">
									<input id="imageid" name="imageid"  type="text" value="${product.IMAGES_ID}" style="display: block;"/>
									<input id="new_imageid" name="new_imageid"  type="text" value="" style="display: block;"/>
									</p>
								</form>
								<p>
										<input style="width: 200px;" type="submit"
											value="Delete Product" class="btn btn-primary" />
									</p>
							</div>
						</div>
						<div
							style="margin-top: 50px; margin-left: 10px; float: left; width: 400px; height: auto;">
							<form id="saleinfoform" action="<%=basePath%>products?method=updateSaleInfo"
								method="post">

								<div id="addproducts_right" class="addproducts_right"
									style="float: left">
									<p style="width: 100px;">
										<select id="currency" name="currency" class="btn btn-default">
											<option value="Pound">Pound</option>
											<option value="RMB">RMB</option>
											<option value="Ringgit">Ringgit</option>
										</select>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 300px;">Price:<input type="text" name="product_price" value="${product.PRODUCT_PRICE}"
											class="btn btn-default"> </td>
											<td><input type="submit"
											name="button" id="button_sale" value="Sale" 
											class="btn btn-primary"></td>
										</tr>
									</table>
										
									</p>
									<br>
									
									<p>
										<input name="commission_model" id="commission_model"
											type="checkbox" value="0" 
											class="btn btn-default"> <label>Commission
											model</label>
									</p>
									

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Sale commission:</td>
											<td><input type="text" name="sale_commission" value="${product.SALE_COMMISSION}"
												id="sale_commission" class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Manager overriding:</td>
											<td><input type="text" name="manager_overriding" value="${product.MANAGER_OVERRIDING}"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">VP overriding:</td>
											<td><input type="text" name="vp_overriding" value="${product.VP_OVERRIDING}"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Ownership commission:</td>
											<td><input type="text" name="ownership_commission" value="${product.OWNERSHIP_COMMISSION}"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>
									<br>
									
									<p>
									<input name="wholesale_model" id="wholesale_model"
											type="checkbox" value="0" 
											class="btn btn-default"> <label>Wholesale
											model</label> <br>
									</p>
									
									
									<p>
									<table>
										<tr>
											<td style="width: 180px;">Whole price:</td>
											<td><input type="text" name="whole_price"
												id="whole_price" value="${product.WHOLESALE_PRICE}" class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Agent price:</td>
											<td><input type="text" name="agent_price"
												class="btn btn-default" value="${product.AGENT_PRICE}"></td>
										</tr>
									</table>
									</p>
									<br>
									<p id="hidden_product_id">
									<input id="product_id" name="product_id"  type="text" value="${product.GOOD_ID}" style="display: block;"/></p>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$("#file-3").fileinput({
		showUpload : false,
		showCaption : true,
		browseClass : "btn btn-primary btn-lg",
		fileType : "any",
		previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
		overwriteInitial : true,
		initialPreviewAsData : true,
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
		maxFileSize : 4000,
		initialPreview : [ "<%=basePath%>${product.IMAGE_PATH}", ],
		initialPreviewConfig : [ {
			caption : "cat1.jpg",
			size : 329892,
			width : "100px",
			url : "{$url}",
			key : 1
		} ]
	});
	function _Select(o,v){
		 for(var i=0; i<o.options.length; i++){
		 if(o.options[i].value == v){
		  	o.options[i].selected=true;
		  	break;
		 }}
	}
	$(document).ready(function(){
		//init select
		var bannedCountries = '${product.BANNED_COUNTRIES}'; 
		var currency = '${product.CURRENCY}'; 
		
	    var bannedCountriesObj = document.getElementById("bannedCountries");  
	    var currencyObj = document.getElementById("currency");
	    _Select(bannedCountriesObj,bannedCountries);
	    _Select(currencyObj,currency);
	    
	    //init checkbox
	    var hide_mark = '${product.HIDE_MARK}';
		var commission_model ='${product.COMMISSION_MODEL}';
		var wholesale_model = '${product.WHOLESALE_MODEL}';
		
		var hide_mark_obj = document.getElementById("hide_mark");
		var commission_model_obj =document.getElementById("commission_model");
		var wholesale_model_obj = document.getElementById("wholesale_model");
		
		if(hide_mark=='1'){
			hide_mark_obj.value="1";
			hide_mark_obj.checked=true;
		}
		if(commission_model=='1'){
			commission_model_obj.value="1";
			commission_model_obj.checked=true;
		}
		if(wholesale_model=='1'){
			wholesale_model_obj.value="1";
			wholesale_model_obj.checked=true;
		}
	   
	});	
</script>
</html>
