package com.capinfo.omp.utils;

import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class JsonUtil {
	
	public static JSONObject  getJson(String json) {
		String liString="";
		json = json.replaceAll("\"", "'");
		json="{data="+json+"}";
		JSONObject job=new JSONObject();
		try {
		JSONObject jsonObject = JSONObject.fromObject(json);
		JSONArray jsonArray = jsonObject.getJSONArray("data");
		
		for(int i=0;i<jsonArray.size();i++){
		     job = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		 }

		for (Iterator iter = jsonObject.keys(); iter.hasNext();) { //先遍历整个 people 对象  
			String key = (String)iter.next();  
		}  

		for (int i = 0; i < jsonArray.size(); i++) {
		     liString=jsonArray.getString(i);
		}
		} catch (JSONException e) {
		   e.printStackTrace();
		}
		return job;
	}
	
	public static JSONArray getJson1(JSONObject jsonObject) {
		String keyPointMessage = "[";
		for(int i=1;i<17;i++){
			String m="M";
			m=m+i;
			String linkNbr=(String) jsonObject.get(m);
			if(linkNbr==null||"".equals(linkNbr)){
				linkNbr="96003";
			}
			keyPointMessage+="{key:"+"'"+m+"'"+",value:"+"'"+linkNbr+"'"+"},";
		}
	    keyPointMessage=keyPointMessage.substring(0,keyPointMessage.length()-1);
	    keyPointMessage+="]";		    
	    
	    JSONArray json1 = JSONArray.fromObject(keyPointMessage); // 首先把字符串转成 JSONArray  对象
		return json1;  
	}
	
	public static void main(String[] args) {  
	    String json= "[{'M3':'62408487','M1':'62409989','M2':'62408487','M4':'62408487','M5':'62408487','M6':'62408487','M7':'62408487','M8':'96003','M9':'96003','M10':'96003','M11':'96003','M12':'96003'}]";
	    
	    JSONObject jsonObject=getJson(json);
	    JSONArray json1=getJson1(jsonObject);
	    if(json1.size()>0){
	       for(int i=0;i<json1.size();i++){
	         JSONObject job = json1.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
	         System.out.println("键位："+job.get("key")+"===联系方式："+job.get("value")) ;  // 得到 每个对象中的属性值
	      }
	    }
	}
}
