package hitaii.pageModel;

import hitaii.model.Services;
import hitaii.model.WhesdtlServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @author zw
 * 
 * 时间：20160108
 * 
 * 客户订单DTO
 */
public class Porders {

	private String id;
	private String name;
	private String usersId;
	private String ordersdate;
	private String users;
	private String vin;
	private String booknum;
	private String carrier;
	private String vessel;
	private String voyage;
	private String connum;
	private String consize;	
	private String pol;
	private String pod;
	private String loaddate;
	private String note; //booknum的note
	private String ordersId;
	
	private int page;
	private int rows;
	
	private String order;
	private String sort;
	
	//addOrder界面
	private String billto;
	private String consignee;
	private String notifyparty;
	private String shipper;
	private String priseterm;
	private String whesdtlVinJSONs;
	private String nvoccId;
	private String servicesId;
	private String discount;
	private String pay;
	private String whes;
	private String price;
	
	//placeOrder界面
	private String orderDateFrom;
	private String orderDateTo;
	
	//editOrder界面
	private String make;
	private String model;
	private String year;
	private String color;
	private String whesdtlId;
	private String whesdtlServiceId;
	private String nvoccServiceId;
	private Set<WhesdtlServices> whesdtlServiceses = new HashSet<WhesdtlServices>(
			0);
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getWhes() {
		return whes;
	}
	public void setWhes(String whes) {
		this.whes = whes;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public Set<WhesdtlServices> getWhesdtlServiceses() {
		return whesdtlServiceses;
	}
	public void setWhesdtlServiceses(Set<WhesdtlServices> whesdtlServiceses) {
		this.whesdtlServiceses = whesdtlServiceses;
	}
	public String getServicesId() {
		return servicesId;
	}
	public void setServicesId(String servicesId) {
		this.servicesId = servicesId;
	}
	public String getWhesdtlId() {
		return whesdtlId;
	}
	public String getWhesdtlServiceId() {
		return whesdtlServiceId;
	}
	public void setWhesdtlServiceId(String whesdtlServiceId) {
		this.whesdtlServiceId = whesdtlServiceId;
	}
	public String getNvoccServiceId() {
		return nvoccServiceId;
	}
	public void setNvoccServiceId(String nvoccServiceId) {
		this.nvoccServiceId = nvoccServiceId;
	}
	public void setWhesdtlId(String whesdtlId) {
		this.whesdtlId = whesdtlId;
	}
	public String getNvoccId() {
		return nvoccId;
	}
	public void setNvoccId(String nvoccId) {
		this.nvoccId = nvoccId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getOrderDateFrom() {
		return orderDateFrom;
	}
	public void setOrderDateFrom(String orderDateFrom) {
		this.orderDateFrom = orderDateFrom;
	}
	public String getOrderDateTo() {
		return orderDateTo;
	}
	public void setOrderDateTo(String orderDateTo) {
		this.orderDateTo = orderDateTo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getBillto() {
		return billto;
	}
	public void setBillto(String billto) {
		this.billto = billto;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getNotifyparty() {
		return notifyparty;
	}
	public void setNotifyparty(String notifyparty) {
		this.notifyparty = notifyparty;
	}
	public String getPriseterm() {
		return priseterm;
	}
	public void setPriseterm(String priseterm) {
		this.priseterm = priseterm;
	}
	public String getWhesdtlVinJSONs() {
		return whesdtlVinJSONs;
	}
	public void setWhesdtlVinJSONs(String whesdtlVinJSONs) {
		this.whesdtlVinJSONs = whesdtlVinJSONs;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}		
	public String getOrdersDate() {
		return ordersdate;
	}
	public void setOrdersDate(String ordersdate) {
		this.ordersdate = ordersdate;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getBooknum() {
		return booknum;
	}
	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}
	public String getVoyage() {
		return voyage;
	}
	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}
	public String getConnum() {
		return connum;
	}
	public void setConnum(String connum) {
		this.connum = connum;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getPod() {
		return pod;
	}
	public void setPod(String pod) {
		this.pod = pod;
	}
	public String getLoaddate() {
		return loaddate;
	}
	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	public String getOrdersdate() {
		return ordersdate;
	}
	public void setOrdersdate(String ordersdate) {
		this.ordersdate = ordersdate;
	}
	public String getConsize() {
		return consize;
	}
	public void setConsize(String consize) {
		this.consize = consize;
	}
	
}
