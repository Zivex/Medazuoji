package com.capinfo.omp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capinfo.omp.service.SystemLogs;

@Service
public class SystemLogsImpl implements SystemLogs {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Map<String, Object>> list() {
		String sql = "select * from  omp_log_record";
		List<Map<String, Object>> showList = jdbcTemplate.queryForList(sql);
		return showList;
	}

	@Override
	public List<Map<String, Object>> getlistCount(String county, String street, String community,String otype,String stimes,String etimes) {
		
		if (county!=null&&!StringUtils.isEmpty(county)) {
			county = " AND I.HOUSEHOLD_COUNTY_ID = '"+county+"'";
		}
		if (street!=null&&!StringUtils.isEmpty(street)) {
			street = " AND I.HOUSEHOLD_STREET_ID = '"+street+"'";
		}
		if (community!=null&&!StringUtils.isEmpty(community)) {
			community = "  AND I.HOUSEHOLD_COMMUNITY_ID = '"+community+"'";
		
		}
		if (otype!=null&&otype!=null&&!StringUtils.isEmpty(otype)) {
			otype = " AND I.TELTYPE = '"+otype+"'";
		}
		if (stimes!=null&&!StringUtils.isEmpty(stimes)) {
			stimes = " AND k.commitTime > '"+stimes+"'";
		}
		if (etimes!=null&&!StringUtils.isEmpty(etimes)) {
			etimes = " AND k.commitTime <= '"+etimes+"'";
		}
		String sql ="SELECT k.keyPointMessage 'key',SUM(k.keyPointCount) AS nbr,r1.`NAME` community,r2.`NAME` county,r3.`NAME` street FROM omp_old_info i,omp_keymapping_statistical k,omp_region r1,omp_region r2,omp_region r3 WHERE k.landLineNumber = i.ZJNUMBER AND i.HOUSEHOLD_COMMUNITY_ID = r1.ID AND i.HOUSEHOLD_COUNTY_ID = r2.ID AND i.HOUSEHOLD_STREET_ID = r3.ID "+county+street+community+otype+stimes+etimes+" GROUP BY k.keyPointMessage ORDER BY k.id";
		List<Map<String, Object>> showList = jdbcTemplate.queryForList(sql);
		return showList;
	}

}
