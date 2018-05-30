package hitaii.service;

import java.util.List;
import java.util.Map;

import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Porders;
import hitaii.pageModel.Pport;
import hitaii.pageModel.Pwhesdtl;

/*
 * @author zw 
 * 
 * 时间：20160108
 * 
 * 示例
 * 客户订单ServiceI
 */
public interface OrdersServiceI {

	//获取所有的订单
	public DataGrid getOrdersDatagrid(Porders orders);
	
	//查询未下订单的车辆
	public DataGrid findOrders(Porders orders, String sql);

	//增加订单
	public String saveOrders(Porders orders);
	
	//根据订单id查询订单
	//public DataGrid editOrders(Porders orders);

	//更新订单
	public String updateOrders(Porders orders);

	//查找每辆车对应的服务
	public List<Whesdtl> findServicesbyVin(String ordersId);
	
	//删除订单
	public void deleteOrders(Porders orders);
	
	//删除订单车辆
	public String deleteOrdersVehicle(String vehicleId);

	//添加车辆服务
	public void addVehicleServices(Porders orders);
	
	//查询下订单的车辆
	public DataGrid findEditOrderVehicles(Porders orders);

	public DataGrid findNoOrdersDatagrid(Porders orders);
}
