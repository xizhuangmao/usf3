<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div id="admin_dateManage_Voyage_VoyageAdd" class="easyui-layout" data-options="fit:true" style="width:350px;height:300px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_Voyage_VoyageAdd_from" method="post">
			    	<table>
						<tr>
							<td><span style="color: red">*</span>Carrier:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_carrier" name="carrierId" style="width:205px;"
		    							data-options="required:true,valueField:'id',textField:'fullname',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action'" /></td>
						</tr>
						<tr><td><span style="color: red">*</span>Vessel:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_vessel" class="easyui-combobox" data-options="required:true" name="vesselId" style="width:205px;"></td>
						</tr>
						<tr><td>Terminal:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_terminal" name="terminal" style="width:200px;" class="easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
						</tr>
						<tr><td>Voyage Name:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_voyage" name="voyage" style="width:200px;" class="easyui-validatebox" data-options="validType:'length[0,100]'"/></td>
						</tr>
						<tr><td>Cutoff Date:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_cutoffdate" name="cutoffdate" class="easyui-datebox" style="width:205px;"
								data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr><td>ETD:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_etd" name="etd" class="easyui-datebox" style="width:205px;"
								data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr><td>ETA:</td>
							<td><input id="admin_dateManage_Voyage_VoyageAdd_from_eta" name="eta" class="easyui-datebox" style="width:205px;"
								data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_Voyage_VoyageAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"  style="margin-top:10px">Save</a></td>
						</tr>   		    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
	
	
	<script type="text/javascript">
	$('#admin_dateManage_Voyage_VoyageAdd_from_carrier').combobox({    
		onSelect:function(record){
			var url = '${pageContext.request.contextPath}/vesselAction!getVesselByCarrierId.action?carrierId='+record.id;    
	        $('#admin_dateManage_Voyage_VoyageAdd_from_vessel').combobox({
		        'url': url,
		        valueField: 'id',    
				textField: 'vessel',
	        }); 
		}	
	});  
	
	$("#admin_dateManage_Voyage_VoyageAdd_from").form({
		url:'voyageAction!addVoyage.action',
		success:function(data){
			var obj = $.parseJSON(data);
			if(obj.success){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
			}
			$("#admin_dateManage_Voyage_datagrid").datagrid('reload');
		}
	});
	
	$('#admin_dateManage_Voyage_VoyageAdd_from_save').bind('click',function(){
		$("#admin_dateManage_Voyage_VoyageAdd_from").submit();
	});

	</script>
	
