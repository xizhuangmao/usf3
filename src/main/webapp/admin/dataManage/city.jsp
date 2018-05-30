<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin_dateManage_city" class="easyui-layout" data-options="fit:true,border:false,collapsible:false"> 
	<div data-options="region:'north',border:0 " style="height:85px;background:#eee;">
		<form id="admin_dataManage_city_from" method="post">
			<table>
				<tr>
	    			<td>State: </td>
					<td><input id="admin_dateManage_city_state" class="easyui-combobox" name="stateId" style="width:205px;" data-options="editable:false"/></td>
				</tr>
				<tr>
					<td>City :</td>
					<td><input id="admin_dateManage_city_city" name="cityId" class="easyui-combobox" style="width:205px;" data-options="editable:false"/></td>
				</tr>
				<tr>	
					<td><a id="admin_dateManage_city_search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Search</a></td>
				</tr>
			</table>
		</form>
	</div>   
    <div data-options="region:'center',border:false">
    	<table id="admin_dateManage_city_datagrid">
    	
    	</table>
    </div>   
</div> 


<script type="text/javascript">
$(function() {
	$("#admin_dateManage_city_datagrid").datagrid({
		url : '${pageContext.request.contextPath}/cityAction!getCityDataGrid.action',
		border : false,
		fit : true,
		idField : 'id',
		fitColumns : true,
		sortOrder : 'desc',
		pagination : true,
		pageSize : 10,
		pageList : [ 10, 20, 50, 100 ],
		columns : [ [ {
			field : 'id',
			hidden: true,
		},{
			field : 'countryId',
			hidden: true,
		},{
			field : 'state',
			title : 'State',
			width : '20%',
		},{
			field : 'city',
			title : 'City',
			width : '20%',
			sortable : true
		},{
			field : 'shortname',
			title : 'City Short',
			width : '20%',
			sortable : true
		},{
			field : 'nickname',
			title : 'Nickname',
			width : '20%',
			sortable : true
		},{
			field : 'opt',
			title : 'Operation',
			formatter : function(value, row, index) {
				str = '';
				str += $.formatString('<img onclick="city_datagrid_Edit(\'{0}\');" src="{1}" title="Edit"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/pencil01.png');
				str += $.formatString('<img onclick="city_datagrid_Delete(\'{0}\');" src="{1}" title="Delete"/>', row.id, '${pageContext.request.contextPath}/style/images/extjs_icons/cancel.png');
				return str;
			}
		}
		] ],
		toolbar : [ {
			iconCls : 'icon-reload',
			handler : function() {
				$("#admin_dateManage_city_datagrid").datagrid('reload');
			}
			},{
			text : 'New Carrier',
			iconCls : 'icon-add',
			handler : function() {
				admin_dateManage_city_datagrid_add();
				}
			} ]
		});
		
		$.ajax({
			url:'stateAction!getStateName.action',
			type:'post',
			dataType:'text',
			success:function(data){
				var obj = $.parseJSON(data);
				obj.unshift({id:'ALL',state:'ALL'});
				$("#admin_dateManage_city_state").combobox({
					valueField: 'id',    
       				textField: 'state',
       				value: 'ALL',
        			data: obj,
				});
			}
		});
		$.ajax({
			url:'cityAction!getCityName.action',
			type:'post',
			dataType:'text',
			success:function(data){
				var obj = $.parseJSON(data);
				obj.unshift({id:'ALL',city:'ALL'});
				$("#admin_dateManage_city_city").combobox({
					valueField: 'id',    
       				textField: 'city',
       				value: 'ALL',
        			data: obj,
				});
			}
		});
	});

	admin_dateManage_city_datagrid_add = function(){
		var d =$('<div/>').dialog({
			width:300,
			heigth:200,
			title:"City Add",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/cityAdd.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});
		changeCityPanalPlace(d);
	};
	
	var city_datagrid_Edit_id;
	city_datagrid_Edit = function(id){
		city_datagrid_Edit_id = id;
		var d =$('<div/>').dialog({
			width:300,
			heigth:200,
			title:"City Edit",
			modal:true,
			href:'${pageContext.request.contextPath}/admin/dataManage/cityEdit.jsp',
			onClose:function(){
				d.dialog('destroy');
			},
		});
		changeCityPanalPlace(d);
	};
	
	city_datagrid_Delete = function(id){
		$.messager.confirm('Warning!', 'Are you sure to delete this City?', function (r) {  
            if (r) { 
          	  $.ajax({
        	        type: "post",
        	        dataType: "json",
        	       	url: "cityAction!deleteCity.action",
        	        data: {"id":id},
        	        success: function (data) {
        	        	if(data.success){
							$.messager.show({
								title:'Message',
								msg:data.msg,
								timeout:5000,
								showType:'slide'
							});
        	        	}
        	        	$("#admin_dateManage_city_datagrid").datagrid('reload');
        	        }
        	    });
            }  
        });
	};
	//------------------------条件查询-----------------------------------
	
	$('#admin_dateManage_city_search').bind('click',function(){
		$("#admin_dateManage_city_datagrid").datagrid('load', serializeObject($("#admin_dataManage_city_from")));
	});
	
	changeCityPanalPlace = function (d){
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

