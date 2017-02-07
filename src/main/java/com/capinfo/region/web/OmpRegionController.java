package com.capinfo.region.web;

import java.awt.Label;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.omp.utils.JsonUtil;
import com.capinfo.omp.utils.Page;
import com.capinfo.region.service.OmpRegionService;
import com.capinfo.region.utils.ExportExcel;
import com.mysql.fabric.xmlrpc.base.Data;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin/omp/ompRegion")
public class OmpRegionController {

	@Autowired
	private OmpRegionService ompRegionService;

	/**
	 * 区划管理列表
	 * 
	 * @param parameter
	 * @param mv
	 */
	@RequestMapping("/initial.shtml")
	@ResponseBody
	private ModelAndView getSortList() {
		
	//	ModelAndView mv = new ModelAndView("/omp/region/allocation");
		ModelAndView mv = new ModelAndView("/omp/region/initial");
		return mv;
	}
	
	
	
	@RequestMapping("/list.shtml")
	@ResponseBody
	private ModelAndView getsortList(HttpServletRequest request,String pid) {
		ModelAndView mv = new ModelAndView("/omp/region/list");
		//城区
		List entities = this.ompRegionService.getOmpOldMatchSortList("3");
		//街道
		List street = this.ompRegionService.getOmpOldMatchSortList("4");
		mv.addObject("dataList",entities);
		mv.addObject("street",street);
//		mv.addObject("community",community);
		return mv;
	}
	
	
	@RequestMapping("listNew.shtml")
	public ModelAndView listNew(String current,String county, String street, String community,String isSend,String creationTime) {
		ModelAndView mv = new ModelAndView("/omp/region/listNew");
		getStreetList(mv, current,county, street,community, isSend, creationTime);
		return mv;
	}
	
	@RequestMapping("list1.shtml")
	public ModelAndView listtoo(String current,String county, String street, String community,String isSend,String creationTime) {
		ModelAndView mv = new ModelAndView("/omp/region/list1");
		getStreetList(mv, current,county, street,community, isSend, creationTime);
		return mv;
	}
	
	@RequestMapping("list2.shtml")
	public ModelAndView listtoos(String current,String county, String street, String community,String isSend,String creationTime) {
		ModelAndView mv = new ModelAndView("/omp/order/list1");
		getStreetList(mv, current,county, street,community, isSend, creationTime);
		return mv;
	}
	
	/**
	 * 带有查询条件的 街道列表
	 * @param mv
	 * @param current 当前页
	 * @param name
	 * @param idCard
	 * @param county 城区
	 * @param street 街道
	 * @param community 社区
	 * @param isSend 是否发送指令
	 * @param creationTime 创建时间
	 */
	public void getStreetList(ModelAndView mv,String current,String county, String street,String community, String isSend,String creationTime) {
		
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		int count = ompRegionService.getCount(county, street,community, isSend,creationTime);
		count = count == 0?1:count;
		Page page = new Page<>(current, count, "10");
		List<Map<String, Object>> entities = ompRegionService.getStreetList(page,county,street,community,isSend,creationTime);
		mv.addObject("dataList",entities);
		mv.addObject("DataTotalCount",count);
		mv.addObject("CurrentPieceNum",page.getCurrentPage());
		mv.addObject("PerPieceSize","10");
		mv.addObject("county",county);
		mv.addObject("street",street);
		mv.addObject("community",community);
		mv.addObject("isSend",isSend);
	}
	
	
	
	
	@RequestMapping("/get.shtml")
	@ResponseBody
	private List<Map<String, Object>> getList(String id) {
		List<Map<String,Object>> entities = this.ompRegionService.getSortList(id);
		return entities;
	}

