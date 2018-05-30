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
				$("#admin_warehouse_vehicleReceive_make").combobox({
					valueField: 'id',    
       				textField: 'make',
       				value: 'DEFAULT',
        			data: obj.make,
				});
				$("#admin_warehouse_vehicleReceive_model").combobox({
       				value: 'DEFAULT',
        		});
				$("#admin_warehouse_vehicleReceive_whes").combobox({
					valueField: 'id',
       				textField: 'fullname',
        			data: obj.whes,
        			required:true
				});
				var myDate = new Date();
				$("#admin_warehouse_vehicleReceive_indate").datebox('setValue', myDate.toLocaleDateString());
			}
		});
		
		//加载make对应的model
		$("#admin_warehouse_vehicleReceive_make").combobox({
			onSelect:function(record){
        		var url = 'modelAction!findModelByMakeId.action?makeId=' + record.id;
        		$.ajax({      								//根据选中的makeId获取对应的model
        			url : url,
					type : "post",
					dataType : "text",
					success : function(r){
						var obj = $.parseJSON(r);
						obj.unshift({id:'DEFAULT',model:'DEFAULT'});
						$("#admin_warehouse_vehicleReceive_model").combobox({
							valueField: 'model',    
       						textField: 'model',
        					data: obj,
        				});
					}
        		});   
        	}  
		});		
	});	
		
	$("#admin_warehouse_vehicleReceive_form").form({
  		url:"warehouseAction!addVehicleInfToWarehouse.action",
  		success:function(data){
  			var obj = $.parseJSON(data);
			if(obj.msg == 'success'){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				$("<div id='admin_wareHouse_vehicleReceiveChooseDiv'/>").dialog({
					title: 'What do you want to do next',
					width: 300,
					height: 200,
					href: "${pageContext.request.contextPath}/admin/wareHouse/vehicleReceiveChoose.jsp?vin="+obj.obj.vin+"",
					modal: true,
					onClose:function(){
						$(this).dialog('destroy');
					}
				});
			}else{
				$.messager.alert('warning', obj.msg);
			}
  		}
  	});

	$("#admin_warehouse_vehicleReceive_submit").click(function(){
		var vin = $("#admin_warehouse_vehicleReceive_vin").val();
		var rex = "^[A-Za-z0-9]+$";
		var regExp = new RegExp(rex);
		if(!regExp.test(vin)){
			$.messager.alert('warning','Please enter the correct vin and customer and make');
		}else{
			$('#admin_warehouse_vehicleReceive_form').submit();
		};
	});

	$("#admin_warehouse_vehicleReceive_users").combogrid({
		url:'warehouseAction!findCustomerByFullName.action',
		panelWidth : 500,
		panelHeight : 200,
		idField : 'id',
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
			field:'id',
			title:'Id',
			hidden:true,
		},{
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
		onLoadSuccess:function(data){
			if(data.rows.length > 0){
				if($("#admin_warehouse_vehicleReceive_users").combogrid("getValue") == data.rows[0].fullname || 
					$("#admin_warehouse_vehicleReceive_users").combogrid("getValue") == data.rows[0].logname){
					$("#admin_warehouse_vehicleReceive_users").combogrid('grid').datagrid('selectRecord',data.rows[0].id);
					$("#admin_warehouse_vehicleReceive_users").combogrid('setValue', data.rows[0].id);
				}
			}
		},
		onClickRow:function(rowIndex, rowData){
            $('#admin_warehouse_vehicleReceive_users').combogrid('setValue', rowData.id);
        }
	});
	
	$("#admin_warehouse_vehicleReceive_remarkTd").hide();
	$("#admin_warehouse_vehicleReceive_memoTd").hide();
	$("#admin_warehouse_vehicleReceive_titleTd").hide();
	$("#admin_warehouse_vehicleReceive_billTd").hide();
	$("#admin_warehouse_vehicleReceive_proofTd").hide();
	function admin_warehouse_vehicleReceive_addTr(val){
		if($("#"+val+"").is(':hidden')){
     		 $("#"+val+"").show();
     		 $("#"+val+'1'+"").attr('src', 'images/up.png');
    	}
    	else{
      		$("#"+val+"").hide();
      		$("#"+val+'1'+"").attr('src', 'images/down.png');
    	}
	}
	
	//----------------------------创建新的make-----------------------------------------
	admin_dateManage_MakeManage_datagrid_add = function (){
		var d =$('<div/>').dialog({
			width:350,
			heigth:200,
			title:"Make Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/makeAdd.jsp',
			onClose:function(){
				$.ajax({
					url:'warehouseAction!findAllWhesMake.action',
					type:'post',
					dataType:'text',
					success:function(data){
						var obj = $.parseJSON(data);
						obj.make.unshift({id:'DEFAULT',make:'DEFAULT'});
						$("#admin_warehouse_vehicleReceive_make").combobox({
							valueField: 'id',    
		       				textField: 'make',
		       				value: 'DEFAULT',
		        			data: obj.make,
						});
					}
				});
				d.dialog('destroy');
			},
		});
			
		changePanalPlace(d);
	};
	//----------------------------创建新的model-----------------------------------------
	admin_dateManage_ModelManage_datagrid_add = function (){
		var d =$('<div/>').dialog({
			width:320,
			heigth:200,
			title:"Model Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/modelAdd.jsp',
			onClose:function(){
				var admin_dateManage_Model_ModelAdd_makeId = $("#admin_dateManage_Model_ModelAdd_from_make").combobox('getValue');
				var admin_warehouse_vehicleReceive_makeId = $("#admin_warehouse_vehicleReceive_make").combobox('getValue');
				if(admin_dateManage_Model_ModelAdd_makeId == admin_warehouse_vehicleReceive_makeId){
					var url = 'modelAction!findModelByMakeId.action?makeId=' + admin_dateManage_Model_ModelAdd_makeId; 
	        		$.ajax({      								//根据选中的makeId获取对应的model
	        			url : url,
						type : "post",
						dataType : "text",
						success : function(r){
							var obj = $.parseJSON(r);
							obj.unshift({id:'DEFAULT',model:'DEFAULT'});
							$("#admin_warehouse_vehicleReceive_model").combobox({
								valueField: 'model',    
	       						textField: 'model',
	        					data: obj,
	        				});
						}
	        		}); 
        		}
				d.dialog('destroy');
			},
		});
			
		changePanalPlace(d);
	};
	changePanalPlace = function (d){
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = d.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				d.panel('resize',{
					left : 615,
					top  : 200,
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
	};
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'Vehicle',split:true" style="height:100%" border="false">
			<label id="admin_warehouse_vehicleReceive_label"></label>
				<form id="admin_warehouse_vehicleReceive_form" name="form" class="datagrid-toolbar" method="post">
					<table id="vehicle_table">
						<tr>
							<th></th>
							<td><input name="id" id="admin_warehouse_vehicleReceive_id" type="hidden"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Vin #:</th>
							<td><input name="vin" id="admin_warehouse_vehicleReceive_vin" style="width: 295px" class="easyui-validatebox" data-options="required:true,validType:'onlyLength[17]'" /></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Customer:</th>
							<td>
								<input id="admin_warehouse_vehicleReceive_users" name="usersId" style="width: 300px;"/> 
							</td>						
						</tr>
						<tr>
							<th><span style="color: red">*</span>Warehouse:</th>
							<td>
								<input id="admin_warehouse_vehicleReceive_whes" name="whesId" style="width: 300px" data-options="editable:false"/>
							</td>	
						</tr>
						<tr>
							<th>Make:</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_make" name="makeId" style="width: 300px" data-options="editable:false">
								</select>
								<img src="jslib/jquery-easyui-1.4.4/themes/icons/edit_add.png" onclick="admin_dateManage_MakeManage_datagrid_add();"/>
							</td>
						</tr>
						<tr>
							<th>Model:</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_model" name="model" style="width: 300px" data-options="editable:false">
								</select>
								<img src="jslib/jquery-easyui-1.4.4/themes/icons/edit_add.png" onclick="admin_dateManage_ModelManage_datagrid_add();"/>
							</td>
						</tr>
						<tr>
							<th>Year:</th>
							<td><input name="year" id="admin_warehouse_vehicleReceive_year" style="width: 295px" placeholder="Enter Year" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Color:</th>
							<td><input name="color" id="admin_warehouse_vehicleReceive_color" style="width: 295px" placeholder="Enter color" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>Engine #:</th>
							<td><input name="engine" id="admin_warehouse_vehicleReceive_engine" style="width: 295px" placeholder="Enter Engine NO." class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>COD:</th>
							<td><input name="cod" id="admin_warehouse_vehicleReceive_cod" style="width: 295px" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>M/Date</th>
							<td><input name="mdate" id="admin_warehouse_vehicleReceive_mdate" style="width: 295px" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>In Date:</th>
							<td><input type="text" id="admin_warehouse_vehicleReceive_indate" name="indate" class="easyui-datebox" style="width: 300px" data-options="formatter:timeformatter,parser:timeparser" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>Free Date:</th>
							<td><input type="text" id="admin_warehouse_vehicleReceive_freedate" name="freedate" class="easyui-datebox" style="width: 300px" data-options="formatter:timeformatter,parser:timeparser,editable:false"/></td>
						</tr>
						<tr>
							<th>Key</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_keynum" name="keynum" style="width: 300px">
									<option value="0">0</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option>
									<option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Vehicle Type:</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_vehicletype" name="vehicletype" style="width: 300px">
									<option value="Vehicle">Vehicle</option>
									<option value="Motorcycle">Motorcycle</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Fuel Type:</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_fuelType" name="fuelType" style="width: 300px">
									<option value="Gasoline">Gasoline</option>
									<option value="Diesel">Diesel</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Fuel Vol:</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_fuel" name="fuel" style="width: 300px">
									<option value="0">0</option><option value="1/4">1/4</option><option value="1/3">1/3</option><option value="1/2">1/2</option><option value="3/4">3/4</option>
									<option value="1">1</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Sticker:</th>
							<td>
								<select id="admin_warehouse_vehicleReceive_sticker" name="sticker" style="width: 300px">
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
								<select id="admin_warehouse_vehicleReceive_floormat" name="floormat" style="width: 300px">
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
								<select id="admin_warehouse_vehicleReceive_dvdremote" name="dvdremote" style="width: 300px">
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
								<select id="admin_warehouse_vehicleReceive_heatset" name="heatset" style="width: 300px">
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
								<select id="admin_warehouse_vehicleReceive_usermanual" name="usermanual" style="width: 300px">
									<option value="NO">
										NO
									</option>
									<option value="YES">
										YES
									</option>
								</select>
							</td>
						</tr>
						</table>
						<table>
							<tr>
								<th>Title:</th>
								<td style="padding-left: 52px">
									<select id="admin_warehouse_vehicleReceive_titlestate" name="titlestate" style="width: 300px">
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
								<td><a onclick="admin_warehouse_vehicleReceive_addTr('admin_warehouse_vehicleReceive_titleTd');"><img id="admin_warehouse_vehicleReceive_titleTd1" src="images/down.png"/></a></td>
							</tr>
						</table>
						<table id="admin_warehouse_vehicleReceive_titleTd">
							<tr>
								<th style="font-weight: normal">Received Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_title1" name="title1" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">To Customs Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_title2" name="title2" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Customs Back Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_title3" name="title3" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Back To Customer Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_title4" name="title4" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
						</table>
						<table>
							<tr>
							<th>Bill:</th>
							<td style="padding-left: 60px">
								<select id="admin_warehouse_vehicleReceive_billstate" name="billstate" style="width: 300px">
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
							<td><a onclick="admin_warehouse_vehicleReceive_addTr('admin_warehouse_vehicleReceive_billTd');"><img id="admin_warehouse_vehicleReceive_billTd1" src="images/down.png"/></a></td>
						</tr>
						</table>
						<table id="admin_warehouse_vehicleReceive_billTd">
							<tr>
								<th style="font-weight: normal">Received Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_bill1" name="bill1" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">To Customs Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_bill2" name="bill2" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Customs Back Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_bill3" name="bill3" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Back To Customer Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_bill4" name="bill4" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
						</table>
						<table>
							<tr>
								<th>Proof:</th>
								<td style="padding-left: 45px">
									<select id="admin_warehouse_vehicleReceive_proofstate" name="proofstate" style="width: 300px">
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
								<td><a onclick="admin_warehouse_vehicleReceive_addTr('admin_warehouse_vehicleReceive_proofTd');"><img id="admin_warehouse_vehicleReceive_proofTd1" src="images/down.png"/></a></td>
							</tr>
						</table>
						<table id="admin_warehouse_vehicleReceive_proofTd">
							<tr>
								<th style="font-weight: normal">Received Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_proof1" name="proof1" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">To Customs Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_proof2" name="proof2" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<th style="font-weight: normal">Customs Back Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_proof3" name="proof3" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>	
								<th style="font-weight: normal">Back To Customer Date:</th>
								<td><input editable="false" type="text" id="admin_warehouse_vehicleReceive_proof4" name="proof4" class="easyui-datebox" style="width: 240px" data-options="formatter:timeformatter,parser:timeparser"/></td>
							</tr>
						</table>
						<table>
							<tr>
								<th>Remark:<a onclick="admin_warehouse_vehicleReceive_addTr('admin_warehouse_vehicleReceive_remarkTd');"><img id="admin_warehouse_vehicleReceive_remarkTd1" src="images/down.png"/></a></th>
								<td id="admin_warehouse_vehicleReceive_remarkTd"><textarea id="admin_warehouse_vehicleReceive_note" name="note" style="width: 300px;height: 180px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,300]'" ></textarea></td>
							</tr>
							<tr>
								<th>Memo:<a onclick="admin_warehouse_vehicleReceive_addTr('admin_warehouse_vehicleReceive_memoTd');"><img id="admin_warehouse_vehicleReceive_memoTd1" src="images/down.png"/></a></th>
								<td id="admin_warehouse_vehicleReceive_memoTd"><textarea id="admin_warehouse_vehicleReceive_memo" name="memo" style="width: 300px;height: 160px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,200]'"></textarea></td>
							</tr>
						</table>
				</form>
					<input style="margin-left: 90px" type="button" id="admin_warehouse_vehicleReceive_submit" value="confirm"/>
			</div>   				
		</div>
