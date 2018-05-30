<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript">
		var admin_validOrders_editServices_datagridId;
		var admin_validOrders_billing_table_datagrid;
		var admin_validOrders_billing_table_datagrid_rows;
		$(function(){
			admin_validOrders_billing_table_datagrid=$("#admin_validOrders_billing_table_datagrid").datagrid({
				url:'whesdtlAction!findUooAndWhesdtlServices.action',
				border:false,
				fitColumns:true,
				pagination:true,
				pageSize:5,
				pageList:[5,10,20],
				idField:'id',
				sortName:'uoo',
				sortOrder:'desc',
				checkOnSelect:false,
				selectOncheck:false,
				fit:true,
				columns:[[{
					field:'uoo',
					title:'Uoo',
					sortable:true,
				},{
					field:'vin',
					title:'Vin',
				},{
					field:'company',
					title:'Company',
				},{
					field:'users',
					title:'Customer',
				},{
					field:'name',
					title:'Service',
				},{
					field:'price',
					title:'Price',
				},{
					field:'discount',
					title:'Discount',
					formatter: function(value,row,index){
						if(row.hasOwnProperty("name") && row.hasOwnProperty("price")){
							if(row.price != ""){
								if(!row.hasOwnProperty("discount")){
									row.discount = "";
								}
								return "<input id=\"admin_validOrders_bill_discount"+row.id+"\" onfocus=\"admin_validOrders_billing_getDiscount('"+row.id+"')\" onblur=\"admin_validOrders_billing_editDiscount('"+row.id+"', '"+row.price+"', '"+row.pay+"', '"+row.paystate+"', '"+row.discount+"')\" value=\""+row.discount+"\" style=\"width:80px\"/>";
							}
						}
					}
				},{
					field:'pay',
					title:'Pay',
					formatter: function(value,row,index){
						if(row.hasOwnProperty("name") && row.hasOwnProperty("price")){
							if(row.price != ""){
								if(!row.hasOwnProperty("pay")){
									row.pay = "";
								}
								return "<input id=\"admin_validOrders_bill_pay"+row.id+"\" onfocus=\"admin_validOrders_billing_getPay('"+row.id+"')\" onblur=\"admin_validOrders_billing_editPay('"+row.id+"', '"+row.price+"', '"+row.discount+"', '"+row.paystate+"', '"+row.pay+"')\" value=\""+row.pay+"\"  style=\"width:100px\"/>";
							}
						}
					}
				},{
					field:'paystate',
					title:'Paystate',
					formatter: function(value,row,index){
						if(row.hasOwnProperty("name") && row.hasOwnProperty("price")){
							if(row.price != ""){
								var str = "";
								if(row.paystate == '1'){
									str += $.formatString("<img id=\"admin_validOrders_billing_changePaystate"+row.id+"\" onclick=\"admin_validOrders_billing_paystate(\'{0}\',\'{1}\');\" src=\"{2}\" title=\"edit\"/>", row.id, row.paystate, '${pageContext.request.contextPath}/style/images/extjs_icons/noactive.png');
								}else if(row.paystate == '2'){
									str += $.formatString("<img id=\"admin_validOrders_billing_changePaystate"+row.id+"\" onclick=\"admin_validOrders_billing_paystate(\'{0}\',\'{1}\');\" src=\"{2}\" title=\"edit\"/>", row.id, row.paystate, '${pageContext.request.contextPath}/style/images/extjs_icons/yes.png');
								}				
	            				return str;
	            			}
	            		}
					}
				},{
					field:'whesdtlServices',
					title:'ChangeServices',
					formatter : function(value, row, index){
						str = '';
						str += $.formatString('<img onclick="admin_validOrders_editServices(\'{0}\', \'{1}\');" src="{2}" title="修改"/>', row.id, row.whesdtlId, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil.png');
						return str;
					}
				}]],
				toolbar : [ {
					iconCls : 'icon-reload',
					handler : function() {
						admin_validOrders_billing_table_datagrid.datagrid('reload');
					}
				} ],
				onLoadSuccess:function(data){
					admin_validOrders_billing_table_datagrid_rows = data.rows; 
					if(data.rows.length>0){
					 	$("#admin_validOrders_billing_table_datagrid").datagrid("mergeCellsArray",{
							mergerows:data.rows,
							mergefields:['uoo', 'vin', 'whesdtlServices', 'selectServices'],
							mergeorder:'vin'
						});
						$("#admin_validOrders_billing_table_datagrid").datagrid("autoMergeCells",['uoo']);
					}
				},
				onClickCell: function (rowIndex, field, value) {
					IsCheckFlag = false;
				},
				onSelect: function (rowIndex, rowData) {
					if (!IsCheckFlag) {
						IsCheckFlag = true;
						$("#admin_validOrders_billing_table_datagrid").datagrid("unselectRow", rowIndex);
					}
				},
				onUnselect: function (rowIndex, rowData) {
					if (!IsCheckFlag) {
						IsCheckFlag = true;
						$("#admin_validOrders_billing_table_datagrid").datagrid("selectRow", rowIndex);
					}
				},
				onClickRow: function(rowIndex, rowData){
					admin_validOrders_editServices_datagridId = rowData.whesdtlId;
				},
			});
		});

		var admin_validOrders_editServices_whesdtlId;
		function admin_validOrders_editServices(id, whesdtlId){
			admin_validOrders_editServices_whesdtlId = whesdtlId;
			var admin_validOrders_editServices_div =$('<div/>').dialog({
				width:500,
				heigth:300,
				title:'ChangeService',
				modal:true,
				href:"${pageContext.request.contextPath}/admin/validOrders/editBillingUoo.jsp?datagrid=admin_validOrders_billing_table_datagrid",
				onClose:function(){
					admin_validOrders_editServices_div.dialog('destroy');
				},
			});
			var browserHeight = $(window).height();  //游览器
			var browserwidth = $(window).width();
			var width = admin_validOrders_editServices_div.panel('options').width;//获取容器的宽
			if(browserwidth>(width+200)){
				if(browserHeight>700){
					admin_validOrders_editServices_div.panel('resize',{
						left : 500,
						top  : 250,
					});
				}else{
					admin_validOrders_editServices_div.panel('resize',{
						left : 150,
						top  : 0,
					});
				}			
			}else{
				if(browserHeight>700){
					admin_validOrders_editServices_div.panel('resize',{
						left : 0,
						top  : 10,
					});
				}else{
					admin_validOrders_editServices_div.panel('resize',{
						left : 0,
						top  : 0,
					});
				}
			}
		}
		
		//enter键失去焦点提交数据
		function admin_validOrders_billing_getDiscount(id){
			$("#admin_validOrders_bill_discount"+id+"").keydown(function(event){
				var code = event.keyCode || event.which;
				if(code == 13){
					$("#admin_validOrders_bill_discount"+id+"").blur();
				}
			});
		}

		function admin_validOrders_billing_editDiscount(id, price, pay, paystate, oldDiscount){
			var discount = ($("#admin_validOrders_bill_discount"+id+"").val());
			var reg = new RegExp(/^(100|[1-9]\d|\d)$/);
			if(reg.test(discount)){
				$.ajax({
					url:'whesdtlServicesAction!updateWhesdtlServices.action',
					data:{"id":id, "discount":discount, "price":price},
					dataType:'text',
					type:'post',
					success:function(data){
						var obj = $.parseJSON(data);
						if(obj.success){
							reloadAdmin_validOrders_billing_table_datagrid(id, obj);		
						}
					}
				});
			}else{
				$.messager.show({
					title:'Message',
					msg:'Please enter the discount for an integer between 0 and 100',
					timeout:5000,
					showType:'slide'
				});
				reloadAdmin_validOrders_billing_table_datagridError(id, pay, oldDiscount, paystate);
			}
		}
		
		//enter键失去焦点提交数据
		function admin_validOrders_billing_getPay(id){
			$("#admin_validOrders_bill_pay"+id+"").keydown(function(event){
				var code = event.keyCode || event.which;
				if(code == 13){
					$("#admin_validOrders_bill_pay"+id+"").blur();
				}
			});
		}
		
		function admin_validOrders_billing_editPay(id, price, discount, paystate, oldPay){
			var pay = ($("#admin_validOrders_bill_pay"+id+"").val());
			var reg = new RegExp(/^(0\.[0-9]\d*||[1-9]\d*(\.\d+)?|0)$/);
			if(reg.test(pay) && parseFloat(pay) <= parseFloat(price)){
				$.ajax({
					url:'whesdtlServicesAction!updateWhesdtlServicesPay.action',
					data:{"id":id, "pay":pay, "price":price},
					dataType:'text',
					type:'post',
					success:function(data){
						var obj = $.parseJSON(data);
						if(obj.success){
							reloadAdmin_validOrders_billing_table_datagrid(id, obj);
						}
					}
				});
			}else{
				$.messager.show({
					title:'Message',
					msg:'Please enter the correct price',
					timeout:5000,
					showType:'slide'
				});
				reloadAdmin_validOrders_billing_table_datagridError(id, oldPay, discount, paystate);
			}
		}
		
		//修改支付状态
		function admin_validOrders_billing_paystate(id, paystate){
			$.ajax({
				url:'whesdtlServicesAction!updateWhesdtlServicesPayState.action',
				data:{"id":id, "paystate":paystate},
				type:'post',
				dataType:'text',
				success:function(data){
					var obj = $.parseJSON(data);
					if(obj.success){
						reloadAdmin_validOrders_billing_table_datagrid(id, obj);			
					}
				}
			});
		}
		
		//刷新当前数据
		function reloadAdmin_validOrders_billing_table_datagrid(id, obj){
			admin_validOrders_billing_table_datagrid.datagrid('updateRow',{
				index: admin_validOrders_billing_table_datagrid.datagrid('getRowIndex', id),
				row: {
					discount: obj.obj.discount,
					pay: obj.obj.pay,
					paystate: obj.obj.paystate,
				}
			});
			$("#admin_validOrders_billing_table_datagrid").datagrid("mergeCellsArray",{
				mergerows:admin_validOrders_billing_table_datagrid_rows,
				mergefields:['uoo', 'vin', 'whesdtlServices', 'selectServices'],
				mergeorder:'vin'
			});
			$("#admin_validOrders_billing_table_datagrid").datagrid("autoMergeCells",['uoo']);
		}
		
		function reloadAdmin_validOrders_billing_table_datagridError(id, pay, discount, paystate){
			if(pay == 'undefined'){
				pay = "";
			}
			if(discount == 'undefined'){
				discount = "";
			}
			admin_validOrders_billing_table_datagrid.datagrid('updateRow',{
				index: admin_validOrders_billing_table_datagrid.datagrid('getRowIndex', id),
				row: {
					discount: discount,
					pay: pay,
					paystate: paystate,
				}
			});
			$("#admin_validOrders_billing_table_datagrid").datagrid("mergeCellsArray",{
				mergerows:admin_validOrders_billing_table_datagrid_rows,
				mergefields:['uoo', 'vin', 'whesdtlServices', 'selectServices'],
				mergeorder:'vin'
			});
			$("#admin_validOrders_billing_table_datagrid").datagrid("autoMergeCells",['uoo']);
		}
		
		$('#admin_validOrders_billing_search').bind('click',function(){
			$("#admin_validOrders_billing_table_datagrid").datagrid('load',serializeObject($("#admin_validOrders_billing_form")));
		});
	</script>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'north',title:'search',split:true" style="height: 70px;border: 0px">
			<form id="admin_validOrders_billing_form" class="datagrid-toolbar" method="post">
				<div style="width: 980px">
					<table id="admin_validOrders_billing_table">
						<tr>
							<td>UOO:<input name="uoo"/></td>
							<td><a class="easyui-linkbutton" id="admin_validOrders_billing_search" data-options="iconCls:'icon-search'">search</a></td>
						</tr>
					</table>
				</div>
			</form>
		</div>  
		<div data-options="region:'center',border:false">
			 <table id="admin_validOrders_billing_table_datagrid"></table>
		</div>    				
	</div>
