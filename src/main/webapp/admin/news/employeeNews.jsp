<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
	$("#employeeNews_datagrid").datagrid({
					 url:"newsAction!findEmployeesNews.action",
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
            				str += "<a class=\"ed\" onclick=\"editEmployeeNews('"+row.id+"')\" href=\"#\">edit</a><br /><a class=\"de\" onclick=\"deleteEmployeeNews('"+row.id+"')\">delete</a>";
            				return str;
					 	}
					 	
					 	}]],
					 	toolbar : [ {
							text : 'Add Emergency',
							iconCls : 'icon-add',
							handler : function() {
								employeeNews_add();
							}
						},{
							text : 'refresh',
							iconCls : 'icon-reload',
							handler : function() {
								employeeNews_reload();
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
			
				employeeNews_reload = function(){
					$("#employeeNews_datagrid").datagrid('reload');
				};
				
				//-------------------添加 界面
				employeeNews_add = function(){
					window.open("${pageContext.request.contextPath}/admin/news/addEmployeeNews.jsp");
				};
				
				editEmployeeNews = function(id){
				//-------------------添加 界面
					window.open("${pageContext.request.contextPath}/admin/news/editEmployeeNews.jsp?id="+id+"");
				};
				
				deleteEmployeeNews = function(id){
					$.ajax({
						url : 'newsAction!deleteEmployeeNews.action',
						data : {"id" : id},
						type : 'post',
						dataType : 'text',
						success : function(data){
							var obj = $.parseJSON(data);
							$("#employeeNews_datagrid").datagrid('load');
							$.messager.show({
								title:'Message',
								msg:'Delete Success',
								timeout:5000,
								showType:'slide'
							});
						}
					});
				};
				
			changeEmployeeNewsPlace = function (d){
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
	
			$("#employeeNews_search").click(function(){
				$("#employeeNews_datagrid").datagrid('load', serializeObject($("#employeeNews_form")));
			});
			
	});	
</script>
		<div class="easyui-layout" fit="true"> 
			<div data-options="region:'north',title:'search',split:true" style="height: 95px" border="false">
				<form id="employeeNews_form" name="employeeNews_form" class="datagrid-toolbar" method="post">
					<div style="width: 980px">
					<table id="employeeNews_table">
						<tr>
							<th>Title:</th>
							<td>
								<input type="text" name="title" class="input-small" autocomplete="off" id="employeeNews_title" style="width: 200px"/>
							</td>
							<th>Content:</th>
							<td>
								<input type="text" name="content" class="input-small" id="employeeNews_content" autocomplete="off" style="width: 200px"/>
							</td>
						</tr>	
						<tr>
							<th>newsDate:</th>
							<td><input editable="false" type="text" id="employeeNews_datefrom" name="newsdatefrom" class="easyui-datebox" style="width: 145px"/>至<input editable="false" type="text" id="employeeNews_dateto" name="newsdateto" class="easyui-datebox" style="width: 145px"/></td>	
							<td>
								<a id="employeeNews_search" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search'">search</a>
							</td>
						</tr>			
					</table>
					</div>
				</form>
			</div>  
			<div data-options="region:'center',border:false">
				 <table id="employeeNews_datagrid"></table>
			</div>    				
		</div>
