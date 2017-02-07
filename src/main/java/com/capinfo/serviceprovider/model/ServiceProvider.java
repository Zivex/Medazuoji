package com.capinfo.serviceprovider.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "SERVICE_PROVIDER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
//@SequenceGenerator(name = "OldMatch", sequenceName = "SEQ_OMP_OLD_MATCH", allocationSize = 1)
public class ServiceProvider implements Serializable{
	
	private static final long serialVersionUID = 8723112805122184479L;

	private String shopName;

	private String shopTelephone;
	
	private String serviceManager;
	
	private Long id;
	
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OldMatch")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SHOP_NAME", length = 100)
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "SHOP_TELEPHONE", length = 100)
	public String getShopTelephone() {
		return shopTelephone;
	}

	public void setShopTelephone(String shopTelephone) {
		this.shopTelephone = shopTelephone;
	}

	@Column(name = "SERVICE_MANAGER", length = 100)
	public String getServiceManager() {
		return serviceManager;
	}

	public void setServiceManager(String serviceManager) {
		this.serviceManager = serviceManager;
	}

	public ServiceProvider() {
		super();
	}
	
	public ServiceProvider(Long id) {
		super();
		this.id = id;
	}

}
