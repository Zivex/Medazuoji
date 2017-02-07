package com.capinfo.region.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capinfo.omp.utils.Page;
import com.capinfo.region.service.OmpRegionService;
@Service
public class OmpRegionServiceImpl implements OmpRegionService {
	
	@Autowired
	private JdbcTemplate JdbcTemplate;

	@Override
	public List<Map<String,Object>> getOmpOldMatchSortList(String pid) {
		String sql = "select id,name text,parentid pid from OMP_REGION r where r.levelid = "+pid;
//		String sql = "select id,name text,parentid pid from OMP_REGION";
		List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
		return queryForList;
	}
    
	@Override
	public List<Map<String,Object>> getPro(String name, String serverType) {
		if (!StringUtils.isEmpty(name)) {
			name = name.replace(" ","");
			name = "and r.server_name like '%"+name+"%'";
		}
		if (!StringUtils.isEmpty(serverType)) {
			serverType = serverType.replace(" ","");
			serverType = "and r.SERVER_TYPE like '%"+serverType+"%'";
		}
		String sql = "select ID,SERVER_NAME,SERVER_TYPE,SERVER_TEL from SERVICE_PROVIDERS_NAVIGATION r where 1=1 "+name+serverType;
		System.out.println(sql);
		List<Map<String,Object>> List = JdbcTemplate.queryForList(sql);
		return List;
	}

	@Override
	public List<Map<String,Object>> getSortList(String id) {
		String sql = "select id,name text,parentid pid from OMP_REGION r where r.parentid = "+id;
		List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
		return queryForList;
	}

	@Override
	public List<Map<String, Object>> getOrder(String ptype) {
		String sql = "SELECT k.id,s.serviceName sname,k.id kid,p.phoneType ptype,k.KEY "
				+ "FROM omp_key k LEFT JOIN omp_service_type s ON k.stId = s.id "
				+ "LEFT JOIN omp_phone_type p ON k.pyId = p.id where k.pyId = "+ptype;
		List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
		return queryForList;
		
	}
	
	@Override
	public List<Map<String, Object>> gerRegionById(String id) {
		String sql = "select id,name text,parentid pid,jjtype,sntype,nstype from OMP_REGION r where r.id = "+id;
		List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
		return queryForList;
	}
	

	@Override
	public List<Map<String, Object>> getServiceType() {
		String sql = "SELECT * FROM omp_service_typeImp";
		List<Map<String,Object>> list = JdbcTemplate.queryForList(sql);
		return list;
	}
    
	
	@Override
	public void saveSqOrder(String comid, String ptype, String kid, String showid, String shownumber) {
		String sql = null;
		if ("on".equals(showid)) {
				sql = "INSERT INTO omp_community_order"
						+ "(communityID,phoneTYPE,keyId,serviceProviderNumber)VALUES"
						+ "('"+comid+"','"+ptype+"','"+kid+"','"+shownumber+"')";
		}else {
			sql = "INSERT INTO omp_community_order"
					+ "(communityID,phoneTYPE,keyId,serviceProviderId,serviceProviderNumber)VALUES"
					+ "('"+comid+"','"+ptype+"','"+kid+"','"+showid+"','"+shownumber+"')";
		}
		JdbcTemplate.update(sql);
	}

	@Override
	public boolean isSqOrder(String comid, String ptype, String kid) {
		// TODO Auto-generated method stub
		String sql = "SELECT count(*) FROM omp_community_order o "
				+ "where o.keyId = '"+kid+"' AND o.phoneTYPE = '"+ptype+"' AND o.communityID = '"+comid+"'";
		int i = JdbcTemplate.queryForInt(sql);
		return i>0;
	}

