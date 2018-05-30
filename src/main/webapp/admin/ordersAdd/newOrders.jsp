<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="user_NewOrders" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
    <div data-options="region:'north',split:true" style="height:40px;background:#eee;">
    	<form id="user_NewOrders_from" method="post">
			<table>
				<tr>
					<td>VIN# last 6 Digits:</td>
					<td><input id="user_NewOrders_from_vin" name="vin"/></td>
					<td>Customer :</td>
					<td>
						<input id="user_NewOrders_from_customer" class="easyui-combobox" name="customer" value="Please Select" style="width:300px;" 
	    							data-options="valueField:'id',
	    										  textField:'fullname',
	    										  url:'${pageContext.request.contextPath}/customerAction!getCustomerName.action',
	    										  " />
					</td>
					<td><a id="user_NewOrders_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
				</tr>
			</table>
		</form>
    </div>   
    
    <div data-options="region:'center',border:false">
    	<table id="user_NewOrders_datagrid" style="width:100%">
    	
    	</table>
    </div> 
    
    <div data-options="region:'south',split:true" style="height:50%;width:100%;">
    	<div class="easyui-layout" data-options="fit : true,">   
		    <div data-options="region:'center',border:false" style="padding:0px;background:#eee;">
		    	<form id="user_NewOrders_AddData_from" method="post">
			    	<table >
						<tr>
							<td colspan = "2"><span style="color: red">*</span>UOO:<input id="user_NewOrders_AddData_from_uoo" name="uoo" class="easyui-validatebox"  maxlength='50'/></td>
							<td> Loading Date  :<input id="user_NewOrders_AddData_from_loaddate" name="loaddate" class="easyui-datebox" 
													data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<td>Carrier:<input id="user_NewOrders_from_carrier" name="carrierId" value="Please Select" style="width:200px;"
    							data-options="valueField:'id',textField:'fullname', editable:false,url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action',
    											" /></td>
    						<td><a id="user_NewOrders_from_carrier_addCarrier" class="easyui-linkbutton" data-options="iconCls:'icon-add'"></a></td>
    						<td> Container No. :<input id="user_NewOrders_AddData_from_connum" name="connum" class="easyui-validatebox"  maxlength='50'/></td>
						</tr>
						<tr>
							<td>Vessel :<input id="user_NewOrders_from_Vessel" class="easyui-combobox"  name="vesselId" value="Please Select"
								data-options="editable:false,disabled:true"> </td>
							<td><a id="user_NewOrders_from_Vessel_addVessel"  class="easyui-linkbutton" style="display:none;"  data-options="iconCls:'icon-add'"></a></td>
							<td> Seal No.  :<input id="user_NewOrders_AddData_from_sealnum" name="sealnum" class="easyui-validatebox"  maxlength='50'/></td>
						</tr>
						<tr>
							<td >Voyage :<input id="user_NewOrders_from_Voyage" class="easyui-combobox"   name="voyageId" value="Please Select"
								data-options="editable:false,disabled:true"> </td>
							<td><a id="user_NewOrders_from_Voyage_addVoyage" class="easyui-linkbutton" style="display:none;"  data-options="iconCls:'icon-add'"></a></td>
							<td> Truck Co.  :<input id="user_NewOrders_AddData_from_truck" name="truckId" class="easyui-combobox"  maxlength='50'
								 data-options="valueField:'id',textField:'fullname', editable:false,url:'${pageContext.request.contextPath}/truckAction!getTruckCoName.action',
    											" /></td>
						</tr>
						<tr>
							<td><span style="color: red">*</span>Booking No. :<input id="user_NewOrders_from_booknum" class="easyui-combobox"  name="id" value="Please Select"
								data-options="editable:false,disabled:true"> </td>
							<td><a id="user_NewOrders_from_booknum_addBooknum" class="easyui-linkbutton" style="display:none;"  data-options="iconCls:'icon-add'"></a></td>
							<td> Trucking Date  :<input id="user_NewOrders_AddData_from_uoo" name="truckdate" class="easyui-datebox" 
													data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<td colspan = "2">Container Size:<select id="user_NewOrders_AddData_from_consize" class="easyui-combobox" data-options="editable:false" name="consize" style="width:80px;">   
												    <option>40ST</option>   
												    <option>40HQ</option>   
												    <option>45HQ</option>   
												    <option>20ST</option>   
												</select>  
							</td>
						</tr>
						<tr>
							<td> 
								<a id="user_NewOrders_from_Save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a>
								<a id="user_NewOrders_from_ToValidOrders"  class="easyui-linkbutton" data-options="iconCls:'icon-redo'">ToValidOrders</a>
							</td>
						</tr>
			    	</table>
		    	</form>
		    </div>   
		</div>  
    </div> 
