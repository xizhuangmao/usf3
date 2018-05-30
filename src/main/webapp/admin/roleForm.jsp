<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">
	$(function() {

	

			/**
			 * 获取角色授权窗口内容
			 */
			$.messager.progress({
				text : 'loading'
			});
			$('#admin_roleForm_resource').tree({
				url : '${pageContext.request.contextPath}/resourceAction!tree.action',
				parentField : 'pid',
				checkbox : true,
				formatter : function(node) {
					return node.text;
				},
				onLoadSuccess : function(node, data) {
					if (admin_role_editId != ''){
						
						$("#admin_roleForm_id").val(admin_role_editId);

						$("#admin_roleForm_name").val(admin_role_dataGrid.datagrid('getSelected').name);
						
						$.post('${pageContext.request.contextPath}/roleAction!get.action', {
							id : admin_role_editId
						}, function(result) {
							if (result) {
								
								var resourceIds =result.ids.split(",");
			
								for (var i = 0; i < resourceIds.length; i++) {
									var node = $('#admin_roleForm_resource').tree('find', resourceIds[i]);
				
									if (node) {
										var isLeaf = $('#admin_roleForm_resource').tree('isLeaf', node.target);
										if (isLeaf) {
											$('#admin_roleForm_resource').tree('check', node.target);
										}
									}
								}
							}
						}, 'json');
						
					}
					$.messager.progress('close');
				}
			});
		
	
	});
	

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" >
		<form id="admin_roleForm_form" method="post">
			<table class="table table-hover table-condensed">
				<tr hidden="true">
					<th>role id</th>
					<td><input id="admin_roleForm_id" name="id" type="text"  readonly="readonly" ></td>
				</tr>
				<tr>
					<th>name</th>
					<td><input id="admin_roleForm_name" name="name" type="text" placeholder="role name" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<th >resource</th>
					<td ><div id="admin_roleForm_resource" ></div></td>
				</tr>
				<tr>
					<th></th>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</div>