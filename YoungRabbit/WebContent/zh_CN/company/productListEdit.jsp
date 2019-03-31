<%@ page language="java" import="java.util.*,com.csh.util.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	PageModel pageModel = (PageModel) request.getAttribute("product");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="../js/jquery.js"></script>
<style>
</style>
<script type="text/javascript"> 
  function ff(val){
     window.location.href="<%=basePath%>products/list?method=listedit&currentPage="+val;
  }  
  function addProduct(){
	     window.location.href="<%=basePath%>jsp/products/addProduct.jsp";
  }
  function editProduct(val){
	     window.location.href="<%=basePath%>products/list?method=editProduct&good_id="+val;
}
</script>
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/layoutit.css">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/bootstrap-combined.css">
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>css/editor_gecko.css">
<html>
<body>
	<div class="wapper cbody">
		<div class="Ccontent left margin-top" style="align: center;margin-left: 200px;">

			<div class="margin-top STYLE1" id="list2">
				<div class="container-fluid">
					<div class="row-fluid">
						<div class="span12">
							<ul class="breadcrumb">
								<li><span>Products</span></li>
							</ul>
						</div>
					</div>
					<div style="text-align: right; padding-right: 200px;">
						<input id="addstock" type="button" class="btn" value="Add Product"
							onclick="addProduct();return false;">
					</div>
				</div>
				<div class="container-fluid">
					<c:forEach items="${product.list}" var="pro" varStatus="status">
						<div style="clear: left;margin-top: 50;">
							<div style="float: left;width: 300px; height: auto;">
								<a
									href="<%=basePath%>products/list?method=detail&goodid=${pro.GOOD_ID}"><img
									src="<%=basePath%>${pro.IMAGE_PATH}" class="img-rounded" /> </a> <a
									href="<%=basePath%>products/list?method=detail&goodid=${pro.GOOD_ID}">${pro.TITLE}
								</a>
							</div>
							<div style="float: left;width: 100px; height: auto;margin-top: 50;margin-left: 30;">
								<input id="editProduct" type="button" class="btn" value="   Edit   "
									onclick="editProduct(${pro.GOOD_ID})">
							</div>
							
						</div>
						<div style="clear: left;width: 300px; height: 20px;">
						</div>
					</c:forEach>
				</div>
				<div class="container-fluid">
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
	</div>
</body>
</html>
