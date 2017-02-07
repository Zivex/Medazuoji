package com.capinfo.omp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.capinfo.framework.model.BaseEntity;

@Entity
@Table(name = "OMP_OLD_MATCH")
//@SequenceGenerator(name = "OldMatch", sequenceName = "SEQ_OMP_OLD_MATCH", allocationSize = 1)
public class OmpOldMatch implements BaseEntity {

	/**
	 *老人信息ID 
	 */
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OldMatch")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	private Long id;
	/**
	 * 老人户籍信息表ID
	 */
	@Column(name = "EX_OLD_BOOKLET_ID")
	private Long exOldBookletId;
	/**
	 *老人活化登记信息表
	 */
	@Column(name = "EX_OLD_CREMATE_ID")
	private Long exOldCremateId;
	/**
	 *老人信息户口所属市 
	 */
	@Column(name = "HOUSEHOLD_CITY_ID")
	private Long householdCityId;
	/**
	 *老人信息户口所属区
	 */
	@Column(name = "HOUSEHOLD_COUNTY_ID")
	private Long householdCountyId;
	/**
	 *老人信息户口所属街道 
	 */
	@Column(name = "HOUSEHOLD_STREET_ID")
	private Long householdStreetId;
	/**
	 *老人信息户口所属社区 
	 */
	@Column(name = "HOUSEHOLD_COMMUNITY_ID")
	private Long householdCommunityId;
	/**
	 *老人信息户口所属地址
	 */
	@Column(name = "HOUSEHOLD_ADDRESS")
	private String householdAddress;
	/**
	 *老人信息居住所属市 
	 */
	@Column(name = "RESIDENCE_CITY_ID")
	private Long residenceCityId;
	/**
	 *老人信息居住所属区 
	 */
	@Column(name = "RESIDENCE_COUNTY_ID")
	private Long residenceCountyId;
	/**
	 *老人信息居住所属街道
	 */
	@Column(name = "RESIDENCE_STREET_ID")
	private Long residenceStreetId;
	/**
	 *老人信息居住所属社区 
	 */
	@Column(name = "")
	private Long residenceCommunityId;
	/**
	 *老人信息居住所属地址 
	 */
	@Column(name = "RESIDENCE_ADDRESS")
	private String residenceAddress;

	@Column(name = "CERTIFICATES_TYPE")
	private Long certificatesType;

	@Column(name = "CERTIFICATES_NUMBER")
	private String certificatesNumber;

	@Column(name = "NAME")
	private String name;

	@Column(name = "")
	private Long sex;

	@Column(name = "")
	private Long nation;

	@Column(name = "")
	private Date birthdate;

	@Column(name = "IS_DEATH")
	private Long isDeath;

	@Column(name = "DEATH_DATE")
	private Date deathDate;

	@Column(name = "REG_DEATH_DATE")
	private Date regDeathDate;

	@Column(name = "DEATH_DATA_SRC")
	private Long deathDataSrc;

	@Column(name = "CONTACTER")
	private String contacter;

	@Column(name = "CONTACTER_TEL")
	private String contacterTel;

	@Column(name = "CONTACTER_MOBILE")
	private Long contacterMobile;

	@Column(name = "")
	private String remark;

	@Column(name = "TEMP_SAVE_FLAG")
	private Short tempSaveFlag;

	@Column(name = "MODIFY_USER_ID")
	private Long modifyUserId;

	@Column(name = "MODIFY_DATE")
	private Date modifyDate;

	@Column(name = "MODIFY_COMMENTS")
	private String modifyComments;

