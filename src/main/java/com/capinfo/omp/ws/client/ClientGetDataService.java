package com.capinfo.omp.ws.client;


import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.encoding.XMLType;

import com.capinfo.omp.utils.PropertiesUtil;
import com.capinfo.omp.ws.model.CountMessge;
import com.capinfo.omp.ws.model.ImKey;
import com.google.gson.Gson;


public class ClientGetDataService {
	
	public ImKey sendOrder(String str) throws Exception {
		String IP= PropertiesUtil.getStringByKey("WSIP");
		String endpoint = "http://"+IP+"/PilotMS/GetDataServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) service
				.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName(new QName(namespace, "getTaskData"));
		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);
		String strs = str.replaceAll("\"\\[\\{", "\\[\\{");
		String gos = strs.replaceAll("\\}\\]\"", "\\}\\]");
		String ajson = gos.replaceAll("e\":0", "e\":\"0\"");
		//String bjson = ajson.replaceAll("e\":2", "e\":\"2\"");
		//String cjson = ajson.replaceAll("s\":0", "s\":\"0\"");
		String ss = ajson.replaceAll("\"", "'");
		
		String res = (String) call.invoke(new Object[] { ss});
		System.out.print(res);
		String gn = res.replaceAll("\\[\\{", "\\{");
		String gg = gn.replaceAll("\\}\\]", "\\}");
		Gson gson =new Gson();
		ImKey imk = gson.fromJson(gg, ImKey.class);
//		System.out.println(imk.getStatusCode());
//		System.out.println(imk.getErrorMessage());
//		System.out.println(imk.getGenerateSerialNumber());
		return imk;
	}
	
	public ImKey sendOrder1(String str) throws Exception {
		String endpoint = "http://202.104.122.115:9090/PilotMS/GetDataServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) service
				.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName(new QName(namespace, "getTaskData"));
		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);
		String ss = str.replaceAll("\"", "'");
		
		String res = (String) call.invoke(new Object[] { ss});
		System.out.print(res);
		String gn = res.replaceAll("\\[\\{", "\\{");
		String gg = gn.replaceAll("\\}\\]", "\\}");
		Gson gson =new Gson();
		ImKey imk = gson.fromJson(gg, ImKey.class);
//		System.out.println(imk.getStatusCode());
//		System.out.println(imk.getErrorMessage());
//		System.out.println(imk.getGenerateSerialNumber());
		return imk;
	}
	
}
