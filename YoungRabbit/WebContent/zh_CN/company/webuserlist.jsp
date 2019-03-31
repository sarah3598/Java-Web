<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
    PageModel pageModel =(PageModel)request.getSession().getAttribute("page");
    
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="baseHeader_1.jsp"></jsp:include>

<script type="text/javascript">
  

function searchCondition111(){
	
	 var vreportLevelSe=$("#reportLevelSe").val();
	 var vreportDistrictSe=$("#reportDistrictSe").val();
	 var vreportDaySe=$("#reportDaySe").val();
	   
	 window.location.href="<%=basePath%>servlet/webreportusers?method=listSearchWebUsers&reportLevelSe="+vreportLevelSe+"&reportDistrictSe="+vreportDistrictSe+"&reportDaySe="+vreportDaySe;
 }

function ff(val){
	
    window.location.href="<%=basePath%>servlet/webreportusers?method=listWebUsers&currentPage="+val;
  }
  
function addUser()
 {

	 window.location.href="<%=basePath%>jsp/webUseradd.jsp";
	
 }
  
  
function deleUser(userId)
 {

	 window.location.href="<%=basePath%>servlet/webreportusers?method=deleteWebUsers&userId="+userId;
	
 }
  
function showUser(userId)
 {

	 window.location.href="<%=basePath%>servlet/webreportusers?method=editWebUsers&userId="+userId;
	
 }
  
 
    

</script>
<html>
	<body>
		<div class="wapper cbody">
			<div class="Ccontent left margin-top">
				<div id="position">
					<p>
						<a href="jsp/login.jsp">首页</a>&gt;
						<a href="jsp/login.jsp">用户信息列表</a>&gt;
					</p>
				</div>
			<div class="margin-top STYLE1" id="divsearch" region="north" >
		    <form action="" id="searchform">
				<table id="dataform"  class="table_d">
					<tr>
						<td style="width: 5%">Rank：</td>
						
						<td style="width: 10%">
									<select id="reportLevelSe" style="width: 80px;">
										<option value="0" selected>
											全部
										</option>
										<option value="1">
											Administrator
										</option>
										<option value="2">
											SalesAgent
										</option>
										<option value="3">
											WholeSaler
										</option>
										<option value="4">
											Customer
										</option>
									</select>
								</td>
						<td style="width: 5%">Status：</td>
						<td style="width: 10%">
									<select id="reportDistrictSe" style="width: 80px;">
										<option value="9" selected>
											全部
										</option>
										<option value="0">
											未审核
										</option>
										<option value="1">
											审核通过
										</option>
									</select>
						</td>
						<td style="width: 5%">Email：</td>
						<td style="width: 10%"><input type="text"  id="reportDaySe" class="easyui-datebox" style="width: 100px;"/></td>
						<td>-</td>
					
						<td style="width: 5%"><a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchCondition111()" >查询</a></td>
		 				<td style="width: 5%"><a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="cleardivform('#searchform')">清除</a></td>
		 			
				        <td style="text-align:right"><input type="button" value="增加用户" onclick="addUser()"/></td>
					</tr>
				</table>
			</form>
			</div>			
				
			<div class="margin-top STYLE1" id="list2">

					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<th style="text-align: left; padding-left: 15px;" width="63">
									序号
								</th>
								<th style="text-align: left; padding-left: 15px;" width="86">
									用户号
								</th>
								<th width="76">
									邮箱
								</th>
								<th width="45">
									姓名
								</th>
								<th width="300px">
									电话
								</th>
									<th width="76">
									地址
								</th>
								<th width="45">
									城市
								</th>
								<th width="300px">
									国家
								</th>
								<th width="150" align="left">
									是否合同
								</th>
								<th width="150" align="left">
									级别
								</th>
								<th width="350" align="left">
									操作
								</th>
							</tr>
							<c:forEach items="${page.list}" var="pe" varStatus="status">
							  <tr class="alist00" align="center">
							      <td>
							         ${status.count}
							      </td>
							      <td>
							         ${pe.userId}
							      </td>
							      <td>
							         ${pe.email}
							      </td>
							       <td>
							         ${pe.userName}
							      </td>
							       <td>
							         ${pe.phone}
							      </td>
							      <td>
							         ${pe.address}
							      </td>
							       <td>
							         ${pe.city}
							      </td>
							       <td>
							         ${pe.country}
							      </td>
							      <td>
							         ${pe.contract}
							      </td>
							      <td>
							         ${pe.status} 
							      </td>
							           <td>
							         <input type="button" value="删除" onclick="deleUser(${pe.userId})" />
							         &nbsp;&nbsp;
							         <input type="button" value="编辑详细" onclick="showUser(${pe.userId})"/>
							      </td>
							  </tr>							
							</c:forEach>
							     
						</tbody>
					</table>
					当前：<%=pageModel.getCurrentPage() %>页|共<%=pageModel.getTotalPage() %>页
					<a style="cursor:pointer"  onclick="ff(1);return false;">首页</a>
					<a style="cursor:pointer" onclick="ff(<%=pageModel.getFirstPage() %>);return false;">上一页</a>
					<a style="cursor:pointer" onclick="ff(<%=pageModel.getDownPage() %>);return false;">下一页</a>
					<a style="cursor:pointer" onclick="ff(<%=pageModel.getTotalPage() %>);return false;">尾页</a>
				</div>
			</div>
		</div>
	</body>
</html>


