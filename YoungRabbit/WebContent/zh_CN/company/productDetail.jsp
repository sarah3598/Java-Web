<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
<title>product details</title>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/layoutit.css">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/bootstrap-combined.css">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/editor_gecko.css">
</head>

<body>
	<div class="wapper cbody">
		<div class="Ccontent left margin-top">

			<div class="margin-top STYLE1" id="list2">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<ul class="breadcrumb">
								<li><a
									href="<%=basePath%>products/list?method=list">Products</a>
									<span class="divider">/Product Detail</span></li>
							</ul>
						</div>
					</div>
				</div>

				<div class="container-fluid" style="margin-top: 30px">
					<div>
						<div style="float: left; height: 300px;">
							<img src="<%=basePath%>${product.IMAGE_PATH}" class="img-rounded"
								width="300px" height="300" />
						</div>

						<div style="margin-left: 50px; float: left; height: 300px;">
							<table class="table">
								<tr>
									<td>Price:</td>
									<td>${product.PRODUCT_PRICE }</td>
								</tr>
								<tr>
									<td>Points:</td>
									<td>${product.POINTS}</td>
								</tr>
								<tr>
									<td>Sale commission:</td>
									<td>${product.SALE_COMMISSION}</td>
								</tr>
								<tr>
									<td>Manager Overriding:</td>
									<td>${product.MANAGER_OVERRIDING}</td>
								</tr>
								<tr>
									<td>VP Overriding:</td>
									<td>${product.VP_OVERRIDING}</td>
								</tr>
							</table>

						</div>
					</div>
				</div>



			</div>
		</div>

	</div>
</body>
</html>
