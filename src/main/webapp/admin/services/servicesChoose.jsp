<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- 收车之后的导向div -->
<table cellSpacing=5 cellPadding=5>
	<tr>
		<td>
			<a id="admin_services_servicesChoose_addAnotherService" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Continue to add</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_services_servicesChoose_backServices" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Back ServicesManage</a>
		</td>
	</tr>
</table>

<script type="text/javascript">

	$(function(){
	    $('#admin_services_servicesChoose_addAnotherService').bind('click', function(){  
	    	admin_services_serviceManage_service_add();
	    	$('#admin_services_servicesChooseDiv').dialog('destroy');
	    });
	    	    
	    $('#admin_services_servicesChoose_backServices').bind('click', function(){
	    	$('#admin_services_servicesChooseDiv').dialog('destroy');
	    });
	});

</script>