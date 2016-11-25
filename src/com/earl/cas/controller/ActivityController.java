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
import com.earl.cas.entity.Activity;
import com.earl.cas.service.ActivityService;
import com.earl.cas.vo.ResultMessage;

/**
 * Activity的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/activity")
public class ActivityController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	private ActivityService activityService;

	private ResultMessage result = null;

	/**
	 * GET /activity -> get all the activity
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)//返回结果是jason类型
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all activity");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> activityList = activityService.findAll();
		result.getResultParm().put("activity", activityList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
}
