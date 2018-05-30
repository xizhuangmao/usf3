package hitaii.action;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.PcurrentStockReport;
import hitaii.pageModel.PdailyLoading;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.BooknumServiceI;
import hitaii.service.WhesdtlServiceI;
import hitaii.util.ExeclUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;


@Namespace("/")
@Action(value = "whesdtlAction")
public class WhesdtlAction extends BaseAction implements ModelDriven<Pwhesdtl>{
	private  Pwhesdtl whesdtl =new Pwhesdtl();
	@Override
	public Pwhesdtl getModel() {
		return whesdtl;
	}
	private WhesdtlServiceI whesdtlService;
	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}
	private BooknumServiceI booknumService;
	
	public BooknumServiceI getBooknumService() {
		return booknumService;
	}
	@Autowired
	public void setBooknumService(BooknumServiceI booknumService) {
		this.booknumService = booknumService;
	}
	

	/**
	 * Report下 CurrentStockReport 查看库存的表单数据
	 */
	public void getCurrentStockReport(){
		Json j = new Json();
		List<PcurrentStockReport> currentStockReports= booknumService.getCurrentStockReport(whesdtl);
		if(currentStockReports.size()>0){
			j.setSuccess(true);
			j.setMsg("success... ");
			j.setObj(currentStockReports);
		}else{
			j.setSuccess(false);
			j.setMsg("failed... ");
		}
		super.writeJson(j);
	}
	
	/**
	 * CurrentStockReport导出Excel
	 */
	public void getCurrentStockReportExcel(){
		HttpServletResponse response = ServletActionContext.getResponse();
		List<PcurrentStockReport> currentStockReports= booknumService.getCurrentStockReport(whesdtl);
		HSSFWorkbook hssfworkbook = ExeclUtil.CurrentStockReportExcel(currentStockReports,whesdtl.getLoaddate());
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("CurrentStockReport.xls".getBytes()));  
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			hssfworkbook.write(os); 
			os.flush();  
			os.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * Received Cars Report接受车辆的report
	 */
	public void getReceivedCarsReport(){
		Json j = new Json();
		List<PcurrentStockReport> receivedCarsReports= booknumService.getReceivedCarsReport(whesdtl);
			j.setSuccess(true);
			j.setMsg("success... ");
			j.setObj(receivedCarsReports);
		super.writeJson(j);
	}
	
	/**
	 * Received Cars Report导出Excel
	 */
	public void getReceivedCarsReportExcel(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HSSFWorkbook hssfworkbook = booknumService.getReceivedCarsReportHSSFWorkbook(whesdtl);
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("ReceivedCarsReport.xls".getBytes()));  
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			hssfworkbook.write(os); 
			os.flush();  
			os.close();  
		} catch (IOException e) {
			
			e.printStackTrace();
		}  
	}
	
	/**
	 * Report下 DailyLoadingSchedule页面表格
	 */
	public void getDailyLoadingScheduleDate(){
		Json j = new Json();
		if(("".equals(whesdtl.getLoadingFrom())|| null==whesdtl.getLoadingFrom())&&("".equals(whesdtl.getLoadingTo())|| null==whesdtl.getLoadingTo())){
			j.setSuccess(false);
			j.setMsg("failed...");
		}else{
			List<PdailyLoading> pdailyLoading= booknumService.getDailyLoadingScheduleDate(whesdtl);
			if(pdailyLoading.size()>0){
				j.setSuccess(true);
				j.setMsg("success... ");
				j.setObj(pdailyLoading);
			}else{
				j.setSuccess(false);
				j.setMsg("failed... ");
			}
		}
		super.writeJson(j);
	}
	/**
	 * DailyLoadingSchedule导出Excel
	 */
	public void getDailyLoadingScheduleExcel(){
		HttpServletResponse response = ServletActionContext.getResponse();
		List<PdailyLoading> pdailyLoadings= booknumService.getDailyLoadingScheduleDate(whesdtl);
		HSSFWorkbook hssfworkbook = ExeclUtil.DailyLoadingScheduleExcel(pdailyLoadings);
		try {
			response.addHeader("Content-Disposition", "attachment;filename="+ new String("DailyLoadingSchedule.xls".getBytes())); 
			response.setContentType("application/vnd.ms-excel;charset=utf-8");  
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			hssfworkbook.write(os); 
			os.flush();  
			os.close();  
		} catch (IOException e) {
			
			e.printStackTrace();
		}  
	}

	/**
	 * 公共方法 导出excel
	 */
	public void getAllExcel(){
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request =ServletActionContext.getRequest();
		response.addHeader("Content-Disposition", "attachment;filename="+ new String(request.getParameter("txtName").getBytes()) + ".xlsx");  
		response.setContentType("application/msexcel;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.append("<html>\n<head>\n");  
			out.append("<style type=\"text/css\">\n.pb{font-size:13px;border-collapse:collapse;} "+  
	                        "\n.pb th{font-weight:bold;text-align:center;border:0.5pt solid windowtext;padding:2px;} " +  
	                        "\n.pb td{border:0.5pt solid windowtext;padding:2px;}\n</style>\n</head>\n");  
			out.append("<body>\n" + request.getParameter("txtContent") + "\n</body>\n</html>");  
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 查找Uoo下面的车辆服务
	 */
	public void findUooAndWhesdtlServices(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		DataGrid d = null;
		if(null != user){
			d = whesdtlService.findUooAndWhesdtlServices(whesdtl, user);
		}
		super.writeJson(d);
	}
	
	/**
	 * 查找车辆服务
	 */
	public void findWhesdtlServices(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		List<Pwhesdtl> d = new ArrayList<Pwhesdtl>();
		if(null != user){
			d = whesdtlService.findWhesdtlServices(user);
		}
		super.writeJson(d);
	}
	
}