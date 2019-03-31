<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	Pagination paging = (Pagination) request.getAttribute("page");
	String month = (String) request.getAttribute("month");
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
		<h2>薪资管理</h2>
		<ul class="nav nav-tabs">
			<li><a href="<%=path%>/company/staff_salary_manage"> 员工工资管理 </a></li>
			<!--<li><a href="<%=path%>/company/staff_leave_manage"> 员工假期管理 </a></li>-->
			<li class="active"><a href="<%=path%>/zh_CN/company/yb_db_do_salary_batch.jsp"> 工资批计算 </a></li>
		</ul>
		</br>
		<!-- 调整工资 -->
		<div id="rule_add">
			<p class="text-center">工资批计算</p>
			<form id="form" method="post">
				<input type="hidden" name="OP" id="OP" /> 
				<div class="form-horizontal smalldiv">
					<div class="form-group text-center">
						<button type="button" class="btn btn-qubico" onclick="add();" id="ok">开始</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script>
		var obj, obj2, rows = [];
		
		function add(){
			obj.submit();
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
			
		var cols = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: "<%=path%>/company/staff_salary_manage?OP=999",
					refresh: cb,
				}
			},];
		
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
</script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>