	@RequestMapping("/allocation.shtml")
	@ResponseBody
	private List<Map<String, Object>> allocation(String showName,String serverType) {
		List<Map<String, Object>> list = ompRegionService.getPro(showName,serverType);
		return list;
	}
	/*
	 * 获取服务商信息
	 */
	@RequestMapping("/getOrder.shtml")
	@ResponseBody 
	public List<Map<String, Object>> getOrder(HttpServletRequest request) {
		String jjtype="";
		String sntype="";
		String nstype="";
		String sname="";
		String id="";
		String kid="";
		String KEY=""; 
		String LinkNbr="96003";
		JSONObject jsonObject=new JSONObject();
		
		
		//服务商类型
		String ptype = request.getParameter("ptype");
		//社区ID
		String shequId = request.getParameter("shequId");
		List<Map<String,Object>> data= ompRegionService.gerRegionById(shequId);
		List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();

		if(data!=null&&data.size()>0){
			if(data.get(0).get("jjtype")!=null){
				jjtype=data.get(0).get("jjtype").toString();
			}
			if(data.get(0).get("sntype")!=null){
				sntype=data.get(0).get("sntype").toString();
			}
			if(data.get(0).get("nstype")!=null){
				nstype=data.get(0).get("nstype").toString();
			}
			
		}
		
		
		if (!StringUtils.isEmpty(ptype)) {
			
			List<Map<String,Object>> order = ompRegionService.getOrder(ptype);
			if("1".equals(ptype)){
				jsonObject=JsonUtil.getJson(jjtype);
			}else if("2".equals(ptype)){
				jsonObject=JsonUtil.getJson(sntype);
			}else{
				jsonObject=JsonUtil.getJson(nstype);
			}
			
			JSONArray json1=JsonUtil.getJson1(jsonObject);
		    if(json1.size()>0){
		       for(int i=0;i<json1.size();i++){
		    	 Map<String, Object> map = new HashMap<String, Object>();
		         JSONObject job = json1.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		         
		         if(order!=null&&!"".equals(order)){
		        	 id=order.get(i).get("id").toString();
		        	 if(order.get(i).get("sname")==null){
		        		 sname = "";
		        	 }else{
		        		 sname=order.get(i).get("sname").toString();
		        	 }
			         kid=order.get(i).get("kid").toString();
			         ptype=order.get(i).get("ptype").toString();
			         KEY=order.get(i).get("KEY").toString();
		         }
                 if(job!=null){
                	LinkNbr= job.get("value").toString();
                 }

		         map.put("id", id);
		         map.put("sname", sname);
		         map.put("kid", kid);
		         map.put("ptype", ptype);
		         map.put("KEY", KEY);
		         if(KEY.equals(job.get("key"))){
		        	 map.put("LinkNbr", job.get("value").toString());
		         }
		         listItems.add(map);
		       }
		 //      System.out.println(listItems);
		       
		    }
			
			return listItems; 
		}
		
		
		return null;
		
	}
	
	/*
	 * 获取服务商信息作为一个方法调用
	 */
	@RequestMapping("/getOrderByShequId.shtml")
	@ResponseBody 
	public List<Map<String, Object>> getOrderByShequId(String shequId,String ptype) {
		String jjtype="";
		String sntype="";
		String nstype="";
		String sname="";
		String id="";
		String kid="";
		String KEY=""; 
		String LinkNbr="96003";
		JSONObject jsonObject=new JSONObject();
		
		List<Map<String,Object>> data= ompRegionService.gerRegionById(shequId);
		List<Map<String,Object>> listItems=new ArrayList<Map<String,Object>>();

		if(data!=null&&data.size()>0){
			if(data.get(0).get("jjtype")!=null){
				jjtype=data.get(0).get("jjtype").toString();
			}
			if(data.get(0).get("sntype")!=null){
				sntype=data.get(0).get("sntype").toString();
			}
			if(data.get(0).get("nstype")!=null){
				nstype=data.get(0).get("nstype").toString();
			}
			
		}
		
		
		if (!StringUtils.isEmpty(ptype)) {
			
			List<Map<String,Object>> order = ompRegionService.getOrder(ptype);
			if("1".equals(ptype)){
				jsonObject=JsonUtil.getJson(jjtype);
			}else if("2".equals(ptype)){
				jsonObject=JsonUtil.getJson(sntype);
			}else{
				jsonObject=JsonUtil.getJson(nstype);
			}
			
			JSONArray json1=JsonUtil.getJson1(jsonObject);
		    if(json1.size()>0){
		       for(int i=0;i<json1.size();i++){
		    	 Map<String, Object> map = new HashMap<String, Object>();
		         JSONObject job = json1.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
		         
		         if(order!=null&&!"".equals(order)){
		        	 id=order.get(i).get("id").toString();
		        	 if(order.get(i).get("sname")==null){
		        		 sname = "";
		        	 }else{
		        		 sname=order.get(i).get("sname").toString();
		        	 }
			         kid=order.get(i).get("kid").toString();
			         ptype=order.get(i).get("ptype").toString();
			         KEY=order.get(i).get("KEY").toString();
		         }
                 if(job!=null){
                	LinkNbr= job.get("value").toString();
                 }

		         map.put("id", id);
		         map.put("sname", sname);
		         map.put("kid", kid);
		         map.put("ptype", ptype);
		         map.put("KEY", KEY);
		         if(KEY.equals(job.get("key"))){
		        	 map.put("LinkNbr", job.get("value").toString());
		         }
		         listItems.add(map);
		       }
		//       System.out.println(listItems);
		       
		    }
			
		//	System.out.println("===="+listItems);
			return listItems; 
		}
		
		
		return null;
		
	}
	
	
	@RequestMapping("/getServiceType.shtml")
	@ResponseBody
	public List<Map<String,Object>> getServiceType() {
		List<Map<String, Object>> list = ompRegionService.getServiceType();
		return list;
	}
	
	
	@RequestMapping("/login.shtml")
	public boolean login(String user,String pass) {
		boolean result=true;
		int isLogin= ompRegionService.login(user,pass);
		if(isLogin>0){
			result=true;
		}else{
			result=false;
		}
		return result;
	}
	
