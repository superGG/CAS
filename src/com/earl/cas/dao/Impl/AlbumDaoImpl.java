package com.earl.cas.dao.Impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Album> getByClubId(Integer id) {
		String hql = "from Album where id = :id";
		return getCurrentSession().createQuery(hql).setInteger("id", id).list();
	}


}