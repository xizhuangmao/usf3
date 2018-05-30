package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Firstpage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "firstpage", catalog = "usf3")
public class Firstpage implements java.io.Serializable {

	// Fields

	private String id;
	private String introduce;
	private String callus;
	private String workinghours;
	private String firstpic;
	private String picintroduce;
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;

	// Constructors

	/** default constructor */
	public Firstpage() {
	}

	/** minimal constructor */
	public Firstpage(String id) {
		this.id = id;
	}

	/** full constructor */
	public Firstpage(String id, String introduce, String callus,
			String workinghours, String firstpic, String picintroduce,
			String pic1, String pic2, String pic3, String pic4, String pic5) {
		this.id = id;
		this.introduce = introduce;
		this.callus = callus;
		this.workinghours = workinghours;
		this.firstpic = firstpic;
		this.picintroduce = picintroduce;
		this.pic1 = pic1;
		this.pic2 = pic2;
		this.pic3 = pic3;
		this.pic4 = pic4;
		this.pic5 = pic5;
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

	@Column(name = "introduce", length = 65535)
	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	@Column(name = "callus", length = 65535)
	public String getCallus() {
		return this.callus;
	}

	public void setCallus(String callus) {
		this.callus = callus;
	}

	@Column(name = "workinghours", length = 65535)
	public String getWorkinghours() {
		return this.workinghours;
	}

	public void setWorkinghours(String workinghours) {
		this.workinghours = workinghours;
	}

	@Column(name = "firstpic", length = 100)
	public String getFirstpic() {
		return this.firstpic;
	}

	public void setFirstpic(String firstpic) {
		this.firstpic = firstpic;
	}

	@Column(name = "picintroduce", length = 65535)
	public String getPicintroduce() {
		return this.picintroduce;
	}

	public void setPicintroduce(String picintroduce) {
		this.picintroduce = picintroduce;
	}

	@Column(name = "pic1", length = 100)
	public String getPic1() {
		return this.pic1;
	}

	public void setPic1(String pic1) {
		this.pic1 = pic1;
	}

	@Column(name = "pic2", length = 100)
	public String getPic2() {
		return this.pic2;
	}

	public void setPic2(String pic2) {
		this.pic2 = pic2;
	}

	@Column(name = "pic3", length = 100)
	public String getPic3() {
		return this.pic3;
	}

	public void setPic3(String pic3) {
		this.pic3 = pic3;
	}

	@Column(name = "pic4", length = 100)
	public String getPic4() {
		return this.pic4;
	}

	public void setPic4(String pic4) {
		this.pic4 = pic4;
	}

	@Column(name = "pic5", length = 100)
	public String getPic5() {
		return this.pic5;
	}

	public void setPic5(String pic5) {
		this.pic5 = pic5;
	}

}