package com.capinfo.omp.ws.client;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.encoding.XMLType;

import com.capinfo.omp.utils.PropertiesUtil;
import com.capinfo.omp.ws.model.ImKey;
import com.google.gson.Gson;

public class ClientGetVoiceDataService {

	public ImKey svoice(String json) throws Exception {
		String IP= PropertiesUtil.getStringByKey("WSIP");
		String endpoint = "http://"+IP+"/PilotMS/GetVoiceDataServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) service
				.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		// call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "getTaskVoiceData"));
		
		

		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);

		//String str="{'generateSerialNumber':'1009','name':'辣','residenceCommunity':'5209','landLineNumber':'07773','voiceFIleId':'lol','voiceFileAddress':'kklol','executeType':'2','startTime':'2016-11-2 16:52','endTime':'2016-12-23 12:13'}";
		String strs = json;
		String ss = strs.replaceAll("\"", "'");
		String res = (String) call.invoke(new Object[] { ss});
		String resb = res.replaceAll("\\[", "");
		String result = resb.replaceAll("\\]", "");
		Gson gson =new Gson();
		ImKey imk = gson.fromJson(result, ImKey.class);
		System.out.print(result);
		return imk;
	}
	
}
