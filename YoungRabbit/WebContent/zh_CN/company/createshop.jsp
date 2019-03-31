<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="baseHeader.jsp"></jsp:include>
<html>
	<head>
		
		    <SCRIPT type="text/javascript">
		    var access = false;
		    
		    function createShop(){
		      access = true;
		       if(access == true){
		          $("form").submit();
		       }
            }

            </SCRIPT>
            <script type="text/javascript">
            
           
  </script>
	</head>
	<body>
		<DIV class="wapper cbody">
			<DIV class="Ccontent left margin-top">
				<DIV id="position">
					<P>
						<A href="jsp/login.jsp">Home</A>&gt;
						<A href="jsp/createshop.jsp">CreateShop</A>&gt;
					</P>


				</DIV>
				<DIV id="list2" class="margin-top">

					<form action="servlet/createShop" method="post">
						<table cellspacing="0" cellpadding="0" width="1000">
							<tbody>
								<tr>
									<th style="text-align: left; padding-left: 15px;" width="600">
										<p
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">
											Please fill in your real information!
										</p>
									</th>

								</tr>
								<tr class="alist00">
									<td>
										<div id=logoin_s_form>
											<table cellspacing=0 cellpadding=0 width="100%"
												style="font-size: 12px;">
												<tbody>
												   <tr>
														<td style="TEXT-ALIGN: left; COLOR: #224469" colspan="2" align="left">
															<span style="COLOR: #f60" id="errorMsg"></span>
														</td>
														
													</tr>
												
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Shop Name：
														</td>
														<td>
															<input type="hidden" name="email" value="<%=request.getAttribute("email")%>" />
															<input type="hidden" name="password" value="<%=request.getAttribute("password")%>" />
															<input type="text" size=25 name="shopname" />
															<span style="COLOR: #f60" id="shopname"></span>
														</td>
													</tr>
													
													
													<tr align="center">
														<td colspan="2" align="right">
															<input type="button" value="Create Shop" onClick="createShop()" />
														</td>
													</tr>

												</tbody>
											</table>
										</div>
									</td>
								</tr>
								<tr class="alist11">
									<td colspan="3">
										&nbsp;
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


