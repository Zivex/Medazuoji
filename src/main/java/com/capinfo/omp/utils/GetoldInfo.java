package com.capinfo.omp.utils;

import com.capinfo.assistant.platform.ws.card.model.CardPersonMessageBack;
import com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceProxy;

public class GetoldInfo {

	public static void main(String[] args) {
//	    DisabilityAccountWsServiceProxy dis=new DisabilityAccountWsServiceProxy();
	    CardPersonMessageWsServiceProxy d = new CardPersonMessageWsServiceProxy();
	    try{
	      CardPersonMessageBack m = d.getCardPersonMessageByIDNumber("110101191603064524");
	      System.out.println("老人信息属性："+m.getBankCard()+"---老人信息属性==："+m.getContacter());
	      
	    }catch(Exception ex){
	      ex.printStackTrace();
	    }
	  }
	
	
	
}
