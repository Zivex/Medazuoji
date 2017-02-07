package com.capinfo.omp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.capinfo.framework.model.BaseEntity;

@Entity
@Table(name = "OMP_OLD_INFO")
//@SequenceGenerator(name = "OmpOldInfo", sequenceName = "SEQ_OMP_OLD_INFO", allocationSize = 1)
public class OmpOldInfo implements BaseEntity {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OmpOldInfo")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long id;
	@Column
	private String householdCountyId;

	private String householdStreetId;

	private String householdCommunityId;

	private String workername;

	private String workertel;

	private String name;

	private String certificatesNumber;

	private String zjNumber;

	private String phone;

	private String address;

	private String emergencycontact;

	private String emergencycontacttle;

	private String teltype;
	
	private String creationTime;

	public OmpOldInfo() {
		super();
	}

	public String getZjNumber() {
		return zjNumber;
	}

	public void setZjNumber(String zjNumber) {
		this.zjNumber = zjNumber;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}

	public OmpOldInfo(String householdCountyId, String householdStreetId, String householdCommunityId,
			String workername, String workertel, String name, String certificatesNumber, String zjNumber, String phone,
			 String emergencycontact, String emergencycontacttle, String teltype,
			String address) {
		super();
		this.householdCountyId = householdCountyId;
		this.householdStreetId = householdStreetId;
		this.householdCommunityId = householdCommunityId;
		this.workername = workername;
		this.workertel = workertel;
		this.name = name;
		this.certificatesNumber = certificatesNumber;
		this.zjNumber = zjNumber;
		this.phone = phone;
		this.emergencycontact = emergencycontact;
		this.emergencycontacttle = emergencycontacttle;
		this.teltype = teltype;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHouseholdCountyId() {
		return householdCountyId;
	}

	public void setHouseholdCountyId(String householdCountyId) {
		this.householdCountyId = householdCountyId == null ? null : householdCountyId.trim();
	}

	public String getHouseholdStreetId() {
		return householdStreetId;
	}

	public void setHouseholdStreetId(String householdStreetId) {
		this.householdStreetId = householdStreetId == null ? null : householdStreetId.trim();
	}

	public String getHouseholdCommunityId() {
		return householdCommunityId;
	}

	public void setHouseholdCommunityId(String householdCommunityId) {
		this.householdCommunityId = householdCommunityId == null ? null : householdCommunityId.trim();
	}

	public String getWorkername() {
		return workername;
	}

	public void setWorkername(String workername) {
		this.workername = workername == null ? null : workername.trim();
	}

	public String getWorkertel() {
		return workertel;
	}

	public void setWorkertel(String workertel) {
		this.workertel = workertel == null ? null : workertel.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getCertificatesNumber() {
		return certificatesNumber;
	}

	public void setCertificatesNumber(String certificatesNumber) {
		this.certificatesNumber = certificatesNumber == null ? null : certificatesNumber.trim();
	}

	public String getzjNumber() {
		return zjNumber;
	}

	public void setzjNumber(String zjNumber) {
		this.zjNumber = zjNumber == null ? null : zjNumber.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getEmergencycontact() {
		return emergencycontact;
	}

	public void setEmergencycontact(String emergencycontact) {
		this.emergencycontact = emergencycontact == null ? null : emergencycontact.trim();
	}

	public String getEmergencycontacttle() {
		return emergencycontacttle;
	}

	public void setEmergencycontacttle(String emergencycontacttle) {
		this.emergencycontacttle = emergencycontacttle == null ? null : emergencycontacttle.trim();
	}

	public String getTeltype() {
		return teltype;
	}

	public void setTeltype(String teltype) {
		this.teltype = teltype == null ? null : teltype.trim();
	}


	@Override
	public String toString() {
		return "OmpOldInfo [householdCountyId=" + householdCountyId + ", householdStreetId=" + householdStreetId
				+ ", householdCommunityId=" + householdCommunityId + ", workername=" + workername + ", workertel="
				+ workertel + ", name=" + name + ", certificatesNumber=" + certificatesNumber + ", zjNumber=" + zjNumber
				+ ", phone=" + phone + ", address=" + address + ", emergencycontact=" + emergencycontact
				+ ", emergencycontacttle=" + emergencycontacttle + ", teltype=" + teltype + "]";
	}
	
	

}