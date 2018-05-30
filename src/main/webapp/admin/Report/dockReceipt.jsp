<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/style/dockReceipt.css" type="text/css"></link>
	<style>
		#centerWhesdtl td{
			padding-left: 5px;
		}
		.trType03{
			height:380px;
		}
	</style>
	<div id="admin_Report_DockReceipt" style="width:100%;height:100%;">
		<div style="height:5%;background:#eee;">
			<form id="admin_Report_DockReceipt_from" method="post">
				<table>
				<tr>
					<td>UOO :</td>
					<td>
						<input id="admin_Report_DockReceipt_from_uoo" name="uoo" style="width:250px;" />
					</td>
					<td><a id="admin_Report_DockReceipt_from_Search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">Search</a>  </td>
					<td><a id="admin_Report_DockReceipt_from_Save" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a></td>
					<td><a id="admin_Report_DockReceipt_from_Print" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-print'">Print</a></td>
				</tr>
			</table>
			</form>
		</div>
		
		<div id="admin_Report_DockReceipt_table" style="height:92%;">
			<div style="width:100%;text-align:center;"><a style="font-size:22px;"><b>DOCK RECEIPT </b></a></div>
			<form id="admin_Report_DockReceipt_table_from" method="post">
				<table id="admin_Report_DockReceipt_table_table" border=1 style="width:100%; cellspacing:0;cellpadding:0;border-collapse:collapse;">
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> STEAMSHIP LINE </label></div>
							<div><input id="carrier" name="carrier" class="input_result"></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> BOOKING NO. </label></div>
							<div><input id="booknum" class="input_result" name="booknum"></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> SHIPPER/EXPORTER </label></div>
							<div><textarea id="shipper" name=" " class="textarea_result"></textarea></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> EXPORT REFERENCES </label></div>
							<div><input id="uoo" name="uoo" class="input_result"/></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> CONSIGNEE </label></div>
							<div><textarea id="consignee" class="textarea_result" name="consignee"></textarea></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> FORWARDING AGENT - REFERENCES </label></div>
							<div><textarea id="agent" class="textarea_result" name="agent"></textarea></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> NOTIFY PARTY </label></div>
							<div><textarea id="notify" class="textarea_result" name="notify"></textarea></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> DOMESTIC ROUTING/EXPORT INSTRUCTIONS: </label></div>
							<div><textarea id="routing" class="textarea_result" name="routing"></textarea></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> POINT AND COUNTRY OF ORIGIN </label></div>
							<div><input id="origin" class="input_result" type="text"  name="origin"></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> PLACE OF RECEIPT </label></div>
							<div><input id="receipt" class="input_result" name="receipt" type="text"></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> EXPORT CARRIER (VESSEL,VOYAGE/FLIGHT) </label></div>
							<div><input id="vessel" class="input_result" type="text"  name="vesselVoyage"></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> PORT OF LOADING</label></div>
							<div><input id="loading" name="loaddate" class="input_result" name="loading" type="text"></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="4">
							<div><label class="label_1"> AIR/SEA PORT OF DISCHARGE</label></div>
							<div><input id="discharge" class="input_result" name="description" type="text"></div>
						</td>
						<td colspan="4">
							<div><label class="label_1"> FINAL DESTINATION(FOR THE MERCHSNT'S REFERENCE ONLY) </label></div>
							<div><input id="final_" class="input_result" type="text" name="final"></div>
						</td>
					</tr>
					<tr class="trType01">
						<td colspan="8" style="text-align:center;font-size:15px;">PARTICULARS FURNISHED BY SHIPPER </td>
					</tr>
					
					<tr style="text-align:center;">
						<td class="trNextType01">
							<div><label> MARKS AND NUMBERS/ </label></div>
							<div><label> CONTAINER NUMBER </label></div>
						</td>
						<td class="trNextType01">
							<div><label> NO OF FKGS OR </label></div>
							<div><label> CONTAINERS </label></div>
						</td>
						<td colspan="4" class="trNextType02">
							<div><label> KIND OF PKCKAGES:DESCRIPTION OF GOODS </label></div>
						</td>
						<td class="trNextType01">
							<div><label> GROSS WEIGHT </label></div>
						</td>
						<td class="trNextType01">
							<div><label> MEASUREMENT </label></div>
						</td>
					</tr>
					<!-- -----------------------------  -->
					<tr class="trType03">
						<td>
							<textarea id="marks" class="textarea_result_2"></textarea>
						</td>
						<td>
							<textarea id="num" class="textarea_result_2"></textarea>
						</td>
						<td colspan="4" scope="row" valign="top">
							<div><label style="font-size:10px;"> SHIPPER'S LOAD & COUNT,STC: </label></div> 
							<div id="centerWhesdtlDiv">
								<table id="centerWhesdtl">
									<!-- 
										<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2015 BMW X3 ,VIN:5UXKR0C55F0K52985</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2015 BMW X3 ,VIN:5UXKR0C55F0K52985</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									<tr>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
										<td><a>2014 BMW X3 ,VIN:5UXKR0C5XE0K51894</a></td>
									</tr>
									 -->
								</table>
							</div> 
							<div class="center_center_below">
								<table>
									<tr>
										<td><label class="label_1"> AES ITN: </label></td>
										<td><input class="input_result_2" value=""></td>
									</tr>
									<tr>
										<td><label class="label_1"> CUT OFF: </label></td>
										<td><input class="input_result_2" name="cutoffdate"></td>
									</tr>
									<tr>
										<td><label class="label_1"> CONTAINER#: </label></td>
										<td><input class="input_result_2" name="connum" ></td>
									</tr>
									<tr>
										<td><label class="label_1"> SEAL#: </label></td>
										<td><input class="input_result_2" name="sealnum"></td>
									</tr>
								</table>
							</div> 
						</td>
						<td>
							<textarea id="weight" class="textarea_result_2"></textarea>
						</td>
						<td>
							<textarea id="measurement" class="textarea_result_2"></textarea>
						</td>						
					</tr>
					
					<!-- -----------------------------  -->
					<tr>
						<td colspan="4" style="height:200px;" scope="row" valign="top">
							<table style="width:100%;height:100%;">
								<tr>
									<td colspan="3" style="width:100%;text-align:center;font-size:10px;"><label>DELIVERED BY : </label></td>
								</tr>
								<tr>
									<td colspan="3"><label class="label_1"> LIGHTER TRUCK : </label></td>
								</tr>
								<tr>
									<td><label class="label_1">ARRIVED :</label></td>
									<td colspan="2"><label class="label_1"> DATE: ______________________ TIME: ________________________ </label></td>
								</tr>
								<tr>
									<td><label class="label_1">UNLOADED :</label></td>
									<td colspan="2"><label class="label_1"> DATE: ______________________ TIME: ________________________ </label></td>
								</tr>
								<tr>
									<td colspan="3"><label class="label_1">CHECKED BY :</label></td>
								</tr>
								<tr>
									<td colspan="2">
										<label class="label_1">PLACED  IN SHIP ON DECK</label>
									</td>
									<td align="right">
										<label class="label_1">LOCATION ______________________ </label>
									</td>
								</tr>
							</table>
						</td>
						<td colspan="4" style="height:200px;" scope="row" valign="top">
							<div style="width:100%;height:60%;text-align:center;">
								<label class="label_1"> RECEIVED THE ABOVE DESCRIBED GOODS OF PACKAGES SUBJECT TO ALL THE TERMS OF </label>
								<label class="label_1"> THE UNDERSIGNED'S REGULAR FORM OF DOCK RECEIPT AND BILL OF LADING WHICH SHALL </label>
								<label class="label_1"> CONSTITUTE THE CONTRACT UNDER WHICH THE GOODS ARE RECEIVED,COPIES OF WHICH </label>
								<label class="label_1"> ARE AVAILABLE FROM THE CARRIER ON REQUEST AND MAY BE INSPECTED AT ANY OF ITS </label>
								<label class="label_1"> OFFICES. </label>
							</div>
							<div style="border-top:1px solid #ddd">
								<table style="width:100%;height:100%;border-collapse: separate;border-spacing:0px 10px">
									<tr>
										<td align="right"><label class="label_1">FOR THE MASTER : _________________________</label></td>
									</tr>
									<tr>
										<td align="right"><label class="label_1">RECEIVING CLERK : ________________________</label></td>
									</tr>
								</table>
							</div>
						</td>
					</tr>
				</table>
				<div style="width:100%;text-align:center;font-size:15px;"><b>ONLY CLEAN DOCK RECEIPT ACCEPTED </b></div>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">

		//查询指定uoo
		$('#admin_Report_DockReceipt_from_Search').bind('click',function(){
			var booknum = serializeObject($("#admin_Report_DockReceipt_from"));
			$.ajax({  
        		type: "post",
        		dataType: "json",
        		url: "booknumAction!SearchUooDockReceipt.action",
        		data: booknum,
        		success: function (result) { 
        			if(result.success){
        				var dock = result.obj;
        				$("#admin_Report_DockReceipt_table_from").form('load',dock);
        				var whesdtlList = dock.whesdtlList;
        				console.info(whesdtlList.length);
        				for(var i = 0;i<whesdtlList.length;i++){
        					var tr = "<tr></tr>";
        					var td = "<td><a>"+whesdtlList[i]+"</a></td>";
        					if(i%2==0){
        						$("#centerWhesdtl").append(tr);
        					}
        					$("#centerWhesdtl tr:last").append(td);
        				}
        				
        			}
        		}  
        	});
		});
		//打印
		$('#admin_Report_DockReceipt_from_Print').bind('click',function(){
			$("#admin_Report_DockReceipt_table").printArea({mode:"popup",popClose:true}); 
		});
	
	
	</script>
