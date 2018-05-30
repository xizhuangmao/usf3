package hitaii.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import hitaii.model.Booknum;
import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pbooknum;
import hitaii.service.BcompanyServiceI;
import hitaii.service.BooknumServiceI;
import hitaii.service.CarrierServiceI;
import hitaii.service.PortServiceI;
import hitaii.service.UserServiceI;
import hitaii.service.VesselServiceI;
import hitaii.service.VoyageServiceI;
import hitaii.service.WhesdtlServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author qc
 * 
 * 时间：201601012
 * 
 * 示例
 * BookingNum
 */
@Namespace("/")
@Action(value = "bookingManageAction")
public class BookingManageAction extends BaseAction implements ModelDriven<Pbooknum>{
	private Pbooknum pageBooknum = new Pbooknum();
	@Override
	public Pbooknum getModel() {
		return pageBooknum;
	}
	
	private UserServiceI userService;
	private VesselServiceI vesselService;
	private VoyageServiceI voyageService;
	private PortServiceI portService;
	private BooknumServiceI booknumService;
	private CarrierServiceI carrierService;
	private BcompanyServiceI bcompanyService;
	private WhesdtlServiceI whesdtlService;
	
	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}
	public BcompanyServiceI getBcompanyService() {
		return bcompanyService;
	}
	@Autowired
	public void setBcompanyService(BcompanyServiceI bcompanyService) {
		this.bcompanyService = bcompanyService;
	}
	public CarrierServiceI getCarrierService() {
		return carrierService;
	}
	@Autowired
	public void setCarrierService(CarrierServiceI carrierService) {
		this.carrierService = carrierService;
	}
	public BooknumServiceI getBooknumService() {
		return booknumService;
	}
	@Autowired
	public void setBooknumService(BooknumServiceI booknumService) {
		this.booknumService = booknumService;
	}
	public PortServiceI getPortService() {
		return portService;
	}
	@Autowired
	public void setPortService(PortServiceI portService) {
		this.portService = portService;
	}
	public VoyageServiceI getVoyageService() {
		return voyageService;
	}
	@Autowired
	public void setVoyageService(VoyageServiceI voyageService) {
		this.voyageService = voyageService;
	}
	public VesselServiceI getVesselService() {
		return vesselService;
	}
	@Autowired
	public void setVesselService(VesselServiceI vesselService) {
		this.vesselService = vesselService;
	}
	public UserServiceI getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}
	
	//查询所有的BookingNum
	public void findAllBookingNum(){
		DataGrid d = booknumService.findAllBookingNum(pageBooknum);
		super.writeJson(d);
	}
	
	//根据id查询BookingNum
	public void findEditBookingNum(){
		Json j = booknumService.findEditBookingNum(pageBooknum);
		super.writeJson(j);
	}
	
	//根据Id删除bookNum
	public void deleteBookingNum(){
		Json j = new Json();
		HttpServletRequest request = ServletActionContext.getRequest();
		String bookingNumId = request.getParameter("id");
		List<Whesdtl> whesdtls = whesdtlService.findVehicleByBooknumId(bookingNumId);
		if(null != whesdtls && whesdtls.size()>0){
			j.setMsg("This booknum can not be deleted, please refresh");
		}else{
			booknumService.deleteBookingNumById(bookingNumId);
			j.setMsg("删除成功!");
			j.setSuccess(true);
		}
		super.writeJson(j);
	}
	
	//修改订单
	public void updateBookingNum(){
		Json j = new Json();
		Booknum booknums = booknumService.getBooknum(pageBooknum);
		if(null != booknums){
			j.setMsg("Duplication of booknum! Please edit your booknum.");
		}else{
			j = booknumService.updateBookingNum(pageBooknum);
		}
		super.writeJson(j);
	}
}
