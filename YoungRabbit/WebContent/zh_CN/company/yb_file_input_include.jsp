<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String file_header_path = request.getContextPath();
%>
<link href="<%=file_header_path %>/js/bootstrap_fileinput/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
<!-- canvas-to-blob.min.js is only needed if you wish to resize images before upload.
     This must be loaded before fileinput.min.js -->
     
     
     
<script src="<%=file_header_path %>/js/bootstrap_fileinput/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
<!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
     This must be loaded before fileinput.min.js -->
<script src="<%=file_header_path %>/js/bootstrap_fileinput/js/plugins/sortable.min.js" type="text/javascript"></script>
<!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
     This must be loaded before fileinput.min.js -->
<script src="<%=file_header_path %>/js/bootstrap_fileinput/js/plugins/purify.min.js" type="text/javascript"></script>
<!-- the main fileinput plugin file -->
<script src="<%=file_header_path %>/js/bootstrap_fileinput/js/fileinput.js"></script>
<!-- bootstrap.js below is needed if you wish to zoom and view file content 
     in a larger detailed modal dialog -->
<!-- optionally if you need a theme like font awesome theme you can include 
    it as mentioned below -->
<script src="<%=file_header_path %>/js/bootstrap_fileinput/js/locales/zh.js"></script>
<!-- optionally if you need translation for your language then include 
    locale file as mentioned below -->
<script src="<%=file_header_path %>/js/bootstrap_fileinput/js/locales/LANG.js"></script>