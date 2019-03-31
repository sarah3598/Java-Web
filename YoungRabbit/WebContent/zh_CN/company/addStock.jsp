<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#addSubmit').click(function() {
			$('form').submit();
		});
	});
</script>
</head>

<body>
	<form method="post" action="inventory?method=addStock">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<ul class="breadcrumb">
						<li><a
							href="<%=basePath%>products/inventory?method=listInventory">Inventory</a>
							<span class="divider">/AddStock</span></li>
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
							<td>Add Incoming</td>
							<td><input name="inputIncomingStock" type="text"
								value="" /></td>
						</tr>
						<tr>
							<td>Supplier</td>
							<td><input name="inputSupplier" type="text"
								value="" /></td>
						</tr>
						<tr>
							<td>Estimated date of arrival</td>
							<td><input name="inputEstimatedDateOfArrival" type="text"
								value="" /></td>
						</tr>
					</table>
					<div>
						<input id="addSubmit" type="submit" class="btn" value="Add" />
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
