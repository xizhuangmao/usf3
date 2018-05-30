<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	var admin_role_dataGrid;
	var admin_role_editId;
	var admin_role_editUrl;
	admin_role_dataGrid = $('#admin_role_datagrid').datagrid({
	    url : '${pageContext.request.contextPath}/roleAction!datagrid.action',
	    fit : true,
		border : false,
		idField : 'id',
		pagination : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 50 ,100],
		rownumbers : true,
		sortName : 'name',
		sortOrder : 'desc',
		singleSelect:true,

		
		frozenColumns : [ [ {
			field : 'id',
			title : 'id',
			width : 150,
			hidden:true
		}, {
			field : 'name',
			title : 'name',
			width : 150,
			sortable:true
		} ] ],
		columns : [ [ {
			field : 'action',
			title : 'manage',
			width : 50,
			formatter : function(value, row, index) {
				var str = '';
				
				str += $.formatString('<img onclick="admin_role_editFun(\'{0}\');" src="{1}" title="edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
				
				str += '&nbsp;';
				
				str += $.formatString('<img onclick="admin_role_deleteFun(\'{0}\');" src="{1}" title="delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				
				return str;
			}
		} ] ],
		toolbar: [{
			text:'add',
			iconCls:'icon-add',
			handler:function(){
				admin_role_addFun();
			}
		},'-',{
			text : 'refresh',
			iconCls:'icon-reload',
			handler:function(){
				admin_role_dataGrid.datagrid('reload');
			}
		},'-']
	});
	
	function admin_role_deleteFun(deleteId) {

			$.messager.confirm('confirm', 'delete the role？', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : 'message',
						text : 'Submitting.Please wait'
					});
					$.post('${pageContext.request.contextPath}/roleAction!delete.action', {
						id : deleteId
					}, function(result) {
						if (result.success) {
							admin_role_dataGrid.datagrid('reload');
						}
						parent.$.messager.progress('close');
						$.messager.show({
							title:'message',
							msg:result.msg
						});
					}, 'JSON');
				}
			});
	}

	function admin_role_editFun(id) {
		admin_role_editId = id;
		admin_role_editUrl = '${pageContext.request.contextPath}/roleAction!edit.action';

		$.modalDialog({
				title : 'Edit role',
				width : 500,
				height : 300,
				href : '${pageContext.request.contextPath}/admin/roleForm.jsp',
				buttons : [ {
					text : 'edit',
					handler : function() {
						admin_role_formsubmit();
					}
				} ]
		});
		
	}

	function admin_role_addFun() {
		admin_role_editId = '';
		admin_role_editUrl = '${pageContext.request.contextPath}/roleAction!add.action';
		$.modalDialog({
			title : 'Add role',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/admin/roleForm.jsp',
			buttons : [ {
				text : 'add',
				handler : function() {
					admin_role_formsubmit();
				}
			} ]
		});
	}
	
	
	function admin_role_formsubmit(){
		//先组装资源ids
		var admin_role_ids = '';
		//由于1.3.1不支持最新方法，先分开获取，再组合
		var nodes = $('#admin_roleForm_resource').tree('getChecked','indeterminate');
		var nodes2 = $('#admin_roleForm_resource').tree('getChecked','checked');
		$.merge(nodes, nodes2);
		for (i=0;i<nodes.length;i++){
	
				admin_role_ids+=nodes[i].id+',';
			
		}

		//再根据url不同提交表单
		$.post(admin_role_editUrl, {
			id : admin_role_editId, name:$("#admin_roleForm_name").val(),ids:admin_role_ids
		}, function(result) {

			$.messager.progress('close');

			if (result.success) {
				admin_role_dataGrid.datagrid('reload');
				$.modalDialog.handler.dialog('close');				
			}
			$.messager.show({
				title:'message',
				msg:result.msg
			});
		}, 'JSON');
	}
	
</script>



<table id="admin_role_datagrid" data-options="fit:true,border:false"></table>





