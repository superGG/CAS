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
import com.earl.cas.entity.Album;
import com.earl.cas.service.AlbumService;
import com.earl.cas.vo.ResultMessage;

/**
 * Album的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/album")
public class AlbumController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(AlbumController.class);

	@Autowired
	private AlbumService albumService;

	private ResultMessage result = null;

	/**
	 * GET /album -> get all the album
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all album");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Album> albumList = albumService.findAll();
		result.getResultParm().put("album", albumList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	

}
