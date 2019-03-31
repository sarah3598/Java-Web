<%@page import="com.csh.servlet.LoginControlPublicServlet"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
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
	<section id="bank" class="white-bg padding-top-bottom">
		<div class="container">
			<div class="smalldiv">
				<h1 class="section-title">请填写您的银行信息</h1>
				<form id="form" method="post">
					<input type="hidden" name="USER_ID" id="USER_ID" value="<%=USER_ID%>"/>
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">银行账户姓名：
								<input class="form-control largcontrol" id="BANK_USER" name="BANK_USER" type="text">
								<span class="text-danger" style="display:none;">请输入银行账户的姓名</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">银行卡号：
								<input class="form-control largcontrol" id="BANK_CARD" name="BANK_CARD" placeholder="银行卡号" type="text">
								<span class="text-danger" style="display:none;">银行卡号有误</span>
							</div>
						</div>

						<div class="form-group"">
							<div class="col-md-12">银行名称：
								<input class="form-control largcontrol" id="BANK_NAME" name="BANK_NAME" placeholder="银行名称" type="text">
								<span class="text-danger" style="display:none;">请输入银行名称</span>
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-12">账户名称：
								<input class="form-control largcontrol" id="ACCOUNT_NAME" name="ACCOUNT_NAME" placeholder="账户名称" value="" type="text">
								<span class="text-danger" style="display:none;">请输入账户名称</span>
							</div>
						</div>
						
						<div id="emsg_id"></div>
						<div class="form-group text-center">
							<input value="完成" class="btn btn-qubico" type="submit">
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
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify1(){
			var o = validator.is_idcard(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function check_form(){
			return true;
		}
		function next_step(data,msg){	
			
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
					url: '<%=path%>/company/register?step=3',
					refresh: next_step,
				}},	
			{id:"USER_ID", init:{}},
			{id:"BANK_USER", tips:{action:ims_show_warn, text:"持卡人"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"BANK_CARD", tips:{action:ims_show_warn, text:"银行卡号"},
				init:{minLength:4, maxLength:19, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify1},
				submit:{formatter:validator.trim, check:validator.is_idcard}},
			{id:"BANK_NAME", tips:{action:ims_show_warn, text:"开户银行"},
				init:{minLength:4, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"ACCOUNT_NAME", tips:{action:ims_show_warn, text:"账户名称"},
				init:{minLength:6, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
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