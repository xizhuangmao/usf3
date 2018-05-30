<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_Country_CountryEdit" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_Country_CountryEdit_from" method="post">
			    	<table>
			    		<tr>
			    			<td><input name="id" hidden="true"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Country :</td>
			    			<td><input id="admin_dateManage_Country_CountryEdit_from_country" name="country" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>CountryShort :</td>
			    			<td><input id="admin_dateManage_Country_CountryEdit_from_shortname" name="shortname" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>Nickname :</td>
			    			<td><input id="admin_dateManage_Country_CountryEdit_from_nickname" name="nickname" class="easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_Country_CountryEdit_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		$(function(){
			$.ajax({
		        type: "post",
		        dataType: "json",
		        url: "countryAction!findCountryById.action",
		        data: {"id" : Country_datagrid_Edit_id},
		        success: function (obj) {
		        	if(obj.success){
		        		$("#admin_dateManage_Country_CountryEdit_from").form('load', obj.obj);
		        	}
		        }
		    });
		});
		
		$("#admin_dateManage_Country_CountryEdit_from").form({
			url:'countryAction!editCountry.action',
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
		
		$('#admin_dateManage_Country_CountryEdit_from_save').bind('click',function(){
			$("#admin_dateManage_Country_CountryEdit_from").submit();
		});
		
	</script>
	