package hitaii.pageModel;

import hitaii.model.Carrier;
import hitaii.model.Company;
import hitaii.model.Nvocc;
import hitaii.model.Role;
import hitaii.model.Truck;
import hitaii.model.Users;
import hitaii.model.Whes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Puser implements java.io.Serializable {

	private String id;
	private String logname;
	private String password;
	private String fullname;
	private String middlename;
	private String lastname;
	private String types;
	private String dob;
	private String country;
	private String state;
	private String city;
	private String address;
	private String ssn;
	private String email;
	private String web;
	private String homephone;
	private String cellphone;
	private String zip;
	private String fax;
	private String datein;
	private String dateout;
	private String dateupdate;
	private String active;
	private String note;
	private String picture;
	private String scoreTotle;	
	private String scoreTimes;
	private String scoreTotleE;
	private String scoreTimesE;
	private Set<Users> userses = new HashSet<Users>(0);
	private Set<Company> companies = new HashSet<Company>(0);
	private Set<Truck> trucks = new HashSet<Truck>(0);
	private Set<Carrier> carriers = new HashSet<Carrier>(0);
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Whes> wheses = new HashSet<Whes>(0);
	private Set<Nvocc> nvoccs = new HashSet<Nvocc>(0);
	

	
	private String validate;
	private int page;
	private int rows;
	private String order;
	private String sort;
	private String ids;
	private String roleids; //角色id
	private String companyids;//公司id
	private String whesids;//仓库id
	private String nvoccids;//nvocc id
	private String carrierids;//航运公司 id
	private String truckids;//卡车公司 id

	public String getRoleids() {
		return roleids;
	}

	public void setRoleids(String roleids) {
		this.roleids = roleids;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getHomephone() {
		return homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getDatein() {
		return datein;
	}

	public void setDatein(String datein) {
		this.datein = datein;
	}

	public String getDateout() {
		return dateout;
	}

	public void setDateout(String dateout) {
		this.dateout = dateout;
	}

	public String getDateupdate() {
		return dateupdate;
	}

	public void setDateupdate(String dateupdate) {
		this.dateupdate = dateupdate;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getScoreTotle() {
		return scoreTotle;
	}

	public void setScoreTotle(String scoreTotle) {
		this.scoreTotle = scoreTotle;
	}

	public String getScoreTimes() {
		return scoreTimes;
	}

	public void setScoreTimes(String scoreTimes) {
		this.scoreTimes = scoreTimes;
	}

	public String getCompanyids() {
		return companyids;
	}

	public void setCompanyids(String companyids) {
		this.companyids = companyids;
	}

	public String getWhesids() {
		return whesids;
	}

	public void setWhesids(String whesids) {
		this.whesids = whesids;
	}

	public String getNvoccids() {
		return nvoccids;
	}

	public void setNvoccids(String nvoccids) {
		this.nvoccids = nvoccids;
	}

	public String getCarrierids() {
		return carrierids;
	}

	public void setCarrierids(String carrierids) {
		this.carrierids = carrierids;
	}

	public String getTruckids() {
		return truckids;
	}

	public void setTruckids(String truckids) {
		this.truckids = truckids;
	}

	public String getScoreTotleE() {
		return scoreTotleE;
	}

	public void setScoreTotleE(String scoreTotleE) {
		this.scoreTotleE = scoreTotleE;
	}

	public String getScoreTimesE() {
		return scoreTimesE;
	}

	public void setScoreTimesE(String scoreTimesE) {
		this.scoreTimesE = scoreTimesE;
	}

	public Set<Users> getUserses() {
		return userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	public Set<Truck> getTrucks() {
		return trucks;
	}

	public void setTrucks(Set<Truck> trucks) {
		this.trucks = trucks;
	}

	public Set<Carrier> getCarriers() {
		return carriers;
	}

	public void setCarriers(Set<Carrier> carriers) {
		this.carriers = carriers;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public Set<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

	public Set<Whes> getWheses() {
		return wheses;
	}

	public void setWheses(Set<Whes> wheses) {
		this.wheses = wheses;
	}

	public Set<Nvocc> getNvoccs() {
		return nvoccs;
	}

	public void setNvoccs(Set<Nvocc> nvoccs) {
		this.nvoccs = nvoccs;
	}

	
	
}
