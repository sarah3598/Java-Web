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

</head>

<body>
	<form method="post" action="servlet/addProduct">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<ul class="breadcrumb">
						<li><a
							href="<%=basePath%>products/inventory?method=listInventory">Inventory</a>
							<span class="divider">/Inventory Detail</span></li>
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
							<td><input name="inputIncomingStock" type="text"
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
					</table>
					<br />
					<!-- <div>New Orders</div>
					<table class="table">
						<thead>
							<tr>
								<th>编号</th>
								<th>产品</th>
								<th>交付时间</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${invOrdlist.list}" var="inv" varStatus="status">
							<tr class="success">
								<td>
									${inv.ORDER_ID}
								</td>
								<td>${inv.GOOD_ID}
								</td>
								<td>${inv.CUSTOMER_ID}</td>
								<td>${inv.SALES_QTY_NUM}</td>
								<td>${inv.HAPPEN_DATE}</td>
							</tr>
						</c:forEach>
							
						</tbody>
					</table>
				</div>
			 -->
			</div>
		</div>

	</form>
</body>
</html>
