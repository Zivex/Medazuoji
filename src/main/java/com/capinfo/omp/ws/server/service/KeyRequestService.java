package com.capinfo.omp.ws.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface KeyRequestService {
	/**
	 * 指令请求接口
	 * @param KeyRequest
	 * @return
	 */
	@WebMethod
	public String KeyRequest(@WebParam(name = "keyrequest") String KeyRequest);
	
    
}
