<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>

	<%@include file="yb_db_head_include.jsp"%>
	<title>add product</title>
	<script type="text/javascript">
		$(function() {
			$('#imageform').ajaxForm({
				success : function(data) {
					//convert josn to object
					alert("Upload product image success!");
					var dataObj = eval("(" + data + ")");
					$.each(dataObj.imageid,function(idx, item) {
							$('#imageid').val(item.imageid_1);
					});
				},
				error : function(XMLHttpRequest, textStatus,errorThrown) {
					alert("Upload product image failed!");
					alert(errorThrown);
				}
			});
		});
		$(function() {
			$('#prodinfoform').ajaxForm({
				success : function(data) {
					//convert josn to object
					alert("add product success!");
					alert(data);
					var dataObj = eval("(" + data + ")");
					$.each(dataObj.product,function(idx, item) {
							$('#product_id').val(item.product_id);
					});
				},
				error : function(XMLHttpRequest, textStatus,errorThrown) {
					alert("add product failed!");
					alert(errorThrown);
				}
			});
		});
		$(function() {
			$('#saleinfoform').ajaxForm({
				success : function(data) {
					var dataObj = eval("(" + data + ")");
					$.each(dataObj.msg,function(idx, item) {
						alert(item.success);
					});
				},
				error : function(XMLHttpRequest, textStatus,errorThrown) {
					alert("add product failed!");
					alert(errorThrown);
				}
			});
		});
		//change hide mark value
		$(function() {
			$('#hide_mark').click(function(){
				//alert("checkbox click");
				if ($('#hide_mark').is(':checked')) {
					//alert("hide checked!");
					$('#hide_mark').val('1');
					//$('#hide_mark').attr('value',1);			
				}
			})
		});
		//change hide mark value
		$(function() {
			$('#commission_model').click(function(){
				//alert("checkbox click");
				if ($('#commission_model').is(':checked')) {
					//alert("hide checked!");
					$('#commission_model').val('1');
					//$('#hide_mark').attr('value',1);			
				}
			})
		});
		//change hide mark value
		$(function() {
			$('#wholesale_model').click(function(){
				//alert("checkbox click");
				if ($('#wholesale_model').is(':checked')) {
					//alert("hide checked!");
					$('#wholesale_model').val('1');
					//$('#hide_mark').attr('value',1);			
				}
			})
		});
	</script>

</head>