</div> 

<script type="text/javascript">
			$('#user_NewOrders_from_carrier').combobox({    
				onSelect:function(record){
					$('#user_NewOrders_from_Vessel').combobox('setValue', "Please Select");
					$("#user_NewOrders_from_Vessel").combobox('loadData', {});
					$('#user_NewOrders_from_Voyage').combobox('setValue', "Please Select");
					$("#user_NewOrders_from_Voyage").combobox('loadData', {});
					$('#user_NewOrders_from_booknum').combobox('setValue', "Please Select");
					$("#user_NewOrders_from_booknum").combobox('loadData', {});
					if(record.id=="" ||record ==undefined){
						initSelect("carrier");
					}else{
						var url = '${pageContext.request.contextPath}/vesselAction!getVesselByCarrierId.action?carrierId='+record.id;    
				        $('#user_NewOrders_from_Vessel').combobox({
					        'url': url,
					        valueField: 'id',    
							textField: 'vessel',
							disabled:false,
				        }); 
				        $('#user_NewOrders_from_Vessel_addVessel').show(); 
					}
				}	
			}); 
			$('#user_NewOrders_from_Vessel').combobox({    
				onSelect:function(record){
					$('#user_NewOrders_from_Voyage').combobox('setValue', "Please Select");
					$("#user_NewOrders_from_Voyage").combobox('loadData', {});
					$('#user_NewOrders_from_booknum').combobox('setValue', "Please Select");
					$("#user_NewOrders_from_booknum").combobox('loadData', {});
					if(record.id=="" ||record ==undefined){
						initSelect("vessel");
					}else{
						var url = '${pageContext.request.contextPath}/voyageAction!getVoyageByVesselId.action?vesselId='+record.id;  
						 $('#user_NewOrders_from_Voyage').combobox({
					            'url': url,
					            valueField: 'id',    
								textField: 'voyage',
								disabled:false,
					            }); 
						  $('#user_NewOrders_from_Voyage_addVoyage').show(); 
					}
				}
			}); 
			$('#user_NewOrders_from_Voyage').combobox({
				onSelect:function(record){
					$('#user_NewOrders_from_booknum').combobox('setValue', "Please Select");
					$("#user_NewOrders_from_booknum").combobox('loadData', {});
					if(record.id=="" ||record ==undefined){
						initSelect("voyage");
					}else{
						var url = '${pageContext.request.contextPath}/booknumAction!getBooknumByVoyageId.action?voyageId='+record.id;  
						 $('#user_NewOrders_from_booknum').combobox({
					            'url': url,
					            valueField: 'id',    
								textField: 'booknum',	
								disabled:false,
					          });
						 $('#user_NewOrders_from_booknum_addBooknum').show();
					}
				}
			});
	
	var editingDiscount = undefined;
	var admin_ordersAdd_newOrders_datagridId;
	var admin_ordersAdd_newOrders_selectWhes = [];
	var IsCheckFlag = true;
	var NewOrdersDateGrid;
	$(function() {
		NewOrdersDateGrid=$("#user_NewOrders_datagrid").datagrid({
			url : '${pageContext.request.contextPath}/exportAction!NewOrders.action',
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
				width : '10%',
				sortable : true
			},{
				field : 'users',
				title : 'Customer',
				width : '12%',
			},{
				field : 'make',
				title : 'Make',
				width : '8%',
			},{
				field : 'model',
				title : 'Model',
				width : '8%',
			},{
				field : 'pol',
				title : 'Pol',
				width : '8%',
			},{
				field : 'pod',
				title : 'Pod',
				width : '8%',
			},{
				field : 'note',
				title : 'Remark',
				width : '10%',
			},{
				field : 'ordersdate',
				title : 'OrdersDate',
				width : '5%',
				sortable : true
			},{
				field:'services',
				title:'Services',
				width : '10%',
				formatter : function(value, row, index){
					str = "<label id=\"admin_ordersAdd_newOrders_label"+row.id+"\"></label>";
					return str;
				}
			},{
				field:'whesdtlServices',
				title:'ChangeServices',
				formatter : function(value, row, index){
					str = '';
					str += $.formatString('<img onclick="admin_ordersAdd_newOrders_editServices(\'{0}\');" src="{1}" title="修改"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
					return str;
				}
			}] ],
			onLoadSuccess: function(data){
				console.info(data);
				$('.datagrid-header-check').children().hide();
				for(var i=0;i<data.rows.length;i++){
					var admin_ordersAdd_newOrders_serviceId = "";
					if(data.rows[i].whesdtlServiceses.length > 0){
						for(var j=0;j<data.rows[i].whesdtlServiceses.length;j++){
							admin_ordersAdd_newOrders_serviceId += data.rows[i].whesdtlServiceses[j].services.name + ',';
						}
						admin_ordersAdd_newOrders_serviceId = admin_ordersAdd_newOrders_serviceId.substring(0, admin_ordersAdd_newOrders_serviceId.length-1);
						$("#admin_ordersAdd_newOrders_label"+data.rows[i].id+"").html(admin_ordersAdd_newOrders_serviceId);
					}
				}
			},
            onClickCell: function (rowIndex, field, value) {
				IsCheckFlag = false;
			},
			onSelect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					NewOrdersDateGrid.datagrid("unselectRow", rowIndex);
				}
			},                    
			onUnselect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					NewOrdersDateGrid.datagrid("selectRow", rowIndex);
				}
			},
			onCheck: function(rowIndex,rowData){
				admin_ordersAdd_newOrders_datagridId = rowData.id;
				admin_ordersAdd_newOrders_selectWhes.push(rowData.vin);
			},
			onUncheck: function (rowIndex, rowData) {
				admin_ordersAdd_newOrders_selectWhes.splice($.inArray(rowData.vin,admin_ordersAdd_newOrders_selectWhes),1);
			},
			});
		});
		
		//修改服务
		var admin_validOrders_editServices_whesdtlId;
		function admin_ordersAdd_newOrders_editServices(id){
			admin_validOrders_editServices_whesdtlId = id;
			var admin_ordersAdd_newOrders_editServices_div =$('<div/>').dialog({
				width:500,
				heigth:300,
				title:'ChangeService',
				modal:true,
				href:"${pageContext.request.contextPath}/admin/validOrders/editBillingUoo.jsp?datagrid=NewOrdersDateGrid",
				onClose:function(){
					admin_ordersAdd_newOrders_editServices_div.dialog('destroy');
				},
			});
			var browserHeight = $(window).height();  //游览器
			var browserwidth = $(window).width();
			var width = admin_ordersAdd_newOrders_editServices_div.panel('options').width;//获取容器的宽
			if(browserwidth>(width+200)){
				if(browserHeight>700){
					admin_ordersAdd_newOrders_editServices_div.panel('resize',{
						left : 500,
						top  : 250,
					});
				}else{
					admin_ordersAdd_newOrders_editServices_div.panel('resize',{
						left : 150,
						top  : 0,
					});
				}			
			}else{
				if(browserHeight>700){
					admin_ordersAdd_newOrders_editServices_div.panel('resize',{
						left : 0,
						top  : 10,
					});
				}else{
					admin_ordersAdd_newOrders_editServices_div.panel('resize',{
						left : 0,
						top  : 0,
					});
				}
			}		
		}
		
	//--------------------------保存按钮 保存对象-----------------------------------------	
		$('#user_NewOrders_from_Save').bind('click', function(){
			var rows=$("#user_NewOrders_datagrid").datagrid('getChecked');
			var booknum = serializeObject($("#user_NewOrders_AddData_from"));
			if(rows.length==0){
				$.messager.alert('warning',"Plese Select Orders",'info');
				return;
			}
			if($("#user_NewOrders_AddData_from_uoo").val() == ''){
				$.messager.show({
					title:'Message',
					msg:"Plese Select UOO",
					timeout:5000,
					showType:'slide'
				});
				return;
			}
			if(booknum.id=="Please Select" || booknum.id=="" || booknum.id==undefined){
				$.messager.show({
					title:'Message',
					msg:"Plese Select Booknum",
					timeout:5000,
					showType:'slide'
				});
				return;
			}else{
				booknum.booknum = $("#user_NewOrders_from_booknum").combo('getText');
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
			booknum.whesdtlVinJSONs = whesdtlVinJSONs;
			$.ajax({  
        		type: "post",
        		dataType: "json",
        		url: "booknumAction!UpdateWhesdtlBooknum.action",
        		data: booknum,
        		success: function (result) { 
        			if(result.success){
        				$.messager.show({
							title:'Message',
							msg:'Save Success',
							timeout:5000,
							showType:'slide'
						});
        				NewOrdersDateGrid.datagrid('reload');
        			}
        		}  
        	});
		});
		
	//-------------------------查询条件查找----------------------------------	
		$('#user_NewOrders_from_Search').bind('click', function(){
			var customer = $('#user_NewOrders_from_customer').combo('getText');
			if(customer=="Please Select" || customer==undefined){
				customer="";
			}
			var vin = $('#user_NewOrders_from_vin').val();
			NewOrdersDateGrid.datagrid('load',{
					vin: vin,
					users : customer
			});	

		});
		
		function NewOrders_OrdersAdd (){
		  	//返回选中多行  
        	var selRow = NewOrdersDateGrid.datagrid('getSelections');
			if(selRow.length>0){
				show_NewOrders_OrdersAdd();
			}else{
				$.messager.alert('warning',"Plese Select Orders",'info');
			}
		}
		
		
		$("#user_NewOrders_from_ToValidOrders").bind("click",function(){
			var url = "${pageContext.request.contextPath}/admin/validOrders/validOrders.jsp";
			addTab({title:"ValidOrders",href:url,closable:true});
		});
	//-------------------------	添加Carrier数据-----------------------------------
		$("#user_NewOrders_from_carrier_addCarrier").bind('click',function(event){
			var d =$('<div/>').dialog({
				width:400,
				heigth:600,
				title:"Carrier Add",
				modal:true,
				href:'${pageContext.request.contextPath}/admin/dataManage/companyAdd.jsp',
				onClose:function(){
					d.dialog('destroy');
					$('#user_NewOrders_from_carrier').combobox('setValue', "Please Select"); 
					$('#user_NewOrders_from_carrier').combobox('reload'); 
				},
			});
			var coord = mouseMove(event);
			d.panel('resize',{
				left : coord.xx,
				top  : coord.yy-400
			});
		});
	
		//-------------------------	添加Vessel数据-----------------------------------
		var vesselCarrier={};
		$("#user_NewOrders_from_Vessel_addVessel").bind('click',function(event){
			vesselCarrier.carrierId =$('#user_NewOrders_from_carrier').combobox('getValue');
			vesselCarrier.fullname =$('#user_NewOrders_from_carrier').combobox('getText');
			var d =$('<div/>').dialog({
				width:300,
				heigth:200,
				title:"Vessel Add",
				modal:true,
				href:'${pageContext.request.contextPath}/admin/ordersAdd/newOrdersAddVessel.jsp',
				onClose:function(){
					d.dialog('destroy');
					$('#user_NewOrders_from_Vessel').combobox('setValue', "Please Select"); 
					$('#user_NewOrders_from_Vessel').combobox('reload'); 
				},
			});
			var coord = mouseMove(event);
			d.panel('resize',{
				left : coord.xx,
				top  : coord.yy-200
			});
		});
		
		//-------------------------	添加Voyage数据-----------------------------------
		var voyageVesselCarrier={};
		$("#user_NewOrders_from_Voyage_addVoyage").bind('click',function(event){
			voyageVesselCarrier.carrierId =$('#user_NewOrders_from_carrier').combobox('getValue');
			voyageVesselCarrier.carrier =$('#user_NewOrders_from_carrier').combobox('getText');
			voyageVesselCarrier.vesselId =$('#user_NewOrders_from_Vessel').combobox('getValue');
			voyageVesselCarrier.vessel =$('#user_NewOrders_from_Vessel').combobox('getText');
			var d =$('<div/>').dialog({
				width:350,
				heigth:300,
				title:"Voyage Add",
				modal:true,
				href:'${pageContext.request.contextPath}/admin/ordersAdd/newOrdersAddVoyage.jsp',
				onClose:function(){
					d.dialog('destroy');
					$('#user_NewOrders_from_Voyage').combobox('setValue', "Please Select"); 
					$('#user_NewOrders_from_Voyage').combobox('reload'); 
				},
			});
			var coord = mouseMove(event);
			d.panel('resize',{
				left : coord.xx,
				top  : coord.yy-300
			});
		});
		
	//--------------------------添加booknum数据--------------------------------------
	var user_NewOrders_from_booknum;
	$("#user_NewOrders_from_booknum_addBooknum").bind('click',function(event){
		user_NewOrders_from_booknum = serializeObject($("#user_NewOrders_AddData_from"));
		user_NewOrders_from_booknum.voyage = $("#user_NewOrders_from_Voyage").combobox('getText');
		user_NewOrders_from_booknum.carrier = $("#user_NewOrders_from_carrier").combobox('getText');
		user_NewOrders_from_booknum.vessel = $("#user_NewOrders_from_Vessel").combobox('getText');
		newOrdersAddBooknumType = 1;
		var d =$('<div/>').dialog({
			width:440,
			heigth:440,
			title:"Add Booknum",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/ordersAdd/newOrdersAddBooknum.jsp',
			onClose:function(){
				d.dialog('destroy');
				var url = '${pageContext.request.contextPath}/booknumAction!getBooknumByVoyageId.action?voyageId='+user_NewOrders_from_booknum.voyageId;  
				 $('#user_NewOrders_from_booknum').combobox({
			            'url': url,
			            valueField: 'id',    
						textField: 'booknum',	
						disabled:false,
			       });
			},
		});
		var coord = mouseMove(event);
		var height = coord.yy;
		var weigth = coord.xx;
		d.panel('resize',{
			left : coord.xx,
			top  : height-500
		});
	});
	
	initSelect = function(type){
		if(type=="carrier"){
			$('#user_NewOrders_from_Vessel').combobox('setText','Please Select');
	    	$('#user_NewOrders_from_Vessel').combobox('disable');
			$('#user_NewOrders_from_Voyage').combobox('setText','Please Select');
	    	$('#user_NewOrders_from_Voyage').combobox('disable');
	    	$('#user_NewOrders_from_booknum').combobox('setText','Please Select');
	    	$('#user_NewOrders_from_booknum').combobox('disable');
	    	$('#user_NewOrders_from_Vessel_addVessel').hide();
	    	$('#user_NewOrders_from_Voyage_addVoyage').hide();
	    	$('#user_NewOrders_from_booknum_addBooknum').hide();
		}
		if(type=="vessel"){
			$('#user_NewOrders_from_Voyage').combobox('setText','Please Select');
	    	$('#user_NewOrders_from_Voyage').combobox('disable');
	    	$('#user_NewOrders_from_booknum').combobox('setText','Please Select');
	    	$('#user_NewOrders_from_booknum').combobox('disable');
	    	$('#user_NewOrders_from_Voyage_addVoyage').hide();
	    	$('#user_NewOrders_from_booknum_addBooknum').hide();
		}
		if(type=="voyage"){
			$('#user_NewOrders_from_booknum').combobox('setText','Please Select');
	    	$('#user_NewOrders_from_booknum').combobox('disable');
	    	$('#user_NewOrders_from_booknum_addBooknum').hide();
		}
	};

</script>