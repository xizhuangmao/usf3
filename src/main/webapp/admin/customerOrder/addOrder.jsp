<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="user_addOrders" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
    <div data-options="region:'north',split:true" style="height:40px;background:#eee;">
    	<form id="user_addOrders_from" method="post">
		<table>
			<tr>
				<td>VIN# last 6 Digits:</td>
				<td><input id="user_addOrders_from_vin" name="vin"/></td>
				<td><a id="user_addOrders_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a></td>
				<td><a id="user_addOrders_from_Clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Clean</a></td>
			</tr>
		</table>
	</form>
    </div>   
    <div data-options="region:'center',border:false">
    	<table id="user_addOrders_datagrid"></table>
    </div> 
    
    <div data-options="region:'south',split:true" style="height:30%;width:100%;backgrounp:red;">
    	<div id="cc" class="easyui-layout" data-options="fit : true,">   
		    <div data-options="region:'center',border:false" style="padding:0px;background:#eee;">
		    	<form id="user_addOrders_AddData_from" method="post">
			    	<table>
						<tr>
							<td> Billto:<input id="user_addOrders_AddData_from_billto" name="billto"/></td>
							<td> Shipper:<input id="user_addOrders_AddData_from_shipper" name="shipper" /></td>
							<td rowspan="5">Remark :<div title="文本" style="width:350px;height:120px">
											<textarea id="user_addOrders_from_remark" style="width:350px;height:120px;resize:none;" name="note" class="easyui-validatebox" data-options="validType:'length[0,200]'" onkeyup="this.value = this.value.substring(0,200)" ></textarea>
									  </div>
							</td>
						</tr>
						<tr>
							<td><span style="color: red">*</span>Pol:<input id="user_addOrders_from_pol" class="easyui-combobox" name="pol" style="width:200px;" value="Please Select"
    							data-options="editable:false"/></td>
    						<td><span style="color: red">*</span>Pod :<input id="user_addOrders_from_pod" class="easyui-combobox" name="pod" style="width:200px;" value="Please Select"
    							data-options="editable:false"/></td>
						</tr>
						<tr>
							<td> Consignee :<input id="user_addOrders_AddData_from_consignee" name="consignee"/></td>
							<td> Notify Party :<input id="user_addOrders_AddData_from_notify" name="notifyparty"/></td>
						</tr>
						<tr>
							<td> Term :<select id="user_addOrders_AddData_from_term" name="priseterm" class="easyui-combobox" style="width:200px;" data-options="editable:false">
										<option value="">Please Select</option>
										<option value="L/C">L/C</option>
										<option value="T/T">T/T</option>
										<option value="OB/L">OB/L</option>
										<option value="TELEX RELEASE">TELEX RELEASE</option>
										</select></td>
						</tr>
						<tr>
							<td> <a id="user_addOrders_from_Save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>
			    	</table>
		    	</form>
		    </div>   
		</div>  
    </div> 
</div> 

