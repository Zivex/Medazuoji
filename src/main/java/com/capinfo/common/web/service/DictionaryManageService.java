package com.capinfo.common.web.service;

import java.util.List;
import java.util.Set;

import com.capinfo.common.model.DictionaryImpl;
import com.capinfo.common.web.parameter.DictionaryParameter;
import com.capinfo.framework.service.GeneralService;
import com.capinfo.framework.web.service.CommonsDataOperationService;

/**
 * @author lp
 * 类说明 字典服务
 *       提供字典维护操作
 */
public interface DictionaryManageService extends CommonsDataOperationService{
	
	/**
	 * 根据字典分类名称查询字典项
	 * @param sortName
	 * @return 返回字典项集合
	 */
	public Set<com.capinfo.framework.model.dictionary.Dictionary> searchDictionaryBySortName(String sortName);
		
	/**
	 * 查询字典分类,并分页显示。
	 * @param DataListParameter
	 * @return
	 */
	public List getDictionarySortList(DictionaryParameter parameter,int dataTotalCount);
	
	/**
	 * 查询字典,并分页显示。
	 * @param DataListParameter
	 * @return
	 */
	public List getDictionaryList(DictionaryParameter parameter,int dataTotalCount);
	
	/**
	 * 统计字典项分类总数
	 * @param parameter
	 * @return
	 */
	public int getDictionarySortTotal(DictionaryParameter parameter);
	
	/**
	 * 统计计算字典总数
	 * @param parameter
	 * @return
	 */
	public int getDictionaryTotal(DictionaryParameter parameter);
	
	
	/**
	 * 添加字典项
	 * @return
	 */
	public boolean addDictionary(DictionaryParameter parameter);
	
	
	/**
	 * 添加字典项分类
	 */
	public boolean addDictionarySort(DictionaryParameter parameter);
	
	/**
	 * 修改字典项分类
	 * @param 根据字典项分类Id修改该字典项分类
	 * @return 成功返回true 失败返回false
	 */
	public boolean modifyDictionarySort(DictionaryParameter parameter);
	
	
	/**
	 * 修改字典项
	 * @param 根据字典项Id 修改该字典项
	 * @return 成功返回true 失败返回false
	 */
	public boolean modifyDictionary(DictionaryParameter parameter);
		
	
	public GeneralService getGeneralService();
	
	public void setGeneralService(GeneralService generalService);

	public List<DictionaryImpl> getDictionariesBySortId(Long sortID);
	
}
