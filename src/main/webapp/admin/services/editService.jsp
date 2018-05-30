<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script type="text/javascript">
	//var editService_subserverId;
	$(function(){
 		$('#admin_services_editService_company').combobox({
			valueField:'id',    
			textField:'fullname',
			url:'${pageContext.request.contextPath}/companyAction!findCompany.action',
			onSelect: function(param){
				findServicesBywhesnvocc(param.id);
			}
		}),
		$.ajax({
			url : 'servicesAction!findServicesById.action',
			data : {"id" : '<%= request.getParameter("id")%>'},
			dataType : 'text',
			type : 'post',
			success : function(data){
				var obj = $.parseJSON(data);
				findServicesBywhesnvocc(obj.companyId);
				$("#admin_services_editService_form").form('load', obj);
				if(obj.hasOwnProperty("ids")){
					$("#admin_services_editService_subserver").combogrid("setValues", Trim(obj.ids, 'g'));
				}
				//editService_subserverId = obj.id;
			}
		});
	});
	
	$('#admin_services_editService_search').linkbutton({
   		iconCls: 'icon-search'   
	});
	
	$('#admin_services_editService_inp').bind('input propertychange',function(){    
         var name = $("#admin_services_editService_inp").val();
         $("#admin_services_editService_subserver").combogrid('grid').datagrid('load', {"name" : name});
    });
    
    $("#admin_services_editService_form").form({
  		url:"servicesAction!updateServices.action",
  		success : function(data){
    		var obj = $.parseJSON(data);
    		if(obj.success == true){
    			$.messager.show({
					title:'Message',
					msg:obj.msg,
					timeout:5000,
					showType:'slide'
				});
    		}
    		$("#admin_services_serviceManage_datagrid").datagrid('reload');
    		admin_servicesManage_editServices.dialog('destroy');			
    	}
  	});
  	
    $("#admin_services_editService_submit").click(function(){
		$("#admin_services_editService_form").submit();
    });
    
    function Trim(str,is_global){
		var result;
		result = str.replace(/(^\s+)|(\s+$)/g,"");
		if(is_global.toLowerCase()=="g"){
			result = result.replace(/\s/g,"");
		}
		return result;
	}
	
	function findServicesBywhesnvocc(companyId){
		$("#admin_services_editService_subserver").combogrid({
			url:"servicesAction!findServicesBywhesnvocc.action?companyId="+companyId+"",
			panelWidth : 500,
			panelHeight : 240,
			idField : 'id',
			textField : 'name',
			toolbar : '#admin_services_editService_toolbar',
			multiple : true,
			fitColumns : true,
			editable:false,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			sortName : 'name',
			sortOrder : 'asc',
			columns: [[{
				field:'id',
				title:'编号',
				hidden:true
			},{
				field:'name',
				title:'name',
				width:100,
				sortable:true,
			}]],
					
			/* onLoadSuccess:function(data){ 
				var serverId = $("#admin_services_editService_subserver").combogrid('grid').datagrid('getRowIndex', editService_subserverId);
				$("#admin_services_editService_subserver").combogrid('grid').datagrid('deleteRow', serverId);
			} */
		});
	}
</script>
<div style="width: 360px;height: 460px">
	<form id="admin_services_editService_form" style="width: 600;padding-left: 10px;padding-top: 10px;line-height: 3em" method="post">
		<table>
			<tr>
				<td><input name="id" type="hidden"/></td>
			</tr>
			<tr>
				<th>company:</th>
				<td><input id="admin_services_editService_company" name="companyId" style="width: 400px" data-options="editable:false" /></td>
			</tr>
			<tr>
				<th>service:</th>
				<td><textarea id="admin_services_editService_name" name="name" style="width: 395px;height: 55px;resize: none" class="easyui-validatebox" data-options="required:true,validType:'length[0,100]'" /></textarea>
			</tr>
			<tr>
				<th>contactServices:</th>
				<td>
					<div id="admin_services_editService_toolbar" class="datagrid-toolbar" data-options="region:'north',title:'search',split:true" style="height: 25px">
						name:<input id="admin_services_editService_inp" style="width: 160px"/>
					</div>
					<input id="admin_services_editService_subserver" class="easyui-combogrid" data-options="editable:false,toolbar : '#admin_services_editService_toolbar'" name="ids" style="width: 400px"/>
				</td>
			</tr>
			<tr>
				<th>price:</th>
				<td><input id="admin_services_editService_price" name="price" style="width: 395px" class="easyui-validatebox" data-options="validType:['moreThanZero[0]', 'length[0,20]']" /></td>
				<td><span style="color: #898b8d">$</span></td>
			</tr>
			<tr>
				<th>discount:</th>
				<td><input id="admin_services_editService_discount" name="discount" style="width: 395px" class="easyui-validatebox" data-options="validType:['moreLength[0,100]', 'length[0,10]']" /></td>
				<td><span style="color: #898b8d">%</span></td>
			</tr>
			<tr>
				<th>active:</th>
				<td><input style="width: 400px" class="easyui-combobox" name="active"
					data-options="valueField: 'value',editable:false,textField: 'label',data: [{label: 'Yes',value: '1'},{label: 'No',value: '0',selected:true}]">
				</td>
			</tr>
			<tr>
				<th>note:</th>
				<td><textarea id="admin_services_editService_note" name="note" style="width: 395px;height: 100px;resize: none" class="easyui-validatebox" data-options="validType:'length[0,200]'"></textarea></td>
			</tr>
			<tr>
				<th></th>
				<td><input id="admin_services_editService_submit" type="button" value="submit"/></td>
			</tr>
		</table>
	</form>
</div>
