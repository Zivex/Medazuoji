package com.capinfo.common.web.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.capinfo.common.model.DictionaryImpl;
import com.capinfo.common.model.DictionarySortImpl;
import com.capinfo.common.web.parameter.DictionaryParameter;
import com.capinfo.common.web.service.DictionaryManageService;
import com.capinfo.framework.dao.SearchCriteria.OrderRow;
import com.capinfo.framework.dao.SearchCriteriaBuilder;
import com.capinfo.framework.dao.impl.restriction.RestrictionExpression;
import com.capinfo.framework.model.dictionary.Dictionary;
import com.capinfo.framework.model.dictionary.DictionarySort;
import com.capinfo.framework.service.GeneralService;
import com.capinfo.framework.web.service.impl.CommonsDataOperationServiceImpl;

public class DictionaryManageServiceImpl extends CommonsDataOperationServiceImpl implements DictionaryManageService {

	private static final Log LOGGER = LogFactory.getLog(DictionaryManageService.class);

	@Autowired
	private GeneralService generalService;

	public boolean addDictionary(DictionaryParameter parameter) {
		Dictionary dictionary = new DictionaryImpl();
		BeanUtils.copyProperties(parameter, dictionary);
		try {
			generalService.persist(dictionary);
			return true;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
		return false;
	}

	public boolean addDictionarySort(DictionaryParameter parameter) {
		DictionarySort dictionarySort = new DictionarySortImpl();
		BeanUtils.copyProperties(parameter, dictionarySort);
		try {
			generalService.persist(dictionarySort);
			return true;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
		return false;
	}

	public boolean modifyDictionary(DictionaryParameter parameter) {
		Dictionary dictionary = (Dictionary) getGeneralService().getObjectById(DictionaryImpl.class, parameter.getId());
		BeanUtils.copyProperties(parameter, dictionary);
		try {
			getGeneralService().update(dictionary);
			return true;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
		return false;
	}

	public boolean modifyDictionarySort(DictionaryParameter parameter) {
		DictionarySort dictionarySort = (DictionarySort) getGeneralService().getObjectById(DictionarySortImpl.class, parameter.getId());
		BeanUtils.copyProperties(parameter, dictionarySort);
		try {
			getGeneralService().update(dictionarySort);
			return true;
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
		return false;
	}

	public Set<com.capinfo.framework.model.dictionary.Dictionary> searchDictionaryBySortName(String sortName) {

		return getDictionariesBySortName(sortName);
	}

	protected SearchCriteriaBuilder getDictionarySortSearchCriteriaBuilder(DictionaryParameter parameter) {

		SearchCriteriaBuilder searchCriteriaBuilder = new SearchCriteriaBuilder(DictionarySortImpl.class).addQueryCondition("name",
				RestrictionExpression.LIKE_OP, parameter.getName()).addOrderCondition("id", OrderRow.ORDER_DESC);

		return searchCriteriaBuilder;
	}

	protected SearchCriteriaBuilder getDictionarySearchCriteriaBuilder(DictionaryParameter parameter) {

		SearchCriteriaBuilder searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class)
				.addQueryCondition("name", RestrictionExpression.LIKE_OP, parameter.getName())
				.addQueryCondition("sort.id", RestrictionExpression.EQUALS_OP, parameter.getSortId()).addOrderCondition("position", OrderRow.ORDER_ASC);

		return searchCriteriaBuilder;
	}

	public List getDictionaryList(DictionaryParameter parameter, int dataTotalCount) {

		SearchCriteriaBuilder searchCriteriaBuilder = getDictionarySearchCriteriaBuilder(parameter);

		searchCriteriaBuilder.addLimitCondition(getDataOffset(dataTotalCount, parameter.getCurrentPieceNum()), getPerPieceSize());

		return getGeneralService().getObjects(searchCriteriaBuilder.build());

	}

	public List getDictionarySortList(DictionaryParameter parameter, int dataTotalCount) {

		SearchCriteriaBuilder searchCriteriaBuilder = getDictionarySortSearchCriteriaBuilder(parameter);

		searchCriteriaBuilder.addLimitCondition(getDataOffset(dataTotalCount, parameter.getCurrentPieceNum()), getPerPieceSize());

		return getGeneralService().getObjects(searchCriteriaBuilder.build());

	}

	public int getDictionarySortTotal(DictionaryParameter parameter) {

		return getGeneralService().getCount(getDictionarySortSearchCriteriaBuilder(parameter).build());

	}

	public int getDictionaryTotal(DictionaryParameter parameter) {

		return getGeneralService().getCount(getDictionarySearchCriteriaBuilder(parameter).build());
	}

	public Set getDictionariesBySortName(String sortName) {
		DictionarySort dictionarySort = getDictionarySortByName(sortName);
		if (dictionarySort != null) {
			getGeneralService().fetchLazyProperty(dictionarySort, "dictionaries");
			return dictionarySort.getDictionaries();
		} else {
			return new HashSet();
		}
	}

	/**
	 * 通过父ID得到子类
	 * 
	 * @param sortID
	 * @return
	 */
	@Override
	public List<DictionaryImpl> getDictionariesBySortId(Long sortID) {
		SearchCriteriaBuilder searchCriteriaBuilder = new SearchCriteriaBuilder(DictionaryImpl.class).addQueryCondition("sortId",
				RestrictionExpression.EQUALS_OP, sortID).addOrderCondition("position", OrderRow.ORDER_ASC);

		return getGeneralService().getObjects(searchCriteriaBuilder.build());
	}

	public DictionarySort getDictionarySortByName(String name) {
		SearchCriteriaBuilder searchCriteriaBuilder = new SearchCriteriaBuilder(DictionarySortImpl.class);
		searchCriteriaBuilder.addQueryCondition("name", "eq", name);
		DictionarySort dictionarySort = null;
		try {
			dictionarySort = (DictionarySort) getGeneralService().getObjectByCriteria(searchCriteriaBuilder.build());
		} catch (Exception e) {
			LOGGER.debug(e.getMessage(), e);
		}
		return dictionarySort;
	}
}
