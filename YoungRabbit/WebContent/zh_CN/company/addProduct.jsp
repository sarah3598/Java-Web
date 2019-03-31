<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>add product</title>
<link href="../../js/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<link href="../../js/bootstrap_fileinput/css/fileinput.css" media="all"
	rel="stylesheet" type="text/css" />
<link href="../../js/bootstrap_fileinput/themes/explorer/theme.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="../../js/ajax/jquery.min.js"></script>
<script src="../../js/ajax/jquery.form.js"></script>
<script src="../../js/bootstrap_fileinput/js/plugins/sortable.js"
	type="text/javascript"></script>
<script src="../../js/bootstrap_fileinput/js/fileinput.js"
	type="text/javascript"></script>
<script src="../../js/bootstrap_fileinput/js/locales/fr.js"
	type="text/javascript"></script>
<script src="../../js/bootstrap_fileinput/js/locales/es.js"
	type="text/javascript"></script>
<script src="../../js/bootstrap_fileinput/themes/explorer/theme.js"
	type="text/javascript"></script>
<script src="../../js/bootstrap3/js/bootstrap.min.js"
	type="text/javascript"></script>

<script type="text/javascript">
	$(function() {
		$('#imageform').ajaxForm({
			success : function(data) {
				//convert josn to object
				alert("Upload product image success!");
				var dataObj = eval("(" + data + ")");
				$.each(dataObj.imageid,function(idx, item) {
						$('#imageid').val(item.imageid_1);
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
						$('#product_id').val(item.product_id);
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
								<li><a href="<%=basePath%>products/list?method=list">Products</a>
									<span class="divider">/Add Product</span></li>
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
										action="<%=basePath%>products/upload?method=addProductImage"
										enctype="multipart/form-data">
										<input id="file-3" type="file" name="file" /> <input
											id="upload_image" type="submit" value="Upload Picture"
											class="btn btn-primary" />
									</form>
								</div>
							</div>
							<div style="margin-left: 30px">
								<form id="prodinfoform" method="post"
									action="<%=basePath%>products?method=addProductInfo">
									<p>
										<input id="hide_mark" name="hide_mark" type="checkbox" value='0'> <label>hide</label>
									</p>
									<p >
										<input style="width: 200px;" type="text" name="product_title"
											value="product ID" class="btn btn-default">
									</p>
									<p>
										<select style="width: 200px;" id="bannedCountries"
											name="banned_countries" class="btn btn-default">
											<option value="China">China</option>
											<option value="Singapore" selected="selected">Singapore</option>
											<option value="Malaysia">Malaysia</option>
										</select>
									</p>
									<p>
										<input style="width: 200px;" type="submit"
											value="Save Product" class="btn btn-primary" />
									</p>
									<p id="hidden_imageid">
									<input id="imageid" name="imageid"  type="text" value="" style="display: block;"/>
									
									</p>
								</form>
							</div>
						</div>
						<div
							style="margin-top: 50px; margin-left: 10px; float: left; width: 400px; height: auto;">
							<form id="saleinfoform" action="<%=basePath%>products?method=addSaleInfo"
								method="post">

								<div id="addproducts_right" class="addproducts_right"
									style="float: left">
									<p style="width: 100px;">
										<select id="currency" name="currency" class="btn btn-default">
											<option value="Pound">Pound</option>
											<option value="RMB" selected="selected">RMB</option>
											<option value="Ringgit">Ringgit</option>
										</select>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 300px;">Price:<input type="text" name="product_price"
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
											type="checkbox" value='0'
											class="btn btn-default"> <label>Commission
											model</label>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Sale commission:</td>
											<td><input type="text" name="sale_commission"
												id="sale_commission" class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Manager overriding:</td>
											<td><input type="text" name="manager_overriding"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">VP overriding:</td>
											<td><input type="text" name="vp_overriding"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Ownership commission:</td>
											<td><input type="text" name="ownership_commission"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>
									<br>
									<p>
										<input name="wholesale_model" id="wholesale_model"
											type="checkbox" value='0'
											class="btn btn-default"> <label>Wholesale
											model</label> <br>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Whole price:</td>
											<td><input type="text" name="whole_price"
												id='sale_commission' class="btn btn-default"></td>
										</tr>
									</table>
									</p>

									<p>
									<table>
										<tr>
											<td style="width: 180px;">Agent price:</td>
											<td><input type="text" name="agent_price"
												class="btn btn-default"></td>
										</tr>
									</table>
									</p>
									<br>
									<p id="hidden_product_id">
									<input id="product_id" name="product_id"  type="text" value="" style="display: block;"/>
									</p>
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
		initialPreview : [ "../../upload/products_images/cat1.jpg", ],
		initialPreviewConfig : [ {
			caption : "cat1.jpg",
			size : 329892,
			width : "100px",
			url : "{$url}",
			key : 1
		} ]
	});
</script>
</html>
