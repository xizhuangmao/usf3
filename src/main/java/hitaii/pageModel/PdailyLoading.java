package hitaii.pageModel;
/**
 * BY MH 2016.1.18
 * DailyLoadingSchedule页面中的除了booknum的一些车辆信息
 */
public class PdailyLoading {
	private String id ;
	//whesdtl 部分
	private String usersId;
	private String users;
	private String vin;
	private String make;
	private String model;
	private String year;
	private String color;
	private String indate;
	private String fuel;
	private String fuelType;
	private String ordersId;
	private String booknumId;
	
	//页面需要的booknum表的部分
	private String booknum;
	private String uoo;
	private String sealnum;   //集装箱铅封号
	private String connum;   //集装箱柜号
	private String loaddate;
	private String consize;  //集装箱型号
	
	
	
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}
	public String getBooknumId() {
		return booknumId;
	}
	public void setBooknumId(String booknumId) {
		this.booknumId = booknumId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
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
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getBooknum() {
		return booknum;
	}
	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}
	public String getUoo() {
		return uoo;
	}
	public void setUoo(String uoo) {
		this.uoo = uoo;
	}
	public String getSealnum() {
		return sealnum;
	}
	public void setSealnum(String sealnum) {
		this.sealnum = sealnum;
	}
	public String getConnum() {
		return connum;
	}
	public void setConnum(String connum) {
		this.connum = connum;
	}
	public String getLoaddate() {
		return loaddate;
	}
	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}
	public String getConsize() {
		return consize;
	}
	public void setConsize(String consize) {
		this.consize = consize;
	}
	
	
	
}
