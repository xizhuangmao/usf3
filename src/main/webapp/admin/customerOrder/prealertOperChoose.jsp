<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- 收车之后的导向div -->
<table cellSpacing=5 cellPadding=5>
	<tr>
		<td>
			<a id="admin_customerOrder_cusVehicleReceive_addAnotherVehicle" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Add anothor prealert</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_customerOrder_cusVehicleReceive_backWarehouse" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search for vehicle</a>
		</td>
	</tr>
</table>

<script type="text/javascript">

	$(function(){
	    $('#admin_customerOrder_cusVehicleReceive_addAnotherVehicle').bind('click', function(){  
	    	$('#cusVehicleReceive_vehicleForm input').val("");
	    	$('#cusVehicleReceive_vehicleForm textarea').val("");
			$('#cusVehicleReceive_vehicleForm select').prop("selectedIndex", 0);   //重置所有select,选中第一项
	    	$('#admin_customerOrder_cusVehicleReceiveDiv').dialog('destroy');
	    });

	    $('#admin_customerOrder_cusVehicleReceive_backWarehouse').bind('click', function(){  
			var url = "${pageContext.request.contextPath}/admin/customerOrder/searchVehicle.jsp";
			addTab({title:"Search Vehicle",href:url,closable:true});
	    	$('#admin_customerOrder_cusVehicleReceiveDiv').dialog('destroy');
	    });
	});

</script>