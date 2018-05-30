package hitaii.pageModel;

import hitaii.model.Services;
import hitaii.model.Whesdtl;

public class PwhesdtlServices {
	private String id;
	private Services services;
	private Whesdtl whesdtl;
	private String discount;
	private String pay;
	private String paystate;
	private String price;
	private String whesdtlId;
	
	public String getWhesdtlId() {
		return whesdtlId;
	}
	public void setWhesdtlId(String whesdtlId) {
		this.whesdtlId = whesdtlId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Services getServices() {
		return services;
	}
	public void setServices(Services services) {
		this.services = services;
	}
	public Whesdtl getWhesdtl() {
		return whesdtl;
	}
	public void setWhesdtl(Whesdtl whesdtl) {
		this.whesdtl = whesdtl;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public String getPaystate() {
		return paystate;
	}
	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}
	
	
}
