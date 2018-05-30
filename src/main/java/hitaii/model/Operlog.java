package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Operlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "operlog", catalog = "usf3")
public class Operlog implements java.io.Serializable {

	// Fields

	private String id;
	private String userId;
	private String userDtl;
	private String operDate;
	private String operItem;
	private String operType;
	private String operDtl;

	// Constructors

	/** default constructor */
	public Operlog() {
	}

	/** minimal constructor */
	public Operlog(String id) {
		this.id = id;
	}

	/** full constructor */
	public Operlog(String id, String userId, String userDtl, String operDate, String operItem, String operType, String operDtl) {
		this.id = id;
		this.userId = userId;
		this.userDtl = userDtl;
		this.operDate = operDate;
		this.operItem = operItem;
		this.operType = operType;
		this.operDtl = operDtl;
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

	@Column(name = "userId", length = 36)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "userDtl", length = 50)
	public String getUserDtl() {
		return this.userDtl;
	}

	public void setUserDtl(String userDtl) {
		this.userDtl = userDtl;
	}

	@Column(name = "operDate", length = 20)
	public String getOperDate() {
		return this.operDate;
	}

	public void setOperDate(String operDate) {
		this.operDate = operDate;
	}

	@Column(name = "operItem", length = 50)
	public String getOperItem() {
		return this.operItem;
	}

	public void setOperItem(String operItem) {
		this.operItem = operItem;
	}

	@Column(name = "operType", length = 20)
	public String getOperType() {
		return this.operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	@Column(name = "operDtl", length = 200)
	public String getOperDtl() {
		return this.operDtl;
	}

	public void setOperDtl(String operDtl) {
		this.operDtl = operDtl;
	}

}