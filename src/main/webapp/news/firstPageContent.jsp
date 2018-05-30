<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="stylesheet" href="../style/firststyle.css" type="text/css" media="screen"></link>
<script type="text/javascript" src="../jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
<title>News</title>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url : '${pageContext.request.contextPath}/firstPageAction!findEditFirstPage.action',
				dataType : 'text',
				type : 'post',
				success : function(data){
					var obj = eval("("+data+")");
					$("#firstPageContent_ul").html();
					$("#firstPageContent_ul").append("<li style=\"word-wrap:break-word;line-height:26px;\">"+obj.introduce+"</li>");
				}
			});
		});
	</script>
</head>

<body>
<div style="height: 30px;width:100%;background: url(../images/body_bg.jpg) 0 0 repeat-x;" align="center">
	<div style="height: 25px;width: 800px;padding-top: 5px" align="left">
		<a style="cursor: pointer;padding-left: 5px;text-decoration:none;font-size: 12px;color:white" href="javascript:history.go(-1);">previous</a>
		<img src="../images/home_back.png" style="height: 22px;float: left;">
		<a style="cursor: pointer;padding-left: 5px;float:left;text-decoration:none;font-size: 12px;color:white" href="../firstPage.jsp">homepage</a>
		<img src="../images/previous.png" style="height: 22px;float: left;padding-left: 15px">
	</div>
</div>
<div id="wrapper">
<div id="container">

<div class="title" style="text-align: center">
	<h2 style="color: #000099"><%= new String(request.getParameter("title").getBytes("iso-8859-1"), "utf-8")%></h2>
</div>


<div class="main" id="two-columns">


	<div class="bottom" style="height: 800px;width: 850px">

		<div class="left" >
		
			<h2 id="conh" style="padding-top: 20px"></h2>

			<ul id="firstPageContent_ul" class="block" style="width: 850px;text-indent: 2em;font:normal 12px/21px Arial, Helvetica, sans-serif;color:#000099;text-decoration:none;display:block;"></ul>
			
		</div>

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
				<td class="design" style="font-size: 10px;color:#5c5c5c">Designed by : Hitaii</td>
			</tr>	
		</table>
	</div>
</html>