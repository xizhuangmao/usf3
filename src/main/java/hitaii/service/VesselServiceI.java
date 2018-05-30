package hitaii.service;

import hitaii.model.Vessel;

import java.util.List;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pvessel;

import java.util.Map;

public interface VesselServiceI {


	public List<Vessel> findVesselByCarrierId(String carrierId);

	/**
	 * 根据carrierId 加载Vessel列表和下拉列表
	 */
	public List<Map<String, String>> findVesselName(Pvessel vessel);

	public List<Pvessel> findByCarrierId(Pvessel vessel);
	/**
	 * 加载Vessel页面 DataGrid数据
	 */
	public DataGrid getVesselDataGrid(Pvessel vessel);
	/**
	 * 根据id 查找Vessel对象
	 */
	public Json findVesselById(Pvessel vessel);
	/**
	 * 保存Vessel对象
	 */
	public Json addVessel(Pvessel vessel);
	/**
	 * 删除一个对象
	 */
	public boolean deleteVessel(Pvessel vessel);
	
	//修改vessel
	public Json editVessel(Pvessel vessel);

}
