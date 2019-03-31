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
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
	<div class="container">
		<h2>发布通知</h2>
		<div class="form-group">选择团队</div>
		<div class="form-group" id="test">
			<script id="tpl" type="text/x-handlebars-template">
			{{#each this}}
				{{#if FLAG}}
				<div>{{help_role ROLE}}
					{{#each members}}
					<input type="checkbox" name="TEAM" id="{{TEAM_ID}}" title="{{ROLE}}" {{#if OP}}checked{{else}}{{/if}}><label for="{{TEAM_ID}}">{{TEAM_NAME}}</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					{{/each}}
				</div>
				{{/if}}
			{{/each}}
			</script>
		</div>
		<form id="form" method="post">
			<input type="hidden" name="TIDS" id="TIDS"/>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="col-md-12">通知内容：
						<textarea class="form-control" id="MESSAGE" name="MESSAGE" type="text" rows="10"></textarea>
						<span class="text-danger" style="display:none;">1～500个文字</span>
					</div>
				</div>
				<div class="form-group text-center">
					<button type="button" class="btn btn-qubico" onclick="add();" id="ok">发布通知</button>
				</div>
			</div>
		</form>
    </div>
	<script>
		var obj, rows = [];
		<%String notification_json = (String) request.getAttribute("notification_list");
		if (notification_json == "") {
			notification_json = "\"no_data\"";
		}%>
		var rows1 =<%=notification_json%>; 
		
		/* var	rows1 = [{"TEAM_ID":1,"TEAM_NAME":"aaaaa",OP:1},
					{"TEAM_ID":2,"TEAM_NAME":"bbbbb"},
					{"TEAM_ID":3,"TEAM_NAME":"ccccc"},
					{"TEAM_ID":4,"TEAM_NAME":"ddddd"}
				]; */
		var rows2 = [
			{"ROLE":"3","FLAG":0,"members":[]},
			{"ROLE":"2","FLAG":0,"members":[]},
			{"ROLE":"1","FLAG":0,"members":[]}
		];
			
		(function(arr){
			for(var i = 0; i < arr.length; i ++){
				if(arr[i].ROLE == 3){  
					rows2[0].FLAG = 1;
					rows2[0].members.push(arr[i]);
				}
				else if(arr[i].ROLE == 2){
					rows2[1].FLAG = 1;
					rows2[1].members.push(arr[i]);
				}
				else if(arr[i].ROLE == 1){
					rows2[2].FLAG = 1;
					rows2[2].members.push(arr[i]);
				}
			}
		})(rows1);
		
		Handlebars.registerHelper("help_role", function (ROLE) {
			if(ROLE){
				if(ROLE == 3)
					return "副总裁团队：";
				else if(ROLE == 2)
					return "经 理 团 队：";
				else if(ROLE == 1)
					return "组 长 团 队：";
			}
			else
				return "";
		});
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		test.innerHTML = tplt(rows2);
		
		function add(){
			var val = "", teams = document.getElementsByName("TEAM");
			for(var i = 0; i < teams.length; i ++){
				if(teams[i].checked){
					if(val == "")
						val = [teams[i].id, ",", teams[i].title].join("");
					else
						val = [val, ",", teams[i].id, ",", teams[i].title].join(""); 
				}   				
			}
			if(val){	
				form.TIDS.value = val;
				obj.submit();
			}
			else
				alert("请选择团队");
		}
		
		function cb(arr,msg){	
			if(msg.show_msg){
				if(msg.msg_content=="")
					window.location.reload();
				else alert(msg.msg_content);
			}		
			window.location.reload();			
		}
		
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
				
		var cols = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: "<%=path%>/company/notification_manage?method=addNotification",
					refresh: cb,
			}},	
			{id:"TIDS", init:{}},
			{id:"MESSAGE", tips:{action:ims_show_warn, text:"通知内容为1～500个文字"},
				init:{minLength:1, maxLength:500, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_length}},
		];
		
		
		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);		
		}
</script>
<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>