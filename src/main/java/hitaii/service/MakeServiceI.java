package hitaii.service;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmake;

import java.util.List;

public interface MakeServiceI {

	public List<Pmake> findAllMake();

	/**
	 * 返回Make页面的DataGrid数据
	 */
	public DataGrid getAllMakeDataGrid(Pmake pmake);
	/**
	 * 保存新建Make对象
	 */
	public Json addMake(Pmake pmake);
	/**
	 * 页面的make下拉列表
	 */
	public List<Pmake> findMakeName();
	/**
	 * 根据id 返回一个Make对象 
	 */
	public Json findMakeById(Pmake pmake);
	/**
	 * Make页面 删除一个对象
	 */
	public boolean deleteMake(Pmake pmake);
	
	//修改make
	public Json editMake(Pmake pmake);

}
