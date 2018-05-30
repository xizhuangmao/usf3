<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_Country_CountryAdd" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_Country_CountryAdd_from" method="post">
			    	<table>
			    		<tr>
			    			<td><span style="color: red">*</span>Country :</td>
			    			<td><input id="admin_dateManage_Country_CountryAdd_from_country" name="country" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Shortname :</td>
			    			<td><input id="admin_dateManage_Country_CountryAdd_from_shortname" name="shortname" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>Nickname :</td>
			    			<td><input id="admin_dateManage_Country_CountryAdd_from_nickname" name="nickname" class="easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_Country_CountryAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		$("#admin_dateManage_Country_CountryAdd_from").form({
			url:'countryAction!addCountry.action',
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
				$("#admin_dateManage_Country_datagrid").datagrid('reload');
			}
		});
		
		$('#admin_dateManage_Country_CountryAdd_from_save').bind('click',function(){
			$("#admin_dateManage_Country_CountryAdd_from").submit();
		});
	</script>
	