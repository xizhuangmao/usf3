package hitaii.pageModel;

import hitaii.model.WhesdtlServices;

import java.util.HashSet;
import java.util.Set;

public class Pwhesdtl {
	
	private String id;            //表whesdtl
	private String usersId;
	private String users;
	private String make;
	private String model;
	private String vin;
	private String engine;
	private String year;
	private String color;
	private String keynum;
	private String sticker;
	private String note;
	private String fuel;
	private String fuelType;
	private String whesId;
	private String whes;
	private String nvoccId;
	private String nvocc;
	private String indate;
	private String vehicletype;
	private String freedate;
	private String usersOperId;
	private String usersOper;
	private String titlestate;
	private String title1;
	private String title2;
	private String title3;
	private String title4;
	private String billstate;
	private String bill1;
	private String bill2;
	private String bill3;
	private String bill4;
	private String proofstate;
	private String proof1;
	private String proof2;
	private String proof3;
	private String proof4;
	private String pic;
	private String mdate;
	private String floormat;
	private String dvdremote;
	private String heatset;
	private String usermanual;
	private String cod;
	private String ordersId;
	private String booknumId;
	private String flowstate;
	private Set<WhesdtlServices> whesdtlServiceses = new HashSet<WhesdtlServices>(
			0);
	/**
	 * Orders Add页面显示的 属性
	 * by mh
	 */
	private String customer;	  //表whesdtl  中的users
	private String pol;			  //表orders
	private String pod;
	private String ordersdate;
	private String remark;       //页面中Memo  数据库中booknum中的note
	/**
	 * Orders Add保存时  添加了 属性
	 * by mh
	 */
	private String uoo;
	private String consize;
	private String carrier;
	private String vessel;
	private String voyage;
	private String booknum;

	/**
	 * Valid Orders页面查询条件时  有添加了 四个属性
	 * by mh
	 */
	private String truckingFromDate;
	private String truckingToDate;
	private String cutoffFromDate;
	private String cutoffToDate;
	/**
	 * Valid Orders 页面需要 添加新的参数
	 * by mh
	 */
	private String cutoffdate;
	private String terminal;
	private String connum;
	private String sealnum;
	private String truckdate;
	
	/**
	 * trucking 页面需要 添加新的参数
	 * by mh
	 */
	private String truckId;
	private String truck;
	private String loaddate;
	private String remake;
	/**
	 * vehicle receive 页面需要 传递的参数
	 * by qc
	 */
	private String receiveFrom;
	private String receiveTO;
	private String loadingFrom;
	private String loadingTo;
	private String cusCond;
	private String contNo;
	private String freeFrom;
	private String freeTo;
	private String carrierCond;
	private String memo;
	private String picPath;
	private String entrydate;
	private String orderstate;
	private String oldUsers;
	
	private String makeId;

	/**
	 * search for vehicle
	 * by qc
	 */
	private String inWareFrom;
	private String inWareTO;

	//pic
	private String name;
	private String path;
	
	private String memoId;
	
