package hitaii.service;

import hitaii.model.Model;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmodel;

import java.util.List;

public interface ModelServiceI {

	public List<Model> findAllModelByMakeId(Pmodel pmodel);
	/**
	 * 返回Model页面的DataGrid数据
	 */
	public DataGrid getModelDataGrid(Pmodel pmodel);
	/**
	 * 保存新建Model对象
	 */
	public Json addModel(Pmodel pmodel);
	/**
	 * 根据id 返回一个Model对象 
	 */
	public Json findModelById(Pmodel pmodel);
	/**
	 * 根据id 删除一个Model对象 
	 */
	public Json deleteModel(Pmodel pmodel);
	
	public List<Pmodel> findModelName();
	
	public Json editModel(Pmodel pmodel);

}
