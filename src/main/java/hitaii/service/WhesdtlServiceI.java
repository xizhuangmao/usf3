package hitaii.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import hitaii.model.Pic;
import hitaii.model.Users;
import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pmemo;
import hitaii.pageModel.Ppic;
import hitaii.pageModel.Pwhesdtl;

public interface WhesdtlServiceI {

	public DataGrid getAlertWhesdtlDatagrid(Pwhesdtl pwhesdtl, String type) throws IOException;

	public DataGrid findWhesdtlWithBooknum(Pwhesdtl pwhesdtl);
	/**
	 * 加载NewOrders页面  DataGrid数据
	 * @param user 
	 * @param user 
	 */
	public DataGrid findWhesdtlNotUOODataGrid(Pwhesdtl whesdtl, Users user);
	/**
	 * 加载Trucking页面  DataGrid数据
	 * @param user 
	 */
	public DataGrid findValidOrdersDataGrid(Pwhesdtl whesdtl, Users user);

	public DataGrid getAllOrders(Pwhesdtl pwhesdtl);

	public void saveVehicleInf(Pwhesdtl pwhesdtl);
	
	//根据vin号查询对应的车辆图片
	public List<Ppic> findPicsByVin(String vin) throws FileNotFoundException, IOException;

	public Pic findPicPathByName(String picName);
	
	//查看warehouse对应车辆信息
	public DataGrid findWareHouseVehicleInfo(String vin);

	//删除车辆图片 
	public Pwhesdtl removeWareHousePic(Pwhesdtl pwhesdtl);

	//修改warehouse界面展示图片
	public Pwhesdtl changeWareHousePic(String picPath, String picVin);
	
	//查看车辆原始图片
	public Ppic findWareHousePic(String id);

	public Pwhesdtl findwhesdtlByVin(String vin);
	
	//添加图片到Pic表
	public void addPic(String vin, String picName, String picPath, String type);

	public List<Whesdtl> groupPic();

	public List<Pwhesdtl> findPicWhesdtl(int i);

	//查看车辆信息
	public Pwhesdtl findVehicleInfoByVin(String vin);
	
	//查看车辆信息
	public Pwhesdtl findVehicleInfoById(String id);
	
	//修改车辆信息
	public String updateWarehouseInfo(Pwhesdtl pwhesdtl);

	public void updatePath(String string, String path, String vin);
	
	//查询预填写车辆信息
	public List<Whesdtl> findVehicleInfByFlowState();
	
	//根据vin号查询预填写车辆信息
	public DataGrid findVehicleInfByUsers(Pwhesdtl pwhesdtl);

	//车辆入库
	public Pwhesdtl saveOrUpdateVehicleInf(Pwhesdtl pwhesdtl);
	
	//根据firstName查询customer
	public DataGrid findCustomerByFullName(String name, Pwhesdtl pwhesdtl);
	
	//查询所有车辆信息
	public Whesdtl findAllVehicle(Pwhesdtl pwhesdtl);
	
	//车辆转手
	public Json resaleVehicle(Pwhesdtl pwhesdtl);

	public DataGrid getWhesdtDatagrid(Pwhesdtl pwhesdtl);
	
	//修改预录入车辆信息
	public String updatePreAlertInfo(Pwhesdtl pwhesdtl);
	
	//查询仓库预录入车辆信息
	public DataGrid getPreAlertWhesdtl(Pwhesdtl pwhesdtl) throws IOException;
	
	//删除仓库预录入车辆信息
	public void deletePreAlertVehicle(Pwhesdtl pwhesdtl);
	
	//车辆入库
	public String inWarehouseInfo(Pwhesdtl pwhesdtl);
	
	//根据vin号type查询车辆图片
	public List<Ppic> findPicsByVinAndType(String vin, String type);
	
	//车辆memo查询
	public DataGrid findVehicleMemoByVin(Pwhesdtl pwhesdtl);

	//根据id查询车辆信息
	public Pwhesdtl findVehicleInfoById(Pwhesdtl pwhesdtl);

	public void changePicType(String picId, String type);

	public Pmemo findMemoContentById(String memoId);

	public DataGrid findUooAndWhesdtlServices(Pwhesdtl whesdtl, Users user);

	public Long findVehicleInfo();

	public DataGrid getAlertWhesdtlNoOrdersCount(Pwhesdtl pwhesdtl);

	public Long getBooknumVehicleCount();

	public Whesdtl getWhesdtlByVin(Pwhesdtl pwhesdtl);

	public Whesdtl getWhesdtl(Pwhesdtl pwhesdtl);

	public void changePicInfo(Whesdtl whesdtl, Pwhesdtl pwhesdtl);

	public List<Whesdtl> findVehicleByBooknumId(String bookingNumId);

	public List<Pwhesdtl> findWhesdtlServices(Users user);

	public Long getCountPreAlertWhesdtl();

}
