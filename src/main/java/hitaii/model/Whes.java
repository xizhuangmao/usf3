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
 * Whes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whes", catalog = "usf3")
public class Whes implements java.io.Serializable {

	// Fields

	private String id;
	private String fullname;
	private String shortname;
	private String country;
	private String state;
	private String city;
	private String note;
	private String address;
	private String zip;
	private String telphone;
	private String fax;
	private String email;
	private String contact;
	private String datein;
	private String dateout;
	private String dateupdate;
	private String picture;
	private String scoretotle;
	private String scoretimes;
	private String active;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Whes() {
	}

	/** minimal constructor */
	public Whes(String id, String fullname) {
		this.id = id;
		this.fullname = fullname;
	}

	/** full constructor */
	public Whes(String id, String fullname, String shortname, String country, String state, String city, String note, String address, String zip, String telphone, String fax, String email, String contact, String datein, String dateout, String dateupdate, String picture, String scoretotle, String scoretimes, String active, Set<Users> userses) {
		this.id = id;
		this.fullname = fullname;
		this.shortname = shortname;
		this.country = country;
		this.state = state;
		this.city = city;
		this.note = note;
		this.address = address;
		this.zip = zip;
		this.telphone = telphone;
		this.fax = fax;
		this.email = email;
		this.contact = contact;
		this.datein = datein;
		this.dateout = dateout;
		this.dateupdate = dateupdate;
		this.picture = picture;
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

	@Column(name = "note", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "zip", length = 50)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "telphone", length = 50)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "fax", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "email", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "contact", length = 100)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	@Column(name = "picture", length = 200)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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

	@Column(name = "active", length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_whes", catalog = "", joinColumns = { @JoinColumn(name = "whesId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) })
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}