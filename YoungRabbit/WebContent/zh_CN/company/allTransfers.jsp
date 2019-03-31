<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="<%=basePath%>jsp/products/transferHeader.jsp"></jsp:include>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
	width:200px;
	height:40px;
	z-index:1;
	top: 120px;
	left:300px;
}
#Layer3 {
	position:absolute;
	width:169px;
	height:171px;
	z-index:1;
	top: 216px;
	left:302px;
	visibility: hidden;
}#Layer4 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 120px;
	left:300px;
}#Layer5 {
	position:absolute;
	width:201px;
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
	width:569px;
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
	left: 863px;
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





</style>
<script type="text/JavaScript">
<!--

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_showHideLayers() { //v6.0
  var i,p,v,obj,args=MM_showHideLayers.arguments;
  for (i=0; i<(args.length-2); i+=3) if ((obj=MM_findObj(args[i]))!=null) { v=args[i+2];
    if (obj.style) { obj=obj.style; v=(v=='show')?'visible':(v=='hide')?'hidden':v; }
    obj.visibility=v; }
}
//-->
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

<body tracingsrc="allTransfer.png" tracingopacity="50" >

<div>
    <div id="Layer1"><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"></div>
    <div id="Layer2" class="STYLE1" style="height: 600px; width: 680px">
	  <br>
      <table width="527" height="33" border="0">
        <tr>
          <td width="118" height="29"><img src="image/filter.jpg" alt="Filiter transfers" width="134" height="28" onclick="MM_showHideLayers('Layer3','','show')" /></td>
          <td width="30"><img src="Magnifier_16px_1180702_easyicon.net.png" /></td>
          <td width="365"><input  name="products" type="text" class="STYLE19" value=" Start typing to search for transfers" size=40 width=350px>
          &nbsp;</td>
        </tr>
      </table>
  </div>
   
   <div id="Layer6">
       <p class="STYLE5">Transfer</p>
   </div>
   
  <div class="STYLE19" id="Layer7">Learn more about Inventory Transfers at the YoungRabbit Help Center  </div>
   
   <div id="Layer8"><a href="jsp/addTransfer.jsp"><img src="image/AddTransferButton.jpg" alt="BUTTON" width="116" height="34" />   </div>
   <div id="Layer3">
     <table width="200" height="92" border="0">
       <tr>
         <td height="45" class="STYLE36"><div align="center">Show all transfer where </div></td>
       </tr>
       <tr>
         <td><form id="form1" name="form1" method="post" action="" align="center">
           <select name="select" size="1" class="STYLE36">
             <option>Select a filiter</option>
             <option>Status</option>
             <option>Supplier</option>
             <option>Taged with</option>
             <option>Created</option>
           </select>
         </form>         </td>
       </tr>
     </table>
   </div>
</div>           
          
  
</body>
</html>
