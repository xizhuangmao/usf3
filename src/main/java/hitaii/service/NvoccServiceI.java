package hitaii.service;

import java.util.List;
import java.util.Map;

import hitaii.model.Users;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pnvocc;

public interface NvoccServiceI {
	/**
	 * 查找Nvocc页面 DataGrid数据
	 */
	public DataGrid getAllNvoccUsers(Pnvocc pnvocc);
	/**
	 * nvocc页面 添加或者修改一个对象
	 */
	public boolean saveOrUpdateOneNvocc(Pnvocc pnvocc);
	/**
	 * nvocc页面 edit时加载数据
	 */
	public Pnvocc findNvoccEditObject(Pnvocc pnvocc);
	/**
	 * nvocc页面 删除一个对象
	 */
	public boolean delectOneNvocc(Pnvocc pnvocc);
	/**
	 * 获取NVOCC下拉列表数据
	 * @param user 
	 */
	public List<Pnvocc> findNvoccName(Users user);
	/**
	 * 获取NVOCC下拉列表数据
	 * @param user 
	 * @return 
	 */
	public List<Map<String, String>> findAllNvocc();

}
