package com.capinfo.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.opensaml.util.resource.HttpResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.common.model.SystemUser;
import com.capinfo.omp.service.OldService;
import com.capinfo.omp.utils.JsonUtil;
import com.capinfo.omp.utils.Page;
import com.capinfo.omp.ws.client.ClientGetDataService;
import com.capinfo.omp.ws.client.ClientGetVoiceDataService;
import com.capinfo.omp.ws.model.ImKey;
import com.capinfo.order.service.OrderService;
import com.capinfo.order.service.impl.OrderServiceImpl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/order")
@SessionAttributes("eccomm_admin")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired 
	private OldService oldService;

	@RequestMapping("/orderManage/initial.shtml")
	public ModelAndView initial(String current, String name, String idCard, String zjNumber, String county, String street, String community, String send_flag, String execute_flag) {
		ModelAndView mv = new ModelAndView("/omp/order/initial");
		getList(mv, current, name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		return mv;
	}

	@RequestMapping("/orderManage/list.shtml")
	public ModelAndView listt(String current, String name, String idCard, String zjNumber, String county, String street, String community, String send_flag, String execute_flag) {
		ModelAndView mv = new ModelAndView("/omp/order/list");
		getList(mv, current, name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		return mv;
	}

	public void getList(ModelAndView mv, String current, String name, String idCard, String zjNumber, String county, String street, String community, String send_flag, String execute_flag) {
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		int count = orderService.getOrderCount(name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		count = count == 0 ? 1 : count;
		Page page = new Page<>(current, count, "10");
		List<Map<String, Object>> entities = orderService.getOrderList(page, name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		mv.addObject("dataList", entities);
		mv.addObject("DataTotalCount", count);
		mv.addObject("CurrentPieceNum", page.getCurrentPage());
		mv.addObject("PerPieceSize", "10");
		mv.addObject("name", name);
		mv.addObject("idCard", idCard);
		mv.addObject("zjNumber", zjNumber);
		mv.addObject("county", county);
		mv.addObject("street", street);
		mv.addObject("community", community);
		mv.addObject("execute_flag", execute_flag);
		mv.addObject("send_flag", send_flag);
	}

	/**
	 * 发送指令
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/orderManage/sendOrder.shtml")
	@ResponseBody
	public String sendOrder(String ids,@ModelAttribute("eccomm_admin") SystemUser user) throws Exception {
		String username = user.getLogonName();
		int i = 0;
		ClientGetDataService c = new ClientGetDataService();
		if (!StringUtils.isEmpty(ids)) {
			String[] split = ids.split(",");
			for (String id : split) {
				String json = orderService.sendOrder(id);
				ImKey imKey = c.sendOrder(json);

				if ("1".equals(imKey.getStatusCode())) {
					orderService.resultOrder(imKey, id,username);
					i++;
				}
				if (i != 0) {
					// 全部成功后修改指令状态
						orderService.toupdete(id,imKey);
						
						orderService.upMessg(imKey, id);
                        
					
				}
			}
			if (i !=0) {
				return "发送成功";
			}
			return "发送失败";

		}

		return null;
	}

	@RequestMapping("/orderManage/delect.shtml")
	public String appointmentsend(String uid) {
			orderService.delect(uid);
			return "发送成功";
		
	}

	@RequestMapping("/orderManage/batchSendInstructions.shtml")
	public void batchSendInstructions() throws Exception {
		System.out.println("batchSendInstructions:定时器自动执行发送指令程序，间隔时间5分钟");
		if (orderService.getcount()) {
			String id = orderService.checkDeBatchSendInstructions();
			sendOrder(id, null);
		}
	}

	@RequestMapping("/orderManage/ordercommunity.shtml")
	public ModelAndView ordercommunity() {
		ModelAndView mv = new ModelAndView("/omp/order/listNew");
		return mv;
	}

	@RequestMapping("/orderManage/odercom.shtml")
	public ModelAndView orderCom(String ids) throws Exception {
		boolean result = true;
		ModelAndView mv = new ModelAndView("/omp/order/initial");
		if (!StringUtils.isEmpty(ids)) {
			String[] split = ids.split(",");
			for (String id : split) {
				System.out.println(id);
				result = orderService.upsendOrder(id);
				System.out.println(result);
			}
		}
		return mv;
	}

	@RequestMapping("/orderManage/oInfo.shtml")
	@ResponseBody
	public ModelAndView oInfo(String id,HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("/omp/old/oldInfoNew");
 
    	List<Map<String,Object>> oList = orderService.getOList(id);
    	if(oList!=null&&oList.size()>0){
    		Map<String, Object> map = oList.get(0);
            mv.addObject("detaMap",map);
    	}
		return mv;
	}
	
	
	
	
}
