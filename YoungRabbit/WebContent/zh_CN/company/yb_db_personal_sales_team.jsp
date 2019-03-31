<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Pagination paging = (Pagination) request.getAttribute("page");
%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<%@include file="yb_db_head_include.jsp"%>
<link href="<%=path%>/css/bootstrap-datetimepicker.min.css" rel="stylesheet" >
<title>product</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
	<div class="container">
		<div id="emsg_id"></div>
		<div id="list">
			<h2>团队管理</h2>
			<div id="vp_team_list" class="table-responsive">
				<table class="table table-striped" style="border: 1px solid #eee;">
					<thead>
						<tr>
							<th>团队名称</th>
							<th>级别</th>
							<th>
							 <div id="dtpicker" class="controls input-append date form_datetime" data-date="" data-date-format="yyyyMM" data-link-field="dtp_date">
								<input id="dtp_input" onchange="team_list()" size="10" type="text" value="" readonly>
								<span class="add-on"><i class="icon-th"></i></span>
							</div>
							</th>
							<th>群销量(RM)</th>
						</tr>	
					</thead>
					<tbody id="test">
					<script id="tpl" type="text/x-handlebars-template">
						{{#each this}}
						<tr>
							<td><a href="javascript:team_detail('{{TEAM_ID}}','{{TYPE}}','{{USERNAME}}');">{{USERNAME}}</a></td>
							<td>{{help_role ROLE}}</td> 
							<td>{{MONTH}}</td>
							<td>{{#if GROUP_SALE}}<a href="javascript:sale_report('{{TEAM_ID}}','{{TYPE}}','{{USERNAME}}');">{{GROUP_SALE}}</a>{{else}}{{/if}}</td>
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
			<!-- 添加副总裁团队 -->
			<div id="vp_team_add" style="display: none">
				<p class="text-center">添加副总裁团队</p>
				<form id="form" method="post">
					<input type="hidden" name="TYPE" id="TYPE" value="4" />
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<select class="form-control largcontrol" id="V_TEAM_ID" name="V_TEAM_ID"></select>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-qubico" onclick="add();">确定</button>
							<button type="button" class="btn btn-qubico" onclick="show(2);">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="list2" style="display: none">
			<h2 id="stitle"></h2>
			<!-- 经理团队列表 -->
			<div id="m_team_list" class="table-responsive">
				<table class="table table-striped" style="border: 1px solid #eee;">
					<thead>
						<tr>
							 <th>经理团队</th>
							<th>成员</th>
							<th>级别</th>
							<th>时间</th>
							<th>销量(RM)</th>
						</tr>
					</thead>
					<tbody id="test2">
					  <script id="tpl2" type="text/x-handlebars-template">
						{{#each this}}
							<tr>
							  <td>{{USERNAME}}</td>
							  <td>{{USERNAME}}</td>
							  <td>{{help_role ROLE}}</td>
							  <td>{{MONTH}}</td>
							  <td>{{GROUP_SALE}}</td>
							</tr>
							{{#each members}}
							<tr>
							 <td>{{V_NAME}}</td>
							  <td>{{USERNAME}}</td>
							  <td>{{help_role ROLE}}</td>				  
							  <td>{{MONTH}}</td>
							  <td>{{PERSONAL_SALE}}</td>
							</tr>
							{{/each}}
						{{/each}}
					  </script>
					</tbody>
				</table>
			</div>
			<!-- 添加经理团队 -->
			<div id="m_team_add" style="display: none">
				<p class="text-center">添加经理团队</p>
				<form id="form2" method="post">
					<input type="hidden" name="M_TYPE" id="M_TYPE" value="2" /> <input type="hidden" name="H_V_TEAM_ID" id="H_V_TEAM_ID" />
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<select class="form-control largcontrol" id="M_TEAM_ID" name="M_TEAM_ID"></select>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-qubico" onclick="add2();">确定</button>
							<button type="button" class="btn btn-qubico" onclick="show(6);">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<div id="list3" style="display: none">
			<h2 id="stitle2"></h2>
			<!-- 销售员调岗 -->
			<div id="sales_member_move" style="display: none">
				<p class="text-center">销售员调岗</p>
				<form id="form3" method="post">
					<input type="hidden" name="H_V_TEAM_ID1" id="H_V_TEAM_ID1" />
					<input type="hidden" name="S_USER_ID" id="S_USER_ID" /><input type="hidden" name="S_PARENT_ID" id="S_PARENT_ID" />
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<select class="form-control largcontrol" id="S_TEAM_ID" name="S_TEAM_ID"></select>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-qubico" onclick="add3();">确定</button>
							<button type="button" class="btn btn-qubico" onclick="show(8);">返回</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 经理团队迁出-->
			<div id="m_team_move" style="display: none">
				<p class="text-center">经理团队迁出</p>
				<form id="form4" method="post">
					<input type="hidden" name="H_V_TEAM_ID2" id="H_V_TEAM_ID2" />
					<input type="hidden" name="M_USER_ID" id="M_USER_ID" /><input type="hidden" name="VP_PARENT_ID" id="VP_PARENT_ID" />
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<select class="form-control largcontrol" id="N_VP_TEAM_ID" name="N_VP_TEAM_ID"></select>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-qubico" onclick="add4();">确定</button>
							<button type="button" class="btn btn-qubico" onclick="show(10);">返回</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 经理团队添加销售-->
			<div id="m_team_add_sales" style="display: none">
				<p class="text-center">添加销售</p>
				<form id="form5" method="post">
					<input type="hidden" name="USER_ID5" id="USER_ID5" />
					<input type="hidden" name="ROLE5" id="ROLE5" />
					<input type="hidden" name="TEAM_ID5" id="TEAM_ID5" />
					<div class="form-horizontal">
						<div class="form-group">
							<div class="col-md-12">
								<select class="form-control largcontrol" id="S_USER_ID5" name="S_USER_ID5"></select>
							</div>
						</div>
						<div class="form-group text-center">
							<button type="button" class="btn btn-qubico" onclick="add5();">确定</button>
							<button type="button" class="btn btn-qubico" onclick="show(14);">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
			
		<div id="list4" style="display:none">
			<h2 id="stitle3"></h2>
			<input type="hidden" name="VP_PARENT_ID4" id="VP_PARENT_ID4" />
			<!-- 待调整人员列表 -->
			<div id="rule_list4" class="table-responsive">
				<table class="table table-striped" style="border: 1px solid #eee;">
					<thead>
					<tr>
						<th>名称</th>
						<th>级别</th>
						<th>销量(RM)</th>
						<th>操作</th>
					</tr>
					</thead>
					<tbody id="test4">
					<script id="tpl4" type="text/x-handlebars-template">
						{{#each this}}
							<tr>
							  <td>{{USERNAME}}</td>
							  <td>{{help_role ROLE}}</td>
							  <td>{{SALES}}</td>
							  <td>
								<button type="button" class="btn btn-sm btn-success" onclick="{{help_operate ROLE}}('{{TEAM_ID}}','{{USER_ID}}');">{{help_oname ROLE}}</button>
							  </td>
							</tr>
						{{/each}}
					</script>
					</tbody>
				</table>
			</div>
		</div>
	</div>

	<script>
		<%String vp_team_list_json = (String) request.getAttribute("vp_team_list");
			if (vp_team_list_json == "") {
				vp_team_list_json = "[]";
		}%>

		<%String vp_list_json = (String) request.getAttribute("vp_list");
			if (vp_list_json == "") {
				vp_list_json = "[]";
		}%>
		
		<%String m_list_json = (String) request.getAttribute("manager_list");
		if (m_list_json == "") {
			m_list_json = "[]";
		}%>
		
		<%String vp_move_list_json = (String) request.getAttribute("vp_move_list");
		if (vp_list_json == "") {
			vp_list_json = "[]";
		}%>
	
		<%String m_move_list_json = (String) request.getAttribute("manager_move_list");
		if (m_list_json == "") {
			m_list_json = "[]";
		}%>
		
		
			
		var obj0,obj1,obj2,obj3,obj4,rows = [];
		
		var rows2 =<%=vp_team_list_json%>;
			 
		var rows3 = <%=vp_list_json%>;
		
		var rows4 = <%=m_list_json%>;
		
		var rows5 = <%=vp_move_list_json%>;
		
		var rows6 = <%=m_move_list_json%>;
			
		function show(flag){//show vp_team_add
			if(flag == 1){
				//**************************
				vp_team_list.style.display = "none";
				vp_team_add.style.display = "";	
				
			}
			else if(flag == 2){//back
				//**************************
				vp_team_list.style.display = "";
				vp_team_add.style.display = "none";	
			}
			else if(flag == 3){//show m_team_list
				//**************************
				list.style.display = "none";
				list2.style.display = "";
				m_team_list.style.display = "";
			}
			else if(flag == 4){//back
				list.style.display = "";
				list2.style.display = "none";
				m_team_list.style.display = "none";
				
			}
			else if(flag == 5){//show m_team_add
				//**************************
				list.style.display = "none";
				list2.style.display = "";
				m_team_add.style.display = "";
			}
			else if(flag == 6){//back
				//**************************
				list.style.display = "";
				list2.style.display = "none";
				m_team_add.style.display = "none";
			}
			else if(flag == 7){//show sales_member_move
				//**************************
				list2.style.display = "none";
				list3.style.display = "";
				sales_member_move.style.display = "";
			}
			else if(flag == 8){//back
				//**************************
				list2.style.display = "";
				list3.style.display = "none";
				sales_member_move.style.display = "none";
			}
			else if(flag == 9){//show m_team_move
				//**************************
				list2.style.display = "none";
				list3.style.display = "";
				m_team_move.style.display = "";
			}
			else if(flag == 10){//back
				//**************************
				list2.style.display = "";
				list3.style.display = "none";
				m_team_move.style.display = "none";
			}
			else if(flag == 11){//show adjust_list
				//**************************
				vp_team_list.style.display = "none";
				list4.style.display = "";	
				rule_list4.style.display = "";
			}
			else if(flag == 12){//back
				//**************************
				vp_team_list.style.display = "";
				list4.style.display = "none";	
				rule_list4.style.display = "none";
			}
			else if(flag == 13){//show m_team_add_sales
				//**************************
				list2.style.display = "none";
				list3.style.display = "";
				m_team_add_sales.style.display = "";
			}
			else if(flag == 14){//back
				//**************************
				list2.style.display = "";
				list3.style.display = "none";
				m_team_add_sales.style.display = "none";
			}
		}
		
		function sale_report(TEAM_ID,TYPE,USERNAME){
			window.location.href = "<%=path%>/company/sale_report";
		}
		
		function team_list(){
			var YM=document.getElementById('dtp_input').value;
			window.location.href="<%=path%>/company/personal_team_manage?method=team_list&YM="+YM;
		}
		
		function cb(){
			window.location.reload();
		}	

		function vp_detail_cb(data){
			
//			test2.innerHTML = tplt2(JSON.parse(arr));
			if(data.length==0){
				test2.innerHTML="暂无数据！";
			}else{
				test2.innerHTML = tplt2(data);
			}
			//test2.innerHTML = tplt2(data);
			show(3);
		}
		
		function team_detail(TEAM_ID, TYPE,USERNAME){
			var stitle = document.getElementById('stitle');
			stitle.innerHTML = ["<button type='button' style='margin-right:20px;' class='btn btn-sm btn-success' onclick='show(4);'>返回</button>", USERNAME, " 团队详情"].join("");
			var o = document.getElementById('H_V_TEAM_ID');
			o.value = TEAM_ID;
			
			var YM=document.getElementById('dtp_input').value;		
			
			show(3);
			
			var params = ["method=vp_team_detail&H_V_TEAM_ID=", TEAM_ID,"&TEAM_ID=",TEAM_ID,"&TYPE=",TYPE,"&YM=",YM].join("");
			ims_submit_ajax("<%=path%>/company/personal_team_manage", params, vp_detail_cb);
			
		}
		
		function close_vp_team_cb(data,msg){
			show_msg_warn("emsg_id",msg.msg_content);
			test.innerHTML = tplt(data);
			//show(1);
		}
		
		function close_vp_team(TEAM_ID){
			if(confirm("确定要关闭该副总裁团队吗？")){
				var params = ["method=vp_team_close&STATUS=2&TEAM_ID=", TEAM_ID].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, close_vp_team_cb);		
			}
		}
		
		function del_vp_team_cb(data,msg){
			show_msg_warn("emsg_id",msg.msg_content);
			
			test.innerHTML = tplt(data);
			show(2);
		}
		
		function del_vp_team(TEAM_ID){
			if(confirm("确定要删除该副总裁团队吗？")){
				var params = ["method=vp_team_delete&STATUS=4&TEAM_ID=", TEAM_ID].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, del_vp_team_cb);	
			}
		}
		
		function adjust_cb(data,msg){
			if(data.length==0){
				test4.innerHTML ="暂无数据！";
			}else{
				test4.innerHTML = tplt4(data);
			}
			
			show(11);
		}
		
		function adjust(TEAM_ID, NAME){
			var o = document.getElementById('VP_PARENT_ID4');
			o.value=TEAM_ID;
			stitle3.innerHTML = ["<button type='button' style='margin-right:20px;' class='btn btn-sm btn-success' onclick='show(12);'>返回</button>人员调整列表"].join("");
			//VP_PARENT_ID.value = TEAM_ID;			//点击"团队迁入"时副总裁ID
			
			//show(11);
			
			var params = ["method=vp_adjust_list&TEAM_ID=", TEAM_ID].join("");
			ims_submit_ajax("<%=path%>/company/personal_team_manage", params, adjust_cb);
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
		
		Handlebars.registerHelper("help_role2", function (ROLE,TEAM_ID) {
			if ((ROLE == 1 || ROLE == 2) && TEAM_ID == VP_PARENT_ID4.value)
				return "必须调岗";
			else
				return "可选调整";
		});

		Handlebars.registerHelper("help_operate", function (ROLE) {
			if (ROLE == 1 || ROLE == 2)
				return "member_move";				
			else if (ROLE == 4 || ROLE == 8 || ROLE == 16)
				return "team_move";	
			else
				return "";
		});
		Handlebars.registerHelper("help_oname", function (ROLE) {
			if (ROLE == 1 || ROLE == 2)
				return "调岗";				
			else if (ROLE == 4 || ROLE == 8 || ROLE == 16)
				return "迁移";	
			else
				return "";
		});
		
		Handlebars.registerHelper("help_status", function (STATUS) {
			if (STATUS == 1)
				return "正常";
			else if (STATUS == 2)
				return "关闭";
			else
				return "";
		});
		
		//vp_list
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		//test.innerHTML = tplt(rows2);
		if(rows2.length==0){
			test.innerHTML="暂无数据！";
		}else{
			test.innerHTML = tplt(rows2);
		}
		
		function vp_team_add_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
		}
		
		
		
		function m_team_add_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
		}
		
		function s_member_move_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
		}
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/personal_team_manage?method=999&current_page="+current_page+"&page_size="+page_size;
		}
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/personal_team_manage?method=999&current_page=1&page_size="+page_size;
		});

		var cols0 = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: '<%=path%>/company/personal_team_manage?method=vp_team_add',
					refresh : vp_team_add_cb,
			}
		}, 
		{
				id : "PAGE_SIZE",
				init : {
					multiple : false,
					vdefault : <%=paging.getPageSize()%>,
					value : 0,
					text : 1,
					ds : arr0
				}
			}, {
			id : "TYPE",
			init : {}
		},  
		{
			id : "V_TEAM_ID",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : rows3
			}
		},];
		
		var cols1 = [
			{id:"form2", 
			submit: {
				postform: submitWindow,
				url: '<%=path%>/company/personal_team_manage?method=manager_team_add',
				refresh : m_team_add_cb,
			}
		},{
			id : "M_TYPE",
			init : {}
		}, {
			id : "H_V_TEAM_ID",
			init : {}
		}, 
		{
			id : "M_TEAM_ID",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : rows4
			}
		}, ];
		
		
		function check_form3(){
			var o = document.getElementById('H_V_TEAM_ID');	
			var h_val=o.value ;
			form3.H_V_TEAM_ID1.value=o.value ;
			return true;
		}
		
		var cols2 = [
			{id:"form3", 
			submit: {
				integrate:check_form3,
				postform: submitWindow,
				url: '<%=path%>/company/personal_team_manage?method=sales_member_move',
				refresh : s_member_move_cb,
			}
		}, {
			id : "H_V_TEAM_ID1",
			init : {}
		}, {
			id : "S_USER_ID",
			init : {}
		}, {
			id : "S_PARENT_ID",
			init : {}
		}, 
		{
			id : "S_TEAM_ID",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : rows6
			}
		}, ];
		
		
		function m_team_move_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
			
		}
		
		function check_form4(){
			var o = document.getElementById('H_V_TEAM_ID');				
			form4.H_V_TEAM_ID2.value=o.value ;
			return true;
		}
		
		var cols3 = [
			{id:"form4", 
			submit: {
				integrate:check_form4,
				postform: submitWindow,
				url: '<%=path%>/company/personal_team_manage?method=manager_team_move',
				refresh : m_team_move_cb,
			}
		}, {
			id : "H_V_TEAM_ID2",
			init : {}
		}, {
			id : "M_USER_ID",
			init : {}
		}, {
			id : "VP_PARENT_ID",
			init : {}
		}, 
		{
			id : "N_VP_TEAM_ID",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : rows5
			}
		}, ];
		
		/***********************经理团队添加销售 开始************************/
		function add5(){
			obj4.submit();
		}
		
		function no_m_team_sales_cb(data,msg){
			cols4[3].init.ds=data;
			obj4.init_object(sales_row);
		}
		
		var sales_row=[];
		function add_sales(VP_ID,TEAM_ID,USERNAME){
			sales_row={"TEAM_ID5":TEAM_ID};
			
			var stitle = document.getElementById('stitle2');
			stitle.innerHTML = ["经理团队 ", USERNAME, " 添加销售"].join("");
			show(13);
		
			//取出当前可以添加的销售放到下拉框中
			var params = ["method=","get_no_m_team_sales&H_V_TEAM_ID=",VP_ID].join("");
			ims_submit_ajax("<%=path%>/company/personal_team_manage", params, no_m_team_sales_cb);
			
		}
		
		function check_form5(){
			//var o = document.getElementById('H_V_TEAM_ID');				
			//form4.H_V_TEAM_ID2.value=o.value ;
			var u_id_role=S_USER_ID5.value;
			var arr=u_id_role.split("_");
			form5.USER_ID5.value=arr[0];
			form5.ROLE5.value=arr[1];
			
			return true;
		}
		
		function manager_team_sales_add_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
		}
		
		var cols4 = [
			{id:"form5", 
			submit: {
				integrate:check_form5,
				postform: submitWindow,
				url: '<%=path%>/company/personal_team_manage?method=manager_team_sales_add',
				refresh : manager_team_sales_add_cb,
			}
		}, {
			id : "ROLE5",
			init : {}
		},
		{
			id : "TEAM_ID5",
			init : {}
		},
		{
			id : "S_USER_ID5",
			init : {
				multiple : false,
				vdefault : 1,
				value : 0,
				text : 1,
				ds : sales_row
			}
		}, {
			id : "USER_ID5",
			init : {}
		}, ];
		/***********************经理团队添加销售 结束************************/

		/******************************** 销售团队详情内容 ********************************/
		function sales_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
			
		}
		
		
		
		function team_move_in_cb(data,msg){
			if(msg.show_msg){
				show_msg_warn("emsg_id",msg.msg_content);
			}
			setTimeout(function() {
				window.location.reload();
			}, __warn_period);
		}
		
		function team_move_in(TEAM_ID,USER_ID){
			if(confirm("确定将该团队迁入吗？")){
				var o = document.getElementById('VP_PARENT_ID4');
				
				var params = ["method=manager_team_move_in&M_USER_ID=", USER_ID, "&VP_PARENT_ID=",TEAM_ID, "&VP_TEAM_ID=", o.value].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, team_move_in_cb);
			}
		}
		
		function team_move(TEAM_ID,USER_ID,USERNAME) {
			if (confirm("确定要执行迁出操作吗？")) {
				var o = document.getElementById('H_V_TEAM_ID');
				 
				var stitle = document.getElementById('stitle2');
				stitle.innerHTML = ["经理团队 ", USERNAME, " 迁出"].join("");
				
				var s_row={"H_V_TEAM_ID2":o.value,"VP_PARENT_ID":TEAM_ID,"M_USER_ID":USER_ID};
				
				obj3.init_object(s_row);
				show(9);
				
			}
		}
		function team_close(TEAM_ID,USER_ID) {
			if (confirm("确定要执行关闭操作吗？")) {
				var o = document.getElementById('H_V_TEAM_ID');
				H_V_TEAM_ID=o.value ;
				var params = [ "method=manager_team_close&TEAM_ID=", TEAM_ID, "&USER_ID=", USER_ID, "&H_V_TEAM_ID=", H_V_TEAM_ID ].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, sales_cb);
			}
		}
		function team_del(TEAM_ID,USER_ID,ROLE) {
			if (confirm("确定要执行删除操作吗？")) {
				var o = document.getElementById('H_V_TEAM_ID');
				H_V_TEAM_ID=o.value ;
				var params = [ "method=manager_team_delete&TEAM_ID=", TEAM_ID, "&USER_ID=", USER_ID,"&ROLE",ROLE, "&H_V_TEAM_ID=", H_V_TEAM_ID ].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, sales_cb);
			}
		}
		
		function member_move(TEAM_ID,USER_ID,USERNAME) {
			
			if (confirm("确定要执行调岗操作吗？")) {
				var s_row={"S_PARENT_ID":TEAM_ID,"S_USER_ID":USER_ID};
				var stitle = document.getElementById('stitle2');
				stitle.innerHTML = ["销售员", USERNAME, " 调岗"].join("");
				obj2.init_object(s_row);
				show(7);
			}
		}
		
		
		function dimission(TEAM_ID, USER_ID) {
			if (confirm("确定要执行离职操作吗？")) {
				var o = document.getElementById('H_V_TEAM_ID');
				H_V_TEAM_ID=o.value ;
				var params = [ "method=sales_member_dimission&TEAM_ID=", TEAM_ID, "&USER_ID=", USER_ID, "&H_V_TEAM_ID=", H_V_TEAM_ID ].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, sales_cb);
			}
		}
		
	
		function member_del(TEAM_ID, USER_ID) {
			if (confirm("确定要删除该销售员吗？")) {
				var o = document.getElementById('H_V_TEAM_ID');
				H_V_TEAM_ID=o.value ;
				var params = [ "method=sales_member_delete&TEAM_ID=", TEAM_ID, "&USER_ID=", USER_ID , "&H_V_TEAM_ID=", H_V_TEAM_ID].join("");
				ims_submit_ajax("<%=path%>/company/personal_team_manage", params, sales_cb);
			}
		}
		
			
		function add(){
			obj0.submit();
		}

		function add2() {
			obj1.submit();
		}
		
		function add3() {
			obj2.submit();
		}
		
		function add4() {
			obj3.submit();
		}

		//manager_list
		var tplo2 = document.getElementById('tpl2');
		var tplt2 = Handlebars.compile(tplo2.innerHTML);
		var test2 = document.getElementById('test2');
		//		test2.innerHTML = tplt2(rows2);
		
		//adjust_list
		var tplo4 = document.getElementById('tpl4');
		var tplt4 = Handlebars.compile(tplo4.innerHTML);
		var test4 = document.getElementById('test4');

		window.onload = function() {
			obj0 = new iform(cols0, document.forms[0]);
			obj0.init_object(rows);
			
			obj1 = new iform(cols1, document.forms[1]);
			obj1.init_object(rows);
			
			obj2 = new iform(cols2, document.forms[2]);
			obj2.init_object(rows);
			
			obj3 = new iform(cols3, document.forms[3]);
			obj3.init_object(rows);
			
			obj4 = new iform(cols4, document.forms[4]);
			obj4.init_object(rows);
			
			var params='<%=paging.getPageParams()%>';
			if(params!=null){
				var arr=params.split(',');
				document.getElementById('dtp_input').value=arr;
					//$('#dtp_input').selectpicker('val', arr);
					//document.getElementById('done').selectpicker('val', params);
			}
			
			
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
</script>

</body>
</html>