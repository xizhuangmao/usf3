package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Port entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "port", catalog = "usf3")
public class Port implements java.io.Serializable {

	// Fields

	private String id;
	private String port;
	private String country;
	private String state;
	private String city;

	// Constructors

	/** default constructor */
	public Port() {
	}

	/** minimal constructor */
	public Port(String id, String port) {
		this.id = id;
		this.port = port;
	}

	/** full constructor */
	public Port(String id, String port, String country, String state, String city) {
		this.id = id;
		this.port = port;
		this.country = country;
		this.state = state;
		this.city = city;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "port", nullable = false, length = 50)
	public String getPort() {
		return this.port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Column(name = "country", length = 50)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "state", length = 50)
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "city", length = 50)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}