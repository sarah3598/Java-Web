<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:directive.page import="java.util.List"/>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>


<body>
<table width="341" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF"  bgcolor="#FEce62">
  
      <tr bgcolor="#FFFFFF">
        <td width="166"><div align="center">
            <table width="160" border="0" align="center" cellpadding="0" cellspacing="0">
              <c:forEach var="v" items="${requestScope.videolist}">
              <tr>
               <td height="150"><div align="center">
               
              <video src="${v.videourl()}" width="160" height="140" controls="controls" autobuffer></video>
              
              
             </div></td>
              
                <td height="20"><div align="center">${v.description()}</div></td>
              
                <td height="20"><div align="center">${v.uptime()}</div></td>
             
                      <td height="20"><div align="left"><a href="head_VideoForm.jsp?method=1&id=${v.id}">评论</a></div></td>
              
                <td height="20"><div align="center">${v.teachername()}</div></td>
             </tr>
              </c:forEach>
            </table>
            
            <table>
            <video width="320" height="240" controls>
  <source src="movie.mp4" type="video/mp4">
  <source src="movie.ogg" type="video/ogg">
  <source src="movie.webm" type="video/webm">
  <object data="videos/1.mp4" width="320" height="240">
    <embed src="videos/1.swf" width="320" height="240">
  </object> 
</video>
            </table>
     
</body>
</html>