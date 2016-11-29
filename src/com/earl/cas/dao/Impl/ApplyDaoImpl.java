package com.earl.cas.dao.Impl;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.entity.Apply;

/**
 * applyDao实现类.
 *@author 宋
 *@date 2016-11-23
 */
@Repository("applyDao")
public class ApplyDaoImpl extends BaseDaoImpl<Apply> implements ApplyDao {

	public boolean update(Apply apply){
		try{
			getCurrentSession().update(apply);
			getCurrentSession().flush();
			return true;
		}catch(HibernateException e){
			return false;
		}
	}

}