<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style>
		.cc td{ border:solid 1px;font-size:12px; font-family: Verdana, Geneva, sans-serif;}
		.tttt{width:10%;}
		.tt {width:40%;}
		.cc {width:100%;clear: both;border-collapse: collapse}
	</style>
	<div id="admin_Report_CurrentStockReport" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
	    <div data-options="region:'north',split:true" style="height:40px;background:#eee;">
	    	<form id="admin_Report_CurrentStockReport_from" method="post">
			<table>
				<tr>
					<td>Customer :</td>
					<td>
						<input id="admin_Report_CurrentStockReport_from_customer" class="easyui-combobox" name="usersId" value="Please Select" style="width:250px;" 
	    							data-options="valueField:'id',
	    										  textField:'fullname',
	    										  url:'${pageContext.request.contextPath}/customerAction!getCustomerName.action',
	    										  editable:false,
	    										  " />
					</td>
					<td>Date: <input id="admin_Report_CurrentStockReport_from_Date" type="text" class="easyui-datebox"  name="loaddate" 
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
					<td><a id="admin_Report_CurrentStockReport_from_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
					<td><a id="admin_Report_CurrentStockReport_from_Print" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'">Print</a></td>
					<td><a id="admin_Report_CurrentStockReport_from_Export" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-excel'">Export</a></td>
				</tr>
			</table>
		</form>
	    </div>   
	    <!--startprint-->
	    <div id="admin_Report_Report_CurrentStockReport_table" data-options="region:'center',border:false," style="width:700px;" >
	    </div> 
	    <!--endprint-->
   </div>
   
   <script type="text/javascript">
	 //获取当前时间
		getNowTimes = function(){
			var curr_time = new Date();
			var strDate = curr_time.getFullYear()+"-";
				strDate += curr_time.getMonth()+1+"-";
				strDate += curr_time.getDate();
			return strDate;
		};
		
		var whesdtl ={};
		$(function(){
			whesdtl.loaddate=getNowTimes();
			$("#admin_Report_Report_CurrentStockReport_table").empty();
			$.ajax({  
	    		type: "post",
	    		dataType: "json",
	    		url: "whesdtlAction!getCurrentStockReport.action",
	    		data: whesdtl,
	    		success: function (result) { 
	    			if(result.success){
	    				loaddate = whesdtl.loaddate;
	    				var whesdtls = result.obj;
	    				CurrentStockReport_table(whesdtls,whesdtl.loaddate);
	    			}
	    		}
			});
		});
		//-----------------------------查询------------------------------------
		$('#admin_Report_CurrentStockReport_from_from_Search').bind('click',function(){
			whesdtl = serializeObject($("#admin_Report_CurrentStockReport_from"));
			if(whesdtl.usersId=="Please Select" || whesdtl.usersId==undefined || whesdtl.usersId==""){
				whesdtl.usersId="";
			}else{
				whesdtl.users = $("#admin_Report_CurrentStockReport_from_customer").combo('getText');
			}
			if(whesdtl.loaddate=="" || whesdtl.loaddate==undefined){
				whesdtl.loaddate=getNowTimes();
			}
			$("#admin_Report_Report_CurrentStockReport_table").empty();
			
			$.ajax({  
	    		type: "post",
	    		dataType: "json",
	    		url: "whesdtlAction!getCurrentStockReport.action",
	    		data: whesdtl,
	    		success: function (result) { 
	    			if(result.success){
	    				var whesdtls = result.obj;
	    				CurrentStockReport_table(whesdtls,whesdtl.loaddate);
	    			}
	    		}
			});
			
		});
		
		CurrentStockReport_table = function(whesdtls,date){
			var customer = whesdtls[0].users;
			var totals = 0;   //统计每个用户下的记录
			for(var i = 0;i<whesdtls.length;i++){
				if(i==0){
					var head =headHtml(date);
					$("#admin_Report_Report_CurrentStockReport_table").append(head);
					var table = tableHtml(whesdtls[i].users);
					$("#admin_Report_Report_CurrentStockReport_table").append(table);
					var tr = trHtml(whesdtls[i]);
					$("#admin_Report_Report_CurrentStockReport_table table:last").append(tr);
					totals ++;
				}else{
					if(customer==whesdtls[i].users){
						var tr = trHtml(whesdtls[i]);
						$("#admin_Report_Report_CurrentStockReport_table table:last").append(tr);
						totals ++;
					}else{
						$("#admin_Report_Report_CurrentStockReport_table table:last").append(totalHtml(totals));
						customer=whesdtls[i].users;
						totals = 1;
						var table = tableHtml(whesdtls[i].users);
						$("#admin_Report_Report_CurrentStockReport_table").append(table);
						var tr = trHtml(whesdtls[i]);
						$("#admin_Report_Report_CurrentStockReport_table table:last").append(tr);
					}
				}
			}
			$("#admin_Report_Report_CurrentStockReport_table table:last").append(totalHtml(totals));
			var count = whesdtls.length;
			$("#admin_Report_Report_CurrentStockReport_table").append('<div style="width:100%;"><a style="font-size:20px;"><b>Total: '+count+' cars</b></a></div>');
		};
		
		headHtml = function(date){
			var head = '<div style="width:100%;text-align:center;"><a style="font-size:20px;"><b>Current Stock Report </b></a></div>';
				head += '<div style="width:100%;text-align:center;"><a style="font-size:15px;"><b>'+date+' </b></a></div><br>';
				return head;
		};
		tableHtml = function(customer){
			var body = '<div style="width:100%;"><a style="font-size:16px;"><b>'+customer+'</b></a></div>';
				body += '<table class="cc" border=1 style="width:100%;">';
				body += '<tr><td class="tttt">In Date</td><td colspan="2" class="tt">Year Make Model Color VIN# </td>';
				body += '<td class="tttt">Engine# </td><td class="tttt">Key</td><td class="tttt">Sticker</td><td class="tttt">Remark </td></tr>';
				body += '</table><br>';
				return body;
		};
		trHtml = function(whesdtl){
			var tr ='<tr><td class="tttt">'+whesdtl.indate+'</td>';
				tr += '<td colspan="2" class="tt">'+whesdtl.year+','+whesdtl.make+','+whesdtl.model+','+whesdtl.color+',VIN:'+whesdtl.vin+' </td>';
				tr += '<td class="tttt">'+whesdtl.engine+'</td><td>'+whesdtl.keynum+'</td>';
				tr += '<td class="tttt">'+whesdtl.sticker+' </td><td>'+whesdtl.note+' </td></tr>';
			return tr;
		};
		totalHtml = function(totals){
			var total='<tr><td colspan="7"><a style="font-size:13px;font-weight:bold;"><b>Total: '+totals+' cars</b></a></td></tr>';
			return total;
		};
		
		// 打印按钮
				$("#admin_Report_CurrentStockReport_from_Print").bind("click", function() {
					$("#admin_Report_Report_CurrentStockReport_table").printArea({mode:"popup",popClose:true}); 
						//$("#admin_Report_Report_CurrentStockReport_table").printArea();
				});
		// 导出Excel格式文件	
				$("#admin_Report_CurrentStockReport_from_Export").bind("click",function(){
					if(whesdtl.loaddate==null || whesdtl.loaddate=="" || whesdtl.loaddate==undefined){
						$.messager.alert('warning',"No query results",'info');
						return;
					}
					var href = "whesdtlAction!getCurrentStockReportExcel.action?loaddate="+whesdtl.loaddate;
					if(whesdtl.usersId==null || whesdtl.usersId=="" || whesdtl.usersId==undefined){
					}else{
						href += "&usersId="+whesdtl.usersId+"&users="+whesdtl.users;
					}
					
					$(this).attr("href",href);
				});
   </script>
   
   
   