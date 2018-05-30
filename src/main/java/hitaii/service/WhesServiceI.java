package hitaii.service;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pwhes;

import java.util.List;

public interface WhesServiceI {

	public List<Pwhes> findAllWhes();
	/**
     * warehouse manage页面 的DataGrid数据
     */
	public DataGrid getAllMakeDataGrid(Pwhes pwhes);
	/**
     * WarehouseManage页面  add一个warehouse对象
     */
	public boolean saveOneWhes(Pwhes pwhes);
	 /**
     * WarehouseManage页面 Edit页面中  获取warehouse对象
     */
	public Pwhes findWarehouseEditObject(Pwhes pwhes);
	/**
     * WarehouseManage页面 删除一个Warehouse对象
     */
	public boolean delectOneWarehouse(Pwhes pwhes);

}
