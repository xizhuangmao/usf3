package hitaii.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Resource entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "resource", catalog = "usf3")
public class Resource implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private String url;
	private String icon;
	private String pid;
	private Set<Role> roles = new HashSet<Role>(0);

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(String id, String name, String url) {
		this.id = id;
		this.name = name;
		this.url = url;
	}

	/** full constructor */
	public Resource(String id, String name, String url, String icon, String pid, Set<Role> roles) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.pid = pid;
		this.roles = roles;
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

	@Column(name = "url", nullable = false, length = 200)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "icon", length = 100)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "pid", length = 36)
	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource", schema = "", joinColumns = { @JoinColumn(name = "resourceId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) })
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}