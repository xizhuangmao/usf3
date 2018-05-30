<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		$(function(){
			if(vesselCarrier!=undefined && vesselCarrier!=""){
				$("#admin_ordersAdd_newOrdersAddVessel_from_carrierId").val(vesselCarrier.carrierId);
				$("#admin_ordersAdd_newOrdersAddVessel_from_carrier").val(vesselCarrier.fullname);
			}
		});
	</script>
		<div id="admin_ordersAdd_newOrdersAddVessel" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_ordersAdd_newOrdersAddVessel_from" method="post">
			    	<table>
			    		<tr>
			    			<td><input id="admin_ordersAdd_newOrdersAddVessel_from_carrierId" name="carrierId" type="hidden"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Carrier :</td>
			    			<td><input id="admin_ordersAdd_newOrdersAddVessel_from_carrier" name="fullname" style="width:180px;" readonly="readonly"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Vessel  :</td>
			    			<td><input id="admin_ordersAdd_newOrdersAddVessel_from_vessel" name="vessel" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'" style="width:180px;"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_ordersAdd_newOrdersAddVessel_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"  style="margin-top:10px">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
	<script type="text/javascript">

		$("#admin_ordersAdd_newOrdersAddVessel_from").form({    
			url:"vesselAction!addVessel.action", 
			success:function(data){    
			    var obj = $.parseJSON(data);
			    if(obj.success == true){
				    $.messager.show({
						title:'Message',
						msg:obj.msg,
						timeout:5000,
						showType:'slide'
					}); 
				}
			}    
		}); 

		$('#admin_ordersAdd_newOrdersAddVessel_from_save').bind('click',function(){
			$("#admin_ordersAdd_newOrdersAddVessel_from").submit(); 
		});
	</script>
	