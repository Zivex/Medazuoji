package com.capinfo.region.parameter;

import com.capinfo.framework.web.parameter.DataListParameter;

/**
 * 区划参数模板
 */
public class OmpRegionParameter extends DataListParameter {

	private String name;

	private String standardNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStandardNo() {
		return standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
	}
	
}
