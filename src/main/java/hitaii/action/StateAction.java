package hitaii.action;

import java.util.List;

import hitaii.model.Country;
import hitaii.model.State;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pstate;
import hitaii.service.StateServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 省Action
 */
@Namespace("/")
@Action(value = "stateAction")
public class StateAction extends BaseAction implements ModelDriven<Pstate>{
	 private Pstate pstate = new Pstate();
		@Override
		public Pstate getModel() {
			// TODO Auto-generated method stub
			return pstate;
		};
	private StateServiceI stateService;
	public StateServiceI getStateService() {
		return stateService;
	}
	@Autowired
	public void setStateService(StateServiceI stateService) {
		this.stateService = stateService;
	}
	
	
	/**
	 * 返回下列表名
	 */
	public void getStateName(){
		super.writeJson(stateService.findStateName(pstate));	
	}
	/**
	 * 展示页面date页面 dategrid数据
	 */
	public void getStateDataGrid(){
		DataGrid d = stateService.getStateDataGrid(pstate);
		super.writeJson(d);
	}
	/**
	 * 保存state对象
	 */
	public void addState(){
		Json j = stateService.addState(pstate);
		super.writeJson(j);
	}
	/**
	 * 根据id 返回一个State对象 
	 */
	public void findStateById(){
		Json j = stateService.findStateById(pstate);
		super.writeJson(j);
	}
	/**
	 * state页面  edit一个对象  更新
	 */
	public void editState(){
		Json j = stateService.editState(pstate);
		super.writeJson(j);
	}
	/**
	 * state页面 删除一个对象
	 */
	public void deleteState(){
		Json j = new Json();
		boolean b = stateService.deleteState(pstate);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect State success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect State failed...");
		}
		super.writeJson(j);
	}
	/**
	 * 根据countryId查询state
	 */
	public void findStateByCountryId(){
		List<State> states = stateService.findStateByCountryId(pstate);
		super.writeJson(states);
	}
}
