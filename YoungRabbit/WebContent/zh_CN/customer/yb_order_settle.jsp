<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en-US">
<head>
<%@include file="yb_base_head_include.jsp"%>
<!-- Loading Bootstrap -->
<link href="<%=path%>/flatui/dist/css/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Loading Flat UI -->
<link href="<%=path%>/flatui/dist/css/flat-ui.css" rel="stylesheet">
<!-- HTML5 shim, for IE6-8 support of HTML5 elements. All other JS at the end of file. -->
<!--[if lt IE 9]>
      <script src="<%=path%>/flatui/dist/js/vendor/html5shiv.js"></script>
      <script src="<%=path%>/flatui/dist/js/vendor/respond.min.js"></script>
    <![endif]-->
<title>Young Rabbit Register</title>



</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<section>
		<div class="container">	
			
			<div id="rule_list">
				<div id="sub_list1">
				<div class="page-header">
					<input type="hidden" name="D_ADDR_ID" id="D_ADDR_ID" value="D_ADDR_ID">
					<h3 class="panel-title" style="display: inline-block;">收货人信息</h3>

				</div>
				<div class="row">
					<div class="alert" id="test">

						<script id="tpl" type="text/x-handlebars-template">
						{{#each this}}
						<div id="addr_class_{{ADDRESS_ID}}" class="panel panel-{{help_style IS_DEFAULT ADDRESS_ID}}">
							<div class="panel-heading">		
								{{USER_NAME}}&nbsp; {{USER_PHONE}}&nbsp; {{help_default IS_DEFAULT}}	                       
								</div>						
							<div class="panel-body">	
								<div id="s0" style="float:right;display:none">
									<a onclick="show_list();">
										<span class="fui-new"></span>
									</a>
								</div>
								<div id="s1" style="float:right;">							
										<a onclick="edit('{{ADDRESS_ID}}','{{USER_NAME}}','{{AREA_ID1}}','{{AREA_ID2}}','{{AREA_ID3}}','{{ADDRESS}}','{{USER_PHONE}}','{{USER_TEL}}','{{IS_DEFAULT}}');" class="btn btn-primary btn-sm" >编辑</a>
										<a onclick="del('{{ADDRESS_ID}}');" class="btn btn-primary btn-sm">删除</a>
								</div>
								
								<div class="form-group" style="float:left;">
             
										<label class="radio" for="OR_ADDRESS_ID_{{ADDRESS_ID}}">
										<a onclick="select('{{ADDRESS_ID}}');">
											<input type="radio" name="OR_ADDRESS_ID" {{help_default_radio IS_DEFAULT}} data-toggle="radio" value="{{ADDRESS_ID}}" id="OR_ADDRESS_ID_{{ADDRESS_ID}}" required="" class="custom-radio" ><span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>
										</a>
										</label>
								</div>
									{{USER_NAME}} {{AREA_ID1}} {{AREA_ID2}} {{AREA_ID3}} {{ADDRESS}} {{USER_PHONE}} {{USER_TEL}}
									
								
							</div>
							
						</div>
						{{/each}}
						</script>
					</div>
					<div id="s2" class="alert"  style="display:none">
						<input class="btn btn-primary btn-sm" onclick="add_address();" value="+ 使用新地址">
						<input class="btn btn-primary btn-sm" onclick="back2();" value="返回">
					</div>
				</div>
				</div>

				<div id="sub_list2">
				<div class="page-header">
					<h3 class="panel-title" style="display: inline-block;">发票信息</h3>
				</div>
				<div class="row">
					<div class="form-group">
						<div class="col-md-12">
							<input name="IS_INVOICE" id="IS_INVOICE" value='0' type="checkbox"> <label>是否开发票</label> <br>
						</div>
					</div>
					<section id="invoice_section" style="display: none;">
						<div class="form-group">
							<div class="col-md-12">
								<label class="radio" for="OR_INVOICE_1">
									<a onclick="sel_invoice_type(1);">
										<input type="radio" name="OR_INVOICE" data-toggle="radio" value="1" id="OR_INVOICE_1" required="" class="custom-radio" ><span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>
									</a>
									个人
								</label>
								<label class="radio" for="OR_INVOICE_2">
									<a onclick="sel_invoice_type(2);">
										<input type="radio" name="OR_INVOICE" data-toggle="radio" value="2" id="OR_INVOICE_2" required="" class="custom-radio" ><span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>
									</a>
									单位
								</label>
							</div>
							<div class="col-md-12">
								<label>发票抬头</label><input type="text" name="INVOICE_CLIENT" id="INVOICE_CLIENT" class="form-control"> <span class="text-danger" style="display: none;">请输入正确的发票抬头。</span>
							</div>
						</div>
					</section>
				</div>
				<div class="page-header">
					<h3 class="panel-title" style="display: inline-block;">支付方式</h3>
				</div>
				<div class="row">
					<div class="alert">
						<label class="radio" for="OR_Pay_1">
							<a onclick="sel_pay_way(1);">
								<input type="radio" name="OR_Pay" data-toggle="radio" value="1" id="OR_Pay_1" required="" class="custom-radio" ><span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>
							</a>
							iPay88
              			</label>
						<label class="radio" for="OR_Pay_2">
							<a onclick="sel_pay_way(2);">
								<input type="radio" name="OR_Pay" data-toggle="radio" value="2" id="OR_Pay_2" required="" class="custom-radio" ><span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>
							</a>
							Paypal
              			</label>
						<label class="radio" for="OR_Pay_3">
							<a onclick="sel_pay_way(3);">
								<input type="radio" name="OR_Pay" data-toggle="radio" value="3" id="OR_Pay_3" required="" class="custom-radio" checked ><span class="icons"><span class="icon-unchecked"></span><span class="icon-checked"></span></span>
							</a>
							微信
              			</label>
						
					</div>
				</div>
				<div class="page-header">
					<h3 class="panel-title" style="display: inline-block;">商品信息</h3>
				</div>
				<div class="row">
					<form id="form2" method="post">
					
						<!--产品 详情 -->
						<div id="test2" class="table-responsive" style="border: 1px solid #eee;">
						<script id="tpl2" type="text/x-handlebars-template">
						{{#each this}}
							<table class="table table-striped">
								<thead>
									<tr>

										<th>产品详情</th>										
										<th>购买数量</th>
										<th>商品总价(元)</th>
									</tr>
								</thead>
								<tbody>
									
										<tr>
											<td>
												<div >
													<span id="IMAGE_ID_{{IMAGE_ID}}">
														{{help_image_id IMAGE_ID}}
													</span>
												</div>
												<div>
													<label>
														名称:{{TITLE}}
															
														<input id="PRODUCT_PRICE" name="PRODUCT_PRICE" type="hidden" value="{{PRODUCT_PRICE}}" >
													</label>
													<label>
														<span class="currency">&nbsp;价格:{{help_currency 	CURRENCY}}</span> 
														<span class="currency">{{PRODUCT_PRICE}}</span> 
													</label>
												</div>
											</td>
											
											<td>
												<input id="BUY_NUM" name="BUY_NUM" type="hidden"  value="1" >
												<span  onclick="buy_num_sub('{{PRODUCT_PRICE}}');" >-</span>
												<span id="BUY_NUM_SPAN">1</span>
												<span class="text-danger" style="display: none;">购买数量不正确(1-9999999999)。</span>
												<span onclick="buy_num_plus('{{PRODUCT_PRICE}}');" >+</span><span class="fui-plus-16"></span>
												
											</td>
											<td>
											
												<span class="currency">{{help_currency CURRENCY}}</span> 
												<span id="PRODUCT_TOTAL_MONEY_SPAN">{{PRODUCT_PRICE}}</span>
												<input type="hidden" id="PRODUCT_TOTAL_MONEY" name="PRODUCT_TOTAL_MONEY"  value="{{PRODUCT_PRICE}}" > 
											
											</td>
										</tr>
									
								</tbody>
							</table>
							<div class="card-footer clearfix">
								<div class="pull-right">
								<label>
									<span class="currency">运费 {{help_currency CURRENCY}}</span> 
									<span>{{SHIPPING_PRICE}}</span> 
									<input  type="hidden" id="SHIPPING_PRICE" name="SHIPPING_PRICE" value="{{SHIPPING_PRICE}}">
								</label>
								</div>
							</div>
							<div class="card-footer clearfix">
								<div class="pull-right">
								<label>
									<span class="currency">优惠 {{help_currency CURRENCY}}</span> 
									<span class="currency">{{help_discount DISCOUNT_MONEY}}</span> 
									<input type="hidden" id="DISCOUNT_MONEY" name="DISCOUNT_MONEY" value="0">
								</label>
								</div>
							</div>
							<div class="card-footer clearfix">
								<div class="pull-right">
								<input type="hidden" id="ORDER_TOTAL_MONEY" name="ORDER_TOTAL_MONEY" value="{{help_money SHIPPING_PRICE PRODUCT_PRICE}}" class="form-control">
								<label>
									<span class="currency">订单总价 {{help_currency CURRENCY}}</span> 
									<span id="ORDER_TOTAL_MONEY_SPAN">{{help_money SHIPPING_PRICE PRODUCT_PRICE}}</span>
									
								</label>
								</div>
							</div>
						{{/each}}
						</script>
						</div>
						<div id="emsg_id"></div>
						<div class="form-group text-center">
							<input type="hidden" name="method" id="method" value=""> 
							<input type="hidden" name="INVOICE_TYPE" id="INVOICE_TYPE" value="">
							<input type="hidden" name="ROLE" id="ROLE" value="<%=request.getParameter("ROLE")%>"> <input type="hidden" name="ADDR_ID" id="ADDR_ID" value=""> <input type="hidden" name="SALES_PERSON_ID" id="SALES_PERSON_ID" value="<%=request.getParameter("SALES_PERSON_ID")%>"> <input type="hidden" name="PAYMENT_WAYS" id="PAYMENT_WAYS" value="3"> <input type="hidden" name="GOOD_ID" id="GOOD_ID" value="<%=request.getParameter("GOOD_ID")%>">
							<button type="button" class="btn btn-qubico" onclick="submit_order();">提交订单</button>
							<button type="button" class="btn btn-qubico" onclick="back3();">返回</button>

						</div>
					</form>
				</div>
				</div>
				
			</div>
			
			<!-- 添加/修改地址 -->
			<div id="rule_add" style="display: none">
				<p class="text-center" id="subtitle"></p>
				<form id="form" method="post">
					<input type="hidden" name="OP" id="OP" /> <input type="hidden" name="USER_ID" id="USER_ID"> <input type="hidden" name="ADDRESS_ID" id="ADDRESS_ID">
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="USER_NAME" name="USER_NAME" placeholder="收货人姓名" type="text"> <span class="text-danger" style="display: none;">请按规范输入收货人姓名！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="USER_PHONE" name="USER_PHONE" placeholder="收货人手机" value="" type="text"> <span class="text-danger" style="display: none;">该项不能为空！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="USER_TEL" name="USER_TEL" placeholder="收货人电话" value="" type="text"> <span class="text-danger" style="display: none;">请输入正确的收货人电话！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="AREA_ID1" name="AREA_ID1" placeholder="地址所属省" value="" type="text"> <span class="text-danger" style="display: none;">请输入地址所在省！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="AREA_ID2" name="AREA_ID2" placeholder="地址所属市" value="" type="text"> <span class="text-danger" style="display: none;">请输入地址所在市！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="AREA_ID3" name="AREA_ID3" placeholder="地址所属区县" value="" type="text"> <span class="text-danger" style="display: none;">请输入地址所在区县！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="ADDRESS" name="ADDRESS" placeholder="详细地址" value="" type="text"> <span class="text-danger" style="display: none;">请输入详细地址！</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								是否为默认地址： <input type="radio" name="IS_DEFAULT" ID="DEFAULT_0"><label for="DEFAULT_0">否</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="IS_DEFAULT" ID="DEFAULT_1"><label for="DEFAULT_1">是</label>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-qubico" onclick="add();">保存</button>
							<button type="button" class="btn btn-qubico" onclick="back();">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<div>
	<input type="hidden" name="ORDER_ID" id="ORDER_ID" value="" />
<button id="PAY_MODAL" style="display:none;" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
	开始演示模态框
</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" style="display:none;" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					在线支付
				</h4>
			</div>
			<div class="modal-body">
				模拟在线支付，未进行真正支付
			</div>
			<div class="modal-footer">
				
				<button id="CLOSE_MODAL" type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" onclick="do_pay();" class="btn btn-primary">
					确定支付
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</div>
	<script>
	
		var obj,obj2, rows = [];
			
	/*************************地址列表的内容***********************************/
		<%String address_json = (String) request.getAttribute("address_list");
			if (address_json == "") {
				address_json = "[]";
			}%>
		
		
		var rows2=[],rows_x=<%=address_json%>;
		var t_row2=[];
		
		if(rows_x.length>0){
			t_row2=rows_x[0];
			
			rows2[0]=t_row2;
		}
			
		
		function add(){
			obj.submit();
		}
		function back(){
			//rule_add.style.display = "none";
			//rule_list.style.display = "";
			window.location.reload();
		}
		function back3(){
			 window.history.go(-1);
		}
		
		function back2(){
			test.innerHTML = tplt(rows2);
			//obj.init_object(rows2);
			s0.style.display = "";
			s1.style.display = "none";
			s2.style.display = "none";
			sub_list1.style.display = "";
			sub_list2.style.display = "";
		}
		
		function show_list(){
			
			
			
			
			
			sub_list1.style.display = "";
			sub_list2.style.display = "none";
			//var rows_n=[];
			//obj.init_object(rows_n);
			
			//s1.style.display = "";
			s2.style.display = "";
			test.innerHTML = tplt(rows_x);
			
		}
		
		//取消其它地址的class
		function select(ADDRESS_ID){
			
			
			var oA = document.getElementById('ADDR_ID');
			oA.value=ADDRESS_ID;
			
			//选中这一地址
			var arr = [];
			for(var i = 0; i < rows_x.length; i ++){
				if(rows_x[i].ADDRESS_ID == parseInt(ADDRESS_ID)){
					arr[0] = rows_x[i];
					break;
				}
					
			}
			//arr[0].IS_DEFAULT=1;
			test.innerHTML = tplt(arr);
			s2.style.display="none";
			s1.style.display="none";
			s0.style.display="";
			
			rule_add.style.display = "none";
			rule_list.style.display = "";
			sub_list1.style.display = "";
			sub_list2.style.display = "";
			//alert("地址选定成功");
			
			var d_addr = document.getElementById('D_ADDR_ID');
			var v =	d_addr.value;
			
			if(v!=ADDRESS_ID&&v!="undefined"){
				if(v!="D_ADDR_ID"){
					var p_id=['addr_class_',v].join("");
					var op = document.getElementById(p_id);
					if(op!=null)
						op.className="panel panel-default";
				}
				var n_id=['addr_class_',ADDRESS_ID].join("");
				var on = document.getElementById(n_id);
				on.className="panel panel-primary";
				var r_id=["OR_ADDRESS_ID_",ADDRESS_ID].join("");
				var oR=document.getElementById(r_id);
				oR.checked="true";
			}	
			d_addr.value=ADDRESS_ID;
		}
		
		
		function sel_invoice_type(type){				
			var oType = document.getElementById('INVOICE_TYPE');
			oType.value=type;
		}
		
		function sel_pay_way(way){				
			var oPay = document.getElementById('PAYMENT_WAYS');
			oPay.value=way;
		}
		
		
		
		
		function add_address(){
			subtitle.innerHTML = "添加地址";
			var rows = {"OP":1};
			obj.init_object(rows);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function edit(ADDRESS_ID,USER_NAME,AREA_ID1,AREA_ID2,AREA_ID3,ADDRESS,USER_PHONE,USER_TEL,IS_DEFAULT){
			subtitle.innerHTML = "修改地址";
			var rows = {"OP":2, "ADDRESS_ID":ADDRESS_ID, "USER_NAME":USER_NAME, "AREA_ID1":AREA_ID1, "AREA_ID2":AREA_ID2, "AREA_ID3":AREA_ID3, "ADDRESS":ADDRESS, "USER_PHONE":USER_PHONE, "USER_TEL":USER_TEL, "IS_DEFAULT":IS_DEFAULT};
			obj.init_object(rows);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}

		function cb(arr,msg){	
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}	
			//location.reload();
			
			
			window.location.reload();
		}
		
		function d_cb(arr,msg){	
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}

			window.location.reload();
		}
		
		function del(ADDRESS_ID){
			if(confirm("确定要删除该地址吗？")){
				var params = ["OP=3&ADDRESS_ID=", ADDRESS_ID].join("");
				ims_submit_ajax("<%=path%>/customer/user_address", params, d_cb);
			}
		}

		Handlebars.registerHelper("help_style", function(IS_DEFAULT,ADDRESS_ID) {			
			
			if (IS_DEFAULT == 1){
				var d_class = document.getElementById('D_ADDR_ID');
				d_class.value=ADDRESS_ID;
				var d_val = document.getElementById('ADDR_ID');
				d_val.value=ADDRESS_ID;
				return "primary";
			}
			else
				return "default";
		});
		
		Handlebars.registerHelper("help_money", function(sm,pm) {							
			var tm=parseFloat(sm)+parseFloat(pm);
			//var oT = document.getElementById('ORDER_TOTAL_MONEY');
			//oT.value=tm;
			return tm;
		});
		
		Handlebars.registerHelper("help_default_radio", function (IS_DEFAULT) {
			if(IS_DEFAULT==1)
				return "checked";
			
		});
		
		Handlebars.registerHelper("help_default", function (IS_DEFAULT) {
			if(IS_DEFAULT==1)
				return "默认地址";
			
		});
		
		
		
		//var arr2 = [["0","请选择产品价格单位"],["1","美元"],["2","人民币"],["3","林吉特"]];
		Handlebars.registerHelper("help_currency", function (CURRENCY) {
			if(CURRENCY==1)
				return ["$",].join("");
			else if(CURRENCY==2)
				return ["¥"].join("");	
			else if(CURRENCY==3)
				return ["RM"].join("");	
		});
		
		Handlebars.registerHelper("help_discount", function (DISCOUNT_MONEY) {
			if(DISCOUNT_MONEY==undefined||DISCOUNT_MONEY==null||DISCOUNT_MONEY=="")
				return "无";
			else if(DISCOUNT_MONE>0)
				return DISCOUNT_MONE;	
		});
		
		

		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if (rows2.length==0) {
			test.innerHTML = "暂无数据！";
			s2.style.display="";
		} else {
			
			test.innerHTML = tplt(rows2);
			s0.style.display="";
			s1.style.display="none";
		}

		function ims_show_warn() {
			var object = this;
			object.nextElementSibling.style.display = "";
			setTimeout(function () { 
				object.nextElementSibling.style.display = "none";
			}, 3000);
			//this.nextElementSibling.style.display = "";
		}

		function verify3() {
			var o = validator.is_name(validator.trim(this.value),
					this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none"
					: this.nextElementSibling.style.display = "";
		}
		function verify4() {
			var o = validator.is_phone(validator.trim(this.value),
					this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none"
					: this.nextElementSibling.style.display = "";
		}
		function verify5() {
			var o = validator.is_telephone(validator.trim(this.value),
					this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none"
					: this.nextElementSibling.style.display = "";
		}

		var cols = [ {
			id : "form",
			submit : {
				postform : submitWindow,
				url : [ "<%=path%>","/customer/user_address" ].join(""),
				refresh : cb,
				
			}
		}, {
			id : "OP",
			init : {}
		}, {
			id : "ADDRESS_ID",
			init : {}
		}, {
			id : "USER_ID",
			init : {}
		}, {
			id : "USER_NAME",
			tips : {
				action : ims_show_warn,
				text : "收货人姓名"
			},
			init : {
				minLength : 1,
				maxLength : 20,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify3
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, {
			id : "USER_PHONE",
			tips : {
				action : ims_show_warn,
				text : "收货人手机号码"
			},
			init : {
				minLength : 1,
				maxLength : 11,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify4
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_phone
			}
		}, {
			id : "USER_TEL",
			tips : {
				action : ims_show_warn,
				text : "收货人电话"
			},
			init : {
				minLength : 6,
				maxLength : 20,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify5
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_telephone
			}
		}, {
			id : "AREA_ID1",
			tips : {
				action : ims_show_warn,
				text : "地址所在省"
			},
			init : {
				minLength : 1,
				maxLength : 20,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify3
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, {
			id : "AREA_ID2",
			tips : {
				action : ims_show_warn,
				text : "地址所在市"
			},
			init : {
				minLength : 1,
				maxLength : 20,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify3
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, {
			id : "AREA_ID3",
			tips : {
				action : ims_show_warn,
				text : "地址所在区县"
			},
			init : {
				minLength : 1,
				maxLength : 20,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify3
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, {
			id : "ADDRESS",
			tips : {
				action : ims_show_warn,
				text : "详细地址"
			},
			init : {
				minLength : 1,
				maxLength : 50,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify3
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, {
			id : "DEFAULT_0",
			init : {
				name : "IS_DEFAULT",
				value : 0,
				vdefault : true
			}
		}, {
			id : "DEFAULT_1",
			init : {
				name : "IS_DEFAULT",
				value : 1
			}
		}, ];

		

		/*************************商品的内容***********************************/
	<%String product_detail_json = (String) request.getAttribute("product_detail");
			if (product_detail_json == "") {
				product_detail_json = "\"no_data\"";
			}%>
		var rows3 =<%=product_detail_json%>;
		
		
		function buy_num_plus(price){
			var oN = document.getElementById('BUY_NUM');
			var oM=document.getElementById('PRODUCT_TOTAL_MONEY');
			
			var oS=document.getElementById('SHIPPING_PRICE');
			var oD=document.getElementById('DISCOUNT_MONEY');
			var oT=document.getElementById('ORDER_TOTAL_MONEY');
			
			var oN_SPAN = document.getElementById('BUY_NUM_SPAN');
			var oM_SPAN=document.getElementById('PRODUCT_TOTAL_MONEY_SPAN');	
			var oT_SPAN=document.getElementById('ORDER_TOTAL_MONEY_SPAN');
			
			//商品数量+1
			var r=Number(oN.value);
			var nr=r+1;
			oN.value=nr;
			oN_SPAN.innerHTML=nr;
			
			//商品总价				
			var nm=nr*price;		
			oM.value=nm;
			oM_SPAN.innerHTML=nm;
			
			//订单总价
			var s=Number(oS.value);
			var d=Number(oD.value);
			var t=nm+s-d;
			oT.value=t;
			oT_SPAN.innerHTML=t;
			
			
		}
		
		function buy_num_sub(price){
			var oN = document.getElementById('BUY_NUM');
			var oM=document.getElementById('PRODUCT_TOTAL_MONEY');
			
			var oS=document.getElementById('SHIPPING_PRICE');
			var oD=document.getElementById('DISCOUNT_MONEY');
			var oT=document.getElementById('ORDER_TOTAL_MONEY');
			
			var oN_SPAN = document.getElementById('BUY_NUM_SPAN');
			var oM_SPAN=document.getElementById('PRODUCT_TOTAL_MONEY_SPAN');	
			var oT_SPAN=document.getElementById('ORDER_TOTAL_MONEY_SPAN');
				

			//商品数量+1
			var r=Number(oN.value);
			if(r<=1){
				return false;				
			}	
			var nr=r-1;
			oN.value=nr;
			oN_SPAN.innerHTML=nr;
			
			//商品总价				
			var nm=nr*price;		
			oM.value=nm;
			oM_SPAN.innerHTML=nm;
			
			//订单总价
			var s=Number(oS.value);
			var d=Number(oD.value);
			var t=nm+s-d;
			oT.value=t;	
			oT_SPAN.innerHTML=t;
		}
		
		function help_image_id_cb(data,msg){
			
			if(data.length>0){
				//just one image
				var root='<%=path%>';
				var image_id=data[0].IMAGE_ID;
				var url=root+data[0].IMAGE_PATH;
				var image='<img src=\"'+url+'\" class=\"img-thumbnail\" alt=\"产品图片\" width=\"100\" height=\"100\">';
				var id="IMAGE_ID_"+image_id;
				var oimg=document.getElementById(id);
				oimg.innerHTML=image;				
			}else{
				if(msg.show_msg){
					alert(msg.msg_content);
				}
			}
								
		}
		
		Handlebars.registerHelper("help_image_id", function (IMAGE_ID) {
			var image=""; 
			var params = ["method=product_get_image_url&IMAGE_ID=", IMAGE_ID].join("");
			ims_submit_ajax("<%=path%>/customer/product", params,image = help_image_id_cb);

		});
		
		
		

		var tplo2 = document.getElementById('tpl2');
		var tplt2 = Handlebars.compile(tplo2.innerHTML);
		var test2 = document.getElementById('test2');
		if (rows3.length==0) {
			test2.innerHTML = "暂无数据！";
		} else {
			test2.innerHTML = tplt2(rows3);
		}

		function verify_buy_num() {
			var o = validator.is_integer(validator.trim(this.value),
					this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none"
					: this.nextElementSibling.style.display = "";																
						
			var oN = document.getElementById('BUY_NUM');
			var oM=document.getElementById('PRODUCT_TOTAL_MONEY');
			
			var oS=document.getElementById('SHIPPING_PRICE');
			var oD=document.getElementById('DISCOUNT_MONEY');
			var oT=document.getElementById('ORDER_TOTAL_MONEY');
			
			var oN_SPAN = document.getElementById('BUY_NUM_SPAN');
			var oM_SPAN=document.getElementById('PRODUCT_TOTAL_MONEY_SPAN');	
			var oT_SPAN=document.getElementById('ORDER_TOTAL_MONEY_SPAN');
			
			var oP = document.getElementById('PRODUCT_PRICE');		
			var price = Number(oP.value);
			
			//商品数量
			var r=Number(oN.value);					
			oN_SPAN.innerHTML=r;
			
			//商品总价				
			var nm=r*price;		
			oM.value=nm;
			oM_SPAN.innerHTML=nm;
			
			//订单总价
			var s=Number(oS.value);
			var d=Number(oD.value);
			var t=nm+s-d;
			oT.value=t;
			oT_SPAN.innerHTML=t;
		}
		
		function pay_cb(data, msg){
			var o=document.getElementById("CLOSE_MODAL");
			o.click();
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			
			setTimeout(function() {
				window.location.href="<%=path%>/customer/orders_manage?method=order_list";
			}, __warn_period);
			
		}
		
		function do_pay(){
			var o = document.getElementById('ORDER_ID');
			var ORDER_ID=o.value;
			var params = ["method=pay_order&ORDER_ID=", ORDER_ID].join("");
			ims_submit_ajax("<%=path%>/customer/buy", params,pay_cb);
		}
		
		function buy_cb(data, msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			if(data.ORDER_ID){
				var o = document.getElementById('ORDER_ID');
				o.value=data.ORDER_ID;
			}
			
			setTimeout(function() {
				var o=document.getElementById("PAY_MODAL");
				var o2=document.getElementById("myModal");
				o2.style.display="";
				o.click();
				//window.location.href="<%=path%>/customer/orders_manage?method=order_list";
			}, __warn_period);
			
		}
		
		function submit_order(){
			obj2.submit();
		}
		
		function verify_is_name(){
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		
		function checked_verify2(){
			
			var o = IS_INVOICE.checked;
			o ? IS_INVOICE.value = "1" : IS_INVOICE.value = "0";
			o ? invoice_section.style.display = "" : invoice_section.style.display = "none";
		}
		
		function check_form(){
			if(BUY_NUM.value <= "0"){
				
				show_msg_warn("emsg_id","购买数量不正确！");
				return false;
			}		
			if(ADDR_ID.value==""){
				
				show_msg_warn("emsg_id","请选择收货地址！");
				return false;				
			}
			if(PAYMENT_WAYS.value==""){
				
				show_msg_warn("emsg_id","请选择支付方式！");
				return false;	
			}
			form2.method.value="submit_order";
			return true;
		}

		var cols2 = [ {
			id : "form2",
			submit : {
				integrate:check_form,
				postform : submitWindow,
				url : [ "<%=path%>", "/customer/buy" ].join(""),
				refresh : buy_cb,
			}
		}, {
			id : "method",
			init : {}
		}, {
			id : "ROLE",
			init : {}
		}, {
			id : "INVOICE_TYPE",
			init : {}
		}, {
			id : "ADDR_ID",
			init : {}
		}, {
			id : "SALES_PERSON_ID",
			init : {}
		}, {
			id : "PAYMENT_WAYS",
			init : {}
		}, {
			id : "GOOD_ID",
			init : {}
		}, {
			id : "PRODUCT_PRICE",
			init : {}
		}, {
			id : "PRODUCT_TOTAL_MONEY",
			init : {}
		},{
			id : "DISCOUNT_MONEY",
			init : {}
		}, {
			id : "SHIPPING_PRICE",
			init : {}
		}, {
			id : "ORDER_TOTAL_MONEY",
			init : {}
		}, {
			id : "BUY_NUM",
			tips : {
				action : ims_show_warn,
				text : "购买数量"
			},
			init : {
				minLength : 1,
				maxLength : 10,
				size : 10,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify_buy_num
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "IS_INVOICE",
			init : {
				multiple : false,
				vdefault : 0,
				value : 0
			},
			event : {
				id : "onclick",
				fn : checked_verify2
			}
		}, {

			id : "INVOICE_CLIENT",
			tips : {
				action : ims_show_warn,
				text : "发票抬头"
			},
			init : {
				minLength : 1,
				maxLength : 6,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify_is_name
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_name
			}
		}, ];

		/***************public***************/
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);

			obj2 = new iform(cols2, document.forms[1]);
			obj2.init_object(rows);
		}
	</script>

	<!-- product private -->
	<script src="<%=path%>/js/jquery"></script>
	<script src="<%=path%>/js/jqueryval"></script>
	<script src="<%=path%>/js/bootstrap.js"></script>
	<script src="<%=path%>/js/jquery_003.js"></script>
	<script src="<%=path%>/js/jquery_006.js"></script>
	<script src="<%=path%>/js/waypoints.js"></script>
	<%@include file="yb_base_foot_include.jsp"%>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="<%=path%>/flatui/dist/js/vendor/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<%=path%>/flatui/dist/js/flat-ui.min.js"></script>
	

</body>
</html>