package hitaii.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import hitaii.model.Whesdtl;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Json;
import hitaii.pageModel.Pnvocc;
import hitaii.pageModel.Porders;
import hitaii.pageModel.Pport;
import hitaii.pageModel.Pservices;
import hitaii.pageModel.Pwhesdtl;
import hitaii.service.NvoccServiceI;
import hitaii.service.OrdersServiceI;
import hitaii.service.ServicesServiceI;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * Copy Right Information : hitaii
 * 
 * Function :客户订单
 * 
 * Author : zw
 * 
 * Date : 20160111
 * 
 * Modification history :
 * 
 */
@Namespace("/")
@Action(value = "ordersAction")
public class OrdersAction extends BaseAction implements ModelDriven<Porders>{

	private Porders orders = new Porders();
	
	private OrdersServiceI ordersService;
	private NvoccServiceI nvoccService;
	private ServicesServiceI servicesService;
	
	public ServicesServiceI getServicesService() {
		return servicesService;
	}
	@Autowired
	public void setServicesService(ServicesServiceI servicesService) {
		this.servicesService = servicesService;
	}
	public NvoccServiceI getNvoccService() {
		return nvoccService;
	}
	@Autowired
	public void setNvoccService(NvoccServiceI nvoccService) {
		this.nvoccService = nvoccService;
	}

	@Override
	public Porders getModel() {
		// TODO Auto-generated method stub
		return orders;
	}
	
	public OrdersServiceI getOrdersService() {
		return ordersService;
	}
	@Autowired
	public void setOrdersService(OrdersServiceI ordersService) {
		this.ordersService = ordersService;
	}

	//获取订单表格datagrid
	public void getOrdersDatagrid() throws IOException{
		DataGrid d = ordersService.getOrdersDatagrid(orders);
		super.writeJson(d);
	}
	
	//查询未下订单的车辆
	public void findNoOrdersDatagrid() throws IOException{
		DataGrid noOrders = ordersService.findNoOrdersDatagrid(orders);
		super.writeJson(noOrders);
	}
	
	//增加订单
	public void saveOrders(){
		String msg = ordersService.saveOrders(orders);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg(msg);
		super.writeJson(j);
	}
	
	//添加车辆服务
	public void addVehicleServices(){
		if(null != orders.getWhesdtlId() && !orders.getWhesdtlId().isEmpty() 
				&& null != orders.getServicesId() && !orders.getServicesId().isEmpty()){
			ordersService.addVehicleServices(orders);
		}
	}
	
	//根据订单id查询订单
	public void findEditOrderVehicles(){
		DataGrid d = ordersService.findEditOrderVehicles(orders);
		super.writeJson(d);
	}
	
	//更新订单
	public void updateOrders(){
		String msg = ordersService.updateOrders(orders);
		Json j = new Json();
		j.setMsg(msg);
		super.writeJson(j);
	}
	
	//查找所有Nvocc
	public void findAllNvocc(){
		List<Map<String, String>> nPnvoccs = nvoccService.findAllNvocc();
		super.writeJson(nPnvoccs);
	}
	
	//根据Nvocc查询Services
	public void findServicesByNvocc(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String nvoccId = request.getParameter("nvoccId");
		List<Pservices> pservices = servicesService.findServicesByNvocc(nvoccId);
		super.writeJson(pservices);
	}
	
	//查找每辆车对应的服务
	public void findServicesbyVin(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String ordersId = request.getParameter("ordersId");
		List<Whesdtl> whesdtls = ordersService.findServicesbyVin(ordersId);
		super.writeJson(whesdtls);
	}
	
	//删除订单
	public void deleteOrders(){
		ordersService.deleteOrders(orders);
	}
	
	//删除订单车辆
	public void deleteOrdersVehicle(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vehicleId = request.getParameter("id");
		String msg = ordersService.deleteOrdersVehicle(vehicleId);
		Json j = new Json();
		j.setMsg(msg);
		super.writeJson(j);
	}
}
