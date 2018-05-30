package hitaii.action;

import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.WhesdtlServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 航运公司Action
 */
@Action(value = "exportAction")
public class ExportAction extends BaseAction implements ModelDriven<Pwhesdtl>{
	private Pwhesdtl whesdtl = new Pwhesdtl();
	private WhesdtlServiceI whesdtlService;

	public WhesdtlServiceI getWhesdtlService() {
		return whesdtlService;
	}
	@Autowired
	public void setWhesdtlService(WhesdtlServiceI whesdtlService) {
		this.whesdtlService = whesdtlService;
	}
	@Override
	public Pwhesdtl getModel() {
		return whesdtl;
	}

	/**
	 * 加载NewOrders页面
	 */
	public void NewOrders(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		DataGrid d =null;
		if(null != user){
			d = whesdtlService.findWhesdtlNotUOODataGrid(whesdtl,user);
		}
		super.writeJson(d);
	}
	
	/**
	 * Trucking页面查询的结果
	 */
	public void getValidOrders(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		DataGrid d =null;
		if(null != user){
			d = whesdtlService.findValidOrdersDataGrid(whesdtl,user);
		}
		super.writeJson(d);
	}
}
