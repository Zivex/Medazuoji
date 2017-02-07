package com.capinfo.voice.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.capinfo.omp.utils.Page;
import com.capinfo.omp.ws.model.ImKey;

@Service
public interface VoiceService {
	/**
	 * 查询
	 * 
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
	List<Map<String, Object>> getOldContextList(Page page, String name,
			String idCard, String zjNumber, String county, String street,
			String community);

	int getCount(String name, String idCard, String zjNumber, String county,
			String street, String community);

	String sendOrder(String id);

	void toupdete(String string);

	List<Map<String, Object>> getshell();

	/*
	 * List<Map<String, Object>> getvoicelist();
	 * 
	 * List<Map<String, Object>> voicequery(String name);
	 */

	List<Map<String, Object>> getvoicelist(Page page, String name);

	int getvoicelist(String name);
	
	int SenVoice(String vid,String cid);
	
	String checkDeBatchSendInstructions();
	
	List<Map<String, Object>> getOldList(Page page, String name, String idCard, String zjNumber, String county, String street, String community,String send_flag,String execute_flag);

	int getOlCount(String name, String idCard, String zjNumber, String county, String street, String community,String send_flag,String execute_flag);
	
	void uploadMiddle(String id);
	
	String middle();
	
	void saveviceoder(String id, String vid,String executeType,String startTime,String endTime,String t);
	
	String resultVice();
	
	String sendvice(String id,String t);
	
	void upMessg(ImKey imKey,String id);
	
	String resultStrtTime(String id,String vid);
	
	String resultendTime(String id,String vid);
	
	boolean getcount();
	
	String resultOrderId();
	
	String ResultOderById(String comid);
	
	void resultVOrders(ImKey imKey,String id,String username);
}
