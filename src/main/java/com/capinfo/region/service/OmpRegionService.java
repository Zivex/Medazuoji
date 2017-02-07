package com.capinfo.region.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.capinfo.omp.utils.Page;
import com.capinfo.region.model.OmpRegion;

@Service
public interface OmpRegionService{

	public List<Map<String,Object>> getOmpOldMatchSortList(String pid);

	public List<Map<String,Object>> getPro(String id, String serverType);

	public List<Map<String,Object>> getSortList(String id);

	public List<Map<String, Object>> getOrder(String ptype);

	public List<Map<String, Object>> getServiceType();
	
	public List<Map<String, Object>> gerRegionById(String id);


	public void saveSqOrder(String comid, String ptype, String kid, String showid, String shownumber);

	public boolean isSqOrder(String comid, String ptype, String kid);

	public void updSqOrder(String comid, String ptype, String kid, String showid, String shownumber);
    
	public void updateOrder1(String shequId,String zhiling);
	
	public void updateOrder2(String shequId,String zhiling);
	
	public void updateOrder3(String shequId,String zhiling);
	
	public List<Map<String, Object>> getOrderByCommunity(String id);
    
	//取得街道的数量
	int getCount(String county, String street,String community, String isSend, String creationTime);
	//取得街道列表信息
	public List<Map<String, Object>> getStreetList(Page page,String county, String street,String community,String isSend, String creationTime);
	
	public String gerRegion(String id);

	public int login(String user, String pass);



}
