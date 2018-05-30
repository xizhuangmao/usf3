<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<link rel="stylesheet" href="../style/firststyle.css" type="text/css" media="screen"></link>
<link rel="stylesheet" href="../style/styleEmployeeNews.css" type="text/css"></link>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.min.js"></script>
<title>News</title>
	<script>
		$(function(){
			var allHelpNews_current = 1;
			$.ajax({
				url : '${pageContext.request.contextPath}/newsAction!findHelpNews.action',
				dataType : 'text',
				data : {"page" : allHelpNews_current,"rows" : "10"},
				type : 'post',
				success : function(data){
					var obj = $.parseJSON(data);
					$("#allHelpNews_total").html(obj.total);
					$("#allHelpNews_totalpa").html(obj.totalPa);
					$("#allHelpNews_current").html(allHelpNews_current);
					$("#allHelpNews_every").val(allHelpNews_current);
					for(var i=0;i<obj.news.length;i++){
						if(obj.news[i].title.length>90){
							obj.news[i].title = obj.news[i].title.substring(0, 90) + "...";
						}
						$("#allHelpNews_ul").append("<a style=\"cursor:pointer;color:inherit;text-decoration:none;\" href=\"newsContent.jsp?id="+obj.news[i].id+"\"><li style=\"font-size:14px;float:right\">"+obj.news[i].newsdate+"</li><li style=\"font-size:14px;\">"+obj.news[i].title+"</li></a><br/>");
					}
					var tot = $("#allHelpNews_totalpa").html();
					if(allHelpNews_current == tot){
						$("#allHelpNews_next").attr("disabled","true");
					}else{
						$("#allHelpNews_next").removeAttr("disabled");
					}
					if(allHelpNews_current <= 1){
						$("#allHelpNews_back").attr("disabled","true");
					}else{
						$("#allHelpNews_back").removeAttr("disabled");
					}			
				}
			});
		
		$("#allHelpNews_next").click(function(){
			allHelpNews_current += 1;
			$("#allHelpNews_current").html(allHelpNews_current);
			$("#allHelpNews_every").val(allHelpNews_current);
			$.ajax({
				url : '${pageContext.request.contextPath}/newsAction!findHelpNews.action',
				type : 'post',
				dataType : 'text',
				data : {"page" : allHelpNews_current,"rows" : "10"},
				success : function(data){	
					$("#allHelpNews_ul").html("");
					var obj = $.parseJSON(data);
					for(var i=0;i<obj.news.length;i++){
						if(obj.news[i].title.length>90){
							obj.news[i].title = obj.news[i].title.substring(0, 90) + "...";
						}
						$("#allHelpNews_ul").append("<a style=\"cursor:pointer;color:inherit;text-decoration:none;\" href=\"newsContent.jsp?id="+obj.news[i].id+"\"><li style=\"font-size:14px;float:right\">"+obj.news[i].newsdate+"</li><li style=\"font-size:14px\">"+obj.news[i].title+"</li></a><br/>");
					}	
					var tot = $("#allHelpNews_totalpa").html();
					if(allHelpNews_current == tot){
						$("#allHelpNews_next").attr("disabled","true");
					}
					$("#allHelpNews_back").removeAttr("disabled");
				}
			});
		});
		
		$("#allHelpNews_back").click(function(){
			allHelpNews_current -= 1;
			$("#allHelpNews_current").html(allHelpNews_current);
			$("#allHelpNews_every").val(allHelpNews_current);
			$.ajax({
				url : '${pageContext.request.contextPath}/newsAction!findHelpNews.action',
				type : 'post',
				dataType : 'text',
				data : {"page" : allHelpNews_current,"rows" : "10"},
				success : function(data){	
					$("#allHelpNews_ul").html("");
					var obj = $.parseJSON(data);
					for(var i=0;i<obj.news.length;i++){
						if(obj.news[i].title.length>90){
							obj.news[i].title = obj.news[i].title.substring(0, 90) + "...";
						}
						$("#allHelpNews_ul").append("<a style=\"cursor:pointer;color:inherit;text-decoration:none;\" href=\"newsContent.jsp?id="+obj.news[i].id+"\"><li style=\"font-size:14px;float:right\">"+obj.news[i].newsdate+"</li><li style=\"font-size:14px\">"+obj.news[i].title+"</li></a><br/>");
					}	
					if(allHelpNews_current <= 1){
						$("#allHelpNews_back").attr("disabled","true");
					}
					$("#allHelpNews_next").removeAttr("disabled");
				}
			});
		});
		});
	</script>
</head>

<body>
<div style="height: 30px;width:100%;background: url(../images/body_bg.jpg) 0 0 repeat-x;" align="center">
	<div style="height: 25px;width: 800px;padding-top: 5px" align="left">
		<a style="cursor: pointer;color:white;padding-left: 5px" href="javascript:history.go(-1);">previous</a>
		<img src="../images/home_back.png" style="height: 22px;float: left;">
		<a style="cursor: pointer;color:white;padding-left: 5px;float:left" href="../firstPage.jsp">homepage</a>
		<img src="../images/previous.png" style="height: 22px;float: left;padding-left: 15px">
	</div>
</div>
<div id="wrapper">
<div id="container">

<div class="title" style="text-align: center">
	<h2 style="color: #000099">Help News</h2>
</div>


<div class="main" id="two-columns">


	<div class="bottom" style="height: 800px;width: 850px">

		<div class="left" >
		
			<h2 style="padding-top: 20px"></h2>
			<ul id="allHelpNews_ul" class="block" style="width: 850px;border-top: 1px #BCBAAC;margin: 4px 0;padding-top: 20px;font:normal 12px/21px Arial, Helvetica, sans-serif;color:#000099;text-decoration:none;background-color:inherit;display:block;"></ul>
			
		<div class="pagin">
	    	<div class="message"><i class="blue"><label id="allHelpNews_total" style="display: none"></label></i><i class="blue"><label id="allHelpNews_current" style="display: none"></label></i></div>
	        <ul class="paginList">
	       <!--  <li class="paginItem"><button id="allNews_first" style="cursor:pointer;"><span style="font-size: 14px">home page</span></button></li> -->
	        <li class="paginItem"><button id="allHelpNews_back" style="cursor:pointer;"><span style="font-size: 14px;color:#000099">Previous</span></button></li>
	        <li class="paginItem" style="padding-top: 5px;display: none">第</li>
	        <li class="paginItem current" style="padding-top: 2px;"><input id="allHelpNews_every" style="width: 10px;"/></li>
	        <li class="paginItem current" style="padding-top: 2px;">/</li>
	        <li class="paginItem" style="padding-top: 5px;display: none">页</li>
	        <li class="paginItem" style="padding-top: 5px;display: none">,</li>
	        <li class="paginItem" style="padding-top: 5px;display: none">共</li>
	        <li class="paginItem more" style="padding-top: 2px;"><label id="allHelpNews_totalpa"></label></li>
	        <li class="paginItem" style="padding-top: 5px;display: none">页</li>
	        <li class="paginItem"><button id="allHelpNews_next" style="cursor:pointer;"><span style="font-size: 14px;color:#000099">Next</span></button></li>
	       <!--  <li class="paginItem"><button id="allNews_end" style="cursor:pointer;"><span style="font-size: 14px">last page</span></button></li> -->
	        </ul>
    		</div>
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