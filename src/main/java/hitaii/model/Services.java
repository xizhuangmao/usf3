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
 * Services entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "services", catalog = "usf3")
public class Services implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String price;
	private String note;
	private String scoreTimes;
	private String scoreTotle;
	private String discount;
	private String active;
	private String usersId;
	private String whesId;
	private String nvoccId;
	private String ids;
	private String companyId;
	private String indate;
	private Set<WhesdtlServices> whesdtlServiceses = new HashSet<WhesdtlServices>(
			0);

	// Constructors

	/** default constructor */
	public Services() {
	}

	/** minimal constructor */
	public Services(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Services(String id, String name, String price, String note,
			String scoreTimes, String scoreTotle, String discount,
			String active, String usersId, String whesId, String nvoccId,String indate,
			String ids, String companyId, Set<WhesdtlServices> whesdtlServiceses) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.note = note;
		this.scoreTimes = scoreTimes;
		this.scoreTotle = scoreTotle;
		this.discount = discount;
		this.active = active;
		this.usersId = usersId;
		this.whesId = whesId;
		this.nvoccId = nvoccId;
		this.ids = ids;
		this.companyId = companyId;
		this.whesdtlServiceses = whesdtlServiceses;
		this.indate =indate;
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

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "price", length = 20)
	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "note", length = 200)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "scoreTimes", length = 10)
	public String getScoreTimes() {
		return this.scoreTimes;
	}

	public void setScoreTimes(String scoreTimes) {
		this.scoreTimes = scoreTimes;
	}

	@Column(name = "scoreTotle", length = 20)
	public String getScoreTotle() {
		return this.scoreTotle;
	}

	public void setScoreTotle(String scoreTotle) {
		this.scoreTotle = scoreTotle;
	}

	@Column(name = "discount", length = 10)
	public String getDiscount() {
		return this.discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	@Column(name = "active", length = 10)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	@Column(name = "indate", length = 50)
	public String getIndate() {
		return this.indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	@Column(name = "usersId", length = 36)
	public String getUsersId() {
		return this.usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
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

	@Column(name = "ids", length = 65535)
	public String getIds() {
		return this.ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@Column(name = "companyId", length = 36)
	public String getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	@JSONField(serialize =false)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "services")
	public Set<WhesdtlServices> getWhesdtlServiceses() {
		return this.whesdtlServiceses;
	}

	public void setWhesdtlServiceses(Set<WhesdtlServices> whesdtlServiceses) {
		this.whesdtlServiceses = whesdtlServiceses;
	}

}