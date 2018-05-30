<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	var layout_west_tree='';
	$(function() {
		$('#user_login_loginForm').form({
			url : '${pageContext.request.contextPath}/userAction!doNotNeedSessionAndSecurity_login.action',
			success : function(r) {
				var obj = $.parseJSON(r);
				if (obj.success) {
					$('#user_login_loginDialog').dialog('close');
					//加载welcome界面重要提醒和待办事项
					if(obj.obj.types != '1'){
						$("#layout_center_welcome_label").hide();
						$("#layout_center_welcome_show").css('display', 'block');
						if(obj.obj.companies.length>0){
							//加载代办事项
							$.ajax({																//商户
								url:'welcomeAction!findWelcomeInfo.action',
								type:'post',
								dataType:'text',
								success:function(data){
									var obj = $.parseJSON(data);
									$("#layout_center_welcome_show").append("<div><p>1、<a onclick=\"user_login_allWhesImport()\" style=\"cursor:pointer\">There are <label style=\"cursor:pointer;color:red\">"+obj.whesdtlCount+"</label> cars need to put in your storage</a></p></div>");
									$("#layout_center_welcome_show").append("<div><p>2、<a onclick=\"user_login_allWhesNoOrdersImport()\" style=\"cursor:pointer\">There are <label style=\"cursor:pointer;color:red\">"+obj.booknumCount+"</label> cars have been out of the library</a></p></div>");
								}
							});
							//根据type搜索
							$("#layout_north_search").click(function(){
								if($("#layout_north_searchInfo").combobox("getValue") == ''){
									$.messager.show({
										title:'Message',
										msg:'Please select the type of the search term',
										timeout:5000,
										showType:'slide'
									});
								}else{
									if($("#layout_north_searchInfo").combobox("getValue") == 'vin'){
										if($('#layout_center_tabs').tabs('exists',"Vehicle Manage")){
											var tab = $('#layout_center_tabs').tabs('getSelected');
											$('#layout_center_tabs').tabs('close', tab);
											layout_north_searchInfo_busyUrl = 1;
											var vin = $("#layout_north_search_input").val();
											addTab({title:"Vehicle Manage",href:"admin/wareHouse/wareHouse.jsp?vin="+vin+"",closable:true,});
										}else{
											layout_north_searchInfo_busyUrl = 1;
											var vin = $("#layout_north_search_input").val();
											addTab({title:"Vehicle Manage",href:"admin/wareHouse/wareHouse.jsp?vin="+vin+"",closable:true,});
										}
									}
								}
							});
						}else{
							//加载重要提醒
							$.ajax({																//客户
								url:'welcomeAction!findWelcomeInfo.action',
								type:'post',
								dataType:'text',
								success:function(data){
									var obj = $.parseJSON(data);
									$("#layout_center_welcome_show").append("<div><p>1、<a onclick=\"user_login_allCustomerImport()\" style=\"cursor:pointer\">You have <label style=\"cursor:pointer;color:red\">"+obj.userCount+"</label> car did not put in storage</a></p></div>");
								}
							});
							//根据type搜索
							$("#layout_north_search").click(function(){
								if($("#layout_north_searchInfo").combobox("getValue") == ''){
									$.messager.show({
										title:'Message',
										msg:'Please select the type of the search term',
										timeout:5000,
										showType:'slide'
									});
								}else{
									if($("#layout_north_searchInfo").combobox("getValue") == 'vin'){
										if($('#layout_center_tabs').tabs('exists',"Search Vehicle")){
											var tab = $('#layout_center_tabs').tabs('getSelected');
											$('#layout_center_tabs').tabs('close', tab);
											layout_north_searchInfo_customerUrl = 1;
											var vin = $("#layout_north_search_input").val();
											addTab({title:"Search Vehicle",href:"admin/customerOrder/searchVehicle.jsp?vin="+vin+"",closable:true,});
										}else{
											layout_north_searchInfo_customerUrl = 1;
											var vin = $("#layout_north_search_input").val();
											addTab({title:"Search Vehicle",href:"admin/customerOrder/searchVehicle.jsp?vin="+vin+"",closable:true,});
										}
									}
								}
							});
						}
					}
					
					
					
					layout_west_tree = $('#layout_west_tree').tree({
					
				        url:'${pageContext.request.contextPath}/menuAction!doNotNeedSessionAndSecurity_getAllTreeNode.action',
				        parentField:'pid',
				        lines:true,
				        onLoadSuccess:function(node, data){$(this).tree('collapseAll')},
				        onClick:function(node){
				        	if(node.attributes.url){
					        	var url ='${pageContext.request.contextPath}'+node.attributes.url;
					        	addTab({title:node.text,href:url,closable:true});
				        	}	
						}
					})
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
				if(null!=obj.obj){
					$('#layout_north_welcome').text('welcome，'+obj.obj.logname);
				}
			}
		});

		$('#user_login_loginForm input').bind('keyup', function(event) {
			if (event.keyCode == '13') {
				$('#user_login_loginForm').submit();
			}
		});
		
		window.setTimeout(function(){
			$('#user_login_loginForm input[logname=logname]').focus();
		}, 0);	
	});
	
	function user_login_allWhesNoOrdersImport(){
		if($('#layout_center_tabs').tabs('exists',"ValidOrders")){
			var tab = $('#layout_center_tabs').tabs('getSelected');
			$('#layout_center_tabs').tabs('close', tab);
			addTab({title:"ValidOrders",href:"admin/validOrders/validOrders.jsp",closable:true,});
		}else{
			addTab({title:"ValidOrders",href:"admin/validOrders/validOrders.jsp",closable:true,});
		}
	}
	
	function user_login_allWhesImport(){
		if($('#layout_center_tabs').tabs('exists',"Prealert Receive")){
			var tab = $('#layout_center_tabs').tabs('getSelected');
			$('#layout_center_tabs').tabs('close', tab);
			addTab({title:"Prealert Receive",href:"admin/wareHouse/preAlertWarehouse.jsp",closable:true,});
		}else{
			addTab({title:"Prealert Receive",href:"admin/wareHouse/preAlertWarehouse.jsp",closable:true,});
		}
	}
	
	function user_login_allCustomerImport(){
		if($('#layout_center_tabs').tabs('exists',"Search Vehicle")){
			var tab = $('#layout_center_tabs').tabs('getSelected');
			$('#layout_center_tabs').tabs('close', tab);
			layout_north_searchInfo_customerUrl = 2;
			addTab({title:"Search Vehicle",href:"admin/customerOrder/searchVehicle.jsp",closable:true,});
		}else{
			layout_north_searchInfo_customerUrl = 2;
			addTab({title:"Search Vehicle",href:"admin/customerOrder/searchVehicle.jsp",closable:true,});
		}
	}

	function user_login_changeValCode(){
		 var rand = Math.random();
	     $('#user_login_getValCode').attr("src","${pageContext.request.contextPath}/userAction!doNotNeedSessionAndSecurity_getValCode.action?rand="+rand);
	}

</script>

<div id="user_login_loginDialog" class="easyui-dialog"
		data-options="title:'log in',	modal:true,closable:false,	
			buttons:[{
				text:'Sign in',
				iconCls:'icon-save',
				handler:function(){
					$('#user_login_loginForm').submit();
				}
			},{
				text:'Register',
				iconCls:'icon-edit',
				handler:function(){
					$('#user_reg_regDialog').dialog('open');
					
				}
			}]">
	<form id="user_login_loginForm" method="post">
		<table>
			<tr>
				<th>User ID</th>
				<td><input name="logname" class="easyui-validatebox" data-options="required:true"/>
				</td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input name="password" class="easyui-validatebox" type="password" data-options="required:true">
				</td>
			</tr>
			<tr>
				<th><img id="user_login_getValCode" width="109" height="40" src="${pageContext.request.contextPath}/userAction!doNotNeedSessionAndSecurity_getValCode.action"></th>
				<td><a  class="login-text03" href="javascript:void(0);" onclick="user_login_changeValCode()">change code</a>
				</td>
			</tr>
			<tr>
				<th>Input code</th>
				<td><input name="validate" class="easyui-validatebox"  data-options="required:true">
				</td>
			</tr>
		</table>
	</form>

</div>