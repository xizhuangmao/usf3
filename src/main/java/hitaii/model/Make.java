package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Make entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "make", catalog = "usf3")
public class Make implements java.io.Serializable {

	// Fields

	private String id;
	private Country country;
	private String make;
	private String shortname;
	private String nickname;

	// Constructors

	/** default constructor */
	public Make() {
	}

	/** minimal constructor */
	public Make(String id, String make) {
		this.id = id;
		this.make = make;
	}

	/** full constructor */
	public Make(String id, Country country, String make, String shortname, String nickname) {
		this.id = id;
		this.country = country;
		this.make = make;
		this.shortname = shortname;
		this.nickname = nickname;
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
	@JoinColumn(name = "countryId")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "make", nullable = false, length = 100)
	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
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

}