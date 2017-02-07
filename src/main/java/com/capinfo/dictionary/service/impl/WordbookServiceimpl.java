package com.capinfo.dictionary.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capinfo.dictionary.service.WordbookService;
import com.capinfo.omp.utils.Page;
@Service
public class WordbookServiceimpl implements WordbookService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getData() {
		// TODO Auto-generated method stub
		String sql = "SELECT k.id,k.`key` k,k.`status` status,p.phoneType phoneType,s.serviceName serviceName FROM omp_key k LEFT JOIN omp_service_type s ON k.stId = s.id LEFT JOIN omp_phone_type p ON k.pyId = p.id";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String,Object>> getPhoneType() {
		String sql = "SELECT serviceName FROM omp_service_type";
		// TODO Auto-generated method stub
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public boolean updServerName(String kid, String serverName) {
		// TODO Auto-generated method stub
		String sql = "update  omp_service_type s set s.serviceName = '"+serverName+"' where s.id = (SELECT k.stId from omp_key k where k.id = "+kid+")";
		int i = jdbcTemplate.update(sql);
		return i>0;
	}

	@Override
	public boolean addServerName(String kid, String newServerName) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO omp_service_type (serviceName) values ('"+newServerName+"')";
		String sql1 = "update  omp_key k set k.stId = (SELECT max(id) FROM omp_service_type) where k.id = "+kid;
		int i = jdbcTemplate.update(sql);
		int j = 0;
		if (i>0) {
			j = jdbcTemplate.update(sql);
		}
		return j>0;
	}
	
    //获得键位数量
	@Override
	public int getMListCount(String pyId, String stId, String key, String status) {
		if (!StringUtils.isEmpty(pyId)) {
			pyId = " AND r1.`pyId` = '"+pyId+"'";
		}
		
		if (!StringUtils.isEmpty(stId)) {
			stId = " AND r1.`stId` = '"+stId+"'";
		}
		
		if (!StringUtils.isEmpty(key)) {
			key = " AND r1.key = '"+key+"'";
		}
		
		if (!StringUtils.isEmpty(status)) {
			status = " AND r1.status ='"+status+"'";
		}
		String sql = "SELECT count(r1.`ID`) from omp_key r1, "
				+ "where 1=1"+pyId+stId+key+status;

		int maxRows = jdbcTemplate.queryForInt(sql);
		return maxRows;
	}
    //获得键位信息详情
	@Override
	public List<Map<String, Object>> getMList(Page page, String pyId,
			String stId, String key, String status) {
		if (!StringUtils.isEmpty(pyId)) {
			pyId = " AND r1.`pyId` = '"+pyId+"'";
		}
		
		if (!StringUtils.isEmpty(stId)) {
			stId = " AND r1.`stId` = '"+stId+"'";
		}
		
		if (!StringUtils.isEmpty(key)) {
			key = " AND r1.key = '"+key+"'";
		}
		
		if (!StringUtils.isEmpty(status)) {
			status = " AND r1.status ='"+status+"'";
		}
		String sql = "SELECT r1.`ID` id, r1.`pyId` pyId,r1.`stId` stId,r1.`key` key,r1.`status` status from omp_key r1, "
				+ "where 1=1"+pyId+stId+key+status+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();

		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
		return queryForList;
	}

	@Override
	public int getMtypeCount(String serviceName, String status) {
		if (!StringUtils.isEmpty(serviceName)) {
			serviceName = " AND r1.`serviceName` = '"+serviceName+"'";
		}
		
		if (!StringUtils.isEmpty(status)) {
			status = " AND r1.status ='"+status+"'";
		}
		String sql = "SELECT count(r1.`ID`) from omp_service_type r1 "
				+ "where 1=1"+serviceName+status;

		int maxRows = jdbcTemplate.queryForInt(sql);
		return maxRows;
	}
	
    //获取键位管理类型信息
	@Override
	public List<Map<String, Object>> getMtypeList(Page page, String serviceName,String status) {
		if (!StringUtils.isEmpty(serviceName)) {
			serviceName = " AND r1.`serviceName` like '%"+serviceName+"%'";
		}
		
		if (!StringUtils.isEmpty(status)) {
			status = " AND r1.status ='"+status+"'";
		}
		String sql = "SELECT r1.id id,r1.serviceName serviceName,r1.status status from omp_service_type r1 "
				+ "where 1=1"+serviceName+status+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
		return queryForList;
	}
    
	@Override
	public int getPtypeCount(String phoneType, String status) {
		
		if (!StringUtils.isEmpty(phoneType)) {
			phoneType = " AND r1.`phoneType` like '%"+phoneType+"%'";
		}
		
		if (!StringUtils.isEmpty(status)) {
			status = " AND r1.status ='"+status+"'";
		}
		String sql = "SELECT count(r1.`ID`) from omp_phone_type r1 "
				+ "where 1=1"+status;

		int maxRows = jdbcTemplate.queryForInt(sql);
		return maxRows;
	}

	@Override
	public List<Map<String, Object>> getPtypeList(Page page, String phoneType,
			String status) {
		if (!StringUtils.isEmpty(phoneType)) {
			phoneType = " AND r1.`serviceName` like '%"+phoneType+"%'";
		}
		
		if (!StringUtils.isEmpty(status)) {
			status = " AND r1.status ='"+status+"'";
		}
		String sql = "SELECT r1.id id,r1.phoneType phoneType,r1.status status from omp_phone_type r1 "
				+ "where 1=1"+phoneType+status+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
		return queryForList;
		
	}
	 
	
    
}
