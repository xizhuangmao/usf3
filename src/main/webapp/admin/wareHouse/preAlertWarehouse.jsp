<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var admin_warehouse_preAlertWarehouse_preAlertReceive;
var admin_warehouse_preAlertWarehouse_datagrid_url;
$(function(){
	$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid({
		url:"warehouseAction!getPreAlertWhesdtl.action",
		border:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[10,20,50,100],
		idField:'id',
		sortName:'vin',
		sortOrder:'desc',
		checkOnSelect:false,
		selectOncheck:false,
		fit:true,
		columns:[[{
			field:'id',
			title:'Id',
			hidden:true
		},{
			field:'vin',
			title:'Vin',
			sortable:true,
		},{
			field:'whes',
			title:'Warehouse',
		},{
			field:'make',
			title:'Make',
		},{
			field:'model',
			title:'Model',
		},{
			field:'year',
			title:'Year',
		},{
			field:'color',
			title:'Color',
		},{
			field:'cod',
			title:'Cod',
		},{
			field:'users',
			title:'Customer',
		},{
			field:'note',
			title:'Remark',
		},{
			field:'str',
			title:'Operation',
			formatter : function(value, row, index){
            	var str = "";
            	str += "<a class=\"alert\" onclick=\"admin_warehouse_preAlertWarehouse_inwarehouse('"+row.id+"')\" href=\"#\">alert</a><br/><a class=\"deletePrealert\" onclick=\"admin_warehouse_preAlertWarehouse_deletevehicle('"+row.id+"','"+row.vin+"')\" href=\"#\">dele</a>";
            	return str;
			}
		}]],
					 	
		//刷新按钮
		toolbar : [ {
			text : 'refresh',
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid('reload');
			}
		} ],
						
		//入库、删除
		onLoadSuccess:function(data){ 
			$(".alert").linkbutton({ 
				text:'alert', 
				plain:true, 
				iconCls:'icon-edit',
			});
			$(".deletePrealert").linkbutton({ 
				text:'delete', 
				plain:true, 
				iconCls:'icon-remove',
			});
		},
		onClickCell: function (rowIndex, field, value) {
			IsCheckFlag = false;
		},
		onSelect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid("unselectRow", rowIndex);
			}
		},                    
		onUnselect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid("selectRow", rowIndex);
			}
		}
	});
	
	admin_warehouse_preAlertWarehouse_deletevehicle = function(id, vin){
		$.messager.confirm('confirm', 'delete the vehicle?', function(r){
		   	if (r){
				$.ajax({
					url : '${pageContext.request.contextPath}/warehouseAction!deletePreAlertVehicle.action',
					data : {"id" : id},
					dataType : 'text',
					type : 'post',
					success : function(data){
						var obj = $.parseJSON(data);
						if(obj.msg == 'success'){
							$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid('reload');
						}else{
							$.messager.alert('warning',obj.msg);
						}
					}
				});
			}
		});
	};
			
	admin_warehouse_preAlertWarehouse_inwarehouse = function(id){
		admin_warehouse_preAlertWarehouse_preAlertReceive =$('<div/>').dialog({
			width:600,
			heigth:800,
			title:"in warehouse",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/wareHouse/preAlertReceive.jsp?id="+id+"",
			onClose:function(){
				admin_warehouse_preAlertWarehouse_preAlertReceive.dialog('destroy');
			},
		});	
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = admin_warehouse_preAlertWarehouse_preAlertReceive.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				admin_warehouse_preAlertWarehouse_preAlertReceive.panel('resize',{
					left : 450,
					top  : 10,
				});
			}else{
				admin_warehouse_preAlertWarehouse_preAlertReceive.panel('resize',{
					left : 150,
					top  : 0,
				});
			}				
		}else{
			if(browserHeight>700){
				admin_warehouse_preAlertWarehouse_preAlertReceive.panel('resize',{
					left : 0,
					top  : 10,
				});
			}else{
				admin_warehouse_preAlertWarehouse_preAlertReceive.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
			
	$.ajax({
		url : "warehouseAction!findAllWhesMake.action",
		type : "post",
		dataType : "text",
		success : function(r){
			var obj = $.parseJSON(r);
			obj.whes.unshift({id:'ALL',fullname:'ALL'});
        	$("#admin_warehouse_preAlertWarehouse_whes").combobox({
				valueField: 'fullname',    
       			textField: 'fullname',
       			value: 'ALL',
        		data: obj.whes,
        	});
		}
	});
			
	$("#admin_warehouse_preAlertWarehouse_search").click(function(){
		$("#admin_warehouse_preAlertWarehouse_datagrid").datagrid('load', serializeObject($("#admin_warehouse_preAlertWarehouse_form")));
	});
});	
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height: 70px" border="false">
				<form id="admin_warehouse_preAlertWarehouse_form" name="form" class="datagrid-toolbar" method="post">
					<div style="width: 980px">
					<table id="admin_warehouse_preAlertWarehouse_table">
						<tr>
							<th>Warehouse :</th>		
							<td>
								<input id="admin_warehouse_preAlertWarehouse_whes" name="whes" class="easyui-combobox" style="width: 150px"/> 
							</td>
							<td>
								<a id="admin_warehouse_preAlertWarehouse_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>				
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="admin_warehouse_preAlertWarehouse_datagrid"></table>
			</div>    				
		</div>
