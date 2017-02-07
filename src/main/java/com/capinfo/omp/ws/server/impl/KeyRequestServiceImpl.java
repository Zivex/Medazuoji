package com.capinfo.omp.ws.server.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.capinfo.omp.ws.model.KeyCount;
import com.capinfo.omp.ws.server.service.KeyRequestService;
import com.capinfo.order.service.OrderService;
import com.google.gson.Gson;

@WebService(endpointInterface = "com.capinfo.omp.ws.server.service.KeyRequestService", serviceName = "KeyRequestService")
public class KeyRequestServiceImpl implements KeyRequestService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Autowired
	private OrderService orderService;


	@Override
	public String KeyRequest(String KeyRequest) {
//		int result = 0;
//		String msg = "";
//		String msgs = "";
//		String msgr = "";
//		String gos = "";
//		String new1 = "";
//		//String PMessg = "[{'M1':'1212','M2':'0072','M3':'83','M4':'4','M5':'5','M6':'6','M7':'7','M8':'8','M9':'9','M10':'10','M11':'11','M12':'12','M13':'12','M14':'12','M15':'12'}]";
//		StringBuilder sb = new StringBuilder();
//		String newJson = KeyRequest.replaceAll("'", "\"");
//		
//		Gson gson = new Gson();
//		KeyCount keyNum = gson.fromJson(newJson, KeyCount.class);
//		KeyRequest = keyNum.getLandLineNumber();
//
//		try {
//			if (KeyRequest != null || !"".equals(KeyRequest)) {
//				result = 1;
//			}
//			result = 1;
//		} catch (Exception e) {
//			result = 0;
//		}
//		if (result == 1) {
//			
//			String sql = "SELECT o.number 'generateSerialNumber',i.updNumber 'instructionsType',i.updNumber 'changeType',i.ZJNUMBER 'landLineNumber',i.`NAME` 'name',i.HOUSEHOLD_COMMUNITY_ID 'residenceCommunity',CASE WHEN i.TELTYPE = '失能型' THEN 1 WHEN i.TELTYPE = '农商型' THEN  2 WHEN i.TELTYPE = '居家型' THEN 3 END 'userType',i.updNumber 'changeTimes' FROM omp_old_info i,omp_order_number o WHERE o.oid = i.ID AND i.ZJNUMBER = "+KeyRequest+"";
//			String sql1 = "select o.keyPointMessage from omp_old_info i,omp_old_order o where o.oldId = i.ID AND i.ZJNUMBER = "+KeyRequest+"";
//			List<?> list = jdbcTemplate.queryForList(sql);
//			List<?> list1 = jdbcTemplate.queryForList(sql1);
//			sb = sb.append(list1);
//			String newlist1 = sb.toString().replaceAll("\"", "'");
//			
//			System.out.println();
//			JSONArray jsonArr = new JSONArray();
//			msgs = JSONArray.fromObject(list).toString();
//			msgr = JSONArray.fromObject(newlist1).toString();
//			
//			gos = (msgs+msgr).toString();
//			String newgos = gos.replaceAll("\\[\\{\"g", "\\{\"g");
//			System.out.println(newgos);
//			new1 = newgos.replaceAll("\\}\\]\\}\\]", "\\}\\],");
//			System.out.println(new1);
//		} else {
//			msg = "信息填写有误";
//		}
//		JSONObject jsonArr = new JSONObject();
//		jsonArr.put("statusCode", result);
//		jsonArr.put("errorMessage", msg);
//		//String keymsg = ",'keyPointMessage':";
//		//String newPMessg = keymsg.replaceAll("'", "\"");
//		String jsQ= jsonArr.toString();
//		String bg = jsQ.replaceAll("\\{\"", "\"");
//		String gging = (new1+bg);
//		String newgging = gging.replaceAll("\\}\\]\\[\\{", ",");
//		return newgging;
		int result = 0;
		String msg = "信息填写有误";
		String ss = "";
		String newJson = KeyRequest.replaceAll("'", "\"");
		Gson gson = new Gson();
		
		
		

		try {
			KeyCount keyNum = gson.fromJson(newJson, KeyCount.class);
			KeyRequest = keyNum.getLandLineNumber();
			if (KeyRequest != null || !"".equals(KeyRequest)) {
				result = 1;
			}
			result = 1;
			String sqls = "select COUNT(id) 'id' from omp_old_info i where i.ZJNUMBER = '"+KeyRequest+"'";
			Map<String, Object> map = jdbcTemplate.queryForMap(sqls);
			String resultzj = map.get("id").toString();
			System.err.println(resultzj);
			if ("0".equals(resultzj)) {
				result = 0;
				JSONObject jsonArr = new JSONObject();
				jsonArr.put("statusCode", result);
				jsonArr.put("errorMessage", msg);
				return jsonArr.toString();
			}
		} catch (Exception e) {
			result = 0;
			JSONObject jsonArr = new JSONObject();
			jsonArr.put("statusCode", result);
			jsonArr.put("errorMessage", msg);
			return jsonArr.toString();
		}
		if (result == 1) {
		String sql = "SELECT CASE WHEN f.updNumber = 0 THEN '1' ELSE  '2' END 'instructionsType',0 'changeType',f.ZJNUMBER 'landLineNumber',f.`NAME` 'name',f.HOUSEHOLD_COMMUNITY_ID 'residenceCommunity',CASE WHEN f.TELTYPE = '失能型' THEN '1' WHEN f.TELTYPE = '农商型' THEN '2' WHEN f.TELTYPE = '居家型' THEN '3' END 'userType',f.updNumber 'changeTimes',o.keyPointMessage 'keyPointMessage' FROM omp_old_info f,omp_old_order o WHERE o.oldId = f.ID AND f.ZJNUMBER = '"+KeyRequest+"'";
		// TODO Auto-generated method stub
		//Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		Map<String, Object> list1 = jdbcTemplate.queryForMap(sql);
		String sql1 = "select i.ID 'id' from omp_old_info i where i.ZJNUMBER = '"+KeyRequest+"'";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql1);
		String oid = map.get("id").toString();
		JSONObject jsonObject = JSONObject.fromObject(list1);
		String string = jsonObject.toString();
		String replace = string.replace("\\","");
		StringBuffer stringBuffer = new StringBuffer(replace);
		int x;// 定义两变量
		Random ne = new Random();// 实例化一个random的对象ne
		x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
		String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String t = time + x;
		StringBuffer insert = stringBuffer.insert(1,"\"generateSerialNumber\":\""+t+"\",");
		String gg = insert.toString();
		System.out.println(insert);
		String gs= gg.replaceAll("\"\\[\\{", "\\[\\{");
		String gos = gs.replaceAll("\\}\\]\"", "\\}\\]");
		String ajson = gos.replaceAll("e\":0", "e\":\"0\"");
		ss = ajson.replaceAll("\"", "'");	
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String date = df.format(new Date());
		String sqlg = "insert into omp_order_number (oid,send_date,number,returnType,exeinfo) VALUES("+oid+",'"+date+"','"+t+"','1',2)";
		jdbcTemplate.update(sqlg);
		System.err.println(ss);
		}
			String gging = (ss);
			return gging;
		
	}

}
