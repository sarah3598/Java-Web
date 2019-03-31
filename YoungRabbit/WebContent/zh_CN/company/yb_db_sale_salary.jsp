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
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<h2>个人工资</h2>
		<div id="rule_list" class="table-responsive">
			<table class="table table-striped" style="border: 1px solid #eee;">
				<thead>
				<tr>
					<th>姓名</th>
					<th>级别</th>
					<th>时间</th>
					<th>额外奖金</th>
					<th>销售佣金</th>
					<th>覆盖佣金</th>
					<th>总计</th>				
				</tr>
				</thead>
				<tbody id="test">
					<script id="tpl" type="text/x-handlebars-template">
					{{#each this}}
						<tr>
						  <td>{{USERNAME}}</td>
						  <td>{{help_role GROLE}}</td>
						  <td>{{MONTH}}</td>
						  <td>{{BONUS}}</td>
						  <td>{{SALE_BROKERAGE}}</td>
						  <td>{{COVER_BROKERAGE}}</td>
						  <td>{{SUM}}</td>
						</tr>
					{{/each}}
					</script>
				</tbody>
			</table>
			<!-- 分页开始 -->
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<li><select style="width: 150px;" class="form-control largcontrol" id="PAGE_SIZE" name="PAGE_SIZE"></select></li>
					<li><span aria-hidden="true"> 当前是第：<span id="CURRENT_PAGE"><%=paging.getCurrentPage()%></span>页|共<%=paging.getTotalPage()%>页
					</span></li>
					<li><a style="cursor: pointer" onclick="pagination(1,<%=paging.getPageSize()%>);return false;">首页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getFirstPage()%>,<%=paging.getPageSize()%>);return false;">上一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getDownPage()%>,<%=paging.getPageSize()%>);return false;">下一页</a></li>
					<li><a style="cursor: pointer" onclick="pagination(<%=paging.getTotalPage()%>,<%=paging.getPageSize()%>);return false;">尾页</a></li>
				</ul>
			</nav>
			<!-- 分页结束 -->
		</div>
    </div>
	<script>
		var obj;
		<%String sale_salary_json = (String) request.getAttribute("sale_salary_list");
		if (sale_salary_json == "") {
			sale_salary_json = "\"no_data\"";
		}%>
	    var  rows =<%=sale_salary_json%>;		
		/* var	rows = [{"SALARY_ID":2,"USERNAME":"Tom2","GROLE":8,"MONTH":"201706","BONUS":4000,"SALE_BROKERAGE":120,"COVER_BROKERAGE":110,"PERSONAL_SALE":0,"GROUP_SALE":80000,"ESPECIAL_REWARD":0,"SUM":10000},
					{"SALARY_ID":3,"USERNAME":"Tom3","GROLE":4,"MONTH":"201705","BONUS":3000,"SALE_BROKERAGE":200,"COVER_BROKERAGE":110,"PERSONAL_SALE":0,"GROUP_SALE":60000,"ESPECIAL_REWARD":0,"SUM":10000},
					{"SALARY_ID":4,"USERNAME":"Tom4","GROLE":2,"MONTH":"201704","BONUS":2000,"SALE_BROKERAGE":200,"COVER_BROKERAGE":120,"PERSONAL_SALE":0,"GROUP_SALE":20000,"ESPECIAL_REWARD":0,"SUM":10000},
					{"SALARY_ID":5,"USERNAME":"Tom5","GROLE":1,"MONTH":"201703","BONUS":1000,"SALE_BROKERAGE":100,"COVER_BROKERAGE":200,"PERSONAL_SALE":1000,"GROUP_SALE":0,"ESPECIAL_REWARD":0,"SUM":10000},
				   ];
		 */
		Handlebars.registerHelper("help_role", function (GROLE) {
			if (GROLE == 0)
				return "入职培训";
			else if (GROLE == 1)
				return "销售代理";
			else if (GROLE == 2)
				return "独家销售代理";
			else if (GROLE == 4)
				return "组长";
			else if (GROLE == 8)
				return "经理";
			else if (GROLE == 16)
				return "副总裁";
			else
				return "";
		});
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		//test.innerHTML = tplt(rows);	
		if(rows.length==0){
			test.innerHTML="暂无数据！";
		}
		else{
			test.innerHTML = tplt(rows);
		}
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/sale_salary?current_page="+current_page+"&page_size="+page_size;
		}
		
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/sale_salary?page_size="+page_size;
		});
		
		var arr0 = [["5","每页5条"],["10","每页10条"],["15","每页15条"],["20","每页20条"]];
		
		var cols = [			
			{
				id : "PAGE_SIZE",
				init : {
					multiple : false,
					vdefault : <%=paging.getPageSize()%>,
					value : 0,
					text : 1,
					ds : arr0
				}
			}
			
		];

		window.onload = function() {
			obj = new iform(cols, document.forms[0]);
			obj.init_object(rows);	
		}
    </script>
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>