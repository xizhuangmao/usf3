package hitaii.action;

import hitaii.pageModel.Json;
import hitaii.pageModel.Pwhes;
import hitaii.service.WhesServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	by mh  2016.01.04
 * 仓库Action
 */
@Namespace("/")
@Action(value = "whesAction")
public class WhesAction extends BaseAction implements ModelDriven<Pwhes>{
	private Pwhes pwhes = new Pwhes();
	@Override
	public Pwhes getModel() {
		// TODO Auto-generated method stub
		return pwhes;
	}
	 private WhesServiceI whesService;
	 public WhesServiceI getWhesService() {
		return whesService;
	}
	 @Autowired
	public void setWhesService(WhesServiceI whesService) {
		this.whesService = whesService;
	}

	/**
     * warehouse manage页面 的DataGrid数据
     * 引用：usersWhes.jsp by zw
     */
    public void getAllWarehouseDataGrid(){
    	super.writeJson(whesService.getAllMakeDataGrid(pwhes));	
    }
    /**
     * WarehouseManage页面  add一个warehouse对象
     */
    public void saveOneWhes(){
    	Json j = new Json();
		boolean b = whesService.saveOneWhes(pwhes);
		if(b){
			j.setSuccess(true);
			j.setMsg("save Whes success...");
		}else{
			j.setSuccess(false);
			j.setMsg("save Whes failed...");
		}
		super.writeJson(j);
    }
    /**
     * WarehouseManage页面 Edit页面中  获取warehouse对象
     */
    public void findWarehouseEditObject(){
    	Json j = new Json();
    	pwhes = whesService.findWarehouseEditObject(pwhes);
		if(null == pwhes){
			j.setSuccess(false);
			j.setMsg("Load...failed");
		}else{
			j.setObj(pwhes);
			j.setMsg("success");
			j.setSuccess(true);
		}
		super.writeJson(j);
    }
    /**
     * WarehouseManage页面 删除一个Warehouse对象
     */
    public void delectOneWarehouse(){
    	Json j = new Json();
		boolean b = whesService.delectOneWarehouse(pwhes);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Warehouse success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Warehouse failed...");
		}
		super.writeJson(j);
    }
    
}
