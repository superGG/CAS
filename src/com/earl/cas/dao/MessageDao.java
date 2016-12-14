package com.earl.cas.dao;

import java.util.List;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Message;
import com.earl.cas.vo.PageInfo;


public interface MessageDao extends BaseDao<Message>{
 
/*
 * 更新信息
 */
     Boolean update(Message message);
    
/*
 * 查找子留言  
 */
     List<Message> findDetail(int fatherid);

     /*
      * 模糊查询所有留言内容
      */
	List<Message> searchAlls(String search, PageInfo pageInfo);

}