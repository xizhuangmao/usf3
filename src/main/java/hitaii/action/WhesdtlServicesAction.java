package hitaii.action;

import java.util.List;

import hitaii.model.WhesdtlServices;
import hitaii.pageModel.Json;
import hitaii.pageModel.PwhesdtlServices;
import hitaii.service.WhesdtlServicesServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Namespace("/")
@Action(value = "whesdtlServicesAction")
public class WhesdtlServicesAction extends BaseAction implements ModelDriven<PwhesdtlServices>{
	
	PwhesdtlServices pwhesdtlServices = new PwhesdtlServices();
	
	@Override
	public PwhesdtlServices getModel() {
		return pwhesdtlServices;
	}
	
	private WhesdtlServicesServiceI whesdtlServicesService;
	
	public WhesdtlServicesServiceI getWhesdtlServicesService() {
		return whesdtlServicesService;
	}
	@Autowired
	public void setWhesdtlServicesService(
			WhesdtlServicesServiceI whesdtlServicesService) {
		this.whesdtlServicesService = whesdtlServicesService;
	}


	/**
	 * 修改折扣
	 */
	public void updateWhesdtlServices(){
		Json j = whesdtlServicesService.updateWhesdtlServices(pwhesdtlServices);
		super.writeJson(j);
	}
	
	/**
	 * 修改实际付款
	 */
	public void updateWhesdtlServicesPay(){
		Json j = whesdtlServicesService.updateWhesdtlServicesPay(pwhesdtlServices);
		super.writeJson(j);
	}
	
	/**
	 * 修改支付状态
	 */
	public void updateWhesdtlServicesPayState(){
		Json j = new Json();
		WhesdtlServices whesdtlServices = whesdtlServicesService.updateWhesdtlServicesPayState(pwhesdtlServices);
		j.setObj(whesdtlServices);
		j.setSuccess(true);
		super.writeJson(j);
	}
	
	/**
	 * 根据车辆id查找车辆服务
	 */
	public void findServicesByWhesdtlId(){
		List<WhesdtlServices> whesdtlServices = whesdtlServicesService.findServicesByWhesdtlId(pwhesdtlServices);
		super.writeJson(whesdtlServices);
	}
}
