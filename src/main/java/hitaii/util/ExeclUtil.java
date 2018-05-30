package hitaii.util;

import hitaii.pageModel.PcurrentStockReport;
import hitaii.pageModel.PdailyLoading;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExeclUtil {
	/**
	 * CurrentStockReport 
	 */
	//导出输出收车excel
	public static HSSFWorkbook CurrentStockReportExcel (List<PcurrentStockReport> list,String time) {
		// 创建工作簿
		HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		// 由工作簿创建工作表
		HSSFSheet hsshsheet = hssfworkbook.createSheet();
		hssfworkbook.setSheetName(0, "CurrentStockReport");
		// 设置内容样式
		HSSFCellStyle style = hssfworkbook.createCellStyle();
		// 设置标题样式
		HSSFCellStyle tstyle = hssfworkbook.createCellStyle();
		HSSFCellStyle ttstyle= hssfworkbook.createCellStyle();
		// 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		tstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ttstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 字体
		HSSFFont font = hssfworkbook.createFont();
		font.setFontName("黑体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font.setFontHeightInPoints((short)15);
		tstyle.setFont(font);
		
		HSSFFont font1 = hssfworkbook.createFont();
		font1.setFontName("黑体");
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font1.setFontHeightInPoints((short)25);
		//font1.setFontHeight((short)20);
		ttstyle.setFont(font1);
		
		// 列小标题
		HSSFRow row = hsshsheet.createRow(0);
		hsshsheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		// 赋值
		// 用户名
		HSSFCell cell = row.createCell(0);
		cell = row.createCell(0);
		cell.setCellValue("Current Stock Report");
		cell.setCellStyle(ttstyle);
		
		row=hsshsheet.createRow(1);
		hsshsheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
		cell = row.createCell(0);
		cell.setCellValue(time);
		cell.setCellStyle(tstyle);
		
		row=hsshsheet.createRow(2);
		cell = row.createCell(0);
		hsshsheet.setColumnWidth(0, 8000);
		hsshsheet.setDefaultColumnWidth(15);
		hsshsheet.setColumnWidth(2, 20000);
		hsshsheet.setColumnWidth(4, 1500);
		cell.setCellValue("Customer Name");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(1);
		cell.setCellValue("In Date");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(2);
		cell.setCellValue("Year Make Model Color & VIN#");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Engine#");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Key");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Sticker");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Remark ");
		cell.setCellStyle(tstyle);
		
		List<String> newCustomer=new ArrayList<String>();
		int count=3;
		int num=0;
		for (int i = 0; i < list.size(); i++) {
			PcurrentStockReport currentStockReport = list.get(i);
			row = hsshsheet.createRow(count);
			if(i==0){
				newCustomer.add(currentStockReport.getUsers());
				// 第一列
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(currentStockReport.getUsers());
			}else{
				if(!newCustomer.contains(currentStockReport.getUsers())){
					row = hsshsheet.createRow(count);
					cell = row.createCell(0);
					cell.setCellStyle(tstyle);
					cell.setCellValue("Total: "+num+" cars");
					num=0;
					count++;
					
					row = hsshsheet.createRow(count);
					newCustomer.add(currentStockReport.getUsers());
					// 第一列
					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(currentStockReport.getUsers());
				}
			}
			// 第二列
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(currentStockReport.getIndate());
			// 第三列
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(currentStockReport.getYear()+","+currentStockReport.getMake()+","+currentStockReport.getModel()+","+currentStockReport.getColor()+",VIN:"+currentStockReport.getVin());
			// 第四列
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(currentStockReport.getEngine());
			// 第五列
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(currentStockReport.getKeynum());
			// 第六列
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(currentStockReport.getSticker());
			// 第七列
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(currentStockReport.getNote());
			num++;
			count++;
			
		}
		row = hsshsheet.createRow(count);
		cell = row.createCell(0);
		cell.setCellStyle(tstyle);
		cell.setCellValue("Total: "+num+" cars");
		
		row = hsshsheet.createRow(count+2);
		cell = row.createCell(0);
		cell.setCellStyle(tstyle);
		cell.setCellValue("Total: "+list.size()+" cars");
		
		return hssfworkbook;
	}
	
	
	/**
	 * DailyLoadingSchedule
	 * Loading Date 装车日期 :
	 */
	public static HSSFWorkbook DailyLoadingScheduleExcel (List<PdailyLoading> list) {
		// 创建工作簿
		HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		// 由工作簿创建工作表
		HSSFSheet hsshsheet = hssfworkbook.createSheet();
		hssfworkbook.setSheetName(0, "DailyLoadingSchedule");
		// 设置内容样式
		HSSFCellStyle style = hssfworkbook.createCellStyle();
		// 设置标题样式
		HSSFCellStyle tstyle = hssfworkbook.createCellStyle();
		HSSFCellStyle ttstyle = hssfworkbook.createCellStyle();
		// 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		tstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ttstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 字体
		HSSFFont font = hssfworkbook.createFont();
		font.setFontName("黑体");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font.setFontHeightInPoints((short)13);
		tstyle.setFont(font);
		
		HSSFFont font1 = hssfworkbook.createFont();
		font1.setFontName("黑体");
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font1.setFontHeightInPoints((short)25);
		ttstyle.setFont(font1);
		
		// 列小标题
		HSSFRow row = hsshsheet.createRow(0);
		// 赋值
		HSSFCell cell = row.createCell( 0);
		hsshsheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		cell.setCellValue("Daily Loading Schedule");
		cell.setCellStyle(ttstyle);
		
		row = hsshsheet.createRow(1);
		hsshsheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
		cell = row.createCell(0);
		//cell.setCellValue("Loading Date 装车日期 :"+time);
		cell.setCellValue("Loading Date 装车日期 :"+list.get(0).getLoaddate());
		cell.setCellStyle(ttstyle);
		
		row = hsshsheet.createRow(2);
		hsshsheet.setDefaultColumnWidth(25);
		hsshsheet.setColumnWidth(4, 15000);
		hsshsheet.setColumnWidth(6, 4000);
		hsshsheet.setColumnWidth(7, 10000);
		cell = row.createCell(0);
		cell.setCellValue("Container#/Seal柜号/船封号");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(1);
		cell.setCellValue("Ocean Export Ref");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(2);
		cell.setCellValue("Booking# 订舱号");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(3);
		cell.setCellValue("vin#");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Year Make Model Color");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Fuel");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(6);
		cell.setCellValue("In Date");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(7);
		cell.setCellValue("customer");
		cell.setCellStyle(tstyle);
		
		List<String> l=new ArrayList<String>();
		int count=3;
		for (int i = 0; i < list.size(); i++) {
			PdailyLoading pdailyLoading = list.get(i);
			if(i==0){
				l.add(pdailyLoading.getLoaddate());
			}else{
				if(!l.contains(pdailyLoading.getLoaddate())){
					l.add(pdailyLoading.getLoaddate());
					row = hsshsheet.createRow(count);
					hsshsheet.addMergedRegion(new CellRangeAddress(count,count,0,4));
					cell = row.createCell(0);
					cell.setCellValue("Daily Loading Schedule");
					cell.setCellStyle(ttstyle);
					count++;
					
					row = hsshsheet.createRow(count);
					hsshsheet.addMergedRegion(new CellRangeAddress(count,count,0,4));
					cell = row.createCell(0);
					cell.setCellValue("Loading Date 装车日期 :"+pdailyLoading.getLoaddate());
					cell.setCellStyle(ttstyle);
					count++;
				}
			}
			row = hsshsheet.createRow(count);
			if(! (l.contains(pdailyLoading.getConnum()) && l.contains(pdailyLoading.getSealnum()))){
				l.add(pdailyLoading.getSealnum());
				l.add(pdailyLoading.getConnum());
				// 第一列
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(pdailyLoading.getConnum()+"/"+pdailyLoading.getSealnum());
			}
			if(! l.contains(pdailyLoading.getUoo())){
				l.add(pdailyLoading.getUoo());
				// 第二列
				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(pdailyLoading.getUoo());
			}
			if(! l.contains(pdailyLoading.getBooknum())){
				l.add(pdailyLoading.getBooknum());
				// 第三列
				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue(pdailyLoading.getBooknum());
			}
			// 第四列
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(pdailyLoading.getVin());
			// 第五列
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(pdailyLoading.getYear()+","+pdailyLoading.getMake()+","+pdailyLoading.getModel()+","+pdailyLoading.getColor());
			// 第六列
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(pdailyLoading.getFuel()+" gallon  "+pdailyLoading.getFuelType());
			// 第七列
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(pdailyLoading.getIndate());
			// 第八列
			hsshsheet.setColumnWidth(0, 10000);
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(pdailyLoading.getUsers());
			count++;
		}
		return hssfworkbook;
	}
	
	/**
	 * Received Cars Report 
	 */
	public static HSSFWorkbook ReceivedCarsReportExcel (List<PcurrentStockReport> list,String from,String to) {
		// 创建工作簿
		HSSFWorkbook hssfworkbook = new HSSFWorkbook();
		// 由工作簿创建工作表
		HSSFSheet hsshsheet = hssfworkbook.createSheet();
		hssfworkbook.setSheetName(0, "ReceivedCarsReport");
		// 设置内容样式
		HSSFCellStyle style = hssfworkbook.createCellStyle();
		// 设置标题样式
		HSSFCellStyle tstyle = hssfworkbook.createCellStyle();
		HSSFCellStyle ttstyle = hssfworkbook.createCellStyle();
		// 居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setWrapText(true);
		tstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		ttstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 字体
		HSSFFont font = hssfworkbook.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)13);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		tstyle.setFont(font);
		
		HSSFFont font1 = hssfworkbook.createFont();
		font1.setFontName("黑体");
		font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		font1.setFontHeightInPoints((short)25);
		ttstyle.setFont(font1);
		//hsshsheet.addMergedRegion(newCellRangeAddress(num+1,num+1,0,2));  //合并单元格（第一个单元格的行数，第二个单元格的行数，第一个单元格的列，第二个单元格的列）
		// 列小标题
		HSSFRow row = hsshsheet.createRow(0);
		// 赋值
		HSSFCell cell = row.createCell( 0);
		hsshsheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		cell.setCellValue("Received Cars Report");
		cell.setCellStyle(ttstyle);
		
		row = hsshsheet.createRow(1);
		hsshsheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
		cell = row.createCell(0);
		cell.setCellValue("From "+from+" to "+to);
		cell.setCellStyle(ttstyle);

		row = hsshsheet.createRow(2);
		hsshsheet.setDefaultColumnWidth(15);
		hsshsheet.setColumnWidth(0, 6500);
		hsshsheet.setColumnWidth(1, 20000);
		hsshsheet.setColumnWidth(2, 6000);
		hsshsheet.setColumnWidth(3, 2500);
		hsshsheet.setColumnWidth(4, 3000);
		hsshsheet.setColumnWidth(7, 5000);
		hsshsheet.setColumnWidth(9, 5000);
		// 赋值
		cell = row.createCell(0);
		cell.setCellValue("Receiving Date");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(1);
		cell.setCellValue("Year Make Model Color & VIN#");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(2);
		cell.setCellValue("Engine#");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(3);
		cell.setCellValue("Key");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(4);
		cell.setCellValue("Sticker");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(5);
		cell.setCellValue("Load Date");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(6);
		cell.setCellValue("Booking #");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(7);
		cell.setCellValue("Container # ");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(8);
		cell.setCellValue("Title Date");
		cell.setCellStyle(tstyle);
		
		cell = row.createCell(9);
		//cell_data_default.setWrapText(true);// 自動換行  
		cell.setCellValue("Remark ");
		cell.setCellStyle(tstyle);
		
		List<String> data=new ArrayList<String>();
		int count=3;
		int num=0;
		for (int i = 0; i < list.size(); i++) {
			PcurrentStockReport pcurrentStockReport = list.get(i);
			row = hsshsheet.createRow(count);
			if(i==0){
				data.add(pcurrentStockReport.getIndate());
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue(pcurrentStockReport.getIndate());
			}else{
				if(!data.contains(pcurrentStockReport.getIndate())){
					cell = row.createCell(0);
					cell.setCellStyle(tstyle);
					cell.setCellValue("Total: "+num+" cars");
					num=0;
					count++;
					
					row = hsshsheet.createRow(count);
					data.add(pcurrentStockReport.getIndate());
					cell = row.createCell(0);
					cell.setCellStyle(style);
					cell.setCellValue(pcurrentStockReport.getIndate());
				}
			}
			// 第三列
			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getYear()+","+pcurrentStockReport.getMake()+","+pcurrentStockReport.getModel()+","+pcurrentStockReport.getColor()+",VIN:"+pcurrentStockReport.getVin());
			// 第四列
			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getEngine());
			// 第五列
			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getKeynum());
			// 第六列
			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getSticker());
			// 第七列
			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getLoaddate());
			// 第八列
			cell = row.createCell(6);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getBooknum());
			// 第九列
			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getConnum());
			// 第十列
			cell = row.createCell(8);
			cell.setCellStyle(style);
			//cell.setCellValue(pcurrentStockReport.getTitle());
			// 第十一列
			cell = row.createCell(9);
			cell.setCellStyle(style);
			cell.setCellValue(pcurrentStockReport.getNote());
			
			count++;
			num++;
		}
		row = hsshsheet.createRow(count);
		cell = row.createCell(0);
		cell.setCellStyle(tstyle);
		cell.setCellValue("Total:"+num+"cars");
		
		row = hsshsheet.createRow(count+2);
		cell = row.createCell(0);
		cell.setCellStyle(tstyle);
		cell.setCellValue("Total:"+list.size()+"cars");
		
		return hssfworkbook;
	}
	
	
	
}

