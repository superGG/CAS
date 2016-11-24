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
import com.earl.cas.entity.Club;
import com.earl.cas.service.ClubService;
import com.earl.cas.vo.ResultMessage;

/**
 * Club的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/club")
public class ClubController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(ClubController.class);

	@Autowired
	private ClubService clubService;

	private ResultMessage result = null;

	/**
	 * GET /club -> get all the club
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all club");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Club> clubList = clubService.findAll();
		result.getResultParm().put("club", clubList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	

}