<body>

	<%@include file="yb_db_nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">Dashboard</h1>
				<div class="container">
			<!--一级导航-->
		    <ul id="WEBSITE_CONTROL" class="nav nav-pills">
	            <li  class="active">
		            <a href="#GENERAL" data-toggle="tab">
			                         通用
		            </a>
	 	        </li>
		        <li>
		        	<a href="#PAGES" data-toggle="tab" >
			       		 页面
			 		</a>
				</li>
			</ul>
			<!--一级导航结束-->
			<div id="myTabContent1" class="tab-content">
				<!--通用页面-->
				<div class="tab-pane fade in active" id="GENERAL">
					<br><br><br>
					<form role="form">
	                    <div class="form-group">
		           			<label for="inputfile">上传公司logo</label>
		                    <input type="file" id="inputfile">
	                    </div>
		                <button type="submit" class="btn btn-default">提交</button>
                    </form>
				</div>
				<!--通用页面结束-->
				<!--页面控制-->
			    <div class="tab-pane fade" id="PAGES"><br />
			    	<!--页面控制下的二级导航-->
			    	<ul id="WEBSITE_CONTROL" class="nav nav-tabs">
	                    <li class="active">
		                    <a href="#HOME" data-toggle="tab">
			                                主页
		                    </a>
	 	                </li>
		                <li>
		        	        <a href="#ABOUT_US" data-toggle="tab">
			       		         关于我们
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#PRODUCTS" data-toggle="tab">
			       		         产品
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#AGENT_LIST" data-toggle="tab">
			       		         代理列表
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#MAGAZINE" data-toggle="tab">
			       		         杂志
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#TERMS" data-toggle="tab">
			       		         服务条款
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#CONTACT" data-toggle="tab">
			       		         联系我们
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#FAQ" data-toggle="tab">
			       		         常见问题解答
			 		        </a>
				        </li>
				        <li>
		        	        <a href="#FOLLOW" data-toggle="tab">
			       		         关注我们
			 		        </a>
				        </li>
			     </ul>
			     <button class="btn btn-default">编辑</button>
			     <!--页面控制下的二级导航结束-->
			        <div class="tab-content">
			        	<!--主页控制-->
			            <div class="tab-pane fade in active" id="HOME">
			            	<br><br><br>
					        <form role="form">
	                            <div class="form-group">
		                        <label for="inputfile">上传图片</label>
		                        <input type="file" id="inputfile">
	                            </div>
		                        <button type="submit" class="btn btn-default">提交</button>
                            </form>
				        </div>
				        <!--主页控制结束-->
				        <!--关于我们-->
				        <div class="tab-pane fade" id="ABOUT_US">			            
				            <form class="form-horizontal" role="form">
   				    		    <div class="form-group col-lg-3">
    				    			<div class="col-sm-offset-2 col-sm-10">
      				    				<div class="checkbox">
       				    				    <label>
         				    				    <input type="checkbox">隐藏
      				    				    </label>
      				    				    <button type="submit" class="btn btn-default">确认</button>
     				    				</div>
    				    		    </div>
  				    		   </div>
                            </form>

		                    <div class="col-md-8 column">
			                    <a id="modal-856382" href="#modal-container-856382" role="button" class="btn" data-toggle="modal"><button type="button" class="btn btn-default">修改</button></a>
			                    <div class="modal fade" id="modal-container-856382" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				                    <div class="modal-dialog">
				                        <div class="modal-content">
						                    <div class="modal-header">
							                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
						                            <h4 class="modal-title" id="myModalLabel">
								                                         关于我们
							                        </h4>
											</div>
											<div class="modal-body">
												内容...
											</div>
											<div class="modal-footer">
										    	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="button" class="btn btn-primary">保存</button>
											</div>
										</div>
                                    </div>
                                </div>
                                <button type="button" class="btn btn-default">删除</button>
                            </div>
	                    </div>
                        <!--关于我们结束-->   
                        <!-- 商品控制-->
						<div class="tab-pane fade" id="PRODUCTS">
				        	<br>
				        	<!--产品下的三级菜单-->
				            <ul id="WEBSITE_CONTROL" class="nav nav-tabs">
	                            <li class="active">
		                            <a href="#PRODUCT_EDIT" data-toggle="tab">
			                                                产品
		                            </a>
	 	                        </li>
		                        <li>
		        	                <a href="#BUNDLES" data-toggle="tab">
			       		                       捆绑   
			 		                </a>
				                </li>
			                </ul>
			                <!--产品下的三级菜单结束-->
			                <div id="myTabContent2" class="tab-content">
			                	<div class="tab-pane fade in active" id="PRODUCT_EDIT">
			                	    <br>
					           	    <h1><span class="label label-default">添加商品</span></h1>
					                <!-- 添加商品  by litai-->
					                <div class="wapper cbody" style="margin-left: 100px">
									<div class="Ccontent left margin-top">
										<div class="margin-top STYLE1" id="list2">
											<div class="container-fluid">
												<div class="row-fluid">
													<div class="span12">
														<ul class="breadcrumb">
															<li><a href="<%=basePath%>products/list?method=list">商品</a>
																<span class="divider">/添加商品</span></li>
														</ul>
													</div>
												</div>
											</div>
											<div class="container-fluid" style="margin-top: 30px" id="addproduct">
												<form>
												<div>
													<div style="float: left; width: 420px; height: auto">
													
														<div>
															<div class="container kv-main"
																style="width: 400px; height: auto">
																	<input id="file-3" type="file" name="file" />
																
															</div>
														</div>
														<br>
														<div style="margin-left: 30px">
															
																<p>
																	<input id="hide_mark" name="hide_mark" type="checkbox" value='0'> <label>隐藏</label>
																</p>
																<p >
																	<input style="width: 200px;" type="text" name="product_title"
																		value="商品名称" class="form-control largcontrol">
																</p>
																<p>
																	<select style="width: 200px;" id="bannedCountries"
																		name="banned_countries" class="btn btn-default">
																		<option value="China">中国</option>
																		<option value="Singapore" selected="selected">新加坡</option>
																		<option value="Malaysia">马来西亚</option>
																	</select>
																</p>
														</div>
													</div>
													<div style="margin-top: 50px; margin-left: 10px; float: left; width: 400px; height: auto;">
														
							
															<div id="addproducts_right" class="addproducts_right" style="width=100%">
																<div style="float:left; width: 30%;">
																	<select id="currency" name="currency" class="btn btn-default">
																		<option value="Pound">英镑</option>
																		<option value="RMB" selected="selected">人民币</option>
																		<option value="Ringgit">林吉特</option>
																	</select>
																</div>
																<div  style="float:left; width: 70%;">
																	<table>
																		<tr>
																			<td style="width: 50px;font-size:15px">价格:
																			
																			</td>
																			<td width="100px">
																				<input type="text" name="product_price" id="product_price" class="form-control largcontrol" > 
																			<!-- <span class="text-danger" style="display:none;">请输入正确的商品价格（0~999999）。</span> -->
																			</td>
																		</tr>
																	</table>	
																</div>
																<div style="clear:both"></div>
																<br>
																<p>
																	<input name="commission_model" id="commission_model"type="checkbox" value='0'class="btn btn-default"> 
																	<label>佣金模式</label>
																</p>
							
																<p>
																	<table>
																		<tr>
																			<td style="width: 180px;">销售佣金:</td>
																			<td  width="150px"><input type="text" name="sale_commission"id="sale_commission"  value="0" class="form-control largcontrol">
																			<span class="text-danger" style="display:none;">请输入提取佣金比例（0~100）。</span>
																			</td>
																			<td style="width: 18px;">%</td>
																		</tr>
																	</table>
																</p>
							
																<p>
																	<table>
																		<tr>
																			<td style="width: 180px;">管理者覆盖佣金:</td>
																			<td width="150px">
																				<input type="text" name="manager_overriding" id="manager_overriding" value="0" class="form-control largcontrol">
																				<span class="text-danger" style="display:none;">请输入提取佣金比例（0~100）。</span>
																			</td>
																			<td style="width: 18px;">%</td>
																		</tr>
																	</table>
																</p>
							
																<p>
																	<table>
																		<tr>
																			<td style="width: 180px;">副总覆盖佣金:</td>
																			<td width="150px">
																				<input type="text" name="vp_overriding" id="vp_overriding"class="form-control largcontrol" value="0">
																				<span class="text-danger" style="display:none;">请输入提取佣金比例（0~100）。</span>
																			</td>
																			<td style="width: 18px;">%</td>
																		</tr>
																	</table>
																</p>
							
																<p>
																	<table>
																		<tr>
																			<td style="width: 180px;">所有者佣金（个人佣金）:</td>
																			<td width="150px">
																				<input type="text" name="ownership_commission" id="ownership_commission"class="form-control largcontrol" value="0">
																				<span class="text-danger" style="display:none;">请输入提取佣金比例（0~100）。</span>
																			</td>
																			<td style="width: 18px;">%</td>
																		</tr>
																	</table>
																</p>
																<br>
																<p>
																	<input name="wholesale_model" id="wholesale_model"
																		type="checkbox" value='0'
																		class="btn btn-default"> <label>批发模式</label> <br>
																</p>
							
																<p>
																<table>
																	<tr>
																		<td style="width: 180px;">批发价格:</td>
																		<td>
																			<input type="text" name="whole_price"id='whole_price' class="form-control largcontrol">
																			<span class="text-danger" style="display:none;">请输入正确的批发价格(0~999999)。</span>
																		</td>
																	</tr>
																</table>
																<br>
																<table>
																	<tr>
																		<td style="width: 180px;">代理价格:</td>
																		<td>
																			<input type="text" name="agent_price" id="agent_price"class="form-control largcontrol">
																			<span class="text-danger" style="display:none;">请输入正确的代理价格(0~999999)。</span>
																		</td>
																	</tr>
																</table>				
																<br>
																<div>
																	<input type="submit" name="editproduct" id="editproduct" value="修改"class="btn btn-primary">
																	<input type="submit" name="deleteproduct" id="deleteproduct" value="删除"class="btn btn-primary">
																</div>
															</div>
													</div>
												</div>
												</form>
											</div>
										</div>
									</div>
								</div> <!-- 添加商品结束 -->					             
				                </div>
				                <div class="tab-pane fade" id="BUNDLES">
				                    <br>
					                <p>bundles</p>
				                </div>
			                </div>
				        </div>
				        <!-- 商品控制结束-->
				        <!--代理列表-->
				      <div class="tab-pane fade" id="AGENT_LIST"><br />
				        	<table class="table table-hover">
					    	<thead>
								<tr>
									<th>
										姓名
									</th>
									<th>
										等级
									</th>
                        			<th>
										加入日期
									</th>
									<th>
										地区
									</th>
									<th>
										联系方式
									</th>
									<th>
										代理语言能力
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>    
				        </div>   
				        <!--代理列表结束-->
				        <!--杂志-->
				        <div class="tab-pane fade" id="MAGAZINE">
				            <p>to be developed</p>
				        </div>
				        <!--杂志结束-->
				        <!--服务条款-->
				        <div class="tab-pane fade" id="TERMS">
				            <p>to be developed</p>
				        </div>
				        <!--服务条款结束-->
				        <!--联系我们-->
				        <div class="tab-pane fade" id="CONTACT">
				            <p>to be developed</p>
				        </div>
				        <!--联系我们结束-->
				        <!--常见问题解答-->
				        <div class="tab-pane fade" id="FAQ">
				            <p>to be developed</p>
				        </div>
				        <!--常见问题解答结束-->
				        <!--关注我们-->
				        <div class="tab-pane fade" id="FOLLOW">
				            <p>to be developed</p>
				        </div>
				        <!--关注我们-->
				    </div> 
			    </div>
			    <!--页面控制结束-->
			</div>
		</div>
			</div>
		</div>
	</div>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="<%=path %>/js/jquery.min.js"><\/script>')
	</script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=path%>/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/js/ie10-viewport-bug-workaround.js"></script>
	<!--添加商品使用  -->
	<script>
		$("#file-3").fileinput({
			showUpload : false,
			showCaption : true,
			browseClass : "btn btn-primary btn-lg",
			fileType : "any",
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			overwriteInitial : true,
			initialPreviewAsData : true,
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
			maxFileSize : 4000,
			initialPreview : [ "../../upload/products_images/cat1.jpg", ],
			initialPreviewConfig : [ {
				caption : "cat1.jpg",
				size : 329892,
				width : "100px",
				url : "{$url}",
				key : 1
			} ]
		});
	</script>
	<!-- 输入有效性检验用 -->
	<script>
		var obj, rows = [];
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify(){
			var o = validator.is_onetohundred(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify2(){
			var o = validator.is_price(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify3(){
			var o = validator.is_password(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		var cols = [
			{id:"saleinfoform", 
				submit: {
					integrate:false,
					postform: submitWindow,
					url: '/account/addproduct',
					refresh: false,
				}},	
			{id:"product_price", tips:{action:ims_show_warn, text:"商品价格"},
				init:{minLength:1, maxLength:6, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify2},
				submit:{formatter:validator.trim, check:validator.is_price}},
			{id:"sale_commission", tips:{action:ims_show_warn, text:"销售佣金"},
				init:{minLength:1, maxLength:3, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_onetohundred}},
			{id:"manager_overriding", tips:{action:ims_show_warn, text:"管理者覆盖佣金"},
				init:{minLength:1, maxLength:3, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_onetohundred}},
			{id:"vp_overriding", tips:{action:ims_show_warn, text:"副总覆盖佣金"},
				init:{minLength:1, maxLength:3, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_onetohundred}},
			{id:"ownership_commission", tips:{action:ims_show_warn, text:"个人佣金"},
				init:{minLength:1, maxLength:3, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_onetohundred}},
			{id:"whole_price", tips:{action:ims_show_warn, text:"批发价格"},
				init:{minLength:1, maxLength:6, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify2},
				submit:{formatter:validator.trim, check:validator.is_price}},
			{id:"agent_price", tips:{action:ims_show_warn, text:"代理价格"},
				init:{minLength:1, maxLength:6, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify2},
				submit:{formatter:validator.trim, check:validator.is_price}},
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
	</script>
    <script src="../js/bootstrap.js"></script>
    <script src="../js/jquery_003.js"></script>
    <script src="../js/jquery_006.js"></script>
    <script src="../js/waypoints.js"></script>
    <script>
    $(document).ready(function () {
        $(window).scroll(function() {
        if ($(this).scrollTop() >= 50) { 
            $('#main-nav').addClass('header-border');
        }else
            $('#main-nav').removeClass('header-border');
        });
    });
    </script>
</body>
</html>