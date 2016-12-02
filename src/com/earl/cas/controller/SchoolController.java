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
import com.earl.cas.entity.School;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.SchoolService;
import com.earl.cas.vo.ResultMessage;

/**
 *school的controller.
 *@author Mr.Chen
 *@date 2016-11-25
 */
@RestController
@RequestMapping(value = "/school")
public class SchoolController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	private SchoolService schoolService;
	

	private ResultMessage result = null;

	/**
	 * GET /school -> get all the school
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all School");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<School> schoolList = schoolService.findAll();
		result.getResultParm().put("school", schoolList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * POST /school -> save school
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(School school) {
		logger.debug("REST request to save School");
		result = new ResultMessage();
		result.setServiceResult(true);
		schoolService.save(school);
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * POST /school -> delete school
	 */
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(Integer id) {
		logger.debug("REST request to delete school");
		result = new ResultMessage();
		result.setServiceResult(true);
		schoolService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	/**
	 * POST /school -> update school
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(School school) {
		logger.debug("REST request to update School");
		result = new ResultMessage();
		result.setServiceResult(true);
		schoolService.update(school);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}



}
