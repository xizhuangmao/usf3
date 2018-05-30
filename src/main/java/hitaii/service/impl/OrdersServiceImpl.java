package hitaii.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import hitaii.dao.CarrierDaoI;
import hitaii.dao.NvoccDaoI;
import hitaii.dao.OrdersDaoI;
import hitaii.dao.PortDaoI;
import hitaii.dao.ServicesDaoI;
import hitaii.dao.UserDaoI;
import hitaii.dao.VesselDaoI;
import hitaii.dao.WhesdtlDaoI;
import hitaii.dao.WhesdtlServicesDaoI;
import hitaii.pageModel.DataGrid;
import hitaii.pageModel.Porders;
import hitaii.pageModel.Pport;
import hitaii.pageModel.Pvessel;
import hitaii.pageModel.Pwhesdtl;
import hitaii.model.Carrier;
import hitaii.model.Nvocc;
import hitaii.model.Orders;
import hitaii.model.Port;
import hitaii.model.Role;
import hitaii.model.Services;
import hitaii.model.SessionInfo;
import hitaii.model.Users;
import hitaii.model.Vessel;
import hitaii.model.Whes;
import hitaii.model.Whesdtl;
import hitaii.model.WhesdtlServices;
import hitaii.service.OrdersServiceI;
import hitaii.util.WhesdtlUtil;

/*
 * @author zw 
 * 
 * 时间：20160108
 * 
 * 示例
 * 客户订单ServiceImpl
 */
@Service("ordersService")
public class OrdersServiceImpl implements OrdersServiceI {
	
	private WhesdtlDaoI whesdtlDao;
	private OrdersDaoI ordersDao;
	private PortDaoI portDao;
	private CarrierDaoI carrierDao;
	private VesselDaoI vesselDao;
	private UserDaoI userDao;
	private NvoccDaoI nvoccDao;
	private ServicesDaoI servicesDao;
	private WhesdtlServicesDaoI whesdtlServicesDao;
	
	public WhesdtlServicesDaoI getWhesdtlServicesDao() {
		return whesdtlServicesDao;
	}
	@Autowired
	public void setWhesdtlServicesDao(WhesdtlServicesDaoI whesdtlServicesDao) {
		this.whesdtlServicesDao = whesdtlServicesDao;
	}
	public ServicesDaoI getServicesDao() {
		return servicesDao;
	}
	@Autowired
	public void setServicesDao(ServicesDaoI servicesDao) {
		this.servicesDao = servicesDao;
	}
	public NvoccDaoI getNvoccDao() {
		return nvoccDao;
	}
	@Autowired
	public void setNvoccDao(NvoccDaoI nvoccDao) {
		this.nvoccDao = nvoccDao;
	}
	public UserDaoI getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(UserDaoI userDao) {
		this.userDao = userDao;
	}
	public CarrierDaoI getCarrierDao() {
		return carrierDao;
	}
	@Autowired
	public void setCarrierDao(CarrierDaoI carrierDao) {
		this.carrierDao = carrierDao;
	}
	public VesselDaoI getVesselDao() {
		return vesselDao;
	}
	@Autowired
	public void setVesselDao(VesselDaoI vesselDao) {
		this.vesselDao = vesselDao;
	}
	public PortDaoI getPortDao() {
		return portDao;
	}
	@Autowired
	public void setPortDao(PortDaoI portDao) {
		this.portDao = portDao;
	}
	public OrdersDaoI getOrdersDao() {
		return ordersDao;
	}
	@Autowired
	public void setOrdersDao(OrdersDaoI ordersDao) {
		this.ordersDao = ordersDao;
	}
	public WhesdtlDaoI getWhesdtlDao() {
		return whesdtlDao;
	}
	@Autowired
	public void setWhesdtlDao(WhesdtlDaoI whesdtlDao) {
		this.whesdtlDao = whesdtlDao;
	}

