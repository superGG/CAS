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
import com.earl.cas.vo.PageInfo;
import com.earl.cas.vo.ResultMessage;

/**
 * Complain的controller.
 *@author Kellan
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
	 * 查看全部举报信息
	 * @author kellan
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll(PageInfo pageInfo) {
		logger.debug("REST request to get all complain");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Complain> complainList = complainService.getAlls(pageInfo);
		result.getResultParm().put("complainList", complainList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 根据id删除举报信息
	 * @param id
	 * @author song
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
	 * 添加举报信息.
	 * @param complain
	 *         内容content必填
	 * @author Kellan
	 * 
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(Complain complain) {
		logger.debug("REST request to delete clubType");
		if(StringUtils.isBlank(complain.getContent())){
			throw new DomainSecurityException("举报内容不能为空");
		}
		if (complain.getDetailId() == null) {
			throw new DomainSecurityException("数据有误");
		}
		result = new ResultMessage();
		result.setServiceResult(true);
		complainService.save(complain);
		result.setResultInfo("感谢您为良好的网络环境贡献一份力！");
		result.getResultParm().put("complain", complainService.get(complain.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 更新举报状态
	 * @param id
	 * @param status
	 * @author song
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Complain complain) {
		logger.debug("REST request to update complain");
		result = new ResultMessage();
		result.setServiceResult(true);
		complainService.updateWithNotNullProperties(complain);
		result.setResultInfo("更新成功");
		result.getResultParm().put("clubtype", complainService.get(complain.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 获取举报详情.
	 *@author Kellan.
	 * @return
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> findById(Integer id) {
		result = new ResultMessage();
		result.setServiceResult(true);
		Complain complain = complainService.findById(id);
		result.getResultParm().put("complain", complain);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * 根据类型查询举报信息
	 * @param type 举报类型
	 * @author kellan
	 */
	@RequestMapping(value = "/getByType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll(Complain complain, PageInfo pageInfo) {
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Complain> complainList = complainService.getByType(complain,pageInfo);
		result.getResultParm().put("complainList", complainList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
}
