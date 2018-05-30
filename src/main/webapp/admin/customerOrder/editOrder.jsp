<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_customerOrder_editOrder_div" class="easyui-layout" data-options="fit:true,border:false,collapsible:false" style="width:950px;height:860px;">  
	<div data-options="region:'north',split:true" style="height:40px;background:#eee;">
    	<form id="admin_customerOrder_editOrder_search_from" method="post">
		<table>
			<tr>
				<td>VIN# last 6 Digits:</td>
				<td><input id="admin_customerOrder_editOrder_search_from_vin" name="vin"/></td>
				<td><a id="admin_customerOrder_editOrder_search_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
				<td><a id="admin_customerOrder_editOrder_search_from_Clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Clean</a></td>
			</tr>
		</table>
	</form>
    </div>    
    <div data-options="region:'center',border:false"> 
    	<table id="admin_customerOrder_editOrder_datagrid"></table>
    </div> 
    
    <div data-options="region:'south',split:true" style="height:30%;width:950px;backgrounp:red;">
    	<div class="easyui-layout" data-options="fit : true,">   
		    <div data-options="region:'center',border:false" style="padding:0px;background:#eee;">
		    	<form id="admin_customerOrder_editOrder_from" method="post">
			    	<table >
						<tr>
							<input type="hidden" name="ordersId"/>
							<td> Billto:<input id="admin_customerOrder_editOrder_from_billto" name="billto"/></td>
							<td> Shipper:<input id="admin_customerOrder_editOrder_from_shipper" name="shipper" /></td>
							<td rowspan="5">Remark :<div title="文本" style="width:350px;height:120px">
											<textarea id="admin_customerOrder_editOrder_from_remark" class="dttextarea_text" style="width:350px;height:120px;resize:none;" name="note"></textarea>
									  </div>
							</td>
						</tr>
						<tr>
							<td><span style="color: red">*</span>Pol:<input id="admin_customerOrder_editOrder_from_pol" class="easyui-combobox" name="pol" style="width:200px;"
    							data-options="editable:false" /></td>
    						<td><span style="color: red">*</span>Pod :<input id="admin_customerOrder_editOrder_from_pod" class="easyui-combobox" name="pod" style="width:200px;"
    							data-options="editable:false"/></td>
    						
						</tr>
						<tr>
							<td> Consignee :<input id="admin_customerOrder_editOrder_from_consignee" name="consignee"/></td>
							<td> Notify Party :<input id="admin_customerOrder_editOrder_from_notify" name="notifyparty"/></td>
						</tr>
						<tr>
							<td> Term :<select id="admin_customerOrder_editOrder_from_term" name="priseterm" class="easyui-combobox" style="width:200px;" data-options="editable:false">
										<option value="">Please Select</option>
										<option value="L/C">L/C</option>
										<option value="T/T">T/T</option>
										<option value="OB/L">OB/L</option>
										<option value="TELEX RELEASE">TELEX RELEASE</option>
										</select></td>
								
						</tr>
						<tr>
							<td> <a id="admin_customerOrder_editOrder_from_Save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>
			    	</table>
		    	</form>
		    </div>   
		</div>  
    </div> 
</div> 

