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
import com.earl.cas.entity.School;
import com.earl.cas.entity.User;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.service.UserDetailsService;
import com.earl.cas.service.UserService;
import com.earl.cas.vo.ResultMessage;

/**
 * userDetails的controller.
 *@author 宋
 *@date 2016-11-25
 */
@RestController
@RequestMapping(value = "/userDetails")
public class UserDetailsController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(UserDetailsController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailsService userDetailsService;

	private ResultMessage result = null;

	/**
	 * GET /userDetails -> get all the userDetails
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all userDetails");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<UserDetails> userDetailsList = userDetailsService.findAll();
		result.getResultParm().put("userDetails", userDetailsList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 修改用户详情.
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(UserDetails userDetail) {
		logger.debug("REST request to update userDetail");
		result = new ResultMessage();
		result.setServiceResult(true);
		userDetailsService.updateWithNotNullProperties(userDetail);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	

}
