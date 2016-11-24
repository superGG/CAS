package com.earl.cas.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.Photo;
import com.earl.cas.service.PhotoService;
import com.earl.cas.vo.ResultMessage;

/**
 * Photo的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/photo")
public class PhotoController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	private PhotoService photoService;

	private ResultMessage result = null;

	/**
	 * GET /photo -> get all the photo
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all photo");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Photo> photoList = photoService.findAll();
		result.getResultParm().put("photo", photoList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	

}
