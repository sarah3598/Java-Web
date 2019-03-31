<%@page import="com.csh.servlet.LoginControlPublicServlet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String USER_ID=(String)LoginControlPublicServlet.getSessionKeyValue(request, response, "USER_ID");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="yb_base_head_include.jsp"%>
</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<%@include file="yb_base_nav.jsp" %>

	<section id="contact" class="white-bg padding-top-bottom">
		<div class="container">
			<div class="smalldiv">
				<h1 class="section-title">请填写您的联络信息</h1>
				<form id="form" method="post">
					<input type="hidden" name="USER_ID" id="USER_ID" value="<%=USER_ID%>" />
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">电话：
								<input class="form-control largcontrol" id="PHONE" name="PHONE"
									placeholder="电话" type="text"> <span class="text-danger"
									style="display: none;">请输入正确的电话号码。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">微信号：
								<input class="form-control largcontrol" id="WECHAT"
									name="WECHAT" placeholder="微信号" type="text"> <span
									class="text-danger" style="display: none;">请输入正确的微信号。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">面书：
								<input class="form-control largcontrol" id="FACEBOOK"
									name="FACEBOOK" placeholder="FACEBOOK" type="text"> <span
									class="text-danger" style="display: none;">Facebook格式有误。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">地址：
								<input class="form-control largcontrol" id="ADDRESS"
									name="ADDRESS" placeholder="地址" value="" type="text"> <span
									class="text-danger" style="display: none;">请输入正确的地址信息。</span>
							</div>
						</div>
						<div id="emsg_id"></div>
						<div class="form-group text-center">
							<input value="下一步" class="btn btn-qubico" type="submit">
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<script>
		var obj, rows = [];
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify(){
			var o = validator.is_phone(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify1(){
			var o = validator.is_wechat(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify3(){
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function check_form(){
				return true;
		}
		function next_register_step(data,msg){
			
			if(data.url==undefined||data.url==""||data.url==null){
				if(msg.show_msg){
					show_msg_warn("emsg_id",msg.msg_content);
				}
				//window.location.href="<%=path%>/zh_CN/customer/yb_home_page.jsp";
			}else{
				window.location.href=data.url;
			}
			return false;
		}
		var cols = [
			{id:"form", 
				submit: {
					integrate:check_form,
					postform: submitWindow,
					url: '<%=path%>/company/register?step=2',
					refresh: next_register_step,
				}},	
			{id:"USER_ID", init:{}},
			{id:"PHONE", tips:{action:ims_show_warn, text:"电话"},
				init:{minLength:7, maxLength:15, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_phone}},
			{id:"WECHAT", tips:{action:ims_show_warn, text:"微信"},
				init:{minLength:5, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify1},
				submit:{formatter:validator.trim, check:validator.is_wechat}},
			{id:"FACEBOOK", tips:{action:ims_show_warn, text:"Facebook"},
				init:{minLength:5, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify1},
				submit:{formatter:validator.trim, check:validator.is_wechat}},
			{id:"ADDRESS", tips:{action:ims_show_warn, text:"地址"},
				init:{minLength:6, maxLength:100, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify3},
				submit:{formatter:validator.trim, check:validator.is_name}},
		
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
	</script>
	<%@include file="yb_base_foot_include.jsp"%>
</body>
</html>