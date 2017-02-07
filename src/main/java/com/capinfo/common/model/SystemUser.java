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
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.capinfo.framework.model.system.SecureUser;

@Entity
@Table(name = "USERS")
//@SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "SEQ_USERS")
public class SystemUser implements SecureUser<SystemUser, Role> {

	private Long id;

	private String logonName;

	private int userType;

	private String password;

	private String name;

	private String descritption;

	private Boolean enabled = true;

	private Set<Role> roles = new HashSet<Role>();

	public SystemUser() {
		super();
	}

	public SystemUser(Long id) {
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

	@Column(name = "USER_NAME", length = 256)
	public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	@Column(name = "USER_TYPE", length = 256)
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	@Column(name = "PASSWORD", length = 256)
	@NotEmpty(message = "密码不能为空!")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NAME_", length = 256)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION", length = 512)
	public String getDescritption() {
		return descritption;
	}

	public void setDescritption(String descritption) {
		this.descritption = descritption;
	}

	@Column(name = "ENABLED")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	// @OneToMany(targetEntity = UserRole.class, cascade = CascadeType.ALL,
	// fetch = FetchType.LAZY)
	// @JoinColumn(name = "USER_ID")
	// public List<UserRole> getUserRoles() {
	// return userRoles;
	// }
	//
	// public void setUserRoles(List<UserRole> userRoles) {
	// this.userRoles = userRoles;
	// }

	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	// 解决FetchType.EAGER数据重复问题
	@JoinTable(name = "USER_ROLES", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles
	 *            the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Transient
	public void updateUserInfo(SystemUser entity) {
		this.setName(entity.getName());
		this.setUserType(entity.getUserType());
		this.setRoles(entity.getRoles());
	}

}