	@Override
	public void updSqOrder(String comid, String ptype, String kid, String showid, String shownumber) {
		String sql = null;
		if ("on".equals(showid)) {
				sql = "UPDATE omp_community_order "
						+ "SET serviceProviderNumber='"+shownumber+"' "
						+ "WHERE keyId='"+kid+"' AND phoneTYPE='"+ptype+"' AND communityID='"+comid+"'";
		}else {
			sql = "UPDATE omp_community_order "
					+ "SET serviceProviderId='"+showid+"', serviceProviderNumber='"+shownumber+"' "
					+ "WHERE keyId='"+kid+"' AND phoneTYPE='"+ptype+"' AND communityID='"+comid+"'";
		}
		JdbcTemplate.update(sql);
	}
	
	@Override
	public void updateOrder1(String shequId,String zhiling) {
		String sql = null;
		sql = "UPDATE omp_region "
				+ "SET JJTYPE='"+zhiling+"'"
				+ "WHERE id="+shequId;
		JdbcTemplate.update(sql);
	}
	
	@Override
	public void updateOrder2(String shequId,String zhiling) {
		String sql = null;
		sql = "UPDATE omp_region "
				+ "SET SNTYPE='"+zhiling+"'"
				+ "WHERE id="+shequId;
		JdbcTemplate.update(sql);
	}
	
	@Override
	public void updateOrder3(String shequId,String zhiling) {
		String sql = null;
		sql = "UPDATE omp_region "
				+ "SET NSTYPE='"+zhiling+"'"
				+ "WHERE id="+shequId;
		JdbcTemplate.update(sql);
	}
	
	//根据社区ID更新键位信息
	@Override
	public List<Map<String, Object>> getOrderByCommunity(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT	c.id,p.phoneType, k.`key`,	s.serviceName,  pn.SERVER_NAME, c.serviceProviderNumber FROM	omp_key k LEFT JOIN	omp_community_order c on c.keyId = k.id LEFT JOIN	service_providers_navigation pn ON c.serviceProviderId = pn.ID LEFT JOIN omp_phone_type p ON k.pyId = p.id LEFT JOIN omp_service_type s ON k.stId = s.id WHERE c.communityID = "+id;
		List<Map<String,Object>> list = JdbcTemplate.queryForList(sql);
		
		return list;
	}

	@Override
	public String gerRegion(String id) {
		// TODO Auto-generated method stub
		String sql = "SELECT rr.`NAME` r1name,r1.r2name,r1.r3name,rr.JJTYPE jjtype,rr.SNTYPE sntype,rr.NSTYPE nstype FROM omp_region rr,(SELECT r2.`NAME` r2name,r2.PARENTID r1id,r3.r3name FROM omp_region r2,(SELECT r.`NAME` r3name,r.PARENTID FROM omp_region r WHERE r.ID = "+id+") r3 WHERE r2.ID = r3.PARENTID) r1 WHERE r1.r1id = rr.id";
		Map<String, Object> map = JdbcTemplate.queryForMap(sql);
		String region = "北京市"+map.get("r1name").toString()+map.get("r2name").toString()+map.get("r3name").toString();
		return region;
	}
	
