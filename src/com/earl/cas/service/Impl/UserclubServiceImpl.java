package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.UserclubDao;
import com.earl.cas.entity.Userclub;
import com.earl.cas.service.UserclubService;

@Service("userclubService")
@Transactional
public class UserclubServiceImpl extends BaseServiceImpl<Userclub> implements
		UserclubService {

	private static Logger logger = LoggerFactory
			.getLogger(UserclubServiceImpl.class);

	@Resource
	private UserclubDao userclubDao;

	@Override
	protected BaseDao<Userclub> getDao() {
		return userclubDao;
	}

}