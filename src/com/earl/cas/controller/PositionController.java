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
import com.earl.cas.entity.Position;
import com.earl.cas.service.PositionService;
import com.earl.cas.vo.PageInfo;
import com.earl.cas.vo.ResultMessage;

/**
 * Position的controller.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@RestController
@RequestMapping(value = "/position")
public class PositionController extends BaseController {

	private final Logger logger = LoggerFactory
			.getLogger(PositionController.class);

	@Autowired
	private PositionService positionService;

	private ResultMessage result = null;

	/**
	 * GET /position -> get all the position
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all position");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Position> positionList = positionService.findAll();
		result.getResultParm().put("position", positionList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * GET /position -> get all the position  分页查询
	 * @param PageInfo
	 */
	@RequestMapping(value = "/getPageAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getAll(PageInfo pageInfo) {
		logger.debug("REST request to get all position");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Position> positionList = positionService.findAll(pageInfo);
		result.getResultParm().put("position", positionList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * POST /position -> 通过社团名字获得该社团职位信息
	 */
	@RequestMapping(value = "/getByClubName", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getByClubName(String name) {
		logger.debug("REST request to get club position");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Position> positionList = positionService.getByClubName(name);
		result.getResultParm().put("position", positionList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	

	/**
	 * GET /position -> 通过社团ID获得职位信息
	 */
	@RequestMapping(value = "/getByClubId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getByClubId(int id) {
		logger.debug("REST request to get club position");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Position> positionList = positionService.getByClubId(id);
		result.getResultParm().put("position", positionList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	
	/**
	 * GET /position -> 通过社团ID获得职位名字
	 */
	@RequestMapping(value = "/getNameByClubId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> getNameByClubId(int id) {
		logger.debug("REST request to get club positionName");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<String> positionNameList = positionService.getNameByClubId(id);
		result.getResultParm().put("position", positionNameList);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}

	/**
	 * 添加社团职位
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> save(Position position) {
		logger.debug("REST request to save position");
		result = new ResultMessage();
		result.setServiceResult(true);
		positionService.save(position);
		result.setResultInfo("添加成功");
		result.getResultParm().put("position",positionService.findById(position.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 更新社团职位
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Position position) {
		logger.debug("REST request to update position");
		result = new ResultMessage();
		result.setServiceResult(true);
		positionService.update(position);
		result.setResultInfo("更新成功");
		result.getResultParm().put("position",positionService.findById(position.getId()));
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	/**
	 * 删除职位
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> delete(int positionId) {
		logger.debug("REST request to delete position");
		result = new ResultMessage();
		result.setServiceResult(true);
		positionService.deleteById(positionId);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
}
