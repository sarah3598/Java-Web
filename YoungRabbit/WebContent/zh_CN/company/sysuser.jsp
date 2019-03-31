<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			

%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>用户管理</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/easyui/demo/demo.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/boxy.css">
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/easyui/jquery.easyui.min.js"></script>
     <script type="text/javascript" src="<%=basePath%>js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/sysuser/sysuser.js"></script>
   
</head>

<body class="easyui-layout">
<input type="hidden" id="usId" name="usId"/>
<div  region="west" iconCls="icon-reload" title="机构树" split="true" style="width:200px;">

		<ul id="ultree" class="easyui-tree" ></ul>
</div>
	
	
	<div d="divsearch" region="north" >
<form action="" id="searchform">
<table id="dataform"  class="table_d">
<tr>
<td>
按工号：</td><td>
<input type="text" name="workId" id="workId" style="width: 80px;"/></td><td>
按姓名：</td><td>
<input type="text" name="name" id="name" style="width: 80px;"/></td><td>

 <a class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="searchCondition()" >查询</a></td>
 <td><a class="easyui-linkbutton" plain="true" iconCls="icon-cancel" onclick="cleardivform('#searchform')">清除</a></td>
</tr>
</table>
</form>
</div>
   <div id="divtable" region="center"  title="用户管理" class="datagrid-wrap panel-body" style="background:#fafafa;overflow:hidden">
   
   <!--div class="datagrid-toolbar">
                    <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)" onClick="openAdd()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-add">增加</span></span></a>
                   
                    <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)"   onclick="openEdit()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-edit">修改</span></span></a>
                   
                     <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)"   onclick="contentdept()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-edit">关联科室</span></span></a>
                   
                   <a style="float: left;" id="undefined" class="l-btn l-btn-plain" href="javascript:void(0)"   onclick="deleterecord()"><span class="l-btn-left"><span style="padding-left: 20px;" class="l-btn-text icon-remove">删除</span></span></a>
                    
             </div-->  
           <div>    
  <table id="list_sub_table" width="100%">
  </table>
  
  </div>
   <!--input type="button" id="ss" onClick="return getSelected()" value="选中"/-->
   </div> 
   <div id="divform" icon="icon-save" style="padding:5px;width:550px;height:600px;display:none;">
    <form id="dataform" method="post">
   <input type="hidden" id="organId" name="organId"/>
    <input type="hidden" id="loginTimes" name="loginTimes" value="0"/>
   <input type="hidden" id="levelnum" name="levelnum" value="0"/>
   <input type="hidden" id="fullTimeOrPartTimeJob" name="fullTimeOrPartTimeJob" value="1"/>
   <input type="hidden" id="companyId" name="companyId" />
   <input type="hidden" id="userId" name="userId" />
		<table width="100%" class="table_d">
            <tr>
                <td>用户名：</td><td>
                <input type="text" id="username" name="user.username" class="easyui-validatebox tab_txt_d" required="true" missingMessage="用户名不能为空！"/></td>
                <td>登陆名：</td><td><input type="text" id="loginName" name="loginName" class="easyui-validatebox tab_txt_d" required="true" missingMessage="登陆名不能为空！"/></td>
            </tr>
             <tr>
                <td>用户角色：</td>
                <td>
                 <input id="userRole" name="userRole" class="easyui-combotree tab_txt_d " required="true" missingMessage="用户角色不能为空！" /></td>
                 <td>员工编号：</td>
                <td>
                  <input id="userNo" name="userNo" class="tab_txt_d" style="width:130px;" />
                </td>
            </tr>
             <tr>
                <td>密码：</td><td><input type="password" id="loginPwd" name="loginPwd" class="easyui-validatebox tab_txt_d"  required="true" missingMessage="密码不能为空！"/></td>
                <td>生日：</td><td><input  id="birthday" name="birthday" class="easyui-datebox"/></td>
               
            </tr>
        
             <tr>
                <td>民族：</td><td><input type="text" id="natives" name="natives" class="tab_txt_d"/></td>
				 <td>排序：</td>
                <td>
                  <input id="oederIndex" name="oederIndex" class="easyui-numberspinner" min="1" max="20" value="0"  style="width:130px;" invalidMessage="请输入或选择数字！"></input>
                </td>
            </tr>
             <tr>
                <td>证件类别：</td><td><select id="icense" name="icense"><option>身份证</option></select></td>
                <td>证件号：</td><td><input type="text" id="idCard" name="idCard" class="tab_txt_d"/></td>
            </tr>
          
             <tr>
                <td>手机：</td><td><input type="text" id="phone" name="phone" class="easyui-validatebox  tab_txt_d" validType="isMobel" invalidMessage="请输入正确手机号：例子13XXXXXXXXX,15XXXXXXXXX,18XXXXXXXXX"/></td>
                 <td>家庭电话：</td><td><input type="text" id="homephone" name="homephone" class="tab_txt_d"/></td>
            </tr>
            
            <tr>
             <td>性别：</td>
                <td>
                <input id="sex" name="sex" checked type="radio" value="男">男<input id="sex" name="sex" type="radio" value="女">女
                </td>
                <td>电子邮箱：</td><td>
			<input type="text" id="email" name="email" class="easyui-validatebox tab_txt_d" validType="email" invalidMessage="请输入正确的邮件格式!：例子为XXX@hotmail.com" /></td>
            
            
            </tr>
             <tr>
                <td>QQ：</td><td><input type="text" id="qq" name="qq" class="tab_txt_d"/></td>
                <td>MSN：</td><td><input type="text" id="msn" name="msn" class="tab_txt_d"/></td>
            </tr>
            
             <tr>
                <td>用户状态：</td>
                <td>
                 <input id="userStatus" name="userStatus" checked type="radio" value="1">有效<input id="userStatus" name="userStatus" type="radio" value="0">无效
                </td>
                 <td>工作职责：</td>
                <td>
                <input id="workduty" name="workduty" type="text"  class="tab_txt_d">
                </td>
            </tr>
          
             <tr>
                <td>联系地址：</td>
                <td colspan="3">
                <textarea id="address" name="address" cols="45" rows="4" class="tab_txt_d"></textarea>
                </td>
            </tr>
            <tr>
            <td>描述：</td><td colspan="3">
            
			<textarea id="descInfo" name="descInfo" cols="45" rows="4" class="tab_txt_d"></textarea>
		</td>
            </tr>
        </table>
        </form>
	</div>
<div id="relatedeptdialog" style="padding:5px;width:280px;height:180px;" title="关联科室">
	<table>
    	<tr>
        	<td>大&nbsp;科&nbsp;室:</td>
            <td colspan="3"><input id="relatehighdeptselect" class="easyui-combobox tab_txt_d" ></td>
        </tr>
        <tr>
        	<td>详细科室:</td>
            <td colspan="3">
            <input id="relatedeptselect" class="easyui-combobox" >
            </td>
        </tr>
    </table>
</div>
</body>
