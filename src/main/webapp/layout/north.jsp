<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
	function layout_north_logout(){
		$.post('${pageContext.request.contextPath}/userAction!doNotNeedSessionAndSecurity_logout.action', 
			function(result) {
				if (result) {
					$.messager.show({
						title:'message',
						msg:result.msg
					});
					location.replace('${pageContext.request.contextPath}/');
			}
		}, 'json');
	}
	
	function layout_north_editPasswordFun(){
		var d =$('<div/>').dialog({
			width:400,
			heigth:200,
			title:"Password",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/menuPassword.jsp',
			buttons:[{
				text:'confirm',
				handler:function(){
					$('#admin_menuPassword_editPasswordForm').form('submit',{
						url : '${pageContext.request.contextPath}/userAction!editPassword.action',
						success : function(r) {
							var obj = $.parseJSON(r);
							if (obj.success) {
								d.dialog('close');					   							
							}
							$.messager.show({
								title : 'message',
								msg : obj.msg
							});
						}
					});
				}
			}],
			onClose:function(){
				$(this).dialog('destroy');
			}
		});
	}
	
	$(function(){
		
	});
	
	
</script>
	<div style="width:100%;height:100%; background-image: url('images/headbkgimg1.png')">
		<div id="layout_north_welcome" style="position: absolute; right: 20px; top: 8px;"> welcome </div>
	
		<div style="position: absolute; right: 0px; bottom: 0px;">
			<div style="float: left;border: 1px solid #b0b0b2">
				<div style="float: left">
					<form id="layout_north_search_form" method="post">
						<input id="layout_north_search_input" class="easyui-validatebox" laceholder="Search" placeholder="Enter Content"/>
					</form>
					
				</div>
				<div style="float: left">
					<input class="easyui-combobox" id="layout_north_searchInfo" data-options="
							valueField: 'label',
							textField: 'value',
							data: [{
								label: 'vin',
								value: 'vin'
							}]"  style="width: 80px"/>
				</div>
				<div style="padding-top: 2px;padding-left:4px; float: left">
					<img id="layout_north_search" style="height: 16px" src="images/search.png"/>
				</div>
			</div>
			<div style="float: left">
				<a href="#" class="easyui-menubutton" data-options="menu:'#layout_north_skin',iconCls:'icon-edit'">change skin</a>
				<a href="#" class="easyui-menubutton" data-options="menu:'#layout_north_profile',iconCls:'icon-cog'">config</a>
				<a href="#" class="easyui-menubutton" data-options="menu:'#layout_north_logout',iconCls:'icon-help'">logout</a>
			</div>
		</div>
		<div id="layout_north_skin" style="width:150px;">
			<div onclick="changeTheme('default')" data-options="iconCls:'icon-undo'">default</div>
			<div class="menu-sep"></div>
			<div onclick="changeTheme('gray')" data-options="iconCls:'icon-redo'">gray</div>
			<div onclick="changeTheme('metro')" data-options="iconCls:'icon-redo'">metro</div>
			<div onclick="changeTheme('cupertino')" data-options="iconCls:'icon-redo'">cupertino</div>
			<div onclick="changeTheme('dark-hive')" data-options="iconCls:'icon-redo'">dark-hive</div>
			<div onclick="changeTheme('sunny')" data-options="iconCls:'icon-redo'">sunny</div>
			<div onclick="changeTheme('pepper-grinder')" data-options="iconCls:'icon-redo'">pepper-grinder</div>
		
		</div>
		<div id="layout_north_profile" style="width:150px;">
			<div data-options="iconCls:'icon-ok'" onclick ="layout_north_editPasswordFun()">change password</div>
			<div data-options="iconCls:'icon-cancel'" style="display:none">my profile</div>
		</div>
		<div id="layout_north_logout" style="width:150px;">
			<div onclick ="layout_north_logout()">logout</div>
			<div style="display:none">change user</div>
		</div>
	</div>