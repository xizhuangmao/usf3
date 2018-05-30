package hitaii.service;

import java.util.List;

import hitaii.model.City;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcity;

public interface CityServiceI {
	/**
	 * 返回下拉列表数据
	 */
	public List<Pcity>  findCityName();

	/**
	 * 返回City页面的DataGrid数据
	 */
	DataGrid getCityDataGrid(Pcity city);
	/**
	 * City页面 保存创建新的城市
	 */
	public Json addCity(Pcity city);
	/**
	 * 根据id 返回一个City对象 
	 */
	public Json findCityById(Pcity city);
	/**
	 * city页面  edit一个对象  更新
	 */
	public Json editCity(Pcity city);
	/**
	 * city页面 删除一个对象
	 */
	boolean deleteCity(Pcity pcity);

	public List<City> findCityByStateId(Pcity city);

}
