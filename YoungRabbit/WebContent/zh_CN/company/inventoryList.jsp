<%@ page language="java" import="java.util.*,com.csh.util.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	PageModel pageModel = (PageModel) request.getAttribute("avaiStock");
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
 		var checkflag=0;
    	$("#test tr input[type=checkbox]").each(function(){
    		//var chk = $(this).find("[checked]");    		
    		if(this.checked){
    			//checkflag=1;
    		 	window.location.href="<%=basePath%>products/inventory?method=showStock&GOOD_ID="+$(this).val();
    			//alert($(this).val());
    		}    		    				
		});
		if(checkflag==0){
			alert('Select a product first!');     
		}
		
	});
});	


function addStock(val){
     window.location.href="<%=basePath%>products/inventory?method=showStock&GOOD_ID="+val;
  }
  
  function ff(val){
     window.location.href="<%=basePath%>products/inventory?method=listInventory&currentPage="+val;
  }
  
  function changePwd(){
     window.location.href="<%=basePath%>servlet/account?method=setNewPassword";
  }
  
  function PayPal(){
     window.location.href="<%=basePath%>servlet/account?method=setNewPassword";
  }
  
   function showInventoryDetail(val){
    window.location.href="<%=basePath%>products/inventory?method=showInventoryDetail&GOOD_ID="+val;
  }
  
  
  
  
  function updateInventory(val){
  var text = document.getElementById("text"+val);
  var quantity=text.value;
  //var quantity=document.
    window.location.href="<%=basePath%>products/inventory?method=updateInventory&INVENTORY_ID="+val+"&QUANTITY="+quantity;
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
			<div id="position">
				<p>
					<a href="#">Product</a>&gt; <a href="#">Inventory</a>&gt;
				</p>
			</div>
			<br /> <br />
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<ul class="nav nav-tabs">
							<li class="active"><a
								href="<%=basePath%>products/inventory?method=listInventory">Available
									Stock</a></li>
							<li><a
								href="<%=basePath%>products/inventory?method=listIncoming">Incoming
									Stock</a></li>
						</ul>

					</div>
				</div>
			</div>
			<div style="text-align: right; padding-right: 15px;">
				<input id="changepwd" type="button" class="btn" value="Test PayPal"
					onclick="PayPal();return false;"><input id="changepwd"
					type="button" class="btn" value="Test Change Pwd"
					onclick="changePwd();return false;">
			</div>
			<div class="margin-top STYLE1" id="list2">
				<div>Available Stock</div>

				<table id="test" class="table">
					<thead>
						<tr>
							<th>ID</th>
							<th>Product</th>
							<th>ProductID</th>
							<th>Quantity</th>
							<th>AddStock</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${avaiStock.list}" var="avai" varStatus="status">
							<tr>
								<td>${avai.INVENTORY_ID}</td>
								<td><a style="cursor: pointer"
									onclick="showInventoryDetail(${avai.GOOD_ID});return false;">${avai.TITLE}</a>
								</td>
								<td><a style="cursor: pointer"
									onclick="showInventoryDetail(${avai.GOOD_ID});return false;">${avai.GOOD_ID}</a>
								</td>
								<td>${avai.QUANTITY}</td>
								<td><input id="addstock" type="button" class="btn"
									value="Add Stock"
									onclick="addStock(${avai.GOOD_ID});return false;"></td>
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
			<br /> <br />
		</div>

	</div>
</body>
</html>
