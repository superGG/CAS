package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.service.ApplyService;

@Service("applyService")
@Transactional
public class ApplyServiceImpl extends BaseServiceImpl<Apply> implements
		ApplyService {

	private static Logger logger = LoggerFactory
			.getLogger(ApplyServiceImpl.class);

	@Resource
	private ApplyDao applyDao;

	@Override
	protected BaseDao<Apply> getDao() {
		return applyDao;
	}

}