	@Override
	public List<Map<String,Object>> getStreetList(Page page, String county, String street,String community, String isSend,
			String creationTime) {
		
		if (!StringUtils.isEmpty(county)) {
			county = " AND r2.`PARENTID` = '"+county+"'";
		}
		
		if (!StringUtils.isEmpty(street)) {
			street = " AND r1.`PARENTID` = '"+street+"'";
		}
		
		if (!StringUtils.isEmpty(community)) {
			community = " AND r1.id = '"+community+"'";
		}
		
		if (!StringUtils.isEmpty(creationTime)) {
			creationTime = " AND r1.CREATEDATE like '%"+creationTime+"%'";
		}
		String sql = "SELECT r1.`ID` id, r3.`NAME` city,r2.`NAME` street,r1.`NAME` shequ,r1.`CREATEDATE` time,r1.`CREATORID` creater,r1.`JJTYPE` jjType,r1.`SNTYPE` snType,r1.`NSTYPE` nsType from omp_region r1,omp_region r2,omp_region r3 "
				+ "where r1.LEVELID=5 and r1.PARENTID=r2.id and r2.PARENTID=r3.id"+street+county+street+community+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		
		if (!StringUtils.isEmpty(isSend)&&isSend.equals("0")) {//未发送的
			sql = "SELECT r1.`ID` id, r3.`NAME` city,r2.`NAME` street,r1.`NAME` shequ,r1.`CREATEDATE` time,r1.`CREATORID` creater,r1.`JJTYPE` jjType,r1.`SNTYPE` snType,r1.`NSTYPE` nsType from omp_region r1,omp_region r2,omp_region r3 "
					+ "where r1.LEVELID=5 and r1.PARENTID=r2.id and r2.PARENTID=r3.id AND r1.`JJTYPE` is NULL AND r1.snType is NULL AND r1.nsType is NULL"+street+county+street+community+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		}
		if (!StringUtils.isEmpty(isSend)&&isSend.equals("1")) {//已发送的
			sql = "SELECT r1.`ID` id, r3.`NAME` city,r2.`NAME` street,r1.`NAME` shequ,r1.`CREATEDATE` time,r1.`CREATORID` creater,r1.`JJTYPE` jjType,r1.`SNTYPE` snType,r1.`NSTYPE` nsType from omp_region r1,omp_region r2,omp_region r3 "
					+ "where r1.LEVELID=5 and r1.PARENTID=r2.id and r2.PARENTID=r3.id AND (r1.`JJTYPE` is not NULL or r1.snType is NOT NULL or r1.nsType is not NULL)"+street+county+street+community+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		}

		List<Map<String,Object>> queryForList = JdbcTemplate.queryForList(sql);
		return queryForList;
	}

	
	@Override
	public int getCount(String county, String street,String community, String isSend,
			String creationTime) {
        
		if (!StringUtils.isEmpty(county)) {
			county = " AND r2.`PARENTID` = '"+county+"'";
		}
		
		if (!StringUtils.isEmpty(street)) {
			street = " AND r1.`PARENTID` = '"+street+"'";
		}
		
		if (!StringUtils.isEmpty(community)) {
			community = " AND r1.id = '"+community+"'";
		}
		
		if (!StringUtils.isEmpty(creationTime)) {
			creationTime = " AND r1.CREATEDATE like '%"+creationTime+"%'";
		}
		String sql = "SELECT count(r1.`ID`)  from omp_region r1,omp_region r2,omp_region r3 "
				+ "where r1.LEVELID=5 and r1.PARENTID=r2.id and r2.PARENTID=r3.id"+street+county+street+community;
		
		if (!StringUtils.isEmpty(isSend)&&isSend.equals("0")) {//未发送的
			sql = "SELECT count(r1.`ID`) from omp_region r1,omp_region r2,omp_region r3 "
					+ "where r1.LEVELID=5 and r1.PARENTID=r2.id and r2.PARENTID=r3.id AND r1.`JJTYPE` is NULL AND r1.snType is NULL AND r1.nsType is NULL"+street+county+street+community;
		}
		if (!StringUtils.isEmpty(isSend)&&isSend.equals("1")) {//已发送的
			sql = "SELECT count(r1.`ID`) from omp_region r1,omp_region r2,omp_region r3 "
					+ "where r1.LEVELID=5 and r1.PARENTID=r2.id and r2.PARENTID=r3.id AND (r1.`JJTYPE` is not NULL or r1.snType is NOT NULL or r1.nsType is not NULL)"+street+county+street+community;
		}
		
		int maxRows = JdbcTemplate.queryForInt(sql);
		return maxRows;
	}



	@Override
	public int login(String user, String pass) {
		String sql = "SELECT count(i.ID) FROM	users i WHERE i.USER_NAME="+user+" and i.PASSWORD="+pass;
		int maxRows = JdbcTemplate.queryForInt(sql);
		return maxRows;
	}


}
