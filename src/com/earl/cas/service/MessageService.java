package com.earl.cas.service;

import java.util.List;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Message;
import com.earl.cas.vo.PageInfo;

public interface MessageService extends BaseService<Message> {

	/*
	 * 更新留言
	 */
	Boolean update(Message message);

	/*
	 * 查找子留言
	 */
	List<Message> findDetail(int fatherId);

	/*
	 * 查找父留言
	 */
	List<Message> getOneMessgae(Message message, PageInfo pageInfo);
	
	
	

}