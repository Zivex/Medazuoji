package com.capinfo.dictionary.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.dictionary.service.WordbookService;
import com.capinfo.omp.utils.Page;

@Controller
@RequestMapping("/wordbook/wordbookmanage")
public class WordbookController {
	
	@Autowired
	private WordbookService wordbookService;
	
	
	@RequestMapping("/initial.shtml")
	@ResponseBody
	private ModelAndView getSortList() {
		ModelAndView mv = new ModelAndView("/omp/wordbook/initial");
		List<Map<String,Object>> list = wordbookService.getData();
		List<Map<String,Object>> type = wordbookService.getPhoneType();
		mv.addObject("dataList", list);
		mv.addObject("phoneType", type);
		return mv;
	}
	
	
	//话机类型管理
	@RequestMapping("/PhoneType.shtml")
	@ResponseBody
	private ModelAndView getPhoneTypeList() {
		ModelAndView mv = new ModelAndView("/omp/wordbook/PhoneType");
		List<Map<String,Object>> list = wordbookService.getData();
		List<Map<String,Object>> type = wordbookService.getPhoneType();
		mv.addObject("dataList", list);
		mv.addObject("phoneType", type);
		return mv;
	}
	
	
	//键位管理
	@RequestMapping("/MList.shtml")
	@ResponseBody
	private ModelAndView MList(String current,String pyId,String stId,String key,String status) {
		ModelAndView mv = new ModelAndView("/omp/wordbook/MList");
		getMList(mv,current,pyId,stId, key,status);
		return mv;
	}
	
	//话机服务类型管理
	@RequestMapping("/MtypeList.shtml")
	@ResponseBody
	private ModelAndView MtypeList(String current,String serviceName,String status) {
		ModelAndView mv = new ModelAndView("/omp/wordbook/MtypeList");
		getMtypeList(mv,current,serviceName,status);
		return mv;
	}
	
	//话机服务类型管理(展示列表)
	@RequestMapping("/MtypeList1.shtml")
	@ResponseBody
	private ModelAndView MtypeList1(String current,String serviceName,String status) {
		ModelAndView mv = new ModelAndView("/omp/wordbook/MtypeList1");
		getMtypeList(mv,current,serviceName,status);
		return mv;
	}
	
    //话机类型管理
	@RequestMapping("/PtypeList.shtml")
	@ResponseBody
	private ModelAndView PtypeList(String current,String phoneType,String status) {
		ModelAndView mv = new ModelAndView("/omp/wordbook/PtypeList");
		getPtypeList(mv,current,phoneType,status);
		return mv;
	}
	
	//话机类型管理(展示列表)
	@RequestMapping("/PtypeList1.shtml")
	@ResponseBody
	private ModelAndView PtypeList1(String current,String serviceName,String status) {
		ModelAndView mv = new ModelAndView("/omp/wordbook/PtypeList1");
		getPtypeList(mv,current,serviceName,status);
		return mv;
	}
	
	
	
	@RequestMapping("/updServerName.shtml")
	@ResponseBody
	public String updServerName(HttpServletRequest request) {
		String kid = request.getParameter("kid");
		String serverName = request.getParameter("serverName");
		String newServerName = request.getParameter("newServerName");
		if (!StringUtils.isEmpty(serverName)&&StringUtils.isEmpty(newServerName)) {
			//修改
			if (wordbookService.updServerName(kid,serverName)) {
				return "修改成功！！！";
			}else {
				
				return "修改失败！！！";
			}
		}else if (!StringUtils.isEmpty(newServerName)&&StringUtils.isEmpty(serverName)) {
			//添加
			if (wordbookService.addServerName(kid,newServerName)) {
				
				return "添加成功！！！";
			}else {
				
				return "添加失败！！！";
			}
		}
		
		return "操作失败！！！";
	}
	
	
   /**
 * 获取键位信息
 * @param mv
 * @param current
 * @param pyId
 * @param stId
 * @param key
 * @param status
 */
public void getMList(ModelAndView mv,String current,String pyId,String stId,String key,String status) {
		
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		int count = wordbookService.getMListCount(pyId, stId,key, status);
		count = count == 0?1:count;
		Page page = new Page<>(current, count, "10");
		List<Map<String, Object>> entities = wordbookService.getMList(page,pyId,stId,key,status);
		mv.addObject("dataList",entities);
		mv.addObject("DataTotalCount",count);
		mv.addObject("CurrentPieceNum",page.getCurrentPage());
		mv.addObject("PerPieceSize","10");
		mv.addObject("pyId",pyId);
		mv.addObject("stId",stId);
		mv.addObject("key",key);
		mv.addObject("status",status);
	}


/**
 * 获取话机类型信息(居家型,失能型,农商型)
 * @param mv
 * @param current
 * @param serviceName
 * @param status
 */
public void getPtypeList(ModelAndView mv,String current,String phoneType,String status) {
	
	if (StringUtils.isEmpty(current)) {
		current = "1";
	}
	int count = wordbookService.getPtypeCount(phoneType,status);
	count = count == 0?1:count;
	Page page = new Page<>(current, count, "10");
	List<Map<String, Object>> entities = wordbookService.getPtypeList(page,phoneType,status);
	mv.addObject("dataList",entities);
	mv.addObject("DataTotalCount",count);
	mv.addObject("CurrentPieceNum",page.getCurrentPage());
	mv.addObject("PerPieceSize","10");
	mv.addObject("phoneType",phoneType);
	mv.addObject("status",status);
}


/**
 * 获取话机服务类型信息
 * @param mv
 * @param current
 * @param serviceName
 * @param status
 */
public void getMtypeList(ModelAndView mv,String current,String serviceName,String status) {
	
	if (StringUtils.isEmpty(current)) {
		current = "1";
	}
	int count = wordbookService.getMtypeCount(serviceName,status);
	count = count == 0?1:count;
	Page page = new Page<>(current, count, "10");
	List<Map<String, Object>> entities = wordbookService.getMtypeList(page,serviceName,status);
	mv.addObject("dataList",entities);
	mv.addObject("DataTotalCount",count);
	mv.addObject("CurrentPieceNum",page.getCurrentPage());
	mv.addObject("PerPieceSize","10");
	mv.addObject("serviceName",serviceName);
	mv.addObject("status",status);
}





}
