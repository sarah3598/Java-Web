<%@ page language="java" import="java.util.*,com.csh.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
    PageModel pageModel =(PageModel)request.getAttribute("page");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="baseHeader_1.jsp"></jsp:include>

<script type="text/javascript">
  function ff(val){
	 alert("ff->"+val);
     window.location.href="<%=basePath%>servlet/webreportusers?method=listWebUsers&currentPage="+val;
  }
  

 function auditUser(userId)
 {
	 alert("auditUser->"+userId);
	// auditUser(${pe.userId})
	 window.location.href="<%=basePath%>servlet/webreportusers?method=auditWebUsers&userId="+userId;
	
 }
 

  function showEnroll(userId)
 {
	 alert("showEnroll->"+userId);
	// auditUser(${pe.userId})
	// window.location.href="<!--%=basePath%>servlet/webreportusers?method=auditWebUsers&userId="+userId;
	
 }
  
 function searchCondition(){
	 var vreportLevelSe=$("#reportLevelSe").val();
	 var vreportDistrictSe=$("#reportDistrictSe").val();
	 //var vreportStatusSe=$("#reportStatusSe").val();
	 var vreportDaySe=$("#reportDaySe").val();
	   alert("ff->1--"+vreportLevelSe);
	   alert("ff->2--"+vreportMonthSe);
	   //alert("ff->3--"+vreportStatusSe);
	   alert("ff->4--"+vreportDaySe);
	 
	    window.location.href="<%=basePath%>servlet/webreportusers?method=listSearchWebUsers&reportLevelSe="+vreportLevelSe+"&reportDistrictSe="+vreportDistrictSe+"&reportStatusSe="+vreportStatusSe+"&reportDaySe="+vreportDaySe;
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
						<td>按级别：</td>
						
						<td>
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
						<td>按地区：</td>
						<td>
									<select id="reportDistrictSe" style="width: 80px;">
										<option value="0" selected>
											全部
										</option>
										<option value="1">
											中国
										</option>
										<option value="2">
											外国
										</option>
									</select>
								</td>
						<td>日期：</td>
						<td><input type="text"  id="reportDaySe" class="easyui-datebox" style="width: 100px;"/></td>
						<td>-</td>
						<!--td><input type="text"  id="noticeDateTwoFrom" name="noticeDateTwoFrom" class="easyui-datebox" style="width: 100px;"/></td-->
						<td><a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchCondition()" >查询</a></td>
		 				<td><a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="cleardivform('#searchform')">清除</a></td>
		 				<!--td><input type="button" value="删除" onclick="deleteEnroll(${pe.userId})" /></td>
				        <td><input type="button" value="预览" onclick="showEnroll(${pe.userId})"/></td-->
					</tr>
				</table>
			</form>
			</div>			
				
			<div class="margin-top STYLE1" id="list2">

					<table cellspacing="0" cellpadding="0" width="130%">
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
							         <input type="button" value="审核" onclick="auditUser(${pe.userId})" />
							         &nbsp;&nbsp;
							         <input type="button" value="详细" onclick="showEnroll(${pe.userId})"/>
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


