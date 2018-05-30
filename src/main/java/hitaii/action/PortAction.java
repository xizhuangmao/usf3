package hitaii.action;

import hitaii.pageModel.Json;
import hitaii.pageModel.Pport;
import hitaii.service.PortServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *  by mh 2016 1 26
 *	港口Action
 */
@Namespace("/")
@Action(value = "portAction")
public class PortAction extends BaseAction implements ModelDriven<Pport>{
	Pport pport =new Pport();
	@Override
	public Pport getModel() {
		return pport;
	}
	private PortServiceI portService;
	public PortServiceI getPortService() {
		return portService;
	}
	@Autowired
	public void setPortService(PortServiceI portService) {
		this.portService = portService;
	}
	
	/**
     * port manage页面 的DataGrid数据
     */
    public void getPortDataGrid(){
    	super.writeJson(portService.getPortDataGrid(pport));	
    }
    
    /**
     * port页面添加一个对象 或者修改一个对象
     */
    public void addPort(){
    	Json j = portService.addPort(pport);
		super.writeJson(j);
    }
    
    /**
     * port manage页面 Edit加载 对象
     */
    public void findPortById(){
    	Json j  = portService.findPortById(pport);
		super.writeJson(j);
    }
    
    /**
	 * 删除一个对象
	 */
	public void deletePort(){
		Json j = new Json();
		boolean b = portService.deletePort(pport);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Port success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Port failed...");
		}
		super.writeJson(j);
	}

	//查找所有port
	public void getPortName(){
		super.writeJson(portService.getPortName());
	}
	
	//更新port
	public void editPort(){
		Json j = portService.editPort(pport);
		super.writeJson(j);
	}
}
