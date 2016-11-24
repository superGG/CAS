package com.earl.cas.service.Impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.AlbumDao;
import com.earl.cas.entity.Album;
import com.earl.cas.service.AlbumService;

/**
 * albumService实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Service("albumService")
@Transactional
public class AlbumServiceImpl extends BaseServiceImpl<Album> implements
		AlbumService {

	private static Logger logger = LoggerFactory
			.getLogger(AlbumServiceImpl.class);

	@Resource
	private AlbumDao albumDao;

	@Override
	protected BaseDao<Album> getDao() {
		return albumDao;
	}

}
