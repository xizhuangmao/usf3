<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
	    //加载make, model, whes
		$.ajax({
			url:'warehouseAction!findAllWhesMake.action',
			type:'post',
			dataType:'text',
			success:function(data){
				var obj = $.parseJSON(data);
				obj.make.unshift({id:'DEFAULT',make:'DEFAULT'});
				$("#admin_warehouse_vehicleEdit_make").combobox({
					valueField: 'id',    
       				textField: 'make',
       				value: 'DEFAULT',
        			data: obj.make,
				});
				$("#admin_warehouse_vehicleEdit_model").combobox({
       				value: 'DEFAULT',
        		});
				$("#admin_warehouse_vehicleEdit_whes").combobox({
					valueField: 'id',    
       				textField: 'fullname',
        			data: obj.whes,
				});
				
				//加载vin对应的车辆信息
			    $.ajax({
			   		 url : 'warehouseAction!findVehicleInfoById.action',
			   		 type : 'post',
			   		 dataType : 'text',
			   		 data : {"id" : "<%= request.getParameter("id")%>"},
			   		 success : function(data){
			   			 var obj = $.parseJSON(data);
			   			 var firstModel = obj.model;
			   			 $("#admin_warehouse_vehicleEdit_form").form('load',obj);
			   			 var url = 'modelAction!findModelByMakeId.action?makeId=' + obj.makeId; 
        				 $.ajax({							    //根据选中的makeId获取对应的model
        					 url : url,
							 type : "post",
							 dataType : "text",
							 success : function(r){
							 	 var obj = $.parseJSON(r);
								 obj.unshift({id:'DEFAULT',model:'DEFAULT'});
								 $("#admin_warehouse_vehicleEdit_model").combobox({
									 valueField: 'model',
       								 textField: 'model',
        							 data: obj,
        						 });
        						 if(firstModel != null && firstModel != ''){
        							 $("#admin_warehouse_vehicleEdit_model").combobox('setValue', firstModel);
        						 }
							 }
        				});
			   		 }
			    });
			}
		});
	    
	    $("#admin_warehouse_vehicleEdit_users").combogrid({
			url:'warehouseAction!findCustomerByFullName.action',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'fullname',
			textField : 'fullname',
			pagination : true,
			hasDownArrow:false,  
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'fullname',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns: [[{
				field:'fullname',
				title:'name',
				width:100,
				sortable:true,
			},{
				field:'logname',
				title:'logname',
				width:100,
			},{
				field:'address',
				title:'address',
				width:100,
			}]],
			onClickRow:function(rowIndex, rowData){
				 $("#admin_warehouse_vehicleEdit_users").combogrid('setValue', rowData.fullname);
			}
		});
	
	   	//加载make对应的model
		$("#admin_warehouse_vehicleEdit_make").combobox({
			onSelect:function(record){
        		var url = 'modelAction!findModelByMakeId.action?makeId=' + record.id; 
        		$.ajax({      								//根据选中的makeId获取对应的model
        			url : url,
					type : "post",
					dataType : "text",
					success : function(r){
						var obj = $.parseJSON(r);
						obj.unshift({id:'DEFAULT',model:'DEFAULT'});
						$("#admin_warehouse_vehicleEdit_model").combobox({
							valueField: 'model',    
       						textField: 'model',
        					data: obj,
        				});
					}
        		});   
        	}  
		});		
	
	  //通过vin号查询车辆memo
	  $.ajax({
			url:'memoAction!findVehicleMemoByVin.action',
			data:{"vin":"<%= request.getParameter("vin")%>"},
			dataType:'text',
			type:'post',
			success:function(value){
				var obj = $.parseJSON(value);
				$("#admin_warehouse_vehicleEdit_memoTable").empty();
				if(obj.length == 0){
					$("#admin_warehouse_vehicleEdit_memoTable").append("<tr><td><p style=\"color:#747474\">Please add the memo.</p></td></tr>");
				}else{
					for(var j=0;j<obj.length;j++){
						if(obj[j].content.length > 100){
							obj[j].content = obj[j].content.substring(0, 100) + "...";
						}
						if(j<5){
							$("#admin_warehouse_vehicleEdit_memoTable").append("<tr><td style=\"width:100px;word-wrap:break-word;float:left\">"+obj[j].users+"</td><td style=\"width:730px;float:left;word-wrap:break-word;\">"+obj[j].content+"</td><td style=\"float:left;padding-top:25px;padding-left:10px\"><img onclick=\"admin_warehouse_vehicleEdit_edit('"+obj[j].id+"');\" src=\"images/pencil.png\"/><br/><img onclick=\"admin_warehouse_vehicleEdit_delete('"+obj[j].id+"');\" src=\"images/no.png\"/></td></tr>" +
																				"<tr><td style=\"padding-left:103px;color:#929395\">"+obj[j].memodate+"</td></tr>" +
																					"<tr><td><p style=\"border-top: 1px solid #ceced0;width: 880px\"></p></td></tr>");
						}
					}
				}
			}
		});
	});
	
	//修改memo
	function admin_warehouse_vehicleEdit_edit(id){
		$("#admin_warehouse_vehicleEdit_div").scrollTop($("#admin_warehouse_vehicleEdit_form").height()); 
		$("#admin_warehouse_vehicleEdit_memoImput").val("");
		$("#admin_warehouse_vehicleEdit_memoTextarea").val("");
		$.ajax({
			url:'warehouseAction!findMemoContentById.action',
			data:{"id":id},
			type:'post',
			dataType:'text',
			success:function(data){
				var obj = $.parseJSON(data);
				$("#admin_warehouse_vehicleEdit_memoTextarea").val(obj.content);
			}
		});
		$("#admin_warehouse_vehicleEdit_memoImput").val(id);
		$("#admin_warehouse_vehicleEdit_submit").attr('src','images/edit.png');
		
	};
	
	//删除memo
	function admin_warehouse_vehicleEdit_delete(id){
		$.messager.confirm('confirm', 'delete the vehicle memo?', function(r){
		    if (r){
		    	$.ajax({
					url:'memoAction!deleteVehicleInfoById.action',
					data:{"id":id},
					dataType:'text',
					type:'post',
					success:function(data){
						var obj = $.parseJSON(data);
						if(obj.msg == 'success'){
							$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							reloadMemo();
						}else{
							$.messager.alert('warning', obj.msg);
							reloadMemo();
						}
					}
				});	
		    }
		});
	};
	
	$("#admin_warehouse_vehicleEdit_form").form({
	  	url:"warehouseAction!updateWarehouseInfo.action",
	  	success:function(data){
	  		var obj = $.parseJSON(data);
			if(obj.msg == 'success'){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide',
				});
				$("#admin_warehouse_vehicleEdit_memoTextarea").val("");
				$("#admin_warehouse_vehicleEdit_memoTable").empty();
				reloadMemo();
				$("#admin_warehouse_vehicleEdit_memoImput").val("");
				$("#admin_warehouse_vehicleEdit_submit").attr('src','images/confirm.png'); 
			}else{
				$.messager.alert('warning', obj.msg);
				$("#admin_warehouse_vehicleEdit_memoImput").val("");
				$("#admin_warehouse_vehicleEdit_memoTextarea").val("");
				$("#admin_warehouse_vehicleEdit_submit").attr('src','images/confirm.png');
			}
			$("#admin_wareHouse_wareHouse_datagrid").datagrid('reload');
	  	}
	});
	
	$("#admin_warehouse_vehicleEdit_submit").click(function(){
		var vin = $("#admin_warehouse_vehicleEdit_vin").val();
		var rex = "^[A-Za-z0-9]+$";
		var regExp = new RegExp(rex);
		if(vin == "" || !regExp.test(vin)){
			$.messager.alert('warning','Please enter the correct vin and customer and make');
		}else{
			$('#admin_warehouse_vehicleEdit_form').submit();
		}
	});
	
	function reloadMemo(){
		var vin = $("#admin_warehouse_vehicleEdit_vin").val();
		$.ajax({
			url:'memoAction!findVehicleMemoByVin.action',
			data:{"vin":vin},
			dataType:'text',
			type:'post',
			success:function(value){
				var obj = $.parseJSON(value);
				$("#admin_warehouse_vehicleEdit_memoTable").empty();
				if(obj.length == 0){
					$("#admin_warehouse_vehicleEdit_memoTable").append("<tr><td><p style=\"color:#747474\">Please add the memo.</p></td></tr>");
				}else{
					for(var j=0;j<obj.length;j++){
						for(var j=0;j<obj.length;j++){
							if(obj[j].content.length > 100){
								obj[j].content = obj[j].content.substring(0, 100) + "...";
							}
							if(j<5){
								$("#admin_warehouse_vehicleEdit_memoTable").append("<tr><td style=\"width:100px;word-wrap:break-word;float:left\">"+obj[j].users+"</td><td style=\"width:730px;float:left;word-wrap:break-word;\">"+obj[j].content+"</td><td style=\"float:left;padding-top:25px;padding-left:10px\"><img onclick=\"admin_warehouse_vehicleEdit_edit('"+obj[j].id+"');\" src=\"images/pencil.png\"/><br/><img onclick=\"admin_warehouse_vehicleEdit_delete('"+obj[j].id+"');\" src=\"images/no.png\"/></td></tr>" +
																							"<tr><td style=\"padding-left:103px;color:#929395\">"+obj[j].memodate+"</td></tr>" +
																								"<tr><td><p style=\"border-top: 1px solid #ceced0;width: 880px\"></p></td></tr>");
							}
						}
					}
				}
			}
		});
	}
	
	function admin_warehouse_vehicleEdit_addTr(val){
		if($("#"+val+"").is(':hidden')){
     		 $("#"+val+"").show();
     		 $("#"+val+'1'+"").attr('src', 'images/down.png');
    	}
    	else{
      		$("#"+val+"").hide();
      		$("#"+val+'1'+"").attr('src', 'images/up.png');
    	}
	}
