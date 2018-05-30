package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Uoo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "uoo", catalog = "usf3")
public class Uoo implements java.io.Serializable {

	// Fields

	private String id;
	private String uoo;

	// Constructors

	/** default constructor */
	public Uoo() {
	}

	/** full constructor */
	public Uoo(String id, String uoo) {
		this.id = id;
		this.uoo = uoo;
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

	@Column(name = "uoo", nullable = false, length = 100)
	public String getUoo() {
		return this.uoo;
	}

	public void setUoo(String uoo) {
		this.uoo = uoo;
	}

}