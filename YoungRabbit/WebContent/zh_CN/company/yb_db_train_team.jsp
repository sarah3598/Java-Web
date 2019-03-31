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
<title>sales management</title>
<script src="<%=path%>/js/iform.js"></script>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<div id="rule_list" class="table-responsive">
			<h2>培训管理</h2>
			<table class="table table-striped" style="border: 1px solid #eee;">
			  <thead>
				<tr>
				  <th>团队名称</th>
				  <th>培训类型</th>
				  <th>可培训人数</th>
				  <th>队长级别</th>
				  <th>操作 <button type="button" class="btn btn-xs btn-primary" onclick="add_train();">添加入职培训团队</button></th>
				</tr>
			  </thead>
			  <tbody id="test">
			  <script id="tpl" type="text/x-handlebars-template">
				{{#each this}}
				<tr>
				  <td><{{help_tag TYPE}} href="{{help_optname TYPE}}('{{TRAIN_ID}}','{{TEAM_ID}}','{{ROLE}}','{{USERNAME}}');">{{USERNAME}}</{{help_tag TYPE}}></td>
				  <td>{{help_type TYPE}}</td>
				  <td>{{COUNT}}</td>
				  <td>{{help_role ROLE}}</td>
				  <td>
					<button type="button" class="btn btn-sm btn-success" {{help_flag FLAG TYPE}} onclick="upd_train('{{TRAIN_ID}}','{{TEAM_ID}}','{{TYPE}}','{{COUNT}}','{{ROLE}}');">{{help_txt TYPE FLAG }}</button>
					<button type="button" class="btn btn-sm btn-danger" {{help_flag2 FLAG TYPE}} onclick="del_team('{{TRAIN_ID}}','{{TEAM_ID}}','{{TYPE}}','{{ROLE}}');">完成培训</button>
				  </td>
				</tr>
				{{/each}}
			  </script>
			  </tbody>
			</table>
		</div>
		<!-- 添加培训人员 -->
		<div id="rule_add" style="display:none">
			<p class="text-center">添加/修改入职培训团队</p>
			<form id="form" method="post">
				<input type="hidden" name="OP" id="OP"/>
				<input type="hidden" name="TRAIN_ID" id="TRAIN_ID"/>
				<input type="hidden" name="TYPE" id="TYPE" value="1"/>
				<div class="form-horizontal smalldiv">
					<div class="form-group">
						<div class="col-md-12">培训团队名称
							<select class="form-control largcontrol" id="TEAM_ID" name="TEAM_ID"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">可培训人数
							<input class="form-control largcontrol" id="COUNT" name="COUNT" type="text">
							<span class="text-danger" style="display:none;">1～3个数字</span>
						</div>
					</div>
					<div class="form-group text-center">
						<button type="button" class="btn btn-qubico" onclick="add();">确定</button> 
						<button type="button" class="btn btn-qubico" onclick="back();">返回</button> 
					</div>
				</div>
			</form>  
		</div>
	</div>
	<script>
		<%String train_team_list_json = (String) request.getAttribute("train_team_list");
			if (train_team_list_json == "") {
				train_team_list_json = "[]";
			}%>
		
		<%String sales_and_manager_list_json = (String) request.getAttribute("sales_and_manager_list");
			if (sales_and_manager_list_json == "") {
				sales_and_manager_list_json = "[]";
			}%>

		
		var obj, rows = [];
		
		var rows2 = <%=train_team_list_json%>;
	 
		var rows3 = <%=sales_and_manager_list_json%>;
	 
		function back(){
			rule_add.style.display = "none";
			rule_list.style.display = "";
		}
		function cb(){
			window.location.reload();
			/*
			test.innerHTML = tplt(JSON.parse(arr));
			train_team_add.style.display = "none";
			train_team_list.style.display = "";
			*/
		}
		function cb(data,msg){
			ims_show_warn(msg.msg_content,"");
			//alert(msg.msg_content);
			test.innerHTML = tplt(data);
		}
		
		function member(TRAIN_ID,TEAM_ID,TEAM_ROLE,USERNAME){
			window.location.href = ["<%=path%>/company/recruiter_manage?TRAIN_ID=", TRAIN_ID, "&PARENT_ID=", TEAM_ID, "&PARENT_ROLE=", TEAM_ROLE,"&USERNAME=",USERNAME].join("");
			//window.location.href = "<%=path%>/zh_CN/company/yb_db_recruiter_admin.jsp";
		}
		
		function donothing(TRAIN_ID,TEAM_ID,TEAM_ROLE,USERNAME){
			return true;
		}
		
		function add_train(){
			var rows = {"OP":1};
			obj.init_object(rows);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		

		function upd_train(TRAIN_ID,TEAM_ID,TYPE,COUNT,ROLE){
			if(TYPE == 1){		//修改入职培训信息
				var rows = {"OP":2, "TEAM_ID":TEAM_ID,"TRAIN_ID":TRAIN_ID, "COUNT":COUNT};
				obj.init_object(rows);
				rule_list.style.display = "none";
				rule_add.style.display = "";
			}
			else if(TYPE ==4){	//独家代理开始培训
				var params = ["method=do_train&TAG=1&TRAIN_ID=",TRAIN_ID, "&TEAM_ID=", TEAM_ID, "&ROLE=", ROLE].join("");	//TAG=1 独家代理开始培训
				ims_submit_ajax("<%=path%>/company/team_manage", params, cb);
			}
		}	
		
		function close_train(TEAM_ID){
			if(confirm("确定要关闭该培训团队吗？")){
				var params = ["method=train_team_close&STATUS=2&TEAM_ID=", TEAM_ID].join("");
				ims_submit_ajax("<%=path%>/company/team_manage", params, cb);		
			}
		}
		
		
		function del_train(TEAM_ID){
			if(confirm("确定要删除该培训团队吗？")){
				var params = ["method=train_team_delete&STATUS=4&TEAM_ID=", TEAM_ID].join("");
				ims_submit_ajax("<%=path%>/company/team_manage", params, cb);	
			}
		}
		
		function fcb(){
			//alert("团队中仍有成员，请处理后再行操作！");
		}
		
		function del_team(TRAIN_ID,TEAM_ID,TYPE){
			if(TYPE == 1){		//完成入职培训信息
				if(confirm("确定该团队已完成培训？")){
					var params = ["method=train_team_delete&TRAIN_ID=", TRAIN_ID].join("");
					ims_submit_ajax("<%=path%>/company/team_manage", params, cb, fcb);	
				}
			}
			else if(TYPE ==4){	//完成独家代理培训
				var params = ["method=do_train&TAG=2&TRAIN_ID=",TRAIN_ID, "&TEAM_ID=", TEAM_ID].join("");	//TAG=2 完成独家代理培训
				ims_submit_ajax("<%=path%>/company/team_manage", params, cb);
			}	
		}
		
		Handlebars.registerHelper("help_txt", function (TYPE,FLAG) {
			if (TYPE == 1)
				return "修改";
			else if (TYPE == 4){
				if (FLAG == 4)
					return "培训中";
				else
					return "开始培训";
			}else
				return "";
		});
		
		Handlebars.registerHelper("help_flag", function (FLAG,TYPE) {
			if (FLAG == 4&&TYPE==4)
				return "disabled";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_flag2", function (FLAG,TYPE) {
			if (FLAG == 3&&TYPE==4)
				return "disabled";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_type", function (TYPE) {
			if (TYPE == 1)
				return "入职培训";
			else if (TYPE == 2)
				return "销售代理培训";
			else if (TYPE == 4)
				return "独家代理培训";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_optname", function (TYPE) {
			if (TYPE == 1)
				return "javascript:member";
			else 
				return "javascript:donothing";
		});
		
		Handlebars.registerHelper("help_tag", function (TYPE) {
			if (TYPE == 1)
				return "a";
			else 
				return "label";
		});
		
		Handlebars.registerHelper("help_role", function (ROLE) {
			if (ROLE == 1)
				return "销售代理";
			else if (ROLE == 2)
				return "独家销售代理";
			else if (ROLE == 4)
				return "组长";
			else if (ROLE == 8)
				return "经理";
			else if (ROLE == 16)
				return "副总裁";
			else
				return "";
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows2.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows2);
		}
		
		
		function add(){
			if(OP.value == 2){
				if(confirm("确定要修改该培训团队吗？")){
					obj.submit();	
				}
			}
			else
				obj.submit();
		}
		
		function add_cb(data,msg){
			ims_show_warn(msg.msg_content,"");
			setTimeout(function () { 
				window.location.reload();	
			}, 3000);
				
				
		}
		
		function verify(){
			var object = this;
			if(object.value){
				var o = validator.is_integer(validator.trim(object.value), object.minLength, object.maxLength);
				if(o)
					object.nextElementSibling.style.display = "none";
				else{
					object.nextElementSibling.style.display = "";
					setTimeout(function () { 
						object.nextElementSibling.style.display = "none";
					}, 3000);
				}
			}
		}
		
		function check_form(){
			if(TEAM_ID.value == P_TEAM_ID.value)
				cb();
			else
				return true;
		}

		var cols = [
			{id:"form", submit: {
				postform: submitWindow,
				url: '<%=path%>/company/team_manage?method=train_team_add',
						refresh : add_cb,
					}
				}, 
			{id:"OP", init:{}},					//OP是标志位 1:添加  2:修改			
			{id:"TRAIN_ID", init:{}},
			{id:"TYPE", init:{}},
			{id:"TEAM_ID",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:rows3}},
			{id:"COUNT", tips:{action:ims_show_warn, text:"可培训人数"},
				init:{minLength:1, maxLength:3, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_integer}}
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);
		}
	</script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>