<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div id="admin_dataManage_ModelManage" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:85px;background:#eee;">
		<form id="admin_dataManage_ModelManage_from" method="post">
			<table>
	    		<tr>
	    			<td>Make :</td>							
	    			<td><input id="admin_dataManage_ModelManage_from_make" class="easyui-combobox" name="makeId" style="width: 205px"
	    							data-options="valueField:'id', textField:'make',url:'${pageContext.request.contextPath}/makeAction!getMakeName.action',
	    											" /></td>
				</tr>
				<tr>
	    			<td>Model :</td>							
	    			<td><input id="admin_dataManage_ModelManage_from_model" class="easyui-combobox" name="model" style="width: 205px"
	    							data-options="valueField:'model', textField:'model',url:'${pageContext.request.contextPath}/modelAction!getModelName.action',
	    											" /></td>
				</tr>
				<tr>
					<td><a id="admin_dataManage_ModelManage_from_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
					<td><a id="admin_dataManage_ModelManage_from_clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Clean</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dataManage_ModelManage_from_datagrid">
    	</table>
    </div>   
</div> 

<script type="text/javascript">
	$(function(){
		$("#admin_dataManage_ModelManage_from_datagrid").datagrid({
			url : '${pageContext.request.contextPath}/modelAction!getModelDataGrid.action',
			border : false,
			fit : true,
			idField : 'id',
			fitColumns : true,
			sortName : 'model',
			sortOrder : 'desc',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 50, 100 ],
			columns : [ [ {
				field : 'id',
				hidden: true,
			},{
				field : 'makeId',
				hidden: true,
			},{
				field : 'make',
				title : 'Make',
				width : '20%',
				sortable : true
			},{
				field : 'model',
				title : 'Model',
				width : '20%',
				sortable : true
			},{
				field : 'opt',
				title : 'Operation',
				formatter : function(value, row, index) {
					str = '';
					str += $.formatString('<img onclick="admin_dataManage_modelManage_editModel(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
					str += $.formatString('<img onclick="admin_dataManage_modelManage_deleteModel(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					return str;
				}
			}
			] ],
			toolbar : [ {
				iconCls : 'icon-reload',
				handler : function() {
					$("#admin_dataManage_ModelManage_from_datagrid").datagrid('reload');
				}
				},{
				text : 'New Make',
				iconCls : 'icon-add',
				handler : function() {
					admin_dateManage_ModelManage_datagrid_add();
					}
				} ]
			});
	});
	
	//--------------------------查询------------------------------------------------
	$('#admin_dataManage_ModelManage_from_search').bind('click',function(){
		$("#admin_dataManage_ModelManage_from_datagrid").datagrid('load', serializeObject($("#admin_dataManage_ModelManage_from")));
	});
	//--------------------------清空条件------------------------------------------------
	$('#admin_dataManage_ModelManage_from_clean').bind('click',function(){
		$("#admin_dataManage_ModelManage_from_make").combobox('setValue', '');
		$("#admin_dataManage_ModelManage_from_model").combobox('setValue', '');
	});
	
	//----------------------------创建新的model-----------------------------------------
	admin_dateManage_ModelManage_datagrid_add = function (){
		var d =$('<div/>').dialog({
			width:320,
			heigth:200,
			title:"Model Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/modelAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});	
		changeModelPanalPlace(d);
	};
	
	//----------------------------更改model-----------------------------------------
	admin_dataManage_modelManage_editModel = function(id){
		var d =$('<div/>').dialog({
			width:320,
			heigth:200,
			title:"Model Edit",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/dataManage/modelEdit.jsp?id="+id+"",
			onClose:function(){
				d.dialog('destroy');
			},
		});
		changeModelPanalPlace(d);
	};
	
	//----------------------------删除model-----------------------------------------
	admin_dataManage_modelManage_deleteModel = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this Model?', function (r) {  
    		if (r) { 
           	  $.ajax({
         	        type: "post",
         	        dataType: "json",
         	       	url: "modelAction!deleteModel.action",
         	        data: {"id": id},
         	        success: function (obj) {
         	        	if(obj.success){
         	        		$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
         	        	}
         	        	$("#admin_dataManage_ModelManage_from_datagrid").datagrid('reload');
         	        }
         	    });
             }  
         });
	};
	
	
	changeModelPanalPlace = function (d){
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