	@Override
	public DataGrid getOrdersDatagrid(Porders porders) {
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		DataGrid d = new DataGrid();
		String sql = null;
		String count = null;
		//第一次查询获取ordersId集合
		if(sessionInfo.getUsers().getTypes().equals("1")){
			String sqlorder = " from Orders as o where id != '1'";
			count ="select count(*) "+ sqlorder;
			
			if(null != porders.getOrderDateFrom() && !porders.getOrderDateFrom().isEmpty() 
					|| null != porders.getOrderDateTo() && !porders.getOrderDateTo().isEmpty()){
				if(porders.getOrderDateFrom() == null || porders.getOrderDateFrom().isEmpty()){
					porders.setOrderDateFrom("");
				}
				sqlorder = sqlorder + (" and o.ordersdate between '"+porders.getOrderDateFrom()+"' and '"+porders.getOrderDateTo()+"'");
				count = count + (" and o.ordersdate between '"+porders.getOrderDateFrom()+"' and '"+porders.getOrderDateTo()+"'");
			}
			
			if(null != porders.getPol() && !porders.getPol().isEmpty() && !porders.getPol().equals("ALL")){
				sqlorder = sqlorder + (" and o.pol = '"+porders.getPol()+"'");
				count = count + (" and o.pol = '"+porders.getPol()+"'");
			}
			if(null != porders.getPod() && !porders.getPod().isEmpty() && !porders.getPod().equals("ALL")){
				sqlorder = sqlorder + (" and o.pod = '"+porders.getPod()+"'");
				count = count + (" and o.pod = '"+porders.getPod()+"'");
			}
			
			if(null != porders.getSort() && !porders.getSort().isEmpty() && null != porders.getOrder() && !porders.getOrder().isEmpty()){
				sqlorder=sqlorder+(" order by" + " " + porders.getSort() + " " + porders.getOrder());
			}
			
			List<Orders> ordersList = ordersDao.find(sqlorder.toString(), porders.getPage(), porders.getRows());
			//组装ordersId字符串
			String sqlordersID ="";
			if(null!=ordersList){
				sqlordersID+=" and o.id  in ('111'";
				for(Orders orders :ordersList){
					sqlordersID+=",'"+orders.getId()+"'";
				}
				sqlordersID+=") ";
			}
			
			//第二次查询，获取页面需要的车辆数据
			sql = " select o.id as ordersId,o.ordersdate as ordersdate,w.users as users,w.vin as vin,b.booknum as booknum, " +
					" b.carrier as carrier,b.vessel as vessel,b.voyage as voyage,b.consize as consize,o.pol as pol,o.pod as pod,b.loaddate as loaddate,o.note as note" +
					" from Orders o left join Whesdtl w on w.ordersId = o.id left join Booknum b on w.booknumId=b.id where w.ordersId != '1' " +sqlordersID+"";
			
			sql = sql + " order by o.ordersdate desc, o.id desc";
			d.setTotal(ordersDao.count(count));
			
		}else if(sessionInfo.getUsers().getTypes().equals("2")){
			String hql = " select o.id as ordersId,o.ordersdate as ordersdate,w.users as users,w.vin as vin,b.booknum as booknum, " +
					" b.carrier as carrier,b.vessel as vessel,b.voyage as voyage,b.consize as consize,o.pol as pol,o.pod as pod,b.loaddate as loaddate,o.note as note" +
					" from Orders o left join Whesdtl w on w.ordersId = o.id left join Booknum b on w.booknumId=b.id where w.ordersId != '1' and w.users = '"+sessionInfo.getUsers().getFullname()+"' ";
			count = "select count(DISTINCT o.id) from Orders o left join Whesdtl w on w.ordersId = o.id left join Booknum b on w.booknumId=b.id where w.ordersId != '1' and w.users = '"+sessionInfo.getUsers().getFullname()+"'";
			if(null != porders.getOrderDateFrom() && !porders.getOrderDateFrom().isEmpty() 
					|| null != porders.getOrderDateTo() && !porders.getOrderDateTo().isEmpty()){
				if(porders.getOrderDateFrom() == null || porders.getOrderDateFrom().isEmpty()){
					porders.setOrderDateFrom("");
				}
				hql = hql + (" and o.ordersdate between '"+porders.getOrderDateFrom()+"' and '"+porders.getOrderDateTo()+"'");
				count = count + (" and o.ordersdate between '"+porders.getOrderDateFrom()+"' and '"+porders.getOrderDateTo()+"'");
			}
			
			if(null != porders.getPol() && !porders.getPol().isEmpty() && !porders.getPol().equals("ALL")){
				hql = hql + (" and o.pol = '"+porders.getPol()+"'");
				count = count + (" and o.pol = '"+porders.getPol()+"'");
			}
			if(null != porders.getPod() && !porders.getPod().isEmpty() && !porders.getPod().equals("ALL")){
				hql = hql + (" and o.pod = '"+porders.getPod()+"'");
				count = count + (" and o.pod = '"+porders.getPod()+"'");
			}
			
			if(null != porders.getSort() && !porders.getSort().isEmpty() && null != porders.getOrder() && !porders.getOrder().isEmpty()){
				sql=sql+(" order by" + " " + porders.getSort() + " " + porders.getOrder());
			}
			
			hql = hql + "group by o.id";
			List<Porders> porders2 =new ArrayList<Porders>();
			porders2 = whesdtlDao.findwithOrders(hql, porders.getPage(), porders.getRows());

			String sqlordersID ="";
			if(null!=porders2){
				sqlordersID+=" and o.id  in ('00000'";
				for(Porders porder :porders2){
					sqlordersID+=",'"+porder.getOrdersId()+"'";
				}
				sqlordersID+=") ";
			}
			sql = " select o.id as ordersId,o.ordersdate as ordersdate,w.users as users,w.vin as vin,b.booknum as booknum, " +
					" b.carrier as carrier,b.vessel as vessel,b.voyage as voyage,b.consize as consize,o.pol as pol,o.pod as pod,b.loaddate as loaddate,o.note as note" +
					" from Orders o left join Whesdtl w on w.ordersId = o.id left join Booknum b on w.booknumId=b.id where w.ordersId != '1' and w.users = '"+sessionInfo.getUsers().getFullname()+"' " + sqlordersID;
			
			sql = sql + " order by o.ordersdate desc, o.id desc";
			d.setTotal(whesdtlDao.findCount(count));
		}
		
		//查询结果
		List<Porders> oList =new ArrayList<Porders>();
		oList = whesdtlDao.findwithOrders(sql, 0, 0);

		d.setRows(oList);
		
		return d;
	}
	
