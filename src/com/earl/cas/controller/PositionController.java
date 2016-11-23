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
import com.earl.cas.entity.Position;
import com.earl.cas.service.PositionService;
import com.earl.cas.vo.ResultMessage;

/**
 * Position的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/position")
public class PositionController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(PositionController.class);

	@Autowired
	private PositionService positionService;

	private ResultMessage result = null;

	/**
	 * GET /position -> get all the position
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all position");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Position> positionList = positionService.findAll();
		result.getResultParm().put("position", positionList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	

}
