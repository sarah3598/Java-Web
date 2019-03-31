<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="yb_base_head_include.jsp"%>
</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
    <%@include file="yb_base_nav.jsp" %>
    <%@include file="yb_product_image_slide.jsp" %>
	
    <%@include file="yb_base_foot_include.jsp"%>
	</body>
</html>