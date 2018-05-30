<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">

	//选中的角色id集合
	var admin_users_roleIds ='';
	
	//选中的公司id集合
	var admin_users_companyIds ='';
	
	//选中的仓库id集合
	var admin_users_whesIds ='';
	
	//选中的nvoccid集合
	var admin_users_nvoccIds ='';
	
	//选中的航运公司id集合
	var admin_users_carrierIds ='';
	
	//选中的卡车公司id集合
	var admin_users_truckIds ='';
	
	//选中的用户id
	//var admin_users_selectId ='';
	
	//选中的用户对象
	var admin_users_selectUser ='';

	$('#admin_users_datagrid').datagrid({
		url : 'userAction!datagrid.action',
		fit : true,
		border : false,
		idField : 'id',
		pagination : true,
		fitColumns : true,
		pageSize : 10,
		pageList : [ 10, 20, 50 ,100],
		rownumbers : true,
		sortName : 'logname',
		sortOrder : 'desc',
		checkOnSelect:true,
		selectOnCheck:true,
		frozenColumns : [ [ {
			field : 'id',
			title : 'id',
			width : 150,
			checkbox : true
		}, {
			field : 'logname',
			title : 'Logname',
			width : 200,
			sortable:true
		} ] ],
		columns : [ [ {
			field : 'roles',
			title : 'Roles',
			width : 150,
			formatter:function(value,row,index){
				var showvalue="";
				for(i=0;i<row.roles.length;i++){
					showvalue = showvalue+row.roles[i].name+"<br>";
				}
				return showvalue;
			}
		},  {
			field : 'companies',
			title : 'Company',
			width : 150,
			formatter:function(value,row,index){
				var showvalue="";
				for(i=0;i<row.companies.length;i++){
					showvalue = showvalue+row.companies[i].fullname+"<br>";
				}
				return showvalue;
			}
		}, {
			field : 'wheses',
			title : 'Whes',
			width : 150,
			formatter:function(value,row,index){
				var showvalue="";
				for(i=0;i<row.wheses.length;i++){
					showvalue = showvalue+row.wheses[i].fullname+"<br>";
				}
				return showvalue;
			}
		}, {
			field : 'nvoccs',
			title : 'Nvocc',
			width : 150,
			formatter:function(value,row,index){
				var showvalue="";
				for(i=0;i<row.nvoccs.length;i++){
					showvalue = showvalue+row.nvoccs[i].fullname+"<br>";
				}
				return showvalue;
			}
		},{
			field : 'carriers',
			title : 'Carrier',
			width : 150,
			formatter:function(value,row,index){
				var showvalue="";
				for(i=0;i<row.carriers.length;i++){
					showvalue = showvalue+row.carriers[i].fullname+"<br>";
				}
				return showvalue;
			}
		},{
			field : 'trucks',
			title : 'Truck',
			width : 150,
			formatter:function(value,row,index){
				var showvalue="";
				for(i=0;i<row.trucks.length;i++){
					showvalue = showvalue+row.trucks[i].fullname+"<br>";
				}
				return showvalue;
			}
		},{
			field : 'active',
			title : 'Active',
			width : 150,
			sortable:true,
			formatter:function(value,row,index){
				if(row.active=="1"){
					return "active";
				}else{
					return "not active";
				}
				
			}
		} ,  {
			field : 'datein',
			title : 'Datein',
			width : 150,
			sortable:true
		} ] ],
		toolbar: [{
			text:'add',
			iconCls:'icon-add',
			handler:function(){
				admin_users_addFun();
			}
		},'-',{
			text:'delete',
			iconCls:'icon-remove',
			handler:function(){
				admin_users_remove();
			}
		},'-',{
			text:'edit',
			iconCls:'icon-edit',
			handler:function(){
				admin_users_editFun();
			}
		},'-',{
			text:'role',
			iconCls:'icon-tip',
			handler:function(){
				admin_users_roleFun();
			}
		},'-',{
			text:'company',
			iconCls:'icon-tip',
			handler:function(){
				admin_users_companyFun();
			}
		},'-',{
			text:'warehouse',
			iconCls:'icon-tip',
			handler:function(){
				admin_users_whesFun();
			}
		},'-',{
			text:'nvocc',
			iconCls:'icon-tip',
			handler:function(){
				admin_users_nvoccFun();
			}
		},'-',{
			text:'carrier',
			iconCls:'icon-tip',
			handler:function(){
				admin_users_carrierFun();
			}
		},'-',{
			text:'truck',
			iconCls:'icon-tip',
			handler:function(){
				admin_users_truckFun();
			}
		},'-'],
		onLoadSuccess:function(data){  
			//增加onmouseover事件
		    //$('#admin_users_datagrid').datagrid('doCellTip',{cls:{'background-color':'#E0FFFF'},delay:1000});   
		} 
	});
	
	//编辑用户
	function admin_users_editFun(){
		var rows = $("#admin_users_datagrid").datagrid('getChecked');
		if(rows.length==1){
			var editUserDialog =$('<div/>').dialog({
				width:600,
				heigth:500,
				left:250,
				top:200,
				title:"Edit user",
				modal:true,
				href:'${pageContext.request.contextPath}/admin/usersEdit.jsp',
				buttons:[{
					text:'confirm',
					iconCls:'icon-ok',
					handler:function(){
						$('#admin_users_editForm').form('submit',{
							url : '${pageContext.request.contextPath}/userAction!edit.action',
							success : function(r) {
								var obj = $.parseJSON(r);
								if (obj.success) {
									editUserDialog.dialog('close');
									
								    $('#admin_users_datagrid').datagrid('updateRow',{
								    	index: $('#admin_users_datagrid').datagrid('getRowIndex',rows[0]),
								    	row: obj.obj
								    });
																	
								}
								$.messager.show({
									title : 'message',
									msg : obj.msg
								});
							}
						});
					}
				}],
				onClose:function(){
					editUserDialog.dialog('destroy');
				},
				onLoad:function(){
					//加载页面时获取用户数据
					$.post('${pageContext.request.contextPath}/userAction!get.action', {
						logname : rows[0].logname
					}, function(result) {
						if (result.success) {		
							$('#admin_users_editForm').form('load',result.obj);
						}else{
							$.messager.alert('Warning',result.msg);
							editUserDialog.dialog('destroy');
						}
					}, 'json');
										
				}
			});
		}
		else{
			$.messager.alert('message ','please select only one user');
		}
		
	}
	
	//增加用户
	function admin_users_addFun(){
			var adduser =$('<div/>').dialog({
				width:600,
				heigth:500,
				left:250,
				top:200,
				title:"Add user",
				modal:true,
				href:'${pageContext.request.contextPath}/admin/usersAdd.jsp',
				buttons:[{
					text:'add',
					iconCls:'icon-add',
					handler:function(){
						$('#admin_users_addForm').form('submit',{
							url : '${pageContext.request.contextPath}/userAction!add.action',
							success : function(r) {
								var obj = $.parseJSON(r);
								if (obj.success) {
									$('#admin_users_datagrid').datagrid('insertRow',{
										index: 0,
										row: obj.obj
									});
									
									
									//复制新增加的用户id，以便弹出框使用
									admin_users_selectUser='';
									admin_users_selectUser=obj.obj;
									
									$("#admin_users_datagrid").datagrid("clearChecked");
									
									adduser.dialog('destroy');	
										
									//选择新增的用户为选中的用户，以便下一步操作
									var rowid = $('#admin_users_datagrid').parent().find("input[value='"+admin_users_selectUser.id+"']").parent().parent().parent().attr("datagrid-row-index");
									$('#admin_users_datagrid').datagrid("checkRow",rowid);
									
									$("<div id='admin_users_usersOpserChooserDiv'/>").dialog({
									    title: 'What do you want to do next',
									    width: 300,
									    height: 400,
									    href: '${pageContext.request.contextPath}/admin/usersOperChoose.jsp',
									    modal: true,
									    onClose:function(){
											$(this).dialog('destroy');
										}
									});
									$.messager.show({
										title : 'message',
										msg : obj.msg
									});
								}else{
									$.messager.alert('Warning',obj.msg);
								}
								
							}
						});
					}
				}],
				onClose:function(){
					$(this).dialog('destroy');
				}
			});	
	}
	
	//1、角色分配
	function admin_users_roleFun(){
		
		admin_users_selectUser='';
		
		var rows = $("#admin_users_datagrid").datagrid('getChecked');
		
		if(rows.length == 1){
		
			admin_users_selectUser =rows[0];

			$.modalDialog({
					title : 'grant roles to '+rows[0].logname,
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/admin/usersRole.jsp',
					buttons : [ {
						text : 'confirm',
						handler : function() {
							admin_users_rolesubmit();
						}
					} ]
			});
		}
		else{
			$.messager.alert('message','please select only one user');
		}
		
	}
	
	//提交选中角色
	function admin_users_rolesubmit(){
		
		admin_users_roleIds ='';
		
		var rows = $("#admin_usersRole_datagrid").datagrid('getChecked');

		$.messager.progress({
			text : 'Submitting.Please wait....'
		});
		
		//获取已有的角色，打勾
		for(i=0;i<rows.length;i++){
			admin_users_roleIds += rows[i].id+",";
		}
		$.post('${pageContext.request.contextPath}/userAction!editRole.action', {
			id : admin_users_selectUser.id,roleids:admin_users_roleIds
		}, function(result) {
			if (result) {
				$('#admin_users_datagrid').datagrid('updateRow',{
			    	index: $('#admin_users_datagrid').datagrid('getRowIndex',admin_users_selectUser),
			    	row: result.obj
			    });
				
				$.messager.show({
					title:'message',
					msg:result.msg
				});
			}
		}, 'json');
		
		$.messager.progress('close');
		$.modalDialog.handler.dialog('close');		
	}
	
	//1.5、公司分配
	function admin_users_companyFun(){

		admin_users_selectUser='';
		
		var rows = $("#admin_users_datagrid").datagrid('getChecked');

		if(rows.length == 1){

			admin_users_selectUser = rows[0];

			$.modalDialog({
					title : 'grant companies to '+rows[0].logname,
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/admin/usersCompany.jsp',
					buttons : [ {
						text : 'confirm',
						handler : function() {
							admin_users_companiessubmit();
						}
					} ]
			});
		}
		else{
			$.messager.alert('message','please select only one user');
		}
		
	}
	
	//提交选中公司
	function admin_users_companiessubmit(){
		
		admin_users_companyIds ='';
		
		var rows = $("#admin_usersCompany_datagrid").datagrid('getChecked');
		
		$.messager.progress({
			text : 'Submitting.Please wait....'
		});
		
		//获取已有的公司，打勾
		for(i=0;i<rows.length;i++){
			admin_users_companyIds += rows[i].id+",";
		}
		$.post('${pageContext.request.contextPath}/userAction!editCompany.action', {
			id : admin_users_selectUser.id,companyids:admin_users_companyIds
		}, function(result) {
			if (result) {

				$('#admin_users_datagrid').datagrid('updateRow',{
			    	index: $('#admin_users_datagrid').datagrid('getRowIndex',admin_users_selectUser),
			    	row: result.obj
			    });
				
				$.messager.show({
					title:'message',
					msg:result.msg
				});
			}
		}, 'json');
		
		$.messager.progress('close');
		$.modalDialog.handler.dialog('close');		
	}
	
	//2、仓库分配
	function admin_users_whesFun(){

		admin_users_selectUser='';
		
		var rows = $("#admin_users_datagrid").datagrid('getChecked');

		if(rows.length == 1){

			admin_users_selectUser = rows[0];

			$.modalDialog({
					title : 'grant warehouse to '+rows[0].logname,
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/admin/usersWhes.jsp',
					buttons : [ {
						text : 'confirm',
						handler : function() {
							admin_users_whesesubmit();
						}
					} ]
			});
		}
		else{
			$.messager.alert('message','please select only one user');
		}
		
	}
	
	//提交选中仓库
	function admin_users_whesesubmit(){
		
		admin_users_whesIds ='';
		
		var rows = $("#admin_usersWhes_datagrid").datagrid('getChecked');
		
		$.messager.progress({
			text : 'Submitting.Please wait....'
		});
		
		//获取已有的仓库，打勾
		for(i=0;i<rows.length;i++){
			admin_users_whesIds += rows[i].id+",";
		}
		$.post('${pageContext.request.contextPath}/userAction!editWhes.action', {
			id : admin_users_selectUser.id,whesids:admin_users_whesIds
		}, function(result) {
			if (result) {
				$('#admin_users_datagrid').datagrid('updateRow',{
			    	index: $('#admin_users_datagrid').datagrid('getRowIndex',admin_users_selectUser),
			    	row: result.obj
			    });
				
				$.messager.show({
					title:'message',
					msg:result.msg
				});
			}
		}, 'json');
		
		$.messager.progress('close');
		$.modalDialog.handler.dialog('close');		
	}
	
	//3、nvocc分配
	function admin_users_nvoccFun(){
		
		admin_users_selectUser='';
		
		var rows = $("#admin_users_datagrid").datagrid('getChecked');
		
		if(rows.length == 1){

			admin_users_selectUser = rows[0];

			$.modalDialog({
					title : 'grant nvocc to '+rows[0].logname,
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/admin/usersNvocc.jsp',
					buttons : [ {
						text : 'confirm',
						handler : function() {
							admin_users_nvoccsubmit();
						}
					} ]
			});
		}
		else{
			$.messager.alert('message','please select only one user');
		}
		
	}
	
	//提交选中nvocc
	function admin_users_nvoccsubmit(){
		
		admin_users_nvoccIds ='';
		
		var rows = $("#admin_usersNvocc_datagrid").datagrid('getChecked');
		
		$.messager.progress({
			text : 'Submitting.Please wait....'
		});
		
		//获取已有的nvocc，打勾
		for(i=0;i<rows.length;i++){
			admin_users_nvoccIds += rows[i].id+",";
		}
		$.post('${pageContext.request.contextPath}/userAction!editNvocc.action', {
			id : admin_users_selectUser.id,nvoccids:admin_users_nvoccIds
		}, function(result) {
			if (result) {
				$('#admin_users_datagrid').datagrid('updateRow',{
			    	index: $('#admin_users_datagrid').datagrid('getRowIndex',admin_users_selectUser),
			    	row: result.obj
			    });
				
				$.messager.show({
					title:'提示',
					msg:result.msg
				});
			}
		}, 'json');
		
		$.messager.progress('close');
		$.modalDialog.handler.dialog('close');		
	}
	
	//4、航运公司分配
	function admin_users_carrierFun(){
		
		admin_users_selectUser='';
		
		var rows = $("#admin_users_datagrid").datagrid('getChecked');
		
		if(rows.length == 1){

			admin_users_selectUser = rows[0];

			$.modalDialog({
					title : 'grant carrier to '+rows[0].logname,
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/admin/usersCarrier.jsp',
					buttons : [ {
						text : 'confirm',
						handler : function() {
							admin_users_carriersubmit();
						}
					} ]
			});
		}
		else{
			$.messager.alert('message','please select only one user');
		}
		
	}
	
	//提交选中订舱公司
	function admin_users_carriersubmit(){
		
		admin_users_carrierIds ='';
		
		var rows = $("#admin_usersCarrier_datagrid").datagrid('getChecked');
		
		$.messager.progress({
			text : 'Submitting.Please wait....'
		});
		
		//获取已有的角色，打勾
		for(i=0;i<rows.length;i++){
			admin_users_carrierIds += rows[i].id+",";
		}
		$.post('${pageContext.request.contextPath}/userAction!editCarrier.action', {
			id : admin_users_selectUser.id,carrierids:admin_users_carrierIds
		}, function(result) {
			if (result) {
				$('#admin_users_datagrid').datagrid('updateRow',{
			    	index: $('#admin_users_datagrid').datagrid('getRowIndex',admin_users_selectUser),
			    	row: result.obj
			    });
				
				$.messager.show({
					title:'message',
					msg:result.msg
				});
			}
		}, 'json');
		
		$.messager.progress('close');
		$.modalDialog.handler.dialog('close');		
	}
	
	//5、卡车公司分配
	function admin_users_truckFun(){
		
		admin_users_selectUser='';
		
		var rows = $("#admin_users_datagrid").datagrid('getChecked');
		
		if(rows.length == 1){

			admin_users_selectUser = rows[0];

			$.modalDialog({
					title : 'grant truck to '+rows[0].logname,
					width : 500,
					height : 300,
					href : '${pageContext.request.contextPath}/admin/usersTruck.jsp',
					buttons : [ {
						text : 'confirm',
						handler : function() {
							admin_users_trucksubmit();
						}
					} ]
			});
		}
		else{
			$.messager.alert('message','please select only one user');
		}
		
	}
	
	//提交选中卡车公司
	function admin_users_trucksubmit(){
		
		admin_users_truckIds ='';
		
		var rows = $("#admin_usersTruck_datagrid").datagrid('getChecked');
		
		$.messager.progress({
			text : 'Submitting.Please wait....'
		});
		
		//获取已有的卡车公司，打勾
		for(i=0;i<rows.length;i++){
			admin_users_truckIds += rows[i].id+",";
		}
		$.post('${pageContext.request.contextPath}/userAction!editTruck.action', {
			id : admin_users_selectUser.id,truckids:admin_users_truckIds
		}, function(result) {
			if (result) {
				$('#admin_users_datagrid').datagrid('updateRow',{
			    	index: $('#admin_users_datagrid').datagrid('getRowIndex',admin_users_selectUser),
			    	row: result.obj
			    });
				
				$.messager.show({
					title:'message',
					msg:result.msg
				});
			}
		}, 'json');
		
		$.messager.progress('close');
		$.modalDialog.handler.dialog('close');		
	}
	
	
	
	function admin_users_searchFun(){
	
		$("#admin_users_datagrid").datagrid("load",serializeObject($('#admin_users_form')));
	}
	
	function admin_users_searchClear(){
		$("#admin_users_layout input[name=logname]").val('');
		$("#admin_users_datagrid").datagrid("load",{});
	}
	
	
	function admin_users_remove(){
		var rows = $("#admin_users_datagrid").datagrid('getSelections');
		//直接返回('1','2')
		var ids ="";
		if(rows.length>0){
		    $.messager.confirm('confirm', 'delete the checked？', function(r){
		    	if (r){
		    		
		    		for(var i=0;i<rows.length;i++){

		    			ids +=rows[i].id+",";
		    			
		    		}
		    		
		    		$.ajax({
		    			url:'${pageContext.request.contextPath}/userAction!remove.action',
		    			data:{ids:ids},
		    			dataType:'json',
		    			type: 'POST',
		    			success:function(re){
		    				$("#admin_users_datagrid").datagrid('load');
		    				$("#admin_users_datagrid").datagrid('unselectAll');
		    				$.messager.show({
		    					title:'message',
		    					msg:re.msg
		    				});
		    			}
		    		});
		    	}
		    });
		}else{
			
		}
	}
</script>

<div id="admin_users_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'serach',border:false" style="height:100px;">
		<form id="admin_users_form">
			types：
			<select class="easyui-combobox" name="types" style="width:200px;">
			    <option value="all">all</option>
			    <option value="all">company</option>
			    <option value="whes">whes</option>
			    <option value="nvocc">nvocc</option>
			    <option value="carrier">carrier</option>
			    <option value="truck">truck</option>
			    <option value="customer">customer</option>
			    <option value="employee">employee</option>
			</select>
			fullname：
			<input name="fullname" /> 
			logname：
			<input name="logname" /> 
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="admin_users_searchFun()">search</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="admin_users_searchClear()">clear</a>
		</form>
	</div>
	<div data-options="region:'center',border:false" >
		<table id="admin_users_datagrid"></table>
	</div>
</div>



