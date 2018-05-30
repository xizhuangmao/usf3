package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * City entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "city", catalog = "usf3")
public class City implements java.io.Serializable {

	// Fields

	private String id;
	private State state;
	private String city;
	private String shortname;
	private String nickname;
	private String isCapital;

	// Constructors

	/** default constructor */
	public City() {
	}

	/** minimal constructor */
	public City(String id, String city) {
		this.id = id;
		this.city = city;
	}

	/** full constructor */
	public City(String id, State state, String city, String shortname, String nickname, String isCapital) {
		this.id = id;
		this.state = state;
		this.city = city;
		this.shortname = shortname;
		this.nickname = nickname;
		this.isCapital = isCapital;
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

	@JSONField(serialize =false)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stateId")
	public State getState() {
		return this.state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@Column(name = "city", nullable = false, length = 100)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "shortname", length = 50)
	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	@Column(name = "nickname", length = 50)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "isCapital", length = 2)
	public String getIsCapital() {
		return this.isCapital;
	}

	public void setIsCapital(String isCapital) {
		this.isCapital = isCapital;
	}

}