</script>
		<div id="admin_warehouse_vehicleEdit_div" style="width:915px;height:790px;overflow:auto;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
				<form id="admin_warehouse_vehicleEdit_form" name="form" class="datagrid-toolbar" method="post">
					<table id="admin_warehouse_vehicleEdit_table">
						<tr>
							<th></th>
							<td><input name="id" id="admin_warehouse_vehicleEdit_id" type="hidden"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Customer:</th>
							<td>
								<input id="admin_warehouse_vehicleEdit_users" name="users" style="width: 360px"/>
							</td>
							<th>Warehouse:</th>
							<td>
								<input id="admin_warehouse_vehicleEdit_whes" name="whesId" class="easyui-combobox" style="width: 360px" data-options="editable:false" />
							</td>					
						</tr>
						<tr>
							<th><span style="color: red">*</span>Vin #:</th>
							<td><input name="vin" id="admin_warehouse_vehicleEdit_vin" style="width: 355px" class="easyui-validatebox" data-options="required:true,validType:'onlyLength[17]'" /></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Make:</th>
							<td>
								<input id="admin_warehouse_vehicleEdit_make" name="makeId" class="easyui-combobox" style="width: 360px" data-options="editable:false" />
							</td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Model:</th>
							<td>
								<input id="admin_warehouse_vehicleEdit_model" name="model" class="easyui-combobox" style="width: 360px" data-options="editable:false" />
							</td>
						</tr>
						<tr>
							<th>Year:</th>
							<td><input name="year" id="admin_warehouse_vehicleEdit_year" style="width: 355px" placeholder="Enter Year" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
							<th>Color:</th>
							<td><input name="color" id="admin_warehouse_vehicleEdit_color" style="width: 355px" placeholder="Enter color" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>Engine #:</th>
							<td><input name="engine" id="admin_warehouse_vehicleEdit_engine" style="width: 355px" placeholder="Enter Engine NO." class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
							<th>COD:</th>
							<td><input name="cod" id="admin_warehouse_vehicleEdit_cod" style="width: 355px" class="easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
						</tr>
						<tr>
							<th>M/Date</th>
							<td><input name="mdate" id="admin_warehouse_vehicleEdit_mdate" style="width: 355px" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
							<th>Key</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_keynum" name="keynum" style="width: 360px">
									<option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option>
									<option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>In Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_indate" name="indate" class="easyui-datebox" style="width: 360px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							<th>Free Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_freedate" name="freedate" class="easyui-datebox" style="width: 360px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th>Fuel Type:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_fuelType" name="fuelType" style="width: 360px">
									<option value="Gasoline">Gasoline</option>
									<option value="Diesel">Diesel</option>
								</select>
							</td>
							<th>Fuel Vol:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_fuel" name="fuel" style="width: 360px">
									<option value="0">0</option><option value="1/4">1/4</option><option value="1/3">1/3</option><option value="1/2">1/2</option><option value="3/4">3/4</option>
									<option value="1">1</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Sticker:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_sticker" name="sticker" style="width: 360px">
									<option value="NO">
										NO
									</option>
									<option value="YES">
										YES
									</option>
								</select>
							</td>
							<th>Floor Mat:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_floormat" name="floormat" style="width: 360px">
									<option value="NO">
										NO
									</option>
									<option value="YES">
										YES
									</option>
								</select>
							</td>
						</tr>					
						<tr>
							<th>Vehicle Type:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_vehicletype" name="vehicletype" style="width: 360px">
									<option value="Vehicle">Vehicle</option>
									<option value="Motorcycle">Motorcycle</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>User Manual:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_usermanual" name="usermanual" style="width: 360px">
									<option value="NO">
										NO
									</option>
									<option value="YES">
										YES
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>DVD Remote:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_dvdremote" name="dvdremote" style="width: 360px">
									<option value="NO">
										NO
									</option>
									<option value="YES">
										YES
									</option>
								</select>
							</td>
							
						</tr>
						<tr>
							<th>Heat Set:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_heatset" name="heatset" style="width: 360px">
									<option value="NO">
										NO
									</option>
									<option value="YES">
										YES
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Title:</th>
							<td>
								<select id="admin_warehouse_vehicleEdit_titlestate" name="titlestate" style="width: 360px">
									<option value="0">
										Not Received
									</option>
									<option value="1">
										Received
									</option>
									<option value="2">
										To Customs
									</option>
									<option value="3">
										Customs Back
									</option>
									<option value="4">
										Back to Customer
									</option>
								</select>
							</td>
							<td><a onclick="admin_warehouse_vehicleEdit_addTr('admin_warehouse_vehicleEdit_titleTd');"><img id="admin_warehouse_vehicleEdit_titleTd1" src="images/down.png"/></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_vehicleEdit_titleTd">
						<tr>
							<th style="font-weight: normal">Received Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_title1" name="title1" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">To Customs Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_title2" name="title2" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Customs Back Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_title3" name="title3" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Back To Customer Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_title4" name="title4" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						</table>
						<table>
						<tr>
							<th>Bill:</th>
							<td style="padding-left: 52px"> 
								<select id="admin_warehouse_vehicleEdit_billstate" name="billstate" style="width: 360px">
									<option value="0">
										Not Received
									</option>
									<option value="1">
										Received
									</option>
									<option value="2">
										To Customs
									</option>
									<option value="3">
										Customs Back
									</option>
									<option value="4">
										Back to Customer
									</option>
								</select>
							</td>
							<td><a onclick="admin_warehouse_vehicleEdit_addTr('admin_warehouse_vehicleEdit_billTd');"><img id="admin_warehouse_vehicleEdit_billTd1" src="images/down.png"/></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_vehicleEdit_billTd">
						<tr>
							<th style="font-weight: normal">Received Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_bill1" name="bill1" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">To Customs Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_bill2" name="bill2" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Customs Back Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_bill3" name="bill3" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Back To Customer Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_bill4" name="bill4" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						</table>
						<table>
						<tr>
							<th>Proof:</th>
							<td style="padding-left: 37px">
								<select id="admin_warehouse_vehicleEdit_proofstate" name="proofstate" style="width: 360px">
									<option value="0">
										Not Received
									</option>
									<option value="1">
										Received
									</option>
									<option value="2">
										To Customs
									</option>
									<option value="3">
										Customs Back
									</option>
									<option value="4">
										Back to Customer
									</option>
								</select>
							</td>
							<td><a onclick="admin_warehouse_vehicleEdit_addTr('admin_warehouse_vehicleEdit_proofTd');"><img id="admin_warehouse_vehicleEdit_proofTd1" src="images/down.png"/></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_vehicleEdit_proofTd">
							<tr>
								<th style="font-weight: normal">Received Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_proof1" name="proof1" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">To Customs Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_proof2" name="proof2" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Customs Back Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_proof3" name="proof3" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>	
								<th style="font-weight: normal">Back To Customer Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleEdit_proof4" name="proof4" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
						</table>
						<p style="border-top: 1px solid #747474;width: 890px"></p>
						<div align="center">
							<label><strong>Remark</strong><a onclick="admin_warehouse_vehicleEdit_addTr('admin_warehouse_vehicleEdit_remarkTd');"><img id="admin_warehouse_vehicleEdit_remarkTd1" src="images/down.png" /></a></label>
						</div>
						<table>
							<tr>
								<td id="admin_warehouse_vehicleEdit_remarkTd"><textarea id="admin_warehouse_vehicleEdit_note" name="note" style="width: 870px;height: 180px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,300]'"></textarea></td>
							</tr>	
						</table>
						<p style="border-top: 1px solid #747474;width: 890px"></p>
						<div align="center">
							<label><strong>Memo</strong></label>
						</div>
						<table id="admin_warehouse_vehicleEdit_memoTable"></table>
						<input id="admin_warehouse_vehicleEdit_memoImput" name="memoId" type="hidden"/>
					<textarea id="admin_warehouse_vehicleEdit_memoTextarea" name="memo" style="width: 880px;height: 60px;resize:none;border-radius:5px; border:solid 1px #747474;margin-left: 2px" placeholder="Please enter a comment on the content"></textarea>
				</form>

					<table style="width: 890px">
						<tr>
							<th align="right" ><img id="admin_warehouse_vehicleEdit_submit" src="images/confirm.png"/></th>
						</tr>
					</table>
			</div>   				
		</div>
