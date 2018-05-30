package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Model entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "model", catalog = "usf3")
public class Model implements java.io.Serializable {

	// Fields

	private String id;
	private String makeId;
	private String model;

	// Constructors

	/** default constructor */
	public Model() {
	}

	/** minimal constructor */
	public Model(String id, String model) {
		this.id = id;
		this.model = model;
	}

	/** full constructor */
	public Model(String id, String makeId, String model) {
		this.id = id;
		this.makeId = makeId;
		this.model = model;
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

	@Column(name = "makeId", length = 36)
	public String getMakeId() {
		return this.makeId;
	}

	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	@Column(name = "model", nullable = false, length = 100)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}