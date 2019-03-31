<%@ page language="java" import="java.util.*,com.csh.domain.*"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	WebGoodsBasicInfo goodinfo = (WebGoodsBasicInfo) request
			.getSession().getAttribute("goodsinfo");
%>

<jsp:include page="baseHeader_1.jsp"></jsp:include>
<html>
	<head>

		<SCRIPT type="text/javascript">
		    var access = false;
		    
		    function addorder(){
		    	
		      //checkRegister();
		      alert("addorder");
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
						<A href="jsp/webUserlogin.jsp">Product details</A>&gt;
					</P>


				</DIV>
				<DIV id="list2" class="margin-top">
                          
					<form action="servlet/weborders?method=addOrder" method="post">
						<table cellspacing="0" cellpadding="0" width="1000">
							<tbody>
								<tr>
									<th style="text-align: left; padding-left: 15px;" width="600">
										<p
											style="TEXT-ALIGN: center; HEIGHT: 20px; COLOR: #23456a; FONT-SIZE: 13px; FONT-WEIGHT: bolder; PADDING-TOP: 15px">
											product information!
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
														<td style="TEXT-ALIGN: left; COLOR: #224469" colspan="2"
															align="left">
															<span style="COLOR: #f60" id="errorMsg"></span>
														</td>

													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469" rowspan="7">
															<span style="COLOR: #f60"></span>商品图片：
														</td>
													
														<td width="159" rowspan="7" bordercolor="1">

															<img src="img/${goodsinfo.imagesId}.jpg" width="60px;"
																height="50px;" />
															
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60"></span>商品编号：
														</td>
														<td>

															<input type="text" name="goodId" value="${goodsinfo.goodId}" readonly="true" />
															<span style="COLOR: #f60 border:solid 1px" id="goodId"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60"></span>商品Title:
														</td>
														<td>

															<input type="text" name="title" value="${goodsinfo.title}" readonly="true"/>
															<span style="COLOR: #f60" id="title"></span>
														</td>
													</tr>

													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60"></span>描述：
														</td>
														<td>

															<input type="text" name="description" value="${goodsinfo.description}" readonly="true"/>
															<span style="COLOR: #f60" id="description"></span>
														</td>
													</tr>

													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60"></span>原价格：
														</td>
														<td>

															<input type="text" name="pricing" value="${goodsinfo.pricing}" readonly="true"/>
															<span style="COLOR: #f60" id="pricing"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60"></span>折后价格：
														</td>
														<td>

															<input type="text" name="compareAtPrice" value="${goodsinfo.compareAtPrice}" readonly="true"/>
															<span style="COLOR: #f60" id="compareAtPrice"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60"></span>状态：
														</td>
														<td>

															<input type="text" name="status" value="${goodsinfo.status}" readonly="true"/>
															<span style="COLOR: #f60" id="status"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>购买数量：
														</td>
														<td>

															<input type="text" name="buynum"/>
															<span style="COLOR: #f60" id="buynum"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>销售商ID:
														</td>
														<td>

															<input type="text" name="customerID"  />
															<span style="COLOR: #f60" id="customerID"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>运费:
														</td>
														<td>

															<input type="text" name="shippingPrice" />
															<span style="COLOR: #f60" id="shippingPrice"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>总费用：
														</td>
														<td>
															<input maxlength="20" size=15 name="totalCharge" />
														    <span style="COLOR: #f60" id="totalCharge"></span>
														</td>
													</tr>
													<tr>
														<td style="TEXT-ALIGN: right; COLOR: #224469">
															<span style="COLOR: #f60">*</span>送货地址：
														</td>
													
														<td>
															<input maxlength="20" size=15 name="soldTo" />
														    <span style="COLOR: #f60" id="totalCharge"></span>
														</td>
													</tr>

													<tr align="center">
														<td align="right">
															<img src="image/order.jpg" onClick="addorder()" />
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


