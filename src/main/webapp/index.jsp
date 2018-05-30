<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>USF</title>
<link rel="stylesheet" href="style/style.css" type="text/css"></link>
<link href="style/master.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.min.js"></script>

<script type="text/javascript">
	$(function(){
		String.prototype.replaceAll  = function(s1,s2){     
        	return this.replace(new RegExp(s1,"gm"),s2);     
   		};   
		$.ajax({
			url : 'firstPageAction!findEditFirstPage.action',
			dataType: 'text',
			type : 'post',
			success : function(data){
				var obj = eval("("+data+")");
				$("#firstImg_div").append("<img src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.firstpic+"\" style=\"width: 360px;height: 180px\"/>");
				if(obj.hasOwnProperty("callus")){
				}else{
					obj.callus = "";
				}
				$("#firstPage_callTable").append(obj.callus);
				if(obj.hasOwnProperty("introduce")){
					var introduce = obj.introduce;
				}else{
					obj.introduce = "";
				}
				$("#firstPage_introTable").append("<a style=\"cursor:pointer;color:inherit;text-decoration:none;\" href=\"news/firstPageContent.jsp?title=About USF\">"+obj.introduce+"</a>");
				if(obj.hasOwnProperty("workinghours")){
				}else{
					obj.workinghours = "";
				}
				$("#workingHours_table").append(obj.workinghours);
				if(obj.hasOwnProperty("picintroduce")){
				}else{
					obj.picintroduce = "";
				}
				$("#firstPage_picintroduce").append("<li><label style=\"color: #000099\">"+obj.picintroduce+"</label></li>");
			}
		});
		
		$.ajax({
			url : 'firstPageAction!findPics.action',
			dataType : 'text',
			type : 'post',
			async: false,
			success : function(data){
				var obj = $.parseJSON(data);
				var index=0;
				for(var i=0;i<obj.pics.length;i++){
					if(obj.pics[i] == null || obj.pics[i].length == 0){
						index += 1;
					}
				}
				if(index>=2){
					document.getElementById('firstPagePics_div').setAttribute("class", "picNotScroll"); ;
				}
				var ul = document.querySelector("#firstPagePics");
				var lis = ul.querySelectorAll("li");
				if(obj.pics[0] !== null && obj.pics[0].length > 0){
					$("#firstPagePics1").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[0]+"\"/>");
				}else{lis[0].remove();}
				if(obj.pics[1] !== null && obj.pics[1].length > 0){
					$("#firstPagePics2").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[1]+"\"/>");
				}else{lis[1].remove();}
				if(obj.pics[2] !== null && obj.pics[2].length > 0){
					$("#firstPagePics3").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[2]+"\"/>");
				}else{lis[2].remove();}
				if(obj.pics[3] !== null && obj.pics[3].length > 0){
					$("#firstPagePics4").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[3]+"\"/>");
				}else{lis[3].remove();}
				if(obj.pics[4] !== null && obj.pics[4].length > 0){
					$("#firstPagePics5").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[4]+"\"/>");
				}else{lis[4].remove();}
			}
		});
		
		$.ajax({
			url : 'newsAction!findEmployeeNews.action',
			dataType: 'text',
			type : 'post',
			success : function(data){
				var obj = $.parseJSON(data);
				for(var i=0;i<obj.news.length;i++){
					if(i<5){
						var title = obj.news[i].title;
						var newsdate = obj.news[i].newsdate.substring(0,10);
						if(obj.news[i].title.length > 22){
							obj.news[i].title = obj.news[i].title.substring(0, 22) + "...";
						}
						$("#emergencyNews_table").append("<tr><td><a style=\"cursor:pointer;color:inherit;text-decoration:none;\" href=\"news/newsContent.jsp?id="+obj.news[i].id+"\">"+obj.news[i].title+"</a></td><td>"+newsdate+"</td></tr>");
					}
				}
			}
		});
		
		$.ajax({
			url : 'newsAction!findCustomerNews.action',
			dataType: 'text',
			type : 'post',
			success : function(data){
				var obj = $.parseJSON(data);
				for(var i=0;i<obj.news.length;i++){
					if(i<5){
						var title = obj.news[i].title;
						var newsdate = obj.news[i].newsdate.substring(0,10);
						if(obj.news[i].title.length > 22){
							obj.news[i].title = obj.news[i].title.substring(0, 22) + "...";
						}
						$("#customersNews_table").append("<tr><td><a style=\"cursor:pointer;color:inherit;text-decoration:none;\" href=\"news/newsContent.jsp?id="+obj.news[i].id+"\">"+obj.news[i].title+"</a></td><td>"+newsdate+"</td></tr>");
					}
				}
			}
		});
		
		$.ajax({
			url : 'newsAction!findHelpNews.action',
			dataType: 'text',
			type : 'post',
			success : function(data){
				var obj = $.parseJSON(data);
				for(var i=0;i<obj.news.length;i++){
					if(i<4){
						var title = obj.news[i].title;
						if(obj.news[i].title.length > 48){
							obj.news[i].title = obj.news[i].title.substring(0, 48) + "...";
						}
						if(obj.news[i].content.length > 48){
							obj.news[i].content = obj.news[i].content.substring(0, 48) + "...";
						}
						$("#firstHelp_div").append("<div style=\"width: 220px;height: 90px;word-wrap:break-word;\"><h3 style=\"color:#000099\">"+obj.news[i].title+"</h3><a class=\"plan\" style=\"color:#000099;text-decoration: none;border-bottom: thin dashed #FF0000;\" href=\"news/newsContent.jsp?id="+obj.news[i].id+"\">"+obj.news[i].content+"</a></div>");
					}
				}
			}
		});
	});
