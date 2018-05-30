<%@ page language="java"  pageEncoding="UTF-8"%>
<!-- 增加用户 -->
	<form id="admin_users_addForm" method="post" style="width:200px;height:400px">
		<table cellSpacing=5 cellPadding=5>
			<tr>
				<!--  
				<th>编号</th>
				<td><input name="id" readonly="readonly"/></td>
				-->
				<th>logname</th>
				<td><input name="logname" class="easyui-validatebox" data-options="required:true"/></td>
					<th>password</th>
				<td><input id ="admin_users_password" name="password"  class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
		
			<!-- 
			<tr>
				<th>确认密码</th>
				<td><input type="password" class="easyui-validatebox" validType="eqPwd['#admin_users_password']" invalidMessage="两次输入密码不匹配" data-options="required:true"/></td>
			</tr>
			-->
			<tr>
				<th>fullname</th>
				<td colspan="3"><input  name="fullname"  class="easyui-validatebox"  style="width: 385px" data-options="required:true,validType:'length[0,200]'"/></td>
			</tr>	

			<tr>
				<th>types</th>				
				<td>
					<select  name="types" style="width: 100px">
										<option value="2">Other</option>
										<option value="1">Employee</option>
					</select>
				</td>
			</tr>
			<tr> 
				<th>dob</th>
				<td>
					<input editable="false" type="text"  name="dob" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser" style="width: 145px"/>
				</td>
			</tr>
			<tr>
				<th>country</th>
				<td>
					<input id="admin_usersAdd_country"   name="country"
    							data-options="valueField:'id', editable:false,textField:'country',url:'${pageContext.request.contextPath}/countryAction!getCountryName.action'," />
				</td>
		
				<th>state</th>
				<td><input id="admin_usersAdd_state" name="state" class="easyui-combobox"  /></td>
			</tr>
			<tr>
				<th>city</th>
				<td><input id="admin_usersAdd_city" name="city" class="easyui-combobox"  /></td>
			</tr>
			<tr>
				<th>address</th>
				<td colspan="3"><input  name="address"  class="easyui-validatebox"  style="width: 385px"  data-options="validType:'length[0,200]'"/></td>
			</tr>
			<tr>
				<th>ssn</th>
				<td><input  name="ssn"  class="easyui-validatebox"  data-options="validType:'length[0,100]'"/></td>
			</tr>
			<tr>
				<th>email</th>
				<td><input  name="email"  class="easyui-validatebox"  data-options="validType:'length[0,100]'"/></td>
			</tr>
			<tr>
				<th>web</th>
				<td><input  name="web"  class="easyui-validatebox"  data-options="validType:'length[0,200]'"/></td>
			</tr>
			<tr>
				<th>homephone</th>
				<td><input  name="homephone"  class="easyui-validatebox"  data-options="validType:'length[0,50]'"/></td>
			</tr>
			<tr>
				<th>cellphone</th>
				<td><input  name="cellphone"  class="easyui-validatebox"  data-options="validType:'length[0,50]'"/></td>
			</tr>
			<tr>
				<th>zip</th>
				<td><input  name="zip"  class="easyui-validatebox"  data-options="validType:'length[0,50]'"/></td>
			</tr>
			<tr>
				<th>fax</th>
				<td><input  name="fax"  class="easyui-validatebox"  data-options="validType:'length[0,50]'"/></td>
			</tr>
			<tr>
				<th>datein</th>
				<td>
					<input id="admin_usersAdd_datein" editable="false" type="text"  name="datein" class="easyui-datebox" data-options="formatter:timeformatter,parser:timeparser" style="width: 145px"/>
				</td>
			</tr>
			<tr>
				<th>active</th>
				<td>
					<select  class="easyui-combobox" name="active" style="width:145px;">
					    <option value="1">active</option>
					    <option value="0">not active</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>note</th>
				<td colspan="3"><input  name="note"  class="easyui-textbox"  style="width:400px;height:120px" data-options="multiline:true,validType:'length[0,300]'"/></td>
			</tr>
		
		</table>
	</form>
	
<script type="text/javascript">

	
	//从国家下拉获取省份列表
	$(function(){
		$('#admin_usersAdd_country').combobox({    
			onSelect:function(record){
				var url = '${pageContext.request.contextPath}/stateAction!getStateName.action?countryId='+record.id;  
				 $('#admin_usersAdd_state').combobox({
			            'url': url,
			            valueField: 'id',    
						textField: 'state',	
			            }); 
			}
		}); 
	});
	
	//从省份下拉获取城市列表
	$(function(){
		$('#admin_usersAdd_state').combobox({    
			onSelect:function(record){
				var url = '${pageContext.request.contextPath}/cityAction!getCityName.action?stateId='+record.id;  
				 $('#admin_usersAdd_city').combobox({
			            'url': url,
			            valueField: 'id',    
						textField: 'city',	
			            }); 
			}
		}); 
	});
	
	//设置默认时间
	$(function(){
		var admin_usersAdd_curr_time = new Date();
		$("#admin_usersAdd_datein").val(timeformatter(admin_usersAdd_curr_time));
 		
	}); 
</script>