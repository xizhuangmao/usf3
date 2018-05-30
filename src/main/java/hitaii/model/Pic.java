package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pic", catalog = "usf3")
public class Pic implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String vin;
	private String path;
	private String pictype;

	// Constructors

	/** default constructor */
	public Pic() {
	}

	/** minimal constructor */
	public Pic(String id) {
		this.id = id;
	}

	/** full constructor */
	public Pic(String id, String name, String vin, String path, String pictype) {
		this.id = id;
		this.name = name;
		this.vin = vin;
		this.path = path;
		this.pictype = pictype;
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

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "vin", length = 20)
	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "path", length = 400)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "pictype", length = 10)
	public String getPictype() {
		return this.pictype;
	}

	public void setPictype(String pictype) {
		this.pictype = pictype;
	}

}