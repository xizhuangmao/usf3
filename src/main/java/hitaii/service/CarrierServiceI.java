package hitaii.service;

import hitaii.pageModel.Pcarrier;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Pcompany;

import java.util.List;
import java.util.Map;

public interface CarrierServiceI {

	/**
	 * 获取carrier下拉列表数据
	 */
	public List<Pcompany> findCarrierName();
	/**
	 * 查找所有carrier用户 DataGrid页面
	 */
	DataGrid getAllCarrierUsers(Pcarrier carrier);
	/**
	 * 保存一个用户
	 */
	boolean saveOneCarrier(Pcarrier carrier);
	/**
	 * 根据Id返回一个carrier对象
	 */
	Pcarrier findCarrierEdit(Pcarrier carrier);
	/**
	 * 更新一个Carrier对象
	 */
	public boolean updateOneCarrier(Pcarrier carrier);
	//查询所有Carrier
	public List<Map<String, String>> findAllCarrier();
	/**
	 * Carrier页面删除一个对象
	 */
	boolean delectOneCarrier(Pcarrier carrier);


	

}
