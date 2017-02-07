package com.capinfo.serviceprovider.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capinfo.omp.utils.Page;
import com.capinfo.serviceprovider.service.ServiceProviderService;
@Service
public class ServiceProviderServiceImpl implements ServiceProviderService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@SuppressWarnings("deprecation")
	@Override
	public int getCount() {
		String sql = "select count(*) from SERVICE_PROVIDERS_NAVIGATION t";
		int int1 = jdbcTemplate.queryForInt(sql);
		return int1;
	}
	public int getCountWithSql(String QSql) {
		String sql = "select count(*) from SERVICE_PROVIDERS_NAVIGATION t " + QSql ;
		int int1 = jdbcTemplate.queryForInt(sql);
		return int1;
	}
	@Override
	public List<Map<String,Object>> getList(Page page) {
		String sql = "SELECT t.* FROM SERVICE_PROVIDERS_NAVIGATION t LIMIT "
				+(page.getCurrentPage()-1)*page.getPageSize()+","+page.getPageSize();
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	@Override
	public List<Map<String,Object>> getListWithSql(Page page , String QSql) {
		String sql = "SELECT t.* FROM SERVICE_PROVIDERS_NAVIGATION t " + QSql+ " LIMIT "
				+(page.getCurrentPage()-1)*page.getPageSize()+","+page.getPageSize();
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}
	// 通过服务商编号，获取服务商信息
	@Override
	public Map<String,Object> getServerInfoWithID( String ID) {
		String sql = "SELECT t.* FROM SERVICE_PROVIDERS_NAVIGATION t WHERE t.ID = "+ ID;
		Map<String,Object> map = jdbcTemplate.queryForMap(sql);
		return map;
	}
	@Override
	public List<Map<String,Object>> getAllList() {
		String sql = "SELECT t.* FROM SERVICE_PROVIDERS_NAVIGATION t ";
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
		return list;
	}

	@Override
	public String getregion(Object id) {
		String sql = "select r.name from Omp_Region r where r.name = "+id;
		List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : queryForList) {
			return map.get("name").toString();
		}
		return null;
	}

	@Override
	public Map<String, Object> getMapById(String id) {
		String sql = "select t.server_id,t.service_providers_identify tify from SERVICE_PROVIDERS_NAVIGATION t where id ="+id;
		Map<String, Object> forMap = jdbcTemplate.queryForMap(sql);
		Object server_id = forMap.get("server_id");
		Object tify = forMap.get("tify");
		String sql1 = null;
		switch (tify.toString()) {
		case "YL":
			sql1 = "select d.*,country.name country,street.name street,city.name city from"+
					"(select * from OMP_P_SERVICE_PROVIDER d where d.id = "+server_id+") d,"+
					"(select r.name from OMP_P_SERVICE_PROVIDER d,Omp_Region r where d.city_id = r.id and d.id = "+server_id+" ) city,"+
					"(select r.name from OMP_P_SERVICE_PROVIDER d,Omp_Region r where d.country_id = r.id and d.id = "+server_id+" ) country,"+
					"(select r.name from OMP_P_SERVICE_PROVIDER d,Omp_Region r where d.street_id = r.id and d.id = "+server_id+" ) street";
			break;

		case "SN":
			sql1 = "select d.*,country.name country,street.name street,community.name community from"+
					"(select * from Omp_D_Service_Provider d where d.id = "+server_id+") d,"+
					"(select r.name from Omp_D_Service_Provider d,Omp_Region r where d.country_id = r.id and d.id = "+server_id+" ) country,"+
					"(select r.name from Omp_D_Service_Provider d,Omp_Region r where d.street_id = r.id and d.id = "+server_id+" ) street,"+
					"(select r.name from Omp_D_Service_Provider d,Omp_Region r where d.community_id = r.id and d.id = "+server_id+" ) community";
			break;
		}
		Map<String, Object> map = jdbcTemplate.queryForMap(sql1);
		map.put("tify", tify);
		return map;
	}


}
