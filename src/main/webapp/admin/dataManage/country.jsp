<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_Country" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_Country_datagrid">
    	
    	</table>
    </div>   
</div> 

<script type="text/javascript">
$(function() {
	$("#admin_dateManage_Country_datagrid").datagrid({
		url : '${pageContext.request.contextPath}/countryAction!getCountryDatagrid.action',
		border : false,
		fit : true,
		idField : 'id',
		fitColumns : true,
		sortName : 'country',
		sortOrder : 'desc',
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ],
		
		columns : [ [ {
			field : 'country',
			title : 'Country',
			width : '20%',
			sortable : true
		},{
			field : 'shortname',
			title : 'Country Short',
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
				str += $.formatString('<img onclick="Country_datagrid_Edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
				str += $.formatString('<img onclick="Country_datagrid_Delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				return str;
			}
		}
		] ],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_dateManage_Country_datagrid").datagrid('reload');
			}
			},{
			text : 'New Country',
			iconCls : 'icon-add',
			handler : function() {
				admin_dateManage_Country_datagrid_add();
				}
			} ]
		});
	});
	
	admin_dateManage_Country_datagrid_add = function(){
		var d =$('<div/>').dialog({
			width:300,
			heigth:200,
			title:"Country Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/countryAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});		
		changeCountryPanalPlace(d);
	};
	
	//-------------------edit 界面---------------
	var Country_datagrid_Edit_id;
	Country_datagrid_Edit = function(id){
		Country_datagrid_Edit_id = id;
		var d =$('<div/>').dialog({
			width:300,
			heigth:200,
			title:"Country Edit",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/countryEdit.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});	
		changeCountryPanalPlace(d);
	};
	
	Country_datagrid_Delete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this Country?', function (r) {  
            if (r) { 
          	  $.ajax({
        	        type: "post",
        	        dataType: "json",
        	       	url: "countryAction!deleteCountry.action",
        	        data: {"id":id},
        	        success: function (obj) {
        	        	if(obj.success){
							$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							$("#admin_dateManage_Country_datagrid").datagrid('reload');
        	        	}
        	        }
        	    });
            }  
        });
	};
	
	changeCountryPanalPlace = function (d){
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