<script type="text/javascript">
	var admin_customerOrder_addOrder_datagridId;
	var addOrdersDateGrid;
	var selectWhesVin;
	var admin_customerOrder_addOrder_selectWhes = [];
	var IsCheckFlag = true;
	$(function() {
		addOrdersDateGrid=$("#user_addOrders_datagrid").datagrid({
			url : '${pageContext.request.contextPath}/ordersAction!findNoOrdersDatagrid.action',
			border : false,
			pagePosition:'bottom',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 50, 100 ],
			fit : true,
			idField : 'id',
			fitColumns : true,
			sortName : 'indate',
			sortOrder : 'desc',
			pagePosition: 'top',
			checkOnSelect:false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编辑',
				width : '5%',
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'vin',
				title : 'Vin',
				width : '12%',
				sortable : true
			},{
				field : 'users',
				title : 'Customer',
				width : '12%',
			},{
				field : 'make',
				title : 'Make',
				width : '12%',
			}, {
				field : 'model',
				title : 'Model',
				width : '10%',
			},{
				field : 'year',
				title : 'Year',
				width : '10%',
			},{
				field : 'color',
				title : 'Color',
				width : '10%',
			},{
				field:'whesdtlServices',
				title:'SelectService',
				width:'160px',
				formatter: function(value,row,index){
					return	"<div onClick=\"findAddOrderCompany('"+row.id+"', '"+row.vin+"')\"><input id=\"admin_customerOrder_addOrder_services"+row.vin+"\" data-options=\"panelWidth:500,panelHeight:240,multiple:true,fitColumns : true,hasDownArrow:false," +
								"rownumbers : true,pagination : true,pageSize : 5,pageList : [5,10,20,50],mode : 'remote',delay : 500,editable:false,idField:'id',textField:'name',columns: [[{" +
									"field:'id',title:'id',hidden: true,},{field:'name',title:'name',width:100,},{field:'price',title:'Price',width:100,},{field:'note',title:'Note',width:100,},{field:'discount',title:'Discount',width:100,},{field:'pay',title:'Pay',width:100,}]]\" style=\"border-radius:4.5px;border: 1px solid #95b8e7\"/></div>";
				}
			}] ],
			onLoadSuccess: function(data){
				$('.datagrid-header-check').children().hide();
				for(var i=0;i<data.rows.length;i++){
					$("#admin_customerOrder_addOrder_services"+data.rows[i].vin+"").combogrid({
						onClickRow:function(rowIndex, rowData){
							admin_customerOrder_addOrder_addServices(admin_customerOrder_addOrder_datagridId, rowData.id, rowData.discount, rowData.pay, rowData.price);
						}
					});
					var admin_customerOrder_addOrder_serviceId = [];
					if(data.rows[i].whesdtlServiceses.length > 0){
						for(var j=0;j<data.rows[i].whesdtlServiceses.length;j++){
							if(data.rows[i].whesdtlServiceses[j].services.active != 0){
								admin_customerOrder_addOrder_serviceId.push(data.rows[i].whesdtlServiceses[j].services);
							}else{
								admin_customerOrder_addOrder_addServices(data.rows[i].id, data.rows[i].whesdtlServiceses[j].services.id);
							}
						}
						$("#admin_customerOrder_addOrder_services"+data.rows[i].vin+"").combogrid("setValues", admin_customerOrder_addOrder_serviceId);
					}
				}
			},
					
            onClickCell: function (rowIndex, field, value) {
				IsCheckFlag = false;
			},
			onSelect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#user_addOrders_datagrid").datagrid("unselectRow", rowIndex);
				}
			},                    
			onUnselect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#user_addOrders_datagrid").datagrid("selectRow", rowIndex);
				}
			},
			onCheck: function(rowIndex,rowData){
				admin_customerOrder_addOrder_datagridId = rowData.id;
				admin_customerOrder_addOrder_selectWhes.push(rowData.vin);
			},
			onUncheck: function (rowIndex, rowData) {
				admin_customerOrder_addOrder_selectWhes.splice($.inArray(rowData.vin,admin_customerOrder_addOrder_selectWhes),1);
			},
		});
		
		//加载port
		$.ajax({
			url : "portAction!getPortDataGrid.action",
			type : "post",
			dataType : "text",
			success : function(r){
				var obj = $.parseJSON(r);
				$("#user_addOrders_from_pol").combobox({
					valueField: 'port',    
		       		textField: 'port',
		        	data: obj.rows,
				});
				$("#user_addOrders_from_pod").combobox({
					valueField: 'port',    
		       		textField: 'port',
		        	data: obj.rows,
				});
			}
		});
	});

		//添加车辆服务
		function admin_customerOrder_addOrder_addServices(whesdtlId, servicesId, discount, pay, price){
			$.ajax({
				url:'ordersAction!addVehicleServices.action',
				data:{"whesdtlId" : whesdtlId, "servicesId" : servicesId, "discount" : discount, "pay" : pay, "price" : price},
				type:'post',
			});
		};
		
		function findAddOrderCompany(whesdtlId, vin){
			addOrdersDateGrid.datagrid('checkRow', addOrdersDateGrid.datagrid('getRowIndex', whesdtlId));
			var url = "servicesAction!findServicesByVin.action?vin="+vin+"";
			$("#admin_customerOrder_addOrder_services"+vin+"").combogrid('grid').datagrid('reload', url);
		};
		
	//--------------------------保存按钮 保存对象-----------------------------------------	
		$('#user_addOrders_from_Save').bind('click', function(){
			var rows = addOrdersDateGrid.datagrid('getChecked');
			var orders = serializeObject($("#user_addOrders_AddData_from"));
			if(rows.length==0){
				$.messager.alert('warning',"Plese Select Orders",'info');
				return;
			}
			if($("#user_addOrders_from_pol").combo('getText') == "Please Select" || $("#user_addOrders_from_pod").combo('getText') == "Please Select"){
				$.messager.show({
					title:'Message',
					msg:'Plese Select Pol and Pod',
					timeout:5000,
					showType:'slide'
				});
				return;
			}
			var whesdtlVinJSONs="(";
			for(i=0;i<rows.length;i++){
				if(whesdtlVinJSONs=="("){
					whesdtlVinJSONs = whesdtlVinJSONs + "'"+rows[i].vin+"'";
				}else{
					whesdtlVinJSONs = whesdtlVinJSONs +","+ "'"+rows[i].vin+"'";
				}
			}
			whesdtlVinJSONs = whesdtlVinJSONs+")";

			orders.whesdtlVinJSONs = whesdtlVinJSONs;
			orders.pol = $("#user_addOrders_from_pol").combo('getText');
			orders.pod = $("#user_addOrders_from_pod").combo('getText');
			$.ajax({  
        		type: "post",
        		dataType: "text",
        		url: "ordersAction!saveOrders.action",
        		data: orders,
        		success: function (result) {
        			var obj = $.parseJSON(result);
        			if(obj.msg == 'success'){
        				$.messager.show({
							title:'Message',
							msg:obj.msg,
							timeout:5000,
							showType:'slide'
						});
        				addOrdersDateGrid.datagrid('clearChecked');
        				addOrdersDateGrid.datagrid('clearSelections');
        				addOrdersDateGrid.datagrid('reload');
        				var index = rows.length;
        				for(var i = 0;i<index; i++){
        					var delectCount = rows.length-1;
        					$("#user_addOrders_datagrid_add").datagrid('deleteRow',delectCount);
        				}
        			}else{
        				$.messager.alert('warning', obj.msg);
        			}
        		}  
        	});
		});
		
	//-------------------------查询条件查找----------------------------------	
		$('#user_addOrders_from_Search').bind('click', function(){
			var vin = $('#user_addOrders_from_vin').val();
			addOrdersDateGrid.datagrid({
				queryParams: {
					vin: vin,
				}
			});	
		});
	//-------------------------清空条件----------------------------------			
		$('#user_addOrders_from_Clean').bind('click', function(){
			$('#user_addOrders_from_vin').val("");
			$('#user_addOrders_from_customer').combobox('setValue',"Please Select");
		});

</script>