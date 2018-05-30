package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * WhesdtlServices entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whesdtl_services", catalog = "usf3")
public class WhesdtlServices implements java.io.Serializable {

	// Fields

	private String id;
	private Services services;
	private Whesdtl whesdtl;
	private String discount;
	private String price;
	private String pay;
	private String paystate;

	// Constructors

	/** default constructor */
	public WhesdtlServices() {
	}

	/** minimal constructor */
	public WhesdtlServices(String id, Services services, Whesdtl whesdtl) {
		this.id = id;
		this.services = services;
		this.whesdtl = whesdtl;
	}

	/** full constructor */
	public WhesdtlServices(String id, Services services, Whesdtl whesdtl,
			String discount, String price, String pay, String paystate) {
		this.id = id;
		this.services = services;
		this.whesdtl = whesdtl;
		this.discount = discount;
		this.price = price;
		this.pay = pay;
		this.paystate = paystate;
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
	@JoinColumn(name = "servicesId", nullable = false)
	public Services getServices() {
		return this.services;
	}

	public void setServices(Services services) {
		this.services = services;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "whesdtlId", nullable = false)
	public Whesdtl getWhesdtl() {
		return this.whesdtl;
	}

	public void setWhesdtl(Whesdtl whesdtl) {
		this.whesdtl = whesdtl;
	}

	@Column(name = "discount", length = 10)
	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Column(name = "price", length = 20)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "pay", length = 20)
	public String getPay() {
		return this.pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Column(name = "paystate", length = 10)
	public String getPaystate() {
		return this.paystate;
	}

	public void setPaystate(String paystate) {
		this.paystate = paystate;
	}

}