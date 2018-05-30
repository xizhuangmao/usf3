<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>USF</title>
	<link rel="stylesheet" 
		href="${pageContext.request.contextPath}/style/style.css" type="text/css" rel="stylesheet" />
	<link 
		href="${pageContext.request.contextPath}/style/master.css" type="text/css" rel="stylesheet" />
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
	<!-- 上传图片 --> 
	<script type="text/javascript" 
		src="${pageContext.request.contextPath}/jslib/ajaxfileupload.js"></script>
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
   		var calleditor = new UE.ui.Editor({ initialFrameHeight:120,initialFrameWidth:240, autoHeightEnabled: false});
		calleditor.render("addFirstPageCall_container");	
		var workeditor = new UE.ui.Editor({ initialFrameHeight:140,initialFrameWidth:200, autoHeightEnabled: false});
		workeditor.render("addFirstPageWork_container");	
		var editor = new UE.ui.Editor({ initialFrameHeight:90, autoHeightEnabled: false});
		editor.render("addFirstPagePicintro_container");
		var ue = UE.getEditor("addFirstPageCall_container");
		var workUe = UE.getEditor("addFirstPageWork_container");
		var picIntroUe = UE.getEditor("addFirstPagePicintro_container");
	</script>
	
	<script type="text/javascript">
		var d;  //定义全局变量刷新dialog
		$(function(){
		
 			$.ajax({
				url : '${pageContext.request.contextPath}/firstPageAction!findEditFirstPage.action',
				dataType: 'text',
				type : 'post',
				success : function(data){
					var obj = eval("("+data+")");
					$("#addOrUpdateFirstImg_div").append("<img src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.firstpic+"\" style=\"width: 360px;height: 160px\"/>");
					if(obj.hasOwnProperty("callus")){
						ue.ready(function() {
							ue.setContent(obj.callus);
						});
					}else{
						obj.callus = "";
					}
					if(obj.hasOwnProperty("workinghours")){
						workUe.ready(function() {
							workUe.setContent(obj.workinghours);
						});
					}else{
						obj.workinghours = "";
					}
					if(obj.hasOwnProperty("picintroduce")){
						picIntroUe.ready(function() {
							picIntroUe.setContent(obj.picintroduce);
						});
					}else{
						obj.picintroduce = "";
					}
				}
			}); 
			
		$.ajax({
			url : '${pageContext.request.contextPath}/firstPageAction!findEditFirstPage.action',
			dataType: 'text',
			type : 'post',
			success : function(data){
				var obj = eval("("+data+")");
				var introduce = obj.introduce;
				if(obj.hasOwnProperty("introduce")){
					var introduce = obj.introduce;
				}else{
					obj.introduce = "";
				}
				$("#addFirstPage_introTable").append("<a style=\"color: #000099\">"+obj.introduce+"</a>");
			}
		});
		
		$.ajax({
			url : '${pageContext.request.contextPath}/newsAction!findEmployeeNews.action',
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
						$("#addFirstPage_emergencyNews_table").append("<tr><td><a style=\"text-decoration:none;color: #000099\">"+obj.news[i].title+"</a></td><td style=\"color: #000099\">"+newsdate+"</td></tr>");
					}
					
				}
			}
		});
		
		$.ajax({
			url : '${pageContext.request.contextPath}/newsAction!findCustomerNews.action',
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
						$("#addFirstPage_customersNews_table").append("<tr><td><a style=\"text-decoration:none;color: #000099\">"+obj.news[i].title+"</a></td><td style=\"color: #000099\">"+newsdate+"</td></tr>");
					}
				}
			}
		});
		
		$.ajax({
			url : '${pageContext.request.contextPath}/newsAction!findHelpNews.action',
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
						$("#addfirstHelp_div").append("<div style=\"width: 220px;height: 90px;word-wrap:break-word;\"><h3 style=\"color: #000099\">"+obj.news[i].title+"</h3><a class=\"plan\" style=\"color: #000099;text-decoration: none;border-bottom: thin dashed #FF0000;\">"+obj.news[i].content+"</a></div>");
					}
				}
			}
		});
		
		$.ajax({
			url : '${pageContext.request.contextPath}/firstPageAction!findPics.action',
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
					document.getElementById('addfirstPagePics_div').setAttribute("class", "picNotScroll"); ;
				}
				var ul = document.querySelector("#addfirstPagePics");
				var lis = ul.querySelectorAll("li");
				if(obj.pics[0] !== null && obj.pics[0].length > 0){
					$("#addfirstPagePics1").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[0]+"\"/>");
				}else{lis[0].remove();}
				if(obj.pics[1] !== null && obj.pics[1].length > 0){
					$("#addfirstPagePics2").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[1]+"\"/>");
				}else{lis[1].remove();}
				if(obj.pics[2] !== null && obj.pics[2].length > 0){
					$("#addfirstPagePics3").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[2]+"\"/>");
				}else{lis[2].remove();}
				if(obj.pics[3] !== null && obj.pics[3].length > 0){
					$("#addfirstPagePics4").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[3]+"\"/>");
				}else{lis[3].remove();}
				if(obj.pics[4] !== null && obj.pics[4].length > 0){
					$("#addfirstPagePics5").append("<img style=\"width:170px\" src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pics[4]+"\"/>");
				}else{lis[4].remove();}
			}
		});
		
		$("#addFirstPageCall_save").click(function(){
			//获得UEditor编辑器的内容			
			var addFirstPageCall_content = ue.getContent();
			//var addFirstPagePicIntro_content = picIntroUe.getContent();
			$.ajax({
				url : '${pageContext.request.contextPath}/firstPageAction!saveOrUpdateFirstPageCall.action',
				data : {"id": '<%= request.getParameter("id")%>', "callus": addFirstPageCall_content},
				type : 'post',
				dataType : 'text',
				success : function(data){
					var obj = $.parseJSON(data);
					$.messager.show({
						title:'Message',
						msg:'Save Success',
						timeout:5000,
						showType:'slide'
					});
				}
			});
		});
		
		$("#addFirstPageWork_save").click(function(){
			//获得UEditor编辑器的内容			
			var addFirstPageWork_content = workUe.getContent();
			$.ajax({
				url : '${pageContext.request.contextPath}/firstPageAction!saveOrUpdateFirstPageWork.action',
				data : {"id": '<%= request.getParameter("id")%>', "workinghours": addFirstPageWork_content},
				type : 'post',
				dataType : 'text',
				success : function(data){
					var obj = $.parseJSON(data);
					$.messager.show({
						title:'Message',
						msg:'Save Success',
						timeout:5000,
						showType:'slide'
					});
				}
			});
		});
		
		$("#addFirstPagePicIntro_save").click(function(){
			//获得UEditor编辑器的内容			
			var addFirstPagePicIntro_content = picIntroUe.getContent();
			$.ajax({
				url : '${pageContext.request.contextPath}/firstPageAction!saveOrUpdateFirstPagePicIntro.action',
				data : {"id": '<%= request.getParameter("id")%>', "picintroduce": addFirstPagePicIntro_content},
				type : 'post',
				dataType : 'text',
				success : function(data){
					var obj = $.parseJSON(data);
					$.messager.show({
						title:'Message',
						msg:'Save Success',
						timeout:5000,
						showType:'slide'
					});
				}
			});
		});
		
		$("#addOrUpdateFirstPagePic").click(function(){
			$.ajaxFileUpload({	
				url : '${pageContext.request.contextPath}/firstPageAction!upPic.action?id='+"<%= request.getParameter("id")%>"+'',
				type : 'post',
				dataType: 'text', 
				secureuri : false,
				fileElementId:  "addOrUpdateFirstImguploadId", //type="file"的input id
				success : function(data) {
					var obj = $.parseJSON(data);
					$("#addOrUpdateFirstImg_div").html("");
					$.ajax({
						url : '${pageContext.request.contextPath}/firstPageAction!findEditFirstPage.action',
						type : 'post',
						dataType : 'text',
						success : function(data){
							var obj = eval("("+data+")");
							$("#addOrUpdateFirstImg_div").append("<img src=\"${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.firstpic+"\" style=\"width: 360px;height: 160px\"/>");
						}
					});
				},
				error: function (data, status, e){//服务器响应失败处理函数
				}
			});
		});
		
		$("#addFirstPage_back").click(function(){
			window.location.href = "javascript:history.go(-1)";
		});
		
		$("#addOrUpdateFirstPic_but").click(function(){
			 d =$('<div/>').dialog({
						width:880,
						heigth:400,
						title:"edit pics",
						modal: true,
						href:'${pageContext.request.contextPath}/admin/news/editFirstPagePic.jsp?id='+"<%= request.getParameter("id")%>"+'',
						onClose:function(){
							d.dialog('destroy');
							location.reload();
						},
					});
					
					var browserHeight = $(window).height();  //游览器
					var browserwidth = $(window).width();
					var width = d.panel('options').width;//获取容器的宽
					if(browserwidth>(width+200)){
						if(browserHeight>700){
							d.panel('resize',{
								left : 770,
								top  : 440,
							});
						}else{
							d.panel('resize',{
								left : 150,
								top  : 0,
							});
						}
						
					}else{
						if(browserHeight<700){
							d.panel('resize',{
								left : 0,
								top  : 10,
							});
						}else{
							d.panel('resize',{
								left : 0,
								top  : 0,
							});
						}
				};			
			});
		});
	</script>
