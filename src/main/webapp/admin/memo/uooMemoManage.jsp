<%@ page language="java" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	$(function(){
		$("#uoomemo_datagrid").datagrid({
						 url:"memoAction!findAllUooMemo.action",
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
						 	field:'uoo',
						 	title:'UOO',
						 	sortable:true,
						 	},{
						 	field:'booknum',
						 	title:'Booking No.',
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
				
				$("#uoomemo_search").click(function(){
					$("#uoomemo_datagrid").datagrid('load', serializeObject($("#uoomemo_form")));
				});
				
		});	
	</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height: 65px" border="false">
				<form id="uoomemo_form" name="uoomemo_form" class="datagrid-toolbar" method="post">
					<div style="width: 980px">
					<table id="uoomemo_table">
						<tr>
							<th>UOO:</th>
							<td>
								<input type="text" id="uoomemo_uoo" name="uoo" style="width: 145px"/>
							</td>	
							<th>Booking No.:</th>
							<td>
								<input type="text" id="uoomemo_bookNum" name="bookNum" style="width: 145px"/>
							</td>
							<td>
								<a id="uoomemo_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>				
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="uoomemo_datagrid"></table>
			</div>    				
		</div>
