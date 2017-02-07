package com.capinfo.sonic.web.controller.admin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.common.web.service.SystemUserService;

/**
 * 
 * 登录控制
 * 
 * @author lengpeng
 * 
 */
@Controller
public class LoginController {
	@Autowired
	private SystemUserService systemUserService;

	@RequestMapping("/login.shtml")
	public ModelAndView login_es(NativeWebRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/admin/login");
		return mv;
	}

	@RequestMapping("/access_denied.shtml")
	public ModelAndView accessDenied(NativeWebRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/admin/accessDenied");
		AccessDeniedException ex = (AccessDeniedException) request.getAttribute(WebAttributes.ACCESS_DENIED_403, RequestAttributes.SCOPE_REQUEST);
		mv.addObject("errorDetails", ex.getMessage());
		return mv;
	}

	@RequestMapping("/sessione_exceeded.shtml")
	public ModelAndView sessionExceeded(NativeWebRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/admin/sessionExceeded");
		return mv;
	}

	@RequestMapping("/session_expired.shtml")
	public ModelAndView sessionExpired(NativeWebRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/admin/sessionExpired");
		return mv;
	}

}
