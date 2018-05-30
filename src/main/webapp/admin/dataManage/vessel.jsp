<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_Vessel" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:80px;background:#eee;">
		<form id="admin_dateManage_Vessel_from" method="post">
			<table>
				<tr>
					<td>Carrier :<input id="admin_dateManage_Vessel_carrier" class="easyui-combobox" name="carrierId" style="width:200px;"
		    							data-options="valueField:'id', textField:'fullname',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action'" /></td>
					
					<td>Vessel Name :<input id="admin_dateManage_Vessel_vessel" name="vessel" style="width:165px;"/></td>
					<td><a id="admin_dateManage_Vessel_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
					<td><a id="admin_dateManage_Vessel_clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Clean</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_Vessel_datagrid">
    	
    	</table>
    </div>   
</div> 

<script type="text/javascript">
$(function() {
	$("#admin_dateManage_Vessel_datagrid").datagrid({
		url : '${pageContext.request.contextPath}/vesselAction!getVesselDataGrid.action',
		border : false,
		fit : true,
		idField : 'id',
		fitColumns : true,
		sortName : 'vessel',
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
			width : '20%',
		},{
			field : 'vessel',
			title : 'Vessel',
			width : '20%',
			sortable : true
		},{
			field : 'opt',
			title : 'Operation',
			formatter : function(value, row, index) {
				str = '';
				str += $.formatString('<img onclick="vessel_datagrid_Edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
				str += $.formatString('<img onclick="vessel_datagrid_Delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				return str;
			}
		}
		] ],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_dateManage_Vessel_datagrid").datagrid('reload');
			}
			},{
			text : 'New Vessel',
			iconCls : 'icon-add',
			handler : function() {
				admin_dateManage_Vessel_datagrid_add();
				}
			} ]
		});
	});
	
	admin_dateManage_Vessel_datagrid_add = function(){
		var d =$('<div/>').dialog({
			width:300,
			heigth:200,
			title:"Vessel Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/vesselAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});		
		changeVesselPanalPlace(d);
	};

	var Vessel_datagrid_Edit_id;
	vessel_datagrid_Edit = function(id){
		Vessel_datagrid_Edit_id = id ;
		var d =$('<div/>').dialog({
			width:300,
			heigth:200,
			title:"Vessel Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/vesselEdit.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});		
		changeVesselPanalPlace(d);
	};

	$('#admin_dateManage_Vessel_search').bind('click',function(){
		$("#admin_dateManage_Vessel_datagrid").datagrid('load', serializeObject($("#admin_dateManage_Vessel_from")));
	});
		
	$('#admin_dateManage_Vessel_clean').bind('click',function(){
		$("#admin_dateManage_Vessel_carrier").combobox('setValue', '');
		$("#admin_dateManage_Vessel_carrier").combobox('setText', '');
		$("#admin_dateManage_Vessel_vessel").val("");	
	});
		
	vessel_datagrid_Delete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this Vessel?', function (r) {  
        	if (r) { 
            	$.ajax({
          	    	type: "post",
          	        dataType: "json",
          	       	url: "vesselAction!deleteVessel.action",
          	        data: {"id":id},
          	        success: function (data) {
          	        	if(data.success){
							$.messager.show({
								title:'Message',
								msg:data.msg,
								timeout:5000,
								showType:'slide'
							});
        	        		$("#admin_dateManage_Vessel_datagrid").datagrid('reload');
          	        	}
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

