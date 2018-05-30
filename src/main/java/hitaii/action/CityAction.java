package hitaii.action;

import java.util.List;

import hitaii.model.City;
import hitaii.model.State;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcity;
import hitaii.pageModel.Pstate;
import hitaii.service.CityServiceI;
import hitaii.service.StateServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 *	@author mh
 * 
 * 时间：20160108
 * 
 * 城市Action
 */
@Namespace("/")
@Action(value = "cityAction")
public class CityAction extends BaseAction implements ModelDriven<Pcity>{
	private Pcity city = new Pcity();
	@Override
	public Pcity getModel() {
		return city;
	}
	
	private CityServiceI cityService;
	public CityServiceI getCityService() {
		return cityService;
	}
	@Autowired
	public void setCityService(CityServiceI cityService) {
		this.cityService = cityService;
	}
	private StateServiceI stateService;
	public StateServiceI getStateService() {
		return stateService;
	}
	@Autowired
	public void setStateService(StateServiceI stateService) {
		this.stateService = stateService;
	}
	/**
	 * 返回下拉列表数据
	 */
	public void getCityName(){
		super.writeJson(cityService.findCityName());	
	}
	
	/**
	 * 返回City页面的DataGrid数据
	 */
	public void getCityDataGrid(){
		super.writeJson(cityService.getCityDataGrid(city));	
	}
	/**
	 * City页面 保存创建新的城市
	 */
	public void addCity(){
		Json j = cityService.addCity(city);
		super.writeJson(j);
	}
	/**
	 * 根据id 返回一个City对象 
	 */
	public void findCityById(){
		Json j = cityService.findCityById(city);
		super.writeJson(j);
	}
	/**
	 * city页面  edit一个对象  更新
	 */
	public void editCity(){
		Json j = cityService.editCity(city);
		super.writeJson(j);
	}
	/**
	 * city页面 删除一个对象
	 */
	public void deleteCity(){
		Json j = new Json();
		boolean b = cityService.deleteCity(city);
		if(b){
			j.setSuccess(true);
			j.setMsg("delect City success...");
		}else{
			j.setSuccess(false);
			j.setMsg("delect City failed...");
		}
		super.writeJson(j);
	}
	/**
	 * 根据stateId查询city
	 */
	public void findCityByStateId(){
		List<City> cities = cityService.findCityByStateId(city);
		super.writeJson(cities);
	}
}
