package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.entity.ClubType;
import com.earl.cas.entity.Message;

/**
 * messageDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {

	@Override
	 public void update(Message message){
		String hql = "update from ClubType set content= :content where id= :id";
		getCurrentSession().createQuery(hql).setString("content",message.getContent()).setInteger("id", message.getId()).executeUpdate();
	}
}