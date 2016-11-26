package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.SchoolDao;
import com.earl.cas.entity.School;
import com.earl.cas.service.SchoolService;

/**
 * schoolService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("schoolService")
@Transactional
public class SchoolServiceImpl extends BaseServiceImpl<School> implements
		SchoolService {

	private static Logger logger = LoggerFactory
			.getLogger(SchoolServiceImpl.class);

	@Resource
	private SchoolDao schoolDao;

	@Override
	protected BaseDao<School> getDao() {
		return schoolDao;
	}
	
	@Override
	public int deleteById(Integer id){
		return schoolDao.deleteById(id);
	}
}
