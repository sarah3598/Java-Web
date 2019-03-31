<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META content="text/html; charset=utf-8" http-equiv="Content-Type">
<base href="<%=basePath%>">
<META name="keywords" content="nnbnbnbnb">
<META name="description" content="aaaaaaaaaaa">
<LINK rel="stylesheet" type="text/css" href="css/gobal.css">
<LINK rel="stylesheet" type="text/css" href="css/style.css">
<LINK rel="stylesheet" type="text/css" href="css/color.css">
<LINK rel="stylesheet" type="text/css" href="css/other.css">
<SCRIPT type="text/javascript" src="js/jquery.js"></SCRIPT>

<SCRIPT type="text/javascript" src="js/jquery.idTabs.min.js"></SCRIPT>

<SCRIPT type="text/javascript" src="js/jquery.treeview.js"></SCRIPT>
<LINK rel="stylesheet" type="text/css" href="css/jquery.treeview.css">
<LINK rel="stylesheet" type="text/css" href="css/red-treeview.css">
<SCRIPT type="text/javascript" src="js/jquery.fun.js"></SCRIPT>

<!-- add by jianpeng//////////////////// -->
<script type="text/javascript" src="js/iform.js"></script>
<script type="text/javascript" src="js/stp.js"></script>
<!--///////////////////////////////////-->

<SCRIPT type="text/javascript">
	$(document).ready(function() {

		$(".liFenlei a").each(function(i) {
			$(this).addClass("lilia" + i);
		})
	})
</SCRIPT>

<META name="GENERATOR" content="MSHTML 9.00.8112.16457">
<style type="text/css">
.STYLE4 {
	color: #FF0000
}

.STYLE5 {
	color: #66CCFF
}
</style>
</HEAD>
<BODY>
	<!--start: header-->
	<DIV class="header cbody">

		<!--start: header-->
		<DIV id="nav">
			<DIV>


				<P class="idTabs">
					<A id="#nav01" href="jsp/login.jsp"> Home</A> <A id="#nav02"
						href="jsp/login.jsp">|| Way to sell </A> <A id="#nav03"
						href="jsp/login.jsp">|| Pricing </A> <A id="#nav04"
						href="jsp/login.jsp">|| Agents </A> <A id="#nav05"
						href="jsp/login.jsp">|| Stores </A> <A id="#nav09"
						href="jsp/login.jsp">|| Reports </A>
				</P>
			</DIV>
		</DIV>
		<DIV id="navLL">
			<P id="nav02">
				<a href="jsp/products/onlineStore.jsp">online store</a>|| <a
					href="jsp/login.jsp">Shopify POS</a>|| <a href="jsp/login.jsp">Retail
					Package </a>|| <A href="jsp/login.jsp">Buy Button</A>|| <A
					href="jsp/login.jsp">Facebook Shop</A>|| <A href="jsp/login.jsp">Facebook
					Messenger</A>|| <A href="jsp/login.jsp">Enterprise</A>
			</P>
			<P id="nav03">
				<a href="jsp/login.jsp">start your free trial</a>
			</P>
			<P id="nav04">
				<a href="jsp/resume.jsp">Ecommerce</a>|| <a href="jsp/login.jsp">Login</a>||
				<a href="jsp/register.jsp">Register</a>|| <a href="jsp/autonomy.jsp">Shopify
					Updates</a>|| <a href="servlet/enroll?method=listEnroll">Enterprise</a>
			</P>
			<P id="nav05">
				<a href="jsp/login.jsp">Guides </a>|| <a href="jsp/login.jsp">Videos
				</a>|| <a href="jsp/login.jsp">Podcasts </a>|| <a href="jsp/login.jsp">Success
					Stories </a>|| <a href="jsp/login.jsp">Encyclopedia </a>|| <a
					href="jsp/login.jsp">Forums </a>|| <a href="jsp/login.jsp">Free
					Tools </a>|| <a href="jsp/products/ProductList.jsp">ProductList </a>||
				<a href="jsp/products/Createshop.jsp">Create Shop </a>|| <a
					href="jsp/products/Storelogin.jsp">ShopLogin </a>
			</P>

			<P id="nav09">
				<a href="servlet/webreportusers?method=listWebUsers">Report
					Salesman</a>|| <a href="servlet/webreportorders?method=listWebOrders">Report
					Orders </a>||
				<!-- <a href="servlet/webreportorders?method=listWebOrders">Report </a>|| -->
				<a href="jsp/cwyb/report_order_detail_sum.jsp">Order List </a>|| <a
					href="jsp/cwyb/reportbase250.jsp">Sales List </a>||


			</P>
		</DIV>
	</DIV>