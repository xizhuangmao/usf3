<%@ page language="java"  pageEncoding="UTF-8"%>

	<form id="admin_menuPassword_editPasswordForm" method="post">
		<table align=center cellSpacing=4 cellPadding=4>

			<tr>
				<th>password</th>
				<td><input id="admin_menuPassword_editPassword" name="password" type="password" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
			<tr>
				<th>confirm password</th>
				<td><input type="password"  class="easyui-validatebox" validType="eqPwd['#admin_menuPassword_editPassword']" invalidMessage="The password and confirmation password do not match" data-options="required:true"/></td>

			</tr>
		</table>
	</form>
