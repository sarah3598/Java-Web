<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Pagination paging = (Pagination) request.getAttribute("page");
	String YM=(String) paging.getPageParams();
%>

<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>

<%@include file="yb_db_head_include.jsp"%>
<title>Staff Dashboard</title>
<link href="<%=path%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" >
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
	<div class="container">
		<h2>薪资管理</h2>
		<ul class="nav nav-tabs">
			<li  class="active"><a href="<%=path%>/company/staff_salary_manage"> 员工工资管理 </a></li>
			<!--<li><a href="<%=path%>/company/staff_leave_manage"> 员工假期管理 </a></li>
			--> <li><a href="<%=path%>/zh_CN/company/yb_db_do_salary_batch.jsp"> 工资批计算 </a></li>
		</ul>
		</br>
	  <!-- 人员列表 -->
		 </br>
	  <div id="rule_list" class="table-responsive">
		<div class="form-inline">
			<div id="dtpicker" class="controls input-append date form_datetime" data-date="" data-date-format="yyyyMM" data-link-field="dtp_date">
				选择年月：<input id="dtp_input" onchange="salary_list()" size="10" type="text" value="" readonly>
				<span style="height:26px;" class="add-on"><i class="icon-th"></i></span>
			</div>
		</div><br>
		<table class="table table-striped" style="border: 1px solid #eee;">
		   <thead>
			<tr>
			  <th>姓名</th>
			  <th>级别</th>
			   <th>额外奖金</th>
			  <th>销售佣金</th>
			  <th>覆盖佣金</th>
			  <th>总计</th>
			<!--  <th>特别奖赏</th>-->
				  <th>操作</th>				
			</tr>
		  </thead>
		  <tbody id="test">
		  <script id="tpl" type="text/x-handlebars-template">
			{{#each this}}
			<tr>
			  <td>{{USERNAME}}</td>
			  <td>{{help_role ROLE}}</td>
			  <td>{{BONUS}}</td>
			  <td>{{SALE_BROKERAGE}}</td>
			  <td>{{COVER_BROKERAGE}}</td>
			  <td>{{SUM}}</td>
			  <!--  <td>{{ESPECIAL_REWARD}}</td>-->
				  <td><button type="button" class="btn btn-sm btn-success" onclick="adjust('{{SALARY_ID}}');">调整</button></td>
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
	  <!-- 调整工资 -->
	  <div id="rule_add" style="display:none">
		<p class="text-center">调整工资</p>
		<form id="form" method="post">
			<input type="hidden" name="SALARY_ID" id="SALARY_ID"/>
			<input type="hidden" name="OP" id="OP"/>
			<input type="hidden" name="SUM" id="SUM"/>
			<div class="form-horizontal smalldiv">
				<div class="form-group">
					<div class="col-md-12">人员名称
						<input class="form-control largcontrol" id="USERNAME" type="text" disabled>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">人员级别
						<select class="form-control largcontrol" id="ROLE" name="ROLE" disabled></select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">额外奖金（RM）：
						<input class="form-control largcontrol" id="BONUS" type="text" disabled>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">销售佣金（RM）：
						<input class="form-control largcontrol" id="SALE_BROKERAGE" type="text" disabled>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">覆盖佣金（RM）：
						<input class="form-control largcontrol" id="COVER_BROKERAGE" type="text" disabled>
					</div>
				</div>	
				<div class="form-group">
					<div class="col-md-12">特别奖赏（RM）：
						<input class="form-control largcontrol" id="ESPECIAL_REWARD" name="ESPECIAL_REWARD" type="text">
						<span class="text-danger" style="display:none;">1～8个数字</span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">总计（RM）：
						<input class="form-control largcontrol" id="SSUM" type="text" disabled>
					</div>
				</div>			
				<div class="form-group">
					<div class="col-md-12">个人最低销售（RM）：
						<input class="form-control largcontrol" id="PERSONAL_SALE" type="text" disabled>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-12">群体最低销售（RM）：
						<input class="form-control largcontrol" id="GROUP_SALE" type="text" disabled>
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
		var obj, obj2, rows = [];
		<%String salary_json = (String) request.getAttribute("salary_list");
		
		if (salary_json == "") {
			salary_json = "\"no_data\"";
	   }%>

        var  rows1 =<%=salary_json%>;
		/*var	rows1 = [{"SALARY_ID":1,"USERNAME":"Tom1","ROLE":128,"SALARY":5000,"VARIABLE_BONUS":100,"SALE_BROKERAGE":100,"COVER_BROKERAGE":100,"EPFSOCSO":10,"PERSONAL_SALE":0,"GROUP_SALE":100000,"MEDICAL_ALLOWANCE":200,"JOURNEY_ALLOWANCE":0,"ESPECIAL_REWARD":1,"ESPECIAL_REWARD":10,"SUM":10000},
					{"SALARY_ID":2,"USERNAME":"Tom2","ROLE":64,"SALARY":4000,"VARIABLE_BONUS":100,"SALE_BROKERAGE":120,"COVER_BROKERAGE":110,"EPFSOCSO":10,"PERSONAL_SALE":0,"GROUP_SALE":80000,"MEDICAL_ALLOWANCE":200,"JOURNEY_ALLOWANCE":0,"ESPECIAL_REWARD":1,"ESPECIAL_REWARD":0,"SUM":10000},
					{"SALARY_ID":3,"USERNAME":"Tom3","ROLE":8,"SALARY":3000,"VARIABLE_BONUS":100,"SALE_BROKERAGE":200,"COVER_BROKERAGE":110,"EPFSOCSO":10,"PERSONAL_SALE":0,"GROUP_SALE":60000,"MEDICAL_ALLOWANCE":200,"JOURNEY_ALLOWANCE":0,"ESPECIAL_REWARD":1,"ESPECIAL_REWARD":0,"SUM":10000},
					{"SALARY_ID":4,"USERNAME":"Tom4","ROLE":4,"SALARY":2000,"VARIABLE_BONUS":100,"SALE_BROKERAGE":200,"COVER_BROKERAGE":120,"EPFSOCSO":10,"PERSONAL_SALE":0,"GROUP_SALE":20000,"MEDICAL_ALLOWANCE":200,"JOURNEY_ALLOWANCE":0,"ESPECIAL_REWARD":1,"ESPECIAL_REWARD":0,"SUM":10000},
					{"SALARY_ID":5,"USERNAME":"Tom5","ROLE":2,"SALARY":1000,"VARIABLE_BONUS":100,"SALE_BROKERAGE":100,"COVER_BROKERAGE":200,"EPFSOCSO":10,"PERSONAL_SALE":1000,"GROUP_SALE":0,"MEDICAL_ALLOWANCE":200,"JOURNEY_ALLOWANCE":0,"ESPECIAL_REWARD":1,"ESPECIAL_REWARD":100,"SUM":10000},
				   ];*/
				  
		var YM = <%=YM%>;
		//var rows2 = {"month": month};
		
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
			
		function d_cb(arr,msg){	
			if(msg.show_msg){
				if(msg.msg_content==""){
					if(arr.length>0)
					     rows1=arr;
					test.innerHTML = tplt(arr);	
				}
				else alert(msg.msg_content);
			}	
			test.innerHTML = tplt(arr);	
		}
			
		function salary_list(){
			var ym = dtp_input.value;				
			window.location.href="<%=path%>/company/staff_salary_manage?YM="+ym;
		}
		
		function adjust(SALARY_ID){
			var arr = {};
			for(var i = 0; i < rows1.length; i ++){
				if(rows1[i].SALARY_ID == parseInt(SALARY_ID))
					arr = rows1[i];
			}
			
			var row = {"OP":2,"SALARY_ID":arr.SALARY_ID,"USERNAME":arr.USERNAME,"ROLE":arr.ROLE,"BONUS":arr.BONUS,"SALE_BROKERAGE":arr.SALE_BROKERAGE,"COVER_BROKERAGE":arr.COVER_BROKERAGE,
					   "PERSONAL_SALE":arr.PERSONAL_SALE,"GROUP_SALE":arr.GROUP_SALE,"ESPECIAL_REWARD":arr.ESPECIAL_REWARD,"SUM":arr.SUM,"SSUM":arr.SUM};
			obj.init_object(row);
			rule_list.style.display = "none";			
			ok.style.display = "";
			rule_add.style.display = "";
		}		
		
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
		//test.innerHTML = tplt(rows1);
		if(rows1.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows1);
		}
		
		function ims_show_warn(){
			var object = this;
			object.nextElementSibling.style.display = "";
			setTimeout(function () { 
				object.nextElementSibling.style.display = "none";
			}, 3000);
		}
		
		function add(){
			obj.submit();
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
			
			var sum = 0;
			form.BONUS.value ? sum += parseInt(form.BONUS.value) : sum = sum;
			form.SALE_BROKERAGE.value ? sum += parseInt(form.SALE_BROKERAGE.value) : sum = sum;
			form.COVER_BROKERAGE.value ? sum += parseInt(form.COVER_BROKERAGE.value) : sum = sum;
			form.ESPECIAL_REWARD.value ? sum += parseInt(form.ESPECIAL_REWARD.value) : sum = sum;
			
			SUM.value = sum;
			SSUM.value = sum;
		}
			
		
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/staff_salary_manage?OP=998&current_page="+current_page+"&page_size="+page_size;
		}
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/staff_salary_manage?OP=998&current_page=1&page_size="+page_size;
		});
		
		var arr1 = [["0","入职培训"],["1","销售代理"],["2","独家销售代理"],["4","组长"],["8","经理"],["16","副总裁"]];
		var cols = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: "<%=path%>/company/staff_salary_manage",
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
			{id:"OP", init:{}},
			{id:"SALARY_ID", init:{}},
			{id:"USERNAME", init:{}},
			{id:"ROLE",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr1}},
			{id:"BONUS", init:{}},
			{id:"SALE_BROKERAGE", init:{}},
			{id:"COVER_BROKERAGE", init:{}},				
			{id:"ESPECIAL_REWARD", tips:{action:ims_show_warn, text:"特别奖赏"},
				init:{minLength:1, maxLength:8, size:32, value:0, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_integer}},
			{id:"SUM", init:{}},
			{id:"SSUM", init:{}},
			{id:"PERSONAL_SALE", init:{}},
			{id:"GROUP_SALE", init:{}},
		];
		
		/*************************************** 月份列表 ***************************************/
		
		/*function search_salary(){
			month = form2.month.value;
			obj2.submit();
		};
		
		var arr2 = [["1","一月"],["2","二月"],["3","三月"],["4","四月"],["5","五月"],["6","六月"],["7","七月"],["8","八月"],["9","九月"],["10","十月"],["11","十一月"],["12","十二月"]];
		var cols2 = [
			{id:"form2", 
				submit: {
					postform: submitWindow,
					url: "<%=path%>/company/staff_salary_manage?OP=1",
					refresh: d_cb,
			}},	
			{id:"month",
				init:{multiple:false, vdefault:1, value:0, text:1, ds:arr2}},	
		];
		*/
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
			
			
			document.getElementById('dtp_input').value=YM;
					
		}
</script>
<%@include file="yb_db_foot_include.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=path%>/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">	
	    $('.form_datetime').datetimepicker({
	        language:  'zh-CN',
			format: 'yyyymm',//显示格式
	        weekStart: 1,
			autoclose: 1,
			startView: 3,
			minView: 3,
			forceParse: 0,
	        showMeridian: 1
	    });
		
//		$('#dtpicker').datetimepicker('setStartDate', '2017-01');
</script>
</body>
</html>
