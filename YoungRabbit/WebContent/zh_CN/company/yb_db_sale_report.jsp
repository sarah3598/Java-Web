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
<title>销售报表</title>
<script src="<%=path%>/js/iform.js"></script>
</head>
<body>
<%@include file="yb_db_nav.jsp"%>
<%@include file="yb_db_left_nav.jsp"%>
	<div class="container">
		<div id="list" class="table-responsive">
			<h2>销售报表</h2>
			<input type="hidden" name="OP" id="OP">
			<table class="table table-striped" style="border: 1px solid #eee;">
				<thead>
					<tr>
						<th>时间</th>
						<th>月报时的级别</th>
						<th>个人月销售额(RM)</th>
						<th>团队月销售额(RM)</th>
						<th>工资总计(RM)</th>
					</tr>
				</thead>
				<tbody id="test">
					<script id="tpl" type="text/x-handlebars-template">
					{{#each this}}
						<tr>
							<td>{{MONTH}}</td>
							<td>{{help_role GROLE}}</td>
							<td>{{#if PERSONAL_SALE}}<a href="javascript:detail_personal('{{MONTH}}');">{{PERSONAL_SALE}}</a>{{else}}{{/if}}</td>
							<td>{{#if GROUP_SALE}}<a href="javascript:detail_group('{{MONTH}}');">{{GROUP_SALE}}</a>{{else}}{{/if}}</td>
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
		<div id="list2" style="display:none">
			<h2 id="stitle"></h2>
			<!-- 详情列表 -->
			<div class="table-responsive">
				<table class="table table-striped" style="border: 1px solid #eee;">
					<thead>
						<tr>
							<th>销售时间</th>
							<th>订单号</th>
							<th>商品名称</th>
							<th>商品金额(RM)</th>
							<th>销售时的级别</th>
						</tr>
					</thead>
					<tbody id="test2">
						<script id="tpl2" type="text/x-handlebars-template">
						{{#each this}}
						<tr>
							<td>{{HAPPEN_DATE}} {{HAPPEN_TIME}}</td>
							<td>{{ORDER_ID}}</td>
							<td>{{TITLE}}</td>
							<td>{{GOOD_MONEY}}</td>
							<td>{{help_type TYPE}}</td>
						</tr>
						{{/each}}
						</script>
					</tbody>
				</table>
			</div>	
		</div>
    </div>
	<script>
		<%String sale_report_json = (String) request.getAttribute("sale_report_list");
		if (sale_report_json == "") {
			sale_report_json = "\"no_data\"";
		}%>
		var rows1 =<%=sale_report_json%>;
		/* var rows = 
			[{"USER_ID":1,"GROLE":4,"MONTH":"201704","PERSONAL_SALE":1000,"GROUP_SALE":100000,"SUM":100},
			{"USER_ID":1,"GROLE":4,"MONTH":"201703","PERSONAL_SALE":2000,"GROUP_SALE":80000,"SUM":100},
			{"USER_ID":1,"GROLE":2,"MONTH":"201702","PERSONAL_SALE":3000,"GROUP_SALE":60000,"SUM":100},
			{"USER_ID":1,"GROLE":1,"MONTH":"201701","PERSONAL_SALE":1000,"GROUP_SALE":20000,"SUM":100},
			];
			 */
					
		function show(flag){
			if(flag == 1){
				list2.style.display = "none";
				list.style.display = "";
			}
			else if(flag == 2){
				list.style.display = "none";
				list2.style.display = "";
			}
		}
					
		function back(){
			list2.style.display = "none";
			list.style.display = "";	
		}
		
		function cb2(arr){
			/* var arr = [
					{"HAPPEN_DATE":"20170401","HAPPEN_TIME":"12:00:00","ORDER_ID":1,"GOOD_ID":1,"GOOD_MONEY":1000,"TYPE":1},
					{"HAPPEN_DATE":"20170402","HAPPEN_TIME":"12:00:00","ORDER_ID":2,"GOOD_ID":2,"GOOD_MONEY":1000,"TYPE":1},
					{"HAPPEN_DATE":"20170403","HAPPEN_TIME":"12:00:00","ORDER_ID":3,"GOOD_ID":3,"GOOD_MONEY":1000,"TYPE":2},
					{"HAPPEN_DATE":"20170404","HAPPEN_TIME":"12:00:00","ORDER_ID":4,"GOOD_ID":4,"GOOD_MONEY":1000,"TYPE":2},
					]; */
//			test2.innerHTML = tplt2(JSON.parse(arr));
			test2.innerHTML = tplt2(arr);
		}
		
		function detail_personal(MONTH){
			stitle.innerHTML = ["<button type='button' style='margin-right:20px;' class='btn btn-sm btn-success' onclick='back();'>返回</button>", MONTH, " 个人月销售详情"].join("");					
			show(2);			
		    var params = ["OP=1&MONTH=", MONTH].join("");
			ims_submit_ajax("<%=path%>/company/sale_report", params, cb2);
			cb2();
		}
		
		function detail_group(MONTH){
			stitle.innerHTML = ["<button type='button' style='margin-right:20px;' class='btn btn-sm btn-success' onclick='show(1);'>返回</button>", MONTH, " 团队月销售详情"].join("");					
			show(2);			
			var params = ["OP=2&MONTH=", MONTH].join("");
			ims_submit_ajax("<%=path%>/company/sale_report", params, cb2);
			cb2();
		}
		
		
		Handlebars.registerHelper("help_role", function (ROLE) {
			if (ROLE == 1)
				return "销售代理";
			else if (ROLE == 2)
				return "独家代理";
			else if (ROLE == 4)
				return "组长";
			else if (ROLE == 8)
				return "经理";
			else if (ROLE == 16)
				return "副总裁";
			else
				return "";
		});
		
		Handlebars.registerHelper("help_type", function (TYPE) {
			if (TYPE == 1)
				return "销售代理销售";
			else if (TYPE == 2)
				return "独家代理销售";
			else if (TYPE == 4)
				return "组长销售";
			else if (TYPE == 8)
				return "经理销售";
			else if (TYPE == 16)
				return "副总裁销售";
			else
				return ""; 
		});
		
		var tplo = document.getElementById('tpl');
		var tplt = Handlebars.compile(tplo.innerHTML);
		var test = document.getElementById('test');
		//test.innerHTML = tplt(rows1);
		if(rows1.length==0){
			test.innerHTML="暂无数据！";
		}
		else{
			test.innerHTML = tplt(rows1);
		}		
		
		var tplo2 = document.getElementById('tpl2');
		var tplt2 = Handlebars.compile(tplo2.innerHTML);
		var test2 = document.getElementById('test2');
		
		function pagination(current_page,page_size){
			window.location.href="<%=path%>/company/sale_report?current_page="+current_page+"&page_size="+page_size;
		}
		
		$( "#PAGE_SIZE" ).change(function() {
			var page_size=this.value;
			window.location.href="<%=path%>/company/sale_report?page_size="+page_size;
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
			obj.init_object(rows1);	
		}
		var tplo2 = document.getElementById('tpl2');
		var tplt2 = Handlebars.compile(tplo2.innerHTML);
		var test2 = document.getElementById('test2');
	</script>	
	<%@include file="yb_db_foot_include.jsp"%>
</body>
</html>