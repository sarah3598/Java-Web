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
<title>网站管理</title>

</head>

<body>

	<%@include file="yb_db_nav.jsp"%>

	<div class="container-fluid">
		<div class="row">
			<%@include file="yb_db_left_nav.jsp"%>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">网站管理</h1>
				<div class="container">
					<!--一级导航-->
					<ul id="WEBSITE_CONTROL" class="nav nav-pills">
						<li class="active"><a href="#GENERAL" data-toggle="tab">
								首页图片管理 </a></li>
						<!--  <li><a href="#PAGES" data-toggle="tab"> 页面 </a></li>-->
					</ul>
					<!--一级导航结束-->
					<div id="myTabContent1" class="tab-content">
						<!--通用页面-->
						<div class="tab-pane fade in active" id="GENERAL">
							
							<div class="container">
								<div class="row">
									<div
										class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
										<!-- image-preview-filename input [CUT FROM HERE]-->
										<div class="input-group image-preview">
											<input type="text"
												class="form-control image-preview-filename"
												disabled="disabled">
											<!-- don't give a name === doesn't send on POST/GET -->
											<span class="input-group-btn"> <!-- image-preview-clear button -->
												<button type="button"
													class="btn btn-default image-preview-clear"
													style="display: none;">
													<span class="glyphicon glyphicon-remove"></span> Clear
												</button> <!-- image-preview-input -->
												<div class="btn btn-default image-preview-input">
													<span class="glyphicon glyphicon-folder-open"></span> <span
														class="image-preview-input-title">Browse</span> <input
														type="file" accept="image/png, image/jpeg, image/gif"
														name="input-file-preview" />
													<!-- rename it -->
												</div>
											</span>
										</div>
										<br />
										<!-- /input-group image-preview [TO HERE]-->
										<button type="submit" class="btn btn-default pull-right">上传图片</button>
									</div>
								</div>
							</div>
						</div>
						<!--通用页面结束-->
						<!--页面控制-->
						<div class="tab-pane fade" id="PAGES">
							<br />
							<!--页面控制下的二级导航-->
							<ul id="WEBSITE_CONTROL" class="nav nav-tabs">
								<li class="active"><a href="#HOME" data-toggle="tab">
										主页 </a></li>
								<li><a href="#ABOUT_US" data-toggle="tab"> 关于我们 </a></li>
								<li><a href="#PRODUCTS" data-toggle="tab"> 产品 </a></li>
								<li><a href="#AGENT_LIST" data-toggle="tab"> 代理列表 </a></li>
								<li><a href="#MAGAZINE" data-toggle="tab"> 杂志 </a></li>
								<li><a href="#TERMS" data-toggle="tab"> 服务条款 </a></li>
								<li><a href="#CONTACT" data-toggle="tab"> 联系我们 </a></li>
								<li><a href="#FAQ" data-toggle="tab"> 常见问题解答 </a></li>
								<li><a href="#FOLLOW" data-toggle="tab"> 关注我们 </a></li>
								<button class="btn btn-default">编辑</button>
							</ul>
							<!--页面控制下的二级导航结束-->
							<div class="tab-content">
								<!--主页控制-->
								<div class="tab-pane fade in active" id="HOME">
									<br>
									<br>
									<br>
									<div class="container">
										<div class="row">
											<div class="col-xs-12 col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
												<!-- image-preview-filename input [CUT FROM HERE]-->
												<div class="input-group image-preview">
													<input type="text"
														class="form-control image-preview-filename"
														disabled="disabled">
													<!-- don't give a name === doesn't send on POST/GET -->
													<span class="input-group-btn"> <!-- image-preview-clear button -->
														<button type="button"
															class="btn btn-default image-preview-clear"
															style="display: none;">
															<span class="glyphicon glyphicon-remove"></span> Clear
														</button> <!-- image-preview-input -->
														<div class="btn btn-default image-preview-input">
															<span class="glyphicon glyphicon-folder-open"></span> <span
																class="image-preview-input-title">Browse</span> <input
																type="file" accept="image/png, image/jpeg, image/gif"
																name="input-file-preview" />
															<!-- rename it -->
														</div>
													</span>
												</div>
												<br />
												<!-- /input-group image-preview [TO HERE]-->
												<button type="submit" class="btn btn-default pull-right">上传图片</button>
											</div>
										</div>
									</div>
								</div>
								<!--主页控制结束-->
								<!--关于我们-->
								<div class="tab-pane fade" id="ABOUT_US">
									<form class="form-horizontal" role="form">
										<div class="form-group col-lg-3">
											<div class="col-sm-offset-2 col-sm-10">
												<div class="checkbox">
													<label> <input type="checkbox">隐藏
													</label>
													<button type="submit" class="btn btn-default">确认</button>
												</div>
											</div>
										</div>
									</form>

									<div class="col-md-8 column">
										<a id="modal-856382" href="#modal-container-856382"
											role="button" class="btn" data-toggle="modal"><button
												type="button" class="btn btn-default">修改</button></a>
										<div class="modal fade" id="modal-container-856382"
											role="dialog" aria-labelledby="myModalLabel"
											aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal"
															aria-hidden="true">×</button>
														<h4 class="modal-title" id="myModalLabel">关于我们</h4>
													</div>
													<div class="modal-body">内容...</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal">关闭</button>
														<button type="button" class="btn btn-primary">保存</button>
													</div>
												</div>
											</div>
										</div>
										<button type="button" class="btn btn-default">删除</button>
									</div>
								</div>
								<!--关于我们结束-->
								<!-- 商品控制-->
								<div class="tab-pane fade" id="PRODUCTS">
									<br>
									<!--产品下的三级菜单-->
									<ul id="WEBSITE_CONTROL" class="nav nav-tabs">
										<li class="active"><a href="#PRODUCT_EDIT"
											data-toggle="tab"> 产品 </a></li>
										<li><a href="#BUNDLES" data-toggle="tab"> 捆绑 </a></li>
									</ul>
									<!--产品下的三级菜单结束-->
									<div id="myTabContent2" class="tab-content">
										<div class="tab-pane fade in active" id="PRODUCT_EDIT">
											<br>
											<h1>
												<span class="label label-default">商品页面信息</span>
											</h1>
											<button class="btn btn-default">高级设置</button>
											<br>
											<br>
											<button class="btn btn-default">添加商品</button>
											<table class="table table-hover">
												<tbody>
													<tr>
														<td>商品</td>
														<td>
															<button type="button" class="btn btn-primary btn-sm">编辑</button>
														</td>
													</tr>
													<tr>
														<td>商品</td>
														<td>
															<button type="button" class="btn btn-primary btn-sm">编辑</button>
														</td>
													</tr>
													<tr>
														<td>商品</td>
														<td>
															<button type="button" class="btn btn-primary btn-sm">编辑</button>
														</td>
													</tr>
												</tbody>
											</table>
										</div>
										<div class="tab-pane fade" id="BUNDLES">
											<br>
											<p>bundles</p>
										</div>
									</div>
								</div>
								<!-- 商品控制结束-->
								<!--代理列表-->
								<div class="tab-pane fade" id="AGENT_LIST">
									<br />
									<!-- <table class="table table-hover">
					    	<thead>
								<tr>
									<th>
										姓名
									</th>
									<th>
										等级
									</th>
                        			<th>
										加入日期
									</th>
									<th>
										地区
									</th>
									<th>
										联系方式
									</th>
									<th>
										代理语言能力
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<tr >
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table> -->
									<div class="table-responsive" style="border: 1px solid #eee;">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>姓名</th>
													<th>等级</th>
													<th>加入日期</th>
													<th>联系方式</th>
													<th>语言能力</th>
												</tr>
											</thead>
											<tbody id="test">
												<script id="tpl" type="text/x-handlebars-template">
			    {{#each this}}
                <tr>
                  <td>{{addOne @index}}</td>
                  <td>{{LAST_NAME}} {{FIRST_NAME}}</td>
                  <td>{{STATUS}}</td>
                  <td>{{HAPPEN_DATE}}</td>
				  <td>{{PHONE}}</td>
                </tr>
				{{/each}}
			  </script>
											</tbody>
										</table>
									</div>
								</div>
								<!--代理列表结束-->
								<!--杂志-->
								<div class="tab-pane fade" id="MAGAZINE">
									<p>to be developed</p>
								</div>
								<!--杂志结束-->
								<!--服务条款-->
								<div class="tab-pane fade" id="TERMS">
									<p>to be developed</p>
								</div>
								<!--服务条款结束-->
								<!--联系我们-->
								<div class="tab-pane fade" id="CONTACT">
									<p>to be developed</p>
								</div>
								<!--联系我们结束-->
								<!--常见问题解答-->
								<div class="tab-pane fade" id="FAQ">
									<p>to be developed</p>
								</div>
								<!--常见问题解答结束-->
								<!--关注我们-->
								<div class="tab-pane fade" id="FOLLOW">
									<p>to be developed</p>
								</div>
								<!--关注我们-->
							</div>
						</div>
						<!--页面控制结束-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		var rows = [{"USER_ID":"58537e612780b0a4558b4567","FIRST_NAME":"Ming","LAST_NAME":"Li","STATUS":"1","EMAIL":"jianpeng@163.com","PHONE":"18062113650"},
					{"USER_ID":"58d4df8fc77fb0a8158b47b2","FIRST_NAME":"Lei","LAST_NAME":"Han","STATUS":"1","EMAIL":"jianpeng@163.com","PHONE":"18062113655"}];
	 
		function upd_user(USER_ID){
			alert("修改用户信息" + USER_ID);
		}
		
		function del_user(USER_ID){
			if(confirm("确定要删除该用户吗？")){
				alert("执行删除用户" + USER_ID)
			}
		}
		
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		test.innerHTML = tplt(rows);
    </script>
	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="<%=path %>/js/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="<%=path %>
		/js/jquery.min.js"><\/script>')
	</script>
	<script src="<%=path%>/js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<script src="<%=path%>/js/holder.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="<%=path%>/js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>