	@Override
	public DataGrid findNoOrdersDatagrid(Porders orders) {
		String sql = " and (ordersId is null or ordersId = '' or ordersId = '1')";
		DataGrid d = findOrders(orders, sql);
		return d;
	}
	
	@Override
	public DataGrid findOrders(Porders orders, String sql) {
		DataGrid d = new DataGrid();
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		String userHql = "from Users where logname = '"+sessionInfo.getUsers().getLogname()+"'";
		Users user = userDao.get(userHql);
		String hql = "from Whesdtl where 1=1";
		if(null != orders.getVin() && !orders.getVin().isEmpty()){
			hql = hql+(" and vin like '%"+orders.getVin()+"%'");
		}
		if(null != user && !user.getTypes().equals("1")){
			hql = hql+(" and users = '"+user.getFullname()+"' and flowstate = '2'"); 
		}
		
		hql += sql;
		String hqlCount = "select count(*) " + hql;
		if(null != orders.getSort() && !orders.getSort().isEmpty() && null != orders.getOrder() && !orders.getOrder().isEmpty()){
			hql = hql+(" order by" + " " + orders.getSort() + " " + orders.getOrder());
		}
		List<Whesdtl> whesdtls = whesdtlDao.findNotOrders(hql,orders.getPage(),orders.getRows());
		
		List<Pwhesdtl> pwhesdtls = new ArrayList<Pwhesdtl>();
		if (null != whesdtls && whesdtls.size() > 0) {
			for (Whesdtl w : whesdtls) {
				Pwhesdtl pwhesdtl = new Pwhesdtl();
				BeanUtils.copyProperties(w, pwhesdtl);
				pwhesdtls.add(pwhesdtl);
			}
		}
		d.setRows(pwhesdtls);
		d.setTotal(whesdtlDao.findwithCount(hqlCount));
		return d;
	}
	
	@Override
	public String saveOrders(Porders orders) {
		orders.setId(UUID.randomUUID().toString());
		orders.setOrdersDate(WhesdtlUtil.getNowTime());
		
		//获取下单人
		SessionInfo sessionInfo = new SessionInfo();
		sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		if(null != sessionInfo.toString() && !sessionInfo.toString().isEmpty()){
			String userHql = "from Users where fullname = '"+sessionInfo.getUsers().getFullname()+"'";
			Users user = userDao.get(userHql);
			String hql = "from Whesdtl where vin in "+orders.getWhesdtlVinJSONs()+"";
			List<Whesdtl> whesdtls = whesdtlDao.findVehicleInfo(hql);
			for(int i=0;i<whesdtls.size();i++){
				whesdtls.get(i).setOrdersId(orders.getId());
			}
			if(orders.getPol().equals("Please Select")){
				orders.setPol(null);
			}
			if(orders.getPod().equals("Please Select")){
				orders.setPod(null);
			}
			if(null != user){
				orders.setUsersId(user.getId());
			}
			Orders order = new Orders();
			BeanUtils.copyProperties(orders, order);
			ordersDao.save(order);
			return "success";
		}else{
			return "Session expired, please login again.";
		}
	}
	
