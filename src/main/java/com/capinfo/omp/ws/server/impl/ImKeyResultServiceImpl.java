package com.capinfo.omp.ws.server.impl;

import javax.jws.WebService;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import net.sf.json.JSONObject;

import com.capinfo.omp.ws.model.ImKey;
import com.capinfo.omp.ws.server.service.ImKeyResultService;
import com.google.gson.Gson;

@WebService(endpointInterface = "com.capinfo.omp.ws.server.service.ImKeyResultService", serviceName = "ImKeyService")
public class ImKeyResultServiceImpl implements ImKeyResultService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public String Imkey(String Imkey) {
		int result = 0;
		int number = 0;
		String msg = "";
		
		String newJson = Imkey.replaceAll("'", "\"");
		Gson gson = new Gson();
		ImKey ikey = gson.fromJson(newJson, ImKey.class);
		try {

//			String sql = "INSERT into omp_order_number (returntype,number,execute_flag,failure_cause,execute_date) VALUES (?,?,?,?,?)";
			String sql = "UPDATE omp_order_number n SET n.returntype = ?,n.execute_flag = ?,n.failure_cause = ? ,n.execute_date= ? where n.number = ? AND n.exeinfo = 1";
			number = Integer.valueOf(ikey.getStatusCode());
			jdbcTemplate.update(
					sql,
					new Object[] { ikey.getReturnType(),
							ikey.getStatusCode(), 
							ikey.getErrorMessage(),
							ikey.getExecutionTime(),
							ikey.getGenerateSerialNumber()});
			//jdbcTemplate.update("UPDATE omp_old_order o SET o.execute_flag = '1' where o.oldId = (SELECT n.oid from omp_order_number n where n.number = "+ikey.getGenerateSerialNumber()+")");
			if ("1".equals(ikey.getReturnType())) {
				String sql2 = "UPDATE `omp_old_order` SET `execute_flag` = "+ikey.getStatusCode()+" WHERE oldId = (select n.oid from omp_order_number n where n.number = '"+ikey.getGenerateSerialNumber()+"')";
				jdbcTemplate.update(sql2);
			}
			if ("2".equals(ikey.getReturnType())) {
				String sql2 =  "UPDATE `omp_voice_order` SET `execute_flag` = (SELECT n.execute_flag 'execute_flag' FROM omp_order_number n WHERE n.number = '"+ikey.getGenerateSerialNumber()+"') WHERE oldId = (select n.oid from omp_order_number n where n.number = '"+ikey.getGenerateSerialNumber()+"') AND number = '"+ikey.getGenerateSerialNumber()+"'";
				jdbcTemplate.update(sql2);
			}
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		if (result == 1 ) {
			msg = "";
		} else {
			msg = "信息填写有误";
		}
		JSONObject jsonArr = new JSONObject();
		jsonArr.put("generateSerialNumber", ikey.getGenerateSerialNumber());
		jsonArr.put("statusCode", result);
		jsonArr.put("errorMessage", msg);

		return jsonArr.toString();

	}

}
