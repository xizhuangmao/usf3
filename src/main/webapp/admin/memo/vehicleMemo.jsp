<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- 车辆memo -->
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:'warehouseAction!findVehicleInfoById.action',
			data:{"id":'<%= request.getParameter("id")%>'},
			type:'post',
			dataType:'text',
			success:function(data){
				var obj = $.parseJSON(data);
				$("#admin_wareHouse_vehicleMemo_form").form('load', obj);
				$("#admin_wareHouse_vehicleMemo_table").datagrid({
					url:"warehouseAction!findVehicleMemoByVin.action?vin="+obj.vin+"",
					border:false,
					fitColumns:true,
					pagination:true,
					pagePosition:'top',
					pageSize:10,
					pageList:[10,20,50,100],
					idField:'id',
					sortName:'memodate',
					sortOrder:'desc',
					checkOnSelect:false,
					selectOncheck:false,
					nowrap:false,
					columns:[[{
						field:'id',
						title:'id',
						hidden:true
					},{
						field:'users',
						title:'User',
						width:'50px',
						formatter : function(value,row,index){
					 		return "<a style=\"cursor:pointer\" title=\""+value+"\" class=\"easyui-tooltip\">"+value+"</a>";
					 	}
					},{
						field:'content',
						title:'Content',
						width:'640px',
					},{
						field:'memodate',
						title:'Date',
						width:'140px',
						sortable:true,
					},{
						field:'str',
						title:'Operation',
						width:'70px',
						formatter : function(value, row, index){
							if(row.users == '<%= session.getAttribute("sessionInfo").toString()%>'){
								var str = "";

		            			str += $.formatString("<div style=\"width:16px;height:16px;float:left\"><img id=\""+row.id+"\" onclick=\"admin_wareHouse_vehicleMemo_editMemo(\'{0}\');\" src=\"{1}\" title=\"edit\"/></div>", index, "${pageContext.request.contextPath}/images/pencil.png");
		            				
		            			str += $.formatString("<div style=\"width:16px;height:16px;float:left\"><img onclick=\"admin_wareHouse_vehicleMemo_deleteMemo(\'{0}\');\" src=\"{1}\" title=\"delete\"/></div>", row.id, "${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png");
		            				
		            			return str;
							}
		            	}
					}]],
					onClickCell: function (rowIndex, field, value) {
						IsCheckFlag = false;
					},
					onSelect: function (rowIndex, rowData) {
						if (!IsCheckFlag) {
							IsCheckFlag = true;
							$("#admin_wareHouse_vehicleMemo_table").datagrid("unselectRow", rowIndex);
						}
					},                    
					onUnselect: function (rowIndex, rowData) {
						if (!IsCheckFlag) {
							IsCheckFlag = true;
							$("#admin_wareHouse_vehicleMemo_table").datagrid("selectRow", rowIndex);
						}
					},
				});
			}
		});
	});
	
	$("#admin_wareHouse_vehicleMemo_form").form({
		url:"warehouseAction!addVehicleMemo.action",
  		success:function(data){
  			var obj = $.parseJSON(data);
			if(obj.msg == 'success'){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				$("#admin_wareHouse_vehicleMemo_table").datagrid('reload');
				$("#admin_wareHouse_vehicleMemo_textarea").val("");
				$("#admin_wareHouse_vehicleMemo_input").val("");
				$("#admin_wareHouse_vehicleMemo_save").attr('src','images/confirm.png'); 
			}else{
				$.messager.alert('warning', obj.msg);
				$("#admin_wareHouse_vehicleMemo_input").val("");
				$("#admin_wareHouse_vehicleMemo_textarea").val("");
				$("#admin_wareHouse_vehicleMemo_save").attr('src','images/confirm.png'); 
			}
  		}
	});
	
	$("#admin_wareHouse_vehicleMemo_save").click(function(){
		if($("#admin_wareHouse_vehicleMemo_textarea").val() != ""){
			$('#admin_wareHouse_vehicleMemo_form').submit();
		}else{
			$.messager.show({
				title:'Message',
				msg:'Please enter the content.',
				timeout:5000,
				showType:'slide'
			});	
		}
	});
	
	admin_wareHouse_vehicleMemo_editMemo = function(index){
		var row = $('#admin_wareHouse_vehicleMemo_table').datagrid('getData').rows[index];
		$("#admin_wareHouse_vehicleMemo_input").val("");
		$("#admin_wareHouse_vehicleMemo_textarea").val("");
		$("#admin_wareHouse_vehicleMemo_input").val(row.id);
		$("#admin_wareHouse_vehicleMemo_textarea").val(row.content);
		$("#admin_wareHouse_vehicleMemo_save").attr('src','images/edit.png'); 
	};
	
	admin_wareHouse_vehicleMemo_deleteMemo = function(id){
		$.messager.confirm('confirm', 'delete the vehicle memo?', function(r){
		    if (r){
		    	$.ajax({
					url:'memoAction!deleteVehicleInfoById.action',
					data:{"id":id},
					dataType:'text',
					type:'post',
					success:function(data){
						var obj = $.parseJSON(data);
						if(obj.msg == 'success'){
							$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							$("#admin_wareHouse_vehicleMemo_table").datagrid('reload');
						}else{
							$.messager.alert('warning', obj.msg);
							$("#admin_wareHouse_vehicleMemo_table").datagrid('reload');
						}
					}
				});	
		    }
		});
	};
</script>
<div style="width: 900px;height: 500px">
	<div>
		<form id="admin_wareHouse_vehicleMemo_form" method="post">
			<table style="padding-top: 5px">
				<tbody>
					<tr>
						<th>Vin:</th>
						<td><input id="admin_wareHouse_vehicleMemo_vin" name="vin" readonly="readonly" style="border:0px"/></td>
						<th>Customer:</th>
						<td><input id="admin_wareHouse_vehicleMemo_users" name="users" style="width: 450px;border:0px" readonly="readonly"/></td>
						<td><input id="admin_wareHouse_vehicleMemo_whes" name="whes" type="hidden"/></td>
					</tr>
				</tbody>
			</table>
			<p style="border-top: 1px solid #747474;width: 900px;"></p>
			<table style="width: 900px;height: 400px" id="admin_wareHouse_vehicleMemo_table"></table>
			<input id="admin_wareHouse_vehicleMemo_input" name="memoId" type="hidden"/>
			<textarea id="admin_wareHouse_vehicleMemo_textarea" name="memo" style="width: 890px;height: 60px;resize:none;border-radius:5px; border:solid 1px #747474;margin-left: 2px" placeholder="Please enter a comment on the content"></textarea>
		</form>
	</div>
	<div align="right">
		<img id="admin_wareHouse_vehicleMemo_save" src="images/confirm.png"/>
	</div>
</div>
