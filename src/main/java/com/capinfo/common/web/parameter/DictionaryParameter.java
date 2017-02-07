package com.capinfo.common.web.parameter;

import com.capinfo.framework.web.parameter.DataListParameter;

/**
 * 字典项参数模板
 */
public class DictionaryParameter extends DataListParameter {

	private String name;

	// type = "1" 表式对字典项分类进行操作 type = "2" 表式对字典项进行操作
	private String type = "1";

	private Long sortId;

	private String description;

	private String code;

	private Integer position;

	private boolean enabled = true;

	private String sortCode;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

}
