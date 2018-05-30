<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_makeManage" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:80px;background:#eee;">
		<form id="admin_dateManage_makeManage_from" method="post">
			<table>
				<tr>
					<td>Country :</td>
					<td><input id="admin_dateManage_makeManage_from_country" class="easyui-combobox" name="countryId" value="Please Select" style="width:150px;"
	    							data-options="valueField:'id', editable:false,textField:'country',url:'${pageContext.request.contextPath}/countryAction!getCountryName.action',
	    											" /></td>
					<td><a id="admin_dateManage_makeManage_from_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
					<td><a id="admin_dateManage_makeManage_from_clean" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Clean</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_makeManage_datagrid">
    	
    	</table>
    </div>   
</div> 

<script type="text/javascript">
	$(function(){
		$("#admin_dateManage_makeManage_datagrid").datagrid({
			url : '${pageContext.request.contextPath}/makeAction!getMakeDataGrid.action',
			border : false,
			fit : true,
			idField : 'id',
			fitColumns : true,
			sortName : 'make',
			sortOrder : 'desc',
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 20, 50, 100 ],
			columns : [ [ {
				field : 'id',
				hidden: true,
			},{
				field : 'make',
				title : 'Make',
				width : '20%',
				sortable : true
			},{
				field : 'shortname',
				title : 'Make Short',
				width : '20%',
				sortable : true
			},{
				field : 'nickname',
				title : 'Nickname',
				width : '20%',
				sortable : true
			},{
				field : 'countryName',
				title : 'Country',
				width : '20%',
			},{
				field : 'opt',
				title : 'Operation',
				formatter : function(value, row, index) {
					str = '';
					str += $.formatString('<img onclick="admin_dateManage_makeManage_makeEdit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
					str += $.formatString('<img onclick="admin_dateManage_makeManage_makeDelete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
					return str;
				}
			}
			] ],
			toolbar : [ {
				iconCls : 'icon-reload',
				handler : function() {
					$("#admin_dateManage_makeManage_datagrid").datagrid('reload');
				}
				},{
				text : 'New Make',
				iconCls : 'icon-add',
				handler : function() {
					admin_dateManage_makeManage_makeAdd();
					}
				} ]
			});
	});
	
	$('#admin_dateManage_makeManage_from_search').bind('click',function(){
			var countryId = $("#admin_dateManage_makeManage_from_country").combo('getValue');
			if(countryId=="Please Select" ||countryId==undefined){
				countryId = "";
			}
			$("#admin_dateManage_makeManage_datagrid").datagrid({
				queryParams: {
					countryId:countryId,
				}
			});	
			
		});

	$('#admin_dateManage_makeManage_from_clean').bind('click',function(){
		$("#admin_dateManage_makeManage_from_country").combobox('setValue', 'Please Select');
	});
	
	admin_dateManage_makeManage_makeAdd = function (){
		var d =$('<div/>').dialog({
			width:350,
			heigth:200,
			title:"Make Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/makeAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});	
		changeMakePanalPlace(d);
	};


	admin_dateManage_makeManage_makeEdit = function(id){
		var d =$('<div/>').dialog({
			width:350,
			heigth:200,
			title:"Make Edit",
			modal:true,
			href:"${pageContext.request.contextPath}/admin/dataManage/makeEdit.jsp?id="+id+"",
			onClose:function(){
				d.dialog('destroy');
			},
		});
		changeMakePanalPlace(d);
	};

	admin_dateManage_makeManage_makeDelete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this Make?', function (r) {  
            if (r) { 
          		$.ajax({
        	        type: "post",
        	        dataType: "json",
        	       	url: "makeAction!deleteMake.action",
        	        data: {"id":id},
        	        success: function (data) {
        	        	if(data.success){
        	        		$.messager.show({
								title:'Message',
								msg:data.msg,
								timeout:5000,
								showType:'slide'
							});
        	        		$("#admin_dateManage_makeManage_datagrid").datagrid('reload');
        	        	}
        	        }
        	    });
            }  
        });
	};

	changeMakePanalPlace = function (d){
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

</script>