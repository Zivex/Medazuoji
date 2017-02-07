package com.capinfo.serviceprovider.service;

import java.util.List;
import java.util.Map;

import com.capinfo.omp.utils.Page;

public interface ServiceProviderService{

	public int getCount();

	public List<Map<String,Object>> getList(Page page);
    
	public List<Map<String,Object>> getAllList();
	
	public String getregion(Object id);

	public Map<String, Object> getMapById(String id);

	List<Map<String, Object>> getListWithSql(Page page, String QSql);

	public int getCountWithSql(String qSql);

	Map<String, Object> getServerInfoWithID(String ID);
}
