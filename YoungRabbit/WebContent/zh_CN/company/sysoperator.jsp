<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!-- %@ taglib prefix ="s" uri="/struts-tags"% -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>用户管理</title>
		<style type="text/css" title="currentStyle">
			@import "js/easyui/themes/default/easyui.css";
			@import "js/easyui/themes/icon.css";
			@import "js/easyui/demo/demo.css";
			@import "css/boxy.css";
			@import "css/boxystatic.css";
	</style> 
	
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="css/boxy.css">
	<script type="text/javascript" src="js/jquery.js"></script>
	
	<script type="text/javascript" src="js/public.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
   <script type="text/javascript" src="js/sysrole/menuFun.js"></script>
   <script type="text/javascript" src="js/sysuser/sysoperator1.js"></script>
	<script type="text/javascript">
  </script> 
  
</head>


<body class="easyui-layout">
<input type="hidden" id="usId" name="usId"/>
<div  region="west" iconCls="icon-reload" title="机构" split="true" style="width:200px;">

		<ul id="ultree" class="easyui-tree" >  
		<url="json/treedata.json">
		</ul>
</div>


<div d="divsearch" region="north" >
<form action="" id="searchform">
<table id="dataform"  class="table_d">
<tr>

<td>
按员工编号</td><td>
<input type="text" name="operatorNoSe" id="operatorNoSe" style="width: 80px;"/></td><td>
按员工姓名</td><td>
<input type="text" name="operatorNameSe" id="operatorNameSe" style="width: 80px;"/></td><td>
 <a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchCondition2()">查询</a></td>
 <td><a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="cleardivform('#searchform')">清除</a></td>
</tr>
</table>
</form>
</div>
   <div id="divtable" region="center"  title="操作员管理" class="datagrid-wrap panel-body" style="background:#fafafa;overflow:hidden">
   
   <div id="tabbar1" class="datagrid-toolbar">
                    <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)" onClick="openAdd_oper()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-add">增加</span></span></a>
                   
                    <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)"   onclick="openEdit_oper()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-edit">修改</span></span></a>
                 
                   <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)"   onclick="deleterecord()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-remove">删除</span></span></a>
   </div>  
     
   <div>  
   <div id="tabbar" class="dialog-toolbar">           
  <table id="list_sub_table" width="100%">
  </table>
  
  </div>
   </div> 
   <div id="divform" icon="icon-save" style="padding:5px;width:550px;height:500px;display:none;">
    <form id="dataform" method="post">

   <input type="hidden" id="operatorId" name="operatorId" />
    <input type="hidden" id="id" name="id" />
 		<table width="100%" class="table_d">
            <tr>
                <td>所属部门ID</td><td>
                <input type="text" id="branchNo" name="branchNo" class="easyui-validatebox tab_txt_d" required="true" missingMessage="所属部门ID不能为空！" disabled="disabled"/></td>
                <td>部门名称</td>
                <td><input type="text" id="branchName" name="branchName" class="easyui-validatebox tab_txt_d" required="true" missingMessage="部门名称不能为空！" disabled="disabled"/></td>
            </tr>
             <tr>  
                <td>操作员编号</td>
                <td>
                  <input id="operatorNo" name="operatorNo" class="easyui-validatebox tab_txt_d" style="width:130px;" required="true" missingMessage="操作员编号不能为空！"/>
                </td>
                <td>操作员密码</td>
                <td><input type="password" id="operatorPwd" name="operatorPwd" class="easyui-validatebox tab_txt_d"  required="true" missingMessage="密码不能为空！"/></td>
               
            </tr>
             <tr>
                <td>操作员状态</td>
                <td>
                 <input id="operStatus" name="operStatus" checked type="radio" value="1">有效
                 <input id="operStatus" name="operStatus" type="radio" value="0">无效
                </td>
                 <td>操作员种类</td>
                <td>
                <select id="operatorKind">
                 <option value="0">
                                                       委外
                 </option>
                 <option value="1">
                                                       委内
                 </option>
               </select>
                
                </td>
            </tr>
            
            <tr>
             <td>
                                           角色
             </td>
             <td >
              <input type="text" id="operatorRole" name="operatorRole" 
              class="easyui-validatebox tab_txt_d" />  
            </td>
            <td>真实姓名：</td>
                <td><input type="text" id="operatorName" name="operatorName" 
                class="easyui-validatebox tab_txt_d" required="true" 
                missingMessage="登陆名不能为空！"/></td>
           
            </tr>
         </table>
        </form>
       <div class="display_form" id="div_administrator"  style="width: 100%;height: 300px; overflow:auto;">
	<ul id="ultreerole" class="easyui-tree" >  
	<url="json/treedata.json"style="display:none">
	<ul>
	</div>
  
	</div>

</body>
</html>