package com.capinfo.region.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
	@Column(name = "id", unique = true, nullable = false, precision = 12, scale = 0)
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "LEVELID")
    private int levelid;

	@Column(name = "DESCRIPTION")
    private String description;

	@Column(name = "CREATEDATE")
    private Date createdate;

	@Column(name = "CREATORID")
    private int creatorid;

	@Column(name = "PARENTID")
    private int parentid;

	@Column(name = "NUM")
    private String num;

	@Column(name = "SHORTNAME")
    private String shortname;

	@Column(name = "DISPORDER")
    private String disporder;

	@Column(name = "STANDARD_NO")
    private String standardNo;

	@Column(name = "USE_FLAG")
    private Short useFlag;

	@Column(name = "OLD_DICTIONARY_ID")
    private String oldDictionaryId;

	@Column(name = "CONVERT_ID")
    private int convertId;

	@Column(name = "PEOPLE_NUM")
    private int peopleNum;
	
	public static void sortList(List list, List<OmpRegion> sourcelist,
			int convertId) {
		for (int i = 0; i < sourcelist.size(); i++) {
			OmpRegion e = sourcelist.get(i);
			if (e.getParentid()==convertId) {
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j = 0; j < sourcelist.size(); j++) {
					OmpRegion child = sourcelist.get(j);
					if (child.getParentid()==e.getConvertId()) {
						sortList(list, sourcelist, e.getConvertId());
						break;
					}
				}
			}
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevelid() {
		return levelid;
	}

	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public int getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(int creatorid) {
		this.creatorid = creatorid;
	}

	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getDisporder() {
		return disporder;
	}

	public void setDisporder(String disporder) {
		this.disporder = disporder;
	}

	public String getStandardNo() {
		return standardNo;
	}

	public void setStandardNo(String standardNo) {
		this.standardNo = standardNo;
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
		this.oldDictionaryId = oldDictionaryId;
	}

	public int getConvertId() {
		return convertId;
	}

	public void setConvertId(int convertId) {
		this.convertId = convertId;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	
	
	
}