package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.entity.Club;
import com.earl.cas.service.ClubService;


/**
 * clubService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("clubService")
@Transactional
public class ClubServiceImpl extends BaseServiceImpl<Club> implements
ClubService {

	private static Logger logger = LoggerFactory.getLogger(ClubServiceImpl.class);

	@Resource
	private ClubDao clubDao;

	@Override
	protected BaseDao<Club> getDao() {
		return clubDao;
	}


}
