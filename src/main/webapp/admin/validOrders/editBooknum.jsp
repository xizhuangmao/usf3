<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
	<div id="admin_validOrders_editBooknum_div" class="easyui-layout" data-options="fit:true" style="width:950px;height:860px;">   
		<div data-options="region:'north',split:true" style="height:40px;background:#eee;">
			<form method="post">
				<table>
					<tr>
						<td>VIN# last 6 Digits:</td>
						<td><input id="admin_validOrders_editBooknum_from_vin" name="vin"/></td>
						<td><a id="admin_validOrders_editBooknum_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
						<td><a id="admin_validOrders_editBooknum_from_Clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Clean</a></td>
					</tr>
				</table>
			</form>
		</div>    
		<div data-options="region:'center',border:false"> 
			<table id="admin_validOrders_editBooknum_datagrid"></table>
		</div> 
		<div data-options="region:'south',split:true" style="height:45%;width:950px;backgrounp:red;">
	    	<div id="cc" class="easyui-layout" data-options="fit : true,">   
				<div data-options="region:'center',border:false" style="padding:0px;background:#eee;">
				    <form id="admin_validOrders_editBooknum_from" method="post">
					    <table>
					    	<tr>
								<td><span style="color: red">*</span>UOO:<input id="admin_validOrders_editBooknum_from_Uoo" name="uoo" class="easyui-validatebox"  maxlength='50'/></td>
								<td> Loading Date  :<input id="admin_validOrders_editBooknum_from_loaddate" name="loaddate" class="easyui-datebox" 
													data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<td>Carrier:<input id="admin_validOrders_editBooknum_from_Carrier" name="carrier" value="Please Select" style="width:200px;"
    											data-options="editable:false,valueField:'fullname',textField:'fullname',url:'${pageContext.request.contextPath}/carrierAction!getCarrierName.action',
    											" /></td>
    							<td> Container No. :<input id="admin_validOrders_editBooknum_from_connum" name="connum" class="easyui-validatebox"  maxlength='50'/></td>
								<td rowspan="5"><div style="width:620px;padding-left: 80px" align="center"><span><strong style="font-size: 12px">memo</strong></span><img id="admin_validOrders_editBooknum_from_more" style="padding-left: 5px" src="images/more.png"/></div><div style="width:620px;height:120px;padding-left: 75px;padding-top: 10px">
														<table id="admin_validOrders_editBooknum_from_memo"></table>
													</div></td>
							</tr>
							<tr>
								<td>Vessel:<input id="admin_validOrders_editBooknum_from_Vessel" class="easyui-combobox" name="vessel" value="Please Select"
												data-options="editable:false,disabled:true"> </td>
								<td> Seal No.  :<input id="admin_validOrders_editBooknum_from_sealnum" name="sealnum" class="easyui-validatebox"  maxlength='50'/></td>
							</tr>
							
							<tr>
								<td>Voyage:<input id="admin_validOrders_editBooknum_from_Voyage" class="easyui-combobox" name="voyage" value="Please Select"
												data-options="editable:false,disabled:true"> </td>
								<td> Truck Co.  :<input id="admin_validOrders_editBooknum_from_truck" name="truckId" class="easyui-combobox"  maxlength='50'
									 data-options="valueField:'id',textField:'fullname', editable:false,url:'${pageContext.request.contextPath}/truckAction!getTruckCoName.action',
    											" /></td>
							</tr>
							<tr>
								<td><input name="booknumId" hidden="true"/></td>
							</tr>
							<tr>
								<td><span style="color: red">*</span>Booking No.:<input id="admin_validOrders_editBooknum_from_booknum" class="easyui-combobox" name="booknum" value="Please Select"
												data-options="editable:false,disabled:true"/></td>
								<td> Trucking Date  :<input id="admin_validOrders_editBooknum_from_uoo" name="truckdate" class="easyui-datebox" 
													data-options=" editable:false,formatter:timeformatter,parser:timeparser"/></td>
							</tr>
							<tr>
								<td>Container Size:<select class="easyui-combobox" name="consize" data-options="editable:false" style="width:80px;">   
												    <option>40ST</option> 
												    <option>40HQ</option>
												    <option>45HQ</option>
												    <option>20ST</option></select></td>
							</tr>
							<tr>
								<td><a id="admin_validOrders_editBooknum_from_Save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
							</tr>
					    </table>
				    </form>
				</div>   
			</div>      	
		</div> 
	</div>  

	<script type="text/javascript">
		var admin_validOrders_editBooknum_datagridId;
		var admin_validOrders_editBooknum_selectWhes = [];
		var admin_validOrders_editBooknum_editBooknum;
		$(function(){
			admin_validOrders_editBooknum_editBooknum = $("#admin_validOrders_editBooknum_datagrid").datagrid({
				url : '${pageContext.request.contextPath}/exportAction!NewOrders.action',
				border : false,
				pagePosition:'bottom',
				pagination : true,
				pageSize : 10,
				pageList : [ 10, 20, 50, 100 ],
				fit : true,
				idField : 'id',
				fitColumns : true,
				sortName : 'indate',
				sortOrder : 'desc',
				checkOnSelect:false,
				pagePosition: 'top',
				frozenColumns : [ [ {
					field : 'id',
					title : '编辑',
					width : '5%',
					checkbox : true
				} ] ],
				columns : [ [ {
					field : 'vin',
					title : 'Vin',
					width : '10%',
				},{
					field : 'users',
					title : 'Customer',
					width : '12%',
				},{
					field : 'make',
					title : 'Make',
					width : '8%',
					sortable : true
				},{
					field : 'model',
					title : 'Model',
					width : '8%',
					sortable : true
				},{
					field : 'pol',
					title : 'Pol',
					width : '8%',
					sortable : true
				},{
					field : 'pod',
					title : 'Pod',
					width : '8%',
					sortable : true
				},{
					field : 'note',
					title : 'Remark',
					width : '10%',
					sortable : true
				},{
					field : 'ordersdate',
					title : 'OrdersDate',
					width : '5%',
					sortable : true
				},{
					field:'services',
					title:'Services',
					width : '10%',
					formatter : function(value, row, index){
						str = "<label id=\"admin_validOrders_editBooknum_label"+row.id+"\"></label>";
						return str;
					}
				},{
					field:'whesdtlServices',
					title:'ChangeServices',
					formatter : function(value, row, index){
						str = '';
						str += $.formatString('<img onclick="admin_validOrders_editBooknum_editServices(\'{0}\');" src="{1}" title="修改"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
						return str;
					}
				}] ],
				onLoadSuccess: function(data){
					$('.datagrid-header-check').children().hide();
					var pageSize = admin_validOrders_editBooknum_editBooknum.datagrid('getPager').data("pagination").options.pageNumber;
					if(pageSize == 1 || pageSize == 0){
						$.ajax({
							url : '${pageContext.request.contextPath}/booknumAction!findBooknumById.action',
							data : {"id" : admin_validOrders_validOrders_editBooknumId},
							type : 'post',
							dataType : 'text',
							success : function(data){
								var obj = $.parseJSON(data);
								for(var i=0;i<obj.length;i++){
									$("#admin_validOrders_editBooknum_datagrid").datagrid('insertRow',{
										index: 0,
										row: {
											id : obj[i].id,
											vin : obj[i].vin,
											users : obj[i].users,
											make: obj[i].make,
											model : obj[i].model ,
											pol:  obj[i].pol,
											pod:  obj[i].pod,
											note : obj[i].note,
											ordersdate : obj[i].ordersdate,
											services: "<label id=\"admin_validOrders_editBooknum_label"+obj[i].id+"\"></label>",
											whesdtlServices: $.formatString('<img onclick="admin_validOrders_editBooknum_editServices(\'{0}\');" src="{1}" title="修改"/>', obj[i].id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png'),
										}
									});
									var admin_validOrders_editBooknum_booknumServiceId = "";
									if(obj[i].whesdtlServiceses.length > 0){
										for(var j=0;j<obj[i].whesdtlServiceses.length;j++){
											admin_validOrders_editBooknum_booknumServiceId += obj[i].whesdtlServiceses[j].services.name + ',';
										}
										admin_validOrders_editBooknum_booknumServiceId = admin_validOrders_editBooknum_booknumServiceId.substring(0, admin_validOrders_editBooknum_booknumServiceId.length-1);
										$("#admin_validOrders_editBooknum_label"+obj[i].id+"").html(admin_validOrders_editBooknum_booknumServiceId);
									}
									admin_validOrders_editBooknum_editBooknum.datagrid('checkRow', admin_validOrders_editBooknum_editBooknum.datagrid('getRowIndex', obj[i].id));
									$("#admin_validOrders_editBooknum_from").form('load', obj[0]);
								};				
							}
						});
					}
					for(var i=0;i<data.rows.length;i++){
						var admin_validOrders_editBooknum_serviceId = "";
						if(data.rows[i].whesdtlServiceses.length > 0){
							for(var j=0;j<data.rows[i].whesdtlServiceses.length;j++){
								admin_validOrders_editBooknum_serviceId += data.rows[i].whesdtlServiceses[j].services.name + ',';
							}
							$("#admin_validOrders_editBooknum_label"+data.rows[i].id+"").html(admin_validOrders_editBooknum_serviceId);
						}
					}
				},
				onClickCell: function (rowIndex, field, value) {
					IsCheckFlag = false;
				},
				onSelect: function (rowIndex, rowData) {
					if (!IsCheckFlag) {
						IsCheckFlag = true;
						$("#admin_validOrders_editBooknum_datagrid").datagrid("unselectRow", rowIndex);
					}
				},                    
				onUnselect: function (rowIndex, rowData) {
					if (!IsCheckFlag) {
						IsCheckFlag = true;
						$("#admin_validOrders_editBooknum_datagrid").datagrid("selectRow", rowIndex);
					}
				},
				onCheck: function(rowIndex,rowData){
					admin_validOrders_editBooknum_datagridId = rowData.id;
					admin_validOrders_editBooknum_selectWhes.push(rowData.vin);
				},
				onUncheck: function (rowIndex, rowData) {
					admin_validOrders_editBooknum_selectWhes.splice($.inArray(rowData.vin,admin_validOrders_editBooknum_selectWhes),1);
				},
			});

			$.ajax({
				url:'memoAction!findUooMemoByBooknumId.action',
				data:{"booknumId" : admin_validOrders_validOrders_editBooknumId},
				type:'post',
				dataType:'text',
				success:function(data){
					var obj = $.parseJSON(data);
					$("#admin_validOrders_editBooknum_from_memo").empty();
						if(obj.rows.length == 0){
							$("#admin_validOrders_editBooknum_from_memo").append("<tr><td><p style=\"color:#747474\">Please add the memo.</p></td></tr>");
						}else{
							for(var j=0;j<obj.rows.length;j++){
								if(obj.rows[j].content.length > 26){
									obj.rows[j].content = obj.rows[j].content.substring(0, 26) + "...";
								}
								if(j<3){
								$("#admin_validOrders_editBooknum_from_memo").append("<tr><td style=\"width:80px;word-wrap:break-word;float:left\">"+obj.rows[j].users+"</td><td style=\"width:400px;float:left;word-wrap:break-word;\">"+obj.rows[j].content+"</td>" +
																						"<td style=\"color:#929395;float:left\">"+obj.rows[j].memodate+"</td></tr>" +
																							"<tr><td><p style=\"border-top: 1px solid #ceced0;width: 660px\"></p></td></tr>");
								}
							}
						}
				} 
			});
		});
		
		$("#admin_validOrders_editBooknum_from_Carrier").combobox({    
		    onSelect: function(record){
		    	$("#admin_validOrders_editBooknum_from_Voyage").combobox('loadData', {});
 				$("#admin_validOrders_editBooknum_from_Voyage").combobox('setValue', 'Please Select');
 				$("#admin_validOrders_editBooknum_from_booknum").combobox('loadData', {});
 				$("#admin_validOrders_editBooknum_from_booknum").combobox('setValue', 'Please Select');
	            if(record.id != ""){
					var url = "${pageContext.request.contextPath}/vesselAction!getVesselByCarrierId.action?carrierId="+record.id;  
					$("#admin_validOrders_editBooknum_from_Vessel").combobox({
					    'url': url,
					     valueField: 'id',    
						 textField: 'vessel',
					     disabled:false,
					}); 
				}
	        }
		});  
	
		$("#admin_validOrders_editBooknum_from_Vessel").combobox({    
		    onSelect: function(record){
	            if(record.id != ""){
					var url = "${pageContext.request.contextPath}/voyageAction!getVoyageByVesselId.action?vesselId="+record.id;  
					$("#admin_validOrders_editBooknum_from_Voyage").combobox({
					    'url': url,
					     valueField: 'id',    
						 textField: 'voyage',
					     disabled:false,
					}); 
				}
	        }
		});
		
		$("#admin_validOrders_editBooknum_from_Voyage").combobox({    
		    onSelect: function(record){
	            if(record.id != ""){
					var url = "${pageContext.request.contextPath}/booknumAction!getBooknumByVoyageId.action?voyageId="+record.id;  
					$("#admin_validOrders_editBooknum_from_booknum").combobox({
					    'url': url,
					     valueField: 'id',    
						 textField: 'booknum',
					     disabled:false,
					}); 
				}
	        }
		});
		
		$("#admin_validOrders_editBooknum_from_more").click(function(){
			var d = $("<div id='admin_validOrders_BooknumMemoDiv'/>").dialog({
				title: 'booknum memo',
				width: 920,
				height: 600,
				href: "${pageContext.request.contextPath}/admin/memo/uooMemo.jsp?id="+admin_validOrders_validOrders_editBooknumId+"",
				modal: true,
				onClose:function(){
					$(this).dialog('destroy');
					$.ajax({
						url:'memoAction!findUooMemoByBooknumId.action',
						data:{"booknumId" : admin_validOrders_validOrders_editBooknumId},
						type:'post',
						dataType:'text',
						success:function(data){
							var obj = $.parseJSON(data);
							$("#admin_validOrders_editBooknum_from_memo").empty();
								if(obj.rows.length == 0){
									$("#admin_validOrders_editBooknum_from_memo").append("<tr><td><p style=\"color:#747474\">Please add the memo.</p></td></tr>");
								}else{
									for(var j=0;j<obj.rows.length;j++){
										if(obj.rows[j].content.length > 26){
											obj.rows[j].content = obj.rows[j].content.substring(0, 26) + "...";
										}
										if(j<3){
										$("#admin_validOrders_editBooknum_from_memo").append("<tr><td style=\"width:80px;word-wrap:break-word;float:left\">"+obj.rows[j].users+"</td><td style=\"width:400px;float:left;word-wrap:break-word;\">"+obj.rows[j].content+"</td>" +
																								"<td style=\"color:#929395;float:left\">"+obj.rows[j].memodate+"</td></tr>" +
																									"<tr><td><p style=\"border-top: 1px solid #ceced0;width: 660px\"></p></td></tr>");
										}
									}
								}
						} 
					});
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
		});
		
		//修改服务
		var admin_validOrders_editServices_whesdtlId;
		function admin_validOrders_editBooknum_editServices(id){
			admin_validOrders_editServices_whesdtlId = id;
			var admin_validOrders_editBooknum_editServices_div =$('<div/>').dialog({
				width:500,
				heigth:300,
				title:'ChangeService',
				modal:true,
				href:"${pageContext.request.contextPath}/admin/validOrders/editBillingUoo.jsp?datagrid=admin_validOrders_editBooknum_editBooknum",
				onClose:function(){
					admin_validOrders_editBooknum_editServices_div.dialog('destroy');
				},
			});
			var browserHeight = $(window).height();  //游览器
			var browserwidth = $(window).width();
			var width = admin_validOrders_editBooknum_editServices_div.panel('options').width;//获取容器的宽
			if(browserwidth>(width+200)){
				if(browserHeight>700){
					admin_validOrders_editBooknum_editServices_div.panel('resize',{
						left : 500,
						top  : 250,
					});
				}else{
					admin_validOrders_editBooknum_editServices_div.panel('resize',{
						left : 150,
						top  : 0,
					});
				}			
			}else{
				if(browserHeight>700){
					admin_validOrders_editBooknum_editServices_div.panel('resize',{
						left : 0,
						top  : 10,
					});
				}else{
					admin_validOrders_editBooknum_editServices_div.panel('resize',{
						left : 0,
						top  : 0,
					});
				}
			}		
		}
					
		$("#admin_validOrders_editBooknum_from_Save").click(function(){
			var rows = admin_validOrders_editBooknum_editBooknum.datagrid('getChecked');
			var orders = serializeObject($("#admin_validOrders_editBooknum_from"));
			if($("#admin_validOrders_editBooknum_from_Uoo").val() == ''){
				$.messager.show({
					title:'Message',
					msg:' Please enter the uoo',
					timeout:5000,
					showType:'slide'
				});
				return;
			}
			
			if($("#admin_validOrders_editBooknum_from_booknum").combo('getText') == '' || $("#admin_validOrders_editBooknum_from_booknum").combo('getText') == 'Please Select'){
				$.messager.show({
					title:'Message',
					msg:' Please enter the booknum',
					timeout:5000,
					showType:'slide'
				});
				return;
			}
			
			var whesdtlVinJSONs="(";
			for(i=0;i<rows.length;i++){
				if(whesdtlVinJSONs=="("){
					whesdtlVinJSONs = whesdtlVinJSONs + "'"+rows[i].vin+"'";
				}else{
					whesdtlVinJSONs = whesdtlVinJSONs +","+ "'"+rows[i].vin+"'";
				}
			}
			whesdtlVinJSONs = whesdtlVinJSONs+")";

			orders.whesdtlVinJSONs = whesdtlVinJSONs;
			orders.vessel = $("#admin_validOrders_editBooknum_from_Vessel").combo('getText');
			orders.voyage = $("#admin_validOrders_editBooknum_from_Voyage").combo('getText');
			orders.booknum = $("#admin_validOrders_editBooknum_from_booknum").combo('getText');
			
			$.ajax({  
        		type: "post",
        		dataType: "text",
        		url: "booknumAction!updateBooknum.action",
        		data: orders,
        		success: function (result) {
        			var obj = $.parseJSON(result);
        			if(obj.msg == 'success'){
        				$.messager.show({
							title:'Message',
							msg:obj.msg,
							timeout:5000,
							showType:'slide'
						});
        				admin_validOrders_editBooknum_editBooknum.datagrid('reload');
        				$("#admin_validOrders_validOrders_datagrid").datagrid('reload');
        			}
        		}  
        	});
		});
	</script>
	