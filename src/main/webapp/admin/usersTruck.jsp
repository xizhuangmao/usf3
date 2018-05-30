<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript">

	$(function() {


		/**
		 * 获取用户卡车窗口内容
		 */
		$.messager.progress({
			text : 'loading....'
		});
		$('#admin_usersTruck_datagrid').datagrid({
		    url : '${pageContext.request.contextPath}/truckAction!getAllTruckDataGrid.action',
		    fit : true,
			border : false,
			idField : 'id',
			fitColumns : true,
			rownumbers : true,
			sortName : 'fullname',
			sortOrder : 'desc',
			checkbox : true,
			
			columns : [ [ {
				field : 'id',
				title : 'titleid',
				width : 150,
				checkbox : true
			}, {
				field : 'fullname',
				title : 'truck',
				width : 150,
				sortable:true
			} ] ],
			onLoadSuccess : function(node, data) {
				//获取已有的卡车，打勾
				$.post('${pageContext.request.contextPath}/userAction!get.action', {
					id : admin_users_selectUser.id
				}, function(result) {
				
					if (result) {

						var userTruck = result.obj.trucks;

						for (var i = 0; i < userTruck.length; i++) {
							//暂时先这么写，想到好办法再改						 						
							var rowid = $('#admin_usersTruck_datagrid').parent().find("input[value='"+userTruck[i].id+"']").parent().parent().parent().attr("datagrid-row-index");
							$('#admin_usersTruck_datagrid').datagrid("checkRow",rowid);
							 
						}
					}
				}, 'json');
				$.messager.progress('close');
			}
		});
		
		
	});
	

	
	</script>
	

<table id="admin_usersTruck_datagrid" data-options="fit:true,border:false"></table>