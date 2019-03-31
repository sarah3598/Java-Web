<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Pagination paging = (Pagination) request.getAttribute("page");
	String train_id=(String) request.getAttribute("train_id");
	String parent_id=(String) request.getAttribute("parent_id");
	String parent_role=(String) request.getAttribute("parent_role");
	String user_name=(String) request.getAttribute("USERNAME");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="yb_db_head_include.jsp"%>
<%@include file="yb_file_input_include.jsp"%>
<title>recruiter admin</title>
</head>
<body>
	<%@include file="yb_db_nav.jsp"%>
	<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<div id="list">				
			<h2><button type='button' style='margin-right:20px;' class='btn btn-sm btn-success' onclick='goback();'>返回</button><%=user_name %>培训团队管理</h2>										
			<div id="emsg_id"></div>
			<div id="rule_list" class="table-responsive">
				<table class="table table-striped" style="border: 1px solid #eee;">
					<thead>
						<tr>
						  <th>人员名称</th>
						  <th>创建时间</th>
						  <th>级别</th>
						  <th>培训状态</th>
						  <th>操作 <button type="button" class="btn btn-xs btn-primary" onclick="add_recruiter();">添加培训人员</button></th>
						</tr>
					</thead>
					<tbody id="test">
						<script id="tpl" type="text/x-handlebars-template">
						{{#each this}}
						<tr>
							<td>{{USERNAME}}</td>
							<td>{{HAPPEN_DATE}}</td>
							<td>{{help_role ROLE}}</td>
							<td>{{help_flag FLAG}}</td>
							<td>
							<button type="button" class="btn btn-sm btn-success" onclick="upd_recruiter('{{USER_ID}}','{{FIRST_NAME}}','{{LAST_NAME}}','{{EMAIL}}','{{PWD}}');">修改</button>
							<button type="button" class="btn btn-sm btn-success" onclick="employ('{{USER_ID}}');" style="display:{{help_employ FLAG}}">聘入</button>
							<button type="button" class="btn btn-sm btn-danger" onclick="del_recruiter('{{USER_ID}}');">删除</button>
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
			<!-- 添加培训人员 -->
			<div id="rule_add" style="display:none">
				<p class="text-center">添加/修改培训人员</p>
				<form id="form" method="post">
					<input type="hidden" name="OP" id="OP"/>
					<input type="hidden" name="USER_ID" id="USER_ID"/>
					<input type="hidden" name="TRAIN_ID" id="TRAIN_ID"/>
					<input type="hidden" name="ROLE" id="ROLE" value="0"/>
					<div class="form-horizontal smalldiv">
						<div class="form-group">
							<div class="col-md-12">名称
								<input class="form-control largcontrol" id="FIRST_NAME" name="FIRST_NAME" type="text">
								<span class="text-danger" style="display:none;">名称为1～20个汉字、字母或数字。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">姓氏
								<input class="form-control largcontrol" id="LAST_NAME" name="LAST_NAME" type="text">
								<span class="text-danger" style="display:none;">姓氏为1～20个汉字、字母或数字。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">电子邮箱
								<input class="form-control largcontrol" id="EMAIL" name="EMAIL" type="text">
								<span class="text-danger" style="display:none;">电子邮箱格式为***@**.com。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">密码
								<input class="form-control largcontrol" id="PWD" name="PWD" type="text">
								<span class="text-danger" style="display:none;">密码由6～20个字母、数字、下划线组成。</span>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">验证密码
								<input class="form-control largcontrol" id="PWD_VERIFY" name="PWD_VERIFY" type="password">
								<span class="text-danger" style="display:none;">密码由6～20个字母、数字、下划线组成。</span>
								<span id="pv" class="text-danger" style="display:none;">密码和验证密码不一致。</span>
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
    </div>
	<script>
	    <%String recruiter_json = (String) request.getAttribute("recruiter_list");
			if (recruiter_json == "") {
				recruiter_json = "\"no_data\"";
		}%>
		
		var TRAIN_ID = <%=train_id%> ;
		var PARENT_ID = <%=parent_id%> ;
		var PARENT_ROLE = <%=parent_role%> ;
		var USERNAME = '<%=user_name%>' ;
		var rows2 =<%=recruiter_json%>; 
		var obj, rows = [];
		/* var rows2 =[{"USER_ID":"10001","USERNAME":"jpeng","HAPPEN_DATE":"20170501","ROLE":0,"FLAG":0,"TRAIN_ID":"1000"},
					{"USER_ID":"10002","USERNAME":"zpeng","HAPPEN_DATE":"20170501","ROLE":0,"FLAG":0,"TRAIN_ID":"1000"},
					{"USER_ID":"10003","USERNAME":"wpeng","HAPPEN_DATE":"20170501","ROLE":0,"FLAG":1,"TRAIN_ID":"1000"}];  */
		
		function back(){
			rule_add.style.display = "none";
			rule_list.style.display = "";
		}
			
		function goback(){
			window.location.href="<%=path%>/company/team_manage?method=train_team_list";
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
			//window.location.reload();
			/*
			test.innerHTML = tplt(JSON.parse(arr));
			rule_add.style.display = "none";
			rule_list.style.display = "";
			*/
		}
		function d_cb(arr,msg){	
			if(msg.show_msg){
				alert(msg.msg_content);
			}		
			window.location.reload();		
		}
	
		function add_recruiter(){
			var rows = {"OP":1,"TRAIN_ID":TRAIN_ID,"USER_ID":USER_ID, "FIRST_NAME":"", "LAST_NAME":"", "EMAIL":"","PWD":""};
			obj.init_object(rows);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function upd_recruiter(USER_ID,FIRST_NAME,LAST_NAME,EMAIL,PWD){
			var rows = {"OP":2, "USER_ID":USER_ID, "FIRST_NAME":FIRST_NAME, "LAST_NAME":LAST_NAME, "EMAIL":EMAIL,"PWD":PWD,"TRAIN_ID":TRAIN_ID};
			obj.init_object(rows);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function employ(USER_ID){
			if(PARENT_ID){
				var params = [ "OP=4&USER_ID=", USER_ID, "&TRAIN_ID=",TRAIN_ID, "&PARENT_ID=",PARENT_ID, "&PARENT_ROLE=",PARENT_ROLE].join("");
				ims_submit_ajax("<%=path%>/company/recruiter_manage", params,  d_cb);	
			}
			else
				alert("未分配培训团队，不能聘入！");
		}
		
		function del_recruiter(USER_ID){
			if(confirm("确定要删除该用户吗？")){
				var params = ["OP=3&USER_ID=", USER_ID, "&TRAIN_ID=", TRAIN_ID].join("");
				ims_submit_ajax("<%=path%>/company/recruiter_manage", params, cb);	
			}
		}
		
		Handlebars.registerHelper("help_employ", function (FLAG) {
			if (FLAG == 1){
				return "none";
			}
				return "";
		});

		Handlebars.registerHelper("help_flag", function (FLAG) {
			if (FLAG == 0)
				return "培训中";
			else if (FLAG == 1)
				return "已完成培训";
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
		test.innerHTML = tplt(rows2);
		
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
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/recruiter_manage?TRAIN_ID="+TRAIN_ID+"&PARENT_ID="+PARENT_ROLE+"&PARENT_ROLE="+PARENT_ROLE+"&current_page="+current_page+"&page_size="+page_size+"&USERNAME="+USERNAME;
		}
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/recruiter_manage?TRAIN_ID="+TRAIN_ID+"&PARENT_ID="+PARENT_ROLE+"&PARENT_ROLE="+PARENT_ROLE+"&page_size="+page_size+"&USERNAME="+USERNAME;
		});
		
		
		
		var cols = [
			{id:"form", 
				submit: {
					integrate:check_form,
					postform: submitWindow,
					url: "<%=path%>/company/recruiter_manage",
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
			{id:"USER_ID", init:{}},
			{id:"TRAIN_ID", init:{}},
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
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>