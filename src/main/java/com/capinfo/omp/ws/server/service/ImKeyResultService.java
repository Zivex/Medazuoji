package com.capinfo.omp.ws.server.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ImKeyResultService {
	/**
	 * 执行指令反馈接口
	 * @param Imkey
	 * @return
	 */
	@WebMethod
	public String Imkey(@WebParam(name = "imkey") String Imkey);
}
