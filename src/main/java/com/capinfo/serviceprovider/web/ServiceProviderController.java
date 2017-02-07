package com.capinfo.serviceprovider.web;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.capinfo.omp.utils.Page;
import com.capinfo.serviceprovider.service.ServiceProviderService;
import com.capinfo.serviceprovider.utils.ExportExcel;

@Controller
@RequestMapping("/admin/omp/ServiceProvider")
public class ServiceProviderController {

	@Autowired
	private ServiceProviderService ompOldMatchService;
	
	
	
	@RequestMapping("/initial.shtml")
	public ModelAndView initial(HttpServletRequest request,String current) {
		ModelAndView mv = new ModelAndView("/omp/serviceProvider/initial");
		
		return mv;
	}

	
	/**
	 * 服务商管理列表
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/list.shtml")
	public ModelAndView dicSortList(HttpServletRequest request,String current) {
		ModelAndView mv = new ModelAndView("/omp/serviceProvider/initial");
//		String current = request.getParameter("current");
		getSortList(current,mv, "");
		return mv;
	}
	

	/**
	 * 服务商管理列表
	 * @param mv 
	 * 
	 * @param parameter
	 * @param mv
	 * @return 
	 */
	private void getSortList(String current, ModelAndView mv , String QSql) {
		if (StringUtils.isEmpty(current)) {
			current = "1";
		}
		if (StringUtils.isEmpty(QSql)) {
			QSql = "";
		}
		int count = ompOldMatchService.getCountWithSql(QSql);
		Page page = new Page(current, count, "10");
		List<Map<String, Object>> list = ompOldMatchService.getListWithSql(page,QSql);
		for (Map<String, Object> map : list) {
			String id = map.get("SCOPE_DELIVERY").toString();
			if (!StringUtils.isEmpty(id)) {
//				String name = ompOldMatchService.getregion(id);
				map.put("SCOPE_DELIVERY", id);
			}
		}
		mv.addObject("DataTotalCount",count);
		mv.addObject("PerPieceSize",10);
		mv.addObject("command", list);
		mv.addObject("CurrentPieceNum",page.getCurrentPage());
		mv.addObject("QSql",QSql);
	}
	
	/**
	 * 服务商列表
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping("/lists.shtml")
	public ModelAndView userList(HttpServletRequest request,String current,String QSql) {
		ModelAndView mv = new ModelAndView("/omp/serviceProvider/list");
		getSortList(current,mv , QSql);
		return mv;
	}
	
	
	@RequestMapping("/serverPro.shtml")
	public ModelAndView serverPro(String id) {
		ModelAndView mv = null;
		Map<String,Object> map = ompOldMatchService.getMapById(id);
		switch (map.get("tify").toString()) {
		case "YL":
			mv = new ModelAndView("/omp/serviceProvider/serverPro_P");
			break;
		case "SN":
			mv = new ModelAndView("/omp/serviceProvider/serverPro_D");
			break;
		}
		mv.addObject("map",map);
		return mv;
	}
	//  查询服务商详情
	@RequestMapping("/serverInfo.shtml")
	public ModelAndView serverInfo(String id) {
		ModelAndView mv = new ModelAndView("/omp/serviceProvider/serverInfo");
//		ompOldMatchService.getMapById(id);
		mv.addObject("item",ompOldMatchService.getServerInfoWithID(id));
		return mv;
	}
	
	
	/**
	 * 导出服务商信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/export.shtml")
	@ResponseBody
	public ModelAndView exportExcel() throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/admin/omp/ServiceProvider/list.shtml");
		getSortList(null,mv, "");
//		setWorkbookStyle(mv);// 获取列头样式对象
		
		String title = "服务商信息表";
		String[] rowName = {"序号","名称","配送范围","服务类型","服务商电话", "是否大座机服务商"};
		List<List> dataList = new ArrayList<List>();
	//	List<Map<String, Object>> list = (List<Map<String, Object>>) mv.getModel().get("command");
		//取得所有的服务商信息
		List<Map<String, Object>> list = ompOldMatchService.getAllList();
		for (Map<String, Object> map : list) {
			List list2 = mapTransitionList(map);
			dataList.add(list2);
			
		}
		ExportExcel excel = new ExportExcel(title, rowName, dataList);
		OutputStream out = new FileOutputStream("D:\\服务商信息.xls");
		excel.export(out);
		return mv;
	}
	
	// map转换成lisy
	public static List mapTransitionList(Map map) {
		List list = new ArrayList();
		Iterator iter = map.entrySet().iterator(); // 获得map的Iterator
		Set keySet = map.keySet();
		for (Object object : keySet) {
			if (!(object.equals("SERVER_ID") || object.equals("SERVICE_PROVIDERS_IDENTIFY"))) {
				if (object.equals("IS_VALID")) {
					list.add("是");
				}else {
					list.add(map.get(object));
				}
			}
		}
		return list;
	}
	
	
//	public void setWorkbookStyle(ModelAndView mv) throws Exception{
//		List<Map<String, Object>> list = (List<Map<String, Object>>) mv.getModel().get("command");
//		// 第一步，创建一个webbook，对应一个Excel文件                         
//		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
//		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet 
//		HSSFSheet sheet = workbook.createSheet("服务商信息表"); // 创建工作表
//		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//        // 第四步，创建单元格，并设置值表头 设置表头居中  
//		 HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);
////		 sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (4)));    
//		
//		HSSFCellStyle style = getStyle(workbook);
//        HSSFRow row = sheet.createRow(0);
//        HSSFCell cell0 = row.createCell((short)0);
//        cell0.setCellStyle(columnTopStyle);
//        cell0.setCellValue("名称");
//        HSSFCell cell1 = row.createCell((short)1);
//        cell1.setCellStyle(columnTopStyle);
//        cell1.setCellValue("配送范围");
//        HSSFCell cell2 = row.createCell((short)2);
//        cell2.setCellStyle(columnTopStyle);
//        cell2.setCellValue("服务类型");
//        HSSFCell cell3 = row.createCell((short)3);
//        cell3.setCellStyle(columnTopStyle);
//        cell3.setCellValue("是否大座机服务商");
//        HSSFCell cell4 = row.createCell((short)4);
//        cell4.setCellStyle(columnTopStyle);
//        cell4.setCellValue("服务商电话");
//		for (int i = 0; i < list.size(); i++) {
//			Map<String, Object> map = list.get(i);
//			HSSFRow r = sheet.createRow(i+1);
//			Set<String> keySet = map.keySet();
//			HSSFCell cell00 = r.createCell(0);
//			cell00.setCellStyle(style);
//			cell00.setCellValue(map.get("SERVER_NAME").toString());
//			HSSFCell cell01 = r.createCell(1);
//			cell01.setCellValue(map.get("SCOPE_DELIVERY").toString());
//			cell01.setCellStyle(style);
//			HSSFCell cell02 = r.createCell(2);
//			cell02.setCellValue(map.get("SERVER_TYPE").toString());
//			cell02.setCellStyle(style);
//			HSSFCell cell03 = r.createCell(3);
////			cell03.setCellValue(map.get("SERVER_TYPE").toString());
//			cell03.setCellStyle(style);
//			HSSFCell cell04 = r.createCell(4);
//			cell04.setCellValue(map.get("SERVER_TEL").toString());
//			cell04.setCellStyle(style);
//			
//		}
//        
//        FileOutputStream fileOut = null;
//		try {
//			fileOut = new FileOutputStream("d:\\workbook.xls");
//			workbook.write(fileOut);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			 fileOut.close(); 
//		}
//	}
//	
//	
//	/*   
//     * 列数据信息单元格样式 
//     */    
//    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {  
//          // 设置字体  
//          HSSFFont font = workbook.createFont();  
//          //设置字体大小  
//          //font.setFontHeightInPoints((short)10);  
//          //字体加粗  
//          //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
//          //设置字体名字   
//          font.setFontName("Courier New");  
//          //设置样式;   
//          HSSFCellStyle style = workbook.createCellStyle();  
//          //设置底边框;   
//          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
//          //设置底边框颜色;    
//          style.setBottomBorderColor(HSSFColor.BLACK.index);  
//          //设置左边框;     
//          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
//          //设置左边框颜色;   
//          style.setLeftBorderColor(HSSFColor.BLACK.index);  
//          //设置右边框;   
//          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
//          //设置右边框颜色;   
//          style.setRightBorderColor(HSSFColor.BLACK.index);  
//          //设置顶边框;   
//          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
//          //设置顶边框颜色;    
//          style.setTopBorderColor(HSSFColor.BLACK.index);  
//          //在样式用应用设置的字体;    
//          style.setFont(font);  
//          //设置自动换行;   
//          style.setWrapText(false);  
//          //设置水平对齐的样式为居中对齐;
//          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
//          //设置垂直对齐的样式为居中对齐;   
//          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
//           
//          return style;  
//      
//    } 
//    
//    
//    /*  
//     * 列头单元格样式 
//     */      
//    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {  
//          
//          // 设置字体  
//          HSSFFont font = workbook.createFont();  
//          //设置字体大小  
//          font.setFontHeightInPoints((short)11);  
//          //字体加粗  
//          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
//          //设置字体名字   
//          font.setFontName("Courier New");  
//          //设置样式;   
//          HSSFCellStyle style = workbook.createCellStyle();  
//          //设置底边框;   
//          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
//          //设置底边框颜色;    
//          style.setBottomBorderColor(HSSFColor.BLACK.index);  
//          //设置左边框;     
//          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
//          //设置左边框颜色;   
//          style.setLeftBorderColor(HSSFColor.BLACK.index);  
//          //设置右边框;   
//          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
//          //设置右边框颜色;   
//          style.setRightBorderColor(HSSFColor.BLACK.index);  
//          //设置顶边框;   
//          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
//          //设置顶边框颜色;    
//          style.setTopBorderColor(HSSFColor.BLACK.index);  
//          //在样式用应用设置的字体;    
//          style.setFont(font);  
//          //设置自动换行;   
//          style.setWrapText(false);  
//          //设置水平对齐的样式为居中对齐;    
//          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
//          //设置垂直对齐的样式为居中对齐;   
//          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
//            
//          return style;  
//            
//    }

}
