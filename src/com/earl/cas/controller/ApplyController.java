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
import com.earl.cas.entity.Apply;
import com.earl.cas.service.ApplyService;
import com.earl.cas.vo.ResultMessage;

/**
 * Apply的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/apply")
public class ApplyController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(ApplyController.class);

	@Autowired
	private ApplyService applyService;

	private ResultMessage result = null;

	/**
	 * GET /apply -> get all the apply
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Apply> applyList = applyService.findAll();
		result.getResultParm().put("apply", applyList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	

}
