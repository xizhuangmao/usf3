package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Booknum entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "booknum", catalog = "usf3")
public class Booknum implements java.io.Serializable {

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
	private String nvoccId;
	private String nvocc;
	private String companyId;
	private String company;
	private String active;
	private String note;
	private String uoo;
	private String consize;
	private String sealnum;
	private String connum;
	private String loaddate;
	private String pod;

	// Constructors

	/** default constructor */
	public Booknum() {
	}

	/** minimal constructor */
	public Booknum(String id, String booknum, String voyageId) {
		this.id = id;
		this.booknum = booknum;
		this.voyageId = voyageId;
	}

	/** full constructor */
	public Booknum(String id, String booknum, String carrier, String vessel, String voyageId, String voyage, String usersId, String users, String entrydate, String truckId, String truck, String truckusersId, String truckusers, String truckdate, String nvoccId, String nvocc,  String companyId, String company, String active, String note, String uoo, String consize, String sealnum, String connum, String loaddate, String pod) {
		this.id = id;
		this.booknum = booknum;
		this.carrier = carrier;
		this.vessel = vessel;
		this.voyageId = voyageId;
		this.voyage = voyage;
		this.usersId = usersId;
		this.users = users;
		this.entrydate = entrydate;
		this.truckId = truckId;
		this.truck = truck;
		this.truckusersId = truckusersId;
		this.truckusers = truckusers;
		this.truckdate = truckdate;
		this.nvoccId = nvoccId;
		this.nvocc = nvocc;
		this.companyId = companyId;
		this.company = company;
		this.active = active;
		this.note = note;
		this.uoo = uoo;
		this.consize = consize;
		this.sealnum = sealnum;
		this.connum = connum;
		this.loaddate = loaddate;
		this.pod = pod;
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

	@Column(name = "booknum", nullable = false, length = 50)
	public String getBooknum() {
		return this.booknum;
	}

	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}

	@Column(name = "carrier", length = 50)
	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Column(name = "vessel", length = 50)
	public String getVessel() {
		return this.vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	@Column(name = "voyageId", nullable = false, length = 36)
	public String getVoyageId() {
		return this.voyageId;
	}

	public void setVoyageId(String voyageId) {
		this.voyageId = voyageId;
	}

	@Column(name = "voyage", length = 50)
	public String getVoyage() {
		return this.voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	@Column(name = "usersId", length = 36)
	public String getUsersId() {
		return this.usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	@Column(name = "users", length = 200)
	public String getUsers() {
		return this.users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	@Column(name = "entrydate", length = 20)
	public String getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	@Column(name = "truckId", length = 36)
	public String getTruckId() {
		return this.truckId;
	}

	public void setTruckId(String truckId) {
		this.truckId = truckId;
	}

	@Column(name = "truck", length = 50)
	public String getTruck() {
		return this.truck;
	}

	public void setTruck(String truck) {
		this.truck = truck;
	}

	@Column(name = "truckusersId", length = 36)
	public String getTruckusersId() {
		return this.truckusersId;
	}

	public void setTruckusersId(String truckusersId) {
		this.truckusersId = truckusersId;
	}

	@Column(name = "truckusers", length = 200)
	public String getTruckusers() {
		return this.truckusers;
	}

	public void setTruckusers(String truckusers) {
		this.truckusers = truckusers;
	}

	@Column(name = "truckdate", length = 20)
	public String getTruckdate() {
		return this.truckdate;
	}

	public void setTruckdate(String truckdate) {
		this.truckdate = truckdate;
	}

	@Column(name = "nvoccId", length = 36)
	public String getNvoccId() {
		return this.nvoccId;
	}

	public void setNvoccId(String nvoccId) {
		this.nvoccId = nvoccId;
	}

	@Column(name = "nvocc", length = 200)
	public String getNvocc() {
		return this.nvocc;
	}

	public void setNvocc(String nvocc) {
		this.nvocc = nvocc;
	}
	
	@Column(name = "companyId", length = 36)
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@Column(name = "company", length = 200)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "active", length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "note", length = 100)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "uoo", length = 50)
	public String getUoo() {
		return this.uoo;
	}

	public void setUoo(String uoo) {
		this.uoo = uoo;
	}

	@Column(name = "consize", length = 20)
	public String getConsize() {
		return this.consize;
	}

	public void setConsize(String consize) {
		this.consize = consize;
	}

	@Column(name = "sealnum", length = 50)
	public String getSealnum() {
		return this.sealnum;
	}

	public void setSealnum(String sealnum) {
		this.sealnum = sealnum;
	}

	@Column(name = "connum", length = 50)
	public String getConnum() {
		return this.connum;
	}

	public void setConnum(String connum) {
		this.connum = connum;
	}

	@Column(name = "loaddate", length = 20)
	public String getLoaddate() {
		return this.loaddate;
	}

	public void setLoaddate(String loaddate) {
		this.loaddate = loaddate;
	}
	
	@Column(name = "pod", length = 50)
	public String getPod() {
		return this.pod;
	}

	public void setPod(String pod) {
		this.pod = pod;
	}

}