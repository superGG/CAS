	package com.earl.cas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.earl.cas.commons.BaseController;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.UserDetailsService;
import com.earl.cas.util.FileUploadUtil;
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

//	@Autowired
//	private UserService userService;
	
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
	public ResponseEntity<ResultMessage> update(UserDetails userDetail, MultipartFile file, HttpServletRequest request) {
		if (userDetail.getId() == 0) {
			throw new DomainSecurityException("用户详情id不能为空");
		}
		result = new ResultMessage();
		if (file!=null) {
			logger.info("file不为空，开始处理上传头像");
			String headPath = FileUploadUtil.NewFileUpload(request, file,"headpath");
			logger.info("上传头像访问地址："+ headPath);
			userDetail.setHeadPath(headPath);
		} 
		userDetailsService.updateWithNotNullProperties(userDetail);
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		result.getResultParm().put("userDetail", userDetailsService.get(userDetail.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 修改用户头像
	 */
	@RequestMapping(value = "/updateUserImage",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> updateUserImage(UserDetails userDetail, MultipartFile file, HttpServletRequest request) {
		if (userDetail.getId() == 0) {
			throw new DomainSecurityException("用户详情id不能为空");
		}
		result = new ResultMessage();
		if (!file.isEmpty()) {
			logger.info("file不为空，开始处理上传头像");
			String headPath = FileUploadUtil.NewFileUpload(request, file,"headpath");
			logger.info("上传头像访问地址："+ headPath);
			userDetail.setHeadPath(headPath);
		} 
		userDetailsService.updateWithNotNullProperties(userDetail);
		result.setServiceResult(true);
		result.setResultInfo("上传头像成功");
		//result.getResultParm().put("userDetail", userDetailsService.get(userDetail.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}

}
