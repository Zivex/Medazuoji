package com.capinfo.dictionary.service;

import java.util.List;
import java.util.Map;

import com.capinfo.omp.utils.Page;

public interface WordbookService {

	List<Map<String, Object>> getData();

	List<Map<String,Object>> getPhoneType();
	
	//取得键位的数量
    int getMListCount(String pyId,String stId,String key,String status);
    //取得键位信息
  	public List<Map<String, Object>> getMList(Page page,String pyId,String stId,String key,String status);
    
    //取得话机类型的数量
    int getPtypeCount(String phoneType,String status);
    //取得话机类型信息
  	public List<Map<String, Object>> getPtypeList(Page page,String phoneType,String status);

  	
	//取得话机服务类型的数量
    int getMtypeCount(String serviceName,String status);
    //取得话机服务类型信息
  	public List<Map<String, Object>> getMtypeList(Page page,String serviceName,String status);

  	
	boolean updServerName(String kid, String serverName);

	boolean addServerName(String kid, String newServerName);

}
