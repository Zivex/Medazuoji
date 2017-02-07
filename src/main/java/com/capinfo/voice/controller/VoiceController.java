package com.capinfo.voice.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.common.model.SystemUser;
import com.capinfo.omp.model.OmpOldInfo;
import com.capinfo.omp.utils.Page;
import com.capinfo.omp.utils.PropertiesUtil;
import com.capinfo.omp.ws.client.ClientGetDataService;
import com.capinfo.omp.ws.client.ClientGetVoiceDataService;
import com.capinfo.omp.ws.model.ImKey;
import com.capinfo.voice.service.VoiceService;

@Controller
@RequestMapping("/voice")
@SessionAttributes("eccomm_admin")
public class VoiceController {

	@Autowired
	private VoiceService voiceService;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/voiceManage/initial.shtml")
	public ModelAndView initial(String current, String name, String idCard, String zjNumber, String county, String street, String community, String isGenerationOrder, String creationTime) {
		ModelAndView mv = new ModelAndView("/omp/voice/initial");
		getList(mv, current, name, idCard, zjNumber, county, street, community);
		return mv;
	}

	@RequestMapping("/voiceManage/list.shtml")
	public ModelAndView listt(String current, String name, String idCard, String zjNumber, String county, String street, String community) {
		ModelAndView mv = new ModelAndView("/omp/voice/list");
		getList(mv, current, name, idCard, zjNumber, county, street, community);
		return mv;
	}

	public void getList(ModelAndView mv, String current, String name, String idCard, String zjNumber, String county, String street, String community) {
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		int count = voiceService.getCount(name, idCard, zjNumber, county, street, community);
		count = count == 0 ? 1 : count;
		Page page = new Page<>(current, count, "10");
		List<Map<String, Object>> entities = voiceService.getOldContextList(page, name, idCard, zjNumber, county, street, community);
		List<Map<String, Object>> enList = voiceService.getshell();

		mv.addObject("enList", enList);
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

	}

	@RequestMapping("/voiceManage/onUpload.shtml")
	public String onUpload(@RequestParam("muFile") MultipartFile muFile, HttpServletRequest request) throws Exception {
		String gn = request.getParameter("gg");
		System.out.println(gn);
		String ids = request.getParameter("userid");
		String gg = request.getParameter("selects");
		int voss = Integer.parseInt(gg);
		String executionTime = request.getParameter("executionTime");
		String endTime = request.getParameter("endTime");
		String[] split1 = ids.split(",");

		System.out.println(ids + "=====");
		String format = null;
		String executeType = "1";
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Date date = new Date();
		if (StringUtils.isEmpty(executionTime)) {

			format = from.format(date);
			executeType = "2";
		}
		// date.setMinutes(date.getDay()+voss);
		// String endTime = from.format(date);
		String uploadURL = request.getSession().getServletContext().getRealPath("/") + "upload/";
		String filName = muFile.getOriginalFilename();

		File dirFile = new File(uploadURL);
		if (!dirFile.exists()) {
			dirFile.mkdir();
		}

		System.out.println("文件上传到" + uploadURL + filName);
		String urls = uploadURL + filName;

		String url = urls.replace("\\", "/");

		String[] split = ids.split(",");
		// SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		for (String goin : split) {
			if (StringUtils.isEmpty(executionTime)) {
				String sql = "insert into omp_voice_order (oldId,executeType,startTime,endTime,voiceFIleId,voiceFileAddress,upload_flag) VALUES ('" + goin + "','" + executeType + "','" + format + "','" + endTime + "','1','" + url + "','1')";
				jdbcTemplate.update(sql);
			}
			if (!StringUtils.isEmpty(executionTime)) {
				String sql = "insert into omp_voice_order (oldId,executeType,startTime,endTime,voiceFIleId,voiceFileAddress,upload_flag) VALUES ('" + goin + "','" + executeType + "','" + executionTime + "','" + endTime + "','1','" + url + "','1')";
				jdbcTemplate.update(sql);
			}
			if ("2".equals(executeType)) {
				for (String goins : split1) {
					String sql2 = "select MAX(v.id) 'id' from omp_voice_order v where v.oldId = " + goins + "";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sql2);
					for (int i = 0; i < list.size(); i++) {
						list.get(i);
						String id = list.get(i).get("id").toString();
						System.out.println(id);
						sendOrder(id);
					}
				}
			}
			if ("1".equals(executeType)) {
				for (String goins : split1) {
					String sql2 = "select MAX(v.id) 'id' from omp_voice_order v where v.oldId = " + goins + "";
					List<Map<String, Object>> list = jdbcTemplate.queryForList(sql2);
					for (int i = 0; i < list.size(); i++) {
						list.get(i);

						String id = list.get(i).get("id").toString();
						System.out.println(id);
						sendOrder(id);
					}
				}

			}

			File tarFile = new File(uploadURL + filName);
			try {

			} catch (Exception e) {
				// TODO: handle exception
			}

			if (!tarFile.exists()) {
				try {
					tarFile.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				muFile.transferTo(tarFile);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "redirect:/voice/voiceManage/initial.shtml?name=&idCard=&zjNumber=&county=&street=&community=";
	}

	/*
	 * public String Onuplod(List<Map<String,Object>> list) throws Exception {
	 * 
	 * ClientGetVoiceDataService cvds = new ClientGetVoiceDataService(); int rsult = 0; for (int i = 0; i < list.size(); i++) { list.get(i); JSONObject jsonObject = JSONObject.fromObject(list.get(i)); String ss = jsonObject.toString(); String replace = ss.replace("\\",""); StringBuffer stringBuffer = new StringBuffer(replace); int x;// 定义两变量 Random ne = new Random();// 实例化一个random的对象ne x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999 String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); String t = time + x; StringBuffer insert = stringBuffer.insert(1,"\"generateSerialNumber\":\""+t+"\","); ImKey imKey = cvds.svoice(insert.toString()); String e = imKey.getErrorMessage(); String s = imKey.getStatusCode(); String r = imKey.getReturnType(); String g = imKey.getGenerateSerialNumber(); if ("1".equals(imKey.getStatusCode())) { i++; }
	 * 
	 * 
	 * }
	 * 
	 * return null; }
	 */

	@ResponseBody
	public String sendOrder(String vid) throws Exception {
		int i = 0;
		ClientGetVoiceDataService c = new ClientGetVoiceDataService();
		if (!StringUtils.isEmpty(vid)) {
			String[] split = vid.split(",");
			for (String id : split) {
				String json = voiceService.sendOrder(id);
				ImKey imKey = c.svoice(json);
				if ("1".equals(imKey.getStatusCode())) {
					i++;
				}
			}
			if (i == split.length) {
				// 全部成功后修改指令状态
				for (String string : split) {
					voiceService.toupdete(string);
				}

				return "发送成功";
			}
			return "发送失败";
		}
		return null;
	}

	@RequestMapping("/voiceManage/listVoice.shtml")
	public ModelAndView listVoice(String current, String name) {
		ModelAndView mv = new ModelAndView("/omp/voice/voiceinfo");
		getVoList(mv, current, name);
		return mv;
	}

	@RequestMapping("/voiceManage/voiceQuery.shtml")
	public ModelAndView voiceQuery(String current, String name) {
		ModelAndView mv = new ModelAndView("/omp/voice/voice");
		getVoList(mv, current, name);
		return mv;
	}

	public void getVoList(ModelAndView mv, String current, String name) {
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		int count = voiceService.getvoicelist(name);
		count = count == 0 ? 1 : count;
		Page page = new Page<>(current, count, "10");
		List<Map<String, Object>> entities = voiceService.getvoicelist(page, name);

		mv.addObject("entities", entities);
		// mv.addObject("dataList",entities);
		mv.addObject("DataTotalCount", count);
		mv.addObject("CurrentPieceNum", page.getCurrentPage());
		mv.addObject("PerPieceSize", "10");
		mv.addObject("name", name);
	}

	/**
	 * 文件上传
	 * @param muFile
	 * @param request
	 * @return
	 */
	@RequestMapping("/voiceManage/oneUpload.shtml")
	public String oneUpload(@RequestParam("muFile") MultipartFile muFile, HttpServletRequest request) {
		
		Random random =new Random();
		String voicename = request.getParameter("voicename");
		String remark = request.getParameter("remark");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		String date = df.format(new Date());
		// String uploadUrl = request.getSession().getServletContext().getRealPath("/") + "upload/";
		String uploadUrl = PropertiesUtil.getStringByKey("BasePath") + "ws/";
		String filName = muFile.getOriginalFilename();
		String extName = filName.substring(filName.lastIndexOf(".")).toLowerCase();
        //重命名上传后的文件名,使用时间戳作为文件名称
		filName = System.currentTimeMillis()+String.valueOf(random.nextInt(10000))+extName;
		String urls = uploadUrl + filName;
		
		String url = urls.replace("\\", "/");
		String sql = "INSERT INTO `omp_voice_info` (`voiceFIleId`, `voiceName`, `voiceFileAddress`, `remark`, `voiceTime`) VALUES ('2', '" + voicename + "', '" + url + "', '" + remark + "', '" + date + "')";
		jdbcTemplate.update(sql);
		File dir = new File(uploadUrl);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		System.out.println("文件上传到: " + url);

		File targetFile = new File(uploadUrl + filName);
		if (!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			muFile.transferTo(targetFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "redirect:/voice/voiceManage/voiceQuery.shtml?name=";
	}

	@RequestMapping("/voiceManage/goVoiceIds.shtml")
	public String getVoiceInfos(HttpServletRequest request) {
		String vid = request.getParameter("vid");
		voiceService.uploadMiddle(vid);
		return "redirect:/voice/voiceManage/initial.shtml?name=&idCard=&zjNumber=&county=&street=&community=";

	}

	@RequestMapping("/voiceManage/initial2.shtml")
	public ModelAndView initial2(String current, String name, String idCard, String zjNumber, String county, String street, String community, String send_flag, String execute_flag) {
		ModelAndView mv = new ModelAndView("/omp/voice/initial2");
		getList(mv, current, name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		return mv;
	}

	@RequestMapping("/voiceManage/list2.shtml")
	public ModelAndView listt2(String current, String name, String idCard, String zjNumber, String county, String street, String community, String send_flag, String execute_flag) {
		ModelAndView mv = new ModelAndView("/omp/voice/list2");
		getList(mv, current, name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		return mv;
	}

	public void getList(ModelAndView mv, String current, String name, String idCard, String zjNumber, String county, String street, String community, String send_flag, String execute_flag) {
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		int count = voiceService.getOlCount(name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		count = count == 0 ? 1 : count;
		Page<Object> page = new Page<>(current, count, "10");
		List<Map<String, Object>> voiceOl = voiceService.getOldList(page, name, idCard, zjNumber, county, street, community, send_flag, execute_flag);
		mv.addObject("voiceOl", voiceOl);
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
	 * 指令发送
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voiceManage/sendOrder.shtml")
	@ResponseBody
	public String sendVice(String ids, Date stime, Date etime, HttpServletRequest request,@ModelAttribute("eccomm_admin") SystemUser user) throws Exception {
		String executeType = "1";
		String executionTime = null;
		String executionEndTime = null;
		Calendar c = Calendar.getInstance();
		if (stime==null) {
			SimpleDateFormat fromg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			executionTime = fromg.format(c.getTime());
		}else {
			SimpleDateFormat fromg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			executionTime = fromg.format(stime.getTime());
		}
		if (etime==null) {
			SimpleDateFormat fromg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			executeType = "2";
			c.add(Calendar.DAY_OF_MONTH, 1);
			executionEndTime = fromg.format(c.getTime());
		}else {
			SimpleDateFormat fromg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			executionEndTime = fromg.format(etime.getTime());
		}
		String username = user.getLogonName();
		int i = 0;
		
		// String startTime = null;
		// String endTime = null;
		
		ClientGetVoiceDataService vice = new ClientGetVoiceDataService();
		if (!StringUtils.isEmpty(executionTime)) {
			String mid = voiceService.middle();// 语音ID

			if (!StringUtils.isEmpty(ids)) {
				String[] split = ids.split(",");
				for (String id : split) {
					int x;// 定义两变量
					Random ne = new Random();// 实例化一个random的对象ne
					x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
					String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					String t = time + x;
					voiceService.saveviceoder(id, mid, executeType, executionTime, executionEndTime, t);
					String result = voiceService.resultVice();
					String json = voiceService.sendvice(result, t);
					ImKey imKey = vice.svoice(json);
					if ("1".equals(imKey.getStatusCode())) {
						voiceService.resultVOrders(imKey, id, username);
						i++;
					}
					if (i != 0) {
						voiceService.toupdete(id);
						voiceService.upMessg(imKey, id);
					}

				}
				if (i != 0) {
					return "发送成功";
				}
				return "发送失败";
			}
		} else {
			String mid = voiceService.middle();
			if (!StringUtils.isEmpty(ids)) {
				String[] split = ids.split(",");
				for (String id : split) {
					int x;// 定义两变量
					Random ne = new Random();// 实例化一个random的对象ne
					x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
					String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
					String t = time + x;
					voiceService.saveviceoder(id, mid, executeType, executionTime, executionEndTime, t);
				}
			}

		}
		return "";
	}

	/**
	 * 指令预约发送
	 * @param ids
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String sendVices(String ids,@ModelAttribute("eccomm_admin") SystemUser user) throws Exception {
		String username = user.getLogonName();
		int i = 0;
		String executeType = "2";

		ClientGetVoiceDataService vice = new ClientGetVoiceDataService();
		String mid = voiceService.middle();// 获取文件ID

		if (!StringUtils.isEmpty(ids)) {
			String[] split = ids.split(",");
			for (String id : split) {// 遍历用户ID
				String startTime = voiceService.resultStrtTime(id, mid);
				String endTime = voiceService.resultendTime(id, mid);
				int x;// 定义两变量
				Random ne = new Random();// 实例化一个random的对象ne
				x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
				String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				String t = time + x;
				voiceService.saveviceoder(id, mid, executeType, startTime, endTime, t);
				String result = voiceService.resultVice();
				String json = voiceService.sendvice(result, t);
				ImKey imKey = vice.svoice(json);
				if ("1".equals(imKey.getStatusCode())) {
					voiceService.resultVOrders(imKey, id,username);
					i++;
				}
				if (i != 0) {
					// 全部成功后修改指令状态
					voiceService.toupdete(id);
					voiceService.upMessg(imKey, id);

				}
			}
			if (i != 0) {
				return "发送成功";
			}
			return "发送失败";
		}
		return "";
	}

	@RequestMapping("/orderManage/batchSendInstructions.shtml")
	public void batchSendInstructions() throws Exception {
		System.out.println("batchSendInstructions:定时器自动执行发送指令程序，间隔时间3分钟");
		if (voiceService.getcount()) {
			String id = voiceService.checkDeBatchSendInstructions();
			sendVices(id, null);
		}
	}

	/**
	 * 按市发送语音
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voiceManage/sendVice.shtml")
	@ResponseBody
	public String sendVice(HttpServletRequest request,@ModelAttribute("eccomm_admin") SystemUser user) throws Exception {
		String username = user.getLogonName();
		String ids = voiceService.resultOrderId();
		int i = 0;
		String executeType = "1";
		String startTime = null;
		String endTime = null;
		startTime = request.getParameter("executionTime");
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Calendar c = Calendar.getInstance();
		if (StringUtils.isEmpty(startTime)) {

			startTime = from.format(c.getTime());
		}
		endTime = request.getParameter("executionEndTime");
		if (StringUtils.isEmpty(endTime)) {
			executeType = "2";
			c.add(Calendar.DAY_OF_MONTH, 1);
			endTime = from.format(c.getTime());
		}
		ClientGetVoiceDataService vice = new ClientGetVoiceDataService();
		String mid = voiceService.middle();

		if (!StringUtils.isEmpty(ids)) {
			String[] split = ids.split(",");
			for (String id : split) {
				int x;// 定义两变量
				Random ne = new Random();// 实例化一个random的对象ne
				x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
				String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				String t = time + x;
				voiceService.saveviceoder(id, mid, executeType, startTime, endTime, t);
				String result = voiceService.resultVice();
				String json = voiceService.sendvice(result, t);
				ImKey imKey = vice.svoice(json);

				if ("1".equals(imKey.getStatusCode())) {
					voiceService.resultVOrders(imKey, id,username);
					i++;
				}
				if (i != 0) {
					voiceService.toupdete(id);
					voiceService.upMessg(imKey, result);
				}
			}
		}
		return "发送成功";
	}

	/**
	 * 按街道发送
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voiceManage/sendCommun.shtml")
	@ResponseBody
	public String sendCommunity(String grade, HttpServletRequest request,@ModelAttribute("eccomm_admin") SystemUser user) throws Exception {
		String username = user.getLogonName();
		String ids = voiceService.ResultOderById(grade);
		int i = 0;
		String executeType = "1";
		String startTime = null;
		String endTime = null;
		startTime = request.getParameter("executionTime");
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		Calendar c = Calendar.getInstance();
		if (StringUtils.isEmpty(startTime)) {

			startTime = from.format(c.getTime());
		}
		endTime = request.getParameter("executionEndTime");
		if (StringUtils.isEmpty(endTime)) {
			executeType = "2";
			c.add(Calendar.DAY_OF_MONTH, 1);
			endTime = from.format(c.getTime());
		}
		ClientGetVoiceDataService vice = new ClientGetVoiceDataService();
		String mid = voiceService.middle();

		if (!StringUtils.isEmpty(ids)) {
			String[] split = ids.split(",");
			for (String id : split) {
				int x;// 定义两变量
				Random ne = new Random();// 实例化一个random的对象ne
				x = ne.nextInt(99999 - 10000 + 1) + 1000;// 为变量赋随机值10000-99999
				String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
				String t = time + x;
				voiceService.saveviceoder(id, mid, executeType, startTime, endTime, t);
				String result = voiceService.resultVice();
				String json = voiceService.sendvice(result, t);
				ImKey imKey = vice.svoice(json);

				if ("1".equals(imKey.getStatusCode())) {
					voiceService.resultVOrders(imKey, id,username);
					i++;
				}
				if (i != 0) {
					voiceService.toupdete(id);
					voiceService.upMessg(imKey, result);
				}
			}
		}
		return "发送成功";
	}

}
