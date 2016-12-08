package com.earl.cas.dao;

import com.earl.cas.commons.dao.BaseDao;
import com.earl.cas.entity.Photo;


public interface PhotoDao extends BaseDao<Photo>{
	
	Long getPhotoNumByAlbumId(int albumId);
	
}