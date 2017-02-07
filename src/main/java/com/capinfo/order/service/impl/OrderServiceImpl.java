package com.capinfo.order.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capinfo.omp.service.OldService;
import com.capinfo.omp.utils.Page;
import com.capinfo.omp.ws.client.ClientGetDataService;
import com.capinfo.omp.ws.model.ImKey;
import com.capinfo.order.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private OldService oldService;
	
	@SuppressWarnings("deprecation")
	@Override
	public int getOrderCount(String name, String idCard, String zjNumber, String county, String street,
			String community, String send_flag, String execute_flag) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(name)) {
			name = " AND oldo.`NAME` LIKE '%"+name+"%'";
		}
		if (!StringUtils.isEmpty(idCard)) {
			idCard = " AND oldo.idCard = '"+idCard+"'";
		}
		if (!StringUtils.isEmpty(zjNumber)) {
			zjNumber = " AND oldo.ZJNUMBER = '"+zjNumber+"'";
		}
		if (!StringUtils.isEmpty(county)) {
			county = " AND i.HOUSEHOLD_COUNTY_ID = '"+county+"'";
		}
		if (!StringUtils.isEmpty(street)) {
			street = " AND i.HOUSEHOLD_STREET_ID = '"+street+"'";
		}
		if (!StringUtils.isEmpty(community)) {
			community = " AND i.HOUSEHOLD_COMMUNITY_ID = '"+community+"'";
		}
		if (!StringUtils.isEmpty(send_flag)) {
			send_flag = " AND oldo.send_flag = '"+send_flag+"'";
		}
		if (!StringUtils.isEmpty(execute_flag)) {
			execute_flag = " AND oldo.execute_flag = '"+execute_flag+"'";
		}
		String sql = "SELECT count(oldo.id) FROM "
				+ "(SELECT old.id,old.`NAME`,old.idcard,old.community,old.county,old.street,"
				+ "old.ZJNUMBER,o.send_flag,o.execute_flag FROM omp_old_order o,"
				+ "(SELECT i.id,i.`NAME`,i.CERTIFICATES_NUMBER idcard,r1.`NAME` community,r2.`NAME`"
				+ " county,r3.`NAME` street,i.ZJNUMBER FROM omp_old_info i,omp_region r1,omp_region r2,"
				+ "omp_region r3	WHERE	i.HOUSEHOLD_COMMUNITY_ID = r1.ID"
				+ " AND i.HOUSEHOLD_COUNTY_ID = r2.ID	AND i.HOUSEHOLD_STREET_ID = r3.ID "+county+street+community+") old"
				+ "	WHERE	o.oldId = old.id) oldo WHERE 1=1 "+name+idCard+zjNumber+
				send_flag+execute_flag;
		int forInt = jdbcTemplate.queryForInt(sql);
		return forInt;
	}
	
	
	@Override
	public List<Map<String, Object>> getOrderList(Page page, String name, String idCard, String zjNumber, String county,
			String street, String community, String send_flag, String execute_flag) {
		// TODO Auto-generated method stub
		if (!StringUtils.isEmpty(name)) {
			name = " AND oldo.`NAME` LIKE '%"+name+"%'";
		}
		if (!StringUtils.isEmpty(idCard)) {
			idCard = " AND oldo.idCard = '"+idCard+"'";
		}
		if (!StringUtils.isEmpty(zjNumber)) {
			zjNumber = " AND oldo.ZJNUMBER = '"+zjNumber+"'";
		}
		if (!StringUtils.isEmpty(county)) {
			county = " AND i.HOUSEHOLD_COUNTY_ID = '"+county+"'";
		}
		if (!StringUtils.isEmpty(street)) {
			street = " AND i.HOUSEHOLD_STREET_ID = '"+street+"'";
		}
		if (!StringUtils.isEmpty(community)) {
			community = " AND i.HOUSEHOLD_COMMUNITY_ID = '"+community+"'";
		}
		if (!StringUtils.isEmpty(send_flag)) {
			send_flag = " AND oldo.send_flag = '"+send_flag+"'";
		}
		if (!StringUtils.isEmpty(execute_flag)) {
			execute_flag = " AND oldo.execute_flag = '"+execute_flag+"'";
		}
		String sql = "SELECT oldo.* FROM "
				+ "(SELECT old.id,old.`NAME`,old.idcard,old.community,old.county,old.street,"
				+ "old.ZJNUMBER,o.send_flag,o.execute_flag FROM omp_old_order o,"
				+ "(SELECT i.id,i.`NAME`,i.CERTIFICATES_NUMBER idcard,r1.`NAME` community,r2.`NAME`"
				+ " county,r3.`NAME` street,i.ZJNUMBER FROM omp_old_info i,omp_region r1,omp_region r2,"
				+ "omp_region r3	WHERE	i.HOUSEHOLD_COMMUNITY_ID = r1.ID"
				+ " AND i.HOUSEHOLD_COUNTY_ID = r2.ID	AND i.HOUSEHOLD_STREET_ID = r3.ID "+county+street+community+") old"
				+ "	WHERE	o.oldId = old.id) oldo WHERE 1=1"+name+idCard+zjNumber+
				send_flag+execute_flag+" LIMIT "+(page.getCurrentPage()-1)*page.getPageSize()+", "+page.getPageSize();
		List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

	

	@Override
	public String sendOrder(String id) {
		String sql = "SELECT CASE WHEN f.updNumber = 0 THEN '1' ELSE  '2' END 'instructionsType',0 'changeType',f.ZJNUMBER 'landLineNumber',f.`NAME` 'name',f.HOUSEHOLD_COMMUNITY_ID 'residenceCommunity',CASE WHEN f.TELTYPE = '失能型' THEN '1' WHEN f.TELTYPE = '农商型' THEN '2' WHEN f.TELTYPE = '居家型' THEN '3' END 'userType',f.updNumber 'changeTimes',o.keyPointMessage 'keyPointMessage' FROM omp_old_info f,omp_old_order o WHERE o.oldId = f.ID AND f.ID = '"+id+"'";
		// TODO Auto-generated method stub
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		JSONObject jsonObject = JSONObject.fromObject(map);
		String string = jsonObject.toString();
		String replace = string.replace("\\","");
		StringBuffer stringBuffer = new StringBuffer(replace);
		int x;// 定义两变量
		Random ne = new Random();// 实例化一个random的对象ne
		x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String t = time + x;
		StringBuffer insert = stringBuffer.insert(1,"\"generateSerialNumber\":\""+t+"\",");
		
		System.out.println(insert);
		return insert.toString();
	}
	
	


	@Override
	public void toupdete(String string,ImKey imKey) {
//		String sql = "UPDATE `omp_old_order` SET `execute_flag`= (select n.execute_flag 'execute_flag' from omp_order_number n where n.number='"+imKey.getGenerateSerialNumber()+"' and n.oid ='"+string+"') WHERE oldId= '"+string+"'";
		String sql = "UPDATE `omp_old_order` SET `send_flag`='"+imKey.getStatusCode()+"' WHERE oldId='"+string+"'";
		jdbcTemplate.update(sql);
		String sqls = "UPDATE `omp_old_order` SET `execute_flag`= 3 WHERE oldId='"+string+"'";
		jdbcTemplate.update(sqls);
		
	}


	@Override
	public void delect(String id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM `omp_old_order` WHERE oldId="+id;
		String sql1 = "UPDATE `omp_old_info` SET `isGenerationOrder`='0' WHERE (`ID`='"+id+"')";
		jdbcTemplate.update(sql);
		jdbcTemplate.update(sql1);
	}


	@Override
	public String checkDeBatchSendInstructions() {
		// TODO Auto-generated method stub
		String sql = "select GROUP_CONCAT(oldId) as id from (SELECT oldId from omp_old_order where send_flag = 0 LIMIT 0,10) o ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		return map.get("id").toString();
	}
    

	@Override
	public boolean getcount() {
		// TODO Auto-generated method stub
		String sql = "SELECT count(id) from omp_old_order where send_flag = 0";
		int i = jdbcTemplate.queryForInt(sql);
		return i>0;
	}
	
	@Override
	public void upoder(String id) {
		String sql = "UPDATE `omp_old_order` SET `send_flag`='1',`execute_flag`='1' WHERE communityOrderId='"+id+"'";
		jdbcTemplate.update(sql);
		
	}


	@Override
	public boolean upsendOrder(String id) throws Exception {
		int result = 0;
		ClientGetDataService c = new ClientGetDataService();
		StringBuffer insert = null;
		String sql = "SELECT CASE WHEN f.updNumber = 0 THEN '1' ELSE  '2' END 'instructionsType',0 'changeType',f.ZJNUMBER 'landLineNumber',f.`NAME` 'name',f.HOUSEHOLD_COMMUNITY_ID 'residenceCommunity',CASE WHEN f.TELTYPE = '失能型' THEN '1' WHEN f.TELTYPE = '农商型' THEN '2' WHEN f.TELTYPE = '居家型' THEN '3' END 'userType',f.updNumber 'changeTimes',o.keyPointMessage 'keyPointMessage' FROM omp_old_info f,omp_old_order o WHERE o.oldId = f.ID AND o.communityOrderId = '"+id+"'";
		List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
		for (int i = 0; i < map.size(); i++) {
			System.out.println(map.get(i));
			JSONArray jsar = JSONArray.fromObject(map.get(i));
			//JSONObject jsonObject = JSONObject.fromObject(map);
			String string = jsar.toString();
			String replace = string.replace("\\","");
			StringBuffer stringBuffer = new StringBuffer(replace);
			int x;// 定义两变量
			Random ne = new Random();// 实例化一个random的对象ne
			x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
			String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			String t = time + x;
			insert = stringBuffer.insert(1,"\"generateSerialNumber\":\""+t+"\",");
			String json2 =  insert.toString();
			String strs = json2.replaceAll("\\[\"", "\\{\"");
			String strs2 = strs.replaceAll(",\\{", ",");
			String star3 = strs2.replaceAll("\"\\[\\{","\\[\\{");
			String json = star3.replaceAll("\\}\\]\"\\}\\]", "\\}\\]\\}");
			System.out.println(json);
			ImKey imKey = c.sendOrder(json);
			if (!"".equals(imKey.getStatusCode()) && "1".equals(imKey.getStatusCode())) {
				result++;
			}
			if (result == 1) {
				//全部成功后修改指令状态
				upoder(id);
			}

		}
		
			
		
		return result>0;
	}


	@Override
	public void upMessg(ImKey imKey,String id) {
		String sql =  "UPDATE `omp_old_order` SET `errorMessage`='"+imKey.getErrorMessage()+"' WHERE Id='"+id+"'";
		jdbcTemplate.update(sql);
	}


	@Override
	public List<Map<String, Object>> getOList(String id) {
		String sql = "SELECT i.id,i.`NAME`,i.ZJNUMBER,i.TELTYPE,r2.`NAME` q,r3.`NAME` j,r1.`NAME` s,r5.id typeid,o.errorMessage,o.execute_flag FROM omp_region r1,omp_region r2,omp_region r3,omp_phone_type r5,omp_old_info i,omp_old_order o WHERE STATE = 1 AND r1.id = i.HOUSEHOLD_COMMUNITY_ID AND r2.id = i.HOUSEHOLD_COUNTY_ID AND r5.phoneType = i.TELTYPE AND r3.ID = i.HOUSEHOLD_STREET_ID AND i.ID = o.oldId AND i.ID = '"+id+"'";
		List<Map<String, Object>> getList = jdbcTemplate.queryForList(sql);
		return getList;
	}


	@Override
	public void resultOrder(ImKey imKey,String id,String username) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String date = df.format(new Date());
		String sql = "insert into omp_order_number (oid,send_date,number,send_flag,returnType,operator) VALUES("+id+",'"+date+"','"+imKey.getGenerateSerialNumber()+"',"+imKey.getStatusCode()+",'1','"+username+"');";
		//String sql = "UPDATE omp_order_number n SET n.send_date = '"+date+"' ,n.returntype = '1' where n.number = '"+imKey.getGenerateSerialNumber()+"'";
		oldService.saveLogger("5", "按键指令表", ""+username+"",""+imKey.getStatusCode()+"");
		jdbcTemplate.update(sql);
	}


	@Override
	public String RequestZJ(String zj) {
		String sql = "select COUNT(id) 'id' from omp_old_info i where i.ZJNUMBER = '"+zj+"'";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		return map.get("vid").toString();
	}
	
	


}
