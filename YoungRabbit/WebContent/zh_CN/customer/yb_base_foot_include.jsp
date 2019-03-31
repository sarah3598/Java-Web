<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String foot_path = request.getContextPath();
	String foot_nav_basePath = request.getScheme() + "://" + request.getServerName() + ":"
			+ request.getServerPort() + foot_path + "/";
%>

<!-- Bootstrap core JavaScript ================================================== -->
<script src="<%=foot_path%>/js/jquery.min.js"></script>
<script src="<%=foot_path%>/dist/js/bootstrap.min.js"></script>
<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
<script src="<%=foot_path%>/assets/js/vendor/holder.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="<%=foot_path%>/assets/js/ie10-viewport-bug-workaround.js"></script>
<!--<script src="<%=foot_path%>/dist/js/flat-ui.min.js"></script>-->


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