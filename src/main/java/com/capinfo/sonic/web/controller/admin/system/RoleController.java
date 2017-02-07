package com.capinfo.sonic.web.controller.admin.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.capinfo.common.model.Resource;
import com.capinfo.common.model.Role;
import com.capinfo.common.security.cache.SecurityCache;
import com.capinfo.common.web.parameter.RoleParameter;
import com.capinfo.common.web.service.ResourceService;
import com.capinfo.common.web.service.RoleService;
import com.capinfo.framework.model.Messages;
import com.capinfo.framework.web.view.model.DataListViewModel;

/**
 * 角色管理控制器
 * 
 * @author lp
 * 
 */
@Controller
@RequestMapping("/admin/sys/role")
public class RoleController {

	private static final Log LOGGER = LogFactory.getLog(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private SecurityCache securityCache;

	/**
	 * 角色管理首页
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/initial.shtml")
	public ModelAndView index(NativeWebRequest request, HttpServletResponse response, RoleParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/role/initial");
		List entities = new ArrayList();
		int totalCount = this.roleService.getTotalCount(parameter);
		if (totalCount > 0) {
			entities = roleService.getList(parameter, totalCount, parameter.getCurrentPieceNum());
		}
		mv.addAllObjects(new DataListViewModel("dataList", entities, totalCount, this.roleService, parameter).buildViewModel());
		return mv;
	}

	/**
	 * 角色管理列表
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/list.shtml")
	public ModelAndView list(NativeWebRequest request, HttpServletResponse response, RoleParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/role/list");
		List entities = new ArrayList();
		int totalCount = this.roleService.getTotalCount(parameter);
		if (totalCount > 0) {
			entities = roleService.getList(parameter, totalCount, parameter.getCurrentPieceNum());
		}
		mv.addAllObjects(new DataListViewModel("dataList", entities, totalCount, this.roleService, parameter).buildViewModel());
		return mv;
	}

	/**
	 * 角色详情
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/detail.shtml")
	public ModelAndView detail(NativeWebRequest request, HttpServletResponse response, RoleParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/role/detail");
		if (parameter.getEntity().getId() != null) {
			Role role = roleService.getRoleById(parameter.getEntity().getId());
			String resourceTree = getTreeResourceTree(role);
			parameter.setEntity(role);
			parameter.setResourceTree(resourceTree);
		}
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 角色管理页面
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/form.shtml", method = RequestMethod.GET)
	public ModelAndView form(NativeWebRequest request, HttpServletResponse response, RoleParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/role/form");
		if (parameter.getEntity().getId() != null) {
			Role role = roleService.getRoleById(parameter.getEntity().getId());
			parameter.setEntity(role);
		}
		mv.addObject("command", parameter);
		mv.addObject("resourceList", resourceService.getAllResources());
		mv.addObject("resourceTree", getResourceTree(parameter.getEntity()));
		return mv;
	}

	private String getResourceTree() {
		List<Resource> resourceList = resourceService.getAllResources();
		JSONArray jsArray = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerPropertyExclusions(Resource.class, new String[] { "type", "value", "position", "roles", "parent", "children" });
		for (Resource resource : resourceList) {
			try {
				JSONObject jsb = JSONObject.fromObject(resource, jsonConfig);
				jsArray.add(jsb);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}

		}
		return jsArray.toString();
	}

	private String getResourceTree(Role role) {
		List<Resource> resourceList = resourceService.getAllResources();
		List<Resource> resList = new ArrayList<Resource>();
		Set<Resource> checkedResource = null;
		if (null != role && null != role.getId()) {
			checkedResource = role.getResources();
			if (null != checkedResource && !checkedResource.isEmpty()) {
				for (Resource resource : resourceList) {
					if (checkedResource.contains(resource)) {
						resource.setChecked(true);
					}
					Resource entity = new Resource();
					try {
						PropertyUtils.copyProperties(entity, resource);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
					resList.add(entity);
				}
			} else {
				resList = resourceList;
			}
		} else {
			resList = resourceList;
		}
		JSONArray jsArray = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerPropertyExclusions(Resource.class, new String[] { "type", "value", "position", "roles", "parent", "children" });
		//
		for (Resource resource : resList) {
			try {
				JSONObject jsb = JSONObject.fromObject(resource, jsonConfig);
				jsArray.add(jsb);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}

		}
		return jsArray.toString();
	}

	private String getTreeResourceTree(Role role) {
		Set<Resource> checkedResource = role.getResources();
		JSONArray jsArray = new JSONArray();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerPropertyExclusions(Resource.class, new String[] { "type", "value", "position", "roles", "parent", "children" });
		//
		if (null != checkedResource) {
			for (Resource resource : checkedResource) {
				try {
					JSONObject jsb = JSONObject.fromObject(resource, jsonConfig);
					jsArray.add(jsb);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}

			}
		}

		return jsArray.toString();
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param parameter
	 *            使用<code>@ModelAttribute</code>才能使Valid生效
	 * @param result
	 * @param redirectAttrs
	 * @return
	 */
	@RequestMapping(value = "/save_or_update.shtml", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject saveOrUpdate(NativeWebRequest request, HttpServletResponse response, Model model, @Valid @ModelAttribute("command") RoleParameter parameter,
			BindingResult result, RedirectAttributes redirectAttrs) {
		Messages messages = new Messages();
		// 基本验证
		if (result.hasErrors()) {
			messages.setSuccess(false);
			messages.setMessage("操作角色失败,验证未通过！");
		} else {
			Long id = parameter.getEntity().getId();
			String opt = id == null ? "添加" : "修改";
			boolean suc = roleService.saveOrUpdateRole(parameter);
			String info = suc == true ? opt + "角色成功" : opt + "角色失败";
			messages.setSuccess(suc);
			messages.setMessage(info);
			if (suc) {
				refreshSecurityResource();
			}
		}

		JSONObject json = JSONObject.fromObject(messages);
		return json;
	}

	/**
	 * 删除角色
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @param redirectAttrs
	 * @return
	 */
	@RequestMapping("/delete.shtml")
	@ResponseBody
	public JSONObject delete(NativeWebRequest request, HttpServletResponse response, @Valid @ModelAttribute("command") RoleParameter parameter,
			RedirectAttributes redirectAttrs) {
		Messages messages = new Messages();
		String info = "";
		// 基本验证
		Long id = parameter.getEntity().getId();
		if (id == null || id.equals(0L)) {
			info = "删除角色失败,参数不全！";
			messages.setSuccess(false);
			messages.setMessage(info);
		} else {
			boolean suc = roleService.deleteRole(parameter.getEntity());
			info = suc == true ? "删除角色成功!" : "删除角色失败!";
			messages.setSuccess(suc);
			messages.setMessage(info);
		}
		JSONObject json = JSONObject.fromObject(messages);
		return json;
	}

	private void refreshSecurityResource() {
		securityCache.refreshResource();
	}
}
