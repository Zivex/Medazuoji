package com.capinfo.omp.utils;
import org.springframework.beans.factory.annotation.Autowired;

import com.capinfo.omp.service.OldService;

public  class LogRecord {
	@Autowired 
	private static OldService oldService;
	
	public static void logger(String type,String content,String createdate,String creater, String state) {
		oldService.saveLogger(type, content, creater, state);
	}
}
