package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Message;


public interface MessageDao extends BaseDao<Message>{
 
/*
 * 更新信息
 */
    public void update(Message message);
    
/*
 * 查找子留言  
 */
    public List<Message> findDetail(int fatherid);
}