package com.earl.cas.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Photo;


public interface PhotoService extends BaseService<Photo> {

	/**
	 * 多图片上传.
	 *@author 宋.
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	List<Photo> upload(HttpServletRequest request, HttpServletResponse response, Photo photo) throws IllegalStateException, IOException;

	

}