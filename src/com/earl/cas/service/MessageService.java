package com.earl.cas.service;

import com.earl.cas.commons.service.BaseService;
import com.earl.cas.entity.Message;


public interface MessageService extends BaseService<Message> {

  /*
   * 更新留言
   */
 public Boolean update(Message message);
}