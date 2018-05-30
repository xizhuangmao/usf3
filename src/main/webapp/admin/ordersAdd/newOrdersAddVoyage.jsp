<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<script type="text/javascript">
			$(function(){
				if(voyageVesselCarrier!=undefined && voyageVesselCarrier !=""){
					$("#admin_ordersAdd_newOrdersAddVoyage_from").form('load',voyageVesselCarrier);
				}
			});
		</script>
		<div id="admin_ordersAdd_newOrdersAddVoyage" class="easyui-layout" data-options="fit:true" style="width:350px;height:300px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_ordersAdd_newOrdersAddVoyage_from" method="post">
			    	<table>
						<tr>
							<td><span style="color: red">*</span>Carrier:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_carrier" name="carrier" class="easyui-textbox" data-options="readonly :'true'" style="width:200px;"/></td>
						</tr>
						<tr>
							<td><input name="vesselId" hidden="true"></td>
						</tr>
						<tr><td><span style="color: red">*</span>Vessel:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_vessel"  name="vessel" class="easyui-textbox" data-options="readonly :'true'" style="width:200px;"></td>
						</tr>
						<tr><td>Terminal:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_terminal" name="terminal" style="width:160px;" class="easyui-validatebox" data-options="validType:'length[0,50]'"/></td>
						</tr>
						<tr><td>Voyage Name:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_voyage" name="voyage" style="width:160px;" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
						</tr>
						<tr><td>Cutoff Date:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_cutoffdate" name="cutoffdate" class="easyui-datebox" 
									data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr><td>ETD:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_etd" name="etd" class="easyui-datebox" 
									data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr><td>ETA:</td>
							<td><input id="admin_ordersAdd_newOrdersAddVoyage_from_eta" name="eta" class="easyui-datebox" 
									data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
						</tr>
						<tr>
							<td></td>
							<td><a id="admin_ordersAdd_newOrdersAddVoyage_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"  style="margin-top:10px">Save</a></td>
						</tr>   		    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
	
	
	<script type="text/javascript">
	//保存
	$('#admin_ordersAdd_newOrdersAddVoyage_from_save').bind('click',function(){
		$("#admin_ordersAdd_newOrdersAddVoyage_from").submit(); 
	});
	
	$("#admin_ordersAdd_newOrdersAddVoyage_from").form({    
		url:"voyageAction!addVoyage.action", 
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

	</script>
	
