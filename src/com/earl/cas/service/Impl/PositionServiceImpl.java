package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.entity.Position;
import com.earl.cas.service.PositionService;

/**
 * positionService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("positionService")
@Transactional
public class PositionServiceImpl extends BaseServiceImpl<Position> implements
		PositionService {

	private static Logger logger = LoggerFactory
			.getLogger(PositionServiceImpl.class);

	@Resource
	private PositionDao positionDao;

	@Override
	protected BaseDao<Position> getDao() {
		return positionDao;
	}

}
