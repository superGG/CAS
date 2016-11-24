package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.UserDao;
import com.earl.cas.entity.User;
import com.earl.cas.service.UserService;

/**
 * User的service层实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("userService")
@Transactional

public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserDao userDao;

	@Override
	protected BaseDao<User> getDao() {
		return userDao;
	}


}
