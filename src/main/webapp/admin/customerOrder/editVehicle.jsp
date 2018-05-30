<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
	   //加载whes, make并添加初始值为DEFAULT
	   $.ajax({
			url : "warehouseAction!findPrealertAllWhesMake.action",
			type : "post",
			dataType : "text",
			success : function(r){
				var obj = $.parseJSON(r);
				obj.make.unshift({id:'DEFAULT',make:'DEFAULT'});      //unshift添加初始值DEFAULT
				$("#admin_customerOrder_editVehicle_make").combobox({
					valueField: 'id',    
       				textField: 'make',
       				value: 'DEFAULT',
        			data: obj.make,
				});
				$("#admin_customerOrder_editVehicle_model").combobox({
       				value: 'DEFAULT',
        		});
				$("#admin_customerOrder_editVehicle_whes").combobox({
					valueField: 'id',    
       				textField: 'fullname',
        			data: obj.whes,
				});
				//加载vin对应的车辆信息
			   	$.ajax({
			   		url : 'warehouseAction!findVehicleInfoById.action',
			   		type : 'post',
			   		dataType : 'text',
			   		data : {"id" : "<%= request.getParameter("id")%>"},
			   		success : function(data){
			   			var obj = $.parseJSON(data);
			   			var firstModel = obj.model;
			   			$("#admin_customerOrder_editVehicle_form").form('load',obj);
			   			var url = 'modelAction!findModelByMakeId.action?makeId=' + obj.makeId; 
        				$.ajax({      								//根据选中的makeId获取对应的model
        					url : url,
							type : "post",
							dataType : "text",
							success : function(r){
								var obj = $.parseJSON(r);
								obj.unshift({id:'DEFAULT',model:'DEFAULT'});
								$("#admin_customerOrder_editVehicle_model").combobox({
									valueField: 'model',    
       								textField: 'model',
        							data: obj,
        						});
        						if(firstModel != null && firstModel != ''){
        							$("#admin_customerOrder_editVehicle_model").combobox('setValue', firstModel);
        						}
							}
        				});
			   		}
			   });
			}
		});
		
		//加载make对应的model
		$("#admin_customerOrder_editVehicle_make").combobox({
			onSelect:function(record){
        		var url = 'modelAction!findModelByMakeId.action?makeId=' + record.id; 
        		$.ajax({      								//根据选中的makeId获取对应的model
        			url : url,
					type : "post",
					dataType : "text",
					success : function(r){
						var obj = $.parseJSON(r);
						obj.unshift({id:'DEFAULT',model:'DEFAULT'});
						$("#admin_customerOrder_editVehicle_model").combobox({
							valueField: 'model',    
       						textField: 'model',
        					data: obj,
        				});
					}
        		});   
        	}  
		});
	});

	$("#admin_customerOrder_editVehicle_form").form({
  		url:"warehouseAction!updatePreAlertInfo.action",
  		success:function(data){
  			var obj = $.parseJSON(data);
			if(obj.msg == 'success'){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				$("#admin_customerOrder_searchForVehicle_datagrid").datagrid('reload');
			}else{
				$.messager.alert('warning',obj.msg);
			}
  		}
  	});
		
	$("#admin_customerOrder_editVehicle_submit").click(function(){
		var vin = $("#admin_customerOrder_editVehicle_vin").val();
		var rex = "^[A-Za-z0-9]+$";
		var regExp = new RegExp(rex);
		if(vin == "" || !regExp.test(vin)){
			$.messager.alert('warning','vin must consist of Numbers and letters');
		}else{
			$('#admin_customerOrder_editVehicle_form').submit();
		}
	});

</script>
		<div id="admin_customerOrder_editVehicle_div" class="easyui-layout" data-options="fit:true" style="width:600px;height:450px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
				<form id="admin_customerOrder_editVehicle_form" name="form" class="datagrid-toolbar" method="post">
					<table id="admin_customerOrder_editVehicle_table">
						<tr>
							<th></th>
							<td><input name="id" id="admin_customerOrder_editVehicle_id" type="hidden"/></td>
						</tr>
						<tr>
							<th><span style="color: red">*</span>Warehouse:</th>
							<td>
								<select id="admin_customerOrder_editVehicle_whes" name="whesId" class="easyui-combobox" data-options="required:true,editable:false" style="width: 300px" ></select>
							</td>	
						</tr>
						<tr>
							<th><span style="color: red">*</span>Vin #:</th>
							<td><input name="vin" id="admin_customerOrder_editVehicle_vin" style="width: 295px" class="easyui-validatebox" data-options="required:true,validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Make:</th>
							<td>
								<select id="admin_customerOrder_editVehicle_make" name="makeId" class="easyui-combobox" style="width: 300px" data-options="editable:false"></select>
							</td>
						</tr>
						<tr>
							<th>Model:</th>
							<td>
								<select id="admin_customerOrder_editVehicle_model" name="model" class="easyui-combobox" style="width: 300px" data-options="editable:false"></select>
							</td>
						</tr>
						<tr>
							<th>Year:</th>
							<td><input name="year" id="admin_customerOrder_editVehicle_year" style="width: 295px" placeholder="Enter Year" class="easyui-validatebox" data-options="validType:'length[0,20]'" /></td>
						</tr>
						<tr>
							<th>Color:</th>
							<td><input name="color" id="admin_customerOrder_editVehicle_color" style="width: 295px" placeholder="Enter color" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>COD:</th>
							<td><input name="cod" id="admin_customerOrder_editVehicle_cod" style="width: 295px" class="easyui-validatebox" data-options="validType:'length[0,50]'" /></td>
						</tr>
						<tr>
							<th>Remark:</th>
							<td><textarea id="admin_customerOrder_editVehicle_note" name="note" style="width: 300px;height: 180px;resize:none" class="easyui-validatebox" data-options="validType:'length[0,300]'" ></textarea></td>
						</tr>	
						</table>
					<table>
						<tr>
							<th style="padding-left: 80px"><input type="button" id="admin_customerOrder_editVehicle_submit" value="confirm"/></th>
						</tr>
					</table>
				</form>
			</div>   				
		</div>
