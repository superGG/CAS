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
import com.earl.cas.entity.Message;
import com.earl.cas.exception.DomainSecurityException;
import com.earl.cas.service.MessageService;
import com.earl.cas.vo.PageInfo;
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
	 * 得到所有留言
	 */
	@RequestMapping(value = "/getAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getAll(PageInfo pageInfo) {
		logger.debug("REST request to get all message");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Message> messageList = messageService.getAlls(pageInfo);
		result.getResultParm().put("message", messageList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 模糊查询所有留言内容
	 */
	@RequestMapping(value = "/searchAlls", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> searchAlls(String search, PageInfo pageInfo) {
		logger.debug("REST request to get all message");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Message> messageList = messageService.searchAlls(search,pageInfo);
		result.getResultParm().put("message", messageList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 *根据父留言id查到子留言
	 */
	@RequestMapping(value = "/getTwoMessgae", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getDetail(Integer fatherId) {
		logger.debug("REST request to get all message");
		result = new ResultMessage();
		result.setServiceResult(true);
		List<Message> detailList = messageService.findDetail(fatherId);
		result.getResultParm().put("message", detailList);
		result.getResultParm().put("total", detailList.size());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 查询所有父留言.
	 *@author 宋.
	 * @param indexPageNum 当前页
	 * @param size 每页数量 
	 * @return
	 */
	@RequestMapping(value = "/getOneMessgae", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<ResultMessage> getOneMessgae(PageInfo pageInfo) {
		logger.info("查询所有父留言");
		result = new ResultMessage();
		result.setServiceResult(true);
		Message message = new Message();
		message.setFatherId(0);
		List<Message> detailList = messageService.getOneMessgae(message, pageInfo);
		result.getResultParm().put("message", detailList);
		result.getResultParm().put("total", pageInfo.getTotalCount());
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}

	/**
	 *根据id删除留言
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
	 * 添加留言
	 * @param content
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> saveMessage(Message message){
		logger.debug("REST request to save message");
		if(StringUtils.isBlank(message.getContent())){//判断评论内容是否为空
			throw new DomainSecurityException("评论内容不能为空");
		}
		result = new ResultMessage();
		if (message.getFatherId() == null) { //如果是父留言
			message.setFatherId(0);
		}
		message.setBad(0);//新留言初始化踩赞位0
		message.setGood(0);
		messageService.save(message);
		result.setServiceResult(true);
		result.setResultInfo("添加成功");
		result.getResultParm().put("message", messageService.findById(message.getId()));
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 更新留言
	 */
	@RequestMapping(value = "/update",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> update(Message message) {
		logger.debug("REST request to update Message");
		result = new ResultMessage();
		messageService.updateWithNotNullProperties(message);
		message = messageService.findById(message.getId());
		result.setServiceResult(true);
		result.setResultInfo("更新成功");
		result.getResultParm().put("message", message);
		return new ResponseEntity<ResultMessage>(result,HttpStatus.OK);
	}
	
	/**
	 * 获取留言详情.
	 *@author Kellan.
	 * @return
	 */
	@RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResultMessage> findById(Integer id) {
		result = new ResultMessage();
		result.setServiceResult(true);
		Message message = messageService.findById(id);
		result.getResultParm().put("message", message);
		return new ResponseEntity<ResultMessage>(result, HttpStatus.OK);
	}
	

}
