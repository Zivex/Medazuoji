package com.capinfo.omp.ws.client;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.XMLType;

import com.capinfo.omp.utils.PropertiesUtil;
import com.capinfo.omp.ws.model.ImKey;
import com.google.gson.Gson;

public class ClientGetLandNumberService {
	
	
	
	public static ImKey getZjnumber(String str) throws MalformedURLException, ServiceException{
		String IP= PropertiesUtil.getStringByKey("WSIP");
		String endpoint = "http://"+IP+"/PilotMS/GetLandNumberServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) 
		service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		// call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "getLandNumber"));
		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名	
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);
				
		String res;
		ImKey imk =new ImKey();
		try {
			res = (String) call.invoke(new Object[] { str});
			System.out.print(res);
			String gn = res.replaceAll("\\[\\{", "\\{");
			String gg = gn.replaceAll("\\}\\]", "\\}");
			Gson gson =new Gson();
			imk = gson.fromJson(gg, ImKey.class);
			System.out.println("同步老人话机返回结果:"+res); 
//			System.out.println(imk.getStatusCode());
//			System.out.println(imk.getErrorMessage());
//			System.out.println(imk.getGenerateSerialNumber());		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imk;
		
	}
	
	

	public static void main(String[] args) throws Exception {
		String endpoint = "http://127.0.0.1:9090/PilotMS/GetLandNumberServiceImplPort?wsdl";
		String namespace="http://service.port.pilot.com/";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) service
				.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		// call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "getLandNumber"));
		
		

		call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);// 参数名
		
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);

		String str="{'landLineNumber':'333,444'}";
		
		String res = (String) call.invoke(new Object[] { str});
		System.out.print(res);
	}
	
	
	
	
	
}
