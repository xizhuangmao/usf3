<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		//加载whes, make
	    $.ajax({
			url : "warehouseAction!findPrealertAllWhesMake.action",
			type : "post",
			dataType : "text",
			success : function(r){
				var obj = $.parseJSON(r);
				obj.make.unshift({id:'DEFAULT',make:'DEFAULT'});      //unshift添加初始值DEFAULT
				$("#admin_customerOrder_prealert_make").combobox({
					valueField: 'id',    
       				textField: 'make',
       				value: 'DEFAULT',
        			data: obj.make,
        			onSelect:function(record){
        				var url = 'modelAction!findModelByMakeId.action?makeId=' + record.id; 
        				$.ajax({      								//根据选中的makeId获取对应的model
        					url : url,
							type : "post",
							dataType : "text",
							success : function(r){
								var obj = $.parseJSON(r);
								obj.unshift({id:'DEFAULT',model:'DEFAULT'});
								$("#admin_customerOrder_prealert_model").combobox({
									valueField: 'model',    
       								textField: 'model',
        							data: obj,
        						});
							}
        				});   
        			}  
				});
				$("#admin_customerOrder_prealert_model").combobox({
       				value: 'DEFAULT',
        		});
				$("#admin_customerOrder_prealert_whes").combobox({
					valueField: 'id',    
       				textField: 'fullname',
        			data: obj.whes,
        			onLoadSuccess:function(data){
        				$("#admin_customerOrder_prealert_whes").combobox('select',data[0].id);
        			}
				});
			}
		});
	});

	$("#admin_customerOrder_prealert_form").form({
  		url:"warehouseAction!addPrealert.action",
  		success:function(data){
  			var obj = $.parseJSON(data);
			if(obj.msg == 'success'){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				$("<div id='admin_customerOrder_cusVehicleReceiveDiv'/>").dialog({
					title: 'What do you want to do next',
					width: 300,
					height: 200,
					href: "${pageContext.request.contextPath}/admin/customerOrder/prealertOperChoose.jsp",
					modal: true,
					onClose:function(){
						$(this).dialog('destroy');
					}
				});
			}else{
				$.messager.alert('warning', obj.msg);
			}
  		}
  	});
	
	$("#admin_customerOrder_prealert_submit").click(function(){
		var vin = $("#admin_customerOrder_prealert_vin").val();
		var rex = "^[A-Za-z0-9]+$";
		var regExp = new RegExp(rex);
		if(vin == "" || !regExp.test(vin)){
			$.messager.alert('warning','vin must consist of Numbers and letters');
		}else{
			$('#admin_customerOrder_prealert_form').submit();
		}
	});
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'Vehicle',split:true" style="height:100%" border="false">
				<form id="admin_customerOrder_prealert_form" name="vehicleForm" class="datagrid-toolbar" method="post">
					<table id="admin_customerOrder_prealert_table">
						<tr>
							<th><span style="color: red">*</span>Warehouse:</th>
							<td>
								<select id="admin_customerOrder_prealert_whes" class="easyui-combobox" name="whesId" style="width: 300px" data-options="editable:false"></select>
							</td>						
						</tr>
						<tr>
							<th><span style="color: red">*</span>Vin #:</th>
							<td><input name="vin" id="admin_customerOrder_prealert_vin" style="width: 295px" class="easyui-validatebox" data-options="required:true,validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Make:</th>
							<td>
								<select id="admin_customerOrder_prealert_make" class="easyui-combobox" name="makeId" style="width: 300px" data-options="editable:false">
								</select>
							</td>
						</tr>
						<tr>
							<th>Model:</th>
							<td>
								<select id="admin_customerOrder_prealert_model" class="easyui-combobox" name="model" style="width: 300px" data-options="editable:false">
								</select>
							</td>
						</tr>
						<tr>
							<th>Year:</th>
							<td><input name="year" id="admin_customerOrder_prealert_year" style="width: 295px" placeholder="Enter Year" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Color:</th>
							<td><input name="color" id="admin_customerOrder_prealert_color" style="width: 295px" placeholder="Enter color" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>COD:</th>
							<td><input name="cod" id="admin_customerOrder_prealert_cod" style="width: 295px" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>Remark:</th>
							<td><textarea id="admin_customerOrder_prealert_note" name="note" style="width: 295px;height: 160px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,200]'" ></textarea></td>
						</tr>
					</table>
				</form>
					<input style="margin-left: 90px" type="button" id="admin_customerOrder_prealert_submit" value="confirm"/>
			</div>   				
		</div>
