package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dock entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dock", catalog = "usf3")
public class Dock implements java.io.Serializable {

	// Fields

	private String id;
	private String uoo;
	private String carrier;
	private String booknum;
	private String shipper;
	private String consignee;
	private String notify;
	private String agent;
	private String routing;
	private String origin;
	private String vessel;
	private String discharge;
	private String receipt;
	private String loading;
	private String final_;
	private String marks;
	private String num;
	private String description;
	private String weight;
	private String measurement;

	// Constructors

	/** default constructor */
	public Dock() {
	}

	/** minimal constructor */
	public Dock(String id) {
		this.id = id;
	}

	/** full constructor */
	public Dock(String id, String uoo, String carrier, String booknum, String shipper, String consignee, String notify, String agent, String routing, String origin, String vessel, String discharge, String receipt, String loading, String final_, String marks, String num, String description, String weight, String measurement) {
		this.id = id;
		this.uoo = uoo;
		this.carrier = carrier;
		this.booknum = booknum;
		this.shipper = shipper;
		this.consignee = consignee;
		this.notify = notify;
		this.agent = agent;
		this.routing = routing;
		this.origin = origin;
		this.vessel = vessel;
		this.discharge = discharge;
		this.receipt = receipt;
		this.loading = loading;
		this.final_ = final_;
		this.marks = marks;
		this.num = num;
		this.description = description;
		this.weight = weight;
		this.measurement = measurement;
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

	@Column(name = "uoo", length = 20)
	public String getUoo() {
		return this.uoo;
	}

	public void setUoo(String uoo) {
		this.uoo = uoo;
	}

	@Column(name = "carrier", length = 50)
	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Column(name = "booknum", length = 50)
	public String getBooknum() {
		return this.booknum;
	}

	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}

	@Column(name = "shipper", length = 65535)
	public String getShipper() {
		return this.shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	@Column(name = "consignee", length = 65535)
	public String getConsignee() {
		return this.consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	@Column(name = "notify", length = 65535)
	public String getNotify() {
		return this.notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}

	@Column(name = "agent", length = 65535)
	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Column(name = "routing", length = 65535)
	public String getRouting() {
		return this.routing;
	}

	public void setRouting(String routing) {
		this.routing = routing;
	}

	@Column(name = "origin", length = 100)
	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "vessel", length = 100)
	public String getVessel() {
		return this.vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	@Column(name = "discharge", length = 100)
	public String getDischarge() {
		return this.discharge;
	}

	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}

	@Column(name = "receipt", length = 100)
	public String getReceipt() {
		return this.receipt;
	}

	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}

	@Column(name = "loading", length = 100)
	public String getLoading() {
		return this.loading;
	}

	public void setLoading(String loading) {
		this.loading = loading;
	}

	@Column(name = "final", length = 100)
	public String getFinal_() {
		return this.final_;
	}

	public void setFinal_(String final_) {
		this.final_ = final_;
	}

	@Column(name = "marks", length = 65535)
	public String getMarks() {
		return this.marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	@Column(name = "num", length = 65535)
	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name = "description", length = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "weight", length = 65535)
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "measurement", length = 65535)
	public String getMeasurement() {
		return this.measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

}