<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<div id="admin_dateManage_Vessel_VesselEdit" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		<div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    <form id="admin_dateManage_Vessel_VesselEdit_from" method="post">
			    <table>
			    	<tr>
			    		<td><input name="id" hidden="true"/></td>
			    	</tr>
			    	<tr>
			    		<td><span style="color: red">*</span>Carrier :</td>
			    		<td><input id="admin_dateManage_Vessel_VesselEdit_from_carrier" class="easyui-combobox" name="carrierId" style="width:205px;"
		    							data-options="valueField:'id', textField:'fullname',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action'" /></td>
			    	</tr>
			    	<tr>
			    		<td><span style="color: red">*</span>Vessel :</td>
			    		<td><input id="admin_dateManage_Vessel_VesselEdit_from_vessel" name="vessel" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'" style="width:200px;"/></td>
			    	</tr>
					<tr>
						<td></td>
						<td><a id="admin_dateManage_Vessel_VesselEdit_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
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
		        url: "vesselAction!findVesselById.action",
		        data: {"id":Vessel_datagrid_Edit_id},
		        success: function (obj) {
					$("#admin_dateManage_Vessel_VesselEdit_from").form('load', obj.obj);
		        }
		    });
		});
		
		$("#admin_dateManage_Vessel_VesselEdit_from").form({
			url:'vesselAction!editVessel.action',
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
		
		$('#admin_dateManage_Vessel_VesselEdit_from_save').bind('click',function(){
			$("#admin_dateManage_Vessel_VesselEdit_from").submit();
		});
	</script>
	