<script type="text/javascript">
	var admin_validOrders_editOrder_datagridId;
	var admin_validOrders_editOrder_selectWhes = [];
	var editOrdersDateGrid;
	var IsCheckFlag = true;
	$(function() {
		editOrdersDateGrid=$("#admin_customerOrder_editOrder_datagrid").datagrid({
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
			checkOnSelect:false,
			pagePosition: 'top',
			frozenColumns : [ [ {
				field : 'id',
				title : '编辑',
				width : '5%',
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'vin',
				title : 'Vin',
				width : '16%',
				sortable : true
			},{
				field : 'users',
				title : 'Customer',
				width : '12%',
			},{
				field : 'make',
				title : 'Make',
				width : '12%',
			},{
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
					return  "<div onClick=\"findEditOrderCompany('"+row.id+"', '"+row.vin+"')\"><input id=\"admin_customerOrders_editOrder_services"+row.vin+"\" data-options=\"panelWidth:500,panelHeight:240,multiple:true,fitColumns : true,hasDownArrow:false," +
								"rownumbers : true,pagination : true,pageSize : 5,pageList : [5,10,20,50],mode : 'remote',delay : 500,editable:false,idField:'id',textField:'name',columns: [[{" +
									"field:'id',title:'id',hidden: true,},{field:'name',title:'name',width:100,},{field:'price',title:'Price',width:100,},{field:'note',title:'Note',width:100,}]]\" style=\"border-radius:4.5px;border: 1px solid #95b8e7\"/></div>";
				}
			}] ],
			onLoadSuccess: function(data){
				$('.datagrid-header-check').children().hide();
				//加载下过订单的服务
				var pageSize = editOrdersDateGrid.datagrid('getPager').data("pagination").options.pageNumber;
				if(pageSize == 1 || pageSize == 0){
					$.ajax({
						url : '${pageContext.request.contextPath}/ordersAction!findEditOrderVehicles.action',
						data : {"ordersId" : "<%= request.getParameter("ordersId")%>"},
						type : 'post',
						dataType : 'text',
						success : function(data){
							var obj = $.parseJSON(data);
							for(var i=0;i<obj.rows.length;i++){
								$("#admin_customerOrder_editOrder_datagrid").datagrid('insertRow',{
									index: 0,
									row: {
										id : obj.rows[i].id,
										vin : obj.rows[i].vin,
										users : obj.rows[i].users,
										make: obj.rows[i].make,
										model : obj.rows[i].model ,
										year:  obj.rows[i].year,
										color:  obj.rows[i].color,
										usersId : obj.rows[i].usersId,
										whesdtlServices : "<div onClick=\"findEditOrderCompany('"+obj.rows[i].id+"', '"+obj.rows[i].vin+"')\"><input id=\"admin_customerOrders_editOrder_services"+obj.rows[i].vin+"\" data-options=\"panelWidth:500,panelHeight:240,multiple:true,fitColumns : true,hasDownArrow:false," +
															"rownumbers : true,pagination : true,pageSize : 5,pageList : [5,10,20,50],mode : 'remote',delay : 500,editable:false,idField:'id',textField:'name',columns: [[{" +
																"field:'id',title:'id',hidden: true,},{field:'name',title:'name',width:100,},{field:'price',title:'Price',width:100,},{field:'note',title:'Note',width:100,}]]\" style=\"border-radius:4.5px;border: 1px solid #95b8e7\"/></div>"
									}
								});
								$("#admin_customerOrders_editOrder_services"+obj.rows[i].vin+"").combogrid({
									onClickRow:function(rowIndex, rowData){
										admin_validOrders_editOrder_addServices(admin_validOrders_editOrder_datagridId, rowData.id, rowData.discount, rowData.pay, rowData.price);
									}
								});
								var admin_customerOrder_editOrder_serviceId = [];
								if(obj.rows[i].whesdtlServiceses.length > 0){
									for(var j=0;j<obj.rows[i].whesdtlServiceses.length;j++){
										admin_customerOrder_editOrder_serviceId.push(obj.rows[i].whesdtlServiceses[j].services);
									}
								}
								$("#admin_customerOrders_editOrder_services"+obj.rows[i].vin+"").combogrid("setValues", admin_customerOrder_editOrder_serviceId);
								editOrdersDateGrid.datagrid('checkRow', editOrdersDateGrid.datagrid('getRowIndex', obj.rows[i].id));
								$("#admin_customerOrder_editOrder_from").form('load', obj.rows[0]);
							};				
						}
					});
				}
				//加载上次选中的服务
				for(var i=0;i<data.rows.length;i++){
					$("#admin_customerOrders_editOrder_services"+data.rows[i].vin+"").combogrid({
						onClickRow:function(rowIndex, rowData){
							admin_validOrders_editOrder_addServices(admin_validOrders_editOrder_datagridId, rowData.id,  rowData.discount, rowData.pay, rowData.price);
						}
					});
					var admin_customerOrder_editOrder_serviceId = [];
					if(data.rows[i].whesdtlServiceses.length > 0){
						for(var j=0;j<data.rows[i].whesdtlServiceses.length;j++){
							if(data.rows[i].whesdtlServiceses[j].services.active != 0){
								admin_customerOrder_editOrder_serviceId.push(data.rows[i].whesdtlServiceses[j].services);
							}else{
								admin_validOrders_editOrder_addServices(data.rows[i].id, data.rows[i].whesdtlServiceses[j].services.id);
							}
						}
						$("#admin_customerOrders_editOrder_services"+data.rows[i].vin+"").combogrid("setValues", admin_customerOrder_editOrder_serviceId);
					}
				}
			},
					
            onClickCell: function (rowIndex, field, value) {
				IsCheckFlag = false;
			},
			onSelect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#admin_customerOrder_editOrder_datagrid").datagrid("unselectRow", rowIndex);
				}
			},                    
			onUnselect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#admin_customerOrder_editOrder_datagrid").datagrid("selectRow", rowIndex);
				}
			},
			onCheck: function(rowIndex,rowData){
				admin_validOrders_editOrder_datagridId = rowData.id;
				admin_validOrders_editOrder_selectWhes.push(rowData.vin);
			},
			onUncheck: function (rowIndex, rowData) {
				admin_validOrders_editOrder_selectWhes.splice($.inArray(rowData.vin,admin_validOrders_editOrder_selectWhes),1);
			},
		});
		
		//加载port
		$.ajax({
			url : "portAction!getPortDataGrid.action",
			type : "post",
			dataType : "text",
			success : function(r){
				var obj = $.parseJSON(r);
				$("#admin_customerOrder_editOrder_from_pol").combobox({
					valueField: 'port',    
		       		textField: 'port',
		        	data: obj.rows,
				});
				$("#admin_customerOrder_editOrder_from_pod").combobox({
					valueField: 'port',    
		       		textField: 'port',
		        	data: obj.rows,
				});
			}
		});
	});
	
		//添加车辆服务
		function admin_validOrders_editOrder_addServices(whesdtlId, servicesId, discount, pay, price){
			$.ajax({
				url:'ordersAction!addVehicleServices.action',
				data:{"whesdtlId" : whesdtlId, "servicesId" : servicesId, "discount" : discount, "pay" : pay, "price" : price},
				type:'post',
			});
		}
		
		function findEditOrderCompany(whesdtlId, vin){
			editOrdersDateGrid.datagrid('checkRow', editOrdersDateGrid.datagrid('getRowIndex', whesdtlId));
			var url = "servicesAction!findServicesByVin.action?vin="+vin+"&whesdtlId="+whesdtlId+"";
			$("#admin_customerOrders_editOrder_services"+vin+"").combogrid('grid').datagrid('reload', url);
		};	
		
	//--------------------------保存按钮 保存对象-----------------------------------------	
		$('#admin_customerOrder_editOrder_from_Save').bind('click', function(){
			var rows = editOrdersDateGrid.datagrid('getChecked');
			var orders = serializeObject($("#admin_customerOrder_editOrder_from"));
			if(rows.length==0){
				$.messager.alert('warning',"Plese Select Orders",'info');
				return;
			}
			if($("#admin_customerOrder_editOrder_from_pol").combo('getText') == "Please Select" || $("#admin_customerOrder_editOrder_from_pod").combo('getText') == "Please Select"){
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
			orders.pol = $("#admin_customerOrder_editOrder_from_pol").combo('getText');
			orders.pod = $("#admin_customerOrder_editOrder_from_pod").combo('getText');
			
			$.ajax({  
        		type: "post",
        		dataType: "text",
        		url: "ordersAction!updateOrders.action",
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
        				editOrdersDateGrid.datagrid('reload');
        			}
        		}  
        	});
		});
		
		$('#admin_customerOrder_editOrder_search_from_Search').bind('click', function(){
			var vin = $('#admin_customerOrder_editOrder_search_from_vin').val();
			editOrdersDateGrid.datagrid({
				queryParams: {
					vin: vin,
				}
			});	

		});
		$('#admin_customerOrder_editOrder_search_from_Clean').bind('click', function(){
			$('#admin_customerOrder_editOrder_search_from_vin').val("");
		});

</script>