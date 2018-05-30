<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- booknum memo -->
<script type="text/javascript">
	$(function(){
		$.ajax({
			url:'booknumAction!findBooknumUooById.action',
			data:{"id":'<%= request.getParameter("id")%>'},
			type:'post',
			dataType:'text',
			success:function(data){
				var obj = $.parseJSON(data);
				$("#admin_validOrders_booknumMemo_form").form('load', obj);
				$("#admin_validOrders_booknumMemo_form_table").datagrid({
					url:"memoAction!findUooMemoByBooknumId.action?booknumId="+obj.id+"",
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

		            			str += $.formatString("<div style=\"width:16px;height:16px;float:left\"><img id=\""+row.id+"\" onclick=\"admin_validOrders_BooknumMemo_editMemo(\'{0}\');\" src=\"{1}\" title=\"edit\"/></div>", index, "${pageContext.request.contextPath}/images/pencil.png");
		            				
		            			str += $.formatString("<div style=\"width:16px;height:16px;float:left\"><img onclick=\"admin_validOrders_BooknumMemo_deleteMemo(\'{0}\');\" src=\"{1}\" title=\"delete\"/></div>", row.id, "${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png");
		            				
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
							$("#admin_validOrders_booknumMemo_form_table").datagrid("unselectRow", rowIndex);
						}
					},                    
					onUnselect: function (rowIndex, rowData) {
						if (!IsCheckFlag) {
							IsCheckFlag = true;
							$("#admin_validOrders_booknumMemo_form_table").datagrid("selectRow", rowIndex);
						}
					},
				});
			}
		});
	});
	
	$("#admin_validOrders_booknumMemo_form").form({
		url:"booknumAction!addBooknumMemo.action",
  		success:function(data){
  			var obj = $.parseJSON(data);
			if(obj.msg == 'success'){
				$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
				$("#admin_validOrders_booknumMemo_form_table").datagrid('reload');
				$("#admin_validOrders_booknumMemo_form_textarea").val("");
				$("#admin_validOrders_booknumMemo_form_input").val("");
				$("#admin_validOrders_booknumMemo_save").attr('src','images/confirm.png'); 
			}else{
				$.messager.alert('warning', obj.msg);
				$("#admin_validOrders_booknumMemo_form_input").val("");
				$("#admin_validOrders_booknumMemo_form_textarea").val("");
				$("#admin_validOrders_booknumMemo_save").attr('src','images/confirm.png'); 
			}
  		}
	});
	
	$("#admin_validOrders_booknumMemo_save").click(function(){
		if($("#admin_validOrders_booknumMemo_form_textarea").val() != ""){
			$('#admin_validOrders_booknumMemo_form').submit();
		}else{
			$.messager.show({
				title:'Message',
				msg:'Please enter the content.',
				timeout:5000,
				showType:'slide'
			});	
		}
	});
	
	admin_validOrders_BooknumMemo_editMemo = function(index){
		var row = $('#admin_validOrders_booknumMemo_form_table').datagrid('getData').rows[index];
		$("#admin_validOrders_booknumMemo_form_input").val("");
		$("#admin_validOrders_booknumMemo_form_textarea").val("");
		$("#admin_validOrders_booknumMemo_form_input").val(row.id);
		$("#admin_validOrders_booknumMemo_form_textarea").val(row.content);
		$("#admin_validOrders_booknumMemo_save").attr('src','images/edit.png'); 
	};
	
	admin_validOrders_BooknumMemo_deleteMemo = function(id){
		$.messager.confirm('confirm', 'delete the booknum memo?', function(r){
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
							$("#admin_validOrders_booknumMemo_form_table").datagrid('reload');
						}else{
							$.messager.alert('warning', obj.msg);
							$("#admin_validOrders_booknumMemo_form_table").datagrid('reload');
						}
					}
				});	
		    }
		});
	};
</script>
<div style="width: 900px;height: 500px">
	<div>
		<form id="admin_validOrders_booknumMemo_form" method="post">
			<table style="padding-top: 5px">
				<tbody>
					<tr>
						<td><input name="id" style="border:0px;" hidden="true"/></td>
						<th>Booknum:</th>
						<td><input id="admin_validOrders_booknumMemo_form_booknum" name="booknum" readonly="readonly" style="border:0px"/></td>
						<th>UOO:</th>
						<td><input id="admin_validOrders_booknumMemo_form_Uoo" name="uoo" style="width: 250px;border:0px" readonly="readonly"/></td>
					</tr>
				</tbody>
			</table>
			<p style="border-top: 1px solid #747474;width: 900px;"></p>
			<table style="width: 900px;height: 400px" id="admin_validOrders_booknumMemo_form_table"></table>
			<input id="admin_validOrders_booknumMemo_form_input" name="memoId" type="hidden"/>
			<textarea id="admin_validOrders_booknumMemo_form_textarea" name="memo" style="width: 890px;height: 60px;resize:none;border-radius:5px; border:solid 1px #747474;margin-left: 2px" placeholder="Please enter a comment on the content"></textarea>
		</form>
	</div>
	<div align="right">
		<img id="admin_validOrders_booknumMemo_save" src="images/confirm.png"/>
	</div>
</div>
