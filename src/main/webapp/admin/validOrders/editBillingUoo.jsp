<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<div id="admin_validOrders_editBillingUoo_div" class="easyui-layout" data-options="fit:true" style="width:500px;height:300px;"> 
	<div data-options="region:'north',split:true" style="height:300px;background:#eee;border: 0px">
		<form method="post">
			<table>
				<tr>
					<th>Company:</th>
					<td><input id="admin_validOrders_editBillingUoo_from_company" name="company" style="width: 200px" /></td>
					<td><a id="admin_validOrders_editBillingUoo_form_submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">Submit</a></td>
				</tr>
			</table>
			<table id="admin_validOrders_editBillingUoo_from_datagrid" class="easyui-datagrid" style="500px;height:260px"
			        data-options="fitColumns:true,idField:'id',rownumbers:true,pagination:true,border : false,
			        	pageSize:5,pageList:[5,10,20,50],mode:'remote',delay:500">
			    <thead>
			        <tr>
			            <th data-options="field:'id',width:100,hidden:true">Id</th>
			            <th data-options="field:'name',width:100">Name</th>
			            <th data-options="field:'price',width:100">Price</th>
			            <th data-options="field:'note',width:100">Note</th>
			            <th data-options="field:'discount',width:100">Discount</th>
			            <th data-options="field:'pay',width:100">Pay</th>
			        </tr>
			    </thead>
			</table>
		</form>
	</div>
</div>

<script type="text/javascript">
	$(function(){
		$("#admin_validOrders_editBillingUoo_from_company").combobox({
			editable:false,
			valueField:'id',
			textField:'fullname',
			url:'companyAction!findCompany.action',
			onSelect:function(record){
				var url = "servicesAction!findServicesByCompany.action?companyId="+record.id+"&whesdtlId="+admin_validOrders_editServices_whesdtlId+"";
				var admin_validOrders_editBillingUoo_from_datagrid = $("#admin_validOrders_editBillingUoo_from_datagrid").datagrid({
					url:url,
					onLoadSuccess:function(data){
						$.ajax({
							url : '${pageContext.request.contextPath}/whesdtlServicesAction!findServicesByWhesdtlId.action',
							data : {"whesdtlId" : admin_validOrders_editServices_whesdtlId},
							type : 'post',
							dataType : 'text',
							success : function(data){
								var obj = $.parseJSON(data);
								for(var i=0;i<obj.length;i++){
									admin_validOrders_editBillingUoo_from_datagrid.datagrid('selectRecord', obj[i].services.id);   //根据id选择一行
								};
							}
						});
					},
					onClickRow:function(rowIndex, rowData){
						admin_validOrders_editServices_addServices(admin_validOrders_editServices_whesdtlId, rowData.id, rowData.discount, rowData.pay, rowData.price);
					}, 
				});
			}
		});
	});
	//添加车辆服务
	function admin_validOrders_editServices_addServices(whesdtlId, servicesId, discount, pay, price){
		$.ajax({
			url:'ordersAction!addVehicleServices.action',
			data:{"whesdtlId" : whesdtlId, "servicesId" : servicesId, "discount" : discount, "pay" : pay, "price" : price},
			type:'post',
		});
	}
	
	//刷新vin号对应的服务
	$("#admin_validOrders_editBillingUoo_form_submit").click(function(){
		<%= request.getParameter("datagrid")%>.datagrid('reload');
	});
	
</script>
	