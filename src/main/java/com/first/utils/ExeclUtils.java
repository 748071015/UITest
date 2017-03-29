package com.first.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExeclUtils {
	
	private static final String EXCEL_XLS = "xls";  
    private static final String EXCEL_XLSX = "xlsx";
    private static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

    public static void checkExcelVaild(File file) throws Exception{  
        if(!file.exists()){  
            throw new Exception("文件不存在");  
        }  
        if(!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))){  
            throw new Exception("文件不是Excel");  
        }  
    }
    
    public static Workbook getWorkbook(File file) throws IOException{  
        Workbook wb = null;  
        FileInputStream in = new FileInputStream(file);  
        if(file.getName().endsWith(EXCEL_XLS)){  //检查后缀名，创建对应的excel文件
            wb = new HSSFWorkbook(in);  
        }else if(file.getName().endsWith(EXCEL_XLSX)){  
            wb = new XSSFWorkbook(in);  
        }  
        return wb;  
    }
    
    @SuppressWarnings("deprecation")//取消警告
	public static String convertCellValveToString(Cell cell){//将各种不同类型的单元格转换为字符串类型
    	int cellType = cell.getCellType();  
        String cellValue = "";
        switch (cellType) {  
	        case Cell.CELL_TYPE_STRING:     // 文本  
	            cellValue = cell.getRichStringCellValue().getString();  
	            break;  
	        case Cell.CELL_TYPE_NUMERIC:    // 数字、日期  
	            if (DateUtil.isCellDateFormatted(cell)) {  
	                cellValue = fmt.format(cell.getDateCellValue());  
	            } else {  
	                cell.setCellType(Cell.CELL_TYPE_STRING);  
	                cellValue = String.valueOf(cell.getRichStringCellValue().getString());  
	            }  
	            break;  
	        case Cell.CELL_TYPE_BOOLEAN:    // 布尔型  
	            cellValue = String.valueOf(cell.getBooleanCellValue());  
	            break;  
	        case Cell.CELL_TYPE_BLANK: // 空白  
	            cellValue = cell.getStringCellValue();  
	            break;  
	        case Cell.CELL_TYPE_ERROR: // 错误  
	            cellValue = "错误";  
	            break;  
	        case Cell.CELL_TYPE_FORMULA:    // 公式  
	            // 得到对应单元格的公式  
	            //cellValue = cell.getCellFormula() + "#";  
	            // 得到对应单元格的字符串  
	            cell.setCellType(Cell.CELL_TYPE_STRING);  
	            cellValue = String.valueOf(cell.getRichStringCellValue().getString());  
	            break;  
	        default:  
	            cellValue = cell.getStringCellValue().toString();  
        }
        return cellValue;
    }
    
    public static List<TestCase> getUITestData(String fileName){
    	List<TestCase> testCases = new ArrayList<TestCase>();//创建一个测试用例的list
    	try{
    		String execlPath = System.getProperty("user.dir")+"/testcasedata/"+fileName;//文件路径
	    	File excelFile = new File(execlPath); // 创建文件对象  
	        checkExcelVaild(excelFile);  //检查文件是否合法有效
	        Workbook workbook = getWorkbook(excelFile);
	        Sheet sheet = workbook.getSheetAt(0);//获取第一张工作表
	        Row firstRow = sheet.getRow(0);//工作表第一行
	        int count = 0;
	        for(Row row:sheet){   //遍历此sheet所有行
	        	if(count == 0){  
                    count++;  
                    continue;  //结束第一次的循环，不执行第二个if
                }
	        	if(row.getCell(0).toString().equals("") || row.getCell(1).toString().equals("notRun")){  
	        		continue;  //若此行casename为空或者isRun为notRun，结束此次for循环
                }
	        	String caseName = convertCellValveToString(row.getCell(0));
	        	String isCheck = convertCellValveToString(row.getCell(2));
	        	String expectedResult = convertCellValveToString(row.getCell(3));
	        	Map<String,String> testData = new HashMap<String,String>();
	        	int skipNum = 0;  //跳过用例必需字段标识，键值对能对应的下标
	        	for(Cell cell : row){   //遍历此行所有单元格  
		        	if(skipNum < 4){  
		        		skipNum++;  
	                    continue;   //这个if配合continue能跳过本行前四个单元格 
	                }
	        		if(cell.toString() == null){  
                        continue;  
                    }
	        		testData.put
	        		(convertCellValveToString(firstRow.getCell(skipNum++)), //读出第一行里的各个数据的名字，如username
	        				convertCellValveToString(cell));  //读出单元格里的数据
	        		
	        	}
	        	testCases.add(new TestCase(caseName,isCheck,expectedResult,testData));	        	
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return testCases;
    }
    
//	public static Map<String,UITestCase> getUITestData(String filepath) throws IOException{
//		Map<String,UITestCase> testcasesmap = new HashMap<String,UITestCase>();
//		File execlfile = new File(filepath);
//		FileInputStream is = new FileInputStream(execlfile);
//		Workbook workbook = new HSSFWorkbook(is);
//		Sheet sheet = workbook.getSheetAt(0);
//		for(int i=1;i<sheet.getPhysicalNumberOfRows();i++){
//			Row row = sheet.getRow(i);
//			UITestCase uc = null;
//			String casename = row.getCell(0).toString();
//			String modelname = row.getCell(1).toString();
//			String[] testdata = row.getCell(2).toString().split(",");
//			String result = row.getCell(3).toString();
//			String isnot = row.getCell(4).toString();
//			uc = new UITestCase(casename,modelname,testdata,result,isnot);
//			testcasesmap.put(casename, uc);
//		}		
//		return testcasesmap;
//	}
		
}

