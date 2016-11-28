package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.UserDetailsDao;
import com.earl.cas.entity.UserDetails;
import com.earl.cas.service.UserDetailsService;

/**
 * userDetailsService实现类.
 * @author 宋
 * @date 2016-11-23
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetails>
		implements UserDetailsService {

	private static Logger logger = LoggerFactory
			.getLogger(UserDetailsServiceImpl.class);

	@Resource
	private UserDetailsDao userDetailsDao;

	@Override
	protected BaseDao<UserDetails> getDao() {
		return userDetailsDao;
	}

	@Override
	public UserDetails getByUserId(int id) {
		logger.info("进入userDetailsService层的getByUserId方法");
		return userDetailsDao.getByUserId(id);
	}

}
