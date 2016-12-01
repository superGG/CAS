package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.dao.UserDetailsDao;
import com.earl.cas.entity.Message;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.service.MessageService;
import com.earl.cas.vo.PageInfo;

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

	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDetailsDao userDetailsDao;

	@Override
	protected BaseDao<Message> getDao() {
		return messageDao;
	}

	@Override
	public Boolean update(Message message) {
		messageDao.update(message);
		return true;
	}
    
	//根据父留言id查找子留言
	@Override
	public List<Message> findDetail(int fatherId) {
		logger.info("进入MessageServiceImpl留言板块的findDetail查询子留言方法");
		List<Message> detailList = messageDao.findDetail(fatherId);
		List<Message> twoMessageList = new ArrayList<Message>();
		for(Message message : detailList){
			UserDetails userDetail = userDetailsDao.get(message.getDetailId());
			message.setUserName(userDetail.getName());
			twoMessageList.add(message);
		}
		return twoMessageList;
	}

	@Override
	public List<Message> getOneMessgae(Message message, PageInfo pageInfo) {
		logger.info("进入MessageServiceImpl留言板块的getOneMessgae查询所有父留言方法");
		List<Message> fatherList = messageDao.findByGivenCriteria(message, pageInfo);
		List<Message> oneMessageList = new ArrayList<Message>();
		for(Message index : fatherList){
			UserDetails userDetail = userDetailsDao.get(index.getDetailId());
			index.setUserName(userDetail.getName());
			index.setSonSize(messageDao.findDetail(index.getId()).size());
			oneMessageList.add(index);
		}
		return oneMessageList;
	}
}
