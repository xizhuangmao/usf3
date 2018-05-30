package hitaii.action;

import hitaii.pageModel.Pcarrier;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.service.CarrierServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 航运公司Action
 */
@Namespace("/")
@Action(value = "carrierAction")
public class CarrierAction extends BaseAction implements ModelDriven<Pcarrier>{
	
	private Pcarrier carrier = new Pcarrier();
	
	private CarrierServiceI carrierService;
	
	public CarrierServiceI getCarrierService() {
		return carrierService;
	}
	@Autowired
	public void setCarrierService(CarrierServiceI carrierService) {
		this.carrierService = carrierService;
	}
	@Override
	public Pcarrier getModel() {
		return carrier;
	}
	
	/**
	 * 获取carrier下拉列表数据
	 */
	public void getCarrierName(){
		super.writeJson(carrierService.findCarrierName());
	}
	/**
	 * 查找所有carrier用户 DataGrid页面
	 */
	public void getAllCarrierUsers(){
		DataGrid d = carrierService.getAllCarrierUsers(carrier);
		super.writeJson(d);
	}
	/**
	 * 保存一个carrier用户
	 */
	public void addCarrier(){
		Json j = new Json();
		boolean b =false;
		if(null == carrier.getId() || "".equals(carrier.getId())){
			b = carrierService.saveOneCarrier(carrier);
		}else{
			//跟新carrier一个对象
			b = carrierService.updateOneCarrier(carrier);
		}
		if(b){
			j.setSuccess(true);
			j.setMsg("success");
		}else{
			j.setSuccess(false);
			j.setMsg("failed");
		}
		super.writeJson(j);
	}
	/**
	 * 根据Id返回一个carrier对象
	 */
	public void findCarrierEdit(){
		Json j = new Json();
		carrier = carrierService.findCarrierEdit(carrier);
		if(null == carrier){
			j.setSuccess(false);
			j.setMsg("Load...failed");
		}else{
			j.setObj(carrier);
			j.setMsg("success");
			j.setSuccess(true);
		}
		super.writeJson(j);
	}
	/**
	 * Carrier页面删除一个对象
	 */
	public void delectOneCarrier(){
		Json j = new Json();
		boolean b = carrierService.delectOneCarrier(carrier);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Carrier success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Carrier failed...");
		}
		super.writeJson(j);
	}
	
	
}
