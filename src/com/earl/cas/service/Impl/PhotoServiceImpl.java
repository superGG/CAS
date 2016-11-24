package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.PhotoDao;
import com.earl.cas.entity.Photo;
import com.earl.cas.service.PhotoService;

/**
 * photoService实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Service("photoService")
@Transactional
public class PhotoServiceImpl extends BaseServiceImpl<Photo> implements
		PhotoService {

	private static Logger logger = LoggerFactory
			.getLogger(PhotoServiceImpl.class);

	@Resource
	private PhotoDao photoDao;

	@Override
	protected BaseDao<Photo> getDao() {
		return photoDao;
	}

}
