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
			<DIV id="miniNav">
				<img src="image/topbanner.jpg" width="999" height="128"
					alt="aaa" />
			</DIV>
			<DIV id="maxNav">
				<div id="div" class="slibarOne margin-top">
					<h2>
						&nbsp;
					</h2>
					<div></div>
				</div>
			</DIV>
			<!--start: header-->
			<DIV id="nav">
				<DIV>


					<P class="idTabs">
						<A id="#nav15" href="jsp/login.jsp">
						Home</a>
						<A id="#nav01" href="jsp/login.jsp">||
							Resume </A>
						<A id="#nav06" href="jsp/login.jsp">||
							Products </A>
						<A id="#nav02" href="jsp/login.jsp">||
							Inventory </A>
						<A id="#nav03" href="jsp/login.jsp">||
							Transfers </A>
						<A id="#nav08" href="jsp/login.jsp">|| 
						    Collection </A>
						<A id="#nav13" href="jsp/login.jsp">||
							Gift Cards </A>
						<A id="#nav09" href="jsp/login.jsp">||
							Reports </A>
						<A id="#nav04" href="jsp/login.jsp">||
							Customer </A>
						<A id="#nav05" href="jsp/login.jsp">||
							Discounts </A>
						
					</P>
				</DIV>
			</DIV>
			<DIV id="navLL">
				<P id="nav01">
					<a href="jsp/login.jsp">Resume </a>||
					<a href="jsp/login.jsp">学院大事记</a>||
					<a href="jsp/login.jsp">光辉历程 </a>||
					<A href="jsp/login.jsp">联系方式</A>||
				</P>
				<P id="nav02">
					<a href="jsp/login.jsp">自主招生信息</a>||
					<a href="jsp/login.jsp">普通高等学校招生信息</a>||
					<a href="jsp/login.jsp">统招生信息</a>||
					<A href="jsp/login.jsp">其它</A>
				</P>
				<P id="nav03">
					<a href="jsp/resume.jsp">自主招生报名需知</a>||
					<a href="jsp/login.jsp">登录</a>||
					<a href="jsp/register.jsp">注册</a>||
					<a href="jsp/autonomy.jsp">报名信息填写</a>||
					<a href="servlet/enroll?method=listEnroll">报名信息查看</a>
				</P>
	            <P id="nav06">
					<a href="jsp/login.jsp">Add  Products </a>||
					<a href="jsp/login.jsp">List Products </a>||
					<a href="jsp/login.jsp">Edit Products</a>||
					<a href="jsp/login.jsp">Dele Products </a>
				</P>
					
				</P>
				<P id="nav13">
					<a href="jsp/login.jsp">考生问答 </a>||
					<a href="jsp/login.jsp">其它 </a>||
				</P>
				<P id="nav09">
					<a href="servlet/webreportusers?method=listWebUsers">Report Salesman</a>||
					<a href="servlet/webreportorders?method=listWebOrders">Report Orders </a>||
					<a href="servlet/webreportorders?method=listWebOrders">Report </a>||
				</P>
				<P id="nav08">
					<a href="jsp/login.jsp">就业情况  </a>||
					<a href="jsp/login.jsp">其它 </a>||
				</P>

			</DIV>
			</DIV>
