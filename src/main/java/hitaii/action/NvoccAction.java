package hitaii.action;

import java.util.List;

import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pnvocc;
import hitaii.service.NvoccServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *  by mh 2016 1 27
 *	Nvocc代理公司Action
 */
@Namespace("/")
@Action(value = "nvoccAction")
public class NvoccAction extends BaseAction implements ModelDriven<Pnvocc>{
	Pnvocc pnvocc =new Pnvocc();
	@Override
	public Pnvocc getModel() {
		return pnvocc;
	}
	private NvoccServiceI nvoccService;
	public NvoccServiceI getNvoccService() {
		return nvoccService;
	}
	@Autowired
	public void setNvoccService(NvoccServiceI nvoccService) {
		this.nvoccService = nvoccService;
	}

	/**
	 * 查找Nvocc页面 DataGrid数据
	 * 引用：usersNvocc.jsp by zw
	 */
	public void getAllNvoccUsers(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		DataGrid d = nvoccService.getAllNvoccUsers(pnvocc);
		super.writeJson(d);
	}
	
	/**
	 * nvocc页面 添加或者修改一个对象
	 */
	public void saveOrUpdateOneNvocc(){
		Json j = new Json();
		boolean b = nvoccService.saveOrUpdateOneNvocc(pnvocc);
		if(b){
			j.setSuccess(true);
			j.setMsg("save Nvocc success...");
		}else{
			j.setSuccess(false);
			j.setMsg("save Nvocc failed...");
		}
		super.writeJson(j);
	}
	
	/**
	 * nvocc页面 edit时加载数据
	 */
	public void findNvoccEditObject(){
		Json j = new Json();
		pnvocc = nvoccService.findNvoccEditObject(pnvocc);
		if(null==pnvocc){
			j.setSuccess(false);
			j.setMsg("failed");
		}else{
			j.setSuccess(true);
			j.setMsg("success");
			j.setObj(pnvocc);
		}
		super.writeJson(j);
	}
	/**
	 * nvocc页面 删除一个对象
	 */
	public void delectOneNvocc(){
		Json j = new Json();
		boolean b = nvoccService.delectOneNvocc(pnvocc);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect Nvocc success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect Nvocc failed...");
		}
		super.writeJson(j);
	}
	
	/**
	 * 获取NVOCC下拉列表数据
	 */
	public void getNvoccName(){
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		Users user = sessionInfo.getUsers();
		List<Pnvocc> d =null;
		if(null != user){
			d = nvoccService.findNvoccName(user);
		}
		super.writeJson(d);
		
	}
	
}
