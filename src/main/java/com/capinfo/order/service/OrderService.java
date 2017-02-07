package com.capinfo.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.capinfo.omp.utils.Page;
import com.capinfo.omp.ws.model.ImKey;

@Service
public interface OrderService {

	int getOrderCount(String name, String idCard, String zjNumber, String county, String street, String community,
			String send_flag, String execute_flag);

	List<Map<String, Object>> getOrderList(Page page, String name, String idCard, String zjNumber, String county,
			String street, String community, String send_flag, String execute_flag);
	
	String sendOrder(String id);
	
	void toupdete(String string,ImKey imkey);

	void delect(String id);

	String checkDeBatchSendInstructions();

	boolean getcount();
	
    boolean upsendOrder(String id) throws Exception;
    
    void upoder(String id);

    void upMessg(ImKey imKey,String id);
    
    List<Map<String, Object>> getOList(String id);
    
    void resultOrder(ImKey imKey,String id,String username);
    
    String RequestZJ(String zj);
    
    
}
