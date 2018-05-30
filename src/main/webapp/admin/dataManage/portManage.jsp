<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_PortManage" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:85px;background:#eee;">
		<form id="admin_dataManage_PortManage_from" method="post">
			<table>
				<tr>
					<td>Port :</td>
					<td><input id="admin_dateManage_PortManage_port" name="port" class="easyui-combobox"  style="width:150px;"
	    							data-options="valueField:'port',textField:'port',url:'${pageContext.request.contextPath}/portAction!getPortName.action',
	    											" /></td>
					<td>Country :</td>
					<td><input id="admin_dateManage_PortManage_country" class="easyui-combobox" name="country" style="width:150px;"
	    							data-options="valueField:'country',textField:'country',url:'${pageContext.request.contextPath}/countryAction!getCountryName.action',
	    											" /></td>
	    		</tr>
	    		<tr>
	    			<td> State: </td>
					<td><input id="admin_dateManage_PortManage_state" class="easyui-combobox" name="state" style="width:150px;"
	    							data-options="valueField:'state', editable:false,textField:'state',url:'${pageContext.request.contextPath}/stateAction!getStateName.action',
	    											" /></td>
					<td> City: </td>
					<td><input id="admin_dateManage_PortManage_city" class="easyui-combobox" name="city" style="width:150px;"
	    							data-options="valueField:'city', editable:false,textField:'city',url:'${pageContext.request.contextPath}/cityAction!getCityName.action',
	    											" /></td>
				</tr>
				<tr>
					<td><a id="admin_dateManage_PortManage_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
					<td><a id="admin_dateManage_PortManage_clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Clean</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_PortManage_datagrid">
    	
    	</table>
    </div>   
</div> 


	<script type="text/javascript">
	
			$(function() {
				$("#admin_dateManage_PortManage_datagrid").datagrid({
					url : '${pageContext.request.contextPath}/portAction!getPortDataGrid.action',
					border : false,
					fit : true,
					idField : 'id',
					fitColumns : true,
					sortName : 'port',
					sortOrder : 'desc',
					pagination : true,
					pageSize : 10,
					pageList : [ 10, 20, 50, 100 ],
					
					columns : [ [ {
						field : 'id',
						hidden: true,
					},{
						field : 'port',
						title : 'Port',
						width : '20%',
					},{
						field : 'country',
						title : 'Country',
						width : '20%',
						sortable : true
					},{
						field : 'state',
						title : 'State',
						width : '20%',
						sortable : true
					},{
						field : 'city',
						title : ' City',
						width : '20%',
						sortable : true
					},{
						field : 'opt',
						title : 'Operation',
						formatter : function(value, row, index) {
							str = '';
							str += $.formatString('<img onclick="port_datagrid_Edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
							str += $.formatString('<img onclick="port_datagrid_Delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
							return str;
						}
					}
					] ],
					toolbar : [ {
						iconCls : 'icon-reload',
						handler : function() {
							$("#admin_dateManage_PortManage_datagrid").datagrid('reload');
						}
						},{
						text : 'Port Add',
						iconCls : 'icon-add',
						handler : function() {
							admin_dateManage_port_datagrid_add();
							}
						} ]
					});
			});
			
			admin_dateManage_port_datagrid_add = function(){
				var d =$('<div/>').dialog({
					width:300,
					heigth:300,
					title:"Port Add",
					modal:true,
					href:'${pageContext.request.contextPath}/admin/dataManage/portAdd.jsp',
					onClose:function(){
						d.dialog('destroy');
					},
				});
				changePortPanalPlace(d);
			};
			
			//-------------------edit 修改 界面-------------------
			var port_datagrid_Edit_id;
			port_datagrid_Edit = function(id){
				port_datagrid_Edit_id = id;
				var d =$('<div/>').dialog({
					width:340,
					heigth:300,
					title:"Port Edit",
					modal:true,
					href:'${pageContext.request.contextPath}/admin/dataManage/portEdit.jsp',
					onClose:function(){
						d.dialog('destroy');
					},
				});
				changePortPanalPlace(d);
			};	
			
			$('#admin_dateManage_PortManage_search').bind('click',function(){
				$("#admin_dateManage_PortManage_datagrid").datagrid('load', serializeObject($("#admin_dataManage_PortManage_from")));
			});
			
			$('#admin_dateManage_PortManage_clean').bind('click',function(){
				$("#admin_dateManage_PortManage_port").combobox('setValue', '');
				$("#admin_dateManage_PortManage_country").combobox('setValue', '');
				$("#admin_dateManage_PortManage_state").combobox('setValue', '');
				$("#admin_dateManage_PortManage_city").combobox('setValue', '');
			});

			port_datagrid_Delete = function(id){
				 $.messager.confirm('Warning!', 'Are you sure to delete this Port?', function (r) {  
		              if (r) { 
		            	  $.ajax({
		          	        type: "post",
		          	        dataType: "json",
		          	       	url: "portAction!deletePort.action",
		          	        data: {"id":id},
		          	        success: function (data) {
		          	        	if(data.success){
									$.messager.show({
										title:'Message',
										msg:data.msg,
										timeout:5000,
										showType:'slide'
									});
		        	        	}
		        	        	$("#admin_dateManage_PortManage_datagrid").datagrid('reload');
		          	        }
		          	    });
		              }  
		          });
			};
		
			
			changePortPanalPlace = function (d){
				var browserHeight = $(window).height();  //游览器
				var browserwidth = $(window).width();
				var width = d.panel('options').width;//获取容器的宽
				if(browserwidth>(width+200)){
					if(browserHeight>700){
						d.panel('resize',{
							left : 150,
							top  : 150,
						});
					}else{
						d.panel('resize',{
							left : 150,
							top  : 0,
						});
					}
					
				}else{
					if(browserHeight>700){
						d.panel('resize',{
							left : 0,
							top  : 150,
						});
					}else{
						d.panel('resize',{
							left : 0,
							top  : 0,
						});
					}
				}
			};
	</script>
