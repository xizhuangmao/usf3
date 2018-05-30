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
 * Country entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "country", catalog = "usf3")
public class Country implements java.io.Serializable {

	// Fields

	private String id;
	private String country;
	private String shortname;
	private String nickname;
	private Set<State> states = new HashSet<State>(0);
	private Set<Make> makes = new HashSet<Make>(0);

	// Constructors

	/** default constructor */
	public Country() {
	}

	/** minimal constructor */
	public Country(String id, String country) {
		this.id = id;
		this.country = country;
	}

	/** full constructor */
	public Country(String id, String country, String shortname, String nickname, Set<State> states, Set<Make> makes) {
		this.id = id;
		this.country = country;
		this.shortname = shortname;
		this.nickname = nickname;
		this.states = states;
		this.makes = makes;
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

	@Column(name = "country", nullable = false, length = 100)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	public Set<State> getStates() {
		return this.states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}

	@JSONField(serialize =false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "country")
	public Set<Make> getMakes() {
		return this.makes;
	}

	public void setMakes(Set<Make> makes) {
		this.makes = makes;
	}

}