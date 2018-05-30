<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- 增加用户之后的导向div -->
<table cellSpacing=5 cellPadding=5>
	<tr>
		<td>
			<a id="admin_usersOperChoose_addAnotherUser" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Add another user</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_grantWarehouse" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Grant warehouse</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_grantNvocc" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Grant NVOCC</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_grantCarrier" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Grant carrier</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_grantTruck" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Grant Truck</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_grantRole" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Grant role</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_editUser" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Re edit the user</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_usersOperChoose_close" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Close</a>
		</td>
	</tr>
</table>

<script type="text/javascript">

	
	$(function(){
	    $('#admin_usersOperChoose_addAnotherUser').bind('click', function(){
	    	admin_users_addFun();
	    	$('#admin_users_usersOpserChooserDiv').dialog('destroy');
	    });
	    $('#admin_usersOperChoose_grantWarehouse').bind('click', function(){
	    
	    	admin_users_whesFun();
	    	
	    });
	    $('#admin_usersOperChoose_grantNvocc').bind('click', function(){
	    
	    	admin_users_nvoccFun();
	    	
	    });
	    $('#admin_usersOperChoose_grantCarrier').bind('click', function(){
	 
	    	admin_users_carrierFun();
	    	
	    });
	    $('#admin_usersOperChoose_grantTruck').bind('click', function(){
	    
	    	admin_users_truckFun();
	    	
	    });
	    $('#admin_usersOperChoose_grantRole').bind('click', function(){
	    	
	    	admin_users_roleFun();
	    	
	    });
	    $('#admin_usersOperChoose_editUser').bind('click', function(){
	    	
	    	admin_users_editFun();
	    	
	    });
	    $('#admin_usersOperChoose_close').bind('click', function(){
	    	$('#admin_users_usersOpserChooserDiv').dialog('destroy');
	    });
	    
	});
	

</script>