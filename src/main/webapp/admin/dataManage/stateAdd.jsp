<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_State_StateAdd" class="easyui-layout" data-options="fit:true" style="width:340px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_State_StateAdd_from" method="post">
			    	<table>
			    		<tr>
			    			<td><span style="color: red">*</span>Country :</td>
			    			<td><input id="admin_dateManage_State_StateAdd_from_country" class="easyui-combobox" name="countryId" style="width:205px;"
    							data-options="required:true,valueField:'id', editable:false,textField:'country',url:'${pageContext.request.contextPath}/countryAction!getCountryName.action',
    											" /></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>State :</td>
			    			<td><input id="admin_dateManage_State_StateAdd_from_state" name="state" style="width:200px;" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Shortname :</td>
			    			<td><input id="admin_dateManage_State_StateAdd_from_shortname" name="shortname" style="width:200px;" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>Nickname :</td>
			    			<td><input id="admin_dateManage_State_StateAdd_from_nickname" name="nickname" class="easyui-validatebox" style="width:200px;"  data-options="validType:'length[0,50]'"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_State_StateAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		$("#admin_dateManage_State_StateAdd_from").form({
			url:'stateAction!addState.action',
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
				$("#admin_dateManage_state_datagrid").datagrid('reload');
			}
		});
		
		$('#admin_dateManage_State_StateAdd_from_save').bind('click',function(){
			$("#admin_dateManage_State_StateAdd_from").submit();
		});
	</script>
	