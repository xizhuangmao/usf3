<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
		<div id="admin_dateManage_Model_ModelEidt" class="easyui-layout" data-options="fit:true" style="width:300px;height:200px;">   
		    <div data-options="region:'center',border:0" style="padding:5px;background:#eee;">
		    	<form id="admin_dateManage_Model_ModelEidt_from" method="post">
			    	<table>
			    		<tr>
			    			<td><input name="id" hidden="true"/></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Make:</td>
			    			<td><input id="admin_dateManage_Model_ModelEidt_from_make" class="easyui-combobox" name="makeId" value="" style="width:150px;"
	    							data-options="required:true,editable:false,valueField:'id', textField:'make',url:'${pageContext.request.contextPath}/makeAction!getMakeName.action',
	    											" /></td>
			    		</tr>
			    		<tr>
			    			<td><span style="color: red">*</span>Model:</td>
			    			<td><input id="admin_dateManage_Model_ModelEidt_from_model" name="model" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'"/></td>
			    		</tr>
						<tr>
							<td></td>
							<td><a id="admin_dateManage_Model_ModelEidt_from_save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
						</tr>    	
			    	</table>
		    	</form>
		    </div>   
		</div>  
		
	<script type="text/javascript">
		$(function(){
			$.ajax({
		        type: "post",
		        dataType: "json",
		        url: "modelAction!findModelById.action",
		        data: {"id": '<%= request.getParameter("id")%>'},
		        success: function (obj) {
		        	if(obj.success){
		        		$("#admin_dateManage_Model_ModelEidt_from").form('load', obj.obj);
		        	}
		        }
		    });
			
		});
		
		$("#admin_dateManage_Model_ModelEidt_from").form({
			url:'modelAction!editModel.action',
			success:function(data){
				var obj = $.parseJSON(data);
				if(obj.success){
					$.messager.show({
						title:'Message',
						msg:obj.msg,
						timeout:5000,
						showType:'slide'
					});
				}
				$("#admin_dataManage_ModelManage_from_datagrid").datagrid('reload');
			}
		});
		
		$("#admin_dateManage_Model_ModelEidt_from_save").bind('click',function(){
			$("#admin_dateManage_Model_ModelEidt_from").submit();
		});
	</script>		
		
		