package com.capinfo.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.capinfo.framework.model.StatusEnabled;
import com.capinfo.framework.model.dictionary.Dictionary;
import com.capinfo.framework.model.dictionary.DictionarySort;

/**
 * 
 * <p>
 * 数据字典项目分组，如<tt>DictionarySortImpl</tt>中“性别”项目中定义的“男”、“女”等。
 * </p>
 * 
 * <p>
 * 数据字典项目分组包括名称、描述、编码、数据字典项目（<tt>DictionarySortImpl</tt>）等
 * </p>
 * 
 * 
 * 
 */

@Entity
@Table(name = "DICTIONARIES")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "SEQ_DICTIONARIES")
public class DictionaryImpl implements Dictionary, Comparable {

	private Long id;

	// 字典项名称
	private String name;

	// 字典项标准code（相当于别名）
	private String code;

	// 字典项描述
	private String description;

	// 字典项位置（字典项列表排序用）
	private Integer position = Integer.valueOf(0);

	// 字典项分类
	private Long sortId;
	private DictionarySort sort;

	// 是否可用
	private Boolean enabled = StatusEnabled.STATUS_ACTIVE;

	// ===================Constructor==================
	public DictionaryImpl() {
		super();
	}

	/**
	 * 根据指定的ID构造相应的数据字典项目分组， 一般用于给对象赋值时的实例化。
	 * 
	 * @param id
	 *            - 字典表ID
	 */
	public DictionaryImpl(Long id) {
		super();

		this.id = id;
	}

	/**
	 * 根据指定的名称和编码构造相应的数据字典项目分组。
	 * 
	 * @param name
	 *            - 数据字典项目分组的名称
	 * @param code
	 *            - 数据字典项目分组的编码
	 */
	public DictionaryImpl(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	/**
	 * 根据指定的名称、编码、描述和排序顺序构造相应的数据字典项目分组。
	 * 
	 * @param name
	 *            - 数据字典项目分组的名称
	 * @param code
	 *            - 数据字典项目分组的编码
	 * @param description
	 *            - 数据字典项目分组的描述
	 * @param position
	 *            - 数据字典项目分组的排序顺序
	 */
	public DictionaryImpl(String name, String code, String description, Integer position) {
		super();

		this.name = name;
		this.code = code;
		this.description = description;
		this.position = position;

	}

	public DictionaryImpl(String name) {
		this.name = name;
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

	/**
	 * 返回数据字典项目分组的名称。
	 * 
	 * @return 数据字典项目分组的名称
	 */
	@Column(name = "NAME_", length = 100)
	public String getName() {
		return this.name;
	}

	/**
	 * 设置数据字典项目分组的名称。
	 * 
	 * @param name
	 *            - 数据字典项目分组的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回数据字典项目分组的编码。
	 * 
	 * @return 数据字典项目分组。
	 */
	@Column(name = "CODE", length = 100)
	public String getCode() {
		return code;
	}

	/**
	 * 设置数据字典项目分组的编码（通常为各种相关标准中的编码）。
	 * 
	 * @param code
	 *            - 数据字典项目分组的编码.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "POSITION", precision = 3, scale = 0)
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	/**
	 * 返回数据字典项目分组的描述。
	 * 
	 * @return 数据字典项目分组的描述
	 */
	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return this.description;
	}

	/**
	 * 设置数据字典项目分组的描述。
	 * 
	 * @param description
	 *            - 数据字典项目分组的描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回数据字典项目分组所对应的字典项目。
	 * 
	 * @return 数据字典项目分组所对应的字典项目
	 */

	@Column(name = "SORT_ID")
	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	@ManyToOne(targetEntity = DictionarySortImpl.class)
	@JoinColumn(name = "SORT_ID", insertable = false, updatable = false)
	public DictionarySort getSort() {
		return this.sort;
	}

	/**
	 * <p>
	 * 设置数据字典项目分组所对应的字典项目。
	 * </p>
	 * 
	 * <p>
	 * 注意：client应该从数据字典项目中添加相应的分组或设置分组集合， 因此这里的访问权限设为package access。
	 * </p>
	 * 
	 * @param dataDictionary
	 *            - 数据字典项目分组所对应的字典项目
	 */
	public void setSort(DictionarySort sort) {
		this.sort = sort;
	}

	@Column(name = "ENABLED")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	// ================重写toString等方法=================
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("code", getCode()).append("name", this.getName()).toString();
	}

	public boolean equals(Object other) {
		if ((this == other)) {

			return true;
		}
		if (!(other instanceof DictionaryImpl)) {

			return false;
		}

		DictionaryImpl otherDictionaryItem = (DictionaryImpl) other;
		return new EqualsBuilder().append(this.getName(), otherDictionaryItem.getName()).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}

	public int compareTo(Object o) {
		// if ( !(other instanceof DictionaryImpl) ) return false;
		DictionaryImpl object = (DictionaryImpl) o;
		return new CompareToBuilder().append(this.getPosition(), object.getPosition()).toComparison();
	}
}
