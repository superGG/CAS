package com.earl.cas.dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import com.earl.cas.commons.dao.BaseDaoImpl;
import com.earl.cas.dao.ApplyDao;
import com.earl.cas.entity.Apply;

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
				.createQuery(hql).setInteger("id", id).setInteger("statue", 0)
				.list();
		return applylist;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getApplyByClubIdStatueNotTwo(int id) {
		String hql = "from Apply where  clubId = :id and statue < :statue";
		List<Apply> applylist = (List<Apply>) getCurrentSession()
				.createQuery(hql).setInteger("id", id).setInteger("statue", 2)
				.list();
		return applylist;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getApplyByClubIdStatueIsTwo(int id) {
		String hql = "from Apply where  clubId = :id and statue = :statue";
		List<Apply> applylist = (List<Apply>) getCurrentSession()
				.createQuery(hql).setInteger("id", id).setInteger("statue", 2)
				.list();
		return applylist;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getPageApplyByClubIdStatusIsOk(int clubId, int pageIndex) {
		String hql = "from Apply where  clubId = :id and statue = :statue";
		List<Apply> applylist = getCurrentSession().createQuery(hql)
				.setInteger("id", clubId).setInteger("statue", 0)
				.setFirstResult((pageIndex - 1) * 10).setMaxResults(10).list();
		return applylist;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getByName(Integer id, String name) {
		String hql = "from Apply where  clubId = :id and statue = :statue and name like :name";
		List<Apply> applylist = getCurrentSession().createQuery(hql)
				.setInteger("id", id).setInteger("statue", 0)
				.setString("name", "%" + name + "%").list();
		return applylist;
	}

	@SuppressWarnings("unchecked")
	public List<Apply> getBydetailIdStatueIsOk(int detailId) {
		String hql = "from Apply where  detailId = :detailId and statue = 0";
		List<Apply> applylist = getCurrentSession().createQuery(hql)
				.setInteger("detailId", detailId).list();
		return applylist;
	}

	public void deleteByClubId(int clubId) {
		String hql = "delete Apply where clubId = :clubId";
		getCurrentSession().createQuery(hql).setInteger("clubId", clubId)
				.executeUpdate();
		getCurrentSession().flush();
	}

	public Apply getByDetailIdAndClubId(int detailId, int clubId) {
		String hql = "from Apply where detailId = :detailId and clubId = :clubId";
		return (Apply) getCurrentSession().createQuery(hql)
				.setInteger("detailId", detailId).setInteger("clubId", clubId)
				.uniqueResult();
	}
}