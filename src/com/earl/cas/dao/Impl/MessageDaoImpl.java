package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.MessageDao;
import com.earl.cas.entity.Message;

/**
 * messageDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("messageDao")
public class MessageDaoImpl extends BaseDaoImpl<Message> implements MessageDao {


}