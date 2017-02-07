package com.capinfo.common.web.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.capinfo.common.model.DictionaryImpl;
import com.capinfo.common.model.DictionarySortImpl;
import com.capinfo.common.web.service.DicService;
import com.capinfo.common.web.service.DictionaryManageService;
import com.capinfo.framework.dao.SearchCriteria.OrderRow;
import com.capinfo.framework.dao.SearchCriteriaBuilder;
import com.capinfo.framework.dao.impl.restriction.RestrictionExpression;
import com.capinfo.framework.model.dictionary.Dictionary;
import com.capinfo.framework.model.dictionary.DictionarySort;
import com.capinfo.framework.web.service.impl.CommonsDataOperationServiceImpl;

public class DicServiceImpl extends CommonsDataOperationServiceImpl implements DicService {

	private static final Log logger = LogFactory.getLog(DictionaryManageService.class);

	@Override
	public List<Dictionary> getDictionariesBySort(String sortName) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.name", RestrictionExpression.EQUALS_OP, sortName);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		return dictionaries;
	}

	@Override
	public List<Dictionary> getDictionariesBySortCode(String sortCode) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.code", RestrictionExpression.EQUALS_OP, sortCode);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		return dictionaries;
	}

	@Override
	public List<Dictionary> getDictionariesBySortId(Long sortId) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.id", RestrictionExpression.EQUALS_OP, sortId);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		return dictionaries;
	}

	@Override
	public Dictionary geDictionaryBySortName(String sortName, String itemName) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.name", RestrictionExpression.EQUALS_OP, sortName);
		searchCriteriaBuilder.addQueryCondition("name", RestrictionExpression.EQUALS_OP, itemName);
		Dictionary dictionary = getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		return dictionary;
	}

	@Override
	public Dictionary geDictionaryBySortId(Long sortId, String itemName) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.id", RestrictionExpression.EQUALS_OP, sortId);
		searchCriteriaBuilder.addQueryCondition("name", RestrictionExpression.EQUALS_OP, itemName);
		Dictionary dictionary = getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		return dictionary;
	}

	@Override
	public Dictionary geDictionaryBySortIdCode(Long sortId, String itemCode) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.id", RestrictionExpression.EQUALS_OP, sortId);
		searchCriteriaBuilder.addQueryCondition("code", RestrictionExpression.EQUALS_OP, itemCode);
		Dictionary dictionary = getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		return dictionary;
	}

	@Override
	public Dictionary geDictionaryByid(Long id) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("id", RestrictionExpression.EQUALS_OP, id);
		Dictionary dictionary = getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		return dictionary;
	}

	@Override
	public HashMap<Long, String> getDictionaryMapBySortName(String sortName) {
		LinkedHashMap<Long, String> dicMap = new LinkedHashMap<Long, String>();
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.name", RestrictionExpression.EQUALS_OP, sortName);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		if (null != dictionaries) {
			for (Dictionary dictionary : dictionaries) {
				dicMap.put(dictionary.getId(), dictionary.getName());
			}
		}
		return dicMap;
	}

	@Override
	public HashMap<String, String> getDictionaryCodeMapBySortName(String sortName) {
		LinkedHashMap<String, String> dicMap = new LinkedHashMap<String, String>();
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.name", RestrictionExpression.EQUALS_OP, sortName);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		if (null != dictionaries) {
			for (Dictionary dictionary : dictionaries) {
				dicMap.put(dictionary.getCode(), dictionary.getName());
			}
		}
		return dicMap;
	}

	@Override
	public HashMap<Long, String> getDictionaryMapBySortCode(String sortCode) {
		LinkedHashMap<Long, String> dicMap = new LinkedHashMap<Long, String>();
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.code", RestrictionExpression.EQUALS_OP, sortCode);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		if (null != dictionaries) {
			for (Dictionary dictionary : dictionaries) {
				dicMap.put(dictionary.getId(), dictionary.getName());
			}
		}
		return dicMap;
	}

	@Override
	public HashMap<String, String> getDictionaryCodeMapBySortCode(String sortCode) {
		LinkedHashMap<String, String> dicMap = new LinkedHashMap<String, String>();
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("sort.code", RestrictionExpression.EQUALS_OP, sortCode);
		searchCriteriaBuilder.addQueryCondition("enabled", RestrictionExpression.EQUALS_OP, true);
		searchCriteriaBuilder.addOrderCondition("position", OrderRow.ORDER_ASC);
		List<Dictionary> dictionaries = getGeneralService().getObjects(searchCriteriaBuilder.build());
		if (null != dictionaries) {
			for (Dictionary dictionary : dictionaries) {
				dicMap.put(dictionary.getCode(), dictionary.getName());
			}
		}
		return dicMap;
	}

	@Override
	public DictionarySort getDictionarySortById(Long id) {
		DictionarySort dictionarySort = getGeneralService().getObjectById(DictionarySortImpl.class, id);
		return dictionarySort;
	}

	@Override
	public Dictionary getDictionaryById(Long id) {
		Dictionary dictionary = getGeneralService().getObjectById(DictionaryImpl.class, id);
		return dictionary;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.capinfo.smart.service.dictionary.DicService#getDictionarySortByCode
	 * (java.lang.String)
	 */
	@Override
	public DictionarySort getDictionarySortByCode(String code) {
		SearchCriteriaBuilder<DictionarySortImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionarySortImpl.class);
		searchCriteriaBuilder.addQueryCondition("code", RestrictionExpression.EQUALS_OP, code);
		DictionarySort dictionarySort = getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		return dictionarySort;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.capinfo.smart.service.dictionary.DicService#getDictionaryByCode(java
	 * .lang.String)
	 */
	@Override
	public Dictionary getDictionaryByCode(String code) {
		SearchCriteriaBuilder<DictionaryImpl> searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class);
		searchCriteriaBuilder.addQueryCondition("code", RestrictionExpression.EQUALS_OP, code);
		Dictionary dictionary = getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		return dictionary;
	}

	@Override
	public List<DictionaryImpl> getdicname(Long id) {
		SearchCriteriaBuilder<DictionaryImpl> scb = new SearchCriteriaBuilder<DictionaryImpl>(DictionaryImpl.class);
		scb.addQueryCondition("sortId", RestrictionExpression.EQUALS_OP, id);
		List<DictionaryImpl> list = getGeneralService().getObjects(scb.build());
		return list;
	}
}
