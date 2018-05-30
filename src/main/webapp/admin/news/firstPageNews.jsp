<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
$(function(){
	$("#firstPageNews_datagrid").datagrid({
					 url:"firstPageAction!findFirstPage.action",
					 border:false,
					 fitColumns:true,
					 pagination:true,
					 pageSize:10,
					 pageList:[10,20,50,100],
					 idField:'id',
					 sortName:'tel',
					 sortOrder:'desc',
					 checkOnSelect:false,
					 selectOncheck:false,
					 fit:true,
					 columns:[[{
					 	field:'id',
					 	title:'编号',
					 	checkbox:true,
					 	},{
					 	field:'content',
					 	title:'Content',
					 	width:'40%',
					 	sortable:true,
					 	},{
					 	field:'str',
					 	title:'Operation',
					 	formatter : function(value, row, index){
            				var str = "";
            				str += "<a class=\"ed\" onclick=\"editfirstPageNews('"+row.id+"','"+row.firstId+"')\" href=\"#\">edit</a>";
            				if(row.firstId == "introduce"){
            					str = "<a class=\"ed\" onclick=\"editIntroduce('"+row.id+"')\" href=\"#\">edit</a>";
            				}
            				return str;
					 	}
					 	
					 	}]],
						toolbar : [ {
							text : 'refresh',
							iconCls : 'icon-reload',
							handler : function() {
								firstPageNews_reload();
							}
						} ],
						onLoadSuccess:function(data){ 
							$(".ed").linkbutton({ 
								text:'edit', 
								plain:true, 
								iconCls:'icon-edit',
							});
						}
			
					});
				
				firstPageNews_reload = function(){
					$("#firstPageNews_datagrid").datagrid('reload');
				};
				
				editfirstPageNews = function(id,firstId){
					window.open("${pageContext.request.contextPath}/admin/news/addOrUpdateFirstPage.jsp?id="+id+"");
				};
				
				editIntroduce = function(id){
					window.open("${pageContext.request.contextPath}/admin/news/addOrUpdateIntroduce.jsp?id="+id+"");
				};
				
			changeEmployeeNewsPlace = function (d){
				var browserHeight = $(window).height();  //游览器
				var browserwidth = $(window).width();
				var width = d.panel('options').width;//获取容器的宽
				if(browserwidth>(width+200)){
					if(browserHeight>700){
						d.panel('resize',{
							left : 150,
							top  : 0,
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
						top  : 0,
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
			<div data-options="region:'center',border:false">
				 <table id="firstPageNews_datagrid"></table>
			</div>    				
		</div>
