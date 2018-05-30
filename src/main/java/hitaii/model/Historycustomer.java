package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Historycustomer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "historycustomer", catalog = "usf3")
public class Historycustomer implements java.io.Serializable {

	// Fields

	private String id;
	private String operUserId;
	private String operDate;
	private String fromUserId;
	private String toUserId;
	private String whesdtlId;
	private String note;

	// Constructors

	/** default constructor */
	public Historycustomer() {
	}

	/** minimal constructor */
	public Historycustomer(String id, String fromUserId, String toUserId, String whesdtlId) {
		this.id = id;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.whesdtlId = whesdtlId;
	}

	/** full constructor */
	public Historycustomer(String id, String operUserId, String operDate, String fromUserId, String toUserId, String whesdtlId, String note) {
		this.id = id;
		this.operUserId = operUserId;
		this.operDate = operDate;
		this.fromUserId = fromUserId;
		this.toUserId = toUserId;
		this.whesdtlId = whesdtlId;
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

	@Column(name = "operUserId", length = 36)
	public String getOperUserId() {
		return this.operUserId;
	}

	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}

	@Column(name = "operDate", length = 20)
	public String getOperDate() {
		return this.operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	@Column(name = "fromUserId", nullable = false, length = 36)
	public String getFromUserId() {
		return this.fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	@Column(name = "toUserId", nullable = false, length = 36)
	public String getToUserId() {
		return this.toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	@Column(name = "whesdtlId", nullable = false, length = 36)
	public String getWhesdtlId() {
		return this.whesdtlId;
	}

	public void setWhesdtlId(String whesdtlId) {
		this.whesdtlId = whesdtlId;
	}

	@Column(name = "note", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}