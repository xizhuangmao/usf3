package hitaii.service;

import java.util.List;

import hitaii.model.State;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pstate;

public interface StateServiceI {
	/**
	 * 返回下列表名
	 */
	public List<Pstate> findStateName(Pstate state);
	/**
	 * 展示页面state页面   dategrid数据
	 */
	public DataGrid getStateDataGrid(Pstate state);
	/**
	 * 保存state对象
	 */
	public Json addState(Pstate state);
	/**
	 * 根据id 返回一个State对象 
	 */
	public Json findStateById(Pstate pstate);
	/**
	 * state页面  edit一个对象  更新
	 */
	public Json editState(Pstate pstate);
	/**
	 * state页面 删除一个对象
	 */
	boolean deleteState(Pstate pstate);
	
	public List<State> findStateByCountryId(Pstate pstate);
}
