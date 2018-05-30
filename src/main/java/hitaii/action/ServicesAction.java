package hitaii.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import hitaii.model.Whesdtl;
import hitaii.model.WhesdtlServices;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pservices;
import hitaii.service.ServicesServiceI;
import hitaii.service.WhesdtlServicesServiceI;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author qc
 * 
 * 时间：20160414
 * 
 * 服务Action
 */
@Namespace("/")
@Action(value = "servicesAction")
public class ServicesAction extends BaseAction implements ModelDriven<Pservices>{
	private Pservices pservices = new Pservices();
	@Override
	public Pservices getModel() {
		return pservices;
	}
	
	private ServicesServiceI servicesService;
	private WhesdtlServicesServiceI whesdtlServicesService;
	
	public WhesdtlServicesServiceI getWhesdtlServicesService() {
		return whesdtlServicesService;
	}
	@Autowired
	public void setWhesdtlServicesService(
			WhesdtlServicesServiceI whesdtlServicesService) {
		this.whesdtlServicesService = whesdtlServicesService;
	}
	public ServicesServiceI getServicesService() {
		return servicesService;
	}
	@Autowired
	public void setServicesService(ServicesServiceI servicesService) {
		this.servicesService = servicesService;
	}
	
	/**
	 * By qc
	 * 2016-04-14 
	 * 查询服务
	 */
	public void findServicesDatagrid(){
		DataGrid d = servicesService.findServices(pservices);
		super.writeJson(d);
	}

	/**
	 * By qc
	 * 2016-04-19
	 * 根据id查询services
	 */
	public void findServicesById(){
		Pservices pServices = servicesService.findServicesById(pservices);
		super.writeJson(pServices);
	}
	
	/**
	 * By qc
	 * 2016-04-15
	 * 添加服务
	 */
	public void addServices(){
		String msg = servicesService.addServices(pservices);
		Json j = new Json();
		j.setMsg(msg);
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	/**
	 * By qc
	 * 2016-04-19
	 * 删除服务
	 */
	public void deleteServices(){
		Json j = new Json();
		List<WhesdtlServices> whesdtlServices = whesdtlServicesService.findWhesdtlServicesById(pservices);
		if(null != whesdtlServices && whesdtlServices.size()>0){
			j.setSuccess(true);
			j.setMsg("This service can not be deleted, please refresh");
		}else{
			j = servicesService.deleteServices(pservices, j);
		}
		super.writeJson(j);
	}
	
	/**
	 * By qc
	 * 2016-04-20
	 * 更新服务
	 */
	public void updateServices(){
		Json j = new Json();
		try {
		    Pservices pservice = servicesService.updateServices(pservices);
			j.setMsg("success");
			j.setObj(pservice);
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	
	/**
	 * By qc
	 * 2016-04-25
	 * 根据vin号查询服务
	 */
	public void findServicesByVin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vin = request.getParameter("vin");
		String whesdtlId = request.getParameter("whesdtlId");
 		DataGrid d = servicesService.findServicesByVin(vin, whesdtlId, pservices);
		super.writeJson(d);
	}
	
	/**
	 * By qc
	 * 2016-07-05
	 * 根据company查询服务
	 */
	public void findServicesByCompany(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String companyId = request.getParameter("companyId");
		String whesdtlId = request.getParameter("whesdtlId");
		DataGrid d = servicesService.findServicesByCompanyName(companyId, whesdtlId, pservices);
		super.writeJson(d);
	}
	
	/**
	 * By qc
	 * 2016-05-04
	 * 根据whesnvocc查询服务
	 */
	public void findServicesBywhesnvocc(){
		DataGrid d = servicesService.findServicesBywhesnvocc(pservices);
		super.writeJson(d);

	}
}
