package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ClubcreateDao;
import com.earl.cas.entity.Clubcreate;
import com.earl.cas.service.ClubcreateService;

/**
 * clubcreateService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("clubcreateService")
@Transactional
public class ClubcreateServiceImpl extends BaseServiceImpl<Clubcreate> implements
ClubcreateService {

	private static Logger logger = LoggerFactory.getLogger(ClubcreateServiceImpl.class);

	@Resource
	private ClubcreateDao clubcreateDao;

	@Override
	protected BaseDao<Clubcreate> getDao() {
		return clubcreateDao;
	}


}
