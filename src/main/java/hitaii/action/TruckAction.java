package hitaii.action;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Ptruck;
import hitaii.pageModel.Puser;
import hitaii.service.TruckServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 卡车公司Action
 */
@Namespace("/")
@Action(value = "truckAction")
public class TruckAction extends BaseAction implements ModelDriven<Ptruck>{
	private Ptruck ptruck=new Ptruck();
	private TruckServiceI truckService;
	public TruckServiceI getTruckService() {
		return truckService;
	}
	@Autowired
	public void setTruckService(TruckServiceI truckService) {
		this.truckService = truckService;
	}
	@Override
	public Ptruck getModel() {
		return ptruck;
	}
	
	/**
	 * 加载TruckCo列表 船运公司
	 */
	public void getTruckCoName(){
		super.writeJson(truckService.findTruckCoName());
	}
	
	/**
	 * 获取所有truck的datagrid by zw
	 * 引用：usersTruck.jsp by zw
	 * 引用：truckCompany.jsp mh
	 */
	public void getAllTruckDataGrid(){
		DataGrid d = truckService.getAllTruckDataGrid(ptruck);
		super.writeJson(d);
	}
	
	/**
	 *truckCompany 添加一个truck 
	 */
	public void saveOneTruck(){
		Json j = new Json();
		boolean b = truckService.saveOneTruck(ptruck);
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
	 * truckCompany edit页面 加载该对象
	 */
	public void findOneTruck(){
		Json j = new Json();
		ptruck = truckService.findOneTruck(ptruck);
		if(null==ptruck){
			j.setSuccess(false);
			j.setMsg("failed");
		}else{
			j.setSuccess(true);
			j.setMsg("success");
			j.setObj(ptruck);
		}
		
		super.writeJson(j);
	}
}
