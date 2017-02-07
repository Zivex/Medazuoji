package com.capinfo.omp.ws.client;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.XMLType;

import com.capinfo.omp.ws.model.ImKey;
import com.google.gson.Gson;

public class ClientProviderInfoService {
	public static void main(String[] args) throws ServiceException, MalformedURLException {
		String endpoint = "http://192.168.20.46:8080/assistant-platform-latest/services/ProviderInfoWsService?wsdl";
		String namespace="http://model.provider.ws.platform.assistant.capinfo.com";
		String key = "P000-0001-0003-0004";
		String modifyDate = "2016-6-24 14:20:80";
		String providerId = "6761";
		String shopType = "0";
        //创建一个服务（service）调用（call）
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) 
		service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		//call.setOperationName("AliassayHello");//方法名
		call.setOperationName(new QName(namespace, "getProviderInfo"));
		call.addParameter("key", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		call.addParameter("modifyDate", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("providerId", XMLType.XSD_STRING, ParameterMode.IN);
		call.addParameter("shopType", XMLType.XSD_STRING, ParameterMode.IN);
		call.setReturnType(XMLType.XSD_INT);//设置返回类型 
		call.setUseSOAPAction(true);
				
		String res;
		try {
			res = (String) call.invoke(new Object[] {key,modifyDate,providerId,shopType});
			System.out.print("返回的数据:"+res);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
		
}