</script>
</head>
<body>
<div style="height: 30px;width:100%;background: url(images/body_bg.jpg) 0 0 repeat-x"></div>
	<div id="main">
		
		<div id="left">
			<div style="width: 247px;height: 97px;padding-left: 18px;">
				<table>
					<tr>
						<td><img src="images/usa.png" style="padding-top: 5px"/></td>
					</tr>
				</table>				
			</div>
			<div style="width: 247px;height: 230px;border: 1px solid #d1d1e8">
				<h3 class="faq"><span style="color: #000099">Emergency</span><a href="news/allNews.jsp" style="font-size: 12px;cursor: pointer;text-decoration:none;color:#000099;padding-left: 40px">&nbsp;&nbsp;more>></a></h3>
				<div style="width:250px; height:160px; ">
					<table id="emergencyNews_table" style="padding-left: 10px;height: 80px;line-height: 20px;font:12px/21px Cambri;color: #000099">
	
					</table>
				</div>
			</div>
			<div style="width: 247px;height: 230px;margin-top:10px;border: 1px solid #d1d1e8">
				<h3 class="faq"><span style="color: #000099">News</span><a href="news/allCustomerNews.jsp" style="font-size: 12px;cursor: pointer;text-decoration:none;color:#000099;padding-left: 80px">&nbsp;&nbsp;more>></a></h3>
				<table id="customersNews_table" style="padding-left: 10px;padding-top:10px;line-height: 20px;font:12px/21px Cambri;color: #000099">

				</table>
			</div>
			
			<div style="width: 247px;height: 230px;margin-top:10px;border: 1px solid #d1d1e8;">
				<div style="width:70;height:45;padding-left: 20px;padding-top: 10px">
					<img src="images/callus.png"  style="float: left"/>
					
				</div>
				<div style="width:160;height:65;padding-top: 15px;padding-left: 105px">
					<h3><span style="color: #000099;font-size: 16px">CONTACT US</span></h3>
				</div>
				<div id="firstPage_callTable" style="clear: both;padding-top: 10px;height: 145px;width: 245px;font:12px/25px Cambri;color: #000099;word-wrap:break-word;overflow:hidden;text-overflow:ellipsis;">
				</div>
			</div>
		</div>

		<div id="right">
			<div id="firstImg_div" style="width: 450px;height: 160px;" align="center"></div>

			<div id="rightBotMain">
				<div id="rightBot">
					<p class="top"></p>
					<div id="rightBot2" style="white-space:normal;word-wrap:break-word;overflow:auto;width: 380px;height: 140px;overflow:hidden;text-overflow:ellipsis;">
						<ul id="firstPage_picintroduce" class="rightLink1" style="width: 382px;height: 120px;">
							<li><label><strong style="color: #000099">About the picture: </strong></label></li>
							<!-- <li><p style="color: #000099">"Statue of Liberty",bronze statues,47 meters high pedestal,like high 46 meters,93 meters high total,completed in 1886,is located in New York Port Menpang the freedom of the island.</p></li> -->
						</ul>
						<br class="spacer" />
					</div>
					<p class="bot"></p>
					<br class="spacer" />
				</div>
				<br class="spacer" />

				<div id="best" style="height: 250px;width: 430px">
					<div style="height: 60px;width: 430px">
						<h2><span style="font-size: 16px;color:#000099">About USF</span></h2>
					</div>
					<div id="firstPage_introTable" style="text-indent:2em;font:12px/21px Arial,Helvetica,sans-serif;color: #000099;height: 210px;width: 430px;word-wrap:break-word;overflow:hidden;text-overflow:ellipsis;">
					</div>
				</div>
				<div id="best" style="width: 600px;height: 165px">
					<h2><span style="font-size: 16px;color:#000099">Show</span></h2>
					<div id="firstPagePics_div" class="picScroll" style="width:645px;height: 115px;">
						<ul id="firstPagePics">
							<li id="firstPagePics1"></li>
							<li id="firstPagePics2"></li>
							<li id="firstPagePics3"></li>
							<li id="firstPagePics4"></li>
							<li id="firstPagePics5"></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="last" style="border: 1px solid #d1d1e8">
			<img src="images/login1.png" onclick="window.location.href='http://66.80.116.243/usf2/toLogin.html'" style="color: black;cursor: pointer;padding-left: 5px;padding-top: 5px"/>
			<div style="width: 220px;height: 200px;	background:url(images/why_best.gif) 0 0 repeat-x;">
				<div style="width: 220px;height: 50px;">
					<p class="lastTop"></p>
					<h2 class="res"><span style="font-size: 16px;color:#000099">Our Working Hours</span></h2>
				</div>
				<div id="workingHours_table" style="padding-left:15px;width:205px;height:145px;font:12px/25px Cambri;color: #000099;word-wrap:break-word;overflow:hidden;text-overflow:ellipsis;">
					
				</div>
			</div>
			<div style="width: 220px;height: 430px;background:url(images/why_best.gif) 0 0 repeat-x;" id="firstHelp_div">
				<div style="width: 220px;height: 50px">
					<h2 class="future" style="width: 200px"><span style="width: 180px;font-size: 16px;color:#000099">Help And Support</span><a href="news/allHelpNews.jsp" style="font-size: 12px;cursor: pointer;text-decoration:none;color:#000099">&nbsp;&nbsp;more>></a></h2>
				</div>
			</div>
		</div>
		<br class="spacer" />
	</div>

	<div id="footer"> 
		<p class="copyright" style="width: 400px">Copyright Intro Type Hitaii. All Rights Reserved.</p>
		<p class="design" style="font-size: 10px">Designed by : Hitaii</p>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.SuperSlide.2.1.1.js"></script>
	<script src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/all.js" type="text/javascript"></script>
</body>

</html>
