<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Pagination paging = (Pagination) request.getAttribute("page");
%>

<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>
<%@include file="yb_db_head_include.jsp"%>
<title>Dashboard</title>
<script src="<%=path%>/js/iform.js"></script>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>规则管理</h2>
		<ul class="nav nav-tabs">
			<!--<li><a href="<%=path%>/company/grade_rule_manage?method=grade_rule_list"> 评级规则管理 </a></li>-->
			<li><a href="<%=path%>/company/basic_rule_manage"> 级别规则 </a></li>
			<li class="active"><a href="<%=path%>/company/year_end_bonus"> 奖金设定 </a></li>
		</ul>
		<div id="rule_list" class="table-responsive">
			<table class="table table-striped" style="border: 1px solid #eee;">
			  <thead>
				<tr>
				  <th>类型</th>
				  <th>级别</th>				  						                
				  <th>目标销量</th>
				  <th>奖金</th>
				  <th>操作 <button type="button" class="btn btn-xs btn-primary" onclick="add_rule();">添加条款</button></th>
				</tr>
			  </thead>
			  <tbody id="test">
			  <script id="tpl" type="text/x-handlebars-template">
				{{#each this}}
				<tr>
				  <td>{{help_type TYPE}}</td>
				  <td>{{help_role ROLE}}</td>
				  <td>{{VALID}}</td>
				  <td>{{BONUS}}</td>
				  <td>
					<button type="button" class="btn btn-sm btn-success" onclick="upd_rule('{{BONUS_ID}}');">修改</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_rule('{{BONUS_ID}}');">删除</button>
				  </td>
				</tr>
				{{/each}}
			  </script>
			  </tbody>
			</table>
			  <!-- 分页开始 -->
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><select style="width: 150px;" class="form-control largcontrol" id="PAGE_SIZE" name="PAGE_SIZE"></select></li>
					<li><span aria-hidden="true"> 当前是第：<%=paging.getCurrentPage()%>页|共<%=paging.getTotalPage()%>页
					</span></li>
					<li><a style="cursor: pointer" onclick="pagination(1,<%=paging.getPageSize()%>);return false;">首页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getFirstPage()%>,<%=paging.getPageSize()%>);return false;">上一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getDownPage()%>,<%=paging.getPageSize()%>);return false;">下一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getTotalPage()%>,<%=paging.getPageSize()%>);return false;">尾页</a></li>
				</ul>
			</nav>
			<!-- 分页结束 -->	  
		</div>
		<!-- 添加规则 -->
		<div id="rule_add" style="display:none">
			<p class="text-center" id="btitle"></p>
			<form id="form" method="post">
				<input type="hidden" name="OP" id="OP"/>
				<input type="hidden" name="TYPE" id="TYPE" value="1"/>
				<input type="hidden" name="BONUS_ID" id="BONUS_ID"/>
				<div class="form-horizontal smalldiv">
					<div class="form-group">
						<div class="col-md-12">人员级别
							<select class="form-control largcontrol" id="ROLE" name="ROLE"></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">目标销量（RM/月）：
							<input class="form-control largcontrol" id="VALID" name="VALID" type="text">
							<span class="text-danger" style="display:none;">1～8个数字</span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">奖金（RM）：
							<input class="form-control largcontrol" id="BONUS" name="BONUS" type="text">
							<span class="text-danger" style="display:none;">1～8个数字</span>
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
		var obj, rows = [];
		<%String bonus_json = (String) request.getAttribute("bonus_list");
		if (bonus_json == "") {
			bonus_json = "\"no_data\"";
		}%>
        var  rows1 =<%=bonus_json%>;		
		
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
			var row = {"OP":1,"ROLE":1,"BONUS":"","VALID":""};
			
			obj.init_object(row);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function upd_rule(BONUS_ID){
			btitle.innerHTML = "修改条款";
			var arr;
			for(var i = 0; i < rows1.length; i ++){
				if(rows1[i].BONUS_ID == parseInt(BONUS_ID))
					arr = rows1[i];
			}
			
			var row = {"OP":2,"TYPE":arr.TYPE,"BONUS_ID":BONUS_ID,"ROLE":arr.ROLE,"BONUS":arr.BONUS,"VALID":arr.VALID};
			obj.init_object(row);
//			change();
			rule_list.style.display = "none";	
			rule_add.style.display = "";
		}
		
		function del_rule(BONUS_ID){
			if(confirm("确定要删除该条款吗？")){
				var params = ["OP=3&BONUS_ID=", BONUS_ID].join("");
				ims_submit_ajax("<%=path%>/company/year_end_bonus", params, cb);	
			}
		}

		Handlebars.registerHelper("help_type", function (TYPE) {
			if (TYPE == 1)
				return "额外奖金";
			else if (TYPE == 2)
				return "年尾花红";
			else
				return "";
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
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/year_end_bonus?current_page="+current_page+"&page_size="+page_size;
		}
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/year_end_bonus?page_size="+page_size;
		});
		
		var arr1 = [["1","销售代理"],["2","独家销售代理"],["4","组长"],["8","经理"],["16","副总裁"]];
//		var arr2 = [["1","额外奖金"],["2","年尾花红"]];
		var cols = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: "<%=path%>/company/year_end_bonus",
					refresh: cb,
				}},	
			{
				id : "PAGE_SIZE",
				init : {
					multiple : false,
					vdefault : <%=paging.getPageSize()%>,
					value : 0,
					text : 1,
					ds : arr0
				}
			}, 	
			{id:"OP", init:{}},					//OP是标志位 1:添加  2:修改
			{id:"BONUS_ID", init:{}},
			{id:"TYPE", init:{}},
			{id:"ROLE",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr1}},
			{id:"BONUS", tips:{action:ims_show_warn, text:"底薪+奖金"},
				init:{minLength:1, maxLength:8, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_integer}},	
			{id:"VALID", tips:{action:ims_show_warn, text:"目标销量"},
				init:{minLength:1, maxLength:8, size:32, allowNull:true},
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