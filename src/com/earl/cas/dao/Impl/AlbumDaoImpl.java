package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.AlbumDao;
import com.earl.cas.entity.Album;

/**
 * albumDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("albumDao")
public class AlbumDaoImpl extends BaseDaoImpl<Album> implements AlbumDao {


}