	public String getTruckId() {
		return truckId;
	}
	public void setTruckId(String truckId) {
		this.truckId = truckId;
	}
	public String getNvoccId() {
		return nvoccId;
	}
	public void setNvoccId(String nvoccId) {
		this.nvoccId = nvoccId;
	}
	public String getNvocc() {
		return nvocc;
	}
	public void setNvocc(String nvocc) {
		this.nvocc = nvocc;
	}
	public Set<WhesdtlServices> getWhesdtlServiceses() {
		return whesdtlServiceses;
	}
	public void setWhesdtlServiceses(Set<WhesdtlServices> whesdtlServiceses) {
		this.whesdtlServiceses = whesdtlServiceses;
	}
	public String getMemoId() {
		return memoId;
	}
	public void setMemoId(String memoId) {
		this.memoId = memoId;
	}
	public String getOldUsers() {
		return oldUsers;
	}
	public void setOldUsers(String oldUsers) {
		this.oldUsers = oldUsers;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getInWareFrom() {
		return inWareFrom;
	}
	public void setInWareFrom(String inWareFrom) {
		this.inWareFrom = inWareFrom;
	}
	public String getInWareTO() {
		return inWareTO;
	}
	public void setInWareTO(String inWareTO) {
		this.inWareTO = inWareTO;
	}
	public String getFlowstate() {
		return flowstate;
	}
	public void setFlowstate(String flowstate) {
		this.flowstate = flowstate;
	}
	public String getMakeId() {
		return makeId;
	}
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	public String getReceiveFrom() {
		return receiveFrom;
	}
	public void setReceiveFrom(String receiveFrom) {
		this.receiveFrom = receiveFrom;
	}
	public String getReceiveTO() {
		return receiveTO;
	}
	public void setReceiveTO(String receiveTO) {
		this.receiveTO = receiveTO;
	}
	public String getLoadingFrom() {
		return loadingFrom;
	}
	public void setLoadingFrom(String loadingFrom) {
		this.loadingFrom = loadingFrom;
	}
	public String getLoadingTo() {
		return loadingTo;
	}
	public void setLoadingTo(String loadingTo) {
		this.loadingTo = loadingTo;
	}
	public String getCusCond() {
		return cusCond;
	}
	public void setCusCond(String cusCond) {
		this.cusCond = cusCond;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getFreeFrom() {
		return freeFrom;
	}
	public void setFreeFrom(String freeFrom) {
		this.freeFrom = freeFrom;
	}
	public String getFreeTo() {
		return freeTo;
	}
	public void setFreeTo(String freeTo) {
		this.freeTo = freeTo;
	}
	public String getCarrierCond() {
		return carrierCond;
	}
	public void setCarrierCond(String carrierCond) {
		this.carrierCond = carrierCond;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	public String getEngine() {
		return engine;
	}
	public void setEngine(String engine) {
		this.engine = engine;
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
	public String getKeynum() {
		return keynum;
	}
	public void setKeynum(String keynum) {
		this.keynum = keynum;
	}
	public String getSticker() {
		return sticker;
	}
	public void setSticker(String sticker) {
		this.sticker = sticker;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getWhesId() {
		return whesId;
	}
	public void setWhesId(String whesId) {
		this.whesId = whesId;
	}
	public String getWhes() {
		return whes;
	}
	public void setWhes(String whes) {
		this.whes = whes;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	public String getFreedate() {
		return freedate;
	}
	public void setFreedate(String freedate) {
		this.freedate = freedate;
	}
	public String getUsersOperId() {
		return usersOperId;
	}
	public void setUsersOperId(String usersOperId) {
		this.usersOperId = usersOperId;
	}
	public String getUsersOper() {
		return usersOper;
	}
	public void setUsersOper(String usersOper) {
		this.usersOper = usersOper;
	}
	public String getTitlestate() {
		return titlestate;
	}
	public void setTitlestate(String titlestate) {
		this.titlestate = titlestate;
	}
	public String getTitle1() {
		return title1;
	}
	public void setTitle1(String title1) {
		this.title1 = title1;
	}
	public String getTitle2() {
		return title2;
	}
	public void setTitle2(String title2) {
		this.title2 = title2;
	}
	public String getTitle3() {
		return title3;
	}
	public void setTitle3(String title3) {
		this.title3 = title3;
	}
	public String getTitle4() {
		return title4;
	}
	public void setTitle4(String title4) {
		this.title4 = title4;
	}
	public String getBillstate() {
		return billstate;
	}
	public void setBillstate(String billstate) {
		this.billstate = billstate;
	}
	public String getBill1() {
		return bill1;
	}
	public void setBill1(String bill1) {
		this.bill1 = bill1;
	}
	public String getBill2() {
		return bill2;
	}
	public void setBill2(String bill2) {
		this.bill2 = bill2;
	}
	public String getBill3() {
		return bill3;
	}
	public void setBill3(String bill3) {
		this.bill3 = bill3;
	}
	public String getBill4() {
		return bill4;
	}
	public void setBill4(String bill4) {
		this.bill4 = bill4;
	}
	public String getProofstate() {
		return proofstate;
	}
	public void setProofstate(String proofstate) {
		this.proofstate = proofstate;
	}
	public String getProof1() {
		return proof1;
	}
	public void setProof1(String proof1) {
		this.proof1 = proof1;
	}
	public String getProof2() {
		return proof2;
	}
	public void setProof2(String proof2) {
		this.proof2 = proof2;
	}
	public String getProof3() {
		return proof3;
	}
	public void setProof3(String proof3) {
		this.proof3 = proof3;
	}
	public String getProof4() {
		return proof4;
	}
	public void setProof4(String proof4) {
		this.proof4 = proof4;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	public String getFloormat() {
		return floormat;
	}
	public void setFloormat(String floormat) {
		this.floormat = floormat;
	}
	public String getDvdremote() {
		return dvdremote;
	}
	public void setDvdremote(String dvdremote) {
		this.dvdremote = dvdremote;
	}
	public String getHeatset() {
		return heatset;
	}
	public void setHeatset(String heatset) {
		this.heatset = heatset;
	}
	public String getUsermanual() {
		return usermanual;
	}
	public void setUsermanual(String usermanual) {
		this.usermanual = usermanual;
	}
	public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getUsers() {
		return users;
	}
	public void setUsers(String users) {
		this.users = users;
	}
	public String getCutoffdate() {
		return cutoffdate;
	}
	public void setCutoffdate(String cutoffdate) {
		this.cutoffdate = cutoffdate;
	}
	public String getConsize() {
		return consize;
	}
	public void setConsize(String consize) {
		this.consize = consize;
	}
	public String getLoaddate() {
		return loaddate;
	}
	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}
	public String getSealnum() {
		return sealnum;
	}
	public void setSealnum(String sealnum) {
		this.sealnum = sealnum;
	}
	public String getTruckdate() {
		return truckdate;
	}
	public void setTruckdate(String truckdate) {
		this.truckdate = truckdate;
	}
	public String getUoo() {
		return uoo;
	}
	public void setUoo(String uoo) {
		this.uoo = uoo;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getBooknum() {
		return booknum;
	}
	public void setBooknum(String booknum) {
		this.booknum = booknum;
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
	public String getTruck() {
		return truck;
	}
	public void setTruck(String truck) {
		this.truck = truck;
	}
	public String getTruckingFromDate() {
		return truckingFromDate;
	}
	public void setTruckingFromDate(String truckingFromDate) {
		this.truckingFromDate = truckingFromDate;
	}
	public String getTruckingToDate() {
		return truckingToDate;
	}
	public void setTruckingToDate(String truckingToDate) {
		this.truckingToDate = truckingToDate;
	}
	public String getCutoffFromDate() {
		return cutoffFromDate;
	}
	public void setCutoffFromDate(String cutoffFromDate) {
		this.cutoffFromDate = cutoffFromDate;
	}
	public String getCutoffToDate() {
		return cutoffToDate;
	}
	public void setCutoffToDate(String cutoffToDate) {
		this.cutoffToDate = cutoffToDate;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
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
	public String getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
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
	public String getOrdersdate() {
		return ordersdate;
	}
	public void setOrdersdate(String ordersdate) {
		this.ordersdate = ordersdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getConnum() {
		return connum;
	}
	public void setConnum(String connum) {
		this.connum = connum;
	}


	public String getBooknumId() {
		return booknumId;
	}
	public void setBooknumId(String booknumId) {
		this.booknumId = booknumId;
	}


	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}


	//排序的数值
	private int page;
	private int rows;
	private String order;
	private String sort;
	
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
	
	
}
