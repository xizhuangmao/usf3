package hitaii.service;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pcountry;

import java.util.List;

public interface CountryServiceI {
	/**
	 * 返回下拉列表名
	 */
	public List<Pcountry> findCountryName();
	/**
	 * 分页查询所有数据  country DataGrid页面
	 */
	DataGrid getCountryDatagrid(Pcountry country);
	/**
	 * 保存一个country
	 */
	boolean saveOneCountry(Pcountry country);
	/**
	 * 根据id 返回Country页面对象
	 */
	public Json findCountryById(Pcountry country);
	/**
	 * Country页面 更新对象
	 */
	public Json addCountry(Pcountry country);
	/**
	 * Country页面 删除一个对象
	 */
	boolean deleteCountry(Pcountry country);
	
	public Json editCountry(Pcountry country);

}
