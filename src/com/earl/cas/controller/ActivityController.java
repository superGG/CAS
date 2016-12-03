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
import com.earl.cas.entity.Club;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ActivityService;
import com.earl.cas.vo.PageInfo;
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
	 *创建活动,标题和内容均不能为空
	 *@param activity
	 *@author 祝
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> createActivity(Activity activity){
		logger.debug("REST request to save activity");
		//判断输入的活动标题或者活动内容是否为空
		if(StringUtils.isBlank(activity.getContent()) | StringUtils.isBlank(activity.getTitle())){//判断活动标题和内容是否为空
			throw new DomainSecurityException("活动内容或活动标题均不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.save(activity);
		result.setResultInfo("创建成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 删除活动
	 * @param id
	 * @author 祝
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
	 * 修改活动，标题和内容均不能为空
	 * @param activity
	 * @author 祝
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateActivity(Activity activity) {
		logger.debug("REST request to update activity");
		//判断输入的活动标题或者活动内容是否为空
		if (StringUtils.isBlank(activity.getContent()) | StringUtils.isBlank(activity.getTitle())) {//判断活动标题和内容是否为空
			throw new DomainSecurityException("活动内容或活动标题均不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		activityService.updateActivity(activity);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 *  查看所有活动
	 *  @author 祝
	 *  @param indexPageNum 当前页
	 * @param size 每页数量 
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)//返回结果是jason类型
	public  ResponseEntity<ResultMessage> getAllActivity(PageInfo pageInfo) {
		logger.debug("REST request to get all activity");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> activityList = activityService.findAllActivity(pageInfo);
		result.getResultParm().put("activity", activityList);
		result.getResultParm().put("total",pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 根据社团id查找社团活动
	 * @param activity
	 * @param indexPageNum 当前页
	 * @param size 每页数量 
	 * @author 祝
	 */
	@RequestMapping(value = "/getClubActivity",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getClubActivity(PageInfo pageInfo,Activity activity){
		logger.debug("通过社团id查找社团活动");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Activity> clubActivityList = activityService.findByClubId(activity, pageInfo);
		result.getResultParm().put("/Activity", clubActivityList);
		result.getResultParm().put("total",pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 *查看活动详情
	 *@param id
	 *@author 祝
	 */
	@RequestMapping(value = "/getDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getDetail(int id) {
		logger.debug("REST request to get activityDetail");
		result = new ResultMessage();
		result.setServiceResult(true);
		Activity activityDetail = activityService.findDetail(id);
		result.getResultParm().put("activity", activityDetail);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
		

}
