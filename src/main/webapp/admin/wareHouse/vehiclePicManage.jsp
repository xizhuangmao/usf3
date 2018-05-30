<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="warehousevehicleInfo_div" class="easyui-layout" data-options="fit:true" style="width:830px;height:600px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;">
		    	<form id="warehouseVehicleInf_form" name="warehouseVehicleInf_form" class="datagrid-toolbar" method="post" style="border: 0px;background-color: white">
			    	<table>
				    	<thead>
				    		<tr>
				    			<th>vin:</th>
				    			<td><input id="warehousevehicleInfo_vin" name="vin" readonly="readonly" style="border:0px"/></td>
				    			<th>make:</th>
				    			<td><input id="warehousevehicleInfo_make" name="make" readonly="readonly" style="border:0px"/></td>
				    			<th>model:</th>
				    			<td><input id="warehousevehicleInfo_model" name="model" readonly="readonly" style="border:0px"/></td>
				    			<th>year:</th>
				    			<td><input id="warehousevehicleInfo_year" name="year" readonly="readonly" style="border:0px;width: 100px"/></td>
				    		</tr>
				    		<tr>
				    			<th>color:</th>
				    			<td><input id="warehousevehicleInfo_color" name="color" readonly="readonly" style="border:0px"></td>
				    			<th>engine:</th>
				    			<td><input id="warehousevehicleInfo_engine" name="engine" readonly="readonly" style="border:0px"/></td>
				    			<th>sticker:</th>
				    			<td><input id="warehousevehicleInfo_sticker" name="sticker" readonly="readonly" style="border:0px"/></td>
				    			<th>fuel:</th>
				    			<td><input id="warehousevehicleInfo_fuel" name="fuel" readonly="readonly" style="border:0px;width: 100px"/></td>
				    		</tr>
			    			<tr>
				    			<th>loaddate:</th>
				    			<td><input id="warehousevehicleInfo_loadingdate" name="loaddate" readonly="readonly" style="border:0px"/></td>
				    			<th>ETD:</th>
				    			<td><input id="warehousevehicleInfo_etd" name="entrydate" readonly="readonly" style="border:0px"/></td>
				    			<th>vesslname:</th>
				    			<td><input id="warehousevehicleInfo_vessel" name="vessel" readonly="readonly" style="border:0px"/></td>
				    		</tr>
				    		<tr>
				    			<th>voyage:</th>
				    			<td><input id="warehousevehicleInfo_voyage" name="voyage" readonly="readonly" style="border:0px"/></td>
				    			<th>UOO:</th>
				    			<td><input id="warehousevehicleInfo_uoo" name="uoo" readonly="readonly" style="border:0px"/></td>
				    		</tr>
						</thead>
			    	</table>
			    	<p style="border-top: 1px solid #979191;width: 788px"></p>
				    <table>
				    	<thead>
				    		<tr>
				    			<th>Up Type</th>
				    			<td><input id="admin_wareHouse_wareHouseVehicleInfo_picType" class="easyui-combobox" name="dept"   
    									data-options="valueField: 'value',textField: 'label',data: [{label: 'Receive',value: '1',selected:true},{label: 'Load',value: '2'}]" /></td>
								<th>Image Upload :</th>
								<td><input id="warehousevehicleInfouploadId" name="warehousevehicleInfoupload" type="file" value="Choose" multiple="true"/></td>
								<td><input type="button" onclick="setImage()" value="upload"/></td>
							</tr> 
				    	</thead>
				    </table>
				    <p style="border-top: 1px solid #979191;width: 788px"></p>
				    <table>
				    	<thead>
				    		<tr>
			    				<th>Choose Type</th>
			    				<td><input id="admin_wareHouse_wareHouseVehicleInfo_type"/></td>
							</tr>
				    	</thead>
				    </table>
				    <p style="border-top: 1px solid #979191;width: 788px"></p>
				    <div style="width: 800px" id="warehousevehicleInfo_body">
			    	</div>
			    </form>
		    </div>   
		</div>  
		
	<script type="text/javascript">
		function setImage(){
			if($("#warehousevehicleInfouploadId").val().length == '0'){
				$.messager.show({
					title:'Message',
					msg:'Please select a file to upload',
					timeout:5000,
					showType:'slide'
				});
			}else{
				var type = $("#admin_wareHouse_wareHouseVehicleInfo_picType").combobox('getValue');
				$.ajaxFileUpload({	
					url : "warehouseAction!upPics.action?type="+type+"&vin=<%= request.getParameter("vin")%>",
					type : 'post',
					dataType: 'text', 
					secureuri : false,
					fileElementId:  "warehousevehicleInfouploadId", //type="file"的input id
					success : function(data) {
						var obj = $.parseJSON(data);
						$.messager.show({
							title:'Message',
							msg:obj,
							timeout:5000,
							showType:'slide'
						});
						var type = $('#admin_wareHouse_wareHouseVehicleInfo_type').combobox('getValue');
						findPicsByType(type);
						var file = $("#warehousevehicleInfouploadId");
						file.after(file.clone().val(""));
						file.remove(); 					
					},
					error: function (data, status, e){//服务器响应失败处理函数
					}
				});
			}
			
		}
		//选择车辆图片类型
		$('#admin_wareHouse_wareHouseVehicleInfo_type').combobox({
			onSelect: function(param){
				findPicsByType(param.value);	
			}
		});
		//进入页面加载车辆信息
		$(function(){
			findPicsByType("0");
			$('#admin_wareHouse_wareHouseVehicleInfo_type').combobox({    
			    valueField:'value',    
			    textField:'label',
			    data: [{
			    	label: 'ALL',
			    	value: '0',
			    	selected:true
			    	},{
			    	label: 'Receive',
			    	value: '1',
			    	},{
			    	label: 'Load',
			    	value: '2'
			    }],
				formatter: function(row){
					var str = "";
					if(row.label == 'Receive'){
						str = "<img style=\"width:15px;height:15px\" src=\"images/receive.png\"/>"+row.label+"";
					}else if(row.label == 'Load'){
						str = "<img style=\"width:15px;height:15px\" src=\"images/push.png\"/>"+row.label+"";
					}else{
						str = "<img style=\"width:15px;height:15px\"/>"+row.label+"";
					}
                    return str;     	
				}
			}); 
		});
		//查看大图
		function changePicSize(id){
			//-------------------添加 界面
					var d =$('<div/>').dialog({
						width:900,
						heigth:700,
						title:"Pic",
						modal:true,
						href:"${pageContext.request.contextPath}/admin/wareHouse/vehiclePicShow.jsp?id="+id+"",
						onClose:function(){
							d.dialog('destroy');
						},
					});
					
					var browserHeight = $(window).height();  //游览器
					var browserwidth = $(window).width();
					var width = d.panel('options').width;//获取容器的宽
					if(browserwidth>(width+200)){
						if(browserHeight>700){
							d.panel('resize',{
								left : 250,
								top  : 50,
							});
						}else{
							d.panel('resize',{
								left : 150,
								top  : 0,
							});
						}
						
					}else{
						if(browserHeight>700){
							d.panel('resize',{
								left : 0,
								top  : 150,
							});
						}else{
							d.panel('resize',{
								left : 0,
								top  : 0,
							});
						}
				}				
		}
		
		//删除车辆图片
		function removePic(picPath,id){
			$.messager.confirm('confirm', 'delete the pic?', function(r){
		    	if (r){
		    		$.ajax({
						url : 'warehouseAction!removeWareHousePic.action',
						data : {"picPath" : picPath, "id" : id},
						type : 'post',
						success : function(data){
							var obj = $.parseJSON(data);
							$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							var type = $('#admin_wareHouse_wareHouseVehicleInfo_type').combobox('getValue');
							//findPicsByType(type);
							$("#admin_wareHouse_vehiclePicmanageTd"+id+"").remove();
							
							if(obj.obj.pic == null || obj.obj.pic == ''){
								var whesdtlIndex = $("#admin_wareHouse_wareHouse_datagrid").datagrid('getRowIndex', obj.obj.id);
								$("#admin_wareHouse_wareHouse_datagrid").datagrid('updateRow', {
									index: whesdtlIndex,
									row: {
										pic: obj.obj.pic,
									}
								});
							}
						}
					});
		    	}else{}
			});
		}
		
		var admin_wareHouse_vehiclePicmanage_lastPicId = [];
		function slectArray(a, b){
			admin_wareHouse_vehiclePicmanage_lastPicId = [];
			for(var i=0;i<a.length;i++){
				if(b.indexOf(a[i])<0 ){
					admin_wareHouse_vehiclePicmanage_lastPicId.push(a[i]);
				}
			}
			return admin_wareHouse_vehiclePicmanage_lastPicId;
		} 
		
		//修改车辆图片
		function changePic(path,vin,id,whesdtlId){
			var admin_wareHouse_vehiclePicmanage_picId = [];
			admin_wareHouse_vehiclePicmanage_picId.push(id);
			$.ajax({
				url : 'warehouseAction!changeWareHousePic.action',
				data : {"picPath" : path, "picVin" : vin},
				type : 'post',
				dataType : 'text',
				success : function(data){
					var type = $('#admin_wareHouse_wareHouseVehicleInfo_type').combobox('getValue');
					$("#admin_wareHouse_vehiclePicmanage"+id+"").attr("src", "images/main.png");
					slectArray(admin_wareHouse_vehiclePicmanage_allPicId,admin_wareHouse_vehiclePicmanage_picId);
					for(var a=0;a<admin_wareHouse_vehiclePicmanage_lastPicId.length;a++){
						$("#admin_wareHouse_vehiclePicmanage"+admin_wareHouse_vehiclePicmanage_lastPicId[a]+"").attr("src", "images/main1.png");
					}

					var whesdtlIndex = $("#admin_wareHouse_wareHouse_datagrid").datagrid('getRowIndex', whesdtlId);
					
					$("#admin_wareHouse_wareHouse_datagrid").datagrid('updateRow', {
						index: whesdtlIndex,
						row: {
							pic: path,
						}
					});
				}
			});
		}
		
		//下载
		$('#vehicleInfo_down').bind('click',function(){
			var o=document.getElementsByName("pic");
			var value = '';
			var result = [];
			for(i=0;i<o.length;i++){
				if(o[i].checked){
					value = value + o[i].value;
					result.push(o[i].value);
				}
			}
			if(value==''){
				alert("请选择!");
			}else{
				$(this).attr("href","${pageContext.request.contextPath}/warehouseAction!downVehiclePics.action?picName="+result.join(","));
			}
		});
		
		//全选
		$('#vehicleInfo_selectAll').bind('click',function(){
			var checklist = document.getElementsByName("pic");
		    if(document.getElementById("vehicleInfo_selectAll").checked){
			    for(var i=0;i<checklist.length;i++){
			    	checklist[i].checked = 1;
			    }
		 	}else{
			  	for(var j=0;j<checklist.length;j++){
			    	checklist[j].checked = 0;
			    }
		    }
		});
		
		var admin_wareHouse_vehiclePicmanage_allPicId = [];
		function findPicsByType(type){
			admin_wareHouse_vehiclePicmanage_allPicId = [];
			$.ajax({
				url : 'warehouseAction!findWareHouseVehicleInfo.action',
				type : 'post',
				data : {"vin" : "<%= request.getParameter("vin")%>", "type" : type},
				dataType : 'text',
				success : function(data){
					$("#warehousevehicleInfo_body").html("");
					var obj = $.parseJSON(data);
					for(var i=0;i<obj.pic.length;i++){
						admin_wareHouse_vehiclePicmanage_allPicId.push(obj.pic[i].id);
					}
					var pic;
 					for(var i=0;i<obj.whesdtl.rows.length;i++){
 						$("#warehouseVehicleInf_form").form('load', obj.whesdtl.rows[i]);
						document.getElementById('warehousevehicleInfo_vin').innerHTML = obj.whesdtl.rows[i].vin;
						pic = obj.whesdtl.rows[i].pic;
					}
					appendWarehousevehicleInfo_body(obj,pic);
				}
			});
		}
		
		//修改图片的type属性
		function changePicType(id){
		$("#"+id+"").next(".combo").show();
			$("#"+id+"").combobox({    
			    valueField:'value',    
			    textField:'label',
			    panelHeight:'60px',
			    editable: false,
			    height:'22px',
			    data: [{
			    	label: 'Receive',
			    	value: '1',
			    	},{
			    	label: 'Load',
			    	value: '2'
			    }],
				formatter: function(row){
					var str = "";
					if(row.label == 'Receive'){
						str = "<img style=\"width:15px;height:15px\" src=\"images/receive.png\"/>"+row.label+"";
					}else if(row.label == 'Load'){
						str = "<img style=\"width:15px;height:15px\" src=\"images/push.png\"/>"+row.label+"";
					}else{
						str = "<img style=\"width:15px;height:15px\"/>"+row.label+"";
					}
                    return str;     	
				},
				onSelect:function(param){
					$.ajax({
						url:'warehouseAction!changePicType.action',
						data:{"id":id, "type":param.value},
						type:'post',
						dataType:'text',
						success:function(){
							$("#"+id+"").next(".combo").hide();
							var pictype = $('#admin_wareHouse_wareHouseVehicleInfo_type').combobox('getValue');
							findPicsByType(pictype);
						}
					});
				}
			}); 
		}
		
		function appendWarehousevehicleInfo_body(obj,pic){
			for(var i=0;i<obj.pic.length;i++){
				if(obj.pic[i].pictype == '1'){
					$("#warehousevehicleInfo_body").append("<div id=\"admin_wareHouse_vehiclePicmanageTd"+obj.pic[i].id+"\" style=\"width:200px;height:145px;float:left\">" + 
																"<img style=\"float:left\" onclick=\"changePicType('"+obj.pic[i].id+"')\" src=\"images/receive.png\"/>" + 
																"<div style=\"height:22px;float:left\"><input id=\""+obj.pic[i].id+"\" style=\"width:80px;border:0px\" /></div>" + 
																"<div style=\"float:right;padding-right:18px\"><img id=\"admin_wareHouse_vehiclePicmanage"+obj.pic[i].id+"\" class=\""+obj.pic[i].id+"\" onclick=\"changePic('"+obj.pic[i].path+"','"+obj.pic[i].vin+"','"+obj.pic[i].id+"','"+obj.whesdtl.rows[0].id+"')\" src=\"images/main1.png\"/>" + 
																"<a onclick=\"removePic('"+obj.pic[i].path+"','"+obj.pic[i].id+"')\" style='cursor: pointer'><img src=\"images/deletepic.png\"/></a></div>" + 
																"<img onclick=\"changePicSize('"+obj.pic[i].id+"')\" style='width:180px;height:120px' src=\"warehouseAction!showWhesdtlPics.action?path="+obj.pic[i].path+"\"/></div>");	
				}else{
					$("#warehousevehicleInfo_body").append("<div id=\"admin_wareHouse_vehiclePicmanageTd"+obj.pic[i].id+"\" style=\"width:200px;height:145px;float:left\">" + 
																"<img style=\"float:left\" onclick=\"changePicType('"+obj.pic[i].id+"')\" src=\"images/push.png\"/>" + 
																"<div style=\"height:22px;float:left\"><input id=\""+obj.pic[i].id+"\" style=\"width:80px;border:0px\" /></div>" + 
																"<div style=\"float:right;padding-right:18px\"><img id=\"admin_wareHouse_vehiclePicmanage"+obj.pic[i].id+"\" class=\""+obj.pic[i].id+"\" onclick=\"changePic('"+obj.pic[i].path+"','"+obj.pic[i].vin+"','"+obj.pic[i].id+"','"+obj.whesdtl.rows[0].id+"')\" src=\"images/main1.png\"/>" + 
																"<a onclick=\"removePic('"+obj.pic[i].path+"','"+obj.pic[i].id+"')\" style='cursor: pointer'><img src=\"images/deletepic.png\"/></a></div>" +
																"<img onclick=\"changePicSize('"+obj.pic[i].id+"')\" style='width:180px;height:120px' src=\"warehouseAction!showWhesdtlPics.action?path="+obj.pic[i].path+"\"/></div>");
				}
				if(pic == obj.pic[i].path){
				$("."+obj.pic[i].id+"").attr("src", "images/main.png"); 
				} 
			}
		}
	</script>