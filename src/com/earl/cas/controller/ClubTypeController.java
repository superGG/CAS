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
import com.earl.cas.exception.DomainSecurityException;
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
	 * 查看全部社团类型
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
	 * 根据id删除社团类型
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
	 * 添加社团类型
	 * 
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(ClubType clubtype) {
		logger.debug("REST request to delete clubType");
		//查找输入的名字是否在数据库中存在
		List<ClubType> typeName = clubTypeService.getByClubTypeName(clubtype);
		if(!typeName.isEmpty()){
			throw new DomainSecurityException("社团类型已存在");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		clubTypeService.save(clubtype);
		result.setResultInfo("增加成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 更新社团类型
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(ClubType clubtype) {
		logger.debug("REST request to update ClubType");
		//查找输入的名字是否在数据库中存在
		List<ClubType> typeName = clubTypeService.getByClubTypeName(clubtype);
		if(!typeName.isEmpty()){
			throw new DomainSecurityException("社团类型已存在");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		clubTypeService.update(clubtype);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
}
