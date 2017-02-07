package com.capinfo.omp.ws.client;
import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

import org.apache.axis.encoding.XMLType;

import com.capinfo.omp.utils.PropertiesUtil;

public class ClientUpdateLandNumberService {

	public static void main(String[] args) throws Exception {
		String IP= PropertiesUtil.getStringByKey("WSIP");
		String endpoint = "http://"+IP+"/PilotMS/GetDataServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) service
				.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		// call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "updateLandNumber"));
		
		

		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);

		String str="{'NewLandLineNumber':'333','OldLandLineNumber':'444'}";
		
		String res = (String) call.invoke(new Object[] { str});
		System.out.print(res);
	}
	
}
