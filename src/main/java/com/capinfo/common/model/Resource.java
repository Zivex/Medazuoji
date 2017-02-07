package com.capinfo.common.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.capinfo.framework.model.PositionalEntity;
import com.capinfo.framework.model.system.SecureResource;

@Entity
@Table(name = "RESOURCES")
//@SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "SEQ_RESOURCES")
public class Resource implements SecureResource<Resource, Role>, PositionalEntity {

	private static final long serialVersionUID = 6568539780208274614L;

	public static final Long ROOT_PARENT = 1L;

	private Long id;

	private Long parentId;

	private String name;

	private String type;

	private String value;

	private Integer position;
	
	private String classId;
	
	private String menuId;
	
	private Set<Role> roles = new HashSet<Role>();

	private Resource parent;

	private List<Resource> children = new ArrayList<Resource>();

	private boolean display = true;

	private boolean enabled = true;

	private boolean checked = false;// 判断角色是否选中资源数

	private List<Resource> items = new ArrayList<Resource>();// 选中的子菜单

	public Resource() {
	}

	public Resource(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "RESOURCE_NAME", length = 128)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(targetEntity = Resource.class, cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID", insertable = false, updatable = false)
	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	@Column(name = "PARENT_ID", precision = 12, scale = 0)
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@OneToMany(targetEntity = Resource.class, cascade = CascadeType.ALL, mappedBy = "parent")
	@Where(clause = "enabled = 1")
	@OrderBy(value = "position asc")
	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	@Column(name = "POSITION", precision = 5, scale = 0)
	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
	
	@Column(name = "CLASS_ID")
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	@Column(name = "MENU_ID")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@Where(clause = "enabled = 1")
	@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	// 解决FetchType.EAGER数据重复问题
	@JoinTable(name = "RESOURCES_ROLES", joinColumns = { @JoinColumn(name = "RESOURCE_ID") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Column(name = "RESOURCE_TYPE", length = 20)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "RESOURCE_VALUE", length = 256)
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Column(name = "DISPLAY")
	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	@Column(name = "ENABLED")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Transient
	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Transient
	public List<Resource> getItems() {
		return items;
	}

	public void setItems(List<Resource> items) {
		this.items = items;
	}
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
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
		Resource other = (Resource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", parentId=" + parentId + ", name=" + name + ", type=" + type + ", value=" + value + ", position=" + position
				+ ", display=" + display + ", enabled=" + enabled + ", checked=" + checked + "]";
	}

	
}