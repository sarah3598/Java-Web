<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>情况统计表</title>
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
	<script type="text/javascript" src="js/sysrole/menuFun.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
   
    <script type="text/javascript" src="js/public.js"></script>
    <script type="text/javascript" src="js/report/cwyb/reportbase250.js"></script>
    <script type="text/javascript" src="supcan/binary/dynaload.js"></script>
    
 	 <script type="text/javascript">

    function OnReady(id){
		if(id!="AF") return ;
		//AF.func("Build","<!--%=basePath%>/filereport/meeting_check.xml");
		AF.func("Build","<%=basePath%>/filereport/report10.xml");
		AF.func("callFunc", "309");
		AF.func("SelectCell","C6");
   } 
	//OnEvent 事件 11. 
	//OnEvent参数说明: “Event”为事件名，p1,p2,p3,p4参数有特定的含义，由具体的功能组件规定. 
	function OnEvent(id, Event, p1, p2, p3, p4){
		if(id!="AF") return ;
		
		if(Event == "Saved"){//点击保存
			if(AF.func("callfunc", "50")==0) return; //未通过验证
			var xml=AF.func("GetUploadXML", "");//alert(xml);
			if(!xml) return;
			if(parent.window.subMitSzXmlData)
			parent.window.subMitSzXmlData(xml);
		}
	} 

	</script>
 
</head>

<body class="easyui-layout">
-<input type="hidden" id="usId" name="usId"/>
<div  region="west" iconCls="icon-reload" title="机构" split="true" style="width:200px;">
<ul id="ultree" class="easyui-tree" >  
</ul>
 </div>

<div d="divsearch" region="north" >
<form action="" id="searchform">
<table id="dataform"  class="table_d">
</table>
</form>
</div>
  <div id="divtable" region="center"  title="销售情况统计表" class="datagrid-wrap panel-body" style="background:#fafafa;overflow:hidden">
   <div id="tabbar" class="dialog-toolbar">
    </div>
   <div class="datagrid-toolbar">
                  
    </div>  
 </div>
    
  <div id="divreport" style=" top:95px;left:0px;width:100%;height:550px;">
   
     <script>
    
        //insertReport('AF', 'workmode=uploadRuntime;Rebar=none; Border=none; Ruler=none; PagesTabPercent=0; SeperateBar=none')
        //insertReport('AF', 'Rebar=none; Border=none; Ruler=none; PagesTabPercent=0; SeperateBar=none')
       //  insertReport('AF', 'workmode=uploadRuntime;Rebar=Main,Print,Property;');
      // AF.func("Build", "report/report10.xml");
    insertReport('AF', 'workmode=uploadDesigntime;Border=single,3D; Ruler=Horz; Rebar=Main, Print, Form,,Font, Property,Text; print=11, 216;text.102=新121建|Open;tip.102=新121建|Open')
      
    </script>
 
  <div id="divlist" icon="icon-save" style="padding:5px;top:95px;left:2px;width:550px;height:200px;display:none">
  		
  <table id="list_sub_table" width="100%">
  </table>

  </div>
  
   <div id="divformUploadExcel" icon="icon-save" style="padding:5px;top:95px;left:2px;width:550px;height:100px;display:none">
		<form id="dataformUploadExcel" enctype="multipart/form-data" action="./gzjg/reportBase50251_uploadExcel?baseParam.path=/WEB-INF/jsp/report/cwyb/reportbase251.jsp" method="post">
			<table width="100%" class="table_d">
				<tr>
					<td>上传文件:</td><td><input type="file" style="width:300px;" name="uploadFile" id="uploadFile"/></td>
				</tr>
		  	</table>
	  </form>	
	</div>
  
  
  
  
</body>
</html>