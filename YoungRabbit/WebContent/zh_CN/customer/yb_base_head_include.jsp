<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String header_path = request.getContextPath();
	String header_basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + header_path + "/";
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="<%=header_path%>/image/favicon.ico">
<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="<%=header_path%>/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=header_path%>/assets/css/ie10-viewport-bug-workaround.css">
<link rel="stylesheet" href="<%=header_path%>/css/carousel.css">
<link rel="stylesheet" href="<%=header_path%>/css/qubico.css">

<!-- Loading Flat UI -->
<!--<link href="<%=header_path%>/dist/css/flat-ui.css" rel="stylesheet">-->
<!--<link href="<%=header_path%>/dist/css/demo.css" rel="stylesheet">-->

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
      
<script src="<%=header_path%>/js/jquery.min.js"></script>
<script src="<%=header_path%>/js/bootstrap.min.js"></script>