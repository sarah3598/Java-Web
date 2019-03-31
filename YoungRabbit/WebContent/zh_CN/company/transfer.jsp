<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>transfer</title>
<script type="text/javascript" src="http://img.jb51.net/jslib/jquery/jquery-1.2.6.js"></script> 
        <script type="text/javascript" src="http://img.jb51.net/jslib/jquery/tween.js"></script> 
<style type="text/css">
<!--
#Layer1 {
	position:absolute;
	width:500px;
	height:400px;
	z-index:1;
	top: 90px;
	left:300px;
}
#Layer2 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 120px;
	left:300px;
}#Layer3 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 90px;
	left:300px;
}#Layer4 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 90px;
	left:300px;
}#Layer5 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 90px;
	left:300px;
}
#Layer6 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 40px;
	left: 320px;
}

#Layer7 {
	position:absolute;
	width:529px;
	height:59px;
	z-index:2;
	left: 900px;
	top: 150px;
}

#Layer8 {
	position:absolute;
	width:200px;
	height:300px;
	z-index:3;
	top: 80px;
	left: 910px;
}

body {
	background-color: #CCCCCC;
}
.STYLE1 {
    background-color: #ffffff;
	font-size: 34px;
	font-family: "Kristen ITC";
	color: #000000;
}

.STYLE5 {
	font-size: 34px;
	font-family: "Kristen ITC";
	color: #808080;
}

.STYLE13 {color: #333333}
a:link {
	color: #FFFFFF;
}

.STYLE15 {
	font-size: 36px;
	font-family: "方正艺黑简体";
	color: #FF33CC;
}

.STYLE16 {color: #FFFFFF}

.STYLE17 {
	font-family: "Kristen ITC";
	font-size: 24px;
	color: #FF3399;
}

.STYLE18 {
	font-size: 28px;
	font-family: "方正艺黑简体";
	color: #FF3399;
}
.STYLE19 {
	color: #999999;
	font-family: "Microsoft YaHei UI";
	font-size: 16px;
}

.STYLE20 {
	color: #FFFFFF;
	font-size: 16px;
	font-family: "楷体";
}
.STYLE22 {
	font-size: 36px;
	font-family: "方正艺黑简体";
}
.STYLE23 {font-size: 24px}
.STYLE24 {font-size: 36px}


.STYLE25 {
	font-size: 16px;
	font-family: "方正艺黑简体";
	color: #333333;
}

.STYLE27 {
	font-size: 24px;
	color: #FFFFFF;
	font-family: "楷体";
}

.STYLE31 {color: #CCCCCC; font-size: 16px; font-family: "楷体"; }




-->
</style>
<script type="text/JavaScript">
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
 <style type="text/css"> 
    img{border:0;} 
    .STYLE32 {
	font-family: "方正艺黑简体";
	color: #FF3399;
}
 .STYLE33 {color: #FF3399}
 .STYLE34 {
	color: #FF3399;
	font-size: 36px;
	font-family: "方正艺黑简体";
}
 </style> 

<body >

<div>
   <div id="Layer1"><jsp:include page="transferHeader.jsp"></jsp:include><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"></div>
    
   <div id="Layer6">
       <p class="STYLE5">Transfer</p>
   </div>
   
   <div class="STYLE19" id="Layer7">Learn more about Inventory Transfers <br />at the YoungRabbit Help Center 
   </div>
   
   <div id="Layer8"><a href="jsp/addTransfer.jsp"><img src="image/AddTransferButton.jpg" alt="BUTTON" width="116" height="34" />
   </div>
</div>           
          
  
</body>
</html>


