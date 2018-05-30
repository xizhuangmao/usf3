package hitaii.service;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pcompany;
import hitaii.pageModel.Ptruck;

import java.util.List;
import java.util.Map;

public interface TruckServiceI {
	/**
	 * 加载TruckCo列表 船运公司
	 */
	public List<Pcompany> findTruckCoName();
	
	/**
	 * 获取所有truck的datagrid by zw
	 */
	public DataGrid getAllTruckDataGrid(Ptruck ptruck);
	/**
	 *truckCompany 添加一个truck or edit truck
	 */
	public boolean saveOneTruck(Ptruck ptruck);
	/**
	 * truckCompany edit页面 加载该对象
	 */
	public Ptruck findOneTruck(Ptruck ptruck);
}
