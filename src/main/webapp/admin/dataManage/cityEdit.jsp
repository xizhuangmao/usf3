<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_City_CityEdit" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_City_CityEdit_from" method="post">
			    	<table>
			    		<tr>
			    			<td><input name="id" hidden="true"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>State :</td>
			    			<td><input id="admin_dateManage_City_CityEdit_from_state" name="stateId" class="easyui-combobox" data-options="editable:false,required:true,validType:'length[0,100]',valueField:'id',textField:'state',url:'stateAction!getStateName.action'" /></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>City :</td>
			    			<td><input id="admin_dateManage_City_CityEdit_from_city" name="city" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'" /></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Shortname :</td>
			    			<td><input id="admin_dateManage_City_CityEdit_from_shortname" name="shortname" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'"/></td>
			    		</tr>
			    		<tr>
			    			<td>Nickname :</td>
			    			<td><input id="admin_dateManage_City_CityEdit_from_nickname" name="nickname" class="easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_City_CityEdit_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
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
		        url: "cityAction!findCityById.action",
		        data: {"id":city_datagrid_Edit_id},
		        success: function (obj) {
		        	if(obj.success){
		        		$("#admin_dateManage_City_CityEdit_from").form('load', obj.obj);
		        	}
		        }
		    });
		});
		
		$("#admin_dateManage_City_CityEdit_from").form({
			url:'cityAction!editCity.action',
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
				$.ajax({
					url:'cityAction!getCityName.action',
					type:'post',
					dataType:'text',
					success:function(data){
						var obj = $.parseJSON(data);
						obj.unshift({id:'ALL',city:'ALL'});
						$("#admin_dateManage_city_city").combobox({
							valueField: 'id',    
		       				textField: 'city',
		       				value: 'ALL',
		        			data: obj,
						});
					}
				});
				$("#admin_dateManage_city_datagrid").datagrid('reload');
			}
		});
		
		$('#admin_dateManage_City_CityEdit_save').bind('click',function(){
			$("#admin_dateManage_City_CityEdit_from").submit();
		});
		
	</script>
	