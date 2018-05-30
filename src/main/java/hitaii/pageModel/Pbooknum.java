package hitaii.pageModel;

public class Pbooknum {
		// Fields
		private String id;
		private String booknum;
		private String carrier;
		private String vessel;
		private String voyageId;
		private String voyage;
		private String usersId;
		private String users;
		private String entrydate;
		private String truckId;
		private String truck;
		private String truckusersId;
		private String truckusers;
		private String truckdate;
		private String bcompanyId;
		private String bcompany;
		private String active;
		private String note;
		private String uoo;
		private String consize;
		private String connum;
		private String loaddate;
		private String sealnum;
		private String nvocc;
		private String nvoccId;
		private String companyId;
		private String company;
		//保存一串whesdtl的 vin字符串；
		private String whesdtlVinJSONs;
		//以下下DailyLoading页面
		private String customerId;
		private String customer;
		private String cutoffdateFromDate;
		private String cutoffdateToDate;
		
		private String vin;

		//editBookingNum页面
		private String carrierId;
		private String vesselId;
		
		//bookingManage页面
		private String pod;
		private String booknumId;
		
		private String memoId;
		private String memo;
		
		private String cutoffdate;
		private String type;
		
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getCutoffdate() {
			return cutoffdate;
		}
		public void setCutoffdate(String cutoffdate) {
			this.cutoffdate = cutoffdate;
		}
		public String getCompanyId() {
			return companyId;
		}
		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		public String getMemoId() {
			return memoId;
		}
		public void setMemoId(String memoId) {
			this.memoId = memoId;
		}
		public String getBooknumId() {
			return booknumId;
		}
		public void setBooknumId(String booknumId) {
			this.booknumId = booknumId;
		}
		public String getPod() {
			return pod;
		}
		public void setPod(String pod) {
			this.pod = pod;
		}
		public String getCarrierId() {
			return carrierId;
		}
		public void setCarrierId(String carrierId) {
			this.carrierId = carrierId;
		}
		public String getVesselId() {
			return vesselId;
		}
		public void setVesselId(String vesselId) {
			this.vesselId = vesselId;
		}
		public String getNvocc() {
			return nvocc;
		}
		public void setNvocc(String nvocc) {
			this.nvocc = nvocc;
		}
		public String getVin() {
			return vin;
		}
		public void setVin(String vin) {
			this.vin = vin;
		}
		public String getCutoffdateFromDate() {
			return cutoffdateFromDate;
		}
		public void setCutoffdateFromDate(String cutoffdateFromDate) {
			this.cutoffdateFromDate = cutoffdateFromDate;
		}
		public String getCutoffdateToDate() {
			return cutoffdateToDate;
		}
		public void setCutoffdateToDate(String cutoffdateToDate) {
			this.cutoffdateToDate = cutoffdateToDate;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getCustomer() {
			return customer;
		}
		public void setCustomer(String customer) {
			this.customer = customer;
		}
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
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
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
		public String getVoyageId() {
			return voyageId;
		}
		public void setVoyageId(String voyageId) {
			this.voyageId = voyageId;
		}
		public String getVoyage() {
			return voyage;
		}
		public void setVoyage(String voyage) {
			this.voyage = voyage;
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
		public String getEntrydate() {
			return entrydate;
		}
		public void setEntrydate(String entrydate) {
			this.entrydate = entrydate;
		}
		public String getTruckId() {
			return truckId;
		}
		public void setTruckId(String truckId) {
			this.truckId = truckId;
		}
		public String getTruck() {
			return truck;
		}
		public void setTruck(String truck) {
			this.truck = truck;
		}
		public String getTruckusersId() {
			return truckusersId;
		}
		public void setTruckusersId(String truckusersId) {
			this.truckusersId = truckusersId;
		}
		public String getTruckusers() {
			return truckusers;
		}
		public void setTruckusers(String truckusers) {
			this.truckusers = truckusers;
		}
		public String getTruckdate() {
			return truckdate;
		}
		public void setTruckdate(String truckdate) {
			this.truckdate = truckdate;
		}
		public String getBcompanyId() {
			return bcompanyId;
		}
		public void setBcompanyId(String bcompanyId) {
			this.bcompanyId = bcompanyId;
		}
		public String getBcompany() {
			return bcompany;
		}
		public void setBcompany(String bcompany) {
			this.bcompany = bcompany;
		}
		public String getActive() {
			return active;
		}
		public void setActive(String active) {
			this.active = active;
		}
		public String getNote() {
			return note;
		}
		public void setNote(String note) {
			this.note = note;
		}
		public String getUoo() {
			return uoo;
		}
		public void setUoo(String uoo) {
			this.uoo = uoo;
		}
		public String getConsize() {
			return consize;
		}
		public void setConsize(String consize) {
			this.consize = consize;
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
		public String getSealnum() {
			return sealnum;
		}
		public void setSealnum(String sealnum) {
			this.sealnum = sealnum;
		}
		public String getWhesdtlVinJSONs() {
			return whesdtlVinJSONs;
		}
		public void setWhesdtlVinJSONs(String whesdtlVinJSONs) {
			this.whesdtlVinJSONs = whesdtlVinJSONs;
		}
		public String getNvoccId() {
			return nvoccId;
		}
		public void setNvoccId(String nvoccId) {
			this.nvoccId = nvoccId;
		}
		
	
}
