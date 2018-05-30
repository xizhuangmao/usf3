<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
var admin_customerOrder_searchForVehicle_datagrid_url;
$(function(){
	if(layout_north_searchInfo_customerUrl == 1){
		layout_north_searchInfo_customerUrl = 0;
		admin_customerOrder_searchForVehicle_datagrid_url = 'warehouseAction!getWhesdtDatagrid.action?vin=' + "<%= request.getParameter("vin")%>";
	}else if(layout_north_searchInfo_customerUrl == 2){
		layout_north_searchInfo_customerUrl = 0;
		$("#admin_customerOrder_searchForVehicle_flowstate").val("1");
		admin_customerOrder_searchForVehicle_datagrid_url = 'warehouseAction!getWhesdtDatagrid.action?flowstate=1';
	}else{
		admin_customerOrder_searchForVehicle_datagrid_url = 'warehouseAction!getWhesdtDatagrid.action';
	};
	$("#admin_customerOrder_searchForVehicle_datagrid").datagrid({
		url:admin_customerOrder_searchForVehicle_datagrid_url,
		border:false,
		fitColumns:false,
		pagination:true,
		pageSize:10,
		pageList:[10,20,50,100],
		idField:'id',
		sortName:'vin',
		sortOrder:'desc',
		checkOnSelect: false,
		selectOncheck:true,
		fit:true,
		columns:[[{
				field:'id',
				title:'Id',
				hidden:true
			},{
				field:'pic',
				title:'Image',
				formatter : function(value,row,index){
					var str = "";
     				if(value!="" && value!=null){
     					str = "<img style=\"width:70px;height:50px\" src=\"warehouseAction!showWhesdtlPics.action?path="+row.pic+"\"/>";
                		return str;
           	 		}
				}
			},{
				field:'vin',
				title:'Vin',
				sortable:true,
				formatter: function(value,rowData,rowIndex) {
                	return "<a href='#' onclick=admin_customerOrder_searchForVehicle_findVehicleInfo('" + value + "');>" + value + "</a>";
                }
			},{
				field:'engine',
				title:'Engine',
			},{
				field:'make',
				title:'Make',
				sortable:true
			},{
				field:'model',
				title:'Model',
			},{
				field:'year',
				title:'Year',
				sortable:true
			},{
				field:'color',
				title:'Color',
			},{
				field:'keynum',
				title:'Key',
			},{
				field:'sticker',
				title:'Sticker',
			},{
				field:'indate',
				title:'In&nbsp;Date',
				sortable:true
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
				field:'ordersdate',
				title:'Order Date',
				sortable:true
			},{
				field:'connum',
				title:'Container#',
			},{
				field:'note',
				title:'Remark',
				width : 100,
				formatter: function (value) {
			    	return "<span title='" + value + "'>" + value + "</span>";
			    }
			},{
				field:'flowstate',
				title:'Received',
				sortable:true,
				formatter : function(value){
					if(value == "1" || value == null){
						 return "No";
					}else if(value == "2"){
						 return "Yes";
					}
				}
			},{
				field:'whes',
				title:'Whes',
			},{
				field:'str',
				title:'Operation',
				width : 150,
				formatter : function(value, row, index){
					if(row.flowstate == '1'){
						var str = "";
            			str += "<a class=\"editprealert\" onclick=\"admin_customerOrder_searchForVehicle_editprealert('"+row.id+"')\" href=\"#\"></a>" + 
            			"<a class=\"deleteprealert\" onclick=\"admin_customerOrder_searchForVehicle_deleteprealert('"+row.id+"')\" href=\"#\"></a>";
            			return str;
					}
				}
			}]],	 	
			toolbar : [ {
				text : 'refresh',
				iconCls : 'icon-reload',
				handler : function() {
					$("#admin_customerOrder_searchForVehicle_datagrid").datagrid('reload');
				}
			} ],
			onLoadSuccess:function(data){ 
				$(".editprealert").linkbutton({ 
					text:'edit', 
					plain:true, 
					iconCls:'icon-edit',
				});
				$(".deleteprealert").linkbutton({ 
					text:'delete', 
					plain:true, 
					iconCls:'icon-remove',
				});
			},		
			onClickCell: function (rowIndex, field, value) {
				IsCheckFlag = false;
			},
			onSelect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#admin_customerOrder_searchForVehicle_datagrid").datagrid("unselectRow", rowIndex);
				}
			},                    
			onUnselect: function (rowIndex, rowData) {
				if (!IsCheckFlag) {
					IsCheckFlag = true;
					$("#admin_customerOrder_searchForVehicle_datagrid").datagrid("selectRow", rowIndex);
				}
			}
		});
		
		admin_customerOrder_searchForVehicle_deleteprealert = function(id){
			$.messager.confirm('confirm', 'delete the preAlert vehicle?', function(r){
			    if (r){
					$.ajax({
						url : 'warehouseAction!deletePreAlertVehicle.action',
						data : {"id" : id},
						dataType : 'text',
						type : 'post',
						success : function(data){
							var obj = $.parseJSON(data);
							if(obj.success == true){
								$.messager.show({
									title:'Message',
									msg:obj.msg,
									timeout:5000,
									showType:'slide'
								});
								$("#admin_customerOrder_searchForVehicle_datagrid").datagrid('reload');
							}
						}
					});
				}else{}
			});
		};
			
		admin_customerOrder_searchForVehicle_editprealert = function(id){
			var d =$('<div/>').dialog({
				width:600,
				heigth:450,
				title:"edit",
				modal:true,
				href:"${pageContext.request.contextPath}/admin/customerOrder/editVehicle.jsp?id="+id+"",
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
						top  : 100,
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
			
		//加载make以及对应的model并初始化值为DEFAULT
		$.ajax({
			url : "warehouseAction!findAllWhesMake.action",
			type : "post",
			dataType : "text",
			success : function(r){
				var obj = $.parseJSON(r);
				obj.whesCompany.unshift({id:'ALL',fullname:'ALL'});
	        	$("#admin_customerOrder_searchForVehicle_whes").combobox({
					valueField: 'id',    
	       			textField: 'fullname',
	       			value: 'ALL',
	        		data: obj.whesCompany,
	        	});
				obj.make.unshift({id:'DEFAULT',make:'DEFAULT'});      //unshift添加初始值DEFAULT
				$("#admin_customerOrder_searchForVehicle_make").combobox({
					valueField: 'id',    
       				textField: 'make',
       				value: 'DEFAULT',
        			data: obj.make,
        			onSelect:function(record){
        				var url = 'modelAction!findModelByMakeId.action?makeId=' + record.id; 
        				$.ajax({      								//根据选中的makeId获取对应的model
        					url : url,
							type : "post",
							dataType : "text",
							success : function(r){
								var obj = $.parseJSON(r);
								obj.unshift({id:'DEFAULT',model:'DEFAULT'});
								$("#admin_customerOrder_searchForVehicle_model").combobox({
									valueField: 'model',    
       								textField: 'model',
        							data: obj,
        						});
							}
        				});   
        			}  
				});
				$("#admin_customerOrder_searchForVehicle_model").combobox({
       				value: 'DEFAULT',
        		});
			}
		});

		//Year
		for(var i=2001;i<2021;i++){
	    	$("#admin_customerOrder_searchForVehicle_year").append("<option value='"+i+"'>"+i+"</option>");
		}
			
		$("#admin_customerOrder_searchForVehicle_search").click(function(){
			//带参重新查询datagrid
			$("#admin_customerOrder_searchForVehicle_datagrid").datagrid({url:'warehouseAction!getWhesdtDatagrid.action',queryParams:serializeObject($("#admin_customerOrder_searchForVehicle_form"))});
		});
			
		admin_customerOrder_searchForVehicle_findVehicleInfo = function(value){
			var d =$('<div/>').dialog({
				width:800,
				heigth:800,
				title:"Search For Vehicle",
				modal:true,
				href:"${pageContext.request.contextPath}/admin/customerOrder/vehicleInfo.jsp?vin="+value+"",
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
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height:195px;" border="false">
				<form id="admin_customerOrder_searchForVehicle_form" name="form" class="datagrid-toolbar" method="post">
				<div style="width: 880px">
					<table id="search_table">
						<tr>
							<th>In Warehouse From:</th>
							<td><input editable="false" type="text" id="admin_customerOrder_searchForVehicle_inWareFrom" name="inWareFrom" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/>至<input editable="false" type="text" id="search_inWareTO" name="inWareTO" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/></td>	
							<th>LoadingDate From:</th>
							<td><input editable="false" type="text" id="admin_customerOrder_searchForVehicle_loadingFrom" name="loadingFrom" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/>至<input editable="false" type="text" id="search_loadingTo" name="loadingTo" class="easyui-datebox" style="width: 145px" data-options="formatter:timeformatter,parser:timeparser"/></td>	
						</tr>
						<tr>
							<th>Color:</th>
							<td>
								<input type="text" name="color" class="input-small" id="search_color" autocomplete="off" style="width: 150px"/>
							</td>
							<th>Ordered State:</th>
							<td>
								<select id="admin_customerOrder_searchForVehicle_order" name="orderstate" style="width: 150px">
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
							<th>Loaded State:</th>
							<td>
								<select id="admin_customerOrder_searchForVehicle_truck" name="loaddate" style="width: 150px">
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
							<td><input type="text" name="vin" class="input-small" id="admin_customerOrder_searchForVehicle_vin" placeholder="" autocomplete="off" style="width: 200px"/></td>
						</tr>
						<tr>
							<th>Make:</th>
							<td>
								<input id="admin_customerOrder_searchForVehicle_make" name="makeId" style="width: 150px"/>
							</td>
							<th>Model:</th>
							<td>
								<input id="admin_customerOrder_searchForVehicle_model" name="model" style="width: 150px"/>
							</td>
						</tr>				
						<tr>
							<th>Year:</th>
							<td>
								<select id="admin_customerOrder_searchForVehicle_year" name="year" style="width: 150px">
									<option value="">
										ALL
									</option>
								</select>
							</td>
							<th>PreAlert:</th>
							<td>
								<select id="admin_customerOrder_searchForVehicle_flowstate" name="flowstate" style="width: 150px">
									<option value="">
										ALL
									</option>
									<option value="1">
										PreAlert
									</option>
									<option value="2">
										InWarehouse
									</option>
								</select>
							</td>
						</tr>	
						<tr>
							<th>Warehouse:</th>
							<td>
								<input id="admin_customerOrder_searchForVehicle_whes" name="whesId" class="easyui-combobox" />
							</td>
							<td>
								<a id="admin_customerOrder_searchForVehicle_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="admin_customerOrder_searchForVehicle_datagrid"></table>
			</div>    				
		</div>
