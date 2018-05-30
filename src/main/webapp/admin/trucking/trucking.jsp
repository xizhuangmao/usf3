<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="user_Trucking" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
    <div data-options="region:'north',split:true" style="height:120px;background:#eee;width:100%">
    	<form id="user_Trucking_from" method="post">
	    		<table>
	    			<tr>
	    				<td>Booking No.: <input id="user_Trucking_from_booknum" name="booknum" style="width:165px;"/></td>
	    				<td>Carrier : <input id="user_Trucking_from_carrier" class="easyui-combobox" name="carrier" value="Please Select" style="width:200px;"
	    							data-options="valueField:'carrier', editable:false,textField:'carrier',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action'" /></td>
	    				<td>Vessel: <input id="user_Trucking_from_vessel" class="easyui-combobox" name="vessel" value="Please Select" style="width:200px;"
	    							data-options="valueField:'vessel', editable:false,textField:'vessel',url:'${pageContext.request.contextPath}/vesselAction!getVesselName.action'" /></td>
	    			</tr>
	    			<tr>
	    				<td>Voyage: <input id="user_Trucking_from_voyage" class="easyui-combobox" name="voyage" value="Please Select" style="width:200px;" 
	    							data-options="valueField:'voyage', editable:false,textField:'voyage',url:'${pageContext.request.contextPath}/voyageAction!getVoyageByVesselId.action'" /></td>
	    				<td>
	    					Trucking Date From:<input id="user_Trucking_from_TruckingFromDate" type="text" name="truckingFromDate" class="easyui-datebox" 
	    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input>  
	    				</td>
	    				<td>
	    					 To:<input id="user_Trucking_from_TruckingToDate" type="text" name="truckingToDate" class="easyui-datebox" 
	    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input>
	    				</td>
	    			</tr>
	    			<tr>
	    				<td>Truck Co.:<input id="user_Trucking_from_truck" class="easyui-combobox" name="truck" value="Please Select" style="width:200px;" 
	    							data-options="valueField:'truck', editable:false,textField:'truck',url:'${pageContext.request.contextPath}/truckAction!getTruckCoName.action'" /></td>
	    				<td>Cutoff Date From: <input id="user_Trucking_from_CutoffFromDate" type="text" name="cutoffFromDate" class="easyui-datebox" 
	    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
	    				<td>To:<input id="user_Trucking_from_CutoffToDate" type="text" name="cutoffToDate" class="easyui-datebox" 
	    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
	    			</tr>
	    			<tr>
	    				<td></td>
	    				<td>
	    					<a id="user_Trucking_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a> 
	    					<a id="user_Trucking_from_Clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">Clean</a> 
	    					<a class="easyui-linkbutton" onclick="CreateFormPage('', $('#user_Trucking_datagrid'));" data-options="iconCls:'icon-print'">Print</a> 
							<a class="easyui-linkbutton" onclick="Export('TruckingExcel',$('#user_Trucking_datagrid'));" data-options="iconCls:'icon-excel'">Excel </a> 
	    				</td>
	    			</tr>
	    		</table>
    	</form>
    </div>   
    <div data-options="region:'center',border:false">
    	<table id="user_Trucking_datagrid">
    		
    	</table>
    </div>   
</div> 


