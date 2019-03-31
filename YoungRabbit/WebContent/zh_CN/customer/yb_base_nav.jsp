<%@ page language="java" import="java.util.*,com.csh.servlet.LoginControlPublicServlet" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int TAG=0;
	if(LoginControlPublicServlet.LoginSessionIsNull(request, response))
		TAG=0;
	else{
		int ROLE=Integer.parseInt((String)LoginControlPublicServlet.getSessionKeyValue(request, response, "ROLE"));
		TAG=0;
		if(ROLE==9999)
			TAG=1;
		else 
			TAG=0;
	}
	
	
%>



<c:set var="salary" scope="session" value="<%=TAG%>"/>
<c:choose>
    <c:when test="${salary == 0}">
       <%@include file="yb_base_nav_public.jsp"%>
    </c:when>
    <c:when test="${salary == 1}">
        <%@include file="yb_base_nav_personal.jsp"%>
    </c:when>
    <c:otherwise>
         <%@include file="yb_base_nav_public.jsp"%>
    </c:otherwise>
</c:choose>