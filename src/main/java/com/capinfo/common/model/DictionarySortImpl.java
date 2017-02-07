package com.capinfo.common.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

import com.capinfo.framework.model.StatusEnabled;
import com.capinfo.framework.model.dictionary.Dictionary;
import com.capinfo.framework.model.dictionary.DictionarySort;

/**
 * 
 * <p>
 * 数据字典项目，如“性别”，“国籍”等。
 * </p>
 * 
 * <p>
 * 数据字典项目包括名称，描述，数据字典项目分组（<tt>DictionaryImpl</tt>）集合等
 * </p>
 * 
 * 
 * 
 * 
 */

@Entity
@Table(name = "DICTIONARY_SORTS")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@SequenceGenerator(allocationSize = 1, name = "seq", sequenceName = "SEQ_DICTIONARY_SORTS")
public class DictionarySortImpl implements DictionarySort {

	private Long id;

	// 字典分类code
	private String code;

	// 字典分类名称
	private String name;

	// 字典分类描述
	private String description;

	// 字典项
	private Set<Dictionary> dictionaries = new HashSet<Dictionary>();

	// 是否可用
	private Boolean enabled = StatusEnabled.STATUS_ACTIVE;

	// ===================Constructor==================
	/**
	 * 根据指定名称，构造相应的数据字典项目。
	 * 
	 * @param name
	 *            - 数据字典项目的名称
	 */
	public DictionarySortImpl(String name) {
		super();
		this.name = name;
	}

	public DictionarySortImpl() {
		super();
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

	@Column(name = "CODE", length = 100)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 返回数据字典项目的名称。
	 * 
	 * @return 数据字典项目的名称
	 */
	@Column(name = "NAME_", length = 100)
	public String getName() {
		return this.name;
	}

	/**
	 * 设置数据字典项目的名称。
	 * 
	 * @param name
	 *            - 数据字典项目的名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回数据字典项目的描述。
	 * 
	 * @return 数据字典项目的描述
	 */
	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription() {
		return description;
	}

	/**
	 * 设置数据字典项目的描述。
	 * 
	 * @param description
	 *            - 数据字典项目的描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 返回数据字典项目的相应分组集合，为unmodifiable set。
	 * 
	 * @return 数据字典项目分组集合
	 */
	@OneToMany(mappedBy = "sort", targetEntity = DictionaryImpl.class)
	@OrderBy(value = "position asc")
	@Where(clause = "enabled=1")
	public Set getDictionaries() {
		return Collections.unmodifiableSet(this.dictionaries);
	}

	/**
	 * 设置数据字典项目的相应分组集合。
	 * 
	 * @param dictionaryItems
	 *            - 数据字典项目的分组集合
	 */
	public void setDictionaries(Set<Dictionary> dictionaries) {
		// if (dictionaryItems == null)
		// return;

		// Iterator itemIterator = dictionaryItems.iterator();
		// while (itemIterator.hasNext()) {
		// DictionaryImpl dictionaryItem = (DictionaryImpl) itemIterator
		// .next();
		// addDictionaryItem(dictionaryItem);
		// }
		this.dictionaries = dictionaries;
	}

	/**
	 * 为数据字典项目添加相应分组。
	 * 
	 * @param dictionaryItem
	 *            - 数据字典项目分组
	 */
	public void addDictionaryItem(Dictionary dictionary) {
		if (dictionary == null) {
			throw new IllegalArgumentException("数据字典项目分组不能为空");
		}
		if (dictionary.getSort() != null) {
			// 数据字典项目分组不能重新绑定到其他数据字典项目中
			StringBuffer error = new StringBuffer().append("数据字典项目").append(dictionary.getSort().getName()).append("中已存在相应的分组名称：").append(dictionary.getName());
			throw new IllegalArgumentException(error.toString());
		}
		dictionary.setSort(this);
		this.dictionaries.add(dictionary);
	}

	@Column(name = "ENABLED")
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("id", getId()).append("name", getName()).append("description", getDescription()).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if (!(other instanceof DictionarySortImpl))
			return false;
		DictionarySortImpl otherDataDictionary = (DictionarySortImpl) other;
		return new EqualsBuilder().append(this.getName(), otherDataDictionary.getName()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getName()).toHashCode();
	}

}
