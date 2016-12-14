package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ComplainDao;
import com.earl.cas.entity.Complain;
import com.earl.cas.service.ComplainService;

/**
 * Complain的service层实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("complainService")
@Transactional
public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements
		ComplainService {

	private static Logger logger = LoggerFactory
			.getLogger(ComplainServiceImpl.class);

	@Resource
	private ComplainDao complainDao;


	@Override
	protected BaseDao<Complain> getDao() {
		return complainDao;
	}


}
