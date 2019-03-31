<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<html lang="zh-CN">
<%@include file="yb_base_head_include.jsp"%>
</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
    <%@include file="yb_base_nav.jsp" %>
    
	<section id="register" class="white-bg padding-top-bottom">
		<div class="container">
			<div class="smalldiv">
				<h1 class="section-title">创建一个账号</h1>
				<p class="text-center">已经有账号?  <a href="<%=path%>/zh_CN/customer/yb_user_login.jsp">直接登录</a></p>
				<div id="emsg_id"></div>
				<form id="form" method="post">
					<input type="hidden" name="ROLE" id="ROLE" value="9999"/>
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="FIRST_NAME" name="FIRST_NAME" placeholder="名字" type="text">
								<span class="text-danger" style="display:none;">名称为1～20个汉字、字母或数字。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="LAST_NAME" name="LAST_NAME" placeholder="姓氏" type="text">
								<span class="text-danger" style="display:none;">姓氏为1～20个汉字、字母或数字。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="EMAIL" name="EMAIL" placeholder="邮箱" type="text">
								<span class="text-danger" style="display:none;">电子邮箱格式有误。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="PWD" name="PWD" placeholder="密码" value="" type="password">
								<span class="text-danger" style="display:none;">密码由6～20个字母、数字、下划线组成。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								<input class="form-control largcontrol" id="PWD_VERIFY" name="PWD_VERIFY" placeholder="确认密码" type="password">
								<span class="text-danger" style="display:none;">密码由6～20个字母、数字、下划线组成。</span>
								<span id="pv" class="text-danger" style="display:none;">密码和验证密码不一致。</span>
							</div>
						</div>
						<div class="form-group text-center">
							<input value="创建" class="btn btn-qubico" onclick="add();">
						</div>
					</div>
				</form>            
				
			</div>
		</div>
	</section>	
	
	<script>
		var obj, rows = [];
		function add(){
			obj.submit();
		}
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify(){
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify2(){
			var o = validator.is_email(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify3(){
			var o = validator.is_password(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function check_form(){
			if(PWD.value == PWD_VERIFY.value)
				return true;
			else{
				pv.style.display = "";
				setTimeout(function () { 
					pv.style.display = "none";
				}, 3000);
				return false;
			}
		}
		
		function next_step(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function(data) {
				
				window.location.href="<%=path%>/zh_CN/customer/yb_user_login.jsp";
				
			}, __warn_period);
		}
		
		function fcb(robj){
			show_msg_warn("emsg_id",robj.emsg);
		}
		
		var cols = [
			{id:"form", 
				submit: {
					integrate:check_form,
					postform: submitWindow,
					url: '<%=path%>/customer/register?step=1',
					refresh: next_step,
					prompt:fcb,
					
				}},	
			{id:"ROLE", init:{}},
			{id:"FIRST_NAME", tips:{action:ims_show_warn, text:"名称"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"LAST_NAME", tips:{action:ims_show_warn, text:"姓氏"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"EMAIL", tips:{action:ims_show_warn, text:"电子邮箱"},
				init:{minLength:1, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify2},
				submit:{formatter:validator.trim, check:validator.is_email}},
			{id:"PWD", tips:{action:ims_show_warn, text:"密码"},
				init:{minLength:6, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify3},
				submit:{formatter:validator.trim, check:validator.is_password}},
			{id:"PWD_VERIFY", tips:{action:ims_show_warn, text:"验证密码"},
				init:{minLength:6, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify3},
				submit:{formatter:validator.trim, check:validator.is_password}},
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
	</script>
    <%@include file="yb_base_foot_include.jsp"%>
	</body>
</html>