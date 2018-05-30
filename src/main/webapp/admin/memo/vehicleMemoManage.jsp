<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	$(function(){
		$("#vehiclememo_datagrid").datagrid({
						 url:"memoAction!findAllVehicleMemo.action",
						 border:false,
						 fitColumns:true,
						 pagination:true,
						 pageSize:10,
						 pageList:[10,20,50,100],
						 idField:'id',
						 sortName:'vin',
						 sortOrder:'desc',
						 checkOnSelect:false,
						 selectOncheck:false,
						 fit:true,
						 columns:[[{
						 	field:'id',
						 	title:'编号',
						 	checkbox:true,
						 	},{
						 	field:'vin',
						 	title:'Vin',
						 	sortable:true,
						 	},{
						 	field:'booknum',
						 	title:'Booking No.',
						 	},{
						 	field:'connum',
						 	title:'Container No.',
						 	},{
						 	field:'sealnum',
						 	title:'Seal No.',
						 	},{
						 	field:'uoo',
						 	title:'UOO',
						 	},{
						 	field:'content',
						 	title:'Memo',
						 	},{
						 	field:'users',
						 	title:'Oper',
						 	},{
						 	field:'memodate',
						 	title:'Time',
						 	}]],
					});
				
				$("#vehiclememo_search").click(function(){
					$("#vehiclememo_datagrid").datagrid('load', serializeObject($("#vehiclememo_form")));
				});
				
		});	
	</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height: 65px" border="false">
				<form id="vehiclememo_form" name="vehiclememo_form" class="datagrid-toolbar" method="post">
					<div style="width: 980px">
					<table id="vehiclememo_table">
						<tr>
							<th>UOO:</th>
							<td>
								<input type="text" id="vehiclememo_uoo" name="uoo" style="width: 145px"/>
							</td>	
							<th>Booking No.:</th>
							<td>
								<input type="text" id="vehiclememo_bookNum" name="bookNum" style="width: 145px"/>
							</td>
							<th>VIN# last 6 digits:</th>
							<td>
								<input type="text" name="vin" id="vehiclememo_vin" style="width: 145px"/>
							</td>
							<td>
								<a id="vehiclememo_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>				
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="vehiclememo_datagrid"></table>
			</div>    				
		</div>
