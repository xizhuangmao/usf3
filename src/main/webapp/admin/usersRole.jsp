<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(function() {


		/**
		 * 获取用户角色窗口内容
		 */
		$.messager.progress({
			text : 'loading....'
		});
		$('#admin_usersRole_datagrid').datagrid({
		    url : '${pageContext.request.contextPath}/roleAction!datagrid.action',
		    fit : true,
			border : false,
			idField : 'id',
			fitColumns : true,
			rownumbers : true,
			sortName : 'name',
			sortOrder : 'desc',
			checkbox : true,
			
			columns : [ [ {
				field : 'id',
				title : 'titleid',
				width : 150,
				checkbox : true
			}, {
				field : 'name',
				title : 'role',
				width : 150,
				sortable:true
			} ] ],
			onLoadSuccess : function(node, data) {
				//获取已有的角色，打勾
				$.post('${pageContext.request.contextPath}/userAction!get.action', {
					id : admin_users_selectUser.id
				}, function(result) {
				
					if (result) {

						var userRoles = result.obj.roles;

						for (var i = 0; i < userRoles.length; i++) {
							//暂时先这么写，想到好办法再改						 						
							var rowid = $('#admin_usersRole_datagrid').parent().find("input[value='"+userRoles[i].id+"']").parent().parent().parent().attr("datagrid-row-index");
							$('#admin_usersRole_datagrid').datagrid("checkRow",rowid);
							 
						}
					}
				}, 'json');
				$.messager.progress('close');
			}
		});
		
		
	});
	

	
	</script>
	

<table id="admin_usersRole_datagrid" data-options="fit:true,border:false"></table>