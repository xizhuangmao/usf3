<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
	var admin_servicesManage_addServices;//全局变量添加services div
	var admin_servicesManage_editServices;
	$(function(){
		$("#admin_services_serviceManage_datagrid").datagrid({
			url:"servicesAction!findServicesDatagrid.action",
			border:false,
			fitColumns:true,
			pagination:true,
			pageSize:10,
			pageList:[10,20,50,100],
			idField:'id',
			sortName:'indate',
			sortOrder:'desc',
			checkOnSelect:false,
			selectOncheck:false,
			fit:true,
			columns:[[{
				field:'id',
				title:'',
				hidden:true,
				},{
				field:'name',
				title:'Name',
				sortable:true,
				width : '300px',
				},{
				field:'fullname',
				title:'Company',
				sortable:true,
				width : '200px',
				},{
				field:'price',
				title:'Price',
				width : '100px',
				},{
				field:'note',
				title:'Note',
				width : '100px',
				formatter : function(value, row, index){
					return "<a title=\""+row.note+"\" class=\"easyui-tooltip\">"+row.note+"</a>";
				}
				},{
				field:'scoreTimes',
				title:'ScoreTimes',
				width : '50px',
				},{
				field:'scoreTotle',
				title:'ScoreTotle',
				width : '50px',
				},{
				field:'discount',
				title:'Discount',
				width : '50px',
				},{
				field:'indate',
				title:'Indate',
				sortable:true,
				width : '140px',
				},{
				field:'active',
				title:'Active',
				width : '40px',
				formatter : function(value, row, index){
					if(row.active == '1'){
						return "<div onClick=\"admin_services_serviceManage_active('"+row.id+"', '"+row.active+"')\" style=\"width:24px;height:24px\"><img src=\"images/yes.png\"/></div>";
					}else if(row.active == '0'){
						return "<div onClick=\"admin_services_serviceManage_active('"+row.id+"', '"+row.active+"')\" style=\"width:24px;height:24px\"><img src=\"images/noactive.png\"/></div>";
					}
				}
				},{
				field:'opt',
				title:'Operation',
				formatter : function(value, row, index){
					var str = "";

            		str += $.formatString('<img onclick="admin_services_serviceManage_editService(\'{0}\');" src="{1}" title="edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
            		if(row.type != '1'){
            			str += $.formatString('<img onclick="admin_services_serviceManage_deleteService(\'{0}\');" src="{1}" title="delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/deletepic.png');
            		}
            		return str;

				}
			}]],
			toolbar:[{
				text : 'add',
				iconCls : 'icon-add',
				handler : function() {
					admin_services_serviceManage_service_add();
				}
			},{
				text : 'refresh',
				iconCls : 'icon-reload',
				handler : function() {
					$("#admin_services_serviceManage_datagrid").datagrid('reload');
				}
			}],
			onClickCell: function (rowIndex, field, value) {
				IsCheckFlag = false;
			},
			onSelect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#admin_services_serviceManage_datagrid").datagrid("unselectRow", rowIndex);
				}
			},                    
			onUnselect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#admin_services_serviceManage_datagrid").datagrid("selectRow", rowIndex);
				}
			}
		});
	});
	
	admin_services_serviceManage_deleteService = function(id){
		$.messager.confirm('confirm', 'delete the service?', function(r){
			if (r){
				$.ajax({
					url : 'servicesAction!deleteServices.action',
					data : {"id" : id},
					dataType : 'text',
					type : 'post',
					success : function(data){
						var obj = $.parseJSON(data);
						if(obj.success == true){
							$.messager.show({
								title:'Message',
								msg:obj.msg,
								timeout:5000,
								showType:'slide'
							});
							$("#admin_services_serviceManage_datagrid").datagrid('reload');
						}else{
							$.messager.alert('warning', obj.msg);
						}
					}
				});
			}else{}
		});
	};
	
	admin_services_serviceManage_editService = function(id){
		admin_servicesManage_editServices =$('<div/>').dialog({
			width:600,
			heigth:460,
			title:"edit services",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/services/editService.jsp?id="+id+"",
			onClose:function(){
				admin_servicesManage_editServices.dialog('destroy');
			},
		});
					
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = admin_servicesManage_editServices.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				admin_servicesManage_editServices.panel('resize',{
					left : 500,
					top  : 250,
				});
			}else{
				admin_servicesManage_editServices.panel('resize',{
					left : 150,
					top  : 0,
				});
			}
						
		}else{
			if(browserHeight>700){
				admin_servicesManage_editServices.panel('resize',{
					left : 0,
					top  : 10,
				});
			}else{
				admin_servicesManage_editServices.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
	
	admin_services_serviceManage_service_add = function(){
		admin_servicesManage_addServices =$('<div/>').dialog({
			width:600,
			heigth:460,
			title:"add services",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/services/addService.jsp",
			onClose:function(){
				admin_servicesManage_addServices.dialog('destroy');
			},
		});
					
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = admin_servicesManage_addServices.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				admin_servicesManage_addServices.panel('resize',{
					left : 500,
					top  : 250,
				});
			}else{
				admin_servicesManage_addServices.panel('resize',{
					left : 150,
					top  : 0,
				});
			}
						
		}else{
			if(browserHeight>700){
				admin_servicesManage_addServices.panel('resize',{
					left : 0,
					top  : 10,
				});
			}else{
				admin_servicesManage_addServices.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
	
	function admin_services_serviceManage_active(id, active){
		$.ajax({
			url:'servicesAction!updateServices.action',
			data:{"id" : id, "active" : active},
			dataType:'text',
			type:'post',
			success:function(data){
				var obj = $.parseJSON(data);
				if(obj.success){
					var serviceIndex = $("#admin_services_serviceManage_datagrid").datagrid('getRowIndex', id);
					$("#admin_services_serviceManage_datagrid").datagrid('updateRow', {
						index: serviceIndex,
						row: {
							active: obj.obj.active,
						}
					});
				}
			}
		});
	};
	
	$("#admin_services_service_search").click(function(){
		$("#admin_services_serviceManage_datagrid").datagrid('load', serializeObject($("#admin_services_serviceManage_form")));
	});
	
	$.ajax({
		url : "companyAction!findCompany.action",
		type : "post",
		dataType : "text",
		success : function(r){
			var obj = $.parseJSON(r);
			obj.unshift({id:'ALL',fullname:'ALL'});
        	$("#admin_services_service_whes").combobox({
				valueField: 'id',    
       			textField: 'fullname',
       			value: 'ALL',
        		data: obj,
        	});
		}
	});
</script>
<div class="easyui-layout" fit="true"> 
	<div data-options="region:'north',title:'search',split:true" style="height: 122px;border: false">
		<form id="admin_services_serviceManage_form" name="form" class="datagrid-toolbar" method="post">
			<div style="width: 980px">
				<table id="admin_services_serviceManage_table">
					<tr>
						<th>Company</th>
						<td><input id="admin_services_service_whes" class="easyui-combobox" name="companyId" style="width: 220px"/></td>
					</tr>
					<tr>
						<th>Services</th>
						<td><input id="admin_services_service_services" class="easyui-validatebox" name="name" style="width: 215px"/></td>
					</tr>
					<tr>
						<td><a id="admin_services_service_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a></td>
					</tr>
				</table>
			</div>
		</form>
	</div>  
	<div data-options="region:'center',border:false" >
		<table id="admin_services_serviceManage_datagrid"></table>
	</div>    				
</div>
