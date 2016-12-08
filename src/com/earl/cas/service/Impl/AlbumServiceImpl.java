package com.earl.cas.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.commons.service.BaseServiceImpl;
import com.earl.cas.dao.AlbumDao;
import com.earl.cas.dao.ClubDao;
import com.earl.cas.dao.PhotoDao;
import com.earl.cas.entity.Album;
import com.earl.cas.entity.Club;
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

	@Autowired
	private AlbumDao albumDao;
	
	@Autowired
	private ClubDao clubDao;
	
	@Autowired
	private PhotoDao photoDao;
	
	@Override
	protected BaseDao<Album> getDao() {
		return albumDao;
	}

	@Override
	public List<Album> getByClubId(Integer id) {
		logger.info("进入AlbumServiceImpl层的getByClubId方法");
		List<Album> albumList = new ArrayList<Album>(); 
		List<Album> list = albumDao.getByClubId(id);
		Club club = clubDao.get(id);
		for(Album album : list) {
			Long photoNum = photoDao.getPhotoNumByAlbumId(album.getId());
			album.setPhotoNumber(photoNum);
			album.setClubName(club.getName());
			albumList.add(album);
		}
		return albumList;
	}

}
