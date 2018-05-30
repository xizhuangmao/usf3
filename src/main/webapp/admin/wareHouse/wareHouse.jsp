<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var admin_wareHouse_wareHouse_editDiv;
var ct=null;
var admin_wareHouse_wareHouse_datagrid_url;
$(function(){
	if(layout_north_searchInfo_busyUrl == 1){
		layout_north_searchInfo_busyUrl = 0;
		admin_wareHouse_wareHouse_datagrid_url = 'warehouseAction!getAlertWhesdtlDatagrid.action?vin=' + "<%= request.getParameter("vin")%>";
	}else{
		admin_wareHouse_wareHouse_datagrid_url = 'warehouseAction!getAlertWhesdtlDatagrid.action';
	};
	$("#admin_wareHouse_wareHouse_datagrid").datagrid({
		url:admin_wareHouse_wareHouse_datagrid_url,
		border:false,
		fitColumns:true,
		pagination:true,
		pageSize:10,
		pageList:[10,20,50,100],
		idField:'id',
		sortName:'indate',
		sortOrder:'desc',
		checkOnSelect:false,
		selectOncheck:false,
		fit:true,
		columns:[[{
			field:'id',
			title:'',
			hidden:true,
		},{
			field:'pic',
			title:'Image',
			formatter : function(value,row,index){
				var str = "";
     			if(value!="" && value!=null){
    				str = "<div onclick=\"findWareHouseVehicleInfo('" + row.vin + "')\"><img style=\"width:70px;height:50px\" src=\"warehouseAction!showWhesdtlPics.action?path="+row.pic+"\"/></div>";
                    return str;
                }else{
                    str = "<div style=\"width:70px;height:50px\" align=\"center\" onclick=\"findWareHouseVehicleInfo('" + row.vin + "')\"><img style=\"width:25px;height:25px;margin-top:13px\" src=\"images/addPics.png\"/></div>";
                    return str;
                }
			}
		},{
			field:'vin',
			title:'Vin',
			sortable:true,
			formatter : function(value,row,index){
				return "<a id=\""+row.vin+"\" onMouseOver=\"showVehicleMemo('"+row.vin+"')\" onMouseOut=\"clearMemoTime()\" style=\"cursor:pointer\" onclick=\"admin_wareHouse_wareHouse_vehicleMemo('" + row.id + "')\">"+value+"</a>";
			}
		},{
			field:'make',
			title:'Make',
		},{
			field:'model',
			title:'Model',
		},{
			field:'year',
			title:'Year',
		},{
			field:'color',
			title:'Color',
		},{
			field:'engine',
			title:'Engine#',
		},{
			field:'keynum',
			title:'Key',
		},{
			field:'users',
			title:'Customer',
			sortable:true,
		},{
			field:'sticker',
			title:'Sticker',
		},{
			field:'indate',
			title:'In&nbsp;Date',
			sortable:true,
		},{
			field:'booknum',
			title:'Booking#',
		},{
			field:'titlestate',
			title:'Title',
			formatter : function(value, row, index){
				if(value == "0" || value == null){
					return "Not Received";
				}else if(value == "1"){
					return "Received";
				}else if(value == "2"){
					return "To Customs";
				}else if(value == "3"){
					return "Customs Back";
				}else if(value == "4"){
					return "Back To Customer";
				}
			}
		},{
			field:'billstate',
			title:'Bill',
			formatter : function(value, row, index){
				if(value == "0" || value == null){
					return "Not Received";
				}else if(value == "1"){
					return "Received";
				}else if(value == "2"){
					return "To Customs";
				}else if(value == "3"){
					return "Customs Back";
				}else if(value == "4"){
					return "Back To Customer";
				}
			}
		},{
			field:'proofstate',
			title:'Proff',
			formatter : function(value, row, index){
				if(value == "0" || value == null){
					return "Not Received";
				}else if(value == "1"){
					return "Received";
				}else if(value == "2"){
					return "To Customs";
				}else if(value == "3"){
					return "Customs Back";
				}else if(value == "4"){
					return "Back To Customer";
				}
			}
		},{
			field:'opt',
			title:'Operation',
			formatter : function(value, row, index){
				var str = "";

            	str += $.formatString('<img onclick="admin_wareHouse_wareHouse_editwarehouse(\'{0}\',\'{1}\');" src="{2}" title="edit"/>', row.vin, row.id, '${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/icons/pencil.png');
            	
            	str += $.formatString('<img onclick="admin_wareHouse_wareHouse_resalevehicle(\'{0}\');" src="{1}" title="edit"/>', row.id, '${pageContext.request.contextPath}/jslib/jquery-easyui-1.4.4/themes/icons/remove1.png');
            	           				
            	return str;
			}
		}]],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_wareHouse_wareHouse_datagrid").datagrid('reload');
			}
		} ],
		onLoadSuccess:function(data){					
			//初始化渲染
			for(var i=0;i<data.rows.length;i++){
				$("#"+data.rows[i].vin+"").tooltip({
					position: 'right',
					content: "<span></span>",
					showDelay:700,
					onShow: function(){        
						$(this).tooltip('tip').css({            
							backgroundColor: 'white',            
							borderColor: '#666'        
						});    
					}
				});
			}
		},
		onClickCell: function (rowIndex, field, value) {
			IsCheckFlag = false;
		},
		onSelect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_wareHouse_wareHouse_datagrid").datagrid("unselectRow", rowIndex);
			}
		},                    
		onUnselect: function (rowIndex, rowData) {
			if (!IsCheckFlag) {
				IsCheckFlag = true;
				$("#admin_wareHouse_wareHouse_datagrid").datagrid("selectRow", rowIndex);
			}
		},			 	
	});

	admin_wareHouse_wareHouse_resalevehicle = function(id,users){
		var d =$('<div/>').dialog({
			width:400,
			heigth:200,
			title:"resale",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/wareHouse/vehicleResale.jsp?id="+id+"",
			onClose:function(){
				d.dialog('destroy');
			},
		});
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = d.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				d.panel('resize',{
					left : 500,
					top  : 250,
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
					top  : 10,
				});
			}else{
				d.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
			
	admin_wareHouse_wareHouse_editwarehouse = function(vin,id){
		admin_wareHouse_wareHouse_editDiv =$('<div/>').dialog({
			width:930,
			heigth:800,
			title:"edit vehicle",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/wareHouse/vehicleEdit.jsp?vin="+vin+"&id="+id+"",
			onClose:function(){
				admin_wareHouse_wareHouse_editDiv.dialog('destroy');
			},
		});
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = admin_wareHouse_wareHouse_editDiv.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				admin_wareHouse_wareHouse_editDiv.panel('resize',{
					left : 250,
					top  : 10,
				});
			}else{
				admin_wareHouse_wareHouse_editDiv.panel('resize',{
					left : 150,
					top  : 0,
				});
			}
		}else{
			if(browserHeight>700){
				admin_wareHouse_wareHouse_editDiv.panel('resize',{
					left : 0,
					top  : 10,
				});
			}else{
				admin_wareHouse_wareHouse_editDiv.panel('resize',{
					left : 0,
					top  : 0,
				});
			}
		}				
	};
			
	$.ajax({
		url : "warehouseAction!findAllWhesMake.action",
		type : "post",
		dataType : "text",
		success : function(r){
			var obj = $.parseJSON(r);
			obj.customer.unshift({id:'ALL',fullname:'ALL'});
			$("#admin_wareHouse_wareHouse_users").combobox({
				valueField: 'fullname',    
       			textField: 'fullname',
       			value: 'ALL',
        		data: obj.customer,
        	});
        	obj.whes.unshift({id:'ALL',fullname:'ALL'});
        	$("#admin_wareHouse_wareHouse_whes").combobox({
				valueField: 'fullname',    
       			textField: 'fullname',
       			value: 'ALL',
        		data: obj.whes,
        	});
		}
	});
			
	//Year
	for(var i=2001;i<2021;i++){
		$("#admin_wareHouse_wareHouse_year").append("<option value='"+i+"'>"+i+"</option>");
	}
			
	$("#admin_wareHouse_wareHouse_search").click(function(){
		$("#admin_wareHouse_wareHouse_datagrid").datagrid({url:'warehouseAction!getAlertWhesdtlDatagrid.action',queryParams:serializeObject($("#admin_wareHouse_wareHouse_form"))});
	});
			
	admin_wareHouse_wareHouse_vehicleMemo = function(id){
		var d = $("<div id='admin_wareHouse_vehicleMemoDiv'/>").dialog({
			title: 'vehicle memo',
			width: 920,
			height: 600,
			href: "${pageContext.request.contextPath}/admin/memo/vehicleMemo.jsp?id="+id+"",
			modal: true,
			onClose:function(){
				$(this).dialog('destroy');
			}
		});
		var browserHeight = $(window).height();  //游览器
		var browserwidth = $(window).width();
		var width = d.panel('options').width;//获取容器的宽
		if(browserwidth>(width+200)){
			if(browserHeight>700){
				d.panel('resize',{
					left : 350,
					top  : 150,
				});
			}else{
				d.panel('resize',{
					left : 410,
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
			
	findWareHouseVehicleInfo = function(value){
		var d =$('<div/>').dialog({
			width:840,
			heigth:800,
			title:"Pic Information",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/wareHouse/vehiclePicManage.jsp?vin="+value+"",
			onClose:function(){
				d.dialog('destroy');
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
	};
});

	function clearMemoTime(){
		window.clearTimeout(ct);
	}
		
	function showVehicleMemo(vin){
		ct=window.setTimeout(function(){
		$.ajax({
			url:'memoAction!findVehicleMemoByVin.action',
			data:{"vin":vin},
			dataType:'text',
			type:'post',
			success:function(value){
				var obj = $.parseJSON(value);
				var conn="";
				if(obj.length == 0){
					$("#"+vin+"").tooltip({   
						position: 'right',    
						content: "<span>Please add the memo</span>",
						onShow: function(){      
							$(this).tooltip('tip').css({            
								backgroundColor: 'white',            
								borderColor: '#666'        
							});    
						}
					});
				}else{
					for(var j=0;j<obj.length;j++){
						if(j<5){
							if(obj[j].content.length > 50){
								obj[j].content = obj[j].content.substring(0, 50) + "...";
							}
							conn = conn + obj[j].content + "<br/>";
						}
					}
					$("#"+vin+"").tooltip({   
						position: 'right',    
						content: "<span>"+conn+"</span>",
						onShow: function(){        
							$(this).tooltip('tip').css({            
								backgroundColor: 'white',            
								borderColor: '#666'        
							});    
						}
					});
				}
			}
		});
		},500);
	}
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height: 170px" border="false">
				<form id="admin_wareHouse_wareHouse_form" name="form" class="datagrid-toolbar" method="post">
					<div style="width: 980px">
					<table id="admin_wareHouse_wareHouse_table">
						<tr>
							<th>In Warehouse From:</th>
							<td><input editable="false" type="text" id="admin_wareHouse_wareHouse_receiveFrom" name="inWareFrom" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/>至<input editable="false" type="text" id="admin_wareHouse_wareHouse_receiveTO" name="inWareTO" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/></td>	
							<th>LoadingDate From:</th>
							<td><input editable="false" type="text" id="admin_wareHouse_wareHouse_loadingFrom" name="loadingFrom" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/>至<input editable="false" type="text" id="admin_wareHouse_wareHouse_loadingTo" name="loadingTo" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/></td>	
						</tr>
						<tr>
							<th>Customer:</th>
							<td>
								<select id="admin_wareHouse_wareHouse_users" name="users" style="width: 300px">
									<option value="">ALL</option>
								</select>
							</td>
							<th>Ordered Status:</th>
							<td>
								<select id="admin_wareHouse_wareHouse_order" name="orderstate" style="width: 150px">
									<option value="" selected="selected">
										ALL
									</option>
									<option value="1">
										Ordered
									</option>
									<option value="2">
										Not Ordered
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Loaded Status:</th>
							<td>
								<select id="admin_wareHouse_wareHouse_truck" name="loaddate" style="width: 150px">
									<option value="" selected="selected">
										ALL
									</option>
									<option value="1">
										Loaded
									</option>
									<option value="2">
										Not Loaded
									</option>
								</select>
							</td>
							<th>VIN# last 6 Digits:</th>		
							<td><input type="text" name="vin" class="input-small" id="admin_wareHouse_wareHouse_vin" placeholder="" autocomplete="off" style="width: 200px"/></td>
						</tr>
						<tr>
							<th>Year:</th>
							<td>
								<select id="admin_wareHouse_wareHouse_year" name="year" style="width: 150px">
									<option value="">
										ALL
									</option>
								</select>
							</td>
							<th>Warehouse :</th>		
							<td>
								<select id="admin_wareHouse_wareHouse_whes" name="whes" style="width: 150px"> 
									<option value="">
										ALL
									</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>Booking#:</th>
							<td>
								<input type="text" name="booknum" class="input-small" autocomplete="off" id="admin_wareHouse_wareHouse_booknum" style="width: 200px"/>
							</td>
							<th>Container#:</th>
							<td>
								<input type="text" name="contNo" class="input-small" id="admin_wareHouse_wareHouse_contNo" autocomplete="off" style="width: 200px"/>
							</td>
							<td>
								<a id="admin_wareHouse_wareHouse_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>				
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="admin_wareHouse_wareHouse_datagrid"></table>
			</div>    				
		</div>
