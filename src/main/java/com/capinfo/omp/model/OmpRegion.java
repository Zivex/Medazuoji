package com.capinfo.omp.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name = "OMP_REGION")
//@SequenceGenerator(name = "OmpRegion", sequenceName = "SEQ_OMP_REGION", allocationSize = 1)
public class OmpRegion {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OmpRegion")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	private BigDecimal id;
	@Column
    private String name;

    private BigDecimal levelid;

    private String description;

    private Date createdate;

    private BigDecimal creatorid;

    private BigDecimal parentid;

    private String num;

    private String shortname;

    private String disporder;

    private String standardNo;

    private Short useFlag;

    private String oldDictionaryId;

    private String convertId;

    private Long peopleNum;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getLevelid() {
        return levelid;
    }

    public void setLevelid(BigDecimal levelid) {
        this.levelid = levelid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public BigDecimal getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(BigDecimal creatorid) {
        this.creatorid = creatorid;
    }

    public BigDecimal getParentid() {
        return parentid;
    }

    public void setParentid(BigDecimal parentid) {
        this.parentid = parentid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname == null ? null : shortname.trim();
    }

    public String getDisporder() {
        return disporder;
    }

    public void setDisporder(String disporder) {
        this.disporder = disporder == null ? null : disporder.trim();
    }

    public String getStandardNo() {
        return standardNo;
    }

    public void setStandardNo(String standardNo) {
        this.standardNo = standardNo == null ? null : standardNo.trim();
    }

    public Short getUseFlag() {
        return useFlag;
    }

    public void setUseFlag(Short useFlag) {
        this.useFlag = useFlag;
    }

    public String getOldDictionaryId() {
        return oldDictionaryId;
    }

    public void setOldDictionaryId(String oldDictionaryId) {
        this.oldDictionaryId = oldDictionaryId == null ? null : oldDictionaryId.trim();
    }

    public String getConvertId() {
        return convertId;
    }

    public void setConvertId(String convertId) {
        this.convertId = convertId == null ? null : convertId.trim();
    }

    public Long getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Long peopleNum) {
        this.peopleNum = peopleNum;
    }
}