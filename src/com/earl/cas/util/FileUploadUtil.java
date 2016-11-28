package com.earl.cas.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类.
 * 
 * @author 宋
 * @date 2016-8-29
 */
public class FileUploadUtil {

	private static Logger logger = Logger.getLogger(FileUploadUtil.class);

	/**
	 * 新的文件上传方法
	 *@author 宋.
	 * @param request
	 * @param file
	 * @return
	 */
	public static String NewFileUpload(HttpServletRequest request,
			MultipartFile file) {
		logger.info("开始文件上传");
		String filePath = null;
		String fileName = null;
		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("/");
			logger.info("--------path: " + path);
			fileName = file.getOriginalFilename();
			File targetFile = new File(path + "/reports", fileName);
			while (targetFile.exists()) {//重命名解决
				fileName = newFileName(fileName);
				targetFile = new File(path + "/reports", fileName);
			}
			targetFile.mkdirs();
			// 保存
			try {
				file.transferTo(targetFile);
			} catch (Exception e) {
				logger.info("上传文件复制失败");
				e.printStackTrace();
			}
			filePath = "/reports/" + fileName;
		}
		logger.info("上传文件结束，filePath:" + filePath);
		return fileName;
	}

	/**
	 * 获取文件的真实名.
	 * 
	 * @author 宋.
	 * @param fileName
	 * @return
	 */
	private static String getRealName(String fileName) {
		String realName = null;
		int index = fileName.indexOf(".");
		realName = fileName.substring(0, index);
		return realName;
	}

	/**
	 * 文件重名时重命名文件.
	 *@author 宋.
	 * @param fileName
	 * @return
	 */
	private static String newFileName(String fileName) {
		StringBuffer newFileName = new StringBuffer();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String str = sdf.format(date);
		newFileName.append(getRealName(fileName)).append("(").append(str).append(")").append(".")
				.append(FilenameUtils.getExtension(fileName));
		return newFileName.toString();
	}

	
	
	
}
