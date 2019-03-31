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

 <%@include file="yb_db_head_include.jsp" %>
<title>Staff Dashboard</title>

</head>

<body>

	 <%@include file="yb_db_nav.jsp" %>

	<div class="container-fluid">
		<div class="row">
			 <%@include file="yb_db_left_nav.jsp" %>
			 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">Dashboard</h1>
          	<div class="form-horizontal">				
						<div class="form-group">
							<div class="col-md-4">
								<input class="form-control largcontrol" id="USERNAME" name="USERNAME" placeholder="员工姓名" type="text">								
							</div>
							<div class="col-md-8">
								<button type="button" class="btn btn-sm btn-success" onclick="query_user();">查询</button>
							</div>
						</div>	
			</div>											
          <div class="table-responsive" style="border: 1px solid #eee;">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>姓名</th>
                  <th>邮箱</th>
                  <th>电话</th>
                  <th>类型</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody id="test">
			  <script id="tpl" type="text/x-handlebars-template">
			    {{#each this}}
                <tr>
                  <td>{{addOne @index}}</td>
                  <td>{{LAST_NAME}} {{FIRST_NAME}}</td>
                  <td>{{EMAIL}}</td>
                  <td>{{PHONE}}</td>
                  <td>{{help_role ROLE}}</td>
                  <td>
					<button type="button" class="btn btn-sm btn-success" onclick="upd_user('{{USER_ID}}');">修改</button>
					<button type="button" class="btn btn-sm btn-danger" onclick="del_user('{{USER_ID}}');">删除</button>
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
	<script>
		var rows = ${user_list};
	 
		function upd_user(USER_ID){
			alert("修改用户信息" + USER_ID);
		}
		function cb(arr){
			test.innerHTML = tplt(arr);
		}
		
		function d_cb(arr,msg){	
			if(msg.show_msg){
				alert(msg.msg_content);
			}
			test.innerHTML = tplt(arr);
		}
		
		function del_user(USER_ID){
			if(confirm("确定要删除该用户吗？")){
				var params = ["method=deleteCompanyUsers&userId=", USER_ID].join("");
				ims_submit_ajax("<%=path%>/company/users_manage", params, d_cb);	
			}
		}
		function query_user(){
			var userName=document.getElementById('USERNAME').value;			
			var params = ["method=queryCompanyUsers&userName=",userName].join("");
			ims_submit_ajax("<%=path%>/company/users_manage", params, d_cb);	
			
		}
		
		Handlebars.registerHelper("addOne",function(index){
			return parseInt(index) + 1;
        });
		Handlebars.registerHelper("help_role", function (ROLE) {
			if (ROLE == 1)
				return "实习员工";
			else if (ROLE == 2)
				return "正式员工";
			else if (ROLE == 4)
				return "S.M.";
			else if (ROLE == 8)
				return "S.V.P";
			else if (ROLE == 128)
				return "批发商";
			else if (ROLE == 256)
				return "代理商";
			else if (ROLE == 512)
				return "管理员";
			else if (ROLE == 1024)
				return "超级管理员";
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
    <script src="<%=path %>/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="<%=path %>/js/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="<%=path %>/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>