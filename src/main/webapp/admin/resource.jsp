<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	var treeGrid;
	var admin_resourceEdit_id;
	var admin_resourceEdit_url;
	treeGrid = $('#admin_resource_treegrid').treegrid({
	    url : '${pageContext.request.contextPath}/resourceAction!treegrid.action',
	    idField : 'id',
	    treeField : 'name',
	    parentField : 'pid',
		rownumbers : true,
		fitColumns : true,
		fit : true,
		border : false,
		
		columns : [ [ {
			field : 'name',
			title : 'name',
			width : 150
		},{
			field : 'icon',
			title : 'icon',
			width : 150
		}, {
			field : 'url',
			title : 'url',
			width : 150
		}, {
			field : 'pid',
			title : 'pid',
			width : 150,
			hidden:true
		}, {
			field : 'action',
			title : 'edit',
			width : 50,
			formatter : function(value, row, index) {
				var str = '';
				
				str += $.formatString('<img onclick="admin_resource_editFun(\'{0}\');" src="{1}" title="edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
				
				str += '&nbsp;';
				
				str += $.formatString('<img onclick="admin_resource_deleteFun(\'{0}\');" src="{1}" title="delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				
				return str;
			}
		} ] ],
		toolbar : [{
			text:'add',
			iconCls:'icon-add',
			handler:function(){
				admin_resource_addFun();
			}
		},'-',{
			text : 'open',
			iconCls:'icon-redo',
			handler:function(){
				treeGrid.treegrid('expandAll');
				
			}
		},{
			text : 'close',
			iconCls:'icon-undo',
			handler:function(){
				treeGrid.treegrid('collapseAll');
				
			}
		},'-',{
			text : 'refresh',
			iconCls:'icon-reload',
			handler:function(){
				treeGrid.treegrid('reload');
			}
		},'-']
	});
	
	function admin_resource_deleteFun(id) {
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.messager.confirm('message', 'confirm the deletion?', function(r) {
				if (r) {
					parent.$.messager.progress({
						title : 'message',
						text : 'Submitting.Please wait'
					});
					$.post('${pageContext.request.contextPath}/resourceAction!delete.action', {
						id : node.id
					}, function(result) {
						if (result.success) {
							parent.$.messager.alert('message', result.msg, 'info');
							treeGrid.treegrid('reload');
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
	}

	function admin_resource_editFun(id) {
	    admin_resourceEdit_id = id;
	    admin_resourceEdit_url = '${pageContext.request.contextPath}/resourceAction!edit.action';
		if (id != undefined) {
			treeGrid.treegrid('select', id);
		}
		var node = treeGrid.treegrid('getSelected');
		if (node) {
			parent.$.modalDialog({
				title : 'resource edit',
				width : 500,
				height : 300,
				href : '${pageContext.request.contextPath}/admin/resourceForm.jsp',
				buttons : [ {
					text : 'edit',
					handler : function() {
						var f = parent.$.modalDialog.handler.find('#admin_resourceForm_form');
						f.submit();
					}
				} ]
			});
		}
	}

	function admin_resource_addFun() {
		admin_resourceEdit_id='';
		admin_resourceEdit_url = '${pageContext.request.contextPath}/resourceAction!add.action';
		parent.$.modalDialog({
			title : 'resource add',
			width : 500,
			height : 300,
			href : '${pageContext.request.contextPath}/admin/resourceForm.jsp',
			buttons : [ {
				text : 'add',
				handler : function() {
					var f = parent.$.modalDialog.handler.find('#admin_resourceForm_form');
					f.submit();
				}
			} ]
		});
	}
	
	
</script>



<table id="admin_resource_treegrid" data-options="fit:true,border:false"></table>





