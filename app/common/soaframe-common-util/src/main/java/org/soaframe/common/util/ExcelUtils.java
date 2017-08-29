package org.soaframe.common.util;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.soaframe.common.util.exception.ExcelException;

/**
 * @Description: excel解析，写入工具类
 * @author zouhao
 * @date 2017年8月28日 下午4:44:45
 * 
 */
public class ExcelUtils {

	private static final Logger log = Logger.getLogger(ExcelUtils.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * @Description:浏览器excle下载配置
	 * @author zouhao
	 * @param list
	 * @param fieldMap
	 * @param sheetName
	 * @param response
	 * @throws ExcelException
	 */
	public static void listToExcel(List list, LinkedHashMap<String, String> fieldMap, String sheetName,
			HttpServletResponse response) throws ExcelException {

		String fileName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()).toString();
		// 设置response头信息
		response.reset();
		response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
		response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");

		// 创建工作簿并发送到浏览器
		try {
			OutputStream out = response.getOutputStream();
			doListToExcel(list, fieldMap, sheetName, out);
		} catch (Exception e) {
			if (e instanceof ExcelException) {
				throw (ExcelException) e;
			} else {
				throw new ExcelException("导出Excel失败");
			}
		}
	}

	/**
	 * @Description:将数据集合转换成excel并在浏览器进行下载
	 * @author zouhao
	 * @param list
	 * @param fieldMap
	 * @param sheetName
	 * @param out
	 * @throws ExcelException
	 */
	private static void doListToExcel(List list, LinkedHashMap<String, String> fieldMap, String sheetName,
			OutputStream out) throws ExcelException {
		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(sheetName);
			sheet.setDefaultColumnWidth(20);
			fillSheet(sheet, list, fieldMap);
			wb.write(out);
			out.flush();
			out.close();
			wb.close();
		} catch (Exception e) {
			if (e instanceof ExcelException) {
				throw (ExcelException) e;
			} else {
				throw new ExcelException("导出Excel失败");
			}
		}
	}

	/**
	 * 根据字段名获取字段值
	 * 
	 * @author zouhao
	 * @param fieldName
	 * @param o
	 * @return
	 * @throws Exception
	 */
	private static Object getFieldValueByName(String fieldName, Object o) throws Exception {

		Object value = null;
		Field field = getFieldByName(fieldName, o.getClass());

		if (field != null) {
			field.setAccessible(true);
			value = field.get(o);
		} else {
			throw new ExcelException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
		}
		return value;
	}

	/**
	 * 根据字段名获取字段
	 * 
	 * @author zouhao
	 * @param fieldName
	 * @param clazz
	 * @return
	 */
	private static Field getFieldByName(String fieldName, Class<?> clazz) {
		// 拿到本类的所有字段
		Field[] selfFields = clazz.getDeclaredFields();

		// 如果本类中存在该字段，则返回
		for (Field field : selfFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		// 否则，查看父类中是否存在此字段，如果有则返回
		Class<?> superClazz = clazz.getSuperclass();
		if (superClazz != null && superClazz != Object.class) {
			return getFieldByName(fieldName, superClazz);
		}

		// 如果本类和父类都没有，则返回空
		return null;
	}

	/**
	 * 根据带路径或不带路径的属性名获取属性值
	 * 即接受简单属性名，如userName等，又接受带路径的属性名，如student.department.name等
	 * 
	 * @author zouhao
	 * @param fieldNameSequence
	 * @param o
	 * @return
	 * @throws Exception
	 */
	private static Object getFieldValueByNameSequence(String fieldNameSequence, Object o) throws Exception {

		Object value = null;

		// 将fieldNameSequence进行拆分
		String[] attributes = fieldNameSequence.split("\\.");
		if (attributes.length == 1) {
			value = getFieldValueByName(fieldNameSequence, o);
		} else {
			// 根据属性名获取属性对象
			Object fieldObj = getFieldValueByName(attributes[0], o);
			String subFieldNameSequence = fieldNameSequence.substring(fieldNameSequence.indexOf(".") + 1);
			value = getFieldValueByNameSequence(subFieldNameSequence, fieldObj);
		}
		return value;

	}

	/**
	 * 向工作表中填充数据
	 * 
	 * @author zouhao
	 * @param sheet
	 * @param list
	 * @param fieldMap
	 * @param firstIndex
	 * @param lastIndex
	 * @throws Exception
	 */
	private static void fillSheet(XSSFSheet sheet, List list, LinkedHashMap<String, String> fieldMap) throws Exception {

		// 定义存放英文字段名和中文字段名的数组
		String[] enFields = new String[fieldMap.size()];
		String[] cnFields = new String[fieldMap.size()];

		// 填充数组
		int count = 0;
		for (Entry<String, String> entry : fieldMap.entrySet()) {
			enFields[count] = entry.getKey();
			cnFields[count] = entry.getValue();
			count++;
		}
		// 填充表头
		XSSFRow row = sheet.createRow(0);
		for (int i = 0; i < cnFields.length; i++) {
			row.createCell(i).setCellValue(cnFields[i]);
		}

		// 填充内容
		if (CollectionUtils.isEmpty(list)) {
			log.error("无任务详情数据可导出");
		} else {
			int firstIndex = 0;
			int lastIndex = CollectionUtils.isEmpty(list) ? 0 : list.size() - 1;
			int rowNo = 1;
			for (int index = firstIndex; index <= lastIndex; index++) {
				XSSFRow temRow = sheet.createRow(rowNo);
				// 获取单个对象
				Object item = list.get(index);
				for (int i = 0; i < enFields.length; i++) {
					Object objValue = getFieldValueByNameSequence(enFields[i], item);
					String fieldValue = formatFieldData(objValue);
					temRow.createCell(i).setCellValue(fieldValue);
				}
				rowNo++;
			}
		}
	}

	/**
	 * @Description:TODO
	 * @author zouhao
	 * @param obj
	 * @return
	 */
	private static String formatFieldData(Object obj) {
		if (null == obj) {
			return "";
		}
		if (Date.class.isAssignableFrom(obj.getClass())) {
			return sdf.format(obj);
		}
		return obj.toString();
	}

}
