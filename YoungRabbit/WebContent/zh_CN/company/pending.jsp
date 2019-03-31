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
#Layer1 {
	position:absolute;
	width:500px;
	height:1000px;
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
	top: 120px;
	left:300px;
}#Layer4 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 120px;
	left:300px;
}#Layer5 {
    position:absolute;
	width:200px;
	height:40px;
	z-index:1;
	top: 120px;
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
	left: 300px;
	top: 750px;
}

#Layer8 {
	position:absolute;
	width:200px;
	height:300px;
	z-index:3;
	top: 83px;
	left: 865px;
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
.STYLE35 {
	color:#CCCCCC ;
	font-family: "Microsoft YaHei UI";
	font-size: 16px;
}
.STYLE36 {
	color:#000000 ;
	font-family: "Microsoft YaHei UI";
	font-size: 16px;
}
.STYLE37 {
	color:#0099FF ;
	font-family: "Microsoft YaHei UI";
	font-size: 16px;
}

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
    <div id="Layer5" class="STYLE1" style="height: 600px; width: 680px">
	 <br>
      <table width="674" height="64" border="0">
        <tr>
          <td width="134" height="29"><img src="filter.jpg" alt="Filiter transfers" width="134" height="28" onclick="MM_showHideLayers('Layer3','','show')" /></td>
          <td width="13"><img src="magnifier.jpg" /></td>
          <td width="306">
            <input  name="transfer" type="text" class="STYLE19" value=" Start typing to search for transfers" size=35 width=320px>         </td>
          <td width="157"><input  name="transfer" type="text" class="STYLE19" value="Save this search" width="150"></td>
          <td width="42" ><img src="trash.jpg" /></td>
        </tr>
        <tr>
          <td height="29"><img src="pending.jpg" width="146" height="30" /></td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td>&nbsp;</td>
          <td >&nbsp;</td>
        </tr>
      </table>
	  <table width="674" height="79" border="0" class="STYLE36">
  <tr>
    <td width="55" height="37"><img src="box.jpg" width="55" height="35" /></td>
    <td width="86" class="STYLE36">Transfer</td>
    <td width="122">Created</td>
    <td width="145">Expected arrival</td>
    <td width="84">Supplier</td>
    <td width="64">Status</td>
    <td width="88">Recieved</td>
  </tr>
  <tr>
    <td><form id="form1" name="form1" method="post" action="">
      <input type="radio" name="radiobutton" value="radiobutton" />
    </form>
    </td>
    <td class="STYLE36">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>

	</div>
    
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


