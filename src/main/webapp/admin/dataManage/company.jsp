<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_company" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_company_datagrid">
    	</table>
    </div>   
</div> 


<script type="text/javascript">
$(function() {
	$("#admin_dateManage_company_datagrid").datagrid({
		url : '${pageContext.request.contextPath}/companyAction!getCompanyDataGrid.action',
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
			field : 'fullname',
			title : 'Fullname',
			width : '10%',
			sortable : true
		},{
			field : 'shortname',
			title : 'Shortname',
			width : '10%',
			sortable : true
		},{
			field : 'country',
			title : 'Country',
			width : '10%',
			sortable : true
		},{
			field : 'state',
			title : 'State',
			width : '10%',
			sortable : true
		},{
			field : 'city',
			title : 'City',
			width : '10%',
			sortable : true
		},{
			field : 'address',
			title : 'Address',
			width : '10%',
			sortable : true
		},{
			field : 'telephone',
			title : 'Telephone',
			width : '10%',
			sortable : true
		},{
			field : 'note',
			title : 'Note',
			width : '10%',
			sortable : true
		},{
			field : 'email',
			title : 'Email',
			width : '10%',
			sortable : true
		},{
			field : 'opt',
			title : 'Operation',
			formatter : function(value, row, index) {
				str = '';
				str += $.formatString('<img onclick="admin_dateManage_company_edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
				str += $.formatString('<img onclick="admin_dateManage_company_delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				return str;
			}
		}
		] ],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_dateManage_company_datagrid").datagrid('reload');
			}
			},{
			text : 'New Company',
			iconCls : 'icon-add',
			handler : function() {
				admin_dateManage_company_datagrid_add();
				}
			} ]
		});
	});

	admin_dateManage_company_datagrid_add = function(){
		var d =$('<div/>').dialog({
			width:400,
			heigth:400,
			title:"Company Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/companyAdd.jsp',
			onClose:function(){
				$("#admin_dateManage_company_datagrid").datagrid('reload');
				d.dialog('destroy');
			},
		});		
		changeCountryPanalPlace(d);
	};
	
	var admin_dateManage_company_editId;
	admin_dateManage_company_edit = function(id){
		admin_dateManage_company_editId = id;
		var d =$('<div/>').dialog({
			width:400,
			heigth:540,
			title:"Company Edit",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/companyEdit.jsp',
			onClose:function(){
				$("#admin_dateManage_company_datagrid").datagrid('reload');
				d.dialog('destroy');
			},
		});	
		changeCountryPanalPlace(d);
	};
	
	admin_dateManage_company_delete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this Company?', function (r) {  
            if (r) { 
          		$.ajax({
        	        type: "post",
        	        dataType: "json",
        	       	url: "companyAction!deleteCompanyById.action",
        	        data: {"id":id},
        	        success: function (data) {
        	        	if(data.success){
        	        		$.messager.show({
								title:'Message',
								msg:data.msg,
								timeout:5000,
								showType:'slide'
							});
        	        		$("#admin_dateManage_company_datagrid").datagrid('reload');
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

