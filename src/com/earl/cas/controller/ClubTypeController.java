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
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.School;
import com.earl.cas.service.ClubTypeService;
import com.earl.cas.vo.ResultMessage;

/**
 * ClubType的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/clubtype")
public class ClubTypeController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(ClubTypeController.class);

	@Autowired
	private ClubTypeService clubTypeService;

	private ResultMessage result = null;

	/**
	 * GET /clubType -> get all the clubType
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all clubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<ClubType> clubTypeList = clubTypeService.findAll();
		result.getResultParm().put("clubType", clubTypeList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * delete /clubType -> delete clubType
	 */
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(Integer id) {
		logger.debug("REST request to delete clubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubTypeService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	
	
	/**
	 * save /clubType -> save clubType
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(ClubType clubs) {
		logger.debug("REST request to delete clubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubTypeService.save(clubs);
		result.setResultInfo("增加成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * POST /school -> update clubType
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(ClubType clubs) {
		logger.debug("REST request to update ClubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		clubTypeService.update(clubs);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
}
