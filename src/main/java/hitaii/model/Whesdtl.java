package hitaii.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Whesdtl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whesdtl", catalog = "usf3")
public class Whesdtl implements java.io.Serializable {

	// Fields

	private String id;
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

	// Constructors

	/** default constructor */
	public Whesdtl() {
	}

	/** minimal constructor */
	public Whesdtl(String id, String usersId, String make, String model) {
		this.id = id;
		this.usersId = usersId;
		this.make = make;
		this.model = model;
	}

	/** full constructor */
	public Whesdtl(String id, String usersId, String users, String make,
			String model, String vin, String engine, String year, String color,
			String keynum, String sticker, String note, String fuel,
			String fuelType, String whesId, String whes, String nvoccId,
			String nvocc, String indate, String vehicletype, String freedate,
			String usersOperId, String usersOper, String titlestate,
			String title1, String title2, String title3, String title4,
			String billstate, String bill1, String bill2, String bill3,
			String bill4, String proofstate, String proof1, String proof2,
			String proof3, String proof4, String pic, String mdate,
			String floormat, String dvdremote, String heatset,
			String usermanual, String cod, String ordersId, String booknumId,
			String flowstate, Set<WhesdtlServices> whesdtlServiceses) {
		this.id = id;
		this.usersId = usersId;
		this.users = users;
		this.make = make;
		this.model = model;
		this.vin = vin;
		this.engine = engine;
		this.year = year;
		this.color = color;
		this.keynum = keynum;
		this.sticker = sticker;
		this.note = note;
		this.fuel = fuel;
		this.fuelType = fuelType;
		this.whesId = whesId;
		this.whes = whes;
		this.nvoccId = nvoccId;
		this.nvocc = nvocc;
		this.indate = indate;
		this.vehicletype = vehicletype;
		this.freedate = freedate;
		this.usersOperId = usersOperId;
		this.usersOper = usersOper;
		this.titlestate = titlestate;
		this.title1 = title1;
		this.title2 = title2;
		this.title3 = title3;
		this.title4 = title4;
		this.billstate = billstate;
		this.bill1 = bill1;
		this.bill2 = bill2;
		this.bill3 = bill3;
		this.bill4 = bill4;
		this.proofstate = proofstate;
		this.proof1 = proof1;
		this.proof2 = proof2;
		this.proof3 = proof3;
		this.proof4 = proof4;
		this.pic = pic;
		this.mdate = mdate;
		this.floormat = floormat;
		this.dvdremote = dvdremote;
		this.heatset = heatset;
		this.usermanual = usermanual;
		this.cod = cod;
		this.ordersId = ordersId;
		this.booknumId = booknumId;
		this.flowstate = flowstate;
		this.whesdtlServiceses = whesdtlServiceses;
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

	@Column(name = "usersId", nullable = false, length = 36)
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

	@Column(name = "make", nullable = false, length = 50)
	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Column(name = "model", nullable = false, length = 50)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "vin", length = 20)
	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "engine", length = 50)
	public String getEngine() {
		return this.engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	@Column(name = "year", length = 20)
	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Column(name = "color", length = 50)
	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "keynum", length = 2)
	public String getKeynum() {
		return this.keynum;
	}

	public void setKeynum(String keynum) {
		this.keynum = keynum;
	}

	@Column(name = "sticker", length = 4)
	public String getSticker() {
		return this.sticker;
	}

	public void setSticker(String sticker) {
		this.sticker = sticker;
	}

	@Column(name = "note", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "fuel", length = 50)
	public String getFuel() {
		return this.fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	@Column(name = "fuelType", length = 50)
	public String getFuelType() {
		return this.fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	@Column(name = "whesId", length = 36)
	public String getWhesId() {
		return this.whesId;
	}

	public void setWhesId(String whesId) {
		this.whesId = whesId;
	}

	@Column(name = "whes", length = 200)
	public String getWhes() {
		return this.whes;
	}

	public void setWhes(String whes) {
		this.whes = whes;
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

	@Column(name = "indate", length = 20)
	public String getIndate() {
		return this.indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	@Column(name = "vehicletype", length = 20)
	public String getVehicletype() {
		return this.vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	@Column(name = "freedate", length = 20)
	public String getFreedate() {
		return this.freedate;
	}

	public void setFreedate(String freedate) {
		this.freedate = freedate;
	}

	@Column(name = "usersOperId", length = 36)
	public String getUsersOperId() {
		return this.usersOperId;
	}

	public void setUsersOperId(String usersOperId) {
		this.usersOperId = usersOperId;
	}

	@Column(name = "usersOper", length = 200)
	public String getUsersOper() {
		return this.usersOper;
	}

	public void setUsersOper(String usersOper) {
		this.usersOper = usersOper;
	}

	@Column(name = "titlestate", length = 10)
	public String getTitlestate() {
		return this.titlestate;
	}

	public void setTitlestate(String titlestate) {
		this.titlestate = titlestate;
	}

	@Column(name = "title1", length = 20)
	public String getTitle1() {
		return this.title1;
	}

	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	@Column(name = "title2", length = 20)
	public String getTitle2() {
		return this.title2;
	}

	public void setTitle2(String title2) {
		this.title2 = title2;
	}

	@Column(name = "title3", length = 20)
	public String getTitle3() {
		return this.title3;
	}

	public void setTitle3(String title3) {
		this.title3 = title3;
	}

	@Column(name = "title4", length = 20)
	public String getTitle4() {
		return this.title4;
	}

	public void setTitle4(String title4) {
		this.title4 = title4;
	}

	@Column(name = "billstate", length = 10)
	public String getBillstate() {
		return this.billstate;
	}

	public void setBillstate(String billstate) {
		this.billstate = billstate;
	}

	@Column(name = "bill1", length = 20)
	public String getBill1() {
		return this.bill1;
	}

	public void setBill1(String bill1) {
		this.bill1 = bill1;
	}

	@Column(name = "bill2", length = 20)
	public String getBill2() {
		return this.bill2;
	}

	public void setBill2(String bill2) {
		this.bill2 = bill2;
	}

	@Column(name = "bill3", length = 20)
	public String getBill3() {
		return this.bill3;
	}

	public void setBill3(String bill3) {
		this.bill3 = bill3;
	}

	@Column(name = "bill4", length = 20)
	public String getBill4() {
		return this.bill4;
	}

	public void setBill4(String bill4) {
		this.bill4 = bill4;
	}

	@Column(name = "proofstate", length = 10)
	public String getProofstate() {
		return this.proofstate;
	}

	public void setProofstate(String proofstate) {
		this.proofstate = proofstate;
	}

	@Column(name = "proof1", length = 20)
	public String getProof1() {
		return this.proof1;
	}

	public void setProof1(String proof1) {
		this.proof1 = proof1;
	}

	@Column(name = "proof2", length = 20)
	public String getProof2() {
		return this.proof2;
	}

	public void setProof2(String proof2) {
		this.proof2 = proof2;
	}

	@Column(name = "proof3", length = 20)
	public String getProof3() {
		return this.proof3;
	}

	public void setProof3(String proof3) {
		this.proof3 = proof3;
	}

	@Column(name = "proof4", length = 20)
	public String getProof4() {
		return this.proof4;
	}

	public void setProof4(String proof4) {
		this.proof4 = proof4;
	}

	@Column(name = "pic", length = 400)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Column(name = "mdate", length = 20)
	public String getMdate() {
		return this.mdate;
	}

	public void setMdate(String mdate) {
		this.mdate = mdate;
	}

	@Column(name = "floormat", length = 20)
	public String getFloormat() {
		return this.floormat;
	}

	public void setFloormat(String floormat) {
		this.floormat = floormat;
	}

	@Column(name = "dvdremote", length = 20)
	public String getDvdremote() {
		return this.dvdremote;
	}

	public void setDvdremote(String dvdremote) {
		this.dvdremote = dvdremote;
	}

	@Column(name = "heatset", length = 20)
	public String getHeatset() {
		return this.heatset;
	}

	public void setHeatset(String heatset) {
		this.heatset = heatset;
	}

	@Column(name = "usermanual", length = 20)
	public String getUsermanual() {
		return this.usermanual;
	}

	public void setUsermanual(String usermanual) {
		this.usermanual = usermanual;
	}

	@Column(name = "cod", length = 50)
	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	@Column(name = "ordersId", length = 36)
	public String getOrdersId() {
		return this.ordersId;
	}

	public void setOrdersId(String ordersId) {
		this.ordersId = ordersId;
	}

	@Column(name = "booknumId", length = 36)
	public String getBooknumId() {
		return this.booknumId;
	}

	public void setBooknumId(String booknumId) {
		this.booknumId = booknumId;
	}

	@Column(name = "flowstate", length = 10)
	public String getFlowstate() {
		return this.flowstate;
	}

	public void setFlowstate(String flowstate) {
		this.flowstate = flowstate;
	}

	@JSONField(serialize =false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "whesdtl")
	public Set<WhesdtlServices> getWhesdtlServiceses() {
		return this.whesdtlServiceses;
	}

	public void setWhesdtlServiceses(Set<WhesdtlServices> whesdtlServiceses) {
		this.whesdtlServiceses = whesdtlServiceses;
	}

}