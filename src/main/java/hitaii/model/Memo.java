package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Memo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "memo", catalog = "usf3")
public class Memo implements java.io.Serializable {

	// Fields

	private String id;
	private String types;
	private String usersId;
	private String users;
	private String content;
	private String memodate;
	private String booknumId;
	private String booknum;
	private String vin;
	private String uoo;
	private String whesId;
	private String nvoccId;

	// Constructors

	/** default constructor */
	public Memo() {
	}

	/** minimal constructor */
	public Memo(String id) {
		this.id = id;
	}

	/** full constructor */
	public Memo(String id, String types, String usersId, String users, String content, String memodate, String booknumId, String booknum, String vin, String uoo, String whesId, String nvoccId) {
		this.id = id;
		this.types = types;
		this.usersId = usersId;
		this.users = users;
		this.content = content;
		this.memodate = memodate;
		this.booknumId = booknumId;
		this.booknum = booknum;
		this.vin = vin;
		this.uoo = uoo;
		this.whesId = whesId;
		this.nvoccId = nvoccId;
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

	@Column(name = "types", length = 10)
	public String getTypes() {
		return this.types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Column(name = "usersId", length = 36)
	public String getUsersId() {
		return this.usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	@Column(name = "users", length = 200)
	public String getUsers() {
		return this.users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "memodate", length = 20)
	public String getMemodate() {
		return this.memodate;
	}

	public void setMemodate(String memodate) {
		this.memodate = memodate;
	}

	@Column(name = "booknumId", length = 36)
	public String getBooknumId() {
		return this.booknumId;
	}

	public void setBooknumId(String booknumId) {
		this.booknumId = booknumId;
	}

	@Column(name = "booknum", length = 50)
	public String getBooknum() {
		return this.booknum;
	}

	public void setBooknum(String booknum) {
		this.booknum = booknum;
	}

	@Column(name = "vin", length = 20)
	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "uoo", length = 20)
	public String getUoo() {
		return this.uoo;
	}

	public void setUoo(String uoo) {
		this.uoo = uoo;
	}

	@Column(name = "whesId", length = 36)
	public String getWhesId() {
		return this.whesId;
	}

	public void setWhesId(String whesId) {
		this.whesId = whesId;
	}

	@Column(name = "nvoccId", length = 36)
	public String getNvoccId() {
		return this.nvoccId;
	}

	public void setNvoccId(String nvoccId) {
		this.nvoccId = nvoccId;
	}

}