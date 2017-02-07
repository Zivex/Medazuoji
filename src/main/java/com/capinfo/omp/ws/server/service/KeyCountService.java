package com.capinfo.omp.ws.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface KeyCountService {
	/**
	 * 统计老人话机按键统计推送接口
	 * @param KeyCount
	 * @return
	 */
	@WebMethod
	public String KeyCout(@WebParam(name = "kcount") String KeyCount);
	
}
