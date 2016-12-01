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
import com.earl.cas.entity.Message;
import com.earl.cas.service.MessageService;
import com.earl.cas.vo.ResultMessage;

/**
 * Activity的controller.
 *@author 宋
 *@date 2016-11-23
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController extends BaseController {

	private final Logger logger = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;

	private ResultMessage result = null;

	/**
	 *GET /message -> 得到所有留言
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll() {
		logger.debug("REST request to get all message");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Message> messageList = messageService.findAll();
		result.getResultParm().put("message", messageList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 *POST /message -> 根据父留言id查到子留言
	 */
	@RequestMapping(value = "/getDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getDetail(int fatherId) {
		logger.debug("REST request to get all message");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Message> detailList = messageService.findDetail(fatherId);
		result.getResultParm().put("message", detailList);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 *POST /message -> 根据id删除留言
	 */
	@RequestMapping(value = "/deleteById",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> deleteMessage(Integer id) {
		logger.debug("REST request to delete message");
		result = new ResultMessage();
		result.setServiceResult(true);
		messageService.deleteById(id);
		result.setResultInfo("删除成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
		
	}
	
	/**
	 *POST /message -> 添加留言
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> saveMessage(Message message){
		logger.debug("REST request to save message");
		result = new ResultMessage();
		result.setServiceResult(true);
		messageService.save(message);
		result.setResultInfo("添加成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * POST /message -> 更新留言
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Message message) {
		logger.debug("REST request to update Message");
		result = new ResultMessage();
		result.setServiceResult(true);
		messageService.update(message);
		result.setResultInfo("更新成功");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
    /**
     * POST /message -> 点赞
     */
	@RequestMapping(value = "/like",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> goodTake(Integer id) {
		logger.debug("REST request to goodTake Message");
		result = new ResultMessage();
		result.setServiceResult(true);
		messageService.goodTake(id);
		result.setResultInfo("已点赞");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
   }
	/**
	 * POST /message -> 点踩
	 */
	@RequestMapping(value="/bad",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<ResultMessage> badTake(Integer id){
		logger.debug("REST request to goodTake Message");
		result = new ResultMessage();
		result.setServiceResult(true);
		messageService.badTake(id);
		result.setResultInfo("已踩");
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
   }
}
