package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Customer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer", catalog = "usf3")
public class Customer implements java.io.Serializable {

	// Fields

	private String id;
	private String logname;
	private String password;
	private String fullname;
	private String shortname;
	private String country;
	private String state;
	private String city;
	private String address;
	private String email;
	private String web;
	private String contact;
	private String telephone;
	private String ein;
	private String zip;
	private String fax;
	private String datein;
	private String dateout;
	private String dateupdate;
	private String active;
	private String note;
	private String picture;
	private String father;
	private String tree;
	private String scoretotle;
	private String scoretimes;
	private String levels;
	private String specialdiscount;

	// Constructors

	/** default constructor */
	public Customer() {
	}

	/** minimal constructor */
	public Customer(String id, String logname, String password) {
		this.id = id;
		this.logname = logname;
		this.password = password;
	}

	/** full constructor */
	public Customer(String id, String logname, String password, String fullname, String shortname, String country, String state, String city, String address, String email, String web, String contact, String telephone, String ein, String zip, String fax, String datein, String dateout, String dateupdate, String active, String note, String picture, String father, String tree, String scoretotle, String scoretimes, String levels, String specialdiscount) {
		this.id = id;
		this.logname = logname;
		this.password = password;
		this.fullname = fullname;
		this.shortname = shortname;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.email = email;
		this.web = web;
		this.contact = contact;
		this.telephone = telephone;
		this.ein = ein;
		this.zip = zip;
		this.fax = fax;
		this.datein = datein;
		this.dateout = dateout;
		this.dateupdate = dateupdate;
		this.active = active;
		this.note = note;
		this.picture = picture;
		this.father = father;
		this.tree = tree;
		this.scoretotle = scoretotle;
		this.scoretimes = scoretimes;
		this.levels = levels;
		this.specialdiscount = specialdiscount;
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

	@Column(name = "shortname", length = 50)
	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
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

	@Column(name = "contact", length = 50)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "ein", length = 50)
	public String getEin() {
		return this.ein;
	}

	public void setEin(String ein) {
		this.ein = ein;
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

	@Column(name = "father", length = 36)
	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@Column(name = "tree", length = 10)
	public String getTree() {
		return this.tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	@Column(name = "scoretotle", length = 20)
	public String getScoretotle() {
		return this.scoretotle;
	}

	public void setScoretotle(String scoretotle) {
		this.scoretotle = scoretotle;
	}

	@Column(name = "scoretimes", length = 10)
	public String getScoretimes() {
		return this.scoretimes;
	}

	public void setScoretimes(String scoretimes) {
		this.scoretimes = scoretimes;
	}

	@Column(name = "levels", length = 10)
	public String getLevels() {
		return this.levels;
	}

	public void setLevels(String levels) {
		this.levels = levels;
	}

	@Column(name = "specialdiscount", length = 10)
	public String getSpecialdiscount() {
		return this.specialdiscount;
	}

	public void setSpecialdiscount(String specialdiscount) {
		this.specialdiscount = specialdiscount;
	}

}