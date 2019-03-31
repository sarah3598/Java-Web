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
<link rel="stylesheet" href="<%=path%>/css/bootstrap-select.css">
<title>Dashboard</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
<div class="container">
	<h2>规则管理</h2>
	<!--页面导航按钮-->
	<ul class="nav nav-tabs">
		<!--<li><a href="<%=path%>/company/grade_rule_manage?method=grade_rule_list"> 评级规则管理 </a></li>-->
		<li class="active"><a href="<%=path%>/company/basic_rule_manage"> 级别规则 </a></li>
		<li><a href="<%=path%>/company/year_end_bonus"> 奖金设定 </a></li>
	</ul>
	<!-- 规则列表 -->
	<div id="rule_list" class="table-responsive">
		<table class="table table-striped" style="border: 1px solid #eee;">
		  <thead>
			<tr>
			  <th>级别</th>
			  <th>个人最低销售</th>
			  <th>群体最低销售</th>
			  <th>操作 <button type="button" class="btn btn-xs btn-primary" onclick="add_rule();">添加条款</button></th>
			</tr>
		  </thead>
		  <tbody id="test">
		  <script id="tpl" type="text/x-handlebars-template">
			{{#each this}}
			<tr>
			  <td>{{help_role ROLE}}</td>
			  <td>{{PERSONAL_SALE}}</td>
			  <td>{{GROUP_SALE}}</td>
			  <td>
				<button type="button" class="btn btn-sm btn-success" onclick="upd_rule('{{ROLE}}');">修改</button>
				<button type="button" class="btn btn-sm btn-danger" onclick="del_rule('{{ROLE}}');">删除</button>
			  </td>
			</tr>
			{{/each}}
		  </script>
		  </tbody>
		</table>
	</div>
	<!-- 添加规则 -->
	<div id="rule_add" style="display:none">
		<p class="text-center" id="btitle"></p>
		<form id="form" method="post">
			<input type="hidden" name="OP" id="OP"/>
			<div class="form-horizontal smalldiv">
				<div class="form-group">
					<div class="col-md-12">人员级别
						<select class="form-control largcontrol" id="ROLE" name="ROLE"></select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">销售佣金：
						<input type="radio" name="SALE_BROKERAGE" ID="SALE_BROKERAGE_0"><label for="SALE_BROKERAGE_0">无</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="SALE_BROKERAGE" ID="SALE_BROKERAGE_1"><label for="SALE_BROKERAGE_1">有</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">覆盖佣金：
						<input type="radio" name="COVER_BROKERAGE" ID="COVER_BROKERAGE_0"><label for="COVER_BROKERAGE_0">无</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="COVER_BROKERAGE" ID="COVER_BROKERAGE_1"><label for="COVER_BROKERAGE_1">有</label>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">个人最低销售（RM）：
						<input class="form-control largcontrol" id="PERSONAL_SALE" name="PERSONAL_SALE" type="text">
						<span class="text-danger" style="display:none;">1～8个数字</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">群体最低销售（RM）：
						<input class="form-control largcontrol" id="GROUP_SALE" name="GROUP_SALE" type="text">
						<span class="text-danger" style="display:none;">1～8个数字</span>
					</div>
				</div>
							
				<div class="form-group text-center">
					<button type="button" class="btn btn-qubico" onclick="add();" id="ok">确定</button> 
					<button type="button" class="btn btn-qubico" onclick="back();">返回</button> 
				</div>
			</div>
		</form> 
	</div>
</div>
	<script>
		<%String basic_rule_json = (String) request.getAttribute("basic_rule_list");
		if (basic_rule_json == "") {
			basic_rule_json = "\"no_data\"";
		}%>
        var  rows1 =<%=basic_rule_json%>;

		var obj, rows = [];
		/*var	rows1 = [{"ROLE":16,"SALARY":5000,"SALE_BROKERAGE":1,"COVER_BROKERAGE":1,"PERSONAL_SALE":0,"GROUP_SALE":100000},
					{"ROLE":1,"SALARY":4000,"SALE_BROKERAGE":1,"COVER_BROKERAGE":1,"PERSONAL_SALE":0,"GROUP_SALE":80000},
					{"ROLE":8,"SALARY":3000,"SALE_BROKERAGE":1,"COVER_BROKERAGE":1,"PERSONAL_SALE":0,"GROUP_SALE":60000},
					{"ROLE":4,"SALARY":2000,"SALE_BROKERAGE":1,"COVER_BROKERAGE":1,"PERSONAL_SALE":0,"GROUP_SALE":20000},
					{"ROLE":2,"SALARY":1000,"SALE_BROKERAGE":1,"COVER_BROKERAGE":1,"PERSONAL_SALE":1000,"GROUP_SALE":0},
				   ];*/
	   function add(){
			obj.submit();
		}
		function back(){
			rule_add.style.display = "none";
			rule_list.style.display = "";
		}
		
		function cb(arr,msg){	
			if(msg.show_msg){
				alert(msg.msg_content);
			}			
			if(arr.length>0)
			     rows1=arr;
			test.innerHTML = tplt(arr);
			rule_add.style.display = "none";
			rule_list.style.display = "";
		}
		
	
		function add_rule(){
			btitle.innerHTML = "添加条款";
			var row = {"OP":1,"ROLE":1,"SALE_BROKERAGE":0,"COVER_BROKERAGE":0,"PERSONAL_SALE":"","GROUP_SALE":""};
			
			obj.init_object(row);
			rule_list.style.display = "none";
			ok.style.display = "";
			rule_add.style.display = "";
		}
		
		function upd_rule(ROLE){
			btitle.innerHTML = "修改条款";
			var arr;
			for(var i = 0; i < rows1.length; i ++){
				if(rows1[i].ROLE == parseInt(ROLE))
					arr = rows1[i];
			}
			
			var row = {"OP":2,"ROLE":arr.ROLE,"SALE_BROKERAGE":arr.SALE_BROKERAGE,"COVER_BROKERAGE":arr.COVER_BROKERAGE,"PERSONAL_SALE":arr.PERSONAL_SALE,"GROUP_SALE":arr.GROUP_SALE};
			
			obj.init_object(row);
			rule_list.style.display = "none";			
			ok.style.display = "";
			rule_add.style.display = "";
		}
			
		
		function del_rule(ROLE){
			if(confirm("确定要删除该条款吗？")){
				var params = ["OP=3&ROLE=", ROLE].join("");
				ims_submit_ajax("<%=path%>/company/basic_rule_manage", params, cb);	
			}
		}
		
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
		
		Handlebars.registerHelper("help_period", function (PERIOD) {
			if (PERIOD == 3)
				return "季度";
			else if (PERIOD == 6)
				return "半年";
			else if (PERIOD == 12)
				return "一年";
			else
				return "状态出错！";
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		test.innerHTML = tplt(rows1);
		
		function ims_show_warn(){
			var object = this;
			object.nextElementSibling.style.display = "";
			setTimeout(function () { 
				object.nextElementSibling.style.display = "none";
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
		function verify1(){
			var object = this;
			if(object.value){
				var o = validator.is_scope(validator.trim(object.value), object.minLength, object.maxLength);
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
						
		
		var arr1 = [["1","销售代理"],["2","独家销售代理"],["4","组长"],["8","经理"],["16","副总裁"]];
		var cols = [
			{id:"form", 
				submit: {
					integrate:true,
					postform: submitWindow,
					url: "<%=path%>/company/basic_rule_manage",
					refresh: cb,
				}},	
			{id:"OP", init:{}},					//OP是标志位 1:添加  2:修改
			{id:"ROLE",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr1}},
			{id:"SALE_BROKERAGE_0",
				init:{name:"SALE_BROKERAGE",value:0, vdefault:true}},
			{id:"SALE_BROKERAGE_1",
				init:{name:"SALE_BROKERAGE",value:1}},
			{id:"COVER_BROKERAGE_0",
				init:{name:"COVER_BROKERAGE",value:0, vdefault:true}},
			{id:"COVER_BROKERAGE_1",
				init:{name:"COVER_BROKERAGE",value:1}},
			{id:"PERSONAL_SALE", tips:{action:ims_show_warn, text:"个人最低销售"},
				init:{minLength:1, maxLength:8, size:32, value:0, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_integer}},
			{id:"GROUP_SALE", tips:{action:ims_show_warn, text:"群体最低销售"},
				init:{minLength:1, maxLength:8, size:32, value:0, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_integer}},			
		];
		
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
		
    </script>  	
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>