<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url : 'bookingManageAction!findEditBookingNum.action',
			type : 'post',
			data : {"id" : '<%= request.getParameter("id")%>'},
			dataType : 'text',
			success : function(data){
				var obj = $.parseJSON(data);
				$("#admin_dataManage_editBookingNum_vessel").combobox({
					url:'vesselAction!getVesselByCarrierId.action?carrierId=' + obj.obj.carrierId,    
    				valueField:'id',    
    				textField:'vessel'
				});
				$("#admin_dataManage_editBookingNum_voyage").combobox({
					url:'voyageAction!getVoyageByVesselId.action?vesselId=' + obj.obj.vesselId,    
    				valueField:'id',    
    				textField:'voyage'
				});
				$("#admin_dataManage_editBookingNum_form").form('load',obj.obj);
			}
		});
	});
	
	$("#admin_dataManage_editBookingNum_carrier").combobox({
		onSelect:function(carrier){
			$("#admin_dataManage_editBookingNum_vessel").combobox('clear');
			$("#admin_dataManage_editBookingNum_voyage").combobox('clear');
			$("#admin_dataManage_editBookingNum_vessel").combobox({
				url:'vesselAction!getVesselByCarrierId.action?carrierId=' + carrier.id,    
    			valueField:'id',    
    			textField:'vessel',
    			onSelect:function(vessel){
    				$("#admin_dataManage_editBookingNum_voyage").combobox({
						url:'voyageAction!getVoyageByVesselId.action?vesselId=' +vessel.id,    
	    				valueField:'id',    
	    				textField:'voyage'
					});
    			}
			});
		}
	});
	
	$("#admin_dataManage_editBookingNum_form").form({
		url:'bookingManageAction!updateBookingNum.action',
		success:function(data){
			var obj = $.parseJSON(data);
			$.messager.show({
				title:'Message',
				msg:obj.msg,
				timeout:5000,
				showType:'slide'
			});
			$("#admin_dataManage_bookingManage_datagrid").datagrid('reload');
		}
	});
	
	$("#admin_dataManage_editBookingNum_submit").click(function(){
		$("#admin_dataManage_editBookingNum_form").submit();
	});
</script>
		<div id="admin_dataManage_editBookingNum_div" class="easyui-layout" data-options="fit:true" style="width:450px;height:280px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
				<form id="admin_dataManage_editBookingNum_form" name="form" class="datagrid-toolbar" method="post">
					<table id="admin_dataManage_editBookingNum_table">
						<tr>
							<th></th>
							<td><input type="hidden" name="id" class="input-small" id="admin_dataManage_editBookingNum_id" style="width: 295px"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Booking No.:</th>
							<td><input name="booknum" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'" id="admin_dataManage_editBookingNum_booknum" style="width: 295px"/></td>
						</tr>
						<tr>
							<th>Company:</th>
							<td>
								<input id="admin_dataManage_editBookingNum_company" name="companyId" class="easyui-combobox" style="width: 300px" data-options="valueField:'id',editable:false,textField:'fullname',url:'companyAction!findCompany.action'"/>
							</td>
						</tr>
						<tr>
							<th>Carrier:</th>
							<td>
								<input id="admin_dataManage_editBookingNum_carrier" name="carrierId" style="width: 300px" data-options="valueField:'id',editable:false,textField:'fullname',url:'carrierAction!getCarrierName.action'"/>
							</td>						
						</tr>
						<tr>
							<th>Vessel:</th>
							<td>
								<input id="admin_dataManage_editBookingNum_vessel" name="vesselId" style="width: 300px" class="easyui-combobox" data-options="editable:false"/>
							</td>	
						</tr>
						<tr>
							<th>Voyage:</th>
							<td>
								<input id="admin_dataManage_editBookingNum_voyage" name="voyageId" style="width: 300px" class="easyui-combobox" data-options="editable:false"/>
							</td>
						</tr>
						<tr>
							<th>Pod:</th>
							<td>
								<input id="admin_dataManage_editBookingNum_pod" name="pod" style="width: 300px" class="easyui-combobox" data-options="valueField:'port',editable:false,textField:'port',url:'portAction!getPortName.action'"/>
							</td>
						</tr>
						<tr>
							<th>Active:</th>
							<td><input id="admin_dataManage_editBookingNum_active" name="active" style="width: 300px" class="easyui-combobox" data-options="editable:false,
									valueField: 'label',
									textField: 'value',
									data: [{
										label: '0',
										value: 'inactive'
									},{
										label: '1',
										value: 'active'
									}]"/></td>
						</tr>
					</table>
					<table>
						<tr>
							<th style="padding-left: 85px"><input id="admin_dataManage_editBookingNum_submit" type="button" value="submit"/></th>
						</tr>
					</table>
				</form>
			</div>   				
		</div>
