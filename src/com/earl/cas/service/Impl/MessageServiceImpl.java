package com.earl.cas.service.Impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.entity.Message;
import com.earl.cas.service.MessageService;

/**
 * messageService实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("messageService")
@Transactional
public class MessageServiceImpl extends BaseServiceImpl<Message> implements
		MessageService {

	private static Logger logger = LoggerFactory
			.getLogger(MessageServiceImpl.class);

	@Resource
	private MessageDao messageDao;

	@Override
	protected BaseDao<Message> getDao() {
		return messageDao;
	}

	@Override
	public Boolean update(Message message) {
		messageDao.update(message);
		return true;
	}

	@Override
	public List<Message> findDetail(int fatherId) {
		logger.info("进入MessageServiceImpl留言板块的findDetail查询子留言方法");
		List<Message> detaillist = messageDao.findDetail(fatherId);
		return detaillist;
	}
}
