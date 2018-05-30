<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url : 'warehouseAction!findVehicleInfoById.action',
			data : {"id" : '<%= request.getParameter("id")%>'},
			dataType : 'text',
			type : 'post',
			success : function(data){
				var obj = $.parseJSON(data);
				$("#admin_warehouse_vehicleResale_owners").html(obj.users);
				$("#admin_warehouse_vehicleResale_id").val(obj.id);
			}
		});

		$("#admin_warehouse_vehicleResale_form").form({    
		    url:'warehouseAction!resaleVehicle.action',    
		    success:function(data){    
		    	var obj = $.parseJSON(data);
				if(obj.success == true){
					$.messager.show({
						title:'Message',
						msg:obj.msg,
						timeout:5000,
						showType:'slide'
					});
					//刷新datagrid中的当前数据
					$("#admin_wareHouse_wareHouse_datagrid").datagrid('updateRow',{
						index: $("#admin_wareHouse_wareHouse_datagrid").datagrid('getRowIndex', obj.obj.id),
						row: {
							users: obj.obj.users,
						}
					});
				}else{
					$.messager.alert('warning', obj.msg);
				}
		    }    
		});    
		
		$("#admin_warehouse_vehicleResale_submit").click(function(){
			$("#admin_warehouse_vehicleResale_form").submit();
		});
	});
	
	$("#admin_warehouse_vehicleResale_users").combogrid({
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
            $('#admin_warehouse_vehicleResale_users').combogrid('setValue', rowData.fullname);
        }
	});

</script>
		<div id="admin_warehouse_vehicleResale_div" class="easyui-layout" data-options="fit:true" style="width:380px;height:205px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;">
				<form id="admin_warehouse_vehicleResale_form" name="form" class="datagrid-toolbar" method="post" style="width:360px;height:190px;">
					<table id="admin_warehouse_vehicleResale_table" style="width: 340px">
						<tr>
							<th></th>
							<td><input name="id" id="admin_warehouse_vehicleResale_id" type="hidden"/></td>
						</tr>
						<tr>
							<th style="padding-left: 17px">Owners:</th>
							<td style="width: 230px">
								<label id="admin_warehouse_vehicleResale_owners"></label>
							</td>						
						</tr>
						<tr>
							<th><span style="color: red">*</span>Resale To:</th>
							<td>
								<input id="admin_warehouse_vehicleResale_users" name="users" style="width: 230px"/>
							</td>						
						</tr>
						</table>
					<table>
						<tr>
							<th style="padding-left: 105px;padding-top: 10px"><input type="button" id="admin_warehouse_vehicleResale_submit" value="submit"/></th>
						</tr>
					</table>
				</form>
			</div>   				
		</div>
