package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.ActivityDao;
import com.earl.cas.entity.Activity;
import com.earl.cas.service.ActivityService;

/**
 * activityService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("activityService")
@Transactional
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements
ActivityService {

	private static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

	@Resource
	private ActivityDao activityDao;//spring自动注入，不需要去实例化就可以引用

	@Override
	protected BaseDao<Activity> getDao() {
		return activityDao;
	}


}
