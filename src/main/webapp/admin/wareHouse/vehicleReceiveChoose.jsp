<%@ page language="java"  pageEncoding="UTF-8"%>

<!-- 收车之后的导向div -->
<table cellSpacing=5 cellPadding=5>
	<tr>
		<td>
			<a id="admin_wareHouse_vehicleReceiveChoose_addAnotherVehicle" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Continue to Receive</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_wareHouse_vehicleReceiveChoose_toVehicleImages" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">To Vehicle Images</a>
		</td>
	</tr>
	<tr>
		<td>
			<a id="admin_wareHouse_vehicleReceiveChoose_backWarehouse" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Back Warehouse</a>
		</td>
	</tr>
</table>

<script type="text/javascript">

	$(function(){
	    $('#admin_wareHouse_vehicleReceiveChoose_addAnotherVehicle').bind('click', function(){  
	    	$('#vehicleReceive_vehicleForm input').val("");
	    	$('#vehicleReceive_vehicleForm textarea').val("");
			$('#vehicleReceive_vehicleForm select').prop("selectedIndex", 0);   //重置所有select,选中第一项
			$('#vehicleReceive_users').combogrid('clear');
			$('#vehicleReceive_indate').datebox('clear');
			var myDate = new Date();
			$('#vehicleReceive_indate').datebox('setValue', myDate.toLocaleDateString()); //初始化入库时间
	    	$('#admin_wareHouse_vehicleReceiveChooseDiv').dialog('destroy');
	    });
	    
	    $('#admin_wareHouse_vehicleReceiveChoose_toVehicleImages').bind('click', function(){  
	    	var d = $("<div id='admin_wareHouse_vehicleImagesDiv'/>").dialog({
				width:810,
				heigth:800,
				title:"Pic Information",
				modal:true,
				href:"${pageContext.request.contextPath}/admin/wareHouse/vehiclePicManage.jsp?vin=<%= request.getParameter("vin")%>",
				onClose:function(){
					$(this).dialog('destroy');
				},
			});
			var browserHeight = $(window).height();  //游览器
			var browserwidth = $(window).width();
			var width = d.panel('options').width;//获取容器的宽
			if(browserwidth>(width+200)){
				if(browserHeight>700){
					d.panel('resize',{
						left : 450,
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
	    });
	    
	    $('#admin_wareHouse_vehicleReceiveChoose_backWarehouse').bind('click', function(){  
			var url = "${pageContext.request.contextPath}/admin/wareHouse/wareHouse.jsp";
			addTab({title:"Vehicle Manage",href:url,closable:true});
	    	$('#admin_wareHouse_vehicleReceiveChooseDiv').dialog('destroy');
	    });
	});

</script>