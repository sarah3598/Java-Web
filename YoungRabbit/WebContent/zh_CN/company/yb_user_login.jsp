<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	String token = "";
	Enumeration paramNames = request.getParameterNames();
	while (paramNames.hasMoreElements()) {
		String paramName = (String) paramNames.nextElement();
		String paramValue = request.getParameter(paramName);

		if (paramName.equals("token")) {
			token += paramValue;
		} else {
			token += "&" + paramName;
			token += "=" + paramValue;
		}
	}
%>

<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="yb_base_head_include.jsp"%>
</head>
<body data-spy="scroll" data-target="#main-nav" data-offset="400">
	<%@include file="yb_base_nav.jsp"%>

	<section id="register" class="white-bg padding-top-bottom">
		<div class="container">
			<div class="smalldiv">
				<h1 class="section-title">员工登录</h1>
				<%--<p class="text-center">
					还没有账户&nbsp;<a href="<%=path%>/zh_CN/company/yb_register_step_1.jsp" title="注册">马上注册</a>
				</p>--%>
				<span id="hidden_token" style="display: none;"></span> <input type="hidden" id="token" name="token" value="">

				<form id="form" method="post">
					<div class="form-horizontal">

						<div class="form-group">
							<div class="col-md-12">
								电子邮箱： <input class="form-control largcontrol" id="EMAIL" name="EMAIL" placeholder="Email" type="text"> <span class="text-danger" style="display: none;">电子邮箱格式有误。</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">
								密码： <input class="form-control largcontrol" id="PWD" name="PWD" placeholder="Password" value="" type="password"> <span class="text-danger" style="display: none;">密码由6～20个字母、数字、下划线组成。</span>
							</div>
						</div>

						<div class="form-group text-center">
							<input value="登录" class="btn btn-qubico" type="submit">
						</div>
						<div id="emsg_id"></div>
					</div>
				</form>
			</div>
		</div>
	</section>
	<script>
		var obj, rows = [];
		function login(){
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
		if(this.value==""||this.value==null){
			return
		}
		else{
			var o = validator.is_email(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}
		}
		function verify3(){
			var o = validator.is_password(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		
		function responseRedict(data){
			var o = validator.is_password(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function next_step(data,msg){
			if(data.url==undefined||data.url==""||data.url==null){
				//window.location.reload();
				if(msg.show_msg){
					show_msg_warn("emsg_id",msg.msg_content);
				}
			}else{
				window.location.href=data.url;
			}
			return false;
		}
		
		function check_form(){
			//var oT= document.getElementById('hidden_token');
			//token.value=oT.innerHTML;
			return true;
			
		}
		
		var cols = [
			{id:"form", 
				submit: {
					integrate:check_form,
					postform: submitWindow,
					url: '<%=path%>/company/login',
					refresh : next_step,
			}
		}, {
			id : "token",
			init : {}
		}, {
			id : "EMAIL",
			tips : {
				action : ims_show_warn,
				text : "电子邮箱"
			},
			init : {
				minLength : 1,
				maxLength : 50,
				size : 32,
				allowNull : false
			},
			event : {
				id : "onkeyup",
				fn : verify2
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_email
			}
		}, {
			id : "PWD",
			tips : {
				action : ims_show_warn,
				text : "密码"
			}
		}, ];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
	</script>
	<%@include file="yb_base_foot_include.jsp"%>
</body>
</html>