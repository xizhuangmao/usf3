<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_companyAdd" class="easyui-layout" data-options="fit:true" style="width:400px;height:400px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_companyAdd_from" method="post">
			    	<table>
			    		<tr>
			    			<td><span style="color: red">*</span>fullname :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_fullname" name="fullname" class="easyui-validatebox" style="width: 200px" data-options="required:true,validType:'length[0,200]'" /></td>
			    		</tr>
			    		<tr>
			    			<td>country :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_country" name="countryId" style="width: 205px" data-options="editable:false,validType:'length[0,100]',valueField:'id',textField:'country',url:'countryAction!getCountryName.action'"/></td>
			    		</tr>
			    		<tr>
			    			<td>state :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_state" name="stateId" class="easyui-combobox" style="width: 205px" data-options="editable:false,validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>city :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_city" name="cityId" class="easyui-combobox" style="width: 205px" data-options="editable:false,validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>address :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_address" name="address" class="easyui-validatebox" style="width: 200px" data-options="validType:'length[0,200]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>telephone :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_telephone" name="telephone" class="easyui-validatebox" style="width: 200px" data-options="validType:'length[0,50]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>email :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_email" name="email" class="easyui-validatebox" style="width: 200px" data-options="validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>contact :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_contact" name="contact" class="easyui-validatebox" style="width: 200px" data-options="validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>types :</td>
			    			<td><input id="admin_dateManage_companyAdd_from_types" name="types" class="easyui-combobox" style="width: 205px" data-options="editable:false,validType:'length[0,10]',valueField: 'label',textField: 'value',
											data: [{label: '1',value: 'whes'},{label: '2',value: 'nvocc'},{label: '3',value: 'truck'},{label: '4',value: 'carrier'}]"/></td>
			    		</tr>
			    		<tr>
			    			<td>note :</td>
			    			<td><textarea id="admin_dateManage_companyAdd_from_note" name="note" class="easyui-validatebox" style="width: 200px;height: 100px;resize:none;" data-options="validType:'length[0,300]'"></textarea></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_companyAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		$("#admin_dateManage_companyAdd_from_country").combobox({
			onSelect: function(record){
				$("#admin_dateManage_companyAdd_from_state").combobox('clear');
				$("#admin_dateManage_companyAdd_from_city").combobox('clear');
				var stateurl = 'stateAction!findStateByCountryId.action?countryId=' + record.id;
				$("#admin_dateManage_companyAdd_from_state").combobox({
					 url:stateurl,    
   					 valueField:'id',    
   					 textField:'state',
   					 onSelect: function(record){
						var cityurl = 'cityAction!findCityByStateId.action?stateId=' + record.id;    
						$("#admin_dateManage_companyAdd_from_city").combobox({
							url:cityurl,    
				   			valueField:'id',    
				   			textField:'city',
						});
					}					
				});
			}
		});
		
		$("#admin_dateManage_companyAdd_from").form({    
		    url:"companyAction!addCompany.action",    
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
		    }    
		});  
		
		$("#admin_dateManage_companyAdd_from_save").bind('click',function(){
			$("#admin_dateManage_companyAdd_from").submit();
		});
	</script>
	