<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_Make_MakeAdd" class="easyui-layout" data-options="fit:true" style="width:350px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_Make_MakeAdd_from" method="post">
			    	<table>
			    		<tr>
			    			<td><span style="color: red">*</span>Country :</td>
			    			<td><input id="admin_dateManage_Make_MakeAdd_from_country" name="countryId" class="easyui-combobox" style="width: 205px" data-options="required:true,valueField:'id',textField:'country',url:'countryAction!getCountryName.action'" /></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Make :</td>
			    			<td><input id="admin_dateManage_Make_MakeAdd_from_make" name="make" class="easyui-validatebox" style="width: 200px" data-options="required:true,validType:'length[0,100]'" /></td>
			    		</tr>
			    		<tr>
			    			<td>Shortname :</td>
			    			<td><input id="admin_dateManage_Make_MakeAdd_from_shortname" name="shortname" class="easyui-validatebox" style="width: 200px" data-options="validType:'length[0,50]'" /></td>
			    		</tr>
			    		<tr>
			    			<td>Nickname :</td>
			    			<td><input id="admin_dateManage_Make_MakeAdd_from_nickname" name="nickname" class="easyui-validatebox" style="width: 200px" data-options="validType:'length[0,50]'" /></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_Make_MakeAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
	<script type="text/javascript">
		$("#admin_dateManage_Make_MakeAdd_from").form({    
		    url:"makeAction!addMake.action",    
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
			    $("#admin_dateManage_makeManage_datagrid").datagrid('reload');
		    }    
		});  
		
		$("#admin_dateManage_Make_MakeAdd_from_save").bind('click',function(){
			$("#admin_dateManage_Make_MakeAdd_from").submit();
		});
	</script>		
		
		