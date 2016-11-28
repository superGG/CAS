package com.earl.cas.dao.Impl;

import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ClubTypeDao;
import com.earl.cas.entity.ClubType;

/**
 * clubTypeDao的实现类
 *@author 宋
 *@date 2016-8-15
 */
@Repository("clubTypeDao")
public class ClubTypeDaoImpl extends BaseDaoImpl<ClubType> implements ClubTypeDao {

	@Override
	public int delete(Integer id){
		String hql = "delete from ClubType where id = ?";
		return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();		
	}

}