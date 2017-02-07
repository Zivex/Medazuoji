package com.capinfo.omp.ws.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import net.sf.json.JSONArray;

import org.apache.axis.encoding.XMLType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;


public class Test {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args){
		/*String endpoint = "http://202.104.122.115:9090/PilotMS/GetDataServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";


		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) service
				.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		// call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "getTaskData"));
		
		

		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);

		String str="{'generateSerialNumber':'201611251722496220','instructionsType':'1','changeType':'0','landLineNumber':'323244','Name':'王五','residenceCommunity':'11329','userType':'2','changeTimes':'0','keyPointMessage':[{'M5':'13552898382','M2':'64941122','M1':'18001393853','M3':'13011889085','M4':'13011889085','M8':'13810623105','M6':'13552898382','M9':'13810623105','M7':'13693309175','M10':'62524415','M14':'96003','M13':'96003','M11':'62524415','M12':'13911738934','M15':'96003','M16':'96003'}]}";
		
		String res = (String) call.invoke(new Object[] { str});
		System.out.print(res);*/
		String carid = "111,1111,111,111";
		String[] strs=carid.split(",");
		for(int i=0,len=strs.length;i<len;i++){
			String g = strs[i].toString();
			System.out.println(g);
		  System.out.println(strs[i].toString());
		}
		
	}
}
