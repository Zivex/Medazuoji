package com.capinfo.serviceprovider.parameter;

import com.capinfo.framework.web.parameter.DataListParameter;

/**
 * 服务商参数模板
 */
public class ServiceProviderParameter extends DataListParameter {

	private String shopName;

	private String shopTelephone;
	
	private String serviceManager;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopTelephone() {
		return shopTelephone;
	}

	public void setShopTelephone(String shopTelephone) {
		this.shopTelephone = shopTelephone;
	}

	public String getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(String serviceManager) {
		this.serviceManager = serviceManager;
	}

}
