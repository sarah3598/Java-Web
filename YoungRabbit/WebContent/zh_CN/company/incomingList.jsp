<%@ page language="java" import="java.util.*,com.csh.util.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	PageModel pageModel = (PageModel) request.getAttribute("inStock");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery.js"></script>
<style>
input.a {
	border: 1px solid #fff;
	background: #fff;
}

input.b {
	border: 1px solid #369;
	background: #fff;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
    $('#test').find('input[type=checkbox]').bind('click', function(){
        $('#test').find('input[type=checkbox]').not(this).attr("checked", false);
    });
});

$(document).ready(function(){
 	$('#addstock').click(function(){
 		//alert('addstock');
    	$("#test tr input[type=checkbox]").each(function(){
    		//var chk = $(this).find("[checked]");
    		if(this.checked){
    		 	window.location.href="<%=basePath%>products/inventory?method=showStock&INVID="+$(this).val();
    			alert($(this).val());
    		}
		});     
	});
});	
	
  function confirmAddStock(val){
     window.location.href="<%=basePath%>products/inventory?method=confirmIncomingStock&GOOD_ID="+val;
  } 
</script>
<link type="text/css" rel="stylesheet" href="../css/layoutit.css">
<link type="text/css" rel="stylesheet"
	href="../css/bootstrap-combined.css">
<link type="text/css" rel="stylesheet" href="../css/editor_gecko.css">
<html>
<body>
	<div class="wapper cbody">
		<div class="Ccontent left margin-top">
			<div class="margin-top STYLE1" id="list2">
				<div id="position">
					<p>
						<a href="#">Product</a>&gt; <a href="#">Inventory</a>&gt;
					</p>
				</div>
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<ul class="nav nav-tabs">
								<li><a
									href="<%=basePath%>products/inventory?method=listInventory">Available
										Stock</a></li>
								<li class="active"><a
									href="<%=basePath%>products/inventory?method=listIncoming">Incoming
										Stock</a></li>
							</ul>

						</div>
					</div>
				</div>
				<table class="table">
					<thead>
						<tr>

							<th>Product</th>
							<th>ProductID</th>
							<th>Quantity</th>
							<th>Status</th>
							<th>Confirm</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${inStock.list}" var="in" varStatus="status">
							<tr>
								<td><a style="cursor: pointer"
									onclick="confirmAddStock(${in.GOOD_ID})">${in.TITLE}</a></td>
								<td><a style="cursor: pointer"
									onclick="confirmAddStock(${in.GOOD_ID})">${in.GOOD_ID}</a></td>
								<td>${in.INCOMING}</td>
								<td>Wait coming</td>
								<td><input id="confirmAddStock" type="button" class="btn"
									value="Confirm"
									onclick="confirmAddStock(${in.GOOD_ID});return false;"></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				当前：第<%=pageModel.getCurrentPage()%>页|共<%=pageModel.getTotalPage()%>页
				<a style="cursor: pointer" onclick="ff(1);return false;">首页</a> <a
					style="cursor: pointer"
					onclick="ff(<%=pageModel.getUpPage()%>);return false;">上一页</a> <a
					style="cursor: pointer"
					onclick="ff(<%=pageModel.getDownPage()%>);return false;">下一页</a> <a
					style="cursor: pointer"
					onclick="ff(<%=pageModel.getTotalPage()%>);return false;">尾页</a>
			</div>

		</div>
	</div>
</body>
</html>
