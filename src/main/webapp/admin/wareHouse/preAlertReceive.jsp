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
				$("#admin_warehouse_preAlertReceive_make").combobox({
					valueField: 'id',    
       				textField: 'make',
       				value: 'DEFAULT',
        			data: obj.make,
				});
				$("#admin_warehouse_preAlertReceive_model").combobox({
       				value: 'DEFAULT',
        		});
				$("#admin_warehouse_preAlertReceive_whes").combobox({
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
			   			 console.info(obj);
			   			 var firstModel = obj.model;
			   			 $("#admin_warehouse_preAlertReceive_form").form('load',obj);
			   			 var url = 'modelAction!findModelByMakeId.action?makeId=' + obj.makeId; 
        				 $.ajax({      								//根据选中的makeId获取对应的model
        					 url : url,
							 type : "post",
							 dataType : "text",
							 success : function(r){
								 var obj = $.parseJSON(r);
								 obj.unshift({id:'DEFAULT',model:'DEFAULT'});
								 $("#admin_warehouse_preAlertReceive_model").combobox({
									 valueField: 'model',    
       								 textField: 'model',
        							 data: obj,
        						 });
        						 if(firstModel != null && firstModel != ''){
        							 $("#admin_warehouse_preAlertReceive_model").combobox('setValue', firstModel);
        						 }
							 }
        				});
			   		 }
			    });
			}
		});
		
		//加载make对应的model
		$("#admin_warehouse_preAlertReceive_make").combobox({
			onSelect:function(record){
        		var url = 'modelAction!findModelByMakeId.action?makeId=' + record.id; 
        		$.ajax({      								//根据选中的makeId获取对应的model
        			url : url,
					type : "post",
					dataType : "text",
					success : function(r){
						var obj = $.parseJSON(r);
						obj.unshift({id:'DEFAULT',model:'DEFAULT'});
						$("#admin_warehouse_preAlertReceive_model").combobox({
							valueField: 'model',    
       						textField: 'model',
        					data: obj,
        				});
					}
        		});   
        	}  
		});		
	});
	    
	$("#admin_warehouse_preAlertReceive_form").form({
  		url:"warehouseAction!inWarehouseInfo.action",
  		success:function(data){
  			var obj = $.parseJSON(data);
  			if(obj.msg == 'success'){
  				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid('reload');
				admin_warehouse_preAlertWarehouse_preAlertReceive.dialog('destroy'); 
  			}else{
				$.messager.alert('warning', obj.msg);
			}
  		}
  	});
		
	$("#admin_warehouse_preAlertReceive_submit").click(function(){
		var vin = $("#admin_warehouse_preAlertReceive_vin").val();
		var rex = "^[A-Za-z0-9]+$";
		var regExp = new RegExp(rex);
		if(!regExp.test(vin)){
			$.messager.alert('warning','Please enter the correct vin and customer and make');
		}else{
			$("#admin_warehouse_preAlertReceive_form").submit();
		}
	});
	
	$("#admin_warehouse_preAlertReceive_users").combogrid({
		url:'warehouseAction!findCustomerByFullName.action',
		panelWidth : 500,
		panelHeight : 200,
		idField : 'name',
		textField : 'name',
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
            $("#admin_warehouse_preAlertReceive_users").combogrid('setValue', rowData.fullname);
        }
	});
	
	$("#admin_warehouse_preAlertReceive_remarkTd").hide();
	$("#admin_warehouse_preAlertReceive_memoTd").hide();
	$("#admin_warehouse_preAlertReceive_titleTd").hide();
	$("#admin_warehouse_preAlertReceive_billTd").hide();
	$("#admin_warehouse_preAlertReceive_proofTd").hide();
	function admin_warehouse_preAlertReceive_addTr(val){
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
		<div id="admin_warehouse_preAlertReceive_div" class="easyui-layout" data-options="fit:true" style="width:600px;height:800px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
				<form id="admin_warehouse_preAlertReceive_form" name="form" class="datagrid-toolbar" method="post">
					<table id="inWarehouse_table">
						<tr>
							<th></th>
							<td><input name="id" id="admin_warehouse_preAlertReceive_id" type="hidden"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Customer:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_users" name="users" style="width: 300px"></select>
							</td>						
						</tr>
						<tr>
							<th><span style="color: red">*</span>Vin #:</th>
							<td><input name="vin" id="admin_warehouse_preAlertReceive_vin" style="width: 295px" class="easyui-validatebox" data-options="required:true,validType:'onlyLength[17]'" /></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Make:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_make" name="makeId" style="width: 300px" data-options="editable:false"></select>
							</td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Model:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_model" name="model" style="width: 300px" data-options="editable:false"></select>
							</td>
						</tr>
						<tr>
							<th>Year:</th>
							<td><input name="year" id="admin_warehouse_preAlertReceive_year" style="width: 295px" placeholder="Enter Year" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Color:</th>
							<td><input name="color" id="admin_warehouse_preAlertReceive_color" style="width: 295px" placeholder="Enter color" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>Engine #:</th>
							<td><input name="engine" id="admin_warehouse_preAlertReceive_engine" style="width: 295px" placeholder="Enter Engine NO." class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>COD:</th>
							<td><input name="cod" id="admin_warehouse_preAlertReceive_cod" style="width: 295px" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>M/Date</th>
							<td><input name="mdate" id="admin_warehouse_preAlertReceive_mdate" style="width: 295px" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Free Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_freedate" name="freedate" class="easyui-datebox" style="width: 300px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th>Warehouse:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_whes" name="whesId" style="width: 300px" data-options="editable:false"></select>
							</td>	
						</tr>
						<tr>
							<th>Key</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_keynum" name="keynum" style="width: 300px">
									<option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option>
									<option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Vehicle Type:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_vehicletype" name="vehicletype" style="width: 300px">
									<option value="Vehicle">Vehicle</option>
									<option value="Motorcycle">Motorcycle</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Fuel Type:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_fuelType" name="fuelType" style="width: 300px">
									<option value="Gasoline">Gasoline</option>
									<option value="Diesel">Diesel</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Fuel Vol:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_fuel" name="fuel" style="width: 300px">
									<option value="0">0</option><option value="1/4">1/4</option><option value="1/3">1/3</option><option value="1/2">1/2</option><option value="3/4">3/4</option>
									<option value="1">1</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Sticker:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_sticker" name="sticker" style="width: 300px">
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
							<th>Floor Mat:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_floormat" name="floormat" style="width: 300px">
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
								<select id="admin_warehouse_preAlertReceive_dvdremote" name="dvdremote" style="width: 300px">
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
								<select id="admin_warehouse_preAlertReceive_heatset" name="heatset" style="width: 300px">
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
							<th>User Manual:</th>
							<td>
								<select id="admin_warehouse_preAlertReceive_usermanual" name="usermanual" style="width: 300px">
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
								<select id="admin_warehouse_preAlertReceive_titlestate" name="titlestate" style="width: 300px">
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
							<td><a onclick="admin_warehouse_preAlertReceive_addTr('admin_warehouse_preAlertReceive_titleTd');"><img id="admin_warehouse_preAlertReceive_titleTd1" src="images/up.png" /></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_preAlertReceive_titleTd">
						<tr>
							<th style="font-weight: normal">Received Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_title1" name="title1" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">To Customs Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_title2" name="title2" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Customs Back Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_title3" name="title3" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Back To Customer Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_title4" name="title4" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						</table>
						<table>
						<tr>
							<th>Bill:</th>
							<td style="padding-left: 60px"> 
								<select id="admin_warehouse_preAlertReceive_billstate" name="billstate" style="width: 300px">
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
							<td><a onclick="admin_warehouse_preAlertReceive_addTr('admin_warehouse_preAlertReceive_billTd');"><img id="admin_warehouse_preAlertReceive_billTd1" src="images/up.png" /></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_preAlertReceive_billTd">
						<tr>
							<th style="font-weight: normal">Received Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_bill1" name="bill1" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">To Customs Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_bill2" name="bill2" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Customs Back Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_bill3" name="bill3" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<th style="font-weight: normal">Back To Customer Date:</th>
							<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_bill4" name="bill4" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						</table>
						<table>
						<tr>
							<th>Proof:</th>
							<td style="padding-left: 45px">
								<select id="admin_warehouse_preAlertReceive_proofstate" name="proofstate" style="width: 300px">
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
							<td><a onclick="admin_warehouse_preAlertReceive_addTr('admin_warehouse_preAlertReceive_proofTd');"><img id="admin_warehouse_preAlertReceive_proofTd1" src="images/up.png" /></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_preAlertReceive_proofTd">
							<tr>
								<th style="font-weight: normal">Received Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_proof1" name="proof1" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">To Customs Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_proof2" name="proof2" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Customs Back Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_proof3" name="proof3" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>	
								<th style="font-weight: normal">Back To Customer Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_preAlertReceive_proof4" name="proof4" class="easyui-datebox" style="width: 240px" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
						</table>
						<table>
							<tr>
								<th>Remark:<a onclick="admin_warehouse_preAlertReceive_addTr('admin_warehouse_preAlertReceive_remarkTd');"><img id="admin_warehouse_preAlertReceive_remarkTd1" src="images/up.png" /></a></th>
								<td id="admin_warehouse_preAlertReceive_remarkTd"><textarea id="admin_warehouse_preAlertReceive_note" name="note" style="width: 300px;height: 180px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,300]'" ></textarea></td>
							</tr>
							<tr>
								<th>Memo:<a onclick="admin_warehouse_preAlertReceive_addTr('admin_warehouse_preAlertReceive_memoTd');"><img id="admin_warehouse_preAlertReceive_memoTd1" src="images/up.png" /></a></th>
								<td id="admin_warehouse_preAlertReceive_memoTd"><textarea id="admin_warehouse_preAlertReceive_memo" name="memo" style="width: 300px;height: 160px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,200]'" ></textarea></td>
							</tr>	
						</table>
					<table>
						<tr>
							<th style="padding-left: 80px"><input type="button" id="admin_warehouse_preAlertReceive_submit" value="receive"/></th>
						</tr>
					</table>
				</form>
			</div>   				
		</div>
