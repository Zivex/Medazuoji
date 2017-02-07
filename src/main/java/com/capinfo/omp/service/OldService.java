package com.capinfo.omp.service;

import java.util.List;
import java.util.Map;

import com.capinfo.assistant.platform.ws.card.model.CardPersonMessageBack;
import com.capinfo.omp.model.OmpOldInfo;
import com.capinfo.omp.model.OmpOldMatch;
import com.capinfo.omp.utils.Page;


public interface OldService {

	/**
	 * 查询
	 * @param page 
	 * @param community 
	 * @param street 
	 * @param county 
	 * @param zjNumber 
	 * @param idCard 
	 * @param name 
	 * @param creationTime 
	 * 
	 * @return
	 */
	List<Map<String, Object>> getOldContextList(Page page, String name, String idCard, String zjNumber, String county, String street, String community, String isGenerationOrder,String isindividuation);

	/**
	 * 增加
	 * @param format 
	 * 
	 * @return
	 */
	boolean addOld(OmpOldInfo ompOldInfo, String format);

	/**
	 * 修改
	 * 
	 * @return
	 */
	boolean updOldById(Map<String, Object> map);

	/**
	 * 删除
	 * 
	 * @return
	 */
	int delOldById(String id);

	int getCount(String name, String idCard, String zjNumber, String county, String street, String community, String isGenerationOrder,String isindividuation);

	String getIdByName(String area, int i);

	List<Map<String, Object>> getOldById(String id);
    
	List<Map<String, Object>> getOldById1(String id);
	
	Map getRegionList(Map<String, Object> map);

	List<Map<String, Object>> getRegionById(String id);

	List<Map<String, Object>> queryCommunityOrder(String id);

	boolean saveOrder(String id, String pname, String comId, String keyPointMessage);

	void updOldState(String id);

	List<Map<String, Object>> getOldKeyPointMessage(String oid);

	boolean checkRe(String idCard, String zjnumber);

	boolean checkId(String string);

	List<Map<String, Object>> getOmpKeyByType(String pyId);

	List<Map<String, Object>> getOldMyPointMessage(String oid);

	Boolean uploadOldIndividuation(String id, String json);

	Boolean addOmpOldOrderInfo(String id, String json);

	boolean checkOldIsHave(String phoneid, String cardID);

	List<Map<String, Object>> getOldKeyInfoById(String id);
	
	boolean saveLogger(String type,String content,String creater,String state);
	
	void saveolInfo(String carid);
	
	boolean getcountid();
	
	String checkDeBatchSendInstructions();
	
	String HOUSEHOLD_COUNTY_ID(String add);
	
	String HOUSEHOLD_STREET_ID(String add);
	
	String HOUSEHOLD_COMMUNITY_ID(String add);


}
