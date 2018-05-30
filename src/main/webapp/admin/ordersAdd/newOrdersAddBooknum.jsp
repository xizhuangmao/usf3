<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<style>
			input{
				width:240px;
			}
		</style>
		<div id="admin_ordersAdd_newOrdersAddBooknum_div" class="easyui-layout" data-options="fit:true" style="width:400px;height:440px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;top:15px;background:#eee;">
		    	<form id="admin_ordersAdd_newOrdersAddBooknum_from" method="post">
			    	<table id="admin_ordersAdd_newOrdersAddBooknum_from_table">
						<tr> 
							<th><span style="color: red">*</span>Company: </th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_company" class="easyui-combobox" name="companyId" 
    							data-options="required:true,valueField:'id', editable:false,textField:'fullname',url:'${pageContext.request.contextPath}/companyAction!findCompany.action',
    											" /></td>
						</tr>  					
						<tr>
							<th><span style="color: red">*</span>Pod :</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_pod" class="easyui-combobox" name="pod" 
    							data-options="required:true,valueField:'port', editable:false,textField:'port',url:'${pageContext.request.contextPath}/portAction!getPortName.action',
    											" /></td>
						</tr> 
						<tr>
							<th>carrier:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_carrier" name="carrier"  /></td>
						</tr>   
						<tr>
							<th>vessel:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_vessel" name="vessel"/></td>
						</tr> 
						<tr>
							<th></th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_voyageId" name="voyageId" hidden="true"/></td>
						</tr> 
						<tr>
							<th>voyage:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_voyage" name="voyage"/></td>
						</tr> 
						<tr> 
							<th>Cutoff Date:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_cutoffdate" readonly="readonly" disabled="disabled" name="cutoffdate"/></td>
						</tr> 
						 <tr> 
							<th>ETD:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_etd" readonly="readonly" disabled="disabled"  name="etd"/></td>
						</tr> 
						<tr> 
							<th>ETA:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_eta" readonly="readonly" disabled="disabled"  name="eta"/></td>
						</tr> 
						<tr>
							<th>Terminal :</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_terminal" readonly="readonly" disabled="disabled"   name="terminal"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Booknum:</th>
							<td><input id="admin_ordersAdd_newOrdersAddBooknum_from_booknum" name="booknum" class="easyui-validatebox" data-options="required:true,validType:'length[0,50]'"/></td>
							<td><a id="admin_ordersAdd_newOrdersAddBooknum_from_addBooknum"  class="easyui-linkbutton" data-options="iconCls:'icon-add'"></a></td>
						</tr>
			    	</table>
			    	<table>
			    		<tr>
							<td></td>
							<td style="padding-left: 75px"><a id="admin_ordersAdd_newOrdersAddBooknum_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'" style="margin-top:10px">Save</a></td>
						</tr>
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
		
	<script type="text/javascript">
		var index = 1;
		if(newOrdersAddBooknumType == '1'){
			newOrdersAddBooknumType = 0;
			$(function(){
				$("#admin_ordersAdd_newOrdersAddBooknum_from_carrier").attr("readonly","true");
				$("#admin_ordersAdd_newOrdersAddBooknum_from_vessel").attr("readonly","true");
				$("#admin_ordersAdd_newOrdersAddBooknum_from_voyage").attr("readonly","true");
				$("#admin_ordersAdd_newOrdersAddBooknum_from").form('load',user_NewOrders_from_booknum);
				$.ajax({  
	        		type: "post",
	        		dataType: "json",
	        		url: "voyageAction!getVoyageById.action",
	        		data: {id: user_NewOrders_from_booknum.voyageId},
	        		success: function (result) {
	        			if(result.success){
	        				$("#admin_ordersAdd_newOrdersAddBooknum_from_cutoffdate").val(result.obj.cutoffdate);
	        				$("#admin_ordersAdd_newOrdersAddBooknum_from_etd").val(result.obj.etd);
	        				$("#admin_ordersAdd_newOrdersAddBooknum_from_eta").val(result.obj.eta);
	        				$("#admin_ordersAdd_newOrdersAddBooknum_from_terminal").val(result.obj.terminal);
	        			}
	        		}  
	        	});  
			});
		}else if(newOrdersAddBooknumType == '2'){
			newOrdersAddBooknumType = 0;
			$(function(){
				$("#admin_ordersAdd_newOrdersAddBooknum_from_vessel").combobox();
				$("#admin_ordersAdd_newOrdersAddBooknum_from_voyage").combobox();
				$("#admin_ordersAdd_newOrdersAddBooknum_from_carrier").combobox({
					url:'carrierAction!getCarrierName.action',
					valueField: 'fullname',    
       				textField: 'fullname',
       				editable:false,
       				onSelect:function(record){
       					$("#admin_ordersAdd_newOrdersAddBooknum_from_vessel").combobox('setValue', "");
       					$("#admin_ordersAdd_newOrdersAddBooknum_from_voyage").combobox('setValue', "");
       					var url = '${pageContext.request.contextPath}/vesselAction!getVesselByCarrierId.action?carrierId='+record.id;    
				        $("#admin_ordersAdd_newOrdersAddBooknum_from_vessel").combobox({
					        'url': url,
					        valueField: 'vessel',    
							textField: 'vessel',
							editable:false,
							onSelect:function(record){
								$("#admin_ordersAdd_newOrdersAddBooknum_from_voyage").combobox('setValue', "");
								var url = '${pageContext.request.contextPath}/voyageAction!getVoyageByVesselId.action?vesselId='+record.id;  
								 $("#admin_ordersAdd_newOrdersAddBooknum_from_voyage").combobox({
								 	'url': url,
								 	valueField: 'voyage',    
									textField: 'voyage',
									editable:false,
									onSelect:function(record){
										$("#admin_ordersAdd_newOrdersAddBooknum_from_voyageId").val(record.id);
							        	$("#admin_ordersAdd_newOrdersAddBooknum_from_cutoffdate").val(record.cutoffdate);
							        	$("#admin_ordersAdd_newOrdersAddBooknum_from_etd").val(record.etd);
							        	$("#admin_ordersAdd_newOrdersAddBooknum_from_eta").val(record.eta);
							        	$("#admin_ordersAdd_newOrdersAddBooknum_from_terminal").val(record.terminal);
									}
								 }); 
							}
				        }); 
				    }
				});
				
			});
		}
		
		$("#admin_ordersAdd_newOrdersAddBooknum_from_addBooknum").click(function(){
			index += 1;
			$("#admin_ordersAdd_newOrdersAddBooknum_from_table").append("<tr id=\"admin_ordersAdd_newOrdersAddBooknum_from_addBooknum"+index+"\"><th></th><td><input name=\"booknum\"/></td><td><img onclick=\"admin_ordersAdd_newOrdersAddBooknum_from_deleteBooknum("+index+")\" src=\"images/delete.png\"/></td></tr>");
		});
		
		admin_ordersAdd_newOrdersAddBooknum_from_deleteBooknum = function(index){
			$("#admin_ordersAdd_newOrdersAddBooknum_from_addBooknum"+index+"").remove();
		};
		
		$("#admin_ordersAdd_newOrdersAddBooknum_from_save").bind('click',function(){
			$("#admin_ordersAdd_newOrdersAddBooknum_from").submit();
		});
		
		$("#admin_ordersAdd_newOrdersAddBooknum_from").form({
			url:'booknumAction!addBooknum.action',
			success:function(data){
				var obj = $.parseJSON(data);
        		$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
			}
		});
		
	</script>
	