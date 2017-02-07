package com.capinfo.common.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.capinfo.framework.model.StatusEnabled;
import com.capinfo.framework.model.system.SecureRole;



@Entity
@Table(name = "ROLES")
//@SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "SEQ_ROLES")
public class Role implements StatusEnabled, SecureRole<Role, SystemUser, Resource> {

	private Long id;

	private String name;
	
	private String code;

	private String description;
	
	private Set<Resource> resources = new HashSet<Resource>();
	
	private Set<SystemUser> users = new HashSet<SystemUser>();

	private Boolean enabled=true;

	public Role() {
		super();
	}

	public Role(Long id) {
		super();
		this.id = id;
	}

	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name = "ROLE_NAME", length = 256)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	@Column(name = "ROLE_CODE", length = 256)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "DESCRIPTION", length = 512)
	public String getDescription() {
		return description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}


	@Column(name = "ENABLED")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

//	@ManyToMany(targetEntity=Resource.class,cascade=CascadeType.PERSIST, mappedBy="roles")
	@ManyToMany(targetEntity=Resource.class,cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)//解决FetchType.EAGER数据重复问题
	@JoinTable(name="RESOURCES_ROLES",
			joinColumns={@JoinColumn(name="ROLE_ID")},
			inverseJoinColumns={@JoinColumn(name="RESOURCE_ID")}
	)
	public Set<Resource> getResources() {
		return resources;
	}

	
	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	
	@ManyToMany(targetEntity=SystemUser.class, cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinTable(name="USER_ROLES",
			joinColumns={@JoinColumn(name="ROLE_ID")},
			inverseJoinColumns={@JoinColumn(name="USER_ID")}
	)
	public Set<SystemUser> getUsers() {

		return users;
	}

	public void setUsers(Set<SystemUser> users) {
		
		this.users = users;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	


}
