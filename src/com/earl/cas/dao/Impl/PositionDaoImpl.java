package com.earl.cas.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.PositionDao;
import com.earl.cas.entity.Position;

/**
 * positionDao的实现类
 *@author 宋
 *@date 2016-11-23
 */
@Repository("positionDao")
public class PositionDaoImpl extends BaseDaoImpl<Position> implements PositionDao {
	/*
	 * 通过Id获取该社团职位
	 */
		@SuppressWarnings("unchecked")
		public List<Position> findByClubId(int id){
			String hql = "from Position where clubId = :id";
			List<Position> list = getCurrentSession().createQuery(hql).setInteger("id",id).list();
		//	logger.info(list.toString());
			return list;	
		}

}