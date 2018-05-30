<%@ page language="java" pageEncoding="UTF-8"%>
<style type="text/css">

.uploader { display:inline-block; overflow:hidden; cursor:default; padding:0; margin:10px 0px; -moz-box-shadow:0px 0px 0px #ddd; -webkit-box-shadow:0px 0px 0px #ddd; box-shadow:0px 0px 5px #ddd; -moz-border-radius:0px; -webkit-border-radius:0px; border-radius:5px; }
.filename { float:left; display:inline-block; outline:0 none; height:32px; width:180px; margin:0; padding:8px 10px; overflow:hidden; cursor:default; border:1px solid; border-right:0; font:9pt/100% Arial, Helvetica, sans-serif; color:#777; text-shadow:1px 1px 0px #fff; text-overflow:ellipsis; white-space:nowrap; -moz-border-radius:5px 0px 0px 5px; -webkit-border-radius:5px 0px 0px 5px; border-radius:5px 0px 0px 5px; background:#f5f5f5; background:-moz-linear-gradient(top, #fafafa 0%, #eee 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #fafafa), color-stop(100%, #f5f5f5)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#fafafa', endColorstr='#f5f5f5', GradientType=0);
border-color:#ccc; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; -moz-box-sizing:border-box; -webkit-box-sizing:border-box; box-sizing:border-box; }
.button { float:left; height:32px; display:inline-block; outline:0 none; padding:8px 12px; margin:0; cursor:pointer; border:1px solid; font:bold 9pt/100% Arial, Helvetica, sans-serif; -moz-border-radius:0px 5px 5px 0px; -webkit-border-radius:0px 5px 5px 0px; border-radius:0px 5px 5px 0px; -moz-box-shadow:0px 0px 1px #fff inset; -webkit-box-shadow:0px 0px 1px #fff inset; box-shadow:0px 0px 1px #fff inset; }
/* .uploader input[type=file] { top:0; right:0; bottom:0; border:0; padding:0; margin:0; height:75px;width:70px; cursor:pointer; filter:alpha(opacity=0); -moz-opacity:0; -khtml-opacity: 0; opacity:0; padding-top: 0px} */
 input[type=button]::-moz-focus-inner {
padding:0;
border:0 none;
-moz-box-sizing:content-box;
}
input[type=button]::-webkit-focus-inner {
padding:0;
border:0 none;
-webkit-box-sizing:content-box;
}
input[type=text]::-moz-focus-inner {
padding:0;
border:0 none;
-moz-box-sizing:content-box;
}
input[type=text]::-webkit-focus-inner {
padding:0;
border:0 none;
-webkit-box-sizing:content-box;
}
/* Blue Color Scheme ------------------------ */

.blue .button { color:#fff; text-shadow:1px 1px 0px #09365f; background:#064884; background:-moz-linear-gradient(top, #3b75b4 0%, #064884 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #3b75b4), color-stop(100%, #064884)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3b75b4', endColorstr='#064884', GradientType=0);
border-color:#09365f; }
.blue:hover .button { background:#3b75b4; background:-moz-linear-gradient(top, #064884 0%, #3b75b4 100%); background:-webkit-gradient(linear, left top, left bottom, color-stop(0%, #064884), color-stop(100%, #3b75b4)); filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#064884', endColorstr='#3b75b4', GradientType=0);
}
</style>
<script type="text/javascript">
	$(function(){
		$("input[type=file]").change(function(){
			$("#editFirstPagePic_pic1").val();
			$(this).parents(".uploader").find(".filename").val($(this).val());
		});
		$("input[type=file]").each(function(){
			if($(this).val()==""){
			$(this).parents(".uploader").find(".filename").val("Please Select...");
			}
		});
		$.ajax({
			url : '${pageContext.request.contextPath}/firstPageAction!findEditFirstPage.action',
			dataType : 'text',
			type : 'post',
			success : function(data){
				var obj = eval("("+data+")");
				if(obj.hasOwnProperty("pic1") && obj.pic1.length>0){
					$("#editFirstPagePic_pic1").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pic1+"");
				}
				if(obj.hasOwnProperty("pic2") && obj.pic2.length>0){
					$("#editFirstPagePic_pic2").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pic2+"");
				}
				if(obj.hasOwnProperty("pic3") && obj.pic3.length>0){
					$("#editFirstPagePic_pic3").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pic3+"");
				}
				if(obj.hasOwnProperty("pic4") && obj.pic4.length>0){
					$("#editFirstPagePic_pic4").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pic4+"");
				}
				if(obj.hasOwnProperty("pic5") && obj.pic5.length>0){
					$("#editFirstPagePic_pic5").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.pic5+"");
				} 
			}
		});
	});
	function savePics(){
		$.ajaxFileUpload({	
			url : '${pageContext.request.contextPath}/firstPageAction!upPics.action?id='+"<%= request.getParameter("id")%>"+'',
			type : 'post',
			dataType: 'text', 
			secureuri : false,
			fileElementId : ['editFirstPagePic1','editFirstPagePic2','editFirstPagePic3','editFirstPagePic4','editFirstPagePic5'], //type="file"的input id
			success : function(data) {
				location.reload();
				$.messager.show({
					title:'Message',
					msg:'Modified Image Successfully',
					timeout:5000,
					showType:'slide'
				});
			},
			error: function (data, status, e){//服务器响应失败处理函数
			}
		});
	}
	
	function deletePics(pic){
		$.ajax({
			url : '${pageContext.request.contextPath}/firstPageAction!deletePics.action',
			data : {"id" : "<%= request.getParameter("id")%>", "pic" : pic},
			type : 'post',
			dataType : 'text',
			success : function(data){
				var obj = $.parseJSON(data);
				d.dialog('refresh');
				$.messager.show({
					title:'Message',
					msg:'Delete Image Successfully',
					timeout:5000,
					showType:'slide'
				});
			}
		});
	}
	
	function tempClick(pic){
		if(pic == "pic1"){
			document.getElementById('editFirstPagePic1').click();
		}else if(pic == "pic2"){
			document.getElementById('editFirstPagePic2').click();
		}else if(pic == "pic3"){
			document.getElementById('editFirstPagePic3').click();
		}else if(pic == "pic4"){
			document.getElementById('editFirstPagePic4').click();
		}else if(pic == "pic5"){
			document.getElementById('editFirstPagePic5').click();
		}
	}  
	
	function PreviewImg(pic){
		var fileId;
		if(pic == 'pic1'){
			fileId = 'editFirstPagePic1';
		}else if(pic == 'pic2'){
			fileId = 'editFirstPagePic2';
		}else if(pic == 'pic3'){
			fileId = 'editFirstPagePic3';
		}else if(pic == 'pic4'){
			fileId = 'editFirstPagePic4';
		}else if(pic == 'pic5'){
			fileId = 'editFirstPagePic5';
		}
		$.ajaxFileUpload({	
			url : "${pageContext.request.contextPath}/firstPageAction!PreviewImg.action?pic="+pic+"",
			type : 'post',
			dataType: 'text', 
			secureuri : false,
			fileElementId : [fileId], //type="file"的input id
			success : function(data) {
				var obj = $.parseJSON(data);
				console.info(obj);
				if(obj.pic == 'pic1'){
					$("#editFirstPagePic_pic1").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.path+"");
				}else if(obj.pic == 'pic2'){
					$("#editFirstPagePic_pic2").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.path+"");
				}else if(obj.pic == 'pic3'){
					$("#editFirstPagePic_pic3").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.path+"");
				}else if(obj.pic == 'pic4'){
					$("#editFirstPagePic_pic4").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.path+"");
				}else if(obj.pic == 'pic5'){
					$("#editFirstPagePic_pic5").attr("src", "${pageContext.request.contextPath}/warehouseAction!showPic.action?path="+obj.path+"");
				}
			},
			error: function (data, status, e){//服务器响应失败处理函数
			}
		});
	};
	
