package com.earl.cas.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.earl.cas.entity.Message;
import com.earl.cas.exception.DomainSecurityException;
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
	 *PSOT /activity -> 创建活动,标题和内容均不能为空
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> createActivity(Activity activity){
		logger.debug("REST request to save activity");
		if(StringUtils.isBlank(activity.getContent()) | StringUtils.isBlank(activity.getTitle())){
			throw new DomainSecurityException("标题和内容均不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.save(activity);
		result.setResultInfo("创建成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 *POST /activity -> 删除活动
	 */
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deleteActivity(Integer id) {
		logger.debug("REST request to delete activity");
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
		
	}
	
	/**
	 *POST /activity ->  修改活动，标题和内容均不能为空
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateActivity(Activity activity) {
		logger.debug("REST request to update activity");
		if (StringUtils.isBlank(activity.getContent()) | StringUtils.isBlank(activity.getTitle())) {
			throw new DomainSecurityException("内容或标题均不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.updateActivity(activity);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * GET /activity -> 查看所有活动
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
	
	/**
	 *POST /activity -> 查看活动详情
	 */
	@RequestMapping(value = "/getDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getDetail(int id) {
		logger.debug("REST request to get activityDetail");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> detailList = activityService.findDetail(id);
		result.getResultParm().put("activity", detailList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	
	

}
