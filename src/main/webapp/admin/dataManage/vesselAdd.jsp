<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<div id="admin_dateManage_Vessel_VesselAdd" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		<div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    <form id="admin_dateManage_Vessel_VesselAdd_from" method="post">
			    <table>
			    	<tr>
			    		<td><span style="color: red">*</span>Carrier :</td>
			    		<td><input id="admin_dateManage_Vessel_VesselAdd_from_carrier" class="easyui-combobox" name="carrierId" style="width:205px"
		    						data-options="required:true,validType:'length[0,200]',valueField:'id',textField:'fullname',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action'" /></td>
			    	</tr>
			    	<tr>
			    		<td><span style="color: red">*</span>Vessel  :</td>
			    		<td><input id="admin_dateManage_Vessel_VesselAdd_from_vessel" name="vessel" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'" style="width:200px;"/></td>
			    	</tr>
					<tr>
						<td></td>
						<td><a id="admin_dateManage_Vessel_VesselAdd_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin-top:10px">Save</a></td>
					</tr>    	
			    </table>
		    </form>
		</div>   
	</div>  
		
	<script type="text/javascript">
		
		$("#admin_dateManage_Vessel_VesselAdd_from").form({
			url:'vesselAction!addVessel.action',
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
			    $("#admin_dateManage_Vessel_datagrid").datagrid('reload');
			}
		});
		
		$('#admin_dateManage_Vessel_VesselAdd_from_save').bind('click',function(){
			$("#admin_dateManage_Vessel_VesselAdd_from").submit();
		});
	</script>
	