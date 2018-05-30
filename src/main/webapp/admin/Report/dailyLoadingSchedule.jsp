<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style>
		.cc td{ border:solid 1px;}
	</style>

	<div id="admin_Report_DailyLoadingSchedule" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
	    <div data-options="region:'north',split:true" style="height:40px;background:#eee;">
	    	<form id="admin_Report_DailyLoadingSchedule_from" method="post">
			<table>
				<tr>
					<td>Customer :</td>
					<td>
						<input id="admin_Report_DailyLoadingSchedule_from_customer" class="easyui-combobox" name="usersId" value="Please Select" style="width:250px;" 
	    							data-options="valueField:'id',
	    										  textField:'fullname',
	    										  url:'${pageContext.request.contextPath}/customerAction!getCustomerName.action',
	    										  editable:false,
	    										  " />
					</td>
					<td>From: <input id="admin_Report_DailyLoadingSchedule_from_fromDate" type="text" class="easyui-datebox"  name="loadingFrom" 
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
    				<td>To:<input id="admin_Report_DailyLoadingSchedule_from_toDate" type="text" class="easyui-datebox" name="loadingTo"
    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
					<td><a id="admin_Report_DailyLoadingSchedule_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
					<td><a id="admin_Report_DailyLoadingSchedule_from_Print" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'">Print</a></td>
					<td><a id="admin_Report_DailyLoadingSchedule_from_Export" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-excel'">Export</a></td>
				</tr>
			</table>
		</form>
	    </div>   
	    <div id="admin_Report_DailyLoadingSchedule_datagrid" data-options="region:'center',border:false">
	    </div> 
   </div>
   
   <script type="text/javascript">
   	var whesdtl ={};
   	//----------------------------------点击查询时--------------------------------------------
   	$('#admin_Report_DailyLoadingSchedule_from_Search').bind('click',function(){
   		whesdtl = serializeObject($("#admin_Report_DailyLoadingSchedule_from"));
   		if(whesdtl.usersId=="Please Select" || whesdtl.usersId==undefined){
   			whesdtl.usersId="";
   		}else{
   			whesdtl.users = $("#admin_Report_DailyLoadingSchedule_from_customer").combo('getText');
   		}
   	 	$("#admin_Report_DailyLoadingSchedule_datagrid").empty();
   		$.ajax({  
    		type: "post",
    		dataType: "json",
    		url: "whesdtlAction!getDailyLoadingScheduleDate.action",
    		data: whesdtl,
    		success: function (result) { 
    			if(result.success){
    				var dailyLoadings = result.obj;
    				var loaddate = dailyLoadings[0].loaddate;
    				var booknum = dailyLoadings[0].booknum;
    				var index=0; 
    				var booknumIndex=0;
    				for(var i = 0;i<dailyLoadings.length;i++){
    					if(index==0){
							var html = '<div style="width:100%;text-align:center;"><a style="font-size:20px;"><b>Loading Date 装车日期 : '+dailyLoadings[i].loaddate+'</b></a></div>';
							 $("#admin_Report_DailyLoadingSchedule_datagrid").append(html);
						}
    					if(booknumIndex==0){
    						 var table = createTable(dailyLoadings[i]);
							 $("#admin_Report_DailyLoadingSchedule_datagrid").append(table);
							 var tr = createTr(dailyLoadings[i]);
							 $("#admin_Report_DailyLoadingSchedule_datagrid table:last").append(tr);
    					}
    					if(index>0 && booknumIndex>0){
    						var tr = createTr(dailyLoadings[i]);
    						$("#admin_Report_DailyLoadingSchedule_datagrid table:last").append(tr);
    					}
    					
    					if(dailyLoadings[i].loaddate==loaddate){
	    					if(dailyLoadings[i].booknum==booknum){
	    						booknumIndex++;
	        				}else{
	        					booknum = dailyLoadings[i].booknum;
	        					booknumIndex=0;
	        				}
	    						index++;
	    				}else{
	    						loaddate = dailyLoadings[i].loaddate;
	    						booknum = dailyLoadings[i].booknum;
	    						index=0;
	    						booknumIndex=0;
	    				}
    				}
    			}else{
    				//传输不成  将表格清空
    				 $("#admin_Report_DailyLoadingSchedule_datagrid").empty();
    			}
    		}  
    	});  
   		
   	});
   
   	function createTable(booknum){
   		var tabledate='<table class="cc"  border=1 style="width:100%; cellspacing:0;cellpadding:0;border-collapse:collapse;">';
   		tabledate = tabledate + '<tr><td colspan="3">Container # /Seal 柜号/船封号 : <br/>'+booknum.consize+'/'+booknum.connum+'/'+booknum.sealnum+'</td><td colspan="2">Ocean Export Ref:<br/>'+booknum.uoo+' </td><td>Booking # 订舱号 :  <br/>'+booknum.booknum+'</td></tr>';
   		tabledate = tabledate +'<tr><td colspan="6"><a style="font-size:18px;"><b>Cars to be loaded 计划要装车辆 :</b></a></td></tr>';
   		tabledate = tabledate +'</table><br><br><br>';
   		return tabledate;
   	};
   	function createTr(whesdtl){
   		var tr = '<tr><td>'+whesdtl.vin+'</td><td>'+whesdtl.year+', '+whesdtl.make+', '+whesdtl.model+', '+whesdtl.color+' </td><td>'+whesdtl.fuel+' gallon   '+whesdtl.fuelType+' </td><td> '+whesdtl.indate+'</td><td colspan="2"> '+whesdtl.users+'</td></tr>';
   		return tr;
   	};
   	
   	
 	// 打印按钮
	$("#admin_Report_DailyLoadingSchedule_from_Print").bind("click", function() {
		$("#admin_Report_DailyLoadingSchedule_datagrid").printArea({mode:"popup",popClose:true}); 
			//$("#admin_Report_Report_CurrentStockReport_table").printArea();
	});
 
 	// 导出Excel文件
 	$("#admin_Report_DailyLoadingSchedule_from_Export").bind("click", function() {
 		var end = "";
 		if(whesdtl.loadingFrom!=null && whesdtl.loadingFrom!="" && whesdtl.loadingFrom!=undefined){
 			end += "&loadingFrom="+whesdtl.loadingFrom;
		}
 		if(whesdtl.loadingTo!=null && whesdtl.loadingTo!="" && whesdtl.loadingTo!=undefined){
 			end += "&loadingTo="+whesdtl.loadingTo;
		}
 		if(whesdtl.usersId!=null && whesdtl.usersId!="" && whesdtl.usersId!=undefined){
 			end += "&usersId="+whesdtl.usersId+"&users="+whesdtl.users;
		}
 		var href = "whesdtlAction!getDailyLoadingScheduleExcel.action";
 		if(end.length>0){
 			href = href+"?"+end.substring(1, end.length);;
 		}else{
 			$.messager.alert('warning',"No query results",'info');
			return;
 		}
		$(this).attr("href",href);
	});
 
   </script>
   
   
   
   
   
   
   
   
   
   
   