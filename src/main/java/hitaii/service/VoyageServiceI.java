package hitaii.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.BeansException;

import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pvoyage;

public interface VoyageServiceI {

	/**
	 * 根据vesselId 加载Voyage列表  和下拉列表
	 * @throws ParseException 
	 * @throws BeansException 
	 */
	public List<Pvoyage> findVoyageName(Pvoyage voyage) throws BeansException, ParseException;
	/**
	 * 加载Voyage页面 DataGrid数据
	 */
	public DataGrid getVoyageDataGrid(Pvoyage voyage);
	/**
	 * voyage页面add添加或者edit修改 一个对象
	 */
	public Json addVoyage(Pvoyage pvoyage);
	/**
	 * 打开Voyage页面 加载对象
	 */
	public Pvoyage findVoyageById(Pvoyage voyage);
	/**
	 * Voyage页面 删除功能
	 */
	public boolean deleteVoyage(Pvoyage voyage);
	/**
	 * 根据id查询voyage对象
	 */
	public Pvoyage getVoyageById(Pvoyage voyage);
	/**
	 * 修改voyage对象
	 */
	public Json editVoyage(Pvoyage voyage);
}
