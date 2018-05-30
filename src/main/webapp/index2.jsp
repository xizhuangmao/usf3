<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML >
<html>
<head>

<title>USF</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/locale/easyui-lang-en.js"></script>
<link id="easyuiTheme" rel="stylesheet"
	href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/<c:out value="${cookie.easyuiThemeName.value}" default="default"/>/easyui.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/jquery.cookie.js"></script>
<!-- 扩展EasyUI Icon -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/extEasyUIIcon.css" type="text/css">
<!-- 扩展jQuery -->
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/extJquery.js" charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/syUtil.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/common.js"></script>	
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/jslib/ajaxfileupload.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/json2.js"></script>

<!-- UEditor配置文件 -->  
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/editor/ueditor.config.js"></script>  

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jslib/jquery.PrintArea.js"></script>
	
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/jslib/print.js"></script>
	
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/jslib/export.js"></script>
	
<script type="text/javascript">
	var uooIndex = 0;
	var layout_north_searchInfo_customerUrl = 0;
	var layout_north_searchInfo_busyUrl = 0;
	var newOrdersAddBooknumType = 0;
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north'" style="height:60px">
		<jsp:include page="layout/north.jsp"></jsp:include>
	</div>
	<div data-options="region:'south',split:true" style="height:110px;background-color: #314a80">
		<jsp:include page="layout/south.jsp"></jsp:include>
	</div>
	<div data-options="region:'west'" style="width:200px">
		<jsp:include page="layout/west.jsp"></jsp:include>
	</div>
	<!-- 
	<div data-options="region:'east',split:true,title:'panel'"
		style="width:200px"></div>
 	-->
	<div data-options="region:'center',title:'welcome to USF'">
		<jsp:include page="layout/center.jsp"></jsp:include>
		
	</div>
	<!-- -->
	<jsp:include page="user/login.jsp"></jsp:include>
	<jsp:include page="user/reg.jsp"></jsp:include>
</body>
</html>