</head>
<body>
<div style="height: 30px;width:100%;background: url(../../images/body_bg.jpg) 0 0 repeat-x" align="center">
	<div style="height: 25px;width: 980px;padding-top: 5px" align="left">
		<img src="../../images/close.png" style="height: 22px;float: left"/>
		<a style="cursor: pointer;color:white;padding-left: 5px;float:left;text-decoration:none;font-size: 12px" href="javascript:self.close();">close</a>
	</div>
</div>
	<div id="main">
		<div id="left">
			<div style="width: 247px;height: 97px;padding-left: 18px;">
				<table>
					<tr>
						<td><img src="../../images/usa.png" style="padding-top: 5px"/></td>
					</tr>
				</table>				
			</div>
			<div style="width: 247px;height: 230px;border: 1px solid #d8d9d9"">
				<h3 class="faq"><span style="color: #000099">Emergency</span></h3>
				<div style="width:250px; height:160px; ">
					<table id="addFirstPage_emergencyNews_table" style="padding-left: 10px;height: 80px;font:12px/21px Cambri;color: #5e543a">
	
					</table>
				</div>
			</div>
			<div style="width: 247px;height: 230px;margin-top:10px;border: 1px solid #d8d9d9"">
				<h3 class="faq"><span style="color: #000099">News</span></h3>
				<table id="addFirstPage_customersNews_table" style="padding-left: 10px;padding-top:10px;font:12px/21px Cambri;color: #5e543a">

				</table>
			</div>
			
			<div style="width: 247px;height: 230px;margin-top:10px;border: 1px solid #d8d9d9">
				<div style="width:70;height:45;padding-left: 20px;padding-top: 10px">
					<img src="../../images/callus.png"  style="float: left"/>
					
				</div>
				<div style="width:160;height:45;padding-top: 15px;padding-left: 105px">
					<h3><span style="color: #000099;font-size: 16px">CONTACT US</span></h3>
				</div>
				<div style="clear: both;padding-top: 10px">
					<table id="addFirstPage_firstPage_callTable" style="padding-top:10px;">
						<tr>
	  						<td><textarea id="addFirstPageCall_container" name="addFirstPageCall_container" style="width: 210px;"></textarea></td>
	  					</tr>
					</table>
				</div>
				<div style="width:160;" align="right">
					<img src="../../images/first_save.png" id="addFirstPageCall_save" style="color: black;cursor:pointer;"/>
				</div>
			</div>
		</div>

		<div id="right">
			<div style="width: 450px;height: 160px;" align="center">
				<div id="addOrUpdateFirstImg_div" style="width: 360px;height: 160px">
					<!-- <img src="../images/NYC5.jpg" style="width: 360px;height: 160px"/> -->
				</div>
				<div>
					<table>
					    <thead>
					    	<tr>
								<td style="font-size: 12px;color: #000099">Change Image:</td>
								<td><input id="addOrUpdateFirstImguploadId" name="addOrUpdateFirstImguploadId" type="file" value="Choose" style="color: #000099"/></td>
								<td><input id="addOrUpdateFirstPagePic" type="button" value="submit"/></td>
							</tr> 
					    </thead>
					</table>
				</div>
			</div>

			<div id="rightBotMain">
				<div id="rightBot">
					<p class="top"></p>
					<div id="rightBot2" style="white-space:normal;word-wrap:break-word;overflow:auto;height: 140px">
						<table id="addFirstPage_firstPage_picintro">
							<tr>
		  						<td><textarea id="addFirstPagePicintro_container" name="addFirstPagePicintro_container" style="width: 380px;padding-top: 10px"></textarea></td>
		  					</tr>
		  					<tr align="right">
		  						<td><img src="../../images/first_save.png" id="addFirstPagePicIntro_save" style="color: black;cursor:pointer;"/></td>
		  					</tr>
						</table>
						<!-- <ul class="rightLink1">
							<li><a href="#"><strong style="color: #000099">About the statue of liberty: </strong></a></li>
							<li><a href="#" style="color: #000099">"Statue of Liberty",bronze statues,47 meters high pedestal,like high 46 meters,93 meters high total,completed in 1886,is located in New York Port Menpang the freedom of the island.</a></li>
						</ul> -->
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
					<div id="addFirstPage_introTable" style="text-indent:2em;font:12px/21px Arial,Helvetica,sans-serif;color: #000099;height: 210px;width: 430px;word-wrap:break-word;overflow:hidden;text-overflow:ellipsis;">
					</div>
				</div>
				<div id="best" style="height: 145px">
					<h2><span style="font-size: 16px;color:#000099">Show</span><button id="addOrUpdateFirstPic_but">change</button></h2>
					<div id="addfirstPagePics_div" class="picScroll" style="width: 645px;height: 115px;">
						<ul id="addfirstPagePics">
							<li id="addfirstPagePics1"></li>
							<li id="addfirstPagePics2"></li>
							<li id="addfirstPagePics3"></li>
							<li id="addfirstPagePics4"></li>
							<li id="addfirstPagePics5"></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="last" style="border: 1px solid #d1d1e8;height:670px;">
			<!-- <img src="../images/add_back.png" id="addFirstPage_back" style="color: black;cursor:pointer;padding-left: 5px;padding-top: 5px"/>
			<img src="../images/add_save.png" id="addFirstPage_save" style="color: black;cursor:pointer;padding-top: 5px"/> -->
			<img src="../../images/login1.png" style="color: black;padding-left: 5px;padding-top: 5px"/>
			<div style="width: 220px;height: 190px;background:url(../../images/why_best.gif) 0 0 repeat-x;">
				<p class="lastTop"></p>
				<h2 class="res"><span style="font-size: 16px;color:#000099">Our Working Hours</span></h2>
				<table id="addFirstPage_workingHours_table" style="width:180px;">
					<tr>
  						<td><textarea id="addFirstPageWork_container" name="addFirstPageWork_container" style="width: 180px;"></textarea></td>
  					</tr>
				</table>
			</div>
			<div style="width:205px;height: 25px" align="right">
				<img src="../../images/first_save.png" id="addFirstPageWork_save" style="color: black;cursor:pointer;"/>
			</div>
			<div style="width: 220px;height: 450px;background:url(../../images/why_best.gif) 0 0 repeat-x;" id="addfirstHelp_div">
				<div style="width: 220px;height: 50px">
					<h2 class="future" style="width: 200px"><span style="width: 180px;font-size: 16px;color:#000099">Help And Support</span></h2>
				</div>
			</div>
		</div>
		<br class="spacer" />
	</div>
	<!-- 图片滚动 --> 
	<script type="text/javascript" 
		src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/jquery.SuperSlide.2.1.1.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/all.js" ></script>
</body>
</html>