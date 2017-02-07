package com.capinfo.omp.ws.server.impl;

import java.util.List;
import javax.jws.WebService;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.capinfo.omp.ws.model.CountMessge;
import com.capinfo.omp.ws.model.KeyCount;
import com.capinfo.omp.ws.server.service.KeyCountService;
import com.google.gson.Gson;

@WebService(endpointInterface = "com.capinfo.omp.ws.server.service.KeyCountService", serviceName = "KeyCountService")
public class KeyCountServiceImpl implements KeyCountService {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public String KeyCout(String KeyCount) {
		int result = 0;
		int number = 0;
		String msg = "";
		String newJson = KeyCount.replaceAll("'", "\"");
		Gson gson = new Gson();
		KeyCount kCount = gson.fromJson(newJson, KeyCount.class);
		List<CountMessge> list = kCount.getCountMessge();
		try {

			String sql = "INSERT into OMP_KEYMAPPING_STATISTICAL (landLineNumber,keyPointMessage,keyPointCount,daysCount,commitTime) VALUES (?,?,?,?,?)";
			number = Integer.valueOf(kCount.getLandLineNumber());

			for (int i = 0; i < list.size(); i++) {
				CountMessge countMessge = list.get(i);
				jdbcTemplate
						.update(sql,
								new Object[] { kCount.getLandLineNumber(),
										countMessge.getKeyPointMessage(),
										countMessge.getKeyPointCount(),
										kCount.getDaysCount(),
										kCount.getCommitTime() });
			}
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		if (result == 1) {
			msg = "";
		} else {
			msg = "信息填写有误";
		}
		JSONObject jsonArr = new JSONObject();
		jsonArr.put("landLineNumber", kCount.getLandLineNumber());
		jsonArr.put("statusCode", result);
		jsonArr.put("errorMessage", msg);
		
		return jsonArr.toString();

	}

}
