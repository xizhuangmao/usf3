package hitaii.action;

import java.util.List;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pvessel;
import hitaii.service.VesselServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 航班Action
 */

@Namespace("/")
@Action(value = "vesselAction")
public class VesselAction extends BaseAction implements ModelDriven<Pvessel>{

	private Pvessel vessel = new Pvessel();

	private VesselServiceI vesselService;

	public VesselServiceI getVesselService() {
		return vesselService;
	}
	@Autowired
	public void setVesselService(VesselServiceI vesselService) {
		this.vesselService = vesselService;
	}
	@Override
	public Pvessel getModel() {
		return vessel;
	}
	
	/**
	 * 加载Vessel列表和下拉列表
	 */
	public void getVesselName(){
		super.writeJson(vesselService.findVesselName(vessel));
	}
	/**
	 * 根据CarrierId 加载下拉列表
	 */
	public void getVesselByCarrierId(){
		super.writeJson(vesselService.findVesselName(vessel));
	}
	
	public void getOrdersAddVessel(){
		Json j = new Json();
		if(null == vessel){
			j.setSuccess(false);
			j.setMsg("Load Fail");
		}else{
			List<Pvessel>list = vesselService.findByCarrierId(vessel);
			if(null==list){
				j.setSuccess(false);
				j.setMsg("Load Fail");
			}else{
				j.setSuccess(true);
				j.setMsg("succeed!");
				j.setObj(list);
			}
		}
		super.writeJson(j);
	}
	/**
	 * 加载Vessel页面 DataGrid数据
	 */
	public void getVesselDataGrid(){
		DataGrid d = vesselService.getVesselDataGrid(vessel);
		super.writeJson(d);
	}
	/**
	 * 根据id 查找Vessel对象
	 */
	public void findVesselById(){
		Json j = vesselService.findVesselById(vessel);
		super.writeJson(j);
	}
	/**
	 * 保存Vessel对象
	 */
	public void addVessel(){
		Json j = vesselService.addVessel(vessel);
		super.writeJson(j);
	}
	
	/**
	 * 删除一个对象
	 */
	public void deleteVessel(){
		Json j = new Json();
		boolean b = vesselService.deleteVessel(vessel);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Vessel success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Vessel failed...");
		}
		super.writeJson(j);
	}
	
	/**
	 * 修改vessel
	 */
	public void editVessel(){
		Json j = vesselService.editVessel(vessel);
		super.writeJson(j);
	}
}
