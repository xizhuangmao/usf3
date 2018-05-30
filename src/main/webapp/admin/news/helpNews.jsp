<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#helpNews_datagrid").datagrid({
					 url:"newsAction!findHelpsNews.action",
					 border:false,
					 fitColumns:true,
					 pagination:true,
					 pageSize:10,
					 pageList:[10,20,50,100],
					 idField:'id',
					 sortName:'newsdate',
					 sortOrder:'desc',
					 checkOnSelect:false,
					 selectOncheck:false,
					 fit:true,
					 columns:[[{
					 	field:'title',
					 	title:'Title',
					 	width:'8%',
					 	sortable:true,
					 	},{
					 	field:'content',
					 	title:'Content',
					 	width:'30%',
					 	},{
					 	field:'newsdate',
					 	title:'NewsDate',
					 	},{
					 	field:'str',
					 	title:'Operation',
					 	formatter : function(value, row, index){
            				var str = "";
            				str += "<a class=\"ed\" onclick=\"editHelpNews('"+row.id+"')\" href=\"#\">edit</a><br /><a class=\"de\" onclick=\"deleteHelpNews('"+row.id+"')\">delete</a>";
            				return str;
					 	}
					 	
					 	}]],
					 	toolbar : [ {
							text : 'Add HelpNews',
							iconCls : 'icon-add',
							handler : function() {
								helpNews_add();
							}
						}, {
							text : 'refresh',
							iconCls : 'icon-reload',
							handler : function() {
								helpNews_reload();
							}
						} ],
			
						onLoadSuccess:function(data){ 
							$(".ed").linkbutton({ 
								text:'edit', 
								plain:true, 
								iconCls:'icon-edit',
							});
							$(".de").linkbutton({ 
								text:'delete',
								plain:true, 
								iconCls:'icon-remove',
							});
						}
			
					});
			
				helpNews_reload = function(){
					$("#helpNews_datagrid").datagrid('reload');
				};
			
				//-------------------添加 界面
				helpNews_add = function(){
					window.open("${pageContext.request.contextPath}/admin/news/addHelpNews.jsp");
				};
				
				editHelpNews = function(id){
				//-------------------添加 界面
					window.open("${pageContext.request.contextPath}/admin/news/editHelpNews.jsp?id="+id+"");
				};
				
				deleteHelpNews = function(id){
					$.ajax({
						url : 'newsAction!deleteHelpNews.action',
						data : {"id" : id},
						type : 'post',
						dataType : 'text',
						success : function(data){
							var obj = $.parseJSON(data);
							$("#helpNews_datagrid").datagrid('load');
							$.messager.show({
								title:'Message',
								msg:'Delete Success',
								timeout:5000,
								showType:'slide'
							});
						}
					});
				};
	
			$("#helpNews_search").click(function(){
				$("#helpNews_datagrid").datagrid('load', serializeObject($("#helpNews_form")));
			});
			
	});	
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height: 95px" border="false">
				<form id="helpNews_form" name="helpNews_form" class="datagrid-toolbar" method="post">
					<div style="width: 980px">
					<table id="helpNews_table">
						<tr>
							<th>Title:</th>
							<td>
								<input type="text" name="title" class="input-small" autocomplete="off" id="helpNews_title" style="width: 200px"/>
							</td>
							<th>Content:</th>
							<td>
								<input type="text" name="content" class="input-small" id="helpNews_content" autocomplete="off" style="width: 200px"/>
							</td>
						</tr>	
						<tr>
							<th>newsDate:</th>
							<td><input editable="false" type="text" id="helpNews_datefrom" name="newsdatefrom" class="easyui-datebox" style="width: 145px"/>至<input editable="false" type="text" id="helpNews_dateto" name="newsdateto" class="easyui-datebox" style="width: 145px"/></td>	
							<td>
								<a id="helpNews_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>			
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="helpNews_datagrid"></table>
			</div>    				
		</div>
