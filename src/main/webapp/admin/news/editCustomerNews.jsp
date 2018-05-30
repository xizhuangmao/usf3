<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>News</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/firststyle.css" type="text/css" media="screen"></link>
	<!-- easyui --> 
	<script type="text/javascript" 
		src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.easyui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/jslib/jquery.cookie.js"></script>
	<link rel="stylesheet" 
		href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/icon.css" type="text/css" />
	<link rel="stylesheet" 
		href="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/default/easyui.css" type="text/css"/>
	<script type="text/javascript" 
		src="${pageContext.request.contextPath}/editor/ueditor.config.js"></script>  
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/jslib/jquery.PrintArea.js"></script>
	<!-- 编辑器源码文件 -->  
	<script type="text/javascript" 
		src="${pageContext.request.contextPath}/editor/ueditor.all.js"></script>  
	<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->  
	<script type="text/javascript" 
		src="${pageContext.request.contextPath}/editor/lang/zh-cn/zh-cn.js"></script>  
     
	<!-- 实例化编辑器 -->  
   	<script type="text/javascript">
   		var editor = new UE.ui.Editor({ initialFrameHeight:600, autoHeightEnabled: false});
		editor.render("editCustomer_container");
		var ue = UE.getEditor("editCustomer_container");
	</script>

	<script type="text/javascript">
		$(function(){
			$.ajax({
				url : '${pageContext.request.contextPath}/newsAction!findEmployeeNewsById.action',
				data : {"id" : '<%= request.getParameter("id")%>'},
				type : 'post',
				dataType : 'text',
				success : function(data){
					var obj = eval("("+data+")");
					ue.ready(function(){
						ue.setContent(obj.content);
					}),
					$("#editCustomerTitle_container").val(obj.title);
				}
			});
 			
			$("#editCustomerNews_save").click(function(){
				var title = $("#editCustomerTitle_container").val();
				var content = ue.getContent();
				if(title.length==0 || content.length==0){
					alert("Please enter a title or content");
				}else{
					$.ajax({
						url : '${pageContext.request.contextPath}/newsAction!editEmployeeNewsById.action',
						type : 'post',
						data : {"id": '<%= request.getParameter("id")%>', "title" : title, "content":content},
						dataType : 'text',
						success : function(data){
							var obj = $.parseJSON(data);
							$("#editCustomerNews_dialog").dialog('open');
						}
					});
				}
			});
		});
	</script>

</head>

<body>
<div style="height: 30px;width:100%;background: url(../../images/body_bg.jpg) 0 0 repeat-x;" align="center">
	<div style="height: 25px;width: 800px;padding-top: 5px" align="left">
		<img src="../../images/close.png" style="height: 22px;float: left"/>
		<a style="cursor: pointer;color:white;padding-left: 5px;float:left;text-decoration:none;font-size: 12px" href="javascript:self.close();">close</a>
	</div>
</div>
<div id="wrapper">
<div id="container">

<div class="title" style="text-align: center">
	<h1><textarea id="editCustomerTitle_container" name="editCustomerTitle_container" style="width: 836px;font-size: 30px;text-align: center; resize: none"></textarea></h1>
</div>


<div class="main" id="two-columns">


	<div class="bottom" style="height: 650px;width: 850px">

		<div class="left" >
		
			<h2>Content:</h2>
			
			<table class="block">
				<tr>
  					<td><textarea id="editCustomer_container" name="editCustomer_container" style="width: 836px;"></textarea></td>
  				</tr>
			</table>
		</div>
		<img src="../../images/save.png" id="editCustomerNews_save" style="color: black;cursor:pointer;"/>
	</div>
</div>

</div>
</div>

</body>
	<div id="footer" align="center"> 
		<table>
			<tr>
				<td class="copyright">Copyright Intro Type Hitaii. All Rights Reserved.</td>
			</tr>
			<tr>
				<td class="design" style="font-size: 12px;color:#5c5c5c">Designed by : Hitaii</td>
			</tr>	
		</table>
	</div>
	
	<div id="editCustomerNews_dialog" style="width: 220px;height: 120px" class="easyui-dialog"
		data-options="title:'What are you going to do?',	modal:true,closed:true,">
		<div style="width: 200px;height: 30px;padding-top: 10px" align="center">
			<a onclick="$('#editCustomerNews_dialog').dialog('close');"><img src="../../images/continuemodify.png"></a>			
		</div>
		<div style="width: 200px;height: 20px;" align="center">
			<a onclick="$('#editCustomerNews_dialog').dialog('destroy');javascript:self.close();"><img src="../../images/closePage.png"></a>			
		</div>
	</div>
</html>
	