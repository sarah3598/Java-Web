<%@ page language="java" import="java.util.*,com.csh.util.*,com.csh.domain.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
 //Users meetinginfo = (MeetingInfo)request.getSession().getAttribute("meetinginfo");	
 Users userinfo = (Users) request.getSession().getAttribute("userinfo");
			
%>

<jsp:include page="baseHeader.jsp"></jsp:include>
<html>
	<head>
		
		    <SCRIPT type="text/javascript">
		    var access = false;
		    
		    function usereditadd(){
		    	
		      //checkRegister();
		      alert("usereditadd");
		      access = true;
		       if(access == true){
		          $("form").submit();
		       }
            }

            function checkRegister(){
                  var val = $("form input[name='userEmail']").val();
		          var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		          var res = reg.test(val)
		          if(!res){
		            $("#errorMsg").html("Email address format is wrong!");
		            return;
		          }
		          $.ajax({
                    type: "POST",
                    url: "<%=basePath%>servlet/validata?method=validateEmail",
                    data: "email="+val,
                    async:false,
                    success: function(msg){
                        
                         if(msg == 0){
                            access = true;
                         }
                     }
                  });
                  if(access == false){
                      $("#errorMsg").html("Email has been occupied!");
		              return;
                  }
                  access = false;
                  reg = /^[a-zA-Z]\w{5,17}$/;
                  var password = $("form input[name='userPassword']").val(); 
                  if(!reg.test(password)){
                     $("#errorMsg").html("Password must begin with a letter，The length is between 6 to 18 byte，must contain only letters, numbers, and underscores");
		              access = false;
		              return;
                  }
                  
                  var pwdconfirm = $("form input[name='pwdconfirm']").val(); 
                  if(password != pwdconfirm ){
                     $("#errorMsg").html("The password and confirmation password are different.");
		              access = false;
		             return;
                  }
		          reg = /^1[3|4|5|7|8|][0-9]{9}/;
		          var phone = $("form input[name='userPhone']").val(); 
		          if(!reg.test(phone)){
		             $("#errorMsg").html("Phone number is wrong!");
		             access = false;
		             return;
		          }
		          access = true;
		          
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
						<A href="jsp/webUserlogin.jsp">Home</A>&gt;
						<A href="jsp/webUserlogin.jsp">edit user detail</A>&gt;
					</P>


				</DIV>
				<DIV id="list2" class="margin-top">

					<form action="/servlet/webreportusers?method=usereditadd" method="post">
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
															<span style="COLOR: #f60">*</span>Email：
														</td>
														<td>
															
															<input type="text" name="email" value = "${userinfo.email}"/>
															<span style="COLOR: #f60" id="email"></span>
														</td>
													</tr>
													<!-- <tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>User Name:
														</td>
														<td>
															
															<input type="text"
																 name="accountEmail" />
															<span style="COLOR: #f60" id="emailCheck"></span>
														</td>
													</tr>
													 -->
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>First Name：
														</td>
														<td>
															
															<input type="text" name="userFirstname" value = "${userinfo.firstName}"/>
															<span style="COLOR: #f60" id="firstName"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Last Name：
														</td>
														<td>
															
															<input type="text" name="userLastname" value ="${userinfo.lastName}"/>
															<span style="COLOR: #f60" id="lastName"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Address：
														</td>
														<td>
															
															<input type="text"
																 name="userAddress" value ="${userinfo.address}" />
															<span style="COLOR: #f60" id="address"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>City：
														</td>
														<td>
															
															<input type="text" name="userCity" value ="${userinfo.city}"/>
															<span style="COLOR: #f60" id="city"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Zip/Postal code：
														</td>
														<td>
															
															<input type="text"
																 name="userZipcode" value ="${userinfo.zipCode}"/>
															<span style="COLOR: #f60" id="zipCode"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Country:
														</td>
														<td>
															
															<input type="text"
																 name="userCountry" value ="${userinfo.country}"/>
															<span style="COLOR: #f60" id="country"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Phone Number:
														</td>
														<td>
															
															<input type="text"
																 name="userPhone" value ="${userinfo.phone}"/>
															<span style="COLOR: #f60" id="phone"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>password：
														</td>
														<td>
															<input maxlength="20" size=15 type=password
																name="userPassword" />
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>confirm password：
														</td>
														<td>
															<input maxlength="20" size=15 type=password
																name="pwdconfirm" />
														</td>
													</tr>
						                           <tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Status：
														</td>
													<td>
									<select name="Status" id ="Status" style="width: 80px;">
										<option value="0" selected>
											NULL
										</option>
										<option value="1">
											Administrator
										</option>
										<option value="2">
										   SalesAgent
										</option>
										<option value="3">
										   WholeSaler
										</option>
										<option value="4">
											Customer
										</option>
									</select>
								</td> 	</tr>			
													
													
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Contract：
														</td>
														<td>
															<input maxlength="20" size=15 type=text
																name="userContract" value ="${userinfo.contract}"/>
														</td>
													</tr>
													
														<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Upper MemberID：
														</td>
														<td>
															<input maxlength="20" size=15 type=text
																name="userUpperID" value ="${userinfo.upperUserId}"/>
														</td>
													</tr>
													
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Upper Name：
														</td>
														<td>
															<input maxlength="20" size=15 type=text
																name="userUpperName" value ="${userinfo.upperUser}"/>
														</td>
													</tr>
													
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Resume：
														</td>
														<td>
															<input maxlength="20" size=15 type=text
																name="remark" value ="${userinfo.remark}"/>
														</td>
													</tr>
													
													<tr align="center">
														<td align="right">
														<img src="image/cancel.jpg" onClick="cancel()" />
															<img src="image/save.jpg" onClick="usereditadd()" />
														</td>
														<td>

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