	@Column(name = "CREATE_USER_ID")
	private Long createUserId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "USE_FLAG")
	private Short useFlag;

	@Column(name = "ADD_TYPE")
	private Long addType;

	@Column(name = "HOUSEHOLD_TYPE")
	private Long householdType;

	@Column(name = "HOUSEHOLD_NAME")
	private String householdName;

	@Column(name = "HOUSEHOLD_CARD_ID")
	private String householdCardId;

	@Column(name = "HOUSEHOLD_TEAM_NAME")
	private String householdTeamName;

	@Column(name = "HOUSEHOLD_RELATION")
	private Long householdRelation;

	@Column(name = "HOUSEHOLD_REGISTER_ORG")
	private String householdRegisterOrg;

	@Column(name = "MARRY_STATE")
	private Long marryState;

	@Column(name = "REGISTER_DATE")
	private Date registerDate;

	@Column(name = "DATE_FROM_QR")
	private String dateFromQr;

	@Column(name = "HOUSEHOLD_CANCEL_DATE")
	private Date householdCancelDate;

	@Column(name = "HOUSEHOLD_CANCEL_REASON")
	private String householdCancelReason;

	@Column(name = "HJ_ADDRESS")
	private String hjAddress;

	@Column(name = "DATE_FROM_TO_CITY")
	private String dateFromToCity;

	@Column(name = "SER_ADDRESS")
	private String serAddress;

	@Column(name = "HOUSEHOLD_CANCEL_TYPE")
	private Long householdCancelType;

	@Column(name = "ROLE_RELATION")
	private String roleRelation;

	@Column(name = "HOST_RELATION")
	private String hostRelation;

	@Column(name = "")
	private String age;

	@Column(name = "RPR_STATUS")
	private String rprStatus;

	@Column(name = "PRESENT_ADDR2")
	private String presentAddr2;

	@Column(name = "PRESENT_ADDR3")
	private String presentAddr3;

	@Column(name = "IS_COMM_PARTY")
	private String isCommParty;

	@Column(name = "HEALTH_TYPE")
	private String healthType;

	@Column(name = "PERSON_EMAIL")
	private String personEmail;

	@Column(name = "")
	private String commno;

	@Column(name = "")
	private String personno;

	@Column(name = "PRESENT_ADDR1")
	private String presentAddr1;

	@Column(name = "")
	private String rpraddr;

	@Column(name = "EX_OLD_BOOKLEFT_ID")
	private Long exOldBookleftId;

	@Column(name = "IS_CHECKED")
	private Short isChecked;

	@Column(name = "CHECK_USER_ID")
	private Long checkUserId;

	@Column(name = "OLD_TYPE")
	private Long oldType;

	@Column(name = "LAST_STATUS")
	private Short lastStatus;

	@Column(name = "BANK_CARD")
	private String bankCard;

	@Column(name = "IDCRAD_DEPT")
	private String idcradDept;

	@Column(name = "POSTAL_CODE")
	private String postalCode;

	@Column(name = "IDCRAD_ENDDATE")
	private Date idcradEnddate;

	@Column(name = "CONTACTER_MOBILE_NUMBER")
	private Long contacterMobileNumber;

	@Column(name = "CONTACTER_IDCARD_TYPE")
	private Long contacterIdcardType;

	@Column(name = "CONTACTER_IDCARD_NUMBER")
	private String contacterIdcardNumber;

	@Column(name = "EMERGENCY_CONTACTER_RELATION")
	private String emergencyContacterRelation;

	@Column(name = "NEED_OLD_SERVICE")
	private String needOldService;

	@Column(name = "BOOK_ID")
	private String bookId;

	@Column(name = "TREATMENT_MONEY")
	private Long treatmentMoney;

	@Column(name = "SX_HOUSEHOLD_CITY_ID")
	private Long sxHouseholdCityId;

	@Column(name = "SX_HOUSEHOLD_COUNTY_ID")
	private Long sxHouseholdCountyId;

	@Column(name = "SX_HOUSEHOLD_STREET_ID")
	private Long sxHouseholdStreetId;

	@Column(name = "SX_HOUSEHOLD_COMMUNITY_ID")
	private Long sxHouseholdCommunityId;

	@Column(name = "SX_RESIDENCE_CITY_ID")
	private Long sxResidenceCityId;

	@Column(name = "SX_RESIDENCE_COUNTY_ID")
	private Long sxResidenceCountyId;

	@Column(name = "SX_RESIDENCE_STREET_ID")
	private Long sxResidenceStreetId;

	@Column(name = "SX_RESIDENCE_COMMUNITY_ID")
	private Long sxResidenceCommunityId;

	@Column(name = "IS_NONLOCAL")
	private Short isNonlocal;

	@Column(name = "HAS_PUSHED")
	private Short hasPushed;

	@Column(name = "MAKE_CARD_SUCCESS")
	private Short makeCardSuccess;

	@Column(name = "FAILURE_REASON")
	private Short failureReason;

	@Column(name = "STATUS")
	private Short status;

	@Column(name = "EXTENSION_DATE")
	private Date extensionDate;

	@Column(name = "END_EXTENSION_DATE")
	private Date endExtensionDate;

	@Column(name = "NEWSPAPER_GET_WAY")
	private Long newspaperGetWay;

	@Column(name = "IMPORT_TYPE")
	private Long importType;

	@Column(name = "POPULARIZATION_KNOWLEDGE")
	private Long popularizationKnowledge;

	@Column(name = "MAIN_SOURCE_INCOME")
	private Long mainSourceIncome;

	@Column(name = "DISABILITY_CARD_NUMBER")
	private String disabilityCardNumber;

	@Column(name = "DISABILITY_TYPE")
	private Long disabilityType;

	@Column(name = "DISABILITY_LEVEL")
	private Long disabilityLevel;

	@Column(name = "DISABILITY_CARD_DATE_END")
	private Date disabilityCardDateEnd;

	@Column(name = "DISABILITY_CARD_DEPT")
	private String disabilityCardDept;

	@Column(name = "MEDICAL_SERVICE")
	private Long medicalService;

	@Column(name = "MEDICAL_SECURITY")
	private Long medicalSecurity;

	@Column(name = "LIMB_AUXILIARY_DEVICE")
	private Long limbAuxiliaryDevice;

	@Column(name = "VISION_AID")
	private Long visionAid;

	@Column(name = "HEARING_AID")
	private Long hearingAid;

	@Column(name = "PSYCHOLOGICAL_SERVICES")
	private Long psychologicalServices;

	@Column(name = "REFERRAL_SERVICE")
	private Long referralService;

	@Column(name = "IS_JOIN_LIMB_REHABILITATION")
	private Long isJoinLimbRehabilitation;

	@Column(name = "ACCEPTED_CATARACT_OPERATION")
	private Long acceptedCataractOperation;

	@Column(name = "HAS_JOB")
	private Long hasJob;

	@Column(name = "SUBSISTENCE_ALLOWANCE")
	private Long subsistenceAllowance;

	@Column(name = "INSURANCE_TYPE")
	private Long insuranceType;

	@Column(name = "GIVE_CONSIDERATION_TYPE")
	private Long giveConsiderationType;

	@Column(name = "SPECIAL_IDENTITY")
	private Long specialIdentity;

	@Column(name = "IS_ONLY_ONE_CHILD")
	private Long isOnlyOneChild;

	@Column(name = "HAS_ACCESSIBILITY_SETTINGS")
	private Long hasAccessibilitySettings;

	@Column(name = "HEALTH_STATUS")
	private Long healthStatus;

	@Column(name = "COMMON_AILMENT")
	private Long commonAilment;
	
	@Column(name = "SEND_CARD_DATE")
	private Date sendCardDate;
	
	@Column(name = "DEATH_PUSHED_STATUS")
	private Short deathPushedStatus;

	@Column(name = "CITY_PUSHED")
	private Short cityPushed;

	@Column(name = "CITY_PUSHED_DATE")
	private Date cityPushedDate;

	@Column(name = "PUSHED_DATE")
	private Date pushedDate;

	@Column(name = "REVOKE_CITY_PUSHED")
	private Short revokeCityPushed;

	@Column(name = "REVOKE_CITY_PUSHED_DATE	")
	private Date revokeCityPushedDate;

	@Column(name = "DEATH_PUSHED_DATE")
	private Date deathPushedDate;

	@Column(name = "BACK_IN_TIME")
	private Date backInTime;

	@Column(name = "DEATH_BACK_IN_TIME")
	private Date deathBackInTime;

	@Column(name = "DEATH_HAS_PUSHED")
	private Short deathHasPushed;

	@Column(name = "DEATH_SUCESS_DATE")
	private Date deathSucessDate;

	@Column(name = "BOOK_ID_OLD")
	private Long bookIdOld;

	@Column(name = "ONLINE_CHECK_RESULT")
	private Short onlineCheckResult;

	@Column(name = "ONLINE_FAILURE_REASON")
	private Short onlineFailureReason;

	@Column(name = "INFO_CHANGE_STATUS")
	private Short infoChangeStatus;

	@Column(name = "BJT_NUMBER")
	private String bjtNumber;

	@Column(name = "YSKH_NUMBER")
	private String yskhNumber;

	@Column(name = "YKT_NUMBER")
	private String yktNumber;

	@Column(name = "YKT_STATUS")
	private Short yktStatus;

	@Column(name = "BANK_STATUS")
	private Short bankStatus;

	@Column(name = "YKT_DATE")
	private Date yktDate;

	@Column(name = "BANK_DATE")
	private Date bankDate;

	@Column(name = "RESTORE_HAS_PUSHED")
	private Short restoreHasPushed;

	@Column(name = "RESTORE_DATE")
	private Date restoreDate;

	@Column(name = "XBS_ADDRESS")
	private String xbsAddress;

	@Column(name = "HC_HAS_PUSHED")
	private Short hcHasPushed;

	@Column(name = "HC_PUSHED_DATE")
	private Date hcPushedDate;

	@Column(name = "HC_SUCCESS")
	private Short hcSuccess;

	@Column(name = "HC_BACK_IN_TIME")
	private Date hcBackInTime;

	@Column(name = "HC_REASON")
	private Short hcReason;

	@Column(name = "AUDIT_STATE")
	private Long auditState;

	@Column(name = "AUDIT_REMARK")
	private String auditRemark;

	@Column(name = "AUDIT_DATE")
	private Date auditDate;

	@Column(name = "HC_NUM")
	private Long hcNum;

	@Column(name = "ZK_NUM")
	private Long zkNum;

	@Column(name = "RE_MODIFY_COMMENT")
	private String reModifyComment;

	@Column(name = "REMARK_ZG")
	private String remarkZg;

	@Column(name = "DATE_COME_BEIJING")
	private Date dateComeBeijing;

	@Column(name = "RESIDENCE_PERMIT_START")
	private Date residencePermitStart;

	@Column(name = "RESIDENCE_PERMIT_END")
	private Date residencePermitEnd;

	@Column(name = "IS_YL")
	private Short isYl;

	@Column(name = "IS_SN")
	private Short isSn;

	@Column(name = "IS_XJ")
	private Short isXj;

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long arg0) {
		// TODO Auto-generated method stub

	}

	public Long getExOldBookletId() {
		return exOldBookletId;
	}

	public void setExOldBookletId(Long exOldBookletId) {
		this.exOldBookletId = exOldBookletId;
	}

	public Long getExOldCremateId() {
		return exOldCremateId;
	}

	public void setExOldCremateId(Long exOldCremateId) {
		this.exOldCremateId = exOldCremateId;
	}

	public Long getHouseholdCityId() {
		return householdCityId;
	}

	public void setHouseholdCityId(Long householdCityId) {
		this.householdCityId = householdCityId;
	}

	public Long getHouseholdCountyId() {
		return householdCountyId;
	}

	public void setHouseholdCountyId(Long householdCountyId) {
		this.householdCountyId = householdCountyId;
	}

	public Long getHouseholdStreetId() {
		return householdStreetId;
	}

	public void setHouseholdStreetId(Long householdStreetId) {
		this.householdStreetId = householdStreetId;
	}

	public Long getHouseholdCommunityId() {
		return householdCommunityId;
	}

	public void setHouseholdCommunityId(Long householdCommunityId) {
		this.householdCommunityId = householdCommunityId;
	}

	public String getHouseholdAddress() {
		return householdAddress;
	}

	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}

	public Long getResidenceCityId() {
		return residenceCityId;
	}

	public void setResidenceCityId(Long residenceCityId) {
		this.residenceCityId = residenceCityId;
	}

	public Long getResidenceCountyId() {
		return residenceCountyId;
	}

	public void setResidenceCountyId(Long residenceCountyId) {
		this.residenceCountyId = residenceCountyId;
	}

	public Long getResidenceStreetId() {
		return residenceStreetId;
	}

	public void setResidenceStreetId(Long residenceStreetId) {
		this.residenceStreetId = residenceStreetId;
	}

	public Long getResidenceCommunityId() {
		return residenceCommunityId;
	}

	public void setResidenceCommunityId(Long residenceCommunityId) {
		this.residenceCommunityId = residenceCommunityId;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public Long getCertificatesType() {
		return certificatesType;
	}

	public void setCertificatesType(Long certificatesType) {
		this.certificatesType = certificatesType;
	}

	public String getCertificatesNumber() {
		return certificatesNumber;
	}

	public void setCertificatesNumber(String certificatesNumber) {
		this.certificatesNumber = certificatesNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSex() {
		return sex;
	}

	public void setSex(Long sex) {
		this.sex = sex;
	}

	public Long getNation() {
		return nation;
	}

	public void setNation(Long nation) {
		this.nation = nation;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Long getIsDeath() {
		return isDeath;
	}

	public void setIsDeath(Long isDeath) {
		this.isDeath = isDeath;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date deathDate) {
		this.deathDate = deathDate;
	}

	public Date getRegDeathDate() {
		return regDeathDate;
	}

	public void setRegDeathDate(Date regDeathDate) {
		this.regDeathDate = regDeathDate;
	}

	public Long getDeathDataSrc() {
		return deathDataSrc;
	}

	public void setDeathDataSrc(Long deathDataSrc) {
		this.deathDataSrc = deathDataSrc;
	}

	public String getContacter() {
		return contacter;
	}

	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	public String getContacterTel() {
		return contacterTel;
	}

	public void setContacterTel(String contacterTel) {
		this.contacterTel = contacterTel;
	}

	public Long getContacterMobile() {
		return contacterMobile;
	}

	public void setContacterMobile(Long contacterMobile) {
		this.contacterMobile = contacterMobile;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getTempSaveFlag() {
		return tempSaveFlag;
	}

	public void setTempSaveFlag(Short tempSaveFlag) {
		this.tempSaveFlag = tempSaveFlag;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getModifyComments() {
		return modifyComments;
	}

	public void setModifyComments(String modifyComments) {
		this.modifyComments = modifyComments;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Short getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Short useFlag) {
		this.useFlag = useFlag;
	}

	public Long getAddType() {
		return addType;
	}

	public void setAddType(Long addType) {
		this.addType = addType;
	}

	public Long getHouseholdType() {
		return householdType;
	}

	public void setHouseholdType(Long householdType) {
		this.householdType = householdType;
	}

	public String getHouseholdName() {
		return householdName;
	}

	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}

	public String getHouseholdCardId() {
		return householdCardId;
	}

	public void setHouseholdCardId(String householdCardId) {
		this.householdCardId = householdCardId;
	}

	public String getHouseholdTeamName() {
		return householdTeamName;
	}

	public void setHouseholdTeamName(String householdTeamName) {
		this.householdTeamName = householdTeamName;
	}

	public Long getHouseholdRelation() {
		return householdRelation;
	}

	public void setHouseholdRelation(Long householdRelation) {
		this.householdRelation = householdRelation;
	}

	public String getHouseholdRegisterOrg() {
		return householdRegisterOrg;
	}

	public void setHouseholdRegisterOrg(String householdRegisterOrg) {
		this.householdRegisterOrg = householdRegisterOrg;
	}

	public Long getMarryState() {
		return marryState;
	}

	public void setMarryState(Long marryState) {
		this.marryState = marryState;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getDateFromQr() {
		return dateFromQr;
	}

	public void setDateFromQr(String dateFromQr) {
		this.dateFromQr = dateFromQr;
	}

	public Date getHouseholdCancelDate() {
		return householdCancelDate;
	}

	public void setHouseholdCancelDate(Date householdCancelDate) {
		this.householdCancelDate = householdCancelDate;
	}

	public String getHouseholdCancelReason() {
		return householdCancelReason;
	}

	public void setHouseholdCancelReason(String householdCancelReason) {
		this.householdCancelReason = householdCancelReason;
	}

	public String getHjAddress() {
		return hjAddress;
	}

	public void setHjAddress(String hjAddress) {
		this.hjAddress = hjAddress;
	}

	public String getDateFromToCity() {
		return dateFromToCity;
	}

	public void setDateFromToCity(String dateFromToCity) {
		this.dateFromToCity = dateFromToCity;
	}

	public String getSerAddress() {
		return serAddress;
	}

	public void setSerAddress(String serAddress) {
		this.serAddress = serAddress;
	}

	public Long getHouseholdCancelType() {
		return householdCancelType;
	}

	public void setHouseholdCancelType(Long householdCancelType) {
		this.householdCancelType = householdCancelType;
	}

	public String getRoleRelation() {
		return roleRelation;
	}

	public void setRoleRelation(String roleRelation) {
		this.roleRelation = roleRelation;
	}

	public String getHostRelation() {
		return hostRelation;
	}

	public void setHostRelation(String hostRelation) {
		this.hostRelation = hostRelation;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getRprStatus() {
		return rprStatus;
	}

	public void setRprStatus(String rprStatus) {
		this.rprStatus = rprStatus;
	}

	public String getPresentAddr2() {
		return presentAddr2;
	}

	public void setPresentAddr2(String presentAddr2) {
		this.presentAddr2 = presentAddr2;
	}

	public String getPresentAddr3() {
		return presentAddr3;
	}

	public void setPresentAddr3(String presentAddr3) {
		this.presentAddr3 = presentAddr3;
	}

	public String getIsCommParty() {
		return isCommParty;
	}

	public void setIsCommParty(String isCommParty) {
		this.isCommParty = isCommParty;
	}

	public String getHealthType() {
		return healthType;
	}

	public void setHealthType(String healthType) {
		this.healthType = healthType;
	}

	public String getPersonEmail() {
		return personEmail;
	}

	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}

	public String getCommno() {
		return commno;
	}

	public void setCommno(String commno) {
		this.commno = commno;
	}

	public String getPersonno() {
		return personno;
	}

	public void setPersonno(String personno) {
		this.personno = personno;
	}

	public String getPresentAddr1() {
		return presentAddr1;
	}

	public void setPresentAddr1(String presentAddr1) {
		this.presentAddr1 = presentAddr1;
	}

	public String getRpraddr() {
		return rpraddr;
	}

	public void setRpraddr(String rpraddr) {
		this.rpraddr = rpraddr;
	}

	public Long getExOldBookleftId() {
		return exOldBookleftId;
	}

	public void setExOldBookleftId(Long exOldBookleftId) {
		this.exOldBookleftId = exOldBookleftId;
	}

	public Short getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Short isChecked) {
		this.isChecked = isChecked;
	}

	public Long getCheckUserId() {
		return checkUserId;
	}

	public void setCheckUserId(Long checkUserId) {
		this.checkUserId = checkUserId;
	}

	public Long getOldType() {
		return oldType;
	}

	public void setOldType(Long oldType) {
		this.oldType = oldType;
	}

	public Short getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(Short lastStatus) {
		this.lastStatus = lastStatus;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getIdcradDept() {
		return idcradDept;
	}

	public void setIdcradDept(String idcradDept) {
		this.idcradDept = idcradDept;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Date getIdcradEnddate() {
		return idcradEnddate;
	}

	public void setIdcradEnddate(Date idcradEnddate) {
		this.idcradEnddate = idcradEnddate;
	}

	public Long getContacterMobileNumber() {
		return contacterMobileNumber;
	}

	public void setContacterMobileNumber(Long contacterMobileNumber) {
		this.contacterMobileNumber = contacterMobileNumber;
	}

	public Long getContacterIdcardType() {
		return contacterIdcardType;
	}

	public void setContacterIdcardType(Long contacterIdcardType) {
		this.contacterIdcardType = contacterIdcardType;
	}

	public String getContacterIdcardNumber() {
		return contacterIdcardNumber;
	}

	public void setContacterIdcardNumber(String contacterIdcardNumber) {
		this.contacterIdcardNumber = contacterIdcardNumber;
	}

	public String getEmergencyContacterRelation() {
		return emergencyContacterRelation;
	}

	public void setEmergencyContacterRelation(String emergencyContacterRelation) {
		this.emergencyContacterRelation = emergencyContacterRelation;
	}

	public String getNeedOldService() {
		return needOldService;
	}

	public void setNeedOldService(String needOldService) {
		this.needOldService = needOldService;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public Long getTreatmentMoney() {
		return treatmentMoney;
	}

	public void setTreatmentMoney(Long treatmentMoney) {
		this.treatmentMoney = treatmentMoney;
	}

	public Long getSxHouseholdCityId() {
		return sxHouseholdCityId;
	}

	public void setSxHouseholdCityId(Long sxHouseholdCityId) {
		this.sxHouseholdCityId = sxHouseholdCityId;
	}

	public Long getSxHouseholdCountyId() {
		return sxHouseholdCountyId;
	}

	public void setSxHouseholdCountyId(Long sxHouseholdCountyId) {
		this.sxHouseholdCountyId = sxHouseholdCountyId;
	}

	public Long getSxHouseholdStreetId() {
		return sxHouseholdStreetId;
	}

	public void setSxHouseholdStreetId(Long sxHouseholdStreetId) {
		this.sxHouseholdStreetId = sxHouseholdStreetId;
	}

	public Long getSxHouseholdCommunityId() {
		return sxHouseholdCommunityId;
	}

	public void setSxHouseholdCommunityId(Long sxHouseholdCommunityId) {
		this.sxHouseholdCommunityId = sxHouseholdCommunityId;
	}

	public Long getSxResidenceCityId() {
		return sxResidenceCityId;
	}

	public void setSxResidenceCityId(Long sxResidenceCityId) {
		this.sxResidenceCityId = sxResidenceCityId;
	}

	public Long getSxResidenceCountyId() {
		return sxResidenceCountyId;
	}

	public void setSxResidenceCountyId(Long sxResidenceCountyId) {
		this.sxResidenceCountyId = sxResidenceCountyId;
	}

	public Long getSxResidenceStreetId() {
		return sxResidenceStreetId;
	}

	public void setSxResidenceStreetId(Long sxResidenceStreetId) {
		this.sxResidenceStreetId = sxResidenceStreetId;
	}

	public Long getSxResidenceCommunityId() {
		return sxResidenceCommunityId;
	}

	public void setSxResidenceCommunityId(Long sxResidenceCommunityId) {
		this.sxResidenceCommunityId = sxResidenceCommunityId;
	}

	public Short getIsNonlocal() {
		return isNonlocal;
	}

	public void setIsNonlocal(Short isNonlocal) {
		this.isNonlocal = isNonlocal;
	}

	public Short getHasPushed() {
		return hasPushed;
	}

	public void setHasPushed(Short hasPushed) {
		this.hasPushed = hasPushed;
	}

	public Short getMakeCardSuccess() {
		return makeCardSuccess;
	}

	public void setMakeCardSuccess(Short makeCardSuccess) {
		this.makeCardSuccess = makeCardSuccess;
	}

	public Short getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(Short failureReason) {
		this.failureReason = failureReason;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getExtensionDate() {
		return extensionDate;
	}

	public void setExtensionDate(Date extensionDate) {
		this.extensionDate = extensionDate;
	}

	public Date getEndExtensionDate() {
		return endExtensionDate;
	}

	public void setEndExtensionDate(Date endExtensionDate) {
		this.endExtensionDate = endExtensionDate;
	}

	public Long getNewspaperGetWay() {
		return newspaperGetWay;
	}

	public void setNewspaperGetWay(Long newspaperGetWay) {
		this.newspaperGetWay = newspaperGetWay;
	}

	public Long getImportType() {
		return importType;
	}

	public void setImportType(Long importType) {
		this.importType = importType;
	}

	public Long getPopularizationKnowledge() {
		return popularizationKnowledge;
	}

	public void setPopularizationKnowledge(Long popularizationKnowledge) {
		this.popularizationKnowledge = popularizationKnowledge;
	}

	public Long getMainSourceIncome() {
		return mainSourceIncome;
	}

	public void setMainSourceIncome(Long mainSourceIncome) {
		this.mainSourceIncome = mainSourceIncome;
	}

	public String getDisabilityCardNumber() {
		return disabilityCardNumber;
	}

	public void setDisabilityCardNumber(String disabilityCardNumber) {
		this.disabilityCardNumber = disabilityCardNumber;
	}

	public Long getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(Long disabilityType) {
		this.disabilityType = disabilityType;
	}

	public Long getDisabilityLevel() {
		return disabilityLevel;
	}

	public void setDisabilityLevel(Long disabilityLevel) {
		this.disabilityLevel = disabilityLevel;
	}

	public Date getDisabilityCardDateEnd() {
		return disabilityCardDateEnd;
	}

	public void setDisabilityCardDateEnd(Date disabilityCardDateEnd) {
		this.disabilityCardDateEnd = disabilityCardDateEnd;
	}

	public String getDisabilityCardDept() {
		return disabilityCardDept;
	}

	public void setDisabilityCardDept(String disabilityCardDept) {
		this.disabilityCardDept = disabilityCardDept;
	}

	public Long getMedicalService() {
		return medicalService;
	}

	public void setMedicalService(Long medicalService) {
		this.medicalService = medicalService;
	}

	public Long getMedicalSecurity() {
		return medicalSecurity;
	}

	public void setMedicalSecurity(Long medicalSecurity) {
		this.medicalSecurity = medicalSecurity;
	}

	public Long getLimbAuxiliaryDevice() {
		return limbAuxiliaryDevice;
	}

	public void setLimbAuxiliaryDevice(Long limbAuxiliaryDevice) {
		this.limbAuxiliaryDevice = limbAuxiliaryDevice;
	}

	public Long getVisionAid() {
		return visionAid;
	}

	public void setVisionAid(Long visionAid) {
		this.visionAid = visionAid;
	}

	public Long getHearingAid() {
		return hearingAid;
	}

	public void setHearingAid(Long hearingAid) {
		this.hearingAid = hearingAid;
	}

	public Long getPsychologicalServices() {
		return psychologicalServices;
	}

	public void setPsychologicalServices(Long psychologicalServices) {
		this.psychologicalServices = psychologicalServices;
	}

	public Long getReferralService() {
		return referralService;
	}

	public void setReferralService(Long referralService) {
		this.referralService = referralService;
	}

	public Long getIsJoinLimbRehabilitation() {
		return isJoinLimbRehabilitation;
	}

	public void setIsJoinLimbRehabilitation(Long isJoinLimbRehabilitation) {
		this.isJoinLimbRehabilitation = isJoinLimbRehabilitation;
	}

	public Long getAcceptedCataractOperation() {
		return acceptedCataractOperation;
	}

	public void setAcceptedCataractOperation(Long acceptedCataractOperation) {
		this.acceptedCataractOperation = acceptedCataractOperation;
	}

	public Long getHasJob() {
		return hasJob;
	}

	public void setHasJob(Long hasJob) {
		this.hasJob = hasJob;
	}

	public Long getSubsistenceAllowance() {
		return subsistenceAllowance;
	}

	public void setSubsistenceAllowance(Long subsistenceAllowance) {
		this.subsistenceAllowance = subsistenceAllowance;
	}

	public Long getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(Long insuranceType) {
		this.insuranceType = insuranceType;
	}

	public Long getGiveConsiderationType() {
		return giveConsiderationType;
	}

	public void setGiveConsiderationType(Long giveConsiderationType) {
		this.giveConsiderationType = giveConsiderationType;
	}

	public Long getSpecialIdentity() {
		return specialIdentity;
	}

	public void setSpecialIdentity(Long specialIdentity) {
		this.specialIdentity = specialIdentity;
	}

	public Long getIsOnlyOneChild() {
		return isOnlyOneChild;
	}

	public void setIsOnlyOneChild(Long isOnlyOneChild) {
		this.isOnlyOneChild = isOnlyOneChild;
	}

	public Long getHasAccessibilitySettings() {
		return hasAccessibilitySettings;
	}

	public void setHasAccessibilitySettings(Long hasAccessibilitySettings) {
		this.hasAccessibilitySettings = hasAccessibilitySettings;
	}

	public Long getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(Long healthStatus) {
		this.healthStatus = healthStatus;
	}

	public Long getCommonAilment() {
		return commonAilment;
	}

	public void setCommonAilment(Long commonAilment) {
		this.commonAilment = commonAilment;
	}

	public Date getSendCardDate() {
		return sendCardDate;
	}

	public void setSendCardDate(Date sendCardDate) {
		this.sendCardDate = sendCardDate;
	}

	public Short getDeathPushedStatus() {
		return deathPushedStatus;
	}

	public void setDeathPushedStatus(Short deathPushedStatus) {
		this.deathPushedStatus = deathPushedStatus;
	}

	public Short getCityPushed() {
		return cityPushed;
	}

	public void setCityPushed(Short cityPushed) {
		this.cityPushed = cityPushed;
	}

	public Date getCityPushedDate() {
		return cityPushedDate;
	}

	public void setCityPushedDate(Date cityPushedDate) {
		this.cityPushedDate = cityPushedDate;
	}

	public Date getPushedDate() {
		return pushedDate;
	}

	public void setPushedDate(Date pushedDate) {
		this.pushedDate = pushedDate;
	}

	public Short getRevokeCityPushed() {
		return revokeCityPushed;
	}

	public void setRevokeCityPushed(Short revokeCityPushed) {
		this.revokeCityPushed = revokeCityPushed;
	}

	public Date getRevokeCityPushedDate() {
		return revokeCityPushedDate;
	}

	public void setRevokeCityPushedDate(Date revokeCityPushedDate) {
		this.revokeCityPushedDate = revokeCityPushedDate;
	}

	public Date getDeathPushedDate() {
		return deathPushedDate;
	}

	public void setDeathPushedDate(Date deathPushedDate) {
		this.deathPushedDate = deathPushedDate;
	}

	public Date getBackInTime() {
		return backInTime;
	}

	public void setBackInTime(Date backInTime) {
		this.backInTime = backInTime;
	}

	public Date getDeathBackInTime() {
		return deathBackInTime;
	}

	public void setDeathBackInTime(Date deathBackInTime) {
		this.deathBackInTime = deathBackInTime;
	}

	public Short getDeathHasPushed() {
		return deathHasPushed;
	}

	public void setDeathHasPushed(Short deathHasPushed) {
		this.deathHasPushed = deathHasPushed;
	}

	public Date getDeathSucessDate() {
		return deathSucessDate;
	}

	public void setDeathSucessDate(Date deathSucessDate) {
		this.deathSucessDate = deathSucessDate;
	}

	public Long getBookIdOld() {
		return bookIdOld;
	}

	public void setBookIdOld(Long bookIdOld) {
		this.bookIdOld = bookIdOld;
	}

	public Short getOnlineCheckResult() {
		return onlineCheckResult;
	}

	public void setOnlineCheckResult(Short onlineCheckResult) {
		this.onlineCheckResult = onlineCheckResult;
	}

	public Short getOnlineFailureReason() {
		return onlineFailureReason;
	}

	public void setOnlineFailureReason(Short onlineFailureReason) {
		this.onlineFailureReason = onlineFailureReason;
	}

	public Short getInfoChangeStatus() {
		return infoChangeStatus;
	}

	public void setInfoChangeStatus(Short infoChangeStatus) {
		this.infoChangeStatus = infoChangeStatus;
	}

	public String getBjtNumber() {
		return bjtNumber;
	}

	public void setBjtNumber(String bjtNumber) {
		this.bjtNumber = bjtNumber;
	}

	public String getYskhNumber() {
		return yskhNumber;
	}

	public void setYskhNumber(String yskhNumber) {
		this.yskhNumber = yskhNumber;
	}

	public String getYktNumber() {
		return yktNumber;
	}

	public void setYktNumber(String yktNumber) {
		this.yktNumber = yktNumber;
	}

	public Short getYktStatus() {
		return yktStatus;
	}

	public void setYktStatus(Short yktStatus) {
		this.yktStatus = yktStatus;
	}

	public Short getBankStatus() {
		return bankStatus;
	}

	public void setBankStatus(Short bankStatus) {
		this.bankStatus = bankStatus;
	}

	public Date getYktDate() {
		return yktDate;
	}

	public void setYktDate(Date yktDate) {
		this.yktDate = yktDate;
	}

	public Date getBankDate() {
		return bankDate;
	}

	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}

	public Short getRestoreHasPushed() {
		return restoreHasPushed;
	}

	public void setRestoreHasPushed(Short restoreHasPushed) {
		this.restoreHasPushed = restoreHasPushed;
	}

	public Date getRestoreDate() {
		return restoreDate;
	}

	public void setRestoreDate(Date restoreDate) {
		this.restoreDate = restoreDate;
	}

	public String getXbsAddress() {
		return xbsAddress;
	}

	public void setXbsAddress(String xbsAddress) {
		this.xbsAddress = xbsAddress;
	}

	public Short getHcHasPushed() {
		return hcHasPushed;
	}

	public void setHcHasPushed(Short hcHasPushed) {
		this.hcHasPushed = hcHasPushed;
	}

	public Date getHcPushedDate() {
		return hcPushedDate;
	}

	public void setHcPushedDate(Date hcPushedDate) {
		this.hcPushedDate = hcPushedDate;
	}

	public Short getHcSuccess() {
		return hcSuccess;
	}

	public void setHcSuccess(Short hcSuccess) {
		this.hcSuccess = hcSuccess;
	}

	public Date getHcBackInTime() {
		return hcBackInTime;
	}

	public void setHcBackInTime(Date hcBackInTime) {
		this.hcBackInTime = hcBackInTime;
	}

	public Short getHcReason() {
		return hcReason;
	}

	public void setHcReason(Short hcReason) {
		this.hcReason = hcReason;
	}

	public Long getAuditState() {
		return auditState;
	}

	public void setAuditState(Long auditState) {
		this.auditState = auditState;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public Date getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public Long getHcNum() {
		return hcNum;
	}

	public void setHcNum(Long hcNum) {
		this.hcNum = hcNum;
	}

	public Long getZkNum() {
		return zkNum;
	}

	public void setZkNum(Long zkNum) {
		this.zkNum = zkNum;
	}

	public String getReModifyComment() {
		return reModifyComment;
	}

	public void setReModifyComment(String reModifyComment) {
		this.reModifyComment = reModifyComment;
	}

	public String getRemarkZg() {
		return remarkZg;
	}

	public void setRemarkZg(String remarkZg) {
		this.remarkZg = remarkZg;
	}

	public Date getDateComeBeijing() {
		return dateComeBeijing;
	}

	public void setDateComeBeijing(Date dateComeBeijing) {
		this.dateComeBeijing = dateComeBeijing;
	}

	public Date getResidencePermitStart() {
		return residencePermitStart;
	}

	public void setResidencePermitStart(Date residencePermitStart) {
		this.residencePermitStart = residencePermitStart;
	}

	public Date getResidencePermitEnd() {
		return residencePermitEnd;
	}

	public void setResidencePermitEnd(Date residencePermitEnd) {
		this.residencePermitEnd = residencePermitEnd;
	}

	public Short getIsYl() {
		return isYl;
	}

	public void setIsYl(Short isYl) {
		this.isYl = isYl;
	}

	public Short getIsSn() {
		return isSn;
	}

	public void setIsSn(Short isSn) {
		this.isSn = isSn;
	}

	public Short getIsXj() {
		return isXj;
	}

	public void setIsXj(Short isXj) {
		this.isXj = isXj;
	}
	
	

}
