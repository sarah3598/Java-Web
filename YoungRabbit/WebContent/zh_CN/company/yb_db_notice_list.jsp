<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="yb_db_head_include.jsp"%>
<title>通知</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>通知列表</h2>
		<div id="list" class="table-responsive">
			<table class="table" style="border: 1px solid #eee;">
				<thead>
					<tr style="background-color: #f9f9f9;">
						<th style="max-width: 800px; word-wrap: break-word; border-bottom: 1px solid #f9f9f9;">通知</th>
						<th style="border-bottom: 1px solid #f9f9f9;">消息状态</th>
						<th style="width: 200px; border-bottom: 1px solid #f9f9f9;">发布时间</th>
					</tr>
				</thead>
				<tbody id="test">
					<script id="tpl" type="text/x-handlebars-template">
					{{#each this}}
					<tr>
					  <td style="max-width:800px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;border-top:1px dotted #ddd;"><a href="javascript:detail('{{M_ID}}')">{{MESSAGE}}</a></td>
					  <td style="border-top:1px dotted #ddd;">{{help_status STATUS}}</td>				 
					  <td style="width:200px;border-top:1px dotted #ddd;">{{HAPPEN_DATE}} {{HAPPEN_TIME}}</td>
					</tr>
					{{/each}}
					</script>
				</tbody>
			</table>
		</div>
		<div id="list2" style="display: none">
			<button type="button" style="margin-right: 20px;" class="btn btn-sm btn-success" onclick="show(1);">返回</button>
			<h1 class="page-header text-center">通知</h1>
			<!-- 月销售详情列表 -->
			<p class="text-center" id="time"></p>
			<div class="form-horizontal">
				<div class="form-group">
					<div class="col-md-12" id="messages" style="text-indent: 2em;"></div>
				</div>
				<div class="form-group">
					<div class="col-md-12">祝工作顺利！</div>
				</div>
				<div class="form-group">
					<div class="col-md-12" id="tname"></div>
				</div>
			</div>
		</div>
	</div>
	<script>
	<%String notice_list_json = (String) request.getAttribute("notice_list");
	if (notice_list_json == "") {
		notice_list_json = "[]";
	}%>

		var rows1 = <%=notice_list_json%>;

		function show(flag) {
			if (flag == 1) {
				list2.style.display = "none";
				list.style.display = "";
			} else if (flag == 2) {
				list.style.display = "none";
				list2.style.display = "";
			}
		}

		function detail(M_ID) {
			var arr = {};
			for (var i = 0; i < rows1.length; i++) {
				if (rows1[i].M_ID == parseInt(M_ID))
					arr = rows1[i];
			}
			time.innerHTML = [ arr.HAPPEN_DATE, " ", arr.HAPPEN_TIME ].join("");
			messages.innerHTML = arr.MESSAGE;
			tname.innerHTML = arr.USERNAME;
			show(2);
		}

		Handlebars.registerHelper("help_status", function(STATUS) {
			if (STATUS)
				return "未读";
			else
				return "已读";
		});

		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		if(rows1.length>0){
			test.innerHTML = tplt(rows1);
		}else{
			test.innerHTML = "暂无通知！";
		}
	</script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>
