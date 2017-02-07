package com.capinfo.omp.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface SystemLogs {
	
	List<Map<String, Object>> list ();
	
	List<Map<String, Object>> getlistCount(String county, String street, String community,String otype,String stimes,String etimes);
}
