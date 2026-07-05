package com.mugsun.core.web.excel;

import cn.idev.excel.FastExcel;
import com.mugsun.core.tool.exception.ServiceException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Excel 导入导出工具（基于 FastExcel）：读上传文件为对象列表、导出对象列表为 xlsx 下载。
 * 字段与列的映射由目标类型的 @ExcelProperty 声明。
 */
public final class ExcelUtil {

	private ExcelUtil() {
	}

	/**
	 * 读取上传的 Excel 为对象列表（默认首个 sheet、首行表头）。
	 *
	 * @param file  上传文件
	 * @param clazz 目标类型
	 * @return 解析后的对象列表
	 */
	public static <T> List<T> read(MultipartFile file, Class<T> clazz) {
		try {
			return FastExcel.read(file.getInputStream()).head(clazz).sheet().doReadSync();
		} catch (IOException e) {
			throw new ServiceException("Excel 文件解析失败：" + e.getMessage());
		}
	}

	/**
	 * 导出对象列表为 xlsx 并写入响应流触发下载。
	 *
	 * @param response  HTTP 响应
	 * @param fileName  文件名（不含扩展名）
	 * @param sheetName sheet 名
	 * @param data      数据列表
	 * @param clazz     数据类型
	 */
	public static <T> void export(HttpServletResponse response, String fileName, String sheetName, List<T> data, Class<T> clazz) {
		try {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setCharacterEncoding(StandardCharsets.UTF_8.name());
			String encoded = URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+", "%20");
			response.setHeader("Content-Disposition", "attachment;filename*=UTF-8''" + encoded + ".xlsx");
			FastExcel.write(response.getOutputStream(), clazz).sheet(sheetName).doWrite(data);
		} catch (IOException e) {
			throw new ServiceException("Excel 导出失败：" + e.getMessage());
		}
	}
}
