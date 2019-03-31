<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String foot_path = request.getContextPath();
	String foot_nav_basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + foot_path + "/";
%>
<script src="<%=foot_path%>/js/bootstrap.js"></script>
<script src="<%=foot_path%>/js/jquery_003.js"></script>
<script src="<%=foot_path%>/js/jquery_006.js"></script>
<script src="<%=foot_path%>/js/waypoints.js"></script>
<script>
	$(document).ready(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() >= 50) {
				$('#main-nav').addClass('header-border');
			} else
				$('#main-nav').removeClass('header-border');
		});
	});
</script>