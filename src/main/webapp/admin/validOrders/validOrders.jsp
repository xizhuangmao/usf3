<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_validOrders_validOrders_div" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
    <div data-options="region:'north',title:'search',split:true" style="height:145px;background:#eee;">
    	<form id="admin_validOrders_validOrders_from" method="post">
    		<table>
    			<tr>
    				<td>Booking No.: <input id="admin_validOrders_validOrders_from_booknum" name="booknum" style="width:165px;"/></td>
    			</tr>
    			<tr>
    				<td>
    					Trucking Date From:<input id="admin_validOrders_validOrders_form_TruckingFromDate" type="text" class="easyui-datebox" name="truckingFromDate"
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input>  
    				</td>
    				<td>
    					 To:<input id="admin_validOrders_validOrders_form_TruckingToDate" type="text" class="easyui-datebox" name="truckingToDate"
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input>
    				</td>
    			</tr>
    			<tr>
    				<td>Cutoff Date From: <input id="admin_validOrders_validOrders_form_CutoffFromDate" type="text" class="easyui-datebox"  name="cutoffFromDate" 
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
    				<td>To:<input id="admin_validOrders_validOrders_form_CutoffToDate" type="text" class="easyui-datebox" name="cutoffToDate"
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
    			</tr>
    			<tr>
    				<td>
    					<a id="admin_validOrders_validOrders_form_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a> 
    					<a id="admin_validOrders_validOrders_form_Clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">Clean</a>
    					<a class="easyui-linkbutton" onclick="CreateFormPage('', $('#admin_validOrders_validOrders_datagrid'));" data-options="iconCls:'icon-print'">Print</a> 
						<a class="easyui-linkbutton" onclick="Export('validOrdersExcel',$('#admin_validOrders_validOrders_datagrid'));" data-options="iconCls:'icon-excel'">Excel </a>
    				</td>
    			</tr>
    		</table>
    	</form>
    </div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_validOrders_validOrders_datagrid">
    	
    	</table>
    </div>   
</div> 


