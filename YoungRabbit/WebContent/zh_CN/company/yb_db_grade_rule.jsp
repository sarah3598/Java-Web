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
<title>Staff Dashboard</title>
</head>

<body>

	<%@include file="yb_db_nav.jsp"%>

	<div class="container-fluid">

		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<!--<p style="color:red;">级别规则|奖金设定|销售名单|团队管理|培训管理</p>-->
				<h1 class="page-header">规则管理</h1>
				<!--页面导航按钮-->
				<ul id="WEBSITE_CONTROL" class="nav nav-tabs">
					<!--<li class="active"><a href="<%=path%>/company/grade_rule_manage?method=grade_rule_list"> 评级规则管理 </a></li>-->
					<li class="active"><a href="<%=path%>/company/basic_rule_manage"> 级别规则 </a></li>
					<li><a href="<%=path%>/company/year_end_bonus"> 奖金设定 </a></li>
				
				</ul>
				<!--页面导航按钮-->
				<!-- 规则列表  -->
				<div id="rule_list" class="table-responsive" style="border: 1px solid #eee;">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>人员级别</th>
								<th>评级周期<p style="color:red">应改为：最低个人销售</p></th>
								<th>招聘人数<p style="color:red">应改为：最低群销售</p></th>
								<th>群销量（RM）<p style="color:red">应改为：代理费</p></th>
								<th>操作
									<button type="button" class="btn btn-xs btn-primary" onclick="add_rule();">添加规则</button>
								</th>
							</tr>
						</thead>
						<tbody id="test">
							<script id="tpl" type="text/x-handlebars-template">
			  {{#each this}}
                <tr>
                  <td>{{help_role ROLE}}</td>
                  <td>{{help_period PERIOD}}</td>
                  <td>{{RECRUIT}}</td>
                  <td>{{SALES}}</td>
                  <td>
					<button type="button" class="btn btn-sm btn-success" onclick="upd_rule('{{ROLE}}','{{PERIOD}}','{{RECRUIT}}','{{SALES}}');">修改</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_rule('{{ROLE}}');">删除</button>
				  </td>
                </tr>
				{{/each}}
			  </script>
						</tbody>
					</table>
				</div>
				<!-- 添加规则 -->
				<div id="rule_add" style="display: none">
					<p class="text-center" id="btitle"></p>
					<form id="form" method="post">
						<input type="hidden" name="OP" id="OP" />
						<div class="form-horizontal smalldiv">
							<div class="form-group">
								<div class="col-md-12">人员级别<p style="color:red">应改为：选择级别</p>
									<select class="form-control largcontrol" id="ROLE" name="ROLE"></select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">评级周期<p style="color:red">应改为：最低个人销售</p>
									<select class="form-control largcontrol" id="PERIOD" name="PERIOD"></select>
								</div>
							</div>			

							<div class="form-group">
								<div class="col-md-12">群销量（RM）<p style="color:red">应改为：最低个人销售</p>
									<input class="form-control largcontrol" id="SALES" name="SALES" type="text"> 
									<span class="text-danger" style="display: none;">群销量为0～9个数字</span>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-md-12">招聘人数<p style="color:red">应改为：代理费</p>
									<input class="form-control largcontrol" id="RECRUIT" name="RECRUIT" type="text"> 
									<span class="text-danger" style="display: none;">招聘人数为0～3个数字</span>
								</div>
							</div>
							<div class="form-group text-center">
								<!-- 	<input value="确定" class="btn btn-qubico" type="submit">-->
								<button type="button" class="btn btn-qubico" onclick="add();">确定</button>
								<button type="button" class="btn btn-qubico" onclick="back();">返回</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		<%String grade_rule_json = (String) request.getAttribute("grade_rule_list");
			if (grade_rule_json == "") {
				grade_rule_json = "\"no_data\"";
			}%>
		var obj, rows =<%=grade_rule_json%>;
					 
		function add(){
			obj.submit();
		}
		function back(){
			rule_add.style.display = "none";
			rule_list.style.display = "";
		}
		
		function cb(arr,msg){	
			if(msg.show_msg){
				//alert(msg.msg_content);
			}						
			test.innerHTML = tplt(arr);
			rule_add.style.display = "none";
			rule_list.style.display = "";
		}
			
	
		function add_rule(){
			btitle.innerHTML = "添加评级规则";
			var row = {"OP":1, "RECRUIT":"", "SALES":""};
			obj.init_object(row);
			rule_list.style.display = "none";
			rule_add.style.display = "";
			//return false;
		}
		
		function upd_rule(ROLE, PERIOD, RECRUIT, SALES){
			btitle.innerHTML = "修改评级规则";
			var row = {"OP":2, "ROLE":ROLE, "PERIOD":PERIOD, "RECRUIT":RECRUIT, "SALES":SALES};
			obj.init_object(row);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function del_rule(ROLE){
			if(confirm("确定要删除该用户吗？")){
				var params = ["OP=3&ROLE=", ROLE].join("");
				ims_submit_ajax("<%=path%>/company/grade_rule_manage", params, cb);	
			}
		}
		
		Handlebars.registerHelper("help_role", function (ROLE) {
			if (ROLE == 1)
				return "实习销售";
			else if (ROLE == 2)
				return "销售员";
			else if (ROLE == 4)
				return "初级经理";
			else if (ROLE == 8)
				return "经理";
			else if (ROLE == 16)
				return "高级经理";
			else if (ROLE == 32)
				return "副总裁助理";
			else if (ROLE == 64)
				return "副总裁";
			else if (ROLE == 128)
				return "高级副总裁";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_period", function (PERIOD) {
			if (PERIOD)
				return [PERIOD, "个月"].join("");
			else 
				return "";
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows=="no_data"){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows);
		}
		
		
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify(){
			if(this.value){
				var o = validator.is_integer(validator.trim(this.value), this.minLength, this.maxLength);
				o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
			}
		}
		
		function check_form(){			
			if(form.RECRUIT.value && form.SALES.value)
				return false;
			else	
				return true;
		}
		
		var arr1 = [["1","实习销售"],["2","销售员"],["4","初级经理"],["8","经理"],["16","高级经理"],["32","副总裁助理"],["64","副总裁"],["128","高级副总裁"]];
		var arr2 = [["1","1个月"],["2","2个月"],["3","3个月"],["4","4个月"],["5","5个月"],["6","6个月"],["7","7个月"],["8","8个月"],["9","9个月"],["10","10个月"],["11","11个月"],["12","12个月"]];
		var cols = [
		{id:"form", 
			submit: {
				integrate:check_form,
				postform: submitWindow,
				url: "<%=path%>/company/grade_rule_manage",
				refresh : cb,
			}
		}, {
			id : "OP",
			init : {}
		}, //OP是标志位 1:添加  2:修改
		{
			id : "ROLE",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : arr1
			}
		}, {
			id : "PERIOD",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : arr2
			}
		}, {
			id : "RECRUIT",
			tips : {
				action : ims_show_warn,
				text : "招聘人数"
			},
			init : {
				minLength : 0,
				maxLength : 3,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		}, {
			id : "SALES",
			tips : {
				action : ims_show_warn,
				text : "群销量"
			},
			init : {
				minLength : 0,
				maxLength : 9,
				size : 32,
				allowNull : true
			},
			event : {
				id : "onkeyup",
				fn : verify
			},
			submit : {
				formatter : validator.trim,
				check : validator.is_integer
			}
		} ];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object([]);
		}
	</script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>