</script>
	<div class="uploader blue">
		<div style="width: 40px;height: 50px;float: left;padding-top: 40px" align="center">
	    	<label>Pic1:</label>
		</div>
	    <div style="width: 100px;height: 100px;float: left;border: 1px solid #000099">	
	    	<input id="editFirstPagePic1" name="pic1" type="file" style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 100px;height: 100px;cursor: pointer" size="1" onchange="PreviewImg('pic1')"/>
	    	<img id="editFirstPagePic_pic1" style="width: 100px;height: 100px;cursor: pointer" onclick="tempClick('pic1')"/>
	    </div>
	    <div style="width: 25px;height: 25px;float: left;padding-left: 3px"> 
	    	<img style="cursor: pointer;" src="../../images/delete.png" onclick="deletePics('pic1');"/>
	    </div>
	    
	  	<div style="width: 40px;height: 50px;float: left;padding-top: 40px" align="center">
	    	<label>Pic2:</label>
		</div>
		<div style="width: 100px;height: 100px;float: left;border: 1px solid #000099">
			<input id="editFirstPagePic2" name="pic2" type="file" style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 100px;height: 100px;cursor: pointer" size="1" onchange="PreviewImg('pic2')"/>
	    	<img id="editFirstPagePic_pic2" style="width: 100px;height: 100px;cursor: pointer" onclick="tempClick('pic2')"/>
	    </div>
	  	<div style="width: 25px;height: 25px;float: left;padding-left: 3px"> 
	    	<img style="cursor: pointer;" src="../../images/delete.png" onclick="deletePics('pic2');"/>
	    </div>	

		<div style="width: 40px;height: 50px;float: left;padding-top: 40px" align="center">
	    	<label>Pic3:</label>
		</div>
		<div style="width: 100px;height: 100px;float: left;border: 1px solid #000099">
			<input id="editFirstPagePic3" name="pic3" type="file" style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 100px;height: 100px;cursor: pointer" size="1" onchange="PreviewImg('pic3')"/>
	    	<img id="editFirstPagePic_pic3" style="width: 100px;height: 100px;cursor: pointer" onclick="tempClick('pic3')"/>
	    </div>
	  	<div style="width: 25px;height: 25px;float: left;padding-left: 3px"> 
	    	<img style="cursor: pointer;" src="../../images/delete.png" onclick="deletePics('pic3');"/>
	    </div>
	    
	    <div style="width: 40px;height: 50px;float: left;padding-top: 40px" align="center">
	    	<label>Pic4:</label>
		</div>
		<div style="width: 100px;height: 100px;float: left;border: 1px solid #000099">
			<input id="editFirstPagePic4" name="pic4" type="file" style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 100px;height: 100px;cursor: pointer" size="1" onchange="PreviewImg('pic4')"/>
	    	<img id="editFirstPagePic_pic4" style="width: 100px;height: 100px;cursor: pointer" onclick="tempClick('pic4')"/>
	    </div>
	  	<div style="width: 25px;height: 25px;float: left;padding-left: 3px"> 
	    	<img style="cursor: pointer;" src="../../images/delete.png" onclick="deletePics('pic4');"/>
	    </div>
	    
	    <div style="width: 40px;height: 50px;float: left;padding-top: 40px" align="center">
	    	<label>Pic5:</label>
		</div>
		<div style="width: 100px;height: 100px;float: left;border: 1px solid #000099">
			<input id="editFirstPagePic5" name="pic5" type="file" style="position: absolute; filter: alpha(opacity = 0); opacity: 0; width: 100px;height: 100px;cursor: pointer" size="1" onchange="PreviewImg('pic5')"/>
	    	<img id="editFirstPagePic_pic5" style="width: 100px;height: 100px;cursor: pointer" onclick="tempClick('pic5')"/>
	    </div>
	  	<div style="width: 25px;height: 25px;float: left;padding-left: 3px"> 
	    	<img style="cursor: pointer;" src="../../images/delete.png" onclick="deletePics('pic5');"/>
	    </div>	

		<div style="padding-top: 120px;padding-left: 40px">
			<input onclick="savePics();" type="button" class="button" value="submit"/>
		</div>
	</div>