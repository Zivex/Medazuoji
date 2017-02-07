package com.capinfo.sonic.web.controller.admin.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import com.capinfo.common.web.parameter.ResourceParameter;
import com.capinfo.common.web.service.ResourceService;
import com.capinfo.common.web.service.RoleService;
import com.capinfo.framework.model.Messages;
import com.capinfo.framework.service.GeneralService;
import com.capinfo.framework.web.view.model.DataListViewModel;

@Controller
@RequestMapping("/admin/sys/resource")
public class ResourceController {
	private static final Log LOGGER = LogFactory.getLog(ResourceController.class);

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private GeneralService generalService;

	@Autowired
	private SecurityCache securityCache;

	/**
	 * 资源管理首页
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/initial.shtml")
	public ModelAndView index(NativeWebRequest request, HttpServletResponse response, ResourceParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/resource/initial");
		String jsonTree = getResourceTree().toString();
		mv.addObject("jsonTree", jsonTree);
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 资源管理树
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/tree.shtml")
	public ModelAndView tree(NativeWebRequest request, HttpServletResponse response, ResourceParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/resource/tree");
		String jsonTree = getResourceTree().toString();
		mv.addObject("jsonTree", jsonTree);
		return mv;
	}

	/**
	 * 资源管理列表页
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/list.shtml")
	public ModelAndView list(NativeWebRequest request, HttpServletResponse response, ResourceParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/resource/list");
		getDataList(parameter, mv);
		return mv;
	}

	/**
	 * 获取资源列表
	 * 
	 * @param parameter
	 * @param mv
	 */
	private void getDataList(ResourceParameter parameter, ModelAndView mv) {
		List entities = new ArrayList();
		int totalCount = this.resourceService.getTotalCount(parameter);
		if (totalCount > 0) {
			entities = resourceService.getList(parameter, totalCount, parameter.getCurrentPieceNum());
		}
		mv.addAllObjects(new DataListViewModel("dataList", entities, totalCount, this.resourceService, parameter).buildViewModel());
	}

	/**
	 * 资源树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getResourceTree.shtml")
	private JSONArray getResourceTree() {
		List<Resource> resourceList = resourceService.getAllResources();
		JSONArray jsArray = new JSONArray();
		for (Resource resource : resourceList) {
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerPropertyExclusions(Resource.class, new String[] { "roles", "parent", "children" });
			jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONObject jsb = JSONObject.fromObject(resource, jsonConfig);
			jsArray.add(jsb);
		}
		return jsArray;
	}

	/**
	 * 资源详情
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/detail.shtml")
	public ModelAndView detail(NativeWebRequest request, HttpServletResponse response, ResourceParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/resource/detail");
		if (parameter.getEntity().getId() != null) {
			Resource resource = resourceService.getResourceById(parameter.getEntity().getId());
			parameter.setEntity(resource);
		}
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 资源编辑页面
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/form.shtml", method = RequestMethod.GET)
	public ModelAndView form(NativeWebRequest request, HttpServletResponse response, ResourceParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/resource/form");
		if (parameter.getEntity().getId() != null) {
			Resource resource = resourceService.getResourceById(parameter.getEntity().getId());
			parameter.setEntity(resource);
		}
		mv.addObject("command", parameter);
		mv.addObject("roleList", roleService.getAllRoles());
		return mv;
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
	public JSONObject saveOrupdate(NativeWebRequest request, HttpServletResponse response, Model model,
			@Valid @ModelAttribute("command") ResourceParameter parameter, BindingResult result, RedirectAttributes redirectAttrs) {
		Messages messages = new Messages();
		// 基本验证
		if (result.hasErrors()) {
			messages.setSuccess(false);
			messages.setMessage("操作资源失败,验证未通过！");
		} else {
			boolean suc = resourceService.saveOrUpdateResource(parameter);
			String info = suc == true ? "操作资源成功" : "操作资源失败";
			messages.setSuccess(suc);
			messages.setMessage(info);
			if (suc) {
				refreshSecurityResource();
			}
		}
		JSONObject json = JSONObject.fromObject(messages);
		return json;
	}

	@RequestMapping("/delete.shtml")
	@ResponseBody
	public JSONObject delete(NativeWebRequest request, HttpServletResponse response, ResourceParameter parameter, RedirectAttributes redirectAttrs) {
		String info = "";
		Messages messages = new Messages();
		// 基本验证
		Long id = parameter.getEntity().getId();
		if (id == null || id.equals(0L)) {
			info = "删除资源失败,参数不全！";
			messages = new Messages(false, info, Messages.MessageType.danger);
		} else {
			boolean suc = resourceService.deleteResource(parameter.getEntity());
			if (suc) {
				info = "删除资源成功!";
				messages = new Messages(suc, info, Messages.MessageType.success);
				refreshSecurityResource();
			} else {
				info = "删除资源失败!";
				messages = new Messages(suc, info, Messages.MessageType.danger);
			}
		}
		JSONObject json = JSONObject.fromObject(messages);
		return json;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Set.class, "entity.roles", new CustomCollectionEditor(Set.class) {
			@Override
			protected Object convertElement(Object element) {
				Long id = null;
				if (element instanceof String && StringUtils.isNotBlank((String) element)) {
					try {
						id = Long.parseLong((String) element);
					} catch (NumberFormatException e) {
						LOGGER.error("Element was " + ((String) element));
						LOGGER.error(e.getMessage(), e);
					}
				} else if (element instanceof Long) {
					// From the database 'element' will be a Long
					id = (Long) element;
				}

				// 一般绑定ID即可，但考虑到刷新SecurityResource需要取出ROLE的全部数据，故不能用次方法
				// 如果用generalService.getObjectById(Role.class,
				// id);会在下次saveOrupdateResource引发下列错误
				// a different object with the same identifier value was already
				// associated with the session
				// return id != null ? new Role(id) : null;
				Role role = null;
				if (id != null) {
					role = generalService.getObjectById(Role.class, id);
				}
				return role;
			}
		});
	}

	private void refreshSecurityResource() {
		securityCache.refreshResource();
	}

}
