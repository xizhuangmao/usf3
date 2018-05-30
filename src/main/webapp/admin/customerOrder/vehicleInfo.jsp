<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="vehicleInfo_div" class="easyui-layout" data-options="fit:true" style="width:800px;height:600px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="vehicleInfo_from" method="post">
			    	<table>
				    	<thead>
				    		<tr>
				    			<td>*Vin#:</td>
				    			<td><span id="vehicleInfo_vin"></span></td>
				    		</tr>
				    		<tr>
				    			<td>make:</td>
				    			<td><span id="vehicleInfo_make"></span></td>
				    			<td>model:</td>
				    			<td><span id="vehicleInfo_model"></span></td>
				    			<td style="padding-left: 10px">year:</td>
				    			<td><span id="vehicleInfo_year"></span></td>
				    			<td style="padding-left: 10px">color:</td>
				    			<td><span id="vehicleInfo_color"></span></td>
				    		</tr>
				    		<tr>
				    			<td>Engine:</td>
				    			<td><span id="vehicleInfo_engine"></span></td>
				    			<td>Sticker:</td>
				    			<td><span id="vehicleInfo_sticker"></span></td>
				    			<td style="padding-left: 10px">Fuel:</td>
				    			<td><span id="vehicleInfo_fuel"></span></td>
				    		</tr>
							 
						</thead>	
			    	</table>
			    	<table>
			    		<thead>
			    			<tr>
								<td><input id="vehicleInfo_selectAll" type="checkbox"/>Select All</td>
								<td><a id="vehicleInfo_down" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">down</a></td>
							</tr> 
						</thead>
			    	</table>
			    	<table>
						<tbody id="vehicleInfo_body">
							
						</tbody>  	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		//进入页面加载车辆信息
		$(function(){
			$.ajax({
				url : 'warehouseAction!findVehicleInfo.action',
				type : 'post',
				data : {"vin" : "<%= request.getParameter("vin")%>"},
				dataType : 'text',
				success : function(data){
					var obj = $.parseJSON(data);
					$("#vehicleInfo_vin").text(obj.whesdtl.vin);
					$("#vehicleInfo_make").text(obj.whesdtl.make);
					$("#vehicleInfo_model").text(obj.whesdtl.model);
					$("#vehicleInfo_year").text(obj.whesdtl.year);
					$("#vehicleInfo_color").text(obj.whesdtl.color);
					$("#vehicleInfo_engine").text(obj.whesdtl.engine);
					$("#vehicleInfo_sticker").text(obj.whesdtl.sticker);
					$("#vehicleInfo_fuel").text(obj.whesdtl.fuel);

					for(var i=0;i<obj.pic.length;i++){						
						if((i+1)%4 == 0){
							$("#vehicleInfo_body").append("<td style=\"width:200px;height:120px\"><input id='"+obj.pic[i].name+"' value='"+obj.pic[i].name+"' name='pic' type='checkbox'/><img style='width:180px;height:120px' src=\"warehouseAction!showWhesdtlPics.action?path="+obj.pic[i].path+"\"/></td>");
							$("#vehicleInfo_body").append("<tr></tr>");
						}else{
							$("#vehicleInfo_body").append("<td style=\"width:200px;height:120px\"><input id='"+obj.pic[i].name+"' value='"+obj.pic[i].name+"' name='pic' type='checkbox'/><img style='width:180px;height:120px' src=\"warehouseAction!showWhesdtlPics.action?path="+obj.pic[i].path+"\"/></td>");
						}
					}
				}
			});
		});
		
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
				$.messager.show({
					title:'Message',
					msg:'Please select to download the picture!',
					timeout:5000,
					showType:'slide'
				});
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
	</script>
	