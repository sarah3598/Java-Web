<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 <jsp:include page="baseHeader.jsp"></jsp:include>
 <html>
 <style type="text/css">
<!--
.STYLE1 {color: #23456a}
-->
 </style>
 <body>
			<DIV class="wapper cbody">
				<DIV class="Ccontent left margin-top">
					<DIV id="position">
						<P>
							<A href="jsp/login.jsp">首页</A>&gt;
							<A href="jsp/login.jsp">会员登录</A>&gt;
						</P>


					</DIV>
					<DIV id="list2" class="margin-top">
						<SCRIPT type="text/javascript">
			
				function login(){
				   $("form").submit();
				}
            </SCRIPT>
                   <form action="servlet/Login" method="post">
						<table width="1039" height="409" align="left" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<th style="text-align: left; padding-left: 15px;" width="69">
										<p
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;									  </p>									</th>
									<TH width="83">
										<P
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;</P>
										
										</TH>
									<TH width="598" align="left"><p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">报名须知:</p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">1.会员注册 ，以邮箱做用户名，并设置密码进行会员信息注册，方可成为会员；(菜</p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">单:自主招生-&gt;注册)</p>
									  <p align="left" style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">2. 登录 用注册的用户名及密码进行登录；(菜单:自主招生-&gt;登录) </p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">3.报名信息填写: 在报名信息填写页面填写报名人的信息，务必保证信息的真实性；</p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">并提交个人照片及身份证扫描照片(最好JPG格式)，填写完毕，提交，一位考生的报</p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">名工作完成； 如果有多位报名人，重复进行3；(菜单:自主招生-&gt;报名信息填写) </p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">4.报名信息查看：填写完成报名人信息，可以报名信息查看网面查看填写的所有报名</p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">人信息列表；列表中每条记录后面有查看按钮，可查看每位考生详细信息；(菜单:自</p>
									  <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">主招生-&gt;报名信息查看)</p>
								    <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">5.打印:考生在考生详细信息页面,经核对考生信息无误后，进行信息打印；(菜单:自主</p>
								    <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">招生-&gt;报名信息查看-&gt;对应记录行后面详情-&gt;打印)</p>
								    <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;</p>
								    <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;<input type="button" onclick="window.history.back()" value="返回" /></p></TH>
									<TH width="287" align="left"><p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;</p>
							        <p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;</p></TH>
							        
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td width=83 height="40"><P style="MARGIN-TOP: 25px" align=center>&nbsp;</P>									</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</tbody>
					 </table>
					</form>
				</DIV>
			</DIV>
		</DIV>
	</BODY>
</HTML>


