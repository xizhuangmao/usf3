package hitaii.pageModel;

import java.util.List;
import java.util.Map;

public class Pdock {
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
	private String vessel;   //vessel , voyage
	private String discharge;
	private String receipt;
	private String loading;
	private String final_;
	private String marks;
	private String num;
	private String description;
	private String weight;
	private String measurement;
	
	private String cutoffdate;
	private String connum;
	private String sealnum;
	private List<Map<String,String>> whesdtls;//year make model vin
	private List<String> whesdtlList;
	public List<String> getWhesdtlList() {
		return whesdtlList;
	}
	public void setWhesdtlList(List<String> whesdtlList) {
		this.whesdtlList = whesdtlList;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUoo() {
		return uoo;
	}
	public void setUoo(String uoo) {
		this.uoo = uoo;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getBooknum() {
		return booknum;
	}
	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}
	public String getShipper() {
		return shipper;
	}
	public void setShipper(String shipper) {
		this.shipper = shipper;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getNotify() {
		return notify;
	}
	public void setNotify(String notify) {
		this.notify = notify;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getRouting() {
		return routing;
	}
	public void setRouting(String routing) {
		this.routing = routing;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getVessel() {
		return vessel;
	}
	public void setVessel(String vessel) {
		this.vessel = vessel;
	}
	public String getDischarge() {
		return discharge;
	}
	public void setDischarge(String discharge) {
		this.discharge = discharge;
	}
	public String getReceipt() {
		return receipt;
	}
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	public String getLoading() {
		return loading;
	}
	public void setLoading(String loading) {
		this.loading = loading;
	}
	public String getFinal_() {
		return final_;
	}
	public void setFinal_(String final_) {
		this.final_ = final_;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	public String getCutoffdate() {
		return cutoffdate;
	}
	public void setCutoffdate(String cutoffdate) {
		this.cutoffdate = cutoffdate;
	}
	public String getConnum() {
		return connum;
	}
	public void setConnum(String connum) {
		this.connum = connum;
	}
	public String getSealnum() {
		return sealnum;
	}
	public void setSealnum(String sealnum) {
		this.sealnum = sealnum;
	}
	public List<Map<String, String>> getWhesdtls() {
		return whesdtls;
	}
	public void setWhesdtls(List<Map<String, String>> whesdtls) {
		this.whesdtls = whesdtls;
	}
	
}
