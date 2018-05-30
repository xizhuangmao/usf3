<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">


	$(function() {

		/**
		 * 获取图标列表
		 */
		$('#admin_resourceForm_icon').combobox({
			data : $.iconData,
			formatter : function(v) {
				return $.formatString('<span class="{0}" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span>{1}', v.value, v.value);
			}
		});

		/**
		 * 获取上级资源树
		 */
		$('#admin_resourceForm_pid').combotree({
			url : '${pageContext.request.contextPath}/resourceAction!tree.action',
			parentField : 'pid',
			lines : true,
			panelHeight : 500,
			onLoadSuccess : function() {
				parent.$.messager.progress('close');
			}
		});

		/**
		 * 根据url不同提交表单
		 */
		$('#admin_resourceForm_form').form({
			url : admin_resourceEdit_url,	
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.success) {
					$("#admin_resource_treegrid").treegrid('reload');
					parent.$.modalDialog.handler.dialog('close');				
				}
				$.messager.show({
					title:'message',
					msg:result.msg
				});
			}
		});

		/**
		 * 获取编辑窗口内容
		 */
		if (admin_resourceEdit_id.length > 0) {
			parent.$.messager.progress({
				text : 'loading'
			});
			$.post('${pageContext.request.contextPath}/resourceAction!getById.action', {
				id : admin_resourceEdit_id,
			}, function(result) {
				var obj = result.obj;
				if (obj.name!= undefined) {
					$('#admin_resourceForm_form').form('load', {
						'id' : obj.id,
						'name' : obj.name,
						'url' : obj.url,
						'pid' : obj.pid,
						'icon' : obj.icon
					});
					$('#admin_resourceForm_icon').attr('class', obj.icon);//设置背景图标
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	
	});
	

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="admin_resourceForm_form" method="post">
			<table class="table table-hover table-condensed">
				<tr hidden="true">
					<th>resourceid</th>
					<td><input name="id" type="text"  readonly="readonly" ></td>
				</tr>
				<tr>
					<th>name</th>
					<td><input name="name" type="text" placeholder="resource name" class="easyui-validatebox span2" data-options="required:true,validType:'length[0,100]'" value="" style="width: 245px"></td>
				</tr>
				<tr>
					<th>url</th>
					<td><input name="url" type="text" placeholder="resource url" class="easyui-validatebox span2" value="" data-options="validType:'length[0,200]'" style="width: 245px"></td>
					
				</tr>
				<tr>
					<th>parent name</th>
					<td><select id="admin_resourceForm_pid" name="pid" style="width: 255px; height: 29px;"></select><img src="${pageContext.request.contextPath}/style/images/extjs_icons/cut_red.png" onclick="$('#admin_resourceForm_pid').combotree('clear');" /></td>
				</tr>
				<tr>
					<th>icon</th>
					<td colspan="3"><input id="admin_resourceForm_icon" name="icon" style="width: 255px; height: 29px;" data-options="editable:false" /></td>
				</tr>

			</table>
		</form>
	</div>
</div>