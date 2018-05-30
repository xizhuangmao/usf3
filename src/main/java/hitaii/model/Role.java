package hitaii.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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
 * Role entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "role", catalog = "usf3")
public class Role implements java.io.Serializable {

	// Fields

	private String id;
	private String name;
	private Set<Resource> resources = new HashSet<Resource>(0);
	private Set<Users> userses = new HashSet<Users>(0);

	// Constructors

	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public Role(String id, String name, Set<Resource> resources, Set<Users> userses) {
		this.id = id;
		this.name = name;
		this.resources = resources;
		this.userses = userses;
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

	@JSONField(serialize =false)
	@ManyToMany( fetch = FetchType.LAZY)
	@JoinTable(name = "role_resource", schema = "", joinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resourceId", nullable = false, updatable = false) })
	public Set<Resource> getResources() {
		return this.resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	@JSONField(serialize =false)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_role", schema = "", joinColumns = { @JoinColumn(name = "roleId", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "usersId", nullable = false, updatable = false) })
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}