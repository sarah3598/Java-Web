<%@ page language="java" import="java.util.*,com.csh.util.*"
	pageEncoding="utf-8"%>
<%
  String path = request.getContextPath();
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			PageModel pageModel = (PageModel) request.getAttribute("page");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script type="text/javascript">
  function ff(val){
     window.location.href="<%=basePath%>products/list?method=listProduct&currentPage="+val;
  }
  function showProduct(goodId){
    window.location.href="<%=basePath%>products/list?method=showProductDetail&GOOD_ID="+goodId;
  }
  function addProduct(storeId){
	    window.location.href="<%=basePath%>products/list?method=addProduct&USER_ID="+userId;
	  }
</script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>Select Product</title>
<style type="text/css">
</style>
</head>

<body>
	<div class="wapper cbody">
			<div id="position">
				<p>
					<a href="jsp/productList.jsp">Products</a>&gt; <a
						href="jsp/productList.jsp">ProductList</a>&gt;
				</p>
				<p></p>
			</div>
            <div>
            <form>
			<table width="917" border="1">
  <tr>
    <td width="71"><form id="form1" name="form1" method="post" action="">
      <input type="checkbox" name="checkbox" id="checkbox" />
      <label for="checkbox"></label>
    </form></td>
    <td width="77">GoodId</td>
    <td width="142">Good_Image</td>
    <td width="101">Title</td>
    <td width="122"><input type="checkbox" name="checkbox6" id="checkbox6" /></td>
    <td width="113">GoodId</td>
    <td width="101">Good_Image</td>
    <td width="180">Title</td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox2" id="checkbox2" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="checkbox" name="checkbox7" id="checkbox7" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox3" id="checkbox3" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="checkbox" name="checkbox8" id="checkbox8" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox4" id="checkbox4" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="checkbox" name="checkbox9" id="checkbox9" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td><input type="checkbox" name="checkbox5" id="checkbox5" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td><input type="checkbox" name="checkbox10" id="checkbox10" /></td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
   <tr>
    <td colspan="7">&nbsp;</td>
    <td>
      <input type="submit" name="button" id="button" value="Add Product" /></td>
  </tr>
</table>
</form>
		</div>
	</div>
</body>
</html>


