package hitaii.dao;

import java.util.List;

import hitaii.model.Booknum;
import hitaii.model.Whesdtl;
import hitaii.pageModel.Pbooknum;
import hitaii.pageModel.PcurrentStockReport;
import hitaii.pageModel.PdailyLoading;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Pmodel;
import hitaii.pageModel.Porders;
import hitaii.pageModel.Pvessel;
import hitaii.pageModel.Pvoyage;
import hitaii.pageModel.Pwhesdtl;

public interface WhesdtlDaoI extends BaseDaoI<Whesdtl>{
	
	public List<Pwhesdtl> findwithbooknum(String hql, int page, int rows) ;

	//查询客户下了订单，但是没有被UOO的数据
	public List<Pwhesdtl> findWhesdtlNotUOO(String hql, int page, int rows);

	public Long findwithCount(String hql);
	
	//查询Valid Orders页面
	public List<Pwhesdtl> findwithBooknumVoyageOrders(String hql, int i, int j);

	public void savewhesdtl(Whesdtl whesdtl);
	
	public void saveOrUpdateWhesdtl(Whesdtl whesdtl);
	
	//查询客户订单数据
	public List<Porders> findwithOrders(String sql, int page, int rows);

	public List<Pmemo> findMemowithbooknum(String hql, int page, int rows);

	public Long findMemowithCount(String hqlCount);
	
	//Vessel页面显示数据
	public List<Pvessel> findVesselToCarrier(String sql, int page, int rows);
	
	//查询车辆信息
	List<Whesdtl> findVehicleInfo(String hql);
	
	//出车时  给车辆更新booknumId对应的信息
	public void updateSomeWhesdtlBooknumId(String sql);
	//返回DailyLoadingSchedule页面表格数据
	public List<PdailyLoading> getPdailyLoadingDate(String sql);

	public List<Pwhesdtl> findWareHousewithbooknum(String string);

	public Whesdtl getPic(String hql);

	public void updatePic(Whesdtl whesdtl);

	public List<Pwhesdtl> findwithPic(String hql);
	/**
	 * model 页面的数据展示  model 和 make 连表查询
	 */
	public List<Pmodel> findMakeAndModel(String sql, int page, int rows);
	/**
	 * Report下 CurrentStockReport 查看库存的表单数据
	 */
	public List<PcurrentStockReport> getCurrentStockReport(String sql);

	public List<Pwhesdtl> findWareHousewithMake(String hql);

	public void updateWarehouseInfo(Whesdtl whesdtl);
	
	//车辆入库
	public void saveOrUpdatewhesdtl(Whesdtl whesdtl);

	public Booknum findEditBookingNum(String hql);

	public List<Whesdtl> findNotOrders(String hql, int page, int rows);

	public Long findCount(String count);

	public List<Pbooknum> findWithWhesdtl(String hql, int i, int j);

	public List<Whesdtl> findW(String whesdtlHql, Pbooknum pbooknum);

	void deleteWhesdtl(Whesdtl whesdtl);

	public List<Whesdtl> findPreAlert(String hql, int page, int rows);

	public List<Whesdtl> findByOrdersId(String hql, Object o);

	public Whesdtl getVehicleInfo(String hql, Object o);

	public List<Pwhesdtl> findWithOrders(String hql);
	
	public Whesdtl getWhesdtl(String hql);

	public List<Pwhesdtl> findUooAndWhesdtlServices(String hql, int page, int rows);

}
