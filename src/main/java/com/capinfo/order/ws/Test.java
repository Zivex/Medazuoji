package com.capinfo.order.ws;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import com.capinfo.assistant.platform.ws.card.model.CardPersonMessageBack;
import com.capinfo.assistant.platform.ws.card.service.CardPersonMessageWsServiceProxy;

public class Test {
	public static void main(String[] args) {
		String idCard = "110101191603064524";
		CardPersonMessageWsServiceProxy d = new CardPersonMessageWsServiceProxy();
	    CardPersonMessageBack m;
		try {
			m = d.getCardPersonMessageByIDNumber(idCard);
			System.out.println("老人信息属性:"+m.getBankCard()+m.getHouseholdAddress());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	}
	
		    
}
