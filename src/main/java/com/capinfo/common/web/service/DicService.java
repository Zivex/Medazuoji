package com.capinfo.common.web.service;

import java.util.HashMap;
import java.util.List;

import com.capinfo.common.model.DictionaryImpl;
import com.capinfo.framework.model.dictionary.Dictionary;
import com.capinfo.framework.model.dictionary.DictionarySort;
import com.capinfo.framework.web.service.CommonsDataOperationService;


public interface DicService extends CommonsDataOperationService{
	
	/**
	 * 根据字典分类名称获取字典项
	 * @param sortName
	 * @return List<Dictionary> 
	 */
	public List<Dictionary> getDictionariesBySort(String sortName);
	
	/**
	 * 根据字典分类ID获取字典项
	 * @param id
	 * @return List<Dictionary> 
	 */
	public List<Dictionary> getDictionariesBySortId(Long id);
	
	/**
	 * 根据字典分类CODE获取字典项
	 * @param sortCode
	 * @return
	 */
	public List<Dictionary> getDictionariesBySortCode(String sortCode);
	
	/**
	 * 根据字典分类名称和字典名称获取字典项
	 * @param sortName 分类名称
	 * @param itemName 字典项名称
	 * @return
	 */
	public Dictionary geDictionaryBySortName(String sortName,String itemName);
	
	
	/**
	 * 根据字典分类ID和字典名称获取字典项
	 * @param sortId 分类ID
	 * @param itemName 字典项名称
	 * @return
	 */
	public Dictionary geDictionaryBySortId(Long sortId,String itemName);
	
	/**
	 * 根据字典分类名称和字典CODE获取字典项
	 * @param sortName
	 * @return Dictionary 
	 */
	public Dictionary geDictionaryBySortIdCode(Long id,String itemCode);
	
	/**
	 * 根据字典ID获取字典项
	 * @param sortName
	 * @return Dictionary 
	 */
	public Dictionary geDictionaryByid(Long id);
	
	/**
	 * 根据字典分类获取字典MAP
	 * @param sortName
	 * @return HashMap<Long,String> [ID,Name]
	 */
	public HashMap<Long,String> getDictionaryMapBySortName(String sortName);
	
	/**
	 * 根据字典分类获取字典MAP
	 * @param sortName
	 * @return HashMap<Long,String> [Code,Name]
	 */
	public HashMap<String,String> getDictionaryCodeMapBySortName(String sortName);
	
	/**
	 * 根据字典分类CODE获取字典MAP
	 * @param sortName
	 * @return HashMap<Long,String> [ID,Name]
	 */
	public HashMap<Long, String> getDictionaryMapBySortCode(String sortCode);

	/**
	 * 根据字典分类CODE获取字典MAP
	 * @param sortName
	 * @return HashMap<Long,String> [Code,Name]
	 */
	public HashMap<String, String> getDictionaryCodeMapBySortCode(String sortCode);
	

	/**
	 * 获取字典分类
	 * @param id
	 * @return
	 */
	public DictionarySort getDictionarySortById(Long id);
	
	
	/**
	 * 获取字典分类
	 * @param code
	 * @return
	 */
	public DictionarySort getDictionarySortByCode(String code);
	

	/**
	 * 获取字典项
	 * @param id
	 * @return
	 */
	public Dictionary getDictionaryById(Long id);
	
	/**
	 * 获取字典项
	 * @param code
	 * @return
	 */
	public Dictionary getDictionaryByCode(String code);
	/**
	 * 字典分类表id查字典表
	 */
	public List<DictionaryImpl> getdicname(Long id);
	
		
	
}
