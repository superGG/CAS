package com.earl.cas.dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.entity.Apply;
import com.earl.cas.entity.Club;

/**
 * applyDao实现类.
 * 
 * @author 宋
 * @date 2016-11-23
 */
@Repository("applyDao")
public class ApplyDaoImpl extends BaseDaoImpl<Apply> implements ApplyDao {

	public boolean update(Apply apply) {
		try {
			getCurrentSession().update(apply);
			getCurrentSession().flush();
			return true;
		} catch (HibernateException e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getApplyByClubId(int id) {
		String hql = "from Apply where  clubId = :id";
		List<Apply> applylist = (List<Apply>) getCurrentSession()
				.createQuery(hql).setInteger("id", id).list();
		return applylist;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getApplyByClubIdStatusIsOk(int id) {
		String hql = "from Apply where  clubId = :id and statue = :statue";
		List<Apply> applylist = (List<Apply>) getCurrentSession()
				.createQuery(hql).setInteger("id", id).setInteger("statue", 1)
				.list();
		return applylist;
	}
}