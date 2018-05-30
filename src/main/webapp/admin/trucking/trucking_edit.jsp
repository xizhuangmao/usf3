<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_Trucking_edit" class="easyui-layout" data-options="fit:true" style="width:300px;height:300px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_Trucking_edit_from" method="post">
			    	<table>
			    		<tr>
			    			<td> Booking No. :</td>
			    			<td><input id="admin_Trucking_edit_from_booknum"  name="booknum" readonly="readonly"/></td>
			    		</tr>
			    		<tr>
			    			<td> Container No. :</td>
			    			<td><input id="admin_Trucking_edit_from_connum" name="connum"/></td>
			    		</tr>
			    		<tr>
			    			<td> Seal No.  :</td>
			    			<td><input id="admin_Trucking_edit_from_sealnum" name="sealnum"/></td>
			    		</tr>
			    		<tr>
			    			<td>UOO :</td>
			    			<td><input id="admin_Trucking_edit_from_uoo" name="uoo"/></td>
			    		</tr>
			    		<tr>
			    			<td>Truck Co.  :</td>
			    			<td><input id="admin_Trucking_edit_from_truck" name="truck"/></td>
			    		</tr>
			    		<tr>
			    			<td>Trucking Date :</td>
			    			<td><input id="admin_Trucking_edit_from_truckdate" name="truckdate" class="easyui-datebox" data-options=" editable:false,"/></td>
			    		</tr>
			    		<tr>
			    			<td>Loading Date :</td>
			    			<td><input id="admin_Trucking_edit_from_loaddate" name="loaddate" class="easyui-datebox" data-options=" editable:false,"/></td>
			    		</tr>
			    		
						<tr>
							<td></td>
							<td><a id="admin_Trucking_edit_from_submit" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">submit</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		var truck_edit_returnBooknum;
		$(function(){
			$.ajax({
		        type: "post",
		        dataType: "json",
		        url: "booknumAction!findEditBooknum.action",
		        data: {"booknum":TruckEditBooknum},
		        success: function (data) {
		        	if(data.success){
		        		truck_edit_returnBooknum = data.obj;
		        		$("#admin_Trucking_edit_from").form('load',data.obj);
		        	}
		        }
		    });
		});
		//保存
		$('#admin_Trucking_edit_from_submit').bind('click',function(){
			var booknum = serializeObject($("#admin_Trucking_edit_from"));
			if(booknum.connum==""||booknum.connum==undefined){
				$.messager.alert('warning',"Container No. is empty",'info');
				return;
			}
			if(booknum.sealnum==""||booknum.sealnum==undefined){
				$.messager.alert('warning',"Seal No. is empty",'info');
				return;
			}
			if(booknum.uoo==""||booknum.uoo==undefined){
				$.messager.alert('warning',"UOO is empty",'info');
				return;
			}
			if(booknum.truck==""||booknum.truck==undefined){
				$.messager.alert('warning',"Truck Co. is empty",'info');
				return;
			}
			truck_edit_returnBooknum.connum = booknum.connum;
			truck_edit_returnBooknum.sealnum = booknum.sealnum;
			truck_edit_returnBooknum.uoo = booknum.uoo;
			truck_edit_returnBooknum.truck = booknum.truck;
			truck_edit_returnBooknum.truckdate = booknum.truckdate;
			truck_edit_returnBooknum.loaddate = booknum.loaddate;
			
			$.ajax({
		        type: "post",
		        dataType: "json",
		        url: "booknumAction!editTruckEditSubmit.action",
		        data: truck_edit_returnBooknum,
		        success: function (data) {
		        	if(data.success){
		        		$.messager.alert('warning',"save success",'info');
		        	}
		        }
		    });
			
		});
	</script>
	