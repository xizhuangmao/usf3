<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	$("#admin_customerOrder_placeOrder_datagrid").datagrid({
		url:"ordersAction!getOrdersDatagrid.action",
		border:false,
		fitColumns:true,
		pagination:true,
	    pageSize:10,
		pageList:[10,20,50,100],
		idField:'id',
		sortName:'ordersdate',
		sortOrder:'desc',
		checkOnSelect:false,
		selectOncheck:false,
		fit:true,
		columns:[[{
			field:'ordersId',
			title:'id',
			hidden:true,
		},{
			field:'ordersdate',
			title:'Order Date',
			sortable:true,
		},{
			field:'users',
			title:'Customer',
			width : 120,
			formatter: function (value) {
				if(value != undefined){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}
		},{
			field:'vin',
			title:'Vin',
		},{
			field:'booknum',
			title:'Booking No.',
		},{
			field:'carrier',
			title:'Carrier',
			width : 120,
			formatter: function (value) {
				if(value != undefined){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}
		},{
			field:'vessel',
			title:'Vessel',
			width : 120,
			formatter: function (value) {
				if(value != undefined){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}
		},{
			field:'voyage',
			title:'Voyage',
			width : 120,
			formatter: function (value) {
				if(value != undefined){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}
		},{
			field:'consize',
			title:'Container Size',
		},{
			field:'pol',
			title:'Pol',
			width : 120,
			formatter: function (value) {
				if(value != undefined){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}
		},{
			field:'pod',
			title:'Pod',
			width : 120,
			formatter: function (value) {
				if(value != undefined){
					return "<span title='" + value + "'>" + value + "</span>";
				}
			}
		},{
			field:'loaddate',
			title:'Loading Date',
		},{
			field:'note',
			title:'Remark',
			width:'200px',
		},{
			field:'str',
			title:'',
			width:'60px',
			formatter : function(value, row, index){
            	var str = "";
            	str += $.formatString('<img onclick="admin_customerOrder_placeOrder_editOrder(\'{0}\');" src="{1}" title="edit"/>', row.ordersId, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
            	str += '&nbsp;';		
            	str += $.formatString('<img onclick="admin_customerOrder_placeOrder_deleteOrder(\'{0}\');" src="{1}" title="resale"/>', row.ordersId, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');			
            	return str;
			}
		}]],
		toolbar : [ {
			text : 'refresh',
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_customerOrder_placeOrder_datagrid").datagrid('reload');
			}
		} ],
		onLoadSuccess:function(data){
			if(data.rows.length>0){		
				$("#admin_customerOrder_placeOrder_datagrid").datagrid("mergeCellsArray",{
					mergerows:data.rows,
					mergefields:['ordersId','ordersdate','pol','pod','note','str'],
					mergeorder:'ordersId'
				});
			}	
		},
		onClickCell: function (rowIndex, field, value) {
			IsCheckFlag = false;
		},
		onSelect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_customerOrder_placeOrder_datagrid").datagrid("unselectRow", rowIndex);
			}
		},                    
		onUnselect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_customerOrder_placeOrder_datagrid").datagrid("selectRow", rowIndex);
			}
		}
	});
			
	admin_customerOrder_placeOrder_deleteOrder = function(id){
		$.messager.confirm('confirm', 'delete the orders?', function(r){
		if (r){
			$.ajax({
				url:'ordersAction!deleteOrders.action',
				data:{"id" : id},
				type:'post',
				dataType:'text',
				success:function(data){
					$.messager.show({
						title:'Message',
						msg:'success',
						timeout:5000,
						showType:'slide'
					}),
					$("#admin_customerOrder_placeOrder_datagrid").datagrid('reload');
				}
			});
		}else{}
		});
	};
			
	admin_customerOrder_placeOrder_editOrder = function(id){
		var d =$('<div/>').dialog({
			width:950,
			heigth:860,
			title:"editOrder",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/customerOrder/editOrder.jsp?ordersId="+id+"",
			onClose:function(){
				d.dialog('destroy');
				$("#admin_customerOrder_placeOrder_datagrid").datagrid('reload');
			},
		});	
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = d.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				d.panel('resize',{
					left : 200,
					top  : 10,
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
					top  : 10,
				});
			}else{
				d.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
	
	$.ajax({
		url : "portAction!getPortDataGrid.action",
		type : "post",
		dataType : "text",
		success : function(r){
			var obj = $.parseJSON(r);
			obj.rows.unshift({id:'ALL',port:'ALL'});      //unshift添加初始值DEFAULT
			$("#admin_customerOrder_placeOrder_pol").combobox({
				valueField: 'port',    
	       		textField: 'port',
	       		value: 'ALL',
	        	data: obj.rows,
			});
			$("#admin_customerOrder_placeOrder_pod").combobox({
				valueField: 'port',    
	       		textField: 'port',
	       		value: 'ALL',
	        	data: obj.rows,
			});
		}
	});
	
	$("#admin_customerOrder_placeOrder_search").click(function(){
		$("#admin_customerOrder_placeOrder_datagrid").datagrid('load', serializeObject($("#admin_customerOrder_placeOrder_form")));
	});

});	
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height:120px;" border="false">
				<form id="admin_customerOrder_placeOrder_form" name="form" class="datagrid-toolbar" method="post">
				<div style="width: 850px">
					<table id="admin_customerOrder_placeOrder_table">
						<tr>
							<th>Order Date From:</th>
							<td><input editable="false" type="text" id="admin_customerOrder_placeOrder_orderDateFrom" name="orderDateFrom" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/>至<input editable="false" type="text" id="admin_customerOrder_placeOrder_orderDateTo" name="orderDateTo" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/></td>	
						</tr>
						<tr>
							<th>POD:</th>
							<td>
								<input id="admin_customerOrder_placeOrder_pod" name="pod" class="easyui-combobox" style="width: 150px"/>
							</td>
						</tr>
						<tr>
							<th>POL:</th>
							<td>
								<input id="admin_customerOrder_placeOrder_pol" name="pol" class="easyui-combobox" style="width: 150px"/>
							</td>
							<td>
								<a id="admin_customerOrder_placeOrder_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false" >
				 <table id="admin_customerOrder_placeOrder_datagrid"></table>
			</div>    				
		</div>
