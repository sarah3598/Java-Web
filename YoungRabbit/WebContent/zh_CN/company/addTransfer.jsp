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
	top: 80px;
	left:191px;
	background-color: #FFFFFF;
}
#Layer2 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 7px;
	left: 195px;
}#Layer3 {
	position:absolute;
	width:587px;
	height:40px;
	z-index:1;
	top: 671px;
	left:188px;
}#Layer4 {
	position:absolute;
	width:201px;
	height:45px;
	z-index:1;
	top:82px;
	left:192px;
}#Layer5 {
	position:absolute;
	width:201px;
	height:40px;
	z-index:1;
	top: 150px;
	left:193px;
	
}
#Layer6 {
	position:absolute;
	width:201px;
	height:60px;
	z-index:1;
	top: 156px;
	left: 652px;

}

#Layer7 {
	position:absolute;
	width:618px;
	height:439px;
	z-index:2;
	left: 189px;
	top: 220px;
	background-color: #FFFFFF;
	<!--visibility: hidden;-->

	visibility: hidden;
	visibility: hidden;
}

#Layer8 {
	position:absolute;
	width:200px;
	height:417px;
	z-index:3;
	top: 149px;
	left: 942px;
}
#Layer9 {
	position:absolute;
	width:250px;
	height:50px;
	z-index:3;
	top: 620px;
	left: 602px;
	visibility: hidden;
}
#Layer10 {
	position:absolute;
	width:50px;
	height:50px;
	z-index:3;
	top: 260px;
	left: 200px;
	visibility: hidden;
}
#Layer11{
	position:absolute;
	width:50px;
	height:50px;
	z-index:3;
	top: 151px;
	left: 199px;

}
#Layer12{
	position:absolute;
	width:336px;
	height:117px;
	z-index:3;
	top: 80px;
	left: 886px;
	background-color: #FFFFFF;

}
#Layer13{
	position:absolute;
	width:335px;
	height:186px;
	z-index:3;
	top: 221px;
	left: 885px;
	background-color: #FFFFFF;

}
#Layer14{
	position:absolute;
	width:336px;
	height:238px;
	z-index:3;
	top: 424px;
	left: 885px;
	background-color: #FFFFFF;

}

#Layer15{
	position:absolute;
	width:200px;
	height:56px;
	z-index:3;
	top: 670px;
	left: 1021px;
}
#Layer16{
	position:absolute;
	width:44px;
	height:26px;
	z-index:3;
	top: 326px;
	left: 884px;
	
}
#Layer17{
	position:absolute;
	width:44px;
	height:25px;
	z-index:3;
	top: 147px;
	left: 889px;
	
}


body {
	background-color: #CCCCCC;
}

.btn1{
    background-color:#0099FF;
   
    font-size:15px;
	font-family:"Microsoft YaHei UI";
	color:000;
	text-align:center;
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
	font-family: "Microsoft YaHei UI";
	font-size: 24px;
	color:;
}

.STYLE18 {
	font-size: 28px;
	font-family: "方正艺黑简体";
	color: #FF3399;
}
.STYLE19 {
	color:#666666 ;
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
	font-size: 20px;
	color: #FFFFFF;
	font-family: "楷体";
}

.STYLE31 {color: #CCCCCC; font-size: 16px; font-family: "楷体"; }

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
-->
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
 
 </style> 

<body  >
<div>
    <div id="Layer1" class="STYLE17" style="height: 121px; width: 614px">    </div>
	<div id="Layer9"> 
	  <form name="form2" method="post" action="">
        <input type="submit" name="Submit" value="Cancel" class="btn1" >
        <input type="submit" name="Submit2" value="Save transfer" class="btn1">
	  </form>
  </div>
    <div id="Layer2">
  <p class="STYLE5">Transfer</p> </div>
   
    <div id="Layer4">
  <p class="STYLE17">Products</p>
    </div>
	<div id="Layer5">
    <input  name="products" type="text" class="STYLE35" value="       Find products" size=30 width=400px>
  </div>
    <div id="Layer6">
      <input name="btn1" type="button"class="btn1" onClick="MM_showHideLayers('Layer10','','show');MM_showHideLayers('Layer7','','inherit');MM_showHideLayers('Layer9','','show')" value="Browse products">
     
  </div>  
    <div class="STYLE19" id="Layer3">Learn more about Inventory Transfers at the YoungRabbit Help Center  </div>
   <div id="Layer7" >
     <table width="618" height="362" border="0">
       <tr>
         <td width="545" class="STYLE19"> Select products </td>
         <td width="63"><div align="center"><img src="image/wrong.jpg"></div></td>
       </tr>
       <tr>
         <td>
           <div align="left">
             <input  name="products" type="text" class="STYLE35" value="         Find products..." size=60  border=1px >
           </div>
           <div align="left"></div></td>
         <td></td>
       </tr>
       <tr>
         <td class="STYLE19"> All products </td>
         <td><div align="center"><img src="image/arrow.jpg" width="22" height="22"></div></td>
       </tr>
       <tr>
         <td class="STYLE19"> Populor products </td>
         <td><div align="center">
           <form name="form4" method="post" action="">
             <img src="image/arrow.jpg" width="22" height="22">
           </form>
           </div></td>
       </tr>
       <tr>
         <td class="STYLE19">Collections</td>
         <td><div align="center">
           <form name="form5" method="post" action="">
             <img src="image/arrow.jpg" width="22" height="22">
           </form>
           </div></td>
       </tr>
       <tr>
         <td class="STYLE19">Product type </td>
         <td><div align="center">
           <form name="form6" method="post" action="">
             <img src="image/arrow.jpg" width="22" height="22">
           </form>
           </div></td>
       </tr>
       <tr>
         <td class="STYLE19">Tags</td>
         <td><div align="center">
           <form name="form7" method="post" action="">
             <img src="image/arrow.jpg" width="22" height="22">
           </form>
           </div></td>
       </tr>
       <tr>
         <td class="STYLE19">vendors</td>
         <td><div align="center">
           <form name="form8" method="post" action="">
             <img src="image/arrow.jpg" width="22" height="22">
           </form>
           </div></td>
       </tr>
     </table>
</div>
<div id="Layer10"><img src="image/magnifier.jpg"></div>
<div id="Layer11"><img src="image/magnifier.jpg"></div>
<div id="Layer12">
  <table width="304" height="107" border="0">
    <tr>
      <td height="53" class="STYLE36">Supplier</td>
    </tr>
    <tr>
      <td><input  name="products" type="text" class="STYLE35" value="     Search suppliers" size=30 width=300px>&nbsp;</td>
    </tr>
  </table>
</div>
<div id="Layer13">
  <table width="328" height="186" border="0">
    <tr>
      <td height="60" class="STYLE36">Shipment</td>
    </tr>
    <tr>
      <td height="35" class="STYLE19">Expected arrivial </td>
    </tr>
    <tr>
      <td height="30"><input  name="products" type="text" class="STYLE35" value="    YYYY-MM-DD" size=30 width=300px>&nbsp;</td>
    </tr>
    <tr>
      <td class="STYLE37"> +Add tracking number </td>
    </tr>
  </table>
</div>
<div id="Layer14">
  <table width="326" height="223" border="0">
    <tr>
      <td height="63" class="STYLE36">Additional details </td>
    </tr>
    <tr>
      <td height="26" class="STYLE19">Reference number </td>
    </tr>
    <tr>
      <td height="28"><input  name="products" type="text" class="STYLE35" value="" size=30 width=300px>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td height="32"><span class="STYLE19">Tags</span>  &nbsp; &nbsp;  &nbsp; &nbsp; &nbsp;  &nbsp;  &nbsp; &nbsp; <span class="STYLE37"> View all tags</span> </td>
    </tr>
    <tr>
      <td><input  name="products" type="text" class="STYLE35" value="  Urgent,Net15,CoD" size=30 width=300px >&nbsp;</td>
    </tr>
  </table>
</div>
<div id="Layer15">
  <form name="form2" method="post" action="">
        <input type="submit" name="Submit" value="Cancel" class="btn1" >
        <input type="submit" name="Submit2" value="Save transfer" class="btn1">
  </form></div>
  <div id="Layer16"><img src="image/magnifier.jpg"></div>
  <div id="Layer17"><img src="image/magnifier.jpg"></div>
</div>
</body>
</html>




