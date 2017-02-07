package com.capinfo.sonic.web.controller.admin.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.common.model.DictionaryImpl;
import com.capinfo.common.model.DictionarySortImpl;
import com.capinfo.common.web.parameter.DictionaryParameter;
import com.capinfo.common.web.service.DictionaryManageService;
import com.capinfo.framework.model.Messages;
import com.capinfo.framework.model.Messages.MessageType;
import com.capinfo.framework.model.dictionary.Dictionary;
import com.capinfo.framework.model.dictionary.DictionarySort;
import com.capinfo.framework.web.view.model.DataListViewModel;

@Controller
@RequestMapping("/admin/sys/dic")
public class DictionaryController {

	@Autowired
	private DictionaryManageService dictionaryManageService;

	/**
	 * 字典分类管理
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/sort.shtml")
	public ModelAndView sort(DictionaryParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/dic/sort");
		getSortList(parameter, mv);
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 字典分类列表
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/sort_list.shtml")
	public ModelAndView dicSortList(DictionaryParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/dic/inc_sort_list");
		getSortList(parameter, mv);
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 获取字典分类列表
	 * 
	 * @param parameter
	 * @param mv
	 */
	private void getSortList(DictionaryParameter parameter, ModelAndView mv) {
		List entities = new ArrayList();
		int totalCount = this.dictionaryManageService.getDictionarySortTotal(parameter);
		if (0 != totalCount) {
			entities = this.dictionaryManageService.getDictionarySortList(parameter, totalCount);
		}
		mv.addAllObjects(new DataListViewModel("dataList", entities, totalCount, this.dictionaryManageService, parameter).buildViewModel());
	}

	/**
	 * 字典分类编辑页面
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/form_sort.shtml", method = RequestMethod.GET)
	public ModelAndView saveOrupdateSortInto(DictionaryParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/dic/inc_sort");
		if (null != parameter.getId()) {
			DictionarySort dictionarySort = (DictionarySort) this.dictionaryManageService.getGeneralService().getObjectById(DictionarySortImpl.class,
					parameter.getId());
			BeanUtils.copyProperties(dictionarySort, parameter);
		}
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 字典分类删除
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/form_sort_del.shtml")
	@ResponseBody
	public JSONObject deleteSortInto(HttpServletRequest request, DictionaryParameter parameter) {
		DictionarySortImpl saterials = dictionaryManageService.getGeneralService().getObjectById(DictionarySortImpl.class,
				(Long.valueOf(request.getParameter("id"))));
		saterials.setEnabled(false);
		Boolean falg = false;
		if (saterials != null) {
			dictionaryManageService.getGeneralService().delete(saterials);
			falg = true;
		}
		String message = falg == true ? "操作成功！" : "操作失败！";
		Messages messages = new Messages(falg, message);
		JSONObject json = JSONObject.fromObject(messages);
		return json;

	}

	/**
	 * 字典分类提交
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/save_or_update_sort.shtml", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject saveOrupdateSort(NativeWebRequest request, HttpServletResponse response, DictionaryParameter parameter) {
		Long id = parameter.getId();
		String info = "";
		boolean suc = true;
		MessageType level = Messages.MessageType.success;
		// 基本验证
		String name = parameter.getName();
		String code = parameter.getCode();
		if (StringUtils.isBlank(name) || StringUtils.isBlank(code)) {
			suc = false;
			info = "操作失败,参数不全！";
			level = Messages.MessageType.warning;
		} else {
			if (null != id) {
				suc = dictionaryManageService.modifyDictionarySort(parameter);
			} else {
				suc = dictionaryManageService.addDictionarySort(parameter);
			}
			info = suc == true ? "操作成功!" : "操作失败!";
			level = suc == true ? Messages.MessageType.success : Messages.MessageType.warning;
		}
		Messages messages = new Messages(suc, info, level);
		JSONObject json = JSONObject.fromObject(messages);
		return json;
	}

	/**
	 * 字典项页面
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/item.shtml")
	public ModelAndView dicItem(NativeWebRequest request, HttpServletResponse response, DictionaryParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/dic/item");
		getItemList(parameter, mv);
		Long sortId = parameter.getSortId();
		DictionarySort dictionarySort = dictionaryManageService.getGeneralService().getObjectById(DictionarySortImpl.class, sortId);
		mv.addObject("dictionarySort", dictionarySort);
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 字典列表
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/item_list.shtml")
	public ModelAndView dicItemList(DictionaryParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/dic/inc_item_list");
		getItemList(parameter, mv);
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 获取字典分类列表
	 * 
	 * @param parameter
	 * @param mv
	 */
	private void getItemList(DictionaryParameter parameter, ModelAndView mv) {
		Long sortId = parameter.getSortId();
		List entities = new ArrayList();
		int totalCount = this.dictionaryManageService.getDictionaryTotal(parameter);
		if (0 != totalCount) {
			entities = this.dictionaryManageService.getDictionaryList(parameter, totalCount);
		}
		mv.addAllObjects(new DataListViewModel("dataList", entities, totalCount, this.dictionaryManageService, parameter).buildViewModel());
	}

	/**
	 * 字典项编辑
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/form_item.shtml", method = RequestMethod.GET)
	public ModelAndView saveOrupdateItemInto(NativeWebRequest request, HttpServletResponse response, DictionaryParameter parameter) {
		ModelAndView mv = new ModelAndView("/admin/sys/dic/inc_item");
		if (parameter.getId() != null) {
			Dictionary dictionary = (Dictionary) this.dictionaryManageService.getGeneralService().getObjectById(DictionaryImpl.class, parameter.getId());
			BeanUtils.copyProperties(dictionary, parameter);
		}
		mv.addObject("command", parameter);
		return mv;
	}

	/**
	 * 字典项编辑提交
	 * 
	 * @param request
	 * @param response
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/save_or_update_item.shtml", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject saveOrupdateItem(NativeWebRequest request, HttpServletResponse response, DictionaryParameter parameter) {
		Long id = parameter.getId();
		String info = "";
		boolean suc = true;
		MessageType level = Messages.MessageType.success;
		// 基本验证
		String name = parameter.getName();
		String desc = parameter.getDescription();
		if (("").equals(name) || ("").equals(desc)) {
			suc = false;
			info = "操作失败,参数不全！";
			level = Messages.MessageType.warning;
		} else {
			if (null != id) {
				suc = dictionaryManageService.modifyDictionary(parameter);
			} else {
				suc = dictionaryManageService.addDictionary(parameter);
			}
			info = suc == true ? "操作成功!" : "操作失败!";
			level = suc == true ? Messages.MessageType.success : Messages.MessageType.warning;
		}
		Messages messages = new Messages(suc, info, level);
		JSONObject json = JSONObject.fromObject(messages);
		return json;
	}

}
