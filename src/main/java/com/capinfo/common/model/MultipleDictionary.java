package com.capinfo.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.capinfo.framework.model.MultipleEntity;
import com.capinfo.framework.model.dictionary.Dictionary;

/**
 * <p>
 * 多选的字典表
 * </p>
 * 
 */
public class MultipleDictionary<T extends Dictionary> implements MultipleEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -418815331712501837L;
	
	private String handleName;
	private Object[] entityArray = new Object[0];
	private Long[] idArray = new Long[0];
	private String idString = null;
	private String nameString = "";
	private String nameSeparator = ", ";
	
	@SuppressWarnings("unchecked")
	private Class<T> entityType = (Class<T>) DictionaryImpl.class;
	
	
	public MultipleDictionary() {}
	
	public MultipleDictionary(String handleName) {
		
		this.handleName = handleName;
	}
	
	public MultipleDictionary(Class<T> entityType, String handleName) {
		
		this.entityType = entityType;
		this.handleName = handleName;
	}
	
	/*public MultipleDictionary(String idString) {
		this.idString = idString;
		
		formatItemIdStr();
	}*/
	
	public MultipleDictionary(String handleName, String idString) {
		
		this(handleName, idString, null);
	}
	
	public MultipleDictionary(String handleName, String idString, String nameSeparator) {
		
		this.handleName = handleName;
		this.idString = idString;
		
		if( StringUtils.isNotBlank(nameSeparator) ) {

			this.nameSeparator = nameSeparator;
		}
		
		formatItemIdStr();
	}
	
	private void formatItemIdStr() {
		
		if( StringUtils.isNotBlank(idString) ) {
			List<Long> tmpIds = new ArrayList<Long>();
			String[] itemIdStrArray = com.capinfo.framework.util.StringUtils.split(idString, ",", false, true);
			for(int i = 0, num = itemIdStrArray.length; i < num; i++) {
				
				tmpIds.add(new Long(itemIdStrArray[i]));
			}
			if( !tmpIds.isEmpty() ) {
				setIdArray((Long[]) tmpIds.toArray(new Long[tmpIds.size()]));
			}
		}
	}

	public Class<T> getEntityType() {
		
		return this.entityType;
	}

	public void setEntityType(Class<T> entityType) {
		
		this.entityType = entityType;
	}

	public String getHandleName() {
		
		return handleName;
	}

	public Object[] getEntityArray() {

		return this.entityArray;
	}

	public void setEntityArray(Object[] entityArray) {
		
		this.entityArray = entityArray;
		
		int num;
		if( 0 != (num = this.entityArray.length) ) {
			StringBuffer tmpName = new StringBuffer();
			for(int i = 0; i < num; i++) {
				tmpName.append(getNameSeparator())
					.append(((Dictionary) this.entityArray[i]).getName());
					//.append(System.getProperty("line.separator"));
			}
			String tmpNameStr = tmpName.toString();
			if( StringUtils.isNotBlank(tmpNameStr) ) {
				//itemName = "<pre>" + tmpItemName.toString().substring(2) + "</pre>";
				setNameString(tmpNameStr.substring(getNameSeparator().length()));
			}
		}
	}

	public Serializable[] getIdArray() {
		
		return idArray;
	}

	public void setIdArray(Serializable[] idArray) {
		
		this.idArray = (Long[]) idArray;
	}

	public String getIdString() {
		
		return idString;
	}

	public void setIdString(String idString) {
		
		this.idString = idString;
		formatItemIdStr();
	}

	public String getNameString() {
		
		return nameString;
	}

	public void setNameString(String nameString) {
		
		this.nameString = nameString;
	}

	public String getName() {
		
		return nameString;
	}

	public String getNameSeparator() {
		
		return nameSeparator;
	}

	public void setNameSeparator(String nameSeparator) {

		this.nameSeparator = nameSeparator;
	}
}
