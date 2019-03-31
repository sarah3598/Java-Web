	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 <jsp:include page="baseHeader_0.jsp"></jsp:include>
 <html>
 <style type="text/css">
<!--
.STYLE1 {color: #23456a}
.STYLE2 {color: #FF0000}
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
					alert("login");
				   $("form").submit();
				}
            </SCRIPT>
                   <form action="servlet/Login" method="post">
						<table width="1050" align="left" cellpadding="0" cellspacing="0">
							<tbody>
								<tr>
									<th style="text-align: left; padding-left: 15px;" width="45">
										<p
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;
											
									  </p>
									</th>
									<TH width="480">
										<P
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">
											还不是会员？立即  
											<SPAN style="COLOR: #f30"><a
													href="jsp/register.jsp"><span class="STYLE4"> 注册 </span> </a><span class="STYLE1">成为会员。</span></SPAN>										</P>
										
										<P 
style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">
注册之前，请先阅读<a href="jsp/resume.jsp" class="STYLE4"> 
 
<SPAN style="COLOR: #f30"><a href="jsp/resume.jsp"><span class="STYLE4"> 报名流程 </span> 
</a>。</P>
										
									</TH>
									<TH width="517" align="left"><p style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;</p>
							      </TH>
								</tr>
								<tr>
									<td>
										<div align="right"></div>
									</td>
									<td width=480 height="40">
										
										<div align="left">
											
										</div>
										
										
									
									</td>
									<td>
										<img src="image/mapIMG.jpg" width="516" height="288" />									</td>
								</tr>

								
							</tbody>
					 </table>
					</form>
				</DIV>
			</DIV>
		</DIV>
	</BODY>
</HTML>


