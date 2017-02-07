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

public class ClientGetOldMenInfo {
	public static ImKey getZjnumber(String cardId) throws MalformedURLException, ServiceException{
		 
		String endpoint = "http://yewu.laoling.bjmzj.gov.cn/assistant_platform/services/CardPersonMessageWsService?wsdl";
		String namespace= "http://model.card.ws.platform.assistant.capinfo.com";

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) 
		service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		// call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "getCardPersonMessageByIDNumber"));
		call.addParameter("idCard", XMLType.XSD_STRING, ParameterMode.IN);// 参数名	
		call.setReturnType(XMLType.XSD_STRING);//设置返回类型 
		call.setUseSOAPAction(true);
				
		String res;
		ImKey imk =new ImKey();
		try {
			res = (String) call.invoke(new Object[] { cardId});
			System.out.print("返回的数据:"+res);
			
			
			
			
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
	
	
	
	public static void main(String[] args) throws RemoteException, MalformedURLException, ServiceException {
		String IP = PropertiesUtil.getStringByKey("WSCARD");
		String endpoint = IP;
		String namespace= "http://model.card.ws.platform.assistant.capinfo.com";
        String idCard="110101191603064524";
        //创建一个服务（service）调用（call）
		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		org.apache.axis.client.Call call = (org.apache.axis.client.Call) 
		service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		//call.setOperationName("AliassayHello");//方法名

		call.setOperationName(new QName(namespace, "getCardPersonMessageByIDNumber"));
		call.addParameter("idCard", XMLType.XSD_STRING, ParameterMode.IN);// 参数名	
		call.setReturnType(XMLType.XSD_INT);//设置返回类型 
		call.setUseSOAPAction(true);
				
		String res;
		res = (String) call.invoke(new Object[] {idCard});
		System.out.print("返回的数据:"+res);
		
		
	}
	
}
