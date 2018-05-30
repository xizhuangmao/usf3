package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * News entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "news", catalog = "usf3")
public class News implements java.io.Serializable {

	// Fields

	private String id;
	private String types;
	private String title;
	private String content;
	private String usersId;
	private String newsdate;

	// Constructors

	/** default constructor */
	public News() {
	}

	/** minimal constructor */
	public News(String id) {
		this.id = id;
	}

	/** full constructor */
	public News(String id, String types, String title, String content, String usersId, String newsdate) {
		this.id = id;
		this.types = types;
		this.title = title;
		this.content = content;
		this.usersId = usersId;
		this.newsdate = newsdate;
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

	@Column(name = "title", length = 100)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "usersId", length = 36)
	public String getUsersId() {
		return this.usersId;
	}

	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}

	@Column(name = "newsdate", length = 20)
	public String getNewsdate() {
		return this.newsdate;
	}

	public void setNewsdate(String newsdate) {
		this.newsdate = newsdate;
	}

}