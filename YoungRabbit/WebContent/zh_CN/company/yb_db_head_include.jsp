<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String header_path = request.getContextPath();
	String header_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + header_path + "/";
%>
<%@include file="yb_db_session_null.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=header_path%>/image/favicon.ico">

<title>YR</title>

<!-- Bootstrap core CSS -->
<link href="<%=header_path%>/dist/css/bootstrap.min.css" rel="stylesheet"> 

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<link href="<%=header_path%>/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

<!-- Custom styles for this template -->
<!--<link href="<%=header_path%>/css/dashboard.css" rel="stylesheet">-->
<link rel="stylesheet" href="<%=header_path%>/css/qubico.css">




<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]>
<script src="<%=header_path%>/assets/js/ie8-responsive-file-warning.js"></script>
<![endif]-->

<script src="<%=header_path%>/assets/js/ie-emulation-modes-warning.js"></script>

<!-- jpp -->
<link href="<%=header_path%>/css/stp.css" rel="stylesheet">
<script src="<%=header_path%>/js/handlebars.js"></script>
<script src="<%=header_path%>/js/iform.js"></script>
<script src="<%=header_path%>/js/stp.js"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="<%=header_path%>/js/html5shiv.min.js"></script>
      <script src="<%=header_path%>/js/respond.min.js"></script>
<![endif]-->

<link rel="stylesheet" href="<%=header_path%>/css/uploadImg.css">
<script src="<%=header_path%>/js/jquery.min.js"></script>
<script src="<%=header_path%>/js/bootstrap.min.js"></script>
<script src="<%=header_path%>/js/uploadImg.js"></script>



