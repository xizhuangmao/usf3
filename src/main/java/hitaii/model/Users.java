package hitaii.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "users", catalog = "usf3")
public class Users implements java.io.Serializable {

	// Fields

	private String id;
	private Users users;
	private String logname;
	private String password;
	private String fullname;
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
	private Set<Truck> trucks = new HashSet<Truck>(0);
	private Set<Carrier> carriers = new HashSet<Carrier>(0);
	private Set<Role> roles = new HashSet<Role>(0);
	private Set<Whes> wheses = new HashSet<Whes>(0);
	private Set<Nvocc> nvoccs = new HashSet<Nvocc>(0);
	private Set<Company> companies = new HashSet<Company>(0);

	// Constructors

	/** default constructor */
	public Users() {
	}

	/** minimal constructor */
	public Users(String id, String logname, String password) {
		this.id = id;
		this.logname = logname;
		this.password = password;
	}

	/** full constructor */
	public Users(String id, Users users, String logname, String password, String fullname, String types, String dob, String country, String state, String city, String address, String ssn, String email, String web, String homephone, String cellphone, String zip, String fax, String datein, String dateout, String dateupdate, String active, String note, String picture, String scoreTotle, String scoreTimes, String scoreTotleE, String scoreTimesE,Set<Users> userses, Set<Company> companies,Set<Truck> trucks, Set<Carrier> carriers, Set<Role> roles, Set<Whes> wheses, Set<Nvocc> nvoccs) {
		this.id = id;
		this.users = users;
		this.logname = logname;
		this.password = password;
		this.fullname = fullname;
		this.types = types;
		this.dob = dob;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.ssn = ssn;
		this.email = email;
		this.web = web;
		this.homephone = homephone;
		this.cellphone = cellphone;
		this.zip = zip;
		this.fax = fax;
		this.datein = datein;
		this.dateout = dateout;
		this.dateupdate = dateupdate;
		this.active = active;
		this.note = note;
		this.picture = picture;
		this.scoreTotle = scoreTotle;
		this.scoreTimes = scoreTimes;
		this.scoreTotleE = scoreTotleE;
		this.scoreTimesE = scoreTimesE;
		this.userses = userses;
		this.trucks = trucks;
		this.carriers = carriers;
		this.roles = roles;
		this.wheses = wheses;
		this.nvoccs = nvoccs;
		this.companies = companies;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "logname", nullable = false, length = 50)
	public String getLogname() {
		return this.logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	@Column(name = "password", nullable = false, length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "fullname", length = 200)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "types", length = 10)
	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Column(name = "dob", length = 50)
	public String getDob() {
		return this.dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Column(name = "country", length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "state", length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city", length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "ssn", length = 100)
	public String getSsn() {
		return this.ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "web", length = 200)
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Column(name = "homephone", length = 50)
	public String getHomephone() {
		return this.homephone;
	}

	public void setHomephone(String homephone) {
		this.homephone = homephone;
	}

	@Column(name = "cellphone", length = 50)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "zip", length = 50)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "fax", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "datein", length = 50)
	public String getDatein() {
		return this.datein;
	}

	public void setDatein(String datein) {
		this.datein = datein;
	}

	@Column(name = "dateout", length = 50)
	public String getDateout() {
		return this.dateout;
	}

	public void setDateout(String dateout) {
		this.dateout = dateout;
	}

	@Column(name = "dateupdate", length = 50)
	public String getDateupdate() {
		return this.dateupdate;
	}

	public void setDateupdate(String dateupdate) {
		this.dateupdate = dateupdate;
	}

	@Column(name = "active", length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "picture", length = 200)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "scoreTotle", length = 20)
	public String getScoreTotle() {
		return this.scoreTotle;
	}

	public void setScoreTotle(String scoreTotle) {
		this.scoreTotle = scoreTotle;
	}

	@Column(name = "scoreTimes", length = 10)
	public String getScoreTimes() {
		return this.scoreTimes;
	}

	public void setScoreTimes(String scoreTimes) {
		this.scoreTimes = scoreTimes;
	}

	@Column(name = "scoreTotleE", length = 20)
	public String getScoreTotleE() {
		return this.scoreTotleE;
	}

	public void setScoreTotleE(String scoreTotleE) {
		this.scoreTotleE = scoreTotleE;
	}

	@Column(name = "scoreTimesE", length = 10)
	public String getScoreTimesE() {
		return this.scoreTimesE;
	}

	public void setScoreTimesE(String scoreTimesE) {
		this.scoreTimesE = scoreTimesE;
	}
	
	@JSONField(serialize =false)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_truck", catalog = "", joinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "truckId", nullable = false, updatable = false) })
	public Set<Truck> getTrucks() {
		return this.trucks;
	}

	public void setTrucks(Set<Truck> trucks) {
		this.trucks = trucks;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_carrier", catalog = "", joinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "carrierId", nullable = false, updatable = false) })
	public Set<Carrier> getCarriers() {
		return this.carriers;
	}

	public void setCarriers(Set<Carrier> carriers) {
		this.carriers = carriers;
	}


	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_role", catalog = "", joinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_whes", catalog = "", joinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "whesId", nullable = false, updatable = false) })
	public Set<Whes> getWheses() {
		return this.wheses;
	}

	public void setWheses(Set<Whes> wheses) {
		this.wheses = wheses;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_nvocc", catalog = "", joinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "nvoccId", nullable = false, updatable = false) })
	public Set<Nvocc> getNvoccs() {
		return this.nvoccs;
	}

	public void setNvoccs(Set<Nvocc> nvoccs) {
		this.nvoccs = nvoccs;
	}
	
	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_company", catalog = "", joinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "companyId", nullable = false, updatable = false) })
	public Set<Company> getCompanies() {
		return this.companies;
	}
	
	public void setCompanies(Set<Company> companies) {
		this.companies = companies;
	}

}