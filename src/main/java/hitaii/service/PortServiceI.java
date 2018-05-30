package hitaii.service;

import java.util.List;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pport;

public interface PortServiceI {

	/**
     * port manage页面 的DataGrid数据
     */
	public DataGrid getPortDataGrid(Pport pport);
	/**
     * port页面添加一个对象 或者修改一个对象
     */
	public Json addPort(Pport pport);
	 /**
     * port manage页面 Edit加载 对象
     */
	public Json findPortById(Pport pport);
	/**
	 * 删除一个对象
	 */
	public boolean deletePort(Pport pport);
	
	public List<Pport> getPortName();
	
	public Json editPort(Pport pport);

}
