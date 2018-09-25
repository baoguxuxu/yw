package com.yinwang.system.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yinwang.owneruser.comment.DateUtil;

/** 
 * @author tmn E-mail: 
 * @version 1.0 
 * @类说明 
 */
public class ExcelUtils {

	
	
	/**
	 * 获取Book
	 * @param is
	 * @param sheetNum
	 * @return
	 */
	public	static Workbook getBook(InputStream is) {
		Workbook wb = null;
		try {
			wb = new XSSFWorkbook(is);
		} catch (Exception ex) {
			try {
				wb = new HSSFWorkbook(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return wb;
	}
	
	/**
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		DecimalFormat df=new DecimalFormat("0");
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
		   case Cell.CELL_TYPE_NUMERIC:
//				cellvalue = String.valueOf(cell.getNumericCellValue());
				
				cellvalue = df.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					cellvalue = DateUtil.dateToString(date);
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = df.format(cell.getNumericCellValue());
				}
				break;
			}
			case HSSFCell.CELL_TYPE_BOOLEAN:
				cellvalue = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				cellvalue = "";
				break;
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;

	}
}
