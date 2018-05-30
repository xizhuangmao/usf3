<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$('#admin_services_addService_whes').combobox({
		valueField:'id',    
		textField:'fullname',
		url:'${pageContext.request.contextPath}/companyAction!findCompany.action',
		onLoadSuccess:function(data){
			$("#admin_services_addService_whes").combobox('select',data[0].id);
		},
		onSelect: function(param){
			$("#admin_services_addService_subserver").combogrid({
				url:"servicesAction!findServicesBywhesnvocc.action?companyId="+param.id+"",
				panelWidth : 500,
				panelHeight : 240,
				idField : 'id',
				textField : 'name',
				multiple : true,
				pagination : true,
				fitColumns : true,
				editable:false,
				rownumbers : true,
				mode : 'remote',
				delay : 500,
				sortName : 'name',
				sortOrder : 'asc',
				pageSize : 5,
				pageList : [ 5, 10 ],
				columns: [[{
					field:'id',
					title:'编号',
					hidden:true,
				},{
					field:'name',
					title:'name',
					width:100,
					sortable:true,
				}]],
			});
		}
	});
	
	$('#admin_services_addService_search').linkbutton({  
   		iconCls: 'icon-search'   
	});
	
	$('#admin_services_addService_inp').bind('input propertychange',function(){    
         var name = $("#admin_services_addService_inp").val();
         $("#admin_services_addService_subserver").combogrid('grid').datagrid('load', {"name" : name});
    });
    
    $("#admin_services_addService_form").form({
  		url:"servicesAction!addServices.action",
  		success : function(data){
    		var obj = $.parseJSON(data);
    		if(obj.msg == "success"){
    			$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				admin_servicesManage_addServices.dialog('destroy');
				$("<div id='admin_services_servicesChooseDiv'/>").dialog({
					title: 'What do you want to do next',
					width: 300,
					height: 200,
					href: "${pageContext.request.contextPath}/admin/services/servicesChoose.jsp",
					modal: true,
					onClose:function(){
						$(this).dialog('destroy');
					}
				});
				$("#admin_services_serviceManage_datagrid").datagrid('reload');
    		}else{
    			$.messager.alert('warning', obj.msg);
    		}  			
    	}
  	});
  	
    $("#admin_services_addService_submit").click(function(){
		$("#admin_services_addService_form").submit();
    });  
</script>
<div style="width: 360px;height: 460px">
	<form id="admin_services_addService_form" style="width: 600;padding-left: 10px;padding-top: 10px;line-height: 3em" method="post">
		<table>
			<tr>
				<th>company:</th>
				<td><input id="admin_services_addService_whes" name="companyId" style="width: 400px" data-options="editable:false,required:true" /></td>
			</tr>
			<tr>
				<th>service:</th>
				<td><textarea id="admin_services_addService_name" name="name" style="width: 395px;height: 55px;resize: none" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"></textarea></td>
			</tr>
			<tr>
				<th>contactServices:</th>
				<td>
					<div id="admin_services_addService_toolbar" class="datagrid-toolbar" data-options="region:'north',title:'search',split:true" style="height: 25px">
						name:<input id="admin_services_addService_inp" style="width: 160px"/>
					</div>
					<input id="admin_services_addService_subserver" class="easyui-combogrid" data-options="editable:false,toolbar : '#admin_services_addService_toolbar'" name="ids" style="width: 400px"/>
				</td>
			</tr>
			<tr>
				<th>price:</th>
				<td><input id="admin_services_addService_price" name="price" style="width: 395px" class="easyui-validatebox" data-options="validType:['moreThanZero[0]', 'length[0,20]']" /></td>
				<td><span style="color: #898b8d">$</span></td>
			</tr>
			<tr>
				<th>discount:</th>
				<td><input id="admin_services_addService_discount" name="discount"  style="width: 395px" class="easyui-validatebox" data-options="validType:['moreLength[0,100]', 'length[0,10]']" /></td>
				<td><span style="color: #898b8d">%</span></td>
			</tr>
			<tr>
				<th>note:</th>
				<td><textarea id="admin_services_addService_note" name="note" style="width: 395px;height: 100px;resize: none" class="easyui-validatebox" data-options="validType:'length[0,200]'"></textarea></td>
			</tr>
		</table>				
	</form>
			<input style="margin-left: 115px" id="admin_services_addService_submit" type="button" value="submit"/>
</div>
