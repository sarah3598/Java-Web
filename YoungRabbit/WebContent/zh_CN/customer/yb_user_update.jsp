<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String user_id=(String) request.getAttribute("user_id");
%>

<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>

<%@include file="yb_base_head_include.jsp"%>
<title>个人信息</title>
<script src="<%=path%>/js/iform.js"></script>
</head>

<body>


	<%@include file="yb_base_nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header"></h1>
				<div class="container">

					<!-- 添加管理员 -->
					<div id="admin_add">
						<p class="text-center">请认真修改以下内容</p>
						<form id="form" method="post" >
							<input type="hidden" name="OP" id="OP" /> 
							<input type="hidden" name="USER_ID" id="USER_ID">
							<div class="form-horizontal smalldiv">
								<div class="form-group">
								
									<div class="col-md-12">
										名称： <input class="form-control largcontrol" id="FIRST_NAME"
											name="FIRST_NAME" type="text"> <span
											class="text-danger" style="display: none;">名称为1～20个汉字、字母或数字。</span>
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-12">
										姓氏： <input class="form-control largcontrol" id="LAST_NAME"
											name="LAST_NAME" type="text"> <span
											class="text-danger" style="display: none;">姓氏为1～20个汉字、字母或数字。</span>
									</div>
								</div>
								

								<div class="form-group">
									<div class="col-md-12">
										邮箱： <input class="form-control largcontrol" id="EMAIL"
											name="EMAIL" type="text"> <span class="text-danger"
											style="display: none;">电子邮箱格式有误。</span>
									</div>
								</div>

								<!--<div class="form-group">
									<div class="col-md-12">
										原始密码： <input class="form-control largcontrol" id="PWD"
											name="PWD"> <span class="text-danger"
											style="display: none;">请输入原始密码。</span>
									</div>
								</div>-->

								<div class="form-group">
									<div class="col-md-12">
										新密码： <input class="form-control largcontrol" id="PWD_NEW"
											name="PWD_NEW" type="password"> <span class="text-danger"
											style="display: none;">密码由6～20个字母、数字、下划线组成。</span>
									</div>
								</div>

								<div class="form-group">
									<div class="col-md-12">
										确认密码：
										<input class="form-control largcontrol" id="PWD_VERIFY"
											name="PWD_VERIFY" type="password"> <span class="text-danger"
											style="display: none;">密码由6～20个字母、数字、下划线组成。</span> <span
											id="pv" class="text-danger" style="display: none;">密码和验证密码不一致。</span>
									</div>
								</div>
								</div>
								
								<div id="emsg_id"></div>
								<div class="form-group text-center">
									<!-- 	<input value="确定" class="btn btn-qubico" type="submit">-->
									<button type="button" class="btn btn-qubico" onclick="update();">确定</button>
									<!--<button type="button" class="btn btn-qubico" onclick="back();">返回</button>-->
								</div>
							</div>
						</form>
					</div>
					<!--管理员结束-->
				</div>
			</div>
		</div>
	</div>
	<script>
	  
	   <%String user_json = (String) request.getAttribute("user_list");
	    if (user_json == "") {
	    	user_json = "\"no_data\"";
	   }%>
	   var rows = [];
       var obj, rows1 =<%=user_json%>;

       function update(){
			obj.submit();
		}	
		
		function cb(arr,msg){	
			
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			
			//window.location.reload();
		} 
		
				
		var user_id = <%=user_id%>;
		function update_user(user_id){
			var arr = {};
			for(var i = 0; i < rows1.length; i ++){
				if(rows1[i].USER_ID == parseInt(user_id))
					arr = rows1[i];
			}
			
			var row = {"OP":1,"USER_ID":arr.USER_ID,"FIRST_NAME":arr.FIRST_NAME,"LAST_NAME":arr.LAST_NAME,"ROLE":arr.ROLE,"EMAIL":arr.EMAIL,"PWD":arr.PWD,"PHONE":arr.PHONE,"WECHAT":arr.WECHAT,"FACEBOOK":arr.FACEBOOK, "BANK_USER":arr.BANK_USER,"BANK_CARD":arr.BANK_CARD,"BANK_NAME":arr.BANK_NAME,"ACCOUNT_NAME":arr.ACCOUNT_NAME};
			obj.init_object(row);
			EMAIL.readOnly=true;
			//PWD.readOnly=true;
		}
			
		
		
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify_name(){
			if(this.value){			
			var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}}
		function verify_email(){
			if(this.value){
			var o = validator.is_email(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}}
		function verify_pwd(){
			if(this.value){
			var o = validator.is_password(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}}
		function verify_phone(){
			if(this.value){
			var o = validator.is_phone(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}}
		function verify_wechat(){
			if(this.value){
			var o = validator.is_wechat(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}}	
		function verify_bank_user(){
			if(this.value){
				var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
				o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}}
		function verify_bank_card(){
			if(this.value){
				var o = validator.is_idcard(validator.trim(this.value), this.minLength, this.maxLength);
				o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}}
		function verify_bank_name(){
			if(this.value){
				var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
				o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}}
		function verify_account_name(){
			if(this.value){
				var o = validator.is_name(validator.trim(this.value), this.minLength, this.maxLength);
				o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}}
	
		function check_form(){
			if(PWD_NEW.value == PWD_VERIFY.value)
				return true;
			else{
				pv.style.display = "";
				setTimeout(function () { 
					pv.style.display = "none";
				}, 3000);
				return false;
			}
		}
		
		var arr1 = [["0","培训人员"],["1","实习销售"],["2","销售员"],["4","初级经理"],["8","经理"],["16","高级经理"],["32","副总裁助理"],["64","副总裁"],["128","高级副总裁"],["256","代理商"],["512","批发商"],["1024","管理员"],["2048","超级管理员"]];
		var cols = [
			{id:"form", 
				submit: {
					integrate:check_form,
					postform: submitWindow,
					url: "<%=path%>/company/user_update",
					refresh : cb,
			}}, 
		    {id : "OP",init : {}},    //OP是标志位 1:添加  2:修改 3:删除  4:查询
		    {id : "USER_ID",init : {}},
		    {id:"FIRST_NAME", tips:{action:ims_show_warn, text:"名称"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_name},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"LAST_NAME", tips:{action:ims_show_warn, text:"姓氏"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_name},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"ROLE",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr1}},
			{id:"EMAIL", tips:{action:ims_show_warn, text:"电子邮箱"},
				init:{minLength:1, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_email},
				submit:{formatter:validator.trim, check:validator.is_email}},
			{id:"PWD", tips:{action:ims_show_warn, text:"密码"},
				init:{minLength:6, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_pwd},
				submit:{formatter:validator.trim, check:validator.is_password}},
			{id:"PWD_NEW", tips:{action:ims_show_warn, text:"新密码"},
				init:{minLength:6, maxLength:20, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify_pwd},
				submit:{formatter:validator.trim, check:validator.is_password}},
			{id:"PWD_VERIFY", tips:{action:ims_show_warn, text:"确认密码"},
				init:{minLength:6, maxLength:20, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify_pwd},
				submit:{formatter:validator.trim, check:validator.is_password}},
			{id:"PHONE", tips:{action:ims_show_warn, text:"电话"},
				init:{minLength:7, maxLength:15, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_phone},
				submit:{formatter:validator.trim, check:validator.is_phone}},
			{id:"WECHAT", tips:{action:ims_show_warn, text:"微信"},
				init:{minLength:5, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_wechat},
				submit:{formatter:validator.trim, check:validator.is_wechat}},
			{id:"FACEBOOK", tips:{action:ims_show_warn, text:"Facebook"},
				init:{minLength:5, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_wechat},
				submit:{formatter:validator.trim, check:validator.is_wechat}},
			{id:"BANK_USER", tips:{action:ims_show_warn, text:"持卡人"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_bank_user},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"BANK_CARD", tips:{action:ims_show_warn, text:"银行卡号"},
				init:{minLength:4, maxLength:19, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_bank_card},
				submit:{formatter:validator.trim, check:validator.is_idcard}},
			{id:"BANK_NAME", tips:{action:ims_show_warn, text:"开户银行"},
				init:{minLength:4, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_bank_name},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"ACCOUNT_NAME", tips:{action:ims_show_warn, text:"账户名称"},
				init:{minLength:6, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify_account_name},
				submit:{formatter:validator.trim, check:validator.is_name}},
			 ];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			/* obj.init_object([]); */
			update_user(user_id);
			
		}
	
		/* var tpl_admino = document.getElementById('tpl_admin');
		var tpl_admint = Handlebars.compile(tpl_admino.innerHTML);
		var admin = document.getElementById('admin');
		admin.innerHTML = tpl_admint(rows);
		
		if(rows=="no_data"){
			admin.innerHTML="暂无数据！";
		}else{
			admin.innerHTML = tpl_admint(rows);
		} */
    </script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=path%>/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>