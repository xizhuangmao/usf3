package hitaii.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import hitaii.model.Booknum;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.PcurrentStockReport;
import hitaii.pageModel.PdailyLoading;
import hitaii.pageModel.Pdock;
import hitaii.pageModel.Pvoyage;
import hitaii.pageModel.Pwhesdtl;

public interface BooknumServiceI {
	//加载booknum 下拉列表数据 
	public List<Booknum> findBooknumSelect(Pbooknum booknum) throws ParseException;

	public DataGrid findAllBookingNum(Pbooknum pageBooknum);

	public boolean saveOrUpdateBooknum(Booknum booknum);

	//根据booknum  查找对应的对象
	public Pbooknum findEditBooknum(Pbooknum booknum);
	//Trucking页面 修改了内容  
	public boolean editTruckEditSubmit(Pbooknum booknum);

	/**
	 * NewOrders 页面 保存
	 */
	public boolean UpdateWhesdtlBooknum(Pbooknum booknum);
	//返回DailyLoadingSchedule页面表格数据
	public List<PdailyLoading> getDailyLoadingScheduleDate(Pwhesdtl whesdtl);
	/**
	 * Report下 CurrentStockReport 查看库存的表单数据
	 */
	public List<PcurrentStockReport> getCurrentStockReport(Pwhesdtl whesdtl);
	//将CurrentStockReport表 导出excel
	public HSSFWorkbook getReceivedCarsReportHSSFWorkbook(Pwhesdtl whesdtl);
	/**
	 * Received Cars Report接受车辆的report
	 */
	public List<PcurrentStockReport> getReceivedCarsReport(Pwhesdtl whesdtl);
	//根据Id删除bookNum
	public void deleteBookingNumById(String bookingNumId);

	public Json findEditBookingNum(Pbooknum pageBooknum);

	public Json updateBookingNum(Pbooknum pageBooknum);

	//public List<Pbooknum> findEditBookingNum(String id);
	/**
	 * DockReceipt  Uoo查询
	 */
	public Pdock SearchUooDockReceipt(Pbooknum booknum);
	/**
	 * 仅创建一个对象 NewOrders页面添加单个booknum
	 */
	public Json addBooknum(Pbooknum booknum, Users user);
	
	//根据booknum查询车辆
	public List<Pwhesdtl> findBooknumById(Pbooknum booknum);

	public Booknum findBooknumUooById(Pbooknum booknum);

	public String updateBooknum(Pbooknum booknum);

	public boolean deleteBooknum(Pbooknum booknum);

	public Booknum getBooknum(Pbooknum booknum);

	public List<Booknum> findBooknumByVoyageId(Pvoyage voyage);

}
