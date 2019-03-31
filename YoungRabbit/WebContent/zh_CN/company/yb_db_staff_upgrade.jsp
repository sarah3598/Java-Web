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
		<h2>用户管理</h2>  
		<ul class="nav nav-tabs">
			<li><a href="<%=path%>/company/sales_manage" >销售管理 </a></li>
			<li><a href="<%=path%>/company/admin_manage" > 管理员 </a></li>
			<li><a href="<%=path%>/customer/users_manage"> 顾客管理</a></li>
			<!--<li><a href="<%=path%>/company/recruiter_manage">招聘管理</a></li>-->
			<li class="active"><a href="<%=path%>/company/staff_upgrade?method=staff_list"> 员工升级 </a></li>
		</ul>
		<!--页面导航按钮-->
		<!-- 人员列表 -->
		<div id="rule_list" class="table-responsive">
			<table class="table table-striped" style="border: 1px solid #eee;">
			  <thead>
				<tr>
				  <th>姓名</th>
				  <th>本月级别</th>
				  <th>已评级</th>
				  <th>上月时间</th>
				  <th>个人销量(RM)</th>
				  <th>群销量(RM)</th>
				  <th>操作</th>
				</tr>
			  </thead>
			  <tbody id="test">
			  <script id="tpl" type="text/x-handlebars-template">
				{{#each this}}
				<tr>
				  <td>{{USERNAME}}</td>
				  <td>{{help_role ROLE}}</td>
				  <td>{{help_role GROLE}}</td>
				  <td>{{MONTH}}</td>
				  <td>{{PERSONAL_SALE}}</td>
				  <td>{{GROUP_SALE}}</td>
				  <td><button type="button" class="btn btn-sm btn-success" onclick="grade('{{USER_ID}}','{{USERNAME}}','{{ROLE}}','{{GROLE}}','{{REMARK}}');">评级</button></td>
				</tr>
				{{/each}}
			  </script>
			  </tbody>
			</table>
			<!-- 分页开始 -->
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><select style="width: 150px;" class="form-control largcontrol" id="PAGE_SIZE" name="PAGE_SIZE"></select></li>
					<li><span aria-hidden="true"> 当前是第：<span id="CURRENT_PAGE"><%=paging.getCurrentPage()%></span>页|共<%=paging.getTotalPage()%>页
					</span></li>
					<li><a style="cursor: pointer" onclick="pagination(1,<%=paging.getPageSize()%>);return false;">首页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getFirstPage()%>,<%=paging.getPageSize()%>);return false;">上一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getDownPage()%>,<%=paging.getPageSize()%>);return false;">下一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getTotalPage()%>,<%=paging.getPageSize()%>);return false;">尾页</a></li>
				</ul>
			</nav>
			<!-- 分页结束 -->
		</div>
		<!-- 升级 -->
		<div id="rule_add" style="display:none">
			<p class="text-center">评级</p>
			<form id="form" method="post">
				<input type="hidden" name="USER_ID" id="USER_ID"/>
				<input type="hidden" name="ROLE" id="ROLE"/>
				<div class="form-horizontal smalldiv">
					<div class="form-group">
						<div class="col-md-12">
							姓名：<input class="form-control largcontrol" id="USERNAME" type="text" disabled>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							当前级别：<select class="form-control largcontrol" id="ROLE1" name="ROLE1" disabled></select>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							拟评级别：<select class="form-control largcontrol" id="NEW_ROLE" name="NEW_ROLE"></select>
							<span class="text-danger" style="display:none;" id="amsg">您只能在销售代理或者独家销售代理之间评级</span>
							<span class="text-danger" style="display:none;" id="mmsg">您只能在组长、经理和副总裁之间评级</span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-12">
							备注：<input class="form-control largcontrol" id="REMARK" name="REMARK" type="text">
							<span class="text-danger" style="display:none;">备注不多于200个字</span>
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
		<%String staff_json = (String) request.getAttribute("staff_list");
		if (staff_json == "") {
			staff_json = "\"no_data\"";
		}%>
		var obj, rows = [], rows1 = <%=staff_json%>;	
//		var obj, rows = [];
		/*var rows1 = [{"USER_ID":1,"USERNAME":"Jianpeng","ROLE":"1","GROLE":"1","MONTH":"201704","PERSONAL_SALE":"3000","GROUP_SALE":"0"},
					   {"USER_ID":2,"USERNAME":"Wangpeng","ROLE":"2","GROLE":"2","MONTH":"201704","PERSONAL_SALE":"3000","GROUP_SALE":"0"},
					   {"USER_ID":3,"USERNAME":"Lipeng","ROLE":"4","GROLE":"4","MONTH":"201704","PERSONAL_SALE":"3000","GROUP_SALE":"3000"},
					   {"USER_ID":4,"USERNAME":"Zhaopeng","ROLE":"8","GROLE":"16","MONTH":"201704","PERSONAL_SALE":"3000","GROUP_SALE":"3000"}];*/
		
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
	
		function grade(USER_ID,NAME,ROLE,GROLE,REMARK){
			var row = {"USER_ID":USER_ID, "USERNAME":NAME, "ROLE":ROLE, "ROLE1":ROLE, "NEW_ROLE":GROLE, "REMARK":REMARK};
			obj.init_object(row);
			rule_list.style.display = "none";
			rule_add.style.display = "";
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
		
		function show_warn(object){
			object.style.display = "";
			setTimeout(function () { 
				object.style.display = "none";
			}, 3000);
		}
		
		function verify(){
			var object = this;
			if(object.value){
				var o = validator.is_length(validator.trim(object.value), object.minLength, object.maxLength);
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
		
		function add(){
			obj.submit();
		}
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/staff_upgrade?method=staff_list&current_page="+current_page+"&page_size="+page_size;
		}
		
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/staff_upgrade?method=staff_list&page_size="+page_size;
		});
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		
		var arr = [["1","销售代理"],["2","独家销售代理"],["4","组长"],["8","经理"],["16","副总裁"]];
		
		//销售代理和独家代理可互换; 组长及以上可升降
		function check_form(){
			var r = ROLE.value, nr = NEW_ROLE.value;
			if(r >=1 && r <=2){
				if(nr >=1 && nr <=2)
					return true;
				else{
					show_warn(amsg);
					return false;
				}
			}
			else if(r >=4 && r <=16){
				if(nr >=4 && nr <=16)
					return true;
				else{
					show_warn(mmsg);
					return false;
				}
			}
			else
				return false;
		}
		
		//销售代理只能转换成独家销售代理; 独家销售代理可以转换成销售代理，也可升级成其他
		var cols = [
			{id:"form", 
				submit: {
					integrate:check_form,
					postform: submitWindow,
					url: "<%=path%>/company/staff_upgrade?method=staff_upgrade",
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
			{id:"USER_ID", init:{}},
			{id:"ROLE", init:{}},
			{id:"USERNAME", init:{}},
			{id:"ROLE1",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr}},
			{id:"NEW_ROLE",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr}},				
			{id:"REMARK", tips:{action:ims_show_warn, text:"备注"},
				init:{minLength:0, maxLength:200, size:32, allowNull:true},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_length}}
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
</script>
<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>
