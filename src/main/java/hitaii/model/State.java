package hitaii.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * State entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "state", catalog = "usf3")
public class State implements java.io.Serializable {
	// Fields

	private String id;
	private Country country;
	private String state;
	private String shortname;
	private String nickname;
	private Set<City> cities = new HashSet<City>(0);

	// Constructors

	/** default constructor */
	public State() {
	}

	/** minimal constructor */
	public State(String id, String state) {
		this.id = id;
		this.state = state;
	}

	/** full constructor */
	public State(String id, Country country, String state, String shortname, String nickname, Set<City> cities) {
		this.id = id;
		this.country = country;
		this.state = state;
		this.shortname = shortname;
		this.nickname = nickname;
		this.cities = cities;
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
	@JoinColumn(name = "countryId")
	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Column(name = "state", nullable = false, length = 100)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
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

	@JSONField(serialize =false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "state")
	public Set<City> getCities() {
		return this.cities;
	}

	public void setCities(Set<City> cities) {
		this.cities = cities;
	}

}