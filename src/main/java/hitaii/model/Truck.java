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
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Truck entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "truck", catalog = "usf3")
public class Truck implements java.io.Serializable {

	// Fields

	private String id;
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
	private String zip;
	private String fax;
	private String picture;
	private String datein;
	private String dateout;
	private String dateupdate;
	private String scoretotle;
	private String scoretimes;
	private String active;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Truck() {
	}

	/** minimal constructor */
	public Truck(String id, String fullname) {
		this.id = id;
		this.fullname = fullname;
	}

	/** full constructor */
	public Truck(String id, String fullname, String shortname, String country, String state, String city, String address, String email, String web, String contact, String telephone, String zip, String fax, String picture, String datein, String dateout, String dateupdate, String scoretotle, String scoretimes, String active, Set<Users> userses) {
		this.id = id;
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
		this.zip = zip;
		this.fax = fax;
		this.picture = picture;
		this.datein = datein;
		this.dateout = dateout;
		this.dateupdate = dateupdate;
		this.scoretotle = scoretotle;
		this.scoretimes = scoretimes;
		this.active = active;
		this.userses = userses;
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

	@Column(name = "fullname", nullable = false, length = 200)
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

	@Column(name = "address", length = 100)
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

	@Column(name = "contact", length = 100)
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

	@Column(name = "picture", length = 200)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	@Column(name = "scoretotle", length = 20)
	public String getScoretotle() {
		return this.scoretotle;
	}

	public void setScoretotle(String scoretotle) {
		this.scoretotle = scoretotle;
	}

	@Column(name = "scoretimes")
	public String getScoretimes() {
		return this.scoretimes;
	}

	public void setScoretimes(String scoretimes) {
		this.scoretimes = scoretimes;
	}

	@Column(name = "active", length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_truck", catalog = "", joinColumns = { @JoinColumn(name = "truckId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) })
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}