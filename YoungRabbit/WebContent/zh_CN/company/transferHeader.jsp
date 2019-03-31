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
			
			<DIV id="nav">
				<DIV>


					<P class="idTabs">
						
						<A id="#nav01" href="jsp/allTransfers.jsp">
							AllTransfers </A>
						<A id="#nav02" href="jsp/pending.jsp">
							Pending </A>
						<A id="#nav03" href="jsp/partial.jsp">
							Partial </A>
						<A id="#nav04" href="jsp/complete.jsp">
							Complete </A>
						
						
					</P>
				</DIV>
			</DIV>			
			</DIV>
			
			</BODY>
</HTML>>
