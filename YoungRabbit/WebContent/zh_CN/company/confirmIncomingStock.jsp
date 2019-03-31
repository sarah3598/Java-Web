<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<META content="text/html; charset=utf-8" http-equiv="Content-Type">
<title>Products</title>
<link type="text/css" rel="stylesheet" href="../css/layoutit.css">
<link type="text/css" rel="stylesheet"
	href="../css/bootstrap-combined.css">
<link type="text/css" rel="stylesheet" href="../css/editor_gecko.css">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
		<script src="js/html5shiv.js"></script>
	<![endif]-->
<script type="text/javascript" src="../js/jquery-2.js"></script>
<!--[if lt IE 9]>
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
	<![endif]-->
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/jquery-ui.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery_002.js"></script>
<script type="text/javascript" src="../js/ckeditor.js"></script>
<style>
.cke {
	visibility: hidden;
}
</style>
<script type="text/javascript" src="../js/config_002.js"></script>
<script type="text/javascript" src="../js/scripts.js"></script>
<script type="text/javascript" src="../js/config.js"></script>
<script type="text/javascript" src="../js/zh-cn.js"></script>
<script type="text/javascript" src="../js/styles.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#confirmSubmit').click(function() {
			$('form').submit();
		});
	});
</script>
</head>

<body>
	<form method="post" action="inventory?method=updateIncomingStock">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<ul class="breadcrumb">
						<li><a
							href="<%=basePath%>products/inventory?method=listIncoming">Incoming
								Stock</a> <span class="divider">/Confirm Add Incoming</span></li>
					</ul>
					<table class="table" style="width: 500px; height: auto;">
						<tr>
							<td>Inventory ID</td>
							<td><input name="invtoryID" type="text"
								value="${product.INVENTORY_ID}" /></td>
						</tr>
						<tr>
							<td>Product Name</td>
							<td><input name="inputProduct" type="text"
								value="${product.TITLE}" /></td>
						</tr>
						<tr>
							<td>Product ID</td>
							<td><input name="inputID" type="text"
								value="${product.GOOD_ID}" /></td>
						</tr>
						<tr>
							<td>Stock in warehouse</td>
							<td><input name="inputWarehouseStock" type="text"
								value="${product.QUANTITY}" /></td>
						</tr>
						<tr>
							<td>Incoming</td>
							<td><input name="inputIncomingStock" type="text"
								value="${product.INCOMING}" /></td>
						</tr>
						<tr>
							<td>Supplier</td>
							<td><input name="inputSupplier" type="text"
								value="${product.SUPPLIER}" /></td>
						</tr>
						<tr>
							<td>Estimated date of arrival</td>
							<td><input name="inputEstimatedDateOfArrival" type="text"
								value="${product.INCOMING_ESTIM_ARRI_DATE}" /></td>
						</tr>
						<tr>
							<td>Upload receipts</td>
							<td><input name="inputEstimatedDateOfArrival" type="text"
								value="${product.INCOMING_ESTIM_ARRI_DATE}" /></td>
						</tr>

					</table>
					<div class="progress progress-striped">
						<div class="bar"></div>
					</div>
					<div>
						<div>
						<input id="confirmSubmit" type="submit" class="btn" value="Confirm Incoming Stock" />
					</div>
					</div>


				</div>
			</div>
		</div>

	</form>
</body>
</html>
