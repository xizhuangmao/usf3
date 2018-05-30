<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<style>
		.cc td{ border:solid 1px;font-size:12px; font-family: Verdana,Geneva,sans-serif;}
		.ll{width: 7%;}
		.tt {width: 14%;}
		.remark{width: 10%;}
		.short{width: 2%;}
		.cc {width:100%;clear: both;}
		.mainDiv div {width:100%;}
	</style>
	
	<div id="admin_Report_ReceivedCarsReport" class="easyui-layout" data-options="fit:true,border:false,collapsible:false">   
	    <div data-options="region:'north',split:true" style="height:40px;background:#eee;">
	    	<form id="admin_Report_ReceivedCarsReport_from" method="post">
				<table>
					<tr>
						<td>Customer :</td>
						<td>
							<input id="admin_Report_ReceivedCarsReport_from_customer" class="easyui-combobox" name="usersId" value="Please Select" style="width:250px;" 
		    							data-options="valueField:'id',
		    										  textField:'fullname',
		    										  url:'${pageContext.request.contextPath}/customerAction!getCustomerName.action',
		    										  editable:false,
		    										  " />
						</td>
						<td>From: <input id="admin_Report_ReceivedCarsReport_from_fromDate" type="text" class="easyui-datebox"  name="loadingFrom" 
	    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
	    				<td>To:<input id="admin_Report_ReceivedCarsReport_from_toDate" type="text" class="easyui-datebox" name="loadingTo"
	    							data-options=" editable:false,formatter:timeformatter,parser:timeparser"></input></td>
						<td><a id="admin_Report_ReceivedCarsReport_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
						<td><a id="admin_Report_ReceivedCarsReport_from_Print" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'">Print</a></td>
						<td><a id="admin_Report_ReceivedCarsReport_from_Excel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-excel'">Excel</a></td>
					</tr>
				</table>
			</form>
	    </div>   
	    <div id="admin_Report_ReceivedCarsReport_table" data-options="region:'center',border:false" class="mainDiv">
	    	
	    </div> 
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
			whesdtl.loadingFrom=getNowTimes();
			whesdtl.loadingTo=getNowTimes();
			$("#admin_Report_ReceivedCarsReport_table").empty();
			$.ajax({  
	    		type: "post",
	    		dataType: "json",
	    		url: "whesdtlAction!getReceivedCarsReport.action",
	    		data: whesdtl,
	    		success: function (result) { 
	    			if(result.success){
	    				var whesdtls = result.obj;
	    				ReceivedCarsReport_table(whesdtls);
	    			}
	    		}
			});
			
		});
		
		//----------------遍历对象  生成table表格--------------------------
		ReceivedCarsReport_table =function(whesdtls){
			
			var totals = 0;   //统计每个indate下的记录总数
			var truckingFromDate = $("#admin_Report_ReceivedCarsReport_from_fromDate").combo('getValue');
			var truckingToDate = $("#admin_Report_ReceivedCarsReport_from_toDate").combo('getValue');
			if(truckingFromDate=="" || truckingFromDate==undefined){
				truckingFromDate = getNowTimes();
			}
			if(truckingToDate=="" || truckingToDate==undefined){
				truckingToDate = getNowTimes();
			}
			if(whesdtls.length==0){
				return;
			}
			var head =headHtml(truckingFromDate,truckingToDate);
			$("#admin_Report_ReceivedCarsReport_table").append(head); //刚开始  先加载头部
			var indate = whesdtls[0].indate;
			for(var i = 0;i<whesdtls.length;i++){
				if(i==0){
					var title = tableHtml(indate);
					$("#admin_Report_ReceivedCarsReport_table").append(title);
					var tableTr =tableContentTr(whesdtls[i]);
					$("#admin_Report_ReceivedCarsReport_table table:last").append(tableTr);
					totals++;
				}else{
					if(indate == whesdtls[i].indate){
						var tableTr =tableContentTr(whesdtls[i]);
						$("#admin_Report_ReceivedCarsReport_table table:last").append(tableTr);
						totals++;
					}else{
						indate = whesdtls[i].indate;
						var end = '<tr><td colspan="11"><a style="font-size:15px;"><b>Total: '+totals+' cars</b></a></td></tr>';
						$("#admin_Report_ReceivedCarsReport_table table:last").append(end);
						var title = tableHtml(indate);
						$("#admin_Report_ReceivedCarsReport_table").append(title);
						var tableTr =tableContentTr(whesdtls[i]);
						$("#admin_Report_ReceivedCarsReport_table table:last").append(tableTr);
						
						totals=1;
					}
				}
			}
			if(totals != 0){
				var end = '<tr><td colspan="11"><a style="font-size:15px;"><b>Total: '+totals+' cars</b></a></td></tr>';
				$("#admin_Report_ReceivedCarsReport_table table:last").append(end);
			}
			
		};
		
		//----------------创建大题---------------------------
		headHtml = function(fromTime,toTime){
			var head = '<div style="text-align:center;"><a style="font-size:20px;"><b>Received Cars Report </b></a></div>';
			head += '<div style="text-align:center;"><a style="font-size:15px;"><b>From '+fromTime+', to '+toTime+'</b></a></div><br>';
			return head;
		};
		//----------------创建表头-----------------------------
		tableHtml = function(indate){
			var tableBady = '<div ><a style="font-size:15px;"><b>Receiving Date : '+indate+'</b></a></div>';
			tableBady += '<table class="cc"  border=1 style="cellspacing:0;cellpadding:0;border-collapse:collapse;">';
			tableBady += '<tr><td class="ll">In Date</td><td colspan="2" class="tt">Year Make Model Color VIN# </td><td class="ll">Engine# </td>';
			tableBady += '<td class="short">Key</td><td class="short">Sticker</td><td class="ll">Load Date</td><td class="ll">Booking #</td><td class="ll">Container #</td>';
			tableBady += '<td class="ll">TitleDate</td><td class="remark">Remark </td></tr></table><br/>';
			return tableBady;
		};
		//------------------加载table数据--------------------
		tableContentTr = function(whesdtl){
			var tableContent = '<tr><td>'+whesdtl.indate+'</td>';
			tableContent  += '<td colspan="2">'+whesdtl.year+','+whesdtl.make+','+whesdtl.model+',<br/>VIN:'+whesdtl.vin+' </td>';
			tableContent  += '<td>'+whesdtl.engine+'</td><td>'+whesdtl.keynum+'</td><td>'+whesdtl.sticker+'</td>';
			tableContent  += '<td>'+whesdtl.loaddate+'</td><td>'+whesdtl.booknum+'</td><td>'+whesdtl.connum+'</td><td></td><td>'+whesdtl.note+'</td></tr>';
			return tableContent;
		};
		
		//-------------------查询---------------------------------------------
		 $('#admin_Report_ReceivedCarsReport_from_Search').bind('click',function(){
			 whesdtl = serializeObject($("#admin_Report_ReceivedCarsReport_from"));
			 if(whesdtl.usersId=="Please Select" || whesdtl.usersId == "" || whesdtl.usersId ==undefined){
				 whesdtl.usersId = "";
			 }else{
				 whesdtl.users = $("#admin_Report_ReceivedCarsReport_from_customer").combo('getText');
			 }
			 if(whesdtl.loadingFrom==""  || whesdtl.loadingFrom==undefined || whesdtl.loadingFrom==null){
				 whesdtl.loadingFrom=getNowTimes();
			 }
 			if(whesdtl.loadingTo==""  || whesdtl.loadingTo==undefined || whesdtl.loadingTo==null){
 				whesdtl.loadingTo=getNowTimes();
			 }
			$.ajax({  
	    		type: "post",
	    		dataType: "json",
	    		url: "whesdtlAction!getReceivedCarsReport.action",
	    		data: whesdtl,
	    		success: function (result) { 
	    			if(result.success){
	    				$("#admin_Report_ReceivedCarsReport_table").empty();
	    				var whesdtls = result.obj;
	    				ReceivedCarsReport_table(whesdtls);
	    			}
	    		}
			});
		 });
		// 打印按钮
		$("#admin_Report_ReceivedCarsReport_from_Print").bind("click", function() {
			$("#admin_Report_ReceivedCarsReport_table").css({
				   'height' : 'auto', //高度自动
				   'overflow' : 'visible' //在打印之前把这个div的overflow改成全部显示
				}).printArea({mode:"popup",popClose:true}); 
				//$("#admin_Report_Report_CurrentStockReport_table").printArea();  //直接打印
		});
		
		//导出Excel文件  点击Excel 
		$("#admin_Report_ReceivedCarsReport_from_Excel").bind("click", function() {
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
	 		var href = "whesdtlAction!getReceivedCarsReportExcel.action";
	 		if(end.length>0){
	 			href = href+"?"+end.substring(1, end.length);;
	 		}else{
	 			$.messager.alert('warning',"No query results",'info');
				return;
	 		}
			$(this).attr("href",href);
		});
   </script>
   