<script type="text/javascript">
		$(function() {
			$("#admin_validOrders_validOrders_datagrid").datagrid({
				url : '${pageContext.request.contextPath}/exportAction!getValidOrders.action',
				border : false,
				fit : true,
				idField : 'id',
				title:'ValidOrders',
				fitColumns : true,
				sortName : 'booknum',
				sortOrder : 'desc',
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 50, 100 ],
				columns : [ [ {
					field : 'booknum',
					title : 'Booking No.',
					width : '8%',
					sortable : true
				},{
					field : 'cutoffdate',
					title : 'Cutoff Date',
					width : '7%',
					sortable : true
				},{
					field : 'consize',
					title : 'ContSize',
					width : '4%',
				}, {
					field : 'carrier',
					title : 'Carrier',
					width : '7%',
					sortable : true
				},{
					field : 'vessel',
					title : ' Vessel',
					width : '7%',
					sortable : true
				} ,{
					field : 'voyage',
					title : 'Voyage',
					width : '6%',
					sortable : true
				} ,{
					field : 'vin',
					title : ' Vin',
					width : '10%',
				} ,{
					field : 'users',
					title : ' Customer',
					width : '7%',
				} ,{
					field : 'pod',
					title : 'Order Pod',
					width : '7%',
				} ,{
					field : 'terminal',
					title : 'bkgPod',
					width : '7%',
				} ,{
					field : 'connum',
					title : 'Container No.',
					width : '5%',
				} ,{
					field : 'sealnum',
					title : 'Seal No.',
					width : '5%',
				} ,{
					field : 'uoo',
					title : 'UOO',
					width : '7%',
				} ,{
					field : 'truckdate',
					title : 'Trucking Date',
					width : '7%',
					sortable : true
				}, {
					field : 'opt',
					title : 'Operation',
					formatter : function(value, row, index) {
						str = '';
						str += $.formatString('<img onclick="admin_validOrders_validOrders_editBooknum(\'{0}\');" src="{1}" title="修改"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
						str += $.formatString('<img onclick="admin_validOrders_validOrders_removeBooknum(\'{0}\');" src="{1}" title="删除"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/deletepic.png');
						return str;
					}
				}
				] ],
				toolbar : [ {
					iconCls : 'icon-reload',
					handler : function() {
						$("#admin_validOrders_validOrders_datagrid").datagrid('reload');
					}
				} ],
				onLoadSuccess:function(data){ 
					if(data.rows.length>0){
						$("#admin_validOrders_validOrders_datagrid").datagrid("mergeCellsArray",{
							mergerows:data.rows,
							mergefields:['booknum','cutoffdate','consize','carrier','vessel','voyage','terminal','connum','sealnum','uoo','opt'],
							mergeorder:'id'
						});
					}
				},
				onClickCell: function (rowIndex, field, value) {
					IsCheckFlag = false;
				},
				onSelect: function (rowIndex, rowData) {
					if (!IsCheckFlag) {
						IsCheckFlag = true;
						$("#admin_validOrders_validOrders_datagrid").datagrid("unselectRow", rowIndex);
					}
				},                    
				onUnselect: function (rowIndex, rowData) {
					if (!IsCheckFlag) {
						IsCheckFlag = true;
						$("#admin_validOrders_validOrders_datagrid").datagrid("selectRow", rowIndex);
					}
				}
			});
		});
		
		var admin_validOrders_validOrders_editBooknumId;
		admin_validOrders_validOrders_editBooknum = function(booknumId, booknum){
			admin_validOrders_validOrders_editBooknumId = booknumId;
			var url = "${pageContext.request.contextPath}/admin/ordersAdd/newOrders.jsp";
			if($('#layout_center_tabs').tabs('exists',"Edit UOO")){
				var tab = $('#layout_center_tabs').tabs('getSelected');
				$('#layout_center_tabs').tabs('close', tab);
				addTab({title:"Edit UOO",href:'admin/validOrders/editBooknum.jsp',closable:true});
				//tab.panel('refresh', 'admin/validOrders/editBooknum.jsp');
			}else{
				addTab({title:"Edit UOO",href:'admin/validOrders/editBooknum.jsp',closable:true});
			}
		};
		
		admin_validOrders_validOrders_removeBooknum = function(booknumId){
			$.messager.confirm('confirm', 'delete the booknum?', function(r){
			    if (r){
					$.ajax({
						url:'booknumAction!deleteBooknum.action',
						data:{"id" : booknumId},
						dataType:'text',
						type:'post',
						success:function(data){
							var obj = $.parseJSON(data);
							if(obj.success){
								$.messager.show({
									title:'Message',
									msg:obj.msg,
									timeout:5000,
									showType:'slide'
								});
								$("#admin_validOrders_validOrders_datagrid").datagrid('reload');
							}
						}
					});
				}
			});
		};
		
		//---------------------根据条件查询------------------------------------
		$('#admin_validOrders_validOrders_form_Search').bind('click',function(){
			$("#admin_validOrders_validOrders_datagrid").datagrid('load',serializeObject($("#admin_validOrders_validOrders_from")));
			$("#admin_validOrders_validOrders_datagrid").datagrid('unselectAll');
		});
		//------------------------清空条件的按钮-------------------------------
		$('#admin_validOrders_validOrders_form_Clean').bind('click',function(){
			$("#admin_validOrders_validOrders_from_booknum").val("");
			
			$("#admin_validOrders_validOrders_from_TruckingFromDate").combo('clear');
			$("#admin_validOrders_validOrders_from_TruckingToDate").combo('clear');
			$("#admin_validOrders_validOrders_from_CutoffFromDate").combo('clear');
			$("#admin_validOrders_validOrders_from_CutoffToDate").combo('clear');
		});
		
		
</script>