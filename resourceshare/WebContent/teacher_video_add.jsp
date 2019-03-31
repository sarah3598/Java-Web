<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <table width="573" border="0" cellpadding="0" cellspacing="0" background="images/back_noword_05.jpg">
        <tr>
          <td valign="top" align="center">		
		  
		  <%out.println("<p align=left>&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/icon.gif width=10 height=10>&nbsp;&nbsp;影音上传</p>");%>
		  <form action="Video.do?op=addVideo" method="post"  name="form" onSubmit="return addVideo()" enctype="multipart/form-data">
		  
            <table width="340" border="1" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#FECE62">
              <tr>
                <td width="72" height="30"><div align="center">上传视频：</div></td>
                <td width="255" bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="videoAddress" type="file" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
                <tr>
                <td width="72" height="30"><div align="center">上传图片：</div></td>
                <td width="255" bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="videoPic" type="file" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
                <td height="30"><div align="center">年级：</div></td>
                <td bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="grade" type="text" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
              <tr>
                <td height="30"><div align="center"><span>学科：</span></div></td>
                <td bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="subject" type="text" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
              <tr>
                <td height="30"><div align="center">教师：</div></td>
                <td bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="teachername" type="text" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
              <tr>
                <td height="30"><div align="center"><span>教师编号：</span>${teacher.teacherid}</div></td>
                <td bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="teacherid" type="text" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
              <tr>
                <td height="30"><div align="center">描述：</div></td>
                <td bgcolor="#FFFFFF">
                    <div align="left">
                      <input name="description" type="text" class="inputinput"  size="30">
                  </div></td></tr>
              <tr>
              
                <td height="30"><div align="center">上传时间：</div></td>
                <td bgcolor="#FFFFFF">
                  <div align="left">
                     <input name="videoTime" type="text" class="inputinput" onclick="alert('此文本框已设为只读，用户不能修改')" value="" size="30" readonly="readonly">
                  </div></td></tr>
            </table>
            <table width="494" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="494">
				<br>
				<div align="center" class="style1">注意：影音的格式只能为：“mp4”格式</div></td>
              </tr>
            </table>
            <div><a href="Video.do?op=findAll">查看视频列表</a></div>
            <br>
 <input type="image" class="inputinputinput" src="images/save.gif">
&nbsp;&nbsp;
 <a href="#" onClick="javascript:form.reset()"><img src="images/reset.gif"></a>
            </form>
            		<%if(request.getAttribute("result")!=null){
			out.print(request.getAttribute("result"));
			} %></td>
        </tr>
      </table>
</body>
</html>