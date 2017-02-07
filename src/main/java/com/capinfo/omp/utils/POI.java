package com.capinfo.omp.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.capinfo.omp.model.OmpOldInfo;
@Service
public class POI {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**  
     * POI:解析Excel文件中的数据并把每行数据封装成一个实体  
     * @param fis 文件输入流  
	 * @param map 
     * @return List<OmpOldInfo> Excel中数据封装实体的集合  
	 * @throws Exception 
     */  
    public List<OmpOldInfo> importEmployeeByPoi(InputStream fis) throws Exception {   
           
        List<OmpOldInfo> infos = new ArrayList<OmpOldInfo>();   
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheetAt0 = workbook.getSheetAt(0);
        Row row3 = sheetAt0.getRow(3);
        String area = getCellValue(row3.getCell(0));
        String street = getCellValue(row3.getCell(1));
        String community = getCellValue(row3.getCell(2));
        String CountyId = getIdByName(area, 3);
        String StreetId = getIdByName(street, 4);
        String CommunityId = getIdByName(community, 5);
        
        Row row6 = sheetAt0.getRow(6);
        
        String workername = getCellValue(row6.getCell(1));
        String workertel = getCellValue(row6.getCell(2));
        
        Row row11 = sheetAt0.getRow(11);
        int rowNum = sheetAt0.getLastRowNum();
        short cellNum = row11.getLastCellNum();
        for (int i = 11; i < rowNum; i++) {
			Row row = sheetAt0.getRow(i);
			OmpOldInfo omp = new OmpOldInfo(CountyId, StreetId, CommunityId, workername, workertel,
					getCellValue(row.getCell(1)), getCellValue(row.getCell(2)), getCellValue(row.getCell(3)),
					getCellValue(row.getCell(4)), getCellValue(row.getCell(5)), getCellValue(row.getCell(6)),
					getCellValue(row.getCell(7)), getCellValue(row.getCell(8)));
			infos.add(omp);
		}
        
        return infos;   
    }  
    
    public String getIdByName(String parameter,int level) {
//		String sql = "select * from Omp_Region r where r.name like '%"+parameter+"%' and r.levelid = "+level;
    	String sql = "select * from Omp_Region r where r.id = 476";
//		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(sql);
//		String id = (String) queryForMap.get("id");
    	List<Map<String,Object>> queryForList = jdbcTemplate.queryForList(sql);
    	String id = (String) queryForList.get(0).get("id");
    	System.out.println(id);
    	
    	return null;
	}
    
    //判断从Excel文件中解析出来数据的格式   
    public static String getCellValue(Cell cell){   
        String value = null;   
        //简单的查检列类型   
        switch(cell.getCellType())   
        {   
            case HSSFCell.CELL_TYPE_STRING://字符串   
                value = cell.getRichStringCellValue().getString();   
                break;   
            case HSSFCell.CELL_TYPE_NUMERIC://数字   
                long dd = (long)cell.getNumericCellValue();   
                value = dd+"";   
                break;   
            case HSSFCell.CELL_TYPE_BLANK:   
                value = "";   
                break;      
            case HSSFCell.CELL_TYPE_FORMULA:   
                value = String.valueOf(cell.getCellFormula());   
                break;   
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值   
                value = String.valueOf(cell.getBooleanCellValue());   
                break;   
            case HSSFCell.CELL_TYPE_ERROR:   
                value = String.valueOf(cell.getErrorCellValue());   
                break;   
            default:   
                break;   
        }   
        return value;   
    } 
    
    /**
     * jsp
     */
//    <input type="file" id="excelPath" name="excelPath"/>&nbsp;&nbsp;   
//    <input type="button"  value="导入Excel" onclick="importEmp()"/>   
//      
//    -----------------------JS对导入的文件做简单的判断------------------------   
//      
//    //Excel文件导入到数据库中   
//    function importEmp(){   
//        //检验导入的文件是否为Excel文件   
//        var excelPath = document.getElementById("excelPath").value;   
//        if(excelPath == null || excelPath == ''){   
//            alert("请选择要上传的Excel文件");   
//            return;   
//        }else{   
//            var fileExtend = excelPath.substring(excelPath.lastIndexOf('.')).toLowerCase();    
//            if(fileExtend == '.xls'){   
//            }else{   
//                alert("文件格式需为'.xls'格式");   
//                return;   
//            }   
//        }   
//        //提交表单   
//        document.getElementById("empForm").action="<%=request.getContextPath()%>/EmpExcel.action.EmpExcelAction.do?method=importOmpOldInfos";     
//        document.getElementById("empForm").submit();   
//    } 
    
    /**
     * controller代码
     */
    
//    String excelPath = request.getParameter("excelPath");  
//    //输入流   
//    InputStream fis = new FileInputStream(excelPath);  
//      
//    //JXL:得到解析Excel的实体集合   
//    // List<OmpOldInfo> infos = importEmployee(fis);   
//      
//    //POI:得到解析Excel的实体集合   
//    List<OmpOldInfo> infos = importEmployeeByPoi(fis);  
//      
//    //遍历解析Excel的实体集合   
//    for(OmpOldInfo info:infos) {  
//        //判断员工编号是否存在(存在：做修改操作；不存在：做新增操作)   
//        OmpOldInfo info1 = this.selectEmpByEmpNum(info.getEmployeeNumber());  
//        if(info1 == null) {  
//            //把实体新加到数据库中   
//            this.service.addOmpOldInfo(info);  
//        }else{  
//            //把personId封装到实体   
//            info.setPersonId(info1.getPersonId());  
//            //更新实体   
//            this.updatOmpOldInfo(info);  
//        }  
//    }  
//    //关闭流   
//    fis.close();  
	
}
