<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_Voyage" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:80px;background:#eee;">
		<form id="admin_dateManage_Voyage_from" method="post">
			<table>
				<tr>
					<td>Carrier :<input id="admin_dateManage_Voyage_from_carrier" name="carrierId" style="width:200px;"
		    							data-options="editable:false,valueField:'id', textField:'fullname',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action'" /></td>
					<td>Vessel : <input id="admin_dateManage_Voyage_from_vessel" class="easyui-combobox"  name="vesselId" data-options="editable:false"></td>
					<td>Voyage Name :<input id="admin_dateManage_Voyage_from_voyage" name="voyage"/></td>
					<td><a id="admin_dateManage_Voyage_from_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
					<td><a id="admin_dateManage_Voyage_from_clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Clean</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_Voyage_datagrid">
    	
    	</table>
    </div>   
</div> 


<script type="text/javascript">
	$('#admin_dateManage_Voyage_from_carrier').combobox({    
		onSelect:function(record){
			var url = '${pageContext.request.contextPath}/vesselAction!getVesselByCarrierId.action?carrierId='+record.id;
	        $('#admin_dateManage_Voyage_from_vessel').combobox({
		        'url': url,
		        valueField: 'id',    
				textField: 'vessel',	
	        }); 
		}
	}); 
	
	$(function() {
		$("#admin_dateManage_Voyage_datagrid").datagrid({
			url : '${pageContext.request.contextPath}/voyageAction!getVoyageDataGrid.action',
			border : false,
			fit : true,
			idField : 'id',
			fitColumns : true,
			sortName : 'voyage',
			sortOrder : 'desc',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 50, 100 ],
			columns : [ [ {
				field : 'id',
				hidden: true,
			},{
				field : 'fullname',
				title : 'Carrier',
				width : '15%',
			},{
				field : 'vessel',
				title : 'Vessel',
				width : '15%',
				sortable : true
			},{
				field : 'voyage',
				title : 'Voyage',
				width : '15%',
			},{
				field : 'terminal',
				title : 'Terminal',
				width : '15%',
				sortable : true
			},{
				field : 'cutoffdate',
				title : 'Cutoffdate',
				width : '8%',
			},{
				field : 'etd',
				title : 'ETD',
				width : '8%',
				sortable : true
			},{
				field : 'eta',
				title : 'ETA',
				width : '8%',
			},{
				field : 'opt',
				title : 'Operation',
				formatter : function(value, row, index) {
					str = '';
					str += $.formatString('<img onclick="voyage_datagrid_Edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
					if(row.type != '1'){
						str += $.formatString('<img onclick="voyage_datagrid_Delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					}
					return str;
				}
			}
			] ],
			toolbar : [ {
				iconCls : 'icon-reload',
				handler : function() {
					$("#admin_dateManage_Voyage_datagrid").datagrid('reload');
				}
				},{
				text : 'New Voyage',
				iconCls : 'icon-add',
				handler : function() {
					admin_dateManage_Voyage_datagrid_add();
				}
			} ]
		});
	});
	
	admin_dateManage_Voyage_datagrid_add = function(){
		var d =$('<div/>').dialog({
			width:350,
			heigth:300,
			title:"Voyage Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/voyageAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});		
		changeVesselPanalPlace(d);
	};

	var Voyage_datagrid_Edit_id;
	voyage_datagrid_Edit = function(id){
		Voyage_datagrid_Edit_id = id ;
		var d =$('<div/>').dialog({
			width:350,
			heigth:300,
			title:"Voyage Edit",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/voyageEdit.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});		
		changeVesselPanalPlace(d);
	};

	$('#admin_dateManage_Voyage_from_search').bind('click',function(){
		$("#admin_dateManage_Voyage_datagrid").datagrid('load', serializeObject($("#admin_dateManage_Voyage_from")));
	});
			
	$('#admin_dateManage_Voyage_from_clean').bind('click',function(){
		$("#admin_dateManage_Voyage_from_carrier").combobox('setValue', '');
		$("#admin_dateManage_Voyage_from_carrier").combobox('setText', '');
		$("#admin_dateManage_Voyage_from_vessel").combobox('setValue', '');
		$("#admin_dateManage_Voyage_from_vessel").combobox('setText', '');
		$("#admin_dateManage_Voyage_from_voyage").val("");
			
	});
		
	voyage_datagrid_Delete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this Voyage?', function (r) {  
        	if (r) { 
            	$.ajax({
          	        type: "post",
          	        dataType: "json",
          	       	url: "voyageAction!deleteVoyage.action",
          	        data: {"id":id},
          	        success: function (data) {
						$.messager.show({
							title:'Message',
							msg:data.msg,
							timeout:5000,
							showType:'slide'
						});
        	        	$("#admin_dateManage_Voyage_datagrid").datagrid('reload');
          	        }
          	    });
            }  
       });
	};
	
	changeVesselPanalPlace = function (d){
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

</script>

