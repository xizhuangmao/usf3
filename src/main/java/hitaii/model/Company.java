package hitaii.model;
// default package

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
 * Company entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "company", catalog = "usf3")
public class Company implements java.io.Serializable {

	// Fields

	private String id;
	private String fullname;
	private String shortname;
	private String country;
	private String state;
	private String city;
	private String address;
	private String telephone;
	private String contact;
	private String fax;
	private String zip;
	private String note;
	private String web;
	private String email;
	private String datein;
	private String dateout;
	private String dateupdate;
	private String active;
	private String father;
	private String scoretotle;
	private String scoretime;
	private String types;
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Company() {
	}

	/** minimal constructor */
	public Company(String id, String fullname) {
		this.id = id;
		this.fullname = fullname;
	}

	/** full constructor */
	public Company(String id, String fullname, String shortname,
			String country, String state, String city, String address,
			String telephone, String contact, String fax, String zip,
			String note, String web, String email, String datein,
			String dateout, String dateupdate, String active, String father,
			String scoretotle, String scoretime, String types,Set<Users> userses) {
		this.id = id;
		this.fullname = fullname;
		this.shortname = shortname;
		this.country = country;
		this.state = state;
		this.city = city;
		this.address = address;
		this.telephone = telephone;
		this.contact = contact;
		this.fax = fax;
		this.zip = zip;
		this.note = note;
		this.web = web;
		this.email = email;
		this.datein = datein;
		this.dateout = dateout;
		this.dateupdate = dateupdate;
		this.active = active;
		this.father = father;
		this.scoretotle = scoretotle;
		this.scoretime = scoretime;
		this.types = types;
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

	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "telephone", length = 50)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "contact", length = 100)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "fax", length = 50)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "zip", length = 50)
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "note", length = 300)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "web", length = 200)
	public String getWeb() {
		return this.web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Column(name = "email", length = 100)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@Column(name = "father", length = 36)
	public String getFather() {
		return this.father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	@Column(name = "scoretotle", length = 20)
	public String getScoretotle() {
		return this.scoretotle;
	}

	public void setScoretotle(String scoretotle) {
		this.scoretotle = scoretotle;
	}

	@Column(name = "scoretime", length = 10)
	public String getScoretime() {
		return this.scoretime;
	}

	public void setScoretime(String scoretime) {
		this.scoretime = scoretime;
	}
	
	@Column(name = "types", length = 10)
	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@JSONField(serialize =false)
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "users_company", catalog = "", joinColumns = { @JoinColumn(name = "companyId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) })
	public Set<Users> getUserses() {
		return this.userses;
	}

	
	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}