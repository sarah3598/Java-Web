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
<title>Tools</title>
</head>

<body>

	<%@include file="yb_db_nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">工具</h1>

				<div class="container">
					<ul id="WEBSITE_CONTROL" class="nav nav-pills">
						<li class="active"><a href="#ANNOUNCEMENT" data-toggle="tab">
								通知 </a></li>
						<li><a href="#INFO" data-toggle="tab"> 信息统计 </a></li>
						<li><a href="#ORDERTRACK" data-toggle="tab"> 物流跟踪 </a></li>
					</ul>
					<div id="myTabContent" class="tab-content">
						<div class="tab-pane fade in active" id="ANNOUNCEMENT">
							<br />
							<br />
							<div class="container">
								<div class="col-sm-7">
									<form class="form-horizontal" id="form" role="form">
										<input type="hidden" name="ROLE" id="ROLE" value="1" />
										<div class="form-group">
											<label class="col-sm-2 control-label">发给</label>
											<div class="col-sm-10">
												<input type="text" class="form-control" id="RECIPIENTS"
													name="RECIPIENTS" placeholder="请输入邮箱"></input> <span
													class="text-danger" style="display: none;">请输入正确邮箱</span>
											</div>
										</div>
										<div class="form-group">
											<label for="lastname" class="col-sm-2 control-label">内容</label>
											<div class="col-sm-10">
												<textarea class="form-control" id="message" name="message"
													rows="5"></textarea>
												<span class="text-danger" style="display: none;">通知内容不得为空</span>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-2 col-sm-10">
												<button class="btn btn-qubico" type="button"
													onclick="add();">发送</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="INFO">
							<div class="tab-content">
								<div class="container">
									<br />
									<h2>
										<span class="label label-primary">客户统计</span>
									</h2>
									<p>此处应为按地区的统计</p>
									<p>按年龄段统计</p>
									<p>按性别统计</p>
									<h2>
										<span class="label label-success">新用户统计</span>
									</h2>
									<p>此处应为新用户的数据</p>
									<h2>
										<span class="label label-info">销售数据汇总</span>
									</h2>
									<p>此处应为销售数据统计</p>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" id="ORDERTRACK">
							<div class="tab-content">
								<div class="container">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>序号</th>
												<th>商品</th>
												<th>ID</th>
												<!-- 对应数据库表里无此字段 -->
												<th>数量</th>
												<th>物流状态</th>
											</tr>
										</thead>
										<tbody id="tracking">
											<script id="track" type="text/x-handlebars-template">
			    								{{#each this}}
                								<tr>
                  									<td>{{addOne @index}}</td>
                  									<td>{{TITLE}}</td>
                  									<td>{{GOOD_ID}}</td>
                  									<td>{{QUANTITY}}</td>
                  									<td>
														<button class="btn btn-info">点击查看物流状态</button>
				  									</td>
               									</tr>
												{{/each}}
			  								</script>
										</tbody>
									</table>

								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script>
		var obj, rows = [];
		function ims_show_warn(){
			this.nextElementSibling.style.display = "";
		}
		function verify(){
			var o = validator.is_length(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		function verify2(){
			var o = validator.is_email(validator.trim(this.value), this.minLength, this.maxLength);
			o ? this.nextElementSibling.style.display = "none" : this.nextElementSibling.style.display = "";
		}
		
		function add(){
			obj.submit();
		}
		var cols = [
			{id:"form", 
				submit: {
					postform: submitWindow,
					url: '/account/register',
					refresh: false,
				}},	
			{id:"ROLE", init:{}},
			
			{id:"message", tips:{action:ims_show_warn, text:"姓氏"},
				init:{minLength:1, maxLength:1000, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify},
				submit:{formatter:validator.trim, check:validator.is_length}},
			{id:"RECIPIENTS", tips:{action:ims_show_warn, text:"电子邮箱"},
				init:{minLength:1, maxLength:50, size:32, allowNull:false},
				event:{id:"onkeyup",fn:verify2},
				submit:{formatter:validator.trim, check:validator.is_email}},
			
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
	</script>

	<script>
    $(document).ready(function () {
        $(window).scroll(function() {
        if ($(this).scrollTop() >= 50) { 
            $('#main-nav').addClass('header-border');
        }else
            $('#main-nav').removeClass('header-border');
        });
    });
    </script>
    <script>
		var rows = [{"TITLE":"口红","GOOD_ID":"7865932","QUANTITY":"34"},
					{"TITLE":"口红","GOOD_ID":"7865932","QUANTITY":"23"}];
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		var track_o = document.getElementById('track');
		var track_t = Handlebars.compile(track_o.innerHTML);
		var tracking = document.getElementById('tracking');
		tracking.innerHTML = track_t(rows);
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