	@RequestMapping("/saveSqOrder.shtml")
	@ResponseBody
	public void saveSqOrder(HttpServletRequest request) {
		String kid = request.getParameter("kid");
		String shequId = request.getParameter("shequId");
		String ptype = request.getParameter("ptype");
		String zhiling=request.getParameter("json");
		
	  //System.out.println("社区ID："+shequId+"类型："+ptype+"指令："+zhiling); 
		
		if("1".equals(ptype)){//居家型
			//更新社区指令
			ompRegionService.updateOrder1(shequId,zhiling);
		}else if("2".equals(ptype)){//失能型
			ompRegionService.updateOrder2(shequId,zhiling);
		}else{//农商型
			ompRegionService.updateOrder3(shequId,zhiling);
		}
		String showid = request.getParameter("showid");
		String shownumber = request.getParameter("shownumber");
		System.out.println("键位："+showid+"-----"+shownumber);
		if (StringUtils.isEmpty(shownumber)) {
			shownumber = "96003";
		}
		String communityids = request.getParameter("communityids");
	
	}
	
	
	@RequestMapping("/exportExcelNew.shtml")
	public String exportExcelNew(String shequId,HttpServletResponse response) throws Exception {
		String name="";
		List<Map<String, Object>> shequInfo=ompRegionService.gerRegionById(shequId);
		if(shequInfo!=null&&shequInfo.get(0).get("text")!=null){
			name=shequInfo.get(0).get("text").toString(); 
		}
		// 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet1 = wb.createSheet("居家型");  
        HSSFSheet sheet2 = wb.createSheet("失能型");
        HSSFSheet sheet3 = wb.createSheet("农商型");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row1 = sheet1.createRow((int) 0); 
        HSSFRow row2 = sheet2.createRow((int) 0); 
        HSSFRow row3= sheet3.createRow((int) 0); 
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
        HSSFCell cell = row1.createCell((short) 0);  
        HSSFCell cell1 = row1.createCell((short) 1);
        HSSFCell cell2 = row1.createCell((short) 2);  
        HSSFCell cell3 = row1.createCell((short) 3);
        
        //sheet1中的标题
        cell = row1.createCell((short) 0);
        cell.setCellValue("键位");  
        cell.setCellStyle(style);  
        cell = row1.createCell((short) 1);  
        cell.setCellValue("话机类型");  
        cell.setCellStyle(style);  
        cell = row1.createCell((short) 2);  
        cell.setCellValue("服务类型");  
        cell.setCellStyle(style);  
        cell = row1.createCell((short) 3);  
        cell.setCellValue("联系电话");  
        cell.setCellStyle(style);  
        
        //sheet2中的标题
        cell = row2.createCell((short) 0);
        cell.setCellValue("键位");  
        cell.setCellStyle(style);  
        cell = row2.createCell((short) 1);  
        cell.setCellValue("话机类型");  
        cell.setCellStyle(style);  
        cell = row2.createCell((short) 2);  
        cell.setCellValue("服务类型");  
        cell.setCellStyle(style);  
        cell = row2.createCell((short) 3);  
        cell.setCellValue("联系电话");  
        cell.setCellStyle(style);  
        
        //sheet3中的标题
        cell = row3.createCell((short) 0);
        cell.setCellValue("键位");  
        cell.setCellStyle(style);  
        cell = row3.createCell((short) 1);  
        cell.setCellValue("话机类型");  
        cell.setCellStyle(style);  
        cell = row3.createCell((short) 2);  
        cell.setCellValue("服务类型");  
        cell.setCellStyle(style);  
        cell = row3.createCell((short) 3);  
        cell.setCellValue("联系电话");  
        cell.setCellStyle(style); 
        
   
        cell.setCellStyle(style);  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
        List<Map<String, Object>> jjTypeinfo=getOrderByShequId(shequId,"1");
        List<Map<String, Object>> snTypeinfo=getOrderByShequId(shequId,"2");
        List<Map<String, Object>> nsTypeinfo=getOrderByShequId(shequId,"3");

        for (int i = 0; i < jjTypeinfo.size(); i++){  
            row1 = sheet1.createRow((int) i + 1);
          //  sheet1.setColumnWidth(i, 20*256);//设置列宽度
            sheet1.autoSizeColumn(i); //自动设置宽度 
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
            cell3=row1.createCell(0);
            cell2=row1.createCell(1);
            cell1=row1.createCell(2);
            cell= row1.createCell(3);
            //居中显示
            cell.setCellStyle(style); 
            cell1.setCellStyle(style); 
            cell2.setCellStyle(style); 
            cell3.setCellStyle(style); 
            // 第四步，创建单元格，并设置值  
            cell3.setCellValue(jjTypeinfo.get(i).get("KEY").toString());
            cell2.setCellValue(jjTypeinfo.get(i).get("ptype").toString());
            cell1.setCellValue(jjTypeinfo.get(i).get("sname").toString());
            cell.setCellValue(jjTypeinfo.get(i).get("LinkNbr").toString());
        } 
        
        for (int i = 0; i < snTypeinfo.size(); i++){  
        	style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
            row3 = sheet2.createRow((int) i + 1);
          //sheet2.setColumnWidth(i, 20*256);//设置列宽度
            sheet2.autoSizeColumn(i); //自动设置宽度 
            cell3=row3.createCell(0);
            cell2=row3.createCell(1);
            cell1=row3.createCell(2);
            cell= row3.createCell(3);
            //居中显示
            cell.setCellStyle(style); 
            cell1.setCellStyle(style); 
            cell2.setCellStyle(style); 
            cell3.setCellStyle(style); 
            
            // 第四步，创建单元格，并设置值  
            cell3.setCellValue(snTypeinfo.get(i).get("KEY").toString());
            cell2.setCellValue(snTypeinfo.get(i).get("ptype").toString());
            cell1.setCellValue(snTypeinfo.get(i).get("sname").toString());
            cell.setCellValue(snTypeinfo.get(i).get("LinkNbr").toString());
        } 
        
        for (int i = 0; i < nsTypeinfo.size(); i++){  
        	style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
            row3 = sheet3.createRow((int) i + 1);
         // sheet3.setColumnWidth(i, 20*256);//设置列宽度
            sheet3.autoSizeColumn(i); //自动设置宽度 
            cell3=row3.createCell(0);
            cell2=row3.createCell(1);
            cell1=row3.createCell(2);
            cell= row3.createCell(3);
            //居中显示
            cell.setCellStyle(style); 
            cell1.setCellStyle(style); 
            cell2.setCellStyle(style); 
            cell3.setCellStyle(style); 
            // 第四步，创建单元格，并设置值  
            cell3.setCellValue(nsTypeinfo.get(i).get("KEY").toString());
            cell2.setCellValue(nsTypeinfo.get(i).get("ptype").toString());
            cell1.setCellValue(nsTypeinfo.get(i).get("sname").toString());
            cell.setCellValue(nsTypeinfo.get(i).get("LinkNbr").toString());
        } 
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((name + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
        // 第六步，将文件存到指定位置  
        /*try{  
            FileOutputStream fout = new FileOutputStream("D:/"+name+"服务指令.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e){  
            e.printStackTrace();  
        }  
      
		return "redirect:/admin/omp/ompRegio/initial.shtml";*/
	}
	
	
	
	@RequestMapping("/exportExcel.shtml")
	public String exportExcel(String id) throws Exception {
		String region = ompRegionService.gerRegion(id);
		String title = region;
		String[] rowName = {"序号","键位","伙计类型","服务类型","服务商名称","服务商电话"};
		List<List> dataList = new ArrayList<List>();
		List<Map<String, Object>> list = ompRegionService.getOrderByCommunity(id); 
		for (Map<String, Object> map : list) {
			List list2 = mapTransitionList(map);
			dataList.add(list2);
			
		}
		ExportExcel excel = new ExportExcel(title, rowName, dataList);
		OutputStream out = new FileOutputStream("d:\\workbook.xls");
		excel.export(out);
		return "redirect:/admin/omp/ompRegio/initial.shtml";
	}
	
	
	
	// map转换成lisy
	public static List mapTransitionList(Map map) {
		List list = new ArrayList();
		Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
		Set keySet = map.keySet();
		for (Object object : keySet) {
			if (map.get(object)==null) {
				list.add("空");
			}else {
				list.add(map.get(object));
			}
		}
		return list;
	}

}


