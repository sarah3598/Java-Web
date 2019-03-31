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
<title>Staff Dashboard</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>申请管理</h2>
		<!-- 规则列表 -->
		<div id="rule_list" class="table-responsive">
			<table class="table table-striped" style="border: 1px solid #eee;">
			  <thead>
				<tr>
				  <th>人员名称</th>
				  <th>级别</th>
				  <th>申请内容</th>
				  <th>操作</th>
				</tr>
			  </thead>
			  <tbody id="test">
			  <script id="tpl" type="text/x-handlebars-template">
				{{#each this}}
				<tr>
				  <td>{{USERNAME}}</td>
				  <td>{{help_role ROLE}}</td>
				  <td>{{help_flag FLAG}}</td>
				  <td>
					<button type="button" class="btn btn-sm btn-success" onclick="approve('{{USER_ID}}','{{FLAG}}');">批准</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_refuse('{{USER_ID}}');">拒绝</button>
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
		<!-- 选择培训团队 -->
		<div id="rule_add" style="display:none">
			<p class="text-center">选择入职培训团队</p>
			<form id="form" method="post">
				<input type="hidden" name="OP" id="OP"/>
				<input type="hidden" name="USER_ID" id="USER_ID"/>
				<input type="hidden" name="FLAG" id="FLAG"/>
				<div class="form-horizontal smalldiv">
					<div class="form-group">
						<div class="col-md-12">
							<select class="form-control largcontrol" id="TRAIN_ID" name="TRAIN_ID"></select>
							<span class="text-danger" style="display:none;" id="show_train">请选择培训团队</span>
						</div>
					</div>
					<div class="form-group text-center">
						<button type="button" class="btn btn-qubico" onclick="add();">批准</button> 
						<button type="button" class="btn btn-qubico" onclick="back();">返回</button> 
					</div>
				</div>
			</form>  
		</div>
    </div>
	<script>	
		<%String apply_json = (String) request.getAttribute("apply_list");
		if (apply_json == "") {
			apply_json = "\"no_data\"";
		}%>
		<%String train_team_list_json = (String) request.getAttribute("train_team_list");
		if (train_team_list_json == "") {
			train_team_list_json = "[]";
		}%>
		
		var obj, rows = [];
		var rows2 =<%=apply_json%>;
		var rows3 = <%=train_team_list_json%>;
		
		/* rows2 = [{"USER_ID":"10001","USERNAME":"jpeng","ROLE":0,"FLAG":1},
				{"USER_ID":"10002","USERNAME":"zpeng","ROLE":1,"FLAG":2},
				{"USER_ID":"10003","USERNAME":"wpeng","ROLE":2,"FLAG":3}]; */
		/* var rows3 = [["1","独家代理A"],["2","独家代理B"],["100037","组长A"],["100038","组长B"]]; */
		
		
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
		
		function show_warn(object){
			object.style.display = "";
			setTimeout(function () { 
				object.style.display = "none";
			}, 3000);
		}
				
		function add(){
			if(!TRAIN_ID.value)
				show_warn(show_train);
			else{
				if(confirm("批准后，该人员将进入培训阶段。")){
					var params = ["OP=1&USER_ID=", USER_ID.value, "&FLAG=", FLAG.value, "&TRAIN_ID=", TRAIN_ID.value].join("");
					ims_submit_ajax("<%=path%>/company/apply_admin", params, cb);	
				}
			}	
		}
		
		function add_train(USER_ID,FLAG){
			var rows = {"USER_ID":USER_ID,"FLAG":FLAG};
			obj.init_object(rows);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function approve(USER_ID,FLAG){
			if(FLAG == 1)
				add_train(USER_ID,FLAG);
			else if(FLAG == 2){
				if(confirm("批准将该人员调整为独家代理？")){
					var params = ["OP=1&USER_ID=", USER_ID, "&FLAG=", FLAG].join("");
					ims_submit_ajax("<%=path%>/company/apply_admin", params, cb);	
				}
			}
			else if(FLAG == 3){
				if(confirm("批准后，该独家代理将进入培训阶段。")){
					var params = ["OP=1&USER_ID=", USER_ID, "&FLAG=", FLAG].join("");
					ims_submit_ajax("<%=path%>/company/apply_admin", params, cb);	
				}
			}
		}
		
		function del_refuse(USER_ID){
			if(confirm("确定要拒绝该申请吗？")){
				var params = ["OP=2&USER_ID=", USER_ID].join("");
				ims_submit_ajax("<%=path%>/company/apply_admin", params, cb);	
			}
		}

		Handlebars.registerHelper("help_flag", function (FLAG) {
			if (FLAG == 1)
				return "申请入职";
			else if (FLAG == 2)
				return "申请成为独家代理";
			else if (FLAG == 3)
				return "申请成为组长";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_role", function (ROLE) {
			if (ROLE == 0)
				return "入职培训";
			else if (ROLE == 1)
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
		//test.innerHTML = tplt(rows2);
		if(rows2.length==0){
			test.innerHTML="暂无数据！";
		}
		else{
			test.innerHTML = tplt(rows2);
		}

		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/apply_admin?current_page="+current_page+"&page_size="+page_size;
		}
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/apply_admin?page_size="+page_size;
		});
		
		var cols = [
			{id:"form"},
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
			{id:"OP", init:{}},	
			{id:"USER_ID", init:{}},
			{id:"FLAG", init:{}},
			{id:"TRAIN_ID",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:rows3}},
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
    </script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>