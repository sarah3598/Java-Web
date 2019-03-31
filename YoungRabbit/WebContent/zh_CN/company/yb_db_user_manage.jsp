<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<!-- saved from url=(0041)http://www.youngrabbit.com/zh_CN/admin/dashboard/ -->
<html lang="zh-CN">
<head>
<%@include file="yb_db_head_include.jsp"%>
<title>user management</title>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>用户管理</h2>  
		<ul id="WEBSITE_CONTROL" class="nav nav-tabs">
			<li class="active"><a href="#LIST" data-toggle="tab">用户列表 </a></li>
			<li><a href="#REM" data-toggle="tab"> 酬金 </a></li>
		</ul>
		<div id="myTabContent1" class="tab-content">
			<!--用户列表-->
			<div class="tab-pane fade in active" id="LIST">
				<br /> <br />
				<!--用户列表下的二级菜单-->
				<ul id="WEBSITE_CONTROL" class="nav nav-pills">
					<li class="active"><a href="#SALES" data-toggle="tab">
							销售 </a></li>
					<li><a href="#ADMIN" data-toggle="tab"> 管理员 </a></li>
					<li><a href="#MEMBER" data-toggle="tab"> 会员 </a></li>
				</ul>
				<!--用户列表下的二级菜单结束-->
				<!-- 二级菜单内容-->
				<div id="myTabContent2" class="tab-content">
					<!--销售-->
					<div class="tab-pane fade in active " id="SALES">
						<br> <br> 
						<div class="col-md-7">
							<button class="btn btn-default ">添加销售人员</button>
						</div>
						<div class="form-inline" col-md-5>
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									条件过滤 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">按排名</a></li>
									<li><a href="#">按状态</a></li>
								</ul>
							</div>
							<input type="text" class="form-control " placeholder="请输入搜索内容"></input>
							<button class="btn btn-default">搜索</button>
							<button class="btn btn-primary">搜索最佳销售</button>
						</div>
						<br /> <br />
						<table class="table table-striped">
							<thead>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>ID</th>
									<th>性别</th>
									<!-- 对应数据表里无此字段 -->
									<th>等级</th>
									<th>状态(活跃，暂停，删除)</th>
								</tr>
							</thead>
							<tbody id="sales">
								<script id="tpl_sales" type="text/x-handlebars-template">
									{{#each this}}
										<tr>
											<td>{{addOne @index}}</td>
											<td>{{LAST_NAME}} {{FIRST_NAME}}</td>
											<td>{{USER_ID}}</td>
											<td>{{SEX}}</td>
											<td>{{RANK}}</td>
											<td>{{STATUS}}</td> 									
										</tr>
									{{/each}}
									 </script>
							</tbody>
						</table>
					</div>
					<!--销售结束-->
					<!--管理员-->
					<div class="tab-pane fade" id="ADMIN">
						<br> <br>
						<div class="col-md-7">
							<button class="btn btn-default ">添加管理员</button>
						</div>
						<div class="form-inline" col-md-5>
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									条件过滤 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">按角色</a></li>
									<li><a href="#">按地位</a></li>
								</ul>
							</div>
							<input type="text" class="form-control " placeholder="请输入搜索内容"></input>
							<button class="btn btn-default">搜索</button>
						</div>
						<br /> <br />
						<table class="table table-striped">
							<thead>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>ID</th>
									<th>性别</th>
									<!-- 对应数据表里无此字段 -->
									<th>角色</th>
									<th>状态(活跃，暂停，删除)</th>
								</tr>
							</thead>
							<tbody id="admin">
								<script id="tpl_admin" type="text/x-handlebars-template">
									{{#each this}}
										<tr>
											<td>{{addOne @index}}</td>
											<td>{{LAST_NAME}} {{FIRST_NAME}}</td>
											<td>{{USER_ID}}</td>
											<td>{{SEX}}</td>
											<td>{{ROLE}}</td>
											<td>{{STATUS}}</td> 									
										</tr>
									{{/each}}
									 </script>
							</tbody>
						</table>
					</div>
					<!--管理员结束-->
					<!--会员-->
					<div class="tab-pane fade" id="MEMBER">
						<br> <br>
						<div class="col-md-7"></div>
						<div class="form-inline" col-md-5>
							<div class="btn-group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown">
									条件过滤 <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="#">按会员类型</a></li>
									<li><a href="#">按状态</a></li>
								</ul>
							</div>
							<input type="text" class="form-control " placeholder="请输入搜索内容"></input>
							<button class="btn btn-default">搜索</button>
						</div>
						<br /> <br />
						<table class="table table-striped">
							<thead>
								<tr>
									<th>序号</th>
									<th>姓名</th>
									<th>ID</th>
									<th>性别</th>
									<!-- 对应数据表里无此字段 -->
									<th>会员类型</th>
									<th>状态(活跃，暂停，删除)</th>
								</tr>
							</thead>
							<tbody id="member">
								<script id="tpl_member" type="text/x-handlebars-template">
									{{#each this}}
										<tr>
											<td>{{addOne @index}}</td>
											<td>{{LAST_NAME}} {{FIRST_NAME}}</td>
											<td>{{USER_ID}}</td>
											<td>{{SEX}}</td>
											<td>{{MEMBER_TYPE}}</td>
											<td>{{STATUS}}</td> 									
										</tr>
									{{/each}}
									 </script>
							</tbody>
						</table>
					</div>
					<!--会员-->
				</div>
				<!-- 二级菜单内容结束-->
			</div>
			<!--用户列表结束-->
			<!--酬金结束-->
			<div class="tab-pane fade" id="REM">
				<div class="tab-content">
					<br /> <br />
					<h3>
						<span class="label label-primary">设置代理基本工资</span>
					</h3>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" class="form-control"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">确定!</button>
							</span>
						</div>
					</div>
					<br /> <br /> <br />
					<h3>
						<span class="label label-primary">设置奖金图表</span>
					</h3>
					<div class="col-md-3">
						<div class="input-group">
							<input type="text" class="form-control"> <span
								class="input-group-btn">
								<button class="btn btn-default" type="button">确定!</button>
							</span>
						</div>
					</div>
				</div>
			</div>
			<!--酬金结束-->
		</div>
	</div>
	<script>
		var rows = [{"FIRST_NAME":"Ming","LAST_NAME":"Li","USER_ID":"756942368","SEX":"女","RANK":1,"STATUS":"活跃"},
					{"FIRST_NAME":"Lei","LAST_NAME":"Han","USER_ID":"536987125","SEX":"男","RANK":4,"STATUS":"删除"}];
	 
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		
		var tpl_saleso = document.getElementById('tpl_sales');
		var tpl_salest = Handlebars.compile(tpl_saleso.innerHTML);
		var sales = document.getElementById('sales');
		sales.innerHTML = tpl_salest(rows);
    </script>
	<script>
		var rows = [{"FIRST_NAME":"Ming","LAST_NAME":"Li","USER_ID":"756942368","SEX":"女","ROLE":1,"STATUS":"活跃"},
					{"FIRST_NAME":"Lei","LAST_NAME":"Han","USER_ID":"536987125","SEX":"男","ROLE":4,"STATUS":"删除"}];
	 
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		
		var tpl_admino = document.getElementById('tpl_admin');
		var tpl_admint = Handlebars.compile(tpl_admino.innerHTML);
		var admin = document.getElementById('admin');
		admin.innerHTML = tpl_admint(rows);
    </script>
	<script>
		var rows = [{"FIRST_NAME":"Ming","LAST_NAME":"Li","USER_ID":"756942368","SEX":"女","MEMBER_TYPE":1,"STATUS":"活跃"},
					{"FIRST_NAME":"Lei","LAST_NAME":"Han","USER_ID":"536987125","SEX":"男","MEMBER_TYPE":4,"STATUS":"删除"}];
	 
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		var tpl_membero = document.getElementById('tpl_member');
		var tpl_membert = Handlebars.compile(tpl_membero.innerHTML);
		var member = document.getElementById('member');
		member.innerHTML = tpl_membert(rows);
    </script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="<%=path %>/js/jquery.min.js"><\/script>')
	</script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=path%>/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>