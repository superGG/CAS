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
import com.earl.cas.entity.User;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.service.ApplyService;
import com.earl.cas.service.UserDetailsService;
import com.earl.cas.vo.ResultMessage;

/**
 * Apply的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/apply")
public class ApplyController extends BaseController {

	private final Logger logger = LoggerFactory
			.getLogger(ApplyController.class);

	@Autowired
	private ApplyService applyService;

	@Autowired
	private UserDetailsService userDetailsService;
	
	private ResultMessage result = null;

	/**
	 * GET /apply -> get all the apply
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Apply> applyList = applyService.findAll();
		result.getResultParm().put("apply", applyList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 同意申请并添加成员
	 */
	@RequestMapping(value = "/agree", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> agree(Apply apply) {
		logger.debug("REST request to agree a apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		applyService.update(apply);
		result.setResultInfo("该成员已加入社团");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 查看申请书
	 */
	@RequestMapping(value = "/displayApply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayApply() {
		logger.debug("REST request to display all apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Apply> applylist = applyService.findAll();
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 查看自己社团的入社申请书->申请书管理列表
	 */
	@RequestMapping(value = "/displayClubApply", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> displayClubApply(int userId) {
		logger.debug("REST request to display club apply");
		result = new ResultMessage();
		result.setServiceResult(true);
		UserDetails userDetails = userDetailsService.getByUserId(userId); //前端传进来userId或者session获取
		List<Apply> applylist = applyService.getClubApply(userDetails.getId());
		result.getResultParm().put("apply", applylist);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
}
