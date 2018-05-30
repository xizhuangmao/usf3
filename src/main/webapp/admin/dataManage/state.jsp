<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div id="admin_dateManage_state" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:80px;background:#eee;">
		<form id="admin_dataManage_state_from" method="post">
			<table>
				<tr>
					<td>Country :</td>
					<td><input id="admin_dateManage_state_country" class="easyui-combobox" name="countryId" value="Please Select" style="width:205px;"
	    							data-options="valueField:'id', editable:false,textField:'country',url:'${pageContext.request.contextPath}/countryAction!getCountryName.action',
	    											" /></td>
					<td><a id="admin_dateManage_state_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
					<td><a id="admin_dateManage_state_clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Clean</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_state_datagrid">
    	
    	</table>
    </div>   
</div> 


<script type="text/javascript">
$(function() {
	$("#admin_dateManage_state_datagrid").datagrid({
		url : '${pageContext.request.contextPath}/stateAction!getStateDataGrid.action',
		border : false,
		fit : true,
		idField : 'id',
		fitColumns : true,
		sortName : 'state',
		sortOrder : 'desc',
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ],
		columns : [ [ {
			field : 'id',
			hidden: true,
		},{
			field : 'countryId',
			hidden: true,
		},{
			field : 'country',
			title : 'Country',
			width : '20%',
		},{
			field : 'state',
			title : 'State',
			width : '20%',
			sortable : true
		},{
			field : 'shortname',
			title : 'State Short',
			width : '20%',
			sortable : true
		},{
			field : 'nickname',
			title : 'Nickname',
			width : '20%',
			sortable : true
		},{
			field : 'opt',
			title : 'Operation',
			formatter : function(value, row, index) {
				str = '';
				str += $.formatString('<img onclick="state_datagrid_Edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
				str += $.formatString('<img onclick="state_datagrid_Delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				return str;
			}
		}
		] ],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_dateManage_state_datagrid").datagrid('reload');
			}
			},{
			text : 'New Carrier',
			iconCls : 'icon-add',
			handler : function() {
				admin_dateManage_state_datagrid_add();
				}
			} ]
		});
	});

	admin_dateManage_state_datagrid_add = function(){
		var d =$('<div/>').dialog({
			width:340,
			heigth:200,
			title:"State Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/stateAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});		
		changeStatePanalPlace(d);
	};
	
	var state_datagrid_Edit_id;
	state_datagrid_Edit = function(id){
		state_datagrid_Edit_id = id ;
		var d =$('<div/>').dialog({
			width:340,
			heigth:200,
			title:"State Edit",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/stateEdit.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});
		changeStatePanalPlace(d);
	};
	
	state_datagrid_Delete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this State?', function (r) {  
            if (r) { 
          	  $.ajax({
        	        type: "post",
        	        dataType: "json",
        	       	url: "stateAction!deleteState.action",
        	        data: {"id":id},
        	        success: function (data) {
        	        	if(data.success){
							$.messager.show({
								title:'Message',
								msg:data.msg,
								timeout:5000,
								showType:'slide'
							});
        	        	}
        	        	$("#admin_dateManage_state_datagrid").datagrid('reload');
        	        }
        	    });
            }  
        });
	};
			
	$('#admin_dateManage_state_search').bind('click',function(){
		$("#admin_dateManage_state_datagrid").datagrid('load', serializeObject($("#admin_dataManage_state_from")));
	});
		
	$('#admin_dateManage_state_clean').bind('click',function(){
		$("#admin_dateManage_state_country").combobox('setValue', '');
	});
	
	changeStatePanalPlace = function (d){
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