<script type="text/javascript">
		$(function() {
			$("#user_Trucking_datagrid").datagrid({
				url : '${pageContext.request.contextPath}/exportAction!getValidOrders.action',
				border : false,
				fit : true,
				idField : 'booknumId',
				fitColumns : true,
				sortName : 'truckdate',
				sortOrder : 'desc',
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 50, 100 ],
				
				columns : [ [{
					field : 'booknumId',
					title : 'booknumId',
					hidden: true,
				},{
					field : 'booknum',
					title : 'Booking No.',
					width : '9%',
					sortable : true
				},{
					field : 'carrier',
					title : 'Carrier',
					width : '8%',
					sortable : true
				},{
					field : 'vessel',
					title : ' Vessel',
					width : '7%',
					sortable : true
				},{
					field : 'voyage',
					title : 'Voyage',
					width : '4%',
					sortable : true
				},{
					field : 'consize',
					title : ' ConSize ',
					width : '3%',
				},{
					field : 'vin',
					title : ' Vin',
					width : '12%',
				},{
					field : 'cutoffdate',
					title : 'Cutoff date',
					width : '5%',
				},{
					field : 'connum',
					title : 'Container No. ',
					width : '7%',
				},{
					field : 'sealnum',
					title : 'Seal No.',
					width : '5%',
				}, {
					field : 'uoo',
					title : 'UOO',
					width : '7%',
					sortable : true
				},{
					field : 'truck',
					title : 'Truck Co.',
					width : '7%',
				} ,{
					field : 'truckdate',
					title : 'Trucking Date',
					width : '6%',
				} ,{
					field : 'loaddate',
					title : 'Loading Date',
					width : '6%',
				} ,{
					field : 'terminal',
					title : 'Terminal',
					width : '7%',
				},{
					field : 'opt',
					title : 'Operation',
					formatter : function(value, row, index) {
						str = '';
						str += $.formatString('<img onclick="trucking_datagrid_edit(\'{0}\');" src="{1}" title="Edit"/>', row.booknum, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
						str += $.formatString('<img onclick="trucking_datagrid_dock(\'{0}\');" src="{1}" title="Dock"/>', row.uoo, '${pageContext.request.contextPath}/style/images/extjs_icons/printer/printer.png');
						return str;
					}
				}
				] ],
				onLoadSuccess:function(data){ 
					if(data.rows.length>0){
						$("#user_Trucking_datagrid").datagrid("mergeCellsArray",{
							mergerows:data.rows,
							mergefields:['booknum','carrier','vessel','voyage','consize','cutoffdate','connum','sealnum','uoo','truck','truckdate','loaddate','terminal','opt'],
							mergeorder:'booknum'
						});
					}
				}
			});
		});
		
		
		
		//----------------根据条件查询----------------
		$('#user_Trucking_from_Search').bind('click',function(){
			var truck = serializeObject($("#user_Trucking_from"));
			if(truck.carrier == "Please Select"){
				truck.carrier="";
			}
			if(truck.truck == "Please Select"){
				truck.truck="";
			}
			if(truck.vessel == "Please Select"){
				truck.vessel="";
			}
			if(truck.voyage == "Please Select"){
				truck.voyage="";
			}
			
			$("#user_Trucking_datagrid").datagrid('load',truck);
			$("#user_Trucking_datagrid").datagrid('unselectAll');
		});
		
		//----------------清空查询条件----------------------------
		$('#user_Trucking_from_Clean').bind('click',function(){
			$("#user_Trucking_from_booknum").val("");
			$("#user_Trucking_from_carrier").combo('setValue',"Please Select");
			$("#user_Trucking_from_carrier").combo('setText',"Please Select");
			$("#user_Trucking_from_vessel").combo('setValue',"Please Select");
			$("#user_Trucking_from_vessel").combo('setText',"Please Select");
			$("#user_Trucking_from_voyage").combo('setValue',"Please Select");
			$("#user_Trucking_from_voyage").combo('setText',"Please Select");
			$("#user_Trucking_from_truck").combo('setValue',"Please Select");
			$("#user_Trucking_from_truck").combo('setText',"Please Select");
			
			$("#user_Trucking_from_TruckingFromDate").combo('setValue',"");
			$("#user_Trucking_from_TruckingToDate").combo('setValue',"");
			$("#user_Trucking_from_CutoffFromDate").combo('setValue',"");
			$("#user_Trucking_from_CutoffToDate").combo('setValue',"");
			$("#user_Trucking_from_TruckingFromDate").combo('setText',"");
			$("#user_Trucking_from_TruckingToDate").combo('setText',"");
			$("#user_Trucking_from_CutoffFromDate").combo('setText',"");
			$("#user_Trucking_from_CutoffToDate").combo('setText',"");
		});
		
		//全局的对象
		var TruckEditBooknum;
		trucking_datagrid_edit = function(booknum){
			TruckEditBooknum = booknum;
			var d =$('<div/>').dialog({
				width:300,
				heigth:300,
				title:"Truck Eidt",
				modal:true,
				href:'${pageContext.request.contextPath}/admin/trucking/trucking_edit.jsp',
				onClose:function(){
					$("#user_Trucking_datagrid").datagrid({url : '${pageContext.request.contextPath}/exportAction!getValidOrders.action',});
					$("#user_Trucking_datagrid").datagrid('unselectAll');
					d.dialog('destroy');
				},
			});
			var browserHeight = $(window).height();  //游览器
			var browserwidth = $(window).width();
			var width = d.panel('options').width;//获取容器的宽
			if(browserwidth>(width+200)){
				if(browserHeight>700){
					d.panel('resize',{
						left : 150,
						top  : 150,
					});
				}else{
					d.panel('resize',{
						left : 150,
						top  : 0,
					});
				}
				
			}else{
				if(browserHeight>700){
					d.panel('resize',{
						left : 0,
						top  : 150,
					});
				}else{
					d.panel('resize',{
						left : 0,
						top  : 0,
					});
				}
			}
		};
		
		
		//全局的对象
		var TruckDockUoo;
		trucking_datagrid_dock = function(uoo){
			TruckDockUoo = uoo;
			var url = "${pageContext.request.contextPath}/admin/report/dockReceipt.jsp";
			addTab({title:"Dock Receipt",href:url,closable:true});
			
		};
		
</script>