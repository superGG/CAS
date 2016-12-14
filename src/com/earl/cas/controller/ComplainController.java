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
import com.earl.cas.entity.Complain;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.ComplainService;
import com.earl.cas.vo.ResultMessage;

/**
 * Complain的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/complain")
public class ComplainController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(ComplainController.class);

	@Autowired
	private ComplainService complainService;

	private ResultMessage result = null;

	/**
	 * 查看全部社团类型
	 * @author 祝
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all clubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Complain> clubTypeList = complainService.findAll();
		result.getResultParm().put("clubType", clubTypeList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 根据id删除社团类型
	 * @param id
	 * @author 祝
	 */
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(Integer id) {
		logger.debug("REST request to delete clubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		complainService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	
	
	/**
	 * 添加社团类型
	 * @param clubtype
	 *         类型名称name必填
	 * @author 祝
	 * 
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(Complain complain) {
		logger.debug("REST request to delete clubType");
		//判断社团类型名字是否为空
		if(StringUtils.isBlank(complain.getContent())){
			throw new DomainSecurityException("社团类型名字不能为空");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		complainService.save(complain);
		result.setResultInfo("增加成功");
		result.getResultParm().put("clubtype", complainService.get(complain.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 更新社团类型
	 * @param clubtype
	 * @author 祝
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Complain complain) {
		logger.debug("REST request to update ClubType");
		result = new ResultMessage();
		result.setServiceResult(true);
		complainService.updateWithNotNullProperties(complain);
		result.setResultInfo("更新成功");
		result.getResultParm().put("clubtype", complainService.get(complain.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
}