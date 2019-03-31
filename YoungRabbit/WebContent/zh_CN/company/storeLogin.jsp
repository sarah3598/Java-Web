<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="baseHeader_2.jsp"></jsp:include>
<html>
<style type="text/css">
<!--
.STYLE1 {
	color: #23456a
}

.STYLE2 {
	color: #FF0000
}
-->
</style>
<body>
	<DIV class="wapper cbody">
		<DIV class="Ccontent left margin-top">
			<DIV id="position">
				<P>
					<A href="jsp/login.jsp">Login</A>&gt;
				</P>
			</DIV>
			<DIV id="list2" class="margin-top">
				<SCRIPT type="text/javascript">
					function login() {
						$("form").submit();
					}
				</SCRIPT>
				<form action="servlet/storeLogin" method="post">
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

										<SPAN style="COLOR: #f30"><a href="jsp/register.jsp"><span
												class="STYLE4"> REGISTER </span> </a><span class="STYLE1"></span></SPAN>
									</P>



								</TH>
								<TH width="517" align="left"><p
										style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">&nbsp;</p>
								</TH>
							</tr>
							<tr>
								<td>
									<div align="right"></div>
								</td>
								<td width=480 height="40">

									<div align="left"></div>
									<table width=420 border="0" align=center cellpadding=0
										cellspacing=0>
										<tbody>

											<tr>
											</tr>
											<tr>
											</tr>
											<tr>
											</tr>
											<tr>
	                                           <td height="50" style="COLOR: #224469">
	                                                <span style="TEXT-ALIGN: right; COLOR: #f60">*</span>Store Name</td><td height="50"><input size=30 type=text
													name="storename" /></td>
													</tr>
											<tr>
											
												<td height="50" style="COLOR: #224469"><span
													style="TEXT-ALIGN: right; COLOR: #f60">*</span>Email</td>
												<td height="50"><input size=30 type=text
													name="email" /></td>
												<td height="50"></td>
											</tr>
											<tr>
											</tr>
											<tr>
											</tr>

											<tr>
												<td height="50" style="COLOR: #224469"><span
													style="TEXT-ALIGN: right; COLOR: #f60">*</span>Password</td>
												<td height="50"><input size=30 type=password
													name="password" /></td>
												<td height="50"></td>
											</tr>

										</tbody>
									</table>
									<P style="MARGIN-TOP: 25px" align=center>
										<input name="image" onClick="login()" type=image
											src="image/deng_lu.jpg">

									</P>

								</td>
								<td><img src="image/mapIMG.jpg" width="516" height="288" />
								</td>
							</tr>


						</tbody>
					</table>
				</form>
			</DIV>
		</DIV>
	</DIV>
</BODY>
</HTML>


