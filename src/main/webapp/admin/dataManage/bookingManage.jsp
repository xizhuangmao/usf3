<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#admin_dataManage_bookingManage_datagrid").datagrid({
		url:"bookingManageAction!findAllBookingNum.action",
		border:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[10,20,50,100],
		idField:'id',
		sortName:'booknum',
		sortOrder:'desc',
		checkOnSelect:false,
		selectOncheck:false,
		fit:true,
		columns:[[{
			field:'id',
			title:'编号',
			checkbox:true,
		},{
			field:'booknum',
			title:'Booking No.',
			sortable:true,
		},{
			field:'company',
			title:'Company',
		},{
			field:'carrier',
			title:'Carrier',
		},{
			field:'vessel',
			title:'Vessel',
		},{
			field:'voyage',
			title:'Voyage',
		},{
			field:'users',
			title:'Customer',
		},{
			field:'vin',
			title:'Vin',
		},{
			field:'pod',
			title:'Pod',
		},{
			field:'cutoffdate',
			title:'Cutoff Date',
		},{
			field:'opt',
			title:'Operation',
			width:'150px',
			formatter : function(value, row, index){
				str = '';
				str += $.formatString('<img onclick="admin_dataManage_editBookingManage(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
				if(row.type != '1'){
					str += $.formatString('<img onclick="admin_dataManage_deleteBookingManage(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				}
				return str;
			}
		}]],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_dataManage_bookingManage_datagrid").datagrid('reload');
			}
			},{
			iconCls : 'icon-add',
			text:'Add Booknum',
			handler : function() {
				newOrdersAddBooknumType = 2;
				var d =$('<div/>').dialog({
					width:440,
					heigth:440,
					title:"Booknum Add",
					modal:true,
					href:'${pageContext.request.contextPath}/admin/ordersAdd/newOrdersAddBooknum.jsp',
					onClose:function(){
						d.dialog('destroy');
					},
				});
			
				changePanalPlace(d);
			}
		} ],	
		onLoadSuccess:function(data){
			if(data.rows.length>0){
		 		$("#admin_dataManage_bookingManage_datagrid").datagrid("mergeCellsArray",{
					mergerows:data.rows,
					mergefields:['id','booknum','nvocc','carrier','vessel','voyage','pod','loaddate','opt'],
					mergeorder:'id',
				});
			} 
		},
		onClickCell: function (rowIndex, field, value) {
			IsCheckFlag = false;
		},
		onSelect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_dataManage_bookingManage_datagrid").datagrid("unselectRow", rowIndex);
			}
		},                    
		onUnselect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_dataManage_bookingManage_datagrid").datagrid("selectRow", rowIndex);
			}
		},	
	});

	admin_dataManage_deleteBookingManage = function(id){
		$.messager.confirm('confirm', 'delete the booknum?', function(r){
		   	if (r){
				$.ajax({
					url : 'bookingManageAction!deleteBookingNum.action',
					data : {"id" : id},
					dataType : 'text',
					type : 'post',
					success : function(data){
						var obj = $.parseJSON(data);
						$.messager.show({
							title:'Message',
							msg:obj.msg,
							timeout:5000,
							showType:'slide'
						});
						$("#admin_dataManage_bookingManage_datagrid").datagrid('reload');
					}
				});
			}
		});
	};
			
	admin_dataManage_editBookingManage = function(id){
		var d =$('<div/>').dialog({
			width:450,
			heigth:250,
			title:"edit booknum",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/dataManage/editBookingNum.jsp?id="+id+"",
			onClose:function(){
				d.dialog('destroy');
			},
		});
					
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = d.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				d.panel('resize',{
					left : 450,
					top  : 210,
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
					top  : 10,
				});
			}else{
				d.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
			
	//carrier对应型号vessel
	$("#admin_dataManage_bookingManage_carrier").combobox({
		onSelect: function(carrier){ 
			$("#admin_dataManage_bookingManage_vessel").combobox({
				url:'vesselAction!getVesselByCarrierId.action?carrierId=' + carrier.id,    
    			valueField:'id',    
    			textField:'vessel',
    			onSelect: function(vessel){ 
    				$("#admin_dataManage_bookingManage_voyage").combobox({
    					url:'voyageAction!getVoyageByVesselId.action?vesselId=' + vessel.id,    
    					valueField:'id',    
    					textField:'voyage',
    				});
    			}  
			});
		}
	});
			
	//Year
	for(var i=2001;i<2021;i++){
		$("#admin_dataManage_bookingManage_year").append("<option value='"+i+"'>"+i+"</option>");
	}
			
	$("#admin_dataManage_bookingManage_search").click(function(){
		$("#admin_dataManage_bookingManage_datagrid").datagrid('load', serializeObject($("#admin_dataManage_bookingManage_form")));
	});
			
	$("#admin_dataManage_bookingManage_clear").click(function(){
		$("#admin_dataManage_bookingManage_datagrid").datagrid('load', {});
		$("#admin_dataManage_bookingManage_form input").val("");
		$("#admin_dataManage_bookingManage_form select").prop("selectedIndex", 0);   //重置所有select,选中第一项
	});
	
	changePanalPlace = function (d){
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = d.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				d.panel('resize',{
					left : 615,
					top  : 200,
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
});	
</script>
	<div class="easyui-layout" fit="true"> 
		<div data-options="region:'north',title:'search',split:true" style="height:145px;" border="false">
			<form id="admin_dataManage_bookingManage_form" name="form" class="datagrid-toolbar" method="post">
				<div style="width: 850px">
					<table id="admin_dataManage_bookingManage_table">
						<tr>
							<th>Carrier:</th>
							<td>
								<input id="admin_dataManage_bookingManage_carrier" name="carrierId" style="width: 300px" data-options="editable:false,valueField:'id',textField:'fullname',url:'carrierAction!getCarrierName.action'" />
							</td>
							<th>Vessel:</th>
							<td>
								<input id="admin_dataManage_bookingManage_vessel" name="vesselId" class="easyui-combobox" style="width: 300px" data-options="editable:false"/>
							</td>
						</tr>
						<tr>
							<th>Voyage:</th>
							<td>
								<input id="admin_dataManage_bookingManage_voyage" name="voyageId" class="easyui-combobox" style="width: 300px" data-options="editable:false"/>
							</td>
							<th>Booking No.:</th>		
							<td><input type="text" name="booknum" class="input-small" id="admin_dataManage_bookingManage_bookingNum" autocomplete="off" style="width: 295px"/></td>
						</tr>
						<tr>
							<th>Company:</th>
							<td>
								<input id="admin_dataManage_bookingManage_bookingCom" name="companyId" class="easyui-combobox" style="width: 300px" data-options="editable:false,valueField:'id',textField:'fullname',url:'companyAction!findCompany.action'" />
							</td>
						</tr>
						<tr>
							<td>
								<a id="admin_dataManage_bookingManage_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
								<a id="admin_dataManage_bookingManage_clear" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">clean</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>  
		<div data-options="region:'center',border:false">
			<table id="admin_dataManage_bookingManage_datagrid"></table>
		</div>    				
	</div>
