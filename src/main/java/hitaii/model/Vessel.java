package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Vessel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "vessel", catalog = "usf3")
public class Vessel implements java.io.Serializable {

	// Fields

	private String id;
	private String carrierId;
	private String vessel;
	private String active;
	private String note;

	// Constructors

	/** default constructor */
	public Vessel() {
	}

	/** minimal constructor */
	public Vessel(String id) {
		this.id = id;
	}

	/** full constructor */
	public Vessel(String id, String carrierId, String vessel, String active, String note) {
		this.id = id;
		this.carrierId = carrierId;
		this.vessel = vessel;
		this.active = active;
		this.note = note;
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

	@Column(name = "carrierId", length = 36)
	public String getCarrierId() {
		return this.carrierId;
	}

	public void setCarrierId(String carrierId) {
		this.carrierId = carrierId;
	}

	@Column(name = "vessel", length = 50)
	public String getVessel() {
		return this.vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	@Column(name = "active", length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "note", length = 100)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}