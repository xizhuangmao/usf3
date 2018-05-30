<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_Port_PortAdd" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_Port_PortAdd_from" method="post">
			    	<table>
			    		<tr>
			    			<td><span style="color: red">*</span>Country :</td>
			    			<td><input id="admin_dateManage_Port_PortAdd_from_country" name="country"  style="width:205px;"
    							data-options="required:true,valueField:'country',textField:'country',url:'${pageContext.request.contextPath}/countryAction!getCountryName.action',
    											" /></td>
			    		</tr>
			    		<tr>
			    			<td>State :</td>
			    			<td><input id="admin_dateManage_Port_PortAdd_from_state" name="state"  style="width:205px;" class="easyui-combobox" /></td>
			    		</tr>
			    		<tr>
			    			<td>City :</td>
			    			<td><input id="admin_dateManage_Port_PortAdd_from_city" name="city"  style="width:205px;" class="easyui-combobox" /></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Port :</td>
			    			<td><input id="admin_dateManage_Port_PortAdd_from_port" name="port" class="easyui-combobox"  style="width:205px;" data-options="required:true,valueField:'port',textField:'port',url:'${pageContext.request.contextPath}/portAction!getPortName.action'"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_Port_PortAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
		
		<script type="text/javascript">
		$('#admin_dateManage_Port_PortAdd_from_country').combobox({    
			onSelect:function(record){
				$('#admin_dateManage_Port_PortAdd_from_state').combobox('clear');
				$('#admin_dateManage_Port_PortAdd_from_city').combobox('clear');
				var url = '${pageContext.request.contextPath}/stateAction!findStateByCountryId.action?countryId='+record.id;    
		        $('#admin_dateManage_Port_PortAdd_from_state').combobox({
			        'url': url,
			        valueField: 'state',    
					textField: 'state',	
		        }); 
			}	
		}); 
		$('#admin_dateManage_Port_PortAdd_from_state').combobox({    
			onSelect:function(record){
				var url = '${pageContext.request.contextPath}/cityAction!findCityByStateId.action?stateId='+record.id;  
				 $('#admin_dateManage_Port_PortAdd_from_city').combobox({
			     	'url': url,
			     	valueField: 'city',    
					textField: 'city',	
			     }); 
			}
		}); 
	
		$("#admin_dateManage_Port_PortAdd_from").form({
			url:'portAction!addPort.action',
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
				$("#admin_dateManage_PortManage_datagrid").datagrid('reload');
				$("#admin_dateManage_PortManage_port").combobox('reload', "portAction!getPortName.action");
			}
		});
		
		$('#admin_dateManage_Port_PortAdd_from_save').bind('click',function(){
			$("#admin_dateManage_Port_PortAdd_from").submit();
		});
	</script>
	