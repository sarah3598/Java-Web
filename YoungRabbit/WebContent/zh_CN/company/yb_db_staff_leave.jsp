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

	<div class="container-fluid">

		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">薪资管理</h1>
				<!--页面导航按钮-->
				<ul class="nav nav-tabs">
					<li><a href="<%=path%>/company/staff_salary_manage"> 员工工资管理 </a></li>
					<li class="active"><a href="<%=path%>/company/staff_leave_manage"> 员工假期管理 </a></li>
					<li><a href="<%=path%>/zh_CN/company/yb_db_do_salary_batch.jsp"> 工资批计算 </a></li>
				</ul>
				<!--页面导航按钮-->
				<!-- 规则列表 -->
				<div id="rule_list" class="table-responsive" style="border: 1px solid #eee;">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>人员名称</th>
								<th>假期类型</th>
								<th>起止时间</th>
								<th>天数</th>
								<th>状态</th>
								<th>操作
									<button type="button" class="btn btn-xs btn-primary" onclick="add_leave();">请假</button>
								</th>
							</tr>
						</thead>
						<tbody id="test">
							<script id="tpl" type="text/x-handlebars-template">
			    {{#each this}}
                <tr>
                  <td>{{USERNAME}}</td>
                  <td>{{help_type TYPE}}</td>
                  <td>{{START_DATE}}-{{END_DATE}}</td>
                  <td>{{DAYS}}</td>
                  <td>{{help_status STATUS}}</td>
                  <td>
					<button type="button" class="btn btn-sm btn-success" onclick="upd_leave('{{LEAVE_ID}}');">修改</button>
					<button type="button" class="btn btn-sm btn-success" onclick="back_leave('{{LEAVE_ID}}','{{STATUS}}');">销假</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_leave('{{LEAVE_ID}}');">删除</button>
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
				<div id="rule_add" style="display: none">
					<p class="text-center" id="btitle"></p>
					<form id="form" method="post">
						<input type="hidden" name="OP" id="OP" /> <input type="hidden" name="LEAVE_ID" id="LEAVE_ID" /> <input type="hidden" name="DAYS" id="DAYS" />
						<div class="form-horizontal smalldiv">
							<div class="form-group">
								<div class="col-md-12">
									人员名称 <input class="form-control largcontrol" id="USERNAME" name="USERNAME" type="text"> <span class="text-danger" style="display: none;">名称为1～20个汉字、字母或数字。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									电子邮箱（登陆账号） <input class="form-control largcontrol" id="EMAIL" name="EMAIL" type="text"> <span class="text-danger" style="display: none;">电子邮箱格式为***@**.com。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									假期类型 <select class="form-control largcontrol" id="TYPE" name="TYPE"></select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									开始时间(格式：20170501)： <input class="form-control largcontrol" id="START_DATE" name="START_DATE" type="text"> <span class="text-danger" style="display: none;">8个数字</span> <span id="SA" class="text-danger" style="display: none;">开始时间不大于结束时间。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									结束时间(格式：20170504)： <input class="form-control largcontrol" id="END_DATE" name="END_DATE" type="text"> <span class="text-danger" style="display: none;">8个数字</span> <span id="ED" class="text-danger" style="display: none;">结束时间不小于开始时间。</span>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									天数 <input class="form-control largcontrol" id="SDAYS" name="SDAYS" type="text" disabled>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									假期状态： <input type="radio" name="STATUS" ID="STATUS_0"><label for="STATUS_0">未销假</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" name="STATUS" ID="STATUS_1"><label for="STATUS_1">已销假</label>
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
	</div>
	<script>
		var obj, rows = [];
		<%String leave_json = (String) request.getAttribute("leave_list");
			if (leave_json == "") {
				leave_json = "\"no_data\"";
			}%>
        var  rows1 =<%=leave_json%>;
		
		/*var	rows1 = [{"LEAVE_ID":100037,"USER_ID":10000,"USERNAME":"Liming","EMAIL":"Liming@163.com","TYPE":1,"STATUS":0,"START_DATE":20170501,"END_DATE":20170504,"DAYS":4},
					{"LEAVE_ID":100038,"USER_ID":10000,"USERNAME":"Liming","EMAIL":"Liming@163.com","TYPE":2,"STATUS":0,"START_DATE":20170501,"END_DATE":20170504,"DAYS":4},
					{"LEAVE_ID":100039,"USER_ID":10001,"USERNAME":"Tom","EMAIL":"Tom@163.com","TYPE":3,"STATUS":1,"START_DATE":20170501,"END_DATE":20170504,"DAYS":4},
					{"LEAVE_ID":100040,"USER_ID":10002,"USERNAME":"Jerry","EMAIL":"Jerry@163.com","TYPE":1,"STATUS":0,"START_DATE":20170501,"END_DATE":20170504,"DAYS":4},
					{"LEAVE_ID":100041,"USER_ID":10002,"USERNAME":"Jerry","EMAIL":"Jerry@163.com","TYPE":4,"STATUS":0,"START_DATE":20170501,"END_DATE":20170504,"DAYS":4}
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
	
		function add_leave(){
			btitle.innerHTML = "请假";
			var row = {"OP":1,"USERNAME":"","EMAIL":"","TYPE":1,"STATUS":0,"START_DATE":"","END_DATE":"","SDAYS":""};			
			obj.init_object(row);
			rule_list.style.display = "none";
			rule_add.style.display = "";
		}
		
		function upd_leave(LEAVE_ID){
			btitle.innerHTML = "修改请假";
			var arr = {};
			for(var i = 0; i < rows1.length; i ++){
				if(rows1[i].LEAVE_ID == parseInt(LEAVE_ID))
					arr = rows1[i];
			}
			
			var row = {"OP":2,"LEAVE_ID":arr.LEAVE_ID,"USER_ID":arr.USER_ID,"USERNAME":arr.USERNAME,"EMAIL":arr.EMAIL,"TYPE":arr.TYPE,"STATUS":arr.STATUS,"START_DATE":arr.START_DATE,"END_DATE":arr.END_DATE,"DAYS":arr.DAYS,"SDAYS":arr.DAYS};
			obj.init_object(row);
			rule_list.style.display = "none";	
			rule_add.style.display = "";
		}
		
		function back_leave(LEAVE_ID,STATUS){
			if(STATUS==1) alert("该员工已销假！");
			else{
			if(confirm("确定要销假吗？")){
				var params = ["OP=3&LEAVE_ID=", LEAVE_ID,"&STATUS=", STATUS].join("");
				ims_submit_ajax("<%=path%>/company/staff_leave_manage", params, cb);	
			}}
		}
		
		function del_leave(LEAVE_ID){
			if(confirm("确定要删除该假期吗？")){
				var params = ["OP=4&LEAVE_ID=", LEAVE_ID].join("");
				ims_submit_ajax("<%=path%>/company/staff_leave_manage", params, cb);	
			}
		}
		
		Handlebars.registerHelper("help_type", function (TYPE) {
			if (TYPE == 1)
				return "年假";
			else if (TYPE == 2)
				return "病假";
			else if (TYPE == 3)
				return "住院医疗假";
			else if (TYPE == 4)
				return "产假";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_status", function (STATUS) {
			if (STATUS == 0)
				return "未销假";
			else if (STATUS == 1)
				return "已销假";
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
				var o = validator.is_name(validator.trim(object.value), object.minLength, object.maxLength);
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
				var o = validator.is_integer(validator.trim(object.value), object.minLength, object.maxLength);
				if(o){
					if(object.id == "START_DATE" && END_DATE.value && object.value.length == 8 && END_DATE.value.length == 8){
						if(parseInt(object.value) > parseInt(END_DATE.value)){
							SA.style.display = "";
							setTimeout(function () { 
								SA.style.display = "none";
							}, 3000);
						}
						else{		//计算days
							var day = get_days(object.value,END_DATE.value); 
							if(day){
								SDAYS.value = day;
								DAYS.value = day;
							}
						}	
					}
					
					if(object.id == "END_DATE" && START_DATE.value && object.value.length == 8 && START_DATE.value.length == 8){
						if(parseInt(object.value) < parseInt(START_DATE.value)){
							ED.style.display = "";
							setTimeout(function () { 
								ED.style.display = "none";
							}, 3000);
						}
						else{		//计算days
							var day = get_days(START_DATE.value,object.value); 
							if(day){
								SDAYS.value = day;
								DAYS.value = day;
							}
						}	
					}					
				
					object.nextElementSibling.style.display = "none";	
				}
				else{
					object.nextElementSibling.style.display = "";
					setTimeout(function () { 
						object.nextElementSibling.style.display = "none";
					}, 6000);
				}
			}
			
		}
		function verify2(){
			var object = this;
			if(object.value){
				var o = validator.is_email(validator.trim(object.value), object.minLength, object.maxLength);
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
			window.location.href="<%=path%>/company/staff_leave_manage?current_page="+current_page+"&page_size="+page_size;
		}
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/staff_leave_manage?page_size="+page_size;
		});
		
		var arr1 = [["1","年假"],["2","病假"],["3","住院医疗假"],["4","产假"]];
		var cols = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: "<%=path%>/company/staff_leave_manage",
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
			{id:"OP", init:{}},					//OP是标志位 1:添加  2:修改 3:销假 4：删除
			{id:"LEAVE_ID", init:{}},
			{id:"USER_ID", init:{}},
			{id:"STATUS", init:{}},
			{id:"USERNAME", tips:{action:ims_show_warn, text:"人员名称"},
				init:{minLength:1, maxLength:20, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_name}},
			{id:"EMAIL", tips:{action:ims_show_warn, text:"电子邮箱"},
				init:{minLength:1, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify2},
				submit:{formatter:validator.trim, check:validator.is_email}},
			{id:"TYPE",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr1}},
			{id:"START_DATE", tips:{action:ims_show_warn, text:"开始时间(格式：20170501)"},
				init:{minLength:8, maxLength:8, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify1},
				submit:{formatter:validator.trim, check:validator.is_integer}},
			{id:"END_DATE", tips:{action:ims_show_warn, text:"结束时间(格式：20170504)"},
				init:{minLength:8, maxLength:8, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify1},
				submit:{formatter:validator.trim, check:validator.is_integer}},	
			{id:"DAYS", init:{}},
			{id:"SDAYS", init:{}},
			{id:"STATUS_0",
				init:{name:"STATUS",value:0, vdefault:true}},
			{id:"STATUS_1",
				init:{name:"STATUS",value:1}},
		];
		
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
</script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>
