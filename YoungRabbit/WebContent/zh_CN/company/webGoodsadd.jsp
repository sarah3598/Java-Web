<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<jsp:include page="baseHeader_0.jsp"></jsp:include>
<html>
	<head>
		
		    <SCRIPT type="text/javascript">
		    var access = false;
		    
		    function addGood(){
		    	
		      //checkRegister();
		      alert("addGood");
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
						<A href="jsp/webUserlogin.jsp">goods</A>&gt;
					</P>


				</DIV>
				<DIV id="list2" class="margin-top">

					<form action="servlet/addGood" method="post">
						<table cellspacing="0" cellpadding="0" width="1000">
							<tbody>
								<tr>
									<th style="text-align: left; padding-left: 15px;" width="600">
										<p
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">
											Please fill goods information!
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
															<span style="COLOR: #f60">*</span>Sorts of Goods：
														</td>
														<td>
															
															<input type="text" name="orgaId" />
															<span style="COLOR: #f60" id="orgaId"></span>
														</td>
													</tr>
													
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Goods title：
														</td>
														<td>
															
															<input type="text" name="title" />
															<span style="COLOR: #f60" id="firstName"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Goods Description：
														</td>
														<td>
															
															<input type="text"
																 name="description" />
															<span style="COLOR: #f60" id="description"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Image:
														</td>
														<td>
															
															<input type="text"
																 name="imagesId" />
															<span style="COLOR: #f60" id="imagesId"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>Price：
														</td>
														<td>
															
															<input type="text" name="pricing" />
															<span style="COLOR: #f60" id="pricing"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>ComparePrice：
														</td>
														<td>
															
															<input type="text"
																 name="compareAtPrice" />
															<span style="COLOR: #f60" id="compareAtPrice"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>TaxMark:
														</td>
														<td>
															
															<input type="text"
																 name="taxMark" />
															<span style="COLOR: #f60" id="taxMark"></span>
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
											selling
										</option>
										<option value="2">
										   stop selling
										</option>
										<option value="3">
										   initing
										</option>
									
									</select>
								</td> 	</tr>			
													
													<tr align="center">
														<td align="right">
															<img src="image/reg.jpg" onClick="addGood()" />
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


