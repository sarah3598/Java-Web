<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
		<LINK rel="stylesheet" type="text/css"
			href="css/jquery.treeview.css">
		<LINK rel="stylesheet" type="text/css"
			href="css/red-treeview.css">
		<SCRIPT type="text/javascript" src="js/jquery.fun.js"></SCRIPT>
         <SCRIPT type="text/javascript">
             $(document).ready(function(){
                   
					$(".liFenlei a").each(function(i){
						$(this).addClass("lilia"+i);
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
						<A id="#nav01" href="shopping/products.html">
							Home</A>
						<A id="#nav02" href="jsp/webUserlogin.jsp">||
							AboutUs </A>
						<A id="#nav03" href="jsp/webUserlogin.jsp">||
							Products </A>
						<A id="#nav04" href="jsp/webUserlogin.jsp">||
							Agents List</A>
						<A id="#nav05" href="jsp/webUserlogin.jsp">||
							Magazine </A>
						<A id="#nav05" href="jsp/webUserlogin.jsp">||
							MyAccount</A>	
						<A id="#nav09" href="jsp/webUserlogin.jsp">||
							Reports </A>				
					</P>
				</DIV>
			</DIV>
			<DIV id="navLL">
				<P id="nav02">
					<a href="jsp/webUserlogin.jsp">Free </a>||
		
				</P>
				
				<P id="nav03">
					
					<a href="jsp/products/Createshop.jsp">Create Shop  </a>||
					<a href="jsp/products/Storelogin.jsp">ShopLogin </a>||
					<a href="jsp/products/onlineStore.jsp">online store</a>||
					<a href="jsp/products/selectProduct.jsp">selectProduct  </a>||
					<a href="jsp/products/onlineStore.jsp">addInventory</a>||
					<a href="shopping/products.html">ProductList  </a>||
					
					
				</P>
				<P id="nav04">
				
					<a href="jsp/sysuser.html">Team Management</a>
					<a href="servlet/webreportusers?method=listWebUsers">member List</a>
				</P>
				<P id="nav05">
				
                    <a href="jsp/login.jsp">Free </a>|| 
			
				</P>
				
				<P id="nav09">
					<a href="servlet/webreportusers?method=listWebUsers">Report Salesman</a>||
					<a href="servlet/webreportorders?method=listWebOrders">Report Orders </a>||
					<a href="jsp/cwyb/report_order_detail_sum.jsp">Order List </a>||
					<a href="jsp/cwyb/reportbase250.jsp">Sales List </a>||
				
				
				</P>
			</DIV>
			</DIV>
