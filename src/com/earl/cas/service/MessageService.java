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
	

	/*
	 * 根据id查询留言
	 */
	Message findById(Integer id);

	/*
	 * 模糊查询所有留言内容
	 */
	List<Message> searchAlls(String search, PageInfo pageInfo);

	/*
	 * 分页查询留言内容
	 */
	List<Message> getAlls(PageInfo pageInfo);


}