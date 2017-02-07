package com.capinfo.sonic.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.common.model.SystemUser;
import com.capinfo.common.web.service.ResourceService;
import com.capinfo.common.web.service.SystemUserService;
import com.capinfo.framework.service.GeneralService;
import com.capinfo.framework.web.parameter.BaseParameter;

/**
 * 
 * 后台初始控制
 * 
 * @author lengpeng
 * 
 */
@SessionAttributes("eccomm_admin")
@Controller
public class AdminController {

	private static final Log LOGGER = LogFactory.getLog(AdminController.class);

	@Autowired
	private ResourceService resourceService;
	@Autowired
	private SystemUserService systemUserService;
	@Autowired
	private GeneralService generalService;

	@RequestMapping("/index.shtml")
	public String index(NativeWebRequest request, HttpServletResponse response) {
		return "redirect:/admin/index.shtml";
	}

	/**
	 * 后台首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/index.shtml")
	public ModelAndView admin_index(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("eccomm_admin") SystemUser user) {
		ModelAndView mv = new ModelAndView("/admin/index");
		return mv;
	}

	/**
	 * 系统管理首页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/admin/sys/index.shtml")
	public ModelAndView sys(NativeWebRequest request, HttpServletResponse response, BaseParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/index");
		mv.addObject("command", parameter);

		return mv;
	}

}