	@Override
	public DataGrid findEditOrderVehicles(Porders orders) {
		DataGrid d = new DataGrid();
		
		String sql = " select w.id as id,w.ordersId as ordersId,w.vin as vin,w.users as users,w.make as make,w.model as model,w.year as year,w.color as color," +
				" o.billto as billto,o.shipper as shipper,o.pol as pol,o.pod as pod,o.consignee as consignee,o.notifyparty as notifyparty,o.priseterm as priseterm, " +
				" o.note as note,o.nvoccId as nvoccId from Orders o left join Whesdtl w on w.ordersId = o.id where o.id = '"+orders.getOrdersId()+"'";
		List<Porders> whesdtlList =new ArrayList<Porders>();
		whesdtlList = whesdtlDao.findwithOrders(sql, 0, 0);
		for(int i=0;i<whesdtlList.size();i++){
			String hql = "from Whesdtl where id = '"+whesdtlList.get(i).getId()+"'";
			Whesdtl whesdtls = whesdtlDao.getWhesdtl(hql);
			whesdtlList.get(i).setWhesdtlServiceses(whesdtls.getWhesdtlServiceses());
		}
		d.setRows(whesdtlList);
		return d;
	}
	
	@Override
	public String updateOrders(Porders orders) {
		Orders order = ordersDao.get("from Orders where id=:ordersId", orders);
		hitaii.util.BeanUtils.copyProperties(orders, order);
		ordersDao.update(order);
		List<Whesdtl> whesdtlsOrder = whesdtlDao.findVehicleInfo("from Whesdtl where ordersId = '"+orders.getOrdersId()+"'");
		
		String hql = "from Whesdtl where vin in "+orders.getWhesdtlVinJSONs()+"";
		List<Whesdtl> whesdtls = whesdtlDao.findVehicleInfo(hql);
		
		if(whesdtlsOrder.containsAll(whesdtls)){
			 whesdtlsOrder.removeAll(whesdtls);
			 for(Whesdtl whesdtl : whesdtlsOrder){
				 whesdtl.setOrdersId("");
			 }
		}
		for(int i=0;i<whesdtls.size();i++){
			whesdtls.get(i).setOrdersId(orders.getOrdersId());
		}
		return "success";
	}
	
	@Override
	public List<Whesdtl> findServicesbyVin(String ordersId) {
		String hql = "from Whesdtl where ordersId = '"+ordersId+"'";
		List<Whesdtl> whesdtls = whesdtlDao.findVehicleInfo(hql);
		return whesdtls;
	}
	
	@Override
	public void deleteOrders(Porders orders) {
		Orders order = ordersDao.get("from Orders where id=:id", orders);
		if(null != order){
			ordersDao.delete(order);
		}
		List<Whesdtl> whesdtls = whesdtlDao.findByOrdersId("from Whesdtl where ordersId=:id", orders);
		if(whesdtls.size()>0 && whesdtls != null){
			for(int i=0;i<whesdtls.size();i++){
				whesdtls.get(i).setOrdersId("");
				whesdtls.get(i).setNvoccId("");
				whesdtls.get(i).setNvocc("");
				/*Iterator<Services> itServices = whesdtls.get(i).getServiceses().iterator();
				while(itServices.hasNext()){
					Services services = itServices.next();
					if(null != services){
						itServices.remove();
					}
				}*/
			}
		}
	}
	
	@Override
	public String deleteOrdersVehicle(String vehicleId) {
		String hql = "from Whesdtl where id = '"+vehicleId+"'";
		Whesdtl whesdtl = whesdtlDao.getPic(hql);
		whesdtl.setOrdersId("");
		whesdtl.setNvoccId("");
		whesdtl.setNvocc("");
		/*Iterator<Services> itServices = whesdtl.getServiceses().iterator();
		while(itServices.hasNext()){
			Services services = itServices.next();
			if(null != services){
				itServices.remove();
			}
		}*/
		return "success";
	}
	@Override
	public void addVehicleServices(Porders orders) {
		String hql = "from WhesdtlServices where whesdtlId = '"+orders.getWhesdtlId()+"' and servicesId = '"+orders.getServicesId()+"'";
		WhesdtlServices whesdtlServicesRepeat = whesdtlServicesDao.get(hql);
		if(null != whesdtlServicesRepeat){
			whesdtlServicesDao.delete(whesdtlServicesRepeat);
		}else{
			String whesdtlHql = "from Whesdtl where id = '"+orders.getWhesdtlId()+"'";
			Whesdtl whesdtl = whesdtlDao.getWhesdtl(whesdtlHql);
			String servicesHql = "from Services where id = '"+orders.getServicesId()+"'";
			Services services = servicesDao.getService(servicesHql);
			
			WhesdtlServices whesdtlServices = new WhesdtlServices();
			whesdtlServices.setWhesdtl(whesdtl);
			whesdtlServices.setServices(services);
			whesdtlServices.setDiscount(orders.getDiscount());
			whesdtlServices.setPay(orders.getPay());
			whesdtlServices.setPaystate("1");
			whesdtlServices.setPrice(orders.getPrice());
			whesdtlServices.setId(UUID.randomUUID().toString());
			whesdtlServicesDao.save(whesdtlServices);